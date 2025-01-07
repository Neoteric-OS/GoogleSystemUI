package com.google.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.KeyguardInteractor;
import com.google.android.systemui.keyguard.data.repository.AmbientIndicationRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AmbientIndicationInteractor {
    public final AmbientIndicationRepository ambientIndicationRepository;
    public final ReadonlyStateFlow ambientMusicState;
    public final KeyguardInteractor keyguardInteractor;
    public final ReadonlyStateFlow reverseChargingMessage;
    public final ReadonlyStateFlow wirelessChargingMessage;

    public AmbientIndicationInteractor(AmbientIndicationRepository ambientIndicationRepository, KeyguardInteractor keyguardInteractor) {
        this.ambientIndicationRepository = ambientIndicationRepository;
        this.keyguardInteractor = keyguardInteractor;
        this.ambientMusicState = new ReadonlyStateFlow(ambientIndicationRepository.ambientMusic);
        this.reverseChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository.reverseChargingMessage);
        this.wirelessChargingMessage = new ReadonlyStateFlow(ambientIndicationRepository.wirelessChargingMessage);
    }

    public final void hideAmbientMusic() {
        this.ambientIndicationRepository.ambientMusic.setValue(null);
        StateFlowImpl stateFlowImpl = this.keyguardInteractor.repository.ambientIndicationVisible;
        Boolean bool = Boolean.FALSE;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bool);
    }
}
