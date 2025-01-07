package com.android.systemui.decor;

import android.content.Context;
import android.view.View;
import com.android.systemui.RegionInterceptingFrameLayout;
import com.android.systemui.ScreenDecorations;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CutoutDecorProviderImpl extends BoundDecorProvider {
    public final int alignedBound;
    public final int viewId;

    public CutoutDecorProviderImpl(int i) {
        this.alignedBound = i;
        this.viewId = i != 0 ? i != 1 ? i != 2 ? R.id.display_cutout_bottom : R.id.display_cutout_right : R.id.display_cutout : R.id.display_cutout_left;
    }

    @Override // com.android.systemui.decor.BoundDecorProvider
    public final int getAlignedBound() {
        return this.alignedBound;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final int getViewId() {
        return this.viewId;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final View inflateView(Context context, RegionInterceptingFrameLayout regionInterceptingFrameLayout, int i, int i2) {
        ScreenDecorations.DisplayCutoutView displayCutoutView = new ScreenDecorations.DisplayCutoutView(this.alignedBound, context);
        displayCutoutView.setId(this.viewId);
        displayCutoutView.setColor$1(i2);
        regionInterceptingFrameLayout.addView(displayCutoutView);
        if (i != displayCutoutView.mRotation) {
            displayCutoutView.mRotation = i;
            displayCutoutView.displayRotation = i;
            displayCutoutView.updateCutout();
            displayCutoutView.updateProtectionBoundingPath();
        }
        return displayCutoutView;
    }

    @Override // com.android.systemui.decor.DecorProvider
    public final void onReloadResAndMeasure(View view, int i, int i2, int i3, String str) {
        ScreenDecorations.DisplayCutoutView displayCutoutView = view instanceof ScreenDecorations.DisplayCutoutView ? (ScreenDecorations.DisplayCutoutView) view : null;
        if (displayCutoutView != null) {
            displayCutoutView.setColor$1(i3);
            if (i2 != displayCutoutView.mRotation) {
                displayCutoutView.mRotation = i2;
                displayCutoutView.displayRotation = i2;
                displayCutoutView.updateCutout();
                displayCutoutView.updateProtectionBoundingPath();
            }
            displayCutoutView.updateConfiguration(str);
        }
    }
}
