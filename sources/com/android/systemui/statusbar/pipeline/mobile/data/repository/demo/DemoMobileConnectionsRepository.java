package com.android.systemui.statusbar.pipeline.mobile.data.repository.demo;

import android.content.Context;
import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import androidx.constraintlayout.core.PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0;
import com.android.settingslib.mobile.MobileMappings;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType;
import com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.model.FakeNetworkEventModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModelKt;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.demo.DemoModeWifiDataSource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.StandaloneCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoMobileConnectionsRepository implements MobileConnectionsRepository {
    public final StateFlowImpl _subscriptions;
    public final ReadonlyStateFlow activeMobileDataRepository;
    public final ReadonlyStateFlow activeMobileDataSubscriptionId;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1 activeSubChangedInGroupEvent;
    public Integer carrierMergedSubId;
    public final StateFlowImpl defaultConnectionIsValidated;
    public final StateFlowImpl defaultDataSubId;
    public final StateFlowImpl defaultDataSubRatConfig;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 defaultMobileIconGroup;
    public final StateFlowImpl defaultMobileIconMapping;
    public final StateFlowImpl hasCarrierMergedConnection;
    public final FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 isAnySimSecure;
    public final StateFlowImpl isDeviceEmergencyCallCapable;
    public final TableLogBufferFactory logFactory;
    public final DemoModeMobileConnectionDataSource mobileDataSource;
    public StandaloneCoroutine mobileDemoCommandJob;
    public final StateFlowImpl mobileIsDefault;
    public final ReadonlyStateFlow mobileMappingsReverseLookup;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow subscriptions;
    public final DemoModeWifiDataSource wifiDataSource;
    public StandaloneCoroutine wifiDemoCommandJob;
    public Map connectionRepoCache = new LinkedHashMap();
    public final Map subscriptionInfoCache = new LinkedHashMap();

    public DemoMobileConnectionsRepository(DemoModeMobileConnectionDataSource demoModeMobileConnectionDataSource, DemoModeWifiDataSource demoModeWifiDataSource, CoroutineScope coroutineScope, Context context, TableLogBufferFactory tableLogBufferFactory) {
        this.mobileDataSource = demoModeMobileConnectionDataSource;
        this.wifiDataSource = demoModeWifiDataSource;
        this.scope = coroutineScope;
        this.logFactory = tableLogBufferFactory;
        SharedFlowKt.MutableSharedFlow$default(0, 1, null, 5);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(EmptyList.INSTANCE);
        this._subscriptions = MutableStateFlow;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(MutableStateFlow, new DemoMobileConnectionsRepository$subscriptions$1(this, null), 0), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow.getValue());
        this.subscriptions = stateIn;
        ChannelFlowTransformLatest mapLatest = FlowKt.mapLatest(new DemoMobileConnectionsRepository$activeMobileDataSubscriptionId$1(2, null), stateIn);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        SubscriptionModel subscriptionModel = (SubscriptionModel) CollectionsKt.firstOrNull((List) stateIn.getValue());
        final ReadonlyStateFlow stateIn2 = FlowKt.stateIn(mapLatest, coroutineScope, WhileSubscribed$default, Integer.valueOf(subscriptionModel != null ? subscriptionModel.subscriptionId : -1));
        this.activeMobileDataSubscriptionId = stateIn2;
        this.activeMobileDataRepository = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ DemoMobileConnectionsRepository this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, DemoMobileConnectionsRepository demoMobileConnectionsRepository) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = demoMobileConnectionsRepository;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L49
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository r6 = r4.this$0
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository r5 = r6.getRepoForSubId(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionsRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                ReadonlyStateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), getRepoForSubId(((Number) stateIn2.getValue()).intValue()));
        this.activeSubChangedInGroupEvent = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$1(new Unit[0]);
        this.defaultDataSubRatConfig = StateFlowKt.MutableStateFlow(MobileMappings.Config.readConfig(context));
        this.defaultMobileIconGroup = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(TelephonyIcons.THREE_G);
        Boolean bool = Boolean.FALSE;
        this.isDeviceEmergencyCallCapable = StateFlowKt.MutableStateFlow(bool);
        this.isAnySimSecure = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(TelephonyIcons.ICON_NAME_TO_ICON);
        this.defaultMobileIconMapping = MutableStateFlow2;
        this.mobileMappingsReverseLookup = FlowKt.stateIn(FlowKt.mapLatest(new DemoMobileConnectionsRepository$mobileMappingsReverseLookup$1(this, null), MutableStateFlow2), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), reverse((Map) MutableStateFlow2.getValue()));
        this.defaultDataSubId = StateFlowKt.MutableStateFlow(-1);
        Boolean bool2 = Boolean.TRUE;
        this.mobileIsDefault = StateFlowKt.MutableStateFlow(bool2);
        this.hasCarrierMergedConnection = StateFlowKt.MutableStateFlow(bool);
        this.defaultConnectionIsValidated = StateFlowKt.MutableStateFlow(bool2);
    }

    public static Map reverse(Map map) {
        Set<Map.Entry> entrySet = map.entrySet();
        int mapCapacity = MapsKt__MapsJVMKt.mapCapacity(CollectionsKt__IterablesKt.collectionSizeOrDefault(entrySet, 10));
        if (mapCapacity < 16) {
            mapCapacity = 16;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(mapCapacity);
        for (Map.Entry entry : entrySet) {
            linkedHashMap.put(entry.getValue(), entry.getKey());
        }
        return linkedHashMap;
    }

    public final void disableCarrierMerged() {
        Integer num = this.carrierMergedSubId;
        if (num != null) {
            int intValue = num.intValue();
            CacheContainer cacheContainer = (CacheContainer) this.connectionRepoCache.get(this.carrierMergedSubId);
            FakeNetworkEventModel.Mobile mobile = cacheContainer != null ? cacheContainer.lastMobileState : null;
            if (mobile != null) {
                processEnabledMobileState(mobile);
            } else {
                removeSubscription(intValue);
            }
        }
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getActiveMobileDataRepository() {
        return this.activeMobileDataRepository;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getActiveMobileDataSubscriptionId() {
        return this.activeMobileDataSubscriptionId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getActiveSubChangedInGroupEvent() {
        return this.activeSubChangedInGroupEvent;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultConnectionIsValidated() {
        return this.defaultConnectionIsValidated;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultDataSubId() {
        return this.defaultDataSubId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getDefaultDataSubRatConfig() {
        return this.defaultDataSubRatConfig;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getDefaultMobileIconGroup() {
        return this.defaultMobileIconGroup;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getDefaultMobileIconMapping() {
        return this.defaultMobileIconMapping;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow getHasCarrierMergedConnection() {
        return this.hasCarrierMergedConnection;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final boolean getIsAnySimSecure() {
        return false;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getMobileIsDefault() {
        return this.mobileIsDefault;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow getSubscriptions() {
        return this.subscriptions;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Flow isAnySimSecure() {
        return this.isAnySimSecure;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final StateFlow isDeviceEmergencyCallCapable() {
        return this.isDeviceEmergencyCallCapable;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final Object isInEcmMode(Continuation continuation) {
        return Boolean.FALSE;
    }

    public final void maybeCreateSubscription(int i) {
        if (this.subscriptionInfoCache.containsKey(Integer.valueOf(i))) {
            return;
        }
        SubscriptionModel subscriptionModel = new SubscriptionModel(i, false, false, null, "demo carrier", -1);
        this.subscriptionInfoCache.put(Integer.valueOf(i), subscriptionModel);
        this._subscriptions.setValue(CollectionsKt.toList(this.subscriptionInfoCache.values()));
    }

    public final void processEnabledMobileState(FakeNetworkEventModel.Mobile mobile) {
        Integer num = mobile.subId;
        int intValue = num != null ? num.intValue() : 1;
        maybeCreateSubscription(intValue);
        DemoMobileConnectionRepository repoForSubId = getRepoForSubId(intValue);
        CacheContainer cacheContainer = (CacheContainer) this.connectionRepoCache.get(Integer.valueOf(intValue));
        if (cacheContainer != null) {
            cacheContainer.lastMobileState = mobile;
        }
        Integer valueOf = Integer.valueOf(intValue);
        StateFlowImpl stateFlowImpl = this.defaultDataSubId;
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, valueOf);
        String str = (String) ((Map) ((StateFlowImpl) this.mobileMappingsReverseLookup.$$delegate_0).getValue()).get(mobile.dataType);
        if (str == null) {
            str = "dis";
        }
        ResolvedNetworkType.DefaultNetworkType defaultNetworkType = new ResolvedNetworkType.DefaultNetworkType(str);
        Boolean bool = Boolean.TRUE;
        StateFlowImpl stateFlowImpl2 = repoForSubId.dataEnabled;
        stateFlowImpl2.getClass();
        stateFlowImpl2.updateState(null, bool);
        String str2 = mobile.name;
        NetworkNameModel.IntentDerived intentDerived = new NetworkNameModel.IntentDerived(str2);
        StateFlowImpl stateFlowImpl3 = repoForSubId.networkName;
        stateFlowImpl3.getClass();
        stateFlowImpl3.updateState(null, intentDerived);
        StringBuilder m = PriorityGoalRow$GoalVariableAccessor$$ExternalSyntheticOutline0.m(str2, " ");
        m.append(mobile.subId);
        NetworkNameModel.SubscriptionDerived subscriptionDerived = new NetworkNameModel.SubscriptionDerived(m.toString());
        StateFlowImpl stateFlowImpl4 = repoForSubId.carrierName;
        stateFlowImpl4.getClass();
        stateFlowImpl4.updateState(null, subscriptionDerived);
        Integer num2 = mobile.carrierId;
        Integer valueOf2 = Integer.valueOf(num2 != null ? num2.intValue() : -1);
        StateFlowImpl stateFlowImpl5 = repoForSubId._carrierId;
        stateFlowImpl5.getClass();
        stateFlowImpl5.updateState(null, valueOf2);
        Boolean valueOf3 = Boolean.valueOf(mobile.inflateStrength);
        StateFlowImpl stateFlowImpl6 = repoForSubId._inflateSignalStrength;
        stateFlowImpl6.getClass();
        stateFlowImpl6.updateState(null, valueOf3);
        boolean z = mobile.roaming;
        Boolean valueOf4 = Boolean.valueOf(z);
        StateFlowImpl stateFlowImpl7 = repoForSubId.cdmaRoaming;
        stateFlowImpl7.getClass();
        stateFlowImpl7.updateState(null, valueOf4);
        Boolean valueOf5 = Boolean.valueOf(z);
        StateFlowImpl stateFlowImpl8 = repoForSubId._isRoaming;
        stateFlowImpl8.getClass();
        stateFlowImpl8.updateState(null, valueOf5);
        Boolean bool2 = Boolean.FALSE;
        StateFlowImpl stateFlowImpl9 = repoForSubId._isEmergencyOnly;
        stateFlowImpl9.getClass();
        stateFlowImpl9.updateState(null, bool2);
        StateFlowImpl stateFlowImpl10 = repoForSubId._operatorAlphaShort;
        stateFlowImpl10.getClass();
        stateFlowImpl10.updateState(null, str2);
        Integer num3 = mobile.level;
        Boolean valueOf6 = Boolean.valueOf((num3 != null ? num3.intValue() : 0) > 0);
        StateFlowImpl stateFlowImpl11 = repoForSubId._isInService;
        stateFlowImpl11.getClass();
        stateFlowImpl11.updateState(null, valueOf6);
        StateFlowImpl stateFlowImpl12 = repoForSubId._isGsm;
        stateFlowImpl12.getClass();
        stateFlowImpl12.updateState(null, bool2);
        Integer valueOf7 = Integer.valueOf(num3 != null ? num3.intValue() : 0);
        StateFlowImpl stateFlowImpl13 = repoForSubId._cdmaLevel;
        stateFlowImpl13.getClass();
        stateFlowImpl13.updateState(null, valueOf7);
        Integer valueOf8 = Integer.valueOf(num3 != null ? num3.intValue() : 0);
        StateFlowImpl stateFlowImpl14 = repoForSubId._primaryLevel;
        stateFlowImpl14.getClass();
        stateFlowImpl14.updateState(null, valueOf8);
        DataConnectionState dataConnectionState = DataConnectionState.Connected;
        StateFlowImpl stateFlowImpl15 = repoForSubId._dataConnectionState;
        stateFlowImpl15.getClass();
        stateFlowImpl15.updateState(null, dataConnectionState);
        Integer num4 = mobile.activity;
        DataActivityModel mobileDataActivityModel = DataActivityModelKt.toMobileDataActivityModel(num4 != null ? num4.intValue() : 0);
        StateFlowImpl stateFlowImpl16 = repoForSubId._dataActivityDirection;
        stateFlowImpl16.getClass();
        stateFlowImpl16.updateState(null, mobileDataActivityModel);
        Boolean valueOf9 = Boolean.valueOf(mobile.carrierNetworkChange);
        StateFlowImpl stateFlowImpl17 = repoForSubId._carrierNetworkChangeActive;
        stateFlowImpl17.getClass();
        stateFlowImpl17.updateState(null, valueOf9);
        StateFlowImpl stateFlowImpl18 = repoForSubId._resolvedNetworkType;
        stateFlowImpl18.getClass();
        stateFlowImpl18.updateState(null, defaultNetworkType);
        Boolean valueOf10 = Boolean.valueOf(mobile.ntn);
        StateFlowImpl stateFlowImpl19 = repoForSubId._isNonTerrestrial;
        stateFlowImpl19.getClass();
        stateFlowImpl19.updateState(null, valueOf10);
        StateFlowImpl stateFlowImpl20 = repoForSubId.isAllowedDuringAirplaneMode;
        stateFlowImpl20.getClass();
        stateFlowImpl20.updateState(null, bool2);
        Boolean valueOf11 = Boolean.valueOf(mobile.slice);
        StateFlowImpl stateFlowImpl21 = repoForSubId.hasPrioritizedNetworkCapabilities;
        stateFlowImpl21.getClass();
        stateFlowImpl21.updateState(null, valueOf11);
    }

    public final void removeSubscription(int i) {
        StateFlowImpl stateFlowImpl = this._subscriptions;
        List list = (List) stateFlowImpl.getValue();
        this.subscriptionInfoCache.remove(Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (((SubscriptionModel) obj).subscriptionId != i) {
                arrayList.add(obj);
            }
        }
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, arrayList);
    }

    public final void startProcessingCommands() {
        DemoMobileConnectionsRepository$startProcessingCommands$1 demoMobileConnectionsRepository$startProcessingCommands$1 = new DemoMobileConnectionsRepository$startProcessingCommands$1(this, null);
        CoroutineScope coroutineScope = this.scope;
        this.mobileDemoCommandJob = BuildersKt.launch$default(coroutineScope, null, null, demoMobileConnectionsRepository$startProcessingCommands$1, 3);
        this.wifiDemoCommandJob = BuildersKt.launch$default(coroutineScope, null, null, new DemoMobileConnectionsRepository$startProcessingCommands$2(this, null), 3);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository
    public final DemoMobileConnectionRepository getRepoForSubId(int i) {
        CacheContainer cacheContainer = (CacheContainer) this.connectionRepoCache.get(Integer.valueOf(i));
        DemoMobileConnectionRepository demoMobileConnectionRepository = cacheContainer != null ? cacheContainer.repo : null;
        if (demoMobileConnectionRepository != null) {
            return demoMobileConnectionRepository;
        }
        String m = GenericDocument$$ExternalSyntheticOutline0.m("DemoMobileConnectionLog[", "]", i);
        TableLogBufferFactory tableLogBufferFactory = this.logFactory;
        Object obj = tableLogBufferFactory.existingBuffers.get(m);
        if (obj == null) {
            obj = tableLogBufferFactory.create(100, m);
            tableLogBufferFactory.existingBuffers.put(m, obj);
        }
        DemoMobileConnectionRepository demoMobileConnectionRepository2 = new DemoMobileConnectionRepository((TableLogBuffer) obj, this.scope);
        CacheContainer cacheContainer2 = new CacheContainer();
        cacheContainer2.repo = demoMobileConnectionRepository2;
        cacheContainer2.lastMobileState = null;
        this.connectionRepoCache.put(Integer.valueOf(i), cacheContainer2);
        return demoMobileConnectionRepository2;
    }
}
