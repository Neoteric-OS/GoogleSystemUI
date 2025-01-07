package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepository;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepositoryImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryFingerprintAuthInteractor {
    public final Flow authenticationStatus;
    public final DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1 fingerprintError;
    public final DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1 fingerprintFailure;
    public final DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1 fingerprintHelp;
    public final DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1 fingerprintSuccess;
    public final ReadonlyStateFlow isEngaged;
    public final StateFlow isLockedOut;
    public final Flow isRunning;
    public final DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1 isSensorUnderDisplay;

    public DeviceEntryFingerprintAuthInteractor(DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository, FingerprintPropertyRepository fingerprintPropertyRepository) {
        DeviceEntryFingerprintAuthRepositoryImpl deviceEntryFingerprintAuthRepositoryImpl = (DeviceEntryFingerprintAuthRepositoryImpl) deviceEntryFingerprintAuthRepository;
        this.isRunning = deviceEntryFingerprintAuthRepositoryImpl.isRunning();
        this.isEngaged = deviceEntryFingerprintAuthRepositoryImpl.isEngaged;
        this.authenticationStatus = deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus();
        this.isLockedOut = (StateFlow) deviceEntryFingerprintAuthRepositoryImpl.isLockedOut$delegate.getValue();
        this.fingerprintFailure = new DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1(deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus(), 1);
        this.fingerprintError = new DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1(deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus(), 2);
        this.fingerprintHelp = new DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1(deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus(), 3);
        this.fingerprintSuccess = new DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1(deviceEntryFingerprintAuthRepositoryImpl.getAuthenticationStatus(), 4);
        this.isSensorUnderDisplay = new DeviceEntryFingerprintAuthInteractor$special$$inlined$map$1(((FingerprintPropertyRepositoryImpl) fingerprintPropertyRepository).sensorType, 0);
    }
}
