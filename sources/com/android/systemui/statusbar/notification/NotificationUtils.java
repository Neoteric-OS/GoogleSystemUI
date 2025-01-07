package com.android.systemui.statusbar.notification;

import android.content.Context;
import android.graphics.Color;
import com.android.systemui.statusbar.notification.collection.ListEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationUtils {
    public static final int[] sLocationBase = new int[2];
    public static final int[] sLocationOffset = new int[2];

    public static int getFontScaledHeight(int i, Context context) {
        return (int) (context.getResources().getDimensionPixelSize(i) * Math.max(1.0f, context.getResources().getDisplayMetrics().scaledDensity / context.getResources().getDisplayMetrics().density));
    }

    public static float interpolate(float f, float f2, float f3) {
        return (f2 * f3) + ((1.0f - f3) * f);
    }

    public static int interpolateColors(int i, float f, int i2) {
        return Color.argb((int) interpolate(Color.alpha(i), Color.alpha(i2), f), (int) interpolate(Color.red(i), Color.red(i2), f), (int) interpolate(Color.green(i), Color.green(i2), f), (int) interpolate(Color.blue(i), Color.blue(i2), f));
    }

    public static String logKey(ListEntry listEntry) {
        return listEntry == null ? "null" : logKey(listEntry.getKey());
    }

    public static String logKey(String str) {
        if (str == null) {
            return "null";
        }
        return str.replace("\n", "");
    }
}
