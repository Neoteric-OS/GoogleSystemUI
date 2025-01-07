package com.google.android.systemui.columbus.sensors;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.SharingCommand;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SharingStartedWhileSubscribedThrottled$command$1 extends SuspendLambda implements Function2 {
    /* synthetic */ int I$0;
    int label;

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SharingStartedWhileSubscribedThrottled$command$1 sharingStartedWhileSubscribedThrottled$command$1 = new SharingStartedWhileSubscribedThrottled$command$1(2, continuation);
        sharingStartedWhileSubscribedThrottled$command$1.I$0 = ((Number) obj).intValue();
        return sharingStartedWhileSubscribedThrottled$command$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((SharingStartedWhileSubscribedThrottled$command$1) create(Integer.valueOf(((Number) obj).intValue()), (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return this.I$0 > 0 ? SharingCommand.START : SharingCommand.STOP_AND_RESET_REPLAY_CACHE;
    }
}
