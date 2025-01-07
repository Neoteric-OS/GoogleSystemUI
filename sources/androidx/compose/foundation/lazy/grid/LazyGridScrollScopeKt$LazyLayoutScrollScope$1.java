package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridScrollScopeKt$LazyLayoutScrollScope$1 implements LazyLayoutScrollScope, ScrollScope {
    public final /* synthetic */ ScrollScope $$delegate_0;
    public final /* synthetic */ LazyGridState $state;

    public LazyGridScrollScopeKt$LazyLayoutScrollScope$1(ScrollScope scrollScope, LazyGridState lazyGridState) {
        this.$state = lazyGridState;
        this.$$delegate_0 = scrollScope;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final int calculateDistanceTo(int i) {
        Integer num;
        Object obj;
        LazyGridState lazyGridState = this.$state;
        LazyGridMeasureResult lazyGridMeasureResult = (LazyGridMeasureResult) lazyGridState.getLayoutInfo();
        if (lazyGridMeasureResult.visibleItemsInfo.isEmpty()) {
            return 0;
        }
        int firstVisibleItemIndex = getFirstVisibleItemIndex();
        int lastVisibleItemIndex = getLastVisibleItemIndex();
        Orientation orientation = Orientation.Vertical;
        if (i <= lastVisibleItemIndex && firstVisibleItemIndex <= i) {
            List list = lazyGridMeasureResult.visibleItemsInfo;
            int size = list.size();
            int i2 = 0;
            while (true) {
                num = null;
                if (i2 >= size) {
                    obj = null;
                    break;
                }
                obj = list.get(i2);
                if (((LazyGridMeasuredItem) ((LazyGridItemInfo) obj)).index == i) {
                    break;
                }
                i2++;
            }
            LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) obj;
            if (lazyGridMeasureResult.orientation == orientation) {
                if (lazyGridItemInfo != null) {
                    num = Integer.valueOf((int) (((LazyGridMeasuredItem) lazyGridItemInfo).offset & 4294967295L));
                }
            } else if (lazyGridItemInfo != null) {
                num = Integer.valueOf((int) (((LazyGridMeasuredItem) lazyGridItemInfo).offset >> 32));
            }
            if (num != null) {
                return num.intValue();
            }
            return 0;
        }
        int i3 = ((LazyGridMeasureResult) ((SnapshotMutableStateImpl) lazyGridState.layoutInfoState).getValue()).slotsPerLine;
        final boolean z = lazyGridMeasureResult.orientation == orientation;
        final List list2 = lazyGridMeasureResult.visibleItemsInfo;
        Function1 function1 = new Function1() { // from class: androidx.compose.foundation.lazy.grid.LazyGridScrollScopeKt$LazyLayoutScrollScope$1$calculateLineAverageMainAxisSize$lineOf$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                int intValue = ((Number) obj2).intValue();
                boolean z2 = z;
                LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) ((LazyGridItemInfo) list2.get(intValue));
                return Integer.valueOf(z2 ? lazyGridMeasuredItem.row : lazyGridMeasuredItem.column);
            }
        };
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < list2.size()) {
            int intValue = ((Number) function1.invoke(Integer.valueOf(i4))).intValue();
            if (intValue == -1) {
                i4++;
            } else {
                int i7 = 0;
                while (i4 < list2.size() && ((Number) function1.invoke(Integer.valueOf(i4))).intValue() == intValue) {
                    i7 = Math.max(i7, (int) (z ? ((LazyGridMeasuredItem) ((LazyGridItemInfo) list2.get(i4))).size & 4294967295L : ((LazyGridMeasuredItem) ((LazyGridItemInfo) list2.get(i4))).size >> 32));
                    i4++;
                }
                i5 += i7;
                i6++;
            }
        }
        return (((((i3 - 1) * (i < getFirstVisibleItemIndex() ? -1 : 1)) + (i - getFirstVisibleItemIndex())) / i3) * ((i5 / i6) + lazyGridMeasureResult.mainAxisItemSpacing)) - getFirstVisibleItemScrollOffset();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final int getFirstVisibleItemIndex() {
        return this.$state.scrollPosition.getIndex();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final int getFirstVisibleItemScrollOffset() {
        return this.$state.scrollPosition.getScrollOffset();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final int getItemCount() {
        return ((LazyGridMeasureResult) this.$state.getLayoutInfo()).totalItemsCount;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final int getLastVisibleItemIndex() {
        LazyGridItemInfo lazyGridItemInfo = (LazyGridItemInfo) CollectionsKt.lastOrNull(((LazyGridMeasureResult) this.$state.getLayoutInfo()).visibleItemsInfo);
        if (lazyGridItemInfo != null) {
            return ((LazyGridMeasuredItem) lazyGridItemInfo).index;
        }
        return 0;
    }

    @Override // androidx.compose.foundation.gestures.ScrollScope
    public final float scrollBy(float f) {
        return this.$$delegate_0.scrollBy(f);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope
    public final void snapToItem(int i, int i2) {
        this.$state.snapToItemIndexInternal$foundation_release(i, i2);
    }
}
