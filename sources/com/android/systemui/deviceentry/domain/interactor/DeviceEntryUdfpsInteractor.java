package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryUdfpsInteractor {
    public final ChannelFlowTransformLatest isListeningForUdfps;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isUdfpsEnrolledAndEnabled;
    public final ReadonlyStateFlow isUdfpsSupported;
    public final ChannelFlowTransformLatest udfpsLocation;

    public DeviceEntryUdfpsInteractor(FingerprintPropertyInteractor fingerprintPropertyInteractor, DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl) {
        ReadonlyStateFlow readonlyStateFlow = fingerprintPropertyInteractor.isUdfps;
        this.isUdfpsSupported = readonlyStateFlow;
        this.isUdfpsEnrolledAndEnabled = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(readonlyStateFlow, biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new DeviceEntryUdfpsInteractor$isUdfpsEnrolledAndEnabled$1(3, null));
        this.isListeningForUdfps = FlowKt.transformLatest(readonlyStateFlow, new DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1(null, deviceEntryFingerprintAuthRepository));
        this.udfpsLocation = FlowKt.transformLatest(readonlyStateFlow, new DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$2(null, fingerprintPropertyInteractor));
    }
}
