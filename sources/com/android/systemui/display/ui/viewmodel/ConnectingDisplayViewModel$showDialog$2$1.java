package com.android.systemui.display.ui.viewmodel;

import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractor$PendingDisplay;
import com.android.systemui.display.domain.interactor.ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ConnectingDisplayViewModel$showDialog$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConnectedDisplayInteractor$PendingDisplay $pendingDisplay;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectingDisplayViewModel$showDialog$2$1(ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay, Continuation continuation) {
        super(2, continuation);
        this.$pendingDisplay = connectedDisplayInteractor$PendingDisplay;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ConnectingDisplayViewModel$showDialog$2$1(this.$pendingDisplay, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ConnectingDisplayViewModel$showDialog$2$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ConnectedDisplayInteractor$PendingDisplay connectedDisplayInteractor$PendingDisplay = this.$pendingDisplay;
            this.label = 1;
            ((ConnectedDisplayInteractorImpl$toInteractorPendingDisplay$1) connectedDisplayInteractor$PendingDisplay).$this_toInteractorPendingDisplay.ignore();
            if (unit == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
