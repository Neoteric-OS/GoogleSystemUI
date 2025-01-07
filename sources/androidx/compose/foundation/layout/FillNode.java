package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FillNode extends Modifier.Node implements LayoutModifierNode {
    public Direction direction;
    public float fraction;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m657getMinWidthimpl;
        int m655getMaxWidthimpl;
        int m656getMinHeightimpl;
        int m654getMaxHeightimpl;
        MeasureResult layout$1;
        if (!Constraints.m651getHasBoundedWidthimpl(j) || this.direction == Direction.Vertical) {
            m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
            m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        } else {
            m657getMinWidthimpl = RangesKt.coerceIn(Math.round(Constraints.m655getMaxWidthimpl(j) * this.fraction), Constraints.m657getMinWidthimpl(j), Constraints.m655getMaxWidthimpl(j));
            m655getMaxWidthimpl = m657getMinWidthimpl;
        }
        if (!Constraints.m650getHasBoundedHeightimpl(j) || this.direction == Direction.Horizontal) {
            m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
            m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
        } else {
            m656getMinHeightimpl = RangesKt.coerceIn(Math.round(Constraints.m654getMaxHeightimpl(j) * this.fraction), Constraints.m656getMinHeightimpl(j), Constraints.m654getMaxHeightimpl(j));
            m654getMaxHeightimpl = m656getMinHeightimpl;
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.Constraints(m657getMinWidthimpl, m655getMaxWidthimpl, m656getMinHeightimpl, m654getMaxHeightimpl));
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FillNode$measure$1
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
}
