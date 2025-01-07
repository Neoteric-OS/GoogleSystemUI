package com.android.systemui.media.controls.ui.viewmodel;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaRecViewModel {
    public final Icon albumIcon;
    public final Drawable appIcon;
    public final CharSequence contentDescription;
    public final Function2 onClicked;
    public final int progress;
    public final CharSequence subtitle;
    public final CharSequence title;

    public MediaRecViewModel(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, int i, Icon icon, Drawable drawable, Function2 function2) {
        this.contentDescription = charSequence;
        this.title = charSequence2;
        this.subtitle = charSequence3;
        this.progress = i;
        this.albumIcon = icon;
        this.appIcon = drawable;
        this.onClicked = function2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaRecViewModel)) {
            return false;
        }
        MediaRecViewModel mediaRecViewModel = (MediaRecViewModel) obj;
        return Intrinsics.areEqual(this.contentDescription, mediaRecViewModel.contentDescription) && Intrinsics.areEqual(this.title, mediaRecViewModel.title) && Intrinsics.areEqual(this.subtitle, mediaRecViewModel.subtitle) && this.progress == mediaRecViewModel.progress && Intrinsics.areEqual(this.albumIcon, mediaRecViewModel.albumIcon) && Intrinsics.areEqual(this.appIcon, mediaRecViewModel.appIcon) && Intrinsics.areEqual(this.onClicked, mediaRecViewModel.onClicked);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.progress, (this.subtitle.hashCode() + ((this.title.hashCode() + (this.contentDescription.hashCode() * 31)) * 31)) * 31, 31);
        Icon icon = this.albumIcon;
        return this.onClicked.hashCode() + ((this.appIcon.hashCode() + ((m + (icon == null ? 0 : icon.hashCode())) * 31)) * 31);
    }

    public final String toString() {
        CharSequence charSequence = this.contentDescription;
        CharSequence charSequence2 = this.title;
        CharSequence charSequence3 = this.subtitle;
        return "MediaRecViewModel(contentDescription=" + ((Object) charSequence) + ", title=" + ((Object) charSequence2) + ", subtitle=" + ((Object) charSequence3) + ", progress=" + this.progress + ", albumIcon=" + this.albumIcon + ", appIcon=" + this.appIcon + ", onClicked=" + this.onClicked + ")";
    }
}
