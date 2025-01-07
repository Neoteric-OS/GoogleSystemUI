package androidx.compose.ui;

import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ZIndexElement extends ModifierNodeElement {
    public final float zIndex;

    public ZIndexElement(float f) {
        this.zIndex = f;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        ZIndexNode zIndexNode = new ZIndexNode();
        zIndexNode.zIndex = this.zIndex;
        return zIndexNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ZIndexElement) && Float.compare(this.zIndex, ((ZIndexElement) obj).zIndex) == 0;
    }

    public final int hashCode() {
        return Float.hashCode(this.zIndex);
    }

    public final String toString() {
        return AndroidFlingSpline$$ExternalSyntheticOutline0.m(new StringBuilder("ZIndexElement(zIndex="), this.zIndex, ')');
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((ZIndexNode) node).zIndex = this.zIndex;
    }
}
