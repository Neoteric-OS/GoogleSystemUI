package com.android.systemui.keyguard.data.repository;

import android.content.pm.UserInfo;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BiometricSettingsRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3(BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl, Continuation continuation) {
        super(3, continuation);
        this.this$0 = biometricSettingsRepositoryImpl;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BiometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3 biometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3 = new BiometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3(this.this$0, (Continuation) obj3);
        biometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3.L$0 = (FlowCollector) obj;
        biometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3.L$1 = obj2;
        return biometricSettingsRepositoryImpl$special$$inlined$flatMapLatest$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i != 0) {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return unit;
        }
        ResultKt.throwOnFailure(obj);
        FlowCollector flowCollector = (FlowCollector) this.L$0;
        UserInfo userInfo = (UserInfo) this.L$1;
        BiometricSettingsRepositoryImpl biometricSettingsRepositoryImpl = this.this$0;
        ReadonlyStateFlow readonlyStateFlow = biometricSettingsRepositoryImpl.areBiometricsEnabledForDeviceEntryFromUserSetting;
        this.label = 1;
        FlowKt.ensureActive(flowCollector);
        readonlyStateFlow.collect(new BiometricSettingsRepositoryImpl$areBiometricsEnabledForCurrentUser$lambda$6$$inlined$map$1$2(flowCollector, biometricSettingsRepositoryImpl, userInfo), this);
        return coroutineSingletons;
    }
}
