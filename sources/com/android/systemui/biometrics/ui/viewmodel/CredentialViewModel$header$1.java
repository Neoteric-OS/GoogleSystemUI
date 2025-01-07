package com.android.systemui.biometrics.ui.viewmodel;

import android.content.Context;
import android.hardware.biometrics.PromptContentView;
import android.os.UserManager;
import com.android.systemui.biometrics.domain.model.BiometricPromptRequest;
import com.android.systemui.biometrics.shared.model.BiometricUserInfo;
import com.android.wm.shell.R;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CredentialViewModel$header$1 extends SuspendLambda implements Function3 {
    /* synthetic */ Object L$0;
    /* synthetic */ boolean Z$0;
    int label;
    final /* synthetic */ CredentialViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CredentialViewModel$header$1(CredentialViewModel credentialViewModel, Continuation continuation) {
        super(3, continuation);
        this.this$0 = credentialViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        boolean booleanValue = ((Boolean) obj2).booleanValue();
        CredentialViewModel$header$1 credentialViewModel$header$1 = new CredentialViewModel$header$1(this.this$0, (Continuation) obj3);
        credentialViewModel$header$1.L$0 = (BiometricPromptRequest.Credential) obj;
        credentialViewModel$header$1.Z$0 = booleanValue;
        return credentialViewModel$header$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        BiometricPromptRequest.Credential credential = (BiometricPromptRequest.Credential) this.L$0;
        boolean z = this.Z$0;
        BiometricUserInfo biometricUserInfo = credential.userInfo;
        String str = z ? "" : credential.subtitle;
        PromptContentView promptContentView = !z ? credential.contentView : null;
        String str2 = credential.contentView != null ? "" : credential.description;
        Context context = this.this$0.applicationContext;
        UserManager userManager = (UserManager) context.getSystemService(UserManager.class);
        return new BiometricPromptHeaderViewModelImpl(credential, biometricUserInfo, credential.title, str, str2, promptContentView, context.getResources().getDrawable(userManager != null ? userManager.isManagedProfile(biometricUserInfo.deviceCredentialOwnerId) : false ? R.drawable.auth_dialog_enterprise : R.drawable.auth_dialog_lock, context.getTheme()), credential.showEmergencyCallButton);
    }
}
