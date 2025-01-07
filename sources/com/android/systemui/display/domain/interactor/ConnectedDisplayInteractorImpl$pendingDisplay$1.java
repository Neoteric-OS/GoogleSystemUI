package com.android.systemui.display.domain.interactor;

import com.android.systemui.display.data.repository.DisplayRepositoryImpl$pendingDisplay$1$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ConnectedDisplayInteractorImpl$pendingDisplay$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ ConnectedDisplayInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectedDisplayInteractorImpl$pendingDisplay$1(ConnectedDisplayInteractorImpl connectedDisplayInteractorImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = connectedDisplayInteractorImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        ConnectedDisplayInteractorImpl$pendingDisplay$1 connectedDisplayInteractorImpl$pendingDisplay$1 = new ConnectedDisplayInteractorImpl$pendingDisplay$1(this.this$0, (Continuation) obj3);
        connectedDisplayInteractorImpl$pendingDisplay$1.L$0 = (DisplayRepositoryImpl$pendingDisplay$1$1) obj;
        connectedDisplayInteractorImpl$pendingDisplay$1.Z$0 = booleanValue;
        return connectedDisplayInteractorImpl$pendingDisplay$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DisplayRepositoryImpl$pendingDisplay$1$1 displayRepositoryImpl$pendingDisplay$1$1 = (DisplayRepositoryImpl$pendingDisplay$1$1) this.L$0;
        boolean z = this.Z$0;
        if (displayRepositoryImpl$pendingDisplay$1$1 == null || z) {
            return null;
        }
        this.this$0.getClass();
        return new ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1(displayRepositoryImpl$pendingDisplay$1$1);
    }
}
