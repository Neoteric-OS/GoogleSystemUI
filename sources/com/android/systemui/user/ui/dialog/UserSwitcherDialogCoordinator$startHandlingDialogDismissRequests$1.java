package com.android.systemui.user.ui.dialog;

import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import com.android.systemui.user.ui.dialog.UserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ UserSwitcherDialogCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(UserSwitcherDialogCoordinator userSwitcherDialogCoordinator, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSwitcherDialogCoordinator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserSwitcherDialogCoordinator$startHandlingDialogDismissRequests$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        ReadonlyStateFlow readonlyStateFlow = ((UserSwitcherInteractor) this.this$0.interactor.get()).dialogDismissRequests;
        UserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1.AnonymousClass1 anonymousClass1 = new UserSwitcherDialogCoordinator$startHandlingDialogShowRequests$1.AnonymousClass1(this.this$0, 1);
        this.label = 1;
        readonlyStateFlow.collect(new FlowKt__TransformKt$filterNotNull$$inlined$unsafeTransform$1.AnonymousClass2(anonymousClass1), this);
        return coroutineSingletons;
    }
}
