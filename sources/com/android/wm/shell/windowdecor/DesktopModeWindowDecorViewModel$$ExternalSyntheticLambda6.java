package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.windowdecor.WindowDecoration;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6 implements Consumer {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ DesktopModeWindowDecorViewModel f$0;
    public final /* synthetic */ ActivityManager.RunningTaskInfo f$1;

    public /* synthetic */ DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda6(DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel, ActivityManager.RunningTaskInfo runningTaskInfo, int i) {
        this.$r8$classId = i;
        this.f$0 = desktopModeWindowDecorViewModel;
        this.f$1 = runningTaskInfo;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel = this.f$0;
                ActivityManager.RunningTaskInfo runningTaskInfo = this.f$1;
                DesktopModeTransitionSource desktopModeTransitionSource = (DesktopModeTransitionSource) obj;
                desktopModeWindowDecorViewModel.getClass();
                int i = runningTaskInfo.taskId;
                DesktopModeWindowDecoration desktopModeWindowDecoration = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel.mWindowDecorByTaskId.get(i);
                if (desktopModeWindowDecoration != null) {
                    WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                    desktopModeWindowDecorViewModel.mInteractionJankMonitor.begin(desktopModeWindowDecoration.mTaskSurface, desktopModeWindowDecorViewModel.mContext, desktopModeWindowDecorViewModel.mMainHandler, 112);
                    int i2 = desktopModeWindowDecoration.mTaskInfo.getWindowingMode() == 1 ? R.dimen.desktop_mode_fullscreen_decor_caption_height : R.dimen.desktop_mode_freeform_decor_caption_height;
                    if (i2 != 0 && desktopModeWindowDecoration.mIsCaptionVisible) {
                        WindowDecoration.WindowDecorationInsets windowDecorationInsets = new WindowDecoration.WindowDecorationInsets(desktopModeWindowDecoration.mTaskInfo.token, desktopModeWindowDecoration.mOwner, new Rect(0, 0, 0, WindowDecoration.loadDimensionPixelSize(i2, desktopModeWindowDecoration.mContext.getResources())), null, 0);
                        if (!windowDecorationInsets.equals(desktopModeWindowDecoration.mWindowDecorationInsets)) {
                            desktopModeWindowDecoration.mWindowDecorationInsets = windowDecorationInsets;
                            windowDecorationInsets.addOrUpdate(windowContainerTransaction);
                        }
                    }
                    desktopModeWindowDecorViewModel.mDesktopTasksController.moveTaskToDesktop(i, windowContainerTransaction, desktopModeTransitionSource);
                    desktopModeWindowDecoration.closeHandleMenu();
                    break;
                }
                break;
            default:
                DesktopModeWindowDecorViewModel desktopModeWindowDecorViewModel2 = this.f$0;
                ActivityManager.RunningTaskInfo runningTaskInfo2 = this.f$1;
                Uri uri = (Uri) obj;
                desktopModeWindowDecorViewModel2.getClass();
                DesktopModeWindowDecoration desktopModeWindowDecoration2 = (DesktopModeWindowDecoration) desktopModeWindowDecorViewModel2.mWindowDecorByTaskId.get(runningTaskInfo2.taskId);
                if (desktopModeWindowDecoration2 != null) {
                    desktopModeWindowDecorViewModel2.mContext.startActivityAsUser(Intent.makeMainSelectorActivity("android.intent.action.MAIN", "android.intent.category.APP_BROWSER").setData(uri).addFlags(268435456), desktopModeWindowDecoration2.mUserContext.getUser());
                    desktopModeWindowDecoration2.closeHandleMenu();
                    desktopModeWindowDecoration2.closeMaximizeMenu();
                    break;
                }
                break;
        }
    }
}
