package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.LogMessageImpl;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class WifiRepositoryImpl$wifiPickerTrackerInfo$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Ref$ObjectRef $current;
    final /* synthetic */ WifiRepositoryImpl $this_run;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiRepositoryImpl$wifiPickerTrackerInfo$1$1(WifiRepositoryImpl wifiRepositoryImpl, Ref$ObjectRef ref$ObjectRef, Continuation continuation) {
        super(2, continuation);
        this.$this_run = wifiRepositoryImpl;
        this.$current = ref$ObjectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        WifiRepositoryImpl$wifiPickerTrackerInfo$1$1 wifiRepositoryImpl$wifiPickerTrackerInfo$1$1 = new WifiRepositoryImpl$wifiPickerTrackerInfo$1$1(this.$this_run, this.$current, continuation);
        wifiRepositoryImpl$wifiPickerTrackerInfo$1$1.L$0 = obj;
        return wifiRepositoryImpl$wifiPickerTrackerInfo$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((WifiRepositoryImpl$wifiPickerTrackerInfo$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final WifiRepositoryImpl wifiRepositoryImpl = this.$this_run;
            final Ref$ObjectRef ref$ObjectRef = this.$current;
            WifiPickerTracker create = wifiRepositoryImpl.wifiPickerTrackerFactory.create(wifiRepositoryImpl.lifecycle, new WifiPickerTracker.WifiPickerTrackerCallback() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiPickerTrackerInfo$1$1$callback$1
                public static void send$default(WifiRepositoryImpl$wifiPickerTrackerInfo$1$1$callback$1 wifiRepositoryImpl$wifiPickerTrackerInfo$1$1$callback$1, int i2, boolean z, WifiNetworkModel wifiNetworkModel, List list, int i3) {
                    if ((i3 & 1) != 0) {
                        i2 = ((WifiRepositoryImpl.WifiPickerTrackerInfo) ref$ObjectRef.element).state;
                    }
                    if ((i3 & 2) != 0) {
                        z = ((WifiRepositoryImpl.WifiPickerTrackerInfo) ref$ObjectRef.element).isDefault;
                    }
                    if ((i3 & 4) != 0) {
                        wifiNetworkModel = ((WifiRepositoryImpl.WifiPickerTrackerInfo) ref$ObjectRef.element).primaryNetwork;
                    }
                    if ((i3 & 8) != 0) {
                        list = ((WifiRepositoryImpl.WifiPickerTrackerInfo) ref$ObjectRef.element).secondaryNetworks;
                    }
                    wifiRepositoryImpl$wifiPickerTrackerInfo$1$1$callback$1.getClass();
                    WifiRepositoryImpl.WifiPickerTrackerInfo wifiPickerTrackerInfo = new WifiRepositoryImpl.WifiPickerTrackerInfo(i2, z, wifiNetworkModel, list);
                    ref$ObjectRef.element = wifiPickerTrackerInfo;
                    ((ProducerCoroutine) producerScope).mo1790trySendJP2dKIU(wifiPickerTrackerInfo);
                }

                @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
                public final void onWifiEntriesChanged() {
                    WifiRepositoryImpl wifiRepositoryImpl2 = WifiRepositoryImpl.this;
                    WifiPickerTracker wifiPickerTracker = wifiRepositoryImpl2.wifiPickerTracker;
                    WifiEntry mergedCarrierEntry = wifiPickerTracker != null ? wifiPickerTracker.getMergedCarrierEntry() : null;
                    if (mergedCarrierEntry == null || !mergedCarrierEntry.isDefaultNetwork()) {
                        mergedCarrierEntry = wifiPickerTracker != null ? wifiPickerTracker.mConnectedWifiEntry : null;
                    }
                    LogLevel logLevel = LogLevel.DEBUG;
                    WifiRepositoryImpl$logOnWifiEntriesChanged$2 wifiRepositoryImpl$logOnWifiEntriesChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$logOnWifiEntriesChanged$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("onWifiEntriesChanged. ConnectedEntry=", ((LogMessage) obj2).getStr1());
                        }
                    };
                    LogBuffer logBuffer = wifiRepositoryImpl2.inputLogger;
                    LogMessage obtain = logBuffer.obtain("WifiTrackerLibInputLog", logLevel, wifiRepositoryImpl$logOnWifiEntriesChanged$2, null);
                    ((LogMessageImpl) obtain).str1 = String.valueOf(mergedCarrierEntry);
                    logBuffer.commit(obtain);
                    WifiPickerTracker wifiPickerTracker2 = wifiRepositoryImpl2.wifiPickerTracker;
                    Iterable arrayList = wifiPickerTracker2 != null ? new ArrayList(wifiPickerTracker2.mActiveWifiEntries) : null;
                    if (arrayList == null) {
                        arrayList = EmptyList.INSTANCE;
                    }
                    ArrayList<WifiEntry> arrayList2 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        WifiEntry wifiEntry = (WifiEntry) obj2;
                        if (!Intrinsics.areEqual(wifiEntry, mergedCarrierEntry) && !wifiEntry.isPrimaryNetwork()) {
                            arrayList2.add(obj2);
                        }
                    }
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                    for (WifiEntry wifiEntry2 : arrayList2) {
                        Intrinsics.checkNotNull(wifiEntry2);
                        arrayList3.add(wifiRepositoryImpl2.toWifiNetworkModel(wifiEntry2));
                    }
                    WifiNetworkModel wifiNetworkModel = WifiRepositoryImpl.WIFI_NETWORK_DEFAULT;
                    if (mergedCarrierEntry != null && mergedCarrierEntry.isPrimaryNetwork()) {
                        wifiNetworkModel = wifiRepositoryImpl2.toWifiNetworkModel(mergedCarrierEntry);
                    }
                    send$default(this, 0, mergedCarrierEntry != null ? mergedCarrierEntry.isDefaultNetwork() : false, wifiNetworkModel, arrayList3, 1);
                }

                @Override // com.android.wifitrackerlib.WifiPickerTracker.WifiPickerTrackerCallback
                public final void onWifiStateChanged() {
                    WifiRepositoryImpl wifiRepositoryImpl2 = WifiRepositoryImpl.this;
                    WifiPickerTracker wifiPickerTracker = wifiRepositoryImpl2.wifiPickerTracker;
                    Integer valueOf = wifiPickerTracker != null ? Integer.valueOf(wifiPickerTracker.mWifiState) : null;
                    LogLevel logLevel = LogLevel.DEBUG;
                    WifiRepositoryImpl$logOnWifiStateChanged$2 wifiRepositoryImpl$logOnWifiStateChanged$2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$logOnWifiStateChanged$2
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            LogMessage logMessage = (LogMessage) obj2;
                            return "onWifiStateChanged. State=" + (logMessage.getInt1() == -1 ? null : Integer.valueOf(logMessage.getInt1()));
                        }
                    };
                    LogBuffer logBuffer = wifiRepositoryImpl2.inputLogger;
                    LogMessage obtain = logBuffer.obtain("WifiTrackerLibInputLog", logLevel, wifiRepositoryImpl$logOnWifiStateChanged$2, null);
                    ((LogMessageImpl) obtain).int1 = valueOf != null ? valueOf.intValue() : -1;
                    logBuffer.commit(obtain);
                    send$default(this, valueOf != null ? valueOf.intValue() : 1, false, null, null, 14);
                }
            }, "WifiRepository");
            if (create != null) {
                create.mIsScanningDisabled = true;
                create.mInjector.mVerboseLoggingDisabledOverride = true;
            }
            wifiRepositoryImpl.wifiPickerTracker = create;
            WifiRepositoryImpl wifiRepositoryImpl2 = this.$this_run;
            wifiRepositoryImpl2.mainExecutor.execute(new WifiRepositoryImpl$lifecycle$1$1(1, wifiRepositoryImpl2));
            final WifiRepositoryImpl wifiRepositoryImpl3 = this.$this_run;
            Function0 function0 = new Function0() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$wifiPickerTrackerInfo$1$1.3
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    WifiRepositoryImpl wifiRepositoryImpl4 = WifiRepositoryImpl.this;
                    wifiRepositoryImpl4.mainExecutor.execute(new WifiRepositoryImpl$lifecycle$1$1(2, wifiRepositoryImpl4));
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
