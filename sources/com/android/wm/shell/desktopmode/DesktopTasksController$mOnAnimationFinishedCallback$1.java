package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.graphics.Rect;
import android.view.SurfaceControl;
import android.window.WindowContainerTransaction;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$mOnAnimationFinishedCallback$1 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object this$0;

    public /* synthetic */ DesktopTasksController$mOnAnimationFinishedCallback$1(int i, Object obj) {
        this.$r8$classId = i;
        this.this$0 = obj;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                SurfaceControl.Transaction transaction = (SurfaceControl.Transaction) obj;
                DesktopModeVisualIndicator desktopModeVisualIndicator = ((DesktopTasksController) this.this$0).visualIndicator;
                if (desktopModeVisualIndicator != null) {
                    desktopModeVisualIndicator.releaseVisualIndicator(transaction);
                }
                ((DesktopTasksController) this.this$0).visualIndicator = null;
                break;
            default:
                DesktopTasksController desktopTasksController = (DesktopTasksController) obj;
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.this$0;
                desktopTasksController.getClass();
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                windowContainerTransaction.setBounds(runningTaskInfo.token, new Rect());
                windowContainerTransaction.setWindowingMode(runningTaskInfo.token, 0);
                desktopTasksController.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
                break;
        }
    }
}
