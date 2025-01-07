package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class BoxScopeInstance implements BoxScope {
    public static final BoxScopeInstance INSTANCE = new BoxScopeInstance();

    @Override // androidx.compose.foundation.layout.BoxScope
    public final Modifier align(Modifier modifier, Alignment alignment) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new BoxChildDataElement(alignment, false));
    }

    @Override // androidx.compose.foundation.layout.BoxScope
    public final Modifier matchParentSize(Modifier modifier) {
        BiasAlignment biasAlignment = Alignment.Companion.Center;
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new BoxChildDataElement(biasAlignment, true));
    }
}
