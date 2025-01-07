package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class IntervalList$Interval {
    public final int size;
    public final int startIndex;
    public final LazyLayoutIntervalContent.Interval value;

    public IntervalList$Interval(int i, int i2, LazyLayoutIntervalContent.Interval interval) {
        this.startIndex = i;
        this.size = i2;
        this.value = interval;
        if (i < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("startIndex should be >= 0");
        }
        if (i2 > 0) {
            return;
        }
        InlineClassHelperKt.throwIllegalArgumentException("size should be > 0");
    }
}
