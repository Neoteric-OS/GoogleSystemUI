package com.android.systemui.keyboard.shortcut.shared.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShortcutCommand {
    public final List keys;

    public ShortcutCommand(List list) {
        this.keys = list;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ShortcutCommand) && Intrinsics.areEqual(this.keys, ((ShortcutCommand) obj).keys);
    }

    public final int hashCode() {
        return this.keys.hashCode();
    }

    public final String toString() {
        return "ShortcutCommand(keys=" + this.keys + ")";
    }
}
