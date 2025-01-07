package com.android.compose.animation.scene;

import com.android.compose.animation.scene.content.state.TransitionState;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MutableSceneTransitionLayoutStateImpl$startTransition$3 extends SuspendLambda implements Function2 {
    final /* synthetic */ TransitionState.Transition $transition;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MutableSceneTransitionLayoutStateImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MutableSceneTransitionLayoutStateImpl$startTransition$3(MutableSceneTransitionLayoutStateImpl mutableSceneTransitionLayoutStateImpl, TransitionState.Transition transition, Continuation continuation) {
        super(2, continuation);
        this.this$0 = mutableSceneTransitionLayoutStateImpl;
        this.$transition = transition;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        MutableSceneTransitionLayoutStateImpl$startTransition$3 mutableSceneTransitionLayoutStateImpl$startTransition$3 = new MutableSceneTransitionLayoutStateImpl$startTransition$3(this.this$0, this.$transition, continuation);
        mutableSceneTransitionLayoutStateImpl$startTransition$3.L$0 = obj;
        return mutableSceneTransitionLayoutStateImpl$startTransition$3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        MutableSceneTransitionLayoutStateImpl$startTransition$3 mutableSceneTransitionLayoutStateImpl$startTransition$3 = (MutableSceneTransitionLayoutStateImpl$startTransition$3) create((CoroutineScope) obj, (Continuation) obj2);
        Unit unit = Unit.INSTANCE;
        mutableSceneTransitionLayoutStateImpl$startTransition$3.invokeSuspend(unit);
        return unit;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        List list = this.this$0.stateLinks;
        if (list.size() <= 0) {
            return Unit.INSTANCE;
        }
        list.get(0).getClass();
        throw new ClassCastException();
    }
}
