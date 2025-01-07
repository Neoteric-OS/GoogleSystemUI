package com.android.systemui.communal.domain.interactor;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneTransitionInteractor$nextKeyguardStateInternal$1 extends SuspendLambda implements Function5 {
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    /* synthetic */ boolean Z$2;
    /* synthetic */ boolean Z$3;
    int label;

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        boolean booleanValue2 = ((Boolean) obj2).booleanValue();
        boolean booleanValue3 = ((Boolean) obj3).booleanValue();
        boolean booleanValue4 = ((Boolean) obj4).booleanValue();
        CommunalSceneTransitionInteractor$nextKeyguardStateInternal$1 communalSceneTransitionInteractor$nextKeyguardStateInternal$1 = new CommunalSceneTransitionInteractor$nextKeyguardStateInternal$1(5, (Continuation) obj5);
        communalSceneTransitionInteractor$nextKeyguardStateInternal$1.Z$0 = booleanValue;
        communalSceneTransitionInteractor$nextKeyguardStateInternal$1.Z$1 = booleanValue2;
        communalSceneTransitionInteractor$nextKeyguardStateInternal$1.Z$2 = booleanValue3;
        communalSceneTransitionInteractor$nextKeyguardStateInternal$1.Z$3 = booleanValue4;
        return communalSceneTransitionInteractor$nextKeyguardStateInternal$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        boolean z3 = this.Z$2;
        boolean z4 = this.Z$3;
        if (z3) {
            return KeyguardState.GONE;
        }
        if (z2 && !z) {
            return KeyguardState.OCCLUDED;
        }
        if (z) {
            return KeyguardState.DREAMING;
        }
        if (z4) {
            return KeyguardState.LOCKSCREEN;
        }
        return null;
    }
}
