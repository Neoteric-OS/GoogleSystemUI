package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LayoutIdElement extends ModifierNodeElement {
    public final Object layoutId;

    public LayoutIdElement(Object obj) {
        this.layoutId = obj;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LayoutIdModifier layoutIdModifier = new LayoutIdModifier();
        layoutIdModifier.layoutId = this.layoutId;
        return layoutIdModifier;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof LayoutIdElement) && Intrinsics.areEqual(this.layoutId, ((LayoutIdElement) obj).layoutId);
    }

    public final int hashCode() {
        return this.layoutId.hashCode();
    }

    public final String toString() {
        return "LayoutIdElement(layoutId=" + this.layoutId + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((LayoutIdModifier) node).layoutId = this.layoutId;
    }
}
