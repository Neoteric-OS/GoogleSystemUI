package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HorizontalAlignElement extends ModifierNodeElement {
    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        HorizontalAlignNode horizontalAlignNode = new HorizontalAlignNode();
        horizontalAlignNode.horizontal = Alignment.Companion.CenterHorizontally;
        return horizontalAlignNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj instanceof HorizontalAlignElement ? (HorizontalAlignElement) obj : null) == null) {
            return false;
        }
        BiasAlignment.Horizontal horizontal = Alignment.Companion.CenterHorizontally;
        return horizontal.equals(horizontal);
    }

    public final int hashCode() {
        return Float.hashCode(0.0f);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((HorizontalAlignNode) node).horizontal = Alignment.Companion.CenterHorizontally;
    }
}
