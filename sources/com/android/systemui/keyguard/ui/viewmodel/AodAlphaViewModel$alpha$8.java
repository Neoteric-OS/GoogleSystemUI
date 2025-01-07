package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AodAlphaViewModel$alpha$8 extends SuspendLambda implements Function6 {
    /* synthetic */ float F$0;
    /* synthetic */ float F$1;
    /* synthetic */ float F$2;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    public AodAlphaViewModel$alpha$8(Continuation continuation) {
        super(6, continuation);
    }

    @Override // kotlin.jvm.functions.Function6
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6) {
        float floatValue = ((Number) obj3).floatValue();
        float floatValue2 = ((Number) obj4).floatValue();
        float floatValue3 = ((Number) obj5).floatValue();
        AodAlphaViewModel$alpha$8 aodAlphaViewModel$alpha$8 = new AodAlphaViewModel$alpha$8((Continuation) obj6);
        aodAlphaViewModel$alpha$8.L$0 = (FlowCollector) obj;
        aodAlphaViewModel$alpha$8.L$1 = (TransitionStep) obj2;
        aodAlphaViewModel$alpha$8.F$0 = floatValue;
        aodAlphaViewModel$alpha$8.F$1 = floatValue2;
        aodAlphaViewModel$alpha$8.F$2 = floatValue3;
        return aodAlphaViewModel$alpha$8.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            TransitionStep transitionStep = (TransitionStep) this.L$1;
            float f = this.F$0;
            float f2 = this.F$1;
            KeyguardState keyguardState = transitionStep.to;
            KeyguardState keyguardState2 = KeyguardState.GONE;
            if (keyguardState != keyguardState2) {
                KeyguardState keyguardState3 = transitionStep.from;
                if (keyguardState3 == keyguardState2 && keyguardState == KeyguardState.AOD) {
                    Float f3 = new Float(f);
                    this.L$0 = null;
                    this.label = 2;
                    if (flowCollector.emit(f3, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                } else if (keyguardState3 == keyguardState2 && keyguardState == KeyguardState.DOZING) {
                    Float f4 = new Float(f2);
                    this.L$0 = null;
                    this.label = 3;
                    if (flowCollector.emit(f4, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            } else if (transitionStep.value == 1.0f) {
                Float f5 = new Float(0.0f);
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(f5, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
            }
        } else {
            if (i != 1 && i != 2 && i != 3 && i != 4) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
