package com.android.systemui.biometrics.shared.model;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SensorStrengthKt {
    public static final SensorStrength toSensorStrength(int i) {
        if (i == 0) {
            return SensorStrength.CONVENIENCE;
        }
        if (i == 1) {
            return SensorStrength.WEAK;
        }
        if (i == 2) {
            return SensorStrength.STRONG;
        }
        throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "Invalid SensorStrength value: "));
    }
}
