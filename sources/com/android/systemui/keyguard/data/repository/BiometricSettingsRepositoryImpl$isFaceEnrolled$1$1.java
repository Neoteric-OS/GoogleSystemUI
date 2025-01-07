package com.android.systemui.keyguard.data.repository;

import android.util.Log;
import com.android.systemui.biometrics.AuthController;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ AuthController $authController;
    final /* synthetic */ int $selectedUserId;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1(AuthController authController, int i, Continuation continuation) {
        super(2, continuation);
        this.$authController = authController;
        this.$selectedUserId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1 biometricSettingsRepositoryImpl$isFaceEnrolled$1$1 = new BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1(this.$authController, this.$selectedUserId, continuation);
        biometricSettingsRepositoryImpl$isFaceEnrolled$1$1.L$0 = obj;
        return biometricSettingsRepositoryImpl$isFaceEnrolled$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.biometrics.AuthController$Callback, com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final AuthController authController = this.$authController;
            final int i2 = this.$selectedUserId;
            final ?? r1 = new AuthController.Callback() { // from class: com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1$callback$1
                @Override // com.android.systemui.biometrics.AuthController.Callback
                public final void onEnrollmentsChanged(BiometricType biometricType, int i3, boolean z) {
                    if (biometricType == BiometricType.FACE) {
                        AuthController authController2 = authController;
                        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) ProducerScope.this)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(authController2.mFaceProps == null ? false : authController2.mFaceEnrolledForUser.get(i2)));
                        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                            Log.e("BiometricsRepositoryImpl", "Failed to send Face enrollment changed - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                        }
                    }
                }
            };
            authController.addCallback(r1);
            AuthController authController2 = this.$authController;
            Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(Boolean.valueOf(authController2.mFaceProps == null ? false : authController2.mFaceEnrolledForUser.get(this.$selectedUserId)));
            if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                Log.e("BiometricsRepositoryImpl", "Failed to send Initial value of face auth enrollment - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
            }
            final AuthController authController3 = this.$authController;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$isFaceEnrolled$1$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    AuthController.this.removeCallback(r1);
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
