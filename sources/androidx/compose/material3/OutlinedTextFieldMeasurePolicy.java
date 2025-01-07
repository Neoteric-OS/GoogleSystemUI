package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
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
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt___ComparisonsJvmKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.math.MathKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class OutlinedTextFieldMeasurePolicy implements MeasurePolicy {
    public final float horizontalIconPadding;
    public final TextFieldLabelPosition labelPosition;
    public final float labelProgress;
    public final Function1 onLabelMeasured;
    public final PaddingValues paddingValues;
    public final boolean singleLine;

    public OutlinedTextFieldMeasurePolicy(Function1 function1, boolean z, TextFieldLabelPosition textFieldLabelPosition, float f, PaddingValues paddingValues, float f2) {
        this.onLabelMeasured = function1;
        this.singleLine = z;
        this.labelPosition = textFieldLabelPosition;
        this.labelProgress = f;
        this.paddingValues = paddingValues;
        this.horizontalIconPadding = f2;
    }

    public static final int place$calculateVerticalPosition(int i, OutlinedTextFieldMeasurePolicy outlinedTextFieldMeasurePolicy, int i2, int i3, Placeable placeable, Placeable placeable2) {
        if (outlinedTextFieldMeasurePolicy.singleLine) {
            i3 = Alignment.Companion.CenterVertically.align(placeable2.height, i2);
        }
        int i4 = i + i3;
        return outlinedTextFieldMeasurePolicy.labelPosition instanceof TextFieldLabelPosition.Default ? Math.max(i4, LayoutUtilKt.getHeightOrZero(placeable) / 2) : i4;
    }

    /* renamed from: calculateHeight-aSWTPvA, reason: not valid java name */
    public final int m219calculateHeightaSWTPvA(IntrinsicMeasureScope intrinsicMeasureScope, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j) {
        float f = this.labelProgress;
        int maxOf = ComparisonsKt___ComparisonsJvmKt.maxOf(new int[]{i7, i3, i4, MathHelpersKt.lerp(i6, f, 0)}, i5);
        PaddingValues paddingValues = this.paddingValues;
        float mo51toPx0680j_4 = intrinsicMeasureScope.mo51toPx0680j_4(paddingValues.mo106calculateTopPaddingD9Ej5fM());
        return Math.max(Constraints.m656getMinHeightimpl(j), Math.max(i, Math.max(i2, MathKt.roundToInt(MathHelpersKt.lerp(mo51toPx0680j_4, Math.max(mo51toPx0680j_4, i6 / 2.0f), f) + maxOf + intrinsicMeasureScope.mo51toPx0680j_4(paddingValues.mo103calculateBottomPaddingD9Ej5fM())))) + i8);
    }

    /* renamed from: calculateWidth-xygx4p4, reason: not valid java name */
    public final int m220calculateWidthxygx4p4(IntrinsicMeasureScope intrinsicMeasureScope, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        int i8 = i3 + i4;
        float f = this.labelProgress;
        int max = Math.max(i5 + i8, Math.max(i7 + i8, MathHelpersKt.lerp(i6, f, 0))) + i + i2;
        LayoutDirection layoutDirection = LayoutDirection.Ltr;
        PaddingValues paddingValues = this.paddingValues;
        return Math.max(max, Math.max(MathKt.roundToInt((i6 + intrinsicMeasureScope.mo51toPx0680j_4(paddingValues.mo105calculateRightPaddingu2uoSUM(layoutDirection) + paddingValues.mo104calculateLeftPaddingu2uoSUM(layoutDirection))) * f), Constraints.m657getMinWidthimpl(j)));
    }

    public final int intrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i, Function2 function2) {
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
            i2 = LayoutUtilKt.subtractConstraintSafely(i, intrinsicMeasurable.maxIntrinsicWidth(Integer.MAX_VALUE));
            i3 = ((Number) function2.invoke(intrinsicMeasurable, Integer.valueOf(i))).intValue();
        } else {
            i2 = i;
            i3 = 0;
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
            i2 = LayoutUtilKt.subtractConstraintSafely(i2, intrinsicMeasurable2.maxIntrinsicWidth(Integer.MAX_VALUE));
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
        int intValue = obj8 != null ? ((Number) function2.invoke(obj8, Integer.valueOf(MathHelpersKt.lerp(i2, this.labelProgress, i)))).intValue() : 0;
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
            int intValue2 = ((Number) function2.invoke(intrinsicMeasurable3, Integer.valueOf(i2))).intValue();
            i2 = LayoutUtilKt.subtractConstraintSafely(i2, intrinsicMeasurable3.maxIntrinsicWidth(Integer.MAX_VALUE));
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
            int intValue3 = ((Number) function2.invoke(intrinsicMeasurable4, Integer.valueOf(i2))).intValue();
            i2 = LayoutUtilKt.subtractConstraintSafely(i2, intrinsicMeasurable4.maxIntrinsicWidth(Integer.MAX_VALUE));
            i6 = intValue3;
        } else {
            i6 = 0;
        }
        int size6 = list.size();
        for (int i12 = 0; i12 < size6; i12++) {
            Object obj9 = list.get(i12);
            if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj9), "TextField")) {
                int intValue4 = ((Number) function2.invoke(obj9, Integer.valueOf(i2))).intValue();
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
                int intValue5 = obj10 != null ? ((Number) function2.invoke(obj10, Integer.valueOf(i2))).intValue() : 0;
                int size8 = list.size();
                int i14 = 0;
                while (true) {
                    if (i14 >= size8) {
                        obj7 = null;
                        break;
                    }
                    Object obj11 = list.get(i14);
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj11), "Supporting")) {
                        obj7 = obj11;
                        break;
                    }
                    i14++;
                }
                Object obj12 = (IntrinsicMeasurable) obj7;
                return m219calculateHeightaSWTPvA(intrinsicMeasureScope, i3, i4, i5, i6, intValue4, intValue, intValue5, obj12 != null ? ((Number) function2.invoke(obj12, Integer.valueOf(i))).intValue() : 0, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public final int intrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i, Function2 function2) {
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
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj4), "Leading")) {
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
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj5), "Prefix")) {
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
                    if (Intrinsics.areEqual(LayoutUtilKt.getLayoutId((IntrinsicMeasurable) obj6), "Suffix")) {
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
                return m220calculateWidthxygx4p4(intrinsicMeasureScope, intValue4, intValue3, intValue5, intValue6, intValue, intValue2, intrinsicMeasurable6 != null ? ((Number) function2.invoke(intrinsicMeasurable6, Integer.valueOf(i))).intValue() : 0, ConstraintsKt.Constraints$default(0, 0, 0, 0, 15));
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).maxIntrinsicHeight(((Number) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$maxIntrinsicWidth$1
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
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        MeasureResult layout$1;
        PaddingValues paddingValues = this.paddingValues;
        int mo45roundToPx0680j_4 = measureScope.mo45roundToPx0680j_4(paddingValues.mo103calculateBottomPaddingD9Ej5fM());
        long m648copyZbe2FdA$default = Constraints.m648copyZbe2FdA$default(j, 0, 0, 0, 0, 10);
        int size = list.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                obj = null;
                break;
            }
            obj = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj), "Leading")) {
                break;
            }
            i++;
        }
        Measurable measurable = (Measurable) obj;
        Placeable mo479measureBRTryo0 = measurable != null ? measurable.mo479measureBRTryo0(m648copyZbe2FdA$default) : null;
        int widthOrZero = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo0);
        int max = Math.max(0, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo0));
        int size2 = list.size();
        int i2 = 0;
        while (true) {
            if (i2 >= size2) {
                obj2 = null;
                break;
            }
            obj2 = list.get(i2);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj2), "Trailing")) {
                break;
            }
            i2++;
        }
        Measurable measurable2 = (Measurable) obj2;
        Placeable mo479measureBRTryo02 = measurable2 != null ? measurable2.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero, 0, m648copyZbe2FdA$default, 2)) : null;
        int widthOrZero2 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo02) + widthOrZero;
        int max2 = Math.max(max, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo02));
        int size3 = list.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                obj3 = null;
                break;
            }
            obj3 = list.get(i3);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj3), "Prefix")) {
                break;
            }
            i3++;
        }
        Measurable measurable3 = (Measurable) obj3;
        Placeable mo479measureBRTryo03 = measurable3 != null ? measurable3.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero2, 0, m648copyZbe2FdA$default, 2)) : null;
        int widthOrZero3 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo03) + widthOrZero2;
        int max3 = Math.max(max2, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo03));
        int size4 = list.size();
        int i4 = 0;
        while (true) {
            if (i4 >= size4) {
                obj4 = null;
                break;
            }
            obj4 = list.get(i4);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj4), "Suffix")) {
                break;
            }
            i4++;
        }
        Measurable measurable4 = (Measurable) obj4;
        Placeable mo479measureBRTryo04 = measurable4 != null ? measurable4.mo479measureBRTryo0(ConstraintsKt.m667offsetNN6EwU$default(-widthOrZero3, 0, m648copyZbe2FdA$default, 2)) : null;
        int widthOrZero4 = LayoutUtilKt.getWidthOrZero(mo479measureBRTryo04) + widthOrZero3;
        int max4 = Math.max(max3, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo04));
        int size5 = list.size();
        int i5 = 0;
        while (true) {
            if (i5 >= size5) {
                obj5 = null;
                break;
            }
            obj5 = list.get(i5);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId((Measurable) obj5), "Label")) {
                break;
            }
            i5++;
        }
        Measurable measurable5 = (Measurable) obj5;
        final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        int mo45roundToPx0680j_42 = measureScope.mo45roundToPx0680j_4(paddingValues.mo105calculateRightPaddingu2uoSUM(measureScope.getLayoutDirection())) + measureScope.mo45roundToPx0680j_4(paddingValues.mo104calculateLeftPaddingu2uoSUM(measureScope.getLayoutDirection()));
        int i6 = -MathHelpersKt.lerp(widthOrZero4 + mo45roundToPx0680j_42, this.labelProgress, mo45roundToPx0680j_42);
        int i7 = -mo45roundToPx0680j_4;
        Placeable mo479measureBRTryo05 = measurable5 != null ? measurable5.mo479measureBRTryo0(ConstraintsKt.m666offsetNN6EwU(i6, i7, m648copyZbe2FdA$default)) : null;
        ref$ObjectRef.element = mo479measureBRTryo05;
        this.onLabelMeasured.invoke(new Size(mo479measureBRTryo05 != null ? SizeKt.Size(mo479measureBRTryo05.width, mo479measureBRTryo05.height) : 0L));
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
        int max5 = Math.max(LayoutUtilKt.getHeightOrZero((Placeable) ref$ObjectRef.element) / 2, measureScope.mo45roundToPx0680j_4(paddingValues.mo106calculateTopPaddingD9Ej5fM()));
        Measurable measurable7 = measurable6;
        long m648copyZbe2FdA$default2 = Constraints.m648copyZbe2FdA$default(ConstraintsKt.m666offsetNN6EwU(-widthOrZero4, (i7 - max5) - minIntrinsicHeight, j), 0, 0, 0, 0, 11);
        int size7 = list.size();
        int i9 = 0;
        while (i9 < size7) {
            int i10 = size7;
            Measurable measurable8 = (Measurable) list.get(i9);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable8), "TextField")) {
                final Placeable mo479measureBRTryo06 = measurable8.mo479measureBRTryo0(m648copyZbe2FdA$default2);
                long m648copyZbe2FdA$default3 = Constraints.m648copyZbe2FdA$default(m648copyZbe2FdA$default2, 0, 0, 0, 0, 14);
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
                Measurable measurable9 = (Measurable) obj7;
                Placeable mo479measureBRTryo07 = measurable9 != null ? measurable9.mo479measureBRTryo0(m648copyZbe2FdA$default3) : null;
                int max6 = Math.max(max4, Math.max(LayoutUtilKt.getHeightOrZero(mo479measureBRTryo06), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo07)) + max5 + mo45roundToPx0680j_4);
                final int m220calculateWidthxygx4p4 = m220calculateWidthxygx4p4(measureScope, LayoutUtilKt.getWidthOrZero(mo479measureBRTryo0), LayoutUtilKt.getWidthOrZero(mo479measureBRTryo02), LayoutUtilKt.getWidthOrZero(mo479measureBRTryo03), LayoutUtilKt.getWidthOrZero(mo479measureBRTryo04), mo479measureBRTryo06.width, LayoutUtilKt.getWidthOrZero((Placeable) ref$ObjectRef.element), LayoutUtilKt.getWidthOrZero(mo479measureBRTryo07), j);
                Placeable mo479measureBRTryo08 = measurable7 != null ? measurable7.mo479measureBRTryo0(Constraints.m648copyZbe2FdA$default(ConstraintsKt.m667offsetNN6EwU$default(0, -max6, m648copyZbe2FdA$default, 1), 0, m220calculateWidthxygx4p4, 0, 0, 9)) : null;
                int heightOrZero = LayoutUtilKt.getHeightOrZero(mo479measureBRTryo08);
                final int m219calculateHeightaSWTPvA = m219calculateHeightaSWTPvA(measureScope, LayoutUtilKt.getHeightOrZero(mo479measureBRTryo0), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo02), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo03), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo04), mo479measureBRTryo06.height, LayoutUtilKt.getHeightOrZero((Placeable) ref$ObjectRef.element), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo07), LayoutUtilKt.getHeightOrZero(mo479measureBRTryo08), j);
                int i13 = m219calculateHeightaSWTPvA - heightOrZero;
                int size9 = list.size();
                for (int i14 = 0; i14 < size9; i14++) {
                    Measurable measurable10 = (Measurable) list.get(i14);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable10), "Container")) {
                        final Placeable mo479measureBRTryo09 = measurable10.mo479measureBRTryo0(ConstraintsKt.Constraints(m220calculateWidthxygx4p4 != Integer.MAX_VALUE ? m220calculateWidthxygx4p4 : 0, m220calculateWidthxygx4p4, i13 != Integer.MAX_VALUE ? i13 : 0, i13));
                        final Placeable placeable = mo479measureBRTryo0;
                        final Placeable placeable2 = mo479measureBRTryo02;
                        final Placeable placeable3 = mo479measureBRTryo03;
                        final Placeable placeable4 = mo479measureBRTryo04;
                        final Placeable placeable5 = mo479measureBRTryo07;
                        final Placeable placeable6 = mo479measureBRTryo08;
                        layout$1 = measureScope.layout$1(m220calculateWidthxygx4p4, m219calculateHeightaSWTPvA, MapsKt.emptyMap(), new Function1() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$measure$1
                            final /* synthetic */ boolean $isLabelAbove = false;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX WARN: Removed duplicated region for block: B:30:0x013e  */
                            /* JADX WARN: Removed duplicated region for block: B:33:0x0184  */
                            /* JADX WARN: Removed duplicated region for block: B:35:0x0196  */
                            /* JADX WARN: Removed duplicated region for block: B:37:0x01b3  */
                            /* JADX WARN: Removed duplicated region for block: B:39:0x01c7  */
                            /* JADX WARN: Removed duplicated region for block: B:43:0x015d  */
                            @Override // kotlin.jvm.functions.Function1
                            /*
                                Code decompiled incorrectly, please refer to instructions dump.
                                To view partially-correct add '--show-bad-code' argument
                            */
                            public final java.lang.Object invoke(java.lang.Object r28) {
                                /*
                                    Method dump skipped, instructions count: 464
                                    To view this dump add '--comments-level debug' option
                                */
                                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$measure$1.invoke(java.lang.Object):java.lang.Object");
                            }
                        });
                        return layout$1;
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }
            i9++;
            measurable7 = measurable7;
            size7 = i10;
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicHeight(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicHeight$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicHeight(((Number) obj2).intValue()));
            }
        });
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public final int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return intrinsicWidth(intrinsicMeasureScope, list, i, new Function2() { // from class: androidx.compose.material3.OutlinedTextFieldMeasurePolicy$minIntrinsicWidth$1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Integer.valueOf(((IntrinsicMeasurable) obj).minIntrinsicWidth(((Number) obj2).intValue()));
            }
        });
    }
}
