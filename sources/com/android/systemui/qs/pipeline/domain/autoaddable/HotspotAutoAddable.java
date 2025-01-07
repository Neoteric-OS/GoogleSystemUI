package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.HotspotController;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HotspotAutoAddable extends CallbackControllerAutoAddable {
    public final String description;

    public HotspotAutoAddable(HotspotController hotspotController) {
        super(hotspotController);
        this.description = "HotspotAutoAddable (" + getAutoAddTracking() + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable
    public final Object getCallback(final ProducerScope producerScope) {
        return new HotspotController.Callback() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.HotspotAutoAddable$getCallback$1
            @Override // com.android.systemui.statusbar.policy.HotspotController.Callback
            public final void onHotspotChanged(int i, boolean z) {
                if (z) {
                    HotspotAutoAddable.this.sendAdd(producerScope);
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
        return TileSpec.Companion.create("hotspot");
    }
}
