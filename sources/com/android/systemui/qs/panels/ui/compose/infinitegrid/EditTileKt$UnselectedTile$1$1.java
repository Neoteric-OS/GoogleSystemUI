package com.android.systemui.qs.panels.ui.compose.infinitegrid;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class EditTileKt$UnselectedTile$1$1 implements MultiContentMeasurePolicy {
    public static final EditTileKt$UnselectedTile$1$1 INSTANCE = new EditTileKt$UnselectedTile$1$1();

    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo91measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        ArrayList arrayList = (ArrayList) list;
        List list2 = (List) arrayList.get(0);
        List list3 = (List) arrayList.get(1);
        final Placeable mo479measureBRTryo0 = ((Measurable) CollectionsKt.first(list2)).mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(j, 0, Constraints.m655getMaxWidthimpl(j), 0, 0, 13));
        final Placeable mo479measureBRTryo02 = ((Measurable) CollectionsKt.first(list3)).mo479measureBRTryo0(j);
        final int i = mo479measureBRTryo0.width - (mo479measureBRTryo02.width / 2);
        final int i2 = (mo479measureBRTryo0.height / 2) - (mo479measureBRTryo02.height / 2);
        layout$1 = measureScope.layout$1(Constraints.m655getMaxWidthimpl(j), Constraints.m654getMaxHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: com.android.systemui.qs.panels.ui.compose.infinitegrid.EditTileKt$UnselectedTile$1$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                placementScope.place(Placeable.this, 0, 0, 0.0f);
                placementScope.place(mo479measureBRTryo02, i, i2, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
