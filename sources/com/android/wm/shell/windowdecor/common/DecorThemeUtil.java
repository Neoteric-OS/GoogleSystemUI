package com.android.wm.shell.windowdecor.common;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Color;
import androidx.compose.material3.ColorScheme;
import androidx.compose.material3.DynamicTonalPaletteKt;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DecorThemeUtil {
    public final Context context;
    public final ColorScheme darkColors;
    public final ColorScheme lightColors;

    public DecorThemeUtil(Context context) {
        this.context = context;
        this.lightColors = DynamicTonalPaletteKt.dynamicLightColorScheme(context);
        this.darkColors = DynamicTonalPaletteKt.dynamicDarkColorScheme(context);
    }

    public final Theme getAppTheme(ActivityManager.RunningTaskInfo runningTaskInfo) {
        ActivityManager.TaskDescription taskDescription = runningTaskInfo.taskDescription;
        return taskDescription != null ? ((double) Color.valueOf(taskDescription.getBackgroundColor()).luminance()) < 0.5d ? Theme.DARK : Theme.LIGHT : (this.context.getResources().getConfiguration().uiMode & 48) == 32 ? Theme.DARK : Theme.LIGHT;
    }

    public final ColorScheme getColorScheme(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int ordinal = getAppTheme(runningTaskInfo).ordinal();
        if (ordinal == 0) {
            return this.lightColors;
        }
        if (ordinal == 1) {
            return this.darkColors;
        }
        throw new NoWhenBranchMatchedException();
    }
}
