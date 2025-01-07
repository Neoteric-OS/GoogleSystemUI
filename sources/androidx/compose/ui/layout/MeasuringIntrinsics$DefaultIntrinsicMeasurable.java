package androidx.compose.ui.layout;

import androidx.compose.ui.unit.Constraints;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MeasuringIntrinsics$DefaultIntrinsicMeasurable implements Measurable {
    public final IntrinsicMeasurable measurable;
    public final MeasuringIntrinsics$IntrinsicMinMax minMax;
    public final MeasuringIntrinsics$IntrinsicWidthHeight widthHeight;

    public MeasuringIntrinsics$DefaultIntrinsicMeasurable(IntrinsicMeasurable intrinsicMeasurable, MeasuringIntrinsics$IntrinsicMinMax measuringIntrinsics$IntrinsicMinMax, MeasuringIntrinsics$IntrinsicWidthHeight measuringIntrinsics$IntrinsicWidthHeight) {
        this.measurable = intrinsicMeasurable;
        this.minMax = measuringIntrinsics$IntrinsicMinMax;
        this.widthHeight = measuringIntrinsics$IntrinsicWidthHeight;
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
    /* renamed from: measure-BRTryo0 */
    public final Placeable mo479measureBRTryo0(long j) {
        final int m655getMaxWidthimpl;
        MeasuringIntrinsics$IntrinsicWidthHeight measuringIntrinsics$IntrinsicWidthHeight = MeasuringIntrinsics$IntrinsicWidthHeight.Width;
        MeasuringIntrinsics$IntrinsicMinMax measuringIntrinsics$IntrinsicMinMax = MeasuringIntrinsics$IntrinsicMinMax.Max;
        MeasuringIntrinsics$IntrinsicMinMax measuringIntrinsics$IntrinsicMinMax2 = this.minMax;
        MeasuringIntrinsics$IntrinsicWidthHeight measuringIntrinsics$IntrinsicWidthHeight2 = this.widthHeight;
        IntrinsicMeasurable intrinsicMeasurable = this.measurable;
        if (measuringIntrinsics$IntrinsicWidthHeight2 == measuringIntrinsics$IntrinsicWidthHeight) {
            final int maxIntrinsicWidth = measuringIntrinsics$IntrinsicMinMax2 == measuringIntrinsics$IntrinsicMinMax ? intrinsicMeasurable.maxIntrinsicWidth(Constraints.m654getMaxHeightimpl(j)) : intrinsicMeasurable.minIntrinsicWidth(Constraints.m654getMaxHeightimpl(j));
            m655getMaxWidthimpl = Constraints.m650getHasBoundedHeightimpl(j) ? Constraints.m654getMaxHeightimpl(j) : 32767;
            return new Placeable(maxIntrinsicWidth, m655getMaxWidthimpl) { // from class: androidx.compose.ui.layout.MeasuringIntrinsics$EmptyPlaceable
                {
                    m493setMeasuredSizeozmzZPI((m655getMaxWidthimpl & 4294967295L) | (maxIntrinsicWidth << 32));
                }

                @Override // androidx.compose.ui.layout.Measured
                public final int get(AlignmentLine alignmentLine) {
                    return Integer.MIN_VALUE;
                }

                @Override // androidx.compose.ui.layout.Placeable
                /* renamed from: placeAt-f8xVGno */
                public final void mo480placeAtf8xVGno(long j2, float f, Function1 function1) {
                }
            };
        }
        final int maxIntrinsicHeight = measuringIntrinsics$IntrinsicMinMax2 == measuringIntrinsics$IntrinsicMinMax ? intrinsicMeasurable.maxIntrinsicHeight(Constraints.m655getMaxWidthimpl(j)) : intrinsicMeasurable.minIntrinsicHeight(Constraints.m655getMaxWidthimpl(j));
        m655getMaxWidthimpl = Constraints.m651getHasBoundedWidthimpl(j) ? Constraints.m655getMaxWidthimpl(j) : 32767;
        return new Placeable(m655getMaxWidthimpl, maxIntrinsicHeight) { // from class: androidx.compose.ui.layout.MeasuringIntrinsics$EmptyPlaceable
            {
                m493setMeasuredSizeozmzZPI((maxIntrinsicHeight & 4294967295L) | (m655getMaxWidthimpl << 32));
            }

            @Override // androidx.compose.ui.layout.Measured
            public final int get(AlignmentLine alignmentLine) {
                return Integer.MIN_VALUE;
            }

            @Override // androidx.compose.ui.layout.Placeable
            /* renamed from: placeAt-f8xVGno */
            public final void mo480placeAtf8xVGno(long j2, float f, Function1 function1) {
            }
        };
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
