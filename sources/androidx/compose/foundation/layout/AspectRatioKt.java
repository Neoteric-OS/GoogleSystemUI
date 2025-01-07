package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.unit.Constraints;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class AspectRatioKt {
    public static Modifier aspectRatio$default(Modifier modifier, float f) {
        Function1 function1 = InspectableValueKt.NoInspectorInfo;
        return modifier.then(new AspectRatioElement(f));
    }

    /* renamed from: isSatisfiedBy-NN6Ew-U, reason: not valid java name */
    public static final boolean m82isSatisfiedByNN6EwU(int i, int i2, long j) {
        int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
        if (i <= Constraints.m655getMaxWidthimpl(j) && m657getMinWidthimpl <= i) {
            int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
            if (i2 <= Constraints.m654getMaxHeightimpl(j) && m656getMinHeightimpl <= i2) {
                return true;
            }
        }
        return false;
    }
}
