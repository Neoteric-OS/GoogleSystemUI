package com.android.systemui.recents;

import android.os.Bundle;
import com.android.systemui.recents.OverviewProxyService;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OverviewProxyService$1$$ExternalSyntheticLambda16 implements Runnable {
    public final /* synthetic */ int $r8$classId = 1;
    public final /* synthetic */ OverviewProxyService.AnonymousClass1 f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda16(OverviewProxyService.AnonymousClass1 anonymousClass1, Bundle bundle) {
        this.f$0 = anonymousClass1;
        this.f$1 = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.$r8$classId) {
            case 0:
                OverviewProxyService.AnonymousClass1 anonymousClass1 = this.f$0;
                int[] iArr = (int[]) this.f$1;
                OverviewProxyService overviewProxyService = anonymousClass1.this$0;
                for (int size = ((ArrayList) overviewProxyService.mConnectionCallbacks).size() - 1; size >= 0; size--) {
                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService.mConnectionCallbacks).get(size)).setAssistantOverridesRequested(iArr);
                }
                break;
            default:
                OverviewProxyService.AnonymousClass1 anonymousClass12 = this.f$0;
                Bundle bundle = (Bundle) this.f$1;
                OverviewProxyService overviewProxyService2 = anonymousClass12.this$0;
                for (int size2 = ((ArrayList) overviewProxyService2.mConnectionCallbacks).size() - 1; size2 >= 0; size2--) {
                    ((OverviewProxyService.OverviewProxyListener) ((ArrayList) overviewProxyService2.mConnectionCallbacks).get(size2)).startAssistant(bundle);
                }
                break;
        }
    }

    public /* synthetic */ OverviewProxyService$1$$ExternalSyntheticLambda16(OverviewProxyService.AnonymousClass1 anonymousClass1, int[] iArr) {
        this.f$0 = anonymousClass1;
        this.f$1 = iArr;
    }
}
