package com.android.systemui.biometrics.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FingerprintSensorType {
    public static final /* synthetic */ FingerprintSensorType[] $VALUES;
    public static final FingerprintSensorType HOME_BUTTON;
    public static final FingerprintSensorType POWER_BUTTON;
    public static final FingerprintSensorType REAR;
    public static final FingerprintSensorType UDFPS_OPTICAL;
    public static final FingerprintSensorType UDFPS_ULTRASONIC;
    public static final FingerprintSensorType UNKNOWN;

    static {
        FingerprintSensorType fingerprintSensorType = new FingerprintSensorType("UNKNOWN", 0);
        UNKNOWN = fingerprintSensorType;
        FingerprintSensorType fingerprintSensorType2 = new FingerprintSensorType("REAR", 1);
        REAR = fingerprintSensorType2;
        FingerprintSensorType fingerprintSensorType3 = new FingerprintSensorType("UDFPS_ULTRASONIC", 2);
        UDFPS_ULTRASONIC = fingerprintSensorType3;
        FingerprintSensorType fingerprintSensorType4 = new FingerprintSensorType("UDFPS_OPTICAL", 3);
        UDFPS_OPTICAL = fingerprintSensorType4;
        FingerprintSensorType fingerprintSensorType5 = new FingerprintSensorType("POWER_BUTTON", 4);
        POWER_BUTTON = fingerprintSensorType5;
        FingerprintSensorType fingerprintSensorType6 = new FingerprintSensorType("HOME_BUTTON", 5);
        HOME_BUTTON = fingerprintSensorType6;
        FingerprintSensorType[] fingerprintSensorTypeArr = {fingerprintSensorType, fingerprintSensorType2, fingerprintSensorType3, fingerprintSensorType4, fingerprintSensorType5, fingerprintSensorType6};
        $VALUES = fingerprintSensorTypeArr;
        EnumEntriesKt.enumEntries(fingerprintSensorTypeArr);
    }

    public static FingerprintSensorType valueOf(String str) {
        return (FingerprintSensorType) Enum.valueOf(FingerprintSensorType.class, str);
    }

    public static FingerprintSensorType[] values() {
        return (FingerprintSensorType[]) $VALUES.clone();
    }

    public final boolean isUdfps() {
        return this == UDFPS_OPTICAL || this == UDFPS_ULTRASONIC;
    }
}
