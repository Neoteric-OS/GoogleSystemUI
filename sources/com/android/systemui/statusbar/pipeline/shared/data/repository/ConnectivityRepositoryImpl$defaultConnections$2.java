package com.android.systemui.statusbar.pipeline.shared.data.repository;

import com.android.systemui.statusbar.pipeline.shared.ConnectivityInputLogger;
import com.android.systemui.statusbar.pipeline.shared.data.model.DefaultConnectionModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class ConnectivityRepositoryImpl$defaultConnections$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ ConnectivityInputLogger $logger;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConnectivityRepositoryImpl$defaultConnections$2(ConnectivityInputLogger connectivityInputLogger, Continuation continuation) {
        super(2, continuation);
        this.$logger = connectivityInputLogger;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        ConnectivityRepositoryImpl$defaultConnections$2 connectivityRepositoryImpl$defaultConnections$2 = new ConnectivityRepositoryImpl$defaultConnections$2(this.$logger, continuation);
        connectivityRepositoryImpl$defaultConnections$2.L$0 = obj;
        return connectivityRepositoryImpl$defaultConnections$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        ConnectivityRepositoryImpl$defaultConnections$2 connectivityRepositoryImpl$defaultConnections$2 = (ConnectivityRepositoryImpl$defaultConnections$2) create((DefaultConnectionModel) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        connectivityRepositoryImpl$defaultConnections$2.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.$logger.logDefaultConnectionsChanged((DefaultConnectionModel) this.L$0);
        return Unit.INSTANCE;
    }
}
