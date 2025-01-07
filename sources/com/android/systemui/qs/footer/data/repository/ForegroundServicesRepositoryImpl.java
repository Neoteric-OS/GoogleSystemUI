package com.android.systemui.qs.footer.data.repository;

import com.android.systemui.qs.FgsManagerController;
import com.android.systemui.qs.FgsManagerControllerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ForegroundServicesRepositoryImpl {
    public final Flow foregroundServicesCount;
    public final ChannelFlowTransformLatest hasNewChanges;

    public ForegroundServicesRepositoryImpl(FgsManagerController fgsManagerController) {
        this.foregroundServicesCount = FlowKt.distinctUntilChanged(FlowConflatedKt.conflatedCallbackFlow(new ForegroundServicesRepositoryImpl$foregroundServicesCount$1(fgsManagerController, null)));
        this.hasNewChanges = FlowKt.transformLatest(((FgsManagerControllerImpl) fgsManagerController).showFooterDot, new ForegroundServicesRepositoryImpl$special$$inlined$flatMapLatest$1(null, this, fgsManagerController));
    }
}
