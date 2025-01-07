package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OnPlacedElement extends ModifierNodeElement {
    public final Function1 onPlaced;

    public OnPlacedElement(Function1 function1) {
        this.onPlaced = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        OnPlacedNode onPlacedNode = new OnPlacedNode();
        onPlacedNode.callback = this.onPlaced;
        return onPlacedNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof OnPlacedElement) && Intrinsics.areEqual(this.onPlaced, ((OnPlacedElement) obj).onPlaced);
    }

    public final int hashCode() {
        return this.onPlaced.hashCode();
    }

    public final String toString() {
        return "OnPlacedElement(onPlaced=" + this.onPlaced + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((OnPlacedNode) node).callback = this.onPlaced;
    }
}
