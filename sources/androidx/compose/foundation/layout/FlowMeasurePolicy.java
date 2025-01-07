package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.collection.IntObjectMapKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.internal.RuntimeHelpersKt;
import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.CrossAxisAlignment;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class FlowMeasurePolicy implements MultiContentMeasurePolicy, FlowLineMeasurePolicy {
    public final CrossAxisAlignment crossAxisAlignment;
    public final float crossAxisArrangementSpacing;
    public final Arrangement.Horizontal horizontalArrangement;
    public final float mainAxisSpacing;
    public final int maxItemsInMainAxis;
    public final int maxLines;
    public final Lambda maxMainAxisIntrinsicItemSize = new Function3() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$maxMainAxisIntrinsicItemSize$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((Number) obj2).intValue();
            return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicWidth(((Number) obj3).intValue()));
        }
    };
    public final Lambda minCrossAxisIntrinsicItemSize = new Function3() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$minCrossAxisIntrinsicItemSize$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((Number) obj2).intValue();
            return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicHeight(((Number) obj3).intValue()));
        }
    };
    public final Lambda minMainAxisIntrinsicItemSize = new Function3() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$minMainAxisIntrinsicItemSize$1
        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            ((Number) obj2).intValue();
            return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicWidth(((Number) obj3).intValue()));
        }
    };
    public final FlowLayoutOverflowState overflow;
    public final Arrangement.Vertical verticalArrangement;

    public FlowMeasurePolicy(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.mainAxisSpacing = f;
        this.crossAxisAlignment = crossAxisAlignment;
        this.crossAxisArrangementSpacing = f2;
        this.maxItemsInMainAxis = i;
        this.maxLines = i2;
        this.overflow = flowLayoutOverflowState;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlowMeasurePolicy)) {
            return false;
        }
        FlowMeasurePolicy flowMeasurePolicy = (FlowMeasurePolicy) obj;
        flowMeasurePolicy.getClass();
        return this.horizontalArrangement.equals(flowMeasurePolicy.horizontalArrangement) && this.verticalArrangement.equals(flowMeasurePolicy.verticalArrangement) && Dp.m668equalsimpl0(this.mainAxisSpacing, flowMeasurePolicy.mainAxisSpacing) && this.crossAxisAlignment.equals(flowMeasurePolicy.crossAxisAlignment) && Dp.m668equalsimpl0(this.crossAxisArrangementSpacing, flowMeasurePolicy.crossAxisArrangementSpacing) && this.maxItemsInMainAxis == flowMeasurePolicy.maxItemsInMainAxis && this.maxLines == flowMeasurePolicy.maxLines && Intrinsics.areEqual(this.overflow, flowMeasurePolicy.overflow);
    }

    public final int hashCode() {
        return this.overflow.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.maxLines, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.maxItemsInMainAxis, FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((((CrossAxisAlignment.VerticalCrossAxisAlignment) this.crossAxisAlignment).vertical.hashCode() + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m((this.verticalArrangement.hashCode() + ((this.horizontalArrangement.hashCode() + (Boolean.hashCode(true) * 31)) * 31)) * 31, this.mainAxisSpacing, 31)) * 31, this.crossAxisArrangementSpacing, 31), 31), 31);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v5, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        List list2 = (List) CollectionsKt.getOrNull(1, list);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(2, list);
        this.overflow.m90setOverflowMeasurableshBUhpc$foundation_layout_release(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, ConstraintsKt.Constraints$default(0, i, 0, 0, 13));
        List list4 = (List) CollectionsKt.firstOrNull(list);
        if (list4 == null) {
            list4 = EmptyList.INSTANCE;
        }
        return (int) (FlowLayoutKt.intrinsicCrossAxisSize(list4, this.minMainAxisIntrinsicItemSize, this.minCrossAxisIntrinsicItemSize, i, intrinsicMeasureScope.mo45roundToPx0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.mo45roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow) >> 32);
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        List list2 = (List) CollectionsKt.getOrNull(1, list);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(2, list);
        this.overflow.m90setOverflowMeasurableshBUhpc$foundation_layout_release(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, ConstraintsKt.Constraints$default(0, 0, 0, i, 7));
        List list4 = (List) CollectionsKt.firstOrNull(list);
        if (list4 == null) {
            list4 = EmptyList.INSTANCE;
        }
        int mo45roundToPx0680j_4 = intrinsicMeasureScope.mo45roundToPx0680j_4(this.mainAxisSpacing);
        ?? r0 = this.maxMainAxisIntrinsicItemSize;
        int size = list4.size();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 < size) {
            int intValue = ((Number) r0.invoke((IntrinsicMeasurable) list4.get(i2), Integer.valueOf(i2), Integer.valueOf(i))).intValue() + mo45roundToPx0680j_4;
            int i6 = i2 + 1;
            if (i6 - i4 == this.maxItemsInMainAxis || i6 == list4.size()) {
                i3 = Math.max(i3, (i5 + intValue) - mo45roundToPx0680j_4);
                i4 = i2;
                i5 = 0;
            } else {
                i5 += intValue;
            }
            i2 = i6;
        }
        return i3;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    /* renamed from: measure-3p2s80s, reason: not valid java name */
    public final MeasureResult mo91measure3p2s80s(MeasureScope measureScope, List list, long j) {
        MeasureResult layout$1;
        long Constraints;
        long Constraints2;
        Measurable measurable;
        Measurable measurable2;
        int i;
        IntIntPair intIntPair;
        FlowMeasurePolicy flowMeasurePolicy;
        int i2;
        MeasureResult layout$12;
        Measurable measurable3;
        Object obj;
        FlowLayoutOverflowState flowLayoutOverflowState;
        IntIntPair intIntPair2;
        Iterator it;
        Integer num;
        IntIntPair intIntPair3;
        int i3;
        FlowLayoutBuildingBlocks.WrapInfo wrapInfo;
        int i4;
        MeasureResult layout$13;
        if (this.maxLines != 0 && this.maxItemsInMainAxis != 0 && !list.isEmpty()) {
            int m654getMaxHeightimpl = Constraints.m654getMaxHeightimpl(j);
            final FlowLayoutOverflowState flowLayoutOverflowState2 = this.overflow;
            if (m654getMaxHeightimpl != 0) {
                List list2 = (List) CollectionsKt.first(list);
                if (list2.isEmpty()) {
                    layout$13 = measureScope.layout$1(0, 0, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$measure$2
                        @Override // kotlin.jvm.functions.Function1
                        public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                            return Unit.INSTANCE;
                        }
                    });
                    return layout$13;
                }
                List list3 = (List) CollectionsKt.getOrNull(1, list);
                Measurable measurable4 = list3 != null ? (Measurable) CollectionsKt.firstOrNull(list3) : null;
                List list4 = (List) CollectionsKt.getOrNull(2, list);
                Measurable measurable5 = list4 != null ? (Measurable) CollectionsKt.firstOrNull(list4) : null;
                list2.size();
                flowLayoutOverflowState2.getClass();
                LayoutOrientation layoutOrientation = LayoutOrientation.Horizontal;
                Constraints = ConstraintsKt.Constraints(0, Constraints.m655getMaxWidthimpl(r8), (r4 & 4) != 0 ? Constraints.m656getMinHeightimpl(r8) : 0, Constraints.m654getMaxHeightimpl(OrientationIndependentConstraints.m94constructorimpl(j, layoutOrientation)));
                long m96toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m96toBoxConstraintsOenEA2s(Constraints);
                if (measurable4 != null) {
                    FlowLayoutKt.m89measureAndCacherqJ1uqs(measurable4, this, m96toBoxConstraintsOenEA2s, new Function1() { // from class: androidx.compose.foundation.layout.FlowLayoutOverflowState$setOverflowMeasurables$3$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            int i5;
                            int i6;
                            Placeable placeable = (Placeable) obj2;
                            if (placeable != null) {
                                this.getClass();
                                i5 = placeable.getMeasuredWidth();
                                i6 = placeable.getMeasuredHeight();
                            } else {
                                i5 = 0;
                                i6 = 0;
                            }
                            FlowLayoutOverflowState.this.seeMoreSize = new IntIntPair(IntIntPair.m0constructorimpl(i5, i6));
                            FlowLayoutOverflowState.this.seeMorePlaceable = placeable;
                            return Unit.INSTANCE;
                        }
                    });
                    flowLayoutOverflowState2.seeMoreMeasurable = measurable4;
                }
                if (measurable5 != null) {
                    FlowLayoutKt.m89measureAndCacherqJ1uqs(measurable5, this, m96toBoxConstraintsOenEA2s, new Function1() { // from class: androidx.compose.foundation.layout.FlowLayoutOverflowState$setOverflowMeasurables$4$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            int i5;
                            int i6;
                            Placeable placeable = (Placeable) obj2;
                            if (placeable != null) {
                                this.getClass();
                                i5 = placeable.getMeasuredWidth();
                                i6 = placeable.getMeasuredHeight();
                            } else {
                                i5 = 0;
                                i6 = 0;
                            }
                            FlowLayoutOverflowState.this.collapseSize = new IntIntPair(IntIntPair.m0constructorimpl(i5, i6));
                            FlowLayoutOverflowState.this.collapsePlaceable = placeable;
                            return Unit.INSTANCE;
                        }
                    });
                    flowLayoutOverflowState2.collapseMeasurable = measurable5;
                }
                Iterator it2 = list2.iterator();
                long m94constructorimpl = OrientationIndependentConstraints.m94constructorimpl(j, layoutOrientation);
                final MutableVector mutableVector = new MutableVector(new MeasureResult[16]);
                int m655getMaxWidthimpl = Constraints.m655getMaxWidthimpl(m94constructorimpl);
                int m657getMinWidthimpl = Constraints.m657getMinWidthimpl(m94constructorimpl);
                int m654getMaxHeightimpl2 = Constraints.m654getMaxHeightimpl(m94constructorimpl);
                MutableIntObjectMap mutableIntObjectMap = IntObjectMapKt.EmptyIntObjectMap;
                MutableIntObjectMap mutableIntObjectMap2 = new MutableIntObjectMap();
                ArrayList arrayList = new ArrayList();
                int ceil = (int) Math.ceil(measureScope.mo51toPx0680j_4(this.mainAxisSpacing));
                int ceil2 = (int) Math.ceil(measureScope.mo51toPx0680j_4(this.crossAxisArrangementSpacing));
                long Constraints3 = ConstraintsKt.Constraints(0, m655getMaxWidthimpl, 0, m654getMaxHeightimpl2);
                Constraints2 = ConstraintsKt.Constraints(0, Constraints.m655getMaxWidthimpl(Constraints3), (r4 & 4) != 0 ? Constraints.m656getMinHeightimpl(Constraints3) : 0, Constraints.m654getMaxHeightimpl(Constraints3));
                long m96toBoxConstraintsOenEA2s2 = OrientationIndependentConstraints.m96toBoxConstraintsOenEA2s(Constraints2);
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                if (it2.hasNext()) {
                    try {
                        measurable = (Measurable) it2.next();
                    } catch (IndexOutOfBoundsException unused) {
                        measurable = null;
                    }
                    measurable2 = measurable;
                } else {
                    measurable2 = null;
                }
                if (measurable2 != null) {
                    i = m657getMinWidthimpl;
                    intIntPair = new IntIntPair(FlowLayoutKt.m89measureAndCacherqJ1uqs(measurable2, this, m96toBoxConstraintsOenEA2s2, new Function1() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$nextSize$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj2) {
                            Ref$ObjectRef.this.element = (Placeable) obj2;
                            return Unit.INSTANCE;
                        }
                    }));
                } else {
                    i = m657getMinWidthimpl;
                    intIntPair = null;
                }
                long j2 = Constraints3;
                Integer valueOf = intIntPair != null ? Integer.valueOf((int) (intIntPair.packedValue >> 32)) : null;
                Integer valueOf2 = intIntPair != null ? Integer.valueOf((int) (intIntPair.packedValue & 4294967295L)) : null;
                int[] iArr = new int[16];
                int[] iArr2 = new int[16];
                Integer num2 = valueOf;
                int i5 = this.maxLines;
                int i6 = this.maxItemsInMainAxis;
                Measurable measurable6 = measurable2;
                FlowLayoutOverflowState flowLayoutOverflowState3 = this.overflow;
                FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i6, flowLayoutOverflowState3, m94constructorimpl, i5, ceil, ceil2);
                int i7 = m655getMaxWidthimpl;
                FlowLayoutBuildingBlocks.WrapInfo m88getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m88getWrapInfoOpUlnko(it2.hasNext(), 0, IntIntPair.m0constructorimpl(i7, m654getMaxHeightimpl2), intIntPair, 0, 0, 0, false, false);
                Integer num3 = valueOf2;
                if (m88getWrapInfoOpUlnko.isLastItemInContainer) {
                    flowLayoutBuildingBlocks.getWrapEllipsisInfo(m88getWrapInfoOpUlnko, intIntPair != null, -1, 0, i7, 0);
                }
                Integer num4 = num3;
                int i8 = m654getMaxHeightimpl2;
                int i9 = i7;
                int i10 = i;
                int[] iArr3 = iArr;
                int i11 = 0;
                int i12 = 0;
                int i13 = 0;
                int i14 = 0;
                int i15 = 0;
                int i16 = 0;
                int i17 = 0;
                int i18 = 0;
                int[] iArr4 = iArr2;
                Measurable measurable7 = measurable6;
                while (!m88getWrapInfoOpUlnko.isLastItemInContainer && measurable7 != null) {
                    Intrinsics.checkNotNull(num2);
                    int intValue = num2.intValue();
                    Intrinsics.checkNotNull(num4);
                    int i19 = i7;
                    int intValue2 = num4.intValue();
                    int i20 = i10;
                    int i21 = i12 + intValue;
                    i18 = Math.max(i18, intValue2);
                    int i22 = i9 - intValue;
                    int i23 = i11 + 1;
                    flowLayoutOverflowState3.getClass();
                    arrayList.add(measurable7);
                    mutableIntObjectMap2.set(i11, ref$ObjectRef.element);
                    int i24 = i23 - i13;
                    if (it2.hasNext()) {
                        try {
                            measurable3 = (Measurable) it2.next();
                        } catch (IndexOutOfBoundsException unused2) {
                            measurable3 = null;
                        }
                        measurable7 = measurable3;
                        obj = null;
                    } else {
                        obj = null;
                        measurable7 = null;
                    }
                    ref$ObjectRef.element = obj;
                    if (measurable7 != null) {
                        flowLayoutOverflowState = flowLayoutOverflowState3;
                        intIntPair2 = new IntIntPair(FlowLayoutKt.m89measureAndCacherqJ1uqs(measurable7, this, m96toBoxConstraintsOenEA2s2, new Function1() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$breakDownItems$1$1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj2) {
                                Ref$ObjectRef.this.element = (Placeable) obj2;
                                return Unit.INSTANCE;
                            }
                        }));
                    } else {
                        flowLayoutOverflowState = flowLayoutOverflowState3;
                        intIntPair2 = null;
                    }
                    Integer valueOf3 = intIntPair2 != null ? Integer.valueOf(((int) (intIntPair2.packedValue >> 32)) + ceil) : null;
                    long j3 = m96toBoxConstraintsOenEA2s2;
                    Integer valueOf4 = intIntPair2 != null ? Integer.valueOf((int) (intIntPair2.packedValue & 4294967295L)) : null;
                    boolean hasNext = it2.hasNext();
                    long m0constructorimpl = IntIntPair.m0constructorimpl(i22, i8);
                    if (intIntPair2 == null) {
                        it = it2;
                        num = valueOf4;
                        intIntPair3 = null;
                    } else {
                        Intrinsics.checkNotNull(valueOf3);
                        int intValue3 = valueOf3.intValue();
                        Intrinsics.checkNotNull(valueOf4);
                        it = it2;
                        num = valueOf4;
                        intIntPair3 = new IntIntPair(IntIntPair.m0constructorimpl(intValue3, valueOf4.intValue()));
                    }
                    FlowLayoutBuildingBlocks.WrapInfo m88getWrapInfoOpUlnko2 = flowLayoutBuildingBlocks.m88getWrapInfoOpUlnko(hasNext, i24, m0constructorimpl, intIntPair3, i15, i14, i18, false, false);
                    if (m88getWrapInfoOpUlnko2.isLastItemInLine) {
                        i3 = i19;
                        int min = Math.min(Math.max(i20, i21), i3);
                        int i25 = i14 + i18;
                        flowLayoutBuildingBlocks.getWrapEllipsisInfo(m88getWrapInfoOpUlnko2, intIntPair2 != null, i15, i25, i22, i24);
                        int i26 = i17;
                        int i27 = i26 + 1;
                        int[] iArr5 = iArr4;
                        int[] copyOf = iArr5.length < i27 ? Arrays.copyOf(iArr5, Math.max(i27, (iArr5.length * 3) / 2)) : iArr5;
                        copyOf[i26] = i18;
                        i17 = i26 + 1;
                        int i28 = (m654getMaxHeightimpl2 - i25) - ceil2;
                        int i29 = i16;
                        int i30 = i29 + 1;
                        wrapInfo = m88getWrapInfoOpUlnko2;
                        int[] iArr6 = iArr3;
                        int[] copyOf2 = iArr6.length < i30 ? Arrays.copyOf(iArr6, Math.max(i30, (iArr6.length * 3) / 2)) : iArr6;
                        copyOf2[i29] = i23;
                        i16 = i29 + 1;
                        i15++;
                        i14 = i25 + ceil2;
                        iArr3 = copyOf2;
                        i10 = min;
                        i9 = i3;
                        num2 = valueOf3 != null ? Integer.valueOf(valueOf3.intValue() - ceil) : null;
                        iArr4 = copyOf;
                        i13 = i23;
                        i4 = 0;
                        i8 = i28;
                        i18 = 0;
                    } else {
                        i3 = i19;
                        wrapInfo = m88getWrapInfoOpUlnko2;
                        i4 = i21;
                        num2 = valueOf3;
                        i10 = i20;
                        i9 = i22;
                    }
                    i12 = i4;
                    i7 = i3;
                    m88getWrapInfoOpUlnko = wrapInfo;
                    flowLayoutOverflowState3 = flowLayoutOverflowState;
                    i11 = i23;
                    m96toBoxConstraintsOenEA2s2 = j3;
                    it2 = it;
                    num4 = num;
                }
                int i31 = i10;
                int[] iArr7 = iArr3;
                int[] iArr8 = iArr4;
                int i32 = i16;
                int i33 = i17;
                int size = arrayList.size();
                Placeable[] placeableArr = new Placeable[size];
                for (int i34 = 0; i34 < size; i34++) {
                    placeableArr[i34] = mutableIntObjectMap2.get(i34);
                }
                int[] iArr9 = new int[i32];
                int[] iArr10 = new int[i32];
                int i35 = i31;
                int i36 = 0;
                int i37 = 0;
                int i38 = 0;
                Placeable[] placeableArr2 = placeableArr;
                while (i36 < i32) {
                    int i39 = iArr7[i36];
                    if (i36 < 0 || i36 >= i33) {
                        RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
                        throw null;
                    }
                    int i40 = iArr8[i36];
                    int i41 = i32;
                    int i42 = ceil;
                    int i43 = i36;
                    ArrayList arrayList2 = arrayList;
                    int i44 = ceil;
                    Placeable[] placeableArr3 = placeableArr2;
                    ArrayList arrayList3 = arrayList;
                    int i45 = i37;
                    Placeable[] placeableArr4 = placeableArr2;
                    int[] iArr11 = iArr10;
                    MeasureResult measure = RowColumnMeasurePolicyKt.measure(this, i35, Constraints.m656getMinHeightimpl(j2), Constraints.m655getMaxWidthimpl(j2), i40, i42, measureScope, arrayList2, placeableArr3, i45, i39, iArr9, i43);
                    int width = measure.getWidth();
                    int height = measure.getHeight();
                    iArr11[i43] = height;
                    i38 += height;
                    i35 = Math.max(i35, width);
                    mutableVector.add(measure);
                    i36 = i43 + 1;
                    iArr10 = iArr11;
                    placeableArr2 = placeableArr4;
                    i37 = i39;
                    ceil = i44;
                    arrayList = arrayList3;
                    j2 = j2;
                    i33 = i33;
                    iArr8 = iArr8;
                    i32 = i41;
                }
                int i46 = i35;
                int[] iArr12 = iArr10;
                if (mutableVector.size == 0) {
                    i2 = 0;
                    i38 = 0;
                    flowMeasurePolicy = this;
                } else {
                    flowMeasurePolicy = this;
                    i2 = i46;
                }
                Arrangement.Vertical vertical = flowMeasurePolicy.verticalArrangement;
                int mo45roundToPx0680j_4 = ((mutableVector.size - 1) * measureScope.mo45roundToPx0680j_4(vertical.mo81getSpacingD9Ej5fM())) + i38;
                int m656getMinHeightimpl = Constraints.m656getMinHeightimpl(m94constructorimpl);
                int m654getMaxHeightimpl3 = Constraints.m654getMaxHeightimpl(m94constructorimpl);
                if (mo45roundToPx0680j_4 < m656getMinHeightimpl) {
                    mo45roundToPx0680j_4 = m656getMinHeightimpl;
                }
                if (mo45roundToPx0680j_4 <= m654getMaxHeightimpl3) {
                    m654getMaxHeightimpl3 = mo45roundToPx0680j_4;
                }
                vertical.arrange(measureScope, m654getMaxHeightimpl3, iArr12, iArr9);
                int m657getMinWidthimpl2 = Constraints.m657getMinWidthimpl(m94constructorimpl);
                int m655getMaxWidthimpl2 = Constraints.m655getMaxWidthimpl(m94constructorimpl);
                if (i2 < m657getMinWidthimpl2) {
                    i2 = m657getMinWidthimpl2;
                }
                if (i2 <= m655getMaxWidthimpl2) {
                    m655getMaxWidthimpl2 = i2;
                }
                layout$12 = measureScope.layout$1(m655getMaxWidthimpl2, m654getMaxHeightimpl3, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$placeHelper$3
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        MutableVector mutableVector2 = MutableVector.this;
                        int i47 = mutableVector2.size;
                        if (i47 > 0) {
                            Object[] objArr = mutableVector2.content;
                            int i48 = 0;
                            do {
                                ((MeasureResult) objArr[i48]).placeChildren();
                                i48++;
                            } while (i48 < i47);
                        }
                        return Unit.INSTANCE;
                    }
                });
                return layout$12;
            }
            flowLayoutOverflowState2.getClass();
        }
        layout$1 = measureScope.layout$1(0, 0, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.foundation.layout.FlowMeasurePolicy$measure$1
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                return Unit.INSTANCE;
            }
        });
        return layout$1;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r2v5, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        List list2 = (List) CollectionsKt.getOrNull(1, list);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(2, list);
        this.overflow.m90setOverflowMeasurableshBUhpc$foundation_layout_release(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, ConstraintsKt.Constraints$default(0, i, 0, 0, 13));
        List list4 = (List) CollectionsKt.firstOrNull(list);
        if (list4 == null) {
            list4 = EmptyList.INSTANCE;
        }
        return (int) (FlowLayoutKt.intrinsicCrossAxisSize(list4, this.minMainAxisIntrinsicItemSize, this.minCrossAxisIntrinsicItemSize, i, intrinsicMeasureScope.mo45roundToPx0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.mo45roundToPx0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow) >> 32);
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    /* JADX WARN: Type inference failed for: r8v4, types: [kotlin.jvm.functions.Function3, kotlin.jvm.internal.Lambda] */
    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
    public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        List list2 = (List) CollectionsKt.getOrNull(1, list);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(2, list);
        this.overflow.m90setOverflowMeasurableshBUhpc$foundation_layout_release(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, ConstraintsKt.Constraints$default(0, 0, 0, i, 7));
        List list4 = (List) CollectionsKt.firstOrNull(list);
        if (list4 == null) {
            list4 = EmptyList.INSTANCE;
        }
        int mo45roundToPx0680j_4 = intrinsicMeasureScope.mo45roundToPx0680j_4(this.mainAxisSpacing);
        int mo45roundToPx0680j_42 = intrinsicMeasureScope.mo45roundToPx0680j_4(this.crossAxisArrangementSpacing);
        ?? r6 = this.minMainAxisIntrinsicItemSize;
        ?? r8 = this.minCrossAxisIntrinsicItemSize;
        if (list4.isEmpty()) {
            return 0;
        }
        int size = list4.size();
        final int[] iArr = new int[size];
        int size2 = list4.size();
        final int[] iArr2 = new int[size2];
        int size3 = list4.size();
        for (int i2 = 0; i2 < size3; i2++) {
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) list4.get(i2);
            int intValue = ((Number) r6.invoke(intrinsicMeasurable2, Integer.valueOf(i2), Integer.valueOf(i))).intValue();
            iArr[i2] = intValue;
            iArr2[i2] = ((Number) r8.invoke(intrinsicMeasurable2, Integer.valueOf(i2), Integer.valueOf(intValue))).intValue();
        }
        int i3 = this.maxItemsInMainAxis;
        int i4 = this.maxLines;
        int i5 = Integer.MAX_VALUE;
        if (i4 != Integer.MAX_VALUE && i3 != Integer.MAX_VALUE) {
            i5 = i3 * i4;
        }
        int size4 = list4.size();
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        if (i5 < size4) {
            flowLayoutOverflowState.getClass();
        }
        if (i5 >= list4.size()) {
            flowLayoutOverflowState.getClass();
        }
        int min = Math.min(i5, list4.size());
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            i6 += iArr[i7];
        }
        int size5 = ((list4.size() - 1) * mo45roundToPx0680j_4) + i6;
        if (size2 == 0) {
            throw new NoSuchElementException();
        }
        int i8 = iArr2[0];
        IntProgressionIterator it = new IntRange(1, size2 - 1, 1).iterator();
        while (it.hasNext) {
            int i9 = iArr2[it.nextInt()];
            if (i8 < i9) {
                i8 = i9;
            }
        }
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int i10 = iArr[0];
        IntProgressionIterator it2 = new IntRange(1, size - 1, 1).iterator();
        while (it2.hasNext) {
            int i11 = iArr[it2.nextInt()];
            if (i10 < i11) {
                i10 = i11;
            }
        }
        int i12 = size5;
        while (i10 <= size5 && i8 != i) {
            int i13 = (i10 + size5) / 2;
            int[] iArr3 = iArr2;
            int[] iArr4 = iArr;
            long intrinsicCrossAxisSize = FlowLayoutKt.intrinsicCrossAxisSize(list4, new Function3() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int intValue2 = ((Number) obj2).intValue();
                    ((Number) obj3).intValue();
                    return Integer.valueOf(iArr[intValue2]);
                }
            }, new Function3() { // from class: androidx.compose.foundation.layout.FlowLayoutKt$intrinsicCrossAxisSize$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    int intValue2 = ((Number) obj2).intValue();
                    ((Number) obj3).intValue();
                    return Integer.valueOf(iArr2[intValue2]);
                }
            }, i13, mo45roundToPx0680j_4, mo45roundToPx0680j_42, i3, i4, flowLayoutOverflowState);
            i8 = (int) (intrinsicCrossAxisSize >> 32);
            int i14 = (int) (intrinsicCrossAxisSize & 4294967295L);
            if (i8 > i || i14 < min) {
                i10 = i13 + 1;
                if (i10 > size5) {
                    return i10;
                }
            } else {
                if (i8 >= i) {
                    return i13;
                }
                size5 = i13 - 1;
            }
            i12 = i13;
            iArr2 = iArr3;
            iArr = iArr4;
        }
        return i12;
    }

    public final String toString() {
        return "FlowMeasurePolicy(isHorizontal=true, horizontalArrangement=" + this.horizontalArrangement + ", verticalArrangement=" + this.verticalArrangement + ", mainAxisSpacing=" + ((Object) Dp.m669toStringimpl(this.mainAxisSpacing)) + ", crossAxisAlignment=" + this.crossAxisAlignment + ", crossAxisArrangementSpacing=" + ((Object) Dp.m669toStringimpl(this.crossAxisArrangementSpacing)) + ", maxItemsInMainAxis=" + this.maxItemsInMainAxis + ", maxLines=" + this.maxLines + ", overflow=" + this.overflow + ')';
    }
}
