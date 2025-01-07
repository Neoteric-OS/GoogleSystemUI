package com.android.systemui.surfaceeffects.turbulencenoise;

import android.animation.ValueAnimator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseView$play$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ TurbulenceNoiseAnimationConfig $config;
    public final /* synthetic */ float $initialX;
    public final /* synthetic */ float $initialY;
    public final /* synthetic */ float $initialZ;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ TurbulenceNoiseShader $shader;
    public final /* synthetic */ TurbulenceNoiseView this$0;

    public /* synthetic */ TurbulenceNoiseView$play$1(TurbulenceNoiseShader turbulenceNoiseShader, float f, TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig, float f2, float f3, TurbulenceNoiseView turbulenceNoiseView, int i) {
        this.$r8$classId = i;
        this.$shader = turbulenceNoiseShader;
        this.$initialX = f;
        this.$config = turbulenceNoiseAnimationConfig;
        this.$initialY = f2;
        this.$initialZ = f3;
        this.this$0 = turbulenceNoiseView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                float currentPlayTime = valueAnimator.getCurrentPlayTime() * 0.001f;
                TurbulenceNoiseShader turbulenceNoiseShader = this.$shader;
                float f = this.$initialX;
                this.$config.getClass();
                turbulenceNoiseShader.setNoiseMove((0.42f * currentPlayTime) + f, (0.0f * currentPlayTime) + this.$initialY, (currentPlayTime * 0.3f) + this.$initialZ);
                TurbulenceNoiseShader turbulenceNoiseShader2 = this.$shader;
                this.$config.getClass();
                turbulenceNoiseShader2.setOpacity(1.0f);
                this.this$0.invalidate();
                break;
            case 1:
                float currentPlayTime2 = valueAnimator.getCurrentPlayTime() * 0.001f;
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TurbulenceNoiseShader turbulenceNoiseShader3 = this.$shader;
                float f2 = this.$initialX;
                this.$config.getClass();
                turbulenceNoiseShader3.setNoiseMove((0.42f * currentPlayTime2) + f2, (0.0f * currentPlayTime2) + this.$initialY, (currentPlayTime2 * 0.3f) + this.$initialZ);
                TurbulenceNoiseShader turbulenceNoiseShader4 = this.$shader;
                this.$config.getClass();
                turbulenceNoiseShader4.setOpacity(floatValue * 1.0f);
                this.this$0.invalidate();
                break;
            default:
                float currentPlayTime3 = valueAnimator.getCurrentPlayTime() * 0.001f;
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                TurbulenceNoiseShader turbulenceNoiseShader5 = this.$shader;
                float f3 = this.$initialX;
                this.$config.getClass();
                turbulenceNoiseShader5.setNoiseMove((0.42f * currentPlayTime3) + f3, (0.0f * currentPlayTime3) + this.$initialY, (currentPlayTime3 * 0.3f) + this.$initialZ);
                TurbulenceNoiseShader turbulenceNoiseShader6 = this.$shader;
                this.$config.getClass();
                turbulenceNoiseShader6.setOpacity((1.0f - floatValue2) * 1.0f);
                this.this$0.invalidate();
                break;
        }
    }
}
