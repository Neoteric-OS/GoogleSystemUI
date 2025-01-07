package androidx.compose.ui.draw;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutNodeDrawScope;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PainterNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode {
    public Alignment alignment;
    public float alpha;
    public ColorFilter colorFilter;
    public ContentScale contentScale;
    public Painter painter;
    public boolean sizeToIntrinsics;

    /* renamed from: hasSpecifiedAndFiniteHeight-uvyYCjk, reason: not valid java name */
    public static boolean m280hasSpecifiedAndFiniteHeightuvyYCjk(long j) {
        return !Size.m326equalsimpl0(j, 9205357640488583168L) && (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L))) & Integer.MAX_VALUE) < 2139095040;
    }

    /* renamed from: hasSpecifiedAndFiniteWidth-uvyYCjk, reason: not valid java name */
    public static boolean m281hasSpecifiedAndFiniteWidthuvyYCjk(long j) {
        return !Size.m326equalsimpl0(j, 9205357640488583168L) && (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j >> 32))) & Integer.MAX_VALUE) < 2139095040;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        long mo436getIntrinsicSizeNHjbRc = this.painter.mo436getIntrinsicSizeNHjbRc();
        boolean m281hasSpecifiedAndFiniteWidthuvyYCjk = m281hasSpecifiedAndFiniteWidthuvyYCjk(mo436getIntrinsicSizeNHjbRc);
        CanvasDrawScope canvasDrawScope = layoutNodeDrawScope.canvasDrawScope;
        long floatToRawIntBits = (Float.floatToRawIntBits(m281hasSpecifiedAndFiniteWidthuvyYCjk ? Float.intBitsToFloat((int) (mo436getIntrinsicSizeNHjbRc >> 32)) : Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32))) << 32) | (Float.floatToRawIntBits(m280hasSpecifiedAndFiniteHeightuvyYCjk(mo436getIntrinsicSizeNHjbRc) ? Float.intBitsToFloat((int) (mo436getIntrinsicSizeNHjbRc & 4294967295L)) : Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L))) & 4294967295L);
        long m501timesUQTWf7w = (Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32)) == 0.0f || Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L)) == 0.0f) ? 0L : ScaleFactorKt.m501timesUQTWf7w(floatToRawIntBits, this.contentScale.mo476computeScaleFactorH7hwNQA(floatToRawIntBits, canvasDrawScope.mo432getSizeNHjbRc()));
        long mo274alignKFBX0sM = this.alignment.mo274alignKFBX0sM((Math.round(Float.intBitsToFloat((int) (m501timesUQTWf7w & 4294967295L))) & 4294967295L) | (Math.round(Float.intBitsToFloat((int) (m501timesUQTWf7w >> 32))) << 32), (Math.round(Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() >> 32))) << 32) | (Math.round(Float.intBitsToFloat((int) (canvasDrawScope.mo432getSizeNHjbRc() & 4294967295L))) & 4294967295L), layoutNodeDrawScope.getLayoutDirection());
        float f = (int) (mo274alignKFBX0sM >> 32);
        float f2 = (int) (mo274alignKFBX0sM & 4294967295L);
        canvasDrawScope.drawContext.transform.translate(f, f2);
        try {
            this.painter.m437drawx_KDEd0(layoutNodeDrawScope, m501timesUQTWf7w, this.alpha, this.colorFilter);
            canvasDrawScope.drawContext.transform.translate(-f, -f2);
            layoutNodeDrawScope.drawContent();
        } catch (Throwable th) {
            canvasDrawScope.drawContext.transform.translate(-f, -f2);
            throw th;
        }
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final boolean getShouldAutoInvalidate() {
        return false;
    }

    public final boolean getUseIntrinsicSize() {
        return this.sizeToIntrinsics && this.painter.mo436getIntrinsicSizeNHjbRc() != 9205357640488583168L;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.maxIntrinsicHeight(i);
        }
        long m282modifyConstraintsZezNO4M = m282modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13));
        return Math.max(Constraints.m656getMinHeightimpl(m282modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.maxIntrinsicWidth(i);
        }
        long m282modifyConstraintsZezNO4M = m282modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7));
        return Math.max(Constraints.m657getMinWidthimpl(m282modifyConstraintsZezNO4M), intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(m282modifyConstraintsZezNO4M(j));
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.draw.PainterNode$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).placeRelative(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.minIntrinsicHeight(i);
        }
        long m282modifyConstraintsZezNO4M = m282modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13));
        return Math.max(Constraints.m656getMinHeightimpl(m282modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (!getUseIntrinsicSize()) {
            return intrinsicMeasurable.minIntrinsicWidth(i);
        }
        long m282modifyConstraintsZezNO4M = m282modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7));
        return Math.max(Constraints.m657getMinWidthimpl(m282modifyConstraintsZezNO4M), intrinsicMeasurable.minIntrinsicWidth(i));
    }

    /* renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    public final long m282modifyConstraintsZezNO4M(long j) {
        boolean z = false;
        boolean z2 = Constraints.m651getHasBoundedWidthimpl(j) && Constraints.m650getHasBoundedHeightimpl(j);
        if (Constraints.m653getHasFixedWidthimpl(j) && Constraints.m652getHasFixedHeightimpl(j)) {
            z = true;
        }
        if ((!getUseIntrinsicSize() && z2) || z) {
            return Constraints.m648copyZbe2FdA$default(j, Constraints.m655getMaxWidthimpl(j), 0, Constraints.m654getMaxHeightimpl(j), 0, 10);
        }
        long mo436getIntrinsicSizeNHjbRc = this.painter.mo436getIntrinsicSizeNHjbRc();
        int round = m281hasSpecifiedAndFiniteWidthuvyYCjk(mo436getIntrinsicSizeNHjbRc) ? Math.round(Float.intBitsToFloat((int) (mo436getIntrinsicSizeNHjbRc >> 32))) : Constraints.m657getMinWidthimpl(j);
        int round2 = m280hasSpecifiedAndFiniteHeightuvyYCjk(mo436getIntrinsicSizeNHjbRc) ? Math.round(Float.intBitsToFloat((int) (mo436getIntrinsicSizeNHjbRc & 4294967295L))) : Constraints.m656getMinHeightimpl(j);
        long floatToRawIntBits = (Float.floatToRawIntBits(ConstraintsKt.m665constrainWidthK40F9xA(j, round)) << 32) | (Float.floatToRawIntBits(ConstraintsKt.m664constrainHeightK40F9xA(j, round2)) & 4294967295L);
        if (getUseIntrinsicSize()) {
            long floatToRawIntBits2 = (Float.floatToRawIntBits(!m281hasSpecifiedAndFiniteWidthuvyYCjk(this.painter.mo436getIntrinsicSizeNHjbRc()) ? Float.intBitsToFloat((int) (floatToRawIntBits >> 32)) : Float.intBitsToFloat((int) (this.painter.mo436getIntrinsicSizeNHjbRc() >> 32))) << 32) | (Float.floatToRawIntBits(!m280hasSpecifiedAndFiniteHeightuvyYCjk(this.painter.mo436getIntrinsicSizeNHjbRc()) ? Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)) : Float.intBitsToFloat((int) (this.painter.mo436getIntrinsicSizeNHjbRc() & 4294967295L))) & 4294967295L);
            floatToRawIntBits = (Float.intBitsToFloat((int) (floatToRawIntBits >> 32)) == 0.0f || Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)) == 0.0f) ? 0L : ScaleFactorKt.m501timesUQTWf7w(floatToRawIntBits2, this.contentScale.mo476computeScaleFactorH7hwNQA(floatToRawIntBits2, floatToRawIntBits));
        }
        return Constraints.m648copyZbe2FdA$default(j, ConstraintsKt.m665constrainWidthK40F9xA(j, Math.round(Float.intBitsToFloat((int) (floatToRawIntBits >> 32)))), 0, ConstraintsKt.m664constrainHeightK40F9xA(j, Math.round(Float.intBitsToFloat((int) (floatToRawIntBits & 4294967295L)))), 0, 10);
    }

    public final String toString() {
        return "PainterModifier(painter=" + this.painter + ", sizeToIntrinsics=" + this.sizeToIntrinsics + ", alignment=" + this.alignment + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }
}
