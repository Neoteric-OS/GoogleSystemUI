package com.google.android.systemui.volume.panel.component.spatial.domain;

import com.android.internal.logging.UiEventLogger;
import com.android.settingslib.volume.domain.interactor.AudioModeInteractor;
import com.android.systemui.volume.domain.interactor.AudioOutputInteractor;
import com.android.systemui.volume.panel.component.spatial.domain.SpatialAudioAvailabilityCriteria;
import com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria;
import com.google.android.systemui.volume.panel.component.shared.availabilitycriteria.GoogleComponentAvailabilityCriteria;
import com.google.android.systemui.volume.panel.domain.interactor.PixelDeviceInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SpatialAudioAvailabilityGoogleCriteria implements ComponentAvailabilityCriteria {
    public final ReadonlySharedFlow availability;

    public SpatialAudioAvailabilityGoogleCriteria(SpatialAudioAvailabilityCriteria spatialAudioAvailabilityCriteria, GoogleComponentAvailabilityCriteria googleComponentAvailabilityCriteria, PixelDeviceInteractor pixelDeviceInteractor, AudioOutputInteractor audioOutputInteractor, AudioModeInteractor audioModeInteractor, ContextScope contextScope, UiEventLogger uiEventLogger) {
        this.availability = FlowKt.shareIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(FlowKt.combine(spatialAudioAvailabilityCriteria.isAvailable(), googleComponentAvailabilityCriteria.isAvailable(), pixelDeviceInteractor.activePixelBluetoothMediaDevice, audioOutputInteractor.currentAudioDevice, audioModeInteractor.isOngoingCall, new SpatialAudioAvailabilityGoogleCriteria$availability$1(null))), new SpatialAudioAvailabilityGoogleCriteria$availability$2(uiEventLogger, null), 0), contextScope, SharingStarted.Companion.WhileSubscribed$default(3), 1);
    }

    @Override // com.android.systemui.volume.panel.domain.ComponentAvailabilityCriteria
    public final Flow isAvailable() {
        return this.availability;
    }
}
