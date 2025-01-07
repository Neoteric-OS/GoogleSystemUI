package androidx.compose.ui.node;

import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawModifierNode;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Remeasurement;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate.LookaheadPassDelegate;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.JvmActuals_jvmKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutNode implements ComposeNodeLifecycleCallback, Remeasurement, OwnerScope, ComposeUiNode, Owner.OnLayoutCompletedListener {
    public SemanticsConfiguration _collapsedSemantics;
    public final MutableVectorWithMutationTracking _foldedChildren;
    public LayoutNode _foldedParent;
    public NodeCoordinator _innerLayerCoordinator;
    public Modifier _modifier;
    public MutableVector _unfoldedChildren;
    public final MutableVector _zSortedChildren;
    public boolean canMultiMeasure;
    public CompositionLocalMap compositionLocalMap;
    public Density density;
    public int depth;
    public boolean forceUseOldLayers;
    public boolean ignoreRemeasureRequests;
    public boolean innerLayerCoordinatorIsDirty;
    public AndroidViewHolder interopViewFactoryHolder;
    public IntrinsicsPolicy intrinsicsPolicy;
    public UsageByParent intrinsicsUsageByParent;
    public boolean isDeactivated;
    public final boolean isVirtual;
    public boolean isVirtualLookaheadRoot;
    public long lastSize;
    public final LayoutNodeLayoutDelegate layoutDelegate;
    public LayoutDirection layoutDirection;
    public LayoutNode lookaheadRoot;
    public MeasurePolicy measurePolicy;
    public boolean needsOnPositionedDispatch;
    public final NodeChain nodes;
    public long offsetFromRoot;
    public Function1 onAttach;
    public Function1 onDetach;
    public long outerToInnerOffset;
    public boolean outerToInnerOffsetDirty;
    public AndroidComposeView owner;
    public Modifier pendingModifier;
    public UsageByParent previousIntrinsicsUsageByParent;
    public int semanticsId;
    public LayoutNodeSubcompositionsState subcompositionsState;
    public boolean unfoldedVirtualChildrenListDirty;
    public ViewConfiguration viewConfiguration;
    public int virtualChildrenCount;
    public boolean zSortedChildrenInvalidated;
    public static final LayoutNode$Companion$ErrorMeasurePolicy$1 ErrorMeasurePolicy = new LayoutNode$Companion$ErrorMeasurePolicy$1("Undefined intrinsics block and it is required");
    public static final Function0 Constructor = new Function0() { // from class: androidx.compose.ui.node.LayoutNode$Companion$Constructor$1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return new LayoutNode(3);
        }
    };
    public static final LayoutNode$Companion$DummyViewConfiguration$1 DummyViewConfiguration = new LayoutNode$Companion$DummyViewConfiguration$1();
    public static final LayoutNode$$ExternalSyntheticLambda0 ZComparator = new LayoutNode$$ExternalSyntheticLambda0();

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class LayoutState {
        public static final /* synthetic */ LayoutState[] $VALUES;
        public static final LayoutState Idle;
        public static final LayoutState LayingOut;
        public static final LayoutState LookaheadLayingOut;
        public static final LayoutState LookaheadMeasuring;
        public static final LayoutState Measuring;

        static {
            LayoutState layoutState = new LayoutState("Measuring", 0);
            Measuring = layoutState;
            LayoutState layoutState2 = new LayoutState("LookaheadMeasuring", 1);
            LookaheadMeasuring = layoutState2;
            LayoutState layoutState3 = new LayoutState("LayingOut", 2);
            LayingOut = layoutState3;
            LayoutState layoutState4 = new LayoutState("LookaheadLayingOut", 3);
            LookaheadLayingOut = layoutState4;
            LayoutState layoutState5 = new LayoutState("Idle", 4);
            Idle = layoutState5;
            $VALUES = new LayoutState[]{layoutState, layoutState2, layoutState3, layoutState4, layoutState5};
        }

        public static LayoutState valueOf(String str) {
            return (LayoutState) Enum.valueOf(LayoutState.class, str);
        }

        public static LayoutState[] values() {
            return (LayoutState[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class NoIntrinsicsMeasurePolicy implements MeasurePolicy {
        public final String error;

        public NoIntrinsicsMeasurePolicy(String str) {
            this.error = str;
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            throw new IllegalStateException(this.error.toString());
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
            throw new IllegalStateException(this.error.toString());
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class UsageByParent {
        public static final /* synthetic */ UsageByParent[] $VALUES;
        public static final UsageByParent InLayoutBlock;
        public static final UsageByParent InMeasureBlock;
        public static final UsageByParent NotUsed;

        static {
            UsageByParent usageByParent = new UsageByParent("InMeasureBlock", 0);
            InMeasureBlock = usageByParent;
            UsageByParent usageByParent2 = new UsageByParent("InLayoutBlock", 1);
            InLayoutBlock = usageByParent2;
            UsageByParent usageByParent3 = new UsageByParent("NotUsed", 2);
            NotUsed = usageByParent3;
            $VALUES = new UsageByParent[]{usageByParent, usageByParent2, usageByParent3};
        }

        public static UsageByParent valueOf(String str) {
            return (UsageByParent) Enum.valueOf(UsageByParent.class, str);
        }

        public static UsageByParent[] values() {
            return (UsageByParent[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutState.values().length];
            try {
                iArr[4] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LayoutNode(int i) {
        this(SemanticsModifierKt.lastIdentifier.addAndGet(1), (i & 1) == 0);
    }

    /* renamed from: remeasure-_Sx5XlM$ui_release$default, reason: not valid java name */
    public static boolean m507remeasure_Sx5XlM$ui_release$default(LayoutNode layoutNode) {
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNode.layoutDelegate.measurePassDelegate;
        Constraints constraints = measurePassDelegate.measuredOnce ? new Constraints(measurePassDelegate.measurementConstraints) : null;
        if (constraints == null) {
            layoutNode.getClass();
            return false;
        }
        if (layoutNode.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
        }
        return layoutNode.layoutDelegate.measurePassDelegate.m516remeasureBRTryo0(constraints.value);
    }

    public static void requestLookaheadRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, int i) {
        LayoutNode parent$ui_release;
        if ((i & 1) != 0) {
            z = false;
        }
        boolean z2 = (i & 2) != 0;
        boolean z3 = (i & 4) != 0;
        if (layoutNode.lookaheadRoot == null) {
            InlineClassHelperKt.throwIllegalStateException("Lookahead measure cannot be requested on a node that is not a part of theLookaheadScope");
        }
        AndroidComposeView androidComposeView = layoutNode.owner;
        if (androidComposeView == null || layoutNode.ignoreRemeasureRequests || layoutNode.isVirtual) {
            return;
        }
        androidComposeView.onRequestMeasure(layoutNode, true, z, z2);
        if (z3) {
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNode.layoutDelegate.lookaheadPassDelegate;
            Intrinsics.checkNotNull(lookaheadPassDelegate);
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            UsageByParent usageByParent = layoutNodeLayoutDelegate.layoutNode.intrinsicsUsageByParent;
            if (parent$ui_release2 == null || usageByParent == UsageByParent.NotUsed) {
                return;
            }
            while (parent$ui_release2.intrinsicsUsageByParent == usageByParent && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
                parent$ui_release2 = parent$ui_release;
            }
            int ordinal = usageByParent.ordinal();
            if (ordinal == 0) {
                if (parent$ui_release2.lookaheadRoot != null) {
                    requestLookaheadRemeasure$ui_release$default(parent$ui_release2, z, 6);
                    return;
                } else {
                    requestRemeasure$ui_release$default(parent$ui_release2, z, 6);
                    return;
                }
            }
            if (ordinal != 1) {
                throw new IllegalStateException("Intrinsics isn't used by the parent");
            }
            if (parent$ui_release2.lookaheadRoot != null) {
                parent$ui_release2.requestLookaheadRelayout$ui_release(z);
            } else {
                parent$ui_release2.requestRelayout$ui_release(z);
            }
        }
    }

    public static void requestRemeasure$ui_release$default(LayoutNode layoutNode, boolean z, int i) {
        AndroidComposeView androidComposeView;
        LayoutNode parent$ui_release;
        if ((i & 1) != 0) {
            z = false;
        }
        boolean z2 = (i & 2) != 0;
        boolean z3 = (i & 4) != 0;
        if (layoutNode.ignoreRemeasureRequests || layoutNode.isVirtual || (androidComposeView = layoutNode.owner) == null) {
            return;
        }
        androidComposeView.onRequestMeasure(layoutNode, false, z, z2);
        if (z3) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
            LayoutNode parent$ui_release2 = layoutNodeLayoutDelegate.layoutNode.getParent$ui_release();
            UsageByParent usageByParent = layoutNodeLayoutDelegate.layoutNode.intrinsicsUsageByParent;
            if (parent$ui_release2 == null || usageByParent == UsageByParent.NotUsed) {
                return;
            }
            while (parent$ui_release2.intrinsicsUsageByParent == usageByParent && (parent$ui_release = parent$ui_release2.getParent$ui_release()) != null) {
                parent$ui_release2 = parent$ui_release;
            }
            int ordinal = usageByParent.ordinal();
            if (ordinal == 0) {
                requestRemeasure$ui_release$default(parent$ui_release2, z, 6);
            } else {
                if (ordinal != 1) {
                    throw new IllegalStateException("Intrinsics isn't used by the parent");
                }
                parent$ui_release2.requestRelayout$ui_release(z);
            }
        }
    }

    public static void rescheduleRemeasureOrRelayout$ui_release(LayoutNode layoutNode) {
        int i = WhenMappings.$EnumSwitchMapping$0[layoutNode.layoutDelegate.layoutState.ordinal()];
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode.layoutDelegate;
        if (i != 1) {
            throw new IllegalStateException("Unexpected state " + layoutNodeLayoutDelegate.layoutState);
        }
        if (layoutNodeLayoutDelegate.lookaheadMeasurePending) {
            requestLookaheadRemeasure$ui_release$default(layoutNode, true, 6);
            return;
        }
        if (layoutNodeLayoutDelegate.lookaheadLayoutPending) {
            layoutNode.requestLookaheadRelayout$ui_release(true);
        }
        if (layoutNodeLayoutDelegate.measurePending) {
            requestRemeasure$ui_release$default(layoutNode, true, 6);
        } else if (layoutNodeLayoutDelegate.layoutPending) {
            layoutNode.requestRelayout$ui_release(true);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:84:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:0x014b  */
    /* JADX WARN: Type inference failed for: r2v19, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r5v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r5v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v0, types: [androidx.compose.ui.node.NodeChain] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyModifier(androidx.compose.ui.Modifier r15) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.node.LayoutNode.applyModifier(androidx.compose.ui.Modifier):void");
    }

    public final void attach$ui_release(AndroidComposeView androidComposeView) {
        LayoutNode layoutNode;
        int i = 0;
        if (!(this.owner == null)) {
            InlineClassHelperKt.throwIllegalStateException("Cannot attach " + this + " as it already is attached.  Tree: " + debugTreeToString(0));
        }
        LayoutNode layoutNode2 = this._foldedParent;
        if (layoutNode2 != null && !Intrinsics.areEqual(layoutNode2.owner, androidComposeView)) {
            StringBuilder sb = new StringBuilder("Attaching to a different owner(");
            sb.append(androidComposeView);
            sb.append(") than the parent's owner(");
            LayoutNode parent$ui_release = getParent$ui_release();
            sb.append(parent$ui_release != null ? parent$ui_release.owner : null);
            sb.append("). This tree: ");
            sb.append(debugTreeToString(0));
            sb.append(" Parent tree: ");
            LayoutNode layoutNode3 = this._foldedParent;
            sb.append(layoutNode3 != null ? layoutNode3.debugTreeToString(0) : null);
            InlineClassHelperKt.throwIllegalStateException(sb.toString());
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        if (parent$ui_release2 == null) {
            layoutNodeLayoutDelegate.measurePassDelegate.isPlaced = true;
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.isPlaced = true;
            }
        }
        NodeChain nodeChain = this.nodes;
        nodeChain.outerCoordinator.wrappedBy = parent$ui_release2 != null ? parent$ui_release2.nodes.innerCoordinator : null;
        this.owner = androidComposeView;
        this.depth = (parent$ui_release2 != null ? parent$ui_release2.depth : -1) + 1;
        Modifier modifier = this.pendingModifier;
        if (modifier != null) {
            applyModifier(modifier);
        }
        this.pendingModifier = null;
        if (nodeChain.m525hasH91voCI$ui_release(8)) {
            invalidateSemantics$ui_release();
        }
        androidComposeView.getClass();
        if (this.isVirtualLookaheadRoot) {
            setLookaheadRoot(this);
        } else {
            LayoutNode layoutNode4 = this._foldedParent;
            if (layoutNode4 == null || (layoutNode = layoutNode4.lookaheadRoot) == null) {
                layoutNode = this.lookaheadRoot;
            }
            setLookaheadRoot(layoutNode);
            if (this.lookaheadRoot == null && nodeChain.m525hasH91voCI$ui_release(512)) {
                setLookaheadRoot(this);
            }
        }
        if (!this.isDeactivated) {
            for (Modifier.Node node = nodeChain.head; node != null; node = node.child) {
                node.markAsAttached$ui_release();
            }
        }
        MutableVector mutableVector = this._foldedChildren.vector;
        int i2 = mutableVector.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector.content;
            do {
                ((LayoutNode) objArr[i]).attach$ui_release(androidComposeView);
                i++;
            } while (i < i2);
        }
        if (!this.isDeactivated) {
            nodeChain.runAttachLifecycle();
        }
        invalidateMeasurements$ui_release();
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateMeasurements$ui_release();
        }
        NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
        for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !Intrinsics.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
            nodeCoordinator2.updateLayerBlock(nodeCoordinator2.layerBlock, true);
            OwnedLayer ownedLayer = nodeCoordinator2.layer;
            if (ownedLayer != null) {
                ownedLayer.invalidate();
            }
        }
        Function1 function1 = this.onAttach;
        if (function1 != null) {
            function1.invoke(androidComposeView);
        }
        layoutNodeLayoutDelegate.updateParentData();
    }

    public final void clearSubtreeIntrinsicsUsage$ui_release() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        UsageByParent usageByParent = UsageByParent.NotUsed;
        this.intrinsicsUsageByParent = usageByParent;
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                if (layoutNode.intrinsicsUsageByParent != usageByParent) {
                    layoutNode.clearSubtreeIntrinsicsUsage$ui_release();
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final void clearSubtreePlacementIntrinsicsUsage() {
        this.previousIntrinsicsUsageByParent = this.intrinsicsUsageByParent;
        this.intrinsicsUsageByParent = UsageByParent.NotUsed;
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                if (layoutNode.intrinsicsUsageByParent == UsageByParent.InLayoutBlock) {
                    layoutNode.clearSubtreePlacementIntrinsicsUsage();
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final String debugTreeToString(int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("  ");
        }
        sb.append("|-");
        sb.append(toString());
        sb.append('\n');
        MutableVector mutableVector = get_children$ui_release();
        int i3 = mutableVector.size;
        if (i3 > 0) {
            Object[] objArr = mutableVector.content;
            int i4 = 0;
            do {
                sb.append(((LayoutNode) objArr[i4]).debugTreeToString(i + 1));
                i4++;
            } while (i4 < i3);
        }
        String sb2 = sb.toString();
        return i == 0 ? sb2.substring(0, sb2.length() - 1) : sb2;
    }

    public final void detach$ui_release() {
        LookaheadAlignmentLines lookaheadAlignmentLines;
        AndroidComposeView androidComposeView = this.owner;
        if (androidComposeView == null) {
            StringBuilder sb = new StringBuilder("Cannot detach node that is already detached!  Tree: ");
            LayoutNode parent$ui_release = getParent$ui_release();
            sb.append(parent$ui_release != null ? parent$ui_release.debugTreeToString(0) : null);
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck(sb.toString());
            throw null;
        }
        LayoutNode parent$ui_release2 = getParent$ui_release();
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        if (parent$ui_release2 != null) {
            parent$ui_release2.invalidateLayer$ui_release();
            parent$ui_release2.invalidateMeasurements$ui_release();
            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate.measurePassDelegate;
            UsageByParent usageByParent = UsageByParent.NotUsed;
            measurePassDelegate.measuredByParent = usageByParent;
            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
            if (lookaheadPassDelegate != null) {
                lookaheadPassDelegate.measuredByParent = usageByParent;
            }
        }
        LayoutNodeAlignmentLines layoutNodeAlignmentLines = layoutNodeLayoutDelegate.measurePassDelegate.alignmentLines;
        layoutNodeAlignmentLines.dirty = true;
        layoutNodeAlignmentLines.usedDuringParentMeasurement = false;
        layoutNodeAlignmentLines.previousUsedDuringParentLayout = false;
        layoutNodeAlignmentLines.usedDuringParentLayout = false;
        layoutNodeAlignmentLines.usedByModifierMeasurement = false;
        layoutNodeAlignmentLines.usedByModifierLayout = false;
        layoutNodeAlignmentLines.queryOwner = null;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate2 != null && (lookaheadAlignmentLines = lookaheadPassDelegate2.alignmentLines) != null) {
            lookaheadAlignmentLines.dirty = true;
            lookaheadAlignmentLines.usedDuringParentMeasurement = false;
            lookaheadAlignmentLines.previousUsedDuringParentLayout = false;
            lookaheadAlignmentLines.usedDuringParentLayout = false;
            lookaheadAlignmentLines.usedByModifierMeasurement = false;
            lookaheadAlignmentLines.usedByModifierLayout = false;
            lookaheadAlignmentLines.queryOwner = null;
        }
        Function1 function1 = this.onDetach;
        if (function1 != null) {
            function1.invoke(androidComposeView);
        }
        NodeChain nodeChain = this.nodes;
        if (nodeChain.m525hasH91voCI$ui_release(8)) {
            invalidateSemantics$ui_release();
        }
        Modifier.Node node = nodeChain.tail;
        for (Modifier.Node node2 = node; node2 != null; node2 = node2.parent) {
            if (node2.isAttached) {
                node2.runDetachLifecycle$ui_release();
            }
        }
        this.ignoreRemeasureRequests = true;
        MutableVector mutableVector = this._foldedChildren.vector;
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                ((LayoutNode) objArr[i2]).detach$ui_release();
                i2++;
            } while (i2 < i);
        }
        this.ignoreRemeasureRequests = false;
        while (node != null) {
            if (node.isAttached) {
                node.markAsDetached$ui_release();
            }
            node = node.parent;
        }
        MeasureAndLayoutDelegate measureAndLayoutDelegate = androidComposeView.measureAndLayoutDelegate;
        DepthSortedSetsForDifferentPasses depthSortedSetsForDifferentPasses = measureAndLayoutDelegate.relayoutNodes;
        depthSortedSetsForDifferentPasses.lookaheadSet.remove(this);
        depthSortedSetsForDifferentPasses.set.remove(this);
        measureAndLayoutDelegate.onPositionedDispatcher.layoutNodes.remove(this);
        androidComposeView.observationClearRequested = true;
        androidComposeView.rectManager.remove(this);
        this.owner = null;
        setLookaheadRoot(null);
        this.depth = 0;
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate2 = layoutNodeLayoutDelegate.measurePassDelegate;
        measurePassDelegate2.placeOrder = Integer.MAX_VALUE;
        measurePassDelegate2.previousPlaceOrder = Integer.MAX_VALUE;
        measurePassDelegate2.isPlaced = false;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate3 != null) {
            lookaheadPassDelegate3.placeOrder = Integer.MAX_VALUE;
            lookaheadPassDelegate3.previousPlaceOrder = Integer.MAX_VALUE;
            lookaheadPassDelegate3.isPlaced = false;
        }
    }

    public final void draw$ui_release(Canvas canvas, GraphicsLayer graphicsLayer) {
        this.nodes.outerCoordinator.draw(canvas, graphicsLayer);
    }

    public final void forceRemeasure() {
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui_release$default(this, false, 5);
        } else {
            requestRemeasure$ui_release$default(this, false, 5);
        }
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.layoutDelegate.measurePassDelegate;
        Constraints constraints = measurePassDelegate.measuredOnce ? new Constraints(measurePassDelegate.measurementConstraints) : null;
        if (constraints != null) {
            AndroidComposeView androidComposeView = this.owner;
            if (androidComposeView != null) {
                androidComposeView.m556measureAndLayout0kLqBqw(this, constraints.value);
                return;
            }
            return;
        }
        AndroidComposeView androidComposeView2 = this.owner;
        if (androidComposeView2 != null) {
            androidComposeView2.measureAndLayout(true);
        }
    }

    public final List getChildLookaheadMeasurables$ui_release() {
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        Intrinsics.checkNotNull(lookaheadPassDelegate);
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = LayoutNodeLayoutDelegate.this;
        layoutNodeLayoutDelegate.layoutNode.getChildren$ui_release();
        boolean z = lookaheadPassDelegate.childDelegatesDirty;
        MutableVector mutableVector = lookaheadPassDelegate._childDelegates;
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
                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate2 = layoutNode2.layoutDelegate.lookaheadPassDelegate;
                    Intrinsics.checkNotNull(lookaheadPassDelegate2);
                    mutableVector.add(lookaheadPassDelegate2);
                } else {
                    LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate3 = layoutNode2.layoutDelegate.lookaheadPassDelegate;
                    Intrinsics.checkNotNull(lookaheadPassDelegate3);
                    Object[] objArr2 = mutableVector.content;
                    Object obj = objArr2[i2];
                    objArr2[i2] = lookaheadPassDelegate3;
                }
                i2++;
            } while (i2 < i);
        }
        mutableVector.removeRange(layoutNode.getChildren$ui_release().size(), mutableVector.size);
        lookaheadPassDelegate.childDelegatesDirty = false;
        return mutableVector.asMutableList();
    }

    public final List getChildMeasurables$ui_release() {
        return this.layoutDelegate.measurePassDelegate.getChildDelegates$ui_release();
    }

    public final List getChildren$ui_release() {
        return get_children$ui_release().asMutableList();
    }

    public final SemanticsConfiguration getCollapsedSemantics$ui_release() {
        if (!isAttached() || this.isDeactivated) {
            return null;
        }
        if (!this.nodes.m525hasH91voCI$ui_release(8) || this._collapsedSemantics != null) {
            return this._collapsedSemantics;
        }
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = new SemanticsConfiguration();
        OwnerSnapshotObserver ownerSnapshotObserver = ((AndroidComposeView) LayoutNodeKt.requireOwner(this)).snapshotObserver;
        ownerSnapshotObserver.observeReads$ui_release(this, ownerSnapshotObserver.onCommitAffectingSemantics, new Function0() { // from class: androidx.compose.ui.node.LayoutNode$collapsedSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v0 */
            /* JADX WARN: Type inference failed for: r2v1, types: [androidx.compose.ui.Modifier$Node] */
            /* JADX WARN: Type inference failed for: r2v10 */
            /* JADX WARN: Type inference failed for: r2v11 */
            /* JADX WARN: Type inference failed for: r2v3 */
            /* JADX WARN: Type inference failed for: r2v4, types: [androidx.compose.ui.Modifier$Node] */
            /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r2v6 */
            /* JADX WARN: Type inference failed for: r2v7 */
            /* JADX WARN: Type inference failed for: r2v8 */
            /* JADX WARN: Type inference failed for: r2v9 */
            /* JADX WARN: Type inference failed for: r3v0 */
            /* JADX WARN: Type inference failed for: r3v1 */
            /* JADX WARN: Type inference failed for: r3v10 */
            /* JADX WARN: Type inference failed for: r3v11 */
            /* JADX WARN: Type inference failed for: r3v2 */
            /* JADX WARN: Type inference failed for: r3v3, types: [androidx.compose.runtime.collection.MutableVector] */
            /* JADX WARN: Type inference failed for: r3v4 */
            /* JADX WARN: Type inference failed for: r3v5 */
            /* JADX WARN: Type inference failed for: r3v6, types: [androidx.compose.runtime.collection.MutableVector] */
            /* JADX WARN: Type inference failed for: r3v8 */
            /* JADX WARN: Type inference failed for: r3v9 */
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                NodeChain nodeChain = LayoutNode.this.nodes;
                Ref$ObjectRef ref$ObjectRef2 = ref$ObjectRef;
                if ((nodeChain.head.aggregateChildKindSet & 8) != 0) {
                    for (Modifier.Node node = nodeChain.tail; node != null; node = node.parent) {
                        if ((node.kindSet & 8) != 0) {
                            DelegatingNode delegatingNode = node;
                            ?? r3 = 0;
                            while (delegatingNode != 0) {
                                if (delegatingNode instanceof SemanticsModifierNode) {
                                    SemanticsModifierNode semanticsModifierNode = (SemanticsModifierNode) delegatingNode;
                                    if (semanticsModifierNode.getShouldClearDescendantSemantics()) {
                                        SemanticsConfiguration semanticsConfiguration = new SemanticsConfiguration();
                                        ref$ObjectRef2.element = semanticsConfiguration;
                                        semanticsConfiguration.isClearingSemantics = true;
                                    }
                                    if (semanticsModifierNode.getShouldMergeDescendantSemantics()) {
                                        ((SemanticsConfiguration) ref$ObjectRef2.element).isMergingSemanticsOfDescendants = true;
                                    }
                                    semanticsModifierNode.applySemantics((SemanticsConfiguration) ref$ObjectRef2.element);
                                } else if ((delegatingNode.kindSet & 8) != 0 && (delegatingNode instanceof DelegatingNode)) {
                                    Modifier.Node node2 = delegatingNode.delegate;
                                    int i = 0;
                                    delegatingNode = delegatingNode;
                                    r3 = r3;
                                    while (node2 != null) {
                                        if ((node2.kindSet & 8) != 0) {
                                            i++;
                                            r3 = r3;
                                            if (i == 1) {
                                                delegatingNode = node2;
                                            } else {
                                                if (r3 == 0) {
                                                    r3 = new MutableVector(new Modifier.Node[16]);
                                                }
                                                if (delegatingNode != 0) {
                                                    r3.add(delegatingNode);
                                                    delegatingNode = 0;
                                                }
                                                r3.add(node2);
                                            }
                                        }
                                        node2 = node2.child;
                                        delegatingNode = delegatingNode;
                                        r3 = r3;
                                    }
                                    if (i == 1) {
                                    }
                                }
                                delegatingNode = DelegatableNodeKt.access$pop(r3);
                            }
                        }
                    }
                }
                return Unit.INSTANCE;
            }
        });
        SemanticsConfiguration semanticsConfiguration = (SemanticsConfiguration) ref$ObjectRef.element;
        this._collapsedSemantics = semanticsConfiguration;
        return semanticsConfiguration;
    }

    public final List getFoldedChildren$ui_release() {
        return this._foldedChildren.vector.asMutableList();
    }

    public final UsageByParent getMeasuredByParentInLookahead$ui_release() {
        UsageByParent usageByParent;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        return (lookaheadPassDelegate == null || (usageByParent = lookaheadPassDelegate.measuredByParent) == null) ? UsageByParent.NotUsed : usageByParent;
    }

    public final IntrinsicsPolicy getOrCreateIntrinsicsPolicy() {
        IntrinsicsPolicy intrinsicsPolicy = this.intrinsicsPolicy;
        if (intrinsicsPolicy != null) {
            return intrinsicsPolicy;
        }
        IntrinsicsPolicy intrinsicsPolicy2 = new IntrinsicsPolicy(this, this.measurePolicy);
        this.intrinsicsPolicy = intrinsicsPolicy2;
        return intrinsicsPolicy2;
    }

    public final LayoutNode getParent$ui_release() {
        LayoutNode layoutNode = this._foldedParent;
        while (layoutNode != null && layoutNode.isVirtual) {
            layoutNode = layoutNode._foldedParent;
        }
        return layoutNode;
    }

    public final int getPlaceOrder$ui_release() {
        return this.layoutDelegate.measurePassDelegate.placeOrder;
    }

    public final MutableVector getZSortedChildren() {
        boolean z = this.zSortedChildrenInvalidated;
        MutableVector mutableVector = this._zSortedChildren;
        if (z) {
            mutableVector.clear();
            mutableVector.addAll(mutableVector.size, get_children$ui_release());
            mutableVector.sortWith(ZComparator);
            this.zSortedChildrenInvalidated = false;
        }
        return mutableVector;
    }

    public final MutableVector get_children$ui_release() {
        updateChildrenIfDirty$ui_release();
        if (this.virtualChildrenCount == 0) {
            return this._foldedChildren.vector;
        }
        MutableVector mutableVector = this._unfoldedChildren;
        Intrinsics.checkNotNull(mutableVector);
        return mutableVector;
    }

    /* renamed from: hitTest-M_7yMNQ$ui_release, reason: not valid java name */
    public final void m508hitTestM_7yMNQ$ui_release(long j, HitTestResult hitTestResult, boolean z, boolean z2) {
        NodeChain nodeChain = this.nodes;
        NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator;
        Function1 function1 = NodeCoordinator.onCommitAffectingLayerParams;
        nodeChain.outerCoordinator.m534hitTestYqVAtuI(NodeCoordinator.PointerInputSource, nodeCoordinator.m529fromParentPosition8S9VItk(j), hitTestResult, z, z2);
    }

    public final void insertAt$ui_release(int i, LayoutNode layoutNode) {
        if (!(layoutNode._foldedParent == null)) {
            StringBuilder sb = new StringBuilder("Cannot insert ");
            sb.append(layoutNode);
            sb.append(" because it already has a parent. This tree: ");
            sb.append(debugTreeToString(0));
            sb.append(" Other tree: ");
            LayoutNode layoutNode2 = layoutNode._foldedParent;
            sb.append(layoutNode2 != null ? layoutNode2.debugTreeToString(0) : null);
            InlineClassHelperKt.throwIllegalStateException(sb.toString());
        }
        if (layoutNode.owner != null) {
            InlineClassHelperKt.throwIllegalStateException("Cannot insert " + layoutNode + " because it already has an owner. This tree: " + debugTreeToString(0) + " Other tree: " + layoutNode.debugTreeToString(0));
        }
        layoutNode._foldedParent = this;
        MutableVectorWithMutationTracking mutableVectorWithMutationTracking = this._foldedChildren;
        mutableVectorWithMutationTracking.vector.add(i, layoutNode);
        ((LayoutNode$_foldedChildren$1) mutableVectorWithMutationTracking.onVectorMutated).invoke();
        onZSortedChildrenInvalidated$ui_release();
        if (layoutNode.isVirtual) {
            this.virtualChildrenCount++;
        }
        invalidateUnfoldedVirtualChildren();
        AndroidComposeView androidComposeView = this.owner;
        if (androidComposeView != null) {
            layoutNode.attach$ui_release(androidComposeView);
        }
        if (layoutNode.layoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            layoutNodeLayoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(layoutNodeLayoutDelegate.childrenAccessingCoordinatesDuringPlacement + 1);
        }
    }

    public final void invalidateLayer$ui_release() {
        if (this.innerLayerCoordinatorIsDirty) {
            NodeChain nodeChain = this.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator;
            NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator.wrappedBy;
            this._innerLayerCoordinator = null;
            while (true) {
                if (Intrinsics.areEqual(nodeCoordinator, nodeCoordinator2)) {
                    break;
                }
                if ((nodeCoordinator != null ? nodeCoordinator.layer : null) != null) {
                    this._innerLayerCoordinator = nodeCoordinator;
                    break;
                }
                nodeCoordinator = nodeCoordinator != null ? nodeCoordinator.wrappedBy : null;
            }
        }
        NodeCoordinator nodeCoordinator3 = this._innerLayerCoordinator;
        if (nodeCoordinator3 != null && nodeCoordinator3.layer == null) {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("layer was not set");
            throw null;
        }
        if (nodeCoordinator3 != null) {
            nodeCoordinator3.invalidateLayer();
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
    }

    public final void invalidateLayers$ui_release() {
        NodeChain nodeChain = this.nodes;
        InnerNodeCoordinator innerNodeCoordinator = nodeChain.innerCoordinator;
        for (NodeCoordinator nodeCoordinator = nodeChain.outerCoordinator; nodeCoordinator != innerNodeCoordinator; nodeCoordinator = nodeCoordinator.wrapped) {
            OwnedLayer ownedLayer = ((LayoutModifierNodeCoordinator) nodeCoordinator).layer;
            if (ownedLayer != null) {
                ownedLayer.invalidate();
            }
        }
        OwnedLayer ownedLayer2 = nodeChain.innerCoordinator.layer;
        if (ownedLayer2 != null) {
            ownedLayer2.invalidate();
        }
    }

    public final void invalidateMeasurements$ui_release() {
        this.outerToInnerOffsetDirty = true;
        if (this.lookaheadRoot != null) {
            requestLookaheadRemeasure$ui_release$default(this, false, 7);
        } else {
            requestRemeasure$ui_release$default(this, false, 7);
        }
    }

    public final void invalidateSemantics$ui_release() {
        this._collapsedSemantics = null;
        ((AndroidComposeView) LayoutNodeKt.requireOwner(this)).onSemanticsChange();
    }

    public final void invalidateUnfoldedVirtualChildren() {
        LayoutNode layoutNode;
        if (this.virtualChildrenCount > 0) {
            this.unfoldedVirtualChildrenListDirty = true;
        }
        if (!this.isVirtual || (layoutNode = this._foldedParent) == null) {
            return;
        }
        layoutNode.invalidateUnfoldedVirtualChildren();
    }

    public final boolean isAttached() {
        return this.owner != null;
    }

    public final boolean isPlaced() {
        return this.layoutDelegate.measurePassDelegate.isPlaced;
    }

    public final Boolean isPlacedInLookahead() {
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            return Boolean.valueOf(lookaheadPassDelegate.isPlaced);
        }
        return null;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return isAttached();
    }

    public final void lookaheadReplace$ui_release() {
        LayoutNode parent$ui_release;
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = this.layoutDelegate.lookaheadPassDelegate;
        Intrinsics.checkNotNull(lookaheadPassDelegate);
        try {
            lookaheadPassDelegate.relayoutWithoutParentInProgress = true;
            if (!lookaheadPassDelegate.placedOnce) {
                InlineClassHelperKt.throwIllegalStateException("replace() called on item that was not placed");
            }
            lookaheadPassDelegate.onNodePlacedCalled = false;
            boolean z = lookaheadPassDelegate.isPlaced;
            lookaheadPassDelegate.m512placeSelfMLgxB_4$1(lookaheadPassDelegate.lastPosition, lookaheadPassDelegate.lastLayerBlock, lookaheadPassDelegate.lastExplicitLayer);
            if (z && !lookaheadPassDelegate.onNodePlacedCalled && (parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release()) != null) {
                parent$ui_release.requestLookaheadRelayout$ui_release(false);
            }
            lookaheadPassDelegate.relayoutWithoutParentInProgress = false;
        } catch (Throwable th) {
            lookaheadPassDelegate.relayoutWithoutParentInProgress = false;
            throw th;
        }
    }

    public final void move$ui_release(int i, int i2, int i3) {
        if (i == i2) {
            return;
        }
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = i > i2 ? i + i4 : i;
            int i6 = i > i2 ? i2 + i4 : (i2 + i3) - 2;
            MutableVectorWithMutationTracking mutableVectorWithMutationTracking = this._foldedChildren;
            Object removeAt = mutableVectorWithMutationTracking.vector.removeAt(i5);
            Function0 function0 = mutableVectorWithMutationTracking.onVectorMutated;
            ((LayoutNode$_foldedChildren$1) function0).invoke();
            mutableVectorWithMutationTracking.vector.add(i6, (LayoutNode) removeAt);
            ((LayoutNode$_foldedChildren$1) function0).invoke();
        }
        onZSortedChildrenInvalidated$ui_release();
        invalidateUnfoldedVirtualChildren();
        invalidateMeasurements$ui_release();
    }

    public final void onChildRemoved(LayoutNode layoutNode) {
        if (layoutNode.layoutDelegate.childrenAccessingCoordinatesDuringPlacement > 0) {
            this.layoutDelegate.setChildrenAccessingCoordinatesDuringPlacement(r0.childrenAccessingCoordinatesDuringPlacement - 1);
        }
        if (this.owner != null) {
            layoutNode.detach$ui_release();
        }
        layoutNode._foldedParent = null;
        layoutNode.nodes.outerCoordinator.wrappedBy = null;
        if (layoutNode.isVirtual) {
            this.virtualChildrenCount--;
            MutableVector mutableVector = layoutNode._foldedChildren.vector;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    ((LayoutNode) objArr[i2]).nodes.outerCoordinator.wrappedBy = null;
                    i2++;
                } while (i2 < i);
            }
        }
        invalidateUnfoldedVirtualChildren();
        onZSortedChildrenInvalidated$ui_release();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onDeactivate() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onDeactivate();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.markActiveNodesAsReused(true);
        }
        this.isDeactivated = true;
        NodeChain nodeChain = this.nodes;
        for (Modifier.Node node = nodeChain.tail; node != null; node = node.parent) {
            if (node.isAttached) {
                node.reset$ui_release();
            }
        }
        Modifier.Node node2 = nodeChain.tail;
        for (Modifier.Node node3 = node2; node3 != null; node3 = node3.parent) {
            if (node3.isAttached) {
                node3.runDetachLifecycle$ui_release();
            }
        }
        while (node2 != null) {
            if (node2.isAttached) {
                node2.markAsDetached$ui_release();
            }
            node2 = node2.parent;
        }
        if (isAttached()) {
            invalidateSemantics$ui_release();
        }
        AndroidComposeView androidComposeView = this.owner;
        if (androidComposeView != null) {
            androidComposeView.rectManager.remove(this);
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onRelease() {
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onRelease();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.onRelease();
        }
        NodeChain nodeChain = this.nodes;
        NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
        for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !Intrinsics.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
            nodeCoordinator2.released = true;
            ((NodeCoordinator$invalidateParentLayer$1) nodeCoordinator2.invalidateParentLayer).invoke();
            if (nodeCoordinator2.layer != null) {
                if (nodeCoordinator2.explicitLayer != null) {
                    nodeCoordinator2.explicitLayer = null;
                }
                nodeCoordinator2.updateLayerBlock(null, false);
                nodeCoordinator2.layoutNode.requestRelayout$ui_release(false);
            }
        }
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onReuse() {
        if (!isAttached()) {
            InlineClassHelperKt.throwIllegalArgumentException("onReuse is only expected on attached node");
        }
        AndroidViewHolder androidViewHolder = this.interopViewFactoryHolder;
        if (androidViewHolder != null) {
            androidViewHolder.onReuse();
        }
        LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = this.subcompositionsState;
        if (layoutNodeSubcompositionsState != null) {
            layoutNodeSubcompositionsState.markActiveNodesAsReused(false);
        }
        boolean z = this.isDeactivated;
        NodeChain nodeChain = this.nodes;
        if (z) {
            this.isDeactivated = false;
            invalidateSemantics$ui_release();
        } else {
            for (Modifier.Node node = nodeChain.tail; node != null; node = node.parent) {
                if (node.isAttached) {
                    node.reset$ui_release();
                }
            }
            Modifier.Node node2 = nodeChain.tail;
            for (Modifier.Node node3 = node2; node3 != null; node3 = node3.parent) {
                if (node3.isAttached) {
                    node3.runDetachLifecycle$ui_release();
                }
            }
            while (node2 != null) {
                if (node2.isAttached) {
                    node2.markAsDetached$ui_release();
                }
                node2 = node2.parent;
            }
        }
        this.semanticsId = SemanticsModifierKt.lastIdentifier.addAndGet(1);
        for (Modifier.Node node4 = nodeChain.head; node4 != null; node4 = node4.child) {
            node4.markAsAttached$ui_release();
        }
        nodeChain.runAttachLifecycle();
        rescheduleRemeasureOrRelayout$ui_release(this);
    }

    public final void onZSortedChildrenInvalidated$ui_release() {
        if (!this.isVirtual) {
            this.zSortedChildrenInvalidated = true;
            return;
        }
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.onZSortedChildrenInvalidated$ui_release();
        }
    }

    public final void removeAll$ui_release() {
        MutableVectorWithMutationTracking mutableVectorWithMutationTracking = this._foldedChildren;
        int i = mutableVectorWithMutationTracking.vector.size;
        while (true) {
            i--;
            if (-1 >= i) {
                mutableVectorWithMutationTracking.vector.clear();
                mutableVectorWithMutationTracking.onVectorMutated.invoke();
                return;
            }
            onChildRemoved((LayoutNode) mutableVectorWithMutationTracking.vector.content[i]);
        }
    }

    public final void removeAt$ui_release(int i, int i2) {
        if (i2 < 0) {
            InlineClassHelperKt.throwIllegalArgumentException("count (" + i2 + ") must be greater than 0");
        }
        int i3 = (i2 + i) - 1;
        if (i > i3) {
            return;
        }
        while (true) {
            MutableVectorWithMutationTracking mutableVectorWithMutationTracking = this._foldedChildren;
            onChildRemoved((LayoutNode) mutableVectorWithMutationTracking.vector.content[i3]);
            Object removeAt = mutableVectorWithMutationTracking.vector.removeAt(i3);
            ((LayoutNode$_foldedChildren$1) mutableVectorWithMutationTracking.onVectorMutated).invoke();
            if (i3 == i) {
                return;
            } else {
                i3--;
            }
        }
    }

    public final void replace$ui_release() {
        LayoutNode parent$ui_release;
        if (this.intrinsicsUsageByParent == UsageByParent.NotUsed) {
            clearSubtreePlacementIntrinsicsUsage();
        }
        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = this.layoutDelegate.measurePassDelegate;
        measurePassDelegate.getClass();
        try {
            measurePassDelegate.relayoutWithoutParentInProgress = true;
            if (!measurePassDelegate.placedOnce) {
                InlineClassHelperKt.throwIllegalStateException("replace called on unplaced item");
            }
            boolean z = measurePassDelegate.isPlaced;
            measurePassDelegate.m514placeOuterCoordinatorMLgxB_4(measurePassDelegate.lastPosition, measurePassDelegate.lastZIndex, measurePassDelegate.lastLayerBlock, measurePassDelegate.lastExplicitLayer);
            if (z && !measurePassDelegate.onNodePlacedCalled && (parent$ui_release = LayoutNodeLayoutDelegate.this.layoutNode.getParent$ui_release()) != null) {
                parent$ui_release.requestRelayout$ui_release(false);
            }
            measurePassDelegate.relayoutWithoutParentInProgress = false;
        } catch (Throwable th) {
            measurePassDelegate.relayoutWithoutParentInProgress = false;
            throw th;
        }
    }

    public final void requestLookaheadRelayout$ui_release(boolean z) {
        AndroidComposeView androidComposeView;
        if (this.isVirtual || (androidComposeView = this.owner) == null) {
            return;
        }
        androidComposeView.onRequestRelayout(this, true, z);
    }

    public final void requestRelayout$ui_release(boolean z) {
        AndroidComposeView androidComposeView;
        this.outerToInnerOffsetDirty = true;
        if (this.isVirtual || (androidComposeView = this.owner) == null) {
            return;
        }
        androidComposeView.onRequestRelayout(this, false, z);
    }

    public final void resetSubtreeIntrinsicsUsage$ui_release() {
        MutableVector mutableVector = get_children$ui_release();
        int i = mutableVector.size;
        if (i > 0) {
            Object[] objArr = mutableVector.content;
            int i2 = 0;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i2];
                UsageByParent usageByParent = layoutNode.previousIntrinsicsUsageByParent;
                layoutNode.intrinsicsUsageByParent = usageByParent;
                if (usageByParent != UsageByParent.NotUsed) {
                    layoutNode.resetSubtreeIntrinsicsUsage$ui_release();
                }
                i2++;
            } while (i2 < i);
        }
    }

    public final void setDensity$1(Density density) {
        if (Intrinsics.areEqual(this.density, density)) {
            return;
        }
        this.density = density;
        invalidateMeasurements$ui_release();
        LayoutNode parent$ui_release = getParent$ui_release();
        if (parent$ui_release != null) {
            parent$ui_release.invalidateLayer$ui_release();
        }
        invalidateLayers$ui_release();
        for (Modifier.Node node = this.nodes.head; node != null; node = node.child) {
            if ((node.kindSet & 16) != 0) {
                ((PointerInputModifierNode) node).onDensityChange();
            } else if (node instanceof CacheDrawModifierNode) {
                ((CacheDrawModifierNode) node).invalidateDrawCache();
            }
        }
    }

    public final void setLookaheadRoot(LayoutNode layoutNode) {
        if (Intrinsics.areEqual(layoutNode, this.lookaheadRoot)) {
            return;
        }
        this.lookaheadRoot = layoutNode;
        if (layoutNode != null) {
            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
            if (layoutNodeLayoutDelegate.lookaheadPassDelegate == null) {
                layoutNodeLayoutDelegate.lookaheadPassDelegate = layoutNodeLayoutDelegate.new LookaheadPassDelegate();
            }
            NodeChain nodeChain = this.nodes;
            NodeCoordinator nodeCoordinator = nodeChain.innerCoordinator.wrapped;
            for (NodeCoordinator nodeCoordinator2 = nodeChain.outerCoordinator; !Intrinsics.areEqual(nodeCoordinator2, nodeCoordinator) && nodeCoordinator2 != null; nodeCoordinator2 = nodeCoordinator2.wrapped) {
                nodeCoordinator2.ensureLookaheadDelegateCreated();
            }
        }
        invalidateMeasurements$ui_release();
    }

    public final void setMeasurePolicy(MeasurePolicy measurePolicy) {
        if (Intrinsics.areEqual(this.measurePolicy, measurePolicy)) {
            return;
        }
        this.measurePolicy = measurePolicy;
        IntrinsicsPolicy intrinsicsPolicy = this.intrinsicsPolicy;
        if (intrinsicsPolicy != null) {
            ((SnapshotMutableStateImpl) intrinsicsPolicy.measurePolicyState$delegate).setValue(measurePolicy);
        }
        invalidateMeasurements$ui_release();
    }

    public final void setModifier(Modifier modifier) {
        if (this.isVirtual && this._modifier != Modifier.Companion.$$INSTANCE) {
            InlineClassHelperKt.throwIllegalArgumentException("Modifiers are not supported on virtual LayoutNodes");
        }
        if (this.isDeactivated) {
            InlineClassHelperKt.throwIllegalArgumentException("modifier is updated when deactivated");
        }
        if (isAttached()) {
            applyModifier(modifier);
        } else {
            this.pendingModifier = modifier;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.runtime.collection.MutableVector] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public final void setViewConfiguration(ViewConfiguration viewConfiguration) {
        if (Intrinsics.areEqual(this.viewConfiguration, viewConfiguration)) {
            return;
        }
        this.viewConfiguration = viewConfiguration;
        Modifier.Node node = this.nodes.head;
        if ((node.aggregateChildKindSet & 16) != 0) {
            while (node != null) {
                if ((node.kindSet & 16) != 0) {
                    DelegatingNode delegatingNode = node;
                    ?? r2 = 0;
                    while (delegatingNode != 0) {
                        if (delegatingNode instanceof PointerInputModifierNode) {
                            ((PointerInputModifierNode) delegatingNode).onViewConfigurationChange();
                        } else if ((delegatingNode.kindSet & 16) != 0 && (delegatingNode instanceof DelegatingNode)) {
                            Modifier.Node node2 = delegatingNode.delegate;
                            int i = 0;
                            delegatingNode = delegatingNode;
                            r2 = r2;
                            while (node2 != null) {
                                if ((node2.kindSet & 16) != 0) {
                                    i++;
                                    r2 = r2;
                                    if (i == 1) {
                                        delegatingNode = node2;
                                    } else {
                                        if (r2 == 0) {
                                            r2 = new MutableVector(new Modifier.Node[16]);
                                        }
                                        if (delegatingNode != 0) {
                                            r2.add(delegatingNode);
                                            delegatingNode = 0;
                                        }
                                        r2.add(node2);
                                    }
                                }
                                node2 = node2.child;
                                delegatingNode = delegatingNode;
                                r2 = r2;
                            }
                            if (i == 1) {
                            }
                        }
                        delegatingNode = DelegatableNodeKt.access$pop(r2);
                    }
                }
                if ((node.aggregateChildKindSet & 16) == 0) {
                    return;
                } else {
                    node = node.child;
                }
            }
        }
    }

    public final String toString() {
        return JvmActuals_jvmKt.simpleIdentityToString(this) + " children: " + getChildren$ui_release().size() + " measurePolicy: " + this.measurePolicy;
    }

    public final void updateChildrenIfDirty$ui_release() {
        if (this.virtualChildrenCount <= 0 || !this.unfoldedVirtualChildrenListDirty) {
            return;
        }
        int i = 0;
        this.unfoldedVirtualChildrenListDirty = false;
        MutableVector mutableVector = this._unfoldedChildren;
        if (mutableVector == null) {
            mutableVector = new MutableVector(new LayoutNode[16]);
            this._unfoldedChildren = mutableVector;
        }
        mutableVector.clear();
        MutableVector mutableVector2 = this._foldedChildren.vector;
        int i2 = mutableVector2.size;
        if (i2 > 0) {
            Object[] objArr = mutableVector2.content;
            do {
                LayoutNode layoutNode = (LayoutNode) objArr[i];
                if (layoutNode.isVirtual) {
                    mutableVector.addAll(mutableVector.size, layoutNode.get_children$ui_release());
                } else {
                    mutableVector.add(layoutNode);
                }
                i++;
            } while (i < i2);
        }
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.layoutDelegate;
        layoutNodeLayoutDelegate.measurePassDelegate.childDelegatesDirty = true;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.childDelegatesDirty = true;
        }
    }

    public LayoutNode(int i, boolean z) {
        this.isVirtual = z;
        this.semanticsId = i;
        this.offsetFromRoot = 9223372034707292159L;
        this.lastSize = 0L;
        this.outerToInnerOffset = 9223372034707292159L;
        this.outerToInnerOffsetDirty = true;
        this._foldedChildren = new MutableVectorWithMutationTracking(new MutableVector(new LayoutNode[16]), new LayoutNode$_foldedChildren$1(this));
        this._zSortedChildren = new MutableVector(new LayoutNode[16]);
        this.zSortedChildrenInvalidated = true;
        this.measurePolicy = ErrorMeasurePolicy;
        this.density = LayoutNodeKt.DefaultDensity;
        this.layoutDirection = LayoutDirection.Ltr;
        this.viewConfiguration = DummyViewConfiguration;
        CompositionLocalMap.Companion.getClass();
        this.compositionLocalMap = CompositionLocalMap.Companion.Empty;
        UsageByParent usageByParent = UsageByParent.NotUsed;
        this.intrinsicsUsageByParent = usageByParent;
        this.previousIntrinsicsUsageByParent = usageByParent;
        this.nodes = new NodeChain(this);
        this.layoutDelegate = new LayoutNodeLayoutDelegate(this);
        this.innerLayerCoordinatorIsDirty = true;
        this._modifier = Modifier.Companion.$$INSTANCE;
    }
}
