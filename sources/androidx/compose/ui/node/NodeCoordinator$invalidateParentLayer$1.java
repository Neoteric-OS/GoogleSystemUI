package androidx.compose.ui.node;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NodeCoordinator$invalidateParentLayer$1 extends Lambda implements Function0 {
    final /* synthetic */ NodeCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NodeCoordinator$invalidateParentLayer$1(NodeCoordinator nodeCoordinator) {
        super(0);
        this.this$0 = nodeCoordinator;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        NodeCoordinator nodeCoordinator = this.this$0.wrappedBy;
        if (nodeCoordinator != null) {
            nodeCoordinator.invalidateLayer();
        }
        return Unit.INSTANCE;
    }
}
