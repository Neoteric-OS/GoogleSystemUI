package com.android.wm.shell.onehanded;

import android.R;
import android.content.Context;
import android.content.res.Resources;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class OneHandedSurfaceTransactionHelper {
    public final float mCornerRadius;
    public final float mCornerRadiusAdjustment;
    public final boolean mEnableCornerRadius;

    public OneHandedSurfaceTransactionHelper(Context context) {
        Resources resources = context.getResources();
        float dimension = resources.getDimension(R.dimen.search_view_preferred_width);
        this.mCornerRadiusAdjustment = dimension;
        this.mCornerRadius = resources.getDimension(R.dimen.search_view_preferred_height) - dimension;
        this.mEnableCornerRadius = resources.getBoolean(com.android.wm.shell.R.bool.config_one_handed_enable_round_corner);
    }
}
