package com.android.wm.shell.freeform;

import android.app.ActivityManager;
import android.content.Context;
import android.os.IBinder;
import android.view.SurfaceControl;
import android.window.TransitionInfo;
import com.android.wm.shell.sysui.ShellInit;
import com.android.wm.shell.transition.Transitions;
import com.android.wm.shell.windowdecor.WindowDecorViewModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FreeformTaskTransitionObserver implements Transitions.TransitionObserver {
    public final Map mTransitionToTaskInfo = new HashMap();
    public final Transitions mTransitions;
    public final WindowDecorViewModel mWindowDecorViewModel;

    public FreeformTaskTransitionObserver(Context context, ShellInit shellInit, Transitions transitions, WindowDecorViewModel windowDecorViewModel) {
        this.mTransitions = transitions;
        this.mWindowDecorViewModel = windowDecorViewModel;
        if (Transitions.ENABLE_SHELL_TRANSITIONS && FreeformComponents.isFreeformEnabled(context)) {
            shellInit.addInitCallback(new Runnable() { // from class: com.android.wm.shell.freeform.FreeformTaskTransitionObserver$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    FreeformTaskTransitionObserver.this.onInit();
                }
            }, this);
        }
    }

    public void onInit() {
        this.mTransitions.registerObserver(this);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionFinished(IBinder iBinder, boolean z) {
        List list = (List) ((HashMap) this.mTransitionToTaskInfo).getOrDefault(iBinder, Collections.emptyList());
        this.mTransitionToTaskInfo.remove(iBinder);
        for (int i = 0; i < list.size(); i++) {
            this.mWindowDecorViewModel.destroyWindowDecoration((ActivityManager.RunningTaskInfo) list.get(i));
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionMerged(IBinder iBinder, IBinder iBinder2) {
        List list = (List) this.mTransitionToTaskInfo.get(iBinder);
        if (list == null) {
            return;
        }
        this.mTransitionToTaskInfo.remove(iBinder);
        List list2 = (List) this.mTransitionToTaskInfo.get(iBinder2);
        if (list2 != null) {
            list2.addAll(list);
        } else {
            this.mTransitionToTaskInfo.put(iBinder2, list);
        }
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionReady(IBinder iBinder, TransitionInfo transitionInfo, SurfaceControl.Transaction transaction, SurfaceControl.Transaction transaction2) {
        ActivityManager.RunningTaskInfo taskInfo;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (TransitionInfo.Change change : transitionInfo.getChanges()) {
            if ((change.getFlags() & 2) == 0 && (taskInfo = change.getTaskInfo()) != null && taskInfo.taskId != -1) {
                if (change.getParent() != null && transitionInfo.getChange(change.getParent()).getTaskInfo() != null) {
                    arrayList2.add(change.getParent());
                }
                if (!arrayList2.contains(change.getContainer())) {
                    int mode = change.getMode();
                    WindowDecorViewModel windowDecorViewModel = this.mWindowDecorViewModel;
                    if (mode == 1) {
                        windowDecorViewModel.onTaskOpening(change.getTaskInfo(), change.getLeash(), transaction, transaction2);
                    } else if (mode == 2) {
                        arrayList.add(change.getTaskInfo());
                        windowDecorViewModel.onTaskClosing(change.getTaskInfo(), transaction, transaction2);
                    } else if (mode == 3) {
                        windowDecorViewModel.onTaskChanging(change.getTaskInfo(), change.getLeash(), transaction, transaction2);
                    } else if (mode == 6) {
                        windowDecorViewModel.onTaskChanging(change.getTaskInfo(), change.getLeash(), transaction, transaction2);
                    }
                }
            }
        }
        this.mTransitionToTaskInfo.put(iBinder, arrayList);
    }

    @Override // com.android.wm.shell.transition.Transitions.TransitionObserver
    public final void onTransitionStarting(IBinder iBinder) {
    }
}
