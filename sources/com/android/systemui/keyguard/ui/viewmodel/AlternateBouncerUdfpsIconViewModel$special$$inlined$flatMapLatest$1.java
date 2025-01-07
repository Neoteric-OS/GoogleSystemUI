package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.biometrics.domain.interactor.FingerprintPropertyInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.EmptyFlow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AlternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ FingerprintPropertyInteractor $fingerprintPropertyInteractor$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1(Continuation continuation, FingerprintPropertyInteractor fingerprintPropertyInteractor) {
        super(3, continuation);
        this.$fingerprintPropertyInteractor$inlined = fingerprintPropertyInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AlternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1 alternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1 = new AlternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1((Continuation) obj3, this.$fingerprintPropertyInteractor$inlined);
        alternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        alternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return alternateBouncerUdfpsIconViewModel$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Flow alternateBouncerUdfpsIconViewModel$special$$inlined$map$1 = ((Boolean) this.L$1).booleanValue() ? new AlternateBouncerUdfpsIconViewModel$special$$inlined$map$1(this.$fingerprintPropertyInteractor$inlined.udfpsSensorBounds, 1) : EmptyFlow.INSTANCE;
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, alternateBouncerUdfpsIconViewModel$special$$inlined$map$1, this) == coroutineSingletons) {
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
