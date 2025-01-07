package androidx.compose.foundation.lazy.grid;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateItemElement;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.IntOffset;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LazyGridItemScope {
    static Modifier animateItem$default(LazyGridItemScope lazyGridItemScope, Modifier modifier, SpringSpec springSpec, int i) {
        SpringSpec spring$default = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5);
        if ((i & 2) != 0) {
            springSpec = AnimationSpecKt.spring$default(0.0f, 400.0f, new IntOffset(VisibilityThresholdsKt.getVisibilityThreshold()), 1);
        }
        SpringSpec spring$default2 = AnimationSpecKt.spring$default(0.0f, 400.0f, null, 5);
        ((LazyGridItemScopeImpl) lazyGridItemScope).getClass();
        return modifier.then(new LazyLayoutAnimateItemElement(spring$default, springSpec, spring$default2));
    }
}
