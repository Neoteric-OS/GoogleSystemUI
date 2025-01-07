package com.android.wm.shell.windowdecor;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import androidx.compose.ui.graphics.ColorKt;
import com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer;
import com.android.wm.shell.splitscreen.SplitScreenController;
import com.android.wm.shell.windowdecor.additionalviewcontainer.AdditionalSystemViewContainer;
import com.android.wm.shell.windowdecor.common.DecorThemeUtil;
import com.android.wm.shell.windowdecor.extension.TaskInfoKt;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopHandleManageWindowsMenu extends ManageWindowsViewContainer {
    public final ActivityManager.RunningTaskInfo callerTaskInfo;
    public final int captionWidth;
    public final int captionX;
    public AdditionalSystemViewContainer menuViewContainer;
    public final SplitScreenController splitScreenController;
    public final WindowManagerWrapper windowManagerWrapper;

    public DesktopHandleManageWindowsMenu(ActivityManager.RunningTaskInfo runningTaskInfo, SplitScreenController splitScreenController, int i, int i2, WindowManagerWrapper windowManagerWrapper, Context context, List list, DesktopModeWindowDecorViewModel$$ExternalSyntheticLambda13 desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, DesktopModeWindowDecoration$$ExternalSyntheticLambda3 desktopModeWindowDecoration$$ExternalSyntheticLambda3) {
        super(ColorKt.m373toArgb8_81llA(new DecorThemeUtil(context).getColorScheme(runningTaskInfo).background), context);
        this.callerTaskInfo = runningTaskInfo;
        this.splitScreenController = splitScreenController;
        this.captionX = i;
        this.captionWidth = i2;
        this.windowManagerWrapper = windowManagerWrapper;
        show(list, desktopModeWindowDecorViewModel$$ExternalSyntheticLambda13, desktopModeWindowDecoration$$ExternalSyntheticLambda3);
    }

    @Override // com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer
    public final void addToContainer(ManageWindowsViewContainer.ManageWindowsView manageWindowsView) {
        Point point = new Point();
        int i = (this.captionWidth / 2) + this.captionX;
        ManageWindowsViewContainer.ManageWindowsView manageWindowsView2 = this.menuView;
        if (manageWindowsView2 == null) {
            manageWindowsView2 = null;
        }
        int i2 = i - (manageWindowsView2.menuWidth / 2);
        if (this.callerTaskInfo.isFreeform()) {
            Rect bounds = this.callerTaskInfo.getConfiguration().windowConfiguration.getBounds();
            point.set(bounds.left, bounds.top);
        } else if (TaskInfoKt.isFullscreen(this.callerTaskInfo)) {
            point.set(i2, 0);
        } else if (TaskInfoKt.isMultiWindow(this.callerTaskInfo)) {
            int i3 = this.callerTaskInfo.taskId;
            SplitScreenController splitScreenController = this.splitScreenController;
            int splitPosition = splitScreenController.getSplitPosition(i3);
            Rect rect = new Rect();
            splitScreenController.getStageBounds(rect, new Rect());
            if (splitPosition == 0) {
                point.set(i2, 0);
            } else if (splitPosition == 1) {
                point.set(rect.width() + i2, 0);
            }
        }
        this.menuViewContainer = new AdditionalSystemViewContainer(this.windowManagerWrapper, this.callerTaskInfo.taskId, point.x, point.y, manageWindowsView.menuWidth, manageWindowsView.menuHeight, manageWindowsView.rootView);
    }

    @Override // com.android.wm.shell.shared.desktopmode.ManageWindowsViewContainer
    public final void close() {
        AdditionalSystemViewContainer additionalSystemViewContainer = this.menuViewContainer;
        if (additionalSystemViewContainer != null) {
            additionalSystemViewContainer.windowManagerWrapper.windowManager.removeViewImmediate(additionalSystemViewContainer.view);
        }
    }
}
