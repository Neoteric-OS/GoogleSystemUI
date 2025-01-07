package com.android.systemui.deviceentry.shared.model;

import android.hardware.face.FaceManager;
import android.os.SystemClock;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SuccessFaceAuthenticationStatus extends FaceAuthenticationStatus {
    public final long createdAt;
    public final FaceManager.AuthenticationResult successResult;

    public SuccessFaceAuthenticationStatus(FaceManager.AuthenticationResult authenticationResult) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.successResult = authenticationResult;
        this.createdAt = elapsedRealtime;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuccessFaceAuthenticationStatus)) {
            return false;
        }
        SuccessFaceAuthenticationStatus successFaceAuthenticationStatus = (SuccessFaceAuthenticationStatus) obj;
        return Intrinsics.areEqual(this.successResult, successFaceAuthenticationStatus.successResult) && this.createdAt == successFaceAuthenticationStatus.createdAt;
    }

    public final int hashCode() {
        return Long.hashCode(this.createdAt) + (this.successResult.hashCode() * 31);
    }

    public final String toString() {
        return "SuccessFaceAuthenticationStatus(successResult=" + this.successResult + ", createdAt=" + this.createdAt + ")";
    }
}
