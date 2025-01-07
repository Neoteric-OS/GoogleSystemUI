package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.os.OutcomeReceiver;
import android.telephony.satellite.SatelliteManager;
import com.android.keyguard.logging.KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ExecutorsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ SatelliteManager $sm;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2(SatelliteManager satelliteManager, DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceBasedSatelliteRepositoryImpl;
        this.$sm = satelliteManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2(this.$sm, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = this.this$0;
            SatelliteManager satelliteManager = this.$sm;
            this.L$0 = deviceBasedSatelliteRepositoryImpl;
            this.L$1 = satelliteManager;
            this.label = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(1, IntrinsicsKt__IntrinsicsJvmKt.intercepted(this));
            cancellableContinuationImpl.initCancellability();
            OutcomeReceiver outcomeReceiver = new OutcomeReceiver() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$receiver$1
                @Override // android.os.OutcomeReceiver
                public final void onError(Throwable th) {
                    DeviceBasedSatelliteRepositoryImpl.Companion.access$e(DeviceBasedSatelliteRepositoryImpl.this.logBuffer, "requestIsProvisioned.onError:", (SatelliteManager.SatelliteException) th);
                    cancellableContinuationImpl.resumeWith(Boolean.FALSE);
                }

                @Override // android.os.OutcomeReceiver
                public final void onResult(Object obj2) {
                    Boolean bool = (Boolean) obj2;
                    final boolean booleanValue = bool.booleanValue();
                    LogBuffer logBuffer = DeviceBasedSatelliteRepositoryImpl.this.logBuffer;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$receiver$1$onResult$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj3) {
                            return KeyguardUpdateMonitorLogger$allowFingerprintOnCurrentOccludingActivityChanged$2$$ExternalSyntheticOutline0.m("requestIsProvisioned.onResult: ", booleanValue);
                        }
                    };
                    DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$1 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
                    LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, function1, null);
                    deviceBasedSatelliteRepositoryImpl$Companion$i$1.getClass();
                    logBuffer.commit(obtain);
                    cancellableContinuationImpl.resumeWith(bool);
                }
            };
            LogBuffer logBuffer = deviceBasedSatelliteRepositoryImpl.logBuffer;
            DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$1 deviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return "Query for current satellite provisioned state.";
                }
            };
            DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$1 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
            LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, deviceBasedSatelliteRepositoryImpl$queryIsSatelliteProvisioned$2$1$1, null);
            deviceBasedSatelliteRepositoryImpl$Companion$i$1.getClass();
            logBuffer.commit(obtain);
            try {
                satelliteManager.requestIsProvisioned(ExecutorsKt.asExecutor(deviceBasedSatelliteRepositoryImpl.bgDispatcher), outcomeReceiver);
            } catch (Exception e) {
                DeviceBasedSatelliteRepositoryImpl.Companion.access$e(deviceBasedSatelliteRepositoryImpl.logBuffer, "Exception while calling SatelliteManager.requestIsProvisioned:", e);
                cancellableContinuationImpl.resumeWith(Boolean.FALSE);
            }
            obj = cancellableContinuationImpl.getResult();
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
