package androidx.compose.ui.focus;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FocusChangedNode extends Modifier.Node implements FocusEventModifierNode {
    public FocusStateImpl focusState;
    public Function1 onFocusChanged;

    @Override // androidx.compose.ui.focus.FocusEventModifierNode
    public final void onFocusEvent(FocusStateImpl focusStateImpl) {
        if (Intrinsics.areEqual(this.focusState, focusStateImpl)) {
            return;
        }
        this.focusState = focusStateImpl;
        this.onFocusChanged.invoke(focusStateImpl);
    }
}
