package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationState;
import java.util.concurrent.CancellationException;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ItemFoundInScroll extends CancellationException {
    private final int itemOffset;
    private final AnimationState previousAnimation;

    public ItemFoundInScroll(int i, AnimationState animationState) {
        this.itemOffset = i;
        this.previousAnimation = animationState;
    }

    public final int getItemOffset() {
        return this.itemOffset;
    }

    public final AnimationState getPreviousAnimation() {
        return this.previousAnimation;
    }
}
