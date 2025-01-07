package com.google.android.systemui.columbus.fetchers;

import android.content.Context;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class UserFetcher$currentUserChange$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Executor $mainExecutor;
    final /* synthetic */ UserTracker $userTracker;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserFetcher$currentUserChange$1(UserTracker userTracker, Executor executor, Continuation continuation) {
        super(2, continuation);
        this.$userTracker = userTracker;
        this.$mainExecutor = executor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        UserFetcher$currentUserChange$1 userFetcher$currentUserChange$1 = new UserFetcher$currentUserChange$1(this.$userTracker, this.$mainExecutor, continuation);
        userFetcher$currentUserChange$1.L$0 = obj;
        return userFetcher$currentUserChange$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((UserFetcher$currentUserChange$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.settings.UserTracker$Callback, com.google.android.systemui.columbus.fetchers.UserFetcher$currentUserChange$1$userTrackerCallback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final ?? r1 = new UserTracker.Callback() { // from class: com.google.android.systemui.columbus.fetchers.UserFetcher$currentUserChange$1$userTrackerCallback$1
                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onProfilesChanged(List list) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }

                @Override // com.android.systemui.settings.UserTracker.Callback
                public final void onUserChanged(int i2, Context context) {
                    ((ProducerCoroutine) ProducerScope.this).mo1790trySendJP2dKIU(Unit.INSTANCE);
                }
            };
            ((UserTrackerImpl) this.$userTracker).addCallback(r1, this.$mainExecutor);
            final UserTracker userTracker = this.$userTracker;
            Function0 function0 = new Function0() { // from class: com.google.android.systemui.columbus.fetchers.UserFetcher$currentUserChange$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    ((UserTrackerImpl) UserTracker.this).removeCallback(r1);
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
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
