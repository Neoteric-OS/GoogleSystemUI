package com.android.systemui.deviceentry.shared.model;

import android.os.SystemClock;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FailedFaceAuthenticationStatus extends FaceAuthenticationStatus {
    public final long createdAt = SystemClock.elapsedRealtime();

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FailedFaceAuthenticationStatus) && this.createdAt == ((FailedFaceAuthenticationStatus) obj).createdAt;
    }

    public final int hashCode() {
        return Long.hashCode(this.createdAt);
    }

    public final String toString() {
        return "FailedFaceAuthenticationStatus(createdAt=" + this.createdAt + ")";
    }
}
