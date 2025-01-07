package com.android.systemui.keyboard.shortcut.shared.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutSubCategory {
    public final String label;
    public final List shortcuts;

    public ShortcutSubCategory(String str, List list) {
        this.label = str;
        this.shortcuts = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortcutSubCategory)) {
            return false;
        }
        ShortcutSubCategory shortcutSubCategory = (ShortcutSubCategory) obj;
        return Intrinsics.areEqual(this.label, shortcutSubCategory.label) && Intrinsics.areEqual(this.shortcuts, shortcutSubCategory.shortcuts);
    }

    public final int hashCode() {
        return this.shortcuts.hashCode() + (this.label.hashCode() * 31);
    }

    public final String toString() {
        return "ShortcutSubCategory(label=" + this.label + ", shortcuts=" + this.shortcuts + ")";
    }
}
