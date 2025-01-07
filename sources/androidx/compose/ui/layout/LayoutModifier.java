package androidx.compose.ui.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.ConstraintsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LayoutModifier extends Modifier.Element {
    default int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo3measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new MeasuringIntrinsics$DefaultIntrinsicMeasurable(intrinsicMeasurable, MeasuringIntrinsics$IntrinsicMinMax.Max, MeasuringIntrinsics$IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
    }

    default int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo3measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new MeasuringIntrinsics$DefaultIntrinsicMeasurable(intrinsicMeasurable, MeasuringIntrinsics$IntrinsicMinMax.Max, MeasuringIntrinsics$IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
    }

    /* renamed from: measure-3p2s80s */
    MeasureResult mo3measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j);

    default int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo3measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new MeasuringIntrinsics$DefaultIntrinsicMeasurable(intrinsicMeasurable, MeasuringIntrinsics$IntrinsicMinMax.Min, MeasuringIntrinsics$IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
    }

    default int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return mo3measure3p2s80s(new IntrinsicsMeasureScope(lookaheadCapablePlaceable, lookaheadCapablePlaceable.getLayoutDirection()), new MeasuringIntrinsics$DefaultIntrinsicMeasurable(intrinsicMeasurable, MeasuringIntrinsics$IntrinsicMinMax.Min, MeasuringIntrinsics$IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
    }
}
