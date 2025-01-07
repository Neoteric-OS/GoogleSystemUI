package androidx.compose.foundation.text.handwriting;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class StylusHandwritingNodeWithNegativePadding extends StylusHandwritingNode implements LayoutModifierNode {
    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(StylusHandwritingKt.HandwritingBoundsVerticalOffset);
        final int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(StylusHandwritingKt.HandwritingBoundsHorizontalOffset);
        int i = mo45roundToPx0680j_42 * 2;
        int i2 = mo45roundToPx0680j_4 * 2;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(i, i2, j));
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width - i, mo479measureBRTryo0.height - i2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.text.handwriting.StylusHandwritingNodeWithNegativePadding$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(mo479measureBRTryo0, -mo45roundToPx0680j_42, -mo45roundToPx0680j_4, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
