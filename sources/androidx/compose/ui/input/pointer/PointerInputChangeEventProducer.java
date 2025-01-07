package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.compose.ui.platform.AndroidComposeView;
import java.util.ArrayList;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class PointerInputChangeEventProducer {
    public final LongSparseArray previousPointerInputData = new LongSparseArray((Object) null);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class PointerInputData {
        public final boolean down;
        public final long positionOnScreen;
        public final long uptime;

        public PointerInputData(long j, long j2, boolean z) {
            this.uptime = j;
            this.positionOnScreen = j2;
            this.down = z;
        }
    }

    public final InternalPointerEvent produce(PointerInputEvent pointerInputEvent, AndroidComposeView androidComposeView) {
        boolean z;
        long j;
        long j2;
        ArrayList arrayList;
        int i;
        int i2;
        LongSparseArray longSparseArray = new LongSparseArray(((ArrayList) pointerInputEvent.pointers).size());
        ArrayList arrayList2 = (ArrayList) pointerInputEvent.pointers;
        int size = arrayList2.size();
        int i3 = 0;
        while (i3 < size) {
            PointerInputEventData pointerInputEventData = (PointerInputEventData) arrayList2.get(i3);
            long j3 = pointerInputEventData.id;
            LongSparseArray longSparseArray2 = this.previousPointerInputData;
            PointerInputData pointerInputData = (PointerInputData) longSparseArray2.get(j3);
            if (pointerInputData == null) {
                j2 = pointerInputEventData.uptime;
                j = pointerInputEventData.position;
                z = false;
            } else {
                long m557screenToLocalMKHz9U = androidComposeView.m557screenToLocalMKHz9U(pointerInputData.positionOnScreen);
                long j4 = pointerInputData.uptime;
                z = pointerInputData.down;
                j = m557screenToLocalMKHz9U;
                j2 = j4;
            }
            List list = pointerInputEventData.historical;
            long j5 = pointerInputEventData.scrollDelta;
            long j6 = pointerInputEventData.originalEventPosition;
            long j7 = pointerInputEventData.id;
            longSparseArray.put(j7, new PointerInputChange(j7, pointerInputEventData.uptime, pointerInputEventData.position, pointerInputEventData.down, pointerInputEventData.pressure, j2, j, z, pointerInputEventData.type, list, j5, j6));
            long j8 = pointerInputEventData.id;
            boolean z2 = pointerInputEventData.down;
            if (z2) {
                i2 = i3;
                arrayList = arrayList2;
                i = size;
                longSparseArray2.put(j8, new PointerInputData(pointerInputEventData.uptime, pointerInputEventData.positionOnScreen, z2));
            } else {
                arrayList = arrayList2;
                i = size;
                i2 = i3;
                longSparseArray2.remove(j8);
            }
            i3 = i2 + 1;
            arrayList2 = arrayList;
            size = i;
        }
        return new InternalPointerEvent(longSparseArray, pointerInputEvent);
    }
}
