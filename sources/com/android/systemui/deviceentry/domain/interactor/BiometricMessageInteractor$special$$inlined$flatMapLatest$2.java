package com.android.systemui.deviceentry.domain.interactor;

import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BiometricMessageInteractor$special$$inlined$flatMapLatest$2 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BiometricMessageInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BiometricMessageInteractor$special$$inlined$flatMapLatest$2(Continuation continuation, BiometricMessageInteractor biometricMessageInteractor) {
        super(3, continuation);
        this.this$0 = biometricMessageInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BiometricMessageInteractor$special$$inlined$flatMapLatest$2 biometricMessageInteractor$special$$inlined$flatMapLatest$2 = new BiometricMessageInteractor$special$$inlined$flatMapLatest$2((Continuation) obj3, this.this$0);
        biometricMessageInteractor$special$$inlined$flatMapLatest$2.L$0 = (FlowCollector) obj;
        biometricMessageInteractor$special$$inlined$flatMapLatest$2.L$1 = obj2;
        return biometricMessageInteractor$special$$inlined$flatMapLatest$2.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Pair pair = (Pair) this.L$1;
            boolean booleanValue = ((Boolean) pair.component1()).booleanValue();
            boolean booleanValue2 = ((Boolean) pair.component2()).booleanValue();
            Flow biometricMessageInteractor$special$$inlined$map$1 = (booleanValue && booleanValue2) ? new BiometricMessageInteractor$special$$inlined$map$1(this.this$0.coExFaceAcquisitionMsgIdsToShow, 1) : booleanValue2 ? new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new Function1() { // from class: com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$filterConditionForFaceHelpMessages$3$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return Boolean.TRUE;
                }
            }) : new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(new Function1() { // from class: com.android.systemui.deviceentry.domain.interactor.BiometricMessageInteractor$filterConditionForFaceHelpMessages$3$3
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return Boolean.FALSE;
                }
            });
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, biometricMessageInteractor$special$$inlined$map$1, this) == coroutineSingletons) {
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
