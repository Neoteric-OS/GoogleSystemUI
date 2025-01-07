package com.android.systemui.biometrics.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BiometricModalityKt {
    public static final BiometricModality asBiometricModality(int i) {
        return i != 2 ? i != 8 ? BiometricModality.None : BiometricModality.Face : BiometricModality.Fingerprint;
    }
}
