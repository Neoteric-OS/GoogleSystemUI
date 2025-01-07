package com.android.systemui.deviceentry.shared.model;

import android.os.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AcquiredFaceAuthenticationStatus extends FaceAuthenticationStatus {
    public final int acquiredInfo;
    public final long createdAt;

    public AcquiredFaceAuthenticationStatus(int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.acquiredInfo = i;
        this.createdAt = elapsedRealtime;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AcquiredFaceAuthenticationStatus)) {
            return false;
        }
        AcquiredFaceAuthenticationStatus acquiredFaceAuthenticationStatus = (AcquiredFaceAuthenticationStatus) obj;
        return this.acquiredInfo == acquiredFaceAuthenticationStatus.acquiredInfo && this.createdAt == acquiredFaceAuthenticationStatus.createdAt;
    }

    public final int hashCode() {
        return Long.hashCode(this.createdAt) + (Integer.hashCode(this.acquiredInfo) * 31);
    }

    public final String toString() {
        return "AcquiredFaceAuthenticationStatus(acquiredInfo=" + this.acquiredInfo + ", createdAt=" + this.createdAt + ")";
    }
}
