package com.android.systemui.qs.pipeline.data.repository;

import com.android.systemui.qs.pipeline.data.repository.UserAutoAddRepository;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserAutoAddRepository$autoAdded$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ UserAutoAddRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAutoAddRepository$autoAdded$3(UserAutoAddRepository userAutoAddRepository, Continuation continuation) {
        super(3, continuation);
        this.this$0 = userAutoAddRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        UserAutoAddRepository$autoAdded$3 userAutoAddRepository$autoAdded$3 = new UserAutoAddRepository$autoAdded$3(this.this$0, (Continuation) obj3);
        userAutoAddRepository$autoAdded$3.L$0 = (Set) obj;
        userAutoAddRepository$autoAdded$3.L$1 = (UserAutoAddRepository.ChangeAction) obj2;
        return userAutoAddRepository$autoAdded$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Set set = (Set) this.L$0;
        UserAutoAddRepository.ChangeAction changeAction = (UserAutoAddRepository.ChangeAction) this.L$1;
        Set apply = changeAction.apply(set);
        UserAutoAddRepository userAutoAddRepository = this.this$0;
        if (changeAction instanceof UserAutoAddRepository.RestoreTiles) {
            userAutoAddRepository.logger.logAutoAddTilesRestoredReconciled(userAutoAddRepository.userId, apply);
        }
        return apply;
    }
}
