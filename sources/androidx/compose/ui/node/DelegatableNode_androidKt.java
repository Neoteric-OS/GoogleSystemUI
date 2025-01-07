package androidx.compose.ui.node;

import android.view.View;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DelegatableNode_androidKt {
    public static final View requireView(DelegatableNode delegatableNode) {
        if (!((Modifier.Node) delegatableNode).node.isAttached) {
            InlineClassHelperKt.throwIllegalStateException("Cannot get View because the Modifier node is not currently attached.");
        }
        return (View) LayoutNodeKt.requireOwner(DelegatableNodeKt.requireLayoutNode(delegatableNode));
    }
}
