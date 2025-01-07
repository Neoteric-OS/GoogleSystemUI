package com.android.systemui.qs.pipeline.domain.autoaddable;

import com.android.systemui.qs.ReduceBrightColorsController;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ReduceBrightColorsAutoAddable extends CallbackControllerAutoAddable {
    public final boolean available;
    public final String description;

    public ReduceBrightColorsAutoAddable(ReduceBrightColorsController reduceBrightColorsController, boolean z) {
        super(reduceBrightColorsController);
        this.available = z;
        this.description = "ReduceBrightColorsAutoAddable (" + AutoAddTracking.Disabled.INSTANCE + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable, com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return AutoAddTracking.Disabled.INSTANCE;
    }

    @Override // com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable
    public final Object getCallback(final ProducerScope producerScope) {
        return new ReduceBrightColorsController.Listener() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.ReduceBrightColorsAutoAddable$getCallback$1
            @Override // com.android.systemui.qs.ReduceBrightColorsController.Listener
            public final void onActivated(boolean z) {
                if (z) {
                    ReduceBrightColorsAutoAddable reduceBrightColorsAutoAddable = ReduceBrightColorsAutoAddable.this;
                    if (reduceBrightColorsAutoAddable.available) {
                        reduceBrightColorsAutoAddable.sendAdd(producerScope);
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
        return TileSpec.Companion.create("reduce_brightness");
    }
}
