package com.android.systemui.statusbar.notification.stack;

import android.animation.ObjectAnimator;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Handler;
import android.os.Trace;
import android.util.Log;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import com.android.app.animation.Interpolators;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.logging.UiEventLogger;
import com.android.keyguard.LegacyLockIconViewController$$ExternalSyntheticOutline0;
import com.android.systemui.Dumpable;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.bouncer.domain.interactor.PrimaryBouncerInteractor;
import com.android.systemui.classifier.FalsingCollector;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.media.controls.ui.controller.KeyguardMediaController;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.shade.ShadeController;
import com.android.systemui.statusbar.LockscreenShadeTransitionController;
import com.android.systemui.statusbar.NotificationLockscreenUserManager;
import com.android.systemui.statusbar.NotificationLockscreenUserManagerImpl;
import com.android.systemui.statusbar.NotificationRemoteInputManager;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.android.systemui.statusbar.SysuiStatusBarStateController;
import com.android.systemui.statusbar.notification.ColorUpdateLogger;
import com.android.systemui.statusbar.notification.DynamicPrivacyController;
import com.android.systemui.statusbar.notification.HeadsUpManagerPhone;
import com.android.systemui.statusbar.notification.NotificationWakeUpCoordinator;
import com.android.systemui.statusbar.notification.Roundable;
import com.android.systemui.statusbar.notification.VisibilityLocationProvider;
import com.android.systemui.statusbar.notification.collection.NotifCollection;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.PipelineDumpable;
import com.android.systemui.statusbar.notification.collection.PipelineDumper;
import com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener;
import com.android.systemui.statusbar.notification.collection.provider.NotificationDismissibilityProviderImpl;
import com.android.systemui.statusbar.notification.collection.provider.VisibilityLocationProviderDelegator;
import com.android.systemui.statusbar.notification.collection.render.GroupExpansionManagerImpl;
import com.android.systemui.statusbar.notification.collection.render.NotificationVisibilityProvider;
import com.android.systemui.statusbar.notification.collection.render.SectionHeaderNodeControllerImpl;
import com.android.systemui.statusbar.notification.domain.interactor.SeenNotificationsInteractor;
import com.android.systemui.statusbar.notification.footer.shared.FooterViewRefactor;
import com.android.systemui.statusbar.notification.init.NotificationsController;
import com.android.systemui.statusbar.notification.row.ActivatableNotificationView;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.row.NotificationGutsManager;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.notification.stack.ui.viewbinder.NotificationListViewBinder;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.phone.HeadsUpAppearanceController;
import com.android.systemui.statusbar.phone.KeyguardBypassController;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.ConfigurationController;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import com.android.systemui.statusbar.policy.OnHeadsUpChangedListener;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionController;
import com.android.systemui.statusbar.policy.SensitiveNotificationProtectionControllerImpl;
import com.android.systemui.statusbar.policy.SplitShadeStateControllerImpl;
import com.android.systemui.statusbar.policy.ZenModeController;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.util.settings.SecureSettings;
import com.android.wm.shell.R;
import dagger.internal.DelegateFactory;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationStackScrollLayoutController implements Dumpable {
    public static final AnonymousClass2 HIDE_ALPHA_PROPERTY = new AnonymousClass2(Float.class, "HideNotificationsAlpha");
    public final ActivityStarter mActivityStarter;
    public final boolean mAllowLongPress;
    public int mBarState;
    public final ColorUpdateLogger mColorUpdateLogger;
    public final ConfigurationController mConfigurationController;
    final ConfigurationController.ConfigurationListener mConfigurationListener;
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final AnonymousClass3 mDeviceProvisionedListener;
    public final NotificationDismissibilityProviderImpl mDismissibilityProvider;
    public final DynamicPrivacyController mDynamicPrivacyController;
    public final NotificationStackScrollLayoutController$$ExternalSyntheticLambda0 mDynamicPrivacyControllerListener;
    public final FalsingCollector mFalsingCollector;
    public final FalsingManager mFalsingManager;
    public final GroupExpansionManagerImpl mGroupExpansionManager;
    public HeadsUpAppearanceController mHeadsUpAppearanceController;
    public final HeadsUpManager mHeadsUpManager;
    public ObjectAnimator mHideAlphaAnimator;
    public Boolean mHistoryEnabled;
    public final InteractionJankMonitor mJankMonitor;
    public final KeyguardMediaController mKeyguardMediaController;
    public final AnonymousClass7 mLockscreenUserChangeListener;
    public final NotificationLockscreenUserManager mLockscreenUserManager;
    public ExpandableView mLongPressedView;
    public float mMaxAlphaForGlanceableHub;
    public float mMaxAlphaForKeyguard;
    public String mMaxAlphaForKeyguardSource;
    public float mMaxAlphaForUnhide;
    public float mMaxAlphaFromView;
    public final AnonymousClass9 mMenuEventListener;
    public final MetricsLogger mMetricsLogger;
    public final NotifCollection mNotifCollection;
    final NotificationSwipeHelper.NotificationCallback mNotificationCallback;
    public final NotificationGutsManager mNotificationGutsManager;
    public final NotificationRoundnessManager mNotificationRoundnessManager;
    public final NotificationStackSizeCalculator mNotificationStackSizeCalculator;
    public final NotificationTargetsHelper mNotificationTargetsHelper;
    public final NotificationsController mNotificationsController;
    final View.OnAttachStateChangeListener mOnAttachStateChangeListener;
    public final AnonymousClass10 mOnHeadsUpChangedListener;
    public final PowerInteractor mPowerInteractor;
    public final SecureSettings mSecureSettings;
    public final SensitiveNotificationProtectionController mSensitiveNotificationProtectionController;
    public final AnonymousClass4 mSensitiveStateChangedListener;
    public final ShadeController mShadeController;
    public final AnonymousClass6 mStateListener;
    public final SysuiStatusBarStateController mStatusBarStateController;
    public final NotificationSwipeHelper mSwipeHelper;
    public final TouchHandler mTouchHandler;
    public final UiEventLogger mUiEventLogger;
    public final NotificationStackScrollLayout mView;
    public final NotificationVisibilityProvider mVisibilityProvider;
    public final DelegateFactory mWindowRootView;
    public final AnonymousClass11 mZenModeControllerCallback;
    public final NotificationListContainerImpl mNotificationListContainer = new NotificationListContainerImpl();
    public final NotifStackControllerImpl mNotifStackController = new NotifStackControllerImpl();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$2, reason: invalid class name */
    public final class AnonymousClass2 extends Property {
        @Override // android.util.Property
        public final Object get(Object obj) {
            return Float.valueOf(((NotificationStackScrollLayoutController) obj).mMaxAlphaForUnhide);
        }

        @Override // android.util.Property
        public final void set(Object obj, Object obj2) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = (NotificationStackScrollLayoutController) obj;
            notificationStackScrollLayoutController.mMaxAlphaForUnhide = ((Float) obj2).floatValue();
            notificationStackScrollLayoutController.updateAlpha$1();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$9, reason: invalid class name */
    public final class AnonymousClass9 implements NotificationSwipeHelper.NotificationCallback, NotificationMenuRowPlugin.OnMenuEventListener {
        public /* synthetic */ AnonymousClass9() {
        }

        public boolean canChildBeDismissed(View view) {
            int i = NotificationStackScrollLayout.$r8$clinit;
            if (!(view instanceof ExpandableNotificationRow)) {
                return false;
            }
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (expandableNotificationRow.areGutsExposed() || !expandableNotificationRow.mEntry.hasFinishedInitialization()) {
                return false;
            }
            return expandableNotificationRow.canViewBeDismissed();
        }

        public ExpandableView getChildAtPosition(MotionEvent motionEvent) {
            ExpandableNotificationRow expandableNotificationRow;
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            ExpandableView childAtPosition = notificationStackScrollLayoutController.mView.getChildAtPosition(motionEvent.getX(), motionEvent.getY(), true, false);
            if ((childAtPosition instanceof NotificationShelf) && !NotificationSwipeHelper.isTouchInView((NotificationShelf) childAtPosition, motionEvent)) {
                return null;
            }
            if (!(childAtPosition instanceof ExpandableNotificationRow) || (expandableNotificationRow = ((ExpandableNotificationRow) childAtPosition).mNotificationParent) == null || !expandableNotificationRow.mChildrenExpanded) {
                return childAtPosition;
            }
            if (!expandableNotificationRow.areGutsExposed() && notificationStackScrollLayoutController.mSwipeHelper.mMenuExposedView != expandableNotificationRow) {
                if (expandableNotificationRow.getAttachedChildren().size() != 1) {
                    return childAtPosition;
                }
                if (notificationStackScrollLayoutController.mDismissibilityProvider.nonDismissableEntryKeys.contains(expandableNotificationRow.mEntry.mKey)) {
                    return childAtPosition;
                }
            }
            return expandableNotificationRow;
        }

        public void handleChildViewDismissed(View view) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
            notificationStackScrollLayout.updateFirstAndLastBackgroundViews();
            notificationStackScrollLayout.mController.mNotificationRoundnessManager.setViewsAffectedBySwipe(null, null, null);
            notificationStackScrollLayout.mShelf.updateAppearance();
            if (notificationStackScrollLayoutController.mView.mClearAllInProgress) {
                return;
            }
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                if (expandableNotificationRow.mIsHeadsUp) {
                    ((HeadsUpManagerPhone) notificationStackScrollLayoutController.mHeadsUpManager).mSwipedOutKeys.add(expandableNotificationRow.mEntry.mSbn.getKey());
                }
                expandableNotificationRow.performDismiss(false);
            }
            notificationStackScrollLayoutController.mView.mSwipedOutViews.add(view);
            notificationStackScrollLayoutController.mFalsingCollector.getClass();
        }

        public void onBeginDrag(View view) {
            Roundable roundable;
            ExpandableView expandableView;
            final NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
            notificationStackScrollLayout.getClass();
            if (view instanceof ExpandableNotificationRow) {
                notificationStackScrollLayout.mSectionsManager.updateFirstAndLastViewsForAllSections(notificationStackScrollLayout.mSections, notificationStackScrollLayout.getChildrenWithBackground());
                NotificationTargetsHelper notificationTargetsHelper = notificationStackScrollLayout.mController.mNotificationTargetsHelper;
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                NotificationSectionsManager notificationSectionsManager = notificationStackScrollLayout.mSectionsManager;
                notificationTargetsHelper.getClass();
                ExpandableNotificationRow expandableNotificationRow2 = expandableNotificationRow.mNotificationParent;
                ExpandableView expandableView2 = null;
                NotificationChildrenContainer notificationChildrenContainer = expandableNotificationRow2 != null ? expandableNotificationRow2.mChildrenContainer : null;
                List list = SequencesKt.toList(SequencesKt.filter(SequencesKt.filter(new Sequence() { // from class: androidx.core.view.ViewGroupKt$children$1
                    @Override // kotlin.sequences.Sequence
                    public final Iterator iterator() {
                        return new ViewGroupKt$iterator$1(notificationStackScrollLayout);
                    }
                }, new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationTargetsHelper$findRoundableTargets$$inlined$filterIsInstance$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(obj instanceof ExpandableView);
                    }
                }), new Function1() { // from class: com.android.systemui.statusbar.notification.stack.NotificationTargetsHelper$findRoundableTargets$visibleStackChildren$1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(((ExpandableView) obj).getVisibility() == 0);
                    }
                }));
                if (expandableNotificationRow2 == null || notificationChildrenContainer == null) {
                    int indexOf = list.indexOf(expandableNotificationRow);
                    ExpandableView expandableView3 = (ExpandableView) CollectionsKt.getOrNull(indexOf - 1, list);
                    roundable = (expandableView3 == null || notificationSectionsManager.beginsSection(expandableNotificationRow, expandableView3)) ? null : expandableView3;
                    ExpandableView expandableView4 = (ExpandableView) CollectionsKt.getOrNull(indexOf + 1, list);
                    if (expandableView4 != null && !notificationSectionsManager.beginsSection(expandableView4, expandableNotificationRow)) {
                        expandableView2 = expandableView4;
                    }
                    expandableView = expandableView2;
                } else {
                    List list2 = notificationChildrenContainer.mAttachedChildren;
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : list2) {
                        ExpandableNotificationRow expandableNotificationRow3 = (ExpandableNotificationRow) obj;
                        Intrinsics.checkNotNull(expandableNotificationRow3);
                        if (expandableNotificationRow3.getVisibility() == 0) {
                            arrayList.add(obj);
                        }
                    }
                    int indexOf2 = arrayList.indexOf(expandableNotificationRow);
                    roundable = (ExpandableNotificationRow) CollectionsKt.getOrNull(indexOf2 - 1, arrayList);
                    if (roundable == null) {
                        roundable = notificationChildrenContainer.mGroupHeaderWrapper;
                    }
                    expandableView = (ExpandableNotificationRow) CollectionsKt.getOrNull(indexOf2 + 1, arrayList);
                    if (expandableView == null) {
                        expandableView = (ExpandableView) CollectionsKt.getOrNull(list.indexOf(expandableNotificationRow2) + 1, list);
                    }
                }
                notificationStackScrollLayout.mController.mNotificationRoundnessManager.setViewsAffectedBySwipe(roundable, expandableNotificationRow, expandableView);
                notificationStackScrollLayout.updateFirstAndLastBackgroundViews();
                notificationStackScrollLayout.requestDisallowInterceptTouchEvent(true);
                notificationStackScrollLayout.updateContinuousShadowDrawing();
                notificationStackScrollLayout.requestChildrenUpdate();
            }
        }

        @Override // com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin.OnMenuEventListener
        public void onMenuClicked(View view, int i, int i2, NotificationMenuRowPlugin.MenuItem menuItem) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            if (notificationStackScrollLayoutController.mAllowLongPress) {
                if (view instanceof ExpandableNotificationRow) {
                    notificationStackScrollLayoutController.mMetricsLogger.write(((ExpandableNotificationRow) view).mEntry.mSbn.getLogMaker().setCategory(333).setType(4));
                }
                notificationStackScrollLayoutController.mNotificationGutsManager.openGuts(view, i, i2, menuItem);
            }
        }

        @Override // com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin.OnMenuEventListener
        public void onMenuReset(View view) {
            NotificationSwipeHelper notificationSwipeHelper = NotificationStackScrollLayoutController.this.mSwipeHelper;
            View view2 = notificationSwipeHelper.mTranslatingParentView;
            if (view2 == null || view != view2) {
                return;
            }
            notificationSwipeHelper.mMenuExposedView = null;
            notificationSwipeHelper.setTranslatingParentView(null);
        }

        @Override // com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin.OnMenuEventListener
        public void onMenuShown(View view) {
            if (view instanceof ExpandableNotificationRow) {
                ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                notificationStackScrollLayoutController.mMetricsLogger.write(expandableNotificationRow.mEntry.mSbn.getLogMaker().setCategory(332).setType(4));
                NotificationSwipeHelper notificationSwipeHelper = notificationStackScrollLayoutController.mSwipeHelper;
                notificationSwipeHelper.mMenuExposedView = notificationSwipeHelper.mTranslatingParentView;
                NotificationSwipeHelper.NotificationCallback notificationCallback = notificationSwipeHelper.mCallback;
                notificationCallback.getClass();
                Handler handler = notificationSwipeHelper.getHandler();
                if (NotificationStackScrollLayoutController.this.mView.onKeyguard()) {
                    handler.removeCallbacks(notificationSwipeHelper.getFalsingCheck());
                    handler.postDelayed(notificationSwipeHelper.getFalsingCheck(), 4000L);
                }
                NotificationGutsManager notificationGutsManager = notificationStackScrollLayoutController.mNotificationGutsManager;
                notificationGutsManager.closeAndSaveGuts(true, false, false, false);
                NotificationMenuRowPlugin notificationMenuRowPlugin = expandableNotificationRow.mMenuRow;
                if (notificationMenuRowPlugin.shouldShowGutsOnSnapOpen()) {
                    NotificationMenuRowPlugin.MenuItem menuItemToExposeOnSnap = notificationMenuRowPlugin.menuItemToExposeOnSnap();
                    if (menuItemToExposeOnSnap != null) {
                        Point revealAnimationOrigin = notificationMenuRowPlugin.getRevealAnimationOrigin();
                        notificationGutsManager.openGuts(view, revealAnimationOrigin.x, revealAnimationOrigin.y, menuItemToExposeOnSnap);
                    } else {
                        Log.e("StackScrollerController", "Provider has shouldShowGutsOnSnapOpen, but provided no menu item in menuItemtoExposeOnSnap. Skipping.");
                    }
                    notificationStackScrollLayoutController.mSwipeHelper.resetExposedMenuView(false, true);
                }
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotifStackControllerImpl {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NotificationListContainerImpl implements PipelineDumpable, VisibilityLocationProvider {
        public NotificationListContainerImpl() {
        }

        @Override // com.android.systemui.statusbar.notification.collection.PipelineDumpable
        public final void dumpPipeline(PipelineDumper pipelineDumper) {
            pipelineDumper.dump(NotificationStackScrollLayoutController.this, "NotificationStackScrollLayoutController.this");
        }

        @Override // com.android.systemui.statusbar.notification.VisibilityLocationProvider
        public final boolean isInVisibleLocation(NotificationEntry notificationEntry) {
            return NotificationStackScrollLayoutController.isInVisibleLocation$1(notificationEntry);
        }

        public final void onHeightChanged(ExpandableView expandableView, boolean z) {
            NotificationStackScrollLayoutController.this.mView.onChildHeightChanged(expandableView, z);
        }

        public final void setExpandingNotification(ExpandableNotificationRow expandableNotificationRow) {
            NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
            ExpandableNotificationRow expandableNotificationRow2 = notificationStackScrollLayout.mExpandingNotificationRow;
            if (expandableNotificationRow2 != null && expandableNotificationRow == null) {
                expandableNotificationRow2.mExpandingClipPath = null;
                expandableNotificationRow2.invalidate();
                ExpandableNotificationRow expandableNotificationRow3 = notificationStackScrollLayout.mExpandingNotificationRow.mNotificationParent;
                if (expandableNotificationRow3 != null) {
                    expandableNotificationRow3.mExpandingClipPath = null;
                    expandableNotificationRow3.invalidate();
                }
            }
            notificationStackScrollLayout.mExpandingNotificationRow = expandableNotificationRow;
            notificationStackScrollLayout.updateLaunchedNotificationClipPath();
            notificationStackScrollLayout.requestChildrenUpdate();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    enum NotificationPanelEvent implements UiEventLogger.UiEventEnum {
        INVALID(0),
        DISMISS_ALL_NOTIFICATIONS_PANEL(312),
        DISMISS_SILENT_NOTIFICATIONS_PANEL(314);

        private final int mId;

        NotificationPanelEvent(int i) {
            this.mId = i;
        }

        public final int getId() {
            return this.mId;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TouchHandler implements Gefingerpoken {
        public TouchHandler() {
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x0075  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x0099  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00a2 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:47:0x00b7  */
        /* JADX WARN: Removed duplicated region for block: B:50:0x00bf A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:55:0x00d1 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:64:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:65:0x009b  */
        @Override // com.android.systemui.Gefingerpoken
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean onInterceptTouchEvent(android.view.MotionEvent r10) {
            /*
                Method dump skipped, instructions count: 217
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.TouchHandler.onInterceptTouchEvent(android.view.MotionEvent):boolean");
        }

        /* JADX WARN: Removed duplicated region for block: B:43:0x0090  */
        /* JADX WARN: Removed duplicated region for block: B:52:0x00aa  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x00cb  */
        /* JADX WARN: Removed duplicated region for block: B:69:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:71:0x0109 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:80:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:81:0x00e8  */
        @Override // com.android.systemui.Gefingerpoken
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final boolean onTouchEvent(android.view.MotionEvent r13) {
            /*
                Method dump skipped, instructions count: 273
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.TouchHandler.onTouchEvent(android.view.MotionEvent):boolean");
        }
    }

    /* JADX WARN: Type inference failed for: r2v22, types: [com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda11] */
    public NotificationStackScrollLayoutController(final NotificationStackScrollLayout notificationStackScrollLayout, boolean z, NotificationGutsManager notificationGutsManager, NotificationsController notificationsController, NotificationVisibilityProvider notificationVisibilityProvider, NotificationWakeUpCoordinator notificationWakeUpCoordinator, HeadsUpManager headsUpManager, NotificationRoundnessManager notificationRoundnessManager, TunerService tunerService, DeviceProvisionedController deviceProvisionedController, DynamicPrivacyController dynamicPrivacyController, ConfigurationController configurationController, SysuiStatusBarStateController sysuiStatusBarStateController, KeyguardMediaController keyguardMediaController, KeyguardBypassController keyguardBypassController, PowerInteractor powerInteractor, PrimaryBouncerInteractor primaryBouncerInteractor, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, ZenModeController zenModeController, NotificationLockscreenUserManager notificationLockscreenUserManager, MetricsLogger metricsLogger, ColorUpdateLogger colorUpdateLogger, DumpManager dumpManager, FalsingCollector falsingCollector, FalsingManager falsingManager, NotificationSwipeHelper.Builder builder, GroupExpansionManagerImpl groupExpansionManagerImpl, SectionHeaderNodeControllerImpl sectionHeaderNodeControllerImpl, NotifPipeline notifPipeline, NotifCollection notifCollection, LockscreenShadeTransitionController lockscreenShadeTransitionController, UiEventLogger uiEventLogger, NotificationRemoteInputManager notificationRemoteInputManager, VisibilityLocationProviderDelegator visibilityLocationProviderDelegator, SeenNotificationsInteractor seenNotificationsInteractor, NotificationListViewBinder notificationListViewBinder, ShadeController shadeController, DelegateFactory delegateFactory, InteractionJankMonitor interactionJankMonitor, StackStateLogger stackStateLogger, NotificationStackScrollLogger notificationStackScrollLogger, NotificationStackSizeCalculator notificationStackSizeCalculator, NotificationTargetsHelper notificationTargetsHelper, SecureSettings secureSettings, NotificationDismissibilityProviderImpl notificationDismissibilityProviderImpl, ActivityStarter activityStarter, SplitShadeStateControllerImpl splitShadeStateControllerImpl, SensitiveNotificationProtectionController sensitiveNotificationProtectionController) {
        View.OnAttachStateChangeListener onAttachStateChangeListener;
        View.OnAttachStateChangeListener onAttachStateChangeListener2 = new View.OnAttachStateChangeListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.1
            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewAttachedToWindow(View view) {
                NotificationStackScrollLayoutController.this.mColorUpdateLogger.getClass();
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                ((ConfigurationControllerImpl) notificationStackScrollLayoutController.mConfigurationController).addCallback(notificationStackScrollLayoutController.mConfigurationListener);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = NotificationStackScrollLayoutController.this;
                int i = ((StatusBarStateControllerImpl) notificationStackScrollLayoutController2.mStatusBarStateController).mState;
                if (i != notificationStackScrollLayoutController2.mBarState) {
                    notificationStackScrollLayoutController2.mStateListener.onStateChanged(i);
                    NotificationStackScrollLayoutController.this.mStateListener.onStatePostChange();
                }
                NotificationStackScrollLayoutController notificationStackScrollLayoutController3 = NotificationStackScrollLayoutController.this;
                SysuiStatusBarStateController sysuiStatusBarStateController2 = notificationStackScrollLayoutController3.mStatusBarStateController;
                AnonymousClass6 anonymousClass6 = notificationStackScrollLayoutController3.mStateListener;
                StatusBarStateControllerImpl statusBarStateControllerImpl = (StatusBarStateControllerImpl) sysuiStatusBarStateController2;
                synchronized (statusBarStateControllerImpl.mListeners) {
                    statusBarStateControllerImpl.addListenerInternalLocked(anonymousClass6, 2);
                }
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public final void onViewDetachedFromWindow(View view) {
                NotificationStackScrollLayoutController.this.mColorUpdateLogger.getClass();
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                ((ConfigurationControllerImpl) notificationStackScrollLayoutController.mConfigurationController).removeCallback(notificationStackScrollLayoutController.mConfigurationListener);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = NotificationStackScrollLayoutController.this;
                ((StatusBarStateControllerImpl) notificationStackScrollLayoutController2.mStatusBarStateController).removeCallback((StatusBarStateController.StateListener) notificationStackScrollLayoutController2.mStateListener);
            }
        };
        this.mOnAttachStateChangeListener = onAttachStateChangeListener2;
        this.mHideAlphaAnimator = null;
        new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.3
            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onDeviceProvisionedChanged() {
                updateCurrentUserIsSetup();
                throw null;
            }

            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSetupChanged() {
                updateCurrentUserIsSetup();
                throw null;
            }

            @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
            public final void onUserSwitched$1() {
                updateCurrentUserIsSetup();
                throw null;
            }

            public final void updateCurrentUserIsSetup() {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController.mView;
                ((DeviceProvisionedControllerImpl) notificationStackScrollLayoutController.mDeviceProvisionedController).isCurrentUserSetup();
                notificationStackScrollLayout2.getClass();
                FooterViewRefactor.assertInLegacyMode();
                throw null;
            }
        };
        Runnable runnable = new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.4
            @Override // java.lang.Runnable
            public final void run() {
                NotificationStackScrollLayoutController.this.updateSensitivenessWithAnimation(false);
            }
        };
        DynamicPrivacyController.Listener listener = new DynamicPrivacyController.Listener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda0
            @Override // com.android.systemui.statusbar.notification.DynamicPrivacyController.Listener
            public final void onDynamicPrivacyChanged() {
                NotificationStackScrollLayoutController.this.getClass();
            }
        };
        this.mConfigurationListener = new ConfigurationController.ConfigurationListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.5
            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onConfigChanged(Configuration configuration) {
                NotificationStackScrollLayoutController.this.mNotificationStackSizeCalculator.updateResources();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onDensityOrFontScaleChanged() {
                NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayoutController.this.mView;
                notificationStackScrollLayout2.inflateEmptyShadeView();
                notificationStackScrollLayout2.mSectionsManager.reinflateViews();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onThemeChanged() {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                ((ConfigurationControllerImpl) notificationStackScrollLayoutController.mConfigurationController).getClass();
                notificationStackScrollLayoutController.mColorUpdateLogger.getClass();
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController.mView;
                int dimensionPixelSize = notificationStackScrollLayout2.getResources().getDimensionPixelSize(R.dimen.notification_corner_radius);
                if (notificationStackScrollLayout2.mCornerRadius != dimensionPixelSize) {
                    notificationStackScrollLayout2.mCornerRadius = dimensionPixelSize;
                    notificationStackScrollLayout2.invalidate();
                }
                NotificationStackScrollLayout notificationStackScrollLayout3 = notificationStackScrollLayoutController.mView;
                for (int i = 0; i < notificationStackScrollLayout3.getChildCount(); i++) {
                    View childAt = notificationStackScrollLayout3.getChildAt(i);
                    if (childAt instanceof ActivatableNotificationView) {
                        ((ActivatableNotificationView) childAt).updateBackgroundColors();
                    }
                }
                notificationStackScrollLayoutController.mView.updateDecorViews();
                NotificationStackScrollLayout notificationStackScrollLayout4 = notificationStackScrollLayoutController.mView;
                notificationStackScrollLayout4.inflateEmptyShadeView();
                notificationStackScrollLayout4.mSectionsManager.reinflateViews();
            }

            @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
            public final void onUiModeChanged() {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                ((ConfigurationControllerImpl) notificationStackScrollLayoutController.mConfigurationController).getClass();
                notificationStackScrollLayoutController.mColorUpdateLogger.getClass();
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController.mView;
                for (int i = 0; i < notificationStackScrollLayout2.getChildCount(); i++) {
                    View childAt = notificationStackScrollLayout2.getChildAt(i);
                    if (childAt instanceof ActivatableNotificationView) {
                        ((ActivatableNotificationView) childAt).updateBackgroundColors();
                    }
                }
                notificationStackScrollLayoutController.mView.updateDecorViews();
            }
        };
        this.mMaxAlphaForKeyguard = 1.0f;
        this.mMaxAlphaForKeyguardSource = "constructor";
        this.mMaxAlphaForUnhide = 1.0f;
        this.mMaxAlphaFromView = 1.0f;
        this.mMaxAlphaForGlanceableHub = 1.0f;
        this.mStateListener = new AnonymousClass6();
        NotificationLockscreenUserManager.UserChangedListener userChangedListener = new NotificationLockscreenUserManager.UserChangedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.7
            @Override // com.android.systemui.statusbar.NotificationLockscreenUserManager.UserChangedListener
            public final void onUserChanged(int i) {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                notificationStackScrollLayoutController.updateSensitivenessWithAnimation(false);
                notificationStackScrollLayoutController.mHistoryEnabled = null;
            }
        };
        AnonymousClass9 anonymousClass9 = new AnonymousClass9();
        AnonymousClass9 anonymousClass92 = new AnonymousClass9();
        this.mNotificationCallback = anonymousClass92;
        OnHeadsUpChangedListener onHeadsUpChangedListener = new OnHeadsUpChangedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.10
            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpPinnedModeChanged(boolean z2) {
                NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayoutController.this.mView;
                notificationStackScrollLayout2.mInHeadsUpPinnedMode = z2;
                notificationStackScrollLayout2.updateClipping$1();
            }

            @Override // com.android.systemui.statusbar.policy.OnHeadsUpChangedListener
            public final void onHeadsUpStateChanged(NotificationEntry notificationEntry, boolean z2) {
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                BaseHeadsUpManager.HeadsUpEntry topHeadsUpEntry = ((BaseHeadsUpManager) notificationStackScrollLayoutController.mHeadsUpManager).getTopHeadsUpEntry();
                NotificationEntry notificationEntry2 = topHeadsUpEntry != null ? topHeadsUpEntry.mEntry : null;
                NotificationStackScrollLayout notificationStackScrollLayout2 = notificationStackScrollLayoutController.mView;
                notificationStackScrollLayout2.mTopHeadsUpRow = notificationEntry2 != null ? notificationEntry2.row : null;
                Iterator it = notificationStackScrollLayout2.mHeadsUpHeightChangedListeners.listeners.iterator();
                while (it.hasNext()) {
                    ((Runnable) it.next()).run();
                }
                NotificationStackScrollLayout notificationStackScrollLayout3 = notificationStackScrollLayoutController.mView;
                notificationStackScrollLayout3.getClass();
                notificationStackScrollLayout3.generateHeadsUpAnimation(notificationEntry.row, z2);
            }
        };
        new ZenModeController.Callback(this) { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.11
            public final /* synthetic */ NotificationStackScrollLayoutController this$0;

            @Override // com.android.systemui.statusbar.policy.ZenModeController.Callback
            public final void onZenChanged(int i) {
                FooterViewRefactor.assertInLegacyMode();
                throw null;
            }
        };
        this.mView = notificationStackScrollLayout;
        this.mAllowLongPress = z;
        this.mNotificationGutsManager = notificationGutsManager;
        this.mNotificationsController = notificationsController;
        this.mVisibilityProvider = notificationVisibilityProvider;
        this.mHeadsUpManager = headsUpManager;
        this.mNotificationRoundnessManager = notificationRoundnessManager;
        this.mDeviceProvisionedController = deviceProvisionedController;
        this.mDynamicPrivacyController = dynamicPrivacyController;
        this.mConfigurationController = configurationController;
        this.mStatusBarStateController = sysuiStatusBarStateController;
        this.mKeyguardMediaController = keyguardMediaController;
        this.mPowerInteractor = powerInteractor;
        this.mLockscreenUserManager = notificationLockscreenUserManager;
        this.mMetricsLogger = metricsLogger;
        this.mColorUpdateLogger = colorUpdateLogger;
        this.mFalsingCollector = falsingCollector;
        this.mFalsingManager = falsingManager;
        this.mJankMonitor = interactionJankMonitor;
        this.mNotificationStackSizeCalculator = notificationStackSizeCalculator;
        this.mGroupExpansionManager = groupExpansionManagerImpl;
        this.mNotifCollection = notifCollection;
        this.mUiEventLogger = uiEventLogger;
        this.mShadeController = shadeController;
        this.mNotificationTargetsHelper = notificationTargetsHelper;
        this.mSecureSettings = secureSettings;
        this.mDismissibilityProvider = notificationDismissibilityProviderImpl;
        this.mActivityStarter = activityStarter;
        this.mSensitiveNotificationProtectionController = sensitiveNotificationProtectionController;
        notificationStackScrollLayout.mSplitShadeStateController = splitShadeStateControllerImpl;
        notificationStackScrollLayout.updateSplitNotificationShade();
        dumpManager.registerDumpable(this);
        notificationStackSizeCalculator.updateResources();
        notificationStackScrollLayout.mStateAnimator.mLogger = stackStateLogger;
        notificationStackScrollLayout.mController = this;
        notificationRoundnessManager.mAnimatedChildren = notificationStackScrollLayout.mChildrenToAddAnimated;
        notificationStackScrollLayout.mLogger = notificationStackScrollLogger;
        TouchHandler touchHandler = new TouchHandler();
        this.mTouchHandler = touchHandler;
        notificationStackScrollLayout.mTouchHandler = touchHandler;
        Objects.requireNonNull(notificationsController);
        notificationStackScrollLayout.mResetUserExpandedStatesRunnable = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda7(2, notificationsController);
        notificationStackScrollLayout.mActivityStarter = activityStarter;
        notificationStackScrollLayout.mClearAllAnimationListener = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda5(this);
        notificationStackScrollLayout.mClearAllListener = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda5(this);
        notificationStackScrollLayout.mClearAllFinishedWhilePanelExpandedRunnable = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda7(0, this);
        dumpManager.registerDumpable(notificationStackScrollLayout);
        final int i = 0;
        keyguardBypassController.registerOnBypassStateChangedListener(new KeyguardBypassController.OnBypassStateChangedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda8
            @Override // com.android.systemui.statusbar.phone.KeyguardBypassController.OnBypassStateChangedListener
            public final void onBypassStateChanged(boolean z2) {
                switch (i) {
                    case 0:
                        ((NotificationStackScrollLayoutController) this).mNotificationRoundnessManager.mRoundForPulsingViews = !z2;
                        break;
                    default:
                        ((NotificationStackScrollLayout) this).mKeyguardBypassEnabled = z2;
                        break;
                }
            }
        });
        notificationRoundnessManager.mRoundForPulsingViews = !keyguardBypassController.getBypassEnabled();
        builder.mNotificationCallback = anonymousClass92;
        builder.mOnMenuEventListener = anonymousClass9;
        NotificationSwipeHelper notificationSwipeHelper = new NotificationSwipeHelper(builder.mResources, builder.mViewConfiguration, builder.mFalsingManager, builder.mFeatureFlags, anonymousClass92, anonymousClass9, builder.mNotificationRoundnessManager);
        builder.mDumpManager.registerDumpable(notificationSwipeHelper);
        this.mSwipeHelper = notificationSwipeHelper;
        notifPipeline.addCollectionListener(new NotifCollectionListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController.13
            @Override // com.android.systemui.statusbar.notification.collection.notifcollection.NotifCollectionListener
            public final void onEntryUpdated(NotificationEntry notificationEntry) {
                NotificationStackScrollLayout notificationStackScrollLayout2 = NotificationStackScrollLayoutController.this.mView;
                notificationStackScrollLayout2.getClass();
                if (!notificationEntry.rowExists() || notificationEntry.mSbn.isClearable()) {
                    return;
                }
                ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
                boolean z2 = notificationStackScrollLayout2.mIsExpanded || NotificationStackScrollLayout.isPinnedHeadsUp(expandableNotificationRow);
                NotificationMenuRowPlugin notificationMenuRowPlugin = expandableNotificationRow.mMenuRow;
                if (notificationMenuRowPlugin != null) {
                    notificationStackScrollLayout2.mSwipeHelper.snapChildIfNeeded(expandableNotificationRow, notificationMenuRowPlugin.isMenuVisible() ? expandableNotificationRow.getTranslation() : 0.0f, z2);
                }
            }
        });
        notificationStackScrollLayout.initView(notificationStackScrollLayout.getContext(), this.mSwipeHelper, notificationStackSizeCalculator);
        notificationStackScrollLayout.mKeyguardBypassEnabled = keyguardBypassController.getBypassEnabled();
        final int i2 = 1;
        keyguardBypassController.registerOnBypassStateChangedListener(new KeyguardBypassController.OnBypassStateChangedListener() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda8
            @Override // com.android.systemui.statusbar.phone.KeyguardBypassController.OnBypassStateChangedListener
            public final void onBypassStateChanged(boolean z2) {
                switch (i2) {
                    case 0:
                        ((NotificationStackScrollLayoutController) notificationStackScrollLayout).mNotificationRoundnessManager.mRoundForPulsingViews = !z2;
                        break;
                    default:
                        ((NotificationStackScrollLayout) notificationStackScrollLayout).mKeyguardBypassEnabled = z2;
                        break;
                }
            }
        });
        ((BaseHeadsUpManager) headsUpManager).addListener(onHeadsUpChangedListener);
        ((HeadsUpManagerPhone) headsUpManager).mAnimationStateHandler = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda5(notificationStackScrollLayout);
        dynamicPrivacyController.mListeners.add(listener);
        lockscreenShadeTransitionController.nsslController = this;
        lockscreenShadeTransitionController.touchHelper.expandCallback = notificationStackScrollLayout.mExpandHelperCallback;
        ((NotificationLockscreenUserManagerImpl) notificationLockscreenUserManager).mListeners.add(userChangedListener);
        visibilityLocationProviderDelegator.delegate = new VisibilityLocationProvider(this) { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda11
            public final /* synthetic */ NotificationStackScrollLayoutController f$0;

            @Override // com.android.systemui.statusbar.notification.VisibilityLocationProvider
            public final boolean isInVisibleLocation(NotificationEntry notificationEntry) {
                return NotificationStackScrollLayoutController.isInVisibleLocation$1(notificationEntry);
            }
        };
        tunerService.addTunable(new TunerService.Tunable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda2
            @Override // com.android.systemui.tuner.TunerService.Tunable
            public final void onTuningChanged(String str, String str2) {
                char c;
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
                notificationStackScrollLayoutController.getClass();
                int hashCode = str.hashCode();
                if (hashCode != -220265567) {
                    if (hashCode == 1304816450 && str.equals("notification_history_enabled")) {
                        c = 0;
                    }
                    c = 65535;
                } else {
                    if (str.equals("high_priority")) {
                        c = 1;
                    }
                    c = 65535;
                }
                if (c == 0) {
                    notificationStackScrollLayoutController.mHistoryEnabled = null;
                } else {
                    if (c != 1) {
                        return;
                    }
                    notificationStackScrollLayoutController.mView.mHighPriorityBeforeSpeedBump = "1".equals(str2);
                }
            }
        }, "high_priority", "notification_history_enabled");
        keyguardMediaController.visibilityChangedListener = new NotificationStackScrollLayoutController$$ExternalSyntheticLambda3(this);
        ((SensitiveNotificationProtectionControllerImpl) sensitiveNotificationProtectionController).mListeners.addIfAbsent(runnable);
        if (notificationStackScrollLayout.isAttachedToWindow()) {
            onAttachStateChangeListener = onAttachStateChangeListener2;
            onAttachStateChangeListener.onViewAttachedToWindow(notificationStackScrollLayout);
        } else {
            onAttachStateChangeListener = onAttachStateChangeListener2;
        }
        notificationStackScrollLayout.addOnAttachStateChangeListener(onAttachStateChangeListener);
        groupExpansionManagerImpl.mOnGroupChangeListeners.add(new NotificationStackScrollLayoutController$$ExternalSyntheticLambda4(this));
        notificationListViewBinder.bindWhileAttached(notificationStackScrollLayout, this);
    }

    public static boolean isInVisibleLocation$1(NotificationEntry notificationEntry) {
        ExpandableNotificationRow expandableNotificationRow = notificationEntry.row;
        return (expandableNotificationRow == null || (expandableNotificationRow.mViewState.location & 5) == 0 || expandableNotificationRow.getVisibility() != 0) ? false : true;
    }

    public final void checkSnoozeLeavebehind() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        if (notificationStackScrollLayout.mCheckForLeavebehind) {
            this.mNotificationGutsManager.closeAndSaveGuts(true, false, false, false);
            notificationStackScrollLayout.mCheckForLeavebehind = false;
        }
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        StringBuilder m = LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(LegacyLockIconViewController$$ExternalSyntheticOutline0.m(new StringBuilder("mMaxAlphaFromView="), this.mMaxAlphaFromView, printWriter, "mMaxAlphaForUnhide="), this.mMaxAlphaForUnhide, printWriter, "mMaxAlphaForGlanceableHub="), this.mMaxAlphaForGlanceableHub, printWriter, "mMaxAlphaForKeyguard="), this.mMaxAlphaForKeyguard, printWriter, "mMaxAlphaForKeyguardSource=");
        m.append(this.mMaxAlphaForKeyguardSource);
        printWriter.println(m.toString());
    }

    public final int getNotGoneChildCount() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        int childCount = notificationStackScrollLayout.getChildCount();
        int i = 0;
        for (int i2 = 0; i2 < childCount; i2++) {
            ExpandableView expandableView = (ExpandableView) notificationStackScrollLayout.getChildAt(i2);
            if (expandableView.getVisibility() != 8 && !expandableView.mWillBeGone && expandableView != notificationStackScrollLayout.mShelf) {
                i++;
            }
        }
        return i;
    }

    public TouchHandler getTouchHandler() {
        return this.mTouchHandler;
    }

    public void onKeyguardTransitionChanged(TransitionStep transitionStep) {
        FooterViewRefactor.assertInLegacyMode();
        throw null;
    }

    public final void setOverExpansion(float f) {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        notificationStackScrollLayout.mAmbientState.mOverExpansion = f;
        int i = notificationStackScrollLayout.mShouldUseSplitNotificationShade ? (int) f : 0;
        if (notificationStackScrollLayout.mRoundedRectClippingYTranslation != i) {
            notificationStackScrollLayout.mRoundedRectClippingYTranslation = i;
            notificationStackScrollLayout.updateRoundedClipPath();
        }
        notificationStackScrollLayout.updateStackPosition(false);
        notificationStackScrollLayout.requestChildrenUpdate();
    }

    public final void setOverScrollAmount(int i) {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        notificationStackScrollLayout.mExtraTopInsetForFullShadeTransition = i;
        notificationStackScrollLayout.updateStackPosition(false);
        notificationStackScrollLayout.requestChildrenUpdate();
    }

    public final void setPanelFlinging(boolean z) {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        AmbientState ambientState = notificationStackScrollLayout.mAmbientState;
        if (ambientState.isOnKeyguard() && !z && ambientState.mIsFlinging) {
            ambientState.mIsFlingRequiredAfterLockScreenSwipeUp = false;
        }
        ambientState.mIsFlinging = z;
        if (z) {
            return;
        }
        notificationStackScrollLayout.updateStackPosition(false);
    }

    public final void updateAlpha$1() {
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        if (notificationStackScrollLayout != null) {
            notificationStackScrollLayout.setAlpha(Math.min(Math.min(this.mMaxAlphaFromView, this.mMaxAlphaForKeyguard), Math.min(this.mMaxAlphaForUnhide, this.mMaxAlphaForGlanceableHub)));
        }
    }

    public final void updateNotificationsContainerVisibility(boolean z, boolean z2) {
        ObjectAnimator objectAnimator = this.mHideAlphaAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        float f = z ? 1.0f : 0.0f;
        AnonymousClass2 anonymousClass2 = HIDE_ALPHA_PROPERTY;
        if (!z2) {
            anonymousClass2.set(this, Float.valueOf(f));
            return;
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, anonymousClass2, f);
        ofFloat.setInterpolator(Interpolators.STANDARD);
        ofFloat.setDuration(360L);
        this.mHideAlphaAnimator = ofFloat;
        ofFloat.start();
    }

    public final void updateSensitivenessWithAnimation(boolean z) {
        Trace.beginSection("NSSLC.updateSensitivenessWithAnimation");
        boolean isAnyProfilePublicMode = ((NotificationLockscreenUserManagerImpl) this.mLockscreenUserManager).isAnyProfilePublicMode();
        boolean isSensitiveStateActive = ((SensitiveNotificationProtectionControllerImpl) this.mSensitiveNotificationProtectionController).isSensitiveStateActive();
        boolean z2 = isAnyProfilePublicMode || isSensitiveStateActive;
        boolean z3 = z && !isSensitiveStateActive;
        NotificationStackScrollLayout notificationStackScrollLayout = this.mView;
        if (z2 != notificationStackScrollLayout.mAmbientState.mHideSensitive) {
            int childCount = notificationStackScrollLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                ((ExpandableView) notificationStackScrollLayout.getChildAt(i)).setHideSensitiveForIntrinsicHeight(z2);
            }
            notificationStackScrollLayout.mAmbientState.mHideSensitive = z2;
            if (z3 && notificationStackScrollLayout.mAnimationsEnabled) {
                notificationStackScrollLayout.mHideSensitiveNeedsAnimation = true;
                notificationStackScrollLayout.mNeedsAnimation = true;
            }
            notificationStackScrollLayout.updateContentHeight();
            notificationStackScrollLayout.requestChildrenUpdate();
        }
        Trace.endSection();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$6, reason: invalid class name */
    public final class AnonymousClass6 implements StatusBarStateController.StateListener {
        public AnonymousClass6() {
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStateChanged(int i) {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            notificationStackScrollLayoutController.mBarState = i;
            notificationStackScrollLayoutController.mView.setStatusBarState(i);
            if (i == 1) {
                GroupExpansionManagerImpl groupExpansionManagerImpl = notificationStackScrollLayoutController.mGroupExpansionManager;
                groupExpansionManagerImpl.getClass();
                Iterator it = new ArrayList(groupExpansionManagerImpl.mExpandedGroups).iterator();
                while (it.hasNext()) {
                    groupExpansionManagerImpl.setGroupExpanded((NotificationEntry) it.next(), false);
                }
            }
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStatePostChange() {
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
            SysuiStatusBarStateController sysuiStatusBarStateController = notificationStackScrollLayoutController.mStatusBarStateController;
            notificationStackScrollLayoutController.updateSensitivenessWithAnimation(((StatusBarStateControllerImpl) sysuiStatusBarStateController).goingToFullShade());
            NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
            int i = ((StatusBarStateControllerImpl) sysuiStatusBarStateController).mLastState;
            boolean onKeyguard = notificationStackScrollLayout.onKeyguard();
            HeadsUpAppearanceController headsUpAppearanceController = notificationStackScrollLayout.mHeadsUpAppearanceController;
            if (headsUpAppearanceController != null) {
                headsUpAppearanceController.updateTopEntry();
            }
            notificationStackScrollLayout.mExpandHelper.mEnabled = !onKeyguard;
            notificationStackScrollLayout.requestChildrenUpdate();
            notificationStackScrollLayout.changeViewPosition(notificationStackScrollLayout.mFooterView, notificationStackScrollLayout.getChildCount() - 1);
            notificationStackScrollLayout.changeViewPosition(notificationStackScrollLayout.mEmptyShadeView, notificationStackScrollLayout.getChildCount() - 2);
            notificationStackScrollLayout.changeViewPosition(notificationStackScrollLayout.mShelf, notificationStackScrollLayout.getChildCount() - 3);
            notificationStackScrollLayout.updateVisibility$7();
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onStatePreChange(int i, int i2) {
            if (i == 2 && i2 == 1) {
                NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
                if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAnimationsEnabled) {
                    notificationStackScrollLayout.mEverythingNeedsAnimation = true;
                    notificationStackScrollLayout.mNeedsAnimation = true;
                    notificationStackScrollLayout.requestChildrenUpdate();
                }
            }
        }

        @Override // com.android.systemui.plugins.statusbar.StatusBarStateController.StateListener
        public final void onUpcomingStateChanged(int i) {
        }
    }
}
