package com.android.systemui.qs.pipeline.domain.autoaddable;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CallbackControllerAutoAddable$autoAddSignal$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CallbackControllerAutoAddable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CallbackControllerAutoAddable$autoAddSignal$1(CallbackControllerAutoAddable callbackControllerAutoAddable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = callbackControllerAutoAddable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CallbackControllerAutoAddable$autoAddSignal$1 callbackControllerAutoAddable$autoAddSignal$1 = new CallbackControllerAutoAddable$autoAddSignal$1(this.this$0, continuation);
        callbackControllerAutoAddable$autoAddSignal$1.L$0 = obj;
        return callbackControllerAutoAddable$autoAddSignal$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CallbackControllerAutoAddable$autoAddSignal$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final Object callback = this.this$0.getCallback(producerScope);
            this.this$0.controller.addCallback(callback);
            final CallbackControllerAutoAddable callbackControllerAutoAddable = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.qs.pipeline.domain.autoaddable.CallbackControllerAutoAddable$autoAddSignal$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    CallbackControllerAutoAddable.this.controller.removeCallback(callback);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
