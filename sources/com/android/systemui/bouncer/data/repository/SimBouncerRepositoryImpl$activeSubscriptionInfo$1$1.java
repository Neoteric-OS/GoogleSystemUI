package com.android.systemui.bouncer.data.repository;

import com.android.systemui.statusbar.pipeline.mobile.util.SubscriptionManagerProxyImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ int $it;
    int label;
    final /* synthetic */ SimBouncerRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1(SimBouncerRepositoryImpl simBouncerRepositoryImpl, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = simBouncerRepositoryImpl;
        this.$it = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1(this.this$0, this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SimBouncerRepositoryImpl$activeSubscriptionInfo$1$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SubscriptionManagerProxyImpl subscriptionManagerProxyImpl = this.this$0.subscriptionManager;
            int i2 = this.$it;
            this.label = 1;
            obj = subscriptionManagerProxyImpl.getActiveSubscriptionInfo(i2, this);
            if (obj == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
