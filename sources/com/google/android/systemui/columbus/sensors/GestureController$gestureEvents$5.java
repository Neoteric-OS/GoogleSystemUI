package com.google.android.systemui.columbus.sensors;

import androidx.core.view.VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.android.internal.logging.UiEventLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class GestureController$gestureEvents$5 extends SuspendLambda implements Function2 {
    final /* synthetic */ UiEventLogger $uiEventLogger;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GestureController$gestureEvents$5(UiEventLogger uiEventLogger, Continuation continuation) {
        super(2, continuation);
        this.$uiEventLogger = uiEventLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        GestureController$gestureEvents$5 gestureController$gestureEvents$5 = new GestureController$gestureEvents$5(this.$uiEventLogger, continuation);
        gestureController$gestureEvents$5.L$0 = obj;
        return gestureController$gestureEvents$5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        if (obj != null) {
            throw new ClassCastException();
        }
        ((GestureController$gestureEvents$5) create(null, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        throw null;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        VelocityTrackerCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.L$0);
        throw null;
    }
}
