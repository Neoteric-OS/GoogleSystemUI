package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Rect;
import android.util.Size;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.common.TaskStackListenerCallback;
import com.android.wm.shell.common.TaskStackListenerImpl;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInit;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopActivityOrientationChangeHandler {
    public final ToggleResizeDesktopTaskTransitionHandler resizeHandler;
    public final ShellTaskOrganizer shellTaskOrganizer;
    public final DesktopModeTaskRepository taskRepository;
    public final TaskStackListenerImpl taskStackListener;

    public DesktopActivityOrientationChangeHandler(Context context, ShellInit shellInit, ShellTaskOrganizer shellTaskOrganizer, TaskStackListenerImpl taskStackListenerImpl, ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler, DesktopModeTaskRepository desktopModeTaskRepository) {
        this.shellTaskOrganizer = shellTaskOrganizer;
        this.taskStackListener = taskStackListenerImpl;
        this.resizeHandler = toggleResizeDesktopTaskTransitionHandler;
        this.taskRepository = desktopModeTaskRepository;
        if (DesktopModeStatus.canEnterDesktopMode(context)) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopActivityOrientationChangeHandler.1
                @Override // java.lang.Runnable
                public final void run() {
                    final DesktopActivityOrientationChangeHandler desktopActivityOrientationChangeHandler = DesktopActivityOrientationChangeHandler.this;
                    desktopActivityOrientationChangeHandler.getClass();
                    desktopActivityOrientationChangeHandler.taskStackListener.addListener(new TaskStackListenerCallback() { // from class: com.android.wm.shell.desktopmode.DesktopActivityOrientationChangeHandler$onInit$1
                        @Override // com.android.wm.shell.common.TaskStackListenerCallback
                        public final void onActivityRequestedOrientationChanged(int i, int i2) {
                            DesktopActivityOrientationChangeHandler.this.handleActivityOrientationChange(i, i2);
                        }
                    });
                }
            }, this);
        }
    }

    public final void handleActivityOrientationChange(int i, int i2) {
        Rect bounds;
        int height;
        int width;
        ActivityManager.RunningTaskInfo runningTaskInfo = this.shellTaskOrganizer.getRunningTaskInfo(i);
        if (runningTaskInfo == null) {
            return;
        }
        if (this.taskRepository.getVisibleTaskCount(runningTaskInfo.displayId) <= 0 || !runningTaskInfo.isFreeform() || runningTaskInfo.isResizeable || (width = bounds.width()) == (height = (bounds = runningTaskInfo.configuration.windowConfiguration.getBounds()).height())) {
            return;
        }
        char c = width > height ? (char) 2 : (char) 1;
        if ((c == 1 && ActivityInfo.isFixedOrientationLandscape(i2)) || (c == 2 && ActivityInfo.isFixedOrientationPortrait(i2))) {
            Size size = new Size(height, width);
            int centerX = bounds.centerX() - (size.getWidth() / 2);
            int width2 = size.getWidth() + centerX;
            int i3 = bounds.top;
            WindowContainerTransaction bounds2 = new WindowContainerTransaction().setBounds(runningTaskInfo.token, new Rect(centerX, i3, width2, size.getHeight() + i3));
            ToggleResizeDesktopTaskTransitionHandler toggleResizeDesktopTaskTransitionHandler = this.resizeHandler;
            toggleResizeDesktopTaskTransitionHandler.transitions.startTransition(1014, bounds2, toggleResizeDesktopTaskTransitionHandler);
            toggleResizeDesktopTaskTransitionHandler.initialBounds = null;
        }
    }
}
