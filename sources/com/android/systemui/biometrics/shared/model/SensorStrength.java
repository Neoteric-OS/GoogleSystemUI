package com.android.systemui.biometrics.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SensorStrength {
    public static final /* synthetic */ SensorStrength[] $VALUES;
    public static final SensorStrength CONVENIENCE;
    public static final SensorStrength STRONG;
    public static final SensorStrength WEAK;

    static {
        SensorStrength sensorStrength = new SensorStrength("CONVENIENCE", 0);
        CONVENIENCE = sensorStrength;
        SensorStrength sensorStrength2 = new SensorStrength("WEAK", 1);
        WEAK = sensorStrength2;
        SensorStrength sensorStrength3 = new SensorStrength("STRONG", 2);
        STRONG = sensorStrength3;
        SensorStrength[] sensorStrengthArr = {sensorStrength, sensorStrength2, sensorStrength3};
        $VALUES = sensorStrengthArr;
        EnumEntriesKt.enumEntries(sensorStrengthArr);
    }

    public static SensorStrength valueOf(String str) {
        return (SensorStrength) Enum.valueOf(SensorStrength.class, str);
    }

    public static SensorStrength[] values() {
        return (SensorStrength[]) $VALUES.clone();
    }
}
