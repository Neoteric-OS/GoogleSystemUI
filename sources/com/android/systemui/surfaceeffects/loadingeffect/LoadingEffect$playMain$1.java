package com.android.systemui.surfaceeffects.loadingeffect;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import com.android.systemui.media.controls.ui.controller.MediaControlPanel;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LoadingEffect$playMain$1 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ float $initialX;
    public final /* synthetic */ float $initialY;
    public final /* synthetic */ float $initialZ;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ LoadingEffect this$0;

    public /* synthetic */ LoadingEffect$playMain$1(LoadingEffect loadingEffect, float f, float f2, float f3, int i) {
        this.$r8$classId = i;
        this.this$0 = loadingEffect;
        this.$initialX = f;
        this.$initialY = f2;
        this.$initialZ = f3;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.$r8$classId) {
            case 0:
                float currentPlayTime = valueAnimator.getCurrentPlayTime() * 0.001f;
                LoadingEffect loadingEffect = this.this$0;
                TurbulenceNoiseShader turbulenceNoiseShader = loadingEffect.turbulenceNoiseShader;
                float f = this.$initialX;
                loadingEffect.config.getClass();
                turbulenceNoiseShader.setNoiseMove((0.42f * currentPlayTime) + f, (0.0f * currentPlayTime) + this.$initialY, (currentPlayTime * 0.3f) + this.$initialZ);
                LoadingEffect loadingEffect2 = this.this$0;
                MediaControlPanel.AnonymousClass1 anonymousClass1 = loadingEffect2.paintCallback;
                Paint paint = loadingEffect2.paint;
                Intrinsics.checkNotNull(paint);
                anonymousClass1.onDraw(paint);
                break;
            case 1:
                float currentPlayTime2 = valueAnimator.getCurrentPlayTime() * 0.001f;
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoadingEffect loadingEffect3 = this.this$0;
                TurbulenceNoiseShader turbulenceNoiseShader2 = loadingEffect3.turbulenceNoiseShader;
                float f2 = this.$initialX;
                loadingEffect3.config.getClass();
                turbulenceNoiseShader2.setNoiseMove((0.42f * currentPlayTime2) + f2, (0.0f * currentPlayTime2) + this.$initialY, (currentPlayTime2 * 0.3f) + this.$initialZ);
                LoadingEffect loadingEffect4 = this.this$0;
                TurbulenceNoiseShader turbulenceNoiseShader3 = loadingEffect4.turbulenceNoiseShader;
                loadingEffect4.config.getClass();
                turbulenceNoiseShader3.setOpacity(floatValue * 1.0f);
                LoadingEffect loadingEffect5 = this.this$0;
                MediaControlPanel.AnonymousClass1 anonymousClass12 = loadingEffect5.paintCallback;
                Paint paint2 = loadingEffect5.paint;
                Intrinsics.checkNotNull(paint2);
                anonymousClass12.onDraw(paint2);
                break;
            default:
                float currentPlayTime3 = valueAnimator.getCurrentPlayTime() * 0.001f;
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                LoadingEffect loadingEffect6 = this.this$0;
                TurbulenceNoiseShader turbulenceNoiseShader4 = loadingEffect6.turbulenceNoiseShader;
                float f3 = this.$initialX;
                loadingEffect6.config.getClass();
                turbulenceNoiseShader4.setNoiseMove((0.42f * currentPlayTime3) + f3, (0.0f * currentPlayTime3) + this.$initialY, (currentPlayTime3 * 0.3f) + this.$initialZ);
                LoadingEffect loadingEffect7 = this.this$0;
                TurbulenceNoiseShader turbulenceNoiseShader5 = loadingEffect7.turbulenceNoiseShader;
                loadingEffect7.config.getClass();
                turbulenceNoiseShader5.setOpacity((1.0f - floatValue2) * 1.0f);
                LoadingEffect loadingEffect8 = this.this$0;
                MediaControlPanel.AnonymousClass1 anonymousClass13 = loadingEffect8.paintCallback;
                Paint paint3 = loadingEffect8.paint;
                Intrinsics.checkNotNull(paint3);
                anonymousClass13.onDraw(paint3);
                break;
        }
    }
}
