package com.android.systemui.user.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherInteractor$switchUser$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Runnable $runnable;
    int label;
    final /* synthetic */ UserSwitcherInteractor this$0;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.user.domain.interactor.UserSwitcherInteractor$switchUser$1$1, reason: invalid class name */
    final class AnonymousClass1 extends SuspendLambda implements Function2 {
        final /* synthetic */ Runnable $runnable;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(Runnable runnable, Continuation continuation) {
            super(2, continuation);
            this.$runnable = runnable;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation create(Object obj, Continuation continuation) {
            return new AnonymousClass1(this.$runnable, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            AnonymousClass1 anonymousClass1 = (AnonymousClass1) create((CoroutineScope) obj, (Continuation) obj2);
            Unit unit = Unit.INSTANCE;
            anonymousClass1.invokeSuspend(unit);
            return unit;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.$runnable.run();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherInteractor$switchUser$1(UserSwitcherInteractor userSwitcherInteractor, Runnable runnable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSwitcherInteractor;
        this.$runnable = runnable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSwitcherInteractor$switchUser$1(this.this$0, this.$runnable, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserSwitcherInteractor$switchUser$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineDispatcher coroutineDispatcher = this.this$0.backgroundDispatcher;
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$runnable, null);
            this.label = 1;
            if (BuildersKt.withContext(coroutineDispatcher, anonymousClass1, this) == coroutineSingletons) {
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
