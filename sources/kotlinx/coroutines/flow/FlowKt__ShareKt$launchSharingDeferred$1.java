package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredImpl;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.JobKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlowKt__ShareKt$launchSharingDeferred$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ CompletableDeferred $result;
    final /* synthetic */ Flow $upstream;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ShareKt$launchSharingDeferred$1(Flow flow, CompletableDeferred completableDeferred, Continuation continuation) {
        super(2, continuation);
        this.$upstream = flow;
        this.$result = completableDeferred;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowKt__ShareKt$launchSharingDeferred$1 flowKt__ShareKt$launchSharingDeferred$1 = new FlowKt__ShareKt$launchSharingDeferred$1(this.$upstream, this.$result, continuation);
        flowKt__ShareKt$launchSharingDeferred$1.L$0 = obj;
        return flowKt__ShareKt$launchSharingDeferred$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__ShareKt$launchSharingDeferred$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Object, kotlin.Unit] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                Flow flow = this.$upstream;
                final CompletableDeferred completableDeferred = this.$result;
                FlowCollector flowCollector = new FlowCollector() { // from class: kotlinx.coroutines.flow.FlowKt__ShareKt$launchSharingDeferred$1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object obj2, Continuation continuation) {
                        Unit unit;
                        Ref$ObjectRef ref$ObjectRef2 = Ref$ObjectRef.this;
                        MutableStateFlow mutableStateFlow = (MutableStateFlow) ref$ObjectRef2.element;
                        Unit unit2 = Unit.INSTANCE;
                        if (mutableStateFlow != null) {
                            ((StateFlowImpl) mutableStateFlow).setValue(obj2);
                            unit = unit2;
                        } else {
                            unit = null;
                        }
                        if (unit == null) {
                            StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(obj2);
                            JobKt.getJob(coroutineScope.getCoroutineContext());
                            ((CompletableDeferredImpl) completableDeferred).makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new ReadonlyStateFlow(MutableStateFlow));
                            ref$ObjectRef2.element = MutableStateFlow;
                        }
                        return unit2;
                    }
                };
                this.label = 1;
                if (flow.collect(flowCollector, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            this = Unit.INSTANCE;
            return this;
        } catch (Throwable th) {
            CompletableDeferredImpl completableDeferredImpl = (CompletableDeferredImpl) this.$result;
            completableDeferredImpl.getClass();
            completableDeferredImpl.makeCompleting$external__kotlinx_coroutines__linux_glibc_common__kotlinx_coroutines_host(new CompletedExceptionally(th, false));
            throw th;
        }
    }
}
