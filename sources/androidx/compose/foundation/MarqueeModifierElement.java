package androidx.compose.foundation;

import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.runtime.SnapshotMutableStateImpl;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.unit.Dp;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MarqueeModifierElement extends ModifierNodeElement {
    public final int initialDelayMillis;
    public final int iterations;
    public final MarqueeSpacing$Companion$$ExternalSyntheticLambda0 spacing;
    public final float velocity;

    public MarqueeModifierElement(int i, int i2, MarqueeSpacing$Companion$$ExternalSyntheticLambda0 marqueeSpacing$Companion$$ExternalSyntheticLambda0, float f) {
        this.iterations = i;
        this.initialDelayMillis = i2;
        this.spacing = marqueeSpacing$Companion$$ExternalSyntheticLambda0;
        this.velocity = f;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new MarqueeModifierNode(this.iterations, this.initialDelayMillis, this.spacing, this.velocity);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarqueeModifierElement)) {
            return false;
        }
        MarqueeModifierElement marqueeModifierElement = (MarqueeModifierElement) obj;
        return this.iterations == marqueeModifierElement.iterations && this.initialDelayMillis == marqueeModifierElement.initialDelayMillis && Intrinsics.areEqual(this.spacing, marqueeModifierElement.spacing) && Dp.m668equalsimpl0(this.velocity, marqueeModifierElement.velocity);
    }

    public final int hashCode() {
        return Float.hashCode(this.velocity) + ((hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.initialDelayMillis, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(1200, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, Integer.hashCode(this.iterations) * 31, 31), 31), 31)) * 31);
    }

    public final String toString() {
        return "MarqueeModifierElement(iterations=" + this.iterations + ", animationMode=Immediately, delayMillis=1200, initialDelayMillis=" + this.initialDelayMillis + ", spacing=" + this.spacing + ", velocity=" + ((Object) Dp.m669toStringimpl(this.velocity)) + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        MarqueeModifierNode marqueeModifierNode = (MarqueeModifierNode) node;
        ((SnapshotMutableStateImpl) marqueeModifierNode.spacing$delegate).setValue(this.spacing);
        ((SnapshotMutableStateImpl) marqueeModifierNode.animationMode$delegate).setValue(new MarqueeAnimationMode());
        int i = marqueeModifierNode.iterations;
        int i2 = this.iterations;
        int i3 = this.initialDelayMillis;
        float f = this.velocity;
        if (i == i2 && marqueeModifierNode.initialDelayMillis == i3 && Dp.m668equalsimpl0(marqueeModifierNode.velocity, f)) {
            return;
        }
        marqueeModifierNode.iterations = i2;
        marqueeModifierNode.initialDelayMillis = i3;
        marqueeModifierNode.velocity = f;
        marqueeModifierNode.restartAnimation();
    }
}
