package com.android.systemui.volume.panel.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ComponentsInteractorImpl$components$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ String $componentKey;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ ComponentsInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComponentsInteractorImpl$components$1$1(ComponentsInteractorImpl componentsInteractorImpl, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = componentsInteractorImpl;
        this.$componentKey = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ComponentsInteractorImpl$components$1$1 componentsInteractorImpl$components$1$1 = new ComponentsInteractorImpl$components$1$1(this.this$0, this.$componentKey, continuation);
        componentsInteractorImpl$components$1$1.Z$0 = ((Boolean) obj).booleanValue();
        return componentsInteractorImpl$components$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        ComponentsInteractorImpl$components$1$1 componentsInteractorImpl$components$1$1 = (ComponentsInteractorImpl$components$1$1) create(bool, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        componentsInteractorImpl$components$1$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.logger.onComponentAvailabilityChanged(this.$componentKey, this.Z$0);
        return Unit.INSTANCE;
    }
}
