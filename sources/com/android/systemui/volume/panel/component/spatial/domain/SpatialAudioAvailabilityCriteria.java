package com.android.systemui.volume.panel.component.spatial.domain;

import com.android.systemui.volume.panel.component.spatial.domain.interactor.SpatialAudioComponentInteractor;
import com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatialAudioAvailabilityCriteria implements ComponentAvailabilityCriteria {
    public final SpatialAudioComponentInteractor interactor;

    public SpatialAudioAvailabilityCriteria(SpatialAudioComponentInteractor spatialAudioComponentInteractor) {
        this.interactor = spatialAudioComponentInteractor;
    }

    @Override // com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria
    public final Flow isAvailable() {
        SpatialAudioComponentInteractor spatialAudioComponentInteractor = this.interactor;
        return new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(spatialAudioComponentInteractor.isAvailable, spatialAudioComponentInteractor.isEnabled, new SpatialAudioAvailabilityCriteria$isAvailable$1(3, null));
    }
}
