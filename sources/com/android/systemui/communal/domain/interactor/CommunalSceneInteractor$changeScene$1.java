package com.android.systemui.communal.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.compose.animation.scene.TransitionKey;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneInteractor$changeScene$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ KeyguardState $keyguardState;
    final /* synthetic */ String $loggingReason;
    final /* synthetic */ SceneKey $newScene;
    final /* synthetic */ TransitionKey $transitionKey;
    int label;
    final /* synthetic */ CommunalSceneInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalSceneInteractor$changeScene$1(CommunalSceneInteractor communalSceneInteractor, SceneKey sceneKey, String str, KeyguardState keyguardState, TransitionKey transitionKey, Continuation continuation) {
        super(2, continuation);
        this.this$0 = communalSceneInteractor;
        this.$newScene = sceneKey;
        this.$loggingReason = str;
        this.$keyguardState = keyguardState;
        this.$transitionKey = transitionKey;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalSceneInteractor$changeScene$1(this.this$0, this.$newScene, this.$loggingReason, this.$keyguardState, this.$transitionKey, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        CommunalSceneInteractor$changeScene$1 communalSceneInteractor$changeScene$1 = (CommunalSceneInteractor$changeScene$1) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        communalSceneInteractor$changeScene$1.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        boolean areEqual = Intrinsics.areEqual(((StateFlowImpl) this.this$0.currentScene.$$delegate_0).getValue(), this.$newScene);
        Unit unit = Unit.INSTANCE;
        if (areEqual) {
            return unit;
        }
        CommunalSceneInteractor communalSceneInteractor = this.this$0;
        communalSceneInteractor.logger.logSceneChangeRequested((SceneKey) ((StateFlowImpl) communalSceneInteractor.currentScene.$$delegate_0).getValue(), this.$newScene, this.$loggingReason, false);
        CommunalSceneInteractor.access$notifyListeners(this.this$0, this.$newScene, this.$keyguardState);
        this.this$0.repository.changeScene(this.$newScene, this.$transitionKey);
        return unit;
    }
}
