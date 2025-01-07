package com.android.systemui.scene.domain.interactor;

import com.android.compose.animation.scene.ObservableTransitionState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SceneInteractor$transitionState$1 extends SuspendLambda implements Function2 {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ SceneInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SceneInteractor$transitionState$1(SceneInteractor sceneInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = sceneInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        SceneInteractor$transitionState$1 sceneInteractor$transitionState$1 = new SceneInteractor$transitionState$1(this.this$0, continuation);
        sceneInteractor$transitionState$1.L$0 = obj;
        return sceneInteractor$transitionState$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        SceneInteractor$transitionState$1 sceneInteractor$transitionState$1 = (SceneInteractor$transitionState$1) create((ObservableTransitionState) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        sceneInteractor$transitionState$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.logger.logSceneTransition((ObservableTransitionState) this.L$0);
        return Unit.INSTANCE;
    }
}
