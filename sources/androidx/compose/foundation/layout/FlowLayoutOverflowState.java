package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.FlowLayoutOverflow;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlowLayoutOverflowState {
    public Measurable collapseMeasurable;
    public Placeable collapsePlaceable;
    public IntIntPair collapseSize;
    public Measurable seeMoreMeasurable;
    public Placeable seeMorePlaceable;
    public IntIntPair seeMoreSize;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlowLayoutOverflowState)) {
            return false;
        }
        ((FlowLayoutOverflowState) obj).getClass();
        return true;
    }

    public final int hashCode() {
        return Integer.hashCode(0) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(0, FlowLayoutOverflow.OverflowType.Clip.hashCode() * 31, 31);
    }

    /* renamed from: setOverflowMeasurables--hBUhpc$foundation_layout_release, reason: not valid java name */
    public final void m90setOverflowMeasurableshBUhpc$foundation_layout_release(IntrinsicMeasurable intrinsicMeasurable, IntrinsicMeasurable intrinsicMeasurable2, long j) {
        long m94constructorimpl = OrientationIndependentConstraints.m94constructorimpl(j, LayoutOrientation.Horizontal);
        if (intrinsicMeasurable != null) {
            int minIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(Constraints.m654getMaxHeightimpl(m94constructorimpl));
            new IntIntPair(IntIntPair.m0constructorimpl(minIntrinsicWidth, intrinsicMeasurable.minIntrinsicHeight(minIntrinsicWidth)));
            if (intrinsicMeasurable instanceof Measurable) {
            }
        }
        if (intrinsicMeasurable2 != null) {
            int minIntrinsicWidth2 = intrinsicMeasurable2.minIntrinsicWidth(Constraints.m654getMaxHeightimpl(m94constructorimpl));
            new IntIntPair(IntIntPair.m0constructorimpl(minIntrinsicWidth2, intrinsicMeasurable2.minIntrinsicHeight(minIntrinsicWidth2)));
            if (intrinsicMeasurable2 instanceof Measurable) {
            }
        }
    }

    public final String toString() {
        return "FlowLayoutOverflowState(type=" + FlowLayoutOverflow.OverflowType.Clip + ", minLinesToShowCollapse=0, minCrossAxisSizeToShowCollapse=0)";
    }
}
