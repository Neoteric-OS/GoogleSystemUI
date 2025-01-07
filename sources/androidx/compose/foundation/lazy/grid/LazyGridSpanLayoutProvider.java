package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.foundation.lazy.layout.IntervalList$Interval;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridSpanLayoutProvider {
    public final ArrayList buckets;
    public final List cachedBucket;
    public int cachedBucketIndex;
    public final LazyGridIntervalContent gridContent;
    public int lastLineIndex;
    public int lastLineStartItemIndex;
    public int lastLineStartKnownSpan;
    public List previousDefaultSpans;
    public int slotsPerLine;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Bucket {
        public final int firstItemIndex;
        public final int firstItemKnownSpan;

        public Bucket(int i, int i2) {
            this.firstItemIndex = i;
            this.firstItemKnownSpan = i2;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class LazyGridItemSpanScopeImpl implements LazyGridItemSpanScope {
        public static final LazyGridItemSpanScopeImpl INSTANCE = new LazyGridItemSpanScopeImpl();
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LineConfiguration {
        public final int firstItemIndex;
        public final List spans;

        public LineConfiguration(int i, List list) {
            this.firstItemIndex = i;
            this.spans = list;
        }
    }

    public LazyGridSpanLayoutProvider(LazyGridIntervalContent lazyGridIntervalContent) {
        this.gridContent = lazyGridIntervalContent;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Bucket(0, 0));
        this.buckets = arrayList;
        this.cachedBucketIndex = -1;
        this.cachedBucket = new ArrayList();
        this.previousDefaultSpans = EmptyList.INSTANCE;
    }

    public final int getBucketSize() {
        return ((int) Math.sqrt((getTotalSize() * 1.0d) / this.slotsPerLine)) + 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x00a7, code lost:
    
        if (r7 < r6) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider.LineConfiguration getLineConfiguration(int r11) {
        /*
            Method dump skipped, instructions count: 347
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider.getLineConfiguration(int):androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider$LineConfiguration");
    }

    public final int getLineIndexOfItem(final int i) {
        int i2;
        if (getTotalSize() <= 0) {
            return 0;
        }
        if (i >= getTotalSize()) {
            InlineClassHelperKt.throwIllegalArgumentException("ItemIndex > total count");
        }
        if (!this.gridContent.hasCustomSpans) {
            return i / this.slotsPerLine;
        }
        ArrayList arrayList = this.buckets;
        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider$getLineIndexOfItem$lowerBoundBucket$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(((LazyGridSpanLayoutProvider.Bucket) obj).firstItemIndex - i);
            }
        };
        int size = arrayList.size();
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(arrayList.size(), size);
        int i3 = size - 1;
        int i4 = 0;
        while (true) {
            if (i4 > i3) {
                i2 = -(i4 + 1);
                break;
            }
            i2 = (i4 + i3) >>> 1;
            int intValue = ((Number) function1.invoke(arrayList.get(i2))).intValue();
            if (intValue >= 0) {
                if (intValue <= 0) {
                    break;
                }
                i3 = i2 - 1;
            } else {
                i4 = i2 + 1;
            }
        }
        if (i2 < 0) {
            i2 = (-i2) - 2;
        }
        int bucketSize = getBucketSize() * i2;
        int i5 = ((Bucket) this.buckets.get(i2)).firstItemIndex;
        if (i5 > i) {
            InlineClassHelperKt.throwIllegalArgumentException("currentItemIndex > itemIndex");
        }
        int i6 = 0;
        while (i5 < i) {
            int i7 = i5 + 1;
            int spanOf = spanOf(i5);
            i6 += spanOf;
            int i8 = this.slotsPerLine;
            if (i6 >= i8) {
                if (i6 == i8) {
                    bucketSize++;
                    i6 = 0;
                } else {
                    bucketSize++;
                    i6 = spanOf;
                }
            }
            if (bucketSize % getBucketSize() == 0 && bucketSize / getBucketSize() >= this.buckets.size()) {
                this.buckets.add(new Bucket(i7 - (i6 > 0 ? 1 : 0), 0));
            }
            i5 = i7;
        }
        return spanOf(i) + i6 > this.slotsPerLine ? bucketSize + 1 : bucketSize;
    }

    public final int getTotalSize() {
        return this.gridContent.intervals.size;
    }

    public final int spanOf(int i) {
        LazyGridItemSpanScopeImpl lazyGridItemSpanScopeImpl = LazyGridItemSpanScopeImpl.INSTANCE;
        IntervalList$Interval intervalList$Interval = this.gridContent.intervals.get(i);
        int i2 = i - intervalList$Interval.startIndex;
        LazyGridInterval lazyGridInterval = (LazyGridInterval) intervalList$Interval.value;
        return (int) ((GridItemSpan) lazyGridInterval.span.invoke(lazyGridItemSpanScopeImpl, Integer.valueOf(i2))).packedValue;
    }
}
