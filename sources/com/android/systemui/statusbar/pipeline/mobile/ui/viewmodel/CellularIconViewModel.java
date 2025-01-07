package com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel;

import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.airplane.domain.interactor.AirplaneModeInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractor;
import com.android.systemui.statusbar.pipeline.mobile.domain.interactor.MobileIconInteractorImpl;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class CellularIconViewModel implements MobileIconViewModelCommon {
    public final ReadonlyStateFlow activityContainerVisible;
    public final ReadonlyStateFlow activityInVisible;
    public final ReadonlyStateFlow activityOutVisible;
    public final ReadonlyStateFlow contentDescription;
    public final StateFlow icon;
    public final ReadonlyStateFlow isVisible;
    public final ReadonlyStateFlow networkTypeBackground;
    public final ReadonlyStateFlow networkTypeIcon;
    public final ReadonlyStateFlow roaming;
    public final ReadonlyStateFlow showNetworkTypeIcon;
    public final int subscriptionId;

    public CellularIconViewModel(int i, MobileIconInteractor mobileIconInteractor, AirplaneModeInteractor airplaneModeInteractor, ConnectivityConstants connectivityConstants, FeatureFlagsClassic featureFlagsClassic, CoroutineScope coroutineScope) {
        Flow combine;
        Flow flow;
        this.subscriptionId = i;
        if (connectivityConstants.hasDataCapabilities) {
            MobileIconInteractorImpl mobileIconInteractorImpl = (MobileIconInteractorImpl) mobileIconInteractor;
            combine = FlowKt.combine(airplaneModeInteractor.isAirplaneMode, mobileIconInteractorImpl.isAllowedDuringAirplaneMode, mobileIconInteractorImpl.isForceHidden, new CellularIconViewModel$isVisible$1(4, null));
        } else {
            combine = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
        }
        Flow distinctUntilChanged = FlowKt.distinctUntilChanged(combine);
        MobileIconInteractorImpl mobileIconInteractorImpl2 = (MobileIconInteractorImpl) mobileIconInteractor;
        TableLogBuffer tableLogBuffer = mobileIconInteractorImpl2.tableLogBuffer;
        SafeFlow logDiffsForTable = DiffableKt.logDiffsForTable(distinctUntilChanged, tableLogBuffer, "", "visible", false);
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.isVisible = FlowKt.stateIn(logDiffsForTable, coroutineScope, WhileSubscribed$default, bool);
        final ReadonlyStateFlow readonlyStateFlow = mobileIconInteractorImpl2.signalLevelIcon;
        this.icon = readonlyStateFlow;
        final int i2 = 0;
        this.contentDescription = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L70
                    L27:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r9)
                        com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel r8 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel) r8
                        boolean r9 = r8 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular
                        r2 = 0
                        if (r9 != 0) goto L3a
                        goto L65
                    L3a:
                        r9 = r8
                        com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel$Cellular r9 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular) r9
                        int r9 = r9.level
                        com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel$Cellular r8 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular) r8
                        int r8 = r8.numberOfLevels
                        r4 = 0
                        r5 = 5
                        if (r8 != r5) goto L52
                        r8 = 4
                        if (r9 > r8) goto L5e
                        if (r9 >= 0) goto L4d
                        goto L5e
                    L4d:
                        int[] r8 = com.android.settingslib.AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH
                        r4 = r8[r9]
                        goto L5e
                    L52:
                        r6 = 6
                        if (r8 != r6) goto L5e
                        if (r9 > r5) goto L5e
                        if (r9 >= 0) goto L5a
                        goto L5e
                    L5a:
                        int[] r8 = com.android.settingslib.AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH_INFLATED
                        r4 = r8[r9]
                    L5e:
                        if (r4 == 0) goto L65
                        com.android.systemui.common.shared.model.ContentDescription$Resource r2 = new com.android.systemui.common.shared.model.ContentDescription$Resource
                        r2.<init>(r4)
                    L65:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        java.lang.Object r7 = r7.emit(r2, r0)
                        if (r7 != r1) goto L70
                        return r1
                    L70:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = readonlyStateFlow.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = readonlyStateFlow.collect(new CellularIconViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.networkTypeIcon = FlowKt.stateIn(FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(mobileIconInteractorImpl2.networkTypeIconGroup, FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(FlowKt.combine(mobileIconInteractorImpl2.isDataConnected, mobileIconInteractorImpl2.isDataEnabled, mobileIconInteractorImpl2.alwaysShowDataRatIcon, mobileIconInteractorImpl2.mobileIsDefault, mobileIconInteractorImpl2.carrierNetworkChangeActive, new CellularIconViewModel$showNetworkTypeIcon$1(null))), tableLogBuffer, "", "showNetworkTypeIcon", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool), new CellularIconViewModel$networkTypeIcon$1(3, null))), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        if (((FeatureFlagsClassicRelease) featureFlagsClassic).isEnabled(Flags.NEW_NETWORK_SLICE_UI)) {
            final ReadonlyStateFlow readonlyStateFlow2 = mobileIconInteractorImpl2.showSliceAttribution;
            final int i3 = 1;
            flow = new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2, reason: invalid class name */
                public final class AnonymousClass2 implements FlowCollector {
                    public final /* synthetic */ FlowCollector $this_unsafeFlow;

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                            boolean r0 = r9 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                            if (r0 == 0) goto L13
                            r0 = r9
                            com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                            int r1 = r0.label
                            r2 = -2147483648(0xffffffff80000000, float:-0.0)
                            r3 = r1 & r2
                            if (r3 == 0) goto L13
                            int r1 = r1 - r2
                            r0.label = r1
                            goto L18
                        L13:
                            com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1$2$1
                            r0.<init>(r9)
                        L18:
                            java.lang.Object r9 = r0.result
                            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                            int r2 = r0.label
                            r3 = 1
                            if (r2 == 0) goto L2f
                            if (r2 != r3) goto L27
                            kotlin.ResultKt.throwOnFailure(r9)
                            goto L70
                        L27:
                            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                            r7.<init>(r8)
                            throw r7
                        L2f:
                            kotlin.ResultKt.throwOnFailure(r9)
                            com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel r8 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel) r8
                            boolean r9 = r8 instanceof com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular
                            r2 = 0
                            if (r9 != 0) goto L3a
                            goto L65
                        L3a:
                            r9 = r8
                            com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel$Cellular r9 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular) r9
                            int r9 = r9.level
                            com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel$Cellular r8 = (com.android.systemui.statusbar.pipeline.mobile.domain.model.SignalIconModel.Cellular) r8
                            int r8 = r8.numberOfLevels
                            r4 = 0
                            r5 = 5
                            if (r8 != r5) goto L52
                            r8 = 4
                            if (r9 > r8) goto L5e
                            if (r9 >= 0) goto L4d
                            goto L5e
                        L4d:
                            int[] r8 = com.android.settingslib.AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH
                            r4 = r8[r9]
                            goto L5e
                        L52:
                            r6 = 6
                            if (r8 != r6) goto L5e
                            if (r9 > r5) goto L5e
                            if (r9 >= 0) goto L5a
                            goto L5e
                        L5a:
                            int[] r8 = com.android.settingslib.AccessibilityContentDescriptions.PHONE_SIGNAL_STRENGTH_INFLATED
                            r4 = r8[r9]
                        L5e:
                            if (r4 == 0) goto L65
                            com.android.systemui.common.shared.model.ContentDescription$Resource r2 = new com.android.systemui.common.shared.model.ContentDescription$Resource
                            r2.<init>(r4)
                        L65:
                            r0.label = r3
                            kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                            java.lang.Object r7 = r7.emit(r2, r0)
                            if (r7 != r1) goto L70
                            return r1
                        L70:
                            kotlin.Unit r7 = kotlin.Unit.INSTANCE
                            return r7
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                    }
                }

                @Override // kotlinx.coroutines.flow.Flow
                public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                    switch (i3) {
                        case 0:
                            Object collect = readonlyStateFlow2.collect(new AnonymousClass2(flowCollector), continuation);
                            if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                        default:
                            Object collect2 = readonlyStateFlow2.collect(new CellularIconViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                            if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                                break;
                            }
                            break;
                    }
                    return Unit.INSTANCE;
                }
            };
        } else {
            flow = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
        }
        this.networkTypeBackground = FlowKt.stateIn(flow, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        this.roaming = FlowKt.stateIn(DiffableKt.logDiffsForTable((Flow) mobileIconInteractorImpl2.isRoaming, tableLogBuffer, "", "roaming", false), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = !connectivityConstants.shouldShowActivityConfig ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null) : mobileIconInteractorImpl2.activity;
        final int i4 = 0;
        this.activityInVisible = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1
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
                        com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel r5 = (com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel) r5
                        if (r5 == 0) goto L39
                        boolean r5 = r5.hasActivityIn
                        goto L3a
                    L39:
                        r5 = 0
                    L3a:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final int i5 = 1;
        this.activityOutVisible = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1
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
                        com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel r5 = (com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel) r5
                        if (r5 == 0) goto L39
                        boolean r5 = r5.hasActivityIn
                        goto L3a
                    L39:
                        r5 = 0
                    L3a:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i5) {
                    case 0:
                        Object collect = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final int i6 = 2;
        this.activityContainerVisible = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = (com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1 r0 = new com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3$2$1
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
                        com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel r5 = (com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel) r5
                        if (r5 == 0) goto L39
                        boolean r5 = r5.hasActivityIn
                        goto L3a
                    L39:
                        r5 = 0
                    L3a:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L49
                        return r1
                    L49:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.CellularIconViewModel$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i6) {
                    case 0:
                        Object collect = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    case 1:
                        Object collect2 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$4$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect3 = flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2.collect(new CellularIconViewModel$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect3 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityContainerVisible() {
        return this.activityContainerVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityInVisible() {
        return this.activityInVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getActivityOutVisible() {
        return this.activityOutVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getContentDescription() {
        return this.contentDescription;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getIcon() {
        return this.icon;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow getNetworkTypeBackground() {
        return this.networkTypeBackground;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getNetworkTypeIcon() {
        return this.networkTypeIcon;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final Flow getRoaming() {
        return this.roaming;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final int getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.android.systemui.statusbar.pipeline.mobile.ui.viewmodel.MobileIconViewModelCommon
    public final StateFlow isVisible() {
        return this.isVisible;
    }
}
