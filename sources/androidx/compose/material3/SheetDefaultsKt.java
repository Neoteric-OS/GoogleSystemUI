package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SheetDefaultsKt {
    public static final float DragHandleVerticalPadding = 22;
    public static final TweenSpec BottomSheetAnimationSpec = AnimationSpecKt.tween$default(300, 0, EasingKt.FastOutSlowInEasing, 2);
}
