package androidx.compose.animation.core;

import androidx.compose.runtime.MonotonicFrameClockKt;
import androidx.compose.ui.platform.InfiniteAnimationPolicy$Key;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class InfiniteAnimationPolicyKt {
    public static final Object withInfiniteAnimationFrameNanos(Function1 function1, ContinuationImpl continuationImpl) {
        if (continuationImpl.getContext().get(InfiniteAnimationPolicy$Key.$$INSTANCE) == null) {
            return MonotonicFrameClockKt.getMonotonicFrameClock(continuationImpl.getContext()).withFrameNanos(function1, continuationImpl);
        }
        throw new ClassCastException();
    }
}
