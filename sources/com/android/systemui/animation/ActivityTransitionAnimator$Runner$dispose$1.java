package com.android.systemui.animation;

import android.os.Handler;
import android.util.Log;
import com.android.systemui.animation.ActivityTransitionAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ActivityTransitionAnimator$Runner$dispose$1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ ActivityTransitionAnimator$Runner$dispose$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                ((ActivityTransitionAnimator.Runner) this.this$0).delegate = null;
                break;
            case 1:
                ActivityTransitionAnimator.AnimationDelegate animationDelegate = (ActivityTransitionAnimator.AnimationDelegate) this.this$0;
                if (!animationDelegate.cancelled) {
                    Log.w("ActivityTransitionAnimator", "Remote animation timed out");
                    animationDelegate.timedOut = true;
                    if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
                        Log.d("ActivityTransitionAnimator", "Calling controller.onTransitionAnimationCancelled() [animation timed out]");
                    }
                    animationDelegate.controller.onTransitionAnimationCancelled();
                    animationDelegate.listener.onTransitionAnimationCancelled();
                    break;
                }
                break;
            default:
                if (((ActivityTransitionAnimator.AnimationDelegate) this.this$0) == null) {
                    Log.wtf("ActivityTransitionAnimator", "onAnimationCancelled called after completion");
                }
                ActivityTransitionAnimator.AnimationDelegate animationDelegate2 = (ActivityTransitionAnimator.AnimationDelegate) this.this$0;
                if (animationDelegate2 != null) {
                    Handler handler = animationDelegate2.timeoutHandler;
                    if (handler != null) {
                        handler.removeCallbacks(animationDelegate2.onTimeout);
                        handler.removeCallbacks(animationDelegate2.onLongTimeout);
                    }
                    if (!animationDelegate2.timedOut) {
                        Log.i("ActivityTransitionAnimator", "Remote animation was cancelled");
                        animationDelegate2.cancelled = true;
                        TransitionAnimator$startAnimation$1 transitionAnimator$startAnimation$1 = animationDelegate2.animation;
                        if (transitionAnimator$startAnimation$1 != null) {
                            transitionAnimator$startAnimation$1.$animator.cancel();
                        }
                        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
                            Log.d("ActivityTransitionAnimator", "Calling controller.onTransitionAnimationCancelled() [remote animation cancelled]");
                        }
                        animationDelegate2.controller.onTransitionAnimationCancelled();
                        animationDelegate2.listener.onTransitionAnimationCancelled();
                        break;
                    }
                }
                break;
        }
    }
}
