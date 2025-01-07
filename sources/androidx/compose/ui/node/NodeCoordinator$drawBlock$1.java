package androidx.compose.ui.node;

import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.platform.AndroidComposeView;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class NodeCoordinator$drawBlock$1 extends Lambda implements Function2 {
    final /* synthetic */ Function0 $drawBlockCallToDrawModifiers;
    final /* synthetic */ NodeCoordinator this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NodeCoordinator$drawBlock$1(NodeCoordinator nodeCoordinator, Function0 function0) {
        super(2);
        this.this$0 = nodeCoordinator;
        this.$drawBlockCallToDrawModifiers = function0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Object obj2) {
        Canvas canvas = (Canvas) obj;
        GraphicsLayer graphicsLayer = (GraphicsLayer) obj2;
        if (this.this$0.layoutNode.isPlaced()) {
            NodeCoordinator nodeCoordinator = this.this$0;
            nodeCoordinator.drawBlockCanvas = canvas;
            nodeCoordinator.drawBlockParentLayer = graphicsLayer;
            ((AndroidComposeView) LayoutNodeKt.requireOwner(nodeCoordinator.layoutNode)).snapshotObserver.observeReads$ui_release(this.this$0, NodeCoordinator.onCommitAffectingLayer, this.$drawBlockCallToDrawModifiers);
            this.this$0.lastLayerDrawingWasSkipped = false;
        } else {
            this.this$0.lastLayerDrawingWasSkipped = true;
        }
        return Unit.INSTANCE;
    }
}
