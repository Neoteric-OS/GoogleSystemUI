package com.android.systemui.keyguard.shared.model;

import com.android.systemui.biometrics.shared.model.AuthenticationReason;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AcquiredFingerprintAuthenticationStatus extends FingerprintAuthenticationStatus {
    public final int acquiredInfo;
    public final AuthenticationReason authenticationReason;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public AcquiredFingerprintAuthenticationStatus(com.android.systemui.biometrics.shared.model.AuthenticationReason r2, int r3) {
        /*
            r1 = this;
            r0 = 7
            if (r3 == 0) goto Lf
            if (r3 == r0) goto Lc
            r0 = 8
            if (r3 == r0) goto Lf
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            goto L10
        Lc:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            goto L10
        Lf:
            r0 = 0
        L10:
            r1.<init>(r0)
            r1.authenticationReason = r2
            r1.acquiredInfo = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.shared.model.AcquiredFingerprintAuthenticationStatus.<init>(com.android.systemui.biometrics.shared.model.AuthenticationReason, int):void");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AcquiredFingerprintAuthenticationStatus)) {
            return false;
        }
        AcquiredFingerprintAuthenticationStatus acquiredFingerprintAuthenticationStatus = (AcquiredFingerprintAuthenticationStatus) obj;
        return Intrinsics.areEqual(this.authenticationReason, acquiredFingerprintAuthenticationStatus.authenticationReason) && this.acquiredInfo == acquiredFingerprintAuthenticationStatus.acquiredInfo;
    }

    public final int hashCode() {
        return Integer.hashCode(this.acquiredInfo) + (this.authenticationReason.hashCode() * 31);
    }

    public final String toString() {
        return "AcquiredFingerprintAuthenticationStatus(authenticationReason=" + this.authenticationReason + ", acquiredInfo=" + this.acquiredInfo + ")";
    }
}
