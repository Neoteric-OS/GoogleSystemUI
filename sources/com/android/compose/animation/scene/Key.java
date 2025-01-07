package com.android.compose.animation.scene;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Key {
    public final String debugName;
    public final Object identity;

    public Key(Object obj, String str) {
        this.debugName = str;
        this.identity = obj;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj != null ? obj.getClass() : null)) {
            return false;
        }
        Key key = obj instanceof Key ? (Key) obj : null;
        return Intrinsics.areEqual(this.identity, key != null ? key.identity : null);
    }

    public final int hashCode() {
        return this.identity.hashCode();
    }

    public abstract String toString();
}
