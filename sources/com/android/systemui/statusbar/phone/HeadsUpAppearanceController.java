package com.android.systemui.statusbar.phone;

import android.util.MathUtils;
import android.view.View;
import com.android.internal.widget.ViewClippingUtil;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.plugins.DarkIconDispatcher;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.shade.ShadeViewController;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.CrossFadeHelper;
import com.android.systemui.statusbar.HeadsUpStatusBarView;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.NotificationContentView;
import com.android.systemui.statusbar.notification.row.wrapper.NotificationViewWrapper;
import com.android.systemui.statusbar.notification.stack.NotificationChildrenContainer;
import com.android.systemui.statusbar.notification.stack.NotificationRoundnessManager;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.Clock;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import com.android.systemui.statusbar.policy.KeyguardStateControllerImpl;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.util.ViewController;
import com.android.wm.shell.R;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class HeadsUpAppearanceController extends ViewController implements OnHeadsUpChangedListener, DarkIconDispatcher.DarkReceiver, NotificationWakeUpCoordinator.WakeUpListener {
    public static final SourceType$Companion$from$1 HEADS_UP = new SourceType$Companion$from$1("HeadsUp");
    public static final SourceType$Companion$from$1 PULSING = new SourceType$Companion$from$1("Pulsing");
    public boolean mAnimationsEnabled;
    float mAppearFraction;
    public final KeyguardBypassController mBypassController;
    public final Clock mClockView;
    public final CommandQueue mCommandQueue;
    public final DarkIconDispatcher mDarkIconDispatcher;
    float mExpandedHeight;
    public final HeadsUpManager mHeadsUpManager;
    public final HeadsUpNotificationIconInteractor mHeadsUpNotificationIconInteractor;
    public final KeyguardStateController mKeyguardStateController;
    public final NotificationRoundnessManager mNotificationRoundnessManager;
    public final Optional mOperatorNameViewOptional;
    public final AnonymousClass1 mParentClippingParams;
    public final PhoneStatusBarTransitions mPhoneStatusBarTransitions;
    public final HeadsUpAppearanceController$$ExternalSyntheticLambda1 mSetExpandedHeight;
    public final HeadsUpAppearanceController$$ExternalSyntheticLambda0 mSetTrackingHeadsUp;
    public final ShadeViewController mShadeViewController;
    public boolean mShown;
    public final NotificationStackScrollLayoutController mStackScrollerController;
    public final StatusBarStateController mStatusBarStateController;
    public ExpandableNotificationRow mTrackedChild;
    public final NotificationWakeUpCoordinator mWakeUpCoordinator;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.phone.HeadsUpAppearanceController$1, reason: invalid class name */
    public final class AnonymousClass1 implements ViewClippingUtil.ClippingParameters {
        public final boolean shouldFinish(View view) {
            return view.getId() == R.id.status_bar;
        }
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [com.android.systemui.statusbar.phone.HeadsUpAppearanceController$$ExternalSyntheticLambda1] */
    public HeadsUpAppearanceController(HeadsUpManager headsUpManager, StatusBarStateController statusBarStateController, PhoneStatusBarTransitions phoneStatusBarTransitions, KeyguardBypassController keyguardBypassController, NotificationWakeUpCoordinator notificationWakeUpCoordinator, DarkIconDispatcher darkIconDispatcher, KeyguardStateController keyguardStateController, CommandQueue commandQueue, NotificationStackScrollLayoutController notificationStackScrollLayoutController, ShadeViewController shadeViewController, NotificationRoundnessManager notificationRoundnessManager, HeadsUpStatusBarView headsUpStatusBarView, Clock clock, FeatureFlagsClassic featureFlagsClassic, HeadsUpNotificationIconInteractor headsUpNotificationIconInteractor, Optional optional) {
        super(headsUpStatusBarView);
        this.mSetTrackingHeadsUp = new HeadsUpAppearanceController$$ExternalSyntheticLambda0(this, 0);
        this.mSetExpandedHeight = new BiConsumer() { // from class: com.android.systemui.statusbar.phone.HeadsUpAppearanceController$$ExternalSyntheticLambda1
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                HeadsUpAppearanceController headsUpAppearanceController = HeadsUpAppearanceController.this;
                float floatValue = ((Float) obj).floatValue();
                float floatValue2 = ((Float) obj2).floatValue();
                float f = headsUpAppearanceController.mExpandedHeight;
                boolean z = floatValue != f;
                boolean z2 = f > 0.0f;
                headsUpAppearanceController.mExpandedHeight = floatValue;
                headsUpAppearanceController.mAppearFraction = floatValue2;
                if (z) {
                    ((BaseHeadsUpManager) headsUpAppearanceController.mHeadsUpManager).getAllEntries().forEach(new HeadsUpAppearanceController$$ExternalSyntheticLambda0(headsUpAppearanceController, 3));
                }
                if ((headsUpAppearanceController.mExpandedHeight > 0.0f) != z2) {
                    headsUpAppearanceController.updateTopEntry();
                }
            }
        };
        this.mParentClippingParams = new AnonymousClass1();
        this.mAnimationsEnabled = true;
        this.mNotificationRoundnessManager = notificationRoundnessManager;
        this.mHeadsUpManager = headsUpManager;
        this.mTrackedChild = shadeViewController.getShadeHeadsUpTracker$1().getTrackedHeadsUpNotification();
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        this.mAppearFraction = notificationStackScrollLayout.mLastSentAppear;
        this.mExpandedHeight = notificationStackScrollLayout.mLastSentExpandedHeight;
        this.mStackScrollerController = notificationStackScrollLayoutController;
        this.mShadeViewController = shadeViewController;
        this.mHeadsUpNotificationIconInteractor = headsUpNotificationIconInteractor;
        notificationStackScrollLayoutController.mHeadsUpAppearanceController = this;
        notificationStackScrollLayout.mHeadsUpAppearanceController = this;
        this.mClockView = clock;
        this.mOperatorNameViewOptional = optional;
        this.mDarkIconDispatcher = darkIconDispatcher;
        headsUpStatusBarView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.android.systemui.statusbar.phone.HeadsUpAppearanceController.2
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (HeadsUpAppearanceController.this.shouldBeVisible$1()) {
                    HeadsUpAppearanceController.this.updateTopEntry();
                    HeadsUpAppearanceController.this.mStackScrollerController.mView.requestLayout();
                }
                ((HeadsUpStatusBarView) HeadsUpAppearanceController.this.mView).removeOnLayoutChangeListener(this);
            }
        });
        this.mBypassController = keyguardBypassController;
        this.mStatusBarStateController = statusBarStateController;
        this.mPhoneStatusBarTransitions = phoneStatusBarTransitions;
        this.mWakeUpCoordinator = notificationWakeUpCoordinator;
        this.mCommandQueue = commandQueue;
        this.mKeyguardStateController = keyguardStateController;
    }

    public static void updateHeader(NotificationEntry notificationEntry) {
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        if (expandableNotificationRow.mHeaderVisibleAmount != 1.0f) {
            expandableNotificationRow.mHeaderVisibleAmount = 1.0f;
            for (NotificationContentView notificationContentView : expandableNotificationRow.mLayouts) {
                NotificationViewWrapper notificationViewWrapper = notificationContentView.mContractedWrapper;
                if (notificationViewWrapper != null) {
                    notificationViewWrapper.setHeaderVisibleAmount(1.0f);
                }
                NotificationViewWrapper notificationViewWrapper2 = notificationContentView.mHeadsUpWrapper;
                if (notificationViewWrapper2 != null) {
                    notificationViewWrapper2.setHeaderVisibleAmount(1.0f);
                }
                NotificationViewWrapper notificationViewWrapper3 = notificationContentView.mExpandedWrapper;
                if (notificationViewWrapper3 != null) {
                    notificationViewWrapper3.setHeaderVisibleAmount(1.0f);
                }
            }
            NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow.mChildrenContainer;
            if (notificationChildrenContainer != null) {
                notificationChildrenContainer.mHeaderVisibleAmount = 1.0f;
                notificationChildrenContainer.mCurrentHeaderTranslation = (int) (0.0f * notificationChildrenContainer.mTranslationForHeader);
            }
            expandableNotificationRow.notifyHeightChanged(false);
        }
    }

    public final void hide(final View view, final int i, final HeadsUpAppearanceController$$ExternalSyntheticLambda4 headsUpAppearanceController$$ExternalSyntheticLambda4) {
        if (this.mAnimationsEnabled) {
            CrossFadeHelper.fadeOut(view, 110L, new Runnable() { // from class: com.android.systemui.statusbar.phone.HeadsUpAppearanceController$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    View view2 = view;
                    int i2 = i;
                    HeadsUpAppearanceController$$ExternalSyntheticLambda4 headsUpAppearanceController$$ExternalSyntheticLambda42 = headsUpAppearanceController$$ExternalSyntheticLambda4;
                    view2.setVisibility(i2);
                    if (headsUpAppearanceController$$ExternalSyntheticLambda42 != null) {
                        headsUpAppearanceController$$ExternalSyntheticLambda42.run();
                    }
                }
            });
            return;
        }
        view.setVisibility(i);
        if (headsUpAppearanceController$$ExternalSyntheticLambda4 != null) {
            headsUpAppearanceController$$ExternalSyntheticLambda4.run();
        }
    }

    public boolean isShown() {
        return this.mShown;
    }

    @Override // com.android.systemui.plugins.DarkIconDispatcher.DarkReceiver
    public final void onDarkChanged(ArrayList arrayList, float f, int i) {
        HeadsUpStatusBarView headsUpStatusBarView = (HeadsUpStatusBarView) this.mView;
        headsUpStatusBarView.mTextView.setTextColor(DarkIconDispatcher.getTint(arrayList, headsUpStatusBarView, i));
    }

    @Override // com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator.WakeUpListener
    public final void onFullyHiddenChanged(boolean z) {
        updateTopEntry();
    }

    @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
    public final void onHeadsUpPinned(NotificationEntry notificationEntry) {
        updateTopEntry();
        updateHeader(notificationEntry);
        updateHeadsUpAndPulsingRoundness(notificationEntry);
    }

    @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
    public final void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z) {
        updateHeadsUpAndPulsingRoundness(notificationEntry);
        PhoneStatusBarTransitions phoneStatusBarTransitions = this.mPhoneStatusBarTransitions;
        phoneStatusBarTransitions.mIsHeadsUp = z;
        phoneStatusBarTransitions.applyMode(phoneStatusBarTransitions.mMode, false);
    }

    @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
    public final void onHeadsUpUnPinned(NotificationEntry notificationEntry) {
        updateTopEntry();
        updateHeader(notificationEntry);
        updateHeadsUpAndPulsingRoundness(notificationEntry);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewAttached() {
        ((BaseHeadsUpManager) this.mHeadsUpManager).addListener(this);
        View view = this.mView;
        ((HeadsUpStatusBarView) view).mOnDrawingRectChangedListener = new HeadsUpAppearanceController$$ExternalSyntheticLambda4(this, 1);
        this.mHeadsUpNotificationIconInteractor.repository.isolatedIconLocation.setValue(((HeadsUpStatusBarView) view).mIconDrawingRect);
        this.mWakeUpCoordinator.wakeUpListeners.add(this);
        ShadeViewController shadeViewController = this.mShadeViewController;
        shadeViewController.getShadeHeadsUpTracker$1().addTrackingHeadsUpListener(this.mSetTrackingHeadsUp);
        shadeViewController.getShadeHeadsUpTracker$1().setHeadsUpAppearanceController(this);
        this.mStackScrollerController.mView.mExpandedHeightListeners.add(this.mSetExpandedHeight);
        this.mDarkIconDispatcher.addDarkReceiver(this);
    }

    @Override // com.android.systemui.util.ViewController
    public final void onViewDetached() {
        ((BaseHeadsUpManager) this.mHeadsUpManager).mListeners.remove(this);
        ((HeadsUpStatusBarView) this.mView).mOnDrawingRectChangedListener = null;
        this.mHeadsUpNotificationIconInteractor.repository.isolatedIconLocation.setValue(null);
        this.mWakeUpCoordinator.wakeUpListeners.remove(this);
        ShadeViewController shadeViewController = this.mShadeViewController;
        shadeViewController.getShadeHeadsUpTracker$1().removeTrackingHeadsUpListener(this.mSetTrackingHeadsUp);
        shadeViewController.getShadeHeadsUpTracker$1().setHeadsUpAppearanceController(null);
        this.mStackScrollerController.mView.mExpandedHeightListeners.remove(this.mSetExpandedHeight);
        this.mDarkIconDispatcher.removeDarkReceiver(this);
    }

    public void setAnimationsEnabled(boolean z) {
        this.mAnimationsEnabled = z;
    }

    public final void setShown(boolean z) {
        if (this.mShown != z) {
            this.mShown = z;
            Clock clock = this.mClockView;
            if (z) {
                ViewClippingUtil.setClippingDeactivated(this.mView, true, this.mParentClippingParams);
                ((HeadsUpStatusBarView) this.mView).setVisibility(0);
                show(this.mView);
                hide(clock, 4, null);
                this.mOperatorNameViewOptional.ifPresent(new HeadsUpAppearanceController$$ExternalSyntheticLambda0(this, 1));
            } else {
                show(clock);
                this.mOperatorNameViewOptional.ifPresent(new HeadsUpAppearanceController$$ExternalSyntheticLambda0(this, 2));
                hide(this.mView, 8, new HeadsUpAppearanceController$$ExternalSyntheticLambda4(this, 0));
            }
            if (this.mStatusBarStateController.getState() != 0) {
                this.mCommandQueue.recomputeDisableFlags(((HeadsUpStatusBarView) this.mView).getContext().getDisplayId(), false);
            }
        }
    }

    public final boolean shouldBeVisible$1() {
        boolean z = this.mWakeUpCoordinator.notificationsFullyHidden;
        boolean z2 = (((this.mExpandedHeight > 0.0f ? 1 : (this.mExpandedHeight == 0.0f ? 0 : -1)) > 0) || z) ? false : true;
        if (this.mBypassController.getBypassEnabled() && ((this.mStatusBarStateController.getState() == 1 || ((KeyguardStateControllerImpl) this.mKeyguardStateController).mKeyguardGoingAway) && !z)) {
            z2 = true;
        }
        return z2 && ((BaseHeadsUpManager) this.mHeadsUpManager).mHasPinnedNotification;
    }

    public final void show(View view) {
        if (this.mAnimationsEnabled) {
            CrossFadeHelper.fadeIn(view, 110L, 100);
        } else {
            view.setVisibility(0);
        }
    }

    public final void updateHeadsUpAndPulsingRoundness(NotificationEntry notificationEntry) {
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        boolean z = expandableNotificationRow == this.mTrackedChild;
        boolean z2 = expandableNotificationRow.mIsPinned;
        SourceType$Companion$from$1 sourceType$Companion$from$1 = HEADS_UP;
        if (z2 || expandableNotificationRow.mHeadsupDisappearRunning || z) {
            float saturate = MathUtils.saturate(1.0f - this.mAppearFraction);
            expandableNotificationRow.requestRoundness(saturate, saturate, sourceType$Companion$from$1);
        } else {
            expandableNotificationRow.requestRoundnessReset(sourceType$Companion$from$1);
        }
        if (this.mNotificationRoundnessManager.mRoundForPulsingViews) {
            boolean showingPulsing = expandableNotificationRow.showingPulsing();
            SourceType$Companion$from$1 sourceType$Companion$from$12 = PULSING;
            if (showingPulsing) {
                expandableNotificationRow.requestRoundness(1.0f, 1.0f, sourceType$Companion$from$12);
            } else {
                expandableNotificationRow.requestRoundnessReset(sourceType$Companion$from$12);
            }
        }
    }

    public final void updateTopEntry() {
        BaseHeadsUpManager.HeadsUpEntry topHeadsUpEntry;
        NotificationEntry notificationEntry = (!shouldBeVisible$1() || (topHeadsUpEntry = ((BaseHeadsUpManager) this.mHeadsUpManager).getTopHeadsUpEntry()) == null) ? null : topHeadsUpEntry.mEntry;
        HeadsUpStatusBarView headsUpStatusBarView = (HeadsUpStatusBarView) this.mView;
        NotificationEntry notificationEntry2 = headsUpStatusBarView.mShowingEntry;
        headsUpStatusBarView.setEntry(notificationEntry);
        if (notificationEntry != notificationEntry2) {
            if (notificationEntry == null) {
                setShown(false);
            } else if (notificationEntry2 == null) {
                setShown(true);
            }
            this.mHeadsUpNotificationIconInteractor.repository.isolatedNotification.setValue(notificationEntry != null ? notificationEntry.mKey : null);
        }
    }
}
