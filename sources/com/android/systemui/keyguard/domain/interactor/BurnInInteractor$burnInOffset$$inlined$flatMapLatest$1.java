package com.android.systemui.keyguard.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ boolean $isXAxis$inlined;
    final /* synthetic */ int $maxBurnInOffsetResourceId$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BurnInInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1(int i, BurnInInteractor burnInInteractor, Continuation continuation, boolean z) {
        super(3, continuation);
        this.this$0 = burnInInteractor;
        this.$maxBurnInOffsetResourceId$inlined = i;
        this.$isXAxis$inlined = z;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        BurnInInteractor burnInInteractor = this.this$0;
        BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1 burnInInteractor$burnInOffset$$inlined$flatMapLatest$1 = new BurnInInteractor$burnInOffset$$inlined$flatMapLatest$1(this.$maxBurnInOffsetResourceId$inlined, burnInInteractor, (Continuation) obj3, this.$isXAxis$inlined);
        burnInInteractor$burnInOffset$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        burnInInteractor$burnInOffset$$inlined$flatMapLatest$1.L$1 = obj2;
        return burnInInteractor$burnInOffset$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            int dimensionPixelSize = this.this$0.context.getResources().getDimensionPixelSize(this.$maxBurnInOffsetResourceId$inlined);
            BurnInInteractor burnInInteractor = this.this$0;
            ChannelFlowTransformLatest mapLatest = FlowKt.mapLatest(new BurnInInteractor$burnInOffset$1$1(dimensionPixelSize, burnInInteractor, null, this.$isXAxis$inlined), burnInInteractor.keyguardInteractor.dozeTimeTick);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, mapLatest, this) == coroutineSingletons) {
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
