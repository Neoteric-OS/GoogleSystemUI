package com.android.wm.shell.compatui;

import android.R;
import android.app.TaskInfo;
import android.content.ComponentName;
import android.content.Context;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AppCompatUtils {
    public static final boolean isTopActivityExemptFromDesktopWindowing(Context context, TaskInfo taskInfo) {
        String string = context.getResources().getString(R.string.config_systemUi);
        ComponentName componentName = taskInfo.baseActivity;
        if (Intrinsics.areEqual(componentName != null ? componentName.getPackageName() : null, string)) {
            return true;
        }
        return taskInfo.isTopActivityTransparent && taskInfo.numActivities == 1 && !taskInfo.isTopActivityStyleFloating;
    }
}
