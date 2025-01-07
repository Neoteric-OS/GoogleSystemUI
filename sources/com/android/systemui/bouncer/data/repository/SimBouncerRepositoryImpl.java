package com.android.systemui.bouncer.data.repository;

import android.R;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.telephony.euicc.EuiccManager;
import com.android.keyguard.KeyguardUpdateMonitor;
import com.android.systemui.bouncer.data.model.SimPukInputModel;
import com.android.systemui.broadcast.BroadcastDispatcher;
import com.android.systemui.statusbar.pipeline.mobile.util.SubscriptionManagerProxyImpl;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SimBouncerRepositoryImpl {
    public final SimPukInputModel _simPukInputModel;
    public final ReadonlyStateFlow activeSubscriptionInfo;
    public final CoroutineDispatcher backgroundDispatcher;
    public final ReadonlyStateFlow errorDialogMessage;
    public final ReadonlyStateFlow isLockedEsim;
    public final boolean isPukScreenAvailable;
    public final ReadonlyStateFlow isSimPukLocked;
    public final SimBouncerRepositoryImpl$special$$inlined$map$1 simBouncerModel;
    public final StateFlowImpl simVerificationErrorMessage;
    public final ReadonlyStateFlow subscriptionId;
    public final SubscriptionManagerProxyImpl subscriptionManager;

    public SimBouncerRepositoryImpl(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, final Resources resources, KeyguardUpdateMonitor keyguardUpdateMonitor, SubscriptionManagerProxyImpl subscriptionManagerProxyImpl, BroadcastDispatcher broadcastDispatcher, final EuiccManager euiccManager) {
        this.backgroundDispatcher = coroutineDispatcher;
        this.subscriptionManager = subscriptionManagerProxyImpl;
        this.isPukScreenAvailable = resources.getBoolean(R.bool.config_fillSecondaryBuiltInDisplayCutout);
        final SimBouncerRepositoryImpl$special$$inlined$map$1 simBouncerRepositoryImpl$special$$inlined$map$1 = new SimBouncerRepositoryImpl$special$$inlined$map$1(FlowConflatedKt.conflatedCallbackFlow(new SimBouncerRepositoryImpl$simBouncerModel$1(keyguardUpdateMonitor, null)), this, keyguardUpdateMonitor);
        final int i = 0;
        final ReadonlyStateFlow stateIn = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1
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
                        com.android.systemui.bouncer.data.model.SimBouncerModel r5 = (com.android.systemui.bouncer.data.model.SimBouncerModel) r5
                        if (r5 == 0) goto L39
                        int r5 = r5.subscriptionId
                        goto L3a
                    L39:
                        r5 = -1
                    L3a:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = simBouncerRepositoryImpl$special$$inlined$map$1.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = simBouncerRepositoryImpl$special$$inlined$map$1.collect(new SimBouncerRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), -1);
        this.subscriptionId = stateIn;
        final int i2 = 0;
        Flow flow = new Flow() { // from class: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SimBouncerRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SimBouncerRepositoryImpl simBouncerRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = simBouncerRepositoryImpl;
                }

                /* JADX WARN: Removed duplicated region for block: B:19:0x0064 A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r8, kotlin.coroutines.Continuation r9) {
                    /*
                        r7 = this;
                        boolean r0 = r9 instanceof com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3b
                        if (r2 == r5) goto L33
                        if (r2 != r4) goto L2b
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L65
                    L2b:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L33:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L5a
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Number r8 = (java.lang.Number) r8
                        int r8 = r8.intValue()
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl r9 = r7.this$0
                        kotlinx.coroutines.CoroutineDispatcher r2 = r9.backgroundDispatcher
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1 r6 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1
                        r6.<init>(r9, r8, r3)
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        r0.L$0 = r7
                        r0.label = r5
                        java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r9 != r1) goto L5a
                        return r1
                    L5a:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r7 = r7.emit(r9, r0)
                        if (r7 != r1) goto L65
                        return r1
                    L65:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        stateIn.collect(new AnonymousClass2(flowCollector, (SimBouncerRepositoryImpl) this), continuation);
                        break;
                    default:
                        stateIn.collect(new SimBouncerRepositoryImpl$special$$inlined$map$4$2(flowCollector, (EuiccManager) this), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        };
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        final ReadonlyStateFlow stateIn2 = FlowKt.stateIn(flow, coroutineScope, startedEagerly, null);
        final int i3 = 1;
        this.isLockedEsim = FlowKt.stateIn(new Flow() { // from class: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ SimBouncerRepositoryImpl this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, SimBouncerRepositoryImpl simBouncerRepositoryImpl) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = simBouncerRepositoryImpl;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    /*
                        this = this;
                        boolean r0 = r9 instanceof com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r9
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1 r0 = (com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1 r0 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3$2$1
                        r0.<init>(r9)
                    L18:
                        java.lang.Object r9 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 0
                        r4 = 2
                        r5 = 1
                        if (r2 == 0) goto L3b
                        if (r2 == r5) goto L33
                        if (r2 != r4) goto L2b
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L65
                    L2b:
                        java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                        java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                        r7.<init>(r8)
                        throw r7
                    L33:
                        java.lang.Object r7 = r0.L$0
                        kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                        kotlin.ResultKt.throwOnFailure(r9)
                        goto L5a
                    L3b:
                        kotlin.ResultKt.throwOnFailure(r9)
                        java.lang.Number r8 = (java.lang.Number) r8
                        int r8 = r8.intValue()
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl r9 = r7.this$0
                        kotlinx.coroutines.CoroutineDispatcher r2 = r9.backgroundDispatcher
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1 r6 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1
                        r6.<init>(r9, r8, r3)
                        kotlinx.coroutines.flow.FlowCollector r7 = r7.$this_unsafeFlow
                        r0.L$0 = r7
                        r0.label = r5
                        java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r2, r6, r0)
                        if (r9 != r1) goto L5a
                        return r1
                    L5a:
                        r0.L$0 = r3
                        r0.label = r4
                        java.lang.Object r7 = r7.emit(r9, r0)
                        if (r7 != r1) goto L65
                        return r1
                    L65:
                        kotlin.Unit r7 = kotlin.Unit.INSTANCE
                        return r7
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$3.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i3) {
                    case 0:
                        stateIn2.collect(new AnonymousClass2(flowCollector, (SimBouncerRepositoryImpl) euiccManager), continuation);
                        break;
                    default:
                        stateIn2.collect(new SimBouncerRepositoryImpl$special$$inlined$map$4$2(flowCollector, (EuiccManager) euiccManager), continuation);
                        break;
                }
                return CoroutineSingletons.COROUTINE_SUSPENDED;
            }
        }, coroutineScope, startedEagerly, null);
        final int i4 = 1;
        FlowKt.stateIn(new Flow() { // from class: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1, reason: invalid class name */
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
                        boolean r0 = r6 instanceof com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1 r0 = (com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1 r0 = new com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2$2$1
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
                        com.android.systemui.bouncer.data.model.SimBouncerModel r5 = (com.android.systemui.bouncer.data.model.SimBouncerModel) r5
                        if (r5 == 0) goto L39
                        int r5 = r5.subscriptionId
                        goto L3a
                    L39:
                        r5 = -1
                    L3a:
                        java.lang.Integer r6 = new java.lang.Integer
                        r6.<init>(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r6, r0)
                        if (r4 != r1) goto L4a
                        return r1
                    L4a:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$special$$inlined$map$2.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i4) {
                    case 0:
                        Object collect = simBouncerRepositoryImpl$special$$inlined$map$1.collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = simBouncerRepositoryImpl$special$$inlined$map$1.collect(new SimBouncerRepositoryImpl$special$$inlined$map$5$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }, coroutineScope, startedEagerly, Boolean.FALSE);
        this.errorDialogMessage = FlowKt.stateIn(FlowKt.merge(BroadcastDispatcher.broadcastFlow$default(broadcastDispatcher, new IntentFilter("com.android.keyguard.disable_esim"), null, new Function2() { // from class: com.android.systemui.bouncer.data.repository.SimBouncerRepositoryImpl$disableEsimErrorMessage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                if (((BroadcastReceiver) obj2).getResultCode() != 0) {
                    return resources.getString(com.android.wm.shell.R.string.error_disable_esim_msg);
                }
                return null;
            }
        }, 14), StateFlowKt.MutableStateFlow(null)), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), null);
        new SimPukInputModel(null, null);
    }
}
