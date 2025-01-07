package androidx.compose.foundation;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HoverableElement extends ModifierNodeElement {
    public final MutableInteractionSource interactionSource;

    public HoverableElement(MutableInteractionSource mutableInteractionSource) {
        this.interactionSource = mutableInteractionSource;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        HoverableNode hoverableNode = new HoverableNode();
        hoverableNode.interactionSource = this.interactionSource;
        return hoverableNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HoverableElement) && Intrinsics.areEqual(((HoverableElement) obj).interactionSource, this.interactionSource);
    }

    public final int hashCode() {
        return this.interactionSource.hashCode() * 31;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        HoverableNode hoverableNode = (HoverableNode) node;
        MutableInteractionSource mutableInteractionSource = hoverableNode.interactionSource;
        MutableInteractionSource mutableInteractionSource2 = this.interactionSource;
        if (Intrinsics.areEqual(mutableInteractionSource, mutableInteractionSource2)) {
            return;
        }
        hoverableNode.tryEmitExit();
        hoverableNode.interactionSource = mutableInteractionSource2;
    }
}
