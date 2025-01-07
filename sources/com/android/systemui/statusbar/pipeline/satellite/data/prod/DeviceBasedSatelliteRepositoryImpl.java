package com.android.systemui.statusbar.pipeline.satellite.data.prod;

import android.telephony.TelephonyManager;
import android.telephony.satellite.SatelliteManager;
import androidx.appsearch.app.AppSearchSchema$Builder$$ExternalSyntheticOutline0;
import com.android.systemui.log.LogBuffer;
import com.android.systemui.log.core.LogLevel;
import com.android.systemui.log.core.LogMessage;
import com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository;
import com.android.systemui.statusbar.pipeline.satellite.data.prod.SatelliteSupport;
import com.android.systemui.statusbar.pipeline.satellite.shared.model.SatelliteConnectionState;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.Optional;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SafeFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceBasedSatelliteRepositoryImpl implements DeviceBasedSatelliteRepository {
    public static final Companion Companion = null;
    public final CoroutineDispatcher bgDispatcher;
    public final ReadonlyStateFlow connectionState;
    public final ReadonlyStateFlow isSatelliteAllowedForCurrentLocation;
    public final ReadonlyStateFlow isSatelliteProvisioned;
    public final LogBuffer logBuffer;
    public final ReadonlyStateFlow radioPowerState;
    public final Flow satelliteIsSupportedCallback;
    public final SatelliteManager satelliteManager;
    public final StateFlowImpl satelliteSupport;
    public final CoroutineScope scope;
    public final ReadonlyStateFlow signalStrength;
    public final SystemClock systemClock;
    public final FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 telephonyProcessCrashedEvent;
    public final LogBuffer verboseLogBuffer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$1$3, reason: invalid class name */
        final class AnonymousClass3 extends SuspendLambda implements Function2 {
            int label;
            final /* synthetic */ DeviceBasedSatelliteRepositoryImpl this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass3(DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl, Continuation continuation) {
                super(2, continuation);
                this.this$0 = deviceBasedSatelliteRepositoryImpl;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new AnonymousClass3(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                Unit unit = Unit.INSTANCE;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = this.this$0;
                    SatelliteManager satelliteManager = deviceBasedSatelliteRepositoryImpl.satelliteManager;
                    this.label = 1;
                    Object collectLatest = FlowKt.collectLatest(deviceBasedSatelliteRepositoryImpl.telephonyProcessCrashedEvent, new DeviceBasedSatelliteRepositoryImpl$listenForChangesToSatelliteSupport$2(satelliteManager, deviceBasedSatelliteRepositoryImpl, null), this);
                    if (collectLatest != coroutineSingletons) {
                        collectLatest = unit;
                    }
                    if (collectLatest == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return unit;
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return DeviceBasedSatelliteRepositoryImpl.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MutableStateFlow mutableStateFlow;
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                MutableStateFlow satelliteSupport = DeviceBasedSatelliteRepositoryImpl.this.getSatelliteSupport();
                DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl = DeviceBasedSatelliteRepositoryImpl.this;
                SatelliteManager satelliteManager = deviceBasedSatelliteRepositoryImpl.satelliteManager;
                this.L$0 = satelliteSupport;
                this.label = 1;
                Object access$checkSatelliteSupportAfterMinUptime = DeviceBasedSatelliteRepositoryImpl.access$checkSatelliteSupportAfterMinUptime(deviceBasedSatelliteRepositoryImpl, satelliteManager, this);
                if (access$checkSatelliteSupportAfterMinUptime == coroutineSingletons) {
                    return coroutineSingletons;
                }
                mutableStateFlow = satelliteSupport;
                obj = access$checkSatelliteSupportAfterMinUptime;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                mutableStateFlow = (MutableStateFlow) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            ((StateFlowImpl) mutableStateFlow).setValue(obj);
            final DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl2 = DeviceBasedSatelliteRepositoryImpl.this;
            LogBuffer logBuffer = deviceBasedSatelliteRepositoryImpl2.logBuffer;
            Function1 function1 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    ((LogMessage) obj2).setStr1(((StateFlowImpl) DeviceBasedSatelliteRepositoryImpl.this.getSatelliteSupport()).getValue().toString());
                    return Unit.INSTANCE;
                }
            };
            LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.1.2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj2) {
                    return AppSearchSchema$Builder$$ExternalSyntheticOutline0.m("Checked for system support. support=", ((LogMessage) obj2).getStr1());
                }
            }, null);
            function1.invoke(obtain);
            logBuffer.commit(obtain);
            DeviceBasedSatelliteRepositoryImpl deviceBasedSatelliteRepositoryImpl3 = DeviceBasedSatelliteRepositoryImpl.this;
            BuildersKt.launch$default(deviceBasedSatelliteRepositoryImpl3.scope, null, null, new AnonymousClass3(deviceBasedSatelliteRepositoryImpl3, null), 3);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$e(LogBuffer logBuffer, String str, Throwable th) {
            logBuffer.log("DeviceBasedSatelliteRepo", LogLevel.ERROR, str, th);
        }
    }

    public DeviceBasedSatelliteRepositoryImpl(Optional optional, TelephonyManager telephonyManager, CoroutineDispatcher coroutineDispatcher, CoroutineScope coroutineScope, LogBuffer logBuffer, LogBuffer logBuffer2, SystemClock systemClock) {
        this.bgDispatcher = coroutineDispatcher;
        this.scope = coroutineScope;
        this.logBuffer = logBuffer;
        this.verboseLogBuffer = logBuffer2;
        this.systemClock = systemClock;
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(SatelliteSupport.Unknown.INSTANCE);
        this.satelliteSupport = MutableStateFlow;
        final SafeFlow pairwise = com.android.systemui.util.kotlin.FlowKt.pairwise(FlowKt.stateIn(FlowKt.flowOn(FlowConflatedKt.conflatedCallbackFlow(new DeviceBasedSatelliteRepositoryImpl$radioPowerState$1(telephonyManager, this, null)), coroutineDispatcher), coroutineScope, SharingStarted.Companion.WhileSubscribed$default(3), 2));
        FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new DeviceBasedSatelliteRepositoryImpl$telephonyProcessCrashedEvent$2(2, null), new Flow() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1$2$1, reason: invalid class name */
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

                /* JADX WARN: Removed duplicated region for block: B:14:0x0031  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r6, kotlin.coroutines.Continuation r7) {
                    /*
                        r5 = this;
                        boolean r0 = r7 instanceof com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r7
                        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1$2$1 r0 = (com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1$2$1 r0 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1$2$1
                        r0.<init>(r7)
                    L18:
                        java.lang.Object r7 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        r4 = 1
                        if (r2 == 0) goto L31
                        if (r2 != r4) goto L29
                        kotlin.ResultKt.throwOnFailure(r7)
                        goto L5a
                    L29:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L31:
                        kotlin.ResultKt.throwOnFailure(r7)
                        com.android.systemui.util.kotlin.WithPrev r6 = (com.android.systemui.util.kotlin.WithPrev) r6
                        java.lang.Object r7 = r6.previousValue
                        java.lang.Number r7 = (java.lang.Number) r7
                        int r7 = r7.intValue()
                        java.lang.Object r6 = r6.newValue
                        java.lang.Number r6 = (java.lang.Number) r6
                        int r6 = r6.intValue()
                        if (r7 != r4) goto L4c
                        if (r6 == r4) goto L4c
                        r6 = r3
                        goto L4d
                    L4c:
                        r6 = 0
                    L4d:
                        if (r6 == 0) goto L5a
                        r0.label = r4
                        kotlinx.coroutines.flow.FlowCollector r5 = r5.$this_unsafeFlow
                        java.lang.Object r5 = r5.emit(r6, r0)
                        if (r5 != r1) goto L5a
                        return r1
                    L5a:
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$special$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = SafeFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.telephonyProcessCrashedEvent = flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
        SatelliteManager satelliteManager = (SatelliteManager) optional.orElse(null);
        this.satelliteManager = satelliteManager;
        if (satelliteManager != null) {
            BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
        } else {
            AnonymousClass2 anonymousClass2 = new Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return "Satellite manager is null";
                }
            };
            DeviceBasedSatelliteRepositoryImpl$Companion$i$1 deviceBasedSatelliteRepositoryImpl$Companion$i$1 = DeviceBasedSatelliteRepositoryImpl$Companion$i$1.INSTANCE;
            LogMessage obtain = logBuffer.obtain("DeviceBasedSatelliteRepo", LogLevel.INFO, anonymousClass2, null);
            deviceBasedSatelliteRepositoryImpl$Companion$i$1.invoke(obtain);
            logBuffer.commit(obtain);
            MutableStateFlow.updateState(null, SatelliteSupport.NotSupported.INSTANCE);
        }
        DeviceBasedSatelliteRepositoryImpl$isSatelliteAllowedForCurrentLocation$1 deviceBasedSatelliteRepositoryImpl$isSatelliteAllowedForCurrentLocation$1 = new DeviceBasedSatelliteRepositoryImpl$isSatelliteAllowedForCurrentLocation$1(1, this, DeviceBasedSatelliteRepositoryImpl.class, "isSatelliteAvailableFlow", "isSatelliteAvailableFlow(Landroid/telephony/satellite/SatelliteManager;)Lkotlinx/coroutines/flow/Flow;", 0);
        Boolean bool = Boolean.FALSE;
        this.isSatelliteAllowedForCurrentLocation = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new SatelliteSupport$Companion$whenSupported$$inlined$flatMapLatest$1(null, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool), deviceBasedSatelliteRepositoryImpl$isSatelliteAllowedForCurrentLocation$1)), coroutineScope, SharingStarted.Companion.Lazily, bool);
        this.satelliteIsSupportedCallback = satelliteManager == null ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool) : FlowConflatedKt.conflatedCallbackFlow(new DeviceBasedSatelliteRepositoryImpl$satelliteIsSupportedCallback$1(this, null));
        ChannelFlowTransformLatest transformLatest = FlowKt.transformLatest(MutableStateFlow, new SatelliteSupport$Companion$whenSupported$$inlined$flatMapLatest$1(null, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool), new DeviceBasedSatelliteRepositoryImpl$isSatelliteProvisioned$1(1, this, DeviceBasedSatelliteRepositoryImpl.class, "satelliteProvisioned", "satelliteProvisioned(Landroid/telephony/satellite/SatelliteManager;)Lkotlinx/coroutines/flow/Flow;", 0)));
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        this.isSatelliteProvisioned = FlowKt.stateIn(transformLatest, coroutineScope, startedEagerly, bool);
        DeviceBasedSatelliteRepositoryImpl$connectionState$1 deviceBasedSatelliteRepositoryImpl$connectionState$1 = new DeviceBasedSatelliteRepositoryImpl$connectionState$1(1, this, DeviceBasedSatelliteRepositoryImpl.class, "connectionStateFlow", "connectionStateFlow(Landroid/telephony/satellite/SatelliteManager;)Lkotlinx/coroutines/flow/Flow;", 0);
        SatelliteConnectionState satelliteConnectionState = SatelliteConnectionState.Off;
        this.connectionState = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new SatelliteSupport$Companion$whenSupported$$inlined$flatMapLatest$1(null, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(satelliteConnectionState), deviceBasedSatelliteRepositoryImpl$connectionState$1)), coroutineScope, startedEagerly, satelliteConnectionState);
        this.signalStrength = FlowKt.stateIn(FlowKt.transformLatest(MutableStateFlow, new SatelliteSupport$Companion$whenSupported$$inlined$flatMapLatest$1(null, flowKt__EmittersKt$onStart$$inlined$unsafeFlow$1, new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(0), new DeviceBasedSatelliteRepositoryImpl$signalStrength$1(1, this, DeviceBasedSatelliteRepositoryImpl.class, "signalStrengthFlow", "signalStrengthFlow(Landroid/telephony/satellite/SatelliteManager;)Lkotlinx/coroutines/flow/Flow;", 0))), coroutineScope, startedEagerly, 0);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(13:0|1|(2:3|(10:5|6|(1:(1:(3:10|11|12)(2:14|15))(1:16))(2:26|(2:28|(1:30)))|17|18|19|20|(1:22)|11|12))|31|6|(0)(0)|17|18|19|20|(0)|11|12) */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a9, code lost:
    
        r12 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x00aa, code lost:
    
        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.Companion.access$e(r11.logBuffer, "Exception when checking for satellite support. Assuming it is not supported for this device.", r12);
        r13.resumeWith(com.android.systemui.statusbar.pipeline.satellite.data.prod.SatelliteSupport.NotSupported.INSTANCE);
     */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$checkSatelliteSupportAfterMinUptime(final com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r11, final android.telephony.satellite.SatelliteManager r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            r11.getClass()
            boolean r0 = r13 instanceof com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$1
            if (r0 == 0) goto L16
            r0 = r13
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$1 r0 = (com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$1 r0 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$1
            r0.<init>(r11, r13)
        L1b:
            java.lang.Object r13 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L44
            if (r2 == r5) goto L37
            if (r2 != r4) goto L2f
            kotlin.ResultKt.throwOnFailure(r13)
            goto Lbf
        L2f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L37:
            java.lang.Object r11 = r0.L$1
            r12 = r11
            android.telephony.satellite.SatelliteManager r12 = (android.telephony.satellite.SatelliteManager) r12
            java.lang.Object r11 = r0.L$0
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl r11 = (com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl) r11
            kotlin.ResultKt.throwOnFailure(r13)
            goto L85
        L44:
            kotlin.ResultKt.throwOnFailure(r13)
            com.android.systemui.util.time.SystemClock r13 = r11.systemClock
            com.android.systemui.util.time.SystemClockImpl r13 = (com.android.systemui.util.time.SystemClockImpl) r13
            r13.getClass()
            long r6 = android.os.SystemClock.uptimeMillis()
            long r8 = android.os.Process.getStartUptimeMillis()
            long r6 = r6 - r8
            r8 = 60000(0xea60, double:2.9644E-319)
            long r8 = r8 - r6
            r6 = 0
            int r13 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r13 <= 0) goto L85
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$2 r13 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$2
            r13.<init>()
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3 r2 = new kotlin.jvm.functions.Function1() { // from class: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3
                static {
                    /*
                        com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3 r0 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3)
 com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3.INSTANCE com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r3) {
                    /*
                        r2 = this;
                        com.android.systemui.log.core.LogMessage r3 = (com.android.systemui.log.core.LogMessage) r3
                        long r2 = r3.getLong1()
                        java.lang.StringBuilder r0 = new java.lang.StringBuilder
                        java.lang.String r1 = "Waiting "
                        r0.<init>(r1)
                        r0.append(r2)
                        java.lang.String r2 = " ms before checking for satellite support"
                        r0.append(r2)
                        java.lang.String r2 = r0.toString()
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupportAfterMinUptime$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            com.android.systemui.log.core.LogLevel r6 = com.android.systemui.log.core.LogLevel.INFO
            java.lang.String r7 = "DeviceBasedSatelliteRepo"
            com.android.systemui.log.LogBuffer r10 = r11.logBuffer
            com.android.systemui.log.core.LogMessage r2 = r10.obtain(r7, r6, r2, r3)
            r13.invoke(r2)
            r10.commit(r2)
            r0.L$0 = r11
            r0.L$1 = r12
            r0.label = r5
            java.lang.Object r13 = kotlinx.coroutines.DelayKt.delay(r8, r0)
            if (r13 != r1) goto L85
            goto Lc0
        L85:
            r0.L$0 = r3
            r0.L$1 = r3
            r0.label = r4
            r11.getClass()
            kotlinx.coroutines.CancellableContinuationImpl r13 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r13.<init>(r5, r0)
            r13.initCancellability()
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupported$2$cb$1 r0 = new com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl$checkSatelliteSupported$2$cb$1
            r0.<init>()
            kotlinx.coroutines.CoroutineDispatcher r2 = r11.bgDispatcher     // Catch: java.lang.Exception -> La9
            java.util.concurrent.Executor r2 = kotlinx.coroutines.ExecutorsKt.asExecutor(r2)     // Catch: java.lang.Exception -> La9
            r12.requestIsSupported(r2, r0)     // Catch: java.lang.Exception -> La9
            goto Lb6
        La9:
            r12 = move-exception
            java.lang.String r0 = "Exception when checking for satellite support. Assuming it is not supported for this device."
            com.android.systemui.log.LogBuffer r11 = r11.logBuffer
            com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.Companion.access$e(r11, r0, r12)
            com.android.systemui.statusbar.pipeline.satellite.data.prod.SatelliteSupport$NotSupported r11 = com.android.systemui.statusbar.pipeline.satellite.data.prod.SatelliteSupport.NotSupported.INSTANCE
            r13.resumeWith(r11)
        Lb6:
            java.lang.Object r13 = r13.getResult()
            kotlin.coroutines.intrinsics.CoroutineSingletons r11 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            if (r13 != r1) goto Lbf
            goto Lc0
        Lbf:
            r1 = r13
        Lc0:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl.access$checkSatelliteSupportAfterMinUptime(com.android.systemui.statusbar.pipeline.satellite.data.prod.DeviceBasedSatelliteRepositoryImpl, android.telephony.satellite.SatelliteManager, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow getConnectionState() {
        return this.connectionState;
    }

    public final MutableStateFlow getSatelliteSupport() {
        return this.satelliteSupport;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow getSignalStrength() {
        return this.signalStrength;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow isSatelliteAllowedForCurrentLocation() {
        return this.isSatelliteAllowedForCurrentLocation;
    }

    @Override // com.android.systemui.statusbar.pipeline.satellite.data.DeviceBasedSatelliteRepository
    public final StateFlow isSatelliteProvisioned() {
        return this.isSatelliteProvisioned;
    }
}
