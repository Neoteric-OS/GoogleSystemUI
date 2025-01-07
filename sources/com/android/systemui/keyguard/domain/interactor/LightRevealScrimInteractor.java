package com.android.systemui.keyguard.domain.interactor;

import com.android.keyguard.logging.ScrimLogger;
import com.android.systemui.keyguard.data.repository.LightRevealScrimRepository;
import com.android.systemui.keyguard.data.repository.LightRevealScrimRepositoryImpl;
import com.android.systemui.util.kotlin.FlowKt;
import dagger.Lazy;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LightRevealScrimInteractor {
    public static final String TAG;
    public final SafeFlow lightRevealEffect;
    public final LightRevealScrimRepository lightRevealScrimRepository;
    public final Lazy powerInteractor;
    public final LightRevealScrimInteractor$special$$inlined$filter$1 revealAmount;
    public final ScrimLogger scrimLogger;
    public final KeyguardTransitionInteractor transitionInteractor;

    static {
        String simpleName = Reflection.getOrCreateKotlinClass(LightRevealScrimInteractor.class).getSimpleName();
        Intrinsics.checkNotNull(simpleName);
        TAG = simpleName;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1] */
    public LightRevealScrimInteractor(KeyguardTransitionInteractor keyguardTransitionInteractor, LightRevealScrimRepository lightRevealScrimRepository, CoroutineScope coroutineScope, ScrimLogger scrimLogger, Lazy lazy) {
        this.transitionInteractor = keyguardTransitionInteractor;
        this.lightRevealScrimRepository = lightRevealScrimRepository;
        this.scrimLogger = scrimLogger;
        this.powerInteractor = lazy;
        BuildersKt.launch$default(coroutineScope, null, null, new LightRevealScrimInteractor$listenForStartedKeyguardTransitionStep$1(this, null), 3);
        LightRevealScrimRepositoryImpl lightRevealScrimRepositoryImpl = (LightRevealScrimRepositoryImpl) lightRevealScrimRepository;
        this.lightRevealEffect = FlowKt.sample(keyguardTransitionInteractor.startedKeyguardTransitionStep, lightRevealScrimRepositoryImpl.revealEffect);
        final CallbackFlowBuilder callbackFlowBuilder = lightRevealScrimRepositoryImpl.revealAmount;
        this.revealAmount = new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ LightRevealScrimInteractor this$0;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    Object L$1;
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

                public AnonymousClass2(FlowCollector flowCollector, LightRevealScrimInteractor lightRevealScrimInteractor) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = lightRevealScrimInteractor;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L7d
                    L27:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r8)
                        r8 = r7
                        java.lang.Number r8 = (java.lang.Number) r8
                        float r8 = r8.floatValue()
                        com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor r2 = r6.this$0
                        dagger.Lazy r2 = r2.powerInteractor
                        java.lang.Object r4 = r2.get()
                        com.android.systemui.power.domain.interactor.PowerInteractor r4 = (com.android.systemui.power.domain.interactor.PowerInteractor) r4
                        kotlinx.coroutines.flow.ReadonlyStateFlow r4 = r4.screenPowerState
                        kotlinx.coroutines.flow.MutableStateFlow r4 = r4.$$delegate_0
                        kotlinx.coroutines.flow.StateFlowImpl r4 = (kotlinx.coroutines.flow.StateFlowImpl) r4
                        java.lang.Object r4 = r4.getValue()
                        com.android.systemui.power.shared.model.ScreenPowerState r5 = com.android.systemui.power.shared.model.ScreenPowerState.SCREEN_OFF
                        if (r4 == r5) goto L66
                        java.lang.Object r2 = r2.get()
                        com.android.systemui.power.domain.interactor.PowerInteractor r2 = (com.android.systemui.power.domain.interactor.PowerInteractor) r2
                        kotlinx.coroutines.flow.ReadonlyStateFlow r2 = r2.screenPowerState
                        kotlinx.coroutines.flow.MutableStateFlow r2 = r2.$$delegate_0
                        kotlinx.coroutines.flow.StateFlowImpl r2 = (kotlinx.coroutines.flow.StateFlowImpl) r2
                        java.lang.Object r2 = r2.getValue()
                        com.android.systemui.power.shared.model.ScreenPowerState r4 = com.android.systemui.power.shared.model.ScreenPowerState.SCREEN_TURNING_ON
                        if (r2 == r4) goto L66
                        goto L72
                    L66:
                        r2 = 1065353216(0x3f800000, float:1.0)
                        int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                        if (r2 != 0) goto L6d
                        goto L72
                    L6d:
                        r2 = 0
                        int r8 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
                        if (r8 != 0) goto L7d
                    L72:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r7, r0)
                        if (r6 != r1) goto L7d
                        return r1
                    L7d:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = CallbackFlowBuilder.this.collect(new AnonymousClass2(flowCollector, this), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
    }
}
