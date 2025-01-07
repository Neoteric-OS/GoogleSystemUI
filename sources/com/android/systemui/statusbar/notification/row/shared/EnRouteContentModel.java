package com.android.systemui.statusbar.notification.row.shared;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EnRouteContentModel implements RichOngoingContentModel {
    public final IconModel smallIcon;
    public final CharSequence text;
    public final CharSequence title;

    public EnRouteContentModel(IconModel iconModel, CharSequence charSequence, CharSequence charSequence2) {
        this.smallIcon = iconModel;
        this.title = charSequence;
        this.text = charSequence2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EnRouteContentModel)) {
            return false;
        }
        EnRouteContentModel enRouteContentModel = (EnRouteContentModel) obj;
        return Intrinsics.areEqual(this.smallIcon, enRouteContentModel.smallIcon) && Intrinsics.areEqual(this.title, enRouteContentModel.title) && Intrinsics.areEqual(this.text, enRouteContentModel.text);
    }

    public final int hashCode() {
        int hashCode = this.smallIcon.hashCode() * 31;
        CharSequence charSequence = this.title;
        int hashCode2 = (hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31;
        CharSequence charSequence2 = this.text;
        return hashCode2 + (charSequence2 != null ? charSequence2.hashCode() : 0);
    }

    public final String toString() {
        return "EnRouteContentModel(smallIcon=" + this.smallIcon + ", title=" + ((Object) this.title) + ", text=" + ((Object) this.text) + ")";
    }
}
