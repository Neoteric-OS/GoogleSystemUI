package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.util.PointerIdArray;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.node.PointerInputModifierNode;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Node extends NodeParent {
    public NodeCoordinator coordinates;
    public boolean hasExited;
    public boolean isIn;
    public final Modifier.Node modifierNode;
    public PointerEvent pointerEvent;
    public final PointerIdArray pointerIds;
    public final LongSparseArray relevantChanges;
    public boolean wasIn;

    public Node(Modifier.Node node) {
        this.modifierNode = node;
        PointerIdArray pointerIdArray = new PointerIdArray();
        pointerIdArray.internalArray = new long[2];
        this.pointerIds = pointerIdArray;
        this.relevantChanges = new LongSparseArray(2);
        this.isIn = true;
        this.hasExited = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v0, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v10, types: [int] */
    /* JADX WARN: Type inference failed for: r5v55 */
    /* JADX WARN: Type inference failed for: r5v56, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v57, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v58 */
    /* JADX WARN: Type inference failed for: r5v59 */
    /* JADX WARN: Type inference failed for: r5v60 */
    /* JADX WARN: Type inference failed for: r5v61 */
    /* JADX WARN: Type inference failed for: r5v62 */
    /* JADX WARN: Type inference failed for: r5v63 */
    /* JADX WARN: Type inference failed for: r5v64 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v14 */
    /* JADX WARN: Type inference failed for: r8v15, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v16 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v18, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v20 */
    /* JADX WARN: Type inference failed for: r8v21 */
    /* JADX WARN: Type inference failed for: r8v22 */
    /* JADX WARN: Type inference failed for: r8v23 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.util.List] */
    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final boolean buildCache(LongSparseArray longSparseArray, LayoutCoordinates layoutCoordinates, InternalPointerEvent internalPointerEvent, boolean z) {
        LongSparseArray longSparseArray2;
        PointerIdArray pointerIdArray;
        Object obj;
        boolean z2;
        boolean z3;
        boolean z4;
        PointerEvent pointerEvent;
        boolean z5;
        int i;
        int i2;
        int i3;
        long j;
        List list;
        boolean buildCache = super.buildCache(longSparseArray, layoutCoordinates, internalPointerEvent, z);
        DelegatingNode delegatingNode = this.modifierNode;
        if (!delegatingNode.isAttached) {
            return true;
        }
        ?? r8 = 0;
        while (delegatingNode != 0) {
            if (delegatingNode instanceof PointerInputModifierNode) {
                this.coordinates = DelegatableNodeKt.m503requireCoordinator64DMado((PointerInputModifierNode) delegatingNode, 16);
            } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode.delegate;
                int i4 = 0;
                delegatingNode = delegatingNode;
                r8 = r8;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
                        i4++;
                        r8 = r8;
                        if (i4 == 1) {
                            delegatingNode = node;
                        } else {
                            if (r8 == 0) {
                                r8 = new MutableVector(new Modifier.Node[16]);
                            }
                            if (delegatingNode != 0) {
                                r8.add(delegatingNode);
                                delegatingNode = 0;
                            }
                            r8.add(node);
                        }
                    }
                    node = node.child;
                    delegatingNode = delegatingNode;
                    r8 = r8;
                }
                if (i4 == 1) {
                }
            }
            delegatingNode = DelegatableNodeKt.access$pop(r8);
        }
        int size = longSparseArray.size();
        int i5 = 0;
        while (true) {
            longSparseArray2 = this.relevantChanges;
            pointerIdArray = this.pointerIds;
            if (i5 >= size) {
                break;
            }
            long keyAt = longSparseArray.keyAt(i5);
            PointerInputChange pointerInputChange = (PointerInputChange) longSparseArray.valueAt(i5);
            if (pointerIdArray.contains(keyAt)) {
                long j2 = pointerInputChange.previousPosition;
                if ((((j2 & 9223372034707292159L) - 9187343246269874177L) & (-9223372034707292160L)) == -9223372034707292160L) {
                    long j3 = pointerInputChange.position;
                    if ((((j3 & 9223372034707292159L) - 9187343246269874177L) & (-9223372034707292160L)) == -9223372034707292160L) {
                        List list2 = pointerInputChange._historical;
                        if (list2 == null) {
                            list2 = EmptyList.INSTANCE;
                        }
                        ArrayList arrayList = new ArrayList(list2.size());
                        List list3 = pointerInputChange._historical;
                        if (list3 == null) {
                            list3 = EmptyList.INSTANCE;
                        }
                        i = size;
                        int size2 = list3.size();
                        z5 = buildCache;
                        int i6 = 0;
                        while (i6 < size2) {
                            int i7 = size2;
                            HistoricalChange historicalChange = (HistoricalChange) list3.get(i6);
                            long j4 = keyAt;
                            long j5 = historicalChange.position;
                            if ((((j5 & 9223372034707292159L) - 9187343246269874177L) & (-9223372034707292160L)) == -9223372034707292160L) {
                                list = list3;
                                NodeCoordinator nodeCoordinator = this.coordinates;
                                Intrinsics.checkNotNull(nodeCoordinator);
                                long mo483localPositionOfS_NoaFU = nodeCoordinator.mo483localPositionOfS_NoaFU(layoutCoordinates, j5);
                                i3 = i5;
                                j = j3;
                                arrayList.add(new HistoricalChange(historicalChange.uptimeMillis, mo483localPositionOfS_NoaFU, historicalChange.originalEventPosition));
                            } else {
                                i3 = i5;
                                j = j3;
                                list = list3;
                            }
                            i6++;
                            i5 = i3;
                            list3 = list;
                            size2 = i7;
                            j3 = j;
                            keyAt = j4;
                        }
                        i2 = i5;
                        long j6 = keyAt;
                        NodeCoordinator nodeCoordinator2 = this.coordinates;
                        Intrinsics.checkNotNull(nodeCoordinator2);
                        long mo483localPositionOfS_NoaFU2 = nodeCoordinator2.mo483localPositionOfS_NoaFU(layoutCoordinates, j2);
                        NodeCoordinator nodeCoordinator3 = this.coordinates;
                        Intrinsics.checkNotNull(nodeCoordinator3);
                        PointerInputChange pointerInputChange2 = new PointerInputChange(pointerInputChange.id, pointerInputChange.uptimeMillis, nodeCoordinator3.mo483localPositionOfS_NoaFU(layoutCoordinates, j3), pointerInputChange.pressed, pointerInputChange.pressure, pointerInputChange.previousUptimeMillis, mo483localPositionOfS_NoaFU2, pointerInputChange.previousPressed, pointerInputChange.type, arrayList, pointerInputChange.scrollDelta, pointerInputChange.originalEventPosition);
                        PointerInputChange pointerInputChange3 = pointerInputChange.consumedDelegate;
                        if (pointerInputChange3 == null) {
                            pointerInputChange3 = pointerInputChange;
                        }
                        pointerInputChange2.consumedDelegate = pointerInputChange3;
                        PointerInputChange pointerInputChange4 = pointerInputChange.consumedDelegate;
                        if (pointerInputChange4 != null) {
                            pointerInputChange = pointerInputChange4;
                        }
                        pointerInputChange2.consumedDelegate = pointerInputChange;
                        longSparseArray2.put(j6, pointerInputChange2);
                        i5 = i2 + 1;
                        size = i;
                        buildCache = z5;
                    }
                }
            }
            z5 = buildCache;
            i = size;
            i2 = i5;
            i5 = i2 + 1;
            size = i;
            buildCache = z5;
        }
        boolean z6 = buildCache;
        if (longSparseArray2.size() == 0) {
            pointerIdArray.size = 0;
            this.children.clear();
            return true;
        }
        for (int i8 = pointerIdArray.size - 1; -1 < i8; i8--) {
            if (longSparseArray.indexOfKey(pointerIdArray.internalArray[i8]) < 0) {
                pointerIdArray.removeAt(i8);
            }
        }
        ArrayList arrayList2 = new ArrayList(longSparseArray2.size());
        int size3 = longSparseArray2.size();
        for (int i9 = 0; i9 < size3; i9++) {
            arrayList2.add(longSparseArray2.valueAt(i9));
        }
        PointerEvent pointerEvent2 = new PointerEvent(arrayList2, internalPointerEvent);
        int size4 = arrayList2.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size4) {
                obj = null;
                break;
            }
            obj = arrayList2.get(i10);
            if (internalPointerEvent.m458activeHoverEvent0FcD4WY(((PointerInputChange) obj).id)) {
                break;
            }
            i10++;
        }
        PointerInputChange pointerInputChange5 = (PointerInputChange) obj;
        if (pointerInputChange5 != null) {
            boolean z7 = pointerInputChange5.pressed;
            if (z) {
                z2 = false;
                if (!this.isIn && (z7 || pointerInputChange5.previousPressed)) {
                    NodeCoordinator nodeCoordinator4 = this.coordinates;
                    Intrinsics.checkNotNull(nodeCoordinator4);
                    boolean m459isOutOfBoundsO0kMr_c = PointerEventKt.m459isOutOfBoundsO0kMr_c(pointerInputChange5, nodeCoordinator4.measuredSize);
                    z3 = true;
                    this.isIn = !m459isOutOfBoundsO0kMr_c;
                    if (this.isIn == this.wasIn && (PointerEventType.m461equalsimpl0(pointerEvent2.type, 3) || PointerEventType.m461equalsimpl0(pointerEvent2.type, 4) || PointerEventType.m461equalsimpl0(pointerEvent2.type, 5))) {
                        pointerEvent2.type = this.isIn ? 4 : 5;
                    } else if (!PointerEventType.m461equalsimpl0(pointerEvent2.type, 4) && this.wasIn && !this.hasExited) {
                        pointerEvent2.type = 3;
                    } else if (PointerEventType.m461equalsimpl0(pointerEvent2.type, 5) && this.isIn && z7) {
                        pointerEvent2.type = 3;
                    }
                }
            } else {
                z2 = false;
                this.isIn = false;
            }
            z3 = true;
            if (this.isIn == this.wasIn) {
            }
            if (!PointerEventType.m461equalsimpl0(pointerEvent2.type, 4)) {
            }
            if (PointerEventType.m461equalsimpl0(pointerEvent2.type, 5)) {
                pointerEvent2.type = 3;
            }
        } else {
            z2 = false;
            z3 = true;
        }
        if (!z6 && PointerEventType.m461equalsimpl0(pointerEvent2.type, 3) && (pointerEvent = this.pointerEvent) != null && pointerEvent.changes.size() == pointerEvent2.changes.size()) {
            int size5 = pointerEvent2.changes.size();
            for (?? r5 = z2; r5 < size5; r5++) {
                if (Offset.m310equalsimpl0(((PointerInputChange) pointerEvent.changes.get(r5)).position, ((PointerInputChange) pointerEvent2.changes.get(r5)).position)) {
                }
            }
            z4 = z2;
            this.pointerEvent = pointerEvent2;
            return z4;
        }
        z4 = z3;
        this.pointerEvent = pointerEvent2;
        return z4;
    }

    @Override // androidx.compose.ui.input.pointer.NodeParent
    public final void cleanUpHits(InternalPointerEvent internalPointerEvent) {
        super.cleanUpHits(internalPointerEvent);
        PointerEvent pointerEvent = this.pointerEvent;
        if (pointerEvent == null) {
            return;
        }
        this.wasIn = this.isIn;
        List list = pointerEvent.changes;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            PointerInputChange pointerInputChange = (PointerInputChange) list.get(i);
            boolean z = pointerInputChange.pressed;
            long j = pointerInputChange.id;
            boolean m458activeHoverEvent0FcD4WY = internalPointerEvent.m458activeHoverEvent0FcD4WY(j);
            boolean z2 = this.isIn;
            if ((!z && !m458activeHoverEvent0FcD4WY) || (!z && !z2)) {
                PointerIdArray pointerIdArray = this.pointerIds;
                int i2 = pointerIdArray.size;
                int i3 = 0;
                while (true) {
                    if (i3 >= i2) {
                        break;
                    }
                    if (j == pointerIdArray.internalArray[i3]) {
                        pointerIdArray.removeAt(i3);
                        break;
                    }
                    i3++;
                }
            }
        }
        this.isIn = false;
        this.hasExited = PointerEventType.m461equalsimpl0(pointerEvent.type, 5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final void dispatchCancel() {
        MutableVector mutableVector = this.children;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((Node) objArr[i2]).dispatchCancel();
                i2++;
            } while (i2 < i);
        }
        DelegatingNode delegatingNode = this.modifierNode;
        ?? r1 = 0;
        while (delegatingNode != 0) {
            if (delegatingNode instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) delegatingNode).onCancelPointerInput();
            } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode.delegate;
                int i3 = 0;
                r1 = r1;
                delegatingNode = delegatingNode;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
                        i3++;
                        r1 = r1;
                        if (i3 == 1) {
                            delegatingNode = node;
                        } else {
                            if (r1 == 0) {
                                r1 = new MutableVector(new Modifier.Node[16]);
                            }
                            if (delegatingNode != 0) {
                                r1.add(delegatingNode);
                                delegatingNode = 0;
                            }
                            r1.add(node);
                        }
                    }
                    node = node.child;
                    r1 = r1;
                    delegatingNode = delegatingNode;
                }
                if (i3 == 1) {
                }
            }
            delegatingNode = DelegatableNodeKt.access$pop(r1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public final boolean dispatchFinalEventPass(InternalPointerEvent internalPointerEvent) {
        MutableVector mutableVector;
        int i;
        LongSparseArray longSparseArray = this.relevantChanges;
        boolean z = false;
        int i2 = 0;
        z = false;
        if (!(longSparseArray.size() == 0)) {
            Modifier.Node node = this.modifierNode;
            if (node.isAttached) {
                PointerEvent pointerEvent = this.pointerEvent;
                Intrinsics.checkNotNull(pointerEvent);
                NodeCoordinator nodeCoordinator = this.coordinates;
                Intrinsics.checkNotNull(nodeCoordinator);
                long j = nodeCoordinator.measuredSize;
                DelegatingNode delegatingNode = node;
                ?? r9 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof PointerInputModifierNode) {
                        ((PointerInputModifierNode) delegatingNode).mo15onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Final, j);
                    } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node2 = delegatingNode.delegate;
                        int i3 = 0;
                        delegatingNode = delegatingNode;
                        r9 = r9;
                        while (node2 != null) {
                            if ((node2.kindSet & 16) != 0) {
                                i3++;
                                r9 = r9;
                                if (i3 == 1) {
                                    delegatingNode = node2;
                                } else {
                                    if (r9 == 0) {
                                        r9 = new MutableVector(new Modifier.Node[16]);
                                    }
                                    if (delegatingNode != 0) {
                                        r9.add(delegatingNode);
                                        delegatingNode = 0;
                                    }
                                    r9.add(node2);
                                }
                            }
                            node2 = node2.child;
                            delegatingNode = delegatingNode;
                            r9 = r9;
                        }
                        if (i3 == 1) {
                        }
                    }
                    delegatingNode = DelegatableNodeKt.access$pop(r9);
                }
                if (node.isAttached && (i = (mutableVector = this.children).size) > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        ((Node) objArr[i2]).dispatchFinalEventPass(internalPointerEvent);
                        i2++;
                    } while (i2 < i);
                }
                z = true;
            }
        }
        cleanUpHits(internalPointerEvent);
        longSparseArray.clear();
        this.coordinates = null;
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v10 */
    /* JADX WARN: Type inference failed for: r13v11 */
    /* JADX WARN: Type inference failed for: r13v12 */
    /* JADX WARN: Type inference failed for: r13v13 */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r13v6 */
    /* JADX WARN: Type inference failed for: r13v7 */
    /* JADX WARN: Type inference failed for: r13v8, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v10, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final boolean dispatchMainEventPass(InternalPointerEvent internalPointerEvent, boolean z) {
        MutableVector mutableVector;
        int i;
        if (this.relevantChanges.size() == 0) {
            return false;
        }
        DelegatingNode delegatingNode = this.modifierNode;
        if (!delegatingNode.isAttached) {
            return false;
        }
        PointerEvent pointerEvent = this.pointerEvent;
        Intrinsics.checkNotNull(pointerEvent);
        NodeCoordinator nodeCoordinator = this.coordinates;
        Intrinsics.checkNotNull(nodeCoordinator);
        long j = nodeCoordinator.measuredSize;
        DelegatingNode delegatingNode2 = delegatingNode;
        ?? r8 = 0;
        while (delegatingNode2 != 0) {
            if (delegatingNode2 instanceof PointerInputModifierNode) {
                ((PointerInputModifierNode) delegatingNode2).mo15onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Initial, j);
            } else if ((delegatingNode2.kindSet & 16) != 0 && (delegatingNode2 instanceof DelegatingNode)) {
                Modifier.Node node = delegatingNode2.delegate;
                int i2 = 0;
                delegatingNode2 = delegatingNode2;
                r8 = r8;
                while (node != null) {
                    if ((node.kindSet & 16) != 0) {
                        i2++;
                        r8 = r8;
                        if (i2 == 1) {
                            delegatingNode2 = node;
                        } else {
                            if (r8 == 0) {
                                r8 = new MutableVector(new Modifier.Node[16]);
                            }
                            if (delegatingNode2 != 0) {
                                r8.add(delegatingNode2);
                                delegatingNode2 = 0;
                            }
                            r8.add(node);
                        }
                    }
                    node = node.child;
                    delegatingNode2 = delegatingNode2;
                    r8 = r8;
                }
                if (i2 == 1) {
                }
            }
            delegatingNode2 = DelegatableNodeKt.access$pop(r8);
        }
        if (delegatingNode.isAttached && (i = (mutableVector = this.children).size) > 0) {
            Object[] objArr = mutableVector.content;
            int i3 = 0;
            do {
                Node node2 = (Node) objArr[i3];
                Intrinsics.checkNotNull(this.coordinates);
                node2.dispatchMainEventPass(internalPointerEvent, z);
                i3++;
            } while (i3 < i);
        }
        if (delegatingNode.isAttached) {
            ?? r13 = 0;
            while (delegatingNode != 0) {
                if (delegatingNode instanceof PointerInputModifierNode) {
                    ((PointerInputModifierNode) delegatingNode).mo15onPointerEventH0pRuoY(pointerEvent, PointerEventPass.Main, j);
                } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                    Modifier.Node node3 = delegatingNode.delegate;
                    int i4 = 0;
                    delegatingNode = delegatingNode;
                    r13 = r13;
                    while (node3 != null) {
                        if ((node3.kindSet & 16) != 0) {
                            i4++;
                            r13 = r13;
                            if (i4 == 1) {
                                delegatingNode = node3;
                            } else {
                                if (r13 == 0) {
                                    r13 = new MutableVector(new Modifier.Node[16]);
                                }
                                if (delegatingNode != 0) {
                                    r13.add(delegatingNode);
                                    delegatingNode = 0;
                                }
                                r13.add(node3);
                            }
                        }
                        node3 = node3.child;
                        delegatingNode = delegatingNode;
                        r13 = r13;
                    }
                    if (i4 == 1) {
                    }
                }
                delegatingNode = DelegatableNodeKt.access$pop(r13);
            }
        }
        return true;
    }

    public final void removeInvalidPointerIdsAndChanges(long j, MutableObjectList mutableObjectList) {
        PointerIdArray pointerIdArray = this.pointerIds;
        int i = 0;
        if (pointerIdArray.contains(j) && mutableObjectList.indexOf(this) < 0) {
            int i2 = pointerIdArray.size;
            int i3 = 0;
            while (true) {
                if (i3 >= i2) {
                    break;
                }
                if (j == pointerIdArray.internalArray[i3]) {
                    pointerIdArray.removeAt(i3);
                    break;
                }
                i3++;
            }
            this.relevantChanges.remove(j);
        }
        MutableVector mutableVector = this.children;
        int i4 = mutableVector.size;
        if (i4 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                ((Node) objArr[i]).removeInvalidPointerIdsAndChanges(j, mutableObjectList);
                i++;
            } while (i < i4);
        }
    }

    public final String toString() {
        return "Node(modifierNode=" + this.modifierNode + ", children=" + this.children + ", pointerIds=" + this.pointerIds + ')';
    }
}
