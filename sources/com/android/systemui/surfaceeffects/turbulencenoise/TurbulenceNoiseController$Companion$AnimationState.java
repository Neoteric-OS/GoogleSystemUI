package com.android.systemui.surfaceeffects.turbulencenoise;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseController$Companion$AnimationState {
    public static final /* synthetic */ TurbulenceNoiseController$Companion$AnimationState[] $VALUES;
    public static final TurbulenceNoiseController$Companion$AnimationState EASE_OUT = null;
    public static final TurbulenceNoiseController$Companion$AnimationState MAIN = null;
    public static final TurbulenceNoiseController$Companion$AnimationState NOT_PLAYING;

    static {
        TurbulenceNoiseController$Companion$AnimationState turbulenceNoiseController$Companion$AnimationState = new TurbulenceNoiseController$Companion$AnimationState("EASE_IN", 0);
        TurbulenceNoiseController$Companion$AnimationState turbulenceNoiseController$Companion$AnimationState2 = new TurbulenceNoiseController$Companion$AnimationState("MAIN", 1);
        TurbulenceNoiseController$Companion$AnimationState turbulenceNoiseController$Companion$AnimationState3 = new TurbulenceNoiseController$Companion$AnimationState("EASE_OUT", 2);
        TurbulenceNoiseController$Companion$AnimationState turbulenceNoiseController$Companion$AnimationState4 = new TurbulenceNoiseController$Companion$AnimationState("NOT_PLAYING", 3);
        NOT_PLAYING = turbulenceNoiseController$Companion$AnimationState4;
        TurbulenceNoiseController$Companion$AnimationState[] turbulenceNoiseController$Companion$AnimationStateArr = {turbulenceNoiseController$Companion$AnimationState, turbulenceNoiseController$Companion$AnimationState2, turbulenceNoiseController$Companion$AnimationState3, turbulenceNoiseController$Companion$AnimationState4};
        $VALUES = turbulenceNoiseController$Companion$AnimationStateArr;
        EnumEntriesKt.enumEntries(turbulenceNoiseController$Companion$AnimationStateArr);
    }

    public static TurbulenceNoiseController$Companion$AnimationState valueOf(String str) {
        return (TurbulenceNoiseController$Companion$AnimationState) Enum.valueOf(TurbulenceNoiseController$Companion$AnimationState.class, str);
    }

    public static TurbulenceNoiseController$Companion$AnimationState[] values() {
        return (TurbulenceNoiseController$Companion$AnimationState[]) $VALUES.clone();
    }
}
