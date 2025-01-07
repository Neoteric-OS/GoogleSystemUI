package com.android.wm.shell.desktopmode;

import com.android.systemui.navigationbar.gestural.EdgeBackGestureHandler$$ExternalSyntheticLambda1;
import com.android.systemui.wmshell.WMShell;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1 implements Runnable {
    public final /* synthetic */ Executor $callbackExecutor;
    public final /* synthetic */ Object $listener;
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopTasksController this$0;

    public /* synthetic */ DesktopTasksController$DesktopModeImpl$addVisibleTasksListener$1(DesktopTasksController desktopTasksController, Object obj, Executor executor, int i) {
        this.$r8$classId = i;
        this.this$0 = desktopTasksController;
        this.$listener = obj;
        this.$callbackExecutor = executor;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                DesktopTasksController desktopTasksController = this.this$0;
                desktopTasksController.taskRepository.addVisibleTasksListener((WMShell.AnonymousClass15) this.$listener, this.$callbackExecutor);
                break;
            default:
                DesktopTasksController desktopTasksController2 = this.this$0;
                EdgeBackGestureHandler$$ExternalSyntheticLambda1 edgeBackGestureHandler$$ExternalSyntheticLambda1 = (EdgeBackGestureHandler$$ExternalSyntheticLambda1) this.$listener;
                Executor executor = this.$callbackExecutor;
                DesktopModeTaskRepository desktopModeTaskRepository = desktopTasksController2.taskRepository;
                desktopModeTaskRepository.desktopGestureExclusionListener = edgeBackGestureHandler$$ExternalSyntheticLambda1;
                desktopModeTaskRepository.desktopGestureExclusionExecutor = executor;
                executor.execute(new DesktopModeTaskRepository.AnonymousClass1(desktopModeTaskRepository, 2));
                break;
        }
    }
}
