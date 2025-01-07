package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.actions.Action;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SetupWizard$onActivate$1 extends SuspendLambda implements Function2 {
    Object L$0;
    int label;
    final /* synthetic */ SetupWizard this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupWizard$onActivate$1(SetupWizard setupWizard, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizard;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizard$onActivate$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SetupWizard$onActivate$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SetupWizard setupWizard;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SetupWizard setupWizard2 = this.this$0;
            setupWizard2.exceptionActive = false;
            for (Action action : setupWizard2.exceptions) {
                action.listeners.add(setupWizard2.actionListener);
                setupWizard2.exceptionActive = setupWizard2.exceptionActive || action.isAvailable;
            }
            SetupWizard setupWizard3 = this.this$0;
            this.L$0 = setupWizard3;
            this.label = 1;
            Object access$isSetupComplete = SetupWizard.access$isSetupComplete(setupWizard3, this);
            if (access$isSetupComplete == coroutineSingletons) {
                return coroutineSingletons;
            }
            setupWizard = setupWizard3;
            obj = access$isSetupComplete;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            setupWizard = (SetupWizard) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        setupWizard.setupComplete = ((Boolean) obj).booleanValue();
        SetupWizard setupWizard4 = this.this$0;
        BuildersKt.launch$default(setupWizard4.coroutineScope, null, null, new SetupWizard$updateBlocking$1(setupWizard4, null), 3);
        return Unit.INSTANCE;
    }
}
