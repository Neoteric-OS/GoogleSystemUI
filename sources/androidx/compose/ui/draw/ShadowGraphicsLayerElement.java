package androidx.compose.ui.draw;

import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.OverscrollConfiguration$$ExternalSyntheticOutline0;
import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.BlockGraphicsLayerModifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ShadowGraphicsLayerElement extends ModifierNodeElement {
    public final long ambientColor;
    public final boolean clip;
    public final Shape shape;
    public final long spotColor;

    public ShadowGraphicsLayerElement(Shape shape, boolean z, long j, long j2) {
        float f = ContextMenuSpec.ContainerWidthMin;
        this.shape = shape;
        this.clip = z;
        this.ambientColor = j;
        this.spotColor = j2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new BlockGraphicsLayerModifier(new ShadowGraphicsLayerElement$createBlock$1(this));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadowGraphicsLayerElement)) {
            return false;
        }
        ShadowGraphicsLayerElement shadowGraphicsLayerElement = (ShadowGraphicsLayerElement) obj;
        shadowGraphicsLayerElement.getClass();
        float f = ContextMenuSpec.MenuContainerElevation;
        return Dp.m668equalsimpl0(f, f) && Intrinsics.areEqual(this.shape, shadowGraphicsLayerElement.shape) && this.clip == shadowGraphicsLayerElement.clip && Color.m363equalsimpl0(this.ambientColor, shadowGraphicsLayerElement.ambientColor) && Color.m363equalsimpl0(this.spotColor, shadowGraphicsLayerElement.spotColor);
    }

    public final int hashCode() {
        int m = TransitionData$$ExternalSyntheticOutline0.m((this.shape.hashCode() + (Float.hashCode(ContextMenuSpec.MenuContainerElevation) * 31)) * 31, 31, this.clip);
        int i = Color.$r8$clinit;
        return Long.hashCode(this.spotColor) + Scale$$ExternalSyntheticOutline0.m(m, 31, this.ambientColor);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShadowGraphicsLayerElement(elevation=");
        sb.append((Object) Dp.m669toStringimpl(ContextMenuSpec.MenuContainerElevation));
        sb.append(", shape=");
        sb.append(this.shape);
        sb.append(", clip=");
        sb.append(this.clip);
        sb.append(", ambientColor=");
        OverscrollConfiguration$$ExternalSyntheticOutline0.m(this.ambientColor, ", spotColor=", sb);
        sb.append((Object) Color.m369toStringimpl(this.spotColor));
        sb.append(')');
        return sb.toString();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        BlockGraphicsLayerModifier blockGraphicsLayerModifier = (BlockGraphicsLayerModifier) node;
        blockGraphicsLayerModifier.layerBlock = new ShadowGraphicsLayerElement$createBlock$1(this);
        NodeCoordinator nodeCoordinator = DelegatableNodeKt.m503requireCoordinator64DMado(blockGraphicsLayerModifier, 2).wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.updateLayerBlock(blockGraphicsLayerModifier.layerBlock, true);
        }
    }
}
