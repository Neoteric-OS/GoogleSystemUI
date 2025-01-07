package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.DeviceControlsControllerImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceControlsAutoAddable implements AutoAddable {
    public final DeviceControlsControllerImpl deviceControlsController;
    public final TileSpec spec = TileSpec.Companion.create("controls");
    public final String description = "DeviceControlsAutoAddable (" + AutoAddTracking.Always.INSTANCE + ")";

    public DeviceControlsAutoAddable(DeviceControlsControllerImpl deviceControlsControllerImpl) {
        this.deviceControlsController = deviceControlsControllerImpl;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(int i) {
        return FlowConflatedKt.conflatedCallbackFlow(new DeviceControlsAutoAddable$autoAddSignal$1(this, null));
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return AutoAddTracking.Always.INSTANCE;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }
}
