package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.model.AutoAddSignal;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.CallbackController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CallbackControllerAutoAddable implements AutoAddable {
    public final CallbackController controller;

    public CallbackControllerAutoAddable(CallbackController callbackController) {
        this.controller = callbackController;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(int i) {
        return FlowConflatedKt.conflatedCallbackFlow(new CallbackControllerAutoAddable$autoAddSignal$1(this, null));
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public AutoAddTracking getAutoAddTracking() {
        return new AutoAddTracking.IfNotAdded(getSpec());
    }

    public abstract Object getCallback(ProducerScope producerScope);

    public abstract TileSpec getSpec();

    public final void sendAdd(ProducerScope producerScope) {
        ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(new AutoAddSignal.Add(-1, getSpec()));
    }
}
