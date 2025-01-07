package androidx.compose.foundation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.InspectableValueKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BackgroundKt {
    public static final Modifier background(Modifier modifier, Brush brush, Shape shape, float f) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new BackgroundElement(0L, brush, f, shape, 1));
    }

    /* renamed from: background-bw27NRU, reason: not valid java name */
    public static final Modifier m25backgroundbw27NRU(Modifier modifier, long j, Shape shape) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new BackgroundElement(j, null, 1.0f, shape, 2));
    }
}
