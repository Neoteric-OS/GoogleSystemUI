package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.shared.model.BiometricModalities;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptViewModel$faceMode$1 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        PromptViewModel$faceMode$1 promptViewModel$faceMode$1 = new PromptViewModel$faceMode$1(4, (Continuation) obj4);
        promptViewModel$faceMode$1.L$0 = (BiometricModalities) obj;
        promptViewModel$faceMode$1.Z$0 = booleanValue;
        promptViewModel$faceMode$1.L$1 = (FingerprintStartMode) obj3;
        return promptViewModel$faceMode$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(((BiometricModalities) this.L$0).getHasFaceAndFingerprint() && !this.Z$0 && ((FingerprintStartMode) this.L$1) == FingerprintStartMode.Pending);
    }
}
