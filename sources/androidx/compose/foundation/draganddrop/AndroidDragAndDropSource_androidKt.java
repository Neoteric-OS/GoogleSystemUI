package androidx.compose.foundation.draganddrop;

import androidx.compose.ui.Modifier;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AndroidDragAndDropSource_androidKt {
    public static final Modifier dragAndDropSource(Modifier modifier, Function2 function2) {
        return modifier.then(new LegacyDragAndDropSourceWithDefaultShadowElement(function2));
    }
}
