package androidx.compose.ui.input.pointer;

import android.view.MotionEvent;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerEvent {
    public final int buttons;
    public final List changes;
    public final InternalPointerEvent internalPointerEvent;
    public int type;

    public PointerEvent(List list, InternalPointerEvent internalPointerEvent) {
        this.changes = list;
        this.internalPointerEvent = internalPointerEvent;
        MotionEvent motionEvent$ui_release = getMotionEvent$ui_release();
        int i = 0;
        this.buttons = motionEvent$ui_release != null ? motionEvent$ui_release.getButtonState() : 0;
        MotionEvent motionEvent$ui_release2 = getMotionEvent$ui_release();
        if (motionEvent$ui_release2 != null) {
            motionEvent$ui_release2.getMetaState();
        }
        MotionEvent motionEvent$ui_release3 = getMotionEvent$ui_release();
        int i2 = 1;
        if (motionEvent$ui_release3 == null) {
            int size = list.size();
            while (true) {
                if (i >= size) {
                    i2 = 3;
                    break;
                }
                PointerInputChange pointerInputChange = (PointerInputChange) list.get(i);
                if (PointerEventKt.changedToUpIgnoreConsumed(pointerInputChange)) {
                    i2 = 2;
                    break;
                } else if (PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange)) {
                    break;
                } else {
                    i++;
                }
            }
        } else {
            int actionMasked = motionEvent$ui_release3.getActionMasked();
            if (actionMasked != 0) {
                if (actionMasked != 1) {
                    if (actionMasked != 2) {
                        switch (actionMasked) {
                            case 8:
                                i = 6;
                                break;
                            case 9:
                                i = 4;
                                break;
                            case 10:
                                i = 5;
                                break;
                        }
                        i2 = i;
                    }
                    i = 3;
                    i2 = i;
                }
                i = 2;
                i2 = i;
            }
            i = 1;
            i2 = i;
        }
        this.type = i2;
    }

    public final MotionEvent getMotionEvent$ui_release() {
        InternalPointerEvent internalPointerEvent = this.internalPointerEvent;
        if (internalPointerEvent != null) {
            return internalPointerEvent.pointerInputEvent.motionEvent;
        }
        return null;
    }
}
