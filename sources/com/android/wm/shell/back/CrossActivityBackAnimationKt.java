package com.android.wm.shell.back;

import android.graphics.RectF;
import androidx.compose.animation.AndroidFlingSpline$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CrossActivityBackAnimationKt {
    public static void scaleCentered$default(RectF rectF, float f) {
        float width = (rectF.width() / 2) + rectF.left;
        float height = (rectF.height() / 2) + rectF.top;
        rectF.offset(-width, -height);
        rectF.scale(f);
        rectF.offset(width, height);
    }

    public static final void setInterpolatedRectF(RectF rectF, RectF rectF2, RectF rectF3, float f) {
        if (f < 0.0f || f > 1.0f) {
            throw new IllegalArgumentException("Progress value must be between 0 and 1");
        }
        float f2 = rectF2.left;
        rectF.left = AndroidFlingSpline$$ExternalSyntheticOutline0.m(rectF3.left, f2, f, f2);
        float f3 = rectF2.top;
        rectF.top = AndroidFlingSpline$$ExternalSyntheticOutline0.m(rectF3.top, f3, f, f3);
        float f4 = rectF2.right;
        rectF.right = AndroidFlingSpline$$ExternalSyntheticOutline0.m(rectF3.right, f4, f, f4);
        float f5 = rectF2.bottom;
        rectF.bottom = AndroidFlingSpline$$ExternalSyntheticOutline0.m(rectF3.bottom, f5, f, f5);
    }
}
