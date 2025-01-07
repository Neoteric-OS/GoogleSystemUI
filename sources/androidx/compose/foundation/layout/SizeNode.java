package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SizeNode extends Modifier.Node implements LayoutModifierNode {
    public boolean enforceIncoming;
    public float maxHeight;
    public float maxWidth;
    public float minHeight;
    public float minWidth;

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0040, code lost:
    
        if (r5 != Integer.MAX_VALUE) goto L24;
     */
    /* renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long m119getTargetConstraintsOenEA2s(androidx.compose.ui.layout.IntrinsicMeasureScope r8) {
        /*
            r7 = this;
            float r0 = r7.maxWidth
            r1 = 2143289344(0x7fc00000, float:NaN)
            boolean r0 = androidx.compose.ui.unit.Dp.m668equalsimpl0(r0, r1)
            r2 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            if (r0 != 0) goto L18
            float r0 = r7.maxWidth
            int r0 = r8.mo45roundToPx0680j_4(r0)
            if (r0 >= 0) goto L19
            r0 = r3
            goto L19
        L18:
            r0 = r2
        L19:
            float r4 = r7.maxHeight
            boolean r4 = androidx.compose.ui.unit.Dp.m668equalsimpl0(r4, r1)
            if (r4 != 0) goto L2b
            float r4 = r7.maxHeight
            int r4 = r8.mo45roundToPx0680j_4(r4)
            if (r4 >= 0) goto L2c
            r4 = r3
            goto L2c
        L2b:
            r4 = r2
        L2c:
            float r5 = r7.minWidth
            boolean r5 = androidx.compose.ui.unit.Dp.m668equalsimpl0(r5, r1)
            if (r5 != 0) goto L43
            float r5 = r7.minWidth
            int r5 = r8.mo45roundToPx0680j_4(r5)
            if (r5 <= r0) goto L3d
            r5 = r0
        L3d:
            if (r5 >= 0) goto L40
            r5 = r3
        L40:
            if (r5 == r2) goto L43
            goto L44
        L43:
            r5 = r3
        L44:
            float r6 = r7.minHeight
            boolean r1 = androidx.compose.ui.unit.Dp.m668equalsimpl0(r6, r1)
            if (r1 != 0) goto L5b
            float r7 = r7.minHeight
            int r7 = r8.mo45roundToPx0680j_4(r7)
            if (r7 <= r4) goto L55
            r7 = r4
        L55:
            if (r7 >= 0) goto L58
            r7 = r3
        L58:
            if (r7 == r2) goto L5b
            r3 = r7
        L5b:
            long r7 = androidx.compose.ui.unit.ConstraintsKt.Constraints(r5, r0, r3, r4)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.SizeNode.m119getTargetConstraintsOenEA2s(androidx.compose.ui.layout.IntrinsicMeasureScope):long");
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m119getTargetConstraintsOenEA2s = m119getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m652getHasFixedHeightimpl(m119getTargetConstraintsOenEA2s) ? Constraints.m654getMaxHeightimpl(m119getTargetConstraintsOenEA2s) : ConstraintsKt.m664constrainHeightK40F9xA(m119getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m119getTargetConstraintsOenEA2s = m119getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m653getHasFixedWidthimpl(m119getTargetConstraintsOenEA2s) ? Constraints.m655getMaxWidthimpl(m119getTargetConstraintsOenEA2s) : ConstraintsKt.m665constrainWidthK40F9xA(m119getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m657getMinWidthimpl;
        int m655getMaxWidthimpl;
        int m656getMinHeightimpl;
        int m654getMaxHeightimpl;
        long Constraints;
        MeasureResult layout$1;
        long m119getTargetConstraintsOenEA2s = m119getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            Constraints = ConstraintsKt.m663constrainN9IONVI(j, m119getTargetConstraintsOenEA2s);
        } else {
            if (Dp.m668equalsimpl0(this.minWidth, Float.NaN)) {
                m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
                int m655getMaxWidthimpl2 = Constraints.m655getMaxWidthimpl(m119getTargetConstraintsOenEA2s);
                if (m657getMinWidthimpl > m655getMaxWidthimpl2) {
                    m657getMinWidthimpl = m655getMaxWidthimpl2;
                }
            } else {
                m657getMinWidthimpl = Constraints.m657getMinWidthimpl(m119getTargetConstraintsOenEA2s);
            }
            if (Dp.m668equalsimpl0(this.maxWidth, Float.NaN)) {
                m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
                int m657getMinWidthimpl2 = Constraints.m657getMinWidthimpl(m119getTargetConstraintsOenEA2s);
                if (m655getMaxWidthimpl < m657getMinWidthimpl2) {
                    m655getMaxWidthimpl = m657getMinWidthimpl2;
                }
            } else {
                m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(m119getTargetConstraintsOenEA2s);
            }
            if (Dp.m668equalsimpl0(this.minHeight, Float.NaN)) {
                m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
                int m654getMaxHeightimpl2 = Constraints.m654getMaxHeightimpl(m119getTargetConstraintsOenEA2s);
                if (m656getMinHeightimpl > m654getMaxHeightimpl2) {
                    m656getMinHeightimpl = m654getMaxHeightimpl2;
                }
            } else {
                m656getMinHeightimpl = Constraints.m656getMinHeightimpl(m119getTargetConstraintsOenEA2s);
            }
            if (Dp.m668equalsimpl0(this.maxHeight, Float.NaN)) {
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
                int m656getMinHeightimpl2 = Constraints.m656getMinHeightimpl(m119getTargetConstraintsOenEA2s);
                if (m654getMaxHeightimpl < m656getMinHeightimpl2) {
                    m654getMaxHeightimpl = m656getMinHeightimpl2;
                }
            } else {
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(m119getTargetConstraintsOenEA2s);
            }
            Constraints = ConstraintsKt.Constraints(m657getMinWidthimpl, m655getMaxWidthimpl, m656getMinHeightimpl, m654getMaxHeightimpl);
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.SizeNode$measure$1
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
        long m119getTargetConstraintsOenEA2s = m119getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m652getHasFixedHeightimpl(m119getTargetConstraintsOenEA2s) ? Constraints.m654getMaxHeightimpl(m119getTargetConstraintsOenEA2s) : ConstraintsKt.m664constrainHeightK40F9xA(m119getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m119getTargetConstraintsOenEA2s = m119getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m653getHasFixedWidthimpl(m119getTargetConstraintsOenEA2s) ? Constraints.m655getMaxWidthimpl(m119getTargetConstraintsOenEA2s) : ConstraintsKt.m665constrainWidthK40F9xA(m119getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i));
    }
}
