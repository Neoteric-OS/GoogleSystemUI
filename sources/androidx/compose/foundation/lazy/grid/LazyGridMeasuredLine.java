package androidx.compose.foundation.lazy.grid;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridMeasuredLine {
    public final int index;
    public final boolean isVertical;
    public final LazyGridMeasuredItem[] items;
    public final int mainAxisSize;
    public final int mainAxisSizeWithSpacings;
    public final int mainAxisSpacing;
    public final LazyGridSlots slots;
    public final List spans;

    public LazyGridMeasuredLine(int i, LazyGridMeasuredItem[] lazyGridMeasuredItemArr, LazyGridSlots lazyGridSlots, List list, boolean z, int i2) {
        this.index = i;
        this.items = lazyGridMeasuredItemArr;
        this.slots = lazyGridSlots;
        this.spans = list;
        this.isVertical = z;
        this.mainAxisSpacing = i2;
        int i3 = 0;
        for (LazyGridMeasuredItem lazyGridMeasuredItem : lazyGridMeasuredItemArr) {
            i3 = Math.max(i3, lazyGridMeasuredItem.mainAxisSize);
        }
        this.mainAxisSize = i3;
        int i4 = i3 + this.mainAxisSpacing;
        this.mainAxisSizeWithSpacings = i4 >= 0 ? i4 : 0;
    }

    public final LazyGridMeasuredItem[] position(int i, int i2, int i3) {
        LazyGridMeasuredItem[] lazyGridMeasuredItemArr = this.items;
        int length = lazyGridMeasuredItemArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < length) {
            LazyGridMeasuredItem lazyGridMeasuredItem = lazyGridMeasuredItemArr[i4];
            int i7 = i5 + 1;
            int i8 = (int) ((GridItemSpan) this.spans.get(i5)).packedValue;
            int i9 = this.slots.positions[i6];
            int i10 = this.index;
            boolean z = this.isVertical;
            lazyGridMeasuredItem.position(i, i9, i2, i3, z ? i10 : i6, z ? i6 : i10);
            i6 += i8;
            i4++;
            i5 = i7;
        }
        return lazyGridMeasuredItemArr;
    }
}
