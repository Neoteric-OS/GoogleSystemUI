package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.app.ActivityOptions;
import android.app.ActivityTaskManager;
import android.app.IActivityManager;
import android.app.IActivityTaskManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.os.RemoteException;
import android.window.TaskSnapshot;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.desktopmode.DesktopTasksController;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.windowdecor.WindowDecoration;
import com.android.wm.shell.windowdecor.viewholder.AppHandleViewHolder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3 implements Function0 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopModeWindowDecorViewModel f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda3(DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = desktopModeWindowDecorViewModel;
        this.f$1 = obj;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        Intent launchIntentForPackage;
        ComponentName componentName;
        switch (this.$r8$classId) {
            case 0:
                ActivityManager.RunningTaskInfo runningTaskInfo = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = this.f$0;
                desktopModeWindowDecorViewModel.getClass();
                desktopModeWindowDecorViewModel.onMaximizeOrRestore(runningTaskInfo.taskId, "maximize_menu");
                return Unit.INSTANCE;
            case 1:
                ActivityManager.RunningTaskInfo runningTaskInfo2 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel2 = this.f$0;
                desktopModeWindowDecorViewModel2.getClass();
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel2.mWindowDecorByTaskId.get(runningTaskInfo2.taskId);
                if (desktopModeWindowDecoration != null) {
                    desktopModeWindowDecoration.closeHandleMenu();
                    ActivityManager.RunningTaskInfo runningTaskInfo3 = desktopModeWindowDecoration.mTaskInfo;
                    DesktopTasksController desktopTasksController = desktopModeWindowDecorViewModel2.mDesktopTasksController;
                    desktopTasksController.getClass();
                    ComponentName componentName2 = runningTaskInfo3.baseActivity;
                    if (componentName2 != null && (launchIntentForPackage = desktopTasksController.context.getPackageManager().getLaunchIntentForPackage(componentName2.getPackageName())) != null) {
                        launchIntentForPackage.setFlags(402653184);
                        PendingIntent activity = PendingIntent.getActivity(desktopTasksController.context, 0, launchIntentForPackage, 67108864);
                        ActivityOptions createNewWindowOptions = DesktopTasksController.createNewWindowOptions(runningTaskInfo3);
                        int launchWindowingMode = createNewWindowOptions.getLaunchWindowingMode();
                        if (launchWindowingMode == 5) {
                            WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                            windowContainerTransaction.sendPendingIntent(activity, launchIntentForPackage, createNewWindowOptions.toBundle());
                            desktopTasksController.transitions.startTransition(1, windowContainerTransaction, null);
                        } else if (launchWindowingMode == 6) {
                            SplitScreenController splitScreenController = desktopTasksController.splitScreenController;
                            if (splitScreenController == null) {
                                splitScreenController = null;
                            }
                            int determineNewInstancePosition = splitScreenController.determineNewInstancePosition(runningTaskInfo3);
                            SplitScreenController splitScreenController2 = desktopTasksController.splitScreenController;
                            (splitScreenController2 == null ? null : splitScreenController2).startIntent(activity, desktopTasksController.context.getUserId(), launchIntentForPackage, determineNewInstancePosition, createNewWindowOptions.toBundle(), null);
                        }
                    }
                }
                return Unit.INSTANCE;
            case 2:
                ActivityManager.RunningTaskInfo runningTaskInfo4 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel3 = this.f$0;
                desktopModeWindowDecorViewModel3.getClass();
                desktopModeWindowDecorViewModel3.onSnapResize(runningTaskInfo4.taskId, true);
                return Unit.INSTANCE;
            case 3:
                ActivityManager.RunningTaskInfo runningTaskInfo5 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel4 = this.f$0;
                desktopModeWindowDecorViewModel4.getClass();
                desktopModeWindowDecorViewModel4.onSnapResize(runningTaskInfo5.taskId, false);
                return Unit.INSTANCE;
            case 4:
                ActivityManager.RunningTaskInfo runningTaskInfo6 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel5 = this.f$0;
                desktopModeWindowDecorViewModel5.getClass();
                int i = runningTaskInfo6.taskId;
                DesktopModeWindowDecoration desktopModeWindowDecoration2 = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel5.mWindowDecorByTaskId.get(i);
                if (desktopModeWindowDecoration2 != null) {
                    desktopModeWindowDecoration2.closeHandleMenu();
                    SplitScreenController splitScreenController3 = desktopModeWindowDecorViewModel5.mSplitScreenController;
                    if (splitScreenController3 == null || !splitScreenController3.isTaskInSplitScreen(i)) {
                        DesktopModeTransitionSource desktopModeTransitionSource = DesktopModeTransitionSource.APP_HANDLE_MENU_BUTTON;
                        DesktopTasksController desktopTasksController2 = desktopModeWindowDecorViewModel5.mDesktopTasksController;
                        ActivityManager.RunningTaskInfo runningTaskInfo7 = desktopTasksController2.shellTaskOrganizer.getRunningTaskInfo(i);
                        if (runningTaskInfo7 != null) {
                            desktopTasksController2.moveToFullscreenWithAnimation(runningTaskInfo7, runningTaskInfo7.positionInParent, desktopModeTransitionSource);
                        }
                    } else {
                        desktopModeWindowDecorViewModel5.mSplitScreenController.moveTaskToFullscreen(i);
                    }
                }
                return Unit.INSTANCE;
            case 5:
                ActivityManager.RunningTaskInfo runningTaskInfo8 = (ActivityManager.RunningTaskInfo) this.f$1;
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel6 = this.f$0;
                desktopModeWindowDecorViewModel6.getClass();
                DesktopModeWindowDecoration desktopModeWindowDecoration3 = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel6.mWindowDecorByTaskId.get(runningTaskInfo8.taskId);
                if (desktopModeWindowDecoration3 != null) {
                    desktopModeWindowDecoration3.closeHandleMenu();
                    boolean z = desktopModeWindowDecoration3.mWindowDecorViewHolder instanceof AppHandleViewHolder;
                    desktopModeWindowDecorViewModel6.mDesktopTasksController.requestSplit(desktopModeWindowDecoration3.mTaskInfo, false);
                }
                return Unit.INSTANCE;
            default:
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel7 = this.f$0;
                desktopModeWindowDecorViewModel7.getClass();
                DesktopModeWindowDecoration desktopModeWindowDecoration4 = (DesktopModeWindowDecoration) this.f$1;
                desktopModeWindowDecoration4.closeHandleMenu();
                ActivityManager.RunningTaskInfo runningTaskInfo9 = desktopModeWindowDecoration4.mTaskInfo;
                ArrayList arrayList = new ArrayList();
                IActivityManager service = ActivityManager.getService();
                IActivityTaskManager service2 = ActivityTaskManager.getService();
                try {
                    List<ActivityManager.RecentTaskInfo> recentTasks = desktopModeWindowDecorViewModel7.mActivityTaskManager.getRecentTasks(Integer.MAX_VALUE, 1, service.getCurrentUser().id);
                    String packageName = runningTaskInfo9.baseActivity.getPackageName();
                    for (ActivityManager.RecentTaskInfo recentTaskInfo : recentTasks) {
                        if (recentTaskInfo.taskId != runningTaskInfo9.taskId && (componentName = recentTaskInfo.baseActivity) != null) {
                            String packageName2 = componentName.getPackageName();
                            if (packageName2.equals(packageName) && recentTaskInfo.baseActivity != null && packageName.equals(packageName2)) {
                                try {
                                    TaskSnapshot taskSnapshot = service2.getTaskSnapshot(recentTaskInfo.taskId, false);
                                    if (taskSnapshot == null) {
                                        taskSnapshot = service2.takeTaskSnapshot(recentTaskInfo.taskId, false);
                                    }
                                    arrayList.add(new Pair(Integer.valueOf(recentTaskInfo.taskId), taskSnapshot));
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                    }
                    DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 = new DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13(desktopModeWindowDecorViewModel7, desktopModeWindowDecoration4);
                    if (desktopModeWindowDecoration4.mTaskInfo.isFreeform()) {
                        desktopModeWindowDecoration4.mManageWindowsMenu = new DesktopHeaderManageWindowsMenu(desktopModeWindowDecoration4.mTaskInfo, desktopModeWindowDecoration4.mDisplayController, desktopModeWindowDecoration4.mRootTaskDisplayAreaOrganizer, desktopModeWindowDecoration4.mContext, desktopModeWindowDecoration4.mSurfaceControlBuilderSupplier, desktopModeWindowDecoration4.mSurfaceControlTransactionSupplier, arrayList, desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(desktopModeWindowDecoration4, 1));
                    } else {
                        ActivityManager.RunningTaskInfo runningTaskInfo10 = desktopModeWindowDecoration4.mTaskInfo;
                        SplitScreenController splitScreenController4 = desktopModeWindowDecoration4.mSplitScreenController;
                        WindowDecoration.RelayoutResult relayoutResult = desktopModeWindowDecoration4.mResult;
                        desktopModeWindowDecoration4.mManageWindowsMenu = new DesktopHandleManageWindowsMenu(runningTaskInfo10, splitScreenController4, relayoutResult.mCaptionX, relayoutResult.mCaptionWidth, desktopModeWindowDecoration4.mWindowManagerWrapper, desktopModeWindowDecoration4.mContext, arrayList, desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, new DesktopModeWindowDecoration$$ExternalSyntheticLambda3(desktopModeWindowDecoration4, 2));
                    }
                    return Unit.INSTANCE;
                } catch (RemoteException e2) {
                    throw new RuntimeException(e2);
                }
        }
    }
}
