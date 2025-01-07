package com.android.wm.shell;

import android.app.ActivityManager;
import android.os.RemoteException;
import android.util.Slog;
import com.android.wm.shell.recents.RecentTasksController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShellTaskOrganizer$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ActivityManager.RunningTaskInfo f$0;

    public /* synthetic */ ShellTaskOrganizer$$ExternalSyntheticLambda1(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
        this.$r8$classId = i;
        this.f$0 = runningTaskInfo;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.f$0;
        RecentTasksController recentTasksController = (RecentTasksController) obj;
        switch (i) {
            case 0:
                int i2 = ShellTaskOrganizer.$r8$clinit;
                recentTasksController.notifyRecentTasksChanged();
                if (recentTasksController.mListener != null && recentTasksController.shouldEnableRunningTasksForDesktopMode() && runningTaskInfo.realActivity != null) {
                    try {
                        recentTasksController.mListener.onRunningTaskChanged(runningTaskInfo);
                        break;
                    } catch (RemoteException e) {
                        Slog.w("RecentTasksController", "Failed call onRunningTaskChanged", e);
                        return;
                    }
                }
                break;
            default:
                int i3 = ShellTaskOrganizer.$r8$clinit;
                recentTasksController.getClass();
                recentTasksController.removeSplitPair(runningTaskInfo.taskId);
                recentTasksController.notifyRecentTasksChanged();
                if (recentTasksController.mListener != null && recentTasksController.shouldEnableRunningTasksForDesktopMode() && runningTaskInfo.realActivity != null) {
                    try {
                        recentTasksController.mListener.onRunningTaskVanished(runningTaskInfo);
                        break;
                    } catch (RemoteException e2) {
                        Slog.w("RecentTasksController", "Failed call onRunningTaskVanished", e2);
                    }
                }
                break;
        }
    }
}
