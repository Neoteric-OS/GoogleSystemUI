package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.telephony.satellite.SatelliteManager;
import android.telephony.satellite.SatelliteModemStateCallback;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl;
import com.android.systemui.statusbar.pipeline.satellite.shared.model.SatelliteConnectionState;
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
final class DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SatelliteManager $sm;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1(SatelliteManager satelliteManager, DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.$sm = satelliteManager;
        this.this$0 = deviceBasedSatelliteRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1 deviceBasedSatelliteRepositoryImpl$connectionStateFlow$1 = new DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1(this.$sm, this.this$0, continuation);
        deviceBasedSatelliteRepositoryImpl$connectionStateFlow$1.L$0 = obj;
        return deviceBasedSatelliteRepositoryImpl$connectionStateFlow$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [android.telephony.satellite.SatelliteModemStateCallback, com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1$cb$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = this.this$0;
            final ?? r1 = new SatelliteModemStateCallback() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1$cb$1
                public final void onSatelliteModemStateChanged(final int i2) {
                    SatelliteConnectionState satelliteConnectionState;
                    LogBuffer logBuffer = DeviceBasedSatelliteRepositoryImpl.this.logBuffer;
                    Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1$cb$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            ((LogMessage) obj2).setInt1(i2);
                            return Unit.INSTANCE;
                        }
                    };
                    LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1$cb$1.2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AnnotationValue$1$$ExternalSyntheticOutline0.m(((LogMessage) obj2).getInt1(), "onSatelliteModemStateChanged: state=");
                        }
                    }, null);
                    function1.invoke(obtain);
                    logBuffer.commit(obtain);
                    ProducerScope producerScope2 = producerScope;
                    SatelliteConnectionState.Companion.getClass();
                    switch (i2) {
                        case -1:
                            satelliteConnectionState = SatelliteConnectionState.Unknown;
                            break;
                        case 0:
                        case 1:
                        case 6:
                            satelliteConnectionState = SatelliteConnectionState.On;
                            break;
                        case 2:
                        case 3:
                        case 7:
                            satelliteConnectionState = SatelliteConnectionState.Connected;
                            break;
                        case 4:
                        case 5:
                            satelliteConnectionState = SatelliteConnectionState.Off;
                            break;
                        default:
                            satelliteConnectionState = SatelliteConnectionState.Unknown;
                            break;
                    }
                    ((ProducerCoroutine) producerScope2).mo1790trySendJP2dKIU(satelliteConnectionState);
                }
            };
            final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
            try {
                ref$BooleanRef.element = this.$sm.registerForModemStateChanged(ExecutorsKt.asExecutor(this.this$0.bgDispatcher), (SatelliteModemStateCallback) r1) == 0;
            } catch (Exception e) {
                DeviceBasedSatelliteRepositoryImpl.Companion.access$e(this.this$0.logBuffer, "error registering for modem state", e);
            }
            final SatelliteManager satelliteManager = this.$sm;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$connectionStateFlow$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    if (Ref$BooleanRef.this.element) {
                        satelliteManager.unregisterForModemStateChanged(r1);
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
