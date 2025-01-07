package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.draganddrop.DragAndDropEvent;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class DragAndDropTargetNode$createAndAttachDragAndDropModifierNode$1 extends Lambda implements Function1 {
    final /* synthetic */ DragAndDropTargetNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DragAndDropTargetNode$createAndAttachDragAndDropModifierNode$1(DragAndDropTargetNode dragAndDropTargetNode) {
        super(1);
        this.this$0 = dragAndDropTargetNode;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return (Boolean) this.this$0.shouldStartDragAndDrop.invoke((DragAndDropEvent) obj);
    }
}
