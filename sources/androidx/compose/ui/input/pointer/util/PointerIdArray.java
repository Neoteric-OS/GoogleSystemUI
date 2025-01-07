package androidx.compose.ui.input.pointer.util;

import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerIdArray {
    public long[] internalArray;
    public int size;

    /* renamed from: add-0FcD4WY, reason: not valid java name */
    public final void m469add0FcD4WY(long j) {
        if (contains(j)) {
            return;
        }
        int i = this.size;
        long[] jArr = this.internalArray;
        if (i >= jArr.length) {
            this.internalArray = Arrays.copyOf(jArr, Math.max(i + 1, jArr.length * 2));
        }
        this.internalArray[i] = j;
        if (i >= this.size) {
            this.size = i + 1;
        }
    }

    public final boolean contains(long j) {
        int i = this.size;
        for (int i2 = 0; i2 < i; i2++) {
            if (this.internalArray[i2] == j) {
                return true;
            }
        }
        return false;
    }

    public final void removeAt(int i) {
        int i2 = this.size;
        if (i < i2) {
            int i3 = i2 - 1;
            while (i < i3) {
                long[] jArr = this.internalArray;
                int i4 = i + 1;
                jArr[i] = jArr[i4];
                i = i4;
            }
            this.size--;
        }
    }
}
