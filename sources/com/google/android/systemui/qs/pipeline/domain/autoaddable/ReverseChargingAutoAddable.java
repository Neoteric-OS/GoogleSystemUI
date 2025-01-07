package com.google.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.BatteryController;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReverseChargingAutoAddable extends CallbackControllerAutoAddable {
    public final String description;
    public final TileSpec spec;

    public ReverseChargingAutoAddable(BatteryController batteryController) {
        super(batteryController);
        this.spec = TileSpec.Companion.create("reverse");
        this.description = "ReverseChargingAutoAddable (" + getAutoAddTracking() + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable
    public final Object getCallback(final ProducerScope producerScope) {
        return new BatteryController.BatteryStateChangeCallback() { // from class: com.google.android.systemui.qs.pipeline.domain.autoaddable.ReverseChargingAutoAddable$getCallback$1
            @Override // com.android.systemui.statusbar.policy.BatteryController.BatteryStateChangeCallback
            public final void onReverseChanged(int i, String str, boolean z) {
                if (z) {
                    ReverseChargingAutoAddable.this.sendAdd(producerScope);
                }
            }
        };
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable
    public final TileSpec getSpec() {
        return this.spec;
    }
}
