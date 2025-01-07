package com.android.systemui.communal.domain.interactor;

import com.android.compose.animation.scene.SceneKey;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class CommunalSceneInteractor$snapToScene$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ long $delayMillis;
    final /* synthetic */ KeyguardState $keyguardState;
    final /* synthetic */ String $loggingReason;
    final /* synthetic */ SceneKey $newScene;
    int label;
    final /* synthetic */ CommunalSceneInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CommunalSceneInteractor$snapToScene$1(long j, CommunalSceneInteractor communalSceneInteractor, SceneKey sceneKey, String str, KeyguardState keyguardState, Continuation continuation) {
        super(2, continuation);
        this.$delayMillis = j;
        this.this$0 = communalSceneInteractor;
        this.$newScene = sceneKey;
        this.$loggingReason = str;
        this.$keyguardState = keyguardState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new CommunalSceneInteractor$snapToScene$1(this.$delayMillis, this.this$0, this.$newScene, this.$loggingReason, this.$keyguardState, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((CommunalSceneInteractor$snapToScene$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            long j = this.$delayMillis;
            this.label = 1;
            if (DelayKt.delay(j, this) == coroutineSingletons) {
                return coroutineSingletons;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        boolean areEqual = Intrinsics.areEqual(((StateFlowImpl) this.this$0.currentScene.$$delegate_0).getValue(), this.$newScene);
        Unit unit = Unit.INSTANCE;
        if (areEqual) {
            return unit;
        }
        CommunalSceneInteractor communalSceneInteractor = this.this$0;
        communalSceneInteractor.logger.logSceneChangeRequested((SceneKey) ((StateFlowImpl) communalSceneInteractor.currentScene.$$delegate_0).getValue(), this.$newScene, this.$loggingReason, true);
        CommunalSceneInteractor.access$notifyListeners(this.this$0, this.$newScene, this.$keyguardState);
        this.this$0.repository.snapToScene(this.$newScene);
        return unit;
    }
}
