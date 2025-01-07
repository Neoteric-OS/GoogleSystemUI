package com.android.systemui.bouncer.log;

import android.os.Build;
import com.android.systemui.CoreStartable;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryBiometricSettingsInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryBiometricsAllowedInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFaceAuthInteractor;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryFingerprintAuthInteractor;
import com.android.systemui.log.BouncerLogger;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerLoggerStartable implements CoreStartable {
    public final CoroutineScope applicationScope;
    public final DeviceEntryBiometricSettingsInteractor biometricSettingsInteractor;
    public final BouncerLogger bouncerLogger;
    public final DeviceEntryBiometricsAllowedInteractor deviceEntryBiometricsAllowedInteractor;
    public final DeviceEntryFaceAuthInteractor faceAuthInteractor;
    public final DeviceEntryFingerprintAuthInteractor fingerprintAuthInteractor;

    public BouncerLoggerStartable(CoroutineScope coroutineScope, DeviceEntryBiometricSettingsInteractor deviceEntryBiometricSettingsInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, BouncerLogger bouncerLogger, DeviceEntryBiometricsAllowedInteractor deviceEntryBiometricsAllowedInteractor) {
        this.applicationScope = coroutineScope;
        this.biometricSettingsInteractor = deviceEntryBiometricSettingsInteractor;
        this.faceAuthInteractor = deviceEntryFaceAuthInteractor;
        this.fingerprintAuthInteractor = deviceEntryFingerprintAuthInteractor;
        this.bouncerLogger = bouncerLogger;
        this.deviceEntryBiometricsAllowedInteractor = deviceEntryBiometricsAllowedInteractor;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        if (Build.isDebuggable()) {
            BouncerLoggerStartable$start$1 bouncerLoggerStartable$start$1 = new BouncerLoggerStartable$start$1(this, null);
            CoroutineScope coroutineScope = this.applicationScope;
            BuildersKt.launch$default(coroutineScope, null, null, bouncerLoggerStartable$start$1, 3);
            BuildersKt.launch$default(coroutineScope, null, null, new BouncerLoggerStartable$start$2(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new BouncerLoggerStartable$start$3(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new BouncerLoggerStartable$start$4(this, null), 3);
            BuildersKt.launch$default(coroutineScope, null, null, new BouncerLoggerStartable$start$5(this, null), 3);
        }
    }
}
