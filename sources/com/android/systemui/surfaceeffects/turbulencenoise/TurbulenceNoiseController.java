package com.android.systemui.surfaceeffects.turbulencenoise;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseController {
    public final TurbulenceNoiseController$Companion$AnimationState state = TurbulenceNoiseController$Companion$AnimationState.NOT_PLAYING;
    public final TurbulenceNoiseView turbulenceNoiseView;

    public TurbulenceNoiseController(TurbulenceNoiseView turbulenceNoiseView) {
        this.turbulenceNoiseView = turbulenceNoiseView;
        turbulenceNoiseView.setVisibility(4);
    }

    public static /* synthetic */ void getState$annotations() {
    }
}
