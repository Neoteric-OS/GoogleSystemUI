package androidx.compose.ui.input.pointer;

import androidx.collection.LongSparseArray;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNode$Companion$ErrorMeasurePolicy$1;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerInputEventProcessor {
    public final HitPathTracker hitPathTracker;
    public boolean isProcessing;
    public final LayoutNode root;
    public final PointerInputChangeEventProducer pointerInputChangeEventProducer = new PointerInputChangeEventProducer();
    public final HitTestResult hitResult = new HitTestResult();

    public PointerInputEventProcessor(LayoutNode layoutNode) {
        this.root = layoutNode;
        this.hitPathTracker = new HitPathTracker(layoutNode.nodes.innerCoordinator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: process-BIzXfog, reason: not valid java name */
    public final int m465processBIzXfog(PointerInputEvent pointerInputEvent, AndroidComposeView androidComposeView, boolean z) {
        Object[] objArr;
        HitPathTracker hitPathTracker;
        int i;
        HitTestResult hitTestResult = this.hitResult;
        if (this.isProcessing) {
            return 0;
        }
        try {
            this.isProcessing = true;
            InternalPointerEvent produce = this.pointerInputChangeEventProducer.produce(pointerInputEvent, androidComposeView);
            LongSparseArray longSparseArray = produce.changes;
            int size = longSparseArray.size();
            for (int i2 = 0; i2 < size; i2++) {
                PointerInputChange pointerInputChange = (PointerInputChange) longSparseArray.valueAt(i2);
                if (!pointerInputChange.pressed && !pointerInputChange.previousPressed) {
                }
                objArr = false;
                break;
            }
            objArr = true;
            int size2 = longSparseArray.size();
            int i3 = 0;
            while (true) {
                hitPathTracker = this.hitPathTracker;
                if (i3 >= size2) {
                    break;
                }
                PointerInputChange pointerInputChange2 = (PointerInputChange) longSparseArray.valueAt(i3);
                if (objArr != false || PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange2)) {
                    boolean m468equalsimpl0 = PointerType.m468equalsimpl0(pointerInputChange2.type, 1);
                    LayoutNode layoutNode = this.root;
                    long j = pointerInputChange2.position;
                    HitTestResult hitTestResult2 = this.hitResult;
                    LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
                    layoutNode.m508hitTestM_7yMNQ$ui_release(j, hitTestResult2, m468equalsimpl0, true);
                    if (!hitTestResult.isEmpty()) {
                        hitPathTracker.m457addHitPathQJqDSyo(pointerInputChange2.id, hitTestResult, PointerEventKt.changedToDownIgnoreConsumed(pointerInputChange2));
                        hitTestResult.clear();
                    }
                }
                i3++;
            }
            boolean dispatchChanges = hitPathTracker.dispatchChanges(produce, z);
            if (!produce.suppressMovementConsumption) {
                int size3 = longSparseArray.size();
                for (int i4 = 0; i4 < size3; i4++) {
                    PointerInputChange pointerInputChange3 = (PointerInputChange) longSparseArray.valueAt(i4);
                    if (!Offset.m310equalsimpl0(PointerEventKt.positionChangeInternal(pointerInputChange3, true), 0L) && pointerInputChange3.isConsumed()) {
                        i = 2;
                        break;
                    }
                }
            }
            i = 0;
            int i5 = i | (dispatchChanges ? 1 : 0);
            this.isProcessing = false;
            return i5;
        } catch (Throwable th) {
            this.isProcessing = false;
            throw th;
        }
    }

    public final void processCancel() {
        if (this.isProcessing) {
            return;
        }
        this.pointerInputChangeEventProducer.previousPointerInputData.clear();
        HitPathTracker hitPathTracker = this.hitPathTracker;
        MutableVector mutableVector = hitPathTracker.root.children;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((Node) objArr[i2]).dispatchCancel();
                i2++;
            } while (i2 < i);
        }
        hitPathTracker.root.children.clear();
    }
}
