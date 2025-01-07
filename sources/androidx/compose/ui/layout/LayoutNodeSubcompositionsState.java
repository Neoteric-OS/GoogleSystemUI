package androidx.compose.ui.layout;

import android.view.ViewGroup;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerImpl;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionImpl;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.OpaqueKey;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.ReusableComposition;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutNodeSubcompositionsState;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.layout.SubcomposeSlotReusePolicy;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.LayoutNodeKt;
import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import androidx.compose.ui.node.LookaheadDelegate;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.Wrapper_androidKt;
import androidx.compose.ui.unit.LayoutDirection;
import com.android.systemui.plugins.DarkIconDispatcher;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LayoutNodeSubcompositionsState implements ComposeNodeLifecycleCallback {
    public CompositionContext compositionContext;
    public int currentIndex;
    public int currentPostLookaheadIndex;
    public int precomposedCount;
    public int reusableCount;
    public final LayoutNode root;
    public SubcomposeSlotReusePolicy slotReusePolicy;
    public final HashMap nodeToNodeState = new HashMap();
    public final HashMap slotIdToNode = new HashMap();
    public final Scope scope = new Scope();
    public final PostLookaheadMeasureScopeImpl postLookaheadMeasureScope = new PostLookaheadMeasureScopeImpl();
    public final HashMap precomposeMap = new HashMap();
    public final SubcomposeSlotReusePolicy.SlotIdsSet reusableSlotIdsSet = new SubcomposeSlotReusePolicy.SlotIdsSet();
    public final Map postLookaheadPrecomposeSlotHandleMap = new LinkedHashMap();
    public final MutableVector postLookaheadComposedSlotIds = new MutableVector(new Object[16]);
    public final String NoIntrinsicsMessage = "Asking for intrinsic measurements of SubcomposeLayout layouts is not supported. This includes components that are built on top of SubcomposeLayout, such as lazy lists, BoxWithConstraints, TabRow, etc. To mitigate this:\n- if intrinsic measurements are used to achieve 'match parent' sizing, consider replacing the parent of the component with a custom layout which controls the order in which children are measured, making intrinsic measurement not needed\n- adding a size modifier to the component, in order to fast return the queried intrinsic measurement.";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class NodeState {
        public MutableState activeState;
        public ReusableComposition composition;
        public Function2 content;
        public boolean forceRecompose;
        public boolean forceReuse;
        public Object slotId;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class PostLookaheadMeasureScopeImpl implements SubcomposeMeasureScope, MeasureScope {
        public final /* synthetic */ Scope $$delegate_0;

        public PostLookaheadMeasureScopeImpl() {
            this.$$delegate_0 = LayoutNodeSubcompositionsState.this.scope;
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getDensity() {
            return this.$$delegate_0.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public final float getFontScale() {
            return this.$$delegate_0.fontScale;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public final LayoutDirection getLayoutDirection() {
            return this.$$delegate_0.layoutDirection;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public final boolean isLookingAhead() {
            return this.$$delegate_0.isLookingAhead();
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public final MeasureResult layout(int i, int i2, Map map, Function1 function1) {
            return this.$$delegate_0.layout(i, i2, map, function1);
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public final MeasureResult layout$1(int i, int i2, Map map, Function1 function1) {
            return this.$$delegate_0.layout(i, i2, map, function1);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: roundToPx-0680j_4 */
        public final int mo45roundToPx0680j_4(float f) {
            return this.$$delegate_0.mo45roundToPx0680j_4(f);
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public final List subcompose(Object obj, Function2 function2) {
            LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            LayoutNode layoutNode = (LayoutNode) layoutNodeSubcompositionsState.slotIdToNode.get(obj);
            List childMeasurables$ui_release = layoutNode != null ? layoutNode.getChildMeasurables$ui_release() : null;
            if (childMeasurables$ui_release != null) {
                return childMeasurables$ui_release;
            }
            MutableVector mutableVector = layoutNodeSubcompositionsState.postLookaheadComposedSlotIds;
            int i = mutableVector.size;
            int i2 = layoutNodeSubcompositionsState.currentPostLookaheadIndex;
            if (i < i2) {
                throw new IllegalArgumentException("Error: currentPostLookaheadIndex cannot be greater than the size of thepostLookaheadComposedSlotIds list.");
            }
            if (i == i2) {
                mutableVector.add(obj);
            } else {
                Object[] objArr = mutableVector.content;
                Object obj2 = objArr[i2];
                objArr[i2] = obj;
            }
            layoutNodeSubcompositionsState.currentPostLookaheadIndex++;
            if (!layoutNodeSubcompositionsState.precomposeMap.containsKey(obj)) {
                layoutNodeSubcompositionsState.postLookaheadPrecomposeSlotHandleMap.put(obj, layoutNodeSubcompositionsState.precompose(obj, function2));
                LayoutNode layoutNode2 = layoutNodeSubcompositionsState.root;
                if (layoutNode2.layoutDelegate.layoutState == LayoutNode.LayoutState.LayingOut) {
                    layoutNode2.requestLookaheadRelayout$ui_release(true);
                } else {
                    LayoutNode.requestLookaheadRemeasure$ui_release$default(layoutNode2, true, 6);
                }
            }
            LayoutNode layoutNode3 = (LayoutNode) layoutNodeSubcompositionsState.precomposeMap.get(obj);
            if (layoutNode3 == null) {
                return EmptyList.INSTANCE;
            }
            List childDelegates$ui_release = layoutNode3.layoutDelegate.measurePassDelegate.getChildDelegates$ui_release();
            int size = childDelegates$ui_release.size();
            for (int i3 = 0; i3 < size; i3++) {
                LayoutNodeLayoutDelegate.this.detachedFromParentLookaheadPass = true;
            }
            return childDelegates$ui_release;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* renamed from: toDp-GaN1DYA */
        public final float mo46toDpGaN1DYA(long j) {
            return this.$$delegate_0.mo46toDpGaN1DYA(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toDp-u2uoSUM */
        public final float mo48toDpu2uoSUM(int i) {
            return this.$$delegate_0.mo48toDpu2uoSUM(i);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toDpSize-k-rfVVM */
        public final long mo49toDpSizekrfVVM(long j) {
            return this.$$delegate_0.mo49toDpSizekrfVVM(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx--R2X_6o */
        public final float mo50toPxR2X_6o(long j) {
            return this.$$delegate_0.mo50toPxR2X_6o(j);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toPx-0680j_4 */
        public final float mo51toPx0680j_4(float f) {
            return this.$$delegate_0.getDensity() * f;
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toSize-XkaWNTQ */
        public final long mo52toSizeXkaWNTQ(long j) {
            return this.$$delegate_0.mo52toSizeXkaWNTQ(j);
        }

        @Override // androidx.compose.ui.unit.FontScaling
        /* renamed from: toSp-0xMU5do */
        public final long mo53toSp0xMU5do(float f) {
            return this.$$delegate_0.mo53toSp0xMU5do(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toSp-kPz2Gy4 */
        public final long mo54toSpkPz2Gy4(float f) {
            return this.$$delegate_0.mo54toSpkPz2Gy4(f);
        }

        @Override // androidx.compose.ui.unit.Density
        /* renamed from: toDp-u2uoSUM */
        public final float mo47toDpu2uoSUM(float f) {
            return f / this.$$delegate_0.getDensity();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class Scope implements SubcomposeMeasureScope {
        public float density;
        public float fontScale;
        public LayoutDirection layoutDirection = LayoutDirection.Rtl;

        public Scope() {
        }

        @Override // androidx.compose.ui.unit.Density
        public final float getDensity() {
            return this.density;
        }

        @Override // androidx.compose.ui.unit.FontScaling
        public final float getFontScale() {
            return this.fontScale;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        @Override // androidx.compose.ui.layout.IntrinsicMeasureScope
        public final boolean isLookingAhead() {
            LayoutNode.LayoutState layoutState = LayoutNodeSubcompositionsState.this.root.layoutDelegate.layoutState;
            return layoutState == LayoutNode.LayoutState.LookaheadLayingOut || layoutState == LayoutNode.LayoutState.LookaheadMeasuring;
        }

        @Override // androidx.compose.ui.layout.MeasureScope
        public final MeasureResult layout(final int i, final int i2, final Map map, final Function1 function1) {
            if ((i & DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT) != 0 || ((-16777216) & i2) != 0) {
                InlineClassHelperKt.throwIllegalStateException("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.");
            }
            final LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            return new MeasureResult() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$Scope$layout$1
                @Override // androidx.compose.ui.layout.MeasureResult
                public final Map getAlignmentLines() {
                    return map;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public final int getHeight() {
                    return i2;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public final Function1 getRulers() {
                    return null;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public final int getWidth() {
                    return i;
                }

                @Override // androidx.compose.ui.layout.MeasureResult
                public final void placeChildren() {
                    LookaheadDelegate lookaheadDelegate;
                    boolean isLookingAhead = this.isLookingAhead();
                    Function1 function12 = function1;
                    LayoutNodeSubcompositionsState layoutNodeSubcompositionsState2 = layoutNodeSubcompositionsState;
                    if (!isLookingAhead || (lookaheadDelegate = layoutNodeSubcompositionsState2.root.nodes.innerCoordinator.lookaheadDelegate) == null) {
                        function12.invoke(layoutNodeSubcompositionsState2.root.nodes.innerCoordinator.placementScope);
                    } else {
                        function12.invoke(lookaheadDelegate.placementScope);
                    }
                }
            };
        }

        @Override // androidx.compose.ui.layout.SubcomposeMeasureScope
        public final List subcompose(Object obj, Function2 function2) {
            LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
            layoutNodeSubcompositionsState.makeSureStateIsConsistent();
            LayoutNode layoutNode = layoutNodeSubcompositionsState.root;
            LayoutNode.LayoutState layoutState = layoutNode.layoutDelegate.layoutState;
            LayoutNode.LayoutState layoutState2 = LayoutNode.LayoutState.Measuring;
            LayoutNode.LayoutState layoutState3 = LayoutNode.LayoutState.LayingOut;
            if (layoutState != layoutState2 && layoutState != layoutState3 && layoutState != LayoutNode.LayoutState.LookaheadMeasuring && layoutState != LayoutNode.LayoutState.LookaheadLayingOut) {
                InlineClassHelperKt.throwIllegalStateException("subcompose can only be used inside the measure or layout blocks");
            }
            HashMap hashMap = layoutNodeSubcompositionsState.slotIdToNode;
            Object obj2 = hashMap.get(obj);
            if (obj2 == null) {
                obj2 = (LayoutNode) layoutNodeSubcompositionsState.precomposeMap.remove(obj);
                if (obj2 != null) {
                    if (layoutNodeSubcompositionsState.precomposedCount <= 0) {
                        InlineClassHelperKt.throwIllegalStateException("Check failed.");
                    }
                    layoutNodeSubcompositionsState.precomposedCount--;
                } else {
                    obj2 = layoutNodeSubcompositionsState.takeNodeFromReusables(obj);
                    if (obj2 == null) {
                        int i = layoutNodeSubcompositionsState.currentIndex;
                        LayoutNode layoutNode2 = new LayoutNode(2);
                        layoutNode.ignoreRemeasureRequests = true;
                        layoutNode.insertAt$ui_release(i, layoutNode2);
                        layoutNode.ignoreRemeasureRequests = false;
                        obj2 = layoutNode2;
                    }
                }
                hashMap.put(obj, obj2);
            }
            LayoutNode layoutNode3 = (LayoutNode) obj2;
            if (CollectionsKt.getOrNull(layoutNodeSubcompositionsState.currentIndex, layoutNode.getFoldedChildren$ui_release()) != layoutNode3) {
                int indexOf = layoutNode.getFoldedChildren$ui_release().indexOf(layoutNode3);
                int i2 = layoutNodeSubcompositionsState.currentIndex;
                if (indexOf < i2) {
                    throw new IllegalArgumentException(("Key \"" + obj + "\" was already used. If you are using LazyColumn/Row please make sure you provide a unique key for each item.").toString());
                }
                if (i2 != indexOf) {
                    layoutNode.ignoreRemeasureRequests = true;
                    layoutNode.move$ui_release(indexOf, i2, 1);
                    layoutNode.ignoreRemeasureRequests = false;
                }
            }
            layoutNodeSubcompositionsState.currentIndex++;
            layoutNodeSubcompositionsState.subcompose(layoutNode3, obj, function2);
            return (layoutState == layoutState2 || layoutState == layoutState3) ? layoutNode3.getChildMeasurables$ui_release() : layoutNode3.getChildLookaheadMeasurables$ui_release();
        }
    }

    public LayoutNodeSubcompositionsState(LayoutNode layoutNode, SubcomposeSlotReusePolicy subcomposeSlotReusePolicy) {
        this.root = layoutNode;
        this.slotReusePolicy = subcomposeSlotReusePolicy;
    }

    public static ReusableComposition subcomposeInto(ReusableComposition reusableComposition, LayoutNode layoutNode, boolean z, CompositionContext compositionContext, ComposableLambdaImpl composableLambdaImpl) {
        if (reusableComposition == null || ((CompositionImpl) reusableComposition).disposed) {
            ViewGroup.LayoutParams layoutParams = Wrapper_androidKt.DefaultLayoutParams;
            reusableComposition = new CompositionImpl(compositionContext, new UiApplier(layoutNode));
        }
        if (z) {
            CompositionImpl compositionImpl = (CompositionImpl) reusableComposition;
            ComposerImpl composerImpl = compositionImpl.composer;
            composerImpl.reusingGroup = 100;
            composerImpl.reusing = true;
            compositionImpl.composeInitial(composableLambdaImpl);
            if (composerImpl.isComposing || composerImpl.reusingGroup != 100) {
                PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
            }
            composerImpl.reusingGroup = -1;
            composerImpl.reusing = false;
        } else {
            ((CompositionImpl) reusableComposition).composeInitial(composableLambdaImpl);
        }
        return reusableComposition;
    }

    public final void disposeOrReuseStartingFromIndex(int i) {
        boolean z = false;
        this.reusableCount = 0;
        LayoutNode layoutNode = this.root;
        int size = (layoutNode.getFoldedChildren$ui_release().size() - this.precomposedCount) - 1;
        if (i <= size) {
            SubcomposeSlotReusePolicy.SlotIdsSet slotIdsSet = this.reusableSlotIdsSet;
            slotIdsSet.clear();
            if (i <= size) {
                int i2 = i;
                while (true) {
                    Object obj = this.nodeToNodeState.get((LayoutNode) layoutNode.getFoldedChildren$ui_release().get(i2));
                    Intrinsics.checkNotNull(obj);
                    slotIdsSet.set.add(((NodeState) obj).slotId);
                    if (i2 == size) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
            this.slotReusePolicy.getSlotsToRetain(slotIdsSet);
            Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
            boolean z2 = false;
            while (size >= i) {
                try {
                    LayoutNode layoutNode2 = (LayoutNode) layoutNode.getFoldedChildren$ui_release().get(size);
                    Object obj2 = this.nodeToNodeState.get(layoutNode2);
                    Intrinsics.checkNotNull(obj2);
                    NodeState nodeState = (NodeState) obj2;
                    Object obj3 = nodeState.slotId;
                    if (slotIdsSet.set.contains(obj3)) {
                        this.reusableCount++;
                        if (((Boolean) ((SnapshotMutableStateImpl) nodeState.activeState).getValue()).booleanValue()) {
                            LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode2.layoutDelegate;
                            LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate.measurePassDelegate;
                            LayoutNode.UsageByParent usageByParent = LayoutNode.UsageByParent.NotUsed;
                            measurePassDelegate.measuredByParent = usageByParent;
                            LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                            if (lookaheadPassDelegate != null) {
                                lookaheadPassDelegate.measuredByParent = usageByParent;
                            }
                            ((SnapshotMutableStateImpl) nodeState.activeState).setValue(Boolean.FALSE);
                            z2 = true;
                        }
                    } else {
                        layoutNode.ignoreRemeasureRequests = true;
                        this.nodeToNodeState.remove(layoutNode2);
                        ReusableComposition reusableComposition = nodeState.composition;
                        if (reusableComposition != null) {
                            ((CompositionImpl) reusableComposition).dispose();
                        }
                        layoutNode.removeAt$ui_release(size, 1);
                        layoutNode.ignoreRemeasureRequests = false;
                    }
                    this.slotIdToNode.remove(obj3);
                    size--;
                } catch (Throwable th) {
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            z = z2;
        }
        if (z) {
            Snapshot.Companion.sendApplyNotifications();
        }
        makeSureStateIsConsistent();
    }

    public final void makeSureStateIsConsistent() {
        int size = this.root.getFoldedChildren$ui_release().size();
        if (this.nodeToNodeState.size() != size) {
            throw new IllegalArgumentException(("Inconsistency between the count of nodes tracked by the state (" + this.nodeToNodeState.size() + ") and the children count on the SubcomposeLayout (" + size + "). Are you trying to use the state of the disposed SubcomposeLayout?").toString());
        }
        if ((size - this.reusableCount) - this.precomposedCount < 0) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("Incorrect state. Total children ", ". Reusable children ", size);
            m.append(this.reusableCount);
            m.append(". Precomposed children ");
            m.append(this.precomposedCount);
            throw new IllegalArgumentException(m.toString().toString());
        }
        if (this.precomposeMap.size() == this.precomposedCount) {
            return;
        }
        throw new IllegalArgumentException(("Incorrect state. Precomposed children " + this.precomposedCount + ". Map size " + this.precomposeMap.size()).toString());
    }

    public final void markActiveNodesAsReused(boolean z) {
        this.precomposedCount = 0;
        this.precomposeMap.clear();
        LayoutNode layoutNode = this.root;
        int size = layoutNode.getFoldedChildren$ui_release().size();
        if (this.reusableCount != size) {
            this.reusableCount = size;
            Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
            for (int i = 0; i < size; i++) {
                try {
                    LayoutNode layoutNode2 = (LayoutNode) layoutNode.getFoldedChildren$ui_release().get(i);
                    NodeState nodeState = (NodeState) this.nodeToNodeState.get(layoutNode2);
                    if (nodeState != null && ((Boolean) ((SnapshotMutableStateImpl) nodeState.activeState).getValue()).booleanValue()) {
                        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = layoutNode2.layoutDelegate;
                        LayoutNodeLayoutDelegate.MeasurePassDelegate measurePassDelegate = layoutNodeLayoutDelegate.measurePassDelegate;
                        LayoutNode.UsageByParent usageByParent = LayoutNode.UsageByParent.NotUsed;
                        measurePassDelegate.measuredByParent = usageByParent;
                        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
                        if (lookaheadPassDelegate != null) {
                            lookaheadPassDelegate.measuredByParent = usageByParent;
                        }
                        if (z) {
                            ReusableComposition reusableComposition = nodeState.composition;
                            if (reusableComposition != null) {
                                ((CompositionImpl) reusableComposition).deactivate();
                            }
                            nodeState.activeState = SnapshotStateKt.mutableStateOf$default(Boolean.FALSE);
                        } else {
                            ((SnapshotMutableStateImpl) nodeState.activeState).setValue(Boolean.FALSE);
                        }
                        nodeState.slotId = SubcomposeLayoutKt.ReusedSlotId;
                    }
                } catch (Throwable th) {
                    Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                    throw th;
                }
            }
            Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
            this.slotIdToNode.clear();
        }
        makeSureStateIsConsistent();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onDeactivate() {
        markActiveNodesAsReused(true);
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onRelease() {
        LayoutNode layoutNode = this.root;
        layoutNode.ignoreRemeasureRequests = true;
        Iterator it = this.nodeToNodeState.values().iterator();
        while (it.hasNext()) {
            ReusableComposition reusableComposition = ((NodeState) it.next()).composition;
            if (reusableComposition != null) {
                ((CompositionImpl) reusableComposition).dispose();
            }
        }
        layoutNode.removeAll$ui_release();
        layoutNode.ignoreRemeasureRequests = false;
        this.nodeToNodeState.clear();
        this.slotIdToNode.clear();
        this.precomposedCount = 0;
        this.reusableCount = 0;
        this.precomposeMap.clear();
        makeSureStateIsConsistent();
    }

    @Override // androidx.compose.runtime.ComposeNodeLifecycleCallback
    public final void onReuse() {
        markActiveNodesAsReused(false);
    }

    public final SubcomposeLayoutState.PrecomposedSlotHandle precompose(final Object obj, Function2 function2) {
        LayoutNode layoutNode = this.root;
        if (!layoutNode.isAttached()) {
            return new LayoutNodeSubcompositionsState$precompose$1();
        }
        makeSureStateIsConsistent();
        if (!this.slotIdToNode.containsKey(obj)) {
            this.postLookaheadPrecomposeSlotHandleMap.remove(obj);
            HashMap hashMap = this.precomposeMap;
            Object obj2 = hashMap.get(obj);
            if (obj2 == null) {
                obj2 = takeNodeFromReusables(obj);
                if (obj2 != null) {
                    int indexOf = layoutNode.getFoldedChildren$ui_release().indexOf(obj2);
                    int size = layoutNode.getFoldedChildren$ui_release().size();
                    layoutNode.ignoreRemeasureRequests = true;
                    layoutNode.move$ui_release(indexOf, size, 1);
                    layoutNode.ignoreRemeasureRequests = false;
                    this.precomposedCount++;
                } else {
                    int size2 = layoutNode.getFoldedChildren$ui_release().size();
                    LayoutNode layoutNode2 = new LayoutNode(2);
                    layoutNode.ignoreRemeasureRequests = true;
                    layoutNode.insertAt$ui_release(size2, layoutNode2);
                    layoutNode.ignoreRemeasureRequests = false;
                    this.precomposedCount++;
                    obj2 = layoutNode2;
                }
                hashMap.put(obj, obj2);
            }
            subcompose((LayoutNode) obj2, obj, function2);
        }
        return new SubcomposeLayoutState.PrecomposedSlotHandle() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$precompose$2
            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public final void dispose() {
                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                layoutNodeSubcompositionsState.makeSureStateIsConsistent();
                LayoutNode layoutNode3 = (LayoutNode) layoutNodeSubcompositionsState.precomposeMap.remove(obj);
                if (layoutNode3 != null) {
                    if (layoutNodeSubcompositionsState.precomposedCount <= 0) {
                        throw new IllegalStateException("No pre-composed items to dispose");
                    }
                    LayoutNode layoutNode4 = layoutNodeSubcompositionsState.root;
                    int indexOf2 = layoutNode4.getFoldedChildren$ui_release().indexOf(layoutNode3);
                    int size3 = layoutNode4.getFoldedChildren$ui_release().size();
                    int i = layoutNodeSubcompositionsState.precomposedCount;
                    if (indexOf2 < size3 - i) {
                        throw new IllegalStateException("Item is not in pre-composed item range");
                    }
                    layoutNodeSubcompositionsState.reusableCount++;
                    layoutNodeSubcompositionsState.precomposedCount = i - 1;
                    int size4 = (layoutNode4.getFoldedChildren$ui_release().size() - layoutNodeSubcompositionsState.precomposedCount) - layoutNodeSubcompositionsState.reusableCount;
                    layoutNode4.ignoreRemeasureRequests = true;
                    layoutNode4.move$ui_release(indexOf2, size4, 1);
                    layoutNode4.ignoreRemeasureRequests = false;
                    layoutNodeSubcompositionsState.disposeOrReuseStartingFromIndex(size4);
                }
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public final int getPlaceablesCount() {
                LayoutNode layoutNode3 = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(obj);
                if (layoutNode3 != null) {
                    return layoutNode3.getChildren$ui_release().size();
                }
                return 0;
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            /* renamed from: premeasure-0kLqBqw, reason: not valid java name */
            public final void mo490premeasure0kLqBqw(long j, int i) {
                LayoutNodeSubcompositionsState layoutNodeSubcompositionsState = LayoutNodeSubcompositionsState.this;
                LayoutNode layoutNode3 = (LayoutNode) layoutNodeSubcompositionsState.precomposeMap.get(obj);
                if (layoutNode3 == null || !layoutNode3.isAttached()) {
                    return;
                }
                int size3 = layoutNode3.getChildren$ui_release().size();
                if (i < 0 || i >= size3) {
                    throw new IndexOutOfBoundsException("Index (" + i + ") is out of bound of [0, " + size3 + ')');
                }
                if (layoutNode3.isPlaced()) {
                    throw new IllegalArgumentException("Pre-measure called on node that is not placed");
                }
                LayoutNode layoutNode4 = layoutNodeSubcompositionsState.root;
                layoutNode4.ignoreRemeasureRequests = true;
                ((AndroidComposeView) LayoutNodeKt.requireOwner(layoutNode3)).m556measureAndLayout0kLqBqw((LayoutNode) layoutNode3.getChildren$ui_release().get(i), j);
                layoutNode4.ignoreRemeasureRequests = false;
            }

            @Override // androidx.compose.ui.layout.SubcomposeLayoutState.PrecomposedSlotHandle
            public final void traverseDescendants(Function1 function1) {
                NodeChain nodeChain;
                Modifier.Node node;
                LayoutNode layoutNode3 = (LayoutNode) LayoutNodeSubcompositionsState.this.precomposeMap.get(obj);
                if (layoutNode3 == null || (nodeChain = layoutNode3.nodes) == null || (node = nodeChain.head) == null) {
                    return;
                }
                TraversableNodeKt.traverseDescendants(node, "androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode", function1);
            }
        };
    }

    public final void subcompose(LayoutNode layoutNode, Object obj, Function2 function2) {
        boolean z;
        HashMap hashMap = this.nodeToNodeState;
        Object obj2 = hashMap.get(layoutNode);
        Object obj3 = obj2;
        if (obj2 == null) {
            ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$SubcomposeLayoutKt.f11lambda1;
            NodeState nodeState = new NodeState();
            nodeState.slotId = obj;
            nodeState.content = composableLambdaImpl;
            nodeState.composition = null;
            nodeState.activeState = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
            hashMap.put(layoutNode, nodeState);
            obj3 = nodeState;
        }
        final NodeState nodeState2 = (NodeState) obj3;
        ReusableComposition reusableComposition = nodeState2.composition;
        if (reusableComposition != null) {
            CompositionImpl compositionImpl = (CompositionImpl) reusableComposition;
            synchronized (compositionImpl.lock) {
                z = compositionImpl.invalidations._size > 0;
            }
        } else {
            z = true;
        }
        if (nodeState2.content != function2 || z || nodeState2.forceRecompose) {
            nodeState2.content = function2;
            Snapshot currentThreadSnapshot = Snapshot.Companion.getCurrentThreadSnapshot();
            Function1 readObserver = currentThreadSnapshot != null ? currentThreadSnapshot.getReadObserver() : null;
            Snapshot makeCurrentNonObservable = Snapshot.Companion.makeCurrentNonObservable(currentThreadSnapshot);
            try {
                LayoutNode layoutNode2 = this.root;
                layoutNode2.ignoreRemeasureRequests = true;
                final Function2 function22 = nodeState2.content;
                ReusableComposition reusableComposition2 = nodeState2.composition;
                CompositionContext compositionContext = this.compositionContext;
                if (compositionContext == null) {
                    throw new IllegalStateException("parent composition reference not set");
                }
                nodeState2.composition = subcomposeInto(reusableComposition2, layoutNode, nodeState2.forceReuse, compositionContext, new ComposableLambdaImpl(-1750409193, true, new Function2() { // from class: androidx.compose.ui.layout.LayoutNodeSubcompositionsState$subcompose$3$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj4, Object obj5) {
                        Composer composer = (Composer) obj4;
                        if ((((Number) obj5).intValue() & 3) == 2) {
                            ComposerImpl composerImpl = (ComposerImpl) composer;
                            if (composerImpl.getSkipping()) {
                                composerImpl.skipToGroupEnd();
                                return Unit.INSTANCE;
                            }
                        }
                        OpaqueKey opaqueKey = ComposerKt.invocation;
                        Boolean bool = (Boolean) ((SnapshotMutableStateImpl) LayoutNodeSubcompositionsState.NodeState.this.activeState).getValue();
                        boolean booleanValue = bool.booleanValue();
                        Function2 function23 = function22;
                        ComposerImpl composerImpl2 = (ComposerImpl) composer;
                        composerImpl2.startReusableGroup(bool);
                        boolean changed = composerImpl2.changed(booleanValue);
                        composerImpl2.startReplaceGroup(-869709043);
                        if (booleanValue) {
                            function23.invoke(composerImpl2, 0);
                        } else {
                            composerImpl2.deactivateToEndGroup(changed);
                        }
                        composerImpl2.end(false);
                        composerImpl2.endReusableGroup();
                        return Unit.INSTANCE;
                    }
                }));
                nodeState2.forceReuse = false;
                layoutNode2.ignoreRemeasureRequests = false;
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                nodeState2.forceRecompose = false;
            } catch (Throwable th) {
                Snapshot.Companion.restoreNonObservable(currentThreadSnapshot, makeCurrentNonObservable, readObserver);
                throw th;
            }
        }
    }

    public final LayoutNode takeNodeFromReusables(Object obj) {
        int i;
        if (this.reusableCount == 0) {
            return null;
        }
        LayoutNode layoutNode = this.root;
        int size = layoutNode.getFoldedChildren$ui_release().size() - this.precomposedCount;
        int i2 = size - this.reusableCount;
        int i3 = size - 1;
        int i4 = i3;
        while (true) {
            if (i4 < i2) {
                i = -1;
                break;
            }
            Object obj2 = this.nodeToNodeState.get((LayoutNode) layoutNode.getFoldedChildren$ui_release().get(i4));
            Intrinsics.checkNotNull(obj2);
            if (Intrinsics.areEqual(((NodeState) obj2).slotId, obj)) {
                i = i4;
                break;
            }
            i4--;
        }
        if (i == -1) {
            while (i3 >= i2) {
                Object obj3 = this.nodeToNodeState.get((LayoutNode) layoutNode.getFoldedChildren$ui_release().get(i3));
                Intrinsics.checkNotNull(obj3);
                NodeState nodeState = (NodeState) obj3;
                Object obj4 = nodeState.slotId;
                if (obj4 == SubcomposeLayoutKt.ReusedSlotId || this.slotReusePolicy.areCompatible(obj, obj4)) {
                    nodeState.slotId = obj;
                    i4 = i3;
                    i = i4;
                    break;
                }
                i3--;
            }
            i4 = i3;
        }
        if (i == -1) {
            return null;
        }
        if (i4 != i2) {
            layoutNode.ignoreRemeasureRequests = true;
            layoutNode.move$ui_release(i4, i2, 1);
            layoutNode.ignoreRemeasureRequests = false;
        }
        this.reusableCount--;
        LayoutNode layoutNode2 = (LayoutNode) layoutNode.getFoldedChildren$ui_release().get(i2);
        Object obj5 = this.nodeToNodeState.get(layoutNode2);
        Intrinsics.checkNotNull(obj5);
        NodeState nodeState2 = (NodeState) obj5;
        nodeState2.activeState = SnapshotStateKt.mutableStateOf$default(Boolean.TRUE);
        nodeState2.forceReuse = true;
        nodeState2.forceRecompose = true;
        return layoutNode2;
    }
}
