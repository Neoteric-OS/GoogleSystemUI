package androidx.compose.ui.focus;

import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusOwnerImpl$focusSearch$1 extends Lambda implements Function1 {
    final /* synthetic */ Function1 $onFound;
    final /* synthetic */ FocusTargetNode $source;
    final /* synthetic */ FocusOwnerImpl this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FocusOwnerImpl$focusSearch$1(FocusTargetNode focusTargetNode, FocusOwnerImpl focusOwnerImpl, Function1 function1) {
        super(1);
        this.$source = focusTargetNode;
        this.this$0 = focusOwnerImpl;
        this.$onFound = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        boolean booleanValue;
        FocusTargetNode focusTargetNode = (FocusTargetNode) obj;
        if (Intrinsics.areEqual(focusTargetNode, this.$source)) {
            booleanValue = false;
        } else {
            if (Intrinsics.areEqual(focusTargetNode, this.this$0.rootFocusNode)) {
                throw new IllegalStateException("Focus search landed at the root.");
            }
            booleanValue = ((Boolean) this.$onFound.invoke(focusTargetNode)).booleanValue();
        }
        return Boolean.valueOf(booleanValue);
    }
}
