package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InternalPointerEvent {
    public final LongSparseArray changes;
    public final PointerInputEvent pointerInputEvent;
    public boolean suppressMovementConsumption;

    public InternalPointerEvent(LongSparseArray longSparseArray, PointerInputEvent pointerInputEvent) {
        this.changes = longSparseArray;
        this.pointerInputEvent = pointerInputEvent;
    }

    /* renamed from: activeHoverEvent-0FcD4WY, reason: not valid java name */
    public final boolean m458activeHoverEvent0FcD4WY(long j) {
        Object obj;
        List list = this.pointerInputEvent.pointers;
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                obj = null;
                break;
            }
            obj = list.get(i);
            if (PointerId.m463equalsimpl0(((PointerInputEventData) obj).id, j)) {
                break;
            }
            i++;
        }
        PointerInputEventData pointerInputEventData = (PointerInputEventData) obj;
        if (pointerInputEventData != null) {
            return pointerInputEventData.activeHover;
        }
        return false;
    }
}
