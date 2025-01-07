package androidx.compose.ui.spatial;

import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RectList {
    public long[] items;
    public int itemsSize;
    public long[] stack;

    public static void insert$default(RectList rectList, int i, int i2, int i3, int i4, int i5, int i6) {
        long[] jArr = rectList.items;
        int i7 = rectList.itemsSize;
        int i8 = i7 + 3;
        rectList.itemsSize = i8;
        int length = jArr.length;
        if (length <= i8) {
            int max = Math.max(length * 2, i8);
            rectList.items = Arrays.copyOf(jArr, max);
            rectList.stack = Arrays.copyOf(rectList.stack, max);
        }
        long[] jArr2 = rectList.items;
        jArr2[i7] = (i2 << 32) | (i3 & 4294967295L);
        jArr2[i7 + 1] = (i5 & 4294967295L) | (i4 << 32);
        int i9 = i6 & 67108863;
        jArr2[i7 + 2] = (0 << 63) | (0 << 62) | (0 << 52) | (i9 << 26) | (i & 67108863);
        if (i6 < 0) {
            return;
        }
        for (int i10 = i7 - 3; i10 > 0; i10 -= 3) {
            int i11 = i10 + 2;
            long j = jArr2[i11];
            if ((((int) j) & 67108863) == i9) {
                jArr2[i11] = (j & (-4607182418800017409L)) | (((i7 - i10) & 1023) << 52);
                return;
            }
        }
    }
}
