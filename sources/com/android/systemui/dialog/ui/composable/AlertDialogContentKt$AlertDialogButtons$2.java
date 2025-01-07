package com.android.systemui.dialog.ui.composable;

import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlertDialogContentKt$AlertDialogButtons$2 implements MeasurePolicy {
    public static final AlertDialogContentKt$AlertDialogButtons$2 INSTANCE = new AlertDialogContentKt$AlertDialogButtons$2();

    public static final int measure_3p2s80s$height(Placeable placeable) {
        if (placeable != null) {
            return placeable.height;
        }
        return 0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        MeasureResult layout$12;
        if (!Constraints.m651getHasBoundedWidthimpl(j)) {
            throw new IllegalStateException("AlertDialogButtons should not be composed in an horizontally scrollable layout");
        }
        final int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
        final Ref$ObjectRef ref$ObjectRef3 = new Ref$ObjectRef();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = (Measurable) list.get(i);
            Object layoutId = LayoutIdKt.getLayoutId(measurable);
            if (Intrinsics.areEqual(layoutId, "positive")) {
                ref$ObjectRef.element = measurable.mo479measureBRTryo0(j);
            } else if (Intrinsics.areEqual(layoutId, "negative")) {
                ref$ObjectRef2.element = measurable.mo479measureBRTryo0(j);
            } else {
                if (!Intrinsics.areEqual(layoutId, "neutral")) {
                    throw new IllegalStateException(("Unexpected layoutId=" + layoutId).toString());
                }
                ref$ObjectRef3.element = measurable.mo479measureBRTryo0(j);
            }
        }
        final float mo51toPx0680j_4 = measureScope.mo51toPx0680j_4(8);
        float size2 = (list.size() - 1) * mo51toPx0680j_4;
        Object obj = ref$ObjectRef.element;
        Placeable placeable = (Placeable) obj;
        int i2 = placeable != null ? placeable.width : 0;
        Placeable placeable2 = (Placeable) ref$ObjectRef2.element;
        int i3 = (placeable2 != null ? placeable2.width : 0) + i2;
        if ((((Placeable) ref$ObjectRef3.element) != null ? r13.width : 0) + i3 + size2 <= m655getMaxWidthimpl) {
            layout$12 = measureScope.layout$1(m655getMaxWidthimpl, Math.max(measure_3p2s80s$height((Placeable) obj), Math.max(measure_3p2s80s$height((Placeable) ref$ObjectRef2.element), measure_3p2s80s$height((Placeable) ref$ObjectRef3.element))), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.dialog.ui.composable.AlertDialogContentKt$AlertDialogButtons$2.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj2;
                    Placeable placeable3 = (Placeable) Ref$ObjectRef.this.element;
                    if (placeable3 != null) {
                        placementScope.placeRelative(placeable3, m655getMaxWidthimpl - placeable3.width, 0, 0.0f);
                    }
                    Placeable placeable4 = (Placeable) ref$ObjectRef2.element;
                    if (placeable4 != null) {
                        Ref$ObjectRef ref$ObjectRef4 = Ref$ObjectRef.this;
                        int i4 = m655getMaxWidthimpl;
                        float f = mo51toPx0680j_4;
                        Object obj3 = ref$ObjectRef4.element;
                        if (obj3 == null) {
                            placementScope.placeRelative(placeable4, i4 - placeable4.width, 0, 0.0f);
                        } else {
                            placementScope.placeRelative(placeable4, ((i4 - placeable4.width) - ((Placeable) obj3).width) - MathKt.roundToInt(f), 0, 0.0f);
                        }
                    }
                    Placeable placeable5 = (Placeable) ref$ObjectRef3.element;
                    if (placeable5 != null) {
                        placementScope.placeRelative(placeable5, 0, 0, 0.0f);
                    }
                    return Unit.INSTANCE;
                }
            });
            return layout$12;
        }
        layout$1 = measureScope.layout$1(m655getMaxWidthimpl, measure_3p2s80s$height((Placeable) ref$ObjectRef3.element) + measure_3p2s80s$height((Placeable) ref$ObjectRef2.element) + measure_3p2s80s$height((Placeable) obj), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.dialog.ui.composable.AlertDialogContentKt$AlertDialogButtons$2.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public static final void invoke$place(Placeable placeable3, Placeable.PlacementScope placementScope, int i4, Ref$IntRef ref$IntRef) {
                placementScope.placeRelative(placeable3, i4 - placeable3.width, ref$IntRef.element, 0.0f);
                ref$IntRef.element += placeable3.height;
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj2;
                Ref$IntRef ref$IntRef = new Ref$IntRef();
                Placeable placeable3 = (Placeable) Ref$ObjectRef.this.element;
                if (placeable3 != null) {
                    invoke$place(placeable3, placementScope, m655getMaxWidthimpl, ref$IntRef);
                }
                Placeable placeable4 = (Placeable) ref$ObjectRef2.element;
                if (placeable4 != null) {
                    invoke$place(placeable4, placementScope, m655getMaxWidthimpl, ref$IntRef);
                }
                Placeable placeable5 = (Placeable) ref$ObjectRef3.element;
                if (placeable5 != null) {
                    invoke$place(placeable5, placementScope, m655getMaxWidthimpl, ref$IntRef);
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
