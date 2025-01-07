package com.android.systemui.communal.log;

import com.android.systemui.communal.shared.log.CommunalUiEvent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalLoggerStartable$start$2 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CommunalLoggerStartable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalLoggerStartable$start$2(CommunalLoggerStartable communalLoggerStartable, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalLoggerStartable;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        CommunalLoggerStartable$start$2 communalLoggerStartable$start$2 = new CommunalLoggerStartable$start$2(this.this$0, continuation);
        communalLoggerStartable$start$2.L$0 = obj;
        return communalLoggerStartable$start$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalLoggerStartable$start$2 communalLoggerStartable$start$2 = (CommunalLoggerStartable$start$2) create((CommunalUiEvent) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalLoggerStartable$start$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.uiEventLogger.log((CommunalUiEvent) this.L$0);
        return Unit.INSTANCE;
    }
}
