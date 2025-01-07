package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.statusbar.policy.CastController$Callback;
import com.android.systemui.statusbar.policy.CastControllerImpl;
import com.android.systemui.statusbar.policy.CastDevice;
import java.util.Iterator;
import java.util.List;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CastAutoAddable extends CallbackControllerAutoAddable {
    public final CastControllerImpl controller;
    public final String description;

    public CastAutoAddable(CastControllerImpl castControllerImpl) {
        super(castControllerImpl);
        this.controller = castControllerImpl;
        this.description = "CastAutoAddable (" + getAutoAddTracking() + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable
    public final Object getCallback(final ProducerScope producerScope) {
        return new CastController$Callback() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.CastAutoAddable$getCallback$1
            @Override // com.android.systemui.statusbar.policy.CastController$Callback
            public final void onCastDevicesChanged() {
                CastAutoAddable castAutoAddable = CastAutoAddable.this;
                List castDevices = castAutoAddable.controller.getCastDevices();
                if (castDevices.isEmpty()) {
                    return;
                }
                Iterator it = castDevices.iterator();
                while (it.hasNext()) {
                    if (((CastDevice) it.next()).isCasting) {
                        castAutoAddable.sendAdd(producerScope);
                        return;
                    }
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
        return TileSpec.Companion.create("cast");
    }
}
