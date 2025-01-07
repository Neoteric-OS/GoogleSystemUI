package androidx.compose.foundation.gestures.snapping;

import androidx.compose.animation.core.AnimationState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class AnimationResult {
    public final AnimationState currentAnimationState;
    public final Object remainingOffset;

    public AnimationResult(Object obj, AnimationState animationState) {
        this.remainingOffset = obj;
        this.currentAnimationState = animationState;
    }
}
