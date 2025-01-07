package com.google.android.systemui.columbus.sensors;

import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class GestureController$gestureEvents$2 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        if (obj != null) {
            throw new ClassCastException();
        }
        if (obj2 != null) {
            throw new ClassCastException();
        }
        GestureController$gestureEvents$2 gestureController$gestureEvents$2 = new GestureController$gestureEvents$2(3, (Continuation) obj3);
        gestureController$gestureEvents$2.L$0 = null;
        gestureController$gestureEvents$2.L$1 = null;
        return gestureController$gestureEvents$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$0);
        if (this.L$1 == null) {
            return new Pair(null, null);
        }
        throw new ClassCastException();
    }
}
