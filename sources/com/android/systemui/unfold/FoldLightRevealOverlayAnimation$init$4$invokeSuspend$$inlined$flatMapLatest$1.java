package com.android.systemui.unfold;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1 extends SuspendLambda implements Function3 {
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ FoldLightRevealOverlayAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1(FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation, Continuation continuation) {
        super(3, continuation);
        this.this$0 = foldLightRevealOverlayAnimation;
    }

    @Override // kotlin.jvm.functions.Function3
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1 foldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1 = new FoldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1(this.this$0, (Continuation) obj3);
        foldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1.L$0 = (FlowCollector) obj;
        foldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1.L$1 = obj2;
        return foldLightRevealOverlayAnimation$init$4$invokeSuspend$$inlined$flatMapLatest$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            boolean booleanValue = ((Boolean) this.L$1).booleanValue();
            FoldLightRevealOverlayAnimation foldLightRevealOverlayAnimation = this.this$0;
            SafeFlow safeFlow = new SafeFlow(new FoldLightRevealOverlayAnimation$init$4$2$1(foldLightRevealOverlayAnimation, booleanValue, null));
            foldLightRevealOverlayAnimation.getClass();
            FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1 = new FlowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1(new FlowKt__ErrorsKt$catch$$inlined$unsafeFlow$1(safeFlow, new FoldLightRevealOverlayAnimation$catchTimeoutAndLog$1(3, null)), new FoldLightRevealOverlayAnimation$init$4$2$2(this.this$0, null));
            this.label = 1;
            if (FlowKt.emitAll(flowCollector, flowKt__EmittersKt$onCompletion$$inlined$unsafeFlow$1, this) == coroutineSingletons) {
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
