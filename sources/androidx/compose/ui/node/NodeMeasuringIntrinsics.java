package androidx.compose.ui.node;

import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.ApproachIntrinsicsMeasureScope;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class NodeMeasuringIntrinsics {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public interface ApproachMeasureBlock {
        /* renamed from: measure-3p2s80s */
        MeasureResult mo474measure3p2s80s(ApproachIntrinsicsMeasureScope approachIntrinsicsMeasureScope, Measurable measurable, long j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
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
        /* renamed from: measure-BRTryo0 */
        public final Placeable mo479measureBRTryo0(long j) {
            IntrinsicWidthHeight intrinsicWidthHeight = IntrinsicWidthHeight.Width;
            IntrinsicMinMax intrinsicMinMax = IntrinsicMinMax.Max;
            IntrinsicMinMax intrinsicMinMax2 = this.minMax;
            IntrinsicWidthHeight intrinsicWidthHeight2 = this.widthHeight;
            IntrinsicMeasurable intrinsicMeasurable = this.measurable;
            if (intrinsicWidthHeight2 == intrinsicWidthHeight) {
                return new EmptyPlaceable(intrinsicMinMax2 == intrinsicMinMax ? intrinsicMeasurable.maxIntrinsicWidth(Constraints.m654getMaxHeightimpl(j)) : intrinsicMeasurable.minIntrinsicWidth(Constraints.m654getMaxHeightimpl(j)), Constraints.m650getHasBoundedHeightimpl(j) ? Constraints.m654getMaxHeightimpl(j) : 32767);
            }
            return new EmptyPlaceable(Constraints.m651getHasBoundedWidthimpl(j) ? Constraints.m655getMaxWidthimpl(j) : 32767, intrinsicMinMax2 == intrinsicMinMax ? intrinsicMeasurable.maxIntrinsicHeight(Constraints.m655getMaxWidthimpl(j)) : intrinsicMeasurable.minIntrinsicHeight(Constraints.m655getMaxWidthimpl(j)));
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IntrinsicMinMax {
        public static final /* synthetic */ IntrinsicMinMax[] $VALUES;
        public static final IntrinsicMinMax Max;
        public static final IntrinsicMinMax Min;

        static {
            IntrinsicMinMax intrinsicMinMax = new IntrinsicMinMax("Min", 0);
            Min = intrinsicMinMax;
            IntrinsicMinMax intrinsicMinMax2 = new IntrinsicMinMax("Max", 1);
            Max = intrinsicMinMax2;
            $VALUES = new IntrinsicMinMax[]{intrinsicMinMax, intrinsicMinMax2};
        }

        public static IntrinsicMinMax valueOf(String str) {
            return (IntrinsicMinMax) Enum.valueOf(IntrinsicMinMax.class, str);
        }

        public static IntrinsicMinMax[] values() {
            return (IntrinsicMinMax[]) $VALUES.clone();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class IntrinsicWidthHeight {
        public static final /* synthetic */ IntrinsicWidthHeight[] $VALUES;
        public static final IntrinsicWidthHeight Height;
        public static final IntrinsicWidthHeight Width;

        static {
            IntrinsicWidthHeight intrinsicWidthHeight = new IntrinsicWidthHeight("Width", 0);
            Width = intrinsicWidthHeight;
            IntrinsicWidthHeight intrinsicWidthHeight2 = new IntrinsicWidthHeight("Height", 1);
            Height = intrinsicWidthHeight2;
            $VALUES = new IntrinsicWidthHeight[]{intrinsicWidthHeight, intrinsicWidthHeight2};
        }

        public static IntrinsicWidthHeight valueOf(String str) {
            return (IntrinsicWidthHeight) Enum.valueOf(IntrinsicWidthHeight.class, str);
        }

        public static IntrinsicWidthHeight[] values() {
            return (IntrinsicWidthHeight[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class EmptyPlaceable extends Placeable {
        public EmptyPlaceable(int i, int i2) {
            m493setMeasuredSizeozmzZPI((i2 & 4294967295L) | (i << 32));
        }

        @Override // androidx.compose.ui.layout.Measured
        public final int get(AlignmentLine alignmentLine) {
            return Integer.MIN_VALUE;
        }

        @Override // androidx.compose.ui.layout.Placeable
        /* renamed from: placeAt-f8xVGno */
        public final void mo480placeAtf8xVGno(long j, float f, Function1 function1) {
        }
    }
}
