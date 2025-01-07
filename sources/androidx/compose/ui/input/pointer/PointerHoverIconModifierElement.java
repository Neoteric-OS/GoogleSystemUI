package androidx.compose.ui.input.pointer;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerHoverIconModifierElement extends ModifierNodeElement {
    public final AndroidPointerIconType icon;

    public PointerHoverIconModifierElement(AndroidPointerIconType androidPointerIconType) {
        this.icon = androidPointerIconType;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        AndroidPointerIconType androidPointerIconType = this.icon;
        PointerHoverIconModifierNode pointerHoverIconModifierNode = new PointerHoverIconModifierNode();
        pointerHoverIconModifierNode.icon = androidPointerIconType;
        return pointerHoverIconModifierNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PointerHoverIconModifierElement) {
            return this.icon.equals(((PointerHoverIconModifierElement) obj).icon);
        }
        return false;
    }

    public final int hashCode() {
        return Boolean.hashCode(false) + (this.icon.hashCode() * 31);
    }

    public final String toString() {
        return "PointerHoverIconModifierElement(icon=" + this.icon + ", overrideDescendants=false)";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        PointerHoverIconModifierNode pointerHoverIconModifierNode = (PointerHoverIconModifierNode) node;
        AndroidPointerIconType androidPointerIconType = pointerHoverIconModifierNode.icon;
        AndroidPointerIconType androidPointerIconType2 = this.icon;
        if (androidPointerIconType.equals(androidPointerIconType2)) {
            return;
        }
        pointerHoverIconModifierNode.icon = androidPointerIconType2;
        if (pointerHoverIconModifierNode.cursorInBoundsOfNode) {
            pointerHoverIconModifierNode.displayIconIfDescendantsDoNotHavePriority();
        }
    }
}
