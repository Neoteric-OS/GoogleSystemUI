package com.android.systemui.qs;

import android.view.animation.Interpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PagedTileLayout$$ExternalSyntheticLambda0 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        PagedTileLayout$$ExternalSyntheticLambda0 pagedTileLayout$$ExternalSyntheticLambda0 = PagedTileLayout.SCROLL_CUBIC;
        float f2 = f - 1.0f;
        return (f2 * f2 * f2) + 1.0f;
    }
}
