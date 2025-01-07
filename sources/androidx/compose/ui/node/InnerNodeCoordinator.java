package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InnerNodeCoordinator extends NodeCoordinator {
    public static final AndroidPaint innerBoundsPaint;
    public LookaheadDelegate lookaheadDelegate;
    public final TailModifierNode tail;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class LookaheadDelegateImpl extends LookaheadDelegate {
        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.coordinator.layoutNode.layoutDelegate.lookaheadPassDelegate;
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            boolean z = lookaheadPassDelegate.duringAlignmentLinesQuery;
            LookaheadAlignmentLines lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines;
            if (!z) {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                if (layoutNodeLayoutDelegate.layoutState == LayoutNode.LayoutState.LookaheadMeasuring) {
                    lookaheadAlignmentLines.usedByModifierMeasurement = true;
                    if (lookaheadAlignmentLines.dirty) {
                        layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
                        layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = true;
                    }
                } else {
                    lookaheadAlignmentLines.usedByModifierLayout = true;
                }
            }
            LookaheadDelegate lookaheadDelegate = lookaheadPassDelegate.getInnerCoordinator().lookaheadDelegate;
            if (lookaheadDelegate != null) {
                lookaheadDelegate.isPlacingForAlignment = true;
            }
            lookaheadPassDelegate.layoutChildren();
            LookaheadDelegate lookaheadDelegate2 = lookaheadPassDelegate.getInnerCoordinator().lookaheadDelegate;
            if (lookaheadDelegate2 != null) {
                lookaheadDelegate2.isPlacingForAlignment = false;
            }
            Integer num = (Integer) lookaheadAlignmentLines.alignmentLineMap.get(alignmentLine);
            int intValue = num != null ? num.intValue() : Integer.MIN_VALUE;
            this.cachedAlignmentLinesMap.put(alignmentLine, Integer.valueOf(intValue));
            return intValue;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicHeight(int i) {
            IntrinsicsPolicy orCreateIntrinsicsPolicy = this.coordinator.layoutNode.getOrCreateIntrinsicsPolicy();
            MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
            LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
            return measurePolicyState.maxIntrinsicHeight(layoutNode.nodes.outerCoordinator, layoutNode.getChildLookaheadMeasurables$ui_release(), i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicWidth(int i) {
            IntrinsicsPolicy orCreateIntrinsicsPolicy = this.coordinator.layoutNode.getOrCreateIntrinsicsPolicy();
            MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
            LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
            return measurePolicyState.maxIntrinsicWidth(layoutNode.nodes.outerCoordinator, layoutNode.getChildLookaheadMeasurables$ui_release(), i);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo479measureBRTryo0(long j) {
            m494setMeasurementConstraintsBRTryo0(j);
            NodeCoordinator nodeCoordinator = this.coordinator;
            MutableVector mutableVector = nodeCoordinator.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i2]).layoutDelegate.lookaheadPassDelegate;
                    Intrinsics.checkNotNull(lookaheadPassDelegate);
                    lookaheadPassDelegate.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                    i2++;
                } while (i2 < i);
            }
            LayoutNode layoutNode = nodeCoordinator.layoutNode;
            LookaheadDelegate.access$set_measureResult(this, layoutNode.measurePolicy.mo2measure3p2s80s(this, layoutNode.getChildLookaheadMeasurables$ui_release(), j));
            return this;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicHeight(int i) {
            IntrinsicsPolicy orCreateIntrinsicsPolicy = this.coordinator.layoutNode.getOrCreateIntrinsicsPolicy();
            MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
            LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
            return measurePolicyState.minIntrinsicHeight(layoutNode.nodes.outerCoordinator, layoutNode.getChildLookaheadMeasurables$ui_release(), i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicWidth(int i) {
            IntrinsicsPolicy orCreateIntrinsicsPolicy = this.coordinator.layoutNode.getOrCreateIntrinsicsPolicy();
            MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
            LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
            return measurePolicyState.minIntrinsicWidth(layoutNode.nodes.outerCoordinator, layoutNode.getChildLookaheadMeasurables$ui_release(), i);
        }

        @Override // androidx.compose.ui.node.LookaheadDelegate
        public final void placeChildren() {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.coordinator.layoutNode.layoutDelegate.lookaheadPassDelegate;
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            lookaheadPassDelegate.onNodePlaced$ui_release();
        }
    }

    static {
        AndroidPaint Paint = AndroidPaint_androidKt.Paint();
        Paint.m348setColor8_81llA(Color.Red);
        Paint.setStrokeWidth(1.0f);
        Paint.m352setStylek9PVt8s(1);
        innerBoundsPaint = Paint;
    }

    public InnerNodeCoordinator(LayoutNode layoutNode) {
        super(layoutNode);
        TailModifierNode tailModifierNode = new TailModifierNode();
        tailModifierNode.aggregateChildKindSet = 0;
        this.tail = tailModifierNode;
        tailModifierNode.coordinator = this;
        this.lookaheadDelegate = layoutNode.lookaheadRoot != null ? new LookaheadDelegateImpl(this) : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (lookaheadDelegate != null) {
            return lookaheadDelegate.calculateAlignmentLine(alignmentLine);
        }
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.layoutNode.layoutDelegate.measurePassDelegate;
        boolean z = measurePassDelegate.duringAlignmentLinesQuery;
        LayoutNodeAlignmentLines layoutNodeAlignmentLines = measurePassDelegate.alignmentLines;
        if (!z) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.layoutState == LayoutNode.LayoutState.Measuring) {
                layoutNodeAlignmentLines.usedByModifierMeasurement = true;
                if (layoutNodeAlignmentLines.dirty) {
                    layoutNodeLayoutDelegate.layoutPending = true;
                    layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
                }
            } else {
                layoutNodeAlignmentLines.usedByModifierLayout = true;
            }
        }
        measurePassDelegate.getInnerCoordinator().isPlacingForAlignment = true;
        measurePassDelegate.layoutChildren();
        measurePassDelegate.getInnerCoordinator().isPlacingForAlignment = false;
        Integer num = (Integer) layoutNodeAlignmentLines.alignmentLineMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void ensureLookaheadDelegateCreated() {
        if (this.lookaheadDelegate == null) {
            this.lookaheadDelegate = new LookaheadDelegateImpl(this);
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return this.tail;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v10 */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r4v9 */
    @Override // androidx.compose.ui.node.NodeCoordinator
    /* renamed from: hitTestChild-YqVAtuI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void mo506hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator.HitTestSource r18, long r19, androidx.compose.ui.node.HitTestResult r21, boolean r22, boolean r23) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.InnerNodeCoordinator.mo506hitTestChildYqVAtuI(androidx.compose.ui.node.NodeCoordinator$HitTestSource, long, androidx.compose.ui.node.HitTestResult, boolean, boolean):void");
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicHeight(int i) {
        IntrinsicsPolicy orCreateIntrinsicsPolicy = this.layoutNode.getOrCreateIntrinsicsPolicy();
        MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
        LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
        return measurePolicyState.maxIntrinsicHeight(layoutNode.nodes.outerCoordinator, layoutNode.getChildMeasurables$ui_release(), i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicWidth(int i) {
        IntrinsicsPolicy orCreateIntrinsicsPolicy = this.layoutNode.getOrCreateIntrinsicsPolicy();
        MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
        LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
        return measurePolicyState.maxIntrinsicWidth(layoutNode.nodes.outerCoordinator, layoutNode.getChildMeasurables$ui_release(), i);
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo479measureBRTryo0(long j) {
        if (this.forceMeasureWithLookaheadConstraints) {
            LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
            Intrinsics.checkNotNull(lookaheadDelegate);
            j = lookaheadDelegate.measurementConstraints;
        }
        m494setMeasurementConstraintsBRTryo0(j);
        LayoutNode layoutNode = this.layoutNode;
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((LayoutNode) objArr[i2]).layoutDelegate.measurePassDelegate.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                i2++;
            } while (i2 < i);
        }
        setMeasureResult$ui_release(layoutNode.measurePolicy.mo2measure3p2s80s(this, layoutNode.getChildMeasurables$ui_release(), j));
        onMeasured();
        return this;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicHeight(int i) {
        IntrinsicsPolicy orCreateIntrinsicsPolicy = this.layoutNode.getOrCreateIntrinsicsPolicy();
        MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
        LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
        return measurePolicyState.minIntrinsicHeight(layoutNode.nodes.outerCoordinator, layoutNode.getChildMeasurables$ui_release(), i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicWidth(int i) {
        IntrinsicsPolicy orCreateIntrinsicsPolicy = this.layoutNode.getOrCreateIntrinsicsPolicy();
        MeasurePolicy measurePolicyState = orCreateIntrinsicsPolicy.getMeasurePolicyState();
        LayoutNode layoutNode = orCreateIntrinsicsPolicy.layoutNode;
        return measurePolicyState.minIntrinsicWidth(layoutNode.nodes.outerCoordinator, layoutNode.getChildMeasurables$ui_release(), i);
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        LayoutNode layoutNode = this.layoutNode;
        Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
        MutableVector zSortedChildren = layoutNode.getZSortedChildren();
        int i = zSortedChildren.size;
        if (i > 0) {
            Object[] objArr = zSortedChildren.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (layoutNode2.isPlaced()) {
                    layoutNode2.draw$ui_release(canvas, graphicsLayer);
                }
                i2++;
            } while (i2 < i);
        }
        if (((AndroidComposeView) requireOwner).showLayoutBounds) {
            long j = this.measuredSize;
            canvas.drawRect(0.5f, 0.5f, ((int) (j >> 32)) - 0.5f, ((int) (j & 4294967295L)) - 0.5f, innerBoundsPaint);
        }
    }

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo480placeAtf8xVGno(long j, float f, Function1 function1) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m535placeSelfMLgxB_4(lookaheadDelegate.position, f, function1, null);
        } else {
            m535placeSelfMLgxB_4(j, f, function1, null);
        }
        if (this.isShallowPlacing) {
            return;
        }
        this.layoutNode.layoutDelegate.measurePassDelegate.onNodePlaced$ui_release();
    }

    @Override // androidx.compose.ui.node.NodeCoordinator, androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public final void mo492placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
        if (this.forcePlaceWithLookaheadOffset) {
            LookaheadDelegate lookaheadDelegate = getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            m535placeSelfMLgxB_4(lookaheadDelegate.position, f, null, graphicsLayer);
        } else {
            m535placeSelfMLgxB_4(j, f, null, graphicsLayer);
        }
        if (this.isShallowPlacing) {
            return;
        }
        this.layoutNode.layoutDelegate.measurePassDelegate.onNodePlaced$ui_release();
    }
}
