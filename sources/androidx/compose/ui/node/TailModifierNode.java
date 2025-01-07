package androidx.compose.ui.node;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TailModifierNode extends Modifier.Node {
    public boolean attachHasBeenRun;

    @Override // androidx.compose.ui.Modifier.Node
    public final void onAttach() {
        this.attachHasBeenRun = true;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public final void onDetach() {
        this.attachHasBeenRun = false;
    }

    public final String toString() {
        return "<tail>";
    }
}
