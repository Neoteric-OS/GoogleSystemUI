package com.android.systemui.statusbar.pipeline.wifi.data.repository.prod;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import android.net.wifi.WifiManager;
import android.net.wifi.sharedconnectivity.app.HotspotNetwork;
import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.table.DiffableKt;
import com.android.systemui.log.table.TableLogBuffer;
import com.android.systemui.statusbar.connectivity.WifiPickerTrackerFactory;
import com.android.systemui.statusbar.pipeline.shared.data.model.DataActivityModel;
import com.android.systemui.statusbar.pipeline.wifi.data.repository.RealWifiRepository;
import com.android.systemui.statusbar.pipeline.wifi.shared.model.WifiNetworkModel;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wifitrackerlib.HotspotNetworkEntry;
import com.android.wifitrackerlib.MergedCarrierEntry;
import com.android.wifitrackerlib.WifiEntry;
import com.android.wifitrackerlib.WifiPickerTracker;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class WifiRepositoryImpl implements RealWifiRepository, LifecycleOwner {
    public final CoroutineDispatcher bgDispatcher;
    public final LogBuffer inputLogger;
    public final ReadonlyStateFlow isWifiDefault;
    public final ReadonlyStateFlow isWifiEnabled;
    public final LifecycleRegistry lifecycle;
    public final Executor mainExecutor;
    public final ReadonlyStateFlow secondaryNetworks;
    public final ReadonlyStateFlow wifiActivity;
    public final WifiManager wifiManager;
    public final ReadonlyStateFlow wifiNetwork;
    public WifiPickerTracker wifiPickerTracker;
    public final WifiPickerTrackerFactory wifiPickerTrackerFactory;
    public final ReadonlyStateFlow wifiPickerTrackerInfo;
    public final ReadonlyStateFlow wifiScanResults;
    public static final WifiNetworkModel.Inactive WIFI_NETWORK_DEFAULT = new WifiNetworkModel.Inactive(null);
    public static final DataActivityModel ACTIVITY_DEFAULT = new DataActivityModel(false, false);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final CoroutineDispatcher bgDispatcher;
        public final LogBuffer inputLogger;
        public final Executor mainExecutor;
        public final CoroutineScope scope;
        public final TableLogBuffer tableLogger;
        public final WifiPickerTrackerFactory wifiPickerTrackerFactory;

        public Factory(CoroutineScope coroutineScope, Executor executor, CoroutineDispatcher coroutineDispatcher, WifiPickerTrackerFactory wifiPickerTrackerFactory, LogBuffer logBuffer, TableLogBuffer tableLogBuffer) {
            this.scope = coroutineScope;
            this.mainExecutor = executor;
            this.bgDispatcher = coroutineDispatcher;
            this.wifiPickerTrackerFactory = wifiPickerTrackerFactory;
            this.inputLogger = logBuffer;
            this.tableLogger = tableLogBuffer;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class WifiPickerTrackerInfo {
        public final boolean isDefault;
        public final WifiNetworkModel primaryNetwork;
        public final List secondaryNetworks;
        public final int state;

        public WifiPickerTrackerInfo(int i, boolean z, WifiNetworkModel wifiNetworkModel, List list) {
            this.state = i;
            this.isDefault = z;
            this.primaryNetwork = wifiNetworkModel;
            this.secondaryNetworks = list;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof WifiPickerTrackerInfo)) {
                return false;
            }
            WifiPickerTrackerInfo wifiPickerTrackerInfo = (WifiPickerTrackerInfo) obj;
            return this.state == wifiPickerTrackerInfo.state && this.isDefault == wifiPickerTrackerInfo.isDefault && Intrinsics.areEqual(this.primaryNetwork, wifiPickerTrackerInfo.primaryNetwork) && Intrinsics.areEqual(this.secondaryNetworks, wifiPickerTrackerInfo.secondaryNetworks);
        }

        public final int hashCode() {
            return this.secondaryNetworks.hashCode() + ((this.primaryNetwork.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(Integer.hashCode(this.state) * 31, 31, this.isDefault)) * 31);
        }

        public final String toString() {
            return "WifiPickerTrackerInfo(state=" + this.state + ", isDefault=" + this.isDefault + ", primaryNetwork=" + this.primaryNetwork + ", secondaryNetworks=" + this.secondaryNetworks + ")";
        }
    }

    public WifiRepositoryImpl(CoroutineScope coroutineScope, Executor executor, CoroutineDispatcher coroutineDispatcher, WifiPickerTrackerFactory wifiPickerTrackerFactory, WifiManager wifiManager, LogBuffer logBuffer, TableLogBuffer tableLogBuffer) {
        this.mainExecutor = executor;
        this.bgDispatcher = coroutineDispatcher;
        this.wifiPickerTrackerFactory = wifiPickerTrackerFactory;
        this.wifiManager = wifiManager;
        this.inputLogger = logBuffer;
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        executor.execute(new WifiRepositoryImpl$lifecycle$1$1(0, lifecycleRegistry));
        this.lifecycle = lifecycleRegistry;
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        WifiNetworkModel.Inactive inactive = WIFI_NETWORK_DEFAULT;
        EmptyList emptyList = EmptyList.INSTANCE;
        ref$ObjectRef.element = new WifiPickerTrackerInfo(1, false, inactive, emptyList);
        CallbackFlowBuilder callbackFlow = FlowKt.callbackFlow(new WifiRepositoryImpl$wifiPickerTrackerInfo$1$1(this, ref$ObjectRef, null));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(callbackFlow, coroutineScope, startedEagerly, ref$ObjectRef.element);
        final int i = 0;
        SafeFlow logDiffsForTable = DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$WifiPickerTrackerInfo r5 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl.WifiPickerTrackerInfo) r5
                        int r5 = r5.state
                        r6 = 3
                        if (r5 != r6) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), tableLogBuffer, "", "isEnabled", false);
        Boolean bool = Boolean.FALSE;
        this.isWifiEnabled = FlowKt.stateIn(logDiffsForTable, coroutineScope, startedEagerly, bool);
        final int i2 = 1;
        this.wifiNetwork = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$WifiPickerTrackerInfo r5 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl.WifiPickerTrackerInfo) r5
                        int r5 = r5.state
                        r6 = 3
                        if (r5 != r6) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), tableLogBuffer, "", inactive), coroutineScope, startedEagerly, inactive);
        final int i3 = 2;
        this.secondaryNetworks = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$WifiPickerTrackerInfo r5 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl.WifiPickerTrackerInfo) r5
                        int r5 = r5.state
                        r6 = 3
                        if (r5 != r6) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), tableLogBuffer, "", "secondaryNetworks", emptyList), coroutineScope, startedEagerly, emptyList);
        final int i4 = 3;
        this.isWifiDefault = FlowKt.stateIn(DiffableKt.logDiffsForTable(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L4b
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$WifiPickerTrackerInfo r5 = (com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl.WifiPickerTrackerInfo) r5
                        int r5 = r5.state
                        r6 = 3
                        if (r5 != r6) goto L3b
                        r5 = r3
                        goto L3c
                    L3b:
                        r5 = 0
                    L3c:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L4b
                        return r1
                    L4b:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.wifi.data.repository.prod.WifiRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector), continuation);
                        break;
                    case 1:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$2$2(flowCollector), continuation);
                        break;
                    case 2:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$3$2(flowCollector), continuation);
                        break;
                    default:
                        stateIn.collect(new WifiRepositoryImpl$special$$inlined$map$4$2(flowCollector), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }), tableLogBuffer, "", "isDefault", false), coroutineScope, startedEagerly, bool);
        this.wifiActivity = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new WifiRepositoryImpl$wifiActivity$1(this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), ACTIVITY_DEFAULT);
        this.wifiScanResults = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new WifiRepositoryImpl$wifiScanResults$1(this, null)), coroutineScope, startedEagerly, emptyList);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public final Lifecycle getLifecycle() {
        return this.lifecycle;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getSecondaryNetworks() {
        return this.secondaryNetworks;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiActivity() {
        return this.wifiActivity;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiNetwork() {
        return this.wifiNetwork;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow getWifiScanResults() {
        return this.wifiScanResults;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow isWifiDefault() {
        return this.isWifiDefault;
    }

    @Override // com.android.systemui.statusbar.pipeline.wifi.data.repository.WifiRepository
    public final StateFlow isWifiEnabled() {
        return this.isWifiEnabled;
    }

    public final WifiNetworkModel toWifiNetworkModel(WifiEntry wifiEntry) {
        WifiNetworkModel.HotspotDeviceType hotspotDeviceType;
        int deviceType;
        if (wifiEntry instanceof MergedCarrierEntry) {
            MergedCarrierEntry mergedCarrierEntry = (MergedCarrierEntry) wifiEntry;
            return WifiNetworkModel.CarrierMerged.Companion.of(mergedCarrierEntry.mSubscriptionId, mergedCarrierEntry.getLevel(), this.wifiManager.getMaxSignalLevel() + 1);
        }
        if (wifiEntry instanceof HotspotNetworkEntry) {
            HotspotNetworkEntry hotspotNetworkEntry = (HotspotNetworkEntry) wifiEntry;
            synchronized (hotspotNetworkEntry) {
                HotspotNetwork hotspotNetwork = hotspotNetworkEntry.mHotspotNetworkData;
                if (hotspotNetwork == null) {
                    deviceType = 0;
                } else {
                    deviceType = hotspotNetwork.getNetworkProviderInfo().getDeviceType();
                }
            }
            hotspotDeviceType = deviceType != 0 ? deviceType != 1 ? deviceType != 2 ? deviceType != 3 ? deviceType != 4 ? deviceType != 5 ? WifiNetworkModel.HotspotDeviceType.INVALID : WifiNetworkModel.HotspotDeviceType.AUTO : WifiNetworkModel.HotspotDeviceType.WATCH : WifiNetworkModel.HotspotDeviceType.LAPTOP : WifiNetworkModel.HotspotDeviceType.TABLET : WifiNetworkModel.HotspotDeviceType.PHONE : WifiNetworkModel.HotspotDeviceType.UNKNOWN;
        } else {
            hotspotDeviceType = WifiNetworkModel.HotspotDeviceType.NONE;
        }
        boolean hasInternetAccess = wifiEntry.hasInternetAccess();
        int level = wifiEntry.getLevel();
        return (level == -1 || level < 0 || level >= 5) ? new WifiNetworkModel.Inactive(AnnotationValue$1$$ExternalSyntheticOutline0.m(level, "Wifi network was active but had invalid level. 0 <= wifi level <= 4 required; level was ")) : new WifiNetworkModel.Active(hasInternetAccess, level, wifiEntry.getTitle(), hotspotDeviceType);
    }
}
