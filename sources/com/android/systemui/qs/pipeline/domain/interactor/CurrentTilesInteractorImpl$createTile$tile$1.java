package com.android.systemui.qs.pipeline.domain.interactor;

import com.android.systemui.qs.pipeline.shared.TileSpec;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class CurrentTilesInteractorImpl$createTile$tile$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ TileSpec $spec;
    int label;
    final /* synthetic */ CurrentTilesInteractorImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CurrentTilesInteractorImpl$createTile$tile$1(CurrentTilesInteractorImpl currentTilesInteractorImpl, TileSpec tileSpec, Continuation continuation) {
        super(2, continuation);
        this.this$0 = currentTilesInteractorImpl;
        this.$spec = tileSpec;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CurrentTilesInteractorImpl$createTile$tile$1(this.this$0, this.$spec, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CurrentTilesInteractorImpl$createTile$tile$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CurrentTilesInteractorImpl currentTilesInteractorImpl = this.this$0;
        TileSpec tileSpec = this.$spec;
        currentTilesInteractorImpl.featureFlags.getClass();
        return currentTilesInteractorImpl.tileFactory.createTile(tileSpec.getSpec());
    }
}
