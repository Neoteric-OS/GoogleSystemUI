package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.repository.UserTileSpecRepository;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserTileSpecRepository$tiles$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ UserTileSpecRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserTileSpecRepository$tiles$2(UserTileSpecRepository userTileSpecRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = userTileSpecRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        UserTileSpecRepository$tiles$2 userTileSpecRepository$tiles$2 = new UserTileSpecRepository$tiles$2(this.this$0, (Continuation) obj3);
        userTileSpecRepository$tiles$2.L$0 = (List) obj;
        userTileSpecRepository$tiles$2.L$1 = (UserTileSpecRepository.ChangeAction) obj2;
        return userTileSpecRepository$tiles$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = (List) this.L$0;
        UserTileSpecRepository.ChangeAction changeAction = (UserTileSpecRepository.ChangeAction) this.L$1;
        List apply = changeAction.apply(list);
        UserTileSpecRepository userTileSpecRepository = this.this$0;
        if (!list.equals(apply)) {
            if (changeAction instanceof UserTileSpecRepository.RestoreTiles) {
                userTileSpecRepository.logger.logTilesRestoredAndReconciled(userTileSpecRepository.userId, list, apply);
            } else {
                userTileSpecRepository.logger.logProcessTileChange(changeAction, apply, userTileSpecRepository.userId);
            }
        }
        return CollectionsKt.distinct(apply);
    }
}
