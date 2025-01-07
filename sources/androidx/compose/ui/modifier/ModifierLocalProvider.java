package androidx.compose.ui.modifier;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.ui.Modifier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ModifierLocalProvider extends Modifier.Element {
    ProvidableModifierLocal getKey();

    WindowInsets getValue$1();
}
