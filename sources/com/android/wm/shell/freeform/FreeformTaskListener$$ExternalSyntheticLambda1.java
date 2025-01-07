package com.android.wm.shell.freeform;

import android.app.ActivityManager;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository$desktopTaskDataByDisplayId$1;
import java.util.Iterator;
import java.util.function.Consumer;
import kotlin.sequences.ConstrainedOnceSequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class FreeformTaskListener$$ExternalSyntheticLambda1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ActivityManager.RunningTaskInfo f$0;

    public /* synthetic */ FreeformTaskListener$$ExternalSyntheticLambda1(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
        this.$r8$classId = i;
        this.f$0 = runningTaskInfo;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        Integer num;
        int i = this.$r8$classId;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.f$0;
        DesktopModeTaskRepository desktopModeTaskRepository = (DesktopModeTaskRepository) obj;
        switch (i) {
            case 0:
                if (runningTaskInfo.isVisible) {
                    desktopModeTaskRepository.addActiveTask(runningTaskInfo.displayId, runningTaskInfo.taskId);
                } else {
                    int i2 = runningTaskInfo.taskId;
                    Iterator it = ((ConstrainedOnceSequence) desktopModeTaskRepository.desktopTaskDataSequence()).iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (((DesktopModeTaskRepository.DesktopTaskData) it.next()).closingTasks.contains(Integer.valueOf(i2))) {
                                int i3 = runningTaskInfo.taskId;
                                DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$1 = desktopModeTaskRepository.desktopTaskDataByDisplayId;
                                int size = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.size();
                                for (int i4 = 0; i4 < size; i4++) {
                                    int keyAt = desktopModeTaskRepository$desktopTaskDataByDisplayId$1.keyAt(i4);
                                    if (((DesktopModeTaskRepository.DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$1.valueAt(i4)).closingTasks.remove(Integer.valueOf(i3))) {
                                        DesktopModeTaskRepository.logD("Removed closing task=%d displayId=%d", Integer.valueOf(i3), Integer.valueOf(keyAt));
                                    }
                                }
                            }
                        }
                    }
                }
                desktopModeTaskRepository.updateTaskVisibility(runningTaskInfo.displayId, runningTaskInfo.taskId, runningTaskInfo.isVisible);
                break;
            case 1:
                desktopModeTaskRepository.addOrMoveFreeformTaskToTop(runningTaskInfo.displayId, runningTaskInfo.taskId);
                if (runningTaskInfo.isVisible) {
                    desktopModeTaskRepository.addActiveTask(runningTaskInfo.displayId, runningTaskInfo.taskId);
                    desktopModeTaskRepository.updateTaskVisibility(runningTaskInfo.displayId, runningTaskInfo.taskId, true);
                    break;
                }
                break;
            case 2:
                int i5 = runningTaskInfo.displayId;
                int i6 = runningTaskInfo.taskId;
                Object[] objArr = {Integer.valueOf(i6)};
                desktopModeTaskRepository.getClass();
                DesktopModeTaskRepository.logD("Removes freeform task: taskId=%d", objArr);
                if (i5 == -1) {
                    DesktopModeTaskRepository$desktopTaskDataByDisplayId$1 desktopModeTaskRepository$desktopTaskDataByDisplayId$12 = desktopModeTaskRepository.desktopTaskDataByDisplayId;
                    int size2 = desktopModeTaskRepository$desktopTaskDataByDisplayId$12.size();
                    int i7 = 0;
                    while (true) {
                        if (i7 < size2) {
                            int keyAt2 = desktopModeTaskRepository$desktopTaskDataByDisplayId$12.keyAt(i7);
                            if (((DesktopModeTaskRepository.DesktopTaskData) desktopModeTaskRepository$desktopTaskDataByDisplayId$12.valueAt(i7)).freeformTasksInZOrder.contains(Integer.valueOf(i6))) {
                                num = Integer.valueOf(keyAt2);
                            } else {
                                i7++;
                            }
                        } else {
                            DesktopModeTaskRepository.logW("No display id found for task: taskId=%d", Integer.valueOf(i6));
                            num = null;
                        }
                    }
                    if (num != null) {
                        desktopModeTaskRepository.removeTaskFromDisplay(num.intValue(), i6);
                        break;
                    }
                } else {
                    desktopModeTaskRepository.removeTaskFromDisplay(i5, i6);
                    break;
                }
                break;
            default:
                desktopModeTaskRepository.addOrMoveFreeformTaskToTop(runningTaskInfo.displayId, runningTaskInfo.taskId);
                break;
        }
    }
}
