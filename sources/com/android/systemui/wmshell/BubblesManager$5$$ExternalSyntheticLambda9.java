package com.android.systemui.wmshell;

import com.android.systemui.model.SysUiState;
import com.android.systemui.wmshell.BubblesManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class BubblesManager$5$$ExternalSyntheticLambda9 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ BubblesManager.AnonymousClass5 f$0;
    public final /* synthetic */ SysUiState f$1;
    public final /* synthetic */ boolean f$2;

    public /* synthetic */ BubblesManager$5$$ExternalSyntheticLambda9(BubblesManager.AnonymousClass5 anonymousClass5, SysUiState sysUiState, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass5;
        this.f$1 = sysUiState;
        this.f$2 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                BubblesManager.AnonymousClass5 anonymousClass5 = this.f$0;
                SysUiState sysUiState = this.f$1;
                boolean z = this.f$2;
                anonymousClass5.getClass();
                sysUiState.setFlag(8388608L, z);
                sysUiState.commitUpdate(BubblesManager.this.mContext.getDisplayId());
                break;
            default:
                BubblesManager.AnonymousClass5 anonymousClass52 = this.f$0;
                SysUiState sysUiState2 = this.f$1;
                boolean z2 = this.f$2;
                anonymousClass52.getClass();
                sysUiState2.setFlag(16384L, z2);
                BubblesManager bubblesManager = BubblesManager.this;
                sysUiState2.commitUpdate(bubblesManager.mContext.getDisplayId());
                if (!z2) {
                    sysUiState2.setFlag(8388608L, false);
                    sysUiState2.commitUpdate(bubblesManager.mContext.getDisplayId());
                    break;
                }
                break;
        }
    }
}
