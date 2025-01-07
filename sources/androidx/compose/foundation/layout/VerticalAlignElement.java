package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class VerticalAlignElement extends ModifierNodeElement {
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        VerticalAlignNode verticalAlignNode = new VerticalAlignNode();
        verticalAlignNode.vertical = Alignment.Companion.CenterVertically;
        return verticalAlignNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof VerticalAlignElement ? (VerticalAlignElement) obj : null) == null) {
            return false;
        }
        BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
        return vertical.equals(vertical);
    }

    public final int hashCode() {
        return Float.hashCode(0.0f);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((VerticalAlignNode) node).vertical = Alignment.Companion.CenterVertically;
    }
}
