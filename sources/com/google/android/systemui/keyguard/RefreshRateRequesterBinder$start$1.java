package com.google.android.systemui.keyguard;

import com.google.android.systemui.keyguard.domain.interactor.RefreshRateInteractor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class RefreshRateRequesterBinder$start$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ RefreshRateRequesterBinder this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RefreshRateRequesterBinder$start$1(RefreshRateRequesterBinder refreshRateRequesterBinder, Continuation continuation) {
        super(2, continuation);
        this.this$0 = refreshRateRequesterBinder;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new RefreshRateRequesterBinder$start$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((RefreshRateRequesterBinder$start$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ChannelFlowTransformLatest channelFlowTransformLatest = ((RefreshRateInteractor) this.this$0.interactor.get()).requestOverridingMaxRefreshRate;
            final RefreshRateRequesterBinder refreshRateRequesterBinder = this.this$0;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.google.android.systemui.keyguard.RefreshRateRequesterBinder$start$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    ((RefreshRateInteractor) RefreshRateRequesterBinder.this.interactor.get()).authController.requestMaxRefreshRate(((Boolean) obj2).booleanValue());
                    return Unit.INSTANCE;
                }
            };
            this.label = 1;
            if (channelFlowTransformLatest.collect(flowCollector, this) == coroutineSingletons) {
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
