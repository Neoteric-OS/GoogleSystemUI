package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.grid.LazyGridSpanLayoutProvider;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyGridMeasuredLineProvider {
    public final int gridItemsCount;
    public final boolean isVertical;
    public final LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1 measuredItemProvider;
    public final LazyGridSlots slots;
    public final int spaceBetweenLines;
    public final LazyGridSpanLayoutProvider spanLayoutProvider;

    public LazyGridMeasuredLineProvider(boolean z, LazyGridSlots lazyGridSlots, int i, int i2, LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1, LazyGridSpanLayoutProvider lazyGridSpanLayoutProvider) {
        this.isVertical = z;
        this.slots = lazyGridSlots;
        this.gridItemsCount = i;
        this.spaceBetweenLines = i2;
        this.measuredItemProvider = lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1;
        this.spanLayoutProvider = lazyGridSpanLayoutProvider;
    }

    /* renamed from: childConstraints-JhjzzOo$foundation_release, reason: not valid java name */
    public final long m130childConstraintsJhjzzOo$foundation_release(int i, int i2) {
        int i3;
        LazyGridSlots lazyGridSlots = this.slots;
        int[] iArr = lazyGridSlots.sizes;
        if (i2 == 1) {
            i3 = iArr[i];
        } else {
            int i4 = (i2 + i) - 1;
            int[] iArr2 = lazyGridSlots.positions;
            i3 = (iArr2[i4] + iArr[i4]) - iArr2[i];
        }
        if (i3 < 0) {
            i3 = 0;
        }
        if (this.isVertical) {
            if (i3 < 0) {
                InlineClassHelperKt.throwIllegalArgumentException("width must be >= 0");
            }
            return ConstraintsKt.createConstraints(i3, i3, 0, Integer.MAX_VALUE);
        }
        if (i3 < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("height must be >= 0");
        }
        return ConstraintsKt.createConstraints(0, Integer.MAX_VALUE, i3, i3);
    }

    public final LazyGridMeasuredLine getAndMeasure(int i) {
        LazyGridSpanLayoutProvider.LineConfiguration lineConfiguration = this.spanLayoutProvider.getLineConfiguration(i);
        int size = lineConfiguration.spans.size();
        int i2 = lineConfiguration.firstItemIndex;
        int i3 = (size == 0 || i2 + size == this.gridItemsCount) ? 0 : this.spaceBetweenLines;
        LazyGridMeasuredItem[] lazyGridMeasuredItemArr = new LazyGridMeasuredItem[size];
        int i4 = 0;
        for (int i5 = 0; i5 < size; i5++) {
            int i6 = (int) ((GridItemSpan) lineConfiguration.spans.get(i5)).packedValue;
            LazyGridMeasuredItem m129getAndMeasurem8Kt_7k = this.measuredItemProvider.m129getAndMeasurem8Kt_7k(i2 + i5, m130childConstraintsJhjzzOo$foundation_release(i4, i6), i4, i6, i3);
            i4 += i6;
            lazyGridMeasuredItemArr[i5] = m129getAndMeasurem8Kt_7k;
        }
        LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1 = (LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1) this;
        return new LazyGridMeasuredLine(i, lazyGridMeasuredItemArr, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.$resolvedSlots, lineConfiguration.spans, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredLineProvider$1.$isVertical, i3);
    }
}
