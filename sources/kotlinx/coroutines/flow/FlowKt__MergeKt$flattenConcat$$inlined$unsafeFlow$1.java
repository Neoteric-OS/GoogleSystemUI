package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1 implements Flow {
    public final /* synthetic */ Flow $this_flattenConcat$inlined;

    public FlowKt__MergeKt$flattenConcat$$inlined$unsafeFlow$1(Flow flow) {
        this.$this_flattenConcat$inlined = flow;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.$this_flattenConcat$inlined.collect(new FlowKt__MergeKt$flattenConcat$1$1(flowCollector), continuation);
        return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
    }
}
