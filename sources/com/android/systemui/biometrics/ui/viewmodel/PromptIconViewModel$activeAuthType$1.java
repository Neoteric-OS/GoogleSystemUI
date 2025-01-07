package com.android.systemui.biometrics.ui.viewmodel;

import com.android.systemui.biometrics.shared.model.BiometricModalities;
import com.android.systemui.biometrics.ui.viewmodel.PromptIconViewModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptIconViewModel$activeAuthType$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        PromptIconViewModel$activeAuthType$1 promptIconViewModel$activeAuthType$1 = new PromptIconViewModel$activeAuthType$1(3, (Continuation) obj3);
        promptIconViewModel$activeAuthType$1.L$0 = (BiometricModalities) obj;
        promptIconViewModel$activeAuthType$1.Z$0 = booleanValue;
        return promptIconViewModel$activeAuthType$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BiometricModalities biometricModalities = (BiometricModalities) this.L$0;
        boolean z = this.Z$0;
        return (!biometricModalities.getHasFaceAndFingerprint() || z) ? (biometricModalities.getHasFaceOnly() || z) ? PromptIconViewModel.AuthType.Face : (biometricModalities.getHasFingerprint() && biometricModalities.faceProperties == null) ? PromptIconViewModel.AuthType.Fingerprint : PromptIconViewModel.AuthType.Fingerprint : PromptIconViewModel.AuthType.Coex;
    }
}
