package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DrawModifierNodeKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final void invalidateDraw(DrawModifierNode drawModifierNode) {
        if (((Modifier.Node) drawModifierNode).node.isAttached) {
            DelegatableNodeKt.m503requireCoordinator64DMado(drawModifierNode, 1).invalidateLayer();
        }
    }
}
