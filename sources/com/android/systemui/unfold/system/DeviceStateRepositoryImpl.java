package com.android.systemui.unfold.system;

import com.android.systemui.unfold.updates.FoldProvider;
import java.util.concurrent.Executor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceStateRepositoryImpl {
    public final Executor executor;
    public final FoldProvider foldProvider;

    public DeviceStateRepositoryImpl(FoldProvider foldProvider, Executor executor) {
        this.foldProvider = foldProvider;
        this.executor = executor;
    }

    public final Flow isFolded() {
        return FlowKt.buffer$default(FlowKt.callbackFlow(new DeviceStateRepositoryImpl$isFolded$1(this, null)), -1);
    }
}
