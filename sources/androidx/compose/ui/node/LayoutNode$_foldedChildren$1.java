package androidx.compose.ui.node;

import androidx.compose.ui.node.LayoutNodeLayoutDelegate;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class LayoutNode$_foldedChildren$1 extends Lambda implements Function0 {
    final /* synthetic */ LayoutNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LayoutNode$_foldedChildren$1(LayoutNode layoutNode) {
        super(0);
        this.this$0 = layoutNode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        LayoutNodeLayoutDelegate layoutNodeLayoutDelegate = this.this$0.layoutDelegate;
        layoutNodeLayoutDelegate.measurePassDelegate.childDelegatesDirty = true;
        LayoutNodeLayoutDelegate.LookaheadPassDelegate lookaheadPassDelegate = layoutNodeLayoutDelegate.lookaheadPassDelegate;
        if (lookaheadPassDelegate != null) {
            lookaheadPassDelegate.childDelegatesDirty = true;
        }
        return Unit.INSTANCE;
    }
}
