package kotlin.collections;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.sequences.EmptySequence;
import kotlin.sequences.Sequence;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ArraysKt extends ArraysKt__ArraysJVMKt {
    public static Iterable asIterable(Object[] objArr) {
        return objArr.length == 0 ? EmptyList.INSTANCE : new ArraysKt___ArraysKt$asIterable$$inlined$Iterable$1(objArr);
    }

    public static Sequence asSequence(Object[] objArr) {
        return objArr.length == 0 ? EmptySequence.INSTANCE : new ArraysKt___ArraysKt$asSequence$$inlined$Sequence$1(0, objArr);
    }

    public static boolean contains(Object[] objArr, Object obj) {
        return indexOf(objArr, obj) >= 0;
    }

    public static void copyInto(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        System.arraycopy(objArr, i2, objArr2, i, i3 - i2);
    }

    public static void copyInto$default(int i, int i2, int i3, Object[] objArr, Object[] objArr2) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = objArr.length;
        }
        System.arraycopy(objArr, i, objArr2, 0, i2 - i);
    }

    public static List drop(int i, Object[] objArr) {
        if (i < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested element count ", " is less than zero.", i).toString());
        }
        int length = objArr.length - i;
        if (length < 0) {
            length = 0;
        }
        if (length < 0) {
            throw new IllegalArgumentException(GenericDocument$$ExternalSyntheticOutline0.m("Requested element count ", " is less than zero.", length).toString());
        }
        if (length == 0) {
            return EmptyList.INSTANCE;
        }
        int length2 = objArr.length;
        if (length >= length2) {
            return toList(objArr);
        }
        if (length == 1) {
            return Collections.singletonList(objArr[length2 - 1]);
        }
        ArrayList arrayList = new ArrayList(length);
        for (int i2 = length2 - length; i2 < length2; i2++) {
            arrayList.add(objArr[i2]);
        }
        return arrayList;
    }

    public static List filterNotNull(Object[] objArr) {
        ArrayList arrayList = new ArrayList();
        for (Object obj : objArr) {
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static Integer firstOrNull(int[] iArr) {
        if (iArr.length == 0) {
            return null;
        }
        return Integer.valueOf(iArr[0]);
    }

    public static IntRange getIndices(int[] iArr) {
        return new IntRange(0, iArr.length - 1, 1);
    }

    public static int indexOf(Object[] objArr, Object obj) {
        int i = 0;
        if (obj == null) {
            int length = objArr.length;
            while (i < length) {
                if (objArr[i] == null) {
                    return i;
                }
                i++;
            }
            return -1;
        }
        int length2 = objArr.length;
        while (i < length2) {
            if (obj.equals(objArr[i])) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int[] plus(int[] iArr, int[] iArr2) {
        int length = iArr.length;
        int length2 = iArr2.length;
        int[] copyOf = Arrays.copyOf(iArr, length + length2);
        System.arraycopy(iArr2, 0, copyOf, length, length2);
        Intrinsics.checkNotNull(copyOf);
        return copyOf;
    }

    public static List toList(Object[] objArr) {
        int length = objArr.length;
        return length != 0 ? length != 1 ? toMutableList(objArr) : Collections.singletonList(objArr[0]) : EmptyList.INSTANCE;
    }

    public static List toMutableList(Object[] objArr) {
        return new ArrayList(new ArrayAsCollection(objArr, false));
    }

    public static Set toSet(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length == 1) {
            return Collections.singleton(objArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(objArr.length));
        for (Object obj : objArr) {
            linkedHashSet.add(obj);
        }
        return linkedHashSet;
    }

    public static boolean contains(int[] iArr, int i) {
        int length = iArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                i2 = -1;
                break;
            }
            if (i == iArr[i2]) {
                break;
            }
            i2++;
        }
        return i2 >= 0;
    }

    public static void copyInto(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        System.arraycopy(iArr, i2, iArr2, i, i3 - i2);
    }

    public static void copyInto$default(int i, int i2, int i3, int[] iArr, int[] iArr2) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = iArr.length;
        }
        System.arraycopy(iArr, 0, iArr2, i, i2);
    }

    public static Set toSet(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return EmptySet.INSTANCE;
        }
        if (length != 1) {
            LinkedHashSet linkedHashSet = new LinkedHashSet(MapsKt__MapsJVMKt.mapCapacity(iArr.length));
            for (int i : iArr) {
                linkedHashSet.add(Integer.valueOf(i));
            }
            return linkedHashSet;
        }
        return Collections.singleton(Integer.valueOf(iArr[0]));
    }
}
