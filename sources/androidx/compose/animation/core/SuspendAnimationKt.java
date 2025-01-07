package androidx.compose.animation.core;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.MotionDurationScale;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SuspendAnimationKt {
    /* JADX WARN: Removed duplicated region for block: B:18:0x0112 A[Catch: CancellationException -> 0x0043, TryCatch #1 {CancellationException -> 0x0043, blocks: (B:13:0x003f, B:16:0x00fb, B:18:0x0112, B:20:0x013b, B:27:0x0140), top: B:12:0x003f }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0154 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object animate(final androidx.compose.animation.core.AnimationState r24, final androidx.compose.animation.core.Animation r25, long r26, final kotlin.jvm.functions.Function1 r28, kotlin.coroutines.jvm.internal.ContinuationImpl r29) {
        /*
            Method dump skipped, instructions count: 381
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.SuspendAnimationKt.animate(androidx.compose.animation.core.AnimationState, androidx.compose.animation.core.Animation, long, kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public static /* synthetic */ Object animate$default(float f, AnimationSpec animationSpec, Function2 function2, SuspendLambda suspendLambda, int i) {
        if ((i & 8) != 0) {
            animationSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);
        }
        return animate(0.0f, f, 0.0f, animationSpec, function2, suspendLambda);
    }

    public static final Object animateDecay(AnimationState animationState, DecayAnimationSpec decayAnimationSpec, boolean z, Function1 function1, ContinuationImpl continuationImpl) {
        Object animate = animate(animationState, new DecayAnimation(decayAnimationSpec, animationState.typeConverter, ((SnapshotMutableStateImpl) animationState.value$delegate).getValue(), animationState.velocityVector), z ? animationState.lastFrameTimeNanos : Long.MIN_VALUE, function1, continuationImpl);
        return animate == CoroutineSingletons.COROUTINE_SUSPENDED ? animate : Unit.INSTANCE;
    }

    public static final Object animateTo(AnimationState animationState, Object obj, AnimationSpec animationSpec, boolean z, Function1 function1, ContinuationImpl continuationImpl) {
        Object animate = animate(animationState, new TargetBasedAnimation(animationSpec, animationState.typeConverter, ((SnapshotMutableStateImpl) animationState.value$delegate).getValue(), obj, animationState.velocityVector), z ? animationState.lastFrameTimeNanos : Long.MIN_VALUE, function1, continuationImpl);
        return animate == CoroutineSingletons.COROUTINE_SUSPENDED ? animate : Unit.INSTANCE;
    }

    public static /* synthetic */ Object animateTo$default(AnimationState animationState, Object obj, SpringSpec springSpec, boolean z, Function1 function1, ContinuationImpl continuationImpl, int i) {
        if ((i & 2) != 0) {
            springSpec = AnimationSpecKt.spring$default(0.0f, 0.0f, null, 7);
        }
        SpringSpec springSpec2 = springSpec;
        if ((i & 8) != 0) {
            function1 = new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$animateTo$2
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return Unit.INSTANCE;
                }
            };
        }
        return animateTo(animationState, obj, springSpec2, z, function1, continuationImpl);
    }

    public static final void doAnimationFrameWithScale(AnimationScope animationScope, long j, float f, Animation animation, AnimationState animationState, Function1 function1) {
        long durationNanos = f == 0.0f ? animation.getDurationNanos() : (long) ((j - animationScope.startTimeNanos) / f);
        animationScope.lastFrameTimeNanos = j;
        ((SnapshotMutableStateImpl) animationScope.value$delegate).setValue(animation.getValueFromNanos(durationNanos));
        animationScope.velocityVector = animation.getVelocityVectorFromNanos(durationNanos);
        if (animation.isFinishedFromNanos(durationNanos)) {
            animationScope.finishedTimeNanos = animationScope.lastFrameTimeNanos;
            ((SnapshotMutableStateImpl) animationScope.isRunning$delegate).setValue(Boolean.FALSE);
        }
        updateState(animationScope, animationState);
        function1.invoke(animationScope);
    }

    public static final float getDurationScale(CoroutineContext coroutineContext) {
        MotionDurationScale motionDurationScale = (MotionDurationScale) coroutineContext.get(MotionDurationScale.Key.$$INSTANCE);
        float scaleFactor = motionDurationScale != null ? motionDurationScale.getScaleFactor() : 1.0f;
        if (scaleFactor < 0.0f) {
            PreconditionsKt.throwIllegalStateException("negative scale factor");
        }
        return scaleFactor;
    }

    public static final void updateState(AnimationScope animationScope, AnimationState animationState) {
        ((SnapshotMutableStateImpl) animationState.value$delegate).setValue(((SnapshotMutableStateImpl) animationScope.value$delegate).getValue());
        AnimationVector animationVector = animationState.velocityVector;
        AnimationVector animationVector2 = animationScope.velocityVector;
        int size$animation_core_release = animationVector.getSize$animation_core_release();
        for (int i = 0; i < size$animation_core_release; i++) {
            animationVector.set$animation_core_release(i, animationVector2.get$animation_core_release(i));
        }
        animationState.finishedTimeNanos = animationScope.finishedTimeNanos;
        animationState.lastFrameTimeNanos = animationScope.lastFrameTimeNanos;
        animationState.isRunning = ((Boolean) ((SnapshotMutableStateImpl) animationScope.isRunning$delegate).getValue()).booleanValue();
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public static final Object animate(float f, float f2, float f3, AnimationSpec animationSpec, final Function2 function2, SuspendLambda suspendLambda) {
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        Float f4 = new Float(f);
        Float f5 = new Float(f2);
        Float f6 = new Float(f3);
        ?? r1 = ((TwoWayConverterImpl) twoWayConverter).convertToVector;
        AnimationVector animationVector = (AnimationVector) r1.invoke(f6);
        if (animationVector == null) {
            animationVector = ((AnimationVector) r1.invoke(f4)).newVector$animation_core_release();
        }
        AnimationVector animationVector2 = animationVector;
        Object animate = animate(new AnimationState(twoWayConverter, f4, animationVector2, 56), new TargetBasedAnimation(animationSpec, twoWayConverter, f4, f5, animationVector2), Long.MIN_VALUE, new Function1() { // from class: androidx.compose.animation.core.SuspendAnimationKt$animate$3
            final /* synthetic */ TwoWayConverter $typeConverter = VectorConvertersKt.FloatToVector;

            {
                super(1);
            }

            /* JADX WARN: Type inference failed for: r2v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                AnimationScope animationScope = (AnimationScope) obj;
                Function2.this.invoke(((SnapshotMutableStateImpl) animationScope.value$delegate).getValue(), ((TwoWayConverterImpl) this.$typeConverter).convertFromVector.invoke(animationScope.velocityVector));
                return Unit.INSTANCE;
            }
        }, suspendLambda);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (animate != coroutineSingletons) {
            animate = unit;
        }
        return animate == coroutineSingletons ? animate : unit;
    }
}
