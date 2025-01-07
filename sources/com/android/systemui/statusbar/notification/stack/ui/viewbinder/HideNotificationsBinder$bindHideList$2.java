package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import kotlin.KotlinNothingValueException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SharedFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class HideNotificationsBinder$bindHideList$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ SharedFlow $hideListFlow;
    final /* synthetic */ NotificationStackScrollLayoutController $viewController;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HideNotificationsBinder$bindHideList$2(SharedFlow sharedFlow, NotificationStackScrollLayoutController notificationStackScrollLayoutController, Continuation continuation) {
        super(2, continuation);
        this.$hideListFlow = sharedFlow;
        this.$viewController = notificationStackScrollLayoutController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new HideNotificationsBinder$bindHideList$2(this.$hideListFlow, this.$viewController, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ((HideNotificationsBinder$bindHideList$2) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlow sharedFlow = this.$hideListFlow;
            final NotificationStackScrollLayoutController notificationStackScrollLayoutController = this.$viewController;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.statusbar.notification.stack.ui.viewbinder.HideNotificationsBinder$bindHideList$2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    HideNotificationsBinder.access$bindHideState(NotificationStackScrollLayoutController.this, ((Boolean) obj2).booleanValue());
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (sharedFlow.collect(flowCollector, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
