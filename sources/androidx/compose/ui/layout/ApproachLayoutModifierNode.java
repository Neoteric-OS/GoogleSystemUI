package androidx.compose.ui.layout;

import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.NodeMeasuringIntrinsics;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ApproachLayoutModifierNode extends LayoutModifierNode {
    /* renamed from: approachMeasure-3p2s80s, reason: not valid java name */
    MeasureResult mo472approachMeasure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j);

    /* renamed from: isMeasurementApproachInProgress-ozmzZPI, reason: not valid java name */
    boolean mo473isMeasurementApproachInProgressozmzZPI(long j);

    default boolean isPlacementApproachInProgress(Placeable.PlacementScope placementScope, LayoutCoordinates layoutCoordinates) {
        return false;
    }

    default int maxApproachIntrinsicHeight(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int height;
        height = new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$maxApproachIntrinsicHeight$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
            /* renamed from: measure-3p2s80s, reason: not valid java name */
            public final MeasureResult mo474measure3p2s80s(ApproachIntrinsicsMeasureScope approachIntrinsicsMeasureScope, Measurable measurable, long j) {
                return ApproachLayoutModifierNode.this.mo472approachMeasure3p2s80s(approachIntrinsicsMeasureScope, measurable, j);
            }
        }.mo474measure3p2s80s(new ApproachIntrinsicsMeasureScope(approachIntrinsicMeasureScope, approachIntrinsicMeasureScope.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Max, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
        return height;
    }

    default int maxApproachIntrinsicWidth(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int width;
        width = new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$maxApproachIntrinsicWidth$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo474measure3p2s80s(ApproachIntrinsicsMeasureScope approachIntrinsicsMeasureScope, Measurable measurable, long j) {
                return ApproachLayoutModifierNode.this.mo472approachMeasure3p2s80s(approachIntrinsicsMeasureScope, measurable, j);
            }
        }.mo474measure3p2s80s(new ApproachIntrinsicsMeasureScope(approachIntrinsicMeasureScope, approachIntrinsicMeasureScope.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Max, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
        return width;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* renamed from: measure-3p2s80s */
    default MeasureResult mo6measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        MeasureResult layout$1;
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$measure$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).place(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    default int minApproachIntrinsicHeight(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int height;
        height = new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$minApproachIntrinsicHeight$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo474measure3p2s80s(ApproachIntrinsicsMeasureScope approachIntrinsicsMeasureScope, Measurable measurable, long j) {
                return ApproachLayoutModifierNode.this.mo472approachMeasure3p2s80s(approachIntrinsicsMeasureScope, measurable, j);
            }
        }.mo474measure3p2s80s(new ApproachIntrinsicsMeasureScope(approachIntrinsicMeasureScope, approachIntrinsicMeasureScope.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Min, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Height), ConstraintsKt.Constraints$default(0, i, 0, 0, 13)).getHeight();
        return height;
    }

    default int minApproachIntrinsicWidth(ApproachIntrinsicMeasureScope approachIntrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        int width;
        width = new NodeMeasuringIntrinsics.ApproachMeasureBlock() { // from class: androidx.compose.ui.layout.ApproachLayoutModifierNode$minApproachIntrinsicWidth$1
            @Override // androidx.compose.ui.node.NodeMeasuringIntrinsics.ApproachMeasureBlock
            /* renamed from: measure-3p2s80s */
            public final MeasureResult mo474measure3p2s80s(ApproachIntrinsicsMeasureScope approachIntrinsicsMeasureScope, Measurable measurable, long j) {
                return ApproachLayoutModifierNode.this.mo472approachMeasure3p2s80s(approachIntrinsicsMeasureScope, measurable, j);
            }
        }.mo474measure3p2s80s(new ApproachIntrinsicsMeasureScope(approachIntrinsicMeasureScope, approachIntrinsicMeasureScope.getLayoutDirection()), new NodeMeasuringIntrinsics.DefaultIntrinsicMeasurable(intrinsicMeasurable, NodeMeasuringIntrinsics.IntrinsicMinMax.Min, NodeMeasuringIntrinsics.IntrinsicWidthHeight.Width), ConstraintsKt.Constraints$default(0, 0, 0, i, 7)).getWidth();
        return width;
    }
}
