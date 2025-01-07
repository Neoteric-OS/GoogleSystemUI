package androidx.compose.material3;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.TraversableNode;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InteractionSourceModifierNode extends Modifier.Node implements TraversableNode {
    public final Object traverseKey;

    @Override // androidx.compose.ui.node.TraversableNode
    public final Object getTraverseKey() {
        return InteractionSourceModifierNodeTraverseKey.INSTANCE;
    }
}
