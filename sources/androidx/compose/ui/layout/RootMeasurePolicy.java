package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RootMeasurePolicy extends LayoutNode.NoIntrinsicsMeasurePolicy {
    public static final RootMeasurePolicy INSTANCE = new RootMeasurePolicy("Undefined intrinsics block and it is required");

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        MeasureResult layout$12;
        MeasureResult layout$13;
        if (list.isEmpty()) {
            layout$13 = measureScope.layout$1(Constraints.m657getMinWidthimpl(j), Constraints.m656getMinHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            });
            return layout$13;
        }
        if (list.size() == 1) {
            final Placeable mo479measureBRTryo0 = ((Measurable) list.get(0)).mo479measureBRTryo0(j);
            layout$12 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, mo479measureBRTryo0.width), ConstraintsKt.m664constrainHeightK40F9xA(j, mo479measureBRTryo0.height), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default((Placeable.PlacementScope) obj, Placeable.this, 0, 0);
                    return Unit.INSTANCE;
                }
            });
            return layout$12;
        }
        final ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(((Measurable) list.get(i)).mo479measureBRTryo0(j));
        }
        int size2 = arrayList.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size2; i4++) {
            Placeable placeable = (Placeable) arrayList.get(i4);
            i2 = Math.max(placeable.width, i2);
            i3 = Math.max(placeable.height, i3);
        }
        layout$1 = measureScope.layout$1(ConstraintsKt.m665constrainWidthK40F9xA(j, i2), ConstraintsKt.m664constrainHeightK40F9xA(j, i3), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.RootMeasurePolicy$measure$4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                List list2 = arrayList;
                int size3 = list2.size();
                for (int i5 = 0; i5 < size3; i5++) {
                    Placeable.PlacementScope.placeRelativeWithLayer$default(placementScope, (Placeable) list2.get(i5), 0, 0);
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
