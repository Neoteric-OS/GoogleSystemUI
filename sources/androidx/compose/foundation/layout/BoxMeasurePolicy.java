package androidx.compose.foundation.layout;

import androidx.collection.MutableScatterMap;
import androidx.compose.animation.ChangeSize$$ExternalSyntheticOutline0;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref$IntRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class BoxMeasurePolicy implements MeasurePolicy {
    public final BiasAlignment alignment;
    public final boolean propagateMinConstraints;

    public BoxMeasurePolicy(BiasAlignment biasAlignment, boolean z) {
        this.alignment = biasAlignment;
        this.propagateMinConstraints = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BoxMeasurePolicy)) {
            return false;
        }
        BoxMeasurePolicy boxMeasurePolicy = (BoxMeasurePolicy) obj;
        return this.alignment.equals(boxMeasurePolicy.alignment) && this.propagateMinConstraints == boxMeasurePolicy.propagateMinConstraints;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.propagateMinConstraints) + (this.alignment.hashCode() * 31);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(final MeasureScope measureScope, final List list, long j) {
        MeasureResult layout$1;
        int m657getMinWidthimpl;
        int m656getMinHeightimpl;
        Placeable mo479measureBRTryo0;
        MeasureResult layout$12;
        MeasureResult layout$13;
        if (list.isEmpty()) {
            layout$13 = measureScope.layout$1(Constraints.m657getMinWidthimpl(j), Constraints.m656getMinHeightimpl(j), MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$1
                @Override // kotlin.jvm.functions.Function1
                public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return Unit.INSTANCE;
                }
            });
            return layout$13;
        }
        long j2 = this.propagateMinConstraints ? j : j & (-8589934589L);
        if (list.size() == 1) {
            final Measurable measurable = (Measurable) list.get(0);
            MutableScatterMap mutableScatterMap = BoxKt.Cache1;
            Object parentData = measurable.getParentData();
            BoxChildDataNode boxChildDataNode = parentData instanceof BoxChildDataNode ? (BoxChildDataNode) parentData : null;
            if (boxChildDataNode != null ? boxChildDataNode.matchParentSize : false) {
                m657getMinWidthimpl = Constraints.m657getMinWidthimpl(j);
                m656getMinHeightimpl = Constraints.m656getMinHeightimpl(j);
                mo479measureBRTryo0 = measurable.mo479measureBRTryo0(Constraints.Companion.m661fixedJhjzzOo(Constraints.m657getMinWidthimpl(j), Constraints.m656getMinHeightimpl(j)));
            } else {
                mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j2);
                m657getMinWidthimpl = Math.max(Constraints.m657getMinWidthimpl(j), mo479measureBRTryo0.width);
                m656getMinHeightimpl = Math.max(Constraints.m656getMinHeightimpl(j), mo479measureBRTryo0.height);
            }
            final int i = m657getMinWidthimpl;
            final int i2 = m656getMinHeightimpl;
            final Placeable placeable = mo479measureBRTryo0;
            layout$12 = measureScope.layout$1(i, i2, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    BoxKt.access$placeInBox((Placeable.PlacementScope) obj, Placeable.this, measurable, measureScope.getLayoutDirection(), i, i2, this.alignment);
                    return Unit.INSTANCE;
                }
            });
            return layout$12;
        }
        final Placeable[] placeableArr = new Placeable[list.size()];
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        ref$IntRef.element = Constraints.m657getMinWidthimpl(j);
        final Ref$IntRef ref$IntRef2 = new Ref$IntRef();
        ref$IntRef2.element = Constraints.m656getMinHeightimpl(j);
        int size = list.size();
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            Measurable measurable2 = (Measurable) list.get(i3);
            MutableScatterMap mutableScatterMap2 = BoxKt.Cache1;
            Object parentData2 = measurable2.getParentData();
            BoxChildDataNode boxChildDataNode2 = parentData2 instanceof BoxChildDataNode ? (BoxChildDataNode) parentData2 : null;
            if (boxChildDataNode2 != null ? boxChildDataNode2.matchParentSize : false) {
                z = true;
            } else {
                Placeable mo479measureBRTryo02 = measurable2.mo479measureBRTryo0(j2);
                placeableArr[i3] = mo479measureBRTryo02;
                ref$IntRef.element = Math.max(ref$IntRef.element, mo479measureBRTryo02.width);
                ref$IntRef2.element = Math.max(ref$IntRef2.element, mo479measureBRTryo02.height);
            }
        }
        if (z) {
            int i4 = ref$IntRef.element;
            int i5 = i4 != Integer.MAX_VALUE ? i4 : 0;
            int i6 = ref$IntRef2.element;
            long Constraints = ConstraintsKt.Constraints(i5, i4, i6 != Integer.MAX_VALUE ? i6 : 0, i6);
            int size2 = list.size();
            for (int i7 = 0; i7 < size2; i7++) {
                Measurable measurable3 = (Measurable) list.get(i7);
                MutableScatterMap mutableScatterMap3 = BoxKt.Cache1;
                Object parentData3 = measurable3.getParentData();
                BoxChildDataNode boxChildDataNode3 = parentData3 instanceof BoxChildDataNode ? (BoxChildDataNode) parentData3 : null;
                if (boxChildDataNode3 != null ? boxChildDataNode3.matchParentSize : false) {
                    placeableArr[i7] = measurable3.mo479measureBRTryo0(Constraints);
                }
            }
        }
        layout$1 = measureScope.layout$1(ref$IntRef.element, ref$IntRef2.element, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj;
                Placeable[] placeableArr2 = placeableArr;
                List list2 = list;
                MeasureScope measureScope2 = measureScope;
                Ref$IntRef ref$IntRef3 = ref$IntRef;
                Ref$IntRef ref$IntRef4 = ref$IntRef2;
                BoxMeasurePolicy boxMeasurePolicy = this;
                int length = placeableArr2.length;
                int i8 = 0;
                int i9 = 0;
                while (i9 < length) {
                    BoxKt.access$placeInBox(placementScope, placeableArr2[i9], (Measurable) list2.get(i8), measureScope2.getLayoutDirection(), ref$IntRef3.element, ref$IntRef4.element, boxMeasurePolicy.alignment);
                    i9++;
                    i8++;
                }
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BoxMeasurePolicy(alignment=");
        sb.append(this.alignment);
        sb.append(", propagateMinConstraints=");
        return ChangeSize$$ExternalSyntheticOutline0.m(sb, this.propagateMinConstraints, ')');
    }
}
