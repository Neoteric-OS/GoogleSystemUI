package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BorderModifierNodeElement extends ModifierNodeElement {
    public final SolidColor brush;
    public final Shape shape;
    public final float width;

    public BorderModifierNodeElement(float f, SolidColor solidColor, Shape shape) {
        this.width = f;
        this.brush = solidColor;
        this.shape = shape;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new BorderModifierNode(this.width, this.brush, this.shape);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BorderModifierNodeElement)) {
            return false;
        }
        BorderModifierNodeElement borderModifierNodeElement = (BorderModifierNodeElement) obj;
        return Dp.m668equalsimpl0(this.width, borderModifierNodeElement.width) && this.brush.equals(borderModifierNodeElement.brush) && Intrinsics.areEqual(this.shape, borderModifierNodeElement.shape);
    }

    public final int hashCode() {
        return this.shape.hashCode() + ((this.brush.hashCode() + (Float.hashCode(this.width) * 31)) * 31);
    }

    public final String toString() {
        return "BorderModifierNodeElement(width=" + ((Object) Dp.m669toStringimpl(this.width)) + ", brush=" + this.brush + ", shape=" + this.shape + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        BorderModifierNode borderModifierNode = (BorderModifierNode) node;
        float f = borderModifierNode.width;
        float f2 = this.width;
        boolean m668equalsimpl0 = Dp.m668equalsimpl0(f, f2);
        CacheDrawModifierNode cacheDrawModifierNode = borderModifierNode.drawWithCacheModifierNode;
        if (!m668equalsimpl0) {
            borderModifierNode.width = f2;
            cacheDrawModifierNode.invalidateDrawCache();
        }
        SolidColor solidColor = borderModifierNode.brush;
        SolidColor solidColor2 = this.brush;
        if (!Intrinsics.areEqual(solidColor, solidColor2)) {
            borderModifierNode.brush = solidColor2;
            cacheDrawModifierNode.invalidateDrawCache();
        }
        Shape shape = borderModifierNode.shape;
        Shape shape2 = this.shape;
        if (Intrinsics.areEqual(shape, shape2)) {
            return;
        }
        borderModifierNode.shape = shape2;
        cacheDrawModifierNode.invalidateDrawCache();
    }
}
