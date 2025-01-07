package androidx.compose.foundation.gestures;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.node.SemanticsModifierNodeKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class ScrollableElement extends ModifierNodeElement {
    public final BringIntoViewSpec bringIntoViewSpec;
    public final boolean enabled;
    public final FlingBehavior flingBehavior;
    public final MutableInteractionSource interactionSource;
    public final Orientation orientation;
    public final OverscrollEffect overscrollEffect;
    public final boolean reverseDirection;
    public final ScrollableState state;

    public ScrollableElement(OverscrollEffect overscrollEffect, BringIntoViewSpec bringIntoViewSpec, FlingBehavior flingBehavior, Orientation orientation, ScrollableState scrollableState, MutableInteractionSource mutableInteractionSource, boolean z, boolean z2) {
        this.state = scrollableState;
        this.orientation = orientation;
        this.overscrollEffect = overscrollEffect;
        this.enabled = z;
        this.reverseDirection = z2;
        this.flingBehavior = flingBehavior;
        this.interactionSource = mutableInteractionSource;
        this.bringIntoViewSpec = bringIntoViewSpec;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        BringIntoViewSpec bringIntoViewSpec = this.bringIntoViewSpec;
        ScrollableState scrollableState = this.state;
        return new ScrollableNode(this.overscrollEffect, bringIntoViewSpec, this.flingBehavior, this.orientation, scrollableState, this.interactionSource, this.enabled, this.reverseDirection);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrollableElement)) {
            return false;
        }
        ScrollableElement scrollableElement = (ScrollableElement) obj;
        return Intrinsics.areEqual(this.state, scrollableElement.state) && this.orientation == scrollableElement.orientation && Intrinsics.areEqual(this.overscrollEffect, scrollableElement.overscrollEffect) && this.enabled == scrollableElement.enabled && this.reverseDirection == scrollableElement.reverseDirection && Intrinsics.areEqual(this.flingBehavior, scrollableElement.flingBehavior) && Intrinsics.areEqual(this.interactionSource, scrollableElement.interactionSource) && Intrinsics.areEqual(this.bringIntoViewSpec, scrollableElement.bringIntoViewSpec);
    }

    public final int hashCode() {
        int hashCode = (this.orientation.hashCode() + (this.state.hashCode() * 31)) * 31;
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        int m = TransitionData$$ExternalSyntheticOutline0.m(TransitionData$$ExternalSyntheticOutline0.m((hashCode + (overscrollEffect != null ? overscrollEffect.hashCode() : 0)) * 31, 31, this.enabled), 31, this.reverseDirection);
        FlingBehavior flingBehavior = this.flingBehavior;
        int hashCode2 = (m + (flingBehavior != null ? flingBehavior.hashCode() : 0)) * 31;
        MutableInteractionSource mutableInteractionSource = this.interactionSource;
        int hashCode3 = (hashCode2 + (mutableInteractionSource != null ? mutableInteractionSource.hashCode() : 0)) * 31;
        BringIntoViewSpec bringIntoViewSpec = this.bringIntoViewSpec;
        return hashCode3 + (bringIntoViewSpec != null ? bringIntoViewSpec.hashCode() : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        boolean z;
        boolean z2;
        ScrollableNode scrollableNode = (ScrollableNode) node;
        boolean z3 = scrollableNode.enabled;
        boolean z4 = this.enabled;
        boolean z5 = false;
        if (z3 != z4) {
            scrollableNode.nestedScrollConnection.enabled = z4;
            scrollableNode.scrollableContainerNode.enabled = z4;
            z = true;
        } else {
            z = false;
        }
        FlingBehavior flingBehavior = this.flingBehavior;
        FlingBehavior flingBehavior2 = flingBehavior == null ? scrollableNode.defaultFlingBehavior : flingBehavior;
        ScrollingLogic scrollingLogic = scrollableNode.scrollingLogic;
        ScrollableState scrollableState = scrollingLogic.scrollableState;
        ScrollableState scrollableState2 = this.state;
        if (!Intrinsics.areEqual(scrollableState, scrollableState2)) {
            scrollingLogic.scrollableState = scrollableState2;
            z5 = true;
        }
        OverscrollEffect overscrollEffect = this.overscrollEffect;
        scrollingLogic.overscrollEffect = overscrollEffect;
        Orientation orientation = scrollingLogic.orientation;
        Orientation orientation2 = this.orientation;
        if (orientation != orientation2) {
            scrollingLogic.orientation = orientation2;
            z5 = true;
        }
        boolean z6 = scrollingLogic.reverseDirection;
        boolean z7 = this.reverseDirection;
        if (z6 != z7) {
            scrollingLogic.reverseDirection = z7;
            z2 = true;
        } else {
            z2 = z5;
        }
        scrollingLogic.flingBehavior = flingBehavior2;
        scrollingLogic.nestedScrollDispatcher = scrollableNode.nestedScrollDispatcher;
        ContentInViewNode contentInViewNode = scrollableNode.contentInViewNode;
        contentInViewNode.orientation = orientation2;
        contentInViewNode.reverseDirection = z7;
        contentInViewNode.bringIntoViewSpec = this.bringIntoViewSpec;
        scrollableNode.overscrollEffect = overscrollEffect;
        scrollableNode.flingBehavior = flingBehavior;
        Function1 function1 = ScrollableKt.CanDragCalculation;
        Orientation orientation3 = scrollingLogic.orientation;
        Orientation orientation4 = Orientation.Vertical;
        scrollableNode.update(function1, z4, this.interactionSource, orientation3 == orientation4 ? orientation4 : Orientation.Horizontal, z2);
        if (z) {
            scrollableNode.scrollByAction = null;
            scrollableNode.scrollByOffsetAction = null;
            SemanticsModifierNodeKt.invalidateSemantics(scrollableNode);
        }
    }
}
