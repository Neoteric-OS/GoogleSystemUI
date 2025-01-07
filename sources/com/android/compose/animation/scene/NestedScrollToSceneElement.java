package com.android.compose.animation.scene;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NestedScrollToSceneElement extends ModifierNodeElement {
    public final NestedScrollBehavior bottomOrRightBehavior;
    public final Function0 isExternalOverscrollGesture;
    public final SceneTransitionLayoutImpl layoutImpl;
    public final Orientation orientation;
    public final NestedScrollBehavior topOrLeftBehavior;

    public NestedScrollToSceneElement(SceneTransitionLayoutImpl sceneTransitionLayoutImpl, Orientation orientation, NestedScrollBehavior nestedScrollBehavior, NestedScrollBehavior nestedScrollBehavior2, Function0 function0) {
        this.layoutImpl = sceneTransitionLayoutImpl;
        this.orientation = orientation;
        this.topOrLeftBehavior = nestedScrollBehavior;
        this.bottomOrRightBehavior = nestedScrollBehavior2;
        this.isExternalOverscrollGesture = function0;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        Orientation orientation = this.orientation;
        final NestedScrollToSceneNode nestedScrollToSceneNode = new NestedScrollToSceneNode();
        nestedScrollToSceneNode.layoutImpl = this.layoutImpl;
        nestedScrollToSceneNode.orientation = orientation;
        nestedScrollToSceneNode.topOrLeftBehavior = this.topOrLeftBehavior;
        nestedScrollToSceneNode.bottomOrRightBehavior = this.bottomOrRightBehavior;
        nestedScrollToSceneNode.isExternalOverscrollGesture = this.isExternalOverscrollGesture;
        nestedScrollToSceneNode.delegate(new NestedScrollNode(new NestedScrollConnection() { // from class: com.android.compose.animation.scene.NestedScrollToSceneNode$updateScrollBehaviorsConnection$1
            @Override // androidx.compose.ui.input.nestedscroll.NestedScrollConnection
            /* renamed from: onPostScroll-DzOQY0M */
            public final long mo70onPostScrollDzOQY0M(int i, long j, long j2) {
                DraggableHandlerImpl draggableHandlerImpl;
                if (!Offset.m310equalsimpl0(j2, 0L)) {
                    NestedScrollToSceneNode nestedScrollToSceneNode2 = NestedScrollToSceneNode.this;
                    ScrollBehaviorOwnerNode scrollBehaviorOwnerNode = nestedScrollToSceneNode2.scrollBehaviorOwner;
                    if (scrollBehaviorOwnerNode == null) {
                        SceneTransitionLayoutImpl sceneTransitionLayoutImpl = nestedScrollToSceneNode2.layoutImpl;
                        Orientation orientation2 = nestedScrollToSceneNode2.orientation;
                        sceneTransitionLayoutImpl.getClass();
                        int ordinal = orientation2.ordinal();
                        if (ordinal == 0) {
                            draggableHandlerImpl = sceneTransitionLayoutImpl.verticalDraggableHandler;
                        } else {
                            if (ordinal != 1) {
                                throw new NoWhenBranchMatchedException();
                            }
                            draggableHandlerImpl = sceneTransitionLayoutImpl.horizontalDraggableHandler;
                        }
                        TraversableNode findNearestAncestor = TraversableNodeKt.findNearestAncestor(nestedScrollToSceneNode2, draggableHandlerImpl.nestedScrollKey);
                        if (findNearestAncestor == null) {
                            throw new IllegalStateException("This should never happen! Couldn't find a ScrollBehaviorOwner. Are we inside an SceneTransitionLayout?");
                        }
                        scrollBehaviorOwnerNode = (ScrollBehaviorOwnerNode) findNearestAncestor;
                        nestedScrollToSceneNode2.scrollBehaviorOwner = scrollBehaviorOwnerNode;
                    }
                    NestedScrollBehavior nestedScrollBehavior = nestedScrollToSceneNode2.topOrLeftBehavior;
                    NestedScrollBehavior nestedScrollBehavior2 = nestedScrollToSceneNode2.bottomOrRightBehavior;
                    Function0 function0 = nestedScrollToSceneNode2.isExternalOverscrollGesture;
                    NestedScrollHandlerImpl nestedScrollHandlerImpl = scrollBehaviorOwnerNode.nestedScrollHandlerImpl;
                    nestedScrollHandlerImpl.topOrLeftBehavior = nestedScrollBehavior;
                    nestedScrollHandlerImpl.bottomOrRightBehavior = nestedScrollBehavior2;
                    nestedScrollHandlerImpl.isExternalOverscrollGesture = function0;
                }
                return 0L;
            }
        }, null));
        return nestedScrollToSceneNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NestedScrollToSceneElement)) {
            return false;
        }
        NestedScrollToSceneElement nestedScrollToSceneElement = (NestedScrollToSceneElement) obj;
        return Intrinsics.areEqual(this.layoutImpl, nestedScrollToSceneElement.layoutImpl) && this.orientation == nestedScrollToSceneElement.orientation && this.topOrLeftBehavior == nestedScrollToSceneElement.topOrLeftBehavior && this.bottomOrRightBehavior == nestedScrollToSceneElement.bottomOrRightBehavior && Intrinsics.areEqual(this.isExternalOverscrollGesture, nestedScrollToSceneElement.isExternalOverscrollGesture);
    }

    public final int hashCode() {
        return this.isExternalOverscrollGesture.hashCode() + ((this.bottomOrRightBehavior.hashCode() + ((this.topOrLeftBehavior.hashCode() + ((this.orientation.hashCode() + (this.layoutImpl.hashCode() * 31)) * 31)) * 31)) * 31);
    }

    public final String toString() {
        return "NestedScrollToSceneElement(layoutImpl=" + this.layoutImpl + ", orientation=" + this.orientation + ", topOrLeftBehavior=" + this.topOrLeftBehavior + ", bottomOrRightBehavior=" + this.bottomOrRightBehavior + ", isExternalOverscrollGesture=" + this.isExternalOverscrollGesture + ")";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        NestedScrollToSceneNode nestedScrollToSceneNode = (NestedScrollToSceneNode) node;
        nestedScrollToSceneNode.layoutImpl = this.layoutImpl;
        nestedScrollToSceneNode.orientation = this.orientation;
        nestedScrollToSceneNode.topOrLeftBehavior = this.topOrLeftBehavior;
        nestedScrollToSceneNode.bottomOrRightBehavior = this.bottomOrRightBehavior;
        nestedScrollToSceneNode.isExternalOverscrollGesture = this.isExternalOverscrollGesture;
    }
}
