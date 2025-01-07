package com.android.systemui.deviceentry.shared.model;

import android.support.v4.media.session.MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HelpFaceAuthenticationStatus extends FaceAuthenticationStatus {
    public final long createdAt;
    public final String msg;
    public final int msgId;

    public HelpFaceAuthenticationStatus(int i, long j, String str) {
        this.msgId = i;
        this.msg = str;
        this.createdAt = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HelpFaceAuthenticationStatus)) {
            return false;
        }
        HelpFaceAuthenticationStatus helpFaceAuthenticationStatus = (HelpFaceAuthenticationStatus) obj;
        return this.msgId == helpFaceAuthenticationStatus.msgId && Intrinsics.areEqual(this.msg, helpFaceAuthenticationStatus.msg) && this.createdAt == helpFaceAuthenticationStatus.createdAt;
    }

    public final int hashCode() {
        int hashCode = Integer.hashCode(this.msgId) * 31;
        String str = this.msg;
        return Long.hashCode(this.createdAt) + ((hashCode + (str == null ? 0 : str.hashCode())) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("HelpFaceAuthenticationStatus(msgId=");
        sb.append(this.msgId);
        sb.append(", msg=");
        sb.append(this.msg);
        sb.append(", createdAt=");
        return MediaSessionCompat$QueueItem$$ExternalSyntheticOutline0.m(this.createdAt, ")", sb);
    }
}
