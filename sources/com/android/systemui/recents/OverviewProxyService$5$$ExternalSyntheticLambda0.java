package com.android.systemui.recents;

import com.android.systemui.model.SysUiState;
import com.android.systemui.recents.OverviewProxyService;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$5$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ Object f$0;
    public final /* synthetic */ boolean f$1;

    public /* synthetic */ OverviewProxyService$5$$ExternalSyntheticLambda0(OverviewProxyService.AnonymousClass1 anonymousClass1, boolean z, int i) {
        this.$r8$classId = i;
        this.f$0 = anonymousClass1;
        this.f$1 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OverviewProxyService.AnonymousClass5 anonymousClass5 = (OverviewProxyService.AnonymousClass5) this.f$0;
                boolean z = this.f$1;
                OverviewProxyService overviewProxyService = anonymousClass5.this$0;
                SysUiState sysUiState = overviewProxyService.mSysUiState;
                sysUiState.setFlag(33554432L, z);
                sysUiState.commitUpdate(overviewProxyService.mContext.getDisplayId());
                break;
            case 1:
                OverviewProxyService.AnonymousClass1 anonymousClass1 = (OverviewProxyService.AnonymousClass1) this.f$0;
                boolean z2 = this.f$1;
                OverviewProxyService overviewProxyService2 = anonymousClass1.this$0;
                for (int size = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size)).onTaskbarAutohideSuspend(z2);
                }
                break;
            case 2:
                OverviewProxyService.AnonymousClass1 anonymousClass12 = (OverviewProxyService.AnonymousClass1) this.f$0;
                anonymousClass12.this$0.mHandler.post(new OverviewProxyService$5$$ExternalSyntheticLambda0(anonymousClass12, this.f$1, 3));
                break;
            default:
                OverviewProxyService.AnonymousClass1 anonymousClass13 = (OverviewProxyService.AnonymousClass1) this.f$0;
                boolean z3 = this.f$1;
                OverviewProxyService overviewProxyService3 = anonymousClass13.this$0;
                for (int size2 = ((ArrayList) overviewProxyService3.mConnectionCallbacks).size() - 1; size2 >= 0; size2--) {
                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService3.mConnectionCallbacks).get(size2)).onHomeRotationEnabled(z3);
                }
                break;
        }
    }

    public /* synthetic */ OverviewProxyService$5$$ExternalSyntheticLambda0(OverviewProxyService.AnonymousClass5 anonymousClass5, boolean z) {
        this.$r8$classId = 0;
        this.f$0 = anonymousClass5;
        this.f$1 = z;
    }
}
