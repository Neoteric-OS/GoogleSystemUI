package com.android.systemui.keyboard.shortcut.shared.model;

import androidx.compose.animation.graphics.vector.PropertyValuesHolder2D$$ExternalSyntheticOutline0;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Shortcut {
    public final List commands;
    public final ShortcutIcon icon;
    public final String label;

    public Shortcut(String str, List list, ShortcutIcon shortcutIcon) {
        this.label = str;
        this.commands = list;
        this.icon = shortcutIcon;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Shortcut)) {
            return false;
        }
        Shortcut shortcut = (Shortcut) obj;
        return Intrinsics.areEqual(this.label, shortcut.label) && Intrinsics.areEqual(this.commands, shortcut.commands) && Intrinsics.areEqual(this.icon, shortcut.icon);
    }

    public final int hashCode() {
        int m = PropertyValuesHolder2D$$ExternalSyntheticOutline0.m(this.label.hashCode() * 31, 31, this.commands);
        ShortcutIcon shortcutIcon = this.icon;
        return m + (shortcutIcon == null ? 0 : shortcutIcon.hashCode());
    }

    public final String toString() {
        return "Shortcut(label=" + this.label + ", commands=" + this.commands + ", icon=" + this.icon + ")";
    }
}
