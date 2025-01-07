package androidx.compose.ui.focus;

import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.platform.AndroidComposeView;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FocusTargetNodeKt {
    public static final void invalidateFocusTarget(FocusTargetNode focusTargetNode) {
        FocusInvalidationManager focusInvalidationManager = ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusTargetNode)).focusOwner.focusInvalidationManager;
        focusInvalidationManager.scheduleInvalidation(focusInvalidationManager.focusTargetNodes, focusTargetNode);
    }

    public static final FocusTransactionManager requireTransactionManager(FocusTargetNode focusTargetNode) {
        return ((AndroidComposeView) DelegatableNodeKt.requireOwner(focusTargetNode)).focusOwner.focusTransactionManager;
    }
}
