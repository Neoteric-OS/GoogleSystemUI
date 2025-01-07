package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.os.IBinder;
import com.android.wm.shell.common.SingleInstanceRemoteListener;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.desktopmode.DesktopTasksLimiter;
import java.util.function.Consumer;
import kotlin.Unit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$addPendingMinimizeTransition$1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object $taskToMinimize;
    public final /* synthetic */ Object $transition;

    public /* synthetic */ DesktopTasksController$addPendingMinimizeTransition$1(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.$transition = obj;
        this.$taskToMinimize = obj2;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Unit unit;
        switch (this.$r8$classId) {
            case 0:
                DesktopTasksLimiter desktopTasksLimiter = (DesktopTasksLimiter) obj;
                IBinder iBinder = (IBinder) this.$transition;
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.$taskToMinimize;
                int i = runningTaskInfo.displayId;
                int i2 = runningTaskInfo.taskId;
                desktopTasksLimiter.getClass();
                desktopTasksLimiter.minimizeTransitionObserver.pendingTransitionTokensAndTasks.put(iBinder, new DesktopTasksLimiter.TaskDetails(i, i2));
                break;
            default:
                IDesktopTaskListener$Stub$Proxy iDesktopTaskListener$Stub$Proxy = (IDesktopTaskListener$Stub$Proxy) this.$transition;
                if (iDesktopTaskListener$Stub$Proxy != null) {
                    SingleInstanceRemoteListener singleInstanceRemoteListener = ((DesktopTasksController.IDesktopModeImpl) this.$taskToMinimize).remoteListener;
                    if (singleInstanceRemoteListener == null) {
                        singleInstanceRemoteListener = null;
                    }
                    singleInstanceRemoteListener.register(iDesktopTaskListener$Stub$Proxy);
                    unit = Unit.INSTANCE;
                } else {
                    unit = null;
                }
                if (unit == null) {
                    SingleInstanceRemoteListener singleInstanceRemoteListener2 = ((DesktopTasksController.IDesktopModeImpl) this.$taskToMinimize).remoteListener;
                    (singleInstanceRemoteListener2 != null ? singleInstanceRemoteListener2 : null).unregister();
                    break;
                }
                break;
        }
    }
}
