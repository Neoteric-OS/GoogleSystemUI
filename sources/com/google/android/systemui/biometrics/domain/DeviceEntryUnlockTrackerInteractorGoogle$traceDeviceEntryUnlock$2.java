package com.google.android.systemui.biometrics.domain;

import android.frameworks.stats.IStats;
import android.frameworks.stats.VendorAtom;
import android.frameworks.stats.VendorAtomValue;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricSourceType;
import android.hardware.google.pixel.vendor.PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType;
import android.provider.Settings;
import android.util.Log;
import com.android.internal.util.LatencyTracker;
import com.android.systemui.biometrics.data.repository.FingerprintPropertyRepositoryImpl;
import com.android.systemui.biometrics.shared.model.FingerprintSensorType;
import com.android.systemui.statusbar.StatusBarStateControllerImpl;
import com.google.android.systemui.biometrics.DeviceEntryUnlockEvent;
import com.google.android.systemui.biometrics.DeviceEntryUnlockStage;
import com.google.android.systemui.biometrics.domain.BiometricUnlockTrackerImpl;
import com.google.android.systemui.biometrics.domain.DeviceEntryUnlockMetricsLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlock$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ DeviceEntryUnlockTrackerInteractorGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlock$2(DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceEntryUnlockTrackerInteractorGoogle;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlock$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlock$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle = this.this$0;
            boolean isDeviceSecure = deviceEntryUnlockTrackerInteractorGoogle.keyguardManager.isDeviceSecure();
            boolean z = false;
            boolean isUnlockingWithBiometricAllowed = deviceEntryUnlockTrackerInteractorGoogle.keyguardUpdateManager.isUnlockingWithBiometricAllowed(false);
            BiometricManager biometricManager = deviceEntryUnlockTrackerInteractorGoogle.biometricManager;
            if (biometricManager != null && biometricManager.canAuthenticate(255) == 0) {
                z = true;
            }
            if (isDeviceSecure && isUnlockingWithBiometricAllowed && z) {
                final DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle2 = this.this$0;
                this.label = 1;
                deviceEntryUnlockTrackerInteractorGoogle2.getClass();
                Object collect = new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$2(2, null), deviceEntryUnlockTrackerInteractorGoogle2.event), new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3(deviceEntryUnlockTrackerInteractorGoogle2, null)).collect(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2(new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$4(2, null), new FlowCollector() { // from class: com.google.android.systemui.biometrics.domain.DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$5
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        LatencyTrackerWrapper latencyTrackerWrapper;
                        LatencyTracker latencyTracker;
                        DeviceEntryUnlockStage deviceEntryUnlockStage;
                        DeviceEntryUnlockStage deviceEntryUnlockStage2;
                        LatencyTrackerWrapper latencyTrackerWrapper2;
                        LatencyTracker latencyTracker2;
                        DeviceEntryUnlockStage deviceEntryUnlockStage3;
                        DeviceEntryUnlockStage deviceEntryUnlockStage4;
                        DeviceEntryUnlockEvent deviceEntryUnlockEvent = (DeviceEntryUnlockEvent) obj2;
                        int ordinal = deviceEntryUnlockEvent.stage.ordinal();
                        DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle3 = DeviceEntryUnlockTrackerInteractorGoogle.this;
                        if (ordinal == 0) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Error error = (DeviceEntryUnlockEvent.Error) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl.metricsLoggerSession.valid) {
                                    BiometricSourceType biometricSourceType = error.type;
                                    BiometricSourceType biometricSourceType2 = biometricUnlockTrackerImpl.type;
                                    if (biometricSourceType == biometricSourceType2) {
                                        int i2 = BiometricUnlockTrackerImpl.WhenMappings.$EnumSwitchMapping$0[biometricSourceType2.ordinal()];
                                        if (error.authInfo.getErrCode() == ((i2 == 1 || i2 == 2) ? 5 : -1)) {
                                            if (biometricUnlockTrackerImpl.stateMachine.tryTransitTo(DeviceEntryUnlockStage.CANCELED)) {
                                                biometricUnlockTrackerImpl.traceStateLogger.log(error.stage.name());
                                                LatencyTrackerWrapper latencyTrackerWrapper3 = biometricUnlockTrackerImpl.latencyTrackerWrapper;
                                                LatencyTracker latencyTracker3 = latencyTrackerWrapper3.tracker;
                                                if (latencyTracker3 != null) {
                                                    latencyTracker3.onActionCancel(latencyTrackerWrapper3.cuj);
                                                }
                                                biometricUnlockTrackerImpl.lastAcquiredTimeStamp = 0L;
                                                biometricUnlockTrackerImpl.lastExitKeyguardTimeStamp = 0L;
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (ordinal == 1) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl2 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Acquired acquired = (DeviceEntryUnlockEvent.Acquired) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl2.metricsLoggerSession.valid && acquired.type == biometricUnlockTrackerImpl2.type) {
                                    DeviceEntryUnlockStage deviceEntryUnlockStage5 = DeviceEntryUnlockStage.HAL_ACQUISITION;
                                    DeviceEntryUnlockStateMachine deviceEntryUnlockStateMachine = biometricUnlockTrackerImpl2.stateMachine;
                                    if (deviceEntryUnlockStateMachine.tryTransitTo(deviceEntryUnlockStage5)) {
                                        DeviceEntryUnlockMetricsLogger.Session session = biometricUnlockTrackerImpl2.metricsLoggerSession;
                                        if (session.valid && !(session.fpsSensorType == PixelAtoms$BiometricDeviceEntryUnlockLatencyReported$FpsSensorType.POWER_BUTTON && ((StatusBarStateControllerImpl) biometricUnlockTrackerImpl2.statusBarStateController).mIsDozing && !session.touchAnytimeEnabled)) {
                                            biometricUnlockTrackerImpl2.traceStateLogger.log(acquired.stage.name() + "(" + acquired.authInfo.getAcquiredInfo() + ")");
                                            long currentTimeMillis = System.currentTimeMillis();
                                            if (deviceEntryUnlockStateMachine.previous == DeviceEntryUnlockStage.STARTED && (latencyTracker = (latencyTrackerWrapper = biometricUnlockTrackerImpl2.latencyTrackerWrapper).tracker) != null) {
                                                latencyTracker.onActionStart(latencyTrackerWrapper.cuj);
                                            }
                                            int acquiredInfo = acquired.authInfo.getAcquiredInfo();
                                            if (acquiredInfo != 7 && acquiredInfo != 20) {
                                                biometricUnlockTrackerImpl2.metricsLoggerSession.log(acquired, currentTimeMillis - biometricUnlockTrackerImpl2.lastAcquiredTimeStamp);
                                            }
                                            biometricUnlockTrackerImpl2.lastAcquiredTimeStamp = currentTimeMillis;
                                        } else {
                                            biometricUnlockTrackerImpl2.lastAcquiredTimeStamp = System.currentTimeMillis();
                                        }
                                    }
                                }
                            }
                        } else if (ordinal == 2) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl3 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Succeeded succeeded = (DeviceEntryUnlockEvent.Succeeded) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl3.metricsLoggerSession.valid && succeeded.type == biometricUnlockTrackerImpl3.type) {
                                    if (biometricUnlockTrackerImpl3.stateMachine.tryTransitTo(DeviceEntryUnlockStage.HAL_AUTHENTICATED)) {
                                        long currentTimeMillis2 = System.currentTimeMillis() - biometricUnlockTrackerImpl3.lastAcquiredTimeStamp;
                                        biometricUnlockTrackerImpl3.traceStateLogger.log(succeeded.stage.name());
                                        biometricUnlockTrackerImpl3.metricsLoggerSession.log(succeeded, currentTimeMillis2);
                                    }
                                }
                            }
                        } else if (ordinal == 3) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl4 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.ExitKeyguard exitKeyguard = (DeviceEntryUnlockEvent.ExitKeyguard) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl4.metricsLoggerSession.valid && (deviceEntryUnlockStage2 = exitKeyguard.stage) == (deviceEntryUnlockStage = DeviceEntryUnlockStage.EXIT_KEYGUARD) && biometricUnlockTrackerImpl4.stateMachine.tryTransitTo(deviceEntryUnlockStage)) {
                                    biometricUnlockTrackerImpl4.traceStateLogger.log(deviceEntryUnlockStage2.name());
                                    biometricUnlockTrackerImpl4.lastExitKeyguardTimeStamp = System.currentTimeMillis();
                                    exitKeyguard.unlockToLauncher = biometricUnlockTrackerImpl4.keyguardUnlockAnimationController.isSupportedLauncherUnderneath();
                                    biometricUnlockTrackerImpl4.metricsLoggerSession.log(exitKeyguard, 0L);
                                }
                            }
                        } else if (ordinal == 4) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl5 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Started started = (DeviceEntryUnlockEvent.Started) deviceEntryUnlockEvent;
                                biometricUnlockTrackerImpl5.getClass();
                                if (started.type == biometricUnlockTrackerImpl5.type) {
                                    if (!biometricUnlockTrackerImpl5.stateMachine.tryTransitTo(DeviceEntryUnlockStage.STARTED) && (latencyTracker2 = (latencyTrackerWrapper2 = biometricUnlockTrackerImpl5.latencyTrackerWrapper).tracker) != null) {
                                        latencyTracker2.onActionCancel(latencyTrackerWrapper2.cuj);
                                    }
                                    IStats iStats = DeviceEntryUnlockMetricsLogger.statsService;
                                    biometricUnlockTrackerImpl5.metricsLoggerSession = new DeviceEntryUnlockMetricsLogger.Session(biometricUnlockTrackerImpl5.type);
                                    biometricUnlockTrackerImpl5.lastAcquiredTimeStamp = 0L;
                                    biometricUnlockTrackerImpl5.lastExitKeyguardTimeStamp = 0L;
                                    int i3 = BiometricUnlockTrackerImpl.WhenMappings.$EnumSwitchMapping$0[biometricUnlockTrackerImpl5.type.ordinal()];
                                    if (i3 != 1) {
                                        if (i3 == 2) {
                                            started.fpsSensorType = (FingerprintSensorType) ((FingerprintPropertyRepositoryImpl) biometricUnlockTrackerImpl5.fingerprintPropertyRepository).sensorType.getValue();
                                            biometricUnlockTrackerImpl5.metricsLoggerSession.touchAnytimeEnabled = Settings.Secure.getIntForUser(biometricUnlockTrackerImpl5.context.getContentResolver(), "sfps_performant_auth_enabled_v2", -1, biometricUnlockTrackerImpl5.context.getUserId()) > 0;
                                            biometricUnlockTrackerImpl5.metricsLoggerSession.valid = true;
                                        }
                                    } else if (Settings.Secure.getIntForUser(biometricUnlockTrackerImpl5.context.getContentResolver(), "face_unlock_dismisses_keyguard", -1, biometricUnlockTrackerImpl5.context.getUserId()) > 0) {
                                        biometricUnlockTrackerImpl5.metricsLoggerSession.valid = true;
                                    }
                                    biometricUnlockTrackerImpl5.metricsLoggerSession.log(started, 0L);
                                }
                            }
                        } else if (ordinal == 5) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl6 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Stopped stopped = (DeviceEntryUnlockEvent.Stopped) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl6.metricsLoggerSession.valid && stopped.type == biometricUnlockTrackerImpl6.type) {
                                    if (biometricUnlockTrackerImpl6.stateMachine.tryTransitTo(DeviceEntryUnlockStage.STOPPED)) {
                                        biometricUnlockTrackerImpl6.traceStateLogger.log(stopped.stage.name());
                                    }
                                }
                            }
                        } else if (ordinal == 7) {
                            for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl7 : deviceEntryUnlockTrackerInteractorGoogle3.trackers) {
                                DeviceEntryUnlockEvent.Unlocked unlocked = (DeviceEntryUnlockEvent.Unlocked) deviceEntryUnlockEvent;
                                if (biometricUnlockTrackerImpl7.metricsLoggerSession.valid && (deviceEntryUnlockStage4 = unlocked.stage) == (deviceEntryUnlockStage3 = DeviceEntryUnlockStage.UNLOCKED)) {
                                    long currentTimeMillis3 = System.currentTimeMillis();
                                    boolean tryTransitTo = biometricUnlockTrackerImpl7.stateMachine.tryTransitTo(deviceEntryUnlockStage3);
                                    LatencyTrackerWrapper latencyTrackerWrapper4 = biometricUnlockTrackerImpl7.latencyTrackerWrapper;
                                    if (tryTransitTo) {
                                        long j = currentTimeMillis3 - biometricUnlockTrackerImpl7.lastExitKeyguardTimeStamp;
                                        LatencyTracker latencyTracker4 = latencyTrackerWrapper4.tracker;
                                        if (latencyTracker4 != null) {
                                            latencyTracker4.onActionEnd(latencyTrackerWrapper4.cuj);
                                        }
                                        biometricUnlockTrackerImpl7.traceStateLogger.log(deviceEntryUnlockStage4.name());
                                        DeviceEntryUnlockMetricsLogger.Session session2 = biometricUnlockTrackerImpl7.metricsLoggerSession;
                                        session2.log(unlocked, j);
                                        if (session2.valid) {
                                            VendorAtom vendorAtom = new VendorAtom();
                                            vendorAtom.reverseDomainName = "";
                                            vendorAtom.atomId = 100246;
                                            vendorAtom.values = new VendorAtomValue[]{VendorAtomValue.intValue(session2.modality), new VendorAtomValue(6, CollectionsKt.toLongArray(session2.acquisitionDurationList)), new VendorAtomValue(1, Long.valueOf(session2.authenticationDuration)), new VendorAtomValue(1, Long.valueOf(session2.exitKeyguardDuration)), VendorAtomValue.intValue(session2.fpsSensorType.getNumber()), VendorAtomValue.intValue(session2.unlockAnimType.getNumber()), new VendorAtomValue(6, CollectionsKt.toLongArray(session2.acquiredInfoList))};
                                            try {
                                                IStats iStats2 = DeviceEntryUnlockMetricsLogger.statsService;
                                                if (iStats2 != null) {
                                                    ((IStats.Stub.Proxy) iStats2).reportVendorAtom(vendorAtom);
                                                }
                                            } catch (Exception e) {
                                                Log.e("DEUMetricsLogger", "Failed to log atom to IStats service!", e);
                                            }
                                            session2.valid = false;
                                        }
                                    } else {
                                        LatencyTracker latencyTracker5 = latencyTrackerWrapper4.tracker;
                                        if (latencyTracker5 != null) {
                                            latencyTracker5.onActionCancel(latencyTrackerWrapper4.cuj);
                                        }
                                        biometricUnlockTrackerImpl7.lastAcquiredTimeStamp = 0L;
                                        biometricUnlockTrackerImpl7.lastExitKeyguardTimeStamp = 0L;
                                    }
                                }
                            }
                        }
                        return Unit.INSTANCE;
                    }
                }), this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
