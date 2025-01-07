package com.android.systemui.shared.system;

import android.app.ActivityManager;
import android.content.ComponentName;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface TaskStackChangeListener {
    default void onTaskMovedToFront() {
    }

    default void onTaskMovedToFront(ActivityManager.RunningTaskInfo runningTaskInfo) {
        int i = runningTaskInfo.taskId;
        onTaskMovedToFront();
    }

    default void onActivityRequestedOrientationChanged(int i) {
    }

    default void onLockTaskModeChanged(int i) {
    }

    default void onTaskCreated(ComponentName componentName) {
    }

    default void onTaskRemoved() {
    }

    default void onTaskStackChanged() {
    }

    default void onTaskStackChangedBackground() {
    }

    default void onTaskProfileLocked(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
    }
}
