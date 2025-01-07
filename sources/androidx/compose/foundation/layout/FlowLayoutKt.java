package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.FlowLayoutBuildingBlocks;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class FlowLayoutKt {
    /* JADX WARN: Removed duplicated region for block: B:101:0x0223 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0251 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x027b  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0143  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x00ab  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x012f  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x014c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01a8  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01d9 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0204  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x020e  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0219  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FlowRow(androidx.compose.ui.Modifier r32, androidx.compose.foundation.layout.Arrangement.Horizontal r33, androidx.compose.foundation.layout.Arrangement.Vertical r34, androidx.compose.ui.Alignment.Vertical r35, int r36, int r37, androidx.compose.foundation.layout.FlowRowOverflow r38, final kotlin.jvm.functions.Function3 r39, androidx.compose.runtime.Composer r40, final int r41, final int r42) {
        /*
            Method dump skipped, instructions count: 716
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.FlowLayoutKt.FlowRow(androidx.compose.ui.Modifier, androidx.compose.foundation.layout.Arrangement$Horizontal, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.ui.Alignment$Vertical, int, int, androidx.compose.foundation.layout.FlowRowOverflow, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final long intrinsicCrossAxisSize(List list, Function3 function3, Function3 function32, int i, int i2, int i3, int i4, int i5, FlowLayoutOverflowState flowLayoutOverflowState) {
        int i6;
        int i7;
        List list2 = list;
        Function3 function33 = function3;
        boolean isEmpty = list.isEmpty();
        int i8 = 0;
        long m0constructorimpl = IntIntPair.m0constructorimpl(0, 0);
        if (isEmpty) {
            return m0constructorimpl;
        }
        FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(i4, flowLayoutOverflowState, ConstraintsKt.Constraints(0, i, 0, Integer.MAX_VALUE), i5, i2, i3);
        IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(0, list2);
        int intValue = intrinsicMeasurable != null ? ((Number) function32.invoke(intrinsicMeasurable, 0, Integer.valueOf(i))).intValue() : 0;
        int intValue2 = intrinsicMeasurable != null ? ((Number) function33.invoke(intrinsicMeasurable, 0, Integer.valueOf(intValue))).intValue() : 0;
        boolean z = list.size() > 1;
        long m0constructorimpl2 = IntIntPair.m0constructorimpl(i, Integer.MAX_VALUE);
        IntIntPair intIntPair = intrinsicMeasurable == null ? null : new IntIntPair(IntIntPair.m0constructorimpl(intValue2, intValue));
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        if (flowLayoutBuildingBlocks.m88getWrapInfoOpUlnko(z, 0, m0constructorimpl2, intIntPair, 0, 0, 0, false, false).isLastItemInContainer) {
            flowLayoutOverflowState.getClass();
            return m0constructorimpl;
        }
        int size = list.size();
        int i12 = i;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (true) {
            if (i13 >= size) {
                i6 = i14;
                break;
            }
            int i16 = i12 - intValue2;
            i6 = i13 + 1;
            int max = Math.max(i11, intValue);
            IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(i6, list2);
            int intValue3 = intrinsicMeasurable2 != null ? ((Number) function32.invoke(intrinsicMeasurable2, Integer.valueOf(i6), Integer.valueOf(i))).intValue() : i8;
            int intValue4 = intrinsicMeasurable2 != null ? ((Number) function33.invoke(intrinsicMeasurable2, Integer.valueOf(i6), Integer.valueOf(intValue3))).intValue() + i2 : 0;
            int i17 = i6 - i15;
            FlowLayoutBuildingBlocks.WrapInfo m88getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m88getWrapInfoOpUlnko(i13 + 2 < list.size(), i17, IntIntPair.m0constructorimpl(i16, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : new IntIntPair(IntIntPair.m0constructorimpl(intValue4, intValue3)), i9, i10, max, false, false);
            if (m88getWrapInfoOpUlnko.isLastItemInLine) {
                i10 += max + i3;
                flowLayoutBuildingBlocks.getWrapEllipsisInfo(m88getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i9, i10, i16, i17);
                int i18 = intValue4 - i2;
                i9++;
                if (m88getWrapInfoOpUlnko.isLastItemInContainer) {
                    break;
                }
                i7 = i;
                intValue2 = i18;
                i15 = i6;
                i11 = 0;
            } else {
                i11 = max;
                i7 = i16;
                intValue2 = intValue4;
            }
            function33 = function3;
            intValue = intValue3;
            i13 = i6;
            i14 = i13;
            i8 = 0;
            i12 = i7;
            list2 = list;
        }
        return IntIntPair.m0constructorimpl(i10 - i3, i6);
    }

    /* renamed from: measureAndCache-rqJ1uqs, reason: not valid java name */
    public static final long m89measureAndCacherqJ1uqs(Measurable measurable, FlowLineMeasurePolicy flowLineMeasurePolicy, long j, Function1 function1) {
        if (RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable)) != 0.0f) {
            int minIntrinsicWidth = measurable.minIntrinsicWidth(Integer.MAX_VALUE);
            return IntIntPair.m0constructorimpl(minIntrinsicWidth, measurable.minIntrinsicHeight(minIntrinsicWidth));
        }
        RowColumnImplKt.getRowColumnParentData(measurable);
        Placeable mo479measureBRTryo0 = measurable.mo479measureBRTryo0(j);
        function1.invoke(mo479measureBRTryo0);
        return IntIntPair.m0constructorimpl(mo479measureBRTryo0.getMeasuredWidth(), mo479measureBRTryo0.getMeasuredHeight());
    }
}
