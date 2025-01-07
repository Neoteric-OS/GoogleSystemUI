package com.android.systemui.keyguard.data.repository;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BiometricSettingsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = biometricSettingsRepositoryImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2 biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2 = new BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2(this.this$0, continuation);
        biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2.L$0 = obj;
        return biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2 biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2 = (BiometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2) create((Pair) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        biometricSettingsRepositoryImpl$areBiometricsEnabledForDeviceEntryFromUserSetting$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Pair pair = (Pair) this.L$0;
        this.this$0.biometricsEnabledForUser.put(pair.getFirst(), pair.getSecond());
        return Unit.INSTANCE;
    }
}
