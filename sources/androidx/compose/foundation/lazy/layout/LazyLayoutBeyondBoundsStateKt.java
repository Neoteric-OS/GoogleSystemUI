package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutPinnedItemList;
import androidx.compose.runtime.SnapshotMutableIntStateImpl;
import androidx.compose.runtime.collection.MutableVector;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.EmptyList;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutBeyondBoundsStateKt {
    public static final List calculateLazyLayoutPinnedIndices(LazyLayoutItemProvider lazyLayoutItemProvider, LazyLayoutPinnedItemList lazyLayoutPinnedItemList, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo) {
        IntRange intRange;
        if (lazyLayoutBeyondBoundsInfo.beyondBoundsItems.size == 0 && lazyLayoutPinnedItemList.items.isEmpty()) {
            return EmptyList.INSTANCE;
        }
        ArrayList arrayList = new ArrayList();
        MutableVector mutableVector = lazyLayoutBeyondBoundsInfo.beyondBoundsItems;
        if (mutableVector.size != 0) {
            int i = mutableVector.size;
            if (i == 0) {
                throw new NoSuchElementException("MutableVector is empty.");
            }
            Object[] objArr = mutableVector.content;
            int i2 = ((LazyLayoutBeyondBoundsInfo.Interval) objArr[0]).start;
            if (i > 0) {
                int i3 = 0;
                do {
                    int i4 = ((LazyLayoutBeyondBoundsInfo.Interval) objArr[i3]).start;
                    if (i4 < i2) {
                        i2 = i4;
                    }
                    i3++;
                } while (i3 < i);
            }
            if (i2 < 0) {
                InlineClassHelperKt.throwIllegalArgumentException("negative minIndex");
            }
            int i5 = mutableVector.size;
            if (i5 == 0) {
                throw new NoSuchElementException("MutableVector is empty.");
            }
            Object[] objArr2 = mutableVector.content;
            int i6 = ((LazyLayoutBeyondBoundsInfo.Interval) objArr2[0]).end;
            if (i5 > 0) {
                int i7 = 0;
                do {
                    int i8 = ((LazyLayoutBeyondBoundsInfo.Interval) objArr2[i7]).end;
                    if (i8 > i6) {
                        i6 = i8;
                    }
                    i7++;
                } while (i7 < i5);
            }
            intRange = new IntRange(i2, Math.min(i6, lazyLayoutItemProvider.getItemCount() - 1), 1);
        } else {
            intRange = IntRange.EMPTY;
        }
        int size = lazyLayoutPinnedItemList.items.size();
        for (int i9 = 0; i9 < size; i9++) {
            LazyLayoutPinnableItem lazyLayoutPinnableItem = (LazyLayoutPinnableItem) ((LazyLayoutPinnedItemList.PinnedItem) lazyLayoutPinnedItemList.get(i9));
            int findIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(((SnapshotMutableIntStateImpl) lazyLayoutPinnableItem.index$delegate).getIntValue(), lazyLayoutItemProvider, lazyLayoutPinnableItem.key);
            int i10 = intRange.first;
            if ((findIndexByKey > intRange.last || i10 > findIndexByKey) && findIndexByKey >= 0 && findIndexByKey < lazyLayoutItemProvider.getItemCount()) {
                arrayList.add(Integer.valueOf(findIndexByKey));
            }
        }
        int i11 = intRange.first;
        int i12 = intRange.last;
        if (i11 <= i12) {
            while (true) {
                arrayList.add(Integer.valueOf(i11));
                if (i11 == i12) {
                    break;
                }
                i11++;
            }
        }
        return arrayList;
    }
}
