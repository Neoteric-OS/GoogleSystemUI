package com.android.systemui.keyguard.data.repository;

import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.IBiometricEnabledOnKeyguardCallback;
import android.util.Log;
import kotlin.Pair;
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
final class BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ BiometricManager $biometricManager;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1(BiometricManager biometricManager, Continuation continuation) {
        super(2, continuation);
        this.$biometricManager = biometricManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1 biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1 = new BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1(this.$biometricManager, continuation);
        biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1.L$0 = obj;
        return biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1) create((ProducerScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final ProducerScope producerScope = (ProducerScope) this.L$0;
            IBiometricEnabledOnKeyguardCallback iBiometricEnabledOnKeyguardCallback = new IBiometricEnabledOnKeyguardCallback.Stub() { // from class: com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1$callback$1
                public final void onChanged(boolean z, int i2) {
                    ProducerScope producerScope2 = ProducerScope.this;
                    Object mo1790trySendJP2dKIU = ((ProducerCoroutine) producerScope2)._channel.mo1790trySendJP2dKIU(new Pair(Integer.valueOf(i2), Boolean.valueOf(z)));
                    if (mo1790trySendJP2dKIU instanceof ChannelResult.Failed) {
                        Log.e("BiometricsRepositoryImpl", "Failed to send biometricsEnabled state changed - downstream canceled or failed.", ChannelResult.m1791exceptionOrNullimpl(mo1790trySendJP2dKIU));
                    }
                }
            };
            BiometricManager biometricManager = this.$biometricManager;
            if (biometricManager != null) {
                biometricManager.registerEnabledOnKeyguardCallback(iBiometricEnabledOnKeyguardCallback);
            }
            AnonymousClass1 anonymousClass1 = new Function0() { // from class: com.android.systemui.keyguard.data.repository.BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$1.1
                @Override // kotlin.jvm.functions.Function0
                public final /* bridge */ /* synthetic */ Object invoke() {
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, anonymousClass1, this) == coroutineSingletons) {
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
