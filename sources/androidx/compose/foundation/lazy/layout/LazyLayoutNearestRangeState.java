package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutNearestRangeState implements State {
    public static final Companion Companion = null;
    public final int extraItemCount;
    public int lastFirstVisibleItem;
    public final int slidingWindowSize;
    public final MutableState value$delegate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Companion {
    }

    public LazyLayoutNearestRangeState(int i, int i2, int i3) {
        this.slidingWindowSize = i2;
        this.extraItemCount = i3;
        int i4 = (i / i2) * i2;
        this.value$delegate = SnapshotStateKt.mutableStateOf(RangesKt.until(Math.max(i4 - i3, 0), i4 + i2 + i3), SnapshotStateKt.structuralEqualityPolicy());
        this.lastFirstVisibleItem = i;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return (IntRange) ((SnapshotMutableStateImpl) this.value$delegate).getValue();
    }

    public final void update(int i) {
        if (i != this.lastFirstVisibleItem) {
            this.lastFirstVisibleItem = i;
            int i2 = this.slidingWindowSize;
            int i3 = (i / i2) * i2;
            int i4 = this.extraItemCount;
            ((SnapshotMutableStateImpl) this.value$delegate).setValue(RangesKt.until(Math.max(i3 - i4, 0), i3 + i2 + i4));
        }
    }
}
