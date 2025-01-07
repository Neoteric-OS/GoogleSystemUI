package androidx.compose.foundation.layout;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OffsetElement extends ModifierNodeElement {
    public final float x;
    public final float y;

    public OffsetElement(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        OffsetNode offsetNode = new OffsetNode();
        offsetNode.x = this.x;
        offsetNode.y = this.y;
        offsetNode.rtlAware = true;
        return offsetNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        OffsetElement offsetElement = obj instanceof OffsetElement ? (OffsetElement) obj : null;
        if (offsetElement == null) {
            return false;
        }
        return Dp.m668equalsimpl0(this.x, offsetElement.x) && Dp.m668equalsimpl0(this.y, offsetElement.y);
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.x) * 31, this.y, 31);
    }

    public final String toString() {
        return "OffsetModifierElement(x=" + ((Object) Dp.m669toStringimpl(this.x)) + ", y=" + ((Object) Dp.m669toStringimpl(this.y)) + ", rtlAware=true)";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        OffsetNode offsetNode = (OffsetNode) node;
        float f = offsetNode.x;
        float f2 = this.x;
        boolean m668equalsimpl0 = Dp.m668equalsimpl0(f, f2);
        float f3 = this.y;
        if (!m668equalsimpl0 || !Dp.m668equalsimpl0(offsetNode.y, f3) || !offsetNode.rtlAware) {
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(offsetNode);
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            requireLayoutNode.requestRelayout$ui_release(false);
        }
        offsetNode.x = f2;
        offsetNode.y = f3;
        offsetNode.rtlAware = true;
    }
}
