package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryBiometricSettingsInteractor {
    public final ChannelFlowTransformLatest authenticationFlags;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 faceAuthCurrentlyAllowed;
    public final StateFlow fingerprintAuthCurrentlyAllowed;
    public final StateFlow isFaceAuthEnrolledAndEnabled;
    public final StateFlow isFingerprintAuthEnrolledAndEnabled;

    public DeviceEntryBiometricSettingsInteractor(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl) {
        this.authenticationFlags = biometricSettingsRepositoryImpl.authenticationFlags;
        this.isFingerprintAuthEnrolledAndEnabled = biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled;
        this.fingerprintAuthCurrentlyAllowed = biometricSettingsRepositoryImpl.isFingerprintAuthCurrentlyAllowed;
        this.isFaceAuthEnrolledAndEnabled = biometricSettingsRepositoryImpl.isFaceAuthEnrolledAndEnabled;
        this.faceAuthCurrentlyAllowed = biometricSettingsRepositoryImpl.isFaceAuthCurrentlyAllowed;
        new DeviceEntryBiometricSettingsInteractor$fingerprintAndFaceEnrolledAndEnabled$1(3, null);
    }
}
