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
final class KeyguardRepositoryImpl$fingerprintSensorLocation$1 extends SuspendLambda implements Function2 {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ KeyguardRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardRepositoryImpl$fingerprintSensorLocation$1(KeyguardRepositoryImpl keyguardRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = keyguardRepositoryImpl;
    }

    public static final void invokeSuspend$sendFpLocation(ProducerScope producerScope, KeyguardRepositoryImpl keyguardRepositoryImpl) {
        Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope)._channel.mo1790trySendJP2dKIU(keyguardRepositoryImpl.authController.mFingerprintSensorLocation);
        if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
            Log.e("KeyguardRepositoryImpl", "Failed to send AuthController.Callback#onFingerprintLocationChanged - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        KeyguardRepositoryImpl$fingerprintSensorLocation$1 keyguardRepositoryImpl$fingerprintSensorLocation$1 = new KeyguardRepositoryImpl$fingerprintSensorLocation$1(this.this$0, continuation);
        keyguardRepositoryImpl$fingerprintSensorLocation$1.L$0 = obj;
        return keyguardRepositoryImpl$fingerprintSensorLocation$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((KeyguardRepositoryImpl$fingerprintSensorLocation$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.android.systemui.biometrics.AuthController$Callback, com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$fingerprintSensorLocation$1$callback$1] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            final KeyguardRepositoryImpl keyguardRepositoryImpl = this.this$0;
            final ?? r1 = new AuthController.Callback() { // from class: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$fingerprintSensorLocation$1$callback$1
                @Override // com.android.systemui.biometrics.AuthController.Callback
                public final void onFingerprintLocationChanged() {
                    KeyguardRepositoryImpl$fingerprintSensorLocation$1.invokeSuspend$sendFpLocation(ProducerScope.this, keyguardRepositoryImpl);
                }
            };
            keyguardRepositoryImpl.authController.addCallback(r1);
            invokeSuspend$sendFpLocation(producerScope, this.this$0);
            final KeyguardRepositoryImpl keyguardRepositoryImpl2 = this.this$0;
            Function0 function0 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.KeyguardRepositoryImpl$fingerprintSensorLocation$1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    KeyguardRepositoryImpl.this.authController.removeCallback(r1);
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
