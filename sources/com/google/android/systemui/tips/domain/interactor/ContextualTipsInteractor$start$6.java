package com.google.android.systemui.tips.domain.interactor;

import com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$start$3;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ContextualTipsInteractor$start$6 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ContextualTipsInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.google.android.systemui.tips.domain.interactor.ContextualTipsInteractor$start$6$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function4 {
        /* synthetic */ int I$0;
        /* synthetic */ boolean Z$0;
        /* synthetic */ boolean Z$1;
        int label;

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
            boolean booleanValue = ((Boolean) obj).booleanValue();
            boolean booleanValue2 = ((Boolean) obj2).booleanValue();
            int intValue = ((Number) obj3).intValue();
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(4, (Continuation) obj4);
            anonymousClass1.Z$0 = booleanValue;
            anonymousClass1.Z$1 = booleanValue2;
            anonymousClass1.I$0 = intValue;
            return anonymousClass1.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Boolean.valueOf((!this.Z$0 && !this.Z$1) && (this.I$0 == 5));
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextualTipsInteractor$start$6(ContextualTipsInteractor contextualTipsInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = contextualTipsInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ContextualTipsInteractor$start$6(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ContextualTipsInteractor$start$6) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ContextualTipsInteractor contextualTipsInteractor = this.this$0;
            Flow distinctUntilChanged = FlowKt.distinctUntilChanged(FlowKt.transformLatest(FlowKt.distinctUntilChanged(FlowKt.combine(contextualTipsInteractor.isOver30Days, contextualTipsInteractor.setupWizardRepository.isWipedOut, contextualTipsInteractor.repository.longPressOnPowerBehavior, new AnonymousClass1(4, null))), new ContextualTipsInteractor$start$6$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, null)));
            ContextualTipsInteractor$start$3.AnonymousClass3 anonymousClass3 = new ContextualTipsInteractor$start$3.AnonymousClass3(this.this$0, 2);
            this.label = 1;
            if (distinctUntilChanged.collect(anonymousClass3, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
