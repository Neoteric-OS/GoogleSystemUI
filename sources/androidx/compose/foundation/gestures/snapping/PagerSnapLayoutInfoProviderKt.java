package androidx.compose.foundation.gestures.snapping;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.pager.PagerMeasureResult;
import androidx.compose.foundation.pager.PagerState;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PagerSnapLayoutInfoProviderKt {
    public static final float dragGestureDelta(PagerState pagerState) {
        return ((PagerMeasureResult) pagerState.getLayoutInfo()).orientation == Orientation.Horizontal ? Float.intBitsToFloat((int) (pagerState.m144getUpDownDifferenceF1C5BW0$foundation_release() >> 32)) : Float.intBitsToFloat((int) (pagerState.m144getUpDownDifferenceF1C5BW0$foundation_release() & 4294967295L));
    }

    public static final boolean isScrollingForward(PagerState pagerState) {
        boolean z = ((PagerMeasureResult) pagerState.getLayoutInfo()).reverseLayout;
        return (((dragGestureDelta(pagerState) > 0.0f ? 1 : (dragGestureDelta(pagerState) == 0.0f ? 0 : -1)) > 0) && z) || (dragGestureDelta(pagerState) <= 0.0f && !z);
    }
}
