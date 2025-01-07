package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ChannelFlowOperator extends ChannelFlow {
    public final Flow flow;

    public ChannelFlowOperator(int i, CoroutineContext coroutineContext, BufferOverflow bufferOverflow, Flow flow) {
        super(coroutineContext, i, bufferOverflow);
        this.flow = flow;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect;
        Unit unit = Unit.INSTANCE;
        if (this.capacity == -3) {
            CoroutineContext context = continuation.getContext();
            CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(context, this.context);
            if (Intrinsics.areEqual(newCoroutineContext, context)) {
                collect = flowCollect(flowCollector, continuation);
                if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return unit;
                }
            } else {
                ContinuationInterceptor.Key key = ContinuationInterceptor.Key.$$INSTANCE;
                if (Intrinsics.areEqual(newCoroutineContext.get(key), context.get(key))) {
                    CoroutineContext context2 = continuation.getContext();
                    if (!(flowCollector instanceof SendingCollector ? true : flowCollector instanceof NopCollector)) {
                        flowCollector = new UndispatchedContextCollector(flowCollector, context2);
                    }
                    collect = ChannelFlowKt.withContextUndispatched(newCoroutineContext, flowCollector, ThreadContextKt.threadContextElements(newCoroutineContext), new ChannelFlowOperator$collectWithContextUndispatched$2(this, null), continuation);
                    if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
                        return unit;
                    }
                }
            }
            return collect;
        }
        collect = super.collect(flowCollector, continuation);
        if (collect != CoroutineSingletons.COROUTINE_SUSPENDED) {
            return unit;
        }
        return collect;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final Object collectTo(ProducerScope producerScope, Continuation continuation) {
        Object flowCollect = flowCollect(new SendingCollector(producerScope), continuation);
        return flowCollect == CoroutineSingletons.COROUTINE_SUSPENDED ? flowCollect : Unit.INSTANCE;
    }

    public abstract Object flowCollect(FlowCollector flowCollector, Continuation continuation);

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final String toString() {
        return this.flow + " -> " + super.toString();
    }
}
