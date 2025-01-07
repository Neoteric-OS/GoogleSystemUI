package com.android.systemui.util;

import android.graphics.Color;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ColorUtilKt {
    public static final int getColorWithAlpha(int i, float f) {
        return Color.argb((int) (f * 255), Color.red(i), Color.green(i), Color.blue(i));
    }

    public static final String hexColorString(Integer num) {
        return num != null ? String.format("#%08x", Arrays.copyOf(new Object[]{Integer.valueOf(num.intValue())}, 1)) : "null";
    }
}
