package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class WrapContentNode extends Modifier.Node implements LayoutModifierNode {
    public Lambda alignmentCallback;
    public Direction direction;
    public boolean unbounded;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        Direction direction = this.direction;
        Direction direction2 = Direction.Vertical;
        int m657getMinWidthimpl = direction != direction2 ? 0 : Constraints.m657getMinWidthimpl(j);
        Direction direction3 = this.direction;
        Direction direction4 = Direction.Horizontal;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.Constraints(m657getMinWidthimpl, (this.direction == direction2 || !this.unbounded) ? Constraints.m655getMaxWidthimpl(j) : Integer.MAX_VALUE, direction3 == direction4 ? Constraints.m656getMinHeightimpl(j) : 0, (this.direction == direction4 || !this.unbounded) ? Constraints.m654getMaxHeightimpl(j) : Integer.MAX_VALUE));
        final int coerceIn = RangesKt.coerceIn(mo479measureBRTryo0.width, Constraints.m657getMinWidthimpl(j), Constraints.m655getMaxWidthimpl(j));
        final int coerceIn2 = RangesKt.coerceIn(mo479measureBRTryo0.height, Constraints.m656getMinHeightimpl(j), Constraints.m654getMaxHeightimpl(j));
        layout$1 = measureScope.layout$1(coerceIn, coerceIn2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.WrapContentNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r0v1, types: [kotlin.jvm.functions.Function2, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ?? r0 = WrapContentNode.this.alignmentCallback;
                int i = coerceIn;
                Placeable placeable = mo479measureBRTryo0;
                Placeable.PlacementScope.m495place70tqf50$default((Placeable.PlacementScope) obj, mo479measureBRTryo0, ((IntOffset) r0.invoke(new IntSize(((i - placeable.width) << 32) | ((coerceIn2 - placeable.height) & 4294967295L)), measureScope.getLayoutDirection())).packedValue);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
