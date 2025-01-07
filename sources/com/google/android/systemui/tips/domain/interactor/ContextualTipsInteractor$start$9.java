package com.google.android.systemui.tips.domain.interactor;

import com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$start$3;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContextualTipsInteractor$start$9 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ContextualTipsInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualTipsInteractor$start$9(ContextualTipsInteractor contextualTipsInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contextualTipsInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ContextualTipsInteractor$start$9(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContextualTipsInteractor$start$9) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        ContextualTipsInteractor contextualTipsInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = contextualTipsInteractor.repository.assistantStartCount;
        ContextualTipsInteractor$start$3.AnonymousClass3 anonymousClass3 = new ContextualTipsInteractor$start$3.AnonymousClass3(contextualTipsInteractor, 9);
        this.label = 1;
        readonlyStateFlow.collect(new ContextualTipsInteractor$start$9$invokeSuspend$$inlined$filter$1$2(anonymousClass3), this);
        return coroutineSingletons;
    }
}
