package androidx.compose.foundation;

import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.node.LayoutNodeDrawScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class IndicationModifier implements DrawModifier {
    public final IndicationInstance indicationInstance;

    @Override // androidx.compose.ui.draw.DrawModifier
    public final void draw(LayoutNodeDrawScope layoutNodeDrawScope) {
        layoutNodeDrawScope.drawContent();
    }
}
