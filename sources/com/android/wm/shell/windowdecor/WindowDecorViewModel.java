package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.view.SurfaceControl;
import com.android.wm.shell.freeform.FreeformTaskTransitionHandler;
import com.android.wm.shell.splitscreen.SplitScreenController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface WindowDecorViewModel {
    void destroyWindowDecoration(ActivityManager.RunningTaskInfo runningTaskInfo);

    void onTaskChanging(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2);

    void onTaskClosing(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2);

    void onTaskInfoChanged(ActivityManager.RunningTaskInfo runningTaskInfo);

    boolean onTaskOpening(ActivityManager.RunningTaskInfo runningTaskInfo, SurfaceControl surfaceControl, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2);

    void onTaskVanished(ActivityManager.RunningTaskInfo runningTaskInfo);

    void setFreeformTaskTransitionStarter(FreeformTaskTransitionHandler freeformTaskTransitionHandler);

    void setSplitScreenController(SplitScreenController splitScreenController);
}
