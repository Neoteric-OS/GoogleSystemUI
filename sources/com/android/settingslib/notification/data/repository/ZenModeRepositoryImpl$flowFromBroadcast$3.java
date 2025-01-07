package com.android.settingslib.notification.data.repository;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ZenModeRepositoryImpl$flowFromBroadcast$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ Function1 $mapper;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ZenModeRepositoryImpl$flowFromBroadcast$3(Continuation continuation, Function1 function1) {
        super(2, continuation);
        this.$mapper = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ZenModeRepositoryImpl$flowFromBroadcast$3 zenModeRepositoryImpl$flowFromBroadcast$3 = new ZenModeRepositoryImpl$flowFromBroadcast$3(continuation, this.$mapper);
        zenModeRepositoryImpl$flowFromBroadcast$3.L$0 = obj;
        return zenModeRepositoryImpl$flowFromBroadcast$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ZenModeRepositoryImpl$flowFromBroadcast$3) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object invoke = this.$mapper.invoke(null);
            this.label = 1;
            if (flowCollector.emit(invoke, this) == coroutineSingletons) {
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
