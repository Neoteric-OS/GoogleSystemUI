package com.android.wm.shell.windowdecor.extension;

import android.app.ActivityManager;
import android.app.TaskInfo;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class TaskInfoKt {
    public static final boolean isFullscreen(TaskInfo taskInfo) {
        return taskInfo.getWindowingMode() == 1;
    }

    public static final boolean isLightCaptionBarAppearance(TaskInfo taskInfo) {
        ActivityManager.TaskDescription taskDescription = taskInfo.taskDescription;
        return ((taskDescription != null ? taskDescription.getTopOpaqueSystemBarsAppearance() : 0) & 256) != 0;
    }

    public static final boolean isMultiWindow(TaskInfo taskInfo) {
        return taskInfo.getWindowingMode() == 6;
    }

    public static final boolean isTransparentCaptionBarAppearance(TaskInfo taskInfo) {
        ActivityManager.TaskDescription taskDescription = taskInfo.taskDescription;
        return ((taskDescription != null ? taskDescription.getTopOpaqueSystemBarsAppearance() : 0) & 128) != 0;
    }
}
