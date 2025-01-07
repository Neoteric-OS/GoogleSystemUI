package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutNodeLayoutDelegate {
    public int childrenAccessingCoordinatesDuringPlacement;
    public int childrenAccessingLookaheadCoordinatesDuringPlacement;
    public boolean coordinatesAccessedDuringModifierPlacement;
    public boolean coordinatesAccessedDuringPlacement;
    public boolean detachedFromParentLookaheadPass;
    public final LayoutNode layoutNode;
    public boolean layoutPending;
    public boolean layoutPendingForAlignment;
    public boolean lookaheadCoordinatesAccessedDuringModifierPlacement;
    public boolean lookaheadCoordinatesAccessedDuringPlacement;
    public boolean lookaheadLayoutPending;
    public boolean lookaheadLayoutPendingForAlignment;
    public boolean lookaheadMeasurePending;
    public LookaheadPassDelegate lookaheadPassDelegate;
    public boolean measurePending;
    public int nextChildLookaheadPlaceOrder;
    public int nextChildPlaceOrder;
    public LayoutNode.LayoutState layoutState = LayoutNode.LayoutState.Idle;
    public final MeasurePassDelegate measurePassDelegate = new MeasurePassDelegate();
    public long performMeasureConstraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15);
    public final Function0 performMeasureBlock = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$performMeasureBlock$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            LayoutNodeLayoutDelegate.this.getOuterCoordinator().mo479measureBRTryo0(LayoutNodeLayoutDelegate.this.performMeasureConstraints);
            return Unit.INSTANCE;
        }
    };

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LookaheadPassDelegate extends Placeable implements Measurable, AlignmentLinesOwner, MotionReferencePlacementDelegate {
        public boolean duringAlignmentLinesQuery;
        public boolean isPlaced;
        public GraphicsLayer lastExplicitLayer;
        public Function1 lastLayerBlock;
        public boolean layingOutChildren;
        public Constraints lookaheadConstraints;
        public boolean measuredOnce;
        public boolean onNodePlacedCalled;
        public Object parentData;
        public boolean placedOnce;
        public boolean relayoutWithoutParentInProgress;
        public int previousPlaceOrder = Integer.MAX_VALUE;
        public int placeOrder = Integer.MAX_VALUE;
        public LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
        public long lastPosition = 0;
        public final LookaheadAlignmentLines alignmentLines = new LookaheadAlignmentLines(this);
        public final MutableVector _childDelegates = new MutableVector(new LookaheadPassDelegate[16]);
        public boolean childDelegatesDirty = true;
        public boolean parentDataDirty = true;

        public LookaheadPassDelegate() {
            this.parentData = LayoutNodeLayoutDelegate.this.measurePassDelegate.parentData;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void forEachChildAlignmentLinesOwner(Function1 function1) {
            MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i2]).layoutDelegate.lookaheadPassDelegate;
                    Intrinsics.checkNotNull(lookaheadPassDelegate);
                    function1.invoke(lookaheadPassDelegate);
                    i2++;
                } while (i2 < i);
            }
        }

        @Override // androidx.compose.ui.layout.Measured
        public final int get(AlignmentLine alignmentLine) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            LayoutNode.LayoutState layoutState = parent$ui_release != null ? parent$ui_release.layoutDelegate.layoutState : null;
            LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.LookaheadMeasuring;
            LookaheadAlignmentLines lookaheadAlignmentLines = this.alignmentLines;
            if (layoutState == layoutState2) {
                lookaheadAlignmentLines.usedDuringParentMeasurement = true;
            } else {
                LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
                if ((parent$ui_release2 != null ? parent$ui_release2.layoutDelegate.layoutState : null) == LayoutNode.LayoutState.LookaheadLayingOut) {
                    lookaheadAlignmentLines.usedDuringParentLayout = true;
                }
            }
            this.duringAlignmentLinesQuery = true;
            LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            int i = lookaheadDelegate.get(alignmentLine);
            this.duringAlignmentLinesQuery = false;
            return i;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final InnerNodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.nodes.innerCoordinator;
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredHeight() {
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.getMeasuredHeight();
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredWidth() {
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate) == null) {
                return null;
            }
            return layoutNodeLayoutDelegate.lookaheadPassDelegate;
        }

        @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
        public final Object getParentData() {
            return this.parentData;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final boolean isPlaced() {
            return this.isPlaced;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void layoutChildren() {
            MutableVector mutableVector;
            int i;
            this.layingOutChildren = true;
            LookaheadAlignmentLines lookaheadAlignmentLines = this.alignmentLines;
            lookaheadAlignmentLines.recalculateQueryOwner();
            final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z = layoutNodeLayoutDelegate.lookaheadLayoutPending;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            if (z && (i = (mutableVector = layoutNode.get_children$ui_release()).size) > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (layoutNode2.layoutDelegate.lookaheadMeasurePending && layoutNode2.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode2.layoutDelegate;
                        LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate2.lookaheadPassDelegate;
                        Intrinsics.checkNotNull(lookaheadPassDelegate);
                        LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate2.lookaheadPassDelegate;
                        Constraints constraints = lookaheadPassDelegate2 != null ? lookaheadPassDelegate2.lookaheadConstraints : null;
                        Intrinsics.checkNotNull(constraints);
                        if (lookaheadPassDelegate.m513remeasureBRTryo0(constraints.value)) {
                            LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, false, 7);
                        }
                    }
                    i2++;
                } while (i2 < i);
            }
            final LookaheadDelegate lookaheadDelegate = getInnerCoordinator().lookaheadDelegate;
            Intrinsics.checkNotNull(lookaheadDelegate);
            if (layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment || (!this.duringAlignmentLinesQuery && !lookaheadDelegate.isPlacingForAlignment && layoutNodeLayoutDelegate.lookaheadLayoutPending)) {
                layoutNodeLayoutDelegate.lookaheadLayoutPending = false;
                LayoutNode.LayoutState layoutState = layoutNodeLayoutDelegate.layoutState;
                layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LookaheadLayingOut;
                Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
                layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringPlacement(false);
                OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) requireOwner).snapshotObserver;
                Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate3 = LayoutNodeLayoutDelegate.this;
                        int i3 = 0;
                        layoutNodeLayoutDelegate3.nextChildLookaheadPlaceOrder = 0;
                        MutableVector mutableVector2 = layoutNodeLayoutDelegate3.layoutNode.get_children$ui_release();
                        int i4 = mutableVector2.size;
                        if (i4 > 0) {
                            Object[] objArr2 = mutableVector2.content;
                            int i5 = 0;
                            do {
                                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = ((LayoutNode) objArr2[i5]).layoutDelegate.lookaheadPassDelegate;
                                Intrinsics.checkNotNull(lookaheadPassDelegate3);
                                lookaheadPassDelegate3.previousPlaceOrder = lookaheadPassDelegate3.placeOrder;
                                lookaheadPassDelegate3.placeOrder = Integer.MAX_VALUE;
                                if (lookaheadPassDelegate3.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                                    lookaheadPassDelegate3.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                                }
                                i5++;
                            } while (i5 < i4);
                        }
                        LayoutNodeLayoutDelegate.LookaheadPassDelegate.this.forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1.1
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                ((AlignmentLinesOwner) obj).getAlignmentLines().usedDuringParentLayout = false;
                                return Unit.INSTANCE;
                            }
                        });
                        LookaheadDelegate lookaheadDelegate2 = LayoutNodeLayoutDelegate.LookaheadPassDelegate.this.getInnerCoordinator().lookaheadDelegate;
                        if (lookaheadDelegate2 != null) {
                            boolean z2 = lookaheadDelegate2.isPlacingForAlignment;
                            List children$ui_release = layoutNodeLayoutDelegate.layoutNode.getChildren$ui_release();
                            int size = children$ui_release.size();
                            for (int i6 = 0; i6 < size; i6++) {
                                LookaheadDelegate lookaheadDelegate3 = ((LayoutNode) children$ui_release.get(i6)).nodes.outerCoordinator.getLookaheadDelegate();
                                if (lookaheadDelegate3 != null) {
                                    lookaheadDelegate3.isPlacingForAlignment = z2;
                                }
                            }
                        }
                        lookaheadDelegate.getMeasureResult$ui_release().placeChildren();
                        if (LayoutNodeLayoutDelegate.LookaheadPassDelegate.this.getInnerCoordinator().lookaheadDelegate != null) {
                            List children$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getChildren$ui_release();
                            int size2 = children$ui_release2.size();
                            for (int i7 = 0; i7 < size2; i7++) {
                                LookaheadDelegate lookaheadDelegate4 = ((LayoutNode) children$ui_release2.get(i7)).nodes.outerCoordinator.getLookaheadDelegate();
                                if (lookaheadDelegate4 != null) {
                                    lookaheadDelegate4.isPlacingForAlignment = false;
                                }
                            }
                        }
                        MutableVector mutableVector3 = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                        int i8 = mutableVector3.size;
                        if (i8 > 0) {
                            Object[] objArr3 = mutableVector3.content;
                            do {
                                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate4 = ((LayoutNode) objArr3[i3]).layoutDelegate.lookaheadPassDelegate;
                                Intrinsics.checkNotNull(lookaheadPassDelegate4);
                                int i9 = lookaheadPassDelegate4.previousPlaceOrder;
                                int i10 = lookaheadPassDelegate4.placeOrder;
                                if (i9 != i10 && i10 == Integer.MAX_VALUE) {
                                    lookaheadPassDelegate4.markSubtreeAsNotPlaced();
                                }
                                i3++;
                            } while (i3 < i8);
                        }
                        LayoutNodeLayoutDelegate.LookaheadPassDelegate.this.forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$layoutChildren$1.4
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                AlignmentLinesOwner alignmentLinesOwner = (AlignmentLinesOwner) obj;
                                alignmentLinesOwner.getAlignmentLines().previousUsedDuringParentLayout = alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout;
                                return Unit.INSTANCE;
                            }
                        });
                        return Unit.INSTANCE;
                    }
                };
                ownerSnapshotObserver.getClass();
                if (layoutNode.lookaheadRoot != null) {
                    ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLookahead, function0);
                } else {
                    ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLayout, function0);
                }
                layoutNodeLayoutDelegate.layoutState = layoutState;
                if (layoutNodeLayoutDelegate.lookaheadCoordinatesAccessedDuringPlacement && lookaheadDelegate.isPlacingForAlignment) {
                    requestLayout();
                }
                layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = false;
            }
            if (lookaheadAlignmentLines.usedDuringParentLayout) {
                lookaheadAlignmentLines.previousUsedDuringParentLayout = true;
            }
            if (lookaheadAlignmentLines.dirty && lookaheadAlignmentLines.getRequired$ui_release()) {
                lookaheadAlignmentLines.recalculate();
            }
            this.layingOutChildren = false;
        }

        public final void markNodeAndSubtreeAsPlaced() {
            boolean z = this.isPlaced;
            this.isPlaced = true;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (!z && layoutNodeLayoutDelegate.lookaheadMeasurePending) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, true, 6);
            }
            MutableVector mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode = (LayoutNode) objArr[i2];
                    LookaheadPassDelegate lookaheadPassDelegate = layoutNode.layoutDelegate.lookaheadPassDelegate;
                    if (lookaheadPassDelegate == null) {
                        throw new IllegalArgumentException("Error: Child node's lookahead pass delegate cannot be null when in a lookahead scope.");
                    }
                    if (lookaheadPassDelegate.placeOrder != Integer.MAX_VALUE) {
                        lookaheadPassDelegate.markNodeAndSubtreeAsPlaced();
                        LayoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode);
                    }
                    i2++;
                } while (i2 < i);
            }
        }

        public final void markSubtreeAsNotPlaced() {
            if (this.isPlaced) {
                int i = 0;
                this.isPlaced = false;
                MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
                int i2 = mutableVector.size;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        LookaheadPassDelegate lookaheadPassDelegate = ((LayoutNode) objArr[i]).layoutDelegate.lookaheadPassDelegate;
                        Intrinsics.checkNotNull(lookaheadPassDelegate);
                        lookaheadPassDelegate.markSubtreeAsNotPlaced();
                        i++;
                    } while (i < i2);
                }
            }
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicHeight(int i) {
            onIntrinsicsQueried();
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.maxIntrinsicHeight(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicWidth(int i) {
            onIntrinsicsQueried();
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.maxIntrinsicWidth(i);
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
        
            if ((r1 != null ? r1.layoutDelegate.layoutState : null) == androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadLayingOut) goto L13;
         */
        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.compose.ui.layout.Placeable mo479measureBRTryo0(long r6) {
            /*
                r5 = this;
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r0 = androidx.compose.ui.node.LayoutNodeLayoutDelegate.this
                androidx.compose.ui.node.LayoutNode r1 = r0.layoutNode
                androidx.compose.ui.node.LayoutNode r1 = r1.getParent$ui_release()
                r2 = 0
                if (r1 == 0) goto L10
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r1 = r1.layoutDelegate
                androidx.compose.ui.node.LayoutNode$LayoutState r1 = r1.layoutState
                goto L11
            L10:
                r1 = r2
            L11:
                androidx.compose.ui.node.LayoutNode$LayoutState r3 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadMeasuring
                if (r1 == r3) goto L25
                androidx.compose.ui.node.LayoutNode r1 = r0.layoutNode
                androidx.compose.ui.node.LayoutNode r1 = r1.getParent$ui_release()
                if (r1 == 0) goto L21
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r1 = r1.layoutDelegate
                androidx.compose.ui.node.LayoutNode$LayoutState r2 = r1.layoutState
            L21:
                androidx.compose.ui.node.LayoutNode$LayoutState r1 = androidx.compose.ui.node.LayoutNode.LayoutState.LookaheadLayingOut
                if (r2 != r1) goto L28
            L25:
                r1 = 0
                r0.detachedFromParentLookaheadPass = r1
            L28:
                androidx.compose.ui.node.LayoutNode r1 = r0.layoutNode
                androidx.compose.ui.node.LayoutNode r2 = r1.getParent$ui_release()
                androidx.compose.ui.node.LayoutNode$UsageByParent r3 = androidx.compose.ui.node.LayoutNode.UsageByParent.NotUsed
                if (r2 == 0) goto L72
                androidx.compose.ui.node.LayoutNode$UsageByParent r4 = r5.measuredByParent
                if (r4 == r3) goto L40
                boolean r1 = r1.canMultiMeasure
                if (r1 == 0) goto L3b
                goto L40
            L3b:
                java.lang.String r1 = "measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()"
                androidx.compose.ui.internal.InlineClassHelperKt.throwIllegalStateException(r1)
            L40:
                androidx.compose.ui.node.LayoutNodeLayoutDelegate r1 = r2.layoutDelegate
                androidx.compose.ui.node.LayoutNode$LayoutState r2 = r1.layoutState
                int r2 = r2.ordinal()
                if (r2 == 0) goto L6d
                r4 = 1
                if (r2 == r4) goto L6d
                r4 = 2
                if (r2 == r4) goto L6a
                r4 = 3
                if (r2 != r4) goto L54
                goto L6a
            L54:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                java.lang.String r7 = "Measurable could be only measured from the parent's measure or layout block. Parents state is "
                r6.<init>(r7)
                androidx.compose.ui.node.LayoutNode$LayoutState r7 = r1.layoutState
                r6.append(r7)
                java.lang.String r6 = r6.toString()
                r5.<init>(r6)
                throw r5
            L6a:
                androidx.compose.ui.node.LayoutNode$UsageByParent r1 = androidx.compose.ui.node.LayoutNode.UsageByParent.InLayoutBlock
                goto L6f
            L6d:
                androidx.compose.ui.node.LayoutNode$UsageByParent r1 = androidx.compose.ui.node.LayoutNode.UsageByParent.InMeasureBlock
            L6f:
                r5.measuredByParent = r1
                goto L74
            L72:
                r5.measuredByParent = r3
            L74:
                androidx.compose.ui.node.LayoutNode r0 = r0.layoutNode
                androidx.compose.ui.node.LayoutNode$UsageByParent r1 = r0.intrinsicsUsageByParent
                if (r1 != r3) goto L7d
                r0.clearSubtreeIntrinsicsUsage$ui_release()
            L7d:
                r5.m513remeasureBRTryo0(r6)
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNodeLayoutDelegate.LookaheadPassDelegate.mo479measureBRTryo0(long):androidx.compose.ui.layout.Placeable");
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicHeight(int i) {
            onIntrinsicsQueried();
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.minIntrinsicHeight(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicWidth(int i) {
            onIntrinsicsQueried();
            LookaheadDelegate lookaheadDelegate = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
            Intrinsics.checkNotNull(lookaheadDelegate);
            return lookaheadDelegate.minIntrinsicWidth(i);
        }

        public final void notifyChildrenUsingLookaheadCoordinatesWhilePlacing() {
            MutableVector mutableVector;
            int i;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.childrenAccessingLookaheadCoordinatesDuringPlacement <= 0 || (i = (mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release()).size) <= 0) {
                return;
            }
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
                if ((layoutNodeLayoutDelegate2.lookaheadCoordinatesAccessedDuringPlacement || layoutNodeLayoutDelegate2.lookaheadCoordinatesAccessedDuringModifierPlacement) && !layoutNodeLayoutDelegate2.lookaheadLayoutPending) {
                    layoutNode.requestLookaheadRelayout$ui_release(false);
                }
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate2.lookaheadPassDelegate;
                if (lookaheadPassDelegate != null) {
                    lookaheadPassDelegate.notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
                }
                i2++;
            } while (i2 < i);
        }

        public final void onIntrinsicsQueried() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, false, 7);
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (parent$ui_release == null || layoutNode.intrinsicsUsageByParent != LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            int ordinal = parent$ui_release.layoutDelegate.layoutState.ordinal();
            layoutNode.intrinsicsUsageByParent = ordinal != 0 ? ordinal != 2 ? parent$ui_release.intrinsicsUsageByParent : LayoutNode.UsageByParent.InLayoutBlock : LayoutNode.UsageByParent.InMeasureBlock;
        }

        public final void onNodePlaced$ui_release() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            LayoutNode.LayoutState layoutState;
            this.onNodePlacedCalled = true;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (!this.isPlaced) {
                markNodeAndSubtreeAsPlaced();
                if (this.relayoutWithoutParentInProgress && parent$ui_release != null) {
                    parent$ui_release.requestLookaheadRelayout$ui_release(false);
                }
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress && ((layoutState = (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate).layoutState) == LayoutNode.LayoutState.LayingOut || layoutState == LayoutNode.LayoutState.LookaheadLayingOut)) {
                if (this.placeOrder != Integer.MAX_VALUE) {
                    InlineClassHelperKt.throwIllegalStateException("Place was called on a node which was placed already");
                }
                int i = layoutNodeLayoutDelegate.nextChildLookaheadPlaceOrder;
                this.placeOrder = i;
                layoutNodeLayoutDelegate.nextChildLookaheadPlaceOrder = i + 1;
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo480placeAtf8xVGno(long j, float f, Function1 function1) {
            m512placeSelfMLgxB_4$1(j, function1, null);
        }

        /* renamed from: placeSelf-MLgxB_4$1, reason: not valid java name */
        public final void m512placeSelfMLgxB_4$1(final long j, Function1 function1, GraphicsLayer graphicsLayer) {
            final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.layoutNode.isDeactivated) {
                InlineClassHelperKt.throwIllegalArgumentException("place is called on a deactivated node");
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LookaheadLayingOut;
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            if (!IntOffset.m674equalsimpl0(j, this.lastPosition)) {
                if (layoutNodeLayoutDelegate.lookaheadCoordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.lookaheadCoordinatesAccessedDuringPlacement) {
                    layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
                }
                notifyChildrenUsingLookaheadCoordinatesWhilePlacing();
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            final Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
            if (layoutNodeLayoutDelegate.lookaheadLayoutPending || !this.isPlaced) {
                layoutNodeLayoutDelegate.setLookaheadCoordinatesAccessedDuringModifierPlacement(false);
                this.alignmentLines.usedByModifierLayout = false;
                OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) requireOwner).snapshotObserver;
                Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$placeSelf$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        LookaheadDelegate lookaheadDelegate;
                        Placeable.PlacementScope placementScope = null;
                        if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(LayoutNodeLayoutDelegate.this.layoutNode)) {
                            NodeCoordinator nodeCoordinator = LayoutNodeLayoutDelegate.this.getOuterCoordinator().wrappedBy;
                            if (nodeCoordinator != null) {
                                placementScope = nodeCoordinator.placementScope;
                            }
                        } else {
                            NodeCoordinator nodeCoordinator2 = LayoutNodeLayoutDelegate.this.getOuterCoordinator().wrappedBy;
                            if (nodeCoordinator2 != null && (lookaheadDelegate = nodeCoordinator2.getLookaheadDelegate()) != null) {
                                placementScope = lookaheadDelegate.placementScope;
                            }
                        }
                        if (placementScope == null) {
                            AndroidComposeView androidComposeView = (AndroidComposeView) requireOwner;
                            androidComposeView.getClass();
                            placementScope = PlaceableKt.PlacementScope(androidComposeView);
                        }
                        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = LayoutNodeLayoutDelegate.this;
                        long j2 = j;
                        LookaheadDelegate lookaheadDelegate2 = layoutNodeLayoutDelegate2.getOuterCoordinator().getLookaheadDelegate();
                        Intrinsics.checkNotNull(lookaheadDelegate2);
                        Placeable.PlacementScope.m495place70tqf50$default(placementScope, lookaheadDelegate2, j2);
                        return Unit.INSTANCE;
                    }
                };
                ownerSnapshotObserver.getClass();
                if (layoutNode.lookaheadRoot != null) {
                    ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLayoutModifierInLookahead, function0);
                } else {
                    ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLayoutModifier, function0);
                }
            } else {
                LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                lookaheadDelegate.m519placeSelfgyyYBs(IntOffset.m676plusqkQi6aY(j, lookaheadDelegate.apparentToRealOffset));
                onNodePlaced$ui_release();
            }
            this.lastPosition = j;
            this.lastLayerBlock = function1;
            this.lastExplicitLayer = graphicsLayer;
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.Idle;
        }

        /* renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m513remeasureBRTryo0(final long j) {
            long j2;
            final LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.layoutNode.isDeactivated) {
                InlineClassHelperKt.throwIllegalArgumentException("measure is called on a deactivated node");
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            layoutNode.canMultiMeasure = layoutNode.canMultiMeasure || (parent$ui_release != null && parent$ui_release.canMultiMeasure);
            if (!layoutNode.layoutDelegate.lookaheadMeasurePending) {
                Constraints constraints = this.lookaheadConstraints;
                if (constraints == null ? false : Constraints.m649equalsimpl0(constraints.value, j)) {
                    AndroidComposeView androidComposeView = layoutNode.owner;
                    if (androidComposeView != null) {
                        androidComposeView.forceMeasureTheSubtree(layoutNode, true);
                    }
                    layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                    return false;
                }
            }
            this.lookaheadConstraints = new Constraints(j);
            m494setMeasurementConstraintsBRTryo0(j);
            this.alignmentLines.usedByModifierMeasurement = false;
            forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$LookaheadPassDelegate$remeasure$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((AlignmentLinesOwner) obj).getAlignmentLines().usedDuringParentMeasurement = false;
                    return Unit.INSTANCE;
                }
            });
            if (this.measuredOnce) {
                j2 = this.measuredSize;
            } else {
                long j3 = Integer.MIN_VALUE;
                j2 = (j3 & 4294967295L) | (j3 << 32);
            }
            this.measuredOnce = true;
            LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
            if (!(lookaheadDelegate != null)) {
                InlineClassHelperKt.throwIllegalStateException("Lookahead result from lookaheadRemeasure cannot be null");
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LookaheadMeasuring;
            layoutNodeLayoutDelegate.lookaheadMeasurePending = false;
            OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).snapshotObserver;
            Function0 function0 = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$performLookaheadMeasure$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    LookaheadDelegate lookaheadDelegate2 = LayoutNodeLayoutDelegate.this.getOuterCoordinator().getLookaheadDelegate();
                    Intrinsics.checkNotNull(lookaheadDelegate2);
                    lookaheadDelegate2.mo479measureBRTryo0(j);
                    return Unit.INSTANCE;
                }
            };
            ownerSnapshotObserver.getClass();
            if (layoutNode.lookaheadRoot != null) {
                ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLookaheadMeasure, function0);
            } else {
                ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingMeasure, function0);
            }
            layoutNodeLayoutDelegate.lookaheadLayoutPending = true;
            layoutNodeLayoutDelegate.lookaheadLayoutPendingForAlignment = true;
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode)) {
                layoutNodeLayoutDelegate.layoutPending = true;
                layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
            } else {
                layoutNodeLayoutDelegate.measurePending = true;
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.Idle;
            m493setMeasuredSizeozmzZPI((lookaheadDelegate.height & 4294967295L) | (lookaheadDelegate.width << 32));
            return (((int) (j2 >> 32)) == lookaheadDelegate.width && ((int) (j2 & 4294967295L)) == lookaheadDelegate.height) ? false : true;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestLayout() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            layoutNode.requestLookaheadRelayout$ui_release(false);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestMeasure() {
            LayoutNode.requestLookaheadRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 7);
        }

        @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
        public final void setPlacedUnderMotionFrameOfReference(boolean z) {
            LookaheadDelegate lookaheadDelegate;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LookaheadDelegate lookaheadDelegate2 = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate();
            if (Boolean.valueOf(z).equals(lookaheadDelegate2 != null ? Boolean.valueOf(lookaheadDelegate2.isPlacedUnderMotionFrameOfReference) : null) || (lookaheadDelegate = layoutNodeLayoutDelegate.getOuterCoordinator().getLookaheadDelegate()) == null) {
                return;
            }
            lookaheadDelegate.isPlacedUnderMotionFrameOfReference = z;
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo492placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
            m512placeSelfMLgxB_4$1(j, null, graphicsLayer);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MeasurePassDelegate extends Placeable implements Measurable, AlignmentLinesOwner, MotionReferencePlacementDelegate {
        public boolean duringAlignmentLinesQuery;
        public boolean isPlaced;
        public boolean isPlacedByParent;
        public GraphicsLayer lastExplicitLayer;
        public Function1 lastLayerBlock;
        public float lastZIndex;
        public boolean layingOutChildren;
        public boolean measuredOnce;
        public boolean needsCoordinatesUpdate;
        public boolean onNodePlacedCalled;
        public Object parentData;
        public final Function0 placeOuterCoordinatorBlock;
        public GraphicsLayer placeOuterCoordinatorLayer;
        public Function1 placeOuterCoordinatorLayerBlock;
        public float placeOuterCoordinatorZIndex;
        public boolean placedOnce;
        public boolean relayoutWithoutParentInProgress;
        public float zIndex;
        public int previousPlaceOrder = Integer.MAX_VALUE;
        public int placeOrder = Integer.MAX_VALUE;
        public LayoutNode.UsageByParent measuredByParent = LayoutNode.UsageByParent.NotUsed;
        public long lastPosition = 0;
        public boolean parentDataDirty = true;
        public final LayoutNodeAlignmentLines alignmentLines = new LayoutNodeAlignmentLines(this);
        public final MutableVector _childDelegates = new MutableVector(new MeasurePassDelegate[16]);
        public boolean childDelegatesDirty = true;
        public final Function0 layoutChildrenBlock = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildrenBlock$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                int i = 0;
                layoutNodeLayoutDelegate.nextChildPlaceOrder = 0;
                MutableVector mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release();
                int i2 = mutableVector.size;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    int i3 = 0;
                    do {
                        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = ((LayoutNode) objArr[i3]).layoutDelegate.measurePassDelegate;
                        measurePassDelegate.previousPlaceOrder = measurePassDelegate.placeOrder;
                        measurePassDelegate.placeOrder = Integer.MAX_VALUE;
                        measurePassDelegate.isPlacedByParent = false;
                        if (measurePassDelegate.measuredByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                            measurePassDelegate.measuredByParent = LayoutNode.UsageByParent.NotUsed;
                        }
                        i3++;
                    } while (i3 < i2);
                }
                LayoutNodeLayoutDelegate.MeasurePassDelegate.this.forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildrenBlock$1.1
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        ((AlignmentLinesOwner) obj).getAlignmentLines().usedDuringParentLayout = false;
                        return Unit.INSTANCE;
                    }
                });
                LayoutNodeLayoutDelegate.MeasurePassDelegate.this.getInnerCoordinator().getMeasureResult$ui_release().placeChildren();
                LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
                MutableVector mutableVector2 = layoutNode.get_children$ui_release();
                int i4 = mutableVector2.size;
                if (i4 > 0) {
                    Object[] objArr2 = mutableVector2.content;
                    do {
                        LayoutNode layoutNode2 = (LayoutNode) objArr2[i];
                        if (layoutNode2.layoutDelegate.measurePassDelegate.previousPlaceOrder != layoutNode2.getPlaceOrder$ui_release()) {
                            layoutNode.onZSortedChildrenInvalidated$ui_release();
                            layoutNode.invalidateLayer$ui_release();
                            if (layoutNode2.getPlaceOrder$ui_release() == Integer.MAX_VALUE) {
                                layoutNode2.layoutDelegate.measurePassDelegate.markSubtreeAsNotPlaced$1();
                            }
                        }
                        i++;
                    } while (i < i4);
                }
                LayoutNodeLayoutDelegate.MeasurePassDelegate.this.forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$layoutChildrenBlock$1.2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        AlignmentLinesOwner alignmentLinesOwner = (AlignmentLinesOwner) obj;
                        alignmentLinesOwner.getAlignmentLines().previousUsedDuringParentLayout = alignmentLinesOwner.getAlignmentLines().usedDuringParentLayout;
                        return Unit.INSTANCE;
                    }
                });
                return Unit.INSTANCE;
            }
        };
        public long placeOuterCoordinatorPosition = 0;

        public MeasurePassDelegate() {
            this.placeOuterCoordinatorBlock = new Function0() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$placeOuterCoordinatorBlock$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    Placeable.PlacementScope PlacementScope;
                    NodeCoordinator nodeCoordinator = LayoutNodeLayoutDelegate.this.getOuterCoordinator().wrappedBy;
                    if (nodeCoordinator == null || (PlacementScope = nodeCoordinator.placementScope) == null) {
                        PlacementScope = PlaceableKt.PlacementScope((AndroidComposeView) LayoutNodeKt.requireOwner(LayoutNodeLayoutDelegate.this.layoutNode));
                    }
                    LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this;
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                    Function1 function1 = measurePassDelegate.placeOuterCoordinatorLayerBlock;
                    GraphicsLayer graphicsLayer = measurePassDelegate.placeOuterCoordinatorLayer;
                    if (graphicsLayer != null) {
                        NodeCoordinator outerCoordinator = layoutNodeLayoutDelegate.getOuterCoordinator();
                        long j = measurePassDelegate.placeOuterCoordinatorPosition;
                        float f = measurePassDelegate.placeOuterCoordinatorZIndex;
                        Placeable.PlacementScope.access$handleMotionFrameOfReferencePlacement(PlacementScope, outerCoordinator);
                        outerCoordinator.mo492placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j, outerCoordinator.apparentToRealOffset), f, graphicsLayer);
                    } else if (function1 == null) {
                        NodeCoordinator outerCoordinator2 = layoutNodeLayoutDelegate.getOuterCoordinator();
                        long j2 = measurePassDelegate.placeOuterCoordinatorPosition;
                        float f2 = measurePassDelegate.placeOuterCoordinatorZIndex;
                        Placeable.PlacementScope.access$handleMotionFrameOfReferencePlacement(PlacementScope, outerCoordinator2);
                        outerCoordinator2.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j2, outerCoordinator2.apparentToRealOffset), f2, (Function1) null);
                    } else {
                        NodeCoordinator outerCoordinator3 = layoutNodeLayoutDelegate.getOuterCoordinator();
                        long j3 = measurePassDelegate.placeOuterCoordinatorPosition;
                        float f3 = measurePassDelegate.placeOuterCoordinatorZIndex;
                        Placeable.PlacementScope.access$handleMotionFrameOfReferencePlacement(PlacementScope, outerCoordinator3);
                        outerCoordinator3.mo480placeAtf8xVGno(IntOffset.m676plusqkQi6aY(j3, outerCoordinator3.apparentToRealOffset), f3, function1);
                    }
                    return Unit.INSTANCE;
                }
            };
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void forEachChildAlignmentLinesOwner(Function1 function1) {
            MutableVector mutableVector = LayoutNodeLayoutDelegate.this.layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    function1.invoke(((LayoutNode) objArr[i2]).layoutDelegate.measurePassDelegate);
                    i2++;
                } while (i2 < i);
            }
        }

        @Override // androidx.compose.ui.layout.Measured
        public final int get(AlignmentLine alignmentLine) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            LayoutNode.LayoutState layoutState = parent$ui_release != null ? parent$ui_release.layoutDelegate.layoutState : null;
            LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.Measuring;
            LayoutNodeAlignmentLines layoutNodeAlignmentLines = this.alignmentLines;
            if (layoutState == layoutState2) {
                layoutNodeAlignmentLines.usedDuringParentMeasurement = true;
            } else {
                LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
                if ((parent$ui_release2 != null ? parent$ui_release2.layoutDelegate.layoutState : null) == LayoutNode.LayoutState.LayingOut) {
                    layoutNodeAlignmentLines.usedDuringParentLayout = true;
                }
            }
            this.duringAlignmentLinesQuery = true;
            int i = layoutNodeLayoutDelegate.getOuterCoordinator().get(alignmentLine);
            this.duringAlignmentLinesQuery = false;
            return i;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLines getAlignmentLines() {
            return this.alignmentLines;
        }

        public final List getChildDelegates$ui_release() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            layoutNodeLayoutDelegate.layoutNode.updateChildrenIfDirty$ui_release();
            boolean z = this.childDelegatesDirty;
            MutableVector mutableVector = this._childDelegates;
            if (!z) {
                return mutableVector.asMutableList();
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            MutableVector mutableVector2 = layoutNode.get_children$ui_release();
            int i = mutableVector2.size;
            if (i > 0) {
                Object[] objArr = mutableVector2.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (mutableVector.size <= i2) {
                        mutableVector.add(layoutNode2.layoutDelegate.measurePassDelegate);
                    } else {
                        MeasurePassDelegate measurePassDelegate = layoutNode2.layoutDelegate.measurePassDelegate;
                        Object[] objArr2 = mutableVector.content;
                        Object obj = objArr2[i2];
                        objArr2[i2] = measurePassDelegate;
                    }
                    i2++;
                } while (i2 < i);
            }
            mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.size);
            this.childDelegatesDirty = false;
            return mutableVector.asMutableList();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final InnerNodeCoordinator getInnerCoordinator() {
            return LayoutNodeLayoutDelegate.this.layoutNode.nodes.innerCoordinator;
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredHeight() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredHeight();
        }

        @Override // androidx.compose.ui.layout.Placeable
        public final int getMeasuredWidth() {
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().getMeasuredWidth();
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final AlignmentLinesOwner getParentAlignmentLinesOwner() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate;
            LayoutNode parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release();
            if (parent$ui_release == null || (layoutNodeLayoutDelegate = parent$ui_release.layoutDelegate) == null) {
                return null;
            }
            return layoutNodeLayoutDelegate.measurePassDelegate;
        }

        @Override // androidx.compose.ui.layout.Measured, androidx.compose.ui.layout.IntrinsicMeasurable
        public final Object getParentData() {
            return this.parentData;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final boolean isPlaced() {
            return this.isPlaced;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void layoutChildren() {
            MutableVector mutableVector;
            int i;
            boolean z;
            this.layingOutChildren = true;
            LayoutNodeAlignmentLines layoutNodeAlignmentLines = this.alignmentLines;
            layoutNodeAlignmentLines.recalculateQueryOwner();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z2 = layoutNodeLayoutDelegate.layoutPending;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            if (z2 && (i = (mutableVector = layoutNode.get_children$ui_release()).size) > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode2.layoutDelegate;
                    if (layoutNodeLayoutDelegate2.measurePending) {
                        MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate2.measurePassDelegate;
                        if (measurePassDelegate.measuredByParent == LayoutNode.UsageByParent.InMeasureBlock) {
                            Constraints constraints = measurePassDelegate.measuredOnce ? new Constraints(measurePassDelegate.measurementConstraints) : null;
                            if (constraints != null) {
                                if (layoutNode2.intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
                                    layoutNode2.clearSubtreeIntrinsicsUsage$ui_release();
                                }
                                z = layoutNode2.layoutDelegate.measurePassDelegate.m516remeasureBRTryo0(constraints.value);
                            } else {
                                z = false;
                            }
                            if (z) {
                                LayoutNode.requestRemeasure$ui_release$default(layoutNode, false, 7);
                            }
                        }
                    }
                    i2++;
                } while (i2 < i);
            }
            if (layoutNodeLayoutDelegate.layoutPendingForAlignment || (!this.duringAlignmentLinesQuery && !getInnerCoordinator().isPlacingForAlignment && layoutNodeLayoutDelegate.layoutPending)) {
                layoutNodeLayoutDelegate.layoutPending = false;
                LayoutNode.LayoutState layoutState = layoutNodeLayoutDelegate.layoutState;
                layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LayingOut;
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringPlacement(false);
                OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).snapshotObserver;
                ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLayout, this.layoutChildrenBlock);
                layoutNodeLayoutDelegate.layoutState = layoutState;
                if (getInnerCoordinator().isPlacingForAlignment && layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement) {
                    requestLayout();
                }
                layoutNodeLayoutDelegate.layoutPendingForAlignment = false;
            }
            if (layoutNodeAlignmentLines.usedDuringParentLayout) {
                layoutNodeAlignmentLines.previousUsedDuringParentLayout = true;
            }
            if (layoutNodeAlignmentLines.dirty && layoutNodeAlignmentLines.getRequired$ui_release()) {
                layoutNodeAlignmentLines.recalculate();
            }
            this.layingOutChildren = false;
        }

        public final void markNodeAndSubtreeAsPlaced$1() {
            boolean z = this.isPlaced;
            this.isPlaced = true;
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            if (!z) {
                layoutNode.nodes.innerCoordinator.onPlaced();
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
                if (layoutNodeLayoutDelegate.measurePending) {
                    LayoutNode.requestRemeasure$ui_release$default(layoutNode, true, 6);
                } else if (layoutNodeLayoutDelegate.lookaheadMeasurePending) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, true, 6);
                }
            }
            NodeChain nodeChain = layoutNode.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
            for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !Intrinsics.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                if (nodeCoordinator2.lastLayerDrawingWasSkipped) {
                    nodeCoordinator2.invalidateLayer();
                }
            }
            MutableVector mutableVector = layoutNode.get_children$ui_release();
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                    if (layoutNode2.getPlaceOrder$ui_release() != Integer.MAX_VALUE) {
                        layoutNode2.layoutDelegate.measurePassDelegate.markNodeAndSubtreeAsPlaced$1();
                        LayoutNode.rescheduleRemeasureOrRelayout$ui_release(layoutNode2);
                    }
                    i2++;
                } while (i2 < i);
            }
        }

        public final void markSubtreeAsNotPlaced$1() {
            if (this.isPlaced) {
                int i = 0;
                this.isPlaced = false;
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
                NodeChain nodeChain = layoutNodeLayoutDelegate.layoutNode.nodes;
                NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
                for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !Intrinsics.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                    if (nodeCoordinator2.layer != null) {
                        if (nodeCoordinator2.explicitLayer != null) {
                            nodeCoordinator2.explicitLayer = null;
                        }
                        nodeCoordinator2.updateLayerBlock(null, false);
                        nodeCoordinator2.layoutNode.requestRelayout$ui_release(false);
                    }
                }
                MutableVector mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release();
                int i2 = mutableVector.size;
                if (i2 > 0) {
                    Object[] objArr = mutableVector.content;
                    do {
                        ((LayoutNode) objArr[i]).layoutDelegate.measurePassDelegate.markSubtreeAsNotPlaced$1();
                        i++;
                    } while (i < i2);
                }
            }
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicHeight(int i) {
            onIntrinsicsQueried$1();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().maxIntrinsicHeight(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int maxIntrinsicWidth(int i) {
            onIntrinsicsQueried$1();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().maxIntrinsicWidth(i);
        }

        @Override // androidx.compose.ui.layout.Measurable
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo479measureBRTryo0(long j) {
            LayoutNode.UsageByParent usageByParent;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode.UsageByParent usageByParent2 = layoutNode.intrinsicsUsageByParent;
            LayoutNode.UsageByParent usageByParent3 = LayoutNode.UsageByParent.NotUsed;
            if (usageByParent2 == usageByParent3) {
                layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
            }
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNodeLayoutDelegate.layoutNode)) {
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                Intrinsics.checkNotNull(lookaheadPassDelegate);
                lookaheadPassDelegate.measuredByParent = usageByParent3;
                lookaheadPassDelegate.mo479measureBRTryo0(j);
            }
            LayoutNode layoutNode2 = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode2.getParent$ui_release();
            if (parent$ui_release != null) {
                if (this.measuredByParent != usageByParent3 && !layoutNode2.canMultiMeasure) {
                    InlineClassHelperKt.throwIllegalStateException("measure() may not be called multiple times on the same Measurable. If you want to get the content size of the Measurable before calculating the final constraints, please use methods like minIntrinsicWidth()/maxIntrinsicWidth() and minIntrinsicHeight()/maxIntrinsicHeight()");
                }
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = parent$ui_release.layoutDelegate;
                int ordinal = layoutNodeLayoutDelegate2.layoutState.ordinal();
                if (ordinal == 0) {
                    usageByParent = LayoutNode.UsageByParent.InMeasureBlock;
                } else {
                    if (ordinal != 2) {
                        throw new IllegalStateException("Measurable could be only measured from the parent's measure or layout block. Parents state is " + layoutNodeLayoutDelegate2.layoutState);
                    }
                    usageByParent = LayoutNode.UsageByParent.InLayoutBlock;
                }
                this.measuredByParent = usageByParent;
            } else {
                this.measuredByParent = usageByParent3;
            }
            m516remeasureBRTryo0(j);
            return this;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicHeight(int i) {
            onIntrinsicsQueried$1();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().minIntrinsicHeight(i);
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasurable
        public final int minIntrinsicWidth(int i) {
            onIntrinsicsQueried$1();
            return LayoutNodeLayoutDelegate.this.getOuterCoordinator().minIntrinsicWidth(i);
        }

        public final void notifyChildrenUsingCoordinatesWhilePlacing() {
            MutableVector mutableVector;
            int i;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement <= 0 || (i = (mutableVector = layoutNodeLayoutDelegate.layoutNode.get_children$ui_release()).size) <= 0) {
                return;
            }
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
                if ((layoutNodeLayoutDelegate2.coordinatesAccessedDuringPlacement || layoutNodeLayoutDelegate2.coordinatesAccessedDuringModifierPlacement) && !layoutNodeLayoutDelegate2.layoutPending) {
                    layoutNode.requestRelayout$ui_release(false);
                }
                layoutNodeLayoutDelegate2.measurePassDelegate.notifyChildrenUsingCoordinatesWhilePlacing();
                i2++;
            } while (i2 < i);
        }

        public final void onIntrinsicsQueried$1() {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode.requestRemeasure$ui_release$default(layoutNodeLayoutDelegate.layoutNode, false, 7);
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (parent$ui_release == null || layoutNode.intrinsicsUsageByParent != LayoutNode.UsageByParent.NotUsed) {
                return;
            }
            int ordinal = parent$ui_release.layoutDelegate.layoutState.ordinal();
            layoutNode.intrinsicsUsageByParent = ordinal != 0 ? ordinal != 2 ? parent$ui_release.intrinsicsUsageByParent : LayoutNode.UsageByParent.InLayoutBlock : LayoutNode.UsageByParent.InMeasureBlock;
        }

        public final void onNodePlaced$ui_release() {
            this.onNodePlacedCalled = true;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            float f = getInnerCoordinator().zIndex;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            NodeChain nodeChain = layoutNode.nodes;
            for (NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator; nodeCoordinator != nodeChain.innerCoordinator; nodeCoordinator = nodeCoordinator.wrapped) {
                f += ((LayoutModifierNodeCoordinator) nodeCoordinator).zIndex;
            }
            if (f != this.zIndex) {
                this.zIndex = f;
                if (parent$ui_release != null) {
                    parent$ui_release.onZSortedChildrenInvalidated$ui_release();
                }
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
            }
            if (this.isPlaced) {
                layoutNode.nodes.innerCoordinator.onPlaced();
            } else {
                if (parent$ui_release != null) {
                    parent$ui_release.invalidateLayer$ui_release();
                }
                markNodeAndSubtreeAsPlaced$1();
                if (this.relayoutWithoutParentInProgress && parent$ui_release != null) {
                    parent$ui_release.requestRelayout$ui_release(false);
                }
            }
            if (parent$ui_release == null) {
                this.placeOrder = 0;
            } else if (!this.relayoutWithoutParentInProgress) {
                LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = parent$ui_release.layoutDelegate;
                if (layoutNodeLayoutDelegate2.layoutState == LayoutNode.LayoutState.LayingOut) {
                    if (this.placeOrder != Integer.MAX_VALUE) {
                        InlineClassHelperKt.throwIllegalStateException("Place was called on a node which was placed already");
                    }
                    int i = layoutNodeLayoutDelegate2.nextChildPlaceOrder;
                    this.placeOrder = i;
                    layoutNodeLayoutDelegate2.nextChildPlaceOrder = i + 1;
                }
            }
            layoutChildren();
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo480placeAtf8xVGno(long j, float f, Function1 function1) {
            m515placeSelfMLgxB_4$2(j, f, function1, null);
        }

        /* renamed from: placeOuterCoordinator-MLgxB_4, reason: not valid java name */
        public final void m514placeOuterCoordinatorMLgxB_4(long j, float f, Function1 function1, GraphicsLayer graphicsLayer) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.layoutNode.isDeactivated) {
                InlineClassHelperKt.throwIllegalArgumentException("place is called on a deactivated node");
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.LayingOut;
            boolean z = !this.placedOnce;
            this.lastPosition = j;
            this.lastZIndex = f;
            this.lastLayerBlock = function1;
            this.lastExplicitLayer = graphicsLayer;
            this.placedOnce = true;
            this.onNodePlacedCalled = false;
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            AndroidComposeView androidComposeView = (AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode);
            androidComposeView.rectManager.m580onLayoutPositionChanged70tqf50(layoutNode, j, z);
            if (layoutNodeLayoutDelegate.layoutPending || !this.isPlaced) {
                this.alignmentLines.usedByModifierLayout = false;
                layoutNodeLayoutDelegate.setCoordinatesAccessedDuringModifierPlacement(false);
                this.placeOuterCoordinatorLayerBlock = function1;
                this.placeOuterCoordinatorPosition = j;
                this.placeOuterCoordinatorZIndex = f;
                this.placeOuterCoordinatorLayer = graphicsLayer;
                OwnerSnapshotObserver ownerSnapshotObserver = androidComposeView.snapshotObserver;
                ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingLayoutModifier, this.placeOuterCoordinatorBlock);
            } else {
                NodeCoordinator outerCoordinator = layoutNodeLayoutDelegate.getOuterCoordinator();
                outerCoordinator.m535placeSelfMLgxB_4(IntOffset.m676plusqkQi6aY(j, outerCoordinator.apparentToRealOffset), f, function1, graphicsLayer);
                onNodePlaced$ui_release();
            }
            layoutNodeLayoutDelegate.layoutState = LayoutNode.LayoutState.Idle;
        }

        /* renamed from: placeSelf-MLgxB_4$2, reason: not valid java name */
        public final void m515placeSelfMLgxB_4$2(long j, float f, Function1 function1, GraphicsLayer graphicsLayer) {
            Placeable.PlacementScope PlacementScope;
            this.isPlacedByParent = true;
            boolean m674equalsimpl0 = IntOffset.m674equalsimpl0(j, this.lastPosition);
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (!m674equalsimpl0 || this.needsCoordinatesUpdate) {
                if (layoutNodeLayoutDelegate.coordinatesAccessedDuringModifierPlacement || layoutNodeLayoutDelegate.coordinatesAccessedDuringPlacement || this.needsCoordinatesUpdate) {
                    layoutNodeLayoutDelegate.layoutPending = true;
                    this.needsCoordinatesUpdate = false;
                }
                notifyChildrenUsingCoordinatesWhilePlacing();
            }
            if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNodeLayoutDelegate.layoutNode)) {
                NodeCoordinator nodeCoordinator = layoutNodeLayoutDelegate.getOuterCoordinator().wrappedBy;
                LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
                if (nodeCoordinator == null || (PlacementScope = nodeCoordinator.placementScope) == null) {
                    PlacementScope = PlaceableKt.PlacementScope((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode));
                }
                LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                Intrinsics.checkNotNull(lookaheadPassDelegate);
                LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
                if (parent$ui_release != null) {
                    parent$ui_release.layoutDelegate.nextChildLookaheadPlaceOrder = 0;
                }
                lookaheadPassDelegate.placeOrder = Integer.MAX_VALUE;
                PlacementScope.place(lookaheadPassDelegate, (int) (j >> 32), (int) (4294967295L & j), 0.0f);
            }
            LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate2 != null && !lookaheadPassDelegate2.placedOnce) {
                InlineClassHelperKt.throwIllegalStateException("Error: Placement happened before lookahead.");
            }
            m514placeOuterCoordinatorMLgxB_4(j, f, function1, graphicsLayer);
        }

        /* renamed from: remeasure-BRTryo0, reason: not valid java name */
        public final boolean m516remeasureBRTryo0(long j) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            if (layoutNodeLayoutDelegate.layoutNode.isDeactivated) {
                InlineClassHelperKt.throwIllegalArgumentException("measure is called on a deactivated node");
            }
            LayoutNode layoutNode = layoutNodeLayoutDelegate.layoutNode;
            Owner requireOwner = LayoutNodeKt.requireOwner(layoutNode);
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            boolean z = true;
            layoutNode.canMultiMeasure = layoutNode.canMultiMeasure || (parent$ui_release != null && parent$ui_release.canMultiMeasure);
            if (!layoutNode.layoutDelegate.measurePending && Constraints.m649equalsimpl0(this.measurementConstraints, j)) {
                ((AndroidComposeView) requireOwner).forceMeasureTheSubtree(layoutNode, false);
                layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                return false;
            }
            this.alignmentLines.usedByModifierMeasurement = false;
            forEachChildAlignmentLinesOwner(new Function1() { // from class: androidx.compose.ui.node.LayoutNodeLayoutDelegate$MeasurePassDelegate$remeasure$2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    ((AlignmentLinesOwner) obj).getAlignmentLines().usedDuringParentMeasurement = false;
                    return Unit.INSTANCE;
                }
            });
            this.measuredOnce = true;
            long j2 = layoutNodeLayoutDelegate.getOuterCoordinator().measuredSize;
            m494setMeasurementConstraintsBRTryo0(j);
            LayoutNode.LayoutState layoutState = layoutNodeLayoutDelegate.layoutState;
            LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.Idle;
            if (layoutState != layoutState2) {
                InlineClassHelperKt.throwIllegalStateException("layout state is not idle before measure starts");
            }
            LayoutNode.LayoutState layoutState3 = LayoutNode.LayoutState.Measuring;
            layoutNodeLayoutDelegate.layoutState = layoutState3;
            layoutNodeLayoutDelegate.measurePending = false;
            layoutNodeLayoutDelegate.performMeasureConstraints = j;
            OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode)).snapshotObserver;
            ownerSnapshotObserver.observeReads$ui_release(layoutNode, ownerSnapshotObserver.onCommitAffectingMeasure, layoutNodeLayoutDelegate.performMeasureBlock);
            if (layoutNodeLayoutDelegate.layoutState == layoutState3) {
                layoutNodeLayoutDelegate.layoutPending = true;
                layoutNodeLayoutDelegate.layoutPendingForAlignment = true;
                layoutNodeLayoutDelegate.layoutState = layoutState2;
            }
            if (IntSize.m683equalsimpl0(layoutNodeLayoutDelegate.getOuterCoordinator().measuredSize, j2) && layoutNodeLayoutDelegate.getOuterCoordinator().width == this.width && layoutNodeLayoutDelegate.getOuterCoordinator().height == this.height) {
                z = false;
            }
            m493setMeasuredSizeozmzZPI((layoutNodeLayoutDelegate.getOuterCoordinator().height & 4294967295L) | (layoutNodeLayoutDelegate.getOuterCoordinator().width << 32));
            return z;
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestLayout() {
            LayoutNode layoutNode = LayoutNodeLayoutDelegate.this.layoutNode;
            LayoutNode$Companion$ErrorMeasurePolicy$1 layoutNode$Companion$ErrorMeasurePolicy$1 = LayoutNode.ErrorMeasurePolicy;
            layoutNode.requestRelayout$ui_release(false);
        }

        @Override // androidx.compose.ui.node.AlignmentLinesOwner
        public final void requestMeasure() {
            LayoutNode.requestRemeasure$ui_release$default(LayoutNodeLayoutDelegate.this.layoutNode, false, 7);
        }

        @Override // androidx.compose.ui.node.MotionReferencePlacementDelegate
        public final void setPlacedUnderMotionFrameOfReference(boolean z) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            boolean z2 = layoutNodeLayoutDelegate.getOuterCoordinator().isPlacedUnderMotionFrameOfReference;
            if (z != z2) {
                layoutNodeLayoutDelegate.getOuterCoordinator().isPlacedUnderMotionFrameOfReference = z2;
                this.needsCoordinatesUpdate = true;
            }
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo492placeAtf8xVGno(long j, float f, GraphicsLayer graphicsLayer) {
            m515placeSelfMLgxB_4$2(j, f, null, graphicsLayer);
        }
    }

    public LayoutNodeLayoutDelegate(LayoutNode layoutNode) {
        this.layoutNode = layoutNode;
    }

    public final NodeCoordinator getOuterCoordinator() {
        return this.layoutNode.nodes.outerCoordinator;
    }

    public final void onCoordinatesUsed() {
        LayoutNode.LayoutState layoutState = this.layoutNode.layoutDelegate.layoutState;
        LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.LayingOut;
        LayoutNode.LayoutState layoutState3 = LayoutNode.LayoutState.LookaheadLayingOut;
        if (layoutState == layoutState2 || layoutState == layoutState3) {
            if (this.measurePassDelegate.layingOutChildren) {
                setCoordinatesAccessedDuringPlacement(true);
            } else {
                setCoordinatesAccessedDuringModifierPlacement(true);
            }
        }
        if (layoutState == layoutState3) {
            LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
            if (lookaheadPassDelegate == null || !lookaheadPassDelegate.layingOutChildren) {
                setLookaheadCoordinatesAccessedDuringModifierPlacement(true);
            } else {
                setLookaheadCoordinatesAccessedDuringPlacement(true);
            }
        }
    }

    public final void setChildrenAccessingCoordinatesDuringPlacement(int i) {
        int i2 = this.childrenAccessingCoordinatesDuringPlacement;
        this.childrenAccessingCoordinatesDuringPlacement = i;
        if ((i2 == 0) != (i == 0)) {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = parent$ui_release != null ? parent$ui_release.layoutDelegate : null;
            if (layoutNodeLayoutDelegate != null) {
                if (i == 0) {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement - 1);
                } else {
                    layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final void setChildrenAccessingLookaheadCoordinatesDuringPlacement(int i) {
        int i2 = this.childrenAccessingLookaheadCoordinatesDuringPlacement;
        this.childrenAccessingLookaheadCoordinatesDuringPlacement = i;
        if ((i2 == 0) != (i == 0)) {
            LayoutNode parent$ui_release = this.layoutNode.getParent$ui_release();
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = parent$ui_release != null ? parent$ui_release.layoutDelegate : null;
            if (layoutNodeLayoutDelegate != null) {
                if (i == 0) {
                    layoutNodeLayoutDelegate.setChildrenAccessingLookaheadCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
                } else {
                    layoutNodeLayoutDelegate.setChildrenAccessingLookaheadCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
                }
            }
        }
    }

    public final void setCoordinatesAccessedDuringModifierPlacement(boolean z) {
        if (this.coordinatesAccessedDuringModifierPlacement != z) {
            this.coordinatesAccessedDuringModifierPlacement = z;
            if (z && !this.coordinatesAccessedDuringPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void setCoordinatesAccessedDuringPlacement(boolean z) {
        if (this.coordinatesAccessedDuringPlacement != z) {
            this.coordinatesAccessedDuringPlacement = z;
            if (z && !this.coordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.coordinatesAccessedDuringModifierPlacement) {
                    return;
                }
                setChildrenAccessingCoordinatesDuringPlacement(this.childrenAccessingCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void setLookaheadCoordinatesAccessedDuringModifierPlacement(boolean z) {
        if (this.lookaheadCoordinatesAccessedDuringModifierPlacement != z) {
            this.lookaheadCoordinatesAccessedDuringModifierPlacement = z;
            if (z && !this.lookaheadCoordinatesAccessedDuringPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.lookaheadCoordinatesAccessedDuringPlacement) {
                    return;
                }
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void setLookaheadCoordinatesAccessedDuringPlacement(boolean z) {
        if (this.lookaheadCoordinatesAccessedDuringPlacement != z) {
            this.lookaheadCoordinatesAccessedDuringPlacement = z;
            if (z && !this.lookaheadCoordinatesAccessedDuringModifierPlacement) {
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement + 1);
            } else {
                if (z || this.lookaheadCoordinatesAccessedDuringModifierPlacement) {
                    return;
                }
                setChildrenAccessingLookaheadCoordinatesDuringPlacement(this.childrenAccessingLookaheadCoordinatesDuringPlacement - 1);
            }
        }
    }

    public final void updateParentData() {
        MeasurePassDelegate measurePassDelegate = this.measurePassDelegate;
        Object obj = measurePassDelegate.parentData;
        LayoutNode layoutNode = this.layoutNode;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        if ((obj != null || layoutNodeLayoutDelegate.getOuterCoordinator().getParentData() != null) && measurePassDelegate.parentDataDirty) {
            measurePassDelegate.parentDataDirty = false;
            measurePassDelegate.parentData = layoutNodeLayoutDelegate.getOuterCoordinator().getParentData();
            LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
            if (parent$ui_release != null) {
                LayoutNode.requestRemeasure$ui_release$default(parent$ui_release, false, 7);
            }
        }
        LookaheadPassDelegate lookaheadPassDelegate = this.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            Object obj2 = lookaheadPassDelegate.parentData;
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = LayoutNodeLayoutDelegate.this;
            if (obj2 == null) {
                LookaheadDelegate lookaheadDelegate = layoutNodeLayoutDelegate2.getOuterCoordinator().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate);
                if (lookaheadDelegate.coordinator.getParentData() == null) {
                    return;
                }
            }
            if (lookaheadPassDelegate.parentDataDirty) {
                lookaheadPassDelegate.parentDataDirty = false;
                LookaheadDelegate lookaheadDelegate2 = layoutNodeLayoutDelegate2.getOuterCoordinator().getLookaheadDelegate();
                Intrinsics.checkNotNull(lookaheadDelegate2);
                lookaheadPassDelegate.parentData = lookaheadDelegate2.coordinator.getParentData();
                if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode)) {
                    LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
                    if (parent$ui_release2 != null) {
                        LayoutNode.requestRemeasure$ui_release$default(parent$ui_release2, false, 7);
                        return;
                    }
                    return;
                }
                LayoutNode parent$ui_release3 = layoutNode.getParent$ui_release();
                if (parent$ui_release3 != null) {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(parent$ui_release3, false, 7);
                }
            }
        }
    }
}
