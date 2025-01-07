package com.android.systemui.bouncer.data.repository;

import android.hardware.biometrics.BiometricSourceType;
import com.android.systemui.bouncer.shared.model.BouncerMessageModel;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageRepositoryImpl {
    public final StateFlowImpl _bouncerMessage;
    public final StateFlowImpl bouncerMessage;
    public BiometricSourceType messageSource;

    public BouncerMessageRepositoryImpl() {
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(new BouncerMessageModel(null, null));
        this._bouncerMessage = MutableStateFlow;
        this.bouncerMessage = MutableStateFlow;
    }

    public final void setMessage(BouncerMessageModel bouncerMessageModel, BiometricSourceType biometricSourceType) {
        StateFlowImpl stateFlowImpl = this._bouncerMessage;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, bouncerMessageModel);
        this.messageSource = biometricSourceType;
    }
}
