package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.telephony.satellite.NtnSignalStrength;
import android.telephony.satellite.NtnSignalStrengthCallback;
import android.telephony.satellite.SatelliteManager;
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
final class DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SatelliteManager $sm;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1(SatelliteManager satelliteManager, DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$sm = satelliteManager;
        this.this$0 = deviceBasedSatelliteRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1 deviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1 = new DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1(this.$sm, this.this$0, continuation);
        deviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1.L$0 = obj;
        return deviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.telephony.satellite.NtnSignalStrengthCallback, com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1$cb$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = this.this$0;
            final ?? r1 = new NtnSignalStrengthCallback() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1$cb$1
                public final void onNtnSignalStrengthChanged(final NtnSignalStrength ntnSignalStrength) {
                    LogBuffer logBuffer = DeviceBasedSatelliteRepositoryImpl.this.verboseLogBuffer;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1$cb$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            ((LogMessage) obj2).setInt1(ntnSignalStrength.getLevel());
                            return Unit.INSTANCE;
                        }
                    };
                    LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1$cb$1.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj2).getInt1(), "onNtnSignalStrengthChanged: level=");
                        }
                    }, null);
                    function1.invoke(obtain);
                    logBuffer.commit(obtain);
                    ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(Integer.valueOf(ntnSignalStrength.getLevel()));
                }
            };
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            try {
                this.$sm.registerForNtnSignalStrengthChanged(ExecutorsKt.asExecutor(this.this$0.bgDispatcher), (NtnSignalStrengthCallback) r1);
                ref$BooleanRef.element = true;
                LogBuffer logBuffer = this.this$0.logBuffer;
                AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                        return "Registered for signal strength successfully";
                    }
                };
                DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$1 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
                LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, anonymousClass1, null);
                deviceBasedSatelliteRepositoryImpl$Companion$i$1.getClass();
                logBuffer.commit(obtain);
            } catch (Exception e) {
                DeviceBasedSatelliteRepositoryImpl.Companion.access$e(this.this$0.logBuffer, "error registering for signal strength", e);
            }
            final SatelliteManager satelliteManager = this.$sm;
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$signalStrengthFlow$1.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    if (Ref$BooleanRef.this.element) {
                        satelliteManager.unregisterForNtnSignalStrengthChanged(r1);
                        LogBuffer logBuffer2 = deviceBasedSatelliteRepositoryImpl2.logBuffer;
                        AnonymousClass1 anonymousClass12 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.signalStrengthFlow.1.2.1
                            @Override // kotlin.jvm.functions.Function1
                            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                                return "Unregistered for signal strength successfully";
                            }
                        };
                        DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$12 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
                        LogMessage obtain2 = logBuffer2.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, anonymousClass12, null);
                        deviceBasedSatelliteRepositoryImpl$Companion$i$12.invoke(obtain2);
                        logBuffer2.commit(obtain2);
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
