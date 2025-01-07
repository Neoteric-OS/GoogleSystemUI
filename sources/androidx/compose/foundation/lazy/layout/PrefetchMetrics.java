package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableObjectLongMap;
import androidx.collection.ObjectLongMapKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrefetchMetrics {
    public long averageCompositionTimeNanos;
    public final MutableObjectLongMap averageCompositionTimeNanosByContentType;
    public long averageMeasureTimeNanos;
    public final MutableObjectLongMap averageMeasureTimeNanosByContentType;

    public PrefetchMetrics() {
        int i = ObjectLongMapKt.$r8$clinit;
        this.averageCompositionTimeNanosByContentType = new MutableObjectLongMap(6);
        this.averageMeasureTimeNanosByContentType = new MutableObjectLongMap(6);
    }

    public static final long access$calculateAverageTime(PrefetchMetrics prefetchMetrics, long j, long j2) {
        prefetchMetrics.getClass();
        if (j2 == 0) {
            return j;
        }
        long j3 = 4;
        return (j / j3) + ((j2 / j3) * 3);
    }
}
