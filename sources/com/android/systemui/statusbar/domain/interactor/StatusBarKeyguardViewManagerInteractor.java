package com.android.systemui.statusbar.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.domain.interactor.KeyguardOcclusionInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardSurfaceBehindInteractor;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.FlowKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.flow.DistinctFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt__DistinctKt;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarKeyguardViewManagerInteractor {
    public final DistinctFlowImpl keyguardViewOcclusionState;
    public final StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1 occlusionStateFromFinishedStep;
    public final FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 occlusionStateFromStartedStep;

    public StatusBarKeyguardViewManagerInteractor(KeyguardTransitionInteractor keyguardTransitionInteractor, KeyguardOcclusionInteractor keyguardOcclusionInteractor, PowerInteractor powerInteractor, WindowManagerLockscreenVisibilityInteractor windowManagerLockscreenVisibilityInteractor, KeyguardSurfaceBehindInteractor keyguardSurfaceBehindInteractor) {
        final SafeFlow sample = FlowKt.sample(keyguardTransitionInteractor.startedKeyguardTransitionStep, powerInteractor.detailedWakefulness, StatusBarKeyguardViewManagerInteractor$occlusionStateFromStartedStep$2.INSTANCE);
        final int i = 0;
        FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1 = new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                    /*
                        r6 = this;
                        boolean r0 = r8 instanceof com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L81
                    L27:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlin.Pair r7 = (kotlin.Pair) r7
                        java.lang.Object r8 = r7.component1()
                        com.android.systemui.keyguard.shared.model.TransitionStep r8 = (com.android.systemui.keyguard.shared.model.TransitionStep) r8
                        java.lang.Object r7 = r7.component2()
                        com.android.systemui.power.shared.model.WakefulnessModel r7 = (com.android.systemui.power.shared.model.WakefulnessModel) r7
                        com.android.systemui.keyguard.shared.model.KeyguardState$Companion r2 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion
                        com.android.systemui.keyguard.shared.model.KeyguardState r4 = r8.from
                        r2.getClass()
                        boolean r2 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion.deviceIsAsleepInState(r4)
                        r4 = 0
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r8.to
                        if (r2 == 0) goto L5a
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                        if (r5 != r2) goto L5a
                        boolean r7 = r7.powerButtonLaunchGestureTriggered
                        if (r7 == 0) goto L5a
                        r7 = r3
                        goto L5b
                    L5a:
                        r7 = r4
                    L5b:
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                        if (r5 != r2) goto L67
                        if (r7 != 0) goto L67
                        com.android.systemui.statusbar.domain.interactor.OccludedState r7 = new com.android.systemui.statusbar.domain.interactor.OccludedState
                        r7.<init>(r3)
                        goto L76
                    L67:
                        com.android.systemui.keyguard.shared.model.KeyguardState r7 = r8.from
                        if (r7 != r2) goto L75
                        com.android.systemui.keyguard.shared.model.KeyguardState r7 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
                        if (r5 != r7) goto L75
                        com.android.systemui.statusbar.domain.interactor.OccludedState r7 = new com.android.systemui.statusbar.domain.interactor.OccludedState
                        r7.<init>(r4)
                        goto L76
                    L75:
                        r7 = 0
                    L76:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r7, r0)
                        if (r6 != r1) goto L81
                        return r1
                    L81:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i) {
                    case 0:
                        Object collect = ((SafeFlow) sample).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) sample).collect(new StatusBarKeyguardViewManagerInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        });
        SceneKey sceneKey = Scenes.Bouncer;
        final FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 combine = kotlinx.coroutines.flow.FlowKt.combine(keyguardTransitionInteractor.isFinishedIn(KeyguardState.GONE), keyguardTransitionInteractor.isFinishedIn$1(KeyguardState.OCCLUDED), keyguardOcclusionInteractor.isShowWhenLockedActivityOnTop, StatusBarKeyguardViewManagerInteractor$occlusionStateFromFinishedStep$2.INSTANCE);
        final int i2 = 1;
        FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(kotlinx.coroutines.flow.FlowKt.merge(flowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1, new Flow() { // from class: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
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
                        boolean r0 = r8 instanceof com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r8
                        com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r8)
                    L18:
                        java.lang.Object r8 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L81
                    L27:
                        java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                        java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                        r6.<init>(r7)
                        throw r6
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r8)
                        kotlin.Pair r7 = (kotlin.Pair) r7
                        java.lang.Object r8 = r7.component1()
                        com.android.systemui.keyguard.shared.model.TransitionStep r8 = (com.android.systemui.keyguard.shared.model.TransitionStep) r8
                        java.lang.Object r7 = r7.component2()
                        com.android.systemui.power.shared.model.WakefulnessModel r7 = (com.android.systemui.power.shared.model.WakefulnessModel) r7
                        com.android.systemui.keyguard.shared.model.KeyguardState$Companion r2 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion
                        com.android.systemui.keyguard.shared.model.KeyguardState r4 = r8.from
                        r2.getClass()
                        boolean r2 = com.android.systemui.keyguard.shared.model.KeyguardState.Companion.deviceIsAsleepInState(r4)
                        r4 = 0
                        com.android.systemui.keyguard.shared.model.KeyguardState r5 = r8.to
                        if (r2 == 0) goto L5a
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                        if (r5 != r2) goto L5a
                        boolean r7 = r7.powerButtonLaunchGestureTriggered
                        if (r7 == 0) goto L5a
                        r7 = r3
                        goto L5b
                    L5a:
                        r7 = r4
                    L5b:
                        com.android.systemui.keyguard.shared.model.KeyguardState r2 = com.android.systemui.keyguard.shared.model.KeyguardState.OCCLUDED
                        if (r5 != r2) goto L67
                        if (r7 != 0) goto L67
                        com.android.systemui.statusbar.domain.interactor.OccludedState r7 = new com.android.systemui.statusbar.domain.interactor.OccludedState
                        r7.<init>(r3)
                        goto L76
                    L67:
                        com.android.systemui.keyguard.shared.model.KeyguardState r7 = r8.from
                        if (r7 != r2) goto L75
                        com.android.systemui.keyguard.shared.model.KeyguardState r7 = com.android.systemui.keyguard.shared.model.KeyguardState.LOCKSCREEN
                        if (r5 != r7) goto L75
                        com.android.systemui.statusbar.domain.interactor.OccludedState r7 = new com.android.systemui.statusbar.domain.interactor.OccludedState
                        r7.<init>(r4)
                        goto L76
                    L75:
                        r7 = 0
                    L76:
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r6 = r6.$this_unsafeFlow
                        java.lang.Object r6 = r6.emit(r7, r0)
                        if (r6 != r1) goto L81
                        return r1
                    L81:
                        kotlin.Unit r6 = kotlin.Unit.INSTANCE
                        return r6
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                switch (i2) {
                    case 0:
                        Object collect = ((SafeFlow) combine).collect(new AnonymousClass2(flowCollector), continuation);
                        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                    default:
                        Object collect2 = ((FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1) combine).collect(new StatusBarKeyguardViewManagerInteractor$special$$inlined$map$2$2(flowCollector), continuation);
                        if (collect2 != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            break;
                        }
                        break;
                }
                return Unit.INSTANCE;
            }
        }), new Function1() { // from class: com.android.systemui.statusbar.domain.interactor.StatusBarKeyguardViewManagerInteractor$keyguardViewOcclusionState$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((OccludedState) obj).occluded);
            }
        }, FlowKt__DistinctKt.defaultAreEquivalent);
        kotlinx.coroutines.flow.FlowKt.distinctUntilChanged(new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(windowManagerLockscreenVisibilityInteractor.lockscreenVisibility, keyguardSurfaceBehindInteractor.isAnimatingSurface, new StatusBarKeyguardViewManagerInteractor$keyguardViewVisibility$1(3, null)));
    }
}
