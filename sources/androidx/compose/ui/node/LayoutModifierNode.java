package androidx.compose.ui.node;

import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicsMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.unit.ConstraintsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LayoutModifierNode extends DelegatableNode {
    default int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo6measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Max, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
    }

    default int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo6measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Max, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
    }

    /* renamed from: measure-3p2s80s */
    MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j);

    default int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo6measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Min, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
    }

    default int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo6measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Min, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
    }
}
