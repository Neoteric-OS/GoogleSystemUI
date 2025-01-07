package com.android.systemui.surfaceeffects.loadingeffect;

import android.animation.ValueAnimator;
import android.graphics.Paint;
import com.android.systemui.media.controls.ui.controller.MediaControlPanel;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseAnimationConfig;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader;
import com.android.systemui.surfaceeffects.turbulencenoise.TurbulenceNoiseShader$Companion$Type;
import kotlin.enums.EnumEntriesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LoadingEffect {
    public final MediaControlPanel.AnonymousClass1 animationStateChangedCallback;
    public final TurbulenceNoiseAnimationConfig config;
    public ValueAnimator currentAnimator;
    public final Paint paint;
    public final MediaControlPanel.AnonymousClass1 paintCallback;
    public AnimationState state;
    public final TurbulenceNoiseShader turbulenceNoiseShader;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class AnimationState {
        public static final /* synthetic */ AnimationState[] $VALUES;
        public static final AnimationState EASE_IN;
        public static final AnimationState EASE_OUT;
        public static final AnimationState MAIN;
        public static final AnimationState NOT_PLAYING;

        static {
            AnimationState animationState = new AnimationState("EASE_IN", 0);
            EASE_IN = animationState;
            AnimationState animationState2 = new AnimationState("MAIN", 1);
            MAIN = animationState2;
            AnimationState animationState3 = new AnimationState("EASE_OUT", 2);
            EASE_OUT = animationState3;
            AnimationState animationState4 = new AnimationState("NOT_PLAYING", 3);
            NOT_PLAYING = animationState4;
            AnimationState[] animationStateArr = {animationState, animationState2, animationState3, animationState4};
            $VALUES = animationStateArr;
            EnumEntriesKt.enumEntries(animationStateArr);
        }

        public static AnimationState valueOf(String str) {
            return (AnimationState) Enum.valueOf(AnimationState.class, str);
        }

        public static AnimationState[] values() {
            return (AnimationState[]) $VALUES.clone();
        }
    }

    public LoadingEffect(TurbulenceNoiseAnimationConfig turbulenceNoiseAnimationConfig, MediaControlPanel.AnonymousClass1 anonymousClass1, MediaControlPanel.AnonymousClass1 anonymousClass12) {
        TurbulenceNoiseShader$Companion$Type turbulenceNoiseShader$Companion$Type = TurbulenceNoiseShader$Companion$Type.SIMPLEX_NOISE;
        this.config = turbulenceNoiseAnimationConfig;
        this.paintCallback = anonymousClass1;
        this.animationStateChangedCallback = anonymousClass12;
        TurbulenceNoiseShader turbulenceNoiseShader = new TurbulenceNoiseShader(turbulenceNoiseShader$Companion$Type);
        turbulenceNoiseShader.applyConfig(turbulenceNoiseAnimationConfig);
        this.turbulenceNoiseShader = turbulenceNoiseShader;
        this.state = AnimationState.NOT_PLAYING;
        Paint paint = new Paint();
        paint.setShader(turbulenceNoiseShader);
        this.paint = paint;
    }

    public final void playEaseOut() {
        if (this.state != AnimationState.MAIN) {
            return;
        }
        setState(AnimationState.EASE_OUT);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        this.config.getClass();
        ofFloat.setDuration((long) 1350.0f);
        TurbulenceNoiseShader turbulenceNoiseShader = this.turbulenceNoiseShader;
        ofFloat.addUpdateListener(new LoadingEffect$playMain$1(this, turbulenceNoiseShader.noiseOffsetX, turbulenceNoiseShader.noiseOffsetY, turbulenceNoiseShader.noiseOffsetZ, 2));
        ofFloat.addListener(new LoadingEffect$playMain$2(this, 2));
        ofFloat.start();
        this.currentAnimator = ofFloat;
    }

    public final void setState(AnimationState animationState) {
        if (this.state != animationState) {
            LoadingEffectView loadingEffectView = MediaControlPanel.this.mMediaViewHolder.loadingEffectView;
            if (animationState == AnimationState.NOT_PLAYING) {
                loadingEffectView.setVisibility(4);
            } else {
                loadingEffectView.setVisibility(0);
            }
            this.state = animationState;
        }
    }
}
