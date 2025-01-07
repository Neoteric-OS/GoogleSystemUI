package com.android.systemui.keyboard.shortcut.ui.composable;

import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.RectangleShapeKt$RectangleShape$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ShortcutHelper$Shapes {
    public static final RectangleShapeKt$RectangleShape$1 singlePaneCategory;
    public static final RoundedCornerShape singlePaneFirstCategory;
    public static final RoundedCornerShape singlePaneLastCategory;
    public static final RoundedCornerShape singlePaneSingleCategory;

    static {
        float f = ShortcutHelper$Dimensions.SinglePaneCategoryCornerRadius;
        singlePaneFirstCategory = RoundedCornerShapeKt.m150RoundedCornerShapea9UjIt4$default(12, f, f);
        singlePaneLastCategory = RoundedCornerShapeKt.m150RoundedCornerShapea9UjIt4$default(3, 0.0f, 0.0f);
        singlePaneSingleCategory = RoundedCornerShapeKt.m148RoundedCornerShape0680j_4(f);
        singlePaneCategory = RectangleShapeKt.RectangleShape;
    }
}
