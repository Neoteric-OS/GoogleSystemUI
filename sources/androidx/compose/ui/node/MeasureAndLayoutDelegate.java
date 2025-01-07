package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.PlaceableKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.unit.Constraints;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MeasureAndLayoutDelegate {
    public boolean duringFullMeasureLayoutPass;
    public boolean duringMeasureLayout;
    public final LayoutNode root;
    public Constraints rootConstraints;
    public final DepthSortedSetsForDifferentPasses relayoutNodes = new DepthSortedSetsForDifferentPasses();
    public final OnPositionedDispatcher onPositionedDispatcher = new OnPositionedDispatcher();
    public final MutableVector onLayoutCompletedListeners = new MutableVector(new Owner.OnLayoutCompletedListener[16]);
    public final MutableVector postponedMeasureRequests = new MutableVector(new PostponedRequest[16]);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PostponedRequest {
        public final boolean isForced;
        public final boolean isLookahead;
        public final LayoutNode node;

        public PostponedRequest(LayoutNode layoutNode, boolean z, boolean z2) {
            this.node = layoutNode;
            this.isLookahead = z;
            this.isForced = z2;
        }
    }

    public MeasureAndLayoutDelegate(LayoutNode layoutNode) {
        this.root = layoutNode;
    }

    /* renamed from: doLookaheadRemeasure-sdFAvZA, reason: not valid java name */
    public static boolean m521doLookaheadRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean m513remeasureBRTryo0;
        LayoutNode layoutNode2 = layoutNode.lookaheadRoot;
        if (layoutNode2 == null) {
            return false;
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (constraints != null) {
            if (layoutNode2 != null) {
                LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                Intrinsics.checkNotNull(lookaheadPassDelegate);
                m513remeasureBRTryo0 = lookaheadPassDelegate.m513remeasureBRTryo0(constraints.value);
            }
            m513remeasureBRTryo0 = false;
        } else {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            Constraints constraints2 = lookaheadPassDelegate2 != null ? lookaheadPassDelegate2.lookaheadConstraints : null;
            if (constraints2 != null && layoutNode2 != null) {
                Intrinsics.checkNotNull(lookaheadPassDelegate2);
                m513remeasureBRTryo0 = lookaheadPassDelegate2.m513remeasureBRTryo0(constraints2.value);
            }
            m513remeasureBRTryo0 = false;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (m513remeasureBRTryo0 && parent$ui_release != null) {
            if (parent$ui_release.lookaheadRoot == null) {
                LayoutNode.requestRemeasure$ui_release$default(parent$ui_release, false, 3);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestLookaheadRemeasure$ui_release$default(parent$ui_release, false, 3);
            } else if (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InLayoutBlock) {
                parent$ui_release.requestLookaheadRelayout$ui_release(false);
            }
        }
        return m513remeasureBRTryo0;
    }

    /* renamed from: doRemeasure-sdFAvZA, reason: not valid java name */
    public static boolean m522doRemeasuresdFAvZA(LayoutNode layoutNode, Constraints constraints) {
        boolean m507remeasure_Sx5XlM$ui_release$default;
        if (constraints != null) {
            if (layoutNode.intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
                layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
            }
            m507remeasure_Sx5XlM$ui_release$default = layoutNode.layoutDelegate.measurePassDelegate.m516remeasureBRTryo0(constraints.value);
        } else {
            m507remeasure_Sx5XlM$ui_release$default = LayoutNode.m507remeasure_Sx5XlM$ui_release$default(layoutNode);
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (m507remeasure_Sx5XlM$ui_release$default && parent$ui_release != null) {
            LayoutNode.UsageByParent usageByParent = layoutNode.layoutDelegate.measurePassDelegate.measuredByParent;
            if (usageByParent == LayoutNode.UsageByParent.InMeasureBlock) {
                LayoutNode.requestRemeasure$ui_release$default(parent$ui_release, false, 3);
            } else if (usageByParent == LayoutNode.UsageByParent.InLayoutBlock) {
                parent$ui_release.requestRelayout$ui_release(false);
            }
        }
        return m507remeasure_Sx5XlM$ui_release$default;
    }

    public static boolean getCanAffectParent(LayoutNode layoutNode) {
        return layoutNode.layoutDelegate.measurePending && getMeasureAffectsParent(layoutNode);
    }

    public static boolean getMeasureAffectsParent(LayoutNode layoutNode) {
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNode.layoutDelegate.measurePassDelegate;
        return measurePassDelegate.measuredByParent == LayoutNode.UsageByParent.InMeasureBlock || measurePassDelegate.alignmentLines.getRequired$ui_release();
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0022, code lost:
    
        if (r3 < r5) goto L9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void dispatchOnPositionedCallbacks(boolean r6) {
        /*
            r5 = this;
            androidx.compose.ui.node.OnPositionedDispatcher r0 = r5.onPositionedDispatcher
            r1 = 1
            if (r6 == 0) goto L11
            androidx.compose.runtime.collection.MutableVector r6 = r0.layoutNodes
            r6.clear()
            androidx.compose.ui.node.LayoutNode r5 = r5.root
            r6.add(r5)
            r5.needsOnPositionedDispatch = r1
        L11:
            r0.getClass()
            androidx.compose.ui.node.OnPositionedDispatcher$Companion$DepthComparator r5 = androidx.compose.ui.node.OnPositionedDispatcher$Companion$DepthComparator.INSTANCE
            androidx.compose.runtime.collection.MutableVector r6 = r0.layoutNodes
            r6.sortWith(r5)
            int r5 = r6.size
            androidx.compose.ui.node.LayoutNode[] r2 = r0.cachedNodes
            if (r2 == 0) goto L24
            int r3 = r2.length
            if (r3 >= r5) goto L2c
        L24:
            r2 = 16
            int r2 = java.lang.Math.max(r2, r5)
            androidx.compose.ui.node.LayoutNode[] r2 = new androidx.compose.ui.node.LayoutNode[r2]
        L2c:
            r3 = 0
            r0.cachedNodes = r3
            r3 = 0
        L30:
            if (r3 >= r5) goto L3b
            java.lang.Object[] r4 = r6.content
            r4 = r4[r3]
            r2[r3] = r4
            int r3 = r3 + 1
            goto L30
        L3b:
            r6.clear()
            int r5 = r5 - r1
        L3f:
            r6 = -1
            if (r6 >= r5) goto L51
            r6 = r2[r5]
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r1 = r6.needsOnPositionedDispatch
            if (r1 == 0) goto L4e
            androidx.compose.ui.node.OnPositionedDispatcher.dispatchHierarchy(r6)
        L4e:
            int r5 = r5 + (-1)
            goto L3f
        L51:
            r0.cachedNodes = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.dispatchOnPositionedCallbacks(boolean):void");
    }

    public final void drainPostponedMeasureRequests() {
        MutableVector mutableVector = this.postponedMeasureRequests;
        int i = mutableVector.size;
        if (i != 0) {
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    PostponedRequest postponedRequest = (PostponedRequest) objArr[i2];
                    if (postponedRequest.node.isAttached()) {
                        boolean z = postponedRequest.isLookahead;
                        boolean z2 = postponedRequest.isForced;
                        LayoutNode layoutNode = postponedRequest.node;
                        if (z) {
                            LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode, z2, 2);
                        } else {
                            LayoutNode.requestRemeasure$ui_release$default(layoutNode, z2, 2);
                        }
                    }
                    i2++;
                } while (i2 < i);
            }
            mutableVector.clear();
        }
    }

    public final void ensureSubtreeLookaheadReplaced(LayoutNode layoutNode) {
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (Intrinsics.areEqual(layoutNode2.isPlacedInLookahead(), Boolean.TRUE) && !layoutNode2.isDeactivated) {
                    if (this.relayoutNodes.contains(layoutNode2, true)) {
                        layoutNode2.lookaheadReplace$ui_release();
                    }
                    ensureSubtreeLookaheadReplaced(layoutNode2);
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final void forceMeasureTheSubtree(LayoutNode layoutNode, boolean z) {
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
        if ((z ? depthSortedSetsForDifferentPasses.lookaheadSet : depthSortedSetsForDifferentPasses.set).set.isEmpty()) {
            return;
        }
        if (!this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalStateException("forceMeasureTheSubtree should be executed during the measureAndLayout pass");
        }
        if (z ? layoutNode.layoutDelegate.lookaheadMeasurePending : layoutNode.layoutDelegate.measurePending) {
            InlineClassHelperKt.throwIllegalArgumentException("node not yet measured");
        }
        forceMeasureTheSubtreeInternal(layoutNode, z);
    }

    public final void forceMeasureTheSubtreeInternal(LayoutNode layoutNode, boolean z) {
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate;
        LookaheadAlignmentLines lookaheadAlignmentLines;
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if ((!z && getMeasureAffectsParent(layoutNode2)) || (z && (layoutNode2.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || ((lookaheadPassDelegate = layoutNode2.layoutDelegate.lookaheadPassDelegate) != null && (lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines) != null && lookaheadAlignmentLines.getRequired$ui_release())))) {
                    boolean isOutMostLookaheadRoot = LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2);
                    LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode2.layoutDelegate;
                    if (isOutMostLookaheadRoot && !z) {
                        if (layoutNodeLayoutDelegate.lookaheadMeasurePending && depthSortedSetsForDifferentPasses.contains(layoutNode2, true)) {
                            remeasureAndRelayoutIfNeeded(layoutNode2, true, false);
                        } else {
                            forceMeasureTheSubtree(layoutNode2, true);
                        }
                    }
                    if ((z ? layoutNodeLayoutDelegate.lookaheadMeasurePending : layoutNodeLayoutDelegate.measurePending) && depthSortedSetsForDifferentPasses.contains(layoutNode2, z)) {
                        remeasureAndRelayoutIfNeeded(layoutNode2, z, false);
                    }
                    if (!(z ? layoutNodeLayoutDelegate.lookaheadMeasurePending : layoutNodeLayoutDelegate.measurePending)) {
                        forceMeasureTheSubtreeInternal(layoutNode2, z);
                    }
                }
                i2++;
            } while (i2 < i);
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate2 = layoutNode.layoutDelegate;
        if ((z ? layoutNodeLayoutDelegate2.lookaheadMeasurePending : layoutNodeLayoutDelegate2.measurePending) && depthSortedSetsForDifferentPasses.contains(layoutNode, z)) {
            remeasureAndRelayoutIfNeeded(layoutNode, z, false);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r11v1 androidx.compose.ui.Modifier$Node, still in use, count: 2, list:
          (r11v1 androidx.compose.ui.Modifier$Node) from 0x00a2: IF  (r11v1 androidx.compose.ui.Modifier$Node) == (null androidx.compose.ui.Modifier$Node)  -> B:86:0x010b A[HIDDEN] (LINE:163)
          (r11v1 androidx.compose.ui.Modifier$Node) from 0x00a6: PHI (r11v2 androidx.compose.ui.Modifier$Node) = (r11v1 androidx.compose.ui.Modifier$Node) binds: [B:93:0x00a2] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:125)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:62)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:45)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:67)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:35)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:34)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v1 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1, types: [int] */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3, types: [int] */
    /* JADX WARN: Type inference failed for: r15v4 */
    public final boolean measureAndLayout(kotlin.jvm.functions.Function0 r17) {
        /*
            Method dump skipped, instructions count: 278
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.measureAndLayout(kotlin.jvm.functions.Function0):boolean");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r6v1 androidx.compose.ui.Modifier$Node, still in use, count: 2, list:
          (r6v1 androidx.compose.ui.Modifier$Node) from 0x00ba: IF  (r6v1 androidx.compose.ui.Modifier$Node) == (null androidx.compose.ui.Modifier$Node)  -> B:92:0x0120 A[HIDDEN] (LINE:187)
          (r6v1 androidx.compose.ui.Modifier$Node) from 0x00be: PHI (r6v2 androidx.compose.ui.Modifier$Node) = (r6v1 androidx.compose.ui.Modifier$Node) binds: [B:99:0x00ba] A[DONT_GENERATE, DONT_INLINE]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:125)
        	at jadx.core.dex.visitors.regions.TernaryMod.processRegion(TernaryMod.java:62)
        	at jadx.core.dex.visitors.regions.TernaryMod.enterRegion(TernaryMod.java:45)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:67)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1117)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1597)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.TernaryMod.process(TernaryMod.java:35)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.process(IfRegionVisitor.java:34)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:30)
        */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v0, types: [androidx.compose.ui.node.LayoutNode, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11 */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v2, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* renamed from: measureAndLayout-0kLqBqw, reason: not valid java name */
    public final void m523measureAndLayout0kLqBqw(androidx.compose.ui.node.LayoutNode r13, long r14) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.MeasureAndLayoutDelegate.m523measureAndLayout0kLqBqw(androidx.compose.ui.node.LayoutNode, long):void");
    }

    public final void measureOnly() {
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = this.relayoutNodes;
        if (depthSortedSetsForDifferentPasses.isNotEmpty()) {
            LayoutNode layoutNode = this.root;
            if (!layoutNode.isAttached()) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unattached root");
            }
            if (!layoutNode.isPlaced()) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called with unplaced root");
            }
            if (this.duringMeasureLayout) {
                InlineClassHelperKt.throwIllegalArgumentException("performMeasureAndLayout called during measure layout");
            }
            if (this.rootConstraints != null) {
                this.duringMeasureLayout = true;
                this.duringFullMeasureLayoutPass = false;
                try {
                    if (!depthSortedSetsForDifferentPasses.lookaheadSet.set.isEmpty()) {
                        if (layoutNode.lookaheadRoot != null) {
                            remeasureOnly(layoutNode, true);
                        } else {
                            remeasureLookaheadRootsInSubtree(layoutNode);
                        }
                    }
                    remeasureOnly(layoutNode, false);
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                } catch (Throwable th) {
                    this.duringMeasureLayout = false;
                    this.duringFullMeasureLayoutPass = false;
                    throw th;
                }
            }
        }
    }

    public final boolean remeasureAndRelayoutIfNeeded(LayoutNode layoutNode, boolean z, boolean z2) {
        Constraints constraints;
        Placeable.PlacementScope PlacementScope;
        InnerNodeCoordinator innerNodeCoordinator;
        LayoutNode parent$ui_release;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate;
        LookaheadAlignmentLines lookaheadAlignmentLines;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2;
        LookaheadAlignmentLines lookaheadAlignmentLines2;
        if (layoutNode.isDeactivated) {
            return false;
        }
        boolean isPlaced = layoutNode.isPlaced();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (isPlaced || layoutNodeLayoutDelegate.measurePassDelegate.isPlacedByParent || getCanAffectParent(layoutNode) || Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE) || ((layoutNodeLayoutDelegate.lookaheadMeasurePending && (layoutNode.getMeasuredByParentInLookahead$ui_release() == LayoutNode.UsageByParent.InMeasureBlock || ((lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate) != null && (lookaheadAlignmentLines2 = lookaheadPassDelegate2.alignmentLines) != null && lookaheadAlignmentLines2.getRequired$ui_release()))) || layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines.getRequired$ui_release() || ((lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate) != null && (lookaheadAlignmentLines = lookaheadPassDelegate.alignmentLines) != null && lookaheadAlignmentLines.getRequired$ui_release()))) {
            LayoutNode layoutNode2 = this.root;
            if (layoutNode == layoutNode2) {
                constraints = this.rootConstraints;
                Intrinsics.checkNotNull(constraints);
            } else {
                constraints = null;
            }
            if (z) {
                r1 = layoutNodeLayoutDelegate.lookaheadMeasurePending ? m521doLookaheadRemeasuresdFAvZA(layoutNode, constraints) : false;
                if (z2 && ((r1 || layoutNodeLayoutDelegate.lookaheadLayoutPending) && Intrinsics.areEqual(layoutNode.isPlacedInLookahead(), Boolean.TRUE))) {
                    layoutNode.lookaheadReplace$ui_release();
                }
            } else {
                boolean m522doRemeasuresdFAvZA = layoutNodeLayoutDelegate.measurePending ? m522doRemeasuresdFAvZA(layoutNode, constraints) : false;
                if (z2 && layoutNodeLayoutDelegate.layoutPending && (layoutNode == layoutNode2 || ((parent$ui_release = layoutNode.getParent$ui_release()) != null && parent$ui_release.isPlaced() && layoutNodeLayoutDelegate.measurePassDelegate.isPlacedByParent))) {
                    if (layoutNode == layoutNode2) {
                        if (layoutNode.intrinsicsUsageByParent == LayoutNode.UsageByParent.NotUsed) {
                            layoutNode.clearSubtreePlacementIntrinsicsUsage();
                        }
                        LayoutNode parent$ui_release2 = layoutNode.getParent$ui_release();
                        if (parent$ui_release2 == null || (innerNodeCoordinator = parent$ui_release2.nodes.innerCoordinator) == null || (PlacementScope = innerNodeCoordinator.placementScope) == null) {
                            PlacementScope = PlaceableKt.PlacementScope((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode));
                        }
                        PlacementScope.placeRelative(layoutNodeLayoutDelegate.measurePassDelegate, 0, 0, 0.0f);
                    } else {
                        layoutNode.replace$ui_release();
                    }
                    this.onPositionedDispatcher.layoutNodes.add(layoutNode);
                    layoutNode.needsOnPositionedDispatch = true;
                }
                r1 = m522doRemeasuresdFAvZA;
            }
            drainPostponedMeasureRequests();
        }
        return r1;
    }

    public final void remeasureLookaheadRootsInSubtree(LayoutNode layoutNode) {
        MutableVector mutableVector = layoutNode.get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode2 = (LayoutNode) objArr[i2];
                if (getMeasureAffectsParent(layoutNode2)) {
                    if (LayoutNodeLayoutDelegateKt.isOutMostLookaheadRoot(layoutNode2)) {
                        remeasureOnly(layoutNode2, true);
                    } else {
                        remeasureLookaheadRootsInSubtree(layoutNode2);
                    }
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final void remeasureOnly(LayoutNode layoutNode, boolean z) {
        Constraints constraints;
        if (layoutNode.isDeactivated) {
            return;
        }
        if (layoutNode == this.root) {
            constraints = this.rootConstraints;
            Intrinsics.checkNotNull(constraints);
        } else {
            constraints = null;
        }
        if (z) {
            m521doLookaheadRemeasuresdFAvZA(layoutNode, constraints);
        } else {
            m522doRemeasuresdFAvZA(layoutNode, constraints);
        }
    }

    public final boolean requestRemeasure(LayoutNode layoutNode, boolean z) {
        int ordinal = layoutNode.layoutDelegate.layoutState.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            return false;
        }
        if (ordinal == 2 || ordinal == 3) {
            this.postponedMeasureRequests.add(new PostponedRequest(layoutNode, false, z));
            return false;
        }
        if (ordinal != 4) {
            throw new NoWhenBranchMatchedException();
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (layoutNodeLayoutDelegate.measurePending && !z) {
            return false;
        }
        layoutNodeLayoutDelegate.measurePending = true;
        if (layoutNode.isDeactivated) {
            return false;
        }
        if (!layoutNode.isPlaced() && !getCanAffectParent(layoutNode)) {
            return false;
        }
        LayoutNode parent$ui_release = layoutNode.getParent$ui_release();
        if (parent$ui_release == null || !parent$ui_release.layoutDelegate.measurePending) {
            this.relayoutNodes.add(layoutNode, false);
        }
        return !this.duringFullMeasureLayoutPass;
    }

    /* renamed from: updateRootConstraints-BRTryo0, reason: not valid java name */
    public final void m524updateRootConstraintsBRTryo0(long j) {
        Constraints constraints = this.rootConstraints;
        if (constraints == null ? false : Constraints.m649equalsimpl0(constraints.value, j)) {
            return;
        }
        if (this.duringMeasureLayout) {
            InlineClassHelperKt.throwIllegalArgumentException("updateRootConstraints called while measuring");
        }
        this.rootConstraints = new Constraints(j);
        LayoutNode layoutNode = this.root;
        LayoutNode layoutNode2 = layoutNode.lookaheadRoot;
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (layoutNode2 != null) {
            layoutNodeLayoutDelegate.lookaheadMeasurePending = true;
        }
        layoutNodeLayoutDelegate.measurePending = true;
        this.relayoutNodes.add(layoutNode, layoutNode2 != null);
    }
}
