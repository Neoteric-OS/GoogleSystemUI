package com.android.systemui.unfold;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DisplaySwitchLatencyTracker$waitForDisplaySwitch$3 extends SuspendLambda implements Function1 {
    int label;
    final /* synthetic */ DisplaySwitchLatencyTracker this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplaySwitchLatencyTracker$waitForDisplaySwitch$3(DisplaySwitchLatencyTracker displaySwitchLatencyTracker, Continuation continuation) {
        super(1, continuation);
        this.this$0 = displaySwitchLatencyTracker;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new DisplaySwitchLatencyTracker$waitForDisplaySwitch$3(this.this$0, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DisplaySwitchLatencyTracker displaySwitchLatencyTracker = this.this$0;
            this.label = 1;
            if (DisplaySwitchLatencyTracker.access$waitForScreenTurnedOn(displaySwitchLatencyTracker, this) == coroutineSingletons) {
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
