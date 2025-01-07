package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScopeImpl;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyGridMeasuredItemProvider implements LazyLayoutMeasuredItemProvider {
    public final int defaultMainAxisSpacing;
    public final LazyGridItemProvider itemProvider;
    public final LazyLayoutMeasureScope measureScope;

    public LazyGridMeasuredItemProvider(LazyGridItemProvider lazyGridItemProvider, LazyLayoutMeasureScope lazyLayoutMeasureScope, int i) {
        this.itemProvider = lazyGridItemProvider;
        this.measureScope = lazyLayoutMeasureScope;
        this.defaultMainAxisSpacing = i;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider
    /* renamed from: getAndMeasure--hBUhpc */
    public final LazyLayoutMeasuredItem mo125getAndMeasurehBUhpc(int i, int i2, long j, int i3) {
        return m129getAndMeasurem8Kt_7k(i, j, i2, i3, this.defaultMainAxisSpacing);
    }

    /* renamed from: getAndMeasure-m8Kt_7k, reason: not valid java name */
    public final LazyGridMeasuredItem m129getAndMeasurem8Kt_7k(int i, long j, int i2, int i3, int i4) {
        int m656getMinHeightimpl;
        LazyGridItemProviderImpl lazyGridItemProviderImpl = (LazyGridItemProviderImpl) this.itemProvider;
        Object key = lazyGridItemProviderImpl.getKey(i);
        Object contentType = lazyGridItemProviderImpl.intervalContent.getContentType(i);
        List m136measure0kLqBqw = ((LazyLayoutMeasureScopeImpl) this.measureScope).m136measure0kLqBqw(j, i);
        if (Constraints.m653getHasFixedWidthimpl(j)) {
            m656getMinHeightimpl = Constraints.m657getMinWidthimpl(j);
        } else {
            if (!Constraints.m652getHasFixedHeightimpl(j)) {
                InlineClassHelperKt.throwIllegalArgumentException("does not have fixed height");
            }
            m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
        }
        int i5 = m656getMinHeightimpl;
        LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1 lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1 = (LazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1) this;
        LayoutDirection layoutDirection = ((LazyLayoutMeasureScopeImpl) lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$this_null).subcomposeMeasureScope.getLayoutDirection();
        LazyLayoutItemAnimator lazyLayoutItemAnimator = lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$state.itemAnimator;
        return new LazyGridMeasuredItem(i, key, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$isVertical, i5, i4, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$reverseLayout, layoutDirection, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$beforeContentPadding, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$afterContentPadding, m136measure0kLqBqw, lazyGridKt$rememberLazyGridMeasurePolicy$1$1$measuredItemProvider$1.$visualItemOffset, contentType, lazyLayoutItemAnimator, j, i2, i3);
    }
}
