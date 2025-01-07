package androidx.compose.ui.window;

import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AndroidPopup_androidKt$SimpleStack$1 implements MeasurePolicy {
    public static final AndroidPopup_androidKt$SimpleStack$1 INSTANCE = new AndroidPopup_androidKt$SimpleStack$1();

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        MeasureResult layout$12;
        int i;
        MeasureResult layout$13;
        int size = list.size();
        int i2 = 0;
        if (size == 0) {
            layout$1 = measureScope.layout$1(0, 0, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1.1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            });
            return layout$1;
        }
        if (size == 1) {
            final Placeable mo479measureBRTryo0 = ((Measurable) list.get(0)).mo479measureBRTryo0(j);
            layout$12 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((Placeable.PlacementScope) obj).placeRelative(Placeable.this, 0, 0, 0.0f);
                    return Unit.INSTANCE;
                }
            });
            return layout$12;
        }
        final ArrayList arrayList = new ArrayList(list.size());
        int size2 = list.size();
        for (int i3 = 0; i3 < size2; i3++) {
            arrayList.add(((Measurable) list.get(i3)).mo479measureBRTryo0(j));
        }
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
        if (lastIndex >= 0) {
            int i4 = 0;
            i = 0;
            while (true) {
                Placeable placeable = (Placeable) arrayList.get(i2);
                i4 = Math.max(i4, placeable.width);
                i = Math.max(i, placeable.height);
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
            i2 = i4;
        } else {
            i = 0;
        }
        layout$13 = measureScope.layout$1(i2, i, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$SimpleStack$1.3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                int lastIndex2 = CollectionsKt__CollectionsKt.getLastIndex(arrayList);
                if (lastIndex2 >= 0) {
                    int i5 = 0;
                    while (true) {
                        placementScope.placeRelative((Placeable) arrayList.get(i5), 0, 0, 0.0f);
                        if (i5 == lastIndex2) {
                            break;
                        }
                        i5++;
                    }
                }
                return Unit.INSTANCE;
            }
        });
        return layout$13;
    }
}
