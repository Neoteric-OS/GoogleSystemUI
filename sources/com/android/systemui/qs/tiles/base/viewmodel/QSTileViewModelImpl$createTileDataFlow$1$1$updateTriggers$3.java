package com.android.systemui.qs.tiles.base.viewmodel;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ QSTileViewModelImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3(QSTileViewModelImpl qSTileViewModelImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = qSTileViewModelImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3 qSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3 = (QSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3) create((FlowCollector) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        qSTileViewModelImpl$createTileDataFlow$1$1$updateTriggers$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        QSTileViewModelImpl qSTileViewModelImpl = this.this$0;
        qSTileViewModelImpl.qsTileLogger.logInitialRequest(qSTileViewModelImpl.config.tileSpec);
        return Unit.INSTANCE;
    }
}
