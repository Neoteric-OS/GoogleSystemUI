package androidx.compose.foundation.text;

import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LinksTextMeasurePolicy implements MeasurePolicy {
    public final Function0 shouldMeasureLinks;

    public LinksTextMeasurePolicy(Function0 function0) {
        this.shouldMeasureLinks = function0;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(MeasureScope measureScope, final List list, long j) {
        MeasureResult layout$1;
        layout$1 = measureScope.layout$1(Constraints.m655getMaxWidthimpl(j), Constraints.m654getMaxHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.text.LinksTextMeasurePolicy$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                List access$measureWithTextRangeMeasureConstraints = BasicTextKt.access$measureWithTextRangeMeasureConstraints(list, this.shouldMeasureLinks);
                if (access$measureWithTextRangeMeasureConstraints != null) {
                    int size = access$measureWithTextRangeMeasureConstraints.size();
                    for (int i = 0; i < size; i++) {
                        Pair pair = (Pair) access$measureWithTextRangeMeasureConstraints.get(i);
                        Placeable placeable = (Placeable) pair.component1();
                        Function0 function0 = (Function0) pair.component2();
                        Placeable.PlacementScope.m495place70tqf50$default(placementScope, placeable, function0 != null ? ((IntOffset) function0.invoke()).packedValue : 0L);
                    }
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }
}
