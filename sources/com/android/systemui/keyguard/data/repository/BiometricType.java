package com.android.systemui.keyguard.data.repository;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricType {
    public static final /* synthetic */ BiometricType[] $VALUES;
    public static final BiometricType FACE;
    public static final BiometricType REAR_FINGERPRINT;
    public static final BiometricType SIDE_FINGERPRINT;
    public static final BiometricType UNDER_DISPLAY_FINGERPRINT;
    public static final BiometricType UNKNOWN;
    private final boolean isFingerprint;

    static {
        BiometricType biometricType = new BiometricType(0, "UNKNOWN", false);
        UNKNOWN = biometricType;
        BiometricType biometricType2 = new BiometricType(1, "REAR_FINGERPRINT", true);
        REAR_FINGERPRINT = biometricType2;
        BiometricType biometricType3 = new BiometricType(2, "UNDER_DISPLAY_FINGERPRINT", true);
        UNDER_DISPLAY_FINGERPRINT = biometricType3;
        BiometricType biometricType4 = new BiometricType(3, "SIDE_FINGERPRINT", true);
        SIDE_FINGERPRINT = biometricType4;
        BiometricType biometricType5 = new BiometricType(4, "FACE", false);
        FACE = biometricType5;
        BiometricType[] biometricTypeArr = {biometricType, biometricType2, biometricType3, biometricType4, biometricType5};
        $VALUES = biometricTypeArr;
        EnumEntriesKt.enumEntries(biometricTypeArr);
    }

    public BiometricType(int i, String str, boolean z) {
        this.isFingerprint = z;
    }

    public static BiometricType valueOf(String str) {
        return (BiometricType) Enum.valueOf(BiometricType.class, str);
    }

    public static BiometricType[] values() {
        return (BiometricType[]) $VALUES.clone();
    }

    public final boolean isFingerprint() {
        return this.isFingerprint;
    }
}
