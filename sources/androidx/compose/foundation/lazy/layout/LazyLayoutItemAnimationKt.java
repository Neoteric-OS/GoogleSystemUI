package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.ui.unit.IntOffset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LazyLayoutItemAnimationKt {
    public static final SpringSpec InterruptionSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntOffset(VisibilityThresholdsKt.getVisibilityThreshold()), 1);
}
