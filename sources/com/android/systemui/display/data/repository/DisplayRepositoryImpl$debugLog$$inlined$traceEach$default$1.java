package com.android.systemui.display.data.repository;

import com.android.app.tracing.TraceStateLogger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ TraceStateLogger $stateLogger;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1(TraceStateLogger traceStateLogger, Continuation continuation) {
        super(2, continuation);
        this.$stateLogger = traceStateLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1 displayRepositoryImpl$debugLog$$inlined$traceEach$default$1 = new DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1(this.$stateLogger, continuation);
        displayRepositoryImpl$debugLog$$inlined$traceEach$default$1.L$0 = obj;
        return displayRepositoryImpl$debugLog$$inlined$traceEach$default$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1 displayRepositoryImpl$debugLog$$inlined$traceEach$default$1 = (DisplayRepositoryImpl$debugLog$$inlined$traceEach$default$1) create(obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        displayRepositoryImpl$debugLog$$inlined$traceEach$default$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$stateLogger.log(String.valueOf(this.L$0));
        return Unit.INSTANCE;
    }
}
