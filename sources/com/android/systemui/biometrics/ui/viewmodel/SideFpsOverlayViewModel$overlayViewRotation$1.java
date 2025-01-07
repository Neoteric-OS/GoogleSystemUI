package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.domain.model.SideFpsSensorLocation;
import com.android.systemui.biometrics.shared.model.DisplayRotation;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class SideFpsOverlayViewModel$overlayViewRotation$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SideFpsOverlayViewModel$overlayViewRotation$1 sideFpsOverlayViewModel$overlayViewRotation$1 = new SideFpsOverlayViewModel$overlayViewRotation$1(3, (Continuation) obj3);
        sideFpsOverlayViewModel$overlayViewRotation$1.L$0 = (DisplayRotation) obj;
        sideFpsOverlayViewModel$overlayViewRotation$1.L$1 = (SideFpsSensorLocation) obj2;
        return sideFpsOverlayViewModel$overlayViewRotation$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        DisplayRotation displayRotation = (DisplayRotation) this.L$0;
        boolean z = ((SideFpsSensorLocation) this.L$1).isSensorVerticalInDefaultOrientation;
        int ordinal = displayRotation.ordinal();
        float f = 180.0f;
        if (ordinal == 1 ? z : !(ordinal == 2 || (ordinal == 3 && z))) {
            f = 0.0f;
        }
        return new Float(f);
    }
}
