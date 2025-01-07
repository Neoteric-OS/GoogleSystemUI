package com.android.systemui.user.domain.interactor;

import java.util.ArrayList;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherInteractor$userRecords$2 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ UserSwitcherInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherInteractor$userRecords$2(UserSwitcherInteractor userSwitcherInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSwitcherInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSwitcherInteractor$userRecords$2(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        UserSwitcherInteractor$userRecords$2 userSwitcherInteractor$userRecords$2 = (UserSwitcherInteractor$userRecords$2) create((ArrayList) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        userSwitcherInteractor$userRecords$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        UserSwitcherInteractor userSwitcherInteractor = this.this$0;
        userSwitcherInteractor.getClass();
        BuildersKt.launch$default(userSwitcherInteractor.applicationScope, null, null, new UserSwitcherInteractor$notifyCallbacks$1(userSwitcherInteractor, null), 3);
        return Unit.INSTANCE;
    }
}
