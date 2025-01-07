package com.android.systemui.volume.panel.component.button.ui.viewmodel;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.common.shared.model.Icon;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ButtonViewModel {
    public final Icon icon;
    public final boolean isActive;
    public final CharSequence label;

    public ButtonViewModel(Icon icon, CharSequence charSequence, boolean z) {
        this.icon = icon;
        this.label = charSequence;
        this.isActive = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ButtonViewModel)) {
            return false;
        }
        ButtonViewModel buttonViewModel = (ButtonViewModel) obj;
        return Intrinsics.areEqual(this.icon, buttonViewModel.icon) && Intrinsics.areEqual(this.label, buttonViewModel.label) && this.isActive == buttonViewModel.isActive;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isActive) + ((this.label.hashCode() + (this.icon.hashCode() * 31)) * 31);
    }

    public final String toString() {
        CharSequence charSequence = this.label;
        StringBuilder sb = new StringBuilder("ButtonViewModel(icon=");
        sb.append(this.icon);
        sb.append(", label=");
        sb.append((Object) charSequence);
        sb.append(", isActive=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isActive, ")");
    }
}
