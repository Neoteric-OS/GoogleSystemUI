package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ObserverNodeOwnerScope implements OwnerScope {
    public static final Function1 OnObserveReadsChanged = new Function1() { // from class: androidx.compose.ui.node.ObserverNodeOwnerScope$Companion$OnObserveReadsChanged$1
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            ObserverNodeOwnerScope observerNodeOwnerScope = (ObserverNodeOwnerScope) obj;
            if (observerNodeOwnerScope.isValidOwnerScope()) {
                observerNodeOwnerScope.observerNode.onObservedReadsChanged();
            }
            return Unit.INSTANCE;
        }
    };
    public final ObserverModifierNode observerNode;

    public ObserverNodeOwnerScope(ObserverModifierNode observerModifierNode) {
        this.observerNode = observerModifierNode;
    }

    @Override // androidx.compose.ui.node.OwnerScope
    public final boolean isValidOwnerScope() {
        return ((Modifier.Node) this.observerNode).node.isAttached;
    }
}
