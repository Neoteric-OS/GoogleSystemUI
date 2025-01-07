package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LazyLayoutAnimateItemElement extends ModifierNodeElement {
    public final SpringSpec fadeInSpec;
    public final SpringSpec fadeOutSpec;
    public final FiniteAnimationSpec placementSpec;

    public LazyLayoutAnimateItemElement(SpringSpec springSpec, FiniteAnimationSpec finiteAnimationSpec, SpringSpec springSpec2) {
        this.fadeInSpec = springSpec;
        this.placementSpec = finiteAnimationSpec;
        this.fadeOutSpec = springSpec2;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        LazyLayoutAnimationSpecsNode lazyLayoutAnimationSpecsNode = new LazyLayoutAnimationSpecsNode();
        lazyLayoutAnimationSpecsNode.fadeInSpec = this.fadeInSpec;
        lazyLayoutAnimationSpecsNode.placementSpec = this.placementSpec;
        lazyLayoutAnimationSpecsNode.fadeOutSpec = this.fadeOutSpec;
        return lazyLayoutAnimationSpecsNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LazyLayoutAnimateItemElement)) {
            return false;
        }
        LazyLayoutAnimateItemElement lazyLayoutAnimateItemElement = (LazyLayoutAnimateItemElement) obj;
        return this.fadeInSpec.equals(lazyLayoutAnimateItemElement.fadeInSpec) && Intrinsics.areEqual(this.placementSpec, lazyLayoutAnimateItemElement.placementSpec) && this.fadeOutSpec.equals(lazyLayoutAnimateItemElement.fadeOutSpec);
    }

    public final int hashCode() {
        int hashCode = this.fadeInSpec.hashCode() * 31;
        FiniteAnimationSpec finiteAnimationSpec = this.placementSpec;
        return this.fadeOutSpec.hashCode() + ((hashCode + (finiteAnimationSpec == null ? 0 : finiteAnimationSpec.hashCode())) * 31);
    }

    public final String toString() {
        return "LazyLayoutAnimateItemElement(fadeInSpec=" + this.fadeInSpec + ", placementSpec=" + this.placementSpec + ", fadeOutSpec=" + this.fadeOutSpec + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        LazyLayoutAnimationSpecsNode lazyLayoutAnimationSpecsNode = (LazyLayoutAnimationSpecsNode) node;
        lazyLayoutAnimationSpecsNode.fadeInSpec = this.fadeInSpec;
        lazyLayoutAnimationSpecsNode.placementSpec = this.placementSpec;
        lazyLayoutAnimationSpecsNode.fadeOutSpec = this.fadeOutSpec;
    }
}
