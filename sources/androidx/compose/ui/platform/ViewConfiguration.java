package androidx.compose.ui.platform;

import androidx.compose.ui.unit.DpKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ViewConfiguration {
    long getDoubleTapTimeoutMillis();

    default float getHandwritingGestureLineMargin() {
        return 16.0f;
    }

    default float getHandwritingSlop() {
        return 2.0f;
    }

    long getLongPressTimeoutMillis();

    default float getMaximumFlingVelocity() {
        return Float.MAX_VALUE;
    }

    /* renamed from: getMinimumTouchTargetSize-MYxV2XQ */
    default long mo509getMinimumTouchTargetSizeMYxV2XQ() {
        float f = 48;
        return DpKt.m670DpSizeYgX7TsA(f, f);
    }

    float getTouchSlop();
}
