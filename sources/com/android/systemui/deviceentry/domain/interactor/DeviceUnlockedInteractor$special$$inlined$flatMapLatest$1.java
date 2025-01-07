package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.keyguard.domain.interactor.TrustInteractor;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceUnlockedInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ DeviceEntryFaceAuthInteractor $faceAuthInteractor$inlined;
    final /* synthetic */ DeviceEntryFingerprintAuthInteractor $fingerprintAuthInteractor$inlined;
    final /* synthetic */ TrustInteractor $trustInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ DeviceUnlockedInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceUnlockedInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, DeviceUnlockedInteractor deviceUnlockedInteractor, DeviceEntryFaceAuthInteractor deviceEntryFaceAuthInteractor, DeviceEntryFingerprintAuthInteractor deviceEntryFingerprintAuthInteractor, TrustInteractor trustInteractor) {
        super(3, continuation);
        this.this$0 = deviceUnlockedInteractor;
        this.$faceAuthInteractor$inlined = deviceEntryFaceAuthInteractor;
        this.$fingerprintAuthInteractor$inlined = deviceEntryFingerprintAuthInteractor;
        this.$trustInteractor$inlined = trustInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceUnlockedInteractor$special$$inlined$flatMapLatest$1 deviceUnlockedInteractor$special$$inlined$flatMapLatest$1 = new DeviceUnlockedInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.this$0, this.$faceAuthInteractor$inlined, this.$fingerprintAuthInteractor$inlined, this.$trustInteractor$inlined);
        deviceUnlockedInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        deviceUnlockedInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return deviceUnlockedInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Triple triple = (Triple) this.L$1;
            boolean booleanValue = ((Boolean) triple.component1()).booleanValue();
            boolean booleanValue2 = ((Boolean) triple.component2()).booleanValue();
            boolean booleanValue3 = ((Boolean) triple.component3()).booleanValue();
            Flow combine = (booleanValue || booleanValue2 || booleanValue3) ? FlowKt.combine(this.this$0.biometricSettingsInteractor.authenticationFlags, this.$faceAuthInteractor$inlined.isLockedOut(), this.$fingerprintAuthInteractor$inlined.isLockedOut, this.$trustInteractor$inlined.isTrustAgentCurrentlyAllowed, new DeviceUnlockedInteractor$deviceEntryRestrictionReason$1$1(this.this$0, this.$faceAuthInteractor$inlined, booleanValue3, null)) : new DeviceUnlockedInteractor$special$$inlined$map$1(this.this$0.biometricSettingsInteractor.authenticationFlags, 1);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, combine, this) == coroutineSingletons) {
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
