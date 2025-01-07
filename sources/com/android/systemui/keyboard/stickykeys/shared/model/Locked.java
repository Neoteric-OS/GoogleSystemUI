package com.android.systemui.keyboard.stickykeys.shared.model;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Locked {
    public final boolean locked;

    public final boolean equals(Object obj) {
        if (obj instanceof Locked) {
            return this.locked == ((Locked) obj).locked;
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.locked);
    }

    public final String toString() {
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(new StringBuilder("Locked(locked="), this.locked, ")");
    }
}
