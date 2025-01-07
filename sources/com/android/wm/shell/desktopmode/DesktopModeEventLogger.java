package com.android.wm.shell.desktopmode;

import com.android.internal.util.FrameworkStatsLog;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeEventLogger {
    public static void logTaskUpdate(int i, int i2, DesktopModeEventLogger$Companion$TaskUpdate desktopModeEventLogger$Companion$TaskUpdate) {
        FrameworkStatsLog.write(819, i, desktopModeEventLogger$Companion$TaskUpdate.instanceId, desktopModeEventLogger$Companion$TaskUpdate.uid, desktopModeEventLogger$Companion$TaskUpdate.taskHeight, desktopModeEventLogger$Companion$TaskUpdate.taskWidth, desktopModeEventLogger$Companion$TaskUpdate.taskX, desktopModeEventLogger$Companion$TaskUpdate.taskY, i2, 0, 0, desktopModeEventLogger$Companion$TaskUpdate.visibleTaskCount);
    }
}
