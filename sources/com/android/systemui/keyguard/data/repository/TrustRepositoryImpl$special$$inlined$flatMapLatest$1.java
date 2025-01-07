package com.android.systemui.keyguard.data.repository;

import android.content.pm.UserInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustRepositoryImpl$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ TrustRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TrustRepositoryImpl$special$$inlined$flatMapLatest$1(TrustRepositoryImpl trustRepositoryImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = trustRepositoryImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        TrustRepositoryImpl$special$$inlined$flatMapLatest$1 trustRepositoryImpl$special$$inlined$flatMapLatest$1 = new TrustRepositoryImpl$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        trustRepositoryImpl$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        trustRepositoryImpl$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return trustRepositoryImpl$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Boolean valueOf = Boolean.valueOf(this.this$0.trustManager.isTrustUsuallyManaged(((UserInfo) this.L$1).id));
            this.label = 1;
            FlowKt.ensureActive(flowCollector);
            Object emit = flowCollector.emit(valueOf, this);
            if (emit != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = unit;
            }
            if (emit != CoroutineSingletons.COROUTINE_SUSPENDED) {
                emit = unit;
            }
            if (emit == coroutineSingletons) {
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
