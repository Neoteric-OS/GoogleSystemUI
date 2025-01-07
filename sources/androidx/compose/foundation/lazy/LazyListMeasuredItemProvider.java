package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScopeImpl;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyListMeasuredItemProvider implements LazyLayoutMeasuredItemProvider {
    public final long childConstraints;
    public final LazyListItemProvider itemProvider;
    public final LazyLayoutMeasureScope measureScope;

    public LazyListMeasuredItemProvider(long j, boolean z, LazyListItemProvider lazyListItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope) {
        this.itemProvider = lazyListItemProvider;
        this.measureScope = lazyLayoutMeasureScope;
        this.childConstraints = ConstraintsKt.Constraints$default(0, z ? Constraints.m655getMaxWidthimpl(j) : Integer.MAX_VALUE, 0, z ? Integer.MAX_VALUE : Constraints.m654getMaxHeightimpl(j), 5);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider
    /* renamed from: getAndMeasure--hBUhpc, reason: not valid java name */
    public final LazyLayoutMeasuredItem mo125getAndMeasurehBUhpc(int i, int i2, long j, int i3) {
        return m126getAndMeasure0kLqBqw(j, i);
    }

    /* renamed from: getAndMeasure-0kLqBqw, reason: not valid java name */
    public final LazyListMeasuredItem m126getAndMeasure0kLqBqw(long j, int i) {
        LazyListItemProviderImpl lazyListItemProviderImpl = (LazyListItemProviderImpl) this.itemProvider;
        Object key = lazyListItemProviderImpl.getKey(i);
        Object contentType = lazyListItemProviderImpl.intervalContent.getContentType(i);
        List m136measure0kLqBqw = ((LazyLayoutMeasureScopeImpl) this.measureScope).m136measure0kLqBqw(j, i);
        LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1 lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1 = (LazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1) this;
        int i2 = i == lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$itemsCount + (-1) ? 0 : lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$spaceBetweenItems;
        return new LazyListMeasuredItem(i, m136measure0kLqBqw, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$isVertical, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$horizontalAlignment, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$verticalAlignment, ((LazyLayoutMeasureScopeImpl) lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$this_null).subcomposeMeasureScope.getLayoutDirection(), lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$reverseLayout, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$beforeContentPadding, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$afterContentPadding, i2, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$visualItemOffset, key, contentType, lazyListKt$rememberLazyListMeasurePolicy$1$1$measuredItemProvider$1.$state.itemAnimator, j);
    }
}
