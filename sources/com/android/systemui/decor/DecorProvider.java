package com.android.systemui.decor;

import android.content.Context;
import android.view.View;
import com.android.systemui.RegionInterceptingFrameLayout;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DecorProvider {
    public abstract List getAlignedBounds();

    public abstract int getViewId();

    public abstract View inflateView(Context context, RegionInterceptingFrameLayout regionInterceptingFrameLayout, int i, int i2);

    public abstract void onReloadResAndMeasure(View view, int i, int i2, int i3, String str);

    public final String toString() {
        return getClass().getSimpleName() + "{alignedBounds=" + getAlignedBounds() + "}";
    }
}
