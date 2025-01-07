package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
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
final class PaddingValuesModifier extends Modifier.Node implements LayoutModifierNode {
    public PaddingValues paddingValues;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        float f = 0;
        if (Float.compare(this.paddingValues.mo104calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()), f) < 0 || Float.compare(this.paddingValues.mo106calculateTopPaddingD9Ej5fM(), f) < 0 || Float.compare(this.paddingValues.mo105calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection()), f) < 0 || Float.compare(this.paddingValues.mo103calculateBottomPaddingD9Ej5fM(), f) < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("Padding must be non-negative");
        }
        int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(this.paddingValues.mo105calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo45roundToPx0680j_4(this.paddingValues.mo104calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()));
        int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(this.paddingValues.mo103calculateBottomPaddingD9Ej5fM()) + measureScope.mo45roundToPx0680j_4(this.paddingValues.mo106calculateTopPaddingD9Ej5fM());
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(-mo45roundToPx0680j_4, -mo45roundToPx0680j_42, j));
        layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width + mo45roundToPx0680j_4), ConstraintsKt.m664constrainHeightK40F9xA(j, mo479measureBRTryo0.height + mo45roundToPx0680j_42), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.PaddingValuesModifier$measure$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable placeable = Placeable.this;
                MeasureScope measureScope2 = measureScope;
                ((Placeable.PlacementScope) obj).place(placeable, measureScope2.mo45roundToPx0680j_4(this.paddingValues.mo104calculateLeftPaddingu2uoSUM(measureScope2.getLayoutDirection())), measureScope.mo45roundToPx0680j_4(this.paddingValues.mo106calculateTopPaddingD9Ej5fM()), 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
