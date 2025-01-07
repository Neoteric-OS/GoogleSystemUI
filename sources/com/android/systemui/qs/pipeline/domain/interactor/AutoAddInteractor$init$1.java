package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.qs.pipeline.domain.model.AutoAddTracking;
import com.android.systemui.qs.pipeline.domain.model.AutoAddable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class AutoAddInteractor$init$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ CurrentTilesInteractor $currentTilesInteractor;
    int label;
    final /* synthetic */ AutoAddInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$init$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        /* synthetic */ int I$0;
        int label;
        final /* synthetic */ AutoAddInteractor this$0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$init$1$1$1, reason: invalid class name and collision with other inner class name */
        final class C01621 extends SuspendLambda implements Function2 {
            final /* synthetic */ int $userId;
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ AutoAddInteractor this$0;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$init$1$1$1$1, reason: invalid class name and collision with other inner class name */
            final class C01631 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $userId;
                private /* synthetic */ Object L$0;
                int label;
                final /* synthetic */ AutoAddInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C01631(AutoAddInteractor autoAddInteractor, int i, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = autoAddInteractor;
                    this.$userId = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    C01631 c01631 = new C01631(this.this$0, this.$userId, continuation);
                    c01631.L$0 = obj;
                    return c01631;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((C01631) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                        AutoAddInteractor autoAddInteractor = this.this$0;
                        int i2 = this.$userId;
                        this.label = 1;
                        if (AutoAddInteractor.access$collectAutoAddSignalsForUser(autoAddInteractor, coroutineScope, i2, this) == coroutineSingletons) {
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
            /* renamed from: com.android.systemui.qs.pipeline.domain.interactor.AutoAddInteractor$init$1$1$1$2, reason: invalid class name */
            final class AnonymousClass2 extends SuspendLambda implements Function2 {
                final /* synthetic */ int $userId;
                int label;
                final /* synthetic */ AutoAddInteractor this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass2(AutoAddInteractor autoAddInteractor, int i, Continuation continuation) {
                    super(2, continuation);
                    this.this$0 = autoAddInteractor;
                    this.$userId = i;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object obj, Continuation continuation) {
                    return new AnonymousClass2(this.this$0, this.$userId, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ((AnonymousClass2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                    int i = this.label;
                    Unit unit = Unit.INSTANCE;
                    if (i != 0) {
                        if (i != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        return unit;
                    }
                    ResultKt.throwOnFailure(obj);
                    AutoAddInteractor autoAddInteractor = this.this$0;
                    int i2 = this.$userId;
                    this.label = 1;
                    Set set = autoAddInteractor.autoAddables;
                    ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
                    Iterator it = set.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((AutoAddable) it.next()).getAutoAddTracking());
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj2 : arrayList) {
                        if (obj2 instanceof AutoAddTracking.IfNotAdded) {
                            arrayList2.add(obj2);
                        }
                    }
                    ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
                    Iterator it2 = arrayList2.iterator();
                    while (it2.hasNext()) {
                        arrayList3.add(((AutoAddTracking.IfNotAdded) it2.next()).spec);
                    }
                    CurrentTilesInteractor currentTilesInteractor = autoAddInteractor.currentTilesInteractor;
                    if (currentTilesInteractor == null) {
                        currentTilesInteractor = null;
                    }
                    ((CurrentTilesInteractorImpl) currentTilesInteractor).currentTiles.collect(new AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$$inlined$map$1$2(new AutoAddInteractor$markTrackIfNotAddedTilesThatAreCurrent$3(arrayList3, autoAddInteractor, i2)), this);
                    CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
                    return coroutineSingletons;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01621(AutoAddInteractor autoAddInteractor, int i, Continuation continuation) {
                super(2, continuation);
                this.this$0 = autoAddInteractor;
                this.$userId = i;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                C01621 c01621 = new C01621(this.this$0, this.$userId, continuation);
                c01621.L$0 = obj;
                return c01621;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                C01621 c01621 = (C01621) create((CoroutineScope) obj, (Continuation) obj2);
                Unit unit = Unit.INSTANCE;
                c01621.invokeSuspend(unit);
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
                BuildersKt.launch$default(coroutineScope, null, null, new C01631(this.this$0, this.$userId, null), 3);
                BuildersKt.launch$default(coroutineScope, null, null, new AnonymousClass2(this.this$0, this.$userId, null), 3);
                return Unit.INSTANCE;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(AutoAddInteractor autoAddInteractor, Continuation continuation) {
            super(2, continuation);
            this.this$0 = autoAddInteractor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, continuation);
            anonymousClass1.I$0 = ((Number) obj).intValue();
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                C01621 c01621 = new C01621(this.this$0, this.I$0, null);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(this, c01621) == coroutineSingletons) {
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
    public AutoAddInteractor$init$1(CurrentTilesInteractor currentTilesInteractor, AutoAddInteractor autoAddInteractor, Continuation continuation) {
        super(2, continuation);
        this.$currentTilesInteractor = currentTilesInteractor;
        this.this$0 = autoAddInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new AutoAddInteractor$init$1(this.$currentTilesInteractor, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((AutoAddInteractor$init$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ReadonlyStateFlow readonlyStateFlow = ((CurrentTilesInteractorImpl) this.$currentTilesInteractor).userId;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.this$0, null);
            this.label = 1;
            if (FlowKt.collectLatest(readonlyStateFlow, anonymousClass1, this) == coroutineSingletons) {
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
