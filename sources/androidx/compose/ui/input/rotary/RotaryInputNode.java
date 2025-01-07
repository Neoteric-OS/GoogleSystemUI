package androidx.compose.ui.input.rotary;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class RotaryInputNode extends Modifier.Node implements RotaryInputModifierNode {
    public Function1 onEvent;

    @Override // androidx.compose.ui.input.rotary.RotaryInputModifierNode
    public final boolean onRotaryScrollEvent(RotaryScrollEvent rotaryScrollEvent) {
        Function1 function1 = this.onEvent;
        if (function1 != null) {
            return ((Boolean) function1.invoke(rotaryScrollEvent)).booleanValue();
        }
        return false;
    }
}
