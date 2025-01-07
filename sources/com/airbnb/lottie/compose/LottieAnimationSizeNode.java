package com.airbnb.lottie.compose;

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

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieAnimationSizeNode extends Modifier.Node implements LayoutModifierNode {
    public int height;
    public int width;

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long Constraints;
        MeasureResult layout$1;
        long m662constrain4WqzIAM = ConstraintsKt.m662constrain4WqzIAM(j, (this.width << 32) | (this.height & 4294967295L));
        if (Constraints.m654getMaxHeightimpl(j) == Integer.MAX_VALUE && Constraints.m655getMaxWidthimpl(j) != Integer.MAX_VALUE) {
            int i = (int) (m662constrain4WqzIAM >> 32);
            int i2 = (this.height * i) / this.width;
            Constraints = ConstraintsKt.Constraints(i, i, i2, i2);
        } else if (Constraints.m655getMaxWidthimpl(j) != Integer.MAX_VALUE || Constraints.m654getMaxHeightimpl(j) == Integer.MAX_VALUE) {
            int i3 = (int) (m662constrain4WqzIAM >> 32);
            int i4 = (int) (m662constrain4WqzIAM & 4294967295L);
            Constraints = ConstraintsKt.Constraints(i3, i3, i4, i4);
        } else {
            int i5 = (int) (m662constrain4WqzIAM & 4294967295L);
            int i6 = (this.width * i5) / this.height;
            Constraints = ConstraintsKt.Constraints(i6, i6, i5, i5);
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.airbnb.lottie.compose.LottieAnimationSizeNode$measure$1
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
