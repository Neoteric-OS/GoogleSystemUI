package com.google.android.systemui.biometrics.domain;

import android.app.KeyguardManager;
import android.hardware.biometrics.BiometricManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.google.android.systemui.biometrics.repository.DeviceEntryUnlockTrackerRepositoryGoogle;
import java.util.Set;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.ReadonlySharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceEntryUnlockTrackerInteractorGoogle {
    public final CoroutineDispatcher backgroundDispatcher;
    public final BiometricManager biometricManager;
    public final ReadonlySharedFlow event;
    public final KeyguardManager keyguardManager;
    public final KeyguardUpdateMonitor keyguardUpdateManager;
    public final Set trackers;

    public DeviceEntryUnlockTrackerInteractorGoogle(CoroutineDispatcher coroutineDispatcher, KeyguardManager keyguardManager, KeyguardUpdateMonitor keyguardUpdateMonitor, BiometricManager biometricManager, DeviceEntryUnlockTrackerRepositoryGoogle deviceEntryUnlockTrackerRepositoryGoogle, Set set) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.keyguardManager = keyguardManager;
        this.keyguardUpdateManager = keyguardUpdateMonitor;
        this.biometricManager = biometricManager;
        this.trackers = set;
        this.event = deviceEntryUnlockTrackerRepositoryGoogle.deviceEntryUnlockEvent;
    }

    public final Object traceDeviceEntryUnlock(Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.backgroundDispatcher, new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlock$2(this, null), continuation);
        return withContext == CoroutineSingletons.COROUTINE_SUSPENDED ? withContext : Unit.INSTANCE;
    }
}
