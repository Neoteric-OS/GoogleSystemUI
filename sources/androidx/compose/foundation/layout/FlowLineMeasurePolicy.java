package androidx.compose.foundation.layout;

import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface FlowLineMeasurePolicy extends RowColumnMeasurePolicy {
    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    /* renamed from: createConstraints-xF2OJ5Q */
    default long mo87createConstraintsxF2OJ5Q(int i, int i2, int i3, boolean z) {
        RowMeasurePolicy rowMeasurePolicy = RowKt.DefaultRowMeasurePolicy;
        return !z ? ConstraintsKt.Constraints(i, i2, 0, i3) : Constraints.Companion.m660fitPrioritizingWidthZbe2FdA(i, i2, 0, i3);
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int crossAxisSize(Placeable placeable) {
        return placeable.getMeasuredHeight();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default int mainAxisSize(Placeable placeable) {
        return placeable.getMeasuredWidth();
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default MeasureResult placeHelper(final Placeable[] placeableArr, MeasureScope measureScope, final int[] iArr, int i, final int i2, final int[] iArr2, final int i3, final int i4, final int i5) {
        MeasureResult layout$1;
        final LayoutDirection layoutDirection = LayoutDirection.Ltr;
        layout$1 = measureScope.layout$1(i, i2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FlowLineMeasurePolicy$placeHelper$1$1
            final /* synthetic */ int $beforeCrossAxisAlignmentLine = 0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                CrossAxisAlignment crossAxisAlignment;
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                int[] iArr3 = iArr2;
                int i6 = iArr3 != null ? iArr3[i3] : 0;
                for (int i7 = i4; i7 < i5; i7++) {
                    Placeable placeable = placeableArr[i7];
                    Intrinsics.checkNotNull(placeable);
                    FlowLineMeasurePolicy flowLineMeasurePolicy = this;
                    int i8 = i2;
                    LayoutDirection layoutDirection2 = layoutDirection;
                    flowLineMeasurePolicy.getClass();
                    Object parentData = placeable.getParentData();
                    RowColumnParentData rowColumnParentData = parentData instanceof RowColumnParentData ? (RowColumnParentData) parentData : null;
                    if (rowColumnParentData == null || (crossAxisAlignment = rowColumnParentData.crossAxisAlignment) == null) {
                        crossAxisAlignment = ((FlowMeasurePolicy) flowLineMeasurePolicy).crossAxisAlignment;
                    }
                    int align$foundation_layout_release = crossAxisAlignment.align$foundation_layout_release(i8 - placeable.getMeasuredHeight(), layoutDirection2) + i6;
                    ((FlowMeasurePolicy) this).getClass();
                    placementScope.place(placeable, iArr[i7 - i4], align$foundation_layout_release, 0.0f);
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.foundation.layout.RowColumnMeasurePolicy
    default void populateMainAxisPositions(int i, int[] iArr, int[] iArr2, MeasureScope measureScope) {
        ((FlowMeasurePolicy) this).horizontalArrangement.arrange(measureScope, i, iArr, measureScope.getLayoutDirection(), iArr2);
    }
}
