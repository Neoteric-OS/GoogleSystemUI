package com.android.systemui.scene.data.model;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EmptyStack implements SceneStack {
    public static final EmptyStack INSTANCE = new EmptyStack();

    public final boolean equals(Object obj) {
        return this == obj || (obj instanceof EmptyStack);
    }

    public final int hashCode() {
        return -432229341;
    }

    public final String toString() {
        return "EmptyStack";
    }
}
