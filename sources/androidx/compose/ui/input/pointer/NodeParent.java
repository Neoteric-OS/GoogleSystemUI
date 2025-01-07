package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.LayoutCoordinates;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class NodeParent {
    public final MutableVector children = new MutableVector(new Node[16]);
    public final MutableObjectList removeMatchingPointerInputModifierNodeList = new MutableObjectList(10);

    public boolean buildCache(LongSparseArray longSparseArray, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        if (i <= 0) {
            return false;
        }
        Object[] objArr = mutableVector.content;
        int i2 = 0;
        boolean z2 = false;
        do {
            z2 = ((Node) objArr[i2]).buildCache(longSparseArray, layoutCoordinates, internalPointerEvent, z) || z2;
            i2++;
        } while (i2 < i);
        return z2;
    }

    public void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        while (true) {
            i--;
            if (-1 >= i) {
                return;
            }
            if (((Node) mutableVector.content[i]).pointerIds.size == 0) {
                mutableVector.removeAt(i);
            }
        }
    }
}
