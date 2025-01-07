package com.android.systemui.qs.pipeline.domain.interactor;

import java.util.List;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$userAndTiles$1$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ int $userId;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentTilesInteractorImpl$userAndTiles$1$1(int i, Continuation continuation) {
        super(3, continuation);
        this.$userId = i;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CurrentTilesInteractorImpl$userAndTiles$1$1 currentTilesInteractorImpl$userAndTiles$1$1 = new CurrentTilesInteractorImpl$userAndTiles$1$1(this.$userId, (Continuation) obj3);
        currentTilesInteractorImpl$userAndTiles$1$1.L$0 = (List) obj;
        currentTilesInteractorImpl$userAndTiles$1$1.L$1 = (Set) obj2;
        return currentTilesInteractorImpl$userAndTiles$1$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new UserTilesAndComponents(this.$userId, (List) this.L$0, (Set) this.L$1);
    }
}
