package com.android.systemui.bouncer.domain.interactor;

import com.android.keyguard.KeyguardSecurityModel;
import com.android.systemui.keyguard.shared.model.AuthenticationFlags;
import com.android.systemui.util.kotlin.Septuple;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.CombineKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BouncerMessageInteractor$special$$inlined$combine$1 implements Flow {
    public final /* synthetic */ Flow[] $flows$inlined;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$combine$1$3, reason: invalid class name */
    public final class AnonymousClass3 extends SuspendLambda implements Function3 {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(3, (Continuation) obj3);
            anonymousClass3.L$0 = (FlowCollector) obj;
            anonymousClass3.L$1 = (Object[]) obj2;
            return anonymousClass3.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                Object[] objArr = (Object[]) this.L$1;
                Object obj2 = objArr[0];
                Object obj3 = objArr[1];
                Object obj4 = objArr[2];
                Object obj5 = objArr[3];
                Object obj6 = objArr[4];
                Object obj7 = objArr[5];
                boolean booleanValue = ((Boolean) objArr[6]).booleanValue();
                boolean booleanValue2 = ((Boolean) obj7).booleanValue();
                boolean booleanValue3 = ((Boolean) obj6).booleanValue();
                KeyguardSecurityModel.SecurityMode securityMode = (KeyguardSecurityModel.SecurityMode) obj2;
                Septuple septuple = new Septuple(securityMode, (AuthenticationFlags) obj3, Boolean.valueOf(((Boolean) obj4).booleanValue()), Boolean.valueOf(((Boolean) obj5).booleanValue()), Boolean.valueOf(booleanValue3), Boolean.valueOf(booleanValue2), Boolean.valueOf(booleanValue));
                this.label = 1;
                if (flowCollector.emit(septuple, this) == coroutineSingletons) {
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

    public BouncerMessageInteractor$special$$inlined$combine$1(Flow[] flowArr) {
        this.$flows$inlined = flowArr;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        final Flow[] flowArr = this.$flows$inlined;
        Object combineInternal = CombineKt.combineInternal(continuation, new Function0() { // from class: com.android.systemui.bouncer.domain.interactor.BouncerMessageInteractor$special$$inlined$combine$1.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return new Object[flowArr.length];
            }
        }, new AnonymousClass3(3, null), flowCollector, flowArr);
        return combineInternal == CoroutineSingletons.COROUTINE_SUSPENDED ? combineInternal : Unit.INSTANCE;
    }
}
