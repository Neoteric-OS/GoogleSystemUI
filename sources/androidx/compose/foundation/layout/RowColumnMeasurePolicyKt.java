package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RowColumnMeasurePolicyKt {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v21 */
    /* JADX WARN: Type inference failed for: r7v22 */
    /* JADX WARN: Type inference failed for: r7v23, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r7v24 */
    public static final MeasureResult measure(RowColumnMeasurePolicy rowColumnMeasurePolicy, int i, int i2, int i3, int i4, int i5, MeasureScope measureScope, List list, Placeable[] placeableArr, int i6, int i7, int[] iArr, int i8) {
        int i9;
        long j;
        int i10;
        int i11;
        int i12;
        int i13;
        ?? r7;
        List list2 = list;
        int i14 = i7;
        long j2 = i5;
        int i15 = i14 - i6;
        int[] iArr2 = new int[i15];
        float f = 0.0f;
        int i16 = i6;
        float f2 = 0.0f;
        int i17 = 0;
        int i18 = 0;
        int i19 = 0;
        int i20 = 0;
        while (i16 < i14) {
            Measurable measurable = (Measurable) list2.get(i16);
            float weight = RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData(measurable));
            if (weight > f) {
                f2 += weight;
                i17++;
                i12 = i15;
            } else {
                int i21 = i3 - i18;
                Placeable placeable = placeableArr[i16];
                if (placeable == null) {
                    if (i3 == Integer.MAX_VALUE) {
                        i12 = i15;
                        r7 = 0;
                        i13 = Integer.MAX_VALUE;
                    } else {
                        i12 = i15;
                        if (i21 < 0) {
                            r7 = 0;
                            i13 = 0;
                        } else {
                            i13 = i21;
                            r7 = 0;
                        }
                    }
                    placeable = measurable.mo479measureBRTryo0(rowColumnMeasurePolicy.mo87createConstraintsxF2OJ5Q(r7, i13, i4, r7));
                } else {
                    i12 = i15;
                }
                Placeable placeable2 = placeable;
                int mainAxisSize = rowColumnMeasurePolicy.mainAxisSize(placeable2);
                int crossAxisSize = rowColumnMeasurePolicy.crossAxisSize(placeable2);
                iArr2[i16 - i6] = mainAxisSize;
                int i22 = i21 - mainAxisSize;
                if (i22 < 0) {
                    i22 = 0;
                }
                i19 = Math.min(i5, i22);
                i18 += mainAxisSize + i19;
                i20 = Math.max(i20, crossAxisSize);
                placeableArr[i16] = placeable2;
            }
            i16++;
            i14 = i7;
            i15 = i12;
            f = 0.0f;
        }
        int i23 = i15;
        int i24 = i20;
        if (i17 == 0) {
            i18 -= i19;
            i9 = 0;
        } else {
            long j3 = j2 * (i17 - 1);
            long j4 = ((i3 != Integer.MAX_VALUE ? i3 : i) - i18) - j3;
            if (j4 < 0) {
                j4 = 0;
            }
            float f3 = j4 / f2;
            int i25 = i7;
            for (int i26 = i6; i26 < i25; i26++) {
                j4 -= Math.round(RowColumnImplKt.getWeight(RowColumnImplKt.getRowColumnParentData((Measurable) list2.get(i26))) * f3);
            }
            int i27 = i6;
            int i28 = 0;
            while (i27 < i25) {
                if (placeableArr[i27] == null) {
                    Measurable measurable2 = (Measurable) list2.get(i27);
                    RowColumnParentData rowColumnParentData = RowColumnImplKt.getRowColumnParentData(measurable2);
                    float weight2 = RowColumnImplKt.getWeight(rowColumnParentData);
                    if (weight2 <= 0.0f) {
                        InlineClassHelperKt.throwIllegalStateException("All weights <= 0 should have placeables");
                    }
                    if (j4 < 0) {
                        j = j3;
                        i10 = -1;
                    } else if (j4 > 0) {
                        j = j3;
                        i10 = 1;
                    } else {
                        j = j3;
                        i10 = 0;
                    }
                    j4 -= i10;
                    int max = Math.max(0, Math.round(weight2 * f3) + i10);
                    if ((rowColumnParentData != null ? rowColumnParentData.fill : true) && max != Integer.MAX_VALUE) {
                        i11 = max;
                        Placeable mo479measureBRTryo0 = measurable2.mo479measureBRTryo0(rowColumnMeasurePolicy.mo87createConstraintsxF2OJ5Q(i11, max, i4, true));
                        int mainAxisSize2 = rowColumnMeasurePolicy.mainAxisSize(mo479measureBRTryo0);
                        int crossAxisSize2 = rowColumnMeasurePolicy.crossAxisSize(mo479measureBRTryo0);
                        iArr2[i27 - i6] = mainAxisSize2;
                        i28 += mainAxisSize2;
                        int max2 = Math.max(i24, crossAxisSize2);
                        placeableArr[i27] = mo479measureBRTryo0;
                        i24 = max2;
                    }
                    i11 = 0;
                    Placeable mo479measureBRTryo02 = measurable2.mo479measureBRTryo0(rowColumnMeasurePolicy.mo87createConstraintsxF2OJ5Q(i11, max, i4, true));
                    int mainAxisSize22 = rowColumnMeasurePolicy.mainAxisSize(mo479measureBRTryo02);
                    int crossAxisSize22 = rowColumnMeasurePolicy.crossAxisSize(mo479measureBRTryo02);
                    iArr2[i27 - i6] = mainAxisSize22;
                    i28 += mainAxisSize22;
                    int max22 = Math.max(i24, crossAxisSize22);
                    placeableArr[i27] = mo479measureBRTryo02;
                    i24 = max22;
                } else {
                    j = j3;
                }
                i27++;
                list2 = list;
                i25 = i7;
                j3 = j;
            }
            i9 = (int) (i28 + j3);
            int i29 = i3 - i18;
            if (i9 < 0) {
                i9 = 0;
            }
            if (i9 > i29) {
                i9 = i29;
            }
        }
        int i30 = i9 + i18;
        if (i30 < 0) {
            i30 = 0;
        }
        int max3 = Math.max(i30, i);
        int max4 = Math.max(i24, Math.max(i2, 0));
        int[] iArr3 = new int[i23];
        rowColumnMeasurePolicy.populateMainAxisPositions(max3, iArr2, iArr3, measureScope);
        return rowColumnMeasurePolicy.placeHelper(placeableArr, measureScope, iArr3, max3, max4, iArr, i8, i6, i7);
    }
}
