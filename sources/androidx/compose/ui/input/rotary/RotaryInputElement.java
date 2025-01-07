package androidx.compose.ui.input.rotary;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RotaryInputElement extends ModifierNodeElement {
    public final Function1 onRotaryScrollEvent;

    public RotaryInputElement(Function1 function1) {
        this.onRotaryScrollEvent = function1;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        RotaryInputNode rotaryInputNode = new RotaryInputNode();
        rotaryInputNode.onEvent = this.onRotaryScrollEvent;
        return rotaryInputNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RotaryInputElement) {
            return Intrinsics.areEqual(this.onRotaryScrollEvent, ((RotaryInputElement) obj).onRotaryScrollEvent) && Intrinsics.areEqual((Object) null, (Object) null);
        }
        return false;
    }

    public final int hashCode() {
        Function1 function1 = this.onRotaryScrollEvent;
        return (function1 == null ? 0 : function1.hashCode()) * 31;
    }

    public final String toString() {
        return "RotaryInputElement(onRotaryScrollEvent=" + this.onRotaryScrollEvent + ", onPreRotaryScrollEvent=null)";
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        ((RotaryInputNode) node).onEvent = this.onRotaryScrollEvent;
    }
}
