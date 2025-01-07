package androidx.compose.foundation.gestures;

import android.view.MotionEvent;
import androidx.compose.ui.input.pointer.PointerEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TapGestureDetector_androidKt {
    public static final boolean isDeepPress(PointerEvent pointerEvent) {
        MotionEvent motionEvent$ui_release = pointerEvent.getMotionEvent$ui_release();
        return (motionEvent$ui_release != null ? motionEvent$ui_release.getClassification() : 0) == 2;
    }
}
