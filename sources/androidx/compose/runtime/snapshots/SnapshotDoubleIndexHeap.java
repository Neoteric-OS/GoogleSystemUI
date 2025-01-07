package androidx.compose.runtime.snapshots;

import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotDoubleIndexHeap {
    public int firstFreeHandle;
    public int[] handles;
    public int[] index;
    public int size;
    public int[] values;

    public final int add(int i) {
        int i2 = this.size + 1;
        int[] iArr = this.values;
        int length = iArr.length;
        if (i2 > length) {
            int i3 = length * 2;
            int[] iArr2 = new int[i3];
            int[] iArr3 = new int[i3];
            ArraysKt.copyInto$default(0, 0, 14, iArr, iArr2);
            ArraysKt.copyInto$default(0, 0, 14, this.index, iArr3);
            this.values = iArr2;
            this.index = iArr3;
        }
        int i4 = this.size;
        this.size = i4 + 1;
        int length2 = this.handles.length;
        if (this.firstFreeHandle >= length2) {
            int i5 = length2 * 2;
            int[] iArr4 = new int[i5];
            int i6 = 0;
            while (i6 < i5) {
                int i7 = i6 + 1;
                iArr4[i6] = i7;
                i6 = i7;
            }
            ArraysKt.copyInto$default(0, 0, 14, this.handles, iArr4);
            this.handles = iArr4;
        }
        int i8 = this.firstFreeHandle;
        int[] iArr5 = this.handles;
        this.firstFreeHandle = iArr5[i8];
        int[] iArr6 = this.values;
        iArr6[i4] = i;
        this.index[i4] = i8;
        iArr5[i8] = i4;
        int i9 = iArr6[i4];
        while (i4 > 0) {
            int i10 = ((i4 + 1) >> 1) - 1;
            if (iArr6[i10] <= i9) {
                break;
            }
            swap(i10, i4);
            i4 = i10;
        }
        return i8;
    }

    public final void swap(int i, int i2) {
        int[] iArr = this.values;
        int[] iArr2 = this.index;
        int[] iArr3 = this.handles;
        int i3 = iArr[i];
        iArr[i] = iArr[i2];
        iArr[i2] = i3;
        int i4 = iArr2[i];
        iArr2[i] = iArr2[i2];
        iArr2[i2] = i4;
        iArr3[iArr2[i]] = i;
        iArr3[iArr2[i2]] = i2;
    }
}
