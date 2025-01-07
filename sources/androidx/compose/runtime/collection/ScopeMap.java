package androidx.compose.runtime.collection;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMapKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ScopeMap {
    /* renamed from: add-impl, reason: not valid java name */
    public static final void m268addimpl(MutableScatterMap mutableScatterMap, Object obj, Object obj2) {
        int findInsertIndex = mutableScatterMap.findInsertIndex(obj);
        boolean z = findInsertIndex < 0;
        Object obj3 = z ? null : mutableScatterMap.values[findInsertIndex];
        if (obj3 != null) {
            if (obj3 instanceof MutableScatterSet) {
                ((MutableScatterSet) obj3).add(obj2);
            } else if (obj3 != obj2) {
                MutableScatterSet mutableScatterSet = new MutableScatterSet();
                mutableScatterSet.add(obj3);
                mutableScatterSet.add(obj2);
                obj2 = mutableScatterSet;
            }
            obj2 = obj3;
        }
        if (!z) {
            mutableScatterMap.values[findInsertIndex] = obj2;
            return;
        }
        int i = ~findInsertIndex;
        mutableScatterMap.keys[i] = obj;
        mutableScatterMap.values[i] = obj2;
    }

    /* renamed from: constructor-impl$default, reason: not valid java name */
    public static MutableScatterMap m269constructorimpl$default() {
        long[] jArr = ScatterMapKt.EmptyGroup;
        return new MutableScatterMap();
    }

    /* renamed from: remove-impl, reason: not valid java name */
    public static final boolean m270removeimpl(MutableScatterMap mutableScatterMap, Object obj, Object obj2) {
        Object obj3 = mutableScatterMap.get(obj);
        if (obj3 == null) {
            return false;
        }
        if (!(obj3 instanceof MutableScatterSet)) {
            if (!obj3.equals(obj2)) {
                return false;
            }
            mutableScatterMap.remove(obj);
            return true;
        }
        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
        boolean remove = mutableScatterSet.remove(obj2);
        if (remove && mutableScatterSet.isEmpty()) {
            mutableScatterMap.remove(obj);
        }
        return remove;
    }

    /* renamed from: removeScope-impl, reason: not valid java name */
    public static final void m271removeScopeimpl(MutableScatterMap mutableScatterMap, Object obj) {
        boolean z;
        long[] jArr = mutableScatterMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        Object obj2 = mutableScatterMap.keys[i4];
                        Object obj3 = mutableScatterMap.values[i4];
                        if (obj3 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj3;
                            mutableScatterSet.remove(obj);
                            z = mutableScatterSet.isEmpty();
                        } else {
                            z = obj3 == obj;
                        }
                        if (z) {
                            mutableScatterMap.removeValueAt(i4);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }
}
