package com.android.wm.shell.bubbles;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShortcutKey {
    public final String pkg;
    public final int userId;

    public ShortcutKey(int i, String str) {
        this.userId = i;
        this.pkg = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShortcutKey)) {
            return false;
        }
        ShortcutKey shortcutKey = (ShortcutKey) obj;
        return this.userId == shortcutKey.userId && Intrinsics.areEqual(this.pkg, shortcutKey.pkg);
    }

    public final int hashCode() {
        return this.pkg.hashCode() + (Integer.hashCode(this.userId) * 31);
    }

    public final String toString() {
        return "ShortcutKey(userId=" + this.userId + ", pkg=" + this.pkg + ")";
    }
}
