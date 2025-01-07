package androidx.compose.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Dp;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MinimumInteractiveModifierNode extends Modifier.Node implements CompositionLocalConsumerModifierNode, LayoutModifierNode {
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        float f = 0;
        float coerceAtLeast = RangesKt.coerceAtLeast(((Dp) CompositionLocalConsumerModifierNodeKt.currentValueOf(this, InteractiveComponentSizeKt.LocalMinimumInteractiveComponentSize)).value, f);
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        boolean z = this.isAttached && !Float.isNaN(coerceAtLeast) && Float.compare(coerceAtLeast, f) > 0;
        int mo45roundToPx0680j_4 = Float.isNaN(coerceAtLeast) ? 0 : measureScope.mo45roundToPx0680j_4(coerceAtLeast);
        final int max = z ? Math.max(mo479measureBRTryo0.width, mo45roundToPx0680j_4) : mo479measureBRTryo0.width;
        final int max2 = z ? Math.max(mo479measureBRTryo0.height, mo45roundToPx0680j_4) : mo479measureBRTryo0.height;
        layout$1 = measureScope.layout$1(max, max2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.MinimumInteractiveModifierNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(mo479measureBRTryo0, MathKt.roundToInt((max - mo479measureBRTryo0.width) / 2.0f), MathKt.roundToInt((max2 - mo479measureBRTryo0.height) / 2.0f), 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
