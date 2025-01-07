package com.android.systemui.media.controls.shared.model;

import android.graphics.drawable.Drawable;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaAction {
    public final Runnable action;
    public final Drawable background;
    public final CharSequence contentDescription;
    public final Drawable icon;
    public final Integer rebindId;

    public MediaAction(Drawable drawable, Runnable runnable, CharSequence charSequence, Drawable drawable2, Integer num) {
        this.icon = drawable;
        this.action = runnable;
        this.contentDescription = charSequence;
        this.background = drawable2;
        this.rebindId = num;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaAction)) {
            return false;
        }
        MediaAction mediaAction = (MediaAction) obj;
        return Intrinsics.areEqual(this.icon, mediaAction.icon) && Intrinsics.areEqual(this.action, mediaAction.action) && Intrinsics.areEqual(this.contentDescription, mediaAction.contentDescription) && Intrinsics.areEqual(this.background, mediaAction.background) && Intrinsics.areEqual(this.rebindId, mediaAction.rebindId);
    }

    public final int hashCode() {
        Drawable drawable = this.icon;
        int hashCode = (drawable == null ? 0 : drawable.hashCode()) * 31;
        Runnable runnable = this.action;
        int hashCode2 = (hashCode + (runnable == null ? 0 : runnable.hashCode())) * 31;
        CharSequence charSequence = this.contentDescription;
        int hashCode3 = (hashCode2 + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        Drawable drawable2 = this.background;
        int hashCode4 = (hashCode3 + (drawable2 == null ? 0 : drawable2.hashCode())) * 31;
        Integer num = this.rebindId;
        return hashCode4 + (num != null ? num.hashCode() : 0);
    }

    public final String toString() {
        Drawable drawable = this.icon;
        Runnable runnable = this.action;
        CharSequence charSequence = this.contentDescription;
        return "MediaAction(icon=" + drawable + ", action=" + runnable + ", contentDescription=" + ((Object) charSequence) + ", background=" + this.background + ", rebindId=" + this.rebindId + ")";
    }
}
