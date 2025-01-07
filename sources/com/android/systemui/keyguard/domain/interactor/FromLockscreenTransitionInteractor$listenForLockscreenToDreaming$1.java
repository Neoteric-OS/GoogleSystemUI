package com.android.systemui.keyguard.domain.interactor;

import com.android.systemui.keyguard.shared.model.KeyguardState;
import com.android.systemui.keyguard.shared.model.TransitionInfo;
import com.android.systemui.util.kotlin.Quad;
import com.android.systemui.util.kotlin.Utils;
import java.util.Set;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1 extends SuspendLambda implements Function2 {
    final /* synthetic */ Set $invalidFromStates;
    int label;
    final /* synthetic */ FromLockscreenTransitionInteractor this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1(FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor, Set set, Continuation continuation) {
        super(2, continuation);
        this.this$0 = fromLockscreenTransitionInteractor;
        this.$invalidFromStates = set;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        return new FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1(this.this$0, this.$invalidFromStates, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        return ((FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1) create((CoroutineScope) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Utils.Companion companion = Utils.Companion;
            FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor = this.this$0;
            SafeFlow sample = companion.sample(new TransitionInteractor$filterRelevantKeyguardState$$inlined$filter$1(fromLockscreenTransitionInteractor.keyguardInteractor.isAbleToDream, fromLockscreenTransitionInteractor), fromLockscreenTransitionInteractor.internalTransitionInteractor.currentTransitionInfoInternal, fromLockscreenTransitionInteractor.transitionInteractor.isFinishedIn$1(KeyguardState.LOCKSCREEN), this.this$0.keyguardInteractor.isActiveDreamLockscreenHosted);
            final Set set = this.$invalidFromStates;
            final FromLockscreenTransitionInteractor fromLockscreenTransitionInteractor2 = this.this$0;
            FlowCollector flowCollector = new FlowCollector() { // from class: com.android.systemui.keyguard.domain.interactor.FromLockscreenTransitionInteractor$listenForLockscreenToDreaming$1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj2, Continuation continuation) {
                    Object startTransitionTo$default;
                    Quad quad = (Quad) obj2;
                    boolean booleanValue = ((Boolean) quad.first).booleanValue();
                    TransitionInfo transitionInfo = (TransitionInfo) quad.second;
                    boolean booleanValue2 = ((Boolean) quad.third).booleanValue();
                    boolean booleanValue3 = ((Boolean) quad.fourth).booleanValue();
                    boolean z = transitionInfo.to == KeyguardState.LOCKSCREEN && !set.contains(transitionInfo.from);
                    Unit unit = Unit.INSTANCE;
                    if (!booleanValue) {
                        return unit;
                    }
                    if (!booleanValue2 && !z) {
                        return unit;
                    }
                    if (booleanValue3) {
                        startTransitionTo$default = TransitionInteractor.startTransitionTo$default(fromLockscreenTransitionInteractor2, KeyguardState.DREAMING_LOCKSCREEN_HOSTED, null, null, null, continuation, 14);
                        if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            return unit;
                        }
                    } else {
                        startTransitionTo$default = TransitionInteractor.startTransitionTo$default(fromLockscreenTransitionInteractor2, KeyguardState.DREAMING, null, null, null, continuation, 14);
                        if (startTransitionTo$default != CoroutineSingletons.COROUTINE_SUSPENDED) {
                            return unit;
                        }
                    }
                    return startTransitionTo$default;
                }
            };
            this.label = 1;
            if (sample.collect(flowCollector, this) == coroutineSingletons) {
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
