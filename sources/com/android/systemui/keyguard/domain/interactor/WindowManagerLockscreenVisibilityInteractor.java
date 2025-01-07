package com.android.systemui.keyguard.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.statusbar.notification.domain.interactor.NotificationLaunchAnimationInteractor;
import com.android.systemui.util.kotlin.Utils;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerLockscreenVisibilityInteractor {
    public final Flow aodVisibility;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 defaultSurfaceBehindVisibility;
    public final Lazy isDeviceEntered$delegate;
    public final Flow lockscreenVisibility;
    public final Flow surfaceBehindVisibility;
    public final Flow transitionSpecificSurfaceBehindVisibility;
    public final Flow usingKeyguardGoingAwayAnimation;

    public WindowManagerLockscreenVisibilityInteractor(KeyguardInteractor keyguardInteractor, final KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardSurfaceBehindInteractor keyguardSurfaceBehindInteractor, FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, FromPrimaryBouncerTransitionInteractor fromPrimaryBouncerTransitionInteractor, FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor, NotificationLaunchAnimationInteractor notificationLaunchAnimationInteractor, final dagger.Lazy lazy, KeyguardWakeDirectlyToGoneInteractor keyguardWakeDirectlyToGoneInteractor) {
        SceneKey sceneKey = Scenes.Gone;
        KeyguardState keyguardState = KeyguardState.GONE;
        Flow isFinishedIn = keyguardTransitionInteractor.isFinishedIn(keyguardState);
        WindowManagerLockscreenVisibilityInteractor$defaultSurfaceBehindVisibility$1 windowManagerLockscreenVisibilityInteractor$defaultSurfaceBehindVisibility$1 = new WindowManagerLockscreenVisibilityInteractor$defaultSurfaceBehindVisibility$1(3, null);
        Flow flow = keyguardWakeDirectlyToGoneInteractor.canWakeDirectlyToGone;
        this.defaultSurfaceBehindVisibility = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(isFinishedIn, flow, windowManagerLockscreenVisibilityInteractor$defaultSurfaceBehindVisibility$1);
        this.transitionSpecificSurfaceBehindVisibility = FlowKt.distinctUntilChanged(FlowKt.transformLatest(keyguardTransitionInteractor.startedKeyguardTransitionStep, new WindowManagerLockscreenVisibilityInteractor$special$$inlined$flatMapLatest$1(null, fromLockscreenTransitionInteractor, fromPrimaryBouncerTransitionInteractor, fromAlternateBouncerTransitionInteractor)));
        this.isDeviceEntered$delegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceEntered$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ((DeviceEntryInteractor) dagger.Lazy.this.get()).isDeviceEntered;
            }
        });
        LazyKt__LazyJVMKt.lazy(new Function0() { // from class: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                final Flow flow2 = (Flow) WindowManagerLockscreenVisibilityInteractor.this.isDeviceEntered$delegate.getValue();
                return new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1$2$1, reason: invalid class name */
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
                                boolean r0 = r6 instanceof com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r6)
                                goto L48
                            L27:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r6)
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                boolean r5 = r5.booleanValue()
                                r5 = r5 ^ r3
                                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L48
                                return r1
                            L48:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$isDeviceNotEntered$2$invoke$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = Flow.this.collect(new AnonymousClass2(flowCollector), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                };
            }
        });
        this.surfaceBehindVisibility = FlowKt.distinctUntilChanged(FlowKt.transformLatest(keyguardTransitionInteractor.isInTransition, new WindowManagerLockscreenVisibilityInteractor$special$$inlined$flatMapLatest$2(null, this)));
        Edge.Companion companion = Edge.Companion;
        this.usingKeyguardGoingAwayAnimation = FlowKt.distinctUntilChanged(FlowKt.combine(keyguardTransitionInteractor.isInTransition(Edge.Companion.create$default(sceneKey), Edge.Companion.create$default(null, keyguardState, 1)), keyguardTransitionInteractor.isFinishedIn(keyguardState), keyguardSurfaceBehindInteractor.isAnimatingSurface, notificationLaunchAnimationInteractor.repository.isLaunchAnimationRunning, new WindowManagerLockscreenVisibilityInteractor$usingKeyguardGoingAwayAnimation$2(5, null)));
        final SafeFlow sample = com.android.systemui.util.kotlin.FlowKt.sample(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(keyguardTransitionInteractor.currentKeyguardState, flow, WindowManagerLockscreenVisibilityInteractor$lockscreenVisibility$2.INSTANCE), keyguardTransitionInteractor.startedStepWithPrecedingStep, new WindowManagerLockscreenVisibilityInteractor$lockscreenVisibility$3(3, Utils.Companion, Utils.Companion.class, "toTriple", "toTriple(Lkotlin/Pair;Ljava/lang/Object;)Lkotlin/Triple;", 4));
        this.lockscreenVisibility = FlowKt.distinctUntilChanged(new Flow() { // from class: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ dagger.Lazy $deviceEntryInteractor$inlined;
                public final /* synthetic */ FlowCollector $this_unsafeFlow;
                public final /* synthetic */ KeyguardTransitionRepositoryImpl $transitionRepository$inlined;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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

                public AnonymousClass2(FlowCollector flowCollector, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, dagger.Lazy lazy) {
                    this.$this_unsafeFlow = flowCollector;
                    this.$transitionRepository$inlined = keyguardTransitionRepositoryImpl;
                    this.$deviceEntryInteractor$inlined = lazy;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r11, kotlin.coroutines.Continuation r12) {
                    /*
                        r10 = this;
                        boolean r0 = r12 instanceof com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r12
                        com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r12)
                    L18:
                        java.lang.Object r12 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L30
                        if (r2 != r3) goto L28
                        kotlin.ResultKt.throwOnFailure(r12)
                        goto Lcd
                    L28:
                        java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                        java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
                        r10.<init>(r11)
                        throw r10
                    L30:
                        kotlin.ResultKt.throwOnFailure(r12)
                        kotlin.Triple r11 = (kotlin.Triple) r11
                        java.lang.Object r12 = r11.component1()
                        com.android.systemui.keyguard.shared.model.KeyguardState r12 = (com.android.systemui.keyguard.shared.model.KeyguardState) r12
                        java.lang.Object r2 = r11.component2()
                        java.lang.Boolean r2 = (java.lang.Boolean) r2
                        boolean r2 = r2.booleanValue()
                        java.lang.Object r11 = r11.component3()
                        com.android.systemui.util.kotlin.WithPrev r11 = (com.android.systemui.util.kotlin.WithPrev) r11
                        java.lang.Object r4 = r11.previousValue
                        com.android.systemui.keyguard.shared.model.TransitionStep r4 = (com.android.systemui.keyguard.shared.model.TransitionStep) r4
                        java.lang.Object r11 = r11.newValue
                        com.android.systemui.keyguard.shared.model.TransitionStep r11 = (com.android.systemui.keyguard.shared.model.TransitionStep) r11
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r11.to
                        com.android.systemui.keyguard.shared.model.KeyguardState r6 = com.android.systemui.keyguard.shared.model.KeyguardState.GONE
                        r7 = 0
                        if (r5 != r6) goto L66
                        com.android.systemui.keyguard.shared.model.TransitionState r5 = r4.transitionState
                        com.android.systemui.keyguard.shared.model.TransitionState r8 = com.android.systemui.keyguard.shared.model.TransitionState.CANCELED
                        if (r5 != r8) goto L66
                        com.android.systemui.keyguard.shared.model.KeyguardState r4 = r4.from
                        if (r4 != r6) goto L66
                        r4 = r3
                        goto L67
                    L66:
                        r4 = r7
                    L67:
                        com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl r5 = r10.$transitionRepository$inlined
                        kotlinx.coroutines.flow.ReadonlyStateFlow r5 = r5.currentTransitionInfoInternal
                        kotlinx.coroutines.flow.MutableStateFlow r5 = r5.$$delegate_0
                        kotlinx.coroutines.flow.StateFlowImpl r5 = (kotlinx.coroutines.flow.StateFlowImpl) r5
                        java.lang.Object r5 = r5.getValue()
                        com.android.systemui.keyguard.shared.model.TransitionInfo r5 = (com.android.systemui.keyguard.shared.model.TransitionInfo) r5
                        com.android.systemui.keyguard.shared.model.KeyguardState$Companion r8 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion
                        com.android.systemui.keyguard.shared.model.KeyguardState r9 = r5.from
                        r8.getClass()
                        boolean r8 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion.deviceIsAsleepInState(r9)
                        if (r8 == 0) goto L88
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r5.to
                        if (r5 != r6) goto L88
                        r5 = r3
                        goto L89
                    L88:
                        r5 = r7
                    L89:
                        if (r4 != 0) goto Lbe
                        if (r5 == 0) goto L8e
                        goto Lbe
                    L8e:
                        if (r2 == 0) goto L91
                        goto Lbe
                    L91:
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.DREAMING
                        if (r12 != r2) goto Lb0
                        dagger.Lazy r2 = r10.$deviceEntryInteractor$inlined
                        java.lang.Object r2 = r2.get()
                        com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor r2 = (com.android.systemui.deviceentry.domain.interactor.DeviceEntryInteractor) r2
                        kotlinx.coroutines.flow.ReadonlyStateFlow r2 = r2.isUnlocked
                        kotlinx.coroutines.flow.MutableStateFlow r2 = r2.$$delegate_0
                        kotlinx.coroutines.flow.StateFlowImpl r2 = (kotlinx.coroutines.flow.StateFlowImpl) r2
                        java.lang.Object r2 = r2.getValue()
                        java.lang.Boolean r2 = (java.lang.Boolean) r2
                        boolean r2 = r2.booleanValue()
                        if (r2 == 0) goto Lb0
                        goto Lbe
                    Lb0:
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = r11.from
                        com.android.systemui.keyguard.shared.model.KeyguardState r4 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                        if (r2 != r4) goto Lbb
                        com.android.systemui.keyguard.shared.model.KeyguardState r11 = r11.to
                        if (r11 != r6) goto Lbb
                        goto Lbe
                    Lbb:
                        if (r12 == r6) goto Lbe
                        r7 = r3
                    Lbe:
                        java.lang.Boolean r11 = java.lang.Boolean.valueOf(r7)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r10 = r10.$this_unsafeFlow
                        java.lang.Object r10 = r10.emit(r11, r0)
                        if (r10 != r1) goto Lcd
                        return r1
                    Lcd:
                        kotlin.Unit r10 = kotlin.Unit.INSTANCE
                        return r10
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = SafeFlow.this.collect(new AnonymousClass2(flowCollector, keyguardTransitionRepositoryImpl, lazy), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        });
        this.aodVisibility = FlowKt.distinctUntilChanged(FlowKt.combine(keyguardInteractor.isDozing, keyguardInteractor.isAodAvailable, keyguardInteractor.biometricUnlockState, new WindowManagerLockscreenVisibilityInteractor$aodVisibility$1(4, null)));
    }
}
