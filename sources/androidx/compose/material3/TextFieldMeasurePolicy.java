package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.BiasAlignment;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class TextFieldMeasurePolicy implements MeasurePolicy {
    public final TextFieldLabelPosition labelPosition;
    public final float labelProgress;
    public final float minimizedLabelHalfHeight;
    public final PaddingValues paddingValues;
    public final boolean singleLine;

    public TextFieldMeasurePolicy(boolean z, TextFieldLabelPosition textFieldLabelPosition, float f, PaddingValues paddingValues, float f2) {
        this.singleLine = z;
        this.labelPosition = textFieldLabelPosition;
        this.labelProgress = f;
        this.paddingValues = paddingValues;
        this.minimizedLabelHalfHeight = f2;
    }

    public static int intrinsicWidth(List list, int i, Function2 function2) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            Object obj7 = list.get(i2);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj7), "TextField")) {
                int intValue = ((Number) function2.invoke(obj7, Integer.valueOf(i))).intValue();
                int size2 = list.size();
                int i3 = 0;
                while (true) {
                    obj = null;
                    if (i3 >= size2) {
                        obj2 = null;
                        break;
                    }
                    obj2 = list.get(i3);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj2), "Label")) {
                        break;
                    }
                    i3++;
                }
                IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj2;
                int intValue2 = intrinsicMeasurable != null ? ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue() : 0;
                int size3 = list.size();
                int i4 = 0;
                while (true) {
                    if (i4 >= size3) {
                        obj3 = null;
                        break;
                    }
                    obj3 = list.get(i4);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj3), "Trailing")) {
                        break;
                    }
                    i4++;
                }
                IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj3;
                int intValue3 = intrinsicMeasurable2 != null ? ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(i))).intValue() : 0;
                int size4 = list.size();
                int i5 = 0;
                while (true) {
                    if (i5 >= size4) {
                        obj4 = null;
                        break;
                    }
                    obj4 = list.get(i5);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj4), "Prefix")) {
                        break;
                    }
                    i5++;
                }
                IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
                int intValue4 = intrinsicMeasurable3 != null ? ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(i))).intValue() : 0;
                int size5 = list.size();
                int i6 = 0;
                while (true) {
                    if (i6 >= size5) {
                        obj5 = null;
                        break;
                    }
                    obj5 = list.get(i6);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj5), "Suffix")) {
                        break;
                    }
                    i6++;
                }
                IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj5;
                int intValue5 = intrinsicMeasurable4 != null ? ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(i))).intValue() : 0;
                int size6 = list.size();
                int i7 = 0;
                while (true) {
                    if (i7 >= size6) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i7);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj6), "Leading")) {
                        break;
                    }
                    i7++;
                }
                IntrinsicMeasurable intrinsicMeasurable5 = (IntrinsicMeasurable) obj6;
                int intValue6 = intrinsicMeasurable5 != null ? ((Number) function2.invoke(intrinsicMeasurable5, Integer.valueOf(i))).intValue() : 0;
                int size7 = list.size();
                int i8 = 0;
                while (true) {
                    if (i8 >= size7) {
                        break;
                    }
                    Object obj8 = list.get(i8);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj8), "Hint")) {
                        obj = obj8;
                        break;
                    }
                    i8++;
                }
                IntrinsicMeasurable intrinsicMeasurable6 = (IntrinsicMeasurable) obj;
                int i9 = intValue4 + intValue5;
                return Math.max(Math.max(intValue + i9, Math.max((intrinsicMeasurable6 != null ? ((Number) function2.invoke(intrinsicMeasurable6, Integer.valueOf(i))).intValue() : 0) + i9, intValue2)) + intValue6 + intValue3, Constraints.m657getMinWidthimpl(ConstraintsKt.Constraints$default(0, 0, 0, 0, 15)));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static final int placeWithoutLabel$calculateVerticalPosition(TextFieldMeasurePolicy textFieldMeasurePolicy, int i, int i2, Placeable placeable) {
        return textFieldMeasurePolicy.singleLine ? Alignment.Companion.CenterVertically.align(placeable.height, i) : i2;
    }

    /* renamed from: calculateHeight-aSWTPvA$1, reason: not valid java name */
    public final int m240calculateHeightaSWTPvA$1(IntrinsicMeasureScope intrinsicMeasureScope, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        PaddingValues paddingValues = this.paddingValues;
        int mo45roundToPx0680j_4 = intrinsicMeasureScope.mo45roundToPx0680j_4(paddingValues.mo103calculateBottomPaddingD9Ej5fM() + paddingValues.mo106calculateTopPaddingD9Ej5fM());
        int i9 = 0;
        float f = this.labelProgress;
        int maxOf = ComparisonsKt___ComparisonsJvmKt.maxOf(new int[]{i7, i5, i6, MathHelpersKt.lerp(i2, f, 0)}, i);
        if (i2 > 0) {
            i9 = Math.max(intrinsicMeasureScope.mo45roundToPx0680j_4(this.minimizedLabelHalfHeight * 2), MathHelpersKt.lerp(0, MotionTokens.EasingEmphasizedAccelerateCubicBezier.transform(f), i2));
        }
        return Math.max(Constraints.m656getMinHeightimpl(j), Math.max(i3, Math.max(i4, mo45roundToPx0680j_4 + i9 + maxOf)) + i8);
    }

    public final int intrinsicHeight$1(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i, Function2 function2) {
        Object obj;
        int i2;
        int i3;
        Object obj2;
        int i4;
        Object obj3;
        Object obj4;
        int i5;
        Object obj5;
        int i6;
        Object obj6;
        Object obj7;
        int size = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i7);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj), "Leading")) {
                break;
            }
            i7++;
        }
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) obj;
        if (intrinsicMeasurable != null) {
            i3 = LayoutUtilKt.subtractConstraintSafely(i, intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE));
            i2 = ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue();
        } else {
            i2 = 0;
            i3 = i;
        }
        int size2 = list.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i8);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj2), "Trailing")) {
                break;
            }
            i8++;
        }
        IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) obj2;
        if (intrinsicMeasurable2 != null) {
            i3 = LayoutUtilKt.subtractConstraintSafely(i3, intrinsicMeasurable2.maxIntrinsicWidth(Integer.MAX_VALUE));
            i4 = ((Number) function2.invoke(intrinsicMeasurable2, Integer.valueOf(i))).intValue();
        } else {
            i4 = 0;
        }
        int size3 = list.size();
        int i9 = 0;
        while (true) {
            if (i9 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i9);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj3), "Label")) {
                break;
            }
            i9++;
        }
        Object obj8 = (IntrinsicMeasurable) obj3;
        int intValue = obj8 != null ? ((Number) function2.invoke(obj8, Integer.valueOf(i3))).intValue() : 0;
        int size4 = list.size();
        int i10 = 0;
        while (true) {
            if (i10 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i10);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj4), "Prefix")) {
                break;
            }
            i10++;
        }
        IntrinsicMeasurable intrinsicMeasurable3 = (IntrinsicMeasurable) obj4;
        if (intrinsicMeasurable3 != null) {
            int intValue2 = ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(i3))).intValue();
            i3 = LayoutUtilKt.subtractConstraintSafely(i3, intrinsicMeasurable3.maxIntrinsicWidth(Integer.MAX_VALUE));
            i5 = intValue2;
        } else {
            i5 = 0;
        }
        int size5 = list.size();
        int i11 = 0;
        while (true) {
            if (i11 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i11);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj5), "Suffix")) {
                break;
            }
            i11++;
        }
        IntrinsicMeasurable intrinsicMeasurable4 = (IntrinsicMeasurable) obj5;
        if (intrinsicMeasurable4 != null) {
            int intValue3 = ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(i3))).intValue();
            i3 = LayoutUtilKt.subtractConstraintSafely(i3, intrinsicMeasurable4.maxIntrinsicWidth(Integer.MAX_VALUE));
            i6 = intValue3;
        } else {
            i6 = 0;
        }
        int size6 = list.size();
        for (int i12 = 0; i12 < size6; i12++) {
            Object obj9 = list.get(i12);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj9), "TextField")) {
                int intValue4 = ((Number) function2.invoke(obj9, Integer.valueOf(i3))).intValue();
                int size7 = list.size();
                int i13 = 0;
                while (true) {
                    if (i13 >= size7) {
                        obj6 = null;
                        break;
                    }
                    obj6 = list.get(i13);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj6), "Hint")) {
                        break;
                    }
                    i13++;
                }
                Object obj10 = (IntrinsicMeasurable) obj6;
                int intValue5 = obj10 != null ? ((Number) function2.invoke(obj10, Integer.valueOf(i3))).intValue() : 0;
                int size8 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size8) {
                        obj7 = null;
                        break;
                    }
                    obj7 = list.get(i14);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj7), "Supporting")) {
                        break;
                    }
                    i14++;
                }
                Object obj11 = (IntrinsicMeasurable) obj7;
                return m240calculateHeightaSWTPvA$1(intrinsicMeasureScope, intValue4, intValue, i2, i4, i5, i6, intValue5, obj11 != null ? ((Number) function2.invoke(obj11, Integer.valueOf(i))).intValue() : 0, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight$1(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicHeight(((Number) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$maxIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicWidth(((Number) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* renamed from: measure-3p2s80s */
    public final MeasureResult mo2measure3p2s80s(final MeasureScope measureScope, List list, long j) {
        Object obj;
        Object obj2;
        int i;
        Placeable placeable;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        MeasureResult layout$1;
        PaddingValues paddingValues = this.paddingValues;
        int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(paddingValues.mo106calculateTopPaddingD9Ej5fM());
        int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(paddingValues.mo103calculateBottomPaddingD9Ej5fM());
        long m648copyZbe2FdA$default = Constraints.m648copyZbe2FdA$default(j, 0, 0, 0, 0, 10);
        int size = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                obj = null;
                break;
            }
            obj = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj), "Leading")) {
                break;
            }
            i2++;
        }
        Measurable measurable = (Measurable) obj;
        Placeable mo479measureBRTryo0 = measurable != null ? measurable.mo479measureBRTryo0(m648copyZbe2FdA$default) : null;
        int widthOrZero = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo0);
        int max = Math.max(0, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo0));
        int size2 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj2), "Trailing")) {
                break;
            }
            i3++;
        }
        Measurable measurable2 = (Measurable) obj2;
        if (measurable2 != null) {
            i = mo45roundToPx0680j_4;
            placeable = measurable2.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero, 0, m648copyZbe2FdA$default, 2));
        } else {
            i = mo45roundToPx0680j_4;
            placeable = null;
        }
        int widthOrZero2 = LayoutUtilKt.getWidthOrZero(placeable) + widthOrZero;
        int max2 = Math.max(max, LayoutUtilKt.getHeightOrZero(placeable));
        int size3 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj3), "Prefix")) {
                break;
            }
            i4++;
        }
        Measurable measurable3 = (Measurable) obj3;
        Placeable mo479measureBRTryo02 = measurable3 != null ? measurable3.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero2, 0, m648copyZbe2FdA$default, 2)) : null;
        int widthOrZero3 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo02) + widthOrZero2;
        int max3 = Math.max(max2, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo02));
        int size4 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj4), "Suffix")) {
                break;
            }
            i5++;
        }
        Measurable measurable4 = (Measurable) obj4;
        Placeable mo479measureBRTryo03 = measurable4 != null ? measurable4.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero3, 0, m648copyZbe2FdA$default, 2)) : null;
        int widthOrZero4 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo03) + widthOrZero3;
        int max4 = Math.max(max3, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo03));
        int size5 = list.size();
        int i6 = 0;
        while (true) {
            if (i6 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i6);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj5), "Label")) {
                break;
            }
            i6++;
        }
        Measurable measurable5 = (Measurable) obj5;
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        int i7 = -widthOrZero4;
        ref$ObjectRef.element = measurable5 != null ? measurable5.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(i7, -mo45roundToPx0680j_42, m648copyZbe2FdA$default)) : null;
        int size6 = list.size();
        int i8 = 0;
        while (true) {
            if (i8 >= size6) {
                obj6 = null;
                break;
            }
            obj6 = list.get(i8);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj6), "Supporting")) {
                break;
            }
            i8++;
        }
        Measurable measurable6 = (Measurable) obj6;
        int minIntrinsicHeight = measurable6 != null ? measurable6.minIntrinsicHeight(Constraints.m657getMinWidthimpl(j)) : 0;
        int heightOrZero = LayoutUtilKt.getHeightOrZero((Placeable) ref$ObjectRef.element) + i;
        long m666offsetNN6EwU = ConstraintsKt.m666offsetNN6EwU(i7, ((-heightOrZero) - mo45roundToPx0680j_42) - minIntrinsicHeight, Constraints.m648copyZbe2FdA$default(j, 0, 0, 0, 0, 11));
        int size7 = list.size();
        int i9 = 0;
        while (i9 < size7) {
            int i10 = size7;
            Measurable measurable7 = (Measurable) list.get(i9);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable7), "TextField")) {
                final Placeable mo479measureBRTryo04 = measurable7.mo479measureBRTryo0(m666offsetNN6EwU);
                long m648copyZbe2FdA$default2 = Constraints.m648copyZbe2FdA$default(m666offsetNN6EwU, 0, 0, 0, 0, 14);
                int size8 = list.size();
                int i11 = 0;
                while (true) {
                    if (i11 >= size8) {
                        obj7 = null;
                        break;
                    }
                    Object obj8 = list.get(i11);
                    int i12 = size8;
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj8), "Hint")) {
                        obj7 = obj8;
                        break;
                    }
                    i11++;
                    size8 = i12;
                }
                Measurable measurable8 = (Measurable) obj7;
                Placeable mo479measureBRTryo05 = measurable8 != null ? measurable8.mo479measureBRTryo0(m648copyZbe2FdA$default2) : null;
                int max5 = Math.max(max4, Math.max(LayoutUtilKt.getHeightOrZero(mo479measureBRTryo04), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo05)) + heightOrZero + mo45roundToPx0680j_42);
                int widthOrZero5 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo0);
                int widthOrZero6 = LayoutUtilKt.getWidthOrZero(placeable);
                int widthOrZero7 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo02) + LayoutUtilKt.getWidthOrZero(mo479measureBRTryo03);
                final int max6 = Math.max(Math.max(mo479measureBRTryo04.width + widthOrZero7, Math.max(LayoutUtilKt.getWidthOrZero(mo479measureBRTryo05) + widthOrZero7, LayoutUtilKt.getWidthOrZero((Placeable) ref$ObjectRef.element))) + widthOrZero5 + widthOrZero6, Constraints.m657getMinWidthimpl(j));
                Placeable mo479measureBRTryo06 = measurable6 != null ? measurable6.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(ConstraintsKt.m667offsetNN6EwU$default(0, -max5, m648copyZbe2FdA$default, 1), 0, max6, 0, 0, 9)) : null;
                int heightOrZero2 = LayoutUtilKt.getHeightOrZero(mo479measureBRTryo06);
                final Placeable placeable2 = mo479measureBRTryo02;
                final int m240calculateHeightaSWTPvA$1 = m240calculateHeightaSWTPvA$1(measureScope, mo479measureBRTryo04.height, LayoutUtilKt.getHeightOrZero((Placeable) ref$ObjectRef.element), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo0), LayoutUtilKt.getHeightOrZero(placeable), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo02), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo03), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo05), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo06), j);
                final int i13 = m240calculateHeightaSWTPvA$1 - heightOrZero2;
                int size9 = list.size();
                for (int i14 = 0; i14 < size9; i14++) {
                    Measurable measurable9 = (Measurable) list.get(i14);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable9), "Container")) {
                        final Placeable mo479measureBRTryo07 = measurable9.mo479measureBRTryo0(ConstraintsKt.Constraints(max6 != Integer.MAX_VALUE ? max6 : 0, max6, i13 != Integer.MAX_VALUE ? i13 : 0, i13));
                        final int i15 = i;
                        final Placeable placeable3 = mo479measureBRTryo05;
                        final Placeable placeable4 = mo479measureBRTryo0;
                        final Placeable placeable5 = placeable;
                        final Placeable placeable6 = mo479measureBRTryo03;
                        final Placeable placeable7 = mo479measureBRTryo06;
                        layout$1 = measureScope.layout$1(max6, m240calculateHeightaSWTPvA$1, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$measure$1
                            final /* synthetic */ boolean $isLabelAbove = false;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj9) {
                                float f;
                                float f2;
                                int align;
                                Placeable placeable8;
                                int i16;
                                int i17;
                                int i18;
                                Placeable placeable9;
                                float f3;
                                Placeable placeable10;
                                float f4;
                                Placeable.PlacementScope placementScope = (Placeable.PlacementScope) obj9;
                                Object obj10 = Ref$ObjectRef.this.element;
                                BiasAlignment.Vertical vertical = Alignment.Companion.CenterVertically;
                                if (obj10 != null) {
                                    if (this.$isLabelAbove) {
                                        align = 0;
                                    } else {
                                        TextFieldMeasurePolicy textFieldMeasurePolicy = this;
                                        align = textFieldMeasurePolicy.singleLine ? vertical.align(((Placeable) obj10).height, i13) : i15 + measureScope.mo45roundToPx0680j_4(textFieldMeasurePolicy.minimizedLabelHalfHeight);
                                    }
                                    boolean z = this.$isLabelAbove;
                                    int i19 = z ? 0 : i15;
                                    TextFieldMeasurePolicy textFieldMeasurePolicy2 = this;
                                    int i20 = max6;
                                    int i21 = m240calculateHeightaSWTPvA$1;
                                    Placeable placeable11 = mo479measureBRTryo04;
                                    Placeable placeable12 = (Placeable) Ref$ObjectRef.this.element;
                                    Placeable placeable13 = placeable3;
                                    Placeable placeable14 = placeable4;
                                    Placeable placeable15 = placeable5;
                                    Placeable placeable16 = placeable2;
                                    Placeable placeable17 = placeable6;
                                    Placeable placeable18 = mo479measureBRTryo07;
                                    Placeable placeable19 = placeable7;
                                    int i22 = i15;
                                    if (z) {
                                        placeable8 = placeable16;
                                        i16 = 0;
                                    } else {
                                        placeable8 = placeable16;
                                        i16 = placeable12.height;
                                    }
                                    int i23 = i22 + i16;
                                    LayoutDirection layoutDirection = measureScope.getLayoutDirection();
                                    textFieldMeasurePolicy2.getClass();
                                    if (z) {
                                        i18 = placeable12.height;
                                        i17 = i23;
                                    } else {
                                        i17 = i23;
                                        i18 = 0;
                                    }
                                    placementScope.place(placeable18, 0, i18, 0.0f);
                                    int heightOrZero3 = (i21 - LayoutUtilKt.getHeightOrZero(placeable19)) - (z ? placeable12.height : 0);
                                    if (placeable14 != null) {
                                        placeable9 = placeable19;
                                        placementScope.placeRelative(placeable14, 0, vertical.align(placeable14.height, heightOrZero3) + i18, 0.0f);
                                    } else {
                                        placeable9 = placeable19;
                                    }
                                    float f5 = textFieldMeasurePolicy2.labelProgress;
                                    int lerp = MathHelpersKt.lerp(align, f5, i19);
                                    TextFieldLabelPosition textFieldLabelPosition = textFieldMeasurePolicy2.labelPosition;
                                    if (z) {
                                        placementScope.place(placeable12, ((TextFieldLabelPosition.Default) textFieldLabelPosition).minimizedAlignment.align(placeable12.width, i20, layoutDirection), lerp, 0.0f);
                                        f3 = 0.0f;
                                    } else {
                                        int widthOrZero8 = layoutDirection == LayoutDirection.Ltr ? LayoutUtilKt.getWidthOrZero(placeable14) : LayoutUtilKt.getWidthOrZero(placeable15);
                                        TextFieldLabelPosition.Default r7 = (TextFieldLabelPosition.Default) textFieldLabelPosition;
                                        int lerp2 = MathHelpersKt.lerp(r7.expandedAlignment.align(placeable12.width, (i20 - LayoutUtilKt.getWidthOrZero(placeable14)) - LayoutUtilKt.getWidthOrZero(placeable15), layoutDirection) + widthOrZero8, f5, r7.minimizedAlignment.align(placeable12.width, (i20 - LayoutUtilKt.getWidthOrZero(placeable14)) - LayoutUtilKt.getWidthOrZero(placeable15), layoutDirection) + widthOrZero8);
                                        f3 = 0.0f;
                                        placementScope.place(placeable12, lerp2, lerp, 0.0f);
                                    }
                                    if (placeable8 != null) {
                                        placeable10 = placeable8;
                                        placementScope.placeRelative(placeable10, LayoutUtilKt.getWidthOrZero(placeable14), i18 + i17, f3);
                                    } else {
                                        placeable10 = placeable8;
                                    }
                                    int widthOrZero9 = LayoutUtilKt.getWidthOrZero(placeable10) + LayoutUtilKt.getWidthOrZero(placeable14);
                                    int i24 = i18 + i17;
                                    placementScope.placeRelative(placeable11, widthOrZero9, i24, f3);
                                    if (placeable13 != null) {
                                        placementScope.placeRelative(placeable13, widthOrZero9, i24, f3);
                                    }
                                    if (placeable17 != null) {
                                        placementScope.placeRelative(placeable17, (i20 - LayoutUtilKt.getWidthOrZero(placeable15)) - placeable17.width, i24, 0.0f);
                                    }
                                    if (placeable15 != null) {
                                        f4 = 0.0f;
                                        placementScope.placeRelative(placeable15, i20 - placeable15.width, vertical.align(placeable15.height, heightOrZero3) + i18, 0.0f);
                                    } else {
                                        f4 = 0.0f;
                                    }
                                    if (placeable9 != null) {
                                        placementScope.placeRelative(placeable9, 0, i18 + heightOrZero3, f4);
                                    }
                                } else {
                                    TextFieldMeasurePolicy textFieldMeasurePolicy3 = this;
                                    int i25 = max6;
                                    int i26 = m240calculateHeightaSWTPvA$1;
                                    Placeable placeable20 = mo479measureBRTryo04;
                                    Placeable placeable21 = placeable3;
                                    Placeable placeable22 = placeable4;
                                    Placeable placeable23 = placeable5;
                                    Placeable placeable24 = placeable2;
                                    Placeable placeable25 = placeable6;
                                    Placeable placeable26 = mo479measureBRTryo07;
                                    Placeable placeable27 = placeable7;
                                    float density = measureScope.getDensity();
                                    textFieldMeasurePolicy3.getClass();
                                    Placeable.PlacementScope.m495place70tqf50$default(placementScope, placeable26, 0L);
                                    int heightOrZero4 = i26 - LayoutUtilKt.getHeightOrZero(placeable27);
                                    int roundToInt = MathKt.roundToInt(textFieldMeasurePolicy3.paddingValues.mo106calculateTopPaddingD9Ej5fM() * density);
                                    if (placeable22 != null) {
                                        f = 0.0f;
                                        placementScope.placeRelative(placeable22, 0, vertical.align(placeable22.height, heightOrZero4), 0.0f);
                                    } else {
                                        f = 0.0f;
                                    }
                                    if (placeable24 != null) {
                                        placementScope.placeRelative(placeable24, LayoutUtilKt.getWidthOrZero(placeable22), TextFieldMeasurePolicy.placeWithoutLabel$calculateVerticalPosition(textFieldMeasurePolicy3, heightOrZero4, roundToInt, placeable24), f);
                                    }
                                    int widthOrZero10 = LayoutUtilKt.getWidthOrZero(placeable24) + LayoutUtilKt.getWidthOrZero(placeable22);
                                    placementScope.placeRelative(placeable20, widthOrZero10, TextFieldMeasurePolicy.placeWithoutLabel$calculateVerticalPosition(textFieldMeasurePolicy3, heightOrZero4, roundToInt, placeable20), f);
                                    if (placeable21 != null) {
                                        placementScope.placeRelative(placeable21, widthOrZero10, TextFieldMeasurePolicy.placeWithoutLabel$calculateVerticalPosition(textFieldMeasurePolicy3, heightOrZero4, roundToInt, placeable21), f);
                                    }
                                    if (placeable25 != null) {
                                        placementScope.placeRelative(placeable25, (i25 - LayoutUtilKt.getWidthOrZero(placeable23)) - placeable25.width, TextFieldMeasurePolicy.placeWithoutLabel$calculateVerticalPosition(textFieldMeasurePolicy3, heightOrZero4, roundToInt, placeable25), 0.0f);
                                    }
                                    if (placeable23 != null) {
                                        f2 = 0.0f;
                                        placementScope.placeRelative(placeable23, i25 - placeable23.width, vertical.align(placeable23.height, heightOrZero4), 0.0f);
                                    } else {
                                        f2 = 0.0f;
                                    }
                                    if (placeable27 != null) {
                                        placementScope.placeRelative(placeable27, 0, heightOrZero4, f2);
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        });
                        return layout$1;
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i9++;
            size7 = i10;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight$1(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicHeight(((Number) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(list, i, new Function2() { // from class: androidx.compose.material3.TextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicWidth(((Number) obj2).intValue()));
            }
        });
    }
}
