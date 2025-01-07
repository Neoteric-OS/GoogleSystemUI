package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TargetApproachAnimation implements ApproachAnimation {
    public final SpringSpec animationSpec;

    public TargetApproachAnimation(SpringSpec springSpec) {
        this.animationSpec = springSpec;
    }

    @Override // androidx.compose.foundation.gestures.snapping.ApproachAnimation
    public final Object approachAnimation(ScrollScope scrollScope, Object obj, Object obj2, Function1 function1, Continuation continuation) {
        float floatValue = ((Number) obj).floatValue();
        float floatValue2 = ((Number) obj2).floatValue();
        Object access$animateWithTarget = SnapFlingBehaviorKt.access$animateWithTarget(scrollScope, Math.signum(floatValue2) * Math.abs(floatValue), floatValue, AnimationStateKt.AnimationState$default(28, 0.0f, floatValue2), this.animationSpec, function1, (ContinuationImpl) continuation);
        return access$animateWithTarget == CoroutineSingletons.COROUTINE_SUSPENDED ? access$animateWithTarget : (AnimationResult) access$animateWithTarget;
    }
}
