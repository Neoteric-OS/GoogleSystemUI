package com.android.launcher3.icons;

import android.content.Context;
import android.content.res.TypedArray;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class GraphicsUtils {
    public static final GraphicsUtils$$ExternalSyntheticLambda0 sOnNewBitmapRunnable = null;

    public static int getAttrColor(int i, Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }
}
