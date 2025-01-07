package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.satellite.SatelliteSupportedStateCallback;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1(DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = deviceBasedSatelliteRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1 deviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1 = new DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1(this.this$0, continuation);
        deviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1.L$0 = obj;
        return deviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.telephony.satellite.SatelliteSupportedStateCallback, com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = this.this$0;
            final ?? r1 = new SatelliteSupportedStateCallback() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1$callback$1
                public final void onSatelliteSupportedStateChanged(final boolean z) {
                    LogBuffer logBuffer = DeviceBasedSatelliteRepositoryImpl.this.logBuffer;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1$callback$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return "onSatelliteSupportedStateChanged: ".concat(z ? "supported" : "not supported");
                        }
                    };
                    DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$1 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
                    LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, function1, null);
                    deviceBasedSatelliteRepositoryImpl$Companion$i$1.getClass();
                    logBuffer.commit(obtain);
                    ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(Boolean.valueOf(z));
                }
            };
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            try {
                DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl2 = this.this$0;
                deviceBasedSatelliteRepositoryImpl2.satelliteManager.registerForSupportedStateChanged(ExecutorsKt.asExecutor(deviceBasedSatelliteRepositoryImpl2.bgDispatcher), (SatelliteSupportedStateCallback) r1);
                ref$BooleanRef.element = true;
            } catch (Exception e) {
                DeviceBasedSatelliteRepositoryImpl.Companion.access$e(this.this$0.logBuffer, "error registering for supported state change", e);
            }
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl3 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    if (Ref$BooleanRef.this.element) {
                        deviceBasedSatelliteRepositoryImpl3.satelliteManager.unregisterForSupportedStateChanged(r1);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
