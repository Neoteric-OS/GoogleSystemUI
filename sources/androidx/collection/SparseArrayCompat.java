package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SparseArrayCompat implements Cloneable {
    public /* synthetic */ int[] keys;
    public /* synthetic */ int size;
    public /* synthetic */ Object[] values;

    public SparseArrayCompat(int i) {
        int i2;
        int i3 = 4;
        while (true) {
            i2 = 40;
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (40 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        int i5 = i2 / 4;
        this.keys = new int[i5];
        this.values = new Object[i5];
    }

    public final void append(int i, Object obj) {
        int i2 = this.size;
        if (i2 != 0 && i <= this.keys[i2 - 1]) {
            put(i, obj);
            return;
        }
        if (i2 >= this.keys.length) {
            int i3 = (i2 + 1) * 4;
            int i4 = 4;
            while (true) {
                if (i4 >= 32) {
                    break;
                }
                int i5 = (1 << i4) - 12;
                if (i3 <= i5) {
                    i3 = i5;
                    break;
                }
                i4++;
            }
            int i6 = i3 / 4;
            this.keys = Arrays.copyOf(this.keys, i6);
            this.values = Arrays.copyOf(this.values, i6);
        }
        this.keys[i2] = i;
        this.values[i2] = obj;
        this.size = i2 + 1;
    }

    public final Object get(int i) {
        Object obj;
        int binarySearch = ContainerHelpersKt.binarySearch(this.size, i, this.keys);
        if (binarySearch < 0 || (obj = this.values[binarySearch]) == SparseArrayCompatKt.DELETED) {
            return null;
        }
        return obj;
    }

    public final void put(int i, Object obj) {
        int binarySearch = ContainerHelpersKt.binarySearch(this.size, i, this.keys);
        if (binarySearch >= 0) {
            this.values[binarySearch] = obj;
            return;
        }
        int i2 = ~binarySearch;
        int i3 = this.size;
        if (i2 < i3) {
            Object[] objArr = this.values;
            if (objArr[i2] == SparseArrayCompatKt.DELETED) {
                this.keys[i2] = i;
                objArr[i2] = obj;
                return;
            }
        }
        if (i3 >= this.keys.length) {
            int i4 = (i3 + 1) * 4;
            int i5 = 4;
            while (true) {
                if (i5 >= 32) {
                    break;
                }
                int i6 = (1 << i5) - 12;
                if (i4 <= i6) {
                    i4 = i6;
                    break;
                }
                i5++;
            }
            int i7 = i4 / 4;
            this.keys = Arrays.copyOf(this.keys, i7);
            this.values = Arrays.copyOf(this.values, i7);
        }
        int i8 = this.size;
        if (i8 - i2 != 0) {
            int[] iArr = this.keys;
            int i9 = i2 + 1;
            ArraysKt.copyInto(i9, i2, i8, iArr, iArr);
            Object[] objArr2 = this.values;
            ArraysKt.copyInto(i9, i2, this.size, objArr2, objArr2);
        }
        this.keys[i2] = i;
        this.values[i2] = obj;
        this.size++;
    }

    public final String toString() {
        int i = this.size;
        if (i <= 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(i * 28);
        sb.append('{');
        int i2 = this.size;
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            sb.append(this.keys[i3]);
            sb.append('=');
            Object obj = this.values[i3];
            if (obj != this) {
                sb.append(obj);
            } else {
                sb.append("(this Map)");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public final SparseArrayCompat m1clone() {
        SparseArrayCompat sparseArrayCompat = (SparseArrayCompat) super.clone();
        sparseArrayCompat.keys = (int[]) this.keys.clone();
        sparseArrayCompat.values = (Object[]) this.values.clone();
        return sparseArrayCompat;
    }
}
