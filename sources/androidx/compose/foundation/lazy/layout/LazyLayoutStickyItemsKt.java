package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutStickyItemsKt {
    public static final List applyStickyItems(StickyItemsPlacement stickyItemsPlacement, List list, MutableIntList mutableIntList, int i, int i2, int i3, Function1 function1) {
        MutableIntList mutableIntList2;
        int i4;
        int i5;
        long j;
        int i6;
        Object obj;
        int i7;
        int i8;
        int i9 = 1;
        if (stickyItemsPlacement == null || list.isEmpty() || mutableIntList._size == 0) {
            return EmptyList.INSTANCE;
        }
        int index = ((LazyLayoutMeasuredItem) CollectionsKt.first(list)).getIndex();
        int i10 = 0;
        if (((LazyLayoutMeasuredItem) CollectionsKt.last(list)).getIndex() - index < 0 || (i8 = mutableIntList._size) == 0) {
            mutableIntList2 = IntListKt.EmptyIntList;
        } else {
            IntRange until = RangesKt.until(0, i8);
            int i11 = until.first;
            int i12 = until.last;
            int i13 = -1;
            if (i11 <= i12) {
                while (mutableIntList.get(i11) <= index) {
                    i13 = mutableIntList.get(i11);
                    if (i11 == i12) {
                        break;
                    }
                    i11++;
                }
            }
            if (i13 == -1) {
                mutableIntList2 = IntListKt.EmptyIntList;
            } else {
                MutableIntList mutableIntList3 = IntListKt.EmptyIntList;
                mutableIntList2 = new MutableIntList(1);
                mutableIntList2.add(i13);
            }
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) list;
        ArrayList arrayList3 = new ArrayList(arrayList2.size());
        int size = arrayList2.size();
        int i14 = 0;
        while (i14 < size) {
            Object obj2 = arrayList2.get(i14);
            int index2 = ((LazyLayoutMeasuredItem) obj2).getIndex();
            int[] iArr = mutableIntList.content;
            int i15 = mutableIntList._size;
            while (true) {
                if (i10 >= i15) {
                    break;
                }
                if (iArr[i10] == index2) {
                    arrayList3.add(obj2);
                    break;
                }
                i10++;
            }
            i14++;
            i10 = 0;
        }
        int[] iArr2 = mutableIntList2.content;
        int i16 = mutableIntList2._size;
        int i17 = 0;
        while (i17 < i16) {
            int i18 = iArr2[i17];
            Iterator it = list.iterator();
            int i19 = 0;
            while (true) {
                if (!it.hasNext()) {
                    i4 = -1;
                    i19 = -1;
                    break;
                }
                if (((LazyLayoutMeasuredItem) it.next()).getIndex() == i18) {
                    i4 = -1;
                    break;
                }
                i19 += i9;
            }
            LazyLayoutMeasuredItem lazyLayoutMeasuredItem = i19 == i4 ? (LazyLayoutMeasuredItem) function1.invoke(Integer.valueOf(i18)) : (LazyLayoutMeasuredItem) list.remove(i19);
            int mainAxisSizeWithSpacings = lazyLayoutMeasuredItem.getMainAxisSizeWithSpacings();
            if (i19 == -1) {
                i5 = i16;
                i6 = Integer.MIN_VALUE;
            } else {
                long mo124getOffsetBjo55l4 = lazyLayoutMeasuredItem.mo124getOffsetBjo55l4(0);
                if (lazyLayoutMeasuredItem.isVertical()) {
                    i5 = i16;
                    j = mo124getOffsetBjo55l4 & 4294967295L;
                } else {
                    i5 = i16;
                    j = mo124getOffsetBjo55l4 >> 32;
                }
                i6 = (int) j;
            }
            int size2 = arrayList3.size();
            int i20 = 0;
            while (true) {
                if (i20 >= size2) {
                    obj = null;
                    break;
                }
                obj = arrayList3.get(i20);
                if (((LazyLayoutMeasuredItem) obj).getIndex() != i18) {
                    break;
                }
                i20++;
            }
            LazyLayoutMeasuredItem lazyLayoutMeasuredItem2 = (LazyLayoutMeasuredItem) obj;
            if (lazyLayoutMeasuredItem2 != null) {
                long mo124getOffsetBjo55l42 = lazyLayoutMeasuredItem2.mo124getOffsetBjo55l4(0);
                i7 = (int) (lazyLayoutMeasuredItem2.isVertical() ? mo124getOffsetBjo55l42 & 4294967295L : mo124getOffsetBjo55l42 >> 32);
            } else {
                i7 = Integer.MIN_VALUE;
            }
            int max = i6 == Integer.MIN_VALUE ? -i : Math.max(-i, i6);
            if (i7 != Integer.MIN_VALUE) {
                max = Math.min(max, i7 - mainAxisSizeWithSpacings);
            }
            lazyLayoutMeasuredItem.setNonScrollableItem();
            lazyLayoutMeasuredItem.position(max, 0, i2, i3);
            arrayList.add(lazyLayoutMeasuredItem);
            i9 = 1;
            i17++;
            i16 = i5;
        }
        return arrayList;
    }
}
