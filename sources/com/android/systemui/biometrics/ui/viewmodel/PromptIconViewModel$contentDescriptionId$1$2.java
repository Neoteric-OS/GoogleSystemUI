package com.android.systemui.biometrics.ui.viewmodel;

import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function4;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptIconViewModel$contentDescriptionId$1$2 extends SuspendLambda implements Function4 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    /* synthetic */ boolean Z$1;
    int label;
    final /* synthetic */ PromptIconViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptIconViewModel$contentDescriptionId$1$2(PromptIconViewModel promptIconViewModel, Continuation continuation) {
        super(4, continuation);
        this.this$0 = promptIconViewModel;
    }

    @Override // kotlin.jvm.functions.Function4
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        boolean booleanValue2 = ((Boolean) obj3).booleanValue();
        PromptIconViewModel$contentDescriptionId$1$2 promptIconViewModel$contentDescriptionId$1$2 = new PromptIconViewModel$contentDescriptionId$1$2(this.this$0, (Continuation) obj4);
        promptIconViewModel$contentDescriptionId$1$2.L$0 = (PromptAuthState) obj;
        promptIconViewModel$contentDescriptionId$1$2.Z$0 = booleanValue;
        promptIconViewModel$contentDescriptionId$1$2.Z$1 = booleanValue2;
        return promptIconViewModel$contentDescriptionId$1$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PromptAuthState promptAuthState = (PromptAuthState) this.L$0;
        boolean z = this.Z$0;
        boolean z2 = this.Z$1;
        this.this$0.getClass();
        return new Integer(promptAuthState.isAuthenticatedAndExplicitlyConfirmed() ? R.string.biometric_dialog_face_icon_description_confirmed : promptAuthState.isAuthenticated ? R.string.biometric_dialog_face_icon_description_authenticated : z ? R.string.biometric_dialog_face_icon_description_authenticating : z2 ? R.string.keyguard_face_failed : R.string.biometric_dialog_face_icon_description_idle);
    }
}
