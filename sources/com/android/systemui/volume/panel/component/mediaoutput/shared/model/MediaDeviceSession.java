package com.android.systemui.volume.panel.component.mediaoutput.shared.model;

import android.media.session.MediaSession;
import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MediaDeviceSession {
    public final CharSequence appLabel;
    public final boolean canAdjustVolume;
    public final String packageName;
    public final MediaSession.Token sessionToken;

    public MediaDeviceSession(CharSequence charSequence, String str, MediaSession.Token token, boolean z) {
        this.appLabel = charSequence;
        this.packageName = str;
        this.sessionToken = token;
        this.canAdjustVolume = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaDeviceSession)) {
            return false;
        }
        MediaDeviceSession mediaDeviceSession = (MediaDeviceSession) obj;
        return Intrinsics.areEqual(this.appLabel, mediaDeviceSession.appLabel) && Intrinsics.areEqual(this.packageName, mediaDeviceSession.packageName) && Intrinsics.areEqual(this.sessionToken, mediaDeviceSession.sessionToken) && this.canAdjustVolume == mediaDeviceSession.canAdjustVolume;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.canAdjustVolume) + ((this.sessionToken.hashCode() + PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.packageName, this.appLabel.hashCode() * 31, 31)) * 31);
    }

    public final String toString() {
        CharSequence charSequence = this.appLabel;
        return "MediaDeviceSession(appLabel=" + ((Object) charSequence) + ", packageName=" + this.packageName + ", sessionToken=" + this.sessionToken + ", canAdjustVolume=" + this.canAdjustVolume + ")";
    }
}
