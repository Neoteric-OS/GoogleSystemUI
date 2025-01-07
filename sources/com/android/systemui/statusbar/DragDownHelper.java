package com.android.systemui.statusbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import com.android.systemui.Gefingerpoken;
import com.android.systemui.keyguard.domain.interactor.NaturalScrollingSettingObserver;
import com.android.systemui.navigationbar.gestural.Utilities;
import com.android.systemui.plugins.ActivityStarter;
import com.android.systemui.plugins.FalsingManager;
import com.android.systemui.shade.QuickSettingsControllerImpl;
import com.android.systemui.shade.data.repository.ShadeRepository;
import com.android.systemui.shade.data.repository.ShadeRepositoryImpl;
import com.android.systemui.shade.domain.interactor.ShadeLockscreenInteractor;
import com.android.systemui.statusbar.notification.row.ExpandableNotificationRow;
import com.android.systemui.statusbar.notification.row.ExpandableView;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.phone.LSShadeTransitionLogger;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;
import java.util.Iterator;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragDownHelper implements Gefingerpoken {
    public float dragDownAmountOnStart;
    public final LockscreenShadeTransitionController dragDownCallback;
    public boolean draggedFarEnough;
    public NotificationStackScrollLayout.AnonymousClass6 expandCallback;
    public final FalsingManager falsingManager;
    public float initialTouchX;
    public float initialTouchY;
    public boolean isDraggingDown;
    public boolean isTrackpadReverseScroll;
    public float lastHeight;
    public int minDragDistance;
    public final NaturalScrollingSettingObserver naturalScrollingSettingObserver;
    public final ShadeRepository shadeRepository;
    public float slopMultiplier;
    public ExpandableView startingChild;
    public float touchSlop;

    public DragDownHelper(FalsingManager falsingManager, LockscreenShadeTransitionController lockscreenShadeTransitionController, NaturalScrollingSettingObserver naturalScrollingSettingObserver, ShadeRepository shadeRepository, Context context) {
        this.falsingManager = falsingManager;
        this.dragDownCallback = lockscreenShadeTransitionController;
        this.naturalScrollingSettingObserver = naturalScrollingSettingObserver;
        this.shadeRepository = shadeRepository;
        this.minDragDistance = context.getResources().getDimensionPixelSize(R.dimen.keyguard_drag_down_min_distance);
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.touchSlop = viewConfiguration.getScaledTouchSlop();
        this.slopMultiplier = viewConfiguration.getScaledAmbiguousGestureMultiplier();
    }

    public final void cancelChildExpansion(final ExpandableView expandableView, long j) {
        if (expandableView.mActualHeight == expandableView.getCollapsedHeight()) {
            NotificationStackScrollLayout.AnonymousClass6 anonymousClass6 = this.expandCallback;
            if (anonymousClass6 == null) {
                anonymousClass6 = null;
            }
            anonymousClass6.setUserLockedChild(expandableView, false);
            return;
        }
        ValueAnimator ofInt = ValueAnimator.ofInt(expandableView.mActualHeight, expandableView.getCollapsedHeight());
        ofInt.setInterpolator(Interpolators.FAST_OUT_SLOW_IN);
        ofInt.setDuration(j);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.statusbar.DragDownHelper$cancelChildExpansion$1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                ExpandableView.this.setActualHeight(((Integer) valueAnimator.getAnimatedValue()).intValue(), true);
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.statusbar.DragDownHelper$cancelChildExpansion$2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                NotificationStackScrollLayout.AnonymousClass6 anonymousClass62 = DragDownHelper.this.expandCallback;
                if (anonymousClass62 == null) {
                    anonymousClass62 = null;
                }
                anonymousClass62.setUserLockedChild(expandableView, false);
            }
        });
        ofInt.start();
    }

    public final void captureStartingChild$1(float f, float f2) {
        if (this.startingChild == null) {
            NotificationStackScrollLayout.AnonymousClass6 anonymousClass6 = this.expandCallback;
            if (anonymousClass6 == null) {
                anonymousClass6 = null;
            }
            ExpandableView childAtRawPosition = NotificationStackScrollLayout.this.getChildAtRawPosition(f, f2);
            this.startingChild = childAtRawPosition;
            if (childAtRawPosition != null) {
                if (!this.dragDownCallback.isDragDownEnabledForView$frameworks__base__packages__SystemUI__android_common__SystemUI_core(childAtRawPosition)) {
                    this.startingChild = null;
                } else {
                    NotificationStackScrollLayout.AnonymousClass6 anonymousClass62 = this.expandCallback;
                    (anonymousClass62 != null ? anonymousClass62 : null).setUserLockedChild(this.startingChild, true);
                }
            }
        }
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.draggedFarEnough = false;
            this.isDraggingDown = false;
            this.startingChild = null;
            this.initialTouchY = y;
            this.initialTouchX = x;
            NaturalScrollingSettingObserver naturalScrollingSettingObserver = this.naturalScrollingSettingObserver;
            if (!naturalScrollingSettingObserver.isInitialized) {
                naturalScrollingSettingObserver.isInitialized = true;
                naturalScrollingSettingObserver.update();
            }
            this.isTrackpadReverseScroll = !naturalScrollingSettingObserver.isNaturalScrollingEnabled && Utilities.isTrackpadScroll(motionEvent);
        } else if (actionMasked == 2) {
            float f = (y - this.initialTouchY) * (this.isTrackpadReverseScroll ? -1 : 1);
            if (f > (motionEvent.getClassification() == 1 ? this.touchSlop * this.slopMultiplier : this.touchSlop) && f > Math.abs(x - this.initialTouchX)) {
                this.isDraggingDown = true;
                captureStartingChild$1(this.initialTouchX, this.initialTouchY);
                this.initialTouchY = y;
                this.initialTouchX = x;
                ExpandableView expandableView = this.startingChild;
                LockscreenShadeTransitionController lockscreenShadeTransitionController = this.dragDownCallback;
                LSShadeTransitionLogger lSShadeTransitionLogger = lockscreenShadeTransitionController.logger;
                lSShadeTransitionLogger.logDragDownStarted(expandableView);
                NotificationStackScrollLayoutController notificationStackScrollLayoutController = lockscreenShadeTransitionController.nsslController;
                if (notificationStackScrollLayoutController == null) {
                    notificationStackScrollLayoutController = null;
                }
                notificationStackScrollLayoutController.mView.cancelLongPress();
                NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = lockscreenShadeTransitionController.nsslController;
                if (notificationStackScrollLayoutController2 == null) {
                    notificationStackScrollLayoutController2 = null;
                }
                notificationStackScrollLayoutController2.checkSnoozeLeavebehind();
                ValueAnimator valueAnimator = lockscreenShadeTransitionController.dragDownAnimator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    lSShadeTransitionLogger.logAnimationCancelled(false);
                    valueAnimator.cancel();
                }
                this.dragDownAmountOnStart = lockscreenShadeTransitionController.dragDownAmount;
                if (this.startingChild == null && !lockscreenShadeTransitionController.isDragDownAnywhereEnabled$frameworks__base__packages__SystemUI__android_common__SystemUI_core()) {
                    r3 = false;
                }
                if (r3) {
                    ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) this.shadeRepository;
                    Boolean bool = Boolean.TRUE;
                    StateFlowImpl stateFlowImpl = shadeRepositoryImpl.legacyLockscreenShadeTracking;
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, bool);
                }
                return r3;
            }
        }
        return false;
    }

    @Override // com.android.systemui.Gefingerpoken
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.isDraggingDown) {
            return false;
        }
        float y = motionEvent.getY();
        int actionMasked = motionEvent.getActionMasked();
        final LockscreenShadeTransitionController lockscreenShadeTransitionController = this.dragDownCallback;
        if (actionMasked != 1) {
            if (actionMasked != 2) {
                if (actionMasked != 3) {
                    return false;
                }
                stopDragging();
                return false;
            }
            float f = this.isTrackpadReverseScroll ? -1 : 1;
            float f2 = this.initialTouchY;
            this.lastHeight = (y - f2) * f;
            captureStartingChild$1(this.initialTouchX, f2);
            lockscreenShadeTransitionController.setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(this.lastHeight + this.dragDownAmountOnStart);
            ExpandableView expandableView = this.startingChild;
            if (expandableView != null) {
                float f3 = this.lastHeight;
                float f4 = f3 >= 0.0f ? f3 : 0.0f;
                boolean isContentExpandable = expandableView.isContentExpandable();
                float f5 = f4 * (isContentExpandable ? 0.5f : 0.15f);
                if (isContentExpandable && expandableView.getCollapsedHeight() + f5 > expandableView.getMaxContentHeight()) {
                    f5 -= ((expandableView.getCollapsedHeight() + f5) - expandableView.getMaxContentHeight()) * 0.85f;
                }
                expandableView.setActualHeight((int) (expandableView.getCollapsedHeight() + f5), true);
            }
            if (this.lastHeight > this.minDragDistance) {
                if (!this.draggedFarEnough) {
                    this.draggedFarEnough = true;
                }
            } else if (this.draggedFarEnough) {
                this.draggedFarEnough = false;
            }
            return true;
        }
        FalsingManager falsingManager = this.falsingManager;
        if (falsingManager.isUnlockingDisabled() || ((((StatusBarStateControllerImpl) lockscreenShadeTransitionController.statusBarStateController).mState == 1 && (falsingManager.isFalseTouch(2) || !this.draggedFarEnough)) || !lockscreenShadeTransitionController.canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core())) {
            stopDragging();
            return false;
        }
        float f6 = (y - this.initialTouchY) * (this.isTrackpadReverseScroll ? -1 : 1);
        final ExpandableView expandableView2 = this.startingChild;
        int i = (int) f6;
        boolean canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core = lockscreenShadeTransitionController.canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core();
        LSShadeTransitionLogger lSShadeTransitionLogger = lockscreenShadeTransitionController.logger;
        if (canDragDown$frameworks__base__packages__SystemUI__android_common__SystemUI_core) {
            LockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1 lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1 = new LockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1(lockscreenShadeTransitionController);
            NotificationStackScrollLayoutController notificationStackScrollLayoutController = lockscreenShadeTransitionController.nsslController;
            if (notificationStackScrollLayoutController == null) {
                notificationStackScrollLayoutController = null;
            }
            if (notificationStackScrollLayoutController.mDynamicPrivacyController.isInLockedDownShade()) {
                lSShadeTransitionLogger.logDraggedDownLockDownShade(expandableView2);
                ((StatusBarStateControllerImpl) lockscreenShadeTransitionController.statusBarStateController).mLeaveOpenOnKeyguardHide = true;
                lockscreenShadeTransitionController.activityStarter.dismissKeyguardThenExecute(new ActivityStarter.OnDismissAction() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$onDraggedDown$1
                    @Override // com.android.systemui.plugins.ActivityStarter.OnDismissAction
                    public final boolean onDismiss() {
                        LockscreenShadeTransitionController.this.nextHideKeyguardNeedsNoAnimation = true;
                        return false;
                    }
                }, lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1, false);
            } else {
                lSShadeTransitionLogger.logDraggedDown(expandableView2, i);
                if (!lockscreenShadeTransitionController.ambientState.mDozing || expandableView2 != null) {
                    lockscreenShadeTransitionController.goToLockedShadeInternal(expandableView2, new Function1() { // from class: com.android.systemui.statusbar.LockscreenShadeTransitionController$onDraggedDown$animationHandler$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            long longValue = ((Number) obj).longValue();
                            View view = expandableView2;
                            if (view instanceof ExpandableNotificationRow) {
                                ((ExpandableNotificationRow) view).onExpandedByGesture(true);
                            }
                            ((ShadeLockscreenInteractor) lockscreenShadeTransitionController.shadeLockscreenInteractorLazy.get()).transitionToExpandedShade(longValue);
                            Iterator it = lockscreenShadeTransitionController.callbacks.iterator();
                            while (it.hasNext()) {
                                ((QuickSettingsControllerImpl.LockscreenShadeTransitionCallback) it.next()).setTransitionToFullShadeAmount(0.0f, longValue, true);
                            }
                            LockscreenShadeTransitionController lockscreenShadeTransitionController2 = lockscreenShadeTransitionController;
                            lockscreenShadeTransitionController2.forceApplyAmount = true;
                            lockscreenShadeTransitionController2.logger.logDragDownAmountReset();
                            lockscreenShadeTransitionController.setDragDownAmount$frameworks__base__packages__SystemUI__android_common__SystemUI_core(0.0f);
                            lockscreenShadeTransitionController.forceApplyAmount = false;
                            return Unit.INSTANCE;
                        }
                    }, lockscreenShadeTransitionController$onDraggedDown$cancelRunnable$1);
                }
            }
        } else {
            lSShadeTransitionLogger.logUnSuccessfulDragDown(expandableView2);
            lockscreenShadeTransitionController.setDragDownAmountAnimated(0.0f, 0L, null);
        }
        ExpandableView expandableView3 = this.startingChild;
        if (expandableView3 != null) {
            NotificationStackScrollLayout.AnonymousClass6 anonymousClass6 = this.expandCallback;
            if (anonymousClass6 == null) {
                anonymousClass6 = null;
            }
            anonymousClass6.setUserLockedChild(expandableView3, false);
            this.startingChild = null;
        }
        this.isDraggingDown = false;
        this.isTrackpadReverseScroll = false;
        ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) this.shadeRepository;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = shadeRepositoryImpl.legacyLockscreenShadeTracking;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        return true;
    }

    public final void stopDragging() {
        ExpandableView expandableView = this.startingChild;
        if (expandableView != null) {
            cancelChildExpansion(expandableView, 375L);
            this.startingChild = null;
        }
        this.isDraggingDown = false;
        this.isTrackpadReverseScroll = false;
        ShadeRepositoryImpl shadeRepositoryImpl = (ShadeRepositoryImpl) this.shadeRepository;
        Boolean bool = Boolean.FALSE;
        StateFlowImpl stateFlowImpl = shadeRepositoryImpl.legacyLockscreenShadeTracking;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
        LockscreenShadeTransitionController lockscreenShadeTransitionController = this.dragDownCallback;
        lockscreenShadeTransitionController.logger.logDragDownAborted();
        NotificationStackScrollLayoutController notificationStackScrollLayoutController = lockscreenShadeTransitionController.nsslController;
        if (notificationStackScrollLayoutController == null) {
            notificationStackScrollLayoutController = null;
        }
        NotificationStackScrollLayout notificationStackScrollLayout = notificationStackScrollLayoutController.mView;
        notificationStackScrollLayout.mScroller.abortAnimation();
        notificationStackScrollLayout.setOwnScrollY(0);
        NotificationStackScrollLayoutController notificationStackScrollLayoutController2 = lockscreenShadeTransitionController.nsslController;
        if (notificationStackScrollLayoutController2 == null) {
            notificationStackScrollLayoutController2 = null;
        }
        notificationStackScrollLayoutController2.mView.mCheckForLeavebehind = true;
        lockscreenShadeTransitionController.setDragDownAmountAnimated(0.0f, 0L, null);
    }
}
