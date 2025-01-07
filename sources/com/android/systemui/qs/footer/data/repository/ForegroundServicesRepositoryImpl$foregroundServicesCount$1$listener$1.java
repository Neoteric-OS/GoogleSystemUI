package com.android.systemui.qs.footer.data.repository;

import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ForegroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1 {
    public final /* synthetic */ ProducerScope $$this$conflatedCallbackFlow;

    public ForegroundServicesRepositoryImpl$foregroundServicesCount$1$listener$1(ProducerScope producerScope) {
        this.$$this$conflatedCallbackFlow = producerScope;
    }

    public final void onNumberOfPackagesChanged(int i) {
        ForegroundServicesRepositoryImpl$foregroundServicesCount$1.invokeSuspend$updateState(i, this.$$this$conflatedCallbackFlow);
    }
}
