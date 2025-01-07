package kotlinx.coroutines.flow;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FlowKt__DelayKt$debounceInternal$1$3$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ FlowCollector $downstream;
    final /* synthetic */ Ref$ObjectRef $lastValue;
    /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__DelayKt$debounceInternal$1$3$2(Continuation continuation, Ref$ObjectRef ref$ObjectRef, FlowCollector flowCollector) {
        super(2, continuation);
        this.$lastValue = ref$ObjectRef;
        this.$downstream = flowCollector;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        FlowKt__DelayKt$debounceInternal$1$3$2 flowKt__DelayKt$debounceInternal$1$3$2 = new FlowKt__DelayKt$debounceInternal$1$3$2(continuation, this.$lastValue, this.$downstream);
        flowKt__DelayKt$debounceInternal$1$3$2.L$0 = obj;
        return flowKt__DelayKt$debounceInternal$1$3$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FlowKt__DelayKt$debounceInternal$1$3$2) create(new ChannelResult(((ChannelResult) obj).holder), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$ObjectRef ref$ObjectRef;
        Ref$ObjectRef ref$ObjectRef2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Object obj2 = ((ChannelResult) this.L$0).holder;
            ref$ObjectRef = this.$lastValue;
            boolean z = obj2 instanceof ChannelResult.Failed;
            if (!z) {
                ref$ObjectRef.element = obj2;
            }
            FlowCollector flowCollector = this.$downstream;
            if (z) {
                Throwable m1791exceptionOrNullimpl = ChannelResult.m1791exceptionOrNullimpl(obj2);
                if (m1791exceptionOrNullimpl != null) {
                    throw m1791exceptionOrNullimpl;
                }
                Object obj3 = ref$ObjectRef.element;
                if (obj3 != null) {
                    if (obj3 == NullSurrogateKt.NULL) {
                        obj3 = null;
                    }
                    this.L$0 = obj2;
                    this.L$1 = ref$ObjectRef;
                    this.label = 1;
                    if (flowCollector.emit(obj3, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                    ref$ObjectRef2 = ref$ObjectRef;
                }
                ref$ObjectRef.element = NullSurrogateKt.DONE;
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ref$ObjectRef2 = (Ref$ObjectRef) this.L$1;
        ResultKt.throwOnFailure(obj);
        ref$ObjectRef = ref$ObjectRef2;
        ref$ObjectRef.element = NullSurrogateKt.DONE;
        return Unit.INSTANCE;
    }
}
