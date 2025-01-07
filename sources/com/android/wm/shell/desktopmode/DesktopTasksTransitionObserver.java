package com.android.wm.shell.desktopmode;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import android.window.WindowContainerTransaction;
import android.window.flags.DesktopModeFlags;
import com.android.internal.protolog.ProtoLog;
import com.android.wm.shell.ShellTaskOrganizer;
import com.android.wm.shell.protolog.ShellProtoLogGroup;
import com.android.wm.shell.shared.desktopmode.DesktopModeStatus;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksTransitionObserver implements Transitions.TransitionObserver {
    public final DesktopModeTaskRepository desktopModeTaskRepository;
    public final ShellTaskOrganizer shellTaskOrganizer;
    public final Transitions transitions;

    public DesktopTasksTransitionObserver(Context context, DesktopModeTaskRepository desktopModeTaskRepository, Transitions transitions, ShellTaskOrganizer shellTaskOrganizer, ShellInit shellInit) {
        this.desktopModeTaskRepository = desktopModeTaskRepository;
        this.transitions = transitions;
        this.shellTaskOrganizer = shellTaskOrganizer;
        if (Transitions.ENABLE_SHELL_TRANSITIONS && DesktopModeStatus.canEnterDesktopMode(context)) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.desktopmode.DesktopTasksTransitionObserver.1
                @Override // java.lang.Runnable
                public final void run() {
                    DesktopTasksTransitionObserver desktopTasksTransitionObserver = DesktopTasksTransitionObserver.this;
                    desktopTasksTransitionObserver.getClass();
                    ProtoLog.d(ShellProtoLogGroup.WM_SHELL_DESKTOP_MODE, "DesktopTasksTransitionObserver: onInit", new Object[0]);
                    desktopTasksTransitionObserver.transitions.registerObserver(desktopTasksTransitionObserver);
                }
            }, this);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        if (DesktopModeFlags.ENABLE_DESKTOP_WINDOWING_WALLPAPER_ACTIVITY.isTrue()) {
            for (TransitionInfo.Change change : transitionInfo.getChanges()) {
                ActivityManager.RunningTaskInfo taskInfo = change.getTaskInfo();
                if (taskInfo != null) {
                    ComponentName componentName = DesktopWallpaperActivity.wallpaperActivityComponent;
                    ComponentName component = taskInfo.baseIntent.getComponent();
                    if (component != null ? component.equals(DesktopWallpaperActivity.wallpaperActivityComponent) : false) {
                        int mode = change.getMode();
                        DesktopModeTaskRepository desktopModeTaskRepository = this.desktopModeTaskRepository;
                        if (mode == 1) {
                            desktopModeTaskRepository.wallpaperActivityToken = taskInfo.token;
                            this.shellTaskOrganizer.applyTransaction(new WindowContainerTransaction().setTaskTrimmableFromRecents(taskInfo.token, false));
                        } else if (mode == 2) {
                            desktopModeTaskRepository.wallpaperActivityToken = null;
                        }
                    }
                }
            }
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionStarting(IBinder iBinder) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionFinished(IBinder iBinder, boolean z) {
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
    }
}
