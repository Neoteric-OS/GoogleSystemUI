package com.android.systemui.statusbar.phone;

import android.view.WindowManager;
import com.android.systemui.shared.animation.UnfoldMoveFromCenterAnimator;
import com.android.systemui.statusbar.phone.PhoneStatusBarViewController;
import com.android.systemui.unfold.UnfoldTransitionProgressProvider;
import com.android.systemui.unfold.system.ActivityManagerActivityTypeProvider;
import com.android.systemui.unfold.util.ScopedUnfoldTransitionProgressProvider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarMoveFromCenterAnimationController {
    public final ActivityManagerActivityTypeProvider currentActivityTypeProvider;
    public Boolean isOnHomeActivity;
    public final UnfoldMoveFromCenterAnimator moveFromCenterAnimator;
    public final ScopedUnfoldTransitionProgressProvider progressProvider;
    public final TransitionListener transitionListener;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatusBarIconsAlphaProvider {
        public StatusBarIconsAlphaProvider() {
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransitionListener implements UnfoldTransitionProgressProvider.TransitionProgressListener {
        public TransitionListener() {
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionFinished() {
            StatusBarMoveFromCenterAnimationController statusBarMoveFromCenterAnimationController = StatusBarMoveFromCenterAnimationController.this;
            statusBarMoveFromCenterAnimationController.moveFromCenterAnimator.onTransitionProgress(1.0f);
            statusBarMoveFromCenterAnimationController.isOnHomeActivity = null;
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionProgress(float f) {
            StatusBarMoveFromCenterAnimationController.this.moveFromCenterAnimator.onTransitionProgress(f);
        }

        @Override // com.android.systemui.unfold.UnfoldTransitionProgressProvider.TransitionProgressListener
        public final void onTransitionStarted() {
            StatusBarMoveFromCenterAnimationController statusBarMoveFromCenterAnimationController = StatusBarMoveFromCenterAnimationController.this;
            statusBarMoveFromCenterAnimationController.isOnHomeActivity = statusBarMoveFromCenterAnimationController.currentActivityTypeProvider._isHomeActivity;
        }
    }

    public StatusBarMoveFromCenterAnimationController(ScopedUnfoldTransitionProgressProvider scopedUnfoldTransitionProgressProvider, ActivityManagerActivityTypeProvider activityManagerActivityTypeProvider, WindowManager windowManager) {
        this.currentActivityTypeProvider = activityManagerActivityTypeProvider;
        new TransitionListener();
        this.moveFromCenterAnimator = new UnfoldMoveFromCenterAnimator(windowManager, new PhoneStatusBarViewController.StatusBarViewsCenterProvider(), new StatusBarIconsAlphaProvider());
    }
}
