package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.gates.Gate;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class Gate$setBlocking$1$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Gate.Listener $it;
    int label;
    final /* synthetic */ Gate this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Gate$setBlocking$1$1$1(Gate.Listener listener, Gate gate, Continuation continuation) {
        super(2, continuation);
        this.$it = listener;
        this.this$0 = gate;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new Gate$setBlocking$1$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Gate$setBlocking$1$1$1 gate$setBlocking$1$1$1 = (Gate$setBlocking$1$1$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        gate$setBlocking$1$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$it.onGateChanged(this.this$0);
        return Unit.INSTANCE;
    }
}
