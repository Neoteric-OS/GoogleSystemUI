package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowRowScopeInstance implements RowScope, FlowRowScope {
    public static final FlowRowScopeInstance INSTANCE = new FlowRowScopeInstance();

    @Override // androidx.compose.foundation.layout.RowScope
    public final Modifier weight(Modifier modifier, float f, boolean z) {
        return RowScopeInstance.INSTANCE.weight(modifier, f, z);
    }
}
