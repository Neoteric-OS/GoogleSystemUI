package com.android.wm.shell.fullscreen;

import android.app.ActivityManager;
import com.android.wm.shell.recents.RecentTasksController;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class FullscreenTaskListener$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ ActivityManager.RunningTaskInfo f$0;

    public /* synthetic */ FullscreenTaskListener$$ExternalSyntheticLambda0(ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
        this.$r8$classId = i;
        this.f$0 = runningTaskInfo;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = this.$r8$classId;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.f$0;
        switch (i) {
            case 0:
                ((WindowDecorViewModel) obj).onTaskVanished(runningTaskInfo);
                break;
            default:
                RecentTasksController recentTasksController = (RecentTasksController) obj;
                if (runningTaskInfo.isVisible) {
                    recentTasksController.removeSplitPair(runningTaskInfo.taskId);
                    break;
                }
                break;
        }
    }
}
