package com.android.systemui.statusbar.pipeline.mobile.data.repository.prod;

import android.content.Context;
import android.net.ConnectivityManager;
import android.telephony.TelephonyManager;
import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.log.table.TableLogBufferFactory;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.CarrierMergedConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.MobileConnectionRepositoryImpl;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FullMobileConnectionRepository implements MobileConnectionRepository {
    public final StateFlowImpl _isCarrierMerged;
    public final ReadonlyStateFlow activeRepo;
    public final ReadonlyStateFlow allowNetworkSliceIndicator;
    public final ReadonlyStateFlow carrierId;
    public final Lazy carrierMergedRepo$delegate;
    public final CarrierMergedConnectionRepository.Factory carrierMergedRepoFactory;
    public final ReadonlyStateFlow carrierName;
    public final ReadonlyStateFlow carrierNetworkChangeActive;
    public final ReadonlyStateFlow cdmaLevel;
    public final ReadonlyStateFlow cdmaRoaming;
    public final ReadonlyStateFlow dataActivityDirection;
    public final ReadonlyStateFlow dataConnectionState;
    public final ReadonlyStateFlow dataEnabled;
    public final NetworkNameModel defaultNetworkName;
    public final ReadonlyStateFlow hasPrioritizedNetworkCapabilities;
    public final ReadonlyStateFlow inflateSignalStrength;
    public final ReadonlyStateFlow isAllowedDuringAirplaneMode;
    public final ReadonlyStateFlow isCarrierMerged;
    public final ReadonlyStateFlow isEmergencyOnly;
    public final ReadonlyStateFlow isGsm;
    public final ReadonlyStateFlow isInService;
    public final ReadonlyStateFlow isNonTerrestrial;
    public final ReadonlyStateFlow isRoaming;
    public final Lazy mobileRepo$delegate;
    public final MobileConnectionRepositoryImpl.Factory mobileRepoFactory;
    public final ReadonlyStateFlow networkName;
    public final String networkNameSeparator;
    public final ReadonlyStateFlow numberOfLevels;
    public final ReadonlyStateFlow operatorAlphaShort;
    public final ReadonlyStateFlow primaryLevel;
    public final ReadonlyStateFlow resolvedNetworkType;
    public final int subId;
    public final TableLogBuffer tableLogBuffer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final CarrierMergedConnectionRepository.Factory carrierMergedRepoFactory;
        public final TableLogBufferFactory logFactory;
        public final MobileConnectionRepositoryImpl.Factory mobileRepoFactory;
        public final CoroutineScope scope;

        public Factory(CoroutineScope coroutineScope, TableLogBufferFactory tableLogBufferFactory, MobileConnectionRepositoryImpl.Factory factory, CarrierMergedConnectionRepository.Factory factory2) {
            this.scope = coroutineScope;
            this.logFactory = tableLogBufferFactory;
            this.mobileRepoFactory = factory;
            this.carrierMergedRepoFactory = factory2;
        }
    }

    public FullMobileConnectionRepository(int i, boolean z, TableLogBuffer tableLogBuffer, final MobileConnectionsRepositoryImpl$subscriptionModelForSubId$$inlined$map$1 mobileConnectionsRepositoryImpl$subscriptionModelForSubId$$inlined$map$1, NetworkNameModel networkNameModel, String str, CoroutineScope coroutineScope, MobileConnectionRepositoryImpl.Factory factory, CarrierMergedConnectionRepository.Factory factory2) {
        this.subId = i;
        this.tableLogBuffer = tableLogBuffer;
        this.defaultNetworkName = networkNameModel;
        this.networkNameSeparator = str;
        this.mobileRepoFactory = factory;
        this.carrierMergedRepoFactory = factory2;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.valueOf(z));
        this._isCarrierMerged = MutableStateFlow;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow, tableLogBuffer, "", "isCarrierMerged", z), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.valueOf(z));
        Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.FullMobileConnectionRepository$mobileRepo$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FullMobileConnectionRepository fullMobileConnectionRepository = FullMobileConnectionRepository.this;
                MobileConnectionRepositoryImpl.Factory factory3 = fullMobileConnectionRepository.mobileRepoFactory;
                Flow flow = mobileConnectionsRepositoryImpl$subscriptionModelForSubId$$inlined$map$1;
                Context context = factory3.context;
                ConnectivityManager connectivityManager = factory3.connectivityManager;
                TelephonyManager telephonyManager = factory3.telephonyManager;
                int i2 = fullMobileConnectionRepository.subId;
                return new MobileConnectionRepositoryImpl(i2, context, flow, fullMobileConnectionRepository.defaultNetworkName, fullMobileConnectionRepository.networkNameSeparator, connectivityManager, telephonyManager.createForSubscriptionId(i2), factory3.carrierConfigRepository.getOrCreateConfigForSubId(i2), factory3.broadcastDispatcher, factory3.mobileMappingsProxy, factory3.bgDispatcher, factory3.logger, fullMobileConnectionRepository.tableLogBuffer, factory3.flags, factory3.scope);
            }
        });
        this.mobileRepo$delegate = lazy;
        Lazy lazy2 = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.prod.FullMobileConnectionRepository$carrierMergedRepo$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                FullMobileConnectionRepository fullMobileConnectionRepository = FullMobileConnectionRepository.this;
                CarrierMergedConnectionRepository.Factory factory3 = fullMobileConnectionRepository.carrierMergedRepoFactory;
                TelephonyManager telephonyManager = factory3.telephonyManager;
                int i2 = fullMobileConnectionRepository.subId;
                TelephonyManager createForSubscriptionId = telephonyManager.createForSubscriptionId(i2);
                return new CarrierMergedConnectionRepository(i2, fullMobileConnectionRepository.tableLogBuffer, createForSubscriptionId, factory3.bgContext, factory3.scope, factory3.wifiRepository);
            }
        });
        this.carrierMergedRepo$delegate = lazy2;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.mapLatest(new FullMobileConnectionRepository$activeRepo$1$1(this, null), stateIn), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), z ? (MobileConnectionRepository) lazy2.getValue() : (MobileConnectionRepository) lazy.getValue());
        this.activeRepo = stateIn2;
        this.carrierId = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getCarrierId().getValue());
        this.cdmaRoaming = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$2(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getCdmaRoaming().getValue());
        this.isEmergencyOnly = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$3(3, null)), tableLogBuffer, "", "emergencyOnly", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).isEmergencyOnly().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isEmergencyOnly().getValue());
        this.isRoaming = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$4(3, null)), tableLogBuffer, "", "roaming", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).isRoaming().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isRoaming().getValue());
        this.operatorAlphaShort = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$5(3, null)), tableLogBuffer, "", "operatorName", (String) ((MobileConnectionRepository) stateIn2.getValue()).getOperatorAlphaShort().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getOperatorAlphaShort().getValue());
        this.isInService = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$6(3, null)), tableLogBuffer, "", "isInService", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).isInService().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isInService().getValue());
        this.isNonTerrestrial = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$7(3, null)), tableLogBuffer, "", "isNtn", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).isNonTerrestrial().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isNonTerrestrial().getValue());
        this.isGsm = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$8(3, null)), tableLogBuffer, "", "isGsm", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).isGsm().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isGsm().getValue());
        this.cdmaLevel = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$9(3, null)), tableLogBuffer, "", "cdmaLevel", ((Number) ((MobileConnectionRepository) stateIn2.getValue()).getCdmaLevel().getValue()).intValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getCdmaLevel().getValue());
        this.primaryLevel = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$10(3, null)), tableLogBuffer, "", "primaryLevel", ((Number) ((MobileConnectionRepository) stateIn2.getValue()).getPrimaryLevel().getValue()).intValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getPrimaryLevel().getValue());
        this.dataConnectionState = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$11(3, null)), tableLogBuffer, "", (Diffable) ((MobileConnectionRepository) stateIn2.getValue()).getDataConnectionState().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getDataConnectionState().getValue());
        this.dataActivityDirection = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$12(3, null)), tableLogBuffer, "", (Diffable) ((MobileConnectionRepository) stateIn2.getValue()).getDataActivityDirection().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getDataActivityDirection().getValue());
        this.carrierNetworkChangeActive = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$13(3, null)), tableLogBuffer, "", "carrierNetworkChangeActive", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).getCarrierNetworkChangeActive().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getCarrierNetworkChangeActive().getValue());
        this.resolvedNetworkType = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$14(3, null)), tableLogBuffer, "", (Diffable) ((MobileConnectionRepository) stateIn2.getValue()).getResolvedNetworkType().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getResolvedNetworkType().getValue());
        this.dataEnabled = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$15(3, null)), tableLogBuffer, "", "dataEnabled", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).getDataEnabled().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getDataEnabled().getValue());
        this.inflateSignalStrength = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$16(3, null)), tableLogBuffer, "", "inflate", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).getInflateSignalStrength().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getInflateSignalStrength().getValue());
        this.allowNetworkSliceIndicator = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$17(3, null)), tableLogBuffer, "", "allowSlice", ((Boolean) ((MobileConnectionRepository) stateIn2.getValue()).getAllowNetworkSliceIndicator().getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getAllowNetworkSliceIndicator().getValue());
        this.numberOfLevels = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$18(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getNumberOfLevels().getValue());
        this.networkName = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$19(3, null)), tableLogBuffer, "intent", (Diffable) ((MobileConnectionRepository) stateIn2.getValue()).getNetworkName().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getNetworkName().getValue());
        this.carrierName = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$20(3, null)), tableLogBuffer, "sub", (Diffable) ((MobileConnectionRepository) stateIn2.getValue()).getCarrierName().getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getCarrierName().getValue());
        this.isAllowedDuringAirplaneMode = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$21(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).isAllowedDuringAirplaneMode().getValue());
        this.hasPrioritizedNetworkCapabilities = FlowKt.stateIn(FlowKt.transformLatest(stateIn2, new FullMobileConnectionRepository$special$$inlined$flatMapLatest$22(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((MobileConnectionRepository) stateIn2.getValue()).getHasPrioritizedNetworkCapabilities().getValue());
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getAllowNetworkSliceIndicator() {
        return this.allowNetworkSliceIndicator;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierId() {
        return this.carrierId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierName() {
        return this.carrierName;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCarrierNetworkChangeActive() {
        return this.carrierNetworkChangeActive;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCdmaLevel() {
        return this.cdmaLevel;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getCdmaRoaming() {
        return this.cdmaRoaming;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataActivityDirection() {
        return this.dataActivityDirection;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataConnectionState() {
        return this.dataConnectionState;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getDataEnabled() {
        return this.dataEnabled;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getHasPrioritizedNetworkCapabilities() {
        return this.hasPrioritizedNetworkCapabilities;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getInflateSignalStrength() {
        return this.inflateSignalStrength;
    }

    public final boolean getIsCarrierMerged() {
        return ((Boolean) this._isCarrierMerged.getValue()).booleanValue();
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getNetworkName() {
        return this.networkName;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getNumberOfLevels() {
        return this.numberOfLevels;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getOperatorAlphaShort() {
        return this.operatorAlphaShort;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getPrimaryLevel() {
        return this.primaryLevel;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow getResolvedNetworkType() {
        return this.resolvedNetworkType;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final TableLogBuffer getTableLogBuffer() {
        return this.tableLogBuffer;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isAllowedDuringAirplaneMode() {
        return this.isAllowedDuringAirplaneMode;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isEmergencyOnly() {
        return this.isEmergencyOnly;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isGsm() {
        return this.isGsm;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final Object isInEcmMode(Continuation continuation) {
        return ((MobileConnectionRepository) ((StateFlowImpl) this.activeRepo.$$delegate_0).getValue()).isInEcmMode(continuation);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isInService() {
        return this.isInService;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isNonTerrestrial() {
        return this.isNonTerrestrial;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository
    public final StateFlow isRoaming() {
        return this.isRoaming;
    }

    public static /* synthetic */ void getActiveRepo$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
    }
}
