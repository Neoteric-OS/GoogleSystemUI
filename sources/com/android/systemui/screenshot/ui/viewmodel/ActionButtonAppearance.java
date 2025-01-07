package com.android.systemui.screenshot.ui.viewmodel;

import android.graphics.drawable.Drawable;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActionButtonAppearance {
    public final Drawable customBackground;
    public final CharSequence description;
    public final Drawable icon;
    public final CharSequence label;
    public final boolean tint;

    public ActionButtonAppearance(Drawable drawable, CharSequence charSequence, CharSequence charSequence2, boolean z) {
        this(drawable, charSequence, charSequence2, z, 16);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActionButtonAppearance)) {
            return false;
        }
        ActionButtonAppearance actionButtonAppearance = (ActionButtonAppearance) obj;
        return Intrinsics.areEqual(this.icon, actionButtonAppearance.icon) && Intrinsics.areEqual(this.label, actionButtonAppearance.label) && Intrinsics.areEqual(this.description, actionButtonAppearance.description) && this.tint == actionButtonAppearance.tint && Intrinsics.areEqual(this.customBackground, actionButtonAppearance.customBackground);
    }

    public final int hashCode() {
        Drawable drawable = this.icon;
        int hashCode = (drawable == null ? 0 : drawable.hashCode()) * 31;
        CharSequence charSequence = this.label;
        int m = TransitionData$$ExternalSyntheticOutline0.m((this.description.hashCode() + ((hashCode + (charSequence == null ? 0 : charSequence.hashCode())) * 31)) * 31, 31, this.tint);
        Drawable drawable2 = this.customBackground;
        return m + (drawable2 != null ? drawable2.hashCode() : 0);
    }

    public final String toString() {
        Drawable drawable = this.icon;
        CharSequence charSequence = this.label;
        CharSequence charSequence2 = this.description;
        return "ActionButtonAppearance(icon=" + drawable + ", label=" + ((Object) charSequence) + ", description=" + ((Object) charSequence2) + ", tint=" + this.tint + ", customBackground=" + this.customBackground + ")";
    }

    public ActionButtonAppearance(Drawable drawable, CharSequence charSequence, CharSequence charSequence2, boolean z, int i) {
        z = (i & 8) != 0 ? true : z;
        this.icon = drawable;
        this.label = charSequence;
        this.description = charSequence2;
        this.tint = z;
        this.customBackground = null;
    }
}
