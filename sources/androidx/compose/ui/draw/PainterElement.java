package androidx.compose.ui.draw;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PainterElement extends ModifierNodeElement {
    public final Alignment alignment;
    public final float alpha;
    public final ColorFilter colorFilter;
    public final ContentScale contentScale;
    public final Painter painter;

    public PainterElement(Painter painter, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter) {
        this.painter = painter;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        PainterNode painterNode = new PainterNode();
        painterNode.painter = this.painter;
        painterNode.sizeToIntrinsics = true;
        painterNode.alignment = this.alignment;
        painterNode.contentScale = this.contentScale;
        painterNode.alpha = this.alpha;
        painterNode.colorFilter = this.colorFilter;
        return painterNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PainterElement)) {
            return false;
        }
        PainterElement painterElement = (PainterElement) obj;
        return Intrinsics.areEqual(this.painter, painterElement.painter) && Intrinsics.areEqual(this.alignment, painterElement.alignment) && Intrinsics.areEqual(this.contentScale, painterElement.contentScale) && Float.compare(this.alpha, painterElement.alpha) == 0 && Intrinsics.areEqual(this.colorFilter, painterElement.colorFilter);
    }

    public final int hashCode() {
        int m = FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.contentScale.hashCode() + ((this.alignment.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.painter.hashCode() * 31, 31, true)) * 31)) * 31, this.alpha, 31);
        ColorFilter colorFilter = this.colorFilter;
        return m + (colorFilter == null ? 0 : colorFilter.hashCode());
    }

    public final String toString() {
        return "PainterElement(painter=" + this.painter + ", sizeToIntrinsics=true, alignment=" + this.alignment + ", contentScale=" + this.contentScale + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        PainterNode painterNode = (PainterNode) node;
        boolean z = painterNode.sizeToIntrinsics;
        Painter painter = this.painter;
        boolean z2 = (z && Size.m326equalsimpl0(painterNode.painter.mo436getIntrinsicSizeNHjbRc(), painter.mo436getIntrinsicSizeNHjbRc())) ? false : true;
        painterNode.painter = painter;
        painterNode.sizeToIntrinsics = true;
        painterNode.alignment = this.alignment;
        painterNode.contentScale = this.contentScale;
        painterNode.alpha = this.alpha;
        painterNode.colorFilter = this.colorFilter;
        if (z2) {
            LayoutModifierNodeKt.invalidateMeasurement(painterNode);
        }
        DrawModifierNodeKt.invalidateDraw(painterNode);
    }
}
