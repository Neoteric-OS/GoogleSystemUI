package com.android.systemui.biometrics.shared.model;

import android.hardware.face.FaceSensorPropertiesInternal;
import android.hardware.fingerprint.FingerprintSensorPropertiesInternal;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricModalities {
    public final FaceSensorPropertiesInternal faceProperties;
    public final FingerprintSensorPropertiesInternal fingerprintProperties;

    public BiometricModalities(FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal, FaceSensorPropertiesInternal faceSensorPropertiesInternal) {
        this.fingerprintProperties = fingerprintSensorPropertiesInternal;
        this.faceProperties = faceSensorPropertiesInternal;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BiometricModalities)) {
            return false;
        }
        BiometricModalities biometricModalities = (BiometricModalities) obj;
        return Intrinsics.areEqual(this.fingerprintProperties, biometricModalities.fingerprintProperties) && Intrinsics.areEqual(this.faceProperties, biometricModalities.faceProperties);
    }

    public final boolean getHasFaceAndFingerprint() {
        return getHasFingerprint() && this.faceProperties != null;
    }

    public final boolean getHasFaceOnly() {
        return (this.faceProperties == null || getHasFingerprint()) ? false : true;
    }

    public final boolean getHasFingerprint() {
        return this.fingerprintProperties != null;
    }

    public final boolean getHasSfps() {
        if (getHasFingerprint()) {
            FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal = this.fingerprintProperties;
            Intrinsics.checkNotNull(fingerprintSensorPropertiesInternal);
            if (fingerprintSensorPropertiesInternal.isAnySidefpsType()) {
                return true;
            }
        }
        return false;
    }

    public final boolean getHasUdfps() {
        if (getHasFingerprint()) {
            FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal = this.fingerprintProperties;
            Intrinsics.checkNotNull(fingerprintSensorPropertiesInternal);
            if (fingerprintSensorPropertiesInternal.isAnyUdfpsType()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        FingerprintSensorPropertiesInternal fingerprintSensorPropertiesInternal = this.fingerprintProperties;
        int hashCode = (fingerprintSensorPropertiesInternal == null ? 0 : fingerprintSensorPropertiesInternal.hashCode()) * 31;
        FaceSensorPropertiesInternal faceSensorPropertiesInternal = this.faceProperties;
        return hashCode + (faceSensorPropertiesInternal != null ? faceSensorPropertiesInternal.hashCode() : 0);
    }

    public final String toString() {
        return "BiometricModalities(fingerprintProperties=" + this.fingerprintProperties + ", faceProperties=" + this.faceProperties + ")";
    }
}
