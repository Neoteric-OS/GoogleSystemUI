package com.android.systemui.biometrics.shared.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FingerprintSensorTypeKt {
    public static final FingerprintSensorType toSensorType(int i) {
        if (i == 0) {
            return FingerprintSensorType.UNKNOWN;
        }
        if (i == 1) {
            return FingerprintSensorType.REAR;
        }
        if (i == 2) {
            return FingerprintSensorType.UDFPS_ULTRASONIC;
        }
        if (i == 3) {
            return FingerprintSensorType.UDFPS_OPTICAL;
        }
        if (i == 4) {
            return FingerprintSensorType.POWER_BUTTON;
        }
        if (i == 5) {
            return FingerprintSensorType.HOME_BUTTON;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid SensorType value: "));
    }
}
