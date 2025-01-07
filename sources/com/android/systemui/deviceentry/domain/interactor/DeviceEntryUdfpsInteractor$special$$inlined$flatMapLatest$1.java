package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepository;
import com.android.systemui.keyguard.data.repository.DeviceEntryFingerprintAuthRepositoryImpl;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ DeviceEntryFingerprintAuthRepository $fingerprintAuthRepository$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, DeviceEntryFingerprintAuthRepository deviceEntryFingerprintAuthRepository) {
        super(3, continuation);
        this.$fingerprintAuthRepository$inlined = deviceEntryFingerprintAuthRepository;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1 deviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1 = new DeviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$fingerprintAuthRepository$inlined);
        deviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        deviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return deviceEntryUdfpsInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow isRunning = ((Boolean) this.L$1).booleanValue() ? ((DeviceEntryFingerprintAuthRepositoryImpl) this.$fingerprintAuthRepository$inlined).isRunning() : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, isRunning, this) == coroutineSingletons) {
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
