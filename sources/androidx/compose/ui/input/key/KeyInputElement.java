package androidx.compose.ui.input.key;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ModifierNodeElement;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class KeyInputElement extends ModifierNodeElement {
    public final Function1 onKeyEvent;
    public final Lambda onPreKeyEvent;

    /* JADX WARN: Multi-variable type inference failed */
    public KeyInputElement(Function1 function1, Function1 function12) {
        this.onKeyEvent = function1;
        this.onPreKeyEvent = (Lambda) function12;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final Modifier.Node create() {
        KeyInputNode keyInputNode = new KeyInputNode();
        keyInputNode.onEvent = this.onKeyEvent;
        keyInputNode.onPreEvent = this.onPreKeyEvent;
        return keyInputNode;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof KeyInputElement)) {
            return false;
        }
        KeyInputElement keyInputElement = (KeyInputElement) obj;
        return Intrinsics.areEqual(this.onKeyEvent, keyInputElement.onKeyEvent) && Intrinsics.areEqual(this.onPreKeyEvent, keyInputElement.onPreKeyEvent);
    }

    public final int hashCode() {
        Function1 function1 = this.onKeyEvent;
        int hashCode = (function1 == null ? 0 : function1.hashCode()) * 31;
        Lambda lambda = this.onPreKeyEvent;
        return hashCode + (lambda != null ? lambda.hashCode() : 0);
    }

    public final String toString() {
        return "KeyInputElement(onKeyEvent=" + this.onKeyEvent + ", onPreKeyEvent=" + this.onPreKeyEvent + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public final void update(Modifier.Node node) {
        KeyInputNode keyInputNode = (KeyInputNode) node;
        keyInputNode.onEvent = this.onKeyEvent;
        keyInputNode.onPreEvent = this.onPreKeyEvent;
    }
}
