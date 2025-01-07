package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.util.kotlin.FlowKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricMessageInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ DeviceEntryFingerprintAuthInteractor $fingerprintAuthInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BiometricMessageInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricMessageInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, BiometricMessageInteractor biometricMessageInteractor) {
        super(3, continuation);
        this.$fingerprintAuthInteractor$inlined = deviceEntryFingerprintAuthInteractor;
        this.this$0 = biometricMessageInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BiometricMessageInteractor$special$$inlined$flatMapLatest$1 biometricMessageInteractor$special$$inlined$flatMapLatest$1 = new BiometricMessageInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$fingerprintAuthInteractor$inlined, this.this$0);
        biometricMessageInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        biometricMessageInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return biometricMessageInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            boolean booleanValue = ((Boolean) this.L$1).booleanValue();
            SafeFlow sample = FlowKt.sample(this.$fingerprintAuthInteractor$inlined.fingerprintFailure, this.this$0.biometricSettingsInteractor.fingerprintAuthCurrentlyAllowed);
            BiometricMessageInteractor biometricMessageInteractor = this.this$0;
            this.label = 1;
            kotlinx.coroutines.flow.FlowKt.ensureActive(flowCollector);
            Object collect = sample.collect(new BiometricMessageInteractor$fingerprintFailMessage$lambda$9$$inlined$filter$1$2(new BiometricMessageInteractor$fingerprintFailMessage$lambda$9$$inlined$map$1$2(flowCollector, booleanValue, biometricMessageInteractor)), this);
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect != coroutineSingletons) {
                collect = unit;
            }
            if (collect == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
