package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DensityKt;
import java.util.Map;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyListStateKt {
    public static final float DeltaThresholdForScrollAnimation = 1;
    public static final LazyListMeasureResult EmptyLazyListMeasureResult = new LazyListMeasureResult(null, 0, false, 0.0f, new MeasureResult() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$EmptyLazyListMeasureResult$1
        public final Map alignmentLines = MapsKt.emptyMap();

        @Override // androidx.compose.ui.layout.MeasureResult
        public final Map getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final int getHeight() {
            return 0;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final int getWidth() {
            return 0;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public final void placeChildren() {
        }
    }, 0.0f, false, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), DensityKt.Density$default(), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15), EmptyList.INSTANCE, 0, 0, 0, false, Orientation.Vertical, 0, 0);
}
