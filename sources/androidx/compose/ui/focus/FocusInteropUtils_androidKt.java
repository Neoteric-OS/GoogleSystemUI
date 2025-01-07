package androidx.compose.ui.focus;

import android.view.FocusFinder;
import android.view.View;
import android.view.ViewGroup;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FocusInteropUtils_androidKt {
    public static final Rect calculateBoundingRectRelativeTo(View view, AndroidComposeView androidComposeView) {
        int[] iArr = FocusInteropUtils.tempCoordinates;
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        androidComposeView.getLocationInWindow(iArr);
        float f = i - iArr[0];
        float f2 = i2 - iArr[1];
        return new Rect(f, f2, view.getWidth() + f, view.getHeight() + f2);
    }

    public static final boolean requestInteropFocus(View view, Integer num, android.graphics.Rect rect) {
        if (num == null) {
            return view.requestFocus();
        }
        if (!(view instanceof ViewGroup)) {
            return view.requestFocus(num.intValue(), rect);
        }
        ViewGroup viewGroup = (ViewGroup) view;
        if (viewGroup.isFocused()) {
            return true;
        }
        if (viewGroup.isFocusable() && !view.hasFocus()) {
            return view.requestFocus(num.intValue(), rect);
        }
        if (view instanceof AndroidComposeView) {
            return view.requestFocus(num.intValue(), rect);
        }
        if (rect != null) {
            View findNextFocusFromRect = FocusFinder.getInstance().findNextFocusFromRect(viewGroup, rect, num.intValue());
            return findNextFocusFromRect != null ? findNextFocusFromRect.requestFocus(num.intValue(), rect) : view.requestFocus(num.intValue(), rect);
        }
        View findNextFocus = FocusFinder.getInstance().findNextFocus(viewGroup, view.hasFocus() ? view.findFocus() : null, num.intValue());
        return findNextFocus != null ? findNextFocus.requestFocus(num.intValue()) : view.requestFocus(num.intValue());
    }

    /* renamed from: toAndroidFocusDirection-3ESFkO8, reason: not valid java name */
    public static final Integer m286toAndroidFocusDirection3ESFkO8(int i) {
        if (FocusDirection.m284equalsimpl0(i, 5)) {
            return 33;
        }
        if (FocusDirection.m284equalsimpl0(i, 6)) {
            return 130;
        }
        if (FocusDirection.m284equalsimpl0(i, 3)) {
            return 17;
        }
        if (FocusDirection.m284equalsimpl0(i, 4)) {
            return 66;
        }
        if (FocusDirection.m284equalsimpl0(i, 1)) {
            return 2;
        }
        return FocusDirection.m284equalsimpl0(i, 2) ? 1 : null;
    }

    public static final FocusDirection toFocusDirection(int i) {
        if (i == 1) {
            return new FocusDirection(2);
        }
        if (i == 2) {
            return new FocusDirection(1);
        }
        if (i == 17) {
            return new FocusDirection(3);
        }
        if (i == 33) {
            return new FocusDirection(5);
        }
        if (i == 66) {
            return new FocusDirection(4);
        }
        if (i != 130) {
            return null;
        }
        return new FocusDirection(6);
    }
}
