package com.android.systemui.biometrics.domain.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricOperationInfo {
    public final long gatekeeperChallenge;

    public BiometricOperationInfo(long j) {
        this.gatekeeperChallenge = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BiometricOperationInfo) && this.gatekeeperChallenge == ((BiometricOperationInfo) obj).gatekeeperChallenge;
    }

    public final int hashCode() {
        return Long.hashCode(this.gatekeeperChallenge);
    }

    public final String toString() {
        return "BiometricOperationInfo(gatekeeperChallenge=" + this.gatekeeperChallenge + ")";
    }
}
