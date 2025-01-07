package com.android.systemui.keyguard.domain.interactor.scenetransition;

import com.android.compose.animation.scene.ObservableTransitionState;
import com.android.systemui.keyguard.domain.interactor.scenetransition.LockscreenSceneTransitionInteractor$collectProgress$1;
import com.android.systemui.scene.shared.model.Scenes;
import com.android.systemui.util.kotlin.FlowKt;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LockscreenSceneTransitionInteractor$listenForSceneTransitionProgress$1 extends SuspendLambda implements Function2 {
    int label;
    final /* synthetic */ LockscreenSceneTransitionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockscreenSceneTransitionInteractor$listenForSceneTransitionProgress$1(LockscreenSceneTransitionInteractor lockscreenSceneTransitionInteractor, Continuation continuation) {
        super(2, continuation);
        this.this$0 = lockscreenSceneTransitionInteractor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new LockscreenSceneTransitionInteractor$listenForSceneTransitionProgress$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((LockscreenSceneTransitionInteractor$listenForSceneTransitionProgress$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            SafeFlow pairwise = FlowKt.pairwise(this.this$0.sceneInteractor.transitionState, new ObservableTransitionState.Idle(Scenes.Lockscreen));
            LockscreenSceneTransitionInteractor$collectProgress$1.AnonymousClass1 anonymousClass1 = new LockscreenSceneTransitionInteractor$collectProgress$1.AnonymousClass1(this.this$0, 1);
            this.label = 1;
            if (pairwise.collect(anonymousClass1, this) == coroutineSingletons) {
                return coroutineSingletons;
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
