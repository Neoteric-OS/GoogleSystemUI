package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.shared.model.BiometricModalities;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptViewModel$isIconConfirmButton$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        PromptViewModel$isIconConfirmButton$1 promptViewModel$isIconConfirmButton$1 = new PromptViewModel$isIconConfirmButton$1(3, (Continuation) obj3);
        promptViewModel$isIconConfirmButton$1.L$0 = (BiometricModalities) obj;
        promptViewModel$isIconConfirmButton$1.L$1 = (PromptSize) obj2;
        return promptViewModel$isIconConfirmButton$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        return Boolean.valueOf(((BiometricModalities) this.L$0).getHasUdfps() && PromptSizeKt.isNotSmall((PromptSize) this.L$1));
    }
}
