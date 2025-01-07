package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LazyLayoutBeyondBoundsModifierElement extends ModifierNodeElement {
    public final LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    public final LayoutDirection layoutDirection;
    public final Orientation orientation;
    public final boolean reverseLayout;
    public final LazyLayoutBeyondBoundsState state;

    public LazyLayoutBeyondBoundsModifierElement(LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsState, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo, boolean z, LayoutDirection layoutDirection, Orientation orientation) {
        this.state = lazyLayoutBeyondBoundsState;
        this.beyondBoundsInfo = lazyLayoutBeyondBoundsInfo;
        this.reverseLayout = z;
        this.layoutDirection = layoutDirection;
        this.orientation = orientation;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LazyLayoutBeyondBoundsModifierNode lazyLayoutBeyondBoundsModifierNode = new LazyLayoutBeyondBoundsModifierNode();
        lazyLayoutBeyondBoundsModifierNode.state = this.state;
        lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo = this.beyondBoundsInfo;
        lazyLayoutBeyondBoundsModifierNode.reverseLayout = this.reverseLayout;
        lazyLayoutBeyondBoundsModifierNode.layoutDirection = this.layoutDirection;
        lazyLayoutBeyondBoundsModifierNode.orientation = this.orientation;
        return lazyLayoutBeyondBoundsModifierNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyLayoutBeyondBoundsModifierElement)) {
            return false;
        }
        LazyLayoutBeyondBoundsModifierElement lazyLayoutBeyondBoundsModifierElement = (LazyLayoutBeyondBoundsModifierElement) obj;
        return Intrinsics.areEqual(this.state, lazyLayoutBeyondBoundsModifierElement.state) && Intrinsics.areEqual(this.beyondBoundsInfo, lazyLayoutBeyondBoundsModifierElement.beyondBoundsInfo) && this.reverseLayout == lazyLayoutBeyondBoundsModifierElement.reverseLayout && this.layoutDirection == lazyLayoutBeyondBoundsModifierElement.layoutDirection && this.orientation == lazyLayoutBeyondBoundsModifierElement.orientation;
    }

    public final int hashCode() {
        return this.orientation.hashCode() + ((this.layoutDirection.hashCode() + TransitionData$$ExternalSyntheticOutline0.m((this.beyondBoundsInfo.hashCode() + (this.state.hashCode() * 31)) * 31, 31, this.reverseLayout)) * 31);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        LazyLayoutBeyondBoundsModifierNode lazyLayoutBeyondBoundsModifierNode = (LazyLayoutBeyondBoundsModifierNode) node;
        lazyLayoutBeyondBoundsModifierNode.state = this.state;
        lazyLayoutBeyondBoundsModifierNode.beyondBoundsInfo = this.beyondBoundsInfo;
        lazyLayoutBeyondBoundsModifierNode.reverseLayout = this.reverseLayout;
        lazyLayoutBeyondBoundsModifierNode.layoutDirection = this.layoutDirection;
        lazyLayoutBeyondBoundsModifierNode.orientation = this.orientation;
    }
}
