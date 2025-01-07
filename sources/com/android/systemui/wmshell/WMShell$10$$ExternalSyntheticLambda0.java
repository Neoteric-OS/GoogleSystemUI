package com.android.systemui.wmshell;

import android.view.KeyEvent;
import com.android.systemui.model.SysUiState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class WMShell$10$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;

    public /* synthetic */ WMShell$10$$ExternalSyntheticLambda0(int i, Object obj) {
        this.$r8$classId = i;
        this.f$0 = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.$r8$classId;
        Object obj = this.f$0;
        switch (i) {
            case 0:
                WMShell wMShell = WMShell.this;
                SysUiState sysUiState = wMShell.mSysUiState;
                sysUiState.setFlag(65536L, true);
                wMShell.mDisplayTracker.getClass();
                sysUiState.commitUpdate(0);
                break;
            case 1:
                WMShell wMShell2 = WMShell.this;
                SysUiState sysUiState2 = wMShell2.mSysUiState;
                sysUiState2.setFlag(65536L, false);
                wMShell2.mDisplayTracker.getClass();
                sysUiState2.commitUpdate(0);
                break;
            case 2:
                WMShell wMShell3 = WMShell.this;
                SysUiState sysUiState3 = wMShell3.mSysUiState;
                sysUiState3.setFlag(65536L, true);
                wMShell3.mDisplayTracker.getClass();
                sysUiState3.commitUpdate(0);
                break;
            default:
                WMShell.this.mCommandQueue.handleSystemKey(new KeyEvent(0, 281));
                break;
        }
    }
}
