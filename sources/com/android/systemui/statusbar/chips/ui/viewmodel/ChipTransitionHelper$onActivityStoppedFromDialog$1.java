package com.android.systemui.statusbar.chips.ui.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SharedFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ChipTransitionHelper$onActivityStoppedFromDialog$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ChipTransitionHelper this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChipTransitionHelper$onActivityStoppedFromDialog$1(ChipTransitionHelper chipTransitionHelper, Continuation continuation) {
        super(2, continuation);
        this.this$0 = chipTransitionHelper;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ChipTransitionHelper$onActivityStoppedFromDialog$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((ChipTransitionHelper$onActivityStoppedFromDialog$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        Unit unit = Unit.INSTANCE;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SharedFlowImpl sharedFlowImpl = this.this$0.activityStoppedFromDialogEvent;
            this.label = 1;
            if (sharedFlowImpl.emit(unit, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return unit;
    }
}
