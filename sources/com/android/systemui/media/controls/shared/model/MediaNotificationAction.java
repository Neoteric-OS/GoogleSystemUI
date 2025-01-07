package com.android.systemui.media.controls.shared.model;

import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaNotificationAction {
    public final PendingIntent actionIntent;
    public final CharSequence contentDescription;
    public final Drawable icon;
    public final boolean isAuthenticationRequired;

    public MediaNotificationAction(boolean z, PendingIntent pendingIntent, Drawable drawable, CharSequence charSequence) {
        this.isAuthenticationRequired = z;
        this.actionIntent = pendingIntent;
        this.icon = drawable;
        this.contentDescription = charSequence;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaNotificationAction)) {
            return false;
        }
        MediaNotificationAction mediaNotificationAction = (MediaNotificationAction) obj;
        return this.isAuthenticationRequired == mediaNotificationAction.isAuthenticationRequired && Intrinsics.areEqual(this.actionIntent, mediaNotificationAction.actionIntent) && Intrinsics.areEqual(this.icon, mediaNotificationAction.icon) && Intrinsics.areEqual(this.contentDescription, mediaNotificationAction.contentDescription);
    }

    public final int hashCode() {
        int hashCode = Boolean.hashCode(this.isAuthenticationRequired) * 31;
        PendingIntent pendingIntent = this.actionIntent;
        int hashCode2 = (hashCode + (pendingIntent == null ? 0 : pendingIntent.hashCode())) * 31;
        Drawable drawable = this.icon;
        int hashCode3 = (hashCode2 + (drawable == null ? 0 : drawable.hashCode())) * 31;
        CharSequence charSequence = this.contentDescription;
        return hashCode3 + (charSequence != null ? charSequence.hashCode() : 0);
    }

    public final String toString() {
        return "MediaNotificationAction(isAuthenticationRequired=" + this.isAuthenticationRequired + ", actionIntent=" + this.actionIntent + ", icon=" + this.icon + ", contentDescription=" + ((Object) this.contentDescription) + ")";
    }
}
