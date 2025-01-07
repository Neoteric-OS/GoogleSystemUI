package com.android.systemui.surfaceeffects.turbulencenoise;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class TurbulenceNoiseShader$Companion$Type {
    public static final /* synthetic */ TurbulenceNoiseShader$Companion$Type[] $VALUES;
    public static final TurbulenceNoiseShader$Companion$Type SIMPLEX_NOISE;

    static {
        TurbulenceNoiseShader$Companion$Type turbulenceNoiseShader$Companion$Type = new TurbulenceNoiseShader$Companion$Type("SIMPLEX_NOISE", 0);
        SIMPLEX_NOISE = turbulenceNoiseShader$Companion$Type;
        TurbulenceNoiseShader$Companion$Type[] turbulenceNoiseShader$Companion$TypeArr = {turbulenceNoiseShader$Companion$Type, new TurbulenceNoiseShader$Companion$Type("SIMPLEX_NOISE_FRACTAL", 1), new TurbulenceNoiseShader$Companion$Type("SIMPLEX_NOISE_SPARKLE", 2)};
        $VALUES = turbulenceNoiseShader$Companion$TypeArr;
        EnumEntriesKt.enumEntries(turbulenceNoiseShader$Companion$TypeArr);
    }

    public static TurbulenceNoiseShader$Companion$Type valueOf(String str) {
        return (TurbulenceNoiseShader$Companion$Type) Enum.valueOf(TurbulenceNoiseShader$Companion$Type.class, str);
    }

    public static TurbulenceNoiseShader$Companion$Type[] values() {
        return (TurbulenceNoiseShader$Companion$Type[]) $VALUES.clone();
    }
}
