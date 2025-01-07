package com.android.wm.shell.splitscreen;

import com.android.systemui.model.SysUiState;
import com.android.systemui.wmshell.WMShell;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class StageCoordinator$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ StageCoordinator f$0;

    public /* synthetic */ StageCoordinator$$ExternalSyntheticLambda1(StageCoordinator stageCoordinator, int i) {
        this.$r8$classId = i;
        this.f$0 = stageCoordinator;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        StageCoordinator stageCoordinator = this.f$0;
        switch (i) {
            case 0:
                stageCoordinator.notifySplitAnimationFinished();
                break;
            case 1:
                WMShell wMShell = WMShell.this;
                SysUiState sysUiState = wMShell.mSysUiState;
                sysUiState.setFlag(4096L, false);
                wMShell.mDisplayTracker.getClass();
                sysUiState.commitUpdate(0);
                break;
            default:
                stageCoordinator.onTransitionAnimationComplete();
                break;
        }
    }
}
