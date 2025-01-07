package androidx.compose.animation.graphics.vector;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorizedFiniteAnimationSpec;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CombinedSpec implements FiniteAnimationSpec {
    public final List specs;

    public CombinedSpec(List list) {
        this.specs = list;
    }

    @Override // androidx.compose.animation.core.AnimationSpec
    public final VectorizedFiniteAnimationSpec vectorize(TwoWayConverter twoWayConverter) {
        ArrayList arrayList = (ArrayList) this.specs;
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList2.add(new Pair(Long.valueOf(((Number) r3.component1()).intValue() * 1000000), ((FiniteAnimationSpec) ((Pair) arrayList.get(i)).component2()).vectorize(twoWayConverter)));
        }
        return new VectorizedCombinedSpec(arrayList2);
    }
}
