package com.google.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.BurnInModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class KeyguardAmbientIndicationViewModel$burnIn$1 extends SuspendLambda implements Function3 {
    /* synthetic */ float F$0;
    /* synthetic */ Object L$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        float floatValue = ((Number) obj2).floatValue();
        KeyguardAmbientIndicationViewModel$burnIn$1 keyguardAmbientIndicationViewModel$burnIn$1 = new KeyguardAmbientIndicationViewModel$burnIn$1(3, (Continuation) obj3);
        keyguardAmbientIndicationViewModel$burnIn$1.L$0 = (BurnInModel) obj;
        keyguardAmbientIndicationViewModel$burnIn$1.F$0 = floatValue;
        return keyguardAmbientIndicationViewModel$burnIn$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BurnInModel burnInModel = (BurnInModel) this.L$0;
        float f = this.F$0;
        return new BurnInModel((int) (burnInModel.translationX * f), (int) (burnInModel.translationY * f), burnInModel.scale, burnInModel.scaleClockOnly);
    }
}
