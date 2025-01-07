package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class IntrinsicKt {
    public static final Modifier height(Modifier modifier, IntrinsicSize intrinsicSize) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new IntrinsicHeightElement(intrinsicSize));
    }

    public static final Modifier width(Modifier modifier) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new IntrinsicWidthElement());
    }
}
