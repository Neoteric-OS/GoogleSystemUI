package com.android.systemui.deviceentry.domain.interactor;

import com.android.systemui.authentication.shared.model.AuthenticationMethodModel;
import com.android.systemui.deviceentry.shared.model.DeviceUnlockStatus;
import com.android.systemui.keyguard.domain.interactor.KeyguardTransitionInteractor;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.utils.coroutines.flow.LatestConflatedKt;
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
public final class DeviceUnlockedInteractor$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardTransitionInteractor $keyguardTransitionInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ DeviceUnlockedInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceUnlockedInteractor$special$$inlined$flatMapLatest$2(Continuation continuation, DeviceUnlockedInteractor deviceUnlockedInteractor, KeyguardTransitionInteractor keyguardTransitionInteractor) {
        super(3, continuation);
        this.this$0 = deviceUnlockedInteractor;
        this.$keyguardTransitionInteractor$inlined = keyguardTransitionInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        DeviceUnlockedInteractor$special$$inlined$flatMapLatest$2 deviceUnlockedInteractor$special$$inlined$flatMapLatest$2 = new DeviceUnlockedInteractor$special$$inlined$flatMapLatest$2((Continuation) obj3, this.this$0, this.$keyguardTransitionInteractor$inlined);
        deviceUnlockedInteractor$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        deviceUnlockedInteractor$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return deviceUnlockedInteractor$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flatMapLatestConflated;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            AuthenticationMethodModel authenticationMethodModel = (AuthenticationMethodModel) this.L$1;
            if (!authenticationMethodModel.isSecure) {
                flatMapLatestConflated = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new DeviceUnlockStatus(true, null));
            } else if (authenticationMethodModel.equals(AuthenticationMethodModel.Sim.INSTANCE)) {
                flatMapLatestConflated = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new DeviceUnlockStatus(false, null));
            } else {
                DeviceUnlockedInteractor deviceUnlockedInteractor = this.this$0;
                flatMapLatestConflated = LatestConflatedKt.flatMapLatestConflated(new DeviceUnlockedInteractor$deviceUnlockStatus$1$4(this.this$0, null), FlowKt.combine(deviceUnlockedInteractor.powerInteractor.isAsleep, deviceUnlockedInteractor.isInLockdown, FlowKt.distinctUntilChanged(new DeviceUnlockedInteractor$special$$inlined$map$1(this.$keyguardTransitionInteractor$inlined.getTransitionValueFlow(KeyguardState.AOD), 3)), DeviceUnlockedInteractor$deviceUnlockStatus$1$3.INSTANCE));
            }
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flatMapLatestConflated, this) == coroutineSingletons) {
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
