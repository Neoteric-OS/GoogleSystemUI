package androidx.compose.material3.internal;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.SemanticsModifierNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ChildSemanticsNode extends Modifier.Node implements SemanticsModifierNode {
    public Function1 properties;

    @Override // androidx.compose.ui.node.SemanticsModifierNode
    public final void applySemantics(final SemanticsPropertyReceiver semanticsPropertyReceiver) {
        TraversableNodeKt.traverseAncestors(this, ParentSemanticsNodeKey.INSTANCE, new Function1() { // from class: androidx.compose.material3.internal.ChildSemanticsNode$applySemantics$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((TraversableNode) obj).getClass();
                throw new ClassCastException();
            }
        });
        this.properties.invoke(semanticsPropertyReceiver);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        TraversableNodeKt.traverseAncestors(this, ParentSemanticsNodeKey.INSTANCE, new Function1() { // from class: androidx.compose.material3.internal.ChildSemanticsNode$onDetach$1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                TraversableNode traversableNode = (TraversableNode) obj;
                if (traversableNode != null) {
                    throw new ClassCastException();
                }
                traversableNode.getClass();
                throw new ClassCastException();
            }
        });
    }
}
