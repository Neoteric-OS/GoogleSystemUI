package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryBiometricAuthInteractor {
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 biometricMode;
    public final DeviceEntryBiometricAuthInteractor$special$$inlined$map$1 faceOnly;
    public final ChannelFlowTransformLatest faceOnlyFaceFailure;

    public DeviceEntryBiometricAuthInteractor(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor) {
        this.faceOnlyFaceFailure = FlowKt.transformLatest(new DeviceEntryBiometricAuthInteractor$special$$inlined$map$1(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled, new DeviceEntryBiometricAuthInteractor$biometricMode$1(3, null)), 0), new DeviceEntryBiometricAuthInteractor$special$$inlined$flatMapLatest$1(null, deviceEntryFaceAuthInteractor));
    }
}
