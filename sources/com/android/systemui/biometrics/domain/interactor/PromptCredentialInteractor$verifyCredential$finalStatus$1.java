package com.android.systemui.biometrics.domain.interactor;

import com.android.systemui.biometrics.domain.interactor.CredentialStatus;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptCredentialInteractor$verifyCredential$finalStatus$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PromptCredentialInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptCredentialInteractor$verifyCredential$finalStatus$1(PromptCredentialInteractor promptCredentialInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = promptCredentialInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        PromptCredentialInteractor$verifyCredential$finalStatus$1 promptCredentialInteractor$verifyCredential$finalStatus$1 = new PromptCredentialInteractor$verifyCredential$finalStatus$1(this.this$0, continuation);
        promptCredentialInteractor$verifyCredential$finalStatus$1.L$0 = obj;
        return promptCredentialInteractor$verifyCredential$finalStatus$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        PromptCredentialInteractor$verifyCredential$finalStatus$1 promptCredentialInteractor$verifyCredential$finalStatus$1 = (PromptCredentialInteractor$verifyCredential$finalStatus$1) create((CredentialStatus) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        promptCredentialInteractor$verifyCredential$finalStatus$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CredentialStatus credentialStatus = (CredentialStatus) this.L$0;
        if (credentialStatus instanceof CredentialStatus$Success$Verified) {
            this.this$0._verificationError.setValue(null);
        } else if (credentialStatus instanceof CredentialStatus.Fail) {
            this.this$0._verificationError.setValue(credentialStatus);
        }
        return Unit.INSTANCE;
    }
}
