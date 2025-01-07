package com.android.systemui.biometrics.domain.interactor;

import android.content.pm.UserInfo;
import com.android.systemui.biometrics.data.repository.PromptRepositoryImpl;
import com.android.systemui.biometrics.shared.model.BiometricUserInfo;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PromptCredentialInteractor {
    public final StateFlowImpl _verificationError;
    public final CoroutineDispatcher bgDispatcher;
    public final CredentialInteractorImpl credentialInteractor;
    public final Flow prompt;
    public final PromptCredentialInteractor$special$$inlined$map$1 showTitleOnly;
    public final ReadonlyStateFlow verificationError;

    /* JADX WARN: Type inference failed for: r6v1, types: [com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1] */
    public PromptCredentialInteractor(CoroutineDispatcher coroutineDispatcher, PromptRepositoryImpl promptRepositoryImpl, CredentialInteractorImpl credentialInteractorImpl) {
        this.bgDispatcher = coroutineDispatcher;
        this.credentialInteractor = credentialInteractorImpl;
        final ReadonlyStateFlow readonlyStateFlow = promptRepositoryImpl.promptInfo;
        this.showTitleOnly = new Flow() { // from class: com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1

            /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
            /* renamed from: com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1$2, reason: invalid class name */
            public final class AnonymousClass2 implements FlowCollector {
                public final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
                /* renamed from: com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1$2$1, reason: invalid class name */
                public final class AnonymousClass1 extends ContinuationImpl {
                    Object L$0;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x002f  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r5, kotlin.coroutines.Continuation r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1
                        if (r0 == 0) goto L13
                        r0 = r6
                        com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1$2$1 r0 = (com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1.AnonymousClass2.AnonymousClass1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r3 = r1 & r2
                        if (r3 == 0) goto L13
                        int r1 = r1 - r2
                        r0.label = r1
                        goto L18
                    L13:
                        com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1$2$1 r0 = new com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1$2$1
                        r0.<init>(r6)
                    L18:
                        java.lang.Object r6 = r0.result
                        kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L2f
                        if (r2 != r3) goto L27
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L56
                    L27:
                        java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
                        java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
                        r4.<init>(r5)
                        throw r4
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r6)
                        android.hardware.biometrics.PromptInfo r5 = (android.hardware.biometrics.PromptInfo) r5
                        if (r5 == 0) goto L3b
                        android.hardware.biometrics.PromptContentView r6 = r5.getContentView()
                        goto L3c
                    L3b:
                        r6 = 0
                    L3c:
                        if (r6 == 0) goto L46
                        boolean r5 = r5.isContentViewMoreOptionsButtonUsed()
                        if (r5 != 0) goto L46
                        r5 = r3
                        goto L47
                    L46:
                        r5 = 0
                    L47:
                        java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
                        r0.label = r3
                        kotlinx.coroutines.flow.FlowCollector r4 = r4.$this_unsafeFlow
                        java.lang.Object r4 = r4.emit(r5, r0)
                        if (r4 != r1) goto L56
                        return r1
                    L56:
                        kotlin.Unit r4 = kotlin.Unit.INSTANCE
                        return r4
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public final Object collect(FlowCollector flowCollector, Continuation continuation) {
                Object collect = StateFlow.this.collect(new AnonymousClass2(flowCollector), continuation);
                return collect == CoroutineSingletons.COROUTINE_SUSPENDED ? collect : Unit.INSTANCE;
            }
        };
        this.prompt = FlowKt.distinctUntilChanged(FlowKt.combine(readonlyStateFlow, promptRepositoryImpl.challenge, promptRepositoryImpl.userId, promptRepositoryImpl.promptKind, new PromptCredentialInteractor$prompt$1(this, null)));
        StateFlowImpl MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._verificationError = MutableStateFlow;
        this.verificationError = new ReadonlyStateFlow(MutableStateFlow);
    }

    public static final BiometricUserInfo access$userInfo(PromptCredentialInteractor promptCredentialInteractor, int i, boolean z) {
        int credentialOwnerProfile;
        CredentialInteractorImpl credentialInteractorImpl = promptCredentialInteractor.credentialInteractor;
        int credentialOwnerProfile2 = credentialInteractorImpl.userManager.getCredentialOwnerProfile(i);
        if (z) {
            UserInfo profileParent = credentialInteractorImpl.userManager.getProfileParent(i);
            credentialOwnerProfile = profileParent != null ? profileParent.id : credentialInteractorImpl.userManager.getCredentialOwnerProfile(i);
        } else {
            credentialOwnerProfile = credentialInteractorImpl.userManager.getCredentialOwnerProfile(i);
        }
        return new BiometricUserInfo(i, credentialOwnerProfile2, credentialOwnerProfile);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$verifyCredential(com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor r6, com.android.systemui.biometrics.domain.model.BiometricPromptRequest.Credential r7, com.android.internal.widget.LockscreenCredential r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r6.getClass()
            boolean r0 = r9 instanceof com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$1
            if (r0 == 0) goto L16
            r0 = r9
            com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$1 r0 = (com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$1 r0 = new com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$1
            r0.<init>(r6, r9)
        L1b:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 7
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L34
            if (r2 != r5) goto L2c
            kotlin.ResultKt.throwOnFailure(r9)
            goto L63
        L2c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L34:
            kotlin.ResultKt.throwOnFailure(r9)
            if (r8 == 0) goto L70
            boolean r9 = r8.isNone()
            if (r9 == 0) goto L40
            goto L70
        L40:
            com.android.systemui.biometrics.domain.interactor.CredentialInteractorImpl r9 = r6.credentialInteractor
            r9.getClass()
            com.android.systemui.biometrics.domain.interactor.CredentialInteractorImpl$verifyCredential$1 r2 = new com.android.systemui.biometrics.domain.interactor.CredentialInteractorImpl$verifyCredential$1
            r2.<init>(r7, r9, r8, r4)
            kotlinx.coroutines.flow.SafeFlow r7 = new kotlinx.coroutines.flow.SafeFlow
            r7.<init>(r2)
            com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$finalStatus$1 r8 = new com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor$verifyCredential$finalStatus$1
            r8.<init>(r6, r4)
            kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 r6 = new kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1
            r9 = 0
            r6.<init>(r7, r8, r9)
            r0.label = r5
            java.lang.Object r9 = kotlinx.coroutines.flow.FlowKt.lastOrNull(r6, r0)
            if (r9 != r1) goto L63
            goto L75
        L63:
            com.android.systemui.biometrics.domain.interactor.CredentialStatus r9 = (com.android.systemui.biometrics.domain.interactor.CredentialStatus) r9
            if (r9 != 0) goto L6e
            com.android.systemui.biometrics.domain.interactor.CredentialStatus$Fail$Error r6 = new com.android.systemui.biometrics.domain.interactor.CredentialStatus$Fail$Error
            r6.<init>(r4, r3)
            r1 = r6
            goto L75
        L6e:
            r1 = r9
            goto L75
        L70:
            com.android.systemui.biometrics.domain.interactor.CredentialStatus$Fail$Error r1 = new com.android.systemui.biometrics.domain.interactor.CredentialStatus$Fail$Error
            r1.<init>(r4, r3)
        L75:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor.access$verifyCredential(com.android.systemui.biometrics.domain.interactor.PromptCredentialInteractor, com.android.systemui.biometrics.domain.model.BiometricPromptRequest$Credential, com.android.internal.widget.LockscreenCredential, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
