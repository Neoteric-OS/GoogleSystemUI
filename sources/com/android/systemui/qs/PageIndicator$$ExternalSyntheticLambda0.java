package com.android.systemui.qs;

import androidx.core.util.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PageIndicator$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ PageIndicator f$0;

    public /* synthetic */ PageIndicator$$ExternalSyntheticLambda0(PageIndicator pageIndicator) {
        this.f$0 = pageIndicator;
    }

    @Override // androidx.core.util.Consumer
    public final void accept(Object obj) {
        Integer num = (Integer) obj;
        PageIndicator pageIndicator = this.f$0;
        if (pageIndicator.mPageScrollActionListener != null) {
            int i = num.intValue() == 21 ? 0 : 1;
            PagedTileLayout pagedTileLayout = pageIndicator.mPageScrollActionListener.f$0;
            if (pagedTileLayout.mScroller.isFinished()) {
                pagedTileLayout.scrollByX(pagedTileLayout.getDeltaXForPageScrolling(i), 300);
            }
        }
    }
}
