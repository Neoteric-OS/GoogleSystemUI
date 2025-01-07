package com.android.systemui.qs.pipeline.domain.autoaddable;

import android.content.Context;
import android.hardware.display.ColorDisplayManager;
import com.android.systemui.dagger.NightDisplayListenerModule$Builder;
import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import com.android.systemui.qs.pipeline.shared.TileSpec;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NightDisplayAutoAddable implements AutoAddable {
    public final AutoAddTracking autoAddTracking;
    public final String description;
    public final NightDisplayListenerModule$Builder nightDisplayListenerBuilder;
    public final TileSpec spec;

    public NightDisplayAutoAddable(NightDisplayListenerModule$Builder nightDisplayListenerModule$Builder, Context context) {
        this.nightDisplayListenerBuilder = nightDisplayListenerModule$Builder;
        boolean isNightDisplayAvailable = ColorDisplayManager.isNightDisplayAvailable(context);
        TileSpec create = TileSpec.Companion.create("night");
        this.spec = create;
        AutoAddTracking ifNotAdded = isNightDisplayAvailable ? new AutoAddTracking.IfNotAdded(create) : AutoAddTracking.Disabled.INSTANCE;
        this.autoAddTracking = ifNotAdded;
        this.description = "NightDisplayAutoAddable (" + ifNotAdded + ")";
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final Flow autoAddSignal(int i) {
        return FlowConflatedKt.conflatedCallbackFlow(new NightDisplayAutoAddable$autoAddSignal$1(this, i, null));
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final AutoAddTracking getAutoAddTracking() {
        return this.autoAddTracking;
    }

    @Override // com.android.systemui.qs.pipeline.domain.model.AutoAddable
    public final String getDescription() {
        return this.description;
    }
}
