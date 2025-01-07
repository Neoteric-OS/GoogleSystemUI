package com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel;

import android.content.Context;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.pipeline.airplane.ui.viewmodel.AirplaneModeViewModelImpl;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityConstants;
import com.android.systemui.statusbar.pipeline.wifi.domain.interactor.WifiInteractorImpl;
import com.android.systemui.statusbar.pipeline.wifi.shared.WifiConstants;
import com.android.systemui.statusbar.pipeline.wifi.ui.model.WifiIcon;
import java.util.function.Supplier;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedWhileSubscribed;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiViewModel implements WifiViewModelCommon {
    public final ReadonlyStateFlow activity;
    public final Context context;
    public final ReadonlyStateFlow isActivityContainerVisible;
    public final ReadonlyStateFlow isActivityInViewVisible;
    public final ReadonlyStateFlow isActivityOutViewVisible;
    public final StateFlow isAirplaneSpacerVisible;
    public final Flow isSignalSpacerVisible;
    public final ReadonlyStateFlow wifiIcon;

    public WifiViewModel(AirplaneModeViewModelImpl airplaneModeViewModelImpl, Supplier supplier, ConnectivityConstants connectivityConstants, Context context, TableLogBuffer tableLogBuffer, WifiInteractorImpl wifiInteractorImpl, CoroutineScope coroutineScope, WifiConstants wifiConstants) {
        Flow flowKt__ZipKt$combine$$inlined$unsafeFlow$1;
        this.context = context;
        FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = FlowKt.combine(wifiInteractorImpl.isEnabled, wifiInteractorImpl.isDefault, wifiInteractorImpl.isForceHidden, wifiInteractorImpl.wifiNetwork, new WifiViewModel$wifiIcon$1(this, wifiConstants, connectivityConstants, null));
        WifiIcon.Hidden hidden = WifiIcon.Hidden.INSTANCE;
        this.wifiIcon = FlowKt.stateIn(DiffableKt.logDiffsForTable(combine, tableLogBuffer, "", hidden), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), hidden);
        if (connectivityConstants.shouldShowActivityConfig) {
            flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(wifiInteractorImpl.activity, wifiInteractorImpl.ssid, new WifiViewModel$activity$1(3, null));
        } else {
            flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(null);
        }
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(flowKt__ZipKt$combine$$inlined$unsafeFlow$1, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        final int i = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedWhileSubscribed WhileSubscribed$default = SharingStarted.Companion.WhileSubscribed$default(3);
        Boolean bool = Boolean.FALSE;
        this.isActivityInViewVisible = FlowKt.stateIn(flow, coroutineScope, WhileSubscribed$default, bool);
        final int i2 = 1;
        this.isActivityOutViewVisible = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        final int i3 = 2;
        this.isActivityContainerVisible = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1$2$1
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
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModel$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiViewModel$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), bool);
        this.isAirplaneSpacerVisible = airplaneModeViewModelImpl.isAirplaneModeIconVisible;
        this.isSignalSpacerVisible = (Flow) supplier.get();
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final StateFlow getWifiIcon() {
        return this.wifiIcon;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityContainerVisible() {
        return this.isActivityContainerVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityInViewVisible() {
        return this.isActivityInViewVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isActivityOutViewVisible() {
        return this.isActivityOutViewVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isAirplaneSpacerVisible() {
        return this.isAirplaneSpacerVisible;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.ui.viewmodel.WifiViewModelCommon
    public final Flow isSignalSpacerVisible() {
        return this.isSignalSpacerVisible;
    }
}
