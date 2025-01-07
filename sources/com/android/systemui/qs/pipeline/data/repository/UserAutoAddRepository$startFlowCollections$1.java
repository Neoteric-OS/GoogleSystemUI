package com.android.systemui.qs.pipeline.data.repository;

import android.database.ContentObserver;
import com.android.systemui.utils.coroutines.flow.FlowConflatedKt;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserAutoAddRepository$startFlowCollections$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ StateFlow $autoAdded;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ UserAutoAddRepository this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ StateFlow $autoAdded;
        int label;
        final /* synthetic */ UserAutoAddRepository this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(UserAutoAddRepository userAutoAddRepository, Continuation continuation, StateFlow stateFlow) {
            super(2, continuation);
            this.$autoAdded = stateFlow;
            this.this$0 = userAutoAddRepository;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation, this.$autoAdded);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                StateFlow stateFlow = this.$autoAdded;
                final UserAutoAddRepository userAutoAddRepository = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.startFlowCollections.1.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object access$store = UserAutoAddRepository.access$store(UserAutoAddRepository.this, (Set) obj2, continuation);
                        return access$store == CoroutineSingletons.COROUTINE_SUSPENDED ? access$store : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (stateFlow.collect(flowCollector, this) == coroutineSingletons) {
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
    /* renamed from: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        final /* synthetic */ StateFlow $autoAdded;
        int label;
        final /* synthetic */ UserAutoAddRepository this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ UserAutoAddRepository this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass1(UserAutoAddRepository userAutoAddRepository, Continuation continuation) {
                super(2, continuation);
                this.this$0 = userAutoAddRepository;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r1v1, types: [android.database.ContentObserver, com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$1$observer$1] */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    final ProducerScope producerScope = (ProducerScope) this.L$0;
                    final ?? r1 = new ContentObserver() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$1$observer$1
                        {
                            super(null);
                        }

                        @Override // android.database.ContentObserver
                        public final void onChange(boolean z) {
                            ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                        }
                    };
                    UserAutoAddRepository userAutoAddRepository = this.this$0;
                    userAutoAddRepository.secureSettings.registerContentObserverForUserSync("qs_auto_tiles", r1, userAutoAddRepository.userId);
                    final UserAutoAddRepository userAutoAddRepository2 = this.this$0;
                    Function0 function0 = new Function0() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.startFlowCollections.1.2.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            UserAutoAddRepository.this.secureSettings.unregisterContentObserverSync(r1);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(UserAutoAddRepository userAutoAddRepository, Continuation continuation, StateFlow stateFlow) {
            super(2, continuation);
            this.this$0 = userAutoAddRepository;
            this.$autoAdded = stateFlow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass2(this.this$0, continuation, this.$autoAdded);
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
                final Flow conflatedCallbackFlow = FlowConflatedKt.conflatedCallbackFlow(new AnonymousClass1(this.this$0, null));
                final UserAutoAddRepository userAutoAddRepository = this.this$0;
                Flow flowOn = FlowKt.flowOn(new Flow() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;
                        public final /* synthetic */ UserAutoAddRepository this$0;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
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

                        public AnonymousClass2(FlowCollector flowCollector, UserAutoAddRepository userAutoAddRepository) {
                            this.$this_unsafeFlow = flowCollector;
                            this.this$0 = userAutoAddRepository;
                        }

                        /* JADX WARN: Removed duplicated region for block: B:19:0x0064 A[RETURN] */
                        /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
                        /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        /*
                            Code decompiled incorrectly, please refer to instructions dump.
                            To view partially-correct add '--show-bad-code' argument
                        */
                        public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                            /*
                                r6 = this;
                                boolean r0 = r8 instanceof com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r8
                                com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1$2$1
                                r0.<init>(r8)
                            L18:
                                java.lang.Object r8 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 0
                                r4 = 2
                                r5 = 1
                                if (r2 == 0) goto L3b
                                if (r2 == r5) goto L33
                                if (r2 != r4) goto L2b
                                kotlin.ResultKt.throwOnFailure(r8)
                                goto L65
                            L2b:
                                java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                                java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
                                r6.<init>(r7)
                                throw r6
                            L33:
                                java.lang.Object r6 = r0.L$0
                                kotlinx.coroutines.flow.FlowCollector r6 = (kotlinx.coroutines.flow.FlowCollector) r6
                                kotlin.ResultKt.throwOnFailure(r8)
                                goto L5a
                            L3b:
                                kotlin.ResultKt.throwOnFailure(r8)
                                kotlin.Unit r7 = (kotlin.Unit) r7
                                kotlinx.coroutines.flow.FlowCollector r7 = r6.$this_unsafeFlow
                                r0.L$0 = r7
                                r0.label = r5
                                com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository r6 = r6.this$0
                                r6.getClass()
                                com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$load$2 r8 = new com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$load$2
                                r8.<init>(r6, r3)
                                kotlinx.coroutines.CoroutineDispatcher r6 = r6.bgDispatcher
                                java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r6, r8, r0)
                                if (r8 != r1) goto L59
                                return r1
                            L59:
                                r6 = r7
                            L5a:
                                r0.L$0 = r3
                                r0.label = r4
                                java.lang.Object r6 = r6.emit(r8, r0)
                                if (r6 != r1) goto L65
                                return r1
                            L65:
                                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                                return r6
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository$startFlowCollections$1$2$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, userAutoAddRepository), continuation);
                        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                    }
                }, userAutoAddRepository.bgDispatcher);
                final StateFlow stateFlow = this.$autoAdded;
                final UserAutoAddRepository userAutoAddRepository2 = this.this$0;
                FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository.startFlowCollections.1.2.3
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object access$store;
                        Set set = (Set) StateFlow.this.getValue();
                        boolean areEqual = Intrinsics.areEqual((Set) obj2, set);
                        Unit unit = Unit.INSTANCE;
                        return (areEqual || (access$store = UserAutoAddRepository.access$store(userAutoAddRepository2, set, continuation)) != CoroutineSingletons.COROUTINE_SUSPENDED) ? unit : access$store;
                    }
                };
                this.label = 1;
                if (flowOn.collect(flowCollector, this) == coroutineSingletons) {
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

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAutoAddRepository$startFlowCollections$1(UserAutoAddRepository userAutoAddRepository, Continuation continuation, StateFlow stateFlow) {
        super(2, continuation);
        this.$autoAdded = stateFlow;
        this.this$0 = userAutoAddRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        UserAutoAddRepository$startFlowCollections$1 userAutoAddRepository$startFlowCollections$1 = new UserAutoAddRepository$startFlowCollections$1(this.this$0, continuation, this.$autoAdded);
        userAutoAddRepository$startFlowCollections$1.L$0 = obj;
        return userAutoAddRepository$startFlowCollections$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UserAutoAddRepository$startFlowCollections$1 userAutoAddRepository$startFlowCollections$1 = (UserAutoAddRepository$startFlowCollections$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        userAutoAddRepository$startFlowCollections$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, null, this.$autoAdded), 3);
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, null, this.$autoAdded), 3);
        return Unit.INSTANCE;
    }
}
