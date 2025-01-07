package androidx.compose.foundation.layout;

import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class OrientationIndependentConstraints {
    /* renamed from: constructor-impl, reason: not valid java name */
    public static long m94constructorimpl(long j, LayoutOrientation layoutOrientation) {
        LayoutOrientation layoutOrientation2 = LayoutOrientation.Horizontal;
        return ConstraintsKt.Constraints(layoutOrientation == layoutOrientation2 ? Constraints.m657getMinWidthimpl(j) : Constraints.m656getMinHeightimpl(j), layoutOrientation == layoutOrientation2 ? Constraints.m655getMaxWidthimpl(j) : Constraints.m654getMaxHeightimpl(j), layoutOrientation == layoutOrientation2 ? Constraints.m656getMinHeightimpl(j) : Constraints.m657getMinWidthimpl(j), layoutOrientation == layoutOrientation2 ? Constraints.m654getMaxHeightimpl(j) : Constraints.m655getMaxWidthimpl(j));
    }

    /* renamed from: toBoxConstraints-OenEA2s, reason: not valid java name */
    public static final long m96toBoxConstraintsOenEA2s(long j) {
        return ConstraintsKt.Constraints(Constraints.m657getMinWidthimpl(j), Constraints.m655getMaxWidthimpl(j), Constraints.m656getMinHeightimpl(j), Constraints.m654getMaxHeightimpl(j));
    }
}
