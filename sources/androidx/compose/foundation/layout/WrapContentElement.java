package androidx.compose.foundation.layout;

import androidx.compose.animation.TransitionData$$ExternalSyntheticOutline0;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WrapContentElement extends ModifierNodeElement {
    public final Object align;
    public final Lambda alignmentCallback;
    public final Direction direction;
    public final boolean unbounded;

    /* JADX WARN: Multi-variable type inference failed */
    public WrapContentElement(Direction direction, boolean z, Function2 function2, Object obj) {
        this.direction = direction;
        this.unbounded = z;
        this.alignmentCallback = (Lambda) function2;
        this.align = obj;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        WrapContentNode wrapContentNode = new WrapContentNode();
        wrapContentNode.direction = this.direction;
        wrapContentNode.unbounded = this.unbounded;
        wrapContentNode.alignmentCallback = this.alignmentCallback;
        return wrapContentNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || WrapContentElement.class != obj.getClass()) {
            return false;
        }
        WrapContentElement wrapContentElement = (WrapContentElement) obj;
        return this.direction == wrapContentElement.direction && this.unbounded == wrapContentElement.unbounded && Intrinsics.areEqual(this.align, wrapContentElement.align);
    }

    public final int hashCode() {
        return this.align.hashCode() + TransitionData$$ExternalSyntheticOutline0.m(this.direction.hashCode() * 31, 31, this.unbounded);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        WrapContentNode wrapContentNode = (WrapContentNode) node;
        wrapContentNode.direction = this.direction;
        wrapContentNode.unbounded = this.unbounded;
        wrapContentNode.alignmentCallback = this.alignmentCallback;
    }
}
