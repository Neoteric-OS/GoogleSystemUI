package com.android.systemui.biometrics.domain.interactor;

import com.google.android.systemui.fingerprint.FingerprintInteractiveToAuthProviderGoogle;
import java.util.Optional;
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
public final class SideFpsSensorInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ Optional $fingerprintInteractiveToAuthProvider$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SideFpsSensorInteractor$special$$inlined$flatMapLatest$1(Continuation continuation, Optional optional) {
        super(3, continuation);
        this.$fingerprintInteractiveToAuthProvider$inlined = optional;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        SideFpsSensorInteractor$special$$inlined$flatMapLatest$1 sideFpsSensorInteractor$special$$inlined$flatMapLatest$1 = new SideFpsSensorInteractor$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$fingerprintInteractiveToAuthProvider$inlined);
        sideFpsSensorInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        sideFpsSensorInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return sideFpsSensorInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = (!((Boolean) this.L$1).booleanValue() || this.$fingerprintInteractiveToAuthProvider$inlined.isEmpty()) ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(Boolean.FALSE) : ((FingerprintInteractiveToAuthProviderGoogle) this.$fingerprintInteractiveToAuthProvider$inlined.get()).enabledForCurrentUser;
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2, this) == coroutineSingletons) {
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
