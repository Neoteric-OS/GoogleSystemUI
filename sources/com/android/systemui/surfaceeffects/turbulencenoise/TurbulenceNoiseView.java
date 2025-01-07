package com.android.systemui.surfaceeffects.turbulencenoise;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseView extends View {
    public ValueAnimator currentAnimator;
    public TurbulenceNoiseAnimationConfig noiseConfig;
    public final Paint paint;
    public TurbulenceNoiseShader turbulenceNoiseShader;

    public TurbulenceNoiseView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.paint = new Paint();
    }

    public final void finish(Runnable runnable) {
        ValueAnimator valueAnimator = this.currentAnimator;
        if (valueAnimator != null) {
            valueAnimator.pause();
        }
        this.currentAnimator = null;
        if (runnable != null) {
            runnable.run();
        }
    }

    public final void initShader(TurbulenceNoiseShader$Companion$Type turbulenceNoiseShader$Companion$Type, TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig) {
        this.noiseConfig = turbulenceNoiseAnimationConfig;
        TurbulenceNoiseShader turbulenceNoiseShader = this.turbulenceNoiseShader;
        if (turbulenceNoiseShader == null || turbulenceNoiseShader.baseType != turbulenceNoiseShader$Companion$Type) {
            TurbulenceNoiseShader turbulenceNoiseShader2 = new TurbulenceNoiseShader(turbulenceNoiseShader$Companion$Type);
            this.turbulenceNoiseShader = turbulenceNoiseShader2;
            this.paint.setShader(turbulenceNoiseShader2);
        }
        TurbulenceNoiseShader turbulenceNoiseShader3 = this.turbulenceNoiseShader;
        Intrinsics.checkNotNull(turbulenceNoiseShader3);
        turbulenceNoiseShader3.applyConfig(turbulenceNoiseAnimationConfig);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        if (canvas.isHardwareAccelerated()) {
            canvas.drawPaint(this.paint);
        }
    }

    public final void play(Runnable runnable) {
        TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig = this.noiseConfig;
        if (turbulenceNoiseAnimationConfig == null) {
            return;
        }
        TurbulenceNoiseShader turbulenceNoiseShader = this.turbulenceNoiseShader;
        Intrinsics.checkNotNull(turbulenceNoiseShader);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration((long) 30000.0f);
        ofFloat.addUpdateListener(new TurbulenceNoiseView$play$1(turbulenceNoiseShader, turbulenceNoiseShader.noiseOffsetX, turbulenceNoiseAnimationConfig, turbulenceNoiseShader.noiseOffsetY, turbulenceNoiseShader.noiseOffsetZ, this, 0));
        ofFloat.addListener(new TurbulenceNoiseView$play$2(this, runnable, 0));
        ofFloat.start();
        this.currentAnimator = ofFloat;
    }

    public final void playEaseIn(Runnable runnable) {
        TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig = this.noiseConfig;
        if (turbulenceNoiseAnimationConfig == null) {
            return;
        }
        TurbulenceNoiseShader turbulenceNoiseShader = this.turbulenceNoiseShader;
        Intrinsics.checkNotNull(turbulenceNoiseShader);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration((long) 1350.0f);
        ofFloat.addUpdateListener(new TurbulenceNoiseView$play$1(turbulenceNoiseShader, turbulenceNoiseShader.noiseOffsetX, turbulenceNoiseAnimationConfig, turbulenceNoiseShader.noiseOffsetY, turbulenceNoiseShader.noiseOffsetZ, this, 1));
        ofFloat.addListener(new TurbulenceNoiseView$play$2(this, runnable, 1));
        ofFloat.start();
        this.currentAnimator = ofFloat;
    }

    public final void playEaseOut(Runnable runnable) {
        TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig = this.noiseConfig;
        if (turbulenceNoiseAnimationConfig == null) {
            return;
        }
        TurbulenceNoiseShader turbulenceNoiseShader = this.turbulenceNoiseShader;
        Intrinsics.checkNotNull(turbulenceNoiseShader);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        ofFloat.setDuration((long) 1350.0f);
        ofFloat.addUpdateListener(new TurbulenceNoiseView$play$1(turbulenceNoiseShader, turbulenceNoiseShader.noiseOffsetX, turbulenceNoiseAnimationConfig, turbulenceNoiseShader.noiseOffsetY, turbulenceNoiseShader.noiseOffsetZ, this, 2));
        ofFloat.addListener(new TurbulenceNoiseView$play$2(this, runnable, 2));
        ofFloat.start();
        this.currentAnimator = ofFloat;
    }

    public static /* synthetic */ void getCurrentAnimator$annotations() {
    }

    public static /* synthetic */ void getNoiseConfig$annotations() {
    }

    public static /* synthetic */ void getTurbulenceNoiseShader$annotations() {
    }
}
