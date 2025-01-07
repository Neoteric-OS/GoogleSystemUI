package androidx.compose.foundation.layout;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OffsetPxElement extends ModifierNodeElement {
    public final Function1 offset;
    public final boolean rtlAware;

    public OffsetPxElement(Function1 function1, boolean z) {
        this.offset = function1;
        this.rtlAware = z;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        OffsetPxNode offsetPxNode = new OffsetPxNode();
        offsetPxNode.offset = this.offset;
        offsetPxNode.rtlAware = this.rtlAware;
        return offsetPxNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        OffsetPxElement offsetPxElement = obj instanceof OffsetPxElement ? (OffsetPxElement) obj : null;
        if (offsetPxElement == null) {
            return false;
        }
        return this.offset == offsetPxElement.offset && this.rtlAware == offsetPxElement.rtlAware;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.rtlAware) + (this.offset.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("OffsetPxModifier(offset=");
        sb.append(this.offset);
        sb.append(", rtlAware=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.rtlAware, ')');
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        OffsetPxNode offsetPxNode = (OffsetPxNode) node;
        Function1 function1 = offsetPxNode.offset;
        Function1 function12 = this.offset;
        boolean z = this.rtlAware;
        if (function1 != function12 || offsetPxNode.rtlAware != z) {
            LayoutNode requireLayoutNode = DelegatableNodeKt.requireLayoutNode(offsetPxNode);
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            requireLayoutNode.requestRelayout$ui_release(false);
        }
        offsetPxNode.offset = function12;
        offsetPxNode.rtlAware = z;
    }
}
