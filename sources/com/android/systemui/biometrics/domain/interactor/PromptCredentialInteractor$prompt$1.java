package com.android.systemui.biometrics.domain.interactor;

import android.hardware.biometrics.PromptInfo;
import com.android.systemui.biometrics.domain.model.BiometricOperationInfo;
import com.android.systemui.biometrics.domain.model.BiometricPromptRequest;
import com.android.systemui.biometrics.shared.model.BiometricUserInfo;
import com.android.systemui.biometrics.shared.model.PromptKind;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PromptCredentialInteractor$prompt$1 extends SuspendLambda implements Function5 {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ Object L$3;
    int label;
    final /* synthetic */ PromptCredentialInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptCredentialInteractor$prompt$1(PromptCredentialInteractor promptCredentialInteractor, Continuation continuation) {
        super(5, continuation);
        this.this$0 = promptCredentialInteractor;
    }

    @Override // kotlin.jvm.functions.Function5
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        PromptCredentialInteractor$prompt$1 promptCredentialInteractor$prompt$1 = new PromptCredentialInteractor$prompt$1(this.this$0, (Continuation) obj5);
        promptCredentialInteractor$prompt$1.L$0 = (PromptInfo) obj;
        promptCredentialInteractor$prompt$1.L$1 = (Long) obj2;
        promptCredentialInteractor$prompt$1.L$2 = (Integer) obj3;
        promptCredentialInteractor$prompt$1.L$3 = (PromptKind) obj4;
        return promptCredentialInteractor$prompt$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        PromptInfo promptInfo = (PromptInfo) this.L$0;
        Long l = (Long) this.L$1;
        Integer num = (Integer) this.L$2;
        PromptKind promptKind = (PromptKind) this.L$3;
        if (promptInfo == null || num == null || l == null) {
            return null;
        }
        if (Intrinsics.areEqual(promptKind, PromptKind.Pin.INSTANCE)) {
            BiometricUserInfo access$userInfo = PromptCredentialInteractor.access$userInfo(this.this$0, num.intValue(), promptInfo.shouldUseParentProfileForDeviceCredential());
            PromptCredentialInteractor promptCredentialInteractor = this.this$0;
            long longValue = l.longValue();
            promptCredentialInteractor.getClass();
            return new BiometricPromptRequest.Credential.Pin(promptInfo, access$userInfo, new BiometricOperationInfo(longValue));
        }
        if (!Intrinsics.areEqual(promptKind, PromptKind.Pattern.INSTANCE)) {
            if (!Intrinsics.areEqual(promptKind, PromptKind.Password.INSTANCE)) {
                return null;
            }
            BiometricUserInfo access$userInfo2 = PromptCredentialInteractor.access$userInfo(this.this$0, num.intValue(), promptInfo.shouldUseParentProfileForDeviceCredential());
            PromptCredentialInteractor promptCredentialInteractor2 = this.this$0;
            long longValue2 = l.longValue();
            promptCredentialInteractor2.getClass();
            return new BiometricPromptRequest.Credential.Password(promptInfo, access$userInfo2, new BiometricOperationInfo(longValue2));
        }
        BiometricUserInfo access$userInfo3 = PromptCredentialInteractor.access$userInfo(this.this$0, num.intValue(), promptInfo.shouldUseParentProfileForDeviceCredential());
        PromptCredentialInteractor promptCredentialInteractor3 = this.this$0;
        long longValue3 = l.longValue();
        promptCredentialInteractor3.getClass();
        BiometricOperationInfo biometricOperationInfo = new BiometricOperationInfo(longValue3);
        CredentialInteractorImpl credentialInteractorImpl = this.this$0.credentialInteractor;
        return new BiometricPromptRequest.Credential.Pattern(promptInfo, access$userInfo3, biometricOperationInfo, !credentialInteractorImpl.lockPatternUtils.isVisiblePatternEnabled(num.intValue()));
    }
}
