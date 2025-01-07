package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class IntrinsicHeightElement extends ModifierNodeElement {
    public final IntrinsicSize height;

    public IntrinsicHeightElement(IntrinsicSize intrinsicSize) {
        this.height = intrinsicSize;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        IntrinsicHeightNode intrinsicHeightNode = new IntrinsicHeightNode();
        intrinsicHeightNode.height = this.height;
        intrinsicHeightNode.enforceIncoming = true;
        return intrinsicHeightNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        IntrinsicHeightElement intrinsicHeightElement = obj instanceof IntrinsicHeightElement ? (IntrinsicHeightElement) obj : null;
        return intrinsicHeightElement != null && this.height == intrinsicHeightElement.height;
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + (this.height.hashCode() * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        IntrinsicHeightNode intrinsicHeightNode = (IntrinsicHeightNode) node;
        intrinsicHeightNode.height = this.height;
        intrinsicHeightNode.enforceIncoming = true;
    }
}
