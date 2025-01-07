package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.os.Parcelable;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1 implements Runnable {
    public final /* synthetic */ int $displayId;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopTasksController this$0;

    public DesktopTasksController$DesktopModeImpl$moveFocusedTaskToDesktop$1(DesktopTasksController desktopTasksController, int i, int i2) {
        this.$r8$classId = i2;
        switch (i2) {
            case 1:
                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                this.this$0 = desktopTasksController;
                this.$displayId = i;
                break;
            default:
                Parcelable.Creator creator2 = DesktopModeTransitionSource.CREATOR;
                this.this$0 = desktopTasksController;
                this.$displayId = i;
                break;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DesktopTasksController desktopTasksController = this.this$0;
                int i = this.$displayId;
                Parcelable.Creator creator = DesktopModeTransitionSource.CREATOR;
                ArrayList runningTasks = desktopTasksController.shellTaskOrganizer.getRunningTasks(i);
                ArrayList arrayList = new ArrayList();
                for (Object obj : runningTasks) {
                    ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) obj;
                    if (runningTaskInfo.isFocused && (runningTaskInfo.getWindowingMode() == 1 || runningTaskInfo.getWindowingMode() == 6)) {
                        if (runningTaskInfo.getActivityType() != 2) {
                            arrayList.add(obj);
                        }
                    }
                }
                int size = arrayList.size();
                if (size != 0) {
                    if (size != 1) {
                        if (size != 2) {
                            DesktopTasksController.logW("DesktopTasksController: Cannot enter desktop, expected less than 3 focused tasks but found %d", Integer.valueOf(arrayList.size()));
                            break;
                        } else {
                            ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) arrayList.get(0);
                            ActivityManager.RunningTaskInfo runningTaskInfo3 = (ActivityManager.RunningTaskInfo) arrayList.get(1);
                            if (runningTaskInfo2.taskId == runningTaskInfo3.parentTaskId) {
                                runningTaskInfo2 = runningTaskInfo3;
                            }
                            desktopTasksController.moveRunningTaskToDesktop(runningTaskInfo2, new WindowContainerTransaction(), DesktopModeTransitionSource.KEYBOARD_SHORTCUT);
                            break;
                        }
                    } else {
                        desktopTasksController.moveRunningTaskToDesktop((ActivityManager.RunningTaskInfo) CollectionsKt.single(arrayList), new WindowContainerTransaction(), DesktopModeTransitionSource.KEYBOARD_SHORTCUT);
                        break;
                    }
                }
                break;
            default:
                DesktopTasksController desktopTasksController2 = this.this$0;
                int i2 = this.$displayId;
                DesktopModeTransitionSource desktopModeTransitionSource = DesktopModeTransitionSource.KEYBOARD_SHORTCUT;
                ActivityManager.RunningTaskInfo focusedFreeformTask = desktopTasksController2.getFocusedFreeformTask(i2);
                if (focusedFreeformTask != null) {
                    desktopTasksController2.moveToFullscreenWithAnimation(focusedFreeformTask, focusedFreeformTask.positionInParent, desktopModeTransitionSource);
                    break;
                }
                break;
        }
    }
}
