package com.android.systemui.keyboard.shortcut.shared.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutCategory {
    public final List subCategories;
    public final ShortcutCategoryType type;

    public ShortcutCategory(ShortcutCategoryType shortcutCategoryType, List list) {
        this.type = shortcutCategoryType;
        this.subCategories = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortcutCategory)) {
            return false;
        }
        ShortcutCategory shortcutCategory = (ShortcutCategory) obj;
        return Intrinsics.areEqual(this.type, shortcutCategory.type) && Intrinsics.areEqual(this.subCategories, shortcutCategory.subCategories);
    }

    public final int hashCode() {
        return this.subCategories.hashCode() + (this.type.hashCode() * 31);
    }

    public final String toString() {
        return "ShortcutCategory(type=" + this.type + ", subCategories=" + this.subCategories + ")";
    }
}
