package androidx.compose.foundation;

import androidx.compose.ui.layout.PinnableContainerKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusableNode$retrievePinnableContainer$1 extends Lambda implements Function0 {
    final /* synthetic */ Ref$ObjectRef $container;
    final /* synthetic */ FocusableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusableNode$retrievePinnableContainer$1(Ref$ObjectRef ref$ObjectRef, FocusableNode focusableNode) {
        super(0);
        this.$container = ref$ObjectRef;
        this.this$0 = focusableNode;
    }

    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        this.$container.element = CompositionLocalConsumerModifierNodeKt.currentValueOf(this.this$0, PinnableContainerKt.LocalPinnableContainer);
        return Unit.INSTANCE;
    }
}
