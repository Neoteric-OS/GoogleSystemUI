package com.android.systemui.keyguard.domain.interactor;

import com.android.compose.animation.scene.ObservableTransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyguardTransitionInteractor$isInTransition$3 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        KeyguardTransitionInteractor$isInTransition$3 keyguardTransitionInteractor$isInTransition$3 = new KeyguardTransitionInteractor$isInTransition$3(3, (Continuation) obj3);
        keyguardTransitionInteractor$isInTransition$3.Z$0 = booleanValue;
        keyguardTransitionInteractor$isInTransition$3.L$0 = (ObservableTransitionState) obj2;
        return keyguardTransitionInteractor$isInTransition$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        return Boolean.valueOf(z);
    }
}
