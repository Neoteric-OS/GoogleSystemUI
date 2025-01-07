package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SuccessFingerprintAuthenticationStatus extends FingerprintAuthenticationStatus {
    public final boolean isStrongBiometric;
    public final int userId;

    public SuccessFingerprintAuthenticationStatus(int i, boolean z) {
        super(Boolean.FALSE);
        this.userId = i;
        this.isStrongBiometric = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuccessFingerprintAuthenticationStatus)) {
            return false;
        }
        SuccessFingerprintAuthenticationStatus successFingerprintAuthenticationStatus = (SuccessFingerprintAuthenticationStatus) obj;
        return this.userId == successFingerprintAuthenticationStatus.userId && this.isStrongBiometric == successFingerprintAuthenticationStatus.isStrongBiometric;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isStrongBiometric) + (Integer.hashCode(this.userId) * 31);
    }

    public final String toString() {
        return "SuccessFingerprintAuthenticationStatus(userId=" + this.userId + ", isStrongBiometric=" + this.isStrongBiometric + ")";
    }
}
