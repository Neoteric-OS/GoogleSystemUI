package androidx.compose.foundation.layout;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PaddingElement extends ModifierNodeElement {
    public final float bottom;
    public final float end;
    public final float start;
    public final float top;

    public PaddingElement(float f, float f2, float f3, float f4) {
        this.start = f;
        this.top = f2;
        this.end = f3;
        this.bottom = f4;
        if ((f >= 0.0f || Dp.m668equalsimpl0(f, Float.NaN)) && ((f2 >= 0.0f || Dp.m668equalsimpl0(f2, Float.NaN)) && ((f3 >= 0.0f || Dp.m668equalsimpl0(f3, Float.NaN)) && (f4 >= 0.0f || Dp.m668equalsimpl0(f4, Float.NaN))))) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("Padding must be non-negative");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        PaddingNode paddingNode = new PaddingNode();
        paddingNode.start = this.start;
        paddingNode.top = this.top;
        paddingNode.end = this.end;
        paddingNode.bottom = this.bottom;
        paddingNode.rtlAware = true;
        return paddingNode;
    }

    public final boolean equals(Object obj) {
        PaddingElement paddingElement = obj instanceof PaddingElement ? (PaddingElement) obj : null;
        return paddingElement != null && Dp.m668equalsimpl0(this.start, paddingElement.start) && Dp.m668equalsimpl0(this.top, paddingElement.top) && Dp.m668equalsimpl0(this.end, paddingElement.end) && Dp.m668equalsimpl0(this.bottom, paddingElement.bottom);
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Float.hashCode(this.start) * 31, this.top, 31), this.end, 31), this.bottom, 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        PaddingNode paddingNode = (PaddingNode) node;
        paddingNode.start = this.start;
        paddingNode.top = this.top;
        paddingNode.end = this.end;
        paddingNode.bottom = this.bottom;
        paddingNode.rtlAware = true;
    }
}
