package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.splitscreen.SplitScreenController;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 implements Function1 {
    public final /* synthetic */ DesktopModeWindowDecorViewModel f$0;
    public final /* synthetic */ DesktopModeWindowDecoration f$1;

    public /* synthetic */ DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13(DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel, DesktopModeWindowDecoration desktopModeWindowDecoration) {
        this.f$0 = desktopModeWindowDecorViewModel;
        this.f$1 = desktopModeWindowDecoration;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = this.f$0;
        desktopModeWindowDecorViewModel.getClass();
        DesktopModeWindowDecoration desktopModeWindowDecoration = this.f$1;
        desktopModeWindowDecoration.closeManageWindowsMenu();
        ActivityManager.RunningTaskInfo runningTaskInfo = desktopModeWindowDecoration.mTaskInfo;
        int intValue = ((Integer) obj).intValue();
        DesktopTasksController desktopTasksController = desktopModeWindowDecorViewModel.mDesktopTasksController;
        desktopTasksController.getClass();
        WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
        ActivityOptions createNewWindowOptions = DesktopTasksController.createNewWindowOptions(runningTaskInfo);
        if (createNewWindowOptions.getLaunchWindowingMode() == 5) {
            windowContainerTransaction.startTask(intValue, createNewWindowOptions.toBundle());
            desktopTasksController.transitions.startTransition(1, windowContainerTransaction, null);
        } else {
            SplitScreenController splitScreenController = desktopTasksController.splitScreenController;
            if (splitScreenController == null) {
                splitScreenController = null;
            }
            int determineNewInstancePosition = splitScreenController.determineNewInstancePosition(runningTaskInfo);
            SplitScreenController splitScreenController2 = desktopTasksController.splitScreenController;
            if (splitScreenController2 == null) {
                splitScreenController2 = null;
            }
            splitScreenController2.startTask(intValue, determineNewInstancePosition, createNewWindowOptions.toBundle(), null);
        }
        return Unit.INSTANCE;
    }
}
