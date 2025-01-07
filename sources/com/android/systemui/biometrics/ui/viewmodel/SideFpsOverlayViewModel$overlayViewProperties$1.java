package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.ui.viewmodel.SideFpsOverlayViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewModel$overlayViewProperties$1 extends SuspendLambda implements Function3 {
    /* synthetic */ float F$0;
    /* synthetic */ int I$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        int intValue = ((Number) obj).intValue();
        float floatValue = ((Number) obj2).floatValue();
        SideFpsOverlayViewModel$overlayViewProperties$1 sideFpsOverlayViewModel$overlayViewProperties$1 = new SideFpsOverlayViewModel$overlayViewProperties$1(3, (Continuation) obj3);
        sideFpsOverlayViewModel$overlayViewProperties$1.I$0 = intValue;
        sideFpsOverlayViewModel$overlayViewProperties$1.F$0 = floatValue;
        return sideFpsOverlayViewModel$overlayViewProperties$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return new SideFpsOverlayViewModel.OverlayViewProperties(this.I$0, this.F$0);
    }
}
