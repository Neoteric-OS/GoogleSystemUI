package com.android.systemui.statusbar.pipeline.mobile.data.repository.demo;

import com.android.systemui.log.table.Diffable;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.model.ResolvedNetworkType;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DemoMobileConnectionRepository implements MobileConnectionRepository {
    public final StateFlowImpl _carrierId;
    public final StateFlowImpl _carrierNetworkChangeActive;
    public final StateFlowImpl _cdmaLevel;
    public final StateFlowImpl _dataActivityDirection;
    public final StateFlowImpl _dataConnectionState;
    public final StateFlowImpl _inflateSignalStrength;
    public final StateFlowImpl _isEmergencyOnly;
    public final StateFlowImpl _isGsm;
    public final StateFlowImpl _isInService;
    public final StateFlowImpl _isNonTerrestrial;
    public final StateFlowImpl _isRoaming;
    public final StateFlowImpl _operatorAlphaShort;
    public final StateFlowImpl _primaryLevel;
    public final StateFlowImpl _resolvedNetworkType;
    public final StateFlowImpl allowNetworkSliceIndicator;
    public final ReadonlyStateFlow carrierId;
    public final StateFlowImpl carrierName;
    public final ReadonlyStateFlow carrierNetworkChangeActive;
    public final ReadonlyStateFlow cdmaLevel;
    public final StateFlowImpl cdmaRoaming;
    public final ReadonlyStateFlow dataActivityDirection;
    public final ReadonlyStateFlow dataConnectionState;
    public final StateFlowImpl dataEnabled;
    public final StateFlowImpl hasPrioritizedNetworkCapabilities;
    public final ReadonlyStateFlow inflateSignalStrength;
    public final StateFlowImpl isAllowedDuringAirplaneMode;
    public final ReadonlyStateFlow isEmergencyOnly;
    public final ReadonlyStateFlow isGsm;
    public final ReadonlyStateFlow isInService;
    public final ReadonlyStateFlow isNonTerrestrial;
    public final ReadonlyStateFlow isRoaming;
    public final StateFlowImpl networkName;
    public final ReadonlyStateFlow numberOfLevels;
    public final ReadonlyStateFlow operatorAlphaShort;
    public final ReadonlyStateFlow primaryLevel;
    public final ReadonlyStateFlow resolvedNetworkType;
    public final TableLogBuffer tableLogBuffer;

    public DemoMobileConnectionRepository(TableLogBuffer tableLogBuffer, CoroutineScope coroutineScope) {
        this.tableLogBuffer = tableLogBuffer;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(-1);
        this._carrierId = MutableStateFlow;
        this.carrierId = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow, tableLogBuffer, "", "carrierId", ((Number) MutableStateFlow.getValue()).intValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow.getValue());
        Boolean bool = Boolean.FALSE;
        final StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(bool);
        this._inflateSignalStrength = MutableStateFlow2;
        this.inflateSignalStrength = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow2, tableLogBuffer, "", "inflate", ((Boolean) MutableStateFlow2.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow2.getValue());
        Boolean bool2 = Boolean.TRUE;
        this.allowNetworkSliceIndicator = StateFlowKt.MutableStateFlow(bool2);
        StateFlowImpl MutableStateFlow3 = StateFlowKt.MutableStateFlow(bool);
        this._isEmergencyOnly = MutableStateFlow3;
        this.isEmergencyOnly = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow3, tableLogBuffer, "", "emergencyOnly", ((Boolean) MutableStateFlow3.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow3.getValue());
        StateFlowImpl MutableStateFlow4 = StateFlowKt.MutableStateFlow(bool);
        this._isRoaming = MutableStateFlow4;
        this.isRoaming = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow4, tableLogBuffer, "", "roaming", ((Boolean) MutableStateFlow4.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow4.getValue());
        StateFlowImpl MutableStateFlow5 = StateFlowKt.MutableStateFlow(null);
        this._operatorAlphaShort = MutableStateFlow5;
        this.operatorAlphaShort = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow5, tableLogBuffer, "", "operatorName", (String) MutableStateFlow5.getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow5.getValue());
        StateFlowImpl MutableStateFlow6 = StateFlowKt.MutableStateFlow(bool);
        this._isInService = MutableStateFlow6;
        this.isInService = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow6, tableLogBuffer, "", "isInService", ((Boolean) MutableStateFlow6.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow6.getValue());
        StateFlowImpl MutableStateFlow7 = StateFlowKt.MutableStateFlow(bool);
        this._isNonTerrestrial = MutableStateFlow7;
        this.isNonTerrestrial = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow7, tableLogBuffer, "", "isNtn", ((Boolean) MutableStateFlow7.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow7.getValue());
        StateFlowImpl MutableStateFlow8 = StateFlowKt.MutableStateFlow(bool);
        this._isGsm = MutableStateFlow8;
        this.isGsm = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow8, tableLogBuffer, "", "isGsm", ((Boolean) MutableStateFlow8.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow8.getValue());
        StateFlowImpl MutableStateFlow9 = StateFlowKt.MutableStateFlow(0);
        this._cdmaLevel = MutableStateFlow9;
        this.cdmaLevel = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow9, tableLogBuffer, "", "cdmaLevel", ((Number) MutableStateFlow9.getValue()).intValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow9.getValue());
        StateFlowImpl MutableStateFlow10 = StateFlowKt.MutableStateFlow(0);
        this._primaryLevel = MutableStateFlow10;
        this.primaryLevel = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow10, tableLogBuffer, "", "primaryLevel", ((Number) MutableStateFlow10.getValue()).intValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow10.getValue());
        StateFlowImpl MutableStateFlow11 = StateFlowKt.MutableStateFlow(DataConnectionState.Disconnected);
        this._dataConnectionState = MutableStateFlow11;
        this.dataConnectionState = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow11, tableLogBuffer, "", (Diffable) MutableStateFlow11.getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow11.getValue());
        StateFlowImpl MutableStateFlow12 = StateFlowKt.MutableStateFlow(new DataActivityModel(false, false));
        this._dataActivityDirection = MutableStateFlow12;
        this.dataActivityDirection = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow12, tableLogBuffer, "", (Diffable) MutableStateFlow12.getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow12.getValue());
        StateFlowImpl MutableStateFlow13 = StateFlowKt.MutableStateFlow(bool);
        this._carrierNetworkChangeActive = MutableStateFlow13;
        this.carrierNetworkChangeActive = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow13, tableLogBuffer, "", "carrierNetworkChangeActive", ((Boolean) MutableStateFlow13.getValue()).booleanValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow13.getValue());
        StateFlowImpl MutableStateFlow14 = StateFlowKt.MutableStateFlow(ResolvedNetworkType.UnknownNetworkType.INSTANCE);
        this._resolvedNetworkType = MutableStateFlow14;
        this.resolvedNetworkType = FlowKt.stateIn(DiffableKt.logDiffsForTable(MutableStateFlow14, tableLogBuffer, "", (Diffable) MutableStateFlow14.getValue()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MutableStateFlow14.getValue());
        Flow flow = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L5a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Boolean r5 = (java.lang.Boolean) r5
                        boolean r5 = r5.booleanValue()
                        if (r5 == 0) goto L43
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository$Companion r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion
                        r5.getClass()
                        int r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS
                        int r5 = r5 + r3
                        goto L4a
                    L43:
                        com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository$Companion r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion
                        r5.getClass()
                        int r5 = com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS
                    L4a:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L5a
                        return r1
                    L5a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.data.repository.demo.DemoMobileConnectionRepository$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                StateFlowImpl.this.collect(new AnonymousClass2(flowCollector), continuation);
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        MobileConnectionRepository.Companion.getClass();
        this.numberOfLevels = FlowKt.stateIn(flow, coroutineScope, WhileSubscribed$default, Integer.valueOf(MobileConnectionRepository.Companion.DEFAULT_NUM_LEVELS));
        this.dataEnabled = StateFlowKt.MutableStateFlow(bool2);
        this.cdmaRoaming = StateFlowKt.MutableStateFlow(bool);
        this.networkName = StateFlowKt.MutableStateFlow(new NetworkNameModel.IntentDerived("Demo Carrier"));
        this.carrierName = StateFlowKt.MutableStateFlow(new NetworkNameModel.SubscriptionDerived("Demo Carrier"));
        this.isAllowedDuringAirplaneMode = StateFlowKt.MutableStateFlow(bool);
        this.hasPrioritizedNetworkCapabilities = StateFlowKt.MutableStateFlow(bool);
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
        return Boolean.FALSE;
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
}
