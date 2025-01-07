package com.android.systemui.statusbar.pipeline.shared.data.repository;

import android.content.Context;
import android.net.ConnectivityManager;
import com.android.systemui.Dumpable;
import com.android.systemui.dump.DumpManager;
import com.android.systemui.statusbar.pipeline.shared.ConnectivityInputLogger;
import com.android.systemui.statusbar.pipeline.shared.data.model.ConnectivitySlot;
import com.android.systemui.statusbar.pipeline.shared.data.model.ConnectivitySlots;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import com.android.systemui.tuner.TunerService;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import com.android.wm.shell.R;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConnectivityRepositoryImpl implements Dumpable {
    public static final Companion Companion = null;
    public final ConnectivityManager connectivityManager;
    public final ConnectivitySlots connectivitySlots;
    public final ReadonlyStateFlow defaultConnections;
    public final Set defaultHiddenIcons;
    public final ReadonlySharedFlow defaultNetworkCapabilities;
    public final ReadonlyStateFlow forceHiddenSlots;
    public final ReadonlyStateFlow vcnSubId;

    public ConnectivityRepositoryImpl(ConnectivityManager connectivityManager, ConnectivitySlots connectivitySlots, Context context, DumpManager dumpManager, ConnectivityInputLogger connectivityInputLogger, CoroutineScope coroutineScope, TunerService tunerService) {
        int i = 0;
        this.connectivityManager = connectivityManager;
        this.connectivitySlots = connectivitySlots;
        dumpManager.registerNormalDumpable("ConnectivityRepository", this);
        Set access$toSlotSet = Companion.access$toSlotSet(Arrays.asList(context.getResources().getStringArray(R.array.config_statusBarIconsToExclude)), connectivitySlots);
        this.defaultHiddenIcons = access$toSlotSet;
        this.forceHiddenSlots = FlowKt.stateIn(FlowConflatedKt.conflatedCallbackFlow(new ConnectivityRepositoryImpl$forceHiddenSlots$1(tunerService, connectivityInputLogger, this, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), access$toSlotSet);
        final ReadonlySharedFlow shareIn = FlowKt.shareIn(FlowConflatedKt.conflatedCallbackFlow(new ConnectivityRepositoryImpl$defaultNetworkCapabilities$1(this, connectivityInputLogger, null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 0);
        FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L66
                    L27:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r7)
                        android.net.NetworkCapabilities r6 = (android.net.NetworkCapabilities) r6
                        r7 = 0
                        if (r6 == 0) goto L5b
                        android.net.TransportInfo r6 = r6.getTransportInfo()
                        boolean r2 = r6 instanceof android.net.vcn.VcnTransportInfo
                        if (r2 == 0) goto L42
                        android.net.vcn.VcnTransportInfo r6 = (android.net.vcn.VcnTransportInfo) r6
                        goto L43
                    L42:
                        r6 = r7
                    L43:
                        if (r6 == 0) goto L4f
                        int r6 = r6.getSubId()
                        java.lang.Integer r2 = new java.lang.Integer
                        r2.<init>(r6)
                        goto L50
                    L4f:
                        r2 = r7
                    L50:
                        if (r2 != 0) goto L53
                        goto L5a
                    L53:
                        int r6 = r2.intValue()
                        r4 = -1
                        if (r6 == r4) goto L5b
                    L5a:
                        r7 = r2
                    L5b:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r7, r0)
                        if (r5 != r1) goto L66
                        return r1
                    L66:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ReadonlySharedFlow.this.$$delegate_0.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }), new ConnectivityRepositoryImpl$vcnSubId$2(connectivityInputLogger, null), i);
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.vcnSubId = FlowKt.stateIn(flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, coroutineScope, startedEagerly, null);
        this.defaultConnections = FlowKt.stateIn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ ConnectivityRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, ConnectivityRepositoryImpl connectivityRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = connectivityRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x0081  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x00c5 A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:45:? A[LOOP:0: B:30:0x008e->B:45:?, LOOP_END, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r14, kotlin.coroutines.Continuation r15) {
                    /*
                        Method dump skipped, instructions count: 275
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.shared.data.repository.ConnectivityRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = ReadonlySharedFlow.this.$$delegate_0.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        }), new ConnectivityRepositoryImpl$defaultConnections$2(connectivityInputLogger, null), i), coroutineScope, startedEagerly, new DefaultConnectionModel(new DefaultConnectionModel.Wifi(false), new DefaultConnectionModel.Mobile(false), new DefaultConnectionModel.CarrierMerged(false), new DefaultConnectionModel.Ethernet(false), false));
    }

    @Override // com.android.systemui.Dumpable
    public final void dump(PrintWriter printWriter, String[] strArr) {
        printWriter.println("defaultHiddenIcons=" + this.defaultHiddenIcons);
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final Set access$toSlotSet(List list, ConnectivitySlots connectivitySlots) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : list) {
                if (!StringsKt__StringsJVMKt.isBlank((String) obj)) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ConnectivitySlot connectivitySlot = (ConnectivitySlot) connectivitySlots.slotByName.get((String) it.next());
                if (connectivitySlot != null) {
                    arrayList2.add(connectivitySlot);
                }
            }
            return CollectionsKt.toSet(arrayList2);
        }

        public static /* synthetic */ void getDEFAULT_HIDDEN_ICONS_RESOURCE$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
        }

        public static /* synthetic */ void getHIDDEN_ICONS_TUNABLE_KEY$frameworks__base__packages__SystemUI__android_common__SystemUI_core$annotations() {
        }
    }
}
