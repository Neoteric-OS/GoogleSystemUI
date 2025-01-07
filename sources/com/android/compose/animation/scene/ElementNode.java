package com.android.compose.animation.scene;

import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LookaheadLayoutCoordinates;
import androidx.compose.ui.layout.LookaheadScope;
import androidx.compose.ui.layout.LookaheadScopeKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.unit.IntSize;
import com.android.compose.animation.scene.Element;
import com.android.compose.animation.scene.content.Content;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ElementNode extends Modifier.Node implements DrawModifierNode, ApproachLayoutModifierNode, TraversableNode {
    public static final Companion Companion = null;
    public static final Object ElementTraverseKey = new Object();
    public Element _element;
    public Element.State _stateInContent;
    public final Content content;
    public List currentTransitionStates;
    public ElementKey key;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final Object traverseKey = ElementTraverseKey;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$maybePruneMaps(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Element element, Element.State state) {
            if (state.nodes.isEmpty()) {
                SnapshotStateMap snapshotStateMap = element.stateByContent;
                ContentKey contentKey = state.content;
                if (Intrinsics.areEqual(snapshotStateMap.get(contentKey), state)) {
                    SnapshotStateMap snapshotStateMap2 = element.stateByContent;
                    snapshotStateMap2.remove(contentKey);
                    if (snapshotStateMap2.isEmpty()) {
                        Map map = sceneTransitionLayoutImpl.elements;
                        ElementKey elementKey = element.key;
                        if (Intrinsics.areEqual(map.get(elementKey), element)) {
                            sceneTransitionLayoutImpl.elements.remove(elementKey);
                        }
                    }
                }
            }
        }
    }

    public ElementNode(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, List list, Content content, ElementKey elementKey) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.currentTransitionStates = list;
        this.content = content;
        this.key = elementKey;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r5v13 long, still in use, count: 2, list:
          (r5v13 long) from 0x02fc: INVOKE (r5v13 long), (r1v8 java.lang.Object) STATIC call: androidx.compose.ui.unit.IntSize.equals-impl(long, java.lang.Object):boolean A[MD:(long, java.lang.Object):boolean (m), WRAPPED] (LINE:765)
          (r5v13 long) from 0x0305: PHI (r5v12 long) = (r5v13 long) binds: [B:183:0x0300] A[DONT_GENERATE, DONT_INLINE]
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
    /* JADX WARN: Removed duplicated region for block: B:167:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:239:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x044a  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0454  */
    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: approachMeasure-3p2s80s */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.layout.MeasureResult mo472approachMeasure3p2s80s(androidx.compose.ui.layout.ApproachMeasureScope r23, androidx.compose.ui.layout.Measurable r24, long r25) {
        /*
            Method dump skipped, instructions count: 1279
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.ElementNode.mo472approachMeasure3p2s80s(androidx.compose.ui.layout.ApproachMeasureScope, androidx.compose.ui.layout.Measurable, long):androidx.compose.ui.layout.MeasureResult");
    }

    /* renamed from: doNotPlace-3p2s80s, reason: not valid java name */
    public final MeasureResult m731doNotPlace3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        Element.State state = this._stateInContent;
        Intrinsics.checkNotNull(state);
        state.lastOffset = 9205357640488583168L;
        state.lastScale = Scale.Unspecified;
        int i = Element.$r8$clinit;
        state.lastAlpha = Float.MAX_VALUE;
        TraversableNodeKt.traverseDescendants(this, ElementTraverseKey, ElementNode$recursivelyClearPlacementValues$1.INSTANCE);
        Element.State state2 = this._stateInContent;
        Intrinsics.checkNotNull(state2);
        state2.lastSize = Element.SizeUnspecified;
        Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        layout$1 = approachMeasureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.animation.scene.ElementNode$doNotPlace$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:206:0x0285, code lost:
    
        if (r16 == null) goto L148;
     */
    /* JADX WARN: Code restructure failed: missing block: B:216:0x014e, code lost:
    
        if (r6 == null) goto L82;
     */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0337 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0345  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x039d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x03a1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0309  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x02b3  */
    @Override // androidx.compose.ui.node.DrawModifierNode
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void draw(androidx.compose.ui.node.LayoutNodeDrawScope r26) {
        /*
            Method dump skipped, instructions count: 999
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.animation.scene.ElementNode.draw(androidx.compose.ui.node.LayoutNodeDrawScope):void");
    }

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return ElementTraverseKey;
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    /* renamed from: isMeasurementApproachInProgress-ozmzZPI */
    public final boolean mo473isMeasurementApproachInProgressozmzZPI(long j) {
        return SceneTransitionLayoutState.isTransitioning$default(this.layoutImpl.state, null, 3);
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode
    public final boolean isPlacementApproachInProgress(Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates) {
        return SceneTransitionLayoutState.isTransitioning$default(this.layoutImpl.state, null, 3);
    }

    @Override // androidx.compose.ui.layout.ApproachLayoutModifierNode, androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        if (!measureScope.isLookingAhead()) {
            throw new IllegalStateException("Check failed.");
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        Element.State state = this._stateInContent;
        Intrinsics.checkNotNull(state);
        long size = ElementKt.size(mo479measureBRTryo0);
        ((SnapshotMutableStateImpl) state.targetSize$delegate).setValue(new IntSize(size));
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.animation.scene.ElementNode$measure$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                LayoutCoordinates coordinates = placementScope.getCoordinates();
                if (coordinates != null) {
                    ElementNode elementNode = this;
                    LookaheadScope lookaheadScope = elementNode.layoutImpl.lookaheadScope;
                    if (lookaheadScope == null) {
                        lookaheadScope = null;
                    }
                    Element.State state2 = elementNode._stateInContent;
                    Intrinsics.checkNotNull(state2);
                    LayoutCoordinates lookaheadScopeCoordinates = lookaheadScope.getLookaheadScopeCoordinates();
                    Function2 function2 = LookaheadScopeKt.defaultPlacementApproachInProgress;
                    LayoutCoordinates lookaheadCoordinates = lookaheadScope.toLookaheadCoordinates(lookaheadScopeCoordinates);
                    LayoutCoordinates lookaheadCoordinates2 = lookaheadScope.toLookaheadCoordinates(coordinates);
                    ((SnapshotMutableStateImpl) state2.targetOffset$delegate).setValue(new Offset(lookaheadCoordinates instanceof LookaheadLayoutCoordinates ? ((LookaheadLayoutCoordinates) lookaheadCoordinates).mo483localPositionOfS_NoaFU(lookaheadCoordinates2, 0L) : lookaheadCoordinates2 instanceof LookaheadLayoutCoordinates ? ((LookaheadLayoutCoordinates) lookaheadCoordinates2).mo483localPositionOfS_NoaFU(lookaheadCoordinates, 0L) ^ (-9223372034707292160L) : lookaheadCoordinates.mo483localPositionOfS_NoaFU(lookaheadCoordinates, 0L)));
                }
                placementScope.place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        updateElementAndContentValues();
        Element.State state = this._stateInContent;
        Intrinsics.checkNotNull(state);
        state.nodes.add(this);
        BuildersKt.launch$default(getCoroutineScope(), null, null, new ElementNode$addNodeToContentState$1(this, null), 3);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        Element.State state = this._stateInContent;
        Intrinsics.checkNotNull(state);
        state.nodes.remove(this);
        Element element = this._element;
        Intrinsics.checkNotNull(element);
        Element.State state2 = this._stateInContent;
        Intrinsics.checkNotNull(state2);
        Companion.access$maybePruneMaps(this.layoutImpl, element, state2);
        this._element = null;
        this._stateInContent = null;
    }

    /* renamed from: placeNormally-3p2s80s, reason: not valid java name */
    public final MeasureResult m732placeNormally3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        Element.State state = this._stateInContent;
        Intrinsics.checkNotNull(state);
        state.lastSize = ElementKt.size(mo479measureBRTryo0);
        layout$1 = approachMeasureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.animation.scene.ElementNode$placeNormally$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                LayoutCoordinates coordinates = placementScope.getCoordinates();
                if (coordinates != null) {
                    ElementNode elementNode = this;
                    LookaheadScope lookaheadScope = elementNode.layoutImpl.lookaheadScope;
                    if (lookaheadScope == null) {
                        lookaheadScope = null;
                    }
                    Element.State state2 = elementNode._stateInContent;
                    Intrinsics.checkNotNull(state2);
                    state2.lastOffset = lookaheadScope.getLookaheadScopeCoordinates().mo482localPositionOfR5De75A(coordinates, 0L);
                }
                placementScope.place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    public final void updateElementAndContentValues() {
        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = this.layoutImpl;
        Element element = (Element) sceneTransitionLayoutImpl.elements.get(this.key);
        if (element == null) {
            element = new Element(this.key);
            sceneTransitionLayoutImpl.elements.put(this.key, element);
        }
        this._element = element;
        Content content = this.content;
        ContentKey key = content.getKey();
        SnapshotStateMap snapshotStateMap = element.stateByContent;
        Element.State state = (Element.State) snapshotStateMap.get(key);
        if (state == null) {
            state = new Element.State(content.getKey());
            snapshotStateMap.put(content.getKey(), state);
        }
        this._stateInContent = state;
    }
}
