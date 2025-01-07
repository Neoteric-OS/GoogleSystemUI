package androidx.compose.material3.internal;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdModifier;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.Placeable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class LayoutUtilKt {
    public static final int getHeightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.height;
        }
        return 0;
    }

    public static final Object getLayoutId(IntrinsicMeasurable intrinsicMeasurable) {
        Object parentData = intrinsicMeasurable.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return ((LayoutIdModifier) layoutIdParentData).layoutId;
        }
        return null;
    }

    public static final int getWidthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.width;
        }
        return 0;
    }

    public static final int subtractConstraintSafely(int i, int i2) {
        return i == Integer.MAX_VALUE ? i : i - i2;
    }
}
