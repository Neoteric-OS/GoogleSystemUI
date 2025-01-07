package androidx.compose.ui.platform;

import android.view.FocusFinder;
import android.view.View;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.focus.FocusInteropUtils_androidKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class AndroidComposeView$focusOwner$3 extends FunctionReferenceImpl implements Function1 {
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int i = ((FocusDirection) obj).value;
        AndroidComposeView androidComposeView = (AndroidComposeView) this.receiver;
        AndroidComposeView.Companion companion = AndroidComposeView.Companion;
        androidComposeView.getClass();
        boolean z = false;
        if (!FocusDirection.m284equalsimpl0(i, 7) && !FocusDirection.m284equalsimpl0(i, 8)) {
            Integer m286toAndroidFocusDirection3ESFkO8 = FocusInteropUtils_androidKt.m286toAndroidFocusDirection3ESFkO8(i);
            if (m286toAndroidFocusDirection3ESFkO8 == null) {
                throw new IllegalStateException("Invalid focus direction");
            }
            int intValue = m286toAndroidFocusDirection3ESFkO8.intValue();
            Rect onFetchFocusRect = androidComposeView.onFetchFocusRect();
            android.graphics.Rect androidRect = onFetchFocusRect != null ? RectHelper_androidKt.toAndroidRect(onFetchFocusRect) : null;
            FocusFinder focusFinder = FocusFinder.getInstance();
            View findNextFocus = androidRect == null ? focusFinder.findNextFocus(androidComposeView, androidComposeView.findFocus(), intValue) : focusFinder.findNextFocusFromRect(androidComposeView, androidRect, intValue);
            if (findNextFocus != null) {
                z = FocusInteropUtils_androidKt.requestInteropFocus(findNextFocus, Integer.valueOf(intValue), androidRect);
            }
        }
        return Boolean.valueOf(z);
    }
}
