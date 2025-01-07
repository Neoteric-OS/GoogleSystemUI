package com.android.systemui.surfaceeffects.loadingeffect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import com.android.systemui.surfaceeffects.loadingeffect.LoadingEffect;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LoadingEffect$playMain$2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LoadingEffect this$0;

    public /* synthetic */ LoadingEffect$playMain$2(LoadingEffect loadingEffect, int i) {
        this.$r8$classId = i;
        this.this$0 = loadingEffect;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        switch (this.$r8$classId) {
            case 0:
                LoadingEffect loadingEffect = this.this$0;
                loadingEffect.currentAnimator = null;
                loadingEffect.playEaseOut();
                break;
            case 1:
                LoadingEffect loadingEffect2 = this.this$0;
                loadingEffect2.currentAnimator = null;
                if (loadingEffect2.state == LoadingEffect.AnimationState.EASE_IN) {
                    loadingEffect2.setState(LoadingEffect.AnimationState.MAIN);
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
                    loadingEffect2.config.getClass();
                    ofFloat.setDuration((long) 30000.0f);
                    TurbulenceNoiseShader turbulenceNoiseShader = loadingEffect2.turbulenceNoiseShader;
                    float f = turbulenceNoiseShader.noiseOffsetX;
                    float f2 = turbulenceNoiseShader.noiseOffsetY;
                    float f3 = turbulenceNoiseShader.noiseOffsetZ;
                    turbulenceNoiseShader.setOpacity(1.0f);
                    ofFloat.addUpdateListener(new LoadingEffect$playMain$1(loadingEffect2, f, f2, f3, 0));
                    ofFloat.addListener(new LoadingEffect$playMain$2(loadingEffect2, 0));
                    ofFloat.start();
                    loadingEffect2.currentAnimator = ofFloat;
                    break;
                }
                break;
            default:
                LoadingEffect loadingEffect3 = this.this$0;
                loadingEffect3.currentAnimator = null;
                loadingEffect3.setState(LoadingEffect.AnimationState.NOT_PLAYING);
                break;
        }
    }
}
