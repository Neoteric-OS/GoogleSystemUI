package com.android.systemui.media.controls.shared.model;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecModel {
    public final Bundle extras;
    public final Icon icon;
    public final Intent intent;
    public final CharSequence subtitle;
    public final CharSequence title;

    public MediaRecModel(Intent intent, CharSequence charSequence, CharSequence charSequence2, Icon icon, Bundle bundle) {
        this.intent = intent;
        this.title = charSequence;
        this.subtitle = charSequence2;
        this.icon = icon;
        this.extras = bundle;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRecModel)) {
            return false;
        }
        MediaRecModel mediaRecModel = (MediaRecModel) obj;
        return Intrinsics.areEqual(this.intent, mediaRecModel.intent) && Intrinsics.areEqual(this.title, mediaRecModel.title) && Intrinsics.areEqual(this.subtitle, mediaRecModel.subtitle) && Intrinsics.areEqual(this.icon, mediaRecModel.icon) && Intrinsics.areEqual(this.extras, mediaRecModel.extras);
    }

    public final int hashCode() {
        Intent intent = this.intent;
        int hashCode = (intent == null ? 0 : intent.hashCode()) * 31;
        CharSequence charSequence = this.title;
        int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        CharSequence charSequence2 = this.subtitle;
        int hashCode3 = (hashCode2 + (charSequence2 == null ? 0 : charSequence2.hashCode())) * 31;
        Icon icon = this.icon;
        int hashCode4 = (hashCode3 + (icon == null ? 0 : icon.hashCode())) * 31;
        Bundle bundle = this.extras;
        return hashCode4 + (bundle != null ? bundle.hashCode() : 0);
    }

    public final String toString() {
        Intent intent = this.intent;
        CharSequence charSequence = this.title;
        CharSequence charSequence2 = this.subtitle;
        return "MediaRecModel(intent=" + intent + ", title=" + ((Object) charSequence) + ", subtitle=" + ((Object) charSequence2) + ", icon=" + this.icon + ", extras=" + this.extras + ")";
    }
}
