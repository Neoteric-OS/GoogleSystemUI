package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import com.android.settingslib.SignalIcon$MobileIconGroup;
import com.android.settingslib.mobile.MobileIconCarrierIdOverridesImpl;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.model.NetworkNameModel;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionRepository;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.NetworkTypeIconModel;
import com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileIconInteractorImpl implements MobileIconInteractor {
    public final StateFlow activity;
    public final ReadonlyStateFlow alwaysShowDataRatIcon;
    public final ReadonlyStateFlow carrierIdIconOverrideExists;
    public final MobileIconCarrierIdOverridesImpl carrierIdOverrides;
    public final ReadonlyStateFlow carrierName;
    public final StateFlow carrierNetworkChangeActive;
    public final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 cellularIcon;
    public final ReadonlyStateFlow cellularShownLevel;
    public final Context context;
    public final ReadonlyStateFlow defaultNetworkType;
    public final StateFlow isAllowedDuringAirplaneMode;
    public final ReadonlyStateFlow isDataConnected;
    public final StateFlow isDataEnabled;
    public final StateFlow isEmergencyOnly;
    public final ReadonlyStateFlow isForceHidden;
    public final StateFlow isInService;
    public final StateFlow isNonTerrestrial;
    public final ReadonlyStateFlow isRoaming;
    public final ReadonlyStateFlow isSingleCarrier;
    public final ReadonlyStateFlow level;
    public final ReadonlyStateFlow mobileIsDefault;
    public final ReadonlyStateFlow networkName;
    public final ReadonlyStateFlow networkTypeIconGroup;
    public final MobileIconInteractorImpl$special$$inlined$map$2 satelliteIcon;
    public final ReadonlyStateFlow satelliteShownLevel;
    public final ReadonlyStateFlow showExclamationMark;
    public final ReadonlyStateFlow showSliceAttribution;
    public final ReadonlyStateFlow signalLevelIcon;
    public final TableLogBuffer tableLogBuffer;

    /* JADX WARN: Type inference failed for: r7v6, types: [com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2] */
    public MobileIconInteractorImpl(CoroutineScope coroutineScope, ReadonlyStateFlow readonlyStateFlow, ReadonlyStateFlow readonlyStateFlow2, ReadonlyStateFlow readonlyStateFlow3, ReadonlyStateFlow readonlyStateFlow4, ReadonlyStateFlow readonlyStateFlow5, ReadonlyStateFlow readonlyStateFlow6, ReadonlyStateFlow readonlyStateFlow7, ReadonlyStateFlow readonlyStateFlow8, ReadonlyStateFlow readonlyStateFlow9, MobileConnectionRepository mobileConnectionRepository, Context context) {
        MobileIconCarrierIdOverridesImpl mobileIconCarrierIdOverridesImpl = new MobileIconCarrierIdOverridesImpl();
        this.alwaysShowDataRatIcon = readonlyStateFlow2;
        this.isSingleCarrier = readonlyStateFlow4;
        this.mobileIsDefault = readonlyStateFlow5;
        this.isForceHidden = readonlyStateFlow9;
        this.context = context;
        this.carrierIdOverrides = mobileIconCarrierIdOverridesImpl;
        TableLogBuffer tableLogBuffer = mobileConnectionRepository.getTableLogBuffer();
        this.tableLogBuffer = tableLogBuffer;
        this.activity = mobileConnectionRepository.getDataActivityDirection();
        this.isDataEnabled = mobileConnectionRepository.getDataEnabled();
        StateFlow carrierNetworkChangeActive = mobileConnectionRepository.getCarrierNetworkChangeActive();
        this.carrierNetworkChangeActive = carrierNetworkChangeActive;
        final StateFlow carrierId = mobileConnectionRepository.getCarrierId();
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MobileIconInteractorImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MobileIconInteractorImpl mobileIconInteractorImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mobileIconInteractorImpl;
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L58
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl r6 = r4.this$0
                        com.android.settingslib.mobile.MobileIconCarrierIdOverridesImpl r6 = r6.carrierIdOverrides
                        r6.getClass()
                        java.util.Map r6 = com.android.settingslib.mobile.MobileIconCarrierIdOverridesImpl.MAPPING
                        java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
                        boolean r5 = r6.containsKey(r5)
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L58
                        return r1
                    L58:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(distinctUntilChanged, coroutineScope, WhileSubscribed$default, bool);
        this.networkName = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileConnectionRepository.getOperatorAlphaShort(), mobileConnectionRepository.getNetworkName(), new MobileIconInteractorImpl$networkName$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), mobileConnectionRepository.getNetworkName().getValue());
        this.carrierName = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileConnectionRepository.getOperatorAlphaShort(), mobileConnectionRepository.getCarrierName(), new MobileIconInteractorImpl$carrierName$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ((NetworkNameModel) mobileConnectionRepository.getCarrierName().getValue()).getName());
        this.networkTypeIconGroup = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(FlowKt.stateIn(FlowKt.combine(mobileConnectionRepository.getResolvedNetworkType(), readonlyStateFlow6, readonlyStateFlow7, new MobileIconInteractorImpl$defaultNetworkType$1(4, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), readonlyStateFlow7.getValue()), stateIn, new MobileIconInteractorImpl$networkTypeIconGroup$1(this, mobileConnectionRepository, null))), tableLogBuffer, "", new NetworkTypeIconModel.DefaultIcon((SignalIcon$MobileIconGroup) readonlyStateFlow7.getValue())), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), new NetworkTypeIconModel.DefaultIcon((SignalIcon$MobileIconGroup) readonlyStateFlow7.getValue()));
        this.showSliceAttribution = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileConnectionRepository.getAllowNetworkSliceIndicator(), mobileConnectionRepository.getHasPrioritizedNetworkCapabilities(), new MobileIconInteractorImpl$showSliceAttribution$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        StateFlow isNonTerrestrial = mobileConnectionRepository.isNonTerrestrial();
        this.isNonTerrestrial = isNonTerrestrial;
        this.isRoaming = FlowKt.stateIn(FlowKt.combine(mobileConnectionRepository.getCarrierNetworkChangeActive(), mobileConnectionRepository.isGsm(), mobileConnectionRepository.isRoaming(), mobileConnectionRepository.getCdmaRoaming(), new MobileIconInteractorImpl$isRoaming$1(5, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(FlowKt.combine(mobileConnectionRepository.isGsm(), mobileConnectionRepository.getPrimaryLevel(), mobileConnectionRepository.getCdmaLevel(), readonlyStateFlow3, new MobileIconInteractorImpl$level$1(5, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        StateFlow numberOfLevels = mobileConnectionRepository.getNumberOfLevels();
        final StateFlow dataConnectionState = mobileConnectionRepository.getDataConnectionState();
        final int i = 0;
        this.isDataConnected = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState r5 = (com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState) r5
                        com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState r6 = com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState.Connected
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = dataConnectionState.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) dataConnectionState).collect(new MobileIconInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        StateFlow isInService = mobileConnectionRepository.isInService();
        this.isInService = isInService;
        this.isEmergencyOnly = mobileConnectionRepository.isEmergencyOnly();
        this.isAllowedDuringAirplaneMode = mobileConnectionRepository.isAllowedDuringAirplaneMode();
        ReadonlyStateFlow stateIn3 = FlowKt.stateIn(FlowKt.combine(readonlyStateFlow, readonlyStateFlow8, isInService, new MobileIconInteractorImpl$showExclamationMark$1(4, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), Boolean.TRUE);
        ReadonlyStateFlow stateIn4 = FlowKt.stateIn(FlowKt.combine(stateIn2, isInService, mobileConnectionRepository.getInflateSignalStrength(), new MobileIconInteractorImpl$cellularShownLevel$1(4, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        final ReadonlyStateFlow stateIn5 = FlowKt.stateIn(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(stateIn2, isInService, new MobileIconInteractorImpl$satelliteShownLevel$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        this.cellularIcon = FlowKt.combine(stateIn4, numberOfLevels, stateIn3, carrierNetworkChangeActive, new MobileIconInteractorImpl$cellularIcon$1(5, null));
        final int i2 = 1;
        this.satelliteIcon = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4a
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState r5 = (com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState) r5
                        com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState r6 = com.android.systemui.statusbar.pipeline.mobile.data.model.DataConnectionState.Connected
                        if (r5 != r6) goto L3a
                        r5 = r3
                        goto L3b
                    L3a:
                        r5 = 0
                    L3b:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = stateIn5.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    default:
                        ((ReadonlyStateFlow) stateIn5).collect(new MobileIconInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                }
            }
        };
        SignalIconModel.Cellular cellular = new SignalIconModel.Cellular(((Number) stateIn4.getValue()).intValue(), ((Number) numberOfLevels.getValue()).intValue(), ((Boolean) stateIn3.getValue()).booleanValue(), ((Boolean) carrierNetworkChangeActive.getValue()).booleanValue());
        this.signalLevelIcon = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.transformLatest(isNonTerrestrial, new MobileIconInteractorImpl$signalLevelIcon$lambda$4$$inlined$flatMapLatest$1(null, this))), tableLogBuffer, "icon", cellular), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), cellular);
    }
}
