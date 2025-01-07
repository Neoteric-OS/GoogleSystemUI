package com.android.wm.shell;

import android.app.ActivityManager;
import android.os.RemoteException;
import android.util.Slog;
import android.window.TaskAppearedInfo;
import com.android.wm.shell.compatui.impl.CompatUIEvents;
import com.android.wm.shell.recents.RecentTasksController;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class ShellTaskOrganizer$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ ShellTaskOrganizer$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        Object obj2 = this.f$0;
        switch (i) {
            case 0:
                RecentTasksController recentTasksController = (RecentTasksController) obj;
                int i2 = ShellTaskOrganizer.$r8$clinit;
                ActivityManager.RunningTaskInfo taskInfo = ((TaskAppearedInfo) obj2).getTaskInfo();
                if (recentTasksController.mListener != null && recentTasksController.shouldEnableRunningTasksForDesktopMode() && taskInfo.realActivity != null) {
                    try {
                        recentTasksController.mListener.onRunningTaskAppeared(taskInfo);
                        break;
                    } catch (RemoteException e) {
                        Slog.w("RecentTasksController", "Failed call onRunningTaskAppeared", e);
                        return;
                    }
                }
                break;
            default:
                ShellTaskOrganizer shellTaskOrganizer = (ShellTaskOrganizer) obj2;
                CompatUIEvents compatUIEvents = (CompatUIEvents) obj;
                int i3 = ShellTaskOrganizer.$r8$clinit;
                int i4 = compatUIEvents.eventId;
                if (i4 == 0) {
                    shellTaskOrganizer.onSizeCompatRestartButtonAppeared((CompatUIEvents.SizeCompatRestartButtonAppeared) compatUIEvents);
                    break;
                } else if (i4 == 1) {
                    shellTaskOrganizer.onSizeCompatRestartButtonClicked((CompatUIEvents.SizeCompatRestartButtonClicked) compatUIEvents);
                    break;
                }
                break;
        }
    }
}
