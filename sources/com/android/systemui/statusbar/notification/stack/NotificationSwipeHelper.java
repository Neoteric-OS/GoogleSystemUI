package com.android.systemui.statusbar.notification.stack;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.SwipeHelper;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.plugins.statusbar.NotificationMenuRowPlugin;
import com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper;
import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.SourceType$Companion$from$1;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.wm.shell.shared.animation.PhysicsAnimator;
import java.lang.ref.WeakReference;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationSwipeHelper extends SwipeHelper implements NotificationSwipeActionHelper {
    protected static final long COVER_MENU_DELAY = 4000;
    public static final SourceType$Companion$from$1 SWIPE_DISMISS = new SourceType$Companion$from$1("SwipeDismiss");
    public final NotificationCallback mCallback;
    public WeakReference mCurrMenuRowRef;
    public final NotificationSwipeHelper$$ExternalSyntheticLambda0 mFalsingCheck;
    public boolean mIsExpanded;
    public View mMenuExposedView;
    public final NotificationStackScrollLayoutController.AnonymousClass9 mMenuListener;
    public final NotificationRoundnessManager mNotificationRoundnessManager;
    public boolean mPulsing;
    public View mTranslatingParentView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public final DumpManager mDumpManager;
        public final FalsingManager mFalsingManager;
        public final FeatureFlags mFeatureFlags;
        public NotificationCallback mNotificationCallback;
        public final NotificationRoundnessManager mNotificationRoundnessManager;
        public NotificationStackScrollLayoutController.AnonymousClass9 mOnMenuEventListener;
        public final Resources mResources;
        public final ViewConfiguration mViewConfiguration;

        public Builder(Resources resources, ViewConfiguration viewConfiguration, DumpManager dumpManager, FalsingManager falsingManager, FeatureFlags featureFlags, NotificationRoundnessManager notificationRoundnessManager) {
            this.mResources = resources;
            this.mViewConfiguration = viewConfiguration;
            this.mDumpManager = dumpManager;
            this.mFalsingManager = falsingManager;
            this.mFeatureFlags = featureFlags;
            this.mNotificationRoundnessManager = notificationRoundnessManager;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface NotificationCallback {
    }

    /* JADX WARN: Type inference failed for: r7v1, types: [com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper$$ExternalSyntheticLambda0] */
    public NotificationSwipeHelper(Resources resources, ViewConfiguration viewConfiguration, FalsingManager falsingManager, FeatureFlags featureFlags, NotificationCallback notificationCallback, NotificationStackScrollLayoutController.AnonymousClass9 anonymousClass9, NotificationRoundnessManager notificationRoundnessManager) {
        super(notificationCallback, resources, viewConfiguration, falsingManager, featureFlags);
        this.mNotificationRoundnessManager = notificationRoundnessManager;
        this.mMenuListener = anonymousClass9;
        this.mCallback = notificationCallback;
        this.mFalsingCheck = new Runnable() { // from class: com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NotificationSwipeHelper.this.resetExposedMenuView(true, true);
            }
        };
    }

    public static boolean isTouchInView(View view, MotionEvent motionEvent) {
        int width;
        if (view == null) {
            return false;
        }
        int height = view instanceof ExpandableView ? ((ExpandableView) view).mActualHeight : view.getHeight();
        if (view instanceof NotificationShelf) {
            NotificationShelf notificationShelf = (NotificationShelf) view;
            float f = notificationShelf.mActualWidth;
            width = f > -1.0f ? (int) f : notificationShelf.getWidth();
        } else {
            width = view.getWidth();
        }
        int rawX = (int) motionEvent.getRawX();
        int rawY = (int) motionEvent.getRawY();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, width + i, height + i2).contains(rawX, rawY);
    }

    public Animator createTranslationAnimation(View view, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) View.TRANSLATION_X, f);
        if (animatorUpdateListener != null) {
            ofFloat.addUpdateListener(animatorUpdateListener);
        }
        return ofFloat;
    }

    @Override // com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper
    public final void dismiss(View view, float f) {
        dismissChild(view, f, !swipedFastEnough());
    }

    @Override // com.android.systemui.SwipeHelper
    public final void dismissChild(View view, float f, boolean z) {
        superDismissChild(view, f, z);
        NotificationStackScrollLayoutController.AnonymousClass9 anonymousClass9 = (NotificationStackScrollLayoutController.AnonymousClass9) this.mCallback;
        NotificationStackScrollLayout notificationStackScrollLayout = NotificationStackScrollLayoutController.this.mView;
        if (notificationStackScrollLayout.mIsExpanded && notificationStackScrollLayout.mAmbientState.mDozeAmount == 0.0f) {
            anonymousClass9.handleChildViewDismissed(view);
        }
        NotificationStackScrollLayoutController.this.mNotificationGutsManager.closeAndSaveGuts(true, false, false, false);
        handleMenuCoveredOrDismissed();
    }

    public final NotificationMenuRowPlugin getCurrentMenuRow() {
        WeakReference weakReference = this.mCurrMenuRowRef;
        if (weakReference == null) {
            return null;
        }
        return (NotificationMenuRowPlugin) weakReference.get();
    }

    @Override // com.android.systemui.SwipeHelper
    public float getEscapeVelocity() {
        return 500.0f * this.mDensityScale;
    }

    public Runnable getFalsingCheck() {
        return this.mFalsingCheck;
    }

    public Handler getHandler() {
        return this.mHandler;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.SwipeHelper
    public final float getTranslation(View view) {
        if (view instanceof SwipeableView) {
            return ((ExpandableNotificationRow) ((SwipeableView) view)).getTranslation();
        }
        return 0.0f;
    }

    @Override // com.android.systemui.SwipeHelper
    public Animator getViewTranslationAnimator(View view, float f, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator.Companion.getInstance(view).cancel();
        return view instanceof ExpandableNotificationRow ? ((ExpandableNotificationRow) view).getTranslateViewAnimator(f, animatorUpdateListener) : createTranslationAnimation(view, f, animatorUpdateListener);
    }

    public void handleMenuCoveredOrDismissed() {
        View view = this.mMenuExposedView;
        if (view == null || view != this.mTranslatingParentView) {
            return;
        }
        this.mMenuExposedView = null;
    }

    public void handleMenuRowSwipe(MotionEvent motionEvent, View view, float f, NotificationMenuRowPlugin notificationMenuRowPlugin) {
        if (!notificationMenuRowPlugin.shouldShowMenu()) {
            if (isDismissGesture(motionEvent)) {
                dismiss(view, f);
                return;
            } else {
                snapClosed(view, f);
                notificationMenuRowPlugin.onSnapClosed();
                return;
            }
        }
        if (notificationMenuRowPlugin.isSnappedAndOnSameSide()) {
            boolean isDismissGesture = isDismissGesture(motionEvent);
            if (notificationMenuRowPlugin.isWithinSnapMenuThreshold() && !isDismissGesture) {
                notificationMenuRowPlugin.onSnapOpen();
                snapChild(view, notificationMenuRowPlugin.getMenuSnapTarget(), f);
                return;
            } else if (!isDismissGesture || notificationMenuRowPlugin.shouldSnapBack()) {
                snapClosed(view, f);
                notificationMenuRowPlugin.onSnapClosed();
                return;
            } else {
                dismiss(view, f);
                notificationMenuRowPlugin.onDismiss();
                return;
            }
        }
        boolean isDismissGesture2 = isDismissGesture(motionEvent);
        boolean isTowardsMenu = notificationMenuRowPlugin.isTowardsMenu(f);
        boolean z = false;
        boolean z2 = getEscapeVelocity() <= Math.abs(f);
        boolean z3 = !notificationMenuRowPlugin.canBeDismissed() && ((double) (motionEvent.getEventTime() - motionEvent.getDownTime())) >= 200.0d;
        boolean z4 = isTowardsMenu && !isDismissGesture2;
        boolean z5 = (!swipedFarEnough() && notificationMenuRowPlugin.isSwipedEnoughToShowMenu() && (!z2 || z3)) || ((z2 && !isTowardsMenu && !isDismissGesture2) && (notificationMenuRowPlugin.shouldShowGutsOnSnapOpen() || (this.mIsExpanded && !this.mPulsing)));
        int menuSnapTarget = notificationMenuRowPlugin.getMenuSnapTarget();
        if (z5 && !isFalseGesture()) {
            z = true;
        }
        if ((z4 || z) && menuSnapTarget != 0) {
            snapChild(view, menuSnapTarget, f);
            notificationMenuRowPlugin.onSnapOpen();
        } else if (!isDismissGesture2 || isTowardsMenu) {
            snapClosed(view, f);
            notificationMenuRowPlugin.onSnapClosed();
        } else {
            dismiss(view, f);
            notificationMenuRowPlugin.onDismiss();
        }
    }

    public void initializeRow(SwipeableView swipeableView) {
        ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) swipeableView;
        if (expandableNotificationRow.mEntry.hasFinishedInitialization()) {
            NotificationMenuRowPlugin createMenu = expandableNotificationRow.createMenu();
            setCurrentMenuRow(createMenu);
            if (createMenu != null) {
                createMenu.setMenuClickListener(this.mMenuListener);
                createMenu.onTouchStart();
            }
        }
    }

    @Override // com.android.systemui.SwipeHelper
    public final void onChildSnappedBack(View view, float f) {
        NotificationStackScrollLayoutController.AnonymousClass9 anonymousClass9 = (NotificationStackScrollLayoutController.AnonymousClass9) super.mCallback;
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = NotificationStackScrollLayoutController.this;
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.updateFirstAndLastBackgroundViews();
        notificationStackScrollLayout.mController.mNotificationRoundnessManager.setViewsAffectedBySwipe(null, null, null);
        notificationStackScrollLayout.mShelf.updateAppearance();
        if (view instanceof ExpandableNotificationRow) {
            ExpandableNotificationRow expandableNotificationRow = (ExpandableNotificationRow) view;
            if (expandableNotificationRow.mIsPinned && !anonymousClass9.canChildBeDismissed(expandableNotificationRow) && expandableNotificationRow.mEntry.mSbn.getNotification().fullScreenIntent == null) {
                ((BaseHeadsUpManager) notificationStackScrollLayoutController.mHeadsUpManager).removeNotification$1(expandableNotificationRow.mEntry.mSbn.getKey(), "onChildSnappedBack", true);
            }
        }
        NotificationMenuRowPlugin currentMenuRow = getCurrentMenuRow();
        if (currentMenuRow != null && f == 0.0f) {
            currentMenuRow.resetMenu();
            setCurrentMenuRow(null);
        }
        InteractionJankMonitor.getInstance().end(4);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
    
        if (r1 != 3) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00be, code lost:
    
        if (r13 != false) goto L62;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.systemui.SwipeHelper, com.android.systemui.Gefingerpoken
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean onInterceptTouchEvent(android.view.MotionEvent r13) {
        /*
            Method dump skipped, instructions count: 357
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.notification.stack.NotificationSwipeHelper.onInterceptTouchEvent(android.view.MotionEvent):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void resetExposedMenuView(boolean z, boolean z2) {
        if (shouldResetMenu(z2)) {
            View view = this.mMenuExposedView;
            if (z) {
                Animator viewTranslationAnimator = getViewTranslationAnimator(view, 0.0f, null);
                if (viewTranslationAnimator != null) {
                    viewTranslationAnimator.start();
                }
            } else if (view instanceof SwipeableView) {
                SwipeableView swipeableView = (SwipeableView) view;
                swipeableView.getClass();
                ((ExpandableNotificationRow) swipeableView).resetTranslation();
            }
            this.mMenuExposedView = null;
        }
    }

    public void setCurrentMenuRow(NotificationMenuRowPlugin notificationMenuRowPlugin) {
        this.mCurrMenuRowRef = notificationMenuRowPlugin != null ? new WeakReference(notificationMenuRowPlugin) : null;
    }

    public void setTranslatingParentView(View view) {
        this.mTranslatingParentView = view;
    }

    public boolean shouldResetMenu(boolean z) {
        View view = this.mMenuExposedView;
        if (view != null) {
            return z || view != this.mTranslatingParentView;
        }
        return false;
    }

    @Override // com.android.systemui.SwipeHelper
    public final void snapChild(View view, float f, float f2) {
        if (view instanceof SwipeableView) {
            superSnapChild(view, f, f2);
        }
        this.mCallback.getClass();
        if (f == 0.0f) {
            handleMenuCoveredOrDismissed();
        }
    }

    public void snapClosed(View view, float f) {
        snapChild(view, 0.0f, f);
    }

    @Override // com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper
    public final void snapOpen(View view, int i, float f) {
        snapChild(view, i, f);
    }

    @Override // com.android.systemui.plugins.statusbar.NotificationSwipeActionHelper
    public final void snooze(StatusBarNotification statusBarNotification, NotificationSwipeActionHelper.SnoozeOption snoozeOption) {
        NotificationStackScrollLayoutController.this.mNotificationsController.setNotificationSnoozed(statusBarNotification, snoozeOption);
    }

    public void superDismissChild(View view, float f, boolean z) {
        dismissChild(view, f, null, 0L, z, 0L, false);
    }

    public void superSnapChild(final View view, final float f, float f2) {
        PhysicsAnimator companion;
        Animator animator;
        final boolean canChildBeDismissed = ((NotificationStackScrollLayoutController.AnonymousClass9) super.mCallback).canChildBeDismissed(view);
        if ((view instanceof ExpandableNotificationRow) && (animator = ((ExpandableNotificationRow) view).mTranslateAnim) != null) {
            animator.cancel();
        }
        Function2 function2 = PhysicsAnimator.onAnimatorCreated;
        PhysicsAnimator.Companion.getInstance(view).cancel();
        boolean z = view instanceof ExpandableNotificationRow;
        PhysicsAnimator.SpringConfig springConfig = this.mSnapBackSpringConfig;
        if (z) {
            companion = PhysicsAnimator.Companion.getInstance((ExpandableNotificationRow) view);
            ExpandableNotificationRow.AnonymousClass2 anonymousClass2 = ExpandableNotificationRow.TRANSLATE_CONTENT;
            anonymousClass2.getName();
            companion.spring(new FloatPropertyCompat.AnonymousClass1(anonymousClass2), f, f2, springConfig);
        } else {
            companion = PhysicsAnimator.Companion.getInstance(view);
            companion.spring(DynamicAnimation.TRANSLATION_X, f, f2, springConfig);
        }
        companion.updateListeners.add(new PhysicsAnimator.UpdateListener() { // from class: com.android.systemui.SwipeHelper$$ExternalSyntheticLambda1
            @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.UpdateListener
            public final void onAnimationUpdateForProperty(Object obj) {
                View view2 = (View) obj;
                NotificationSwipeHelper notificationSwipeHelper = NotificationSwipeHelper.this;
                notificationSwipeHelper.updateSwipeProgressFromOffset(view2, notificationSwipeHelper.getTranslation(view2), canChildBeDismissed);
            }
        });
        companion.endListeners.add(new PhysicsAnimator.EndListener() { // from class: com.android.systemui.SwipeHelper$$ExternalSyntheticLambda2
            @Override // com.android.wm.shell.shared.animation.PhysicsAnimator.EndListener
            public final void onAnimationEnd(Object obj, FloatPropertyCompat floatPropertyCompat, boolean z2, boolean z3, float f3, float f4) {
                View view2 = view;
                NotificationSwipeHelper notificationSwipeHelper = NotificationSwipeHelper.this;
                notificationSwipeHelper.mSnappingChild = false;
                if (!z3) {
                    notificationSwipeHelper.updateSwipeProgressFromOffset(view2, notificationSwipeHelper.getTranslation(view2), canChildBeDismissed);
                    if ((notificationSwipeHelper.mIsSwiping ? notificationSwipeHelper.mTouchedView : null) == view2) {
                        notificationSwipeHelper.resetSwipeStates(false);
                    }
                    if (view2 == notificationSwipeHelper.mTouchedView && !notificationSwipeHelper.mIsSwiping) {
                        notificationSwipeHelper.mTouchedView = null;
                    }
                }
                notificationSwipeHelper.onChildSnappedBack(view2, f);
            }
        });
        this.mSnappingChild = true;
        companion.start();
    }

    @Override // com.android.systemui.SwipeHelper
    public boolean swipedFarEnough() {
        return Math.abs(getTranslation(this.mTouchedView)) > ((float) this.mTouchedView.getMeasuredWidth()) * 0.6f;
    }

    @Override // com.android.systemui.SwipeHelper
    public boolean swipedFastEnough() {
        float xVelocity = this.mVelocityTracker.getXVelocity();
        float translation = getTranslation(this.mTouchedView);
        if (Math.abs(xVelocity) > getEscapeVelocity()) {
            return ((xVelocity > 0.0f ? 1 : (xVelocity == 0.0f ? 0 : -1)) > 0) == ((translation > 0.0f ? 1 : (translation == 0.0f ? 0 : -1)) > 0);
        }
        return false;
    }
}
