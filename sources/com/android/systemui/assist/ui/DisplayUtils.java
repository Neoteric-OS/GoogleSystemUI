package com.android.systemui.assist.ui;

import android.content.Context;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DisplayUtils {
    public static int getCornerRadiusBottom(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.config_rounded_mask_size_bottom);
        return dimensionPixelSize == 0 ? context.getResources().getDimensionPixelSize(R.dimen.config_rounded_mask_size) : dimensionPixelSize;
    }

    public static int getCornerRadiusTop(Context context) {
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.config_rounded_mask_size_top);
        return dimensionPixelSize == 0 ? context.getResources().getDimensionPixelSize(R.dimen.config_rounded_mask_size) : dimensionPixelSize;
    }
}
