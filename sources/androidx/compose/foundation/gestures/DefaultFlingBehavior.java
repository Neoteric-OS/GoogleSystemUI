package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.DecayAnimationSpec;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultFlingBehavior implements FlingBehavior {
    public DecayAnimationSpec flingDecay;
    public final ScrollableKt$DefaultScrollMotionDurationScale$1 motionDurationScale;

    public DefaultFlingBehavior(DecayAnimationSpec decayAnimationSpec) {
        ScrollableKt$DefaultScrollMotionDurationScale$1 scrollableKt$DefaultScrollMotionDurationScale$1 = ScrollableKt.DefaultScrollMotionDurationScale;
        this.flingDecay = decayAnimationSpec;
        this.motionDurationScale = scrollableKt$DefaultScrollMotionDurationScale$1;
    }

    @Override // androidx.compose.foundation.gestures.FlingBehavior
    public final Object performFling(ScrollingLogic$doFlingAnimation$2$reverseScope$1 scrollingLogic$doFlingAnimation$2$reverseScope$1, float f, Continuation continuation) {
        return BuildersKt.withContext(this.motionDurationScale, new DefaultFlingBehavior$performFling$2(f, this, scrollingLogic$doFlingAnimation$2$reverseScope$1, null), continuation);
    }
}
