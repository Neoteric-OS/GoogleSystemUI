package com.android.compose.modifiers;

import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SizeModifier extends InspectorValueInfo implements LayoutModifier {
    public final boolean enforceIncoming;
    public final Function1 maxHeight;
    public final Function1 maxWidth;
    public final Function1 minHeight;
    public final Function1 minWidth;

    public SizeModifier(Function1 function1, Function1 function12, Function1 function13, Function1 function14, int i) {
        function1 = (i & 1) != 0 ? SizeKt.SizeUnspecified : function1;
        function12 = (i & 2) != 0 ? SizeKt.SizeUnspecified : function12;
        function13 = (i & 4) != 0 ? SizeKt.SizeUnspecified : function13;
        function14 = (i & 8) != 0 ? SizeKt.SizeUnspecified : function14;
        this.minWidth = function1;
        this.minHeight = function12;
        this.maxWidth = function13;
        this.maxHeight = function14;
        this.enforceIncoming = true;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof SizeModifier)) {
            return false;
        }
        SizeModifier sizeModifier = (SizeModifier) obj;
        return Intrinsics.areEqual(this.minWidth, sizeModifier.minWidth) && Intrinsics.areEqual(this.minHeight, sizeModifier.minHeight) && Intrinsics.areEqual(this.maxWidth, sizeModifier.maxWidth) && Intrinsics.areEqual(this.maxHeight, sizeModifier.maxHeight) && this.enforceIncoming == sizeModifier.enforceIncoming;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x004c, code lost:
    
        if (r5 != Integer.MAX_VALUE) goto L24;
     */
    /* renamed from: getTargetConstraints-OenEA2s, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final long m742getTargetConstraintsOenEA2s(androidx.compose.ui.layout.IntrinsicMeasureScope r8) {
        /*
            r7 = this;
            kotlin.jvm.functions.Function1 r0 = com.android.compose.modifiers.SizeKt.SizeUnspecified
            kotlin.jvm.functions.Function1 r1 = r7.maxWidth
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            r3 = 2147483647(0x7fffffff, float:NaN)
            r4 = 0
            if (r2 != 0) goto L1c
            java.lang.Object r1 = r1.invoke(r8)
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            if (r1 >= 0) goto L1d
            r1 = r4
            goto L1d
        L1c:
            r1 = r3
        L1d:
            kotlin.jvm.functions.Function1 r2 = r7.maxHeight
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r5 != 0) goto L33
            java.lang.Object r2 = r2.invoke(r8)
            java.lang.Number r2 = (java.lang.Number) r2
            int r2 = r2.intValue()
            if (r2 >= 0) goto L34
            r2 = r4
            goto L34
        L33:
            r2 = r3
        L34:
            kotlin.jvm.functions.Function1 r5 = r7.minWidth
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r6 != 0) goto L4f
            java.lang.Object r5 = r5.invoke(r8)
            java.lang.Number r5 = (java.lang.Number) r5
            int r5 = r5.intValue()
            if (r5 <= r1) goto L49
            r5 = r1
        L49:
            if (r5 >= 0) goto L4c
            r5 = r4
        L4c:
            if (r5 == r3) goto L4f
            goto L50
        L4f:
            r5 = r4
        L50:
            kotlin.jvm.functions.Function1 r7 = r7.minHeight
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)
            if (r0 != 0) goto L6b
            java.lang.Object r7 = r7.invoke(r8)
            java.lang.Number r7 = (java.lang.Number) r7
            int r7 = r7.intValue()
            if (r7 <= r2) goto L65
            r7 = r2
        L65:
            if (r7 >= 0) goto L68
            r7 = r4
        L68:
            if (r7 == r3) goto L6b
            r4 = r7
        L6b:
            long r7 = androidx.compose.ui.unit.ConstraintsKt.Constraints(r5, r1, r4, r2)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.compose.modifiers.SizeModifier.m742getTargetConstraintsOenEA2s(androidx.compose.ui.layout.IntrinsicMeasureScope):long");
    }

    public final int hashCode() {
        return ChangeSize$$ExternalSyntheticOutline0.m(this.maxHeight, ChangeSize$$ExternalSyntheticOutline0.m(this.maxWidth, ChangeSize$$ExternalSyntheticOutline0.m(this.minHeight, this.minWidth.hashCode() * 31, 31), 31), 31);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public final int maxIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m742getTargetConstraintsOenEA2s = m742getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m652getHasFixedHeightimpl(m742getTargetConstraintsOenEA2s) ? Constraints.m654getMaxHeightimpl(m742getTargetConstraintsOenEA2s) : ConstraintsKt.m664constrainHeightK40F9xA(m742getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public final int maxIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m742getTargetConstraintsOenEA2s = m742getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m653getHasFixedWidthimpl(m742getTargetConstraintsOenEA2s) ? Constraints.m655getMaxWidthimpl(m742getTargetConstraintsOenEA2s) : ConstraintsKt.m665constrainWidthK40F9xA(m742getTargetConstraintsOenEA2s, intrinsicMeasurable.maxIntrinsicWidth(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo3measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        int m657getMinWidthimpl;
        int m655getMaxWidthimpl;
        int m656getMinHeightimpl;
        int m654getMaxHeightimpl;
        long Constraints;
        MeasureResult layout$1;
        long m742getTargetConstraintsOenEA2s = m742getTargetConstraintsOenEA2s(measureScope);
        if (this.enforceIncoming) {
            Constraints = ConstraintsKt.m663constrainN9IONVI(j, m742getTargetConstraintsOenEA2s);
        } else {
            Function1 function1 = SizeKt.SizeUnspecified;
            if (Intrinsics.areEqual(this.minWidth, function1)) {
                m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
                int m655getMaxWidthimpl2 = Constraints.m655getMaxWidthimpl(m742getTargetConstraintsOenEA2s);
                if (m657getMinWidthimpl > m655getMaxWidthimpl2) {
                    m657getMinWidthimpl = m655getMaxWidthimpl2;
                }
            } else {
                m657getMinWidthimpl = Constraints.m657getMinWidthimpl(m742getTargetConstraintsOenEA2s);
            }
            if (Intrinsics.areEqual(this.maxWidth, function1)) {
                m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(j);
                int m657getMinWidthimpl2 = Constraints.m657getMinWidthimpl(m742getTargetConstraintsOenEA2s);
                if (m655getMaxWidthimpl < m657getMinWidthimpl2) {
                    m655getMaxWidthimpl = m657getMinWidthimpl2;
                }
            } else {
                m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(m742getTargetConstraintsOenEA2s);
            }
            if (Intrinsics.areEqual(this.minHeight, function1)) {
                m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
                int m654getMaxHeightimpl2 = Constraints.m654getMaxHeightimpl(m742getTargetConstraintsOenEA2s);
                if (m656getMinHeightimpl > m654getMaxHeightimpl2) {
                    m656getMinHeightimpl = m654getMaxHeightimpl2;
                }
            } else {
                m656getMinHeightimpl = Constraints.m656getMinHeightimpl(m742getTargetConstraintsOenEA2s);
            }
            if (Intrinsics.areEqual(this.maxHeight, function1)) {
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
                int m656getMinHeightimpl2 = Constraints.m656getMinHeightimpl(m742getTargetConstraintsOenEA2s);
                if (m654getMaxHeightimpl < m656getMinHeightimpl2) {
                    m654getMaxHeightimpl = m656getMinHeightimpl2;
                }
            } else {
                m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(m742getTargetConstraintsOenEA2s);
            }
            Constraints = ConstraintsKt.Constraints(m657getMinWidthimpl, m655getMaxWidthimpl, m656getMinHeightimpl, m654getMaxHeightimpl);
        }
        final Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints);
        layout$1 = measureScope.layout$1(mo479measureBRTryo0.width, mo479measureBRTryo0.height, MapsKt.emptyMap(), new Function1() { // from class: com.android.compose.modifiers.SizeModifier$measure$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                ((Placeable.PlacementScope) obj).placeRelative(Placeable.this, 0, 0, 0.0f);
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public final int minIntrinsicHeight(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m742getTargetConstraintsOenEA2s = m742getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m652getHasFixedHeightimpl(m742getTargetConstraintsOenEA2s) ? Constraints.m654getMaxHeightimpl(m742getTargetConstraintsOenEA2s) : ConstraintsKt.m664constrainHeightK40F9xA(m742getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicHeight(i));
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public final int minIntrinsicWidth(LookaheadCapablePlaceable lookaheadCapablePlaceable, IntrinsicMeasurable intrinsicMeasurable, int i) {
        long m742getTargetConstraintsOenEA2s = m742getTargetConstraintsOenEA2s(lookaheadCapablePlaceable);
        return Constraints.m653getHasFixedWidthimpl(m742getTargetConstraintsOenEA2s) ? Constraints.m655getMaxWidthimpl(m742getTargetConstraintsOenEA2s) : ConstraintsKt.m665constrainWidthK40F9xA(m742getTargetConstraintsOenEA2s, intrinsicMeasurable.minIntrinsicWidth(i));
    }
}
