package androidx.compose.animation.core;

import androidx.compose.runtime.SnapshotMutableStateImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AnimationStateKt {
    public static AnimationState AnimationState$default(int i, float f, float f2) {
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        return new AnimationState(VectorConvertersKt.FloatToVector, Float.valueOf(f), new AnimationVector1D(f2), Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    public static AnimationState copy$default(AnimationState animationState, float f, float f2, int i) {
        if ((i & 1) != 0) {
            f = ((Number) ((SnapshotMutableStateImpl) animationState.value$delegate).getValue()).floatValue();
        }
        if ((i & 2) != 0) {
            f2 = ((AnimationVector1D) animationState.velocityVector).value;
        }
        return new AnimationState(animationState.typeConverter, Float.valueOf(f), new AnimationVector1D(f2), animationState.lastFrameTimeNanos, animationState.finishedTimeNanos, animationState.isRunning);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public static AnimationState AnimationState$default() {
        TwoWayConverter twoWayConverter = VectorConvertersKt.FloatToVector;
        Float valueOf = Float.valueOf(0.0f);
        return new AnimationState(twoWayConverter, valueOf, (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(valueOf), Long.MIN_VALUE, Long.MIN_VALUE, false);
    }
}
