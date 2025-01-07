package com.android.systemui.unfold;

import android.util.Log;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.TimeoutCancellationException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class FoldLightRevealOverlayAnimation$catchTimeoutAndLog$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FoldLightRevealOverlayAnimation$catchTimeoutAndLog$1 foldLightRevealOverlayAnimation$catchTimeoutAndLog$1 = new FoldLightRevealOverlayAnimation$catchTimeoutAndLog$1(3, (Continuation) obj3);
        foldLightRevealOverlayAnimation$catchTimeoutAndLog$1.L$0 = (Throwable) obj2;
        Unit unit = Unit.INSTANCE;
        foldLightRevealOverlayAnimation$catchTimeoutAndLog$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Throwable th = (Throwable) this.L$0;
        if (!(th instanceof TimeoutCancellationException)) {
            throw th;
        }
        Log.e("FoldLightRevealOverlayAnimation", "Fold light reveal animation timed out");
        return Unit.INSTANCE;
    }
}
