package com.android.systemui.statusbar.events;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SystemEventCoordinator$startConnectedDisplayCollection$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConnectedDisplayEvent $connectedDisplayEvent;
    int label;
    final /* synthetic */ SystemEventCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SystemEventCoordinator$startConnectedDisplayCollection$1(SystemEventCoordinator systemEventCoordinator, ConnectedDisplayEvent connectedDisplayEvent, Continuation continuation) {
        super(2, continuation);
        this.this$0 = systemEventCoordinator;
        this.$connectedDisplayEvent = connectedDisplayEvent;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SystemEventCoordinator$startConnectedDisplayCollection$1(this.this$0, this.$connectedDisplayEvent, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SystemEventCoordinator$startConnectedDisplayCollection$1 systemEventCoordinator$startConnectedDisplayCollection$1 = (SystemEventCoordinator$startConnectedDisplayCollection$1) create((Unit) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        systemEventCoordinator$startConnectedDisplayCollection$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SystemStatusAnimationSchedulerImpl systemStatusAnimationSchedulerImpl = this.this$0.scheduler;
        if (systemStatusAnimationSchedulerImpl == null) {
            systemStatusAnimationSchedulerImpl = null;
        }
        systemStatusAnimationSchedulerImpl.onStatusEvent(this.$connectedDisplayEvent);
        return Unit.INSTANCE;
    }
}
