package com.android.systemui.statusbar.notification.shelf.ui.viewbinder;

import com.android.systemui.statusbar.NotificationShelf;
import com.android.systemui.statusbar.notification.shelf.ui.viewbinder.NotificationShelfViewBinder$bind$2$1$2;
import com.android.systemui.statusbar.notification.shelf.ui.viewmodel.NotificationShelfViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class NotificationShelfViewBinder$bind$2$1$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ NotificationShelf $this_apply;
    final /* synthetic */ NotificationShelfViewModel $viewModel;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NotificationShelfViewBinder$bind$2$1$3(NotificationShelfViewModel notificationShelfViewModel, NotificationShelf notificationShelf, Continuation continuation) {
        super(2, continuation);
        this.$viewModel = notificationShelfViewModel;
        this.$this_apply = notificationShelf;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new NotificationShelfViewBinder$bind$2$1$3(this.$viewModel, this.$this_apply, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((NotificationShelfViewBinder$bind$2$1$3) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        StateFlowImpl stateFlowImpl = this.$viewModel.interactor.keyguardRepository.isKeyguardShowing;
        NotificationShelfViewBinder$bind$2$1$2.AnonymousClass1 anonymousClass1 = new NotificationShelfViewBinder$bind$2$1$2.AnonymousClass1(this.$this_apply, 1);
        this.label = 1;
        stateFlowImpl.collect(anonymousClass1, this);
        return coroutineSingletons;
    }
}
