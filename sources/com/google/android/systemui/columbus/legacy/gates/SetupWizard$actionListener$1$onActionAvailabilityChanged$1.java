package com.google.android.systemui.columbus.legacy.gates;

import com.google.android.systemui.columbus.legacy.actions.Action;
import java.util.Iterator;
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
final class SetupWizard$actionListener$1$onActionAvailabilityChanged$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ SetupWizard this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SetupWizard$actionListener$1$onActionAvailabilityChanged$1(SetupWizard setupWizard, Continuation continuation) {
        super(2, continuation);
        this.this$0 = setupWizard;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new SetupWizard$actionListener$1$onActionAvailabilityChanged$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SetupWizard$actionListener$1$onActionAvailabilityChanged$1 setupWizard$actionListener$1$onActionAvailabilityChanged$1 = (SetupWizard$actionListener$1$onActionAvailabilityChanged$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        setupWizard$actionListener$1$onActionAvailabilityChanged$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object obj2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SetupWizard setupWizard = this.this$0;
        Iterator it = setupWizard.exceptions.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj2 = null;
                break;
            }
            obj2 = it.next();
            if (((Action) obj2).isAvailable) {
                break;
            }
        }
        setupWizard.exceptionActive = obj2 != null;
        SetupWizard setupWizard2 = this.this$0;
        BuildersKt.launch$default(setupWizard2.coroutineScope, null, null, new SetupWizard$updateBlocking$1(setupWizard2, null), 3);
        return Unit.INSTANCE;
    }
}
