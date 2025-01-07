package com.android.systemui.deviceentry.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class OccludingAppDeviceEntryInteractor$special$$inlined$combine$1$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        OccludingAppDeviceEntryInteractor$special$$inlined$combine$1$3 occludingAppDeviceEntryInteractor$special$$inlined$combine$1$3 = new OccludingAppDeviceEntryInteractor$special$$inlined$combine$1$3(3, (Continuation) obj3);
        occludingAppDeviceEntryInteractor$special$$inlined$combine$1$3.L$0 = (FlowCollector) obj;
        occludingAppDeviceEntryInteractor$special$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return occludingAppDeviceEntryInteractor$special$$inlined$combine$1$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object[] objArr = (Object[]) this.L$1;
            boolean z = false;
            Object obj2 = objArr[0];
            Object obj3 = objArr[1];
            Object obj4 = objArr[2];
            Object obj5 = objArr[3];
            Object obj6 = objArr[4];
            boolean booleanValue = ((Boolean) objArr[5]).booleanValue();
            boolean booleanValue2 = ((Boolean) obj6).booleanValue();
            boolean booleanValue3 = ((Boolean) obj5).booleanValue();
            boolean booleanValue4 = ((Boolean) obj4).booleanValue();
            boolean booleanValue5 = ((Boolean) obj3).booleanValue();
            if (((Boolean) obj2).booleanValue() && booleanValue5 && !booleanValue4 && !booleanValue3 && !booleanValue2 && !booleanValue) {
                z = true;
            }
            Boolean valueOf = Boolean.valueOf(z);
            this.label = 1;
            if (flowCollector.emit(valueOf, this) == coroutineSingletons) {
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
