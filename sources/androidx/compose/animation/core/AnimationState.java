package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationState implements State {
    public long finishedTimeNanos;
    public boolean isRunning;
    public long lastFrameTimeNanos;
    public final TwoWayConverter typeConverter;
    public final MutableState value$delegate;
    public AnimationVector velocityVector;

    public /* synthetic */ AnimationState(TwoWayConverter twoWayConverter, Object obj, AnimationVector animationVector, int i) {
        this(twoWayConverter, obj, (i & 4) != 0 ? null : animationVector, Long.MIN_VALUE, Long.MIN_VALUE, false);
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return ((SnapshotMutableStateImpl) this.value$delegate).getValue();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final Object getVelocity() {
        return ((TwoWayConverterImpl) this.typeConverter).convertFromVector.invoke(this.velocityVector);
    }

    public final String toString() {
        return "AnimationState(value=" + ((SnapshotMutableStateImpl) this.value$delegate).getValue() + ", velocity=" + getVelocity() + ", isRunning=" + this.isRunning + ", lastFrameTimeNanos=" + this.lastFrameTimeNanos + ", finishedTimeNanos=" + this.finishedTimeNanos + ')';
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public AnimationState(TwoWayConverter twoWayConverter, Object obj, AnimationVector animationVector, long j, long j2, boolean z) {
        AnimationVector animationVector2;
        this.typeConverter = twoWayConverter;
        this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        if (animationVector != null) {
            animationVector2 = AnimationVectorsKt.copy(animationVector);
        } else {
            animationVector2 = (AnimationVector) ((TwoWayConverterImpl) twoWayConverter).convertToVector.invoke(obj);
            animationVector2.reset$animation_core_release();
        }
        this.velocityVector = animationVector2;
        this.lastFrameTimeNanos = j;
        this.finishedTimeNanos = j2;
        this.isRunning = z;
    }
}
