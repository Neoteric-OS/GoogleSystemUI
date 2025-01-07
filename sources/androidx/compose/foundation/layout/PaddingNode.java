package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
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
final class PaddingNode extends Modifier.Node implements LayoutModifierNode {
    public float bottom;
    public float end;
    public boolean rtlAware;
    public float start;
    public float top;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(this.end) + measureScope.mo45roundToPx0680j_4(this.start);
        int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(this.bottom) + measureScope.mo45roundToPx0680j_4(this.top);
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(-mo45roundToPx0680j_4, -mo45roundToPx0680j_42, j));
        layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width + mo45roundToPx0680j_4), ConstraintsKt.m664constrainHeightK40F9xA(j, mo479measureBRTryo0.height + mo45roundToPx0680j_42), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.PaddingNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                PaddingNode paddingNode = PaddingNode.this;
                if (paddingNode.rtlAware) {
                    placementScope.placeRelative(mo479measureBRTryo0, measureScope.mo45roundToPx0680j_4(paddingNode.start), measureScope.mo45roundToPx0680j_4(PaddingNode.this.top), 0.0f);
                } else {
                    placementScope.place(mo479measureBRTryo0, measureScope.mo45roundToPx0680j_4(paddingNode.start), measureScope.mo45roundToPx0680j_4(PaddingNode.this.top), 0.0f);
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
