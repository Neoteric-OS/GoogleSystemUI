package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InteractionSourceModifierElement extends ModifierNodeElement {
    public final MutableInteractionSource interactionSource;

    public InteractionSourceModifierElement(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        return new InteractionSourceModifierNode();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InteractionSourceModifierElement) && Intrinsics.areEqual(this.interactionSource, ((InteractionSourceModifierElement) obj).interactionSource);
    }

    public final int hashCode() {
        return this.interactionSource.hashCode();
    }

    public final String toString() {
        return "InteractionSourceModifierElement(interactionSource=" + this.interactionSource + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((InteractionSourceModifierNode) node).getClass();
    }
}
