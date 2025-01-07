package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.LayoutNodeDrawScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DrawModifier extends Modifier.Element {
    void draw(LayoutNodeDrawScope layoutNodeDrawScope);
}
