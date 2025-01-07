package com.android.systemui.qs.pipeline.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$userAndTiles$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        CurrentTilesInteractorImpl$userAndTiles$2 currentTilesInteractorImpl$userAndTiles$2 = new CurrentTilesInteractorImpl$userAndTiles$2(3, (Continuation) obj3);
        currentTilesInteractorImpl$userAndTiles$2.L$0 = (UserTilesAndComponents) obj;
        currentTilesInteractorImpl$userAndTiles$2.L$1 = (UserTilesAndComponents) obj2;
        return currentTilesInteractorImpl$userAndTiles$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserTilesAndComponents userTilesAndComponents = (UserTilesAndComponents) this.L$0;
        UserTilesAndComponents userTilesAndComponents2 = (UserTilesAndComponents) this.L$1;
        int i = userTilesAndComponents.userId;
        int i2 = userTilesAndComponents2.userId;
        return new DataWithUserChange(i2, userTilesAndComponents2.tiles, userTilesAndComponents2.installedComponents, i != i2);
    }
}
