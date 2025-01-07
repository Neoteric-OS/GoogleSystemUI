package com.android.wm.shell.desktopmode;

import android.os.IBinder;
import android.window.RemoteTransition;
import android.window.WindowContainerTransaction;
import com.android.wm.shell.shared.desktopmode.DesktopModeTransitionSource;
import com.android.wm.shell.transition.OneShotRemoteHandler;
import com.android.wm.shell.transition.Transitions;
import java.util.function.Consumer;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopTasksController$IDesktopModeImpl$moveToDesktop$1 implements Consumer {
    public final /* synthetic */ int $r8$classId = 2;
    public final /* synthetic */ int $taskId;
    public final /* synthetic */ Object $transitionSource;

    public DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(int i, RemoteTransition remoteTransition) {
        this.$taskId = i;
        this.$transitionSource = remoteTransition;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        int i = 0;
        switch (this.$r8$classId) {
            case 0:
                DesktopTasksController desktopTasksController = (DesktopTasksController) obj;
                Intrinsics.checkNotNull(desktopTasksController);
                int i2 = this.$taskId;
                DesktopModeTransitionSource desktopModeTransitionSource = (DesktopModeTransitionSource) this.$transitionSource;
                float f = DesktopTasksController.DESKTOP_MODE_INITIAL_BOUNDS_SCALE;
                desktopTasksController.moveTaskToDesktop(i2, new WindowContainerTransaction(), desktopModeTransitionSource);
                break;
            case 1:
                ((int[]) this.$transitionSource)[0] = ((DesktopTasksController) obj).taskRepository.getVisibleTaskCount(this.$taskId);
                break;
            default:
                DesktopTasksController desktopTasksController2 = (DesktopTasksController) obj;
                int i3 = this.$taskId;
                RemoteTransition remoteTransition = (RemoteTransition) this.$transitionSource;
                desktopTasksController2.getClass();
                DesktopTasksController.logV("showDesktopApps", new Object[0]);
                WindowContainerTransaction windowContainerTransaction = new WindowContainerTransaction();
                desktopTasksController2.bringDesktopAppsToFront(i3, windowContainerTransaction, null);
                if (!Transitions.ENABLE_SHELL_TRANSITIONS) {
                    desktopTasksController2.shellTaskOrganizer.applyTransaction(windowContainerTransaction);
                    break;
                } else {
                    if (remoteTransition == null) {
                        DesktopTasksController.logV("RemoteTransition is null", new Object[0]);
                    } else {
                        i = 3;
                    }
                    Transitions transitions = desktopTasksController2.transitions;
                    OneShotRemoteHandler oneShotRemoteHandler = remoteTransition != null ? new OneShotRemoteHandler(transitions.mMainExecutor, remoteTransition) : null;
                    IBinder startTransition = transitions.startTransition(i, windowContainerTransaction, oneShotRemoteHandler);
                    if (oneShotRemoteHandler != null) {
                        oneShotRemoteHandler.mTransition = startTransition;
                        break;
                    }
                }
                break;
        }
    }

    public DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(int i, DesktopModeTransitionSource desktopModeTransitionSource) {
        this.$taskId = i;
        this.$transitionSource = desktopModeTransitionSource;
    }

    public DesktopTasksController$IDesktopModeImpl$moveToDesktop$1(int[] iArr, int i) {
        this.$transitionSource = iArr;
        this.$taskId = i;
    }
}
