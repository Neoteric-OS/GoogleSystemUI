package com.android.systemui.keyguard.ui.binder;

import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel;
import com.android.systemui.keyguard.ui.viewmodel.KeyguardIndicationAreaViewModel$special$$inlined$map$2;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class KeyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    final /* synthetic */ KeyguardIndicationAreaViewModel $viewModel$inlined;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KeyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1(Continuation continuation, KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel) {
        super(3, continuation);
        this.$viewModel$inlined = keyguardIndicationAreaViewModel;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        KeyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1 keyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1 = new KeyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1((Continuation) obj3, this.$viewModel$inlined);
        keyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        keyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return keyguardIndicationAreaBinder$bind$2$1$4$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            ((Number) this.L$1).intValue();
            KeyguardIndicationAreaViewModel keyguardIndicationAreaViewModel = this.$viewModel$inlined;
            Flow flowOn = FlowKt.flowOn(new KeyguardIndicationAreaViewModel$special$$inlined$map$2(keyguardIndicationAreaViewModel.burnIn, 1), keyguardIndicationAreaViewModel.mainDispatcher);
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowOn, this) == coroutineSingletons) {
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
