package com.android.systemui.qs.tiles.base.viewmodel;

import android.os.UserHandle;
import com.android.systemui.qs.tiles.base.interactor.DataUpdateTrigger;
import com.android.systemui.qs.tiles.base.interactor.QSTileDataInteractor;
import com.android.systemui.qs.tiles.viewmodel.QSTilePolicy;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.CancellableFlow;
import kotlinx.coroutines.flow.CancellableFlowImpl;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharingStarted;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSTileViewModelImpl$createTileDataFlow$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ QSTileViewModelImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ FlowCollector $$this$transformLatest;
        final /* synthetic */ UserHandle $user;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ QSTileViewModelImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01681 extends SuspendLambda implements Function3 {
            /* synthetic */ Object L$0;
            int label;

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                C01681 c01681 = new C01681(3, (Continuation) obj3);
                c01681.L$0 = obj;
                return c01681.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return this.L$0;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(QSTileViewModelImpl qSTileViewModelImpl, UserHandle userHandle, FlowCollector flowCollector, Continuation continuation) {
            super(2, continuation);
            this.this$0 = qSTileViewModelImpl;
            this.$user = userHandle;
            this.$$this$transformLatest = flowCollector;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$user, this.$$this$transformLatest, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v5, types: [com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final QSTileViewModelImpl qSTileViewModelImpl = this.this$0;
                final UserHandle userHandle = this.$user;
                final QSTileViewModelImpl$filterFalseActions$$inlined$filter$1 qSTileViewModelImpl$filterFalseActions$$inlined$filter$1 = new QSTileViewModelImpl$filterFalseActions$$inlined$filter$1(qSTileViewModelImpl.userInputs, qSTileViewModelImpl);
                final QSTilePolicy qSTilePolicy = qSTileViewModelImpl.config.policy;
                if (!(qSTilePolicy instanceof QSTilePolicy.NoRestrictions)) {
                    if (!(qSTilePolicy instanceof QSTilePolicy.Restricted)) {
                        throw new NoWhenBranchMatchedException();
                    }
                    qSTileViewModelImpl$filterFalseActions$$inlined$filter$1 = new Flow() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1$2, reason: invalid class name */
                        public final class AnonymousClass2 implements FlowCollector {
                            public final /* synthetic */ QSTilePolicy $policy$inlined;
                            public final /* synthetic */ FlowCollector $this_unsafeFlow;
                            public final /* synthetic */ UserHandle $user$inlined;
                            public final /* synthetic */ QSTileViewModelImpl this$0;

                            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                            /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1$2$1, reason: invalid class name */
                            public final class AnonymousClass1 extends ContinuationImpl {
                                Object L$0;
                                Object L$1;
                                Object L$2;
                                Object L$3;
                                Object L$4;
                                Object L$5;
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

                            public AnonymousClass2(FlowCollector flowCollector, QSTilePolicy qSTilePolicy, QSTileViewModelImpl qSTileViewModelImpl, UserHandle userHandle) {
                                this.$this_unsafeFlow = flowCollector;
                                this.$policy$inlined = qSTilePolicy;
                                this.this$0 = qSTileViewModelImpl;
                                this.$user$inlined = userHandle;
                            }

                            /* JADX WARN: Removed duplicated region for block: B:19:0x00af  */
                            /* JADX WARN: Removed duplicated region for block: B:21:0x00c5  */
                            /* JADX WARN: Removed duplicated region for block: B:23:0x00d2  */
                            /* JADX WARN: Removed duplicated region for block: B:25:0x00e5  */
                            /* JADX WARN: Removed duplicated region for block: B:28:0x00d7  */
                            /* JADX WARN: Removed duplicated region for block: B:31:0x007a  */
                            /* JADX WARN: Removed duplicated region for block: B:35:0x00e2  */
                            /* JADX WARN: Removed duplicated region for block: B:36:0x00b1  */
                            /* JADX WARN: Removed duplicated region for block: B:41:0x0054  */
                            /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
                            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x009b -> B:17:0x00a1). Please report as a decompilation issue!!! */
                            @Override // kotlinx.coroutines.flow.FlowCollector
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object emit(java.lang.Object r13, kotlin.coroutines.Continuation r14) {
                                /*
                                    Method dump skipped, instructions count: 254
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$filterByPolicy$lambda$7$$inlined$filter$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                            }
                        }

                        @Override // kotlinx.coroutines.flow.Flow
                        public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                            Object collect = QSTileViewModelImpl$filterFalseActions$$inlined$filter$1.this.collect(new AnonymousClass2(flowCollector, qSTilePolicy, qSTileViewModelImpl, userHandle), continuation);
                            return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
                        }
                    };
                }
                Flow flowOn = FlowKt.flowOn(new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new QSTileViewModelImpl$special$$inlined$map$1(com.android.systemui.util.kotlin.FlowKt.throttle(qSTileViewModelImpl$filterFalseActions$$inlined$filter$1, 200L, qSTileViewModelImpl.systemClock), qSTileViewModelImpl, userHandle), new QSTileViewModelImpl$userInputFlow$2(qSTileViewModelImpl, null), 0), qSTileViewModelImpl.backgroundDispatcher);
                QSTileViewModelImpl qSTileViewModelImpl2 = this.this$0;
                final SharedFlowImpl sharedFlowImpl = qSTileViewModelImpl2.forceUpdates;
                ReadonlyStateFlow stateIn = FlowKt.stateIn(new FlowKt__EmittersKt$onStart$$inlined$unsafeFlow$1(new QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3(this.this$0, null), FlowKt.merge(flowOn, new FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1(new Flow() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1

                    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                    /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1$2, reason: invalid class name */
                    public final class AnonymousClass2 implements FlowCollector {
                        public final /* synthetic */ FlowCollector $this_unsafeFlow;

                        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                        /* renamed from: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1$2$1, reason: invalid class name */
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
                                boolean r0 = r6 instanceof com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1
                                if (r0 == 0) goto L13
                                r0 = r6
                                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1$2$1 r0 = (com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                                int r1 = r0.label
                                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                                r3 = r1 & r2
                                if (r3 == 0) goto L13
                                int r1 = r1 - r2
                                r0.label = r1
                                goto L18
                            L13:
                                com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1$2$1 r0 = new com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1$2$1
                                r0.<init>(r6)
                            L18:
                                java.lang.Object r6 = r0.result
                                kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                                int r2 = r0.label
                                r3 = 1
                                if (r2 == 0) goto L2f
                                if (r2 != r3) goto L27
                                kotlin.ResultKt.throwOnFailure(r6)
                                goto L41
                            L27:
                                java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                                java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                                r4.<init>(r5)
                                throw r4
                            L2f:
                                kotlin.ResultKt.throwOnFailure(r6)
                                kotlin.Unit r5 = (kotlin.Unit) r5
                                com.android.systemui.qs.tiles.base.interactor.DataUpdateTrigger$ForceUpdate r5 = com.android.systemui.qs.tiles.base.interactor.DataUpdateTrigger.ForceUpdate.INSTANCE
                                r0.label = r3
                                kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                                java.lang.Object r4 = r4.emit(r5, r0)
                                if (r4 != r1) goto L41
                                return r1
                            L41:
                                kotlin.Unit r4 = kotlin.Unit.INSTANCE
                                return r4
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl$createTileDataFlow$1$1$invokeSuspend$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                        }
                    }

                    @Override // kotlinx.coroutines.flow.Flow
                    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                        SharedFlowImpl.this.collect(new AnonymousClass2(flowCollector), continuation);
                        return CoroutineSingletons.COROUTINE_SUSPENDED;
                    }
                }, new QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$2(qSTileViewModelImpl2, null), 0))), coroutineScope, SharingStarted.Companion.Eagerly, DataUpdateTrigger.InitialRequest.INSTANCE);
                Flow flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(((QSTileDataInteractor) this.this$0.tileDataInteractor.invoke()).tileData(this.$user, stateIn), stateIn, new C01681(3, null));
                if (!(flowKt__ZipKt$combine$$inlined$unsafeFlow$1 instanceof CancellableFlow)) {
                    flowKt__ZipKt$combine$$inlined$unsafeFlow$1 = new CancellableFlowImpl(flowKt__ZipKt$combine$$inlined$unsafeFlow$1);
                }
                Flow flowOn2 = FlowKt.flowOn((CancellableFlow) flowKt__ZipKt$combine$$inlined$unsafeFlow$1, this.this$0.backgroundDispatcher);
                final FlowCollector flowCollector = this.$$this$transformLatest;
                FlowCollector flowCollector2 = new FlowCollector() { // from class: com.android.systemui.qs.tiles.base.viewmodel.QSTileViewModelImpl.createTileDataFlow.1.1.2
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Object emit = FlowCollector.this.emit(obj2, continuation);
                        return emit == CoroutineSingletons.COROUTINE_SUSPENDED ? emit : Unit.INSTANCE;
                    }
                };
                this.label = 1;
                if (flowOn2.collect(flowCollector2, this) == coroutineSingletons) {
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
    public QSTileViewModelImpl$createTileDataFlow$1(QSTileViewModelImpl qSTileViewModelImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = qSTileViewModelImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        QSTileViewModelImpl$createTileDataFlow$1 qSTileViewModelImpl$createTileDataFlow$1 = new QSTileViewModelImpl$createTileDataFlow$1(this.this$0, (Continuation) obj3);
        qSTileViewModelImpl$createTileDataFlow$1.L$0 = (FlowCollector) obj;
        qSTileViewModelImpl$createTileDataFlow$1.L$1 = (UserHandle) obj2;
        return qSTileViewModelImpl$createTileDataFlow$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, (UserHandle) this.L$1, flowCollector, null);
            this.L$0 = null;
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(this, anonymousClass1) == coroutineSingletons) {
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
