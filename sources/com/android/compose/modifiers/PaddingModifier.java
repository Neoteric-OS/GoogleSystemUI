package com.android.compose.modifiers;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PaddingModifier extends InspectorValueInfo implements LayoutModifier {
    public final Function1 bottom;
    public final Function1 end;
    public final Function1 start;
    public final Function1 top;

    public PaddingModifier(Function1 function1, Function1 function12, Function1 function13, Function1 function14) {
        this.start = function1;
        this.top = function12;
        this.end = function13;
        this.bottom = function14;
    }

    public final boolean equals(Object obj) {
        PaddingModifier paddingModifier = obj instanceof PaddingModifier ? (PaddingModifier) obj : null;
        return paddingModifier != null && Intrinsics.areEqual(this.start, paddingModifier.start) && Intrinsics.areEqual(this.top, paddingModifier.top) && Intrinsics.areEqual(this.end, paddingModifier.end) && Intrinsics.areEqual(this.bottom, paddingModifier.bottom);
    }

    public final int hashCode() {
        return Boolean.hashCode(true) + ChangeSize$$ExternalSyntheticOutline0.m(this.bottom, ChangeSize$$ExternalSyntheticOutline0.m(this.end, ChangeSize$$ExternalSyntheticOutline0.m(this.top, this.start.hashCode() * 31, 31), 31), 31);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo3measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final int intValue = ((Number) this.start.invoke(measureScope)).intValue();
        final int intValue2 = ((Number) this.top.invoke(measureScope)).intValue();
        int intValue3 = ((Number) this.end.invoke(measureScope)).intValue() + intValue;
        int intValue4 = ((Number) this.bottom.invoke(measureScope)).intValue() + intValue2;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(-intValue3, -intValue4, j));
        layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width + intValue3), ConstraintsKt.m664constrainHeightK40F9xA(j, mo479measureBRTryo0.height + intValue4), MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.modifiers.PaddingModifier$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                PaddingModifier.this.getClass();
                ((Placeable.PlacementScope) obj).placeRelative(mo479measureBRTryo0, intValue, intValue2, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
