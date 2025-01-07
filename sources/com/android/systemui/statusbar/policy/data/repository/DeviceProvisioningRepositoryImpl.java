package com.android.systemui.statusbar.policy.data.repository;

import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceProvisioningRepositoryImpl {
    public final DeviceProvisionedController deviceProvisionedController;
    public final Flow isDeviceProvisioned = FlowConflatedKt.conflatedCallbackFlow(new DeviceProvisioningRepositoryImpl$isDeviceProvisioned$1(this, null));

    public DeviceProvisioningRepositoryImpl(DeviceProvisionedController deviceProvisionedController) {
        this.deviceProvisionedController = deviceProvisionedController;
    }
}
