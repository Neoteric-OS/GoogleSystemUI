package com.android.systemui.keyguard.shared.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FailFingerprintAuthenticationStatus extends FingerprintAuthenticationStatus {
    public static final FailFingerprintAuthenticationStatus INSTANCE = new FailFingerprintAuthenticationStatus(Boolean.FALSE);

    public final boolean equals(Object obj) {
        return this == obj || (obj instanceof FailFingerprintAuthenticationStatus);
    }

    public final int hashCode() {
        return -1516498837;
    }

    public final String toString() {
        return "FailFingerprintAuthenticationStatus";
    }
}
