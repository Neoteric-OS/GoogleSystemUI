package com.android.systemui.volume.panel.component.mediaoutput.domain.model;

import com.android.systemui.volume.panel.component.mediaoutput.shared.model.MediaDeviceSession;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaDeviceSessions {
    public final MediaDeviceSession local;
    public final MediaDeviceSession remote;

    public MediaDeviceSessions(MediaDeviceSession mediaDeviceSession, MediaDeviceSession mediaDeviceSession2) {
        this.local = mediaDeviceSession;
        this.remote = mediaDeviceSession2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaDeviceSessions)) {
            return false;
        }
        MediaDeviceSessions mediaDeviceSessions = (MediaDeviceSessions) obj;
        return Intrinsics.areEqual(this.local, mediaDeviceSessions.local) && Intrinsics.areEqual(this.remote, mediaDeviceSessions.remote);
    }

    public final int hashCode() {
        MediaDeviceSession mediaDeviceSession = this.local;
        int hashCode = (mediaDeviceSession == null ? 0 : mediaDeviceSession.hashCode()) * 31;
        MediaDeviceSession mediaDeviceSession2 = this.remote;
        return hashCode + (mediaDeviceSession2 != null ? mediaDeviceSession2.hashCode() : 0);
    }

    public final String toString() {
        return "MediaDeviceSessions(local=" + this.local + ", remote=" + this.remote + ")";
    }
}
