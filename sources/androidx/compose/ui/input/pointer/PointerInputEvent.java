package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerInputEvent {
    public final MotionEvent motionEvent;
    public final List pointers;

    public PointerInputEvent(List list, MotionEvent motionEvent) {
        this.pointers = list;
        this.motionEvent = motionEvent;
    }
}
