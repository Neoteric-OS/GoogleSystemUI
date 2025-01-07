package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimation;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Density;
import java.util.List;
import java.util.Map;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListMeasureResult implements LazyListLayoutInfo, MeasureResult {
    public final int afterContentPadding;
    public final boolean canScrollForward;
    public final long childConstraints;
    public final float consumedScroll;
    public final CoroutineScope coroutineScope;
    public final Density density;
    public final LazyListMeasuredItem firstVisibleItem;
    public final int firstVisibleItemScrollOffset;
    public final int mainAxisItemSpacing;
    public final MeasureResult measureResult;
    public final Orientation orientation;
    public final boolean remeasureNeeded;
    public final boolean reverseLayout;
    public final float scrollBackAmount;
    public final int totalItemsCount;
    public final int viewportEndOffset;
    public final int viewportStartOffset;
    public final List visibleItemsInfo;

    public LazyListMeasureResult(LazyListMeasuredItem lazyListMeasuredItem, int i, boolean z, float f, MeasureResult measureResult, float f2, boolean z2, CoroutineScope coroutineScope, Density density, long j, List list, int i2, int i3, int i4, boolean z3, Orientation orientation, int i5, int i6) {
        this.firstVisibleItem = lazyListMeasuredItem;
        this.firstVisibleItemScrollOffset = i;
        this.canScrollForward = z;
        this.consumedScroll = f;
        this.measureResult = measureResult;
        this.scrollBackAmount = f2;
        this.remeasureNeeded = z2;
        this.coroutineScope = coroutineScope;
        this.density = density;
        this.childConstraints = j;
        this.visibleItemsInfo = list;
        this.viewportStartOffset = i2;
        this.viewportEndOffset = i3;
        this.totalItemsCount = i4;
        this.reverseLayout = z3;
        this.orientation = orientation;
        this.afterContentPadding = i5;
        this.mainAxisItemSpacing = i6;
    }

    public final LazyListMeasureResult copyWithScrollDeltaWithoutRemeasure(int i, boolean z) {
        LazyListMeasuredItem lazyListMeasuredItem;
        int i2;
        boolean z2;
        int i3;
        int i4;
        int i5;
        LazyListMeasureResult lazyListMeasureResult = null;
        if (!this.remeasureNeeded && !this.visibleItemsInfo.isEmpty() && (lazyListMeasuredItem = this.firstVisibleItem) != null && (i2 = this.firstVisibleItemScrollOffset - i) >= 0 && i2 < lazyListMeasuredItem.mainAxisSizeWithSpacings) {
            LazyListMeasuredItem lazyListMeasuredItem2 = (LazyListMeasuredItem) CollectionsKt.first(this.visibleItemsInfo);
            LazyListMeasuredItem lazyListMeasuredItem3 = (LazyListMeasuredItem) CollectionsKt.last(this.visibleItemsInfo);
            if (!lazyListMeasuredItem2.nonScrollableItem && !lazyListMeasuredItem3.nonScrollableItem) {
                int i6 = this.viewportEndOffset;
                int i7 = this.viewportStartOffset;
                if (i >= 0 ? Math.min(i7 - lazyListMeasuredItem2.offset, i6 - lazyListMeasuredItem3.offset) > i : Math.min((lazyListMeasuredItem2.offset + lazyListMeasuredItem2.mainAxisSizeWithSpacings) - i7, (lazyListMeasuredItem3.offset + lazyListMeasuredItem3.mainAxisSizeWithSpacings) - i6) > (-i)) {
                    List list = this.visibleItemsInfo;
                    int size = list.size();
                    int i8 = 0;
                    while (i8 < size) {
                        LazyListMeasuredItem lazyListMeasuredItem4 = (LazyListMeasuredItem) list.get(i8);
                        if (!lazyListMeasuredItem4.nonScrollableItem) {
                            lazyListMeasuredItem4.offset += i;
                            int[] iArr = lazyListMeasuredItem4.placeableOffsets;
                            int length = iArr.length;
                            int i9 = 0;
                            while (true) {
                                z2 = lazyListMeasuredItem4.isVertical;
                                if (i9 >= length) {
                                    break;
                                }
                                int i10 = i9 & 1;
                                if ((z2 && i10 != 0) || (!z2 && i10 == 0)) {
                                    iArr[i9] = iArr[i9] + i;
                                }
                                i9++;
                            }
                            if (z) {
                                int size2 = lazyListMeasuredItem4.placeables.size();
                                int i11 = 0;
                                while (i11 < size2) {
                                    LazyLayoutItemAnimation animation = lazyListMeasuredItem4.animator.getAnimation(i11, lazyListMeasuredItem4.key);
                                    if (animation != null) {
                                        long j = animation.rawOffset;
                                        if (z2) {
                                            i3 = i8;
                                            i4 = (int) (j >> 32);
                                            i5 = ((int) (j & 4294967295L)) + i;
                                        } else {
                                            i3 = i8;
                                            i4 = ((int) (j >> 32)) + i;
                                            i5 = (int) (j & 4294967295L);
                                        }
                                        animation.rawOffset = (i5 & 4294967295L) | (i4 << 32);
                                    } else {
                                        i3 = i8;
                                    }
                                    i11++;
                                    i8 = i3;
                                }
                            }
                        }
                        i8++;
                    }
                    lazyListMeasureResult = new LazyListMeasureResult(this.firstVisibleItem, i2, this.canScrollForward || i > 0, i, this.measureResult, this.scrollBackAmount, this.remeasureNeeded, this.coroutineScope, this.density, this.childConstraints, this.visibleItemsInfo, this.viewportStartOffset, this.viewportEndOffset, this.totalItemsCount, this.reverseLayout, this.orientation, this.afterContentPadding, this.mainAxisItemSpacing);
                }
            }
        }
        return lazyListMeasureResult;
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Map getAlignmentLines() {
        return this.measureResult.getAlignmentLines();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getHeight() {
        return this.measureResult.getHeight();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final Function1 getRulers() {
        return this.measureResult.getRulers();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final int getWidth() {
        return this.measureResult.getWidth();
    }

    @Override // androidx.compose.ui.layout.MeasureResult
    public final void placeChildren() {
        this.measureResult.placeChildren();
    }
}
