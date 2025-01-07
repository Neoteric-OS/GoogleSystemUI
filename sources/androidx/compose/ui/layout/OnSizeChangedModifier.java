package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OnSizeChangedModifier extends ModifierNodeElement {
    public final Function1 onSizeChanged;

    public OnSizeChangedModifier(Function1 function1) {
        this.onSizeChanged = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        OnSizeChangedNode onSizeChangedNode = new OnSizeChangedNode();
        onSizeChangedNode.onSizeChanged = this.onSizeChanged;
        long j = Integer.MIN_VALUE;
        onSizeChangedNode.previousSize = (j & 4294967295L) | (j << 32);
        return onSizeChangedNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnSizeChangedModifier) {
            return this.onSizeChanged == ((OnSizeChangedModifier) obj).onSizeChanged;
        }
        return false;
    }

    public final int hashCode() {
        return this.onSizeChanged.hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        OnSizeChangedNode onSizeChangedNode = (OnSizeChangedNode) node;
        onSizeChangedNode.onSizeChanged = this.onSizeChanged;
        long j = Integer.MIN_VALUE;
        onSizeChangedNode.previousSize = (j & 4294967295L) | (j << 32);
    }
}
