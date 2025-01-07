package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.AndroidPaint;
import androidx.compose.ui.graphics.AndroidPaint_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScopeImpl;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutModifierNodeCoordinator extends NodeCoordinator {
    public static final AndroidPaint modifierBoundsPaint;
    public ApproachMeasureScopeImpl approachMeasureScope;
    public LayoutModifierNode layoutModifierNode;
    public Constraints lookaheadConstraints;
    public LookaheadDelegate lookaheadDelegate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class LookaheadDelegateForLayoutModifierNode extends LookaheadDelegate {
        public LookaheadDelegateForLayoutModifierNode() {
            super(LayoutModifierNodeCoordinator.this);
        }

        @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
        public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
            int access$calculateAlignmentAndPlaceChildAsNeeded = LayoutModifierNodeCoordinatorKt.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
            this.cachedAlignmentLinesMap.put(alignmentLine, Integer.valueOf(access$calculateAlignmentAndPlaceChildAsNeeded));
            return access$calculateAlignmentAndPlaceChildAsNeeded;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicHeight(int i) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
            NodeCoordinator nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicHeight(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicWidth(int i) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
            NodeCoordinator nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.maxIntrinsicWidth(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo479measureBRTryo0(long j) {
            m494setMeasurementConstraintsBRTryo0(j);
            Constraints constraints = new Constraints(j);
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            layoutModifierNodeCoordinator.lookaheadConstraints = constraints;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
            NodeCoordinator nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            LookaheadDelegate.access$set_measureResult(this, layoutModifierNode.mo6measure3p2s80s(this, lookaheadDelegate, j));
            return this;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicHeight(int i) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
            NodeCoordinator nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicHeight(this, lookaheadDelegate, i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicWidth(int i) {
            LayoutModifierNodeCoordinator layoutModifierNodeCoordinator = LayoutModifierNodeCoordinator.this;
            LayoutModifierNode layoutModifierNode = layoutModifierNodeCoordinator.layoutModifierNode;
            NodeCoordinator nodeCoordinator = layoutModifierNodeCoordinator.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            LookaheadDelegate lookaheadDelegate = nodeCoordinator.getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return layoutModifierNode.minIntrinsicWidth(this, lookaheadDelegate, i);
        }
    }

    static {
        AndroidPaint Paint = AndroidPaint_androidKt.Paint();
        Paint.m348setColor8_81llA(Color.Blue);
        Paint.setStrokeWidth(1.0f);
        Paint.m352setStylek9PVt8s(1);
        modifierBoundsPaint = Paint;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutModifierNodeCoordinator(LayoutNode layoutNode, LayoutModifierNode layoutModifierNode) {
        super(layoutNode);
        this.layoutModifierNode = layoutModifierNode;
        this.lookaheadDelegate = layoutNode.lookaheadRoot != null ? new LookaheadDelegateForLayoutModifierNode() : null;
        this.approachMeasureScope = (((Modifier.Node) layoutModifierNode).node.kindSet & 512) != 0 ? new ApproachMeasureScopeImpl(this, (ApproachLayoutModifierNode) layoutModifierNode) : null;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final int calculateAlignmentLine(AlignmentLine alignmentLine) {
        LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
        if (lookaheadDelegate == null) {
            return LayoutModifierNodeCoordinatorKt.access$calculateAlignmentAndPlaceChildAsNeeded(this, alignmentLine);
        }
        Integer num = (Integer) lookaheadDelegate.cachedAlignmentLinesMap.get(alignmentLine);
        if (num != null) {
            return num.intValue();
        }
        return Integer.MIN_VALUE;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void ensureLookaheadDelegateCreated() {
        if (this.lookaheadDelegate == null) {
            this.lookaheadDelegate = new LookaheadDelegateForLayoutModifierNode();
        }
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final LookaheadDelegate getLookaheadDelegate() {
        return this.lookaheadDelegate;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final Modifier.Node getTail() {
        return ((Modifier.Node) this.layoutModifierNode).node;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicHeight(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            ApproachLayoutModifierNode approachLayoutModifierNode = approachMeasureScopeImpl.approachNode;
            NodeCoordinator nodeCoordinator = this.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            return approachLayoutModifierNode.maxApproachIntrinsicHeight(approachMeasureScopeImpl, nodeCoordinator, i);
        }
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        NodeCoordinator nodeCoordinator2 = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator2);
        return layoutModifierNode.maxIntrinsicHeight(this, nodeCoordinator2, i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicWidth(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            ApproachLayoutModifierNode approachLayoutModifierNode = approachMeasureScopeImpl.approachNode;
            NodeCoordinator nodeCoordinator = this.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            return approachLayoutModifierNode.maxApproachIntrinsicWidth(approachMeasureScopeImpl, nodeCoordinator, i);
        }
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        NodeCoordinator nodeCoordinator2 = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator2);
        return layoutModifierNode.maxIntrinsicWidth(this, nodeCoordinator2, i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:24:0x006a, code lost:
    
        if (r9 == r1.height) goto L30;
     */
    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.layout.Placeable mo479measureBRTryo0(long r8) {
        /*
            r7 = this;
            boolean r0 = r7.forceMeasureWithLookaheadConstraints
            if (r0 == 0) goto L13
            androidx.compose.ui.unit.Constraints r8 = r7.lookaheadConstraints
            if (r8 == 0) goto Lb
            long r8 = r8.value
            goto L13
        Lb:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "Lookahead constraints cannot be null in approach pass."
            r7.<init>(r8)
            throw r7
        L13:
            r7.m494setMeasurementConstraintsBRTryo0(r8)
            androidx.compose.ui.layout.ApproachMeasureScopeImpl r0 = r7.approachMeasureScope
            if (r0 == 0) goto L9e
            androidx.compose.ui.layout.ApproachLayoutModifierNode r1 = r0.approachNode
            long r2 = r0.mo471getLookaheadSizeYbymL2g()
            boolean r2 = r1.mo473isMeasurementApproachInProgressozmzZPI(r2)
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L36
            androidx.compose.ui.unit.Constraints r2 = r7.lookaheadConstraints
            if (r2 != 0) goto L2d
            goto L36
        L2d:
            long r5 = r2.value
            int r2 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r2 == 0) goto L34
            goto L36
        L34:
            r2 = r4
            goto L37
        L36:
            r2 = r3
        L37:
            r0.approachMeasureRequired = r2
            if (r2 != 0) goto L42
            androidx.compose.ui.node.NodeCoordinator r2 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r2.forceMeasureWithLookaheadConstraints = r3
        L42:
            androidx.compose.ui.node.NodeCoordinator r2 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            androidx.compose.ui.layout.MeasureResult r8 = r1.mo472approachMeasure3p2s80s(r0, r2, r8)
            androidx.compose.ui.node.NodeCoordinator r9 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r9.forceMeasureWithLookaheadConstraints = r4
            int r9 = r8.getWidth()
            androidx.compose.ui.node.LookaheadDelegate r1 = r7.lookaheadDelegate
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.width
            if (r9 != r1) goto L6d
            int r9 = r8.getHeight()
            androidx.compose.ui.node.LookaheadDelegate r1 = r7.lookaheadDelegate
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            int r1 = r1.height
            if (r9 != r1) goto L6d
            goto L6e
        L6d:
            r3 = r4
        L6e:
            boolean r9 = r0.approachMeasureRequired
            if (r9 != 0) goto La9
            androidx.compose.ui.node.NodeCoordinator r9 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            long r0 = r9.measuredSize
            androidx.compose.ui.node.NodeCoordinator r9 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            androidx.compose.ui.node.LookaheadDelegate r9 = r9.getLookaheadDelegate()
            if (r9 == 0) goto L8e
            long r4 = r9.m518getSizeYbymL2g$ui_release()
            androidx.compose.ui.unit.IntSize r9 = new androidx.compose.ui.unit.IntSize
            r9.<init>(r4)
            goto L8f
        L8e:
            r9 = 0
        L8f:
            boolean r9 = androidx.compose.ui.unit.IntSize.m682equalsimpl(r0, r9)
            if (r9 == 0) goto La9
            if (r3 != 0) goto La9
            androidx.compose.ui.node.LayoutModifierNodeCoordinator$measure$1$1$1$1 r9 = new androidx.compose.ui.node.LayoutModifierNodeCoordinator$measure$1$1$1$1
            r9.<init>(r7)
            r8 = r9
            goto La9
        L9e:
            androidx.compose.ui.node.LayoutModifierNode r0 = r7.layoutModifierNode
            androidx.compose.ui.node.NodeCoordinator r1 = r7.wrapped
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            androidx.compose.ui.layout.MeasureResult r8 = r0.mo6measure3p2s80s(r7, r1, r8)
        La9:
            r7.setMeasureResult$ui_release(r8)
            r7.onMeasured()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutModifierNodeCoordinator.mo479measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicHeight(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            ApproachLayoutModifierNode approachLayoutModifierNode = approachMeasureScopeImpl.approachNode;
            NodeCoordinator nodeCoordinator = this.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            return approachLayoutModifierNode.minApproachIntrinsicHeight(approachMeasureScopeImpl, nodeCoordinator, i);
        }
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        NodeCoordinator nodeCoordinator2 = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator2);
        return layoutModifierNode.minIntrinsicHeight(this, nodeCoordinator2, i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicWidth(int i) {
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            ApproachLayoutModifierNode approachLayoutModifierNode = approachMeasureScopeImpl.approachNode;
            NodeCoordinator nodeCoordinator = this.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator);
            return approachLayoutModifierNode.minApproachIntrinsicWidth(approachMeasureScopeImpl, nodeCoordinator, i);
        }
        LayoutModifierNode layoutModifierNode = this.layoutModifierNode;
        NodeCoordinator nodeCoordinator2 = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator2);
        return layoutModifierNode.minIntrinsicWidth(this, nodeCoordinator2, i);
    }

    public final void onAfterPlaceAt$1() {
        boolean z;
        if (this.isShallowPlacing) {
            return;
        }
        onPlaced();
        ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
        if (approachMeasureScopeImpl != null) {
            ApproachLayoutModifierNode approachLayoutModifierNode = approachMeasureScopeImpl.approachNode;
            Placeable.PlacementScope placementScope = this.placementScope;
            LookaheadDelegate lookaheadDelegate = this.lookaheadDelegate;
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (!approachLayoutModifierNode.isPlacementApproachInProgress(placementScope, lookaheadDelegate.lookaheadLayoutCoordinates) && !approachMeasureScopeImpl.approachMeasureRequired) {
                long j = this.measuredSize;
                LookaheadDelegate lookaheadDelegate2 = this.lookaheadDelegate;
                if (IntSize.m682equalsimpl(j, lookaheadDelegate2 != null ? new IntSize(lookaheadDelegate2.m518getSizeYbymL2g$ui_release()) : null)) {
                    NodeCoordinator nodeCoordinator = this.wrapped;
                    Intrinsics.checkNotNull(nodeCoordinator);
                    long j2 = nodeCoordinator.measuredSize;
                    NodeCoordinator nodeCoordinator2 = this.wrapped;
                    Intrinsics.checkNotNull(nodeCoordinator2);
                    LookaheadDelegate lookaheadDelegate3 = nodeCoordinator2.getLookaheadDelegate();
                    if (IntSize.m682equalsimpl(j2, lookaheadDelegate3 != null ? new IntSize(lookaheadDelegate3.m518getSizeYbymL2g$ui_release()) : null)) {
                        z = true;
                        NodeCoordinator nodeCoordinator3 = this.wrapped;
                        Intrinsics.checkNotNull(nodeCoordinator3);
                        nodeCoordinator3.forcePlaceWithLookaheadOffset = z;
                    }
                }
            }
            z = false;
            NodeCoordinator nodeCoordinator32 = this.wrapped;
            Intrinsics.checkNotNull(nodeCoordinator32);
            nodeCoordinator32.forcePlaceWithLookaheadOffset = z;
        }
        getMeasureResult$ui_release().placeChildren();
        NodeCoordinator nodeCoordinator4 = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator4);
        nodeCoordinator4.forcePlaceWithLookaheadOffset = false;
    }

    @Override // androidx.compose.ui.node.NodeCoordinator
    public final void performDraw(Canvas canvas, GraphicsLayer graphicsLayer) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        Intrinsics.checkNotNull(nodeCoordinator);
        nodeCoordinator.draw(canvas, graphicsLayer);
        if (((AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode)).showLayoutBounds) {
            long j = this.measuredSize;
            canvas.drawRect(0.5f, 0.5f, ((int) (j >> 32)) - 0.5f, ((int) (j & 4294967295L)) - 0.5f, modifierBoundsPaint);
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
        onAfterPlaceAt$1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void setLayoutModifierNode$ui_release(LayoutModifierNode layoutModifierNode) {
        if (!layoutModifierNode.equals(this.layoutModifierNode)) {
            if ((((Modifier.Node) layoutModifierNode).node.kindSet & 512) != 0) {
                ApproachLayoutModifierNode approachLayoutModifierNode = (ApproachLayoutModifierNode) layoutModifierNode;
                ApproachMeasureScopeImpl approachMeasureScopeImpl = this.approachMeasureScope;
                if (approachMeasureScopeImpl != null) {
                    approachMeasureScopeImpl.approachNode = approachLayoutModifierNode;
                } else {
                    approachMeasureScopeImpl = new ApproachMeasureScopeImpl(this, approachLayoutModifierNode);
                }
                this.approachMeasureScope = approachMeasureScopeImpl;
            } else {
                this.approachMeasureScope = null;
            }
        }
        this.layoutModifierNode = layoutModifierNode;
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
        onAfterPlaceAt$1();
    }
}
