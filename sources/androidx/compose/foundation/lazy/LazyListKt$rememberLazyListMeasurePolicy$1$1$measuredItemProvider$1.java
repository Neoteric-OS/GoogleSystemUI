package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.ui.Alignment;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1 extends LazyListMeasuredItemProvider {
    public final /* synthetic */ int $afterContentPadding;
    public final /* synthetic */ int $beforeContentPadding;
    public final /* synthetic */ Alignment.Horizontal $horizontalAlignment;
    public final /* synthetic */ boolean $isVertical;
    public final /* synthetic */ int $itemsCount;
    public final /* synthetic */ boolean $reverseLayout;
    public final /* synthetic */ int $spaceBetweenItems;
    public final /* synthetic */ LazyListState $state;
    public final /* synthetic */ LazyLayoutMeasureScope $this_null;
    public final /* synthetic */ Alignment.Vertical $verticalAlignment;
    public final /* synthetic */ long $visualItemOffset;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1(long j, boolean z, LazyListItemProvider lazyListItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, int i2, Alignment.Horizontal horizontal, Alignment.Vertical vertical, boolean z2, int i3, int i4, long j2, LazyListState lazyListState) {
        super(j, z, lazyListItemProvider, lazyLayoutMeasureScope);
        this.$isVertical = z;
        this.$this_null = lazyLayoutMeasureScope;
        this.$itemsCount = i;
        this.$spaceBetweenItems = i2;
        this.$horizontalAlignment = horizontal;
        this.$verticalAlignment = vertical;
        this.$reverseLayout = z2;
        this.$beforeContentPadding = i3;
        this.$afterContentPadding = i4;
        this.$visualItemOffset = j2;
        this.$state = lazyListState;
    }
}
