package androidx.compose.ui.node;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.MutableRect;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.GraphicsLayerScopeKt;
import androidx.compose.ui.graphics.Matrix;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.ReusableGraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOrigin;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NodeCoordinator extends LookaheadCapablePlaceable implements Measurable, LayoutCoordinates, OwnerScope {
    public static final NodeCoordinator$Companion$PointerInputSource$1 PointerInputSource;
    public static final NodeCoordinator$Companion$SemanticsSource$1 SemanticsSource;
    public static final ReusableGraphicsLayerScope graphicsLayerScope;
    public static final LayerPositionalProperties tmpLayerPositionalProperties;
    public static final float[] tmpMatrix;
    public Function2 _drawBlock;
    public MeasureResult _measureResult;
    public MutableRect _rectCache;
    public Canvas drawBlockCanvas;
    public GraphicsLayer drawBlockParentLayer;
    public GraphicsLayer explicitLayer;
    public boolean forceMeasureWithLookaheadConstraints;
    public boolean forcePlaceWithLookaheadOffset;
    public boolean isClipping;
    public boolean lastLayerDrawingWasSkipped;
    public OwnedLayer layer;
    public Function1 layerBlock;
    public Density layerDensity;
    public LayoutDirection layerLayoutDirection;
    public LayerPositionalProperties layerPositionalProperties;
    public final LayoutNode layoutNode;
    public MutableObjectIntMap oldAlignmentLines;
    public boolean released;
    public NodeCoordinator wrapped;
    public NodeCoordinator wrappedBy;
    public float zIndex;
    public static final Function1 onCommitAffectingLayerParams = new Function1() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayerParams$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            NodeCoordinator nodeCoordinator = (NodeCoordinator) obj;
            if (nodeCoordinator.isValidOwnerScope() && nodeCoordinator.updateLayerParameters(true)) {
                LayoutNode layoutNode = nodeCoordinator.layoutNode;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
                if (layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
                    if (layoutNodeLayoutDelegate.coordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                        layoutNode.requestRelayout$ui_release(false);
                    }
                    layoutNodeLayoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                }
                AndroidComposeView androidComposeView = (AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode);
                androidComposeView.rectManager.onLayoutLayerPositionalPropertiesChanged(layoutNode);
                androidComposeView.measureAndLayoutDelegate.onPositionedDispatcher.layoutNodes.add(layoutNode);
                layoutNode.needsOnPositionedDispatch = true;
                androidComposeView.scheduleMeasureAndLayout(null);
            }
            return Unit.INSTANCE;
        }
    };
    public static final Function1 onCommitAffectingLayer = new Function1() { // from class: androidx.compose.ui.node.NodeCoordinator$Companion$onCommitAffectingLayer$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            OwnedLayer ownedLayer = ((NodeCoordinator) obj).layer;
            if (ownedLayer != null) {
                ownedLayer.invalidate();
            }
            return Unit.INSTANCE;
        }
    };
    public float lastLayerAlpha = 0.8f;
    public long position = 0;
    public final Function0 invalidateParentLayer = new NodeCoordinator$invalidateParentLayer$1(this);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface HitTestSource {
        /* renamed from: childHitTest-YqVAtuI */
        void mo539childHitTestYqVAtuI(LayoutNode layoutNode, long j, HitTestResult hitTestResult, boolean z, boolean z2);

        /* renamed from: entityType-OLwlOKw */
        int mo540entityTypeOLwlOKw();

        boolean interceptOutOfBoundsChildEvents(Modifier.Node node);

        boolean shouldHitTestChildren(LayoutNode layoutNode);
    }

    static {
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = new ReusableGraphicsLayerScope();
        reusableGraphicsLayerScope.scaleX = 1.0f;
        reusableGraphicsLayerScope.scaleY = 1.0f;
        reusableGraphicsLayerScope.alpha = 1.0f;
        long j = GraphicsLayerScopeKt.DefaultShadowColor;
        reusableGraphicsLayerScope.ambientShadowColor = j;
        reusableGraphicsLayerScope.spotShadowColor = j;
        reusableGraphicsLayerScope.cameraDistance = 8.0f;
        reusableGraphicsLayerScope.transformOrigin = TransformOrigin.Center;
        reusableGraphicsLayerScope.shape = RectangleShapeKt.RectangleShape;
        reusableGraphicsLayerScope.compositingStrategy = 0;
        reusableGraphicsLayerScope.size = 9205357640488583168L;
        reusableGraphicsLayerScope.graphicsDensity = DensityKt.Density$default();
        reusableGraphicsLayerScope.layoutDirection = LayoutDirection.Ltr;
        graphicsLayerScope = reusableGraphicsLayerScope;
        tmpLayerPositionalProperties = new LayerPositionalProperties();
        tmpMatrix = Matrix.m379constructorimpl$default();
        PointerInputSource = new NodeCoordinator$Companion$PointerInputSource$1();
        SemanticsSource = new NodeCoordinator$Companion$SemanticsSource$1();
    }

    public NodeCoordinator(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
        this.layerDensity = layoutNode.density;
        this.layerLayoutDirection = layoutNode.layoutDirection;
    }

    public static NodeCoordinator toCoordinator(LayoutCoordinates layoutCoordinates) {
        NodeCoordinator nodeCoordinator;
        LookaheadLayoutCoordinates lookaheadLayoutCoordinates = layoutCoordinates instanceof LookaheadLayoutCoordinates ? (LookaheadLayoutCoordinates) layoutCoordinates : null;
        return (lookaheadLayoutCoordinates == null || (nodeCoordinator = lookaheadLayoutCoordinates.lookaheadDelegate.coordinator) == null) ? (NodeCoordinator) layoutCoordinates : nodeCoordinator;
    }

    public final void ancestorToLocal(NodeCoordinator nodeCoordinator, MutableRect mutableRect, boolean z) {
        if (nodeCoordinator == this) {
            return;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        if (nodeCoordinator2 != null) {
            nodeCoordinator2.ancestorToLocal(nodeCoordinator, mutableRect, z);
        }
        long j = this.position;
        float f = (int) (j >> 32);
        mutableRect.left -= f;
        mutableRect.right -= f;
        float f2 = (int) (j & 4294967295L);
        mutableRect.top -= f2;
        mutableRect.bottom -= f2;
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mapBounds(mutableRect, true);
            if (this.isClipping && z) {
                long j2 = this.measuredSize;
                mutableRect.intersect(0.0f, 0.0f, (int) (j2 >> 32), (int) (j2 & 4294967295L));
            }
        }
    }

    /* renamed from: ancestorToLocal-S_NoaFU, reason: not valid java name */
    public final long m526ancestorToLocalS_NoaFU(NodeCoordinator nodeCoordinator, long j) {
        if (nodeCoordinator == this) {
            return j;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        return (nodeCoordinator2 == null || Intrinsics.areEqual(nodeCoordinator, nodeCoordinator2)) ? m529fromParentPosition8S9VItk(j) : m529fromParentPosition8S9VItk(nodeCoordinator2.m526ancestorToLocalS_NoaFU(nodeCoordinator, j));
    }

    /* renamed from: calculateMinimumTouchTargetPadding-E7KxVPU, reason: not valid java name */
    public final long m527calculateMinimumTouchTargetPaddingE7KxVPU(long j) {
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - getMeasuredWidth();
        float intBitsToFloat2 = Float.intBitsToFloat((int) (j & 4294967295L)) - getMeasuredHeight();
        float max = Math.max(0.0f, intBitsToFloat / 2.0f);
        float max2 = Math.max(0.0f, intBitsToFloat2 / 2.0f);
        return (Float.floatToRawIntBits(max) << 32) | (Float.floatToRawIntBits(max2) & 4294967295L);
    }

    /* renamed from: distanceInMinimumTouchTarget-tz77jQw, reason: not valid java name */
    public final float m528distanceInMinimumTouchTargettz77jQw(long j, long j2) {
        if (getMeasuredWidth() >= Float.intBitsToFloat((int) (j2 >> 32)) && getMeasuredHeight() >= Float.intBitsToFloat((int) (j2 & 4294967295L))) {
            return Float.POSITIVE_INFINITY;
        }
        long m527calculateMinimumTouchTargetPaddingE7KxVPU = m527calculateMinimumTouchTargetPaddingE7KxVPU(j2);
        float intBitsToFloat = Float.intBitsToFloat((int) (m527calculateMinimumTouchTargetPaddingE7KxVPU >> 32));
        float intBitsToFloat2 = Float.intBitsToFloat((int) (m527calculateMinimumTouchTargetPaddingE7KxVPU & 4294967295L));
        float intBitsToFloat3 = Float.intBitsToFloat((int) (j >> 32));
        float max = Math.max(0.0f, intBitsToFloat3 < 0.0f ? -intBitsToFloat3 : intBitsToFloat3 - getMeasuredWidth());
        long floatToRawIntBits = (Float.floatToRawIntBits(max) << 32) | (Float.floatToRawIntBits(Math.max(0.0f, Float.intBitsToFloat((int) (j & 4294967295L)) < 0.0f ? -r9 : r9 - getMeasuredHeight())) & 4294967295L);
        if (intBitsToFloat <= 0.0f && intBitsToFloat2 <= 0.0f) {
            return Float.POSITIVE_INFINITY;
        }
        int i = (int) (floatToRawIntBits >> 32);
        if (Float.intBitsToFloat(i) > intBitsToFloat) {
            return Float.POSITIVE_INFINITY;
        }
        int i2 = (int) (floatToRawIntBits & 4294967295L);
        if (Float.intBitsToFloat(i2) > intBitsToFloat2) {
            return Float.POSITIVE_INFINITY;
        }
        float intBitsToFloat4 = Float.intBitsToFloat(i);
        float intBitsToFloat5 = Float.intBitsToFloat(i2);
        return (intBitsToFloat5 * intBitsToFloat5) + (intBitsToFloat4 * intBitsToFloat4);
    }

    public final void draw(Canvas canvas, GraphicsLayer graphicsLayer) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.drawLayer(canvas, graphicsLayer);
            return;
        }
        long j = this.position;
        float f = (int) (j >> 32);
        float f2 = (int) (j & 4294967295L);
        canvas.translate(f, f2);
        drawContainedDrawModifiers(canvas, graphicsLayer);
        canvas.translate(-f, -f2);
    }

    public final void drawContainedDrawModifiers(Canvas canvas, GraphicsLayer graphicsLayer) {
        Modifier.Node m531headH91voCI = m531headH91voCI(4);
        if (m531headH91voCI == null) {
            performDraw(canvas, graphicsLayer);
            return;
        }
        LayoutNode layoutNode = this.layoutNode;
        layoutNode.getClass();
        LayoutNodeDrawScope layoutNodeDrawScope = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).sharedDrawScope;
        long m685toSizeozmzZPI = IntSizeKt.m685toSizeozmzZPI(this.measuredSize);
        layoutNodeDrawScope.getClass();
        MutableVector mutableVector = null;
        while (m531headH91voCI != null) {
            if (m531headH91voCI instanceof DrawModifierNode) {
                layoutNodeDrawScope.m510drawDirecteZhPAX0$ui_release(canvas, m685toSizeozmzZPI, this, (DrawModifierNode) m531headH91voCI, graphicsLayer);
            } else if ((m531headH91voCI.kindSet & 4) != 0 && (m531headH91voCI instanceof DelegatingNode)) {
                int i = 0;
                for (Modifier.Node node = ((DelegatingNode) m531headH91voCI).delegate; node != null; node = node.child) {
                    if ((node.kindSet & 4) != 0) {
                        i++;
                        if (i == 1) {
                            m531headH91voCI = node;
                        } else {
                            if (mutableVector == null) {
                                mutableVector = new MutableVector(new Modifier.Node[16]);
                            }
                            if (m531headH91voCI != null) {
                                mutableVector.add(m531headH91voCI);
                                m531headH91voCI = null;
                            }
                            mutableVector.add(node);
                        }
                    }
                }
                if (i == 1) {
                }
            }
            m531headH91voCI = DelegatableNodeKt.access$pop(mutableVector);
        }
    }

    public abstract void ensureLookaheadDelegateCreated();

    public final NodeCoordinator findCommonAncestor$ui_release(NodeCoordinator nodeCoordinator) {
        LayoutNode layoutNode = nodeCoordinator.layoutNode;
        LayoutNode layoutNode2 = this.layoutNode;
        if (layoutNode == layoutNode2) {
            Modifier.Node tail = nodeCoordinator.getTail();
            Modifier.Node tail2 = getTail();
            if (!tail2.node.isAttached) {
                InlineClassHelperKt.throwIllegalStateException("visitLocalAncestors called on an unattached node");
            }
            for (Modifier.Node node = tail2.node.parent; node != null; node = node.parent) {
                if ((node.kindSet & 2) != 0 && node == tail) {
                    return nodeCoordinator;
                }
            }
            return this;
        }
        while (layoutNode.depth > layoutNode2.depth) {
            layoutNode = layoutNode.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode);
        }
        LayoutNode layoutNode3 = layoutNode2;
        while (layoutNode3.depth > layoutNode.depth) {
            layoutNode3 = layoutNode3.getParent$ui_release();
            Intrinsics.checkNotNull(layoutNode3);
        }
        while (layoutNode != layoutNode3) {
            layoutNode = layoutNode.getParent$ui_release();
            layoutNode3 = layoutNode3.getParent$ui_release();
            if (layoutNode == null || layoutNode3 == null) {
                throw new IllegalArgumentException("layouts are not part of the same hierarchy");
            }
        }
        return layoutNode3 == layoutNode2 ? this : layoutNode == nodeCoordinator.layoutNode ? nodeCoordinator : layoutNode.nodes.innerCoordinator;
    }

    /* renamed from: fromParentPosition-8S9VItk, reason: not valid java name */
    public final long m529fromParentPosition8S9VItk(long j) {
        long j2 = this.position;
        float intBitsToFloat = Float.intBitsToFloat((int) (j >> 32)) - ((int) (j2 >> 32));
        long floatToRawIntBits = (Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) - ((int) (j2 & 4294967295L))) & 4294967295L) | (Float.floatToRawIntBits(intBitsToFloat) << 32);
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer != null ? ownedLayer.mo546mapOffset8S9VItk(floatToRawIntBits, true) : floatToRawIntBits;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getChild() {
        return this.wrapped;
    }

    @Override // androidx.compose.ui.unit.Density
    public final float getDensity() {
        return this.layoutNode.density.getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public final float getFontScale() {
        return this.layoutNode.density.getFontScale();
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final boolean getHasMeasureResult() {
        return this._measureResult != null;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
    public final LayoutDirection getLayoutDirection() {
        return this.layoutNode.layoutDirection;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable, androidx.compose.ui.node.MeasureScopeWithLayoutNode
    public final LayoutNode getLayoutNode() {
        return this.layoutNode;
    }

    public abstract LookaheadDelegate getLookaheadDelegate();

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final MeasureResult getMeasureResult$ui_release() {
        MeasureResult measureResult = this._measureResult;
        if (measureResult != null) {
            return measureResult;
        }
        throw new IllegalStateException("Asking for measurement result of unmeasured layout modifier");
    }

    /* renamed from: getMinimumTouchTargetSize-NH-jbRc, reason: not valid java name */
    public final long m530getMinimumTouchTargetSizeNHjbRc() {
        return this.layerDensity.mo52toSizeXkaWNTQ(this.layoutNode.viewConfiguration.mo509getMinimumTouchTargetSizeMYxV2XQ());
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LookaheadCapablePlaceable getParent() {
        return this.wrappedBy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
    public final Object getParentData() {
        LayoutNode layoutNode = this.layoutNode;
        if (!layoutNode.nodes.m525hasH91voCI$ui_release(64)) {
            return null;
        }
        getTail();
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        for (Modifier.Node node = layoutNode.nodes.tail; node != null; node = node.parent) {
            if ((node.kindSet & 64) != 0) {
                DelegatingNode delegatingNode = node;
                ?? r5 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof ParentDataModifierNode) {
                        ref$ObjectRef.element = ((ParentDataModifierNode) delegatingNode).modifyParentData(layoutNode.density, ref$ObjectRef.element);
                    } else if ((delegatingNode.kindSet & 64) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node2 = delegatingNode.delegate;
                        int i = 0;
                        delegatingNode = delegatingNode;
                        r5 = r5;
                        while (node2 != null) {
                            if ((node2.kindSet & 64) != 0) {
                                i++;
                                r5 = r5;
                                if (i == 1) {
                                    delegatingNode = node2;
                                } else {
                                    if (r5 == 0) {
                                        r5 = new MutableVector(new Modifier.Node[16]);
                                    }
                                    if (delegatingNode != 0) {
                                        r5.add(delegatingNode);
                                        delegatingNode = 0;
                                    }
                                    r5.add(node2);
                                }
                            }
                            node2 = node2.child;
                            delegatingNode = delegatingNode;
                            r5 = r5;
                        }
                        if (i == 1) {
                        }
                    }
                    delegatingNode = DelegatableNodeKt.access$pop(r5);
                }
            }
        }
        return ref$ObjectRef.element;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final LayoutCoordinates getParentLayoutCoordinates() {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        onCoordinatesUsed$ui_release();
        return this.layoutNode.nodes.outerCoordinator.wrappedBy;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    /* renamed from: getPosition-nOcc-ac */
    public final long mo517getPositionnOccac() {
        return this.position;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: getSize-YbymL2g */
    public final long mo481getSizeYbymL2g() {
        return this.measuredSize;
    }

    public abstract Modifier.Node getTail();

    /* renamed from: head-H91voCI, reason: not valid java name */
    public final Modifier.Node m531headH91voCI(int i) {
        boolean m542getIncludeSelfInTraversalH91voCI = NodeKindKt.m542getIncludeSelfInTraversalH91voCI(i);
        Modifier.Node tail = getTail();
        if (!m542getIncludeSelfInTraversalH91voCI && (tail = tail.parent) == null) {
            return null;
        }
        for (Modifier.Node headNode = headNode(m542getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & i) != 0; headNode = headNode.child) {
            if ((headNode.kindSet & i) != 0) {
                return headNode;
            }
            if (headNode == tail) {
                return null;
            }
        }
        return null;
    }

    public final Modifier.Node headNode(boolean z) {
        Modifier.Node tail;
        NodeChain nodeChain = this.layoutNode.nodes;
        if (nodeChain.outerCoordinator == this) {
            return nodeChain.head;
        }
        if (z) {
            NodeCoordinator nodeCoordinator = this.wrappedBy;
            if (nodeCoordinator != null && (tail = nodeCoordinator.getTail()) != null) {
                return tail.child;
            }
        } else {
            NodeCoordinator nodeCoordinator2 = this.wrappedBy;
            if (nodeCoordinator2 != null) {
                return nodeCoordinator2.getTail();
            }
        }
        return null;
    }

    /* renamed from: hit-1hIXUjU, reason: not valid java name */
    public final void m532hit1hIXUjU(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        if (node == null) {
            mo506hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        int i = hitTestResult.hitDepth;
        hitTestResult.hitDepth = i + 1;
        HitTestResult.access$ensureContainerSize(hitTestResult);
        Object[] objArr = hitTestResult.values;
        int i2 = hitTestResult.hitDepth;
        objArr[i2] = node;
        hitTestResult.distanceFromEdgeAndInLayer[i2] = HitTestResultKt.access$DistanceAndInLayer(-1.0f, z2);
        hitTestResult.resizeToHitDepth();
        m532hit1hIXUjU(NodeCoordinatorKt.m541access$nextUntilhw7D004(node, hitTestSource.mo540entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2);
        hitTestResult.hitDepth = i;
    }

    /* renamed from: hitNear-JHbHoSQ, reason: not valid java name */
    public final void m533hitNearJHbHoSQ(Modifier.Node node, HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2, float f) {
        if (node == null) {
            mo506hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        int i = hitTestResult.hitDepth;
        hitTestResult.hitDepth = i + 1;
        HitTestResult.access$ensureContainerSize(hitTestResult);
        Object[] objArr = hitTestResult.values;
        int i2 = hitTestResult.hitDepth;
        objArr[i2] = node;
        hitTestResult.distanceFromEdgeAndInLayer[i2] = HitTestResultKt.access$DistanceAndInLayer(f, z2);
        hitTestResult.resizeToHitDepth();
        m533hitNearJHbHoSQ(NodeCoordinatorKt.m541access$nextUntilhw7D004(node, hitTestSource.mo540entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2, f);
        hitTestResult.hitDepth = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:40:0x00bf, code lost:
    
        if (androidx.compose.ui.node.DistanceAndInLayer.m504compareToS_HNhKs(r17.m505findBestHitDistanceptXAw2c(), androidx.compose.ui.node.HitTestResultKt.access$DistanceAndInLayer(r8, r19)) > 0) goto L35;
     */
    /* renamed from: hitTest-YqVAtuI, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m534hitTestYqVAtuI(androidx.compose.ui.node.NodeCoordinator.HitTestSource r14, long r15, androidx.compose.ui.node.HitTestResult r17, boolean r18, boolean r19) {
        /*
            Method dump skipped, instructions count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.NodeCoordinator.m534hitTestYqVAtuI(androidx.compose.ui.node.NodeCoordinator$HitTestSource, long, androidx.compose.ui.node.HitTestResult, boolean, boolean):void");
    }

    /* renamed from: hitTestChild-YqVAtuI */
    public void mo506hitTestChildYqVAtuI(HitTestSource hitTestSource, long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        NodeCoordinator nodeCoordinator = this.wrapped;
        if (nodeCoordinator != null) {
            nodeCoordinator.m534hitTestYqVAtuI(hitTestSource, nodeCoordinator.m529fromParentPosition8S9VItk(j), hitTestResult, z, z2);
        }
    }

    public final void invalidateLayer() {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.invalidate();
            return;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final boolean isAttached() {
        return getTail().isAttached;
    }

    public final boolean isTransparent() {
        if (this.layer != null && this.lastLayerAlpha <= 0.0f) {
            return true;
        }
        NodeCoordinator nodeCoordinator = this.wrappedBy;
        if (nodeCoordinator != null) {
            return nodeCoordinator.isTransparent();
        }
        return false;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return (this.layer == null || this.released || !this.layoutNode.isAttached()) ? false : true;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    public final Rect localBoundingBoxOf(LayoutCoordinates layoutCoordinates, boolean z) {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        if (!layoutCoordinates.isAttached()) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinates " + layoutCoordinates + " is not attached!");
        }
        NodeCoordinator coordinator = toCoordinator(layoutCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        MutableRect mutableRect = this._rectCache;
        if (mutableRect == null) {
            mutableRect = new MutableRect();
            this._rectCache = mutableRect;
        }
        mutableRect.left = 0.0f;
        mutableRect.top = 0.0f;
        mutableRect.right = (int) (layoutCoordinates.mo481getSizeYbymL2g() >> 32);
        mutableRect.bottom = (int) (layoutCoordinates.mo481getSizeYbymL2g() & 4294967295L);
        while (coordinator != findCommonAncestor$ui_release) {
            coordinator.rectInParent$ui_release(mutableRect, z, false);
            if (mutableRect.isEmpty()) {
                return Rect.Zero;
            }
            coordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(coordinator);
        }
        ancestorToLocal(findCommonAncestor$ui_release, mutableRect, z);
        return new Rect(mutableRect.left, mutableRect.top, mutableRect.right, mutableRect.bottom);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-R5De75A */
    public final long mo482localPositionOfR5De75A(LayoutCoordinates layoutCoordinates, long j) {
        return mo483localPositionOfS_NoaFU(layoutCoordinates, j);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localPositionOf-S_NoaFU */
    public final long mo483localPositionOfS_NoaFU(LayoutCoordinates layoutCoordinates, long j) {
        if (layoutCoordinates instanceof LookaheadLayoutCoordinates) {
            ((LookaheadLayoutCoordinates) layoutCoordinates).lookaheadDelegate.coordinator.onCoordinatesUsed$ui_release();
            return ((LookaheadLayoutCoordinates) layoutCoordinates).mo483localPositionOfS_NoaFU(this, j ^ (-9223372034707292160L)) ^ (-9223372034707292160L);
        }
        NodeCoordinator coordinator = toCoordinator(layoutCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        while (coordinator != findCommonAncestor$ui_release) {
            OwnedLayer ownedLayer = coordinator.layer;
            if (ownedLayer != null) {
                j = ownedLayer.mo546mapOffset8S9VItk(j, false);
            }
            j = IntOffsetKt.m678plusNvtHpc(j, coordinator.position);
            coordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(coordinator);
        }
        return m526ancestorToLocalS_NoaFU(findCommonAncestor$ui_release, j);
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToRoot-MK-Hz9U */
    public final long mo484localToRootMKHz9U(long j) {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        onCoordinatesUsed$ui_release();
        while (this != null) {
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                j = ownedLayer.mo546mapOffset8S9VItk(j, false);
            }
            j = IntOffsetKt.m678plusNvtHpc(j, this.position);
            this = this.wrappedBy;
        }
        return j;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToScreen-MK-Hz9U */
    public final long mo485localToScreenMKHz9U(long j) {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        return ((AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode)).m555localToScreenMKHz9U(mo484localToRootMKHz9U(j));
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: localToWindow-MK-Hz9U */
    public final long mo486localToWindowMKHz9U(long j) {
        long mo484localToRootMKHz9U = mo484localToRootMKHz9U(j);
        AndroidComposeView androidComposeView = (AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode);
        androidComposeView.recalculateWindowPosition();
        return Matrix.m380mapMKHz9U(mo484localToRootMKHz9U, androidComposeView.viewToWindowMatrix);
    }

    public final void onCoordinatesUsed$ui_release() {
        this.layoutNode.layoutDelegate.onCoordinatesUsed();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v7, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    public final void onMeasured() {
        Modifier.Node node;
        Modifier.Node headNode = headNode(NodeKindKt.m542getIncludeSelfInTraversalH91voCI(128));
        if (headNode == null || (headNode.node.aggregateChildKindSet & 128) == 0) {
            return;
        }
        Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
        Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
        Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
        try {
            boolean m542getIncludeSelfInTraversalH91voCI = NodeKindKt.m542getIncludeSelfInTraversalH91voCI(128);
            if (m542getIncludeSelfInTraversalH91voCI) {
                node = getTail();
            } else {
                node = getTail().parent;
                if (node == null) {
                }
            }
            for (Modifier.Node headNode2 = headNode(m542getIncludeSelfInTraversalH91voCI); headNode2 != null; headNode2 = headNode2.child) {
                if ((headNode2.aggregateChildKindSet & 128) == 0) {
                    break;
                }
                if ((headNode2.kindSet & 128) != 0) {
                    ?? r8 = 0;
                    DelegatingNode delegatingNode = headNode2;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof LayoutAwareModifierNode) {
                            ((LayoutAwareModifierNode) delegatingNode).mo43onRemeasuredozmzZPI(this.measuredSize);
                        } else if ((delegatingNode.kindSet & 128) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node2 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r8 = r8;
                            while (node2 != null) {
                                if ((node2.kindSet & 128) != 0) {
                                    i++;
                                    r8 = r8;
                                    if (i == 1) {
                                        delegatingNode = node2;
                                    } else {
                                        if (r8 == 0) {
                                            r8 = new MutableVector(new Modifier.Node[16]);
                                        }
                                        if (delegatingNode != 0) {
                                            r8.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r8.add(node2);
                                    }
                                }
                                node2 = node2.child;
                                delegatingNode = delegatingNode;
                                r8 = r8;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = DelegatableNodeKt.access$pop(r8);
                    }
                }
                if (headNode2 == node) {
                    break;
                }
            }
        } finally {
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v10 */
    /* JADX WARN: Type inference failed for: r5v11 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    public final void onPlaced() {
        boolean m542getIncludeSelfInTraversalH91voCI = NodeKindKt.m542getIncludeSelfInTraversalH91voCI(128);
        Modifier.Node tail = getTail();
        if (!m542getIncludeSelfInTraversalH91voCI && (tail = tail.parent) == null) {
            return;
        }
        for (Modifier.Node headNode = headNode(m542getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & 128) != 0; headNode = headNode.child) {
            if ((headNode.kindSet & 128) != 0) {
                DelegatingNode delegatingNode = headNode;
                ?? r5 = 0;
                while (delegatingNode != 0) {
                    if (delegatingNode instanceof LayoutAwareModifierNode) {
                        ((LayoutAwareModifierNode) delegatingNode).onPlaced(this);
                    } else if ((delegatingNode.kindSet & 128) != 0 && (delegatingNode instanceof DelegatingNode)) {
                        Modifier.Node node = delegatingNode.delegate;
                        int i = 0;
                        delegatingNode = delegatingNode;
                        r5 = r5;
                        while (node != null) {
                            if ((node.kindSet & 128) != 0) {
                                i++;
                                r5 = r5;
                                if (i == 1) {
                                    delegatingNode = node;
                                } else {
                                    if (r5 == 0) {
                                        r5 = new MutableVector(new Modifier.Node[16]);
                                    }
                                    if (delegatingNode != 0) {
                                        r5.add(delegatingNode);
                                        delegatingNode = 0;
                                    }
                                    r5.add(node);
                                }
                            }
                            node = node.child;
                            delegatingNode = delegatingNode;
                            r5 = r5;
                        }
                        if (i == 1) {
                        }
                    }
                    delegatingNode = DelegatableNodeKt.access$pop(r5);
                }
            }
            if (headNode == tail) {
                return;
            }
        }
    }

    public abstract void performDraw(Canvas canvas, GraphicsLayer graphicsLayer);

    @Override // androidx.compose.ui.layout.Placeable
    /* renamed from: placeAt-f8xVGno */
    public abstract void mo492placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer);

    /* renamed from: placeSelf-MLgxB_4, reason: not valid java name */
    public final void m535placeSelfMLgxB_4(long j, float f, Function1 function1, GraphicsLayer graphicsLayer) {
        Function2 function2;
        LayoutNode layoutNode = this.layoutNode;
        if (graphicsLayer != null) {
            if (function1 != null) {
                InlineClassHelperKt.throwIllegalArgumentException("both ways to create layers shouldn't be used together");
            }
            if (this.explicitLayer != graphicsLayer) {
                this.explicitLayer = null;
                updateLayerBlock(null, false);
                this.explicitLayer = graphicsLayer;
            }
            if (this.layer == null) {
                Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
                Function2 function22 = this._drawBlock;
                if (function22 == null) {
                    NodeCoordinator$drawBlock$1 nodeCoordinator$drawBlock$1 = new NodeCoordinator$drawBlock$1(this, new NodeCoordinator$drawBlock$drawBlockCallToDrawModifiers$1(this));
                    this._drawBlock = nodeCoordinator$drawBlock$1;
                    function2 = nodeCoordinator$drawBlock$1;
                } else {
                    function2 = function22;
                }
                Function0 function0 = this.invalidateParentLayer;
                OwnedLayer createLayer$default = Owner.createLayer$default(requireOwner, function2, function0, graphicsLayer, false, 8);
                createLayer$default.mo548resizeozmzZPI(this.measuredSize);
                createLayer$default.mo547movegyyYBs(j);
                this.layer = createLayer$default;
                layoutNode.innerLayerCoordinatorIsDirty = true;
                ((NodeCoordinator$invalidateParentLayer$1) function0).invoke();
            }
        } else {
            if (this.explicitLayer != null) {
                this.explicitLayer = null;
                updateLayerBlock(null, false);
            }
            updateLayerBlock(function1, false);
        }
        if (!IntOffset.m674equalsimpl0(this.position, j)) {
            this.position = j;
            layoutNode.layoutDelegate.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.mo547movegyyYBs(j);
            } else {
                NodeCoordinator nodeCoordinator = this.wrappedBy;
                if (nodeCoordinator != null) {
                    nodeCoordinator.invalidateLayer();
                }
            }
            LookaheadCapablePlaceable.invalidateAlignmentLinesFromPositionChange(this);
            AndroidComposeView androidComposeView = layoutNode.owner;
            if (androidComposeView != null) {
                androidComposeView.onLayoutChange(layoutNode);
            }
        }
        this.zIndex = f;
        if (this.isPlacingForAlignment) {
            return;
        }
        captureRulers(new PlaceableResult(getMeasureResult$ui_release(), this));
    }

    public final void rectInParent$ui_release(MutableRect mutableRect, boolean z, boolean z2) {
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            if (this.isClipping) {
                if (z2) {
                    long m530getMinimumTouchTargetSizeNHjbRc = m530getMinimumTouchTargetSizeNHjbRc();
                    float intBitsToFloat = Float.intBitsToFloat((int) (m530getMinimumTouchTargetSizeNHjbRc >> 32)) / 2.0f;
                    float intBitsToFloat2 = Float.intBitsToFloat((int) (m530getMinimumTouchTargetSizeNHjbRc & 4294967295L)) / 2.0f;
                    long j = this.measuredSize;
                    mutableRect.intersect(-intBitsToFloat, -intBitsToFloat2, ((int) (j >> 32)) + intBitsToFloat, ((int) (j & 4294967295L)) + intBitsToFloat2);
                } else if (z) {
                    long j2 = this.measuredSize;
                    mutableRect.intersect(0.0f, 0.0f, (int) (j2 >> 32), (int) (j2 & 4294967295L));
                }
                if (mutableRect.isEmpty()) {
                    return;
                }
            }
            ownedLayer.mapBounds(mutableRect, false);
        }
        long j3 = this.position;
        float f = (int) (j3 >> 32);
        mutableRect.left += f;
        mutableRect.right += f;
        float f2 = (int) (j3 & 4294967295L);
        mutableRect.top += f2;
        mutableRect.bottom += f2;
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final void replace$ui_release() {
        GraphicsLayer graphicsLayer = this.explicitLayer;
        if (graphicsLayer != null) {
            mo492placeAtf8xVGno(this.position, this.zIndex, graphicsLayer);
        } else {
            mo480placeAtf8xVGno(this.position, this.zIndex, this.layerBlock);
        }
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: screenToLocal-MK-Hz9U */
    public final long mo487screenToLocalMKHz9U(long j) {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        return mo483localPositionOfS_NoaFU(LayoutCoordinatesKt.findRootCoordinates(this), ((AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode)).m557screenToLocalMKHz9U(j));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public final void setMeasureResult$ui_release(MeasureResult measureResult) {
        NodeCoordinator nodeCoordinator;
        int i = 1;
        MeasureResult measureResult2 = this._measureResult;
        if (measureResult != measureResult2) {
            this._measureResult = measureResult;
            LayoutNode layoutNode = this.layoutNode;
            int i2 = 0;
            if (measureResult2 == null || measureResult.getWidth() != measureResult2.getWidth() || measureResult.getHeight() != measureResult2.getHeight()) {
                int width = measureResult.getWidth();
                int height = measureResult.getHeight();
                OwnedLayer ownedLayer = this.layer;
                if (ownedLayer != null) {
                    ownedLayer.mo548resizeozmzZPI((width << 32) | (height & 4294967295L));
                } else if (layoutNode.isPlaced() && (nodeCoordinator = this.wrappedBy) != null) {
                    nodeCoordinator.invalidateLayer();
                }
                m493setMeasuredSizeozmzZPI((height & 4294967295L) | (width << 32));
                if (this.layerBlock != null) {
                    updateLayerParameters(false);
                }
                boolean m542getIncludeSelfInTraversalH91voCI = NodeKindKt.m542getIncludeSelfInTraversalH91voCI(4);
                Modifier.Node tail = getTail();
                if (m542getIncludeSelfInTraversalH91voCI || (tail = tail.parent) != null) {
                    for (Modifier.Node headNode = headNode(m542getIncludeSelfInTraversalH91voCI); headNode != null && (headNode.aggregateChildKindSet & 4) != 0; headNode = headNode.child) {
                        if ((headNode.kindSet & 4) != 0) {
                            DelegatingNode delegatingNode = headNode;
                            ?? r10 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof DrawModifierNode) {
                                    ((DrawModifierNode) delegatingNode).onMeasureResultChanged();
                                } else if ((delegatingNode.kindSet & 4) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node = delegatingNode.delegate;
                                    int i3 = 0;
                                    delegatingNode = delegatingNode;
                                    r10 = r10;
                                    while (node != null) {
                                        if ((node.kindSet & 4) != 0) {
                                            i3++;
                                            r10 = r10;
                                            if (i3 == 1) {
                                                delegatingNode = node;
                                            } else {
                                                if (r10 == 0) {
                                                    r10 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (delegatingNode != 0) {
                                                    r10.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r10.add(node);
                                            }
                                        }
                                        node = node.child;
                                        delegatingNode = delegatingNode;
                                        r10 = r10;
                                    }
                                    if (i3 == 1) {
                                    }
                                }
                                delegatingNode = DelegatableNodeKt.access$pop(r10);
                            }
                        }
                        if (headNode == tail) {
                            break;
                        }
                    }
                }
                AndroidComposeView androidComposeView = layoutNode.owner;
                if (androidComposeView != null) {
                    androidComposeView.onLayoutChange(layoutNode);
                }
            }
            MutableObjectIntMap mutableObjectIntMap = this.oldAlignmentLines;
            if ((mutableObjectIntMap == null || mutableObjectIntMap._size == 0) && measureResult.getAlignmentLines().isEmpty()) {
                return;
            }
            MutableObjectIntMap mutableObjectIntMap2 = this.oldAlignmentLines;
            Map alignmentLines = measureResult.getAlignmentLines();
            if (mutableObjectIntMap2 != null && mutableObjectIntMap2._size == alignmentLines.size()) {
                Object[] objArr = mutableObjectIntMap2.keys;
                int[] iArr = mutableObjectIntMap2.values;
                long[] jArr = mutableObjectIntMap2.metadata;
                int length = jArr.length - 2;
                if (length < 0) {
                    return;
                }
                int i4 = 0;
                loop0: while (true) {
                    long j = jArr[i4];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i5 = 8 - ((~(i4 - length)) >>> 31);
                        for (int i6 = i2; i6 < i5; i6++) {
                            if ((j & 255) < 128) {
                                int i7 = (i4 << 3) + i6;
                                Object obj = objArr[i7];
                                int i8 = iArr[i7];
                                Integer num = (Integer) alignmentLines.get((AlignmentLine) obj);
                                if (num == null || num.intValue() != i8) {
                                    break loop0;
                                }
                            }
                            j >>= 8;
                            i = 1;
                        }
                        if (i5 != 8) {
                            return;
                        }
                    }
                    if (i4 == length) {
                        return;
                    }
                    i4 += i;
                    i2 = 0;
                }
            }
            layoutNode.layoutDelegate.measurePassDelegate.alignmentLines.onAlignmentsChanged();
            MutableObjectIntMap mutableObjectIntMap3 = this.oldAlignmentLines;
            if (mutableObjectIntMap3 == null) {
                MutableObjectIntMap mutableObjectIntMap4 = ObjectIntMapKt.EmptyObjectIntMap;
                mutableObjectIntMap3 = new MutableObjectIntMap();
                this.oldAlignmentLines = mutableObjectIntMap3;
            }
            mutableObjectIntMap3.clear();
            for (Map.Entry entry : measureResult.getAlignmentLines().entrySet()) {
                mutableObjectIntMap3.set(((Number) entry.getValue()).intValue(), entry.getKey());
            }
        }
    }

    /* renamed from: speculativeHit-JHbHoSQ, reason: not valid java name */
    public final void m536speculativeHitJHbHoSQ(final Modifier.Node node, final HitTestSource hitTestSource, final long j, final HitTestResult hitTestResult, final boolean z, final boolean z2, final float f) {
        if (node == null) {
            mo506hitTestChildYqVAtuI(hitTestSource, j, hitTestResult, z, z2);
            return;
        }
        if (!hitTestSource.interceptOutOfBoundsChildEvents(node)) {
            m536speculativeHitJHbHoSQ(NodeCoordinatorKt.m541access$nextUntilhw7D004(node, hitTestSource.mo540entityTypeOLwlOKw()), hitTestSource, j, hitTestResult, z, z2, f);
            return;
        }
        Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$speculativeHit$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NodeCoordinator nodeCoordinator = NodeCoordinator.this;
                Modifier.Node m541access$nextUntilhw7D004 = NodeCoordinatorKt.m541access$nextUntilhw7D004(node, hitTestSource.mo540entityTypeOLwlOKw());
                NodeCoordinator.HitTestSource hitTestSource2 = hitTestSource;
                long j2 = j;
                HitTestResult hitTestResult2 = hitTestResult;
                boolean z3 = z;
                boolean z4 = z2;
                float f2 = f;
                Function1 function1 = NodeCoordinator.onCommitAffectingLayerParams;
                nodeCoordinator.m536speculativeHitJHbHoSQ(m541access$nextUntilhw7D004, hitTestSource2, j2, hitTestResult2, z3, z4, f2);
                return Unit.INSTANCE;
            }
        };
        if (hitTestResult.hitDepth == CollectionsKt__CollectionsKt.getLastIndex(hitTestResult)) {
            int i = hitTestResult.hitDepth;
            int i2 = i + 1;
            hitTestResult.hitDepth = i2;
            HitTestResult.access$ensureContainerSize(hitTestResult);
            Object[] objArr = hitTestResult.values;
            int i3 = hitTestResult.hitDepth;
            objArr[i3] = node;
            hitTestResult.distanceFromEdgeAndInLayer[i3] = HitTestResultKt.access$DistanceAndInLayer(f, z2);
            hitTestResult.resizeToHitDepth();
            function0.invoke();
            hitTestResult.hitDepth = i;
            if (i2 == CollectionsKt__CollectionsKt.getLastIndex(hitTestResult)) {
                hitTestResult.resizeToHitDepth();
                return;
            }
            return;
        }
        long m505findBestHitDistanceptXAw2c = hitTestResult.m505findBestHitDistanceptXAw2c();
        int i4 = hitTestResult.hitDepth;
        int lastIndex = CollectionsKt__CollectionsKt.getLastIndex(hitTestResult);
        int i5 = lastIndex + 1;
        hitTestResult.hitDepth = i5;
        HitTestResult.access$ensureContainerSize(hitTestResult);
        Object[] objArr2 = hitTestResult.values;
        int i6 = hitTestResult.hitDepth;
        objArr2[i6] = node;
        hitTestResult.distanceFromEdgeAndInLayer[i6] = HitTestResultKt.access$DistanceAndInLayer(f, z2);
        hitTestResult.resizeToHitDepth();
        function0.invoke();
        hitTestResult.hitDepth = lastIndex;
        if (i5 < CollectionsKt__CollectionsKt.getLastIndex(hitTestResult) && DistanceAndInLayer.m504compareToS_HNhKs(m505findBestHitDistanceptXAw2c, hitTestResult.m505findBestHitDistanceptXAw2c()) > 0) {
            int i7 = hitTestResult.hitDepth + 1;
            int i8 = i4 + 1;
            Object[] objArr3 = hitTestResult.values;
            ArraysKt.copyInto(i8, i7, hitTestResult.size, objArr3, objArr3);
            long[] jArr = hitTestResult.distanceFromEdgeAndInLayer;
            System.arraycopy(jArr, i7, jArr, i8, hitTestResult.size - i7);
            hitTestResult.hitDepth = ((hitTestResult.size + i4) - hitTestResult.hitDepth) - 1;
        }
        hitTestResult.resizeToHitDepth();
        hitTestResult.hitDepth = i4;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: transformFrom-EL8BTi8 */
    public final void mo488transformFromEL8BTi8(LayoutCoordinates layoutCoordinates, float[] fArr) {
        NodeCoordinator coordinator = toCoordinator(layoutCoordinates);
        coordinator.onCoordinatesUsed$ui_release();
        NodeCoordinator findCommonAncestor$ui_release = findCommonAncestor$ui_release(coordinator);
        Matrix.m382resetimpl(fArr);
        while (!coordinator.equals(findCommonAncestor$ui_release)) {
            OwnedLayer ownedLayer = coordinator.layer;
            if (ownedLayer != null) {
                ownedLayer.mo549transform58bKbWc(fArr);
            }
            if (!IntOffset.m674equalsimpl0(coordinator.position, 0L)) {
                float[] fArr2 = tmpMatrix;
                Matrix.m382resetimpl(fArr2);
                Matrix.m384translateimpl(fArr2, (int) (r1 >> 32), (int) (r1 & 4294967295L));
                Matrix.m383timesAssign58bKbWc(fArr, fArr2);
            }
            coordinator = coordinator.wrappedBy;
            Intrinsics.checkNotNull(coordinator);
        }
        m537transformFromAncestorEL8BTi8(findCommonAncestor$ui_release, fArr);
    }

    /* renamed from: transformFromAncestor-EL8BTi8, reason: not valid java name */
    public final void m537transformFromAncestorEL8BTi8(NodeCoordinator nodeCoordinator, float[] fArr) {
        if (Intrinsics.areEqual(nodeCoordinator, this)) {
            return;
        }
        NodeCoordinator nodeCoordinator2 = this.wrappedBy;
        Intrinsics.checkNotNull(nodeCoordinator2);
        nodeCoordinator2.m537transformFromAncestorEL8BTi8(nodeCoordinator, fArr);
        if (!IntOffset.m674equalsimpl0(this.position, 0L)) {
            float[] fArr2 = tmpMatrix;
            Matrix.m382resetimpl(fArr2);
            long j = this.position;
            Matrix.m384translateimpl(fArr2, -((int) (j >> 32)), -((int) (j & 4294967295L)));
            Matrix.m383timesAssign58bKbWc(fArr, fArr2);
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer != null) {
            ownedLayer.mo544inverseTransform58bKbWc(fArr);
        }
    }

    public final void updateLayerBlock(Function1 function1, boolean z) {
        AndroidComposeView androidComposeView;
        Function2 function2;
        if (function1 != null && this.explicitLayer != null) {
            InlineClassHelperKt.throwIllegalArgumentException("layerBlock can't be provided when explicitLayer is provided");
        }
        LayoutNode layoutNode = this.layoutNode;
        boolean z2 = (!z && this.layerBlock == function1 && Intrinsics.areEqual(this.layerDensity, layoutNode.density) && this.layerLayoutDirection == layoutNode.layoutDirection) ? false : true;
        this.layerDensity = layoutNode.density;
        this.layerLayoutDirection = layoutNode.layoutDirection;
        boolean isAttached = layoutNode.isAttached();
        Function0 function0 = this.invalidateParentLayer;
        if (!isAttached || function1 == null) {
            this.layerBlock = null;
            OwnedLayer ownedLayer = this.layer;
            if (ownedLayer != null) {
                ownedLayer.destroy();
                layoutNode.innerLayerCoordinatorIsDirty = true;
                ((NodeCoordinator$invalidateParentLayer$1) function0).invoke();
                if (getTail().isAttached && layoutNode.isPlaced() && (androidComposeView = layoutNode.owner) != null) {
                    androidComposeView.onLayoutChange(layoutNode);
                }
            }
            this.layer = null;
            this.lastLayerDrawingWasSkipped = false;
            return;
        }
        this.layerBlock = function1;
        if (this.layer != null) {
            if (z2 && updateLayerParameters(true)) {
                ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).rectManager.onLayoutLayerPositionalPropertiesChanged(layoutNode);
                return;
            }
            return;
        }
        Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
        Function2 function22 = this._drawBlock;
        if (function22 == null) {
            NodeCoordinator$drawBlock$1 nodeCoordinator$drawBlock$1 = new NodeCoordinator$drawBlock$1(this, new NodeCoordinator$drawBlock$drawBlockCallToDrawModifiers$1(this));
            this._drawBlock = nodeCoordinator$drawBlock$1;
            function2 = nodeCoordinator$drawBlock$1;
        } else {
            function2 = function22;
        }
        OwnedLayer createLayer$default = Owner.createLayer$default(requireOwner, function2, function0, null, layoutNode.forceUseOldLayers, 4);
        createLayer$default.mo548resizeozmzZPI(this.measuredSize);
        createLayer$default.mo547movegyyYBs(this.position);
        this.layer = createLayer$default;
        updateLayerParameters(true);
        layoutNode.innerLayerCoordinatorIsDirty = true;
        ((NodeCoordinator$invalidateParentLayer$1) function0).invoke();
    }

    public final boolean updateLayerParameters(boolean z) {
        AndroidComposeView androidComposeView;
        boolean z2 = false;
        if (this.explicitLayer != null) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        if (ownedLayer == null) {
            if (this.layerBlock != null) {
                InlineClassHelperKt.throwIllegalStateException("null layer with a non-null layerBlock");
            }
            return false;
        }
        final Function1 function1 = this.layerBlock;
        if (function1 == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("updateLayerParameters requires a non-null layerBlock");
            throw null;
        }
        ReusableGraphicsLayerScope reusableGraphicsLayerScope = graphicsLayerScope;
        reusableGraphicsLayerScope.setScaleX(1.0f);
        reusableGraphicsLayerScope.setScaleY(1.0f);
        reusableGraphicsLayerScope.setAlpha(1.0f);
        reusableGraphicsLayerScope.setTranslationX(0.0f);
        reusableGraphicsLayerScope.setTranslationY(0.0f);
        reusableGraphicsLayerScope.setShadowElevation(0.0f);
        long j = GraphicsLayerScopeKt.DefaultShadowColor;
        reusableGraphicsLayerScope.m388setAmbientShadowColor8_81llA(j);
        reusableGraphicsLayerScope.m390setSpotShadowColor8_81llA(j);
        reusableGraphicsLayerScope.setRotationZ(0.0f);
        if (reusableGraphicsLayerScope.cameraDistance != 8.0f) {
            reusableGraphicsLayerScope.mutatedFields |= 2048;
            reusableGraphicsLayerScope.cameraDistance = 8.0f;
        }
        reusableGraphicsLayerScope.m391setTransformOrigin__ExYCQ(TransformOrigin.Center);
        reusableGraphicsLayerScope.setShape(RectangleShapeKt.RectangleShape);
        reusableGraphicsLayerScope.setClip(false);
        if (!Intrinsics.areEqual(reusableGraphicsLayerScope.renderEffect, (Object) null)) {
            reusableGraphicsLayerScope.mutatedFields |= 131072;
            reusableGraphicsLayerScope.renderEffect = null;
        }
        reusableGraphicsLayerScope.m389setCompositingStrategyaDBOjCE(0);
        reusableGraphicsLayerScope.size = 9205357640488583168L;
        reusableGraphicsLayerScope.outline = null;
        reusableGraphicsLayerScope.mutatedFields = 0;
        LayoutNode layoutNode = this.layoutNode;
        reusableGraphicsLayerScope.graphicsDensity = layoutNode.density;
        reusableGraphicsLayerScope.layoutDirection = layoutNode.layoutDirection;
        reusableGraphicsLayerScope.size = IntSizeKt.m685toSizeozmzZPI(this.measuredSize);
        ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).snapshotObserver.observeReads$ui_release(this, onCommitAffectingLayerParams, new Function0() { // from class: androidx.compose.ui.node.NodeCoordinator$updateLayerParameters$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Function1 function12 = Function1.this;
                ReusableGraphicsLayerScope reusableGraphicsLayerScope2 = NodeCoordinator.graphicsLayerScope;
                function12.invoke(reusableGraphicsLayerScope2);
                reusableGraphicsLayerScope2.outline = reusableGraphicsLayerScope2.shape.mo37createOutlinePq9zytI(reusableGraphicsLayerScope2.size, reusableGraphicsLayerScope2.layoutDirection, reusableGraphicsLayerScope2.graphicsDensity);
                return Unit.INSTANCE;
            }
        });
        LayerPositionalProperties layerPositionalProperties = this.layerPositionalProperties;
        if (layerPositionalProperties == null) {
            layerPositionalProperties = new LayerPositionalProperties();
            this.layerPositionalProperties = layerPositionalProperties;
        }
        LayerPositionalProperties layerPositionalProperties2 = tmpLayerPositionalProperties;
        layerPositionalProperties2.getClass();
        layerPositionalProperties2.scaleX = layerPositionalProperties.scaleX;
        layerPositionalProperties2.scaleY = layerPositionalProperties.scaleY;
        layerPositionalProperties2.translationX = layerPositionalProperties.translationX;
        layerPositionalProperties2.translationY = layerPositionalProperties.translationY;
        layerPositionalProperties2.rotationZ = layerPositionalProperties.rotationZ;
        layerPositionalProperties2.cameraDistance = layerPositionalProperties.cameraDistance;
        layerPositionalProperties2.transformOrigin = layerPositionalProperties.transformOrigin;
        layerPositionalProperties.scaleX = reusableGraphicsLayerScope.scaleX;
        layerPositionalProperties.scaleY = reusableGraphicsLayerScope.scaleY;
        layerPositionalProperties.translationX = reusableGraphicsLayerScope.translationX;
        layerPositionalProperties.translationY = reusableGraphicsLayerScope.translationY;
        layerPositionalProperties.rotationZ = reusableGraphicsLayerScope.rotationZ;
        layerPositionalProperties.cameraDistance = reusableGraphicsLayerScope.cameraDistance;
        layerPositionalProperties.transformOrigin = reusableGraphicsLayerScope.transformOrigin;
        ownedLayer.updateLayerProperties(reusableGraphicsLayerScope);
        boolean z3 = this.isClipping;
        this.isClipping = reusableGraphicsLayerScope.clip;
        this.lastLayerAlpha = reusableGraphicsLayerScope.alpha;
        if (layerPositionalProperties2.scaleX == layerPositionalProperties.scaleX && layerPositionalProperties2.scaleY == layerPositionalProperties.scaleY && layerPositionalProperties2.translationX == layerPositionalProperties.translationX && layerPositionalProperties2.translationY == layerPositionalProperties.translationY && layerPositionalProperties2.rotationZ == layerPositionalProperties.rotationZ && layerPositionalProperties2.cameraDistance == layerPositionalProperties.cameraDistance && TransformOrigin.m398equalsimpl0(layerPositionalProperties2.transformOrigin, layerPositionalProperties.transformOrigin)) {
            z2 = true;
        }
        boolean z4 = !z2;
        if (z && ((!z2 || z3 != this.isClipping) && (androidComposeView = layoutNode.owner) != null)) {
            androidComposeView.onLayoutChange(layoutNode);
        }
        return z4;
    }

    @Override // androidx.compose.ui.layout.LayoutCoordinates
    /* renamed from: windowToLocal-MK-Hz9U */
    public final long mo489windowToLocalMKHz9U(long j) {
        if (!getTail().isAttached) {
            InlineClassHelperKt.throwIllegalStateException("LayoutCoordinate operations are only valid when isAttached is true");
        }
        LayoutCoordinates findRootCoordinates = LayoutCoordinatesKt.findRootCoordinates(this);
        AndroidComposeView androidComposeView = (AndroidComposeView) LayoutNodeKt.requireOwner(this.layoutNode);
        androidComposeView.recalculateWindowPosition();
        return mo483localPositionOfS_NoaFU(findRootCoordinates, Offset.m314minusMKHz9U(Matrix.m380mapMKHz9U(j, androidComposeView.windowToViewMatrix), findRootCoordinates.mo484localToRootMKHz9U(0L)));
    }

    /* renamed from: withinLayerBounds-k-4lQ0M, reason: not valid java name */
    public final boolean m538withinLayerBoundsk4lQ0M(long j) {
        if ((((9187343241974906880L ^ (j & 9187343241974906880L)) - 4294967297L) & (-9223372034707292160L)) != 0) {
            return false;
        }
        OwnedLayer ownedLayer = this.layer;
        return ownedLayer == null || !this.isClipping || ownedLayer.mo545isInLayerk4lQ0M(j);
    }

    @Override // androidx.compose.ui.node.LookaheadCapablePlaceable
    public final LayoutCoordinates getCoordinates() {
        return this;
    }
}
