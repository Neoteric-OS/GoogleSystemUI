package androidx.compose.ui.input.pointer;

import androidx.collection.MutableLongObjectMap;
import androidx.collection.MutableObjectList;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutCoordinates;
import java.util.Arrays;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HitPathTracker {
    public final LayoutCoordinates rootCoordinates;
    public final NodeParent root = new NodeParent();
    public final MutableLongObjectMap hitPointerIdsAndNodes = new MutableLongObjectMap(10);

    public HitPathTracker(LayoutCoordinates layoutCoordinates) {
        this.rootCoordinates = layoutCoordinates;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v4, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r16v3 */
    /* JADX WARN: Type inference failed for: r16v4 */
    /* JADX WARN: Type inference failed for: r16v5 */
    /* renamed from: addHitPath-QJqDSyo, reason: not valid java name */
    public final void m457addHitPathQJqDSyo(long j, List list, boolean z) {
        long[] jArr;
        long[] jArr2;
        int i;
        Node node;
        Node node2;
        NodeParent nodeParent = this.root;
        MutableLongObjectMap mutableLongObjectMap = this.hitPointerIdsAndNodes;
        mutableLongObjectMap.clear();
        int size = list.size();
        NodeParent nodeParent2 = nodeParent;
        boolean z2 = true;
        for (int i2 = 0; i2 < size; i2++) {
            final Modifier.Node node3 = (Modifier.Node) list.get(i2);
            if (node3.isAttached) {
                node3.detachedListener = new Function0() { // from class: androidx.compose.ui.input.pointer.HitPathTracker$addHitPath$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        HitPathTracker hitPathTracker = HitPathTracker.this;
                        Modifier.Node node4 = node3;
                        NodeParent nodeParent3 = hitPathTracker.root;
                        MutableObjectList mutableObjectList = nodeParent3.removeMatchingPointerInputModifierNodeList;
                        Arrays.fill(mutableObjectList.content, 0, mutableObjectList._size, (Object) null);
                        mutableObjectList._size = 0;
                        mutableObjectList.add(nodeParent3);
                        while (true) {
                            int i3 = mutableObjectList._size;
                            if (i3 == 0) {
                                return Unit.INSTANCE;
                            }
                            NodeParent nodeParent4 = (NodeParent) mutableObjectList.removeAt(i3 - 1);
                            int i4 = 0;
                            while (true) {
                                MutableVector mutableVector = nodeParent4.children;
                                if (i4 < mutableVector.size) {
                                    Node node5 = (Node) mutableVector.content[i4];
                                    if (Intrinsics.areEqual(node5.modifierNode, node4)) {
                                        nodeParent4.children.remove(node5);
                                        node5.dispatchCancel();
                                    } else {
                                        mutableObjectList.add(node5);
                                        i4++;
                                    }
                                }
                            }
                        }
                    }
                };
                if (z2) {
                    MutableVector mutableVector = nodeParent2.children;
                    int i3 = mutableVector.size;
                    if (i3 > 0) {
                        ?? r13 = mutableVector.content;
                        int i4 = 0;
                        do {
                            node2 = r13[i4];
                            if (Intrinsics.areEqual(((Node) node2).modifierNode, node3)) {
                                break;
                            } else {
                                i4++;
                            }
                        } while (i4 < i3);
                    }
                    node2 = 0;
                    node = node2;
                    if (node != null) {
                        node.isIn = true;
                        node.pointerIds.m469add0FcD4WY(j);
                        Object obj = mutableLongObjectMap.get(j);
                        if (obj == null) {
                            obj = new MutableObjectList();
                            mutableLongObjectMap.set(j, obj);
                        }
                        ((MutableObjectList) obj).add(node);
                        nodeParent2 = node;
                    } else {
                        z2 = false;
                    }
                }
                node = new Node(node3);
                node.pointerIds.m469add0FcD4WY(j);
                Object obj2 = mutableLongObjectMap.get(j);
                if (obj2 == null) {
                    obj2 = new MutableObjectList();
                    mutableLongObjectMap.set(j, obj2);
                }
                ((MutableObjectList) obj2).add(node);
                nodeParent2.children.add(node);
                nodeParent2 = node;
            }
        }
        if (!z) {
            return;
        }
        long[] jArr3 = mutableLongObjectMap.keys;
        Object[] objArr = mutableLongObjectMap.values;
        long[] jArr4 = mutableLongObjectMap.metadata;
        int length = jArr4.length - 2;
        if (length < 0) {
            return;
        }
        int i5 = 0;
        while (true) {
            long j2 = jArr4[i5];
            if ((((~j2) << 7) & j2 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i6 = 8;
                int i7 = 8 - ((~(i5 - length)) >>> 31);
                int i8 = 0;
                while (i8 < i7) {
                    if ((255 & j2) < 128) {
                        int i9 = (i5 << 3) + i8;
                        long j3 = jArr3[i9];
                        MutableObjectList mutableObjectList = (MutableObjectList) objArr[i9];
                        MutableVector mutableVector2 = nodeParent.children;
                        int i10 = mutableVector2.size;
                        if (i10 > 0) {
                            Object[] objArr2 = mutableVector2.content;
                            int i11 = 0;
                            while (true) {
                                jArr2 = jArr3;
                                ((Node) objArr2[i11]).removeInvalidPointerIdsAndChanges(j3, mutableObjectList);
                                int i12 = i11 + 1;
                                if (i12 >= i10) {
                                    break;
                                }
                                i11 = i12;
                                jArr3 = jArr2;
                            }
                        } else {
                            jArr2 = jArr3;
                        }
                        i = 8;
                    } else {
                        jArr2 = jArr3;
                        i = i6;
                    }
                    j2 >>= i;
                    i8++;
                    i6 = i;
                    jArr3 = jArr2;
                }
                jArr = jArr3;
                if (i7 != i6) {
                    return;
                }
            } else {
                jArr = jArr3;
            }
            if (i5 == length) {
                return;
            }
            i5++;
            jArr3 = jArr;
        }
    }

    public final boolean dispatchChanges(InternalPointerEvent internalPointerEvent, boolean z) {
        boolean z2;
        boolean z3;
        NodeParent nodeParent = this.root;
        if (!nodeParent.buildCache(internalPointerEvent.changes, this.rootCoordinates, internalPointerEvent, z)) {
            return false;
        }
        MutableVector mutableVector = nodeParent.children;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            z2 = false;
            do {
                z2 = ((Node) objArr[i2]).dispatchMainEventPass(internalPointerEvent, z) || z2;
                i2++;
            } while (i2 < i);
        } else {
            z2 = false;
        }
        int i3 = mutableVector.size;
        if (i3 > 0) {
            Object[] objArr2 = mutableVector.content;
            int i4 = 0;
            z3 = false;
            do {
                z3 = ((Node) objArr2[i4]).dispatchFinalEventPass(internalPointerEvent) || z3;
                i4++;
            } while (i4 < i3);
        } else {
            z3 = false;
        }
        nodeParent.cleanUpHits(internalPointerEvent);
        return z3 || z2;
    }
}
