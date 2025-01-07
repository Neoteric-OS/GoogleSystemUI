package androidx.compose.animation.core;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class AnimationScope {
    public long finishedTimeNanos = Long.MIN_VALUE;
    public final MutableState isRunning$delegate = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
    public long lastFrameTimeNanos;
    public final Lambda onCancel;
    public final long startTimeNanos;
    public final Object targetValue;
    public final TwoWayConverter typeConverter;
    public final MutableState value$delegate;
    public AnimationVector velocityVector;

    /* JADX WARN: Multi-variable type inference failed */
    public AnimationScope(Object obj, TwoWayConverter twoWayConverter, AnimationVector animationVector, long j, Object obj2, long j2, Function0 function0) {
        this.typeConverter = twoWayConverter;
        this.targetValue = obj2;
        this.startTimeNanos = j2;
        this.onCancel = (Lambda) function0;
        this.value$delegate = SnapshotStateKt.mutableStateOf$default(obj);
        this.velocityVector = AnimationVectorsKt.copy(animationVector);
        this.lastFrameTimeNanos = j;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    public final void cancelAnimation() {
        ((SnapshotMutableStateImpl) this.isRunning$delegate).setValue(Boolean.FALSE);
        this.onCancel.invoke();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    public final Object getVelocity() {
        return ((TwoWayConverterImpl) this.typeConverter).convertFromVector.invoke(this.velocityVector);
    }
}
