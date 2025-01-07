package com.android.systemui.qs.pipeline.domain.interactor;

import android.content.pm.UserInfo;
import com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository;
import com.android.systemui.settings.UserTrackerImpl;
import com.android.systemui.user.data.repository.UserRepositoryImpl$special$$inlined$map$2;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$startTileCollection$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CurrentTilesInteractorImpl this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        int label;
        final /* synthetic */ CurrentTilesInteractorImpl this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(CurrentTilesInteractorImpl currentTilesInteractorImpl, Continuation continuation) {
            super(2, continuation);
            this.this$0 = currentTilesInteractorImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.this$0, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i != 0) {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            ResultKt.throwOnFailure(obj);
            final CurrentTilesInteractorImpl currentTilesInteractorImpl = this.this$0;
            UserRepositoryImpl$special$$inlined$map$2 userRepositoryImpl$special$$inlined$map$2 = currentTilesInteractorImpl.userRepository.selectedUserInfo;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl.startTileCollection.1.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    CurrentTilesInteractorImpl currentTilesInteractorImpl2 = CurrentTilesInteractorImpl.this;
                    StateFlowImpl stateFlowImpl = currentTilesInteractorImpl2.currentUser;
                    Integer num = new Integer(((UserInfo) obj2).id);
                    stateFlowImpl.getClass();
                    stateFlowImpl.updateState(null, num);
                    currentTilesInteractorImpl2._userContext.setValue(((UserTrackerImpl) currentTilesInteractorImpl2.userTracker).getUserContext());
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            userRepositoryImpl$special$$inlined$map$2.collect(flowCollector, this);
            return coroutineSingletons;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1$2, reason: invalid class name */
    final class AnonymousClass2 extends SuspendLambda implements Function2 {
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ CurrentTilesInteractorImpl this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1$2$1, reason: invalid class name */
        final class AnonymousClass1 extends SuspendLambda implements Function2 {
            final /* synthetic */ CoroutineScope $$this$launch;
            int I$0;
            /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            Object L$3;
            Object L$4;
            Object L$5;
            boolean Z$0;
            int label;
            final /* synthetic */ CurrentTilesInteractorImpl this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1$2$1$5, reason: invalid class name */
            final class AnonymousClass5 extends SuspendLambda implements Function2 {
                int label;
                final /* synthetic */ CurrentTilesInteractorImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass5(CurrentTilesInteractorImpl currentTilesInteractorImpl, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = currentTilesInteractorImpl;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass5(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass5) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Code restructure failed: missing block: B:19:0x0053, code lost:
                
                    if (r4 == r0) goto L18;
                 */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                    /*
                        r4 = this;
                        kotlin.coroutines.intrinsics.CoroutineSingletons r0 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r1 = r4.label
                        kotlin.Unit r2 = kotlin.Unit.INSTANCE
                        r3 = 1
                        if (r1 == 0) goto L17
                        if (r1 != r3) goto Lf
                        kotlin.ResultKt.throwOnFailure(r5)
                        goto L58
                    Lf:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L17:
                        kotlin.ResultKt.throwOnFailure(r5)
                        com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl r5 = r4.this$0
                        com.android.systemui.qs.pipeline.data.repository.TileSpecSettingsRepository r1 = r5.tileSpecRepository
                        kotlinx.coroutines.flow.StateFlowImpl r5 = r5.currentUser
                        java.lang.Object r5 = r5.getValue()
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        r4.label = r3
                        com.android.systemui.retail.data.repository.RetailModeSettingsRepository r3 = r1.retailModeRepository
                        boolean r3 = r3.getInRetailMode()
                        if (r3 == 0) goto L36
                    L34:
                        r4 = r2
                        goto L55
                    L36:
                        android.util.SparseArray r1 = r1.userTileRepositories
                        java.lang.Object r5 = r1.get(r5)
                        com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository r5 = (com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository) r5
                        if (r5 == 0) goto L34
                        kotlinx.coroutines.flow.SharedFlowImpl r1 = r5.changeEvents
                        com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$PrependDefault r3 = new com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository$PrependDefault
                        java.util.List r5 = r5.getDefaultTiles()
                        r3.<init>(r5)
                        java.lang.Object r4 = r1.emit(r3, r4)
                        if (r4 != r0) goto L52
                        goto L53
                    L52:
                        r4 = r2
                    L53:
                        if (r4 != r0) goto L34
                    L55:
                        if (r4 != r0) goto L58
                        return r0
                    L58:
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1.AnonymousClass2.AnonymousClass1.AnonymousClass5.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1$2$1$6, reason: invalid class name */
            final class AnonymousClass6 extends SuspendLambda implements Function2 {
                final /* synthetic */ List $resolvedSpecs;
                int label;
                final /* synthetic */ CurrentTilesInteractorImpl this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass6(CurrentTilesInteractorImpl currentTilesInteractorImpl, List list, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = currentTilesInteractorImpl;
                    this.$resolvedSpecs = list;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass6(this.this$0, this.$resolvedSpecs, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CurrentTilesInteractorImpl currentTilesInteractorImpl = this.this$0;
                        TileSpecSettingsRepository tileSpecSettingsRepository = currentTilesInteractorImpl.tileSpecRepository;
                        int intValue = ((Number) currentTilesInteractorImpl.currentUser.getValue()).intValue();
                        List list = this.$resolvedSpecs;
                        this.label = 1;
                        if (tileSpecSettingsRepository.setTiles(intValue, list, this) == coroutineSingletons) {
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
            public AnonymousClass1(CurrentTilesInteractorImpl currentTilesInteractorImpl, CoroutineScope coroutineScope, Continuation continuation) {
                super(2, continuation);
                this.this$0 = currentTilesInteractorImpl;
                this.$$this$launch = coroutineScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, this.$$this$launch, continuation);
                anonymousClass1.L$0 = obj;
                return anonymousClass1;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((AnonymousClass1) create((DataWithUserChange) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:12:0x00f1  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x011a  */
            /* JADX WARN: Removed duplicated region for block: B:48:0x0182  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x01a9 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:8:0x019f  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:37:0x015e -> B:7:0x019d). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:43:0x0198 -> B:6:0x019b). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r17) {
                /*
                    Method dump skipped, instructions count: 670
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.qs.pipeline.domain.interactor.CurrentTilesInteractorImpl$startTileCollection$1.AnonymousClass2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(CurrentTilesInteractorImpl currentTilesInteractorImpl, Continuation continuation) {
            super(2, continuation);
            this.this$0 = currentTilesInteractorImpl;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.this$0, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
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
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                CurrentTilesInteractorImpl currentTilesInteractorImpl = this.this$0;
                Flow flow = currentTilesInteractorImpl.userAndTiles;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(currentTilesInteractorImpl, coroutineScope, null);
                this.label = 1;
                if (FlowKt.collectLatest(flow, anonymousClass1, this) == coroutineSingletons) {
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
    public CurrentTilesInteractorImpl$startTileCollection$1(CurrentTilesInteractorImpl currentTilesInteractorImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = currentTilesInteractorImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CurrentTilesInteractorImpl$startTileCollection$1 currentTilesInteractorImpl$startTileCollection$1 = new CurrentTilesInteractorImpl$startTileCollection$1(this.this$0, continuation);
        currentTilesInteractorImpl$startTileCollection$1.L$0 = obj;
        return currentTilesInteractorImpl$startTileCollection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CurrentTilesInteractorImpl$startTileCollection$1 currentTilesInteractorImpl$startTileCollection$1 = (CurrentTilesInteractorImpl$startTileCollection$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        currentTilesInteractorImpl$startTileCollection$1.invokeSuspend(unit);
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
        BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass1(this.this$0, null), 3);
        CurrentTilesInteractorImpl currentTilesInteractorImpl = this.this$0;
        BuildersKt.launch$default(coroutineScope, currentTilesInteractorImpl.backgroundDispatcher, null, new AnonymousClass2(currentTilesInteractorImpl, null), 2);
        return Unit.INSTANCE;
    }
}
