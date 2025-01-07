package com.android.wm.shell.common;

import android.app.ActivityManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface TaskStackListenerCallback {
    default void onTaskMovedToFront() {
    }

    default void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int i = runningTaskInfo.taskId;
        onTaskMovedToFront();
    }

    default void onActivityPinned(String str) {
    }

    default void onActivityUnpinned() {
    }

    default void onRecentTaskListUpdated() {
    }

    default void onTaskCreated() {
    }

    default void onTaskStackChanged() {
    }

    default void onActivityRequestedOrientationChanged(int i, int i2) {
    }

    default void onActivityRestartAttempt(ActivityManager.RunningTaskInfo runningTaskInfo, boolean z) {
    }
}
