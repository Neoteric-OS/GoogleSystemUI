package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.BurnInModel;
import com.android.systemui.keyguard.ui.StateToValue;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        AodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3 aodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3 = new AodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3(3, (Continuation) obj3);
        aodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3.L$0 = (FlowCollector) obj;
        aodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3.L$1 = (Object[]) obj2;
        return aodBurnInViewModel$movement$lambda$2$lambda$1$$inlined$combine$1$3.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        float f;
        float f2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object[] objArr = (Object[]) this.L$1;
            float floatValue = ((Float) objArr[0]).floatValue();
            BurnInModel burnInModel = (BurnInModel) objArr[1];
            StateToValue stateToValue = (StateToValue) objArr[2];
            StateToValue stateToValue2 = (StateToValue) objArr[3];
            StateToValue stateToValue3 = (StateToValue) objArr[4];
            float floatValue2 = ((Float) objArr[5]).floatValue();
            StateToValue stateToValue4 = (StateToValue) objArr[6];
            if (stateToValue4.transitionState.isTransitioning()) {
                Float f3 = stateToValue4.value;
                f2 = f3 != null ? f3.floatValue() : 0.0f;
            } else {
                boolean isTransitioning = stateToValue.transitionState.isTransitioning();
                int i2 = burnInModel.translationY;
                if (isTransitioning) {
                    Float f4 = stateToValue.value;
                    f = f4 != null ? f4.floatValue() : 0.0f;
                    floatValue = i2;
                } else {
                    f = i2 + floatValue2;
                }
                f2 = f + floatValue;
            }
            float f5 = burnInModel.translationX;
            Float f6 = stateToValue2.value;
            float floatValue3 = f5 + (f6 != null ? f6.floatValue() : 0.0f);
            Float f7 = stateToValue3.value;
            BurnInModel burnInModel2 = new BurnInModel((int) (floatValue3 + (f7 != null ? f7.floatValue() : 0.0f)), (int) f2, burnInModel.scale, burnInModel.scaleClockOnly);
            this.label = 1;
            if (flowCollector.emit(burnInModel2, this) == coroutineSingletons) {
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
