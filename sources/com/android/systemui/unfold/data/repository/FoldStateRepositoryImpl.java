package com.android.systemui.unfold.data.repository;

import com.android.systemui.unfold.updates.DeviceFoldStateProvider;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldStateRepositoryImpl {
    public final DeviceFoldStateProvider foldStateProvider;

    public FoldStateRepositoryImpl(DeviceFoldStateProvider deviceFoldStateProvider) {
        this.foldStateProvider = deviceFoldStateProvider;
    }

    public final Flow getFoldUpdate() {
        return FlowKt.buffer$default(FlowKt.callbackFlow(new FoldStateRepositoryImpl$foldUpdate$1(this, null)), -1);
    }

    public final Flow getHingeAngle() {
        return FlowKt.buffer$default(FlowKt.callbackFlow(new FoldStateRepositoryImpl$hingeAngle$1(this, null)), -1);
    }
}
