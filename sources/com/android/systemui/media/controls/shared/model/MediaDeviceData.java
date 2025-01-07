package com.android.systemui.media.controls.shared.model;

import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaDeviceData {
    public final boolean enabled;
    public final Drawable icon;
    public final String id;
    public final PendingIntent intent;
    public final CharSequence name;
    public final boolean showBroadcastButton;

    public MediaDeviceData(boolean z, Drawable drawable, CharSequence charSequence, PendingIntent pendingIntent, String str, boolean z2) {
        this.enabled = z;
        this.icon = drawable;
        this.name = charSequence;
        this.intent = pendingIntent;
        this.id = str;
        this.showBroadcastButton = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaDeviceData)) {
            return false;
        }
        MediaDeviceData mediaDeviceData = (MediaDeviceData) obj;
        return this.enabled == mediaDeviceData.enabled && Intrinsics.areEqual(this.icon, mediaDeviceData.icon) && Intrinsics.areEqual(this.name, mediaDeviceData.name) && Intrinsics.areEqual(this.intent, mediaDeviceData.intent) && Intrinsics.areEqual(this.id, mediaDeviceData.id) && this.showBroadcastButton == mediaDeviceData.showBroadcastButton;
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.enabled) * 31;
        Drawable drawable = this.icon;
        int hashCode2 = (hashCode + (drawable == null ? 0 : drawable.hashCode())) * 31;
        CharSequence charSequence = this.name;
        int hashCode3 = (hashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        PendingIntent pendingIntent = this.intent;
        int hashCode4 = (hashCode3 + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
        String str = this.id;
        return Boolean.hashCode(this.showBroadcastButton) + ((hashCode4 + (str != null ? str.hashCode() : 0)) * 31);
    }

    public final String toString() {
        Drawable drawable = this.icon;
        CharSequence charSequence = this.name;
        return "MediaDeviceData(enabled=" + this.enabled + ", icon=" + drawable + ", name=" + ((Object) charSequence) + ", intent=" + this.intent + ", id=" + this.id + ", showBroadcastButton=" + this.showBroadcastButton + ")";
    }

    public /* synthetic */ MediaDeviceData(boolean z, Drawable drawable, CharSequence charSequence, PendingIntent pendingIntent, String str, boolean z2, int i) {
        this(z, drawable, charSequence, (i & 8) != 0 ? null : pendingIntent, (i & 16) != 0 ? null : str, z2);
    }
}
