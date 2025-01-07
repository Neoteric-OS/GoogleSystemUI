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
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AspectRatioNode extends Modifier.Node implements LayoutModifierNode {
    public float aspectRatio;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i / this.aspectRatio) : intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i * this.aspectRatio) : intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        long m84tryMaxWidthJN0ABg = m84tryMaxWidthJN0ABg(j, true);
        if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
            m84tryMaxWidthJN0ABg = m83tryMaxHeightJN0ABg(j, true);
            if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                m84tryMaxWidthJN0ABg = m86tryMinWidthJN0ABg(j, true);
                if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                    m84tryMaxWidthJN0ABg = m85tryMinHeightJN0ABg(j, true);
                    if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                        m84tryMaxWidthJN0ABg = m84tryMaxWidthJN0ABg(j, false);
                        if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                            m84tryMaxWidthJN0ABg = m83tryMaxHeightJN0ABg(j, false);
                            if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                                m84tryMaxWidthJN0ABg = m86tryMinWidthJN0ABg(j, false);
                                if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                                    m84tryMaxWidthJN0ABg = m85tryMinHeightJN0ABg(j, false);
                                    if (IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
                                        m84tryMaxWidthJN0ABg = 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!IntSize.m683equalsimpl0(m84tryMaxWidthJN0ABg, 0L)) {
            j = Constraints.Companion.m661fixedJhjzzOo((int) (m84tryMaxWidthJN0ABg >> 32), (int) (4294967295L & m84tryMaxWidthJN0ABg));
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.AspectRatioNode$measure$1
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
        return i != Integer.MAX_VALUE ? Math.round(i / this.aspectRatio) : intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return i != Integer.MAX_VALUE ? Math.round(i * this.aspectRatio) : intrinsicMeasurable.minIntrinsicWidth(i);
    }

    /* renamed from: tryMaxHeight-JN-0ABg, reason: not valid java name */
    public final long m83tryMaxHeightJN0ABg(long j, boolean z) {
        int round;
        int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        if (m654getMaxHeightimpl == Integer.MAX_VALUE || (round = Math.round(m654getMaxHeightimpl * this.aspectRatio)) <= 0) {
            return 0L;
        }
        if (!z || AspectRatioKt.m82isSatisfiedByNN6EwU(round, m654getMaxHeightimpl, j)) {
            return (round << 32) | (m654getMaxHeightimpl & 4294967295L);
        }
        return 0L;
    }

    /* renamed from: tryMaxWidth-JN-0ABg, reason: not valid java name */
    public final long m84tryMaxWidthJN0ABg(long j, boolean z) {
        int round;
        int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        if (m655getMaxWidthimpl == Integer.MAX_VALUE || (round = Math.round(m655getMaxWidthimpl / this.aspectRatio)) <= 0) {
            return 0L;
        }
        if (!z || AspectRatioKt.m82isSatisfiedByNN6EwU(m655getMaxWidthimpl, round, j)) {
            return (m655getMaxWidthimpl << 32) | (round & 4294967295L);
        }
        return 0L;
    }

    /* renamed from: tryMinHeight-JN-0ABg, reason: not valid java name */
    public final long m85tryMinHeightJN0ABg(long j, boolean z) {
        int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        int round = Math.round(m656getMinHeightimpl * this.aspectRatio);
        if (round <= 0) {
            return 0L;
        }
        if (!z || AspectRatioKt.m82isSatisfiedByNN6EwU(round, m656getMinHeightimpl, j)) {
            return (round << 32) | (m656getMinHeightimpl & 4294967295L);
        }
        return 0L;
    }

    /* renamed from: tryMinWidth-JN-0ABg, reason: not valid java name */
    public final long m86tryMinWidthJN0ABg(long j, boolean z) {
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        int round = Math.round(m657getMinWidthimpl / this.aspectRatio);
        if (round <= 0) {
            return 0L;
        }
        if (!z || AspectRatioKt.m82isSatisfiedByNN6EwU(m657getMinWidthimpl, round, j)) {
            return (m657getMinWidthimpl << 32) | (round & 4294967295L);
        }
        return 0L;
    }
}
