package com.android.systemui.statusbar.pipeline.mobile.domain.interactor;

import android.content.Context;
import com.android.settingslib.mobile.TelephonyIcons;
import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.mobile.data.repository.MobileConnectionsRepository;
import com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl;
import com.android.systemui.util.CarrierConfigTracker;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MobileIconsInteractorImpl {
    public final ReadonlyStateFlow activeDataConnectionHasDataEnabled;
    public final ReadonlyStateFlow activeDataIconInteractor;
    public final ReadonlyStateFlow alwaysShowDataRatIcon;
    public final ReadonlyStateFlow alwaysUseCdmaLevel;
    public final CarrierConfigTracker carrierConfigTracker;
    public final Context context;
    public final ReadonlyStateFlow defaultMobileIconGroup;
    public final ReadonlyStateFlow defaultMobileIconMapping;
    public final FeatureFlagsClassic featureFlagsClassic;
    public final ReadonlyStateFlow filteredSubscriptions;
    public final ReadonlyStateFlow forcingCellularValidation;
    public final ReadonlyStateFlow icons;
    public final ReadonlyStateFlow isDefaultConnectionFailed;
    public final StateFlow isDeviceInEmergencyCallsOnlyMode;
    public final ReadonlyStateFlow isForceHidden;
    public final ReadonlyStateFlow isSingleCarrier;
    public final MobileConnectionsRepository mobileConnectionsRepo;
    public final ReadonlyStateFlow mobileIsDefault;
    public final Map reuseCache = new LinkedHashMap();
    public final CoroutineScope scope;
    public final StateFlow unfilteredSubscriptions;

    public MobileIconsInteractorImpl(MobileConnectionsRepository mobileConnectionsRepository, CarrierConfigTracker carrierConfigTracker, TableLogBuffer tableLogBuffer, ConnectivityRepositoryImpl connectivityRepositoryImpl, CoroutineScope coroutineScope, Context context, FeatureFlagsClassic featureFlagsClassic) {
        this.mobileConnectionsRepo = mobileConnectionsRepository;
        this.carrierConfigTracker = carrierConfigTracker;
        this.scope = coroutineScope;
        this.context = context;
        this.featureFlagsClassic = featureFlagsClassic;
        SafeFlow logDiffsForTable = DiffableKt.logDiffsForTable((Flow) new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileConnectionsRepository.getMobileIsDefault(), mobileConnectionsRepository.getHasCarrierMergedConnection(), new MobileIconsInteractorImpl$mobileIsDefault$1(3, null)), tableLogBuffer, "Intr", "mobileIsDefault", false);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        ReadonlyStateFlow stateIn = FlowKt.stateIn(logDiffsForTable, coroutineScope, WhileSubscribed$default, bool);
        this.mobileIsDefault = stateIn;
        this.activeDataConnectionHasDataEnabled = FlowKt.stateIn(FlowKt.transformLatest(mobileConnectionsRepository.getActiveMobileDataRepository(), new MobileIconsInteractorImpl$special$$inlined$flatMapLatest$1(3, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.activeDataIconInteractor = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconsInteractorImpl$activeDataIconInteractor$1(this, null), mobileConnectionsRepository.getActiveMobileDataSubscriptionId()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        final StateFlow subscriptions = mobileConnectionsRepository.getSubscriptions();
        final int i = 0;
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.combine(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MobileIconsInteractorImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MobileIconsInteractorImpl mobileIconsInteractorImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mobileIconsInteractorImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L8e
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.util.List r6 = (java.util.List) r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl r7 = r5.this$0
                        r7.getClass()
                        com.android.systemui.flags.ReleasedFlag r2 = com.android.systemui.flags.Flags.FILTER_PROVISIONING_NETWORK_SUBSCRIPTIONS
                        com.android.systemui.flags.FeatureFlagsClassic r7 = r7.featureFlagsClassic
                        com.android.systemui.flags.FeatureFlagsClassicRelease r7 = (com.android.systemui.flags.FeatureFlagsClassicRelease) r7
                        boolean r7 = r7.isEnabled(r2)
                        if (r7 != 0) goto L46
                        goto L65
                    L46:
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L4f:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L64
                        java.lang.Object r2 = r6.next()
                        r4 = r2
                        com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel r4 = (com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel) r4
                        int r4 = r4.profileClass
                        if (r4 == r3) goto L4f
                        r7.add(r2)
                        goto L4f
                    L64:
                        r6 = r7
                    L65:
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L6e:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L83
                        java.lang.Object r2 = r6.next()
                        r4 = r2
                        com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel r4 = (com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel) r4
                        boolean r4 = r4.isExclusivelyNonTerrestrial
                        if (r4 != 0) goto L6e
                        r7.add(r2)
                        goto L6e
                    L83:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L8e
                        return r1
                    L8e:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((StateFlow) subscriptions).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = subscriptions.collect(new MobileIconsInteractorImpl$special$$inlined$filter$1$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), mobileConnectionsRepository.getActiveMobileDataSubscriptionId(), connectivityRepositoryImpl.vcnSubId, new MobileIconsInteractorImpl$filteredSubscriptions$1(this, null)));
        EmptyList emptyList = EmptyList.INSTANCE;
        ReadonlyStateFlow stateIn2 = FlowKt.stateIn(DiffableKt.logDiffsForTable(distinctUntilChanged, tableLogBuffer, "Intr", "filteredSubscriptions", emptyList), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), emptyList);
        this.filteredSubscriptions = stateIn2;
        this.icons = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconsInteractorImpl$icons$1(this, null), stateIn2), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), emptyList);
        final Flow activeSubChangedInGroupEvent = mobileConnectionsRepository.getActiveSubChangedInGroupEvent();
        final int i2 = 1;
        ReadonlyStateFlow stateIn3 = FlowKt.stateIn(DiffableKt.logDiffsForTable((Flow) FlowKt.transformLatest(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ MobileIconsInteractorImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, MobileIconsInteractorImpl mobileIconsInteractorImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = mobileIconsInteractorImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L8e
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        java.util.List r6 = (java.util.List) r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl r7 = r5.this$0
                        r7.getClass()
                        com.android.systemui.flags.ReleasedFlag r2 = com.android.systemui.flags.Flags.FILTER_PROVISIONING_NETWORK_SUBSCRIPTIONS
                        com.android.systemui.flags.FeatureFlagsClassic r7 = r7.featureFlagsClassic
                        com.android.systemui.flags.FeatureFlagsClassicRelease r7 = (com.android.systemui.flags.FeatureFlagsClassicRelease) r7
                        boolean r7 = r7.isEnabled(r2)
                        if (r7 != 0) goto L46
                        goto L65
                    L46:
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L4f:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L64
                        java.lang.Object r2 = r6.next()
                        r4 = r2
                        com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel r4 = (com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel) r4
                        int r4 = r4.profileClass
                        if (r4 == r3) goto L4f
                        r7.add(r2)
                        goto L4f
                    L64:
                        r6 = r7
                    L65:
                        java.util.ArrayList r7 = new java.util.ArrayList
                        r7.<init>()
                        java.util.Iterator r6 = r6.iterator()
                    L6e:
                        boolean r2 = r6.hasNext()
                        if (r2 == 0) goto L83
                        java.lang.Object r2 = r6.next()
                        r4 = r2
                        com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel r4 = (com.android.systemui.statusbar.pipeline.mobile.data.model.SubscriptionModel) r4
                        boolean r4 = r4.isExclusivelyNonTerrestrial
                        if (r4 != 0) goto L6e
                        r7.add(r2)
                        goto L6e
                    L83:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L8e
                        return r1
                    L8e:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((StateFlow) activeSubChangedInGroupEvent).collect(new AnonymousClass2(flowCollector, this), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = activeSubChangedInGroupEvent.collect(new MobileIconsInteractorImpl$special$$inlined$filter$1$2(flowCollector, this), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, new MobileIconsInteractorImpl$forcingCellularValidation$2(3, null)), tableLogBuffer, "Intr", "forcingValidation", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.defaultMobileIconMapping = FlowKt.stateIn(mobileConnectionsRepository.getDefaultMobileIconMapping(), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), MapsKt.emptyMap());
        this.alwaysShowDataRatIcon = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconsInteractorImpl$alwaysShowDataRatIcon$1(2, null), mobileConnectionsRepository.getDefaultDataSubRatConfig()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.alwaysUseCdmaLevel = FlowKt.stateIn(FlowKt.mapLatest(new MobileIconsInteractorImpl$alwaysUseCdmaLevel$1(2, null), mobileConnectionsRepository.getDefaultDataSubRatConfig()), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final StateFlow subscriptions2 = mobileConnectionsRepository.getSubscriptions();
        final int i3 = 0;
        this.isSingleCarrier = FlowKt.stateIn(DiffableKt.logDiffsForTable(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.util.List r5 = (java.util.List) r5
                        int r5 = r5.size()
                        if (r5 != r3) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        Object collect = subscriptions2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = subscriptions2.collect(new MobileIconsInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, tableLogBuffer, "Intr", "isSingleCarrier", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.defaultMobileIconGroup = FlowKt.stateIn(mobileConnectionsRepository.getDefaultMobileIconGroup(), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), TelephonyIcons.G);
        this.isDefaultConnectionFailed = FlowKt.stateIn(DiffableKt.logDiffsForTable((Flow) FlowKt.combine(stateIn, mobileConnectionsRepository.getDefaultConnectionIsValidated(), stateIn3, new MobileIconsInteractorImpl$isDefaultConnectionFailed$1(4, null)), tableLogBuffer, "Intr", "isDefaultConnectionFailed", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final ReadonlyStateFlow readonlyStateFlow = connectivityRepositoryImpl.forceHiddenSlots;
        final int i4 = 1;
        this.isForceHidden = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4c
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        java.util.List r5 = (java.util.List) r5
                        int r5 = r5.size()
                        if (r5 != r3) goto L3c
                        r5 = r3
                        goto L3d
                    L3c:
                        r5 = 0
                    L3d:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4c
                        return r1
                    L4c:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconsInteractorImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = readonlyStateFlow.collect(new MobileIconsInteractorImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.isDeviceInEmergencyCallsOnlyMode = mobileConnectionsRepository.isDeviceEmergencyCallCapable();
    }

    public final MobileIconInteractor getMobileConnectionInteractorForSubId(int i) {
        WeakReference weakReference = (WeakReference) this.reuseCache.get(Integer.valueOf(i));
        MobileIconInteractor mobileIconInteractor = weakReference != null ? (MobileIconInteractor) weakReference.get() : null;
        if (mobileIconInteractor != null) {
            return mobileIconInteractor;
        }
        MobileIconInteractorImpl mobileIconInteractorImpl = new MobileIconInteractorImpl(this.scope, this.activeDataConnectionHasDataEnabled, this.alwaysShowDataRatIcon, this.alwaysUseCdmaLevel, this.isSingleCarrier, this.mobileIsDefault, this.defaultMobileIconMapping, this.defaultMobileIconGroup, this.isDefaultConnectionFailed, this.isForceHidden, this.mobileConnectionsRepo.getRepoForSubId(i), this.context);
        this.reuseCache.put(Integer.valueOf(i), new WeakReference(mobileIconInteractorImpl));
        return mobileIconInteractorImpl;
    }
}
