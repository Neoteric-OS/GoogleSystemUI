package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.communal.domain.interactor.CommunalSceneInteractor;
import com.android.systemui.communal.shared.model.CommunalScenes;
import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionStep;
import com.android.systemui.power.shared.model.WakefulnessModel;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.StateFlowImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromDreamingTransitionInteractor$startToLockscreenOrGlanceableHubTransition$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ boolean $openHub;
    int label;
    final /* synthetic */ FromDreamingTransitionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromDreamingTransitionInteractor$startToLockscreenOrGlanceableHubTransition$1(FromDreamingTransitionInteractor fromDreamingTransitionInteractor, boolean z, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromDreamingTransitionInteractor;
        this.$openHub = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromDreamingTransitionInteractor$startToLockscreenOrGlanceableHubTransition$1(this.this$0, this.$openHub, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromDreamingTransitionInteractor$startToLockscreenOrGlanceableHubTransition$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (((TransitionStep) ((StateFlowImpl) this.this$0.transitionInteractor.startedKeyguardTransitionStep.$$delegate_0).getValue()).to == KeyguardState.DREAMING && ((WakefulnessModel) ((StateFlowImpl) this.this$0.powerInteractor.detailedWakefulness.$$delegate_0).getValue()).isAwake()) {
                if (this.$openHub) {
                    CommunalSceneInteractor.changeScene$default(this.this$0.communalSceneInteractor, CommunalScenes.Communal, "FromDreamingTransitionInteractor", null, 12);
                } else {
                    FromDreamingTransitionInteractor fromDreamingTransitionInteractor = this.this$0;
                    KeyguardState keyguardState = KeyguardState.LOCKSCREEN;
                    this.label = 1;
                    if (TransitionInteractor.startTransitionTo$default(fromDreamingTransitionInteractor, keyguardState, null, null, "Dream has ended and device is awake", this, 6) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
