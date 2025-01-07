package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationStateKt;
import androidx.compose.animation.core.DecayAnimationSpec;
import androidx.compose.foundation.gestures.ScrollScope;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DecayApproachAnimation implements ApproachAnimation {
    public final DecayAnimationSpec decayAnimationSpec;

    public DecayApproachAnimation(DecayAnimationSpec decayAnimationSpec) {
        this.decayAnimationSpec = decayAnimationSpec;
    }

    @Override // androidx.compose.foundation.gestures.snapping.ApproachAnimation
    public final Object approachAnimation(ScrollScope scrollScope, Object obj, Object obj2, Function1 function1, Continuation continuation) {
        Object access$animateDecay = SnapFlingBehaviorKt.access$animateDecay(scrollScope, ((Number) obj).floatValue(), AnimationStateKt.AnimationState$default(28, 0.0f, ((Number) obj2).floatValue()), this.decayAnimationSpec, function1, (ContinuationImpl) continuation);
        return access$animateDecay == CoroutineSingletons.COROUTINE_SUSPENDED ? access$animateDecay : (AnimationResult) access$animateDecay;
    }
}
