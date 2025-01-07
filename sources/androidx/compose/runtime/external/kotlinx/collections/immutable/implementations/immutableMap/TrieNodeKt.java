package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import kotlin.collections.ArraysKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class TrieNodeKt {
    public static final Object[] access$insertEntryAtIndex(Object[] objArr, int i, Object obj, Object obj2) {
        Object[] objArr2 = new Object[objArr.length + 2];
        ArraysKt.copyInto$default(0, i, 6, objArr, objArr2);
        ArraysKt.copyInto(i + 2, i, objArr.length, objArr, objArr2);
        objArr2[i] = obj;
        objArr2[i + 1] = obj2;
        return objArr2;
    }

    public static final Object[] access$removeEntryAtIndex(int i, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 2];
        ArraysKt.copyInto$default(0, i, 6, objArr, objArr2);
        ArraysKt.copyInto(i, i + 2, objArr.length, objArr, objArr2);
        return objArr2;
    }

    public static final Object[] access$removeNodeAtIndex(int i, Object[] objArr) {
        Object[] objArr2 = new Object[objArr.length - 1];
        ArraysKt.copyInto$default(0, i, 6, objArr, objArr2);
        ArraysKt.copyInto(i, i + 1, objArr.length, objArr, objArr2);
        return objArr2;
    }

    public static final int indexSegment(int i, int i2) {
        return (i >> i2) & 31;
    }
}
