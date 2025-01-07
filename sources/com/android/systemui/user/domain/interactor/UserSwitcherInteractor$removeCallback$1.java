package com.android.systemui.user.domain.interactor;

import com.android.systemui.user.domain.interactor.UserSwitcherInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserSwitcherInteractor$removeCallback$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ UserSwitcherInteractor.UserCallback $callback;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ UserSwitcherInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserSwitcherInteractor$removeCallback$1(UserSwitcherInteractor userSwitcherInteractor, UserSwitcherInteractor.UserCallback userCallback, Continuation continuation) {
        super(2, continuation);
        this.this$0 = userSwitcherInteractor;
        this.$callback = userCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new UserSwitcherInteractor$removeCallback$1(this.this$0, this.$callback, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserSwitcherInteractor$removeCallback$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UserSwitcherInteractor userSwitcherInteractor;
        Mutex mutex;
        UserSwitcherInteractor.UserCallback userCallback;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            userSwitcherInteractor = this.this$0;
            MutexImpl mutexImpl = userSwitcherInteractor.callbackMutex;
            UserSwitcherInteractor.UserCallback userCallback2 = this.$callback;
            this.L$0 = mutexImpl;
            this.L$1 = userSwitcherInteractor;
            this.L$2 = userCallback2;
            this.label = 1;
            if (mutexImpl.lock(this) == coroutineSingletons) {
                return coroutineSingletons;
            }
            mutex = mutexImpl;
            userCallback = userCallback2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            userCallback = (UserSwitcherInteractor.UserCallback) this.L$2;
            userSwitcherInteractor = (UserSwitcherInteractor) this.L$1;
            mutex = (Mutex) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        try {
            userSwitcherInteractor.callbacks.remove(userCallback);
            mutex.unlock(null);
            return Unit.INSTANCE;
        } catch (Throwable th) {
            mutex.unlock(null);
            throw th;
        }
    }
}
