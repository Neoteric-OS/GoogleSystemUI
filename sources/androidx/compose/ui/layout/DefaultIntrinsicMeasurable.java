package androidx.compose.ui.layout;

import androidx.compose.ui.unit.Constraints;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DefaultIntrinsicMeasurable implements Measurable {
    public final IntrinsicMeasurable measurable;
    public final IntrinsicMinMax minMax;
    public final IntrinsicWidthHeight widthHeight;

    public DefaultIntrinsicMeasurable(IntrinsicMeasurable intrinsicMeasurable, IntrinsicMinMax intrinsicMinMax, IntrinsicWidthHeight intrinsicWidthHeight) {
        this.measurable = intrinsicMeasurable;
        this.minMax = intrinsicMinMax;
        this.widthHeight = intrinsicWidthHeight;
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final Object getParentData() {
        return this.measurable.getParentData();
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicHeight(int i) {
        return this.measurable.maxIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int maxIntrinsicWidth(int i) {
        return this.measurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.layout.Measurable
    /* renamed from: measure-BRTryo0, reason: not valid java name */
    public final Placeable mo479measureBRTryo0(long j) {
        IntrinsicWidthHeight intrinsicWidthHeight = IntrinsicWidthHeight.Width;
        IntrinsicMinMax intrinsicMinMax = IntrinsicMinMax.Max;
        IntrinsicMinMax intrinsicMinMax2 = this.minMax;
        IntrinsicWidthHeight intrinsicWidthHeight2 = this.widthHeight;
        IntrinsicMeasurable intrinsicMeasurable = this.measurable;
        if (intrinsicWidthHeight2 == intrinsicWidthHeight) {
            return new FixedSizeIntrinsicsPlaceable(intrinsicMinMax2 == intrinsicMinMax ? intrinsicMeasurable.maxIntrinsicWidth(Constraints.m654getMaxHeightimpl(j)) : intrinsicMeasurable.minIntrinsicWidth(Constraints.m654getMaxHeightimpl(j)), Constraints.m650getHasBoundedHeightimpl(j) ? Constraints.m654getMaxHeightimpl(j) : 32767);
        }
        return new FixedSizeIntrinsicsPlaceable(Constraints.m651getHasBoundedWidthimpl(j) ? Constraints.m655getMaxWidthimpl(j) : 32767, intrinsicMinMax2 == intrinsicMinMax ? intrinsicMeasurable.maxIntrinsicHeight(Constraints.m655getMaxWidthimpl(j)) : intrinsicMeasurable.minIntrinsicHeight(Constraints.m655getMaxWidthimpl(j)));
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicHeight(int i) {
        return this.measurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.layout.IntrinsicMeasurable
    public final int minIntrinsicWidth(int i) {
        return this.measurable.minIntrinsicWidth(i);
    }
}
