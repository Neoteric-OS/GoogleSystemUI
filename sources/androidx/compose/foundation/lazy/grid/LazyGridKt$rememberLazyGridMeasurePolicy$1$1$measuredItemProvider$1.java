package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1 extends LazyGridMeasuredItemProvider {
    public final /* synthetic */ int $afterContentPadding;
    public final /* synthetic */ int $beforeContentPadding;
    public final /* synthetic */ boolean $isVertical;
    public final /* synthetic */ boolean $reverseLayout;
    public final /* synthetic */ LazyGridState $state;
    public final /* synthetic */ LazyLayoutMeasureScope $this_null;
    public final /* synthetic */ long $visualItemOffset;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1(LazyGridItemProvider lazyGridItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, LazyGridState lazyGridState, boolean z, boolean z2, int i2, int i3, long j) {
        super(lazyGridItemProvider, lazyLayoutMeasureScope, i);
        this.$this_null = lazyLayoutMeasureScope;
        this.$state = lazyGridState;
        this.$isVertical = z;
        this.$reverseLayout = z2;
        this.$beforeContentPadding = i2;
        this.$afterContentPadding = i3;
        this.$visualItemOffset = j;
    }
}
