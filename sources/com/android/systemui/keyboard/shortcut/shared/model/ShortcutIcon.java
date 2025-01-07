package com.android.systemui.keyboard.shortcut.shared.model;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutIcon {
    public final String packageName;
    public final int resourceId;

    public ShortcutIcon(String str, int i) {
        this.packageName = str;
        this.resourceId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortcutIcon)) {
            return false;
        }
        ShortcutIcon shortcutIcon = (ShortcutIcon) obj;
        return Intrinsics.areEqual(this.packageName, shortcutIcon.packageName) && this.resourceId == shortcutIcon.resourceId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.resourceId) + (this.packageName.hashCode() * 31);
    }

    public final String toString() {
        return "ShortcutIcon(packageName=" + this.packageName + ", resourceId=" + this.resourceId + ")";
    }
}
