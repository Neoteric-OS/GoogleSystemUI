package com.android.systemui.keyguard.domain.interactor;

import android.util.Log;
import com.android.systemui.keyguard.data.repository.KeyguardTransitionRepositoryImpl;
import com.android.systemui.keyguard.shared.model.Edge;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.scene.domain.interactor.SceneInteractor;
import com.android.systemui.util.kotlin.WithPrev;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StartedEagerly;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTransitionInteractor {
    public static final String TAG = Reflection.getOrCreateKotlinClass(KeyguardTransitionInteractor.class).getSimpleName();
    public final ReadonlyStateFlow currentKeyguardState;
    public final ReadonlyStateFlow finishedKeyguardState;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 isInTransition;
    public final KeyguardTransitionRepositoryImpl repository;
    public final ReadonlyStateFlow sceneTransitionPair;
    public final ReadonlyStateFlow startedKeyguardTransitionStep;
    public final ReadonlySharedFlow startedStepWithPrecedingStep;
    public final ReadonlyStateFlow transitionState;
    public final Flow transitions;
    public final Map transitionMap = new LinkedHashMap();
    public final Map transitionValueCache = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2, reason: invalid class name */
        public final class AnonymousClass2 implements FlowCollector {
            public final /* synthetic */ KeyguardTransitionInteractor this$0;

            public AnonymousClass2(KeyguardTransitionInteractor keyguardTransitionInteractor) {
                this.this$0 = keyguardTransitionInteractor;
            }

            /* JADX WARN: Removed duplicated region for block: B:19:0x008c A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.android.systemui.keyguard.shared.model.TransitionStep r8, kotlin.coroutines.Continuation r9) {
                /*
                    r7 = this;
                    boolean r0 = r9 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2$emit$1
                    if (r0 == 0) goto L13
                    r0 = r9
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2$emit$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2$emit$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2$emit$1
                    r0.<init>(r7, r9)
                L18:
                    java.lang.Object r9 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L42
                    if (r2 == r4) goto L32
                    if (r2 != r3) goto L2a
                    kotlin.ResultKt.throwOnFailure(r9)
                    goto L8d
                L2a:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L32:
                    float r7 = r0.F$0
                    java.lang.Object r8 = r0.L$1
                    com.android.systemui.keyguard.shared.model.TransitionStep r8 = (com.android.systemui.keyguard.shared.model.TransitionStep) r8
                    java.lang.Object r2 = r0.L$0
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$1$2 r2 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass1.AnonymousClass2) r2
                    kotlin.ResultKt.throwOnFailure(r9)
                    r9 = r7
                    r7 = r2
                    goto L70
                L42:
                    kotlin.ResultKt.throwOnFailure(r9)
                    com.android.systemui.keyguard.shared.model.TransitionState r9 = r8.transitionState
                    com.android.systemui.keyguard.shared.model.TransitionState r2 = com.android.systemui.keyguard.shared.model.TransitionState.FINISHED
                    r5 = 1065353216(0x3f800000, float:1.0)
                    if (r9 != r2) goto L4f
                    r9 = r5
                    goto L51
                L4f:
                    float r9 = r8.value
                L51:
                    java.lang.String r2 = com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.TAG
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r2 = r7.this$0
                    com.android.systemui.keyguard.shared.model.KeyguardState r6 = r8.from
                    kotlinx.coroutines.flow.MutableSharedFlow r2 = r2.getTransitionValueFlow(r6)
                    float r5 = r5 - r9
                    java.lang.Float r6 = new java.lang.Float
                    r6.<init>(r5)
                    r0.L$0 = r7
                    r0.L$1 = r8
                    r0.F$0 = r9
                    r0.label = r4
                    java.lang.Object r2 = r2.emit(r6, r0)
                    if (r2 != r1) goto L70
                    return r1
                L70:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r7 = r7.this$0
                    com.android.systemui.keyguard.shared.model.KeyguardState r8 = r8.to
                    java.lang.String r2 = com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.TAG
                    kotlinx.coroutines.flow.MutableSharedFlow r7 = r7.getTransitionValueFlow(r8)
                    java.lang.Float r8 = new java.lang.Float
                    r8.<init>(r9)
                    r9 = 0
                    r0.L$0 = r9
                    r0.L$1 = r9
                    r0.label = r3
                    java.lang.Object r7 = r7.emit(r8, r0)
                    if (r7 != r1) goto L8d
                    return r1
                L8d:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass1.AnonymousClass2.emit(com.android.systemui.keyguard.shared.model.TransitionStep, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass1(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardTransitionInteractor.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                KeyguardTransitionInteractor keyguardTransitionInteractor = KeyguardTransitionInteractor.this;
                Flow flow = keyguardTransitionInteractor.repository.transitions;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(keyguardTransitionInteractor);
                this.label = 1;
                Object collect = flow.collect(new KeyguardTransitionInteractor$1$invokeSuspend$$inlined$filter$1$2(anonymousClass2), this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
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

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1, reason: invalid class name */
        public final class AnonymousClass1 implements FlowCollector {
            public final /* synthetic */ KeyguardTransitionInteractor this$0;

            public AnonymousClass1(KeyguardTransitionInteractor keyguardTransitionInteractor) {
                this.this$0 = keyguardTransitionInteractor;
            }

            /* JADX WARN: Removed duplicated region for block: B:20:0x00b6  */
            /* JADX WARN: Removed duplicated region for block: B:26:0x008e  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x0052  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.android.systemui.keyguard.shared.model.TransitionStep r12, kotlin.coroutines.Continuation r13) {
                /*
                    r11 = this;
                    boolean r0 = r13 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1$emit$1
                    if (r0 == 0) goto L13
                    r0 = r13
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1$emit$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1$emit$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1$emit$1
                    r0.<init>(r11, r13)
                L18:
                    java.lang.Object r13 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    kotlin.Unit r3 = kotlin.Unit.INSTANCE
                    r4 = 3
                    r5 = 2
                    r6 = 1
                    r7 = 0
                    if (r2 == 0) goto L52
                    if (r2 == r6) goto L45
                    if (r2 == r5) goto L39
                    if (r2 != r4) goto L31
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto Lc3
                L31:
                    java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                    java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
                    r11.<init>(r12)
                    throw r11
                L39:
                    java.lang.Object r11 = r0.L$1
                    com.android.systemui.keyguard.shared.model.TransitionStep r11 = (com.android.systemui.keyguard.shared.model.TransitionStep) r11
                    java.lang.Object r12 = r0.L$0
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1 r12 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass2.AnonymousClass1) r12
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L9e
                L45:
                    java.lang.Object r11 = r0.L$1
                    r12 = r11
                    com.android.systemui.keyguard.shared.model.TransitionStep r12 = (com.android.systemui.keyguard.shared.model.TransitionStep) r12
                    java.lang.Object r11 = r0.L$0
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$2$1 r11 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass2.AnonymousClass1) r11
                    kotlin.ResultKt.throwOnFailure(r13)
                    goto L79
                L52:
                    kotlin.ResultKt.throwOnFailure(r13)
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r13 = r11.this$0
                    java.util.Map r13 = r13.transitionMap
                    com.android.systemui.keyguard.shared.model.Edge$Companion r2 = com.android.systemui.keyguard.shared.model.Edge.Companion
                    com.android.systemui.keyguard.shared.model.KeyguardState r2 = r12.from
                    com.android.systemui.keyguard.shared.model.Edge$StateToState r8 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
                    com.android.systemui.keyguard.shared.model.KeyguardState r9 = r12.to
                    r8.<init>(r2, r9)
                    java.lang.Object r13 = r13.get(r8)
                    kotlinx.coroutines.flow.MutableSharedFlow r13 = (kotlinx.coroutines.flow.MutableSharedFlow) r13
                    if (r13 == 0) goto L79
                    r0.L$0 = r11
                    r0.L$1 = r12
                    r0.label = r6
                    java.lang.Object r13 = r13.emit(r12, r0)
                    if (r13 != r1) goto L79
                    return r1
                L79:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r13 = r11.this$0
                    java.util.Map r13 = r13.transitionMap
                    com.android.systemui.keyguard.shared.model.Edge$Companion r2 = com.android.systemui.keyguard.shared.model.Edge.Companion
                    com.android.systemui.keyguard.shared.model.KeyguardState r2 = r12.from
                    com.android.systemui.keyguard.shared.model.Edge$StateToState r6 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
                    r6.<init>(r2, r7)
                    java.lang.Object r13 = r13.get(r6)
                    kotlinx.coroutines.flow.MutableSharedFlow r13 = (kotlinx.coroutines.flow.MutableSharedFlow) r13
                    if (r13 == 0) goto La1
                    r0.L$0 = r11
                    r0.L$1 = r12
                    r0.label = r5
                    java.lang.Object r13 = r13.emit(r12, r0)
                    if (r13 != r1) goto L9b
                    return r1
                L9b:
                    r10 = r12
                    r12 = r11
                    r11 = r10
                L9e:
                    r10 = r12
                    r12 = r11
                    r11 = r10
                La1:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r11 = r11.this$0
                    java.util.Map r11 = r11.transitionMap
                    com.android.systemui.keyguard.shared.model.Edge$Companion r13 = com.android.systemui.keyguard.shared.model.Edge.Companion
                    com.android.systemui.keyguard.shared.model.KeyguardState r13 = r12.to
                    com.android.systemui.keyguard.shared.model.Edge$StateToState r2 = new com.android.systemui.keyguard.shared.model.Edge$StateToState
                    r2.<init>(r7, r13)
                    java.lang.Object r11 = r11.get(r2)
                    kotlinx.coroutines.flow.MutableSharedFlow r11 = (kotlinx.coroutines.flow.MutableSharedFlow) r11
                    if (r11 == 0) goto Lc3
                    r0.L$0 = r7
                    r0.L$1 = r7
                    r0.label = r4
                    java.lang.Object r11 = r11.emit(r12, r0)
                    if (r11 != r1) goto Lc3
                    return r1
                Lc3:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass2.AnonymousClass1.emit(com.android.systemui.keyguard.shared.model.TransitionStep, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass2(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardTransitionInteractor.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                KeyguardTransitionInteractor keyguardTransitionInteractor = KeyguardTransitionInteractor.this;
                Flow flow = keyguardTransitionInteractor.repository.transitions;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(keyguardTransitionInteractor);
                this.label = 1;
                if (flow.collect(anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$3, reason: invalid class name */
    final class AnonymousClass3 extends SuspendLambda implements Function2 {
        int label;

        public AnonymousClass3(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardTransitionInteractor.this.new AnonymousClass3(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final KeyguardTransitionInteractor keyguardTransitionInteractor = KeyguardTransitionInteractor.this;
                ReadonlySharedFlow readonlySharedFlow = keyguardTransitionInteractor.startedStepWithPrecedingStep;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.3.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        WithPrev withPrev = (WithPrev) obj2;
                        TransitionStep transitionStep = (TransitionStep) withPrev.previousValue;
                        TransitionStep transitionStep2 = (TransitionStep) withPrev.newValue;
                        TransitionState transitionState = transitionStep.transitionState;
                        TransitionState transitionState2 = TransitionState.CANCELED;
                        Unit unit = Unit.INSTANCE;
                        if (transitionState == transitionState2) {
                            KeyguardState keyguardState = transitionStep2.to;
                            KeyguardState keyguardState2 = transitionStep.from;
                            if (keyguardState != keyguardState2) {
                                String str = KeyguardTransitionInteractor.TAG;
                                Object emit = KeyguardTransitionInteractor.this.getTransitionValueFlow(keyguardState2).emit(new Float(0.0f), continuation);
                                return emit == CoroutineSingletons.COROUTINE_SUSPENDED ? emit : unit;
                            }
                        }
                        if (transitionState != TransitionState.RUNNING) {
                            return unit;
                        }
                        Log.e(KeyguardTransitionInteractor.TAG, "STARTED step (" + transitionStep2 + ") was preceded by a RUNNING step (" + transitionStep + "), which should never happen. Things could go badly here.");
                        return unit;
                    }
                };
                this.label = 1;
                if (readonlySharedFlow.$$delegate_0.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            throw new KotlinNothingValueException();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4, reason: invalid class name */
    final class AnonymousClass4 extends SuspendLambda implements Function2 {
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2, reason: invalid class name */
        public final class AnonymousClass2 implements FlowCollector {
            public final /* synthetic */ KeyguardTransitionInteractor this$0;

            public AnonymousClass2(KeyguardTransitionInteractor keyguardTransitionInteractor) {
                this.this$0 = keyguardTransitionInteractor;
            }

            /* JADX WARN: Removed duplicated region for block: B:13:0x0055  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x003d  */
            /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
            @Override // kotlinx.coroutines.flow.FlowCollector
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object emit(com.android.systemui.keyguard.shared.model.TransitionStep r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2$emit$1
                    if (r0 == 0) goto L13
                    r0 = r8
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2$emit$1 r0 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2$emit$1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r3 = r1 & r2
                    if (r3 == 0) goto L13
                    int r1 = r1 - r2
                    r0.label = r1
                    goto L18
                L13:
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2$emit$1 r0 = new com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2$emit$1
                    r0.<init>(r6, r8)
                L18:
                    java.lang.Object r8 = r0.result
                    kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                    int r2 = r0.label
                    r3 = 1
                    if (r2 == 0) goto L3d
                    if (r2 != r3) goto L35
                    java.lang.Object r6 = r0.L$2
                    java.util.Iterator r6 = (java.util.Iterator) r6
                    java.lang.Object r7 = r0.L$1
                    com.android.systemui.keyguard.shared.model.TransitionStep r7 = (com.android.systemui.keyguard.shared.model.TransitionStep) r7
                    java.lang.Object r2 = r0.L$0
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$4$2 r2 = (com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass4.AnonymousClass2) r2
                    kotlin.ResultKt.throwOnFailure(r8)
                    r8 = r7
                    r7 = r2
                    goto L4f
                L35:
                    java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                    java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                    r6.<init>(r7)
                    throw r6
                L3d:
                    kotlin.ResultKt.throwOnFailure(r8)
                    kotlin.enums.EnumEntries r8 = com.android.systemui.keyguard.shared.model.KeyguardState.$ENTRIES
                    kotlin.collections.AbstractList r8 = (kotlin.collections.AbstractList) r8
                    r8.getClass()
                    kotlin.collections.AbstractList$IteratorImpl r2 = new kotlin.collections.AbstractList$IteratorImpl
                    r2.<init>()
                    r8 = r7
                    r7 = r6
                    r6 = r2
                L4f:
                    boolean r2 = r6.hasNext()
                    if (r2 == 0) goto L95
                    java.lang.Object r2 = r6.next()
                    com.android.systemui.keyguard.shared.model.KeyguardState r2 = (com.android.systemui.keyguard.shared.model.KeyguardState) r2
                    com.android.systemui.keyguard.shared.model.KeyguardState r4 = r8.to
                    if (r2 == r4) goto L4f
                    com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor r4 = r7.this$0
                    java.lang.String r5 = com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.TAG
                    kotlinx.coroutines.flow.MutableSharedFlow r2 = r4.getTransitionValueFlow(r2)
                    java.util.List r4 = r2.getReplayCache()
                    boolean r5 = r4.isEmpty()
                    if (r5 != 0) goto L4f
                    java.lang.Object r4 = kotlin.collections.CollectionsKt.last(r4)
                    java.lang.Number r4 = (java.lang.Number) r4
                    float r4 = r4.floatValue()
                    r5 = 0
                    int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                    if (r4 != 0) goto L81
                    goto L4f
                L81:
                    java.lang.Float r4 = new java.lang.Float
                    r4.<init>(r5)
                    r0.L$0 = r7
                    r0.L$1 = r8
                    r0.L$2 = r6
                    r0.label = r3
                    java.lang.Object r2 = r2.emit(r4, r0)
                    if (r2 != r1) goto L4f
                    return r1
                L95:
                    kotlin.Unit r6 = kotlin.Unit.INSTANCE
                    return r6
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor.AnonymousClass4.AnonymousClass2.emit(com.android.systemui.keyguard.shared.model.TransitionStep, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }

        public AnonymousClass4(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return KeyguardTransitionInteractor.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass4) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            Unit unit = Unit.INSTANCE;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                KeyguardTransitionInteractor keyguardTransitionInteractor = KeyguardTransitionInteractor.this;
                Flow flow = keyguardTransitionInteractor.repository.transitions;
                AnonymousClass2 anonymousClass2 = new AnonymousClass2(keyguardTransitionInteractor);
                this.label = 1;
                Object collect = flow.collect(new KeyguardTransitionInteractor$4$invokeSuspend$$inlined$filter$1$2(anonymousClass2), this);
                if (collect != coroutineSingletons) {
                    collect = unit;
                }
                if (collect == coroutineSingletons) {
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

    public KeyguardTransitionInteractor(CoroutineScope coroutineScope, KeyguardTransitionRepositoryImpl keyguardTransitionRepositoryImpl, SceneInteractor sceneInteractor) {
        this.repository = keyguardTransitionRepositoryImpl;
        Flow flow = keyguardTransitionRepositoryImpl.transitions;
        this.transitions = flow;
        StartedEagerly startedEagerly = SharingStarted.Companion.Eagerly;
        int i = 31;
        this.transitionState = FlowKt.stateIn(flow, coroutineScope, startedEagerly, new TransitionStep(i));
        ReadonlyStateFlow readonlyStateFlow = sceneInteractor.transitionState;
        FlowKt.stateIn(com.android.systemui.util.kotlin.FlowKt.pairwise(readonlyStateFlow), coroutineScope, startedEagerly, new WithPrev(readonlyStateFlow.getValue(), readonlyStateFlow.getValue()));
        this.startedStepWithPrecedingStep = FlowKt.shareIn(new KeyguardTransitionInteractor$special$$inlined$map$1(com.android.systemui.util.kotlin.FlowKt.pairwise(keyguardTransitionRepositoryImpl.transitions), 2), coroutineScope, startedEagerly, 0);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass3(null), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass4(null), 3);
        this.startedKeyguardTransitionStep = FlowKt.stateIn(new KeyguardTransitionInteractor$special$$inlined$filter$2(keyguardTransitionRepositoryImpl.transitions, 0), coroutineScope, startedEagerly, new TransitionStep(i));
        ChannelFlowTransformLatest mapLatest = FlowKt.mapLatest(new KeyguardTransitionInteractor$currentKeyguardState$1(2, null), keyguardTransitionRepositoryImpl.transitions);
        KeyguardState keyguardState = KeyguardState.OFF;
        this.currentKeyguardState = FlowKt.stateIn(mapLatest, coroutineScope, startedEagerly, keyguardState);
        this.isInTransition = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(isInTransitionWhere(new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isInTransition$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        }, new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor$isInTransition$2
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Boolean.TRUE;
            }
        }), readonlyStateFlow, new KeyguardTransitionInteractor$isInTransition$3(3, null));
        this.finishedKeyguardState = FlowKt.stateIn(new KeyguardTransitionInteractor$special$$inlined$map$1(new KeyguardTransitionInteractor$special$$inlined$filter$2(keyguardTransitionRepositoryImpl.transitions, 2), 0), coroutineScope, startedEagerly, keyguardState);
    }

    public final KeyguardState getCurrentState() {
        return (KeyguardState) CollectionsKt.last(this.currentKeyguardState.getReplayCache());
    }

    public final MutableSharedFlow getTransitionValueFlow(KeyguardState keyguardState) {
        Map map = this.transitionValueCache;
        Object obj = map.get(keyguardState);
        Object obj2 = obj;
        if (obj == null) {
            SharedFlowImpl MutableSharedFlow = SharedFlowKt.MutableSharedFlow(1, 2, BufferOverflow.DROP_OLDEST);
            MutableSharedFlow.tryEmit(Float.valueOf(0.0f));
            map.put(keyguardState, MutableSharedFlow);
            obj2 = MutableSharedFlow;
        }
        return (MutableSharedFlow) obj2;
    }

    public final Flow isFinishedIn(KeyguardState keyguardState) {
        return FlowKt.distinctUntilChanged(isFinishedIn$1(keyguardState));
    }

    public final Flow isFinishedIn$1(KeyguardState keyguardState) {
        keyguardState.checkValidState();
        return FlowKt.distinctUntilChanged(new KeyguardTransitionInteractor$isFinishedIn$$inlined$map$2(this.finishedKeyguardState, keyguardState, 0));
    }

    public final Flow isInTransition(Edge edge, Edge.StateToState stateToState) {
        if (stateToState != null) {
            edge = stateToState;
        }
        return FlowKt.distinctUntilChanged(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new KeyguardTransitionInteractor$isInTransition$7(2, null), FlowKt.mapLatest(new KeyguardTransitionInteractor$isInTransition$6(2, null), transition(edge))));
    }

    public final Flow isInTransitionWhere(Function1 function1, Function1 function12) {
        return FlowKt.distinctUntilChanged(FlowKt.mapLatest(new KeyguardTransitionInteractor$isInTransitionWhere$4(function1, function12, null), new KeyguardTransitionInteractor$special$$inlined$filter$2(this.repository.transitions, 1)));
    }

    public final Flow transition(Edge edge) {
        if (edge instanceof Edge.StateToState) {
            Edge.StateToState stateToState = (Edge.StateToState) edge;
            KeyguardState keyguardState = stateToState.from;
            edge.getClass();
            Edge.verifyValidKeyguardStates(keyguardState, stateToState.to);
        } else if (edge instanceof Edge.SceneToState) {
            KeyguardState keyguardState2 = ((Edge.SceneToState) edge).to;
            edge.getClass();
            Edge.verifyValidKeyguardStates(null, keyguardState2);
        } else if (edge instanceof Edge.StateToScene) {
            KeyguardState keyguardState3 = ((Edge.StateToScene) edge).from;
            edge.getClass();
            Edge.verifyValidKeyguardStates(keyguardState3, null);
        }
        Edge.StateToState stateToState2 = (Edge.StateToState) edge;
        Map map = this.transitionMap;
        Object obj = map.get(stateToState2);
        if (obj == null) {
            obj = SharedFlowKt.MutableSharedFlow$default(0, 10, BufferOverflow.DROP_OLDEST, 1);
            map.put(stateToState2, obj);
        }
        return (Flow) obj;
    }
}
