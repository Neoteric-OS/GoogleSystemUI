package com.android.systemui.keyguard.shared.model;

import android.hardware.biometrics.BiometricSourceType;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricUnlockSource {
    public static final /* synthetic */ BiometricUnlockSource[] $VALUES;
    public static final Companion Companion;
    public static final BiometricUnlockSource FACE_SENSOR;
    public static final BiometricUnlockSource FINGERPRINT_SENSOR;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public abstract /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[BiometricSourceType.values().length];
                try {
                    iArr[BiometricSourceType.FINGERPRINT.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[BiometricSourceType.FACE.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[BiometricSourceType.IRIS.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public static BiometricUnlockSource fromBiometricSourceType(BiometricSourceType biometricSourceType) {
            int i = biometricSourceType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[biometricSourceType.ordinal()];
            if (i == 1) {
                return BiometricUnlockSource.FINGERPRINT_SENSOR;
            }
            if (i == 2) {
                return BiometricUnlockSource.FACE_SENSOR;
            }
            if (i != 3) {
                return null;
            }
            return BiometricUnlockSource.FACE_SENSOR;
        }
    }

    static {
        BiometricUnlockSource biometricUnlockSource = new BiometricUnlockSource("FINGERPRINT_SENSOR", 0);
        FINGERPRINT_SENSOR = biometricUnlockSource;
        BiometricUnlockSource biometricUnlockSource2 = new BiometricUnlockSource("FACE_SENSOR", 1);
        FACE_SENSOR = biometricUnlockSource2;
        BiometricUnlockSource[] biometricUnlockSourceArr = {biometricUnlockSource, biometricUnlockSource2};
        $VALUES = biometricUnlockSourceArr;
        EnumEntriesKt.enumEntries(biometricUnlockSourceArr);
        Companion = new Companion();
    }

    public static BiometricUnlockSource valueOf(String str) {
        return (BiometricUnlockSource) Enum.valueOf(BiometricUnlockSource.class, str);
    }

    public static BiometricUnlockSource[] values() {
        return (BiometricUnlockSource[]) $VALUES.clone();
    }
}
