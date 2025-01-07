package androidx.collection;

import androidx.collection.internal.RuntimeHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MutableIntList {
    public int _size;
    public int[] content;

    public MutableIntList(int i) {
        this.content = i == 0 ? IntSetKt.EmptyIntArray : new int[i];
    }

    public final void add(int i) {
        ensureCapacity(this._size + 1);
        int[] iArr = this.content;
        int i2 = this._size;
        iArr[i2] = i;
        this._size = i2 + 1;
    }

    public final void ensureCapacity(int i) {
        int[] iArr = this.content;
        if (iArr.length < i) {
            this.content = Arrays.copyOf(iArr, Math.max(i, (iArr.length * 3) / 2));
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof MutableIntList) {
            MutableIntList mutableIntList = (MutableIntList) obj;
            int i = mutableIntList._size;
            int i2 = this._size;
            if (i == i2) {
                int[] iArr = this.content;
                int[] iArr2 = mutableIntList.content;
                IntRange until = RangesKt.until(0, i2);
                int i3 = until.first;
                int i4 = until.last;
                if (i3 > i4) {
                    return true;
                }
                while (iArr[i3] == iArr2[i3]) {
                    if (i3 == i4) {
                        return true;
                    }
                    i3++;
                }
                return false;
            }
        }
        return false;
    }

    public final int get(int i) {
        if (i >= 0 && i < this._size) {
            return this.content[i];
        }
        RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
        throw null;
    }

    public final int hashCode() {
        int[] iArr = this.content;
        int i = this._size;
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            i2 += Integer.hashCode(iArr[i3]) * 31;
        }
        return i2;
    }

    public final int removeAt(int i) {
        int i2;
        if (i < 0 || i >= (i2 = this._size)) {
            RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
            throw null;
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        if (i != i2 - 1) {
            ArraysKt.copyInto(i, i + 1, i2, iArr, iArr);
        }
        this._size--;
        return i3;
    }

    public final void set(int i, int i2) {
        if (i < 0 || i >= this._size) {
            RuntimeHelpersKt.throwIndexOutOfBoundsException("Index must be between 0 and size");
            throw null;
        }
        int[] iArr = this.content;
        int i3 = iArr[i];
        iArr[i] = i2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "[");
        int[] iArr = this.content;
        int i = this._size;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                sb.append((CharSequence) "]");
                break;
            }
            int i3 = iArr[i2];
            if (i2 == -1) {
                sb.append((CharSequence) "...");
                break;
            }
            if (i2 != 0) {
                sb.append((CharSequence) ", ");
            }
            sb.append(i3);
            i2++;
        }
        return sb.toString();
    }

    public /* synthetic */ MutableIntList() {
        this(16);
    }
}
