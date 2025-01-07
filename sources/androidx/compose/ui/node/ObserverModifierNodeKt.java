package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ObserverModifierNodeKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final void observeReads(Modifier.Node node, Function0 function0) {
        ObserverNodeOwnerScope observerNodeOwnerScope = node.ownerScope;
        if (observerNodeOwnerScope == null) {
            observerNodeOwnerScope = new ObserverNodeOwnerScope((ObserverModifierNode) node);
            node.ownerScope = observerNodeOwnerScope;
        }
        ((AndroidComposeView) DelegatableNodeKt.requireOwner(node)).snapshotObserver.observeReads$ui_release(observerNodeOwnerScope, ObserverNodeOwnerScope.OnObserveReadsChanged, function0);
    }
}
