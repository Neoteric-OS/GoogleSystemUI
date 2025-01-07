package com.android.compose.animation.scene;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LayoutElement extends ModifierNodeElement {
    public final SceneTransitionLayoutImpl layoutImpl;

    public LayoutElement(SceneTransitionLayoutImpl sceneTransitionLayoutImpl) {
        this.layoutImpl = sceneTransitionLayoutImpl;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LayoutNode layoutNode = new LayoutNode();
        layoutNode.layoutImpl = this.layoutImpl;
        return layoutNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutElement) && Intrinsics.areEqual(this.layoutImpl, ((LayoutElement) obj).layoutImpl);
    }

    public final int hashCode() {
        return this.layoutImpl.hashCode();
    }

    public final String toString() {
        return "LayoutElement(layoutImpl=" + this.layoutImpl + ")";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((LayoutNode) node).layoutImpl = this.layoutImpl;
    }
}
