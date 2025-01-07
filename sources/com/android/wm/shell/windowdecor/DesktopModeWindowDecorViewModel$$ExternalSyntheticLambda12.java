package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import com.android.wm.shell.desktopmode.DesktopActivityOrientationChangeHandler;
import com.android.wm.shell.desktopmode.DesktopTasksLimiter;
import com.android.wm.shell.windowdecor.DesktopModeWindowDecorViewModel;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda12 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda12(int i, Object obj, Object obj2) {
        this.$r8$classId = i;
        this.f$0 = obj;
        this.f$1 = obj2;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        ActivityInfo activityInfo;
        switch (this.$r8$classId) {
            case 0:
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.f$0;
                ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopActivityOrientationChangeHandler desktopActivityOrientationChangeHandler = (DesktopActivityOrientationChangeHandler) obj;
                desktopActivityOrientationChangeHandler.getClass();
                ActivityInfo activityInfo2 = runningTaskInfo2.topActivityInfo;
                if (activityInfo2 != null && (activityInfo = runningTaskInfo.topActivityInfo) != null) {
                    int i = activityInfo.screenOrientation;
                    int i2 = activityInfo2.screenOrientation;
                    if (i != i2) {
                        desktopActivityOrientationChangeHandler.handleActivityOrientationChange(runningTaskInfo2.taskId, i2);
                        break;
                    }
                }
                break;
            default:
                DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener desktopModeTouchEventListener = (DesktopModeWindowDecorViewModel.DesktopModeTouchEventListener) this.f$0;
                IBinder iBinder = (IBinder) this.f$1;
                DesktopTasksLimiter desktopTasksLimiter = (DesktopTasksLimiter) obj;
                int i3 = desktopModeTouchEventListener.mDisplayId;
                int i4 = desktopModeTouchEventListener.mTaskId;
                desktopTasksLimiter.getClass();
                desktopTasksLimiter.minimizeTransitionObserver.pendingTransitionTokensAndTasks.put(iBinder, new DesktopTasksLimiter.TaskDetails(i3, i4));
                break;
        }
    }
}
