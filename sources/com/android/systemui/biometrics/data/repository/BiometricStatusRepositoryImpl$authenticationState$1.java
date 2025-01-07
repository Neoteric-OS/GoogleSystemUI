package com.android.systemui.biometrics.data.repository;

import android.hardware.biometrics.AuthenticationStateListener;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.events.AuthenticationAcquiredInfo;
import android.hardware.biometrics.events.AuthenticationErrorInfo;
import android.hardware.biometrics.events.AuthenticationFailedInfo;
import android.hardware.biometrics.events.AuthenticationHelpInfo;
import android.hardware.biometrics.events.AuthenticationStartedInfo;
import android.hardware.biometrics.events.AuthenticationStoppedInfo;
import android.hardware.biometrics.events.AuthenticationSucceededInfo;
import com.android.systemui.biometrics.shared.model.AuthenticationState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricStatusRepositoryImpl$authenticationState$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BiometricStatusRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricStatusRepositoryImpl$authenticationState$1(BiometricStatusRepositoryImpl biometricStatusRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = biometricStatusRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricStatusRepositoryImpl$authenticationState$1 biometricStatusRepositoryImpl$authenticationState$1 = new BiometricStatusRepositoryImpl$authenticationState$1(this.this$0, continuation);
        biometricStatusRepositoryImpl$authenticationState$1.L$0 = obj;
        return biometricStatusRepositoryImpl$authenticationState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BiometricStatusRepositoryImpl$authenticationState$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            final BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1 biometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1 = new BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1(producerScope);
            final AuthenticationStateListener authenticationStateListener = new AuthenticationStateListener.Stub() { // from class: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$authenticationState$1$authenticationStateListener$1
                public final void onAuthenticationAcquired(AuthenticationAcquiredInfo authenticationAcquiredInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Acquired(authenticationAcquiredInfo.getBiometricSourceType(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationAcquiredInfo.getRequestReason()), authenticationAcquiredInfo.getAcquiredInfo()));
                }

                public final void onAuthenticationError(AuthenticationErrorInfo authenticationErrorInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Error(authenticationErrorInfo.getBiometricSourceType(), authenticationErrorInfo.getErrString(), authenticationErrorInfo.getErrCode(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationErrorInfo.getRequestReason())));
                }

                public final void onAuthenticationFailed(AuthenticationFailedInfo authenticationFailedInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Failed(authenticationFailedInfo.getBiometricSourceType(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationFailedInfo.getRequestReason()), authenticationFailedInfo.getUserId()));
                }

                public final void onAuthenticationHelp(AuthenticationHelpInfo authenticationHelpInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Help(authenticationHelpInfo.getBiometricSourceType(), authenticationHelpInfo.getHelpString(), authenticationHelpInfo.getHelpCode(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationHelpInfo.getRequestReason())));
                }

                public final void onAuthenticationStarted(AuthenticationStartedInfo authenticationStartedInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Started(authenticationStartedInfo.getBiometricSourceType(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationStartedInfo.getRequestReason())));
                }

                public final void onAuthenticationStopped(AuthenticationStoppedInfo authenticationStoppedInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Stopped(authenticationStoppedInfo.getBiometricSourceType()));
                }

                public final void onAuthenticationSucceeded(AuthenticationSucceededInfo authenticationSucceededInfo) {
                    ((BiometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1) Function1.this).invoke(new AuthenticationState.Succeeded(authenticationSucceededInfo.getBiometricSourceType(), authenticationSucceededInfo.isIsStrongBiometric(), BiometricStatusRepositoryKt.access$toAuthenticationReason(authenticationSucceededInfo.getRequestReason()), authenticationSucceededInfo.getUserId()));
                }
            };
            biometricStatusRepositoryImpl$authenticationState$1$updateAuthenticationState$1.invoke(new AuthenticationState.Idle());
            BiometricManager biometricManager = this.this$0.biometricManager;
            if (biometricManager != null) {
                biometricManager.registerAuthenticationStateListener(authenticationStateListener);
            }
            final BiometricStatusRepositoryImpl biometricStatusRepositoryImpl = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.biometrics.data.repository.BiometricStatusRepositoryImpl$authenticationState$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    BiometricManager biometricManager2 = BiometricStatusRepositoryImpl.this.biometricManager;
                    if (biometricManager2 != null) {
                        biometricManager2.unregisterAuthenticationStateListener(authenticationStateListener);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, function0, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
