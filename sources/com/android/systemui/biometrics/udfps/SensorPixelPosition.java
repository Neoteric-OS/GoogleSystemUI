package com.android.systemui.biometrics.udfps;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SensorPixelPosition {
    public static final /* synthetic */ SensorPixelPosition[] $VALUES;
    public static final SensorPixelPosition OUTSIDE;
    public static final SensorPixelPosition SENSOR;
    public static final SensorPixelPosition TARGET;

    static {
        SensorPixelPosition sensorPixelPosition = new SensorPixelPosition("OUTSIDE", 0);
        OUTSIDE = sensorPixelPosition;
        SensorPixelPosition sensorPixelPosition2 = new SensorPixelPosition("SENSOR", 1);
        SENSOR = sensorPixelPosition2;
        SensorPixelPosition sensorPixelPosition3 = new SensorPixelPosition("TARGET", 2);
        TARGET = sensorPixelPosition3;
        SensorPixelPosition[] sensorPixelPositionArr = {sensorPixelPosition, sensorPixelPosition2, sensorPixelPosition3};
        $VALUES = sensorPixelPositionArr;
        EnumEntriesKt.enumEntries(sensorPixelPositionArr);
    }

    public static SensorPixelPosition valueOf(String str) {
        return (SensorPixelPosition) Enum.valueOf(SensorPixelPosition.class, str);
    }

    public static SensorPixelPosition[] values() {
        return (SensorPixelPosition[]) $VALUES.clone();
    }
}
