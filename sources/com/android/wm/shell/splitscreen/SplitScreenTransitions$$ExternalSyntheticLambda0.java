package com.android.wm.shell.splitscreen;

import android.animation.Animator;
import com.android.systemui.model.SysUiState;
import com.android.systemui.wmshell.WMShell;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class SplitScreenTransitions$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ SplitScreenTransitions$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                ((SplitScreenTransitions) obj).onFinish(null);
                break;
            case 1:
                ((SplitScreenTransitions) obj).onFinish(null);
                break;
            case 2:
                WMShell wMShell = WMShell.this;
                SysUiState sysUiState = wMShell.mSysUiState;
                sysUiState.setFlag(4096L, true);
                wMShell.mDisplayTracker.getClass();
                sysUiState.commitUpdate(0);
                break;
            default:
                ((Animator) obj).end();
                break;
        }
    }
}
