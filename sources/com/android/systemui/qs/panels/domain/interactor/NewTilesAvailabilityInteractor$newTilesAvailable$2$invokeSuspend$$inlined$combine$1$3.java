package com.android.systemui.qs.panels.domain.interactor;

import java.util.Map;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NewTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        NewTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3 newTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3 = new NewTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3(3, (Continuation) obj3);
        newTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3.L$0 = (FlowCollector) obj;
        newTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return newTilesAvailabilityInteractor$newTilesAvailable$2$invokeSuspend$$inlined$combine$1$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Map map = MapsKt.toMap((Pair[]) ((Object[]) this.L$1));
            this.label = 1;
            if (flowCollector.emit(map, this) == coroutineSingletons) {
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
