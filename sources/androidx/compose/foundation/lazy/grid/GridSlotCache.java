package androidx.compose.foundation.lazy.grid;

import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class GridSlotCache implements LazyGridSlotsProvider {
    public long cachedConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
    public float cachedDensity;
    public LazyGridSlots cachedSizes;
    public final Lambda calculation;

    /* JADX WARN: Multi-variable type inference failed */
    public GridSlotCache(Function2 function2) {
        this.calculation = (Lambda) function2;
    }
}
