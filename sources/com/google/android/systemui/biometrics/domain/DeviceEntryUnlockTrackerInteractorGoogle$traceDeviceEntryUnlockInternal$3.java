package com.google.android.systemui.biometrics.domain;

import com.android.internal.util.LatencyTracker;
import com.google.android.systemui.biometrics.DeviceEntryUnlockStage;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceEntryUnlockTrackerInteractorGoogle this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3(DeviceEntryUnlockTrackerInteractorGoogle deviceEntryUnlockTrackerInteractorGoogle, Continuation continuation) {
        super(3, continuation);
        this.this$0 = deviceEntryUnlockTrackerInteractorGoogle;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3 deviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3 = new DeviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3(this.this$0, (Continuation) obj3);
        deviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3.L$0 = (Throwable) obj2;
        Unit unit = Unit.INSTANCE;
        deviceEntryUnlockTrackerInteractorGoogle$traceDeviceEntryUnlockInternal$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        for (BiometricUnlockTrackerImpl biometricUnlockTrackerImpl : this.this$0.trackers) {
            DeviceEntryUnlockStateMachine deviceEntryUnlockStateMachine = biometricUnlockTrackerImpl.stateMachine;
            if (deviceEntryUnlockStateMachine.current == DeviceEntryUnlockStage.EXIT_KEYGUARD) {
                LatencyTrackerWrapper latencyTrackerWrapper = biometricUnlockTrackerImpl.latencyTrackerWrapper;
                LatencyTracker latencyTracker = latencyTrackerWrapper.tracker;
                if (latencyTracker != null) {
                    latencyTracker.onActionCancel(latencyTrackerWrapper.cuj);
                }
                biometricUnlockTrackerImpl.lastAcquiredTimeStamp = 0L;
                biometricUnlockTrackerImpl.lastExitKeyguardTimeStamp = 0L;
                deviceEntryUnlockStateMachine.tryTransitTo(DeviceEntryUnlockStage.UNKNOWN);
            }
        }
        return Unit.INSTANCE;
    }
}
