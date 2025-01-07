package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiScanEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WifiRepositoryImpl$wifiScanResults$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ WifiRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiRepositoryImpl$wifiScanResults$1(WifiRepositoryImpl wifiRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = wifiRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WifiRepositoryImpl$wifiScanResults$1 wifiRepositoryImpl$wifiScanResults$1 = new WifiRepositoryImpl$wifiScanResults$1(this.this$0, continuation);
        wifiRepositoryImpl$wifiScanResults$1.L$0 = obj;
        return wifiRepositoryImpl$wifiScanResults$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WifiRepositoryImpl$wifiScanResults$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [android.net.wifi.WifiManager$ScanResultsCallback, com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiScanResults$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WifiRepositoryImpl wifiRepositoryImpl = this.this$0;
            final ?? r1 = new WifiManager.ScanResultsCallback() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiScanResults$1$callback$1
                @Override // android.net.wifi.WifiManager.ScanResultsCallback
                public final void onScanResultsAvailable() {
                    WifiRepositoryImpl wifiRepositoryImpl2 = WifiRepositoryImpl.this;
                    wifiRepositoryImpl2.getClass();
                    LogLevel logLevel = LogLevel.DEBUG;
                    WifiRepositoryImpl$logScanResults$2 wifiRepositoryImpl$logScanResults$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$logScanResults$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            return "onScanResultsAvailable";
                        }
                    };
                    LogBuffer logBuffer = wifiRepositoryImpl2.inputLogger;
                    logBuffer.commit(logBuffer.obtain("WifiTrackerLibInputLog", logLevel, wifiRepositoryImpl$logScanResults$2, null));
                    ProducerScope producerScope2 = producerScope;
                    List<ScanResult> scanResults = WifiRepositoryImpl.this.wifiManager.getScanResults();
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(scanResults, 10));
                    Iterator<T> it = scanResults.iterator();
                    while (it.hasNext()) {
                        arrayList.add(new WifiScanEntry(((ScanResult) it.next()).SSID));
                    }
                    ((ProducerCoroutine) producerScope2).mo1790trySendJP2dKIU(arrayList);
                }
            };
            WifiRepositoryImpl wifiRepositoryImpl2 = this.this$0;
            wifiRepositoryImpl2.wifiManager.registerScanResultsCallback(ExecutorsKt.asExecutor(wifiRepositoryImpl2.bgDispatcher), r1);
            final WifiRepositoryImpl wifiRepositoryImpl3 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiScanResults$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    WifiRepositoryImpl.this.wifiManager.unregisterScanResultsCallback(r1);
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
