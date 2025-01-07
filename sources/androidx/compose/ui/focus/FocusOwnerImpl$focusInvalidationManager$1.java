package androidx.compose.ui.focus;

import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final /* synthetic */ class FocusOwnerImpl$focusInvalidationManager$1 extends FunctionReferenceImpl implements Function0 {
    @Override // kotlin.jvm.functions.Function0
    public final Object invoke() {
        FocusOwnerImpl focusOwnerImpl = (FocusOwnerImpl) this.receiver;
        if (focusOwnerImpl.rootFocusNode.getFocusState() == FocusStateImpl.Inactive) {
            focusOwnerImpl.onClearFocusForOwner.invoke();
        }
        return Unit.INSTANCE;
    }
}
