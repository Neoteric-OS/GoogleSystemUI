package com.android.systemui.communal.data.repository;

import com.android.compose.animation.scene.SceneKey;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneRepositoryImpl$snapToScene$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ SceneKey $toScene;
    int label;
    final /* synthetic */ CommunalSceneRepositoryImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalSceneRepositoryImpl$snapToScene$1(CommunalSceneRepositoryImpl communalSceneRepositoryImpl, SceneKey sceneKey, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalSceneRepositoryImpl;
        this.$toScene = sceneKey;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalSceneRepositoryImpl$snapToScene$1(this.this$0, this.$toScene, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalSceneRepositoryImpl$snapToScene$1 communalSceneRepositoryImpl$snapToScene$1 = (CommunalSceneRepositoryImpl$snapToScene$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalSceneRepositoryImpl$snapToScene$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.sceneDataSource.snapToScene(this.$toScene);
        return Unit.INSTANCE;
    }
}
