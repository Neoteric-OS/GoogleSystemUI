package com.android.systemui.deviceentry.domain.interactor;

import com.android.keyguard.logging.BiometricUnlockLogger;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import com.android.systemui.keyevent.domain.interactor.KeyEventInteractor;
import com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.util.time.SystemClock;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryHapticsInteractor {
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 lastPowerButtonWakeup;
    public final BiometricUnlockLogger logger;
    public final DeviceEntryHapticsInteractor$special$$inlined$map$2 playErrorHaptic;
    public final DeviceEntryHapticsInteractor$special$$inlined$map$2 playErrorHapticForBiometricFailure;
    public final DeviceEntryHapticsInteractor$special$$inlined$map$2 playSuccessHaptic;
    public final long recentPowerButtonPressThresholdMs;
    public final SystemClock systemClock;

    public DeviceEntryHapticsInteractor(DeviceEntrySourceInteractor deviceEntrySourceInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, DeviceEntryBiometricAuthInteractor deviceEntryBiometricAuthInteractor, FingerprintPropertyRepository fingerprintPropertyRepository, BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, KeyEventInteractor keyEventInteractor, PowerInteractor powerInteractor, SystemClock systemClock, BiometricUnlockLogger biometricUnlockLogger) {
        this.systemClock = systemClock;
        this.logger = biometricUnlockLogger;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combineTransform(((FingerprintPropertyRepositoryImpl) fingerprintPropertyRepository).sensorType, biometricSettingsRepositoryImpl.isFingerprintEnrolledAndEnabled, new DeviceEntryHapticsInteractor$powerButtonSideFpsEnrolled$1(4, null)));
        Flow flow = keyEventInteractor.isPowerButtonDown;
        this.playSuccessHaptic = new DeviceEntryHapticsInteractor$special$$inlined$map$2(new DeviceEntryHapticsInteractor$special$$inlined$map$1(com.android.systemui.util.kotlin.FlowKt.sample(deviceEntrySourceInteractor.deviceEntryFromBiometricSource, FlowKt.combine(distinctUntilChanged, flow, new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryHapticsInteractor$lastPowerButtonWakeup$3(this, null), new DeviceEntryHapticsInteractor$special$$inlined$map$1(new DeviceEntryHapticsInteractor$special$$inlined$map$2(powerInteractor.detailedWakefulness, 1), this, 0)), DeviceEntryHapticsInteractor$playSuccessHaptic$2.INSTANCE)), this, 1), 0);
        this.playErrorHaptic = new DeviceEntryHapticsInteractor$special$$inlined$map$2(new DeviceEntryHapticsInteractor$special$$inlined$map$1(com.android.systemui.util.kotlin.FlowKt.sample(new DeviceEntryHapticsInteractor$special$$inlined$map$2(FlowKt.merge(deviceEntryFingerprintAuthInteractor.fingerprintFailure, deviceEntryBiometricAuthInteractor.faceOnlyFaceFailure), 2), new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(distinctUntilChanged, flow, DeviceEntryHapticsInteractor$playErrorHaptic$2.INSTANCE)), this, 2), 3);
        this.recentPowerButtonPressThresholdMs = 400L;
    }
}
