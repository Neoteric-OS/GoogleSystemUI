package com.android.systemui.scene.data.model;

import com.android.compose.animation.scene.SceneKey;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StackedNodes implements SceneStack {
    public final SceneKey head;
    public final SceneStack tail;

    public StackedNodes(SceneKey sceneKey, SceneStack sceneStack) {
        this.head = sceneKey;
        this.tail = sceneStack;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StackedNodes)) {
            return false;
        }
        StackedNodes stackedNodes = (StackedNodes) obj;
        return Intrinsics.areEqual(this.head, stackedNodes.head) && Intrinsics.areEqual(this.tail, stackedNodes.tail);
    }

    public final int hashCode() {
        return this.tail.hashCode() + (this.head.identity.hashCode() * 31);
    }

    public final String toString() {
        return "StackedNodes(head=" + this.head + ", tail=" + this.tail + ")";
    }
}
