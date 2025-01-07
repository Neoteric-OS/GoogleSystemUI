package com.android.wm.shell.recents;

import android.app.ActivityManager;
import android.os.IInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface IRecentTasksListener extends IInterface {
    void onRecentTasksChanged();

    void onRunningTaskAppeared(ActivityManager.RunningTaskInfo runningTaskInfo);

    void onRunningTaskChanged(ActivityManager.RunningTaskInfo runningTaskInfo);

    void onRunningTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo);

    void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo);
}
