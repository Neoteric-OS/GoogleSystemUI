package com.android.systemui.qs.tiles.impl.saver.domain.interactor;

import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class DataSaverTileUserActionInteractor$handleInput$2$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $wasEnabled;
    int label;
    final /* synthetic */ DataSaverTileUserActionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DataSaverTileUserActionInteractor$handleInput$2$1(DataSaverTileUserActionInteractor dataSaverTileUserActionInteractor, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = dataSaverTileUserActionInteractor;
        this.$wasEnabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new DataSaverTileUserActionInteractor$handleInput$2$1(this.this$0, this.$wasEnabled, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        DataSaverTileUserActionInteractor$handleInput$2$1 dataSaverTileUserActionInteractor$handleInput$2$1 = (DataSaverTileUserActionInteractor$handleInput$2$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        dataSaverTileUserActionInteractor$handleInput$2$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.dataSaverController.setDataSaverEnabled(!this.$wasEnabled);
        return Unit.INSTANCE;
    }
}
