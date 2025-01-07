package com.android.systemui.touchpad.tutorial.domain.interactor;

import com.android.systemui.model.SysUiState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class TouchpadGesturesInteractor$setGesturesState$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $disabled;
    int label;
    final /* synthetic */ TouchpadGesturesInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TouchpadGesturesInteractor$setGesturesState$1(TouchpadGesturesInteractor touchpadGesturesInteractor, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = touchpadGesturesInteractor;
        this.$disabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new TouchpadGesturesInteractor$setGesturesState$1(this.this$0, this.$disabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        TouchpadGesturesInteractor$setGesturesState$1 touchpadGesturesInteractor$setGesturesState$1 = (TouchpadGesturesInteractor$setGesturesState$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        touchpadGesturesInteractor$setGesturesState$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        SysUiState sysUiState = this.this$0.sysUiState;
        sysUiState.setFlag(8589934592L, this.$disabled);
        this.this$0.displayTracker.getClass();
        sysUiState.commitUpdate(0);
        return Unit.INSTANCE;
    }
}
