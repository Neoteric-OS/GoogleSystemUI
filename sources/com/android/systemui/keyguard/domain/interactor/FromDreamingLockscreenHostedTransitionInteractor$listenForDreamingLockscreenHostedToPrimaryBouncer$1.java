package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1;
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
final class FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToPrimaryBouncer$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ FromDreamingLockscreenHostedTransitionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToPrimaryBouncer$1(FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromDreamingLockscreenHostedTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToPrimaryBouncer$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToPrimaryBouncer$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
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
        FromDreamingLockscreenHostedTransitionInteractor fromDreamingLockscreenHostedTransitionInteractor = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = fromDreamingLockscreenHostedTransitionInteractor.keyguardInteractor.primaryBouncerShowing;
        AnonymousClass1 anonymousClass1 = new Function1() { // from class: com.android.systemui.keyguard.domain.interactor.FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToPrimaryBouncer$1.1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                Boolean bool = (Boolean) obj2;
                bool.booleanValue();
                return bool;
            }
        };
        FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1.AnonymousClass2 anonymousClass2 = new FromDreamingLockscreenHostedTransitionInteractor$listenForDreamingLockscreenHostedToGone$1.AnonymousClass2(fromDreamingLockscreenHostedTransitionInteractor, 4);
        this.label = 1;
        readonlyStateFlow.collect(new TransitionInteractor$filterRelevantKeyguardStateAnd$$inlined$filter$1.AnonymousClass2(anonymousClass2, fromDreamingLockscreenHostedTransitionInteractor, anonymousClass1), this);
        return coroutineSingletons;
    }
}
