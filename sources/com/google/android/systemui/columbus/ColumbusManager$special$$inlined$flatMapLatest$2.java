package com.google.android.systemui.columbus;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ColumbusManager$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ ColumbusManager this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ColumbusManager$special$$inlined$flatMapLatest$2(ColumbusManager columbusManager, Continuation continuation) {
        super(3, continuation);
        this.this$0 = columbusManager;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        ColumbusManager$special$$inlined$flatMapLatest$2 columbusManager$special$$inlined$flatMapLatest$2 = new ColumbusManager$special$$inlined$flatMapLatest$2(this.this$0, (Continuation) obj3);
        columbusManager$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        columbusManager$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return columbusManager$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Pair pair = (Pair) this.L$1;
            if (pair.component1() != null) {
                throw new ClassCastException();
            }
            if (pair.component2() != null) {
                throw new ClassCastException();
            }
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            if (unit == coroutineSingletons) {
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
