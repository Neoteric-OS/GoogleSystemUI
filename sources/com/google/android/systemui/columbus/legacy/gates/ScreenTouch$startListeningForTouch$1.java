package com.google.android.systemui.columbus.legacy.gates;

import android.os.Looper;
import android.view.Choreographer;
import com.android.systemui.shared.system.InputMonitorCompat;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ScreenTouch$startListeningForTouch$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ ScreenTouch this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreenTouch$startListeningForTouch$1(ScreenTouch screenTouch, Continuation continuation) {
        super(2, continuation);
        this.this$0 = screenTouch;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new ScreenTouch$startListeningForTouch$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ScreenTouch$startListeningForTouch$1 screenTouch$startListeningForTouch$1 = (ScreenTouch$startListeningForTouch$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        screenTouch$startListeningForTouch$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        ScreenTouch screenTouch = this.this$0;
        if (screenTouch.inputEventReceiver == null) {
            screenTouch.inputMonitor = (InputMonitorCompat) screenTouch.inputMonitorProvider.get();
            ScreenTouch screenTouch2 = this.this$0;
            InputMonitorCompat inputMonitorCompat = screenTouch2.inputMonitor;
            screenTouch2.inputEventReceiver = inputMonitorCompat != null ? inputMonitorCompat.getInputReceiver(Looper.getMainLooper(), Choreographer.getInstance(), this.this$0.inputEventListener) : null;
        }
        return Unit.INSTANCE;
    }
}
