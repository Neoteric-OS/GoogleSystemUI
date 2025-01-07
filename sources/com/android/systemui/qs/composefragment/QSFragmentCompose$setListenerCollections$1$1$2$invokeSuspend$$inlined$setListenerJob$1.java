package com.android.systemui.qs.composefragment;

import com.android.systemui.plugins.qs.QS;
import com.android.systemui.plugins.qs.QSContainerController;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Flow $dataFlow;
    final /* synthetic */ MutableStateFlow $listenerFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.qs.composefragment.QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1$1, reason: invalid class name */
    public final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ CoroutineScope $$this$coroutineScope;
        final /* synthetic */ Flow $dataFlow;
        /* synthetic */ Object L$0;
        int label;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        /* renamed from: com.android.systemui.qs.composefragment.QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1$1$1, reason: invalid class name and collision with other inner class name */
        public final class C01471 extends SuspendLambda implements Function2 {
            final /* synthetic */ Object $currentListener;
            final /* synthetic */ Flow $dataFlow;
            int label;

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.qs.composefragment.QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1$1$1$1, reason: invalid class name and collision with other inner class name */
            public final class C01481 implements FlowCollector {
                public final /* synthetic */ Object $currentListener;
                public final /* synthetic */ int $r8$classId;

                public /* synthetic */ C01481(int i, Object obj) {
                    this.$r8$classId = i;
                    this.$currentListener = obj;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    switch (this.$r8$classId) {
                        case 0:
                            ((Boolean) obj).getClass();
                            ((QS.HeightListener) this.$currentListener).onQsHeightChanged();
                            break;
                        default:
                            ((QSContainerController) this.$currentListener).setCustomizerShowing(((Boolean) obj).booleanValue());
                            break;
                    }
                    return Unit.INSTANCE;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C01471(Flow flow, Object obj, Continuation continuation) {
                super(2, continuation);
                this.$dataFlow = flow;
                this.$currentListener = obj;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object obj, Continuation continuation) {
                return new C01471(this.$dataFlow, this.$currentListener, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ((C01471) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow flow = this.$dataFlow;
                    C01481 c01481 = new C01481(0, this.$currentListener);
                    this.label = 1;
                    if (flow.collect(c01481, this) == coroutineSingletons) {
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
        public AnonymousClass1(CoroutineScope coroutineScope, Flow flow, Continuation continuation) {
            super(2, continuation);
            this.$dataFlow = flow;
            this.$$this$coroutineScope = coroutineScope;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$coroutineScope, this.$dataFlow, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create(obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            Object obj2 = this.L$0;
            if (obj2 != null) {
                BuildersKt.launch$default(this.$$this$coroutineScope, null, null, new C01471(this.$dataFlow, obj2, null), 3);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1(MutableStateFlow mutableStateFlow, Flow flow, Continuation continuation) {
        super(2, continuation);
        this.$listenerFlow = mutableStateFlow;
        this.$dataFlow = flow;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1 qSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1 = new QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1(this.$listenerFlow, this.$dataFlow, continuation);
        qSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1.L$0 = obj;
        return qSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((QSFragmentCompose$setListenerCollections$1$1$2$invokeSuspend$$inlined$setListenerJob$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                MutableStateFlow mutableStateFlow = this.$listenerFlow;
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(coroutineScope, this.$dataFlow, null);
                this.label = 1;
                if (FlowKt.collectLatest(mutableStateFlow, anonymousClass1, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    if (i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                    throw new KotlinNothingValueException();
                }
                ResultKt.throwOnFailure(obj);
            }
            this.label = 2;
            DelayKt.awaitCancellation(this);
            return coroutineSingletons;
        } catch (Throwable th) {
            ((StateFlowImpl) this.$listenerFlow).setValue(null);
            throw th;
        }
    }
}
