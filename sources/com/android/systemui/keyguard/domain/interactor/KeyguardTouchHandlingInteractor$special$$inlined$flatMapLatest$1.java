package com.android.systemui.keyguard.domain.interactor;

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
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ KeyguardTouchHandlingInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1(KeyguardTouchHandlingInteractor keyguardTouchHandlingInteractor, Continuation continuation) {
        super(3, continuation);
        this.this$0 = keyguardTouchHandlingInteractor;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1 keyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1 = new KeyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        keyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        keyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1.L$1 = obj2;
        return keyguardTouchHandlingInteractor$special$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Flow flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            if (((Boolean) this.L$1).booleanValue()) {
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = new ReadonlyStateFlow(this.this$0._isMenuVisible);
            } else {
                StateFlowImpl stateFlowImpl = this.this$0._isMenuVisible;
                Boolean bool = Boolean.FALSE;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, bool);
                flowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2 = new FlowKt__BuildersKt$flowOf$$inlined$unsafeFlow$2(bool);
            }
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
