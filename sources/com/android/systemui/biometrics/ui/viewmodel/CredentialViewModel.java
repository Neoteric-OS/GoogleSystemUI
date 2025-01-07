package com.android.systemui.biometrics.ui.viewmodel;

import android.content.Context;
import com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.ReadonlySharedFlow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.SharedFlowImpl;
import kotlinx.coroutines.flow.SharedFlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CredentialViewModel {
    public final StateFlowImpl _animateContents;
    public final StateFlowImpl _remainingAttempts;
    public final SharedFlowImpl _validatedAttestation;
    public final ReadonlyStateFlow animateContents;
    public final Context applicationContext;
    public final PromptCredentialInteractor credentialInteractor;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 errorMessage;
    public final FlowKt__ZipKt$combine$$inlined$unsafeFlow$1 header;
    public final CredentialViewModel$special$$inlined$map$1 inputFlags;
    public final ReadonlyStateFlow remainingAttempts;
    public final CredentialViewModel$special$$inlined$map$1 stealthMode;
    public final ReadonlySharedFlow validatedAttestation;

    public CredentialViewModel(Context context, PromptCredentialInteractor promptCredentialInteractor) {
        this.applicationContext = context;
        this.credentialInteractor = promptCredentialInteractor;
        Flow flow = promptCredentialInteractor.prompt;
        this.header = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(new CredentialViewModel$special$$inlined$map$1(flow, 1), promptCredentialInteractor.showTitleOnly, new CredentialViewModel$header$1(this, null));
        this.inputFlags = new CredentialViewModel$special$$inlined$map$1(flow, 0);
        this.stealthMode = new CredentialViewModel$special$$inlined$map$1(flow, 2);
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(Boolean.TRUE);
        this._animateContents = MutableStateFlow;
        this.animateContents = new ReadonlyStateFlow(MutableStateFlow);
        this.errorMessage = new FlowKt__ZipKt$combine$$inlined$unsafeFlow$1(promptCredentialInteractor.verificationError, flow, new CredentialViewModel$errorMessage$1(this, null));
        SharedFlowImpl MutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7);
        this._validatedAttestation = MutableSharedFlow$default;
        this.validatedAttestation = new ReadonlySharedFlow(MutableSharedFlow$default);
        StateFlowImpl MutableStateFlow2 = StateFlowKt.MutableStateFlow(new RemainingAttempts());
        this._remainingAttempts = MutableStateFlow2;
        this.remainingAttempts = new ReadonlyStateFlow(MutableStateFlow2);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkCredential(java.lang.CharSequence r10, com.android.systemui.biometrics.ui.viewmodel.CredentialHeaderViewModel r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$1
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$1 r0 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$1 r0 = new com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$1
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 == r2) goto L32
            if (r1 != r8) goto L2a
            kotlin.ResultKt.throwOnFailure(r12)
            goto L61
        L2a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L32:
            java.lang.Object r9 = r0.L$0
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel r9 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L53
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.android.systemui.biometrics.ui.viewmodel.BiometricPromptHeaderViewModelImpl r11 = (com.android.systemui.biometrics.ui.viewmodel.BiometricPromptHeaderViewModelImpl) r11
            com.android.systemui.biometrics.domain.model.BiometricPromptRequest$Credential r11 = r11.request
            r0.L$0 = r9
            r0.label = r2
            com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor r1 = r9.credentialInteractor
            r4 = 0
            r6 = 4
            r2 = r11
            r3 = r10
            r5 = r0
            java.lang.Object r12 = com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor.checkCredential$default(r1, r2, r3, r4, r5, r6)
            if (r12 != r7) goto L53
            return r7
        L53:
            com.android.systemui.biometrics.domain.interactor.CredentialStatus r12 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus) r12
            r10 = 0
            r0.L$0 = r10
            r0.label = r8
            java.lang.Object r9 = r9.checkCredential(r12, r0)
            if (r9 != r7) goto L61
            return r7
        L61:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel.checkCredential(java.lang.CharSequence, com.android.systemui.biometrics.ui.viewmodel.CredentialHeaderViewModel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0060 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkCredential(java.util.List r10, com.android.systemui.biometrics.ui.viewmodel.CredentialHeaderViewModel r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$2
            if (r0 == 0) goto L13
            r0 = r12
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$2 r0 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$2 r0 = new com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$2
            r0.<init>(r9, r12)
        L18:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r7 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L3a
            if (r1 == r2) goto L32
            if (r1 != r8) goto L2a
            kotlin.ResultKt.throwOnFailure(r12)
            goto L61
        L2a:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L32:
            java.lang.Object r9 = r0.L$0
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel r9 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L53
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.android.systemui.biometrics.ui.viewmodel.BiometricPromptHeaderViewModelImpl r11 = (com.android.systemui.biometrics.ui.viewmodel.BiometricPromptHeaderViewModelImpl) r11
            com.android.systemui.biometrics.domain.model.BiometricPromptRequest$Credential r11 = r11.request
            r0.L$0 = r9
            r0.label = r2
            com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor r1 = r9.credentialInteractor
            r3 = 0
            r6 = 2
            r2 = r11
            r4 = r10
            r5 = r0
            java.lang.Object r12 = com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor.checkCredential$default(r1, r2, r3, r4, r5, r6)
            if (r12 != r7) goto L53
            return r7
        L53:
            com.android.systemui.biometrics.domain.interactor.CredentialStatus r12 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus) r12
            r10 = 0
            r0.L$0 = r10
            r0.label = r8
            java.lang.Object r9 = r9.checkCredential(r12, r0)
            if (r9 != r7) goto L61
            return r7
        L61:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel.checkCredential(java.util.List, com.android.systemui.biometrics.ui.viewmodel.CredentialHeaderViewModel, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object checkCredential(com.android.systemui.biometrics.domain.interactor.CredentialStatus r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$3
            if (r0 == 0) goto L13
            r0 = r9
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$3 r0 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$3 r0 = new com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel$checkCredential$3
            r0.<init>(r7, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 3
            r6 = 0
            if (r2 == 0) goto L50
            if (r2 == r4) goto L48
            if (r2 == r3) goto L3b
            if (r2 != r5) goto L33
            java.lang.Object r7 = r0.L$0
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel r7 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto Lae
        L33:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L3b:
            java.lang.Object r7 = r0.L$1
            r8 = r7
            com.android.systemui.biometrics.domain.interactor.CredentialStatus r8 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus) r8
            java.lang.Object r7 = r0.L$0
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel r7 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L87
        L48:
            java.lang.Object r7 = r0.L$0
            com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel r7 = (com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel) r7
            kotlin.ResultKt.throwOnFailure(r9)
            goto L68
        L50:
            kotlin.ResultKt.throwOnFailure(r9)
            boolean r9 = r8 instanceof com.android.systemui.biometrics.domain.interactor.CredentialStatus$Success$Verified
            kotlinx.coroutines.flow.SharedFlowImpl r2 = r7._validatedAttestation
            if (r9 == 0) goto L76
            com.android.systemui.biometrics.domain.interactor.CredentialStatus$Success$Verified r8 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus$Success$Verified) r8
            byte[] r8 = r8.hat
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r8 = r2.emit(r8, r0)
            if (r8 != r1) goto L68
            return r1
        L68:
            kotlinx.coroutines.flow.StateFlowImpl r7 = r7._remainingAttempts
            com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts r8 = new com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts
            r8.<init>()
            r7.getClass()
            r7.updateState(r6, r8)
            goto Lbb
        L76:
            boolean r9 = r8 instanceof com.android.systemui.biometrics.domain.interactor.CredentialStatus.Fail.Error
            if (r9 == 0) goto L9f
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.emit(r6, r0)
            if (r9 != r1) goto L87
            return r1
        L87:
            kotlinx.coroutines.flow.StateFlowImpl r7 = r7._remainingAttempts
            com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts r9 = new com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts
            com.android.systemui.biometrics.domain.interactor.CredentialStatus$Fail$Error r8 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus.Fail.Error) r8
            java.lang.Integer r0 = r8.remainingAttempts
            java.lang.String r8 = r8.urgentMessage
            if (r8 != 0) goto L95
            java.lang.String r8 = ""
        L95:
            r9.<init>(r0, r8)
            r7.getClass()
            r7.updateState(r6, r9)
            goto Lbb
        L9f:
            boolean r8 = r8 instanceof com.android.systemui.biometrics.domain.interactor.CredentialStatus.Fail.Throttled
            if (r8 == 0) goto Lbb
            r0.L$0 = r7
            r0.label = r5
            java.lang.Object r8 = r2.emit(r6, r0)
            if (r8 != r1) goto Lae
            return r1
        Lae:
            kotlinx.coroutines.flow.StateFlowImpl r7 = r7._remainingAttempts
            com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts r8 = new com.android.systemui.biometrics.ui.viewmodel.RemainingAttempts
            r8.<init>()
            r7.getClass()
            r7.updateState(r6, r8)
        Lbb:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.ui.viewmodel.CredentialViewModel.checkCredential(com.android.systemui.biometrics.domain.interactor.CredentialStatus, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
