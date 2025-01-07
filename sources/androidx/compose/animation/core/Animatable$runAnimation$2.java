package androidx.compose.animation.core;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import java.util.concurrent.CancellationException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class Animatable$runAnimation$2 extends SuspendLambda implements Function1 {
    final /* synthetic */ Animation $animation;
    final /* synthetic */ Function1 $block;
    final /* synthetic */ Object $initialVelocity;
    final /* synthetic */ long $startTime;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Animatable this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Animatable$runAnimation$2(Animatable animatable, Object obj, Animation animation, long j, Function1 function1, Continuation continuation) {
        super(1, continuation);
        this.this$0 = animatable;
        this.$initialVelocity = obj;
        this.$animation = animation;
        this.$startTime = j;
        this.$block = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return new Animatable$runAnimation$2(this.this$0, this.$initialVelocity, this.$animation, this.$startTime, this.$block, (Continuation) obj).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Ref$BooleanRef ref$BooleanRef;
        AnimationState animationState;
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Animatable animatable = this.this$0;
                animatable.internalState.velocityVector = (AnimationVector) ((TwoWayConverterImpl) animatable.typeConverter).convertToVector.invoke(this.$initialVelocity);
                ((SnapshotMutableStateImpl) this.this$0.targetValue$delegate).setValue(this.$animation.getTargetValue());
                ((SnapshotMutableStateImpl) this.this$0.isRunning$delegate).setValue(Boolean.TRUE);
                AnimationState animationState2 = this.this$0.internalState;
                final AnimationState animationState3 = new AnimationState(animationState2.typeConverter, ((SnapshotMutableStateImpl) animationState2.value$delegate).getValue(), AnimationVectorsKt.copy(animationState2.velocityVector), animationState2.lastFrameTimeNanos, Long.MIN_VALUE, animationState2.isRunning);
                final Ref$BooleanRef ref$BooleanRef2 = new Ref$BooleanRef();
                Animation animation = this.$animation;
                long j = this.$startTime;
                final Animatable animatable2 = this.this$0;
                final Function1 function1 = this.$block;
                Function1 function12 = new Function1() { // from class: androidx.compose.animation.core.Animatable$runAnimation$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        AnimationScope animationScope = (AnimationScope) obj2;
                        SuspendAnimationKt.updateState(animationScope, Animatable.this.internalState);
                        Animatable animatable3 = Animatable.this;
                        SnapshotMutableStateImpl snapshotMutableStateImpl = (SnapshotMutableStateImpl) animationScope.value$delegate;
                        Object access$clampToBounds = Animatable.access$clampToBounds(animatable3, snapshotMutableStateImpl.getValue());
                        if (Intrinsics.areEqual(access$clampToBounds, snapshotMutableStateImpl.getValue())) {
                            Function1 function13 = function1;
                            if (function13 != null) {
                                function13.invoke(Animatable.this);
                            }
                        } else {
                            ((SnapshotMutableStateImpl) Animatable.this.internalState.value$delegate).setValue(access$clampToBounds);
                            ((SnapshotMutableStateImpl) animationState3.value$delegate).setValue(access$clampToBounds);
                            Function1 function14 = function1;
                            if (function14 != null) {
                                function14.invoke(Animatable.this);
                            }
                            animationScope.cancelAnimation();
                            ref$BooleanRef2.element = true;
                        }
                        return Unit.INSTANCE;
                    }
                };
                this.L$0 = animationState3;
                this.L$1 = ref$BooleanRef2;
                this.label = 1;
                if (SuspendAnimationKt.animate(animationState3, animation, j, function12, this) == coroutineSingletons) {
                    return coroutineSingletons;
                }
                ref$BooleanRef = ref$BooleanRef2;
                animationState = animationState3;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ref$BooleanRef = (Ref$BooleanRef) this.L$1;
                animationState = (AnimationState) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            AnimationEndReason animationEndReason = ref$BooleanRef.element ? AnimationEndReason.BoundReached : AnimationEndReason.Finished;
            Animatable.access$endAnimation(this.this$0);
            return new AnimationResult(animationState, animationEndReason);
        } catch (CancellationException e) {
            Animatable.access$endAnimation(this.this$0);
            throw e;
        }
    }
}
