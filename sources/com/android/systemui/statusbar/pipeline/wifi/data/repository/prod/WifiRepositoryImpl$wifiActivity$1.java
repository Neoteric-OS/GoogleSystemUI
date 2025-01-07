package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import android.net.wifi.WifiManager;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModelKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WifiRepositoryImpl$wifiActivity$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WifiRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiRepositoryImpl$wifiActivity$1(WifiRepositoryImpl wifiRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wifiRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WifiRepositoryImpl$wifiActivity$1 wifiRepositoryImpl$wifiActivity$1 = new WifiRepositoryImpl$wifiActivity$1(this.this$0, continuation);
        wifiRepositoryImpl$wifiActivity$1.L$0 = obj;
        return wifiRepositoryImpl$wifiActivity$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WifiRepositoryImpl$wifiActivity$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.net.wifi.WifiManager$TrafficStateCallback, com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiActivity$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WifiRepositoryImpl wifiRepositoryImpl = this.this$0;
            final ?? r1 = new WifiManager.TrafficStateCallback() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiActivity$1$callback$1
                public final void onStateChanged(int i2) {
                    WifiRepositoryImpl wifiRepositoryImpl2 = WifiRepositoryImpl.this;
                    wifiRepositoryImpl2.getClass();
                    LogLevel logLevel = LogLevel.DEBUG;
                    WifiRepositoryImpl$logActivity$2 wifiRepositoryImpl$logActivity$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$logActivity$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onActivityChanged: ", ((LogMessage) obj2).getStr1());
                        }
                    };
                    LogBuffer logBuffer = wifiRepositoryImpl2.inputLogger;
                    LogMessage obtain = logBuffer.obtain("WifiTrackerLibInputLog", logLevel, wifiRepositoryImpl$logActivity$2, null);
                    ((LogMessageImpl) obtain).str1 = i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? "INVALID" : "INOUT" : "OUT" : "IN" : "NONE";
                    logBuffer.commit(obtain);
                    ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(DataActivityModelKt.toWifiDataActivityModel(i2));
                }
            };
            wifiRepositoryImpl.wifiManager.registerTrafficStateCallback(wifiRepositoryImpl.mainExecutor, r1);
            final WifiRepositoryImpl wifiRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiActivity$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    WifiRepositoryImpl.this.wifiManager.unregisterTrafficStateCallback(r1);
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
