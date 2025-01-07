package com.android.systemui.keyguard.data.repository;

import com.android.systemui.statusbar.policy.DevicePostureController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DevicePostureRepositoryImpl {
    public final CoroutineDispatcher mainDispatcher;
    public final DevicePostureController postureController;

    public DevicePostureRepositoryImpl(DevicePostureController devicePostureController, CoroutineDispatcher coroutineDispatcher) {
        this.postureController = devicePostureController;
        this.mainDispatcher = coroutineDispatcher;
    }

    public final Flow getCurrentDevicePosture() {
        return FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new DevicePostureRepositoryImpl$currentDevicePosture$1(this, null)), this.mainDispatcher);
    }
}
