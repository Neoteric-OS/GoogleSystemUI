package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1;
import com.android.systemui.keyguard.domain.interactor.TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromAlternateBouncerTransitionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1(FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromAlternateBouncerTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        FromAlternateBouncerTransitionInteractor fromAlternateBouncerTransitionInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = fromAlternateBouncerTransitionInteractor.keyguardInteractor.primaryBouncerShowing;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToPrimaryBouncer$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                return bool;
            }
        };
        FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1.AnonymousClass4 anonymousClass4 = new FromAlternateBouncerTransitionInteractor$listenForAlternateBouncerToGone$1.AnonymousClass4(fromAlternateBouncerTransitionInteractor, 2);
        this.label = 1;
        readonlyStateFlow.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass4, fromAlternateBouncerTransitionInteractor, anonymousClass1), this);
        return coroutineSingletons;
    }
}
