package com.android.systemui.unfold;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.ViewPropertyAnimator;
import com.android.app.animation.Interpolators;
import com.android.systemui.keyguard.domain.interactor.ToAodFoldTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.ToAodFoldTransitionInteractor$foldAnimator$1;
import com.android.systemui.plugins.clocks.ClockController;
import com.android.systemui.shade.NotificationPanelViewController;
import com.android.systemui.unfold.FoldAodAnimationController$onScreenTurnedOn$1;
import com.android.systemui.util.concurrency.ExecutorImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldAodAnimationController$onScreenTurnedOn$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ FoldAodAnimationController this$0;

    public /* synthetic */ FoldAodAnimationController$onScreenTurnedOn$1(FoldAodAnimationController foldAodAnimationController, int i) {
        this.$r8$classId = i;
        this.this$0 = foldAodAnimationController;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                FoldAodAnimationController foldAodAnimationController = this.this$0;
                if (foldAodAnimationController.shouldPlayAnimation) {
                    ExecutorImpl.ExecutionToken executionToken = foldAodAnimationController.cancelAnimation;
                    if (executionToken != null) {
                        executionToken.run();
                    }
                    FoldAodAnimationController foldAodAnimationController2 = this.this$0;
                    foldAodAnimationController2.cancelAnimation = foldAodAnimationController2.mainExecutor.executeDelayed(foldAodAnimationController2.startAnimationRunnable, 0L);
                    this.this$0.shouldPlayAnimation = false;
                    break;
                }
                break;
            case 1:
                FoldAodAnimationController.this.latencyTracker.onActionEnd(18);
                break;
            case 2:
                this.this$0.setAnimationState(false);
                break;
            case 3:
                this.this$0.setAnimationState(false);
                break;
            default:
                ToAodFoldTransitionInteractor$foldAnimator$1 toAodFoldTransitionInteractor$foldAnimator$1 = ((ToAodFoldTransitionInteractor) this.this$0.foldTransitionInteractor.get()).foldAnimator;
                FoldAodAnimationController foldAodAnimationController3 = this.this$0;
                FoldAodAnimationController$onScreenTurnedOn$1 foldAodAnimationController$onScreenTurnedOn$1 = new FoldAodAnimationController$onScreenTurnedOn$1(foldAodAnimationController3, 1);
                final FoldAodAnimationController$onScreenTurnedOn$1 foldAodAnimationController$onScreenTurnedOn$12 = new FoldAodAnimationController$onScreenTurnedOn$1(foldAodAnimationController3, 2);
                final FoldAodAnimationController$onScreenTurnedOn$1 foldAodAnimationController$onScreenTurnedOn$13 = new FoldAodAnimationController$onScreenTurnedOn$1(foldAodAnimationController3, 3);
                final ToAodFoldTransitionInteractor toAodFoldTransitionInteractor = toAodFoldTransitionInteractor$foldAnimator$1.this$0;
                NotificationPanelViewController.ShadeFoldAnimatorImpl shadeFoldAnimatorImpl = toAodFoldTransitionInteractor.parentAnimator;
                if (shadeFoldAnimatorImpl != null) {
                    final ViewPropertyAnimator animate = NotificationPanelViewController.this.mView.animate();
                    animate.cancel();
                    animate.translationX(0.0f).alpha(1.0f).setDuration(600L).setInterpolator(Interpolators.EMPHASIZED_DECELERATE).setListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.shade.NotificationPanelViewController.ShadeFoldAnimatorImpl.1
                        public final /* synthetic */ FoldAodAnimationController$onScreenTurnedOn$1 val$cancelAction;
                        public final /* synthetic */ FoldAodAnimationController$onScreenTurnedOn$1 val$endAction;
                        public final /* synthetic */ ViewPropertyAnimator val$viewAnimator;

                        public AnonymousClass1(final FoldAodAnimationController$onScreenTurnedOn$1 foldAodAnimationController$onScreenTurnedOn$132, final FoldAodAnimationController$onScreenTurnedOn$1 foldAodAnimationController$onScreenTurnedOn$122, final ViewPropertyAnimator animate2) {
                            r2 = foldAodAnimationController$onScreenTurnedOn$132;
                            r3 = foldAodAnimationController$onScreenTurnedOn$122;
                            r4 = animate2;
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationCancel(Animator animator) {
                            r2.run();
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationEnd(Animator animator) {
                            r3.run();
                            r4.setListener(null);
                            r4.setUpdateListener(null);
                        }

                        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                        public final void onAnimationStart(Animator animator) {
                            FoldAodAnimationController$onScreenTurnedOn$1.this.run();
                        }
                    }).setUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.keyguard.domain.interactor.ToAodFoldTransitionInteractor$foldAnimator$1$startFoldToAodAnimation$1$1
                        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                        public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                            KeyguardClockInteractor keyguardClockInteractor = ToAodFoldTransitionInteractor.this.keyguardClockInteractor;
                            float animatedFraction = valueAnimator.getAnimatedFraction();
                            ClockController clockController = keyguardClockInteractor.clock$receiver.clock;
                            if (clockController != null) {
                                clockController.getSmallClock().getAnimations().fold(animatedFraction);
                                clockController.getLargeClock().getAnimations().fold(animatedFraction);
                            }
                        }
                    }).start();
                    break;
                }
                break;
        }
    }
}
