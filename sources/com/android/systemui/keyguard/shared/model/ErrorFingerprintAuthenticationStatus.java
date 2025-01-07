package com.android.systemui.keyguard.shared.model;

import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ErrorFingerprintAuthenticationStatus extends FingerprintAuthenticationStatus {
    public final long createdAt;
    public final String msg;
    public final int msgId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ErrorFingerprintAuthenticationStatus(int i, String str) {
        super(Boolean.FALSE);
        long elapsedRealtime = SystemClock.elapsedRealtime();
        this.msgId = i;
        this.msg = str;
        this.createdAt = elapsedRealtime;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ErrorFingerprintAuthenticationStatus)) {
            return false;
        }
        ErrorFingerprintAuthenticationStatus errorFingerprintAuthenticationStatus = (ErrorFingerprintAuthenticationStatus) obj;
        return this.msgId == errorFingerprintAuthenticationStatus.msgId && Intrinsics.areEqual(this.msg, errorFingerprintAuthenticationStatus.msg) && this.createdAt == errorFingerprintAuthenticationStatus.createdAt;
    }

    public final int hashCode() {
        int hashCode = Integer.hashCode(this.msgId) * 31;
        String str = this.msg;
        return Long.hashCode(this.createdAt) + ((hashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ErrorFingerprintAuthenticationStatus(msgId=");
        sb.append(this.msgId);
        sb.append(", msg=");
        sb.append(this.msg);
        sb.append(", createdAt=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.createdAt, ")", sb);
    }
}
