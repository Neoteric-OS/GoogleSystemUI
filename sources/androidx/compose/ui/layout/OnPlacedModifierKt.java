package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OnPlacedModifierKt {
    public static final Modifier onPlaced(Modifier modifier, Function1 function1) {
        return modifier.then(new OnPlacedElement(function1));
    }
}
