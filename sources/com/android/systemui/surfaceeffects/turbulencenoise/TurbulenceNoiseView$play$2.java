package com.android.systemui.surfaceeffects.turbulencenoise;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseView$play$2 extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable $onAnimationEnd;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TurbulenceNoiseView this$0;

    public /* synthetic */ TurbulenceNoiseView$play$2(TurbulenceNoiseView turbulenceNoiseView, Runnable runnable, int i) {
        this.$r8$classId = i;
        this.this$0 = turbulenceNoiseView;
        this.$onAnimationEnd = runnable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                this.this$0.currentAnimator = null;
                Runnable runnable = this.$onAnimationEnd;
                if (runnable != null) {
                    runnable.run();
                    break;
                }
                break;
            case 1:
                this.this$0.currentAnimator = null;
                Runnable runnable2 = this.$onAnimationEnd;
                if (runnable2 != null) {
                    runnable2.run();
                    break;
                }
                break;
            default:
                this.this$0.currentAnimator = null;
                Runnable runnable3 = this.$onAnimationEnd;
                if (runnable3 != null) {
                    runnable3.run();
                    break;
                }
                break;
        }
    }
}
