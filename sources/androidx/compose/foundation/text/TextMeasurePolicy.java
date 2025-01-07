package androidx.compose.foundation.text;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextMeasurePolicy implements MeasurePolicy {
    public final Function0 placements;
    public final Function0 shouldMeasureLinks;

    public TextMeasurePolicy(Function0 function0, Function0 function02) {
        this.shouldMeasureLinks = function0;
        this.placements = function02;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        Pair pair;
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Object obj = list.get(i);
            if (!(((Measurable) obj).getParentData() instanceof TextRangeLayoutModifier)) {
                arrayList.add(obj);
            }
        }
        List list2 = (List) this.placements.invoke();
        final ArrayList arrayList2 = null;
        if (list2 != null) {
            ArrayList arrayList3 = new ArrayList(list2.size());
            int size2 = list2.size();
            for (int i2 = 0; i2 < size2; i2++) {
                Rect rect = (Rect) list2.get(i2);
                if (rect != null) {
                    Measurable measurable = (Measurable) arrayList.get(i2);
                    float f = rect.right;
                    float f2 = rect.left;
                    float f3 = rect.bottom;
                    float f4 = rect.top;
                    pair = new Pair(measurable.mo479measureBRTryo0(ConstraintsKt.Constraints$default(0, (int) Math.floor(f - f2), 0, (int) Math.floor(f3 - f4), 5)), new IntOffset((Math.round(f2) << 32) | (Math.round(f4) & 4294967295L)));
                } else {
                    pair = null;
                }
                if (pair != null) {
                    arrayList3.add(pair);
                }
            }
            arrayList2 = arrayList3;
        }
        ArrayList arrayList4 = new ArrayList(list.size());
        int size3 = list.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Object obj2 = list.get(i3);
            if (((Measurable) obj2).getParentData() instanceof TextRangeLayoutModifier) {
                arrayList4.add(obj2);
            }
        }
        final List access$measureWithTextRangeMeasureConstraints = BasicTextKt.access$measureWithTextRangeMeasureConstraints(arrayList4, this.shouldMeasureLinks);
        layout$1 = measureScope.layout$1(Constraints.m655getMaxWidthimpl(j), Constraints.m654getMaxHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.text.TextMeasurePolicy$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj3;
                List list3 = arrayList2;
                if (list3 != null) {
                    int size4 = list3.size();
                    for (int i4 = 0; i4 < size4; i4++) {
                        Pair pair2 = (Pair) list3.get(i4);
                        Placeable.PlacementScope.m495place70tqf50$default(placementScope, (Placeable) pair2.component1(), ((IntOffset) pair2.component2()).packedValue);
                    }
                }
                List list4 = access$measureWithTextRangeMeasureConstraints;
                if (list4 != null) {
                    int size5 = list4.size();
                    for (int i5 = 0; i5 < size5; i5++) {
                        Pair pair3 = (Pair) list4.get(i5);
                        Placeable placeable = (Placeable) pair3.component1();
                        Function0 function0 = (Function0) pair3.component2();
                        Placeable.PlacementScope.m495place70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).packedValue : 0L);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
