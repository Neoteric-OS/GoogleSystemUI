package com.android.wm.shell.draganddrop;

import android.app.ActivityManager;
import android.app.ActivityTaskManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import com.android.internal.protolog.ProtoLogImpl_411527699;
import com.android.wm.shell.common.DisplayLayout;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DragSession {
    public ActivityInfo activityInfo;
    public Intent appData;
    public final DisplayLayout displayLayout;
    public final int hideDragSourceTaskId;
    public PendingIntent launchableIntent;
    public final ActivityTaskManager mActivityTaskManager;
    public final ClipData mInitialDragData;
    public ActivityManager.RunningTaskInfo runningTaskInfo;
    public int runningTaskWinMode = 0;
    public int runningTaskActType = 1;

    public DragSession(ActivityTaskManager activityTaskManager, DisplayLayout displayLayout, ClipData clipData, int i) {
        this.hideDragSourceTaskId = -1;
        this.mActivityTaskManager = activityTaskManager;
        this.mInitialDragData = clipData;
        this.displayLayout = displayLayout;
        int i2 = clipData.getDescription().getExtras() != null ? clipData.getDescription().getExtras().getInt("android.intent.extra.HIDE_DRAG_SOURCE_TASK_ID", -1) : -1;
        this.hideDragSourceTaskId = i2;
        if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
            ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -7476564039895466497L, 1, Long.valueOf(i2));
        }
    }

    public final void updateRunningTask() {
        int i;
        int i2 = this.hideDragSourceTaskId;
        boolean z = i2 != -1;
        List tasks = this.mActivityTaskManager.getTasks(z ? 2 : 1, false);
        if (tasks.isEmpty()) {
            return;
        }
        for (int size = tasks.size() - 1; size >= 0; size--) {
            ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) tasks.get(size);
            if (!z || i2 != (i = runningTaskInfo.taskId)) {
                this.runningTaskInfo = runningTaskInfo;
                this.runningTaskWinMode = runningTaskInfo.getWindowingMode();
                this.runningTaskActType = runningTaskInfo.getActivityType();
                if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                    long j = runningTaskInfo.taskId;
                    Intent intent = runningTaskInfo.baseIntent;
                    ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -2392824589187272349L, 1, Long.valueOf(j), String.valueOf(intent != null ? intent.getComponent() : "null"));
                    return;
                }
                return;
            }
            if (ProtoLogImpl_411527699.Cache.WM_SHELL_DRAG_AND_DROP_enabled[1]) {
                long j2 = i;
                Intent intent2 = runningTaskInfo.baseIntent;
                ProtoLogImpl_411527699.v(ShellProtoLogGroup.WM_SHELL_DRAG_AND_DROP, -8010626571342564658L, 1, Long.valueOf(j2), String.valueOf(intent2 != null ? intent2.getComponent() : "null"));
            }
        }
    }
}
