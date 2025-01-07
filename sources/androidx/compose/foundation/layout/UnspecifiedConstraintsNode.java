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
final class UnspecifiedConstraintsNode extends Modifier.Node implements LayoutModifierNode {
    public float minHeight;
    public float minWidth;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int maxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(i);
        int mo45roundToPx0680j_4 = !Dp.m668equalsimpl0(this.minHeight, Float.NaN) ? lookaheadCapablePlaceable.mo45roundToPx0680j_4(this.minHeight) : 0;
        return maxIntrinsicHeight < mo45roundToPx0680j_4 ? mo45roundToPx0680j_4 : maxIntrinsicHeight;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int maxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(i);
        int mo45roundToPx0680j_4 = !Dp.m668equalsimpl0(this.minWidth, Float.NaN) ? lookaheadCapablePlaceable.mo45roundToPx0680j_4(this.minWidth) : 0;
        return maxIntrinsicWidth < mo45roundToPx0680j_4 ? mo45roundToPx0680j_4 : maxIntrinsicWidth;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m657getMinWidthimpl;
        MeasureResult layout$1;
        int i = 0;
        if (Dp.m668equalsimpl0(this.minWidth, Float.NaN) || Constraints.m657getMinWidthimpl(j) != 0) {
            m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        } else {
            m657getMinWidthimpl = measureScope.mo45roundToPx0680j_4(this.minWidth);
            int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
            if (m657getMinWidthimpl > m655getMaxWidthimpl) {
                m657getMinWidthimpl = m655getMaxWidthimpl;
            }
            if (m657getMinWidthimpl < 0) {
                m657getMinWidthimpl = 0;
            }
        }
        int m655getMaxWidthimpl2 = Constraints.m655getMaxWidthimpl(j);
        if (Dp.m668equalsimpl0(this.minHeight, Float.NaN) || Constraints.m656getMinHeightimpl(j) != 0) {
            i = Constraints.m656getMinHeightimpl(j);
        } else {
            int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(this.minHeight);
            int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
            if (mo45roundToPx0680j_4 > m654getMaxHeightimpl) {
                mo45roundToPx0680j_4 = m654getMaxHeightimpl;
            }
            if (mo45roundToPx0680j_4 >= 0) {
                i = mo45roundToPx0680j_4;
            }
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.Constraints(m657getMinWidthimpl, m655getMaxWidthimpl2, i, Constraints.m654getMaxHeightimpl(j)));
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.UnspecifiedConstraintsNode$measure$1
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
        int minIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(i);
        int mo45roundToPx0680j_4 = !Dp.m668equalsimpl0(this.minHeight, Float.NaN) ? lookaheadCapablePlaceable.mo45roundToPx0680j_4(this.minHeight) : 0;
        return minIntrinsicHeight < mo45roundToPx0680j_4 ? mo45roundToPx0680j_4 : minIntrinsicHeight;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int minIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(i);
        int mo45roundToPx0680j_4 = !Dp.m668equalsimpl0(this.minWidth, Float.NaN) ? lookaheadCapablePlaceable.mo45roundToPx0680j_4(this.minWidth) : 0;
        return minIntrinsicWidth < mo45roundToPx0680j_4 ? mo45roundToPx0680j_4 : minIntrinsicWidth;
    }
}
