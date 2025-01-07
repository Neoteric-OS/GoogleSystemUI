package kotlin.collections.builders;

import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.ranges.IntProgressionIterator;
import kotlin.ranges.IntRange;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MapBuilder implements Map, Serializable, KMutableMap {
    public static final Companion Companion = null;
    public static final MapBuilder Empty;
    private MapBuilderEntries entriesView;
    private int[] hashArray;
    private int hashShift;
    private boolean isReadOnly;
    private Object[] keysArray;
    private MapBuilderKeys keysView;
    private int length;
    private int maxProbeDistance;
    private int modCount;
    private int[] presenceArray;
    private int size;
    private Object[] valuesArray;
    private MapBuilderValues valuesView;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class EntryRef implements Map.Entry, KMutableMap.Entry {
        public final int index;
        public final MapBuilder map;

        public EntryRef(MapBuilder mapBuilder, int i) {
            this.map = mapBuilder;
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (Intrinsics.areEqual(entry.getKey(), getKey()) && Intrinsics.areEqual(entry.getValue(), getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.map.keysArray[this.index];
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            Object[] objArr = this.map.valuesArray;
            Intrinsics.checkNotNull(objArr);
            return objArr[this.index];
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            Object key = getKey();
            int hashCode = key != null ? key.hashCode() : 0;
            Object value = getValue();
            return hashCode ^ (value != null ? value.hashCode() : 0);
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            this.map.checkIsMutable$kotlin_stdlib();
            Object[] allocateValuesArray = this.map.allocateValuesArray();
            int i = this.index;
            Object obj2 = allocateValuesArray[i];
            allocateValuesArray[i] = obj;
            return obj2;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(getKey());
            sb.append('=');
            sb.append(getValue());
            return sb.toString();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class KeysItr implements Iterator, KMappedMarker {
        public final /* synthetic */ int $r8$classId;
        public int expectedModCount;
        public int index;
        public int lastIndex = -1;
        public final MapBuilder map;

        public KeysItr(MapBuilder mapBuilder, int i) {
            this.$r8$classId = i;
            this.map = mapBuilder;
            this.expectedModCount = mapBuilder.modCount;
            initNext$kotlin_stdlib();
        }

        public final void checkForComodification$kotlin_stdlib() {
            if (this.map.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.index < this.map.length;
        }

        public final void initNext$kotlin_stdlib() {
            while (true) {
                int i = this.index;
                MapBuilder mapBuilder = this.map;
                if (i >= mapBuilder.length) {
                    return;
                }
                int[] iArr = mapBuilder.presenceArray;
                int i2 = this.index;
                if (iArr[i2] >= 0) {
                    return;
                } else {
                    this.index = i2 + 1;
                }
            }
        }

        @Override // java.util.Iterator
        public final Object next() {
            switch (this.$r8$classId) {
                case 0:
                    checkForComodification$kotlin_stdlib();
                    int i = this.index;
                    MapBuilder mapBuilder = this.map;
                    if (i >= mapBuilder.length) {
                        throw new NoSuchElementException();
                    }
                    int i2 = this.index;
                    this.index = i2 + 1;
                    this.lastIndex = i2;
                    Object obj = mapBuilder.keysArray[this.lastIndex];
                    initNext$kotlin_stdlib();
                    return obj;
                case 1:
                    checkForComodification$kotlin_stdlib();
                    int i3 = this.index;
                    MapBuilder mapBuilder2 = this.map;
                    if (i3 >= mapBuilder2.length) {
                        throw new NoSuchElementException();
                    }
                    int i4 = this.index;
                    this.index = i4 + 1;
                    this.lastIndex = i4;
                    EntryRef entryRef = new EntryRef(mapBuilder2, i4);
                    initNext$kotlin_stdlib();
                    return entryRef;
                default:
                    checkForComodification$kotlin_stdlib();
                    int i5 = this.index;
                    MapBuilder mapBuilder3 = this.map;
                    if (i5 >= mapBuilder3.length) {
                        throw new NoSuchElementException();
                    }
                    int i6 = this.index;
                    this.index = i6 + 1;
                    this.lastIndex = i6;
                    Object[] objArr = mapBuilder3.valuesArray;
                    Intrinsics.checkNotNull(objArr);
                    Object obj2 = objArr[this.lastIndex];
                    initNext$kotlin_stdlib();
                    return obj2;
            }
        }

        @Override // java.util.Iterator
        public final void remove() {
            checkForComodification$kotlin_stdlib();
            if (this.lastIndex == -1) {
                throw new IllegalStateException("Call next() before removing element from the iterator.");
            }
            MapBuilder mapBuilder = this.map;
            mapBuilder.checkIsMutable$kotlin_stdlib();
            mapBuilder.removeKeyAt(this.lastIndex);
            this.lastIndex = -1;
            this.expectedModCount = mapBuilder.modCount;
        }
    }

    static {
        MapBuilder mapBuilder = new MapBuilder(0);
        mapBuilder.isReadOnly = true;
        Empty = mapBuilder;
    }

    public MapBuilder() {
        this(8);
    }

    private final Object writeReplace() {
        if (this.isReadOnly) {
            return new SerializedMap(this);
        }
        throw new NotSerializableException("The map cannot be serialized while it is being built.");
    }

    public final int addKey$kotlin_stdlib(Object obj) {
        checkIsMutable$kotlin_stdlib();
        while (true) {
            int hash = hash(obj);
            int i = this.maxProbeDistance * 2;
            int length = this.hashArray.length / 2;
            if (i > length) {
                i = length;
            }
            int i2 = 0;
            while (true) {
                int[] iArr = this.hashArray;
                int i3 = iArr[hash];
                if (i3 <= 0) {
                    int i4 = this.length;
                    Object[] objArr = this.keysArray;
                    if (i4 < objArr.length) {
                        int i5 = i4 + 1;
                        this.length = i5;
                        objArr[i4] = obj;
                        this.presenceArray[i4] = hash;
                        iArr[hash] = i5;
                        this.size++;
                        this.modCount++;
                        if (i2 > this.maxProbeDistance) {
                            this.maxProbeDistance = i2;
                        }
                        return i4;
                    }
                    ensureExtraCapacity$1(1);
                } else {
                    if (Intrinsics.areEqual(this.keysArray[i3 - 1], obj)) {
                        return -i3;
                    }
                    i2++;
                    if (i2 > i) {
                        rehash(this.hashArray.length * 2);
                        break;
                    }
                    hash = hash == 0 ? this.hashArray.length - 1 : hash - 1;
                }
            }
        }
    }

    public final Object[] allocateValuesArray() {
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            return objArr;
        }
        int length = this.keysArray.length;
        if (length < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr2 = new Object[length];
        this.valuesArray = objArr2;
        return objArr2;
    }

    public final MapBuilder build() {
        checkIsMutable$kotlin_stdlib();
        this.isReadOnly = true;
        return this.size > 0 ? this : Empty;
    }

    public final void checkIsMutable$kotlin_stdlib() {
        if (this.isReadOnly) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final void clear() {
        checkIsMutable$kotlin_stdlib();
        IntProgressionIterator it = new IntRange(0, this.length - 1, 1).iterator();
        while (it.hasNext) {
            int nextInt = it.nextInt();
            int[] iArr = this.presenceArray;
            int i = iArr[nextInt];
            if (i >= 0) {
                this.hashArray[i] = 0;
                iArr[nextInt] = -1;
            }
        }
        ListBuilderKt.resetRange(this.keysArray, 0, this.length);
        Object[] objArr = this.valuesArray;
        if (objArr != null) {
            ListBuilderKt.resetRange(objArr, 0, this.length);
        }
        this.size = 0;
        this.length = 0;
        this.modCount++;
    }

    public final boolean containsAllEntries$kotlin_stdlib(Collection collection) {
        for (Object obj : collection) {
            if (obj != null) {
                try {
                    if (!containsEntry$kotlin_stdlib((Map.Entry) obj)) {
                    }
                } catch (ClassCastException unused) {
                }
            }
            return false;
        }
        return true;
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry entry) {
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return Intrinsics.areEqual(objArr[findKey], entry.getValue());
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return findKey(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return findValue(obj) >= 0;
    }

    public final void ensureExtraCapacity$1(int i) {
        Object[] objArr = this.keysArray;
        int length = objArr.length;
        int i2 = this.length;
        int i3 = length - i2;
        int i4 = i2 - this.size;
        if (i3 < i && i3 + i4 >= i && i4 >= objArr.length / 4) {
            rehash(this.hashArray.length);
            return;
        }
        int i5 = i2 + i;
        if (i5 < 0) {
            throw new OutOfMemoryError();
        }
        if (i5 > objArr.length) {
            int length2 = objArr.length;
            int i6 = length2 + (length2 >> 1);
            if (i6 - i5 < 0) {
                i6 = i5;
            }
            if (i6 - 2147483639 > 0) {
                i6 = i5 > 2147483639 ? Integer.MAX_VALUE : 2147483639;
            }
            this.keysArray = Arrays.copyOf(objArr, i6);
            Object[] objArr2 = this.valuesArray;
            this.valuesArray = objArr2 != null ? Arrays.copyOf(objArr2, i6) : null;
            this.presenceArray = Arrays.copyOf(this.presenceArray, i6);
            if (i6 < 1) {
                i6 = 1;
            }
            int highestOneBit = Integer.highestOneBit(i6 * 3);
            if (highestOneBit > this.hashArray.length) {
                rehash(highestOneBit);
            }
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        MapBuilderEntries mapBuilderEntries = this.entriesView;
        if (mapBuilderEntries != null) {
            return mapBuilderEntries;
        }
        MapBuilderEntries mapBuilderEntries2 = new MapBuilderEntries(this);
        this.entriesView = mapBuilderEntries2;
        return mapBuilderEntries2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.size != map.size() || !containsAllEntries$kotlin_stdlib(map.entrySet())) {
                }
            }
            return false;
        }
        return true;
    }

    public final int findKey(Object obj) {
        int hash = hash(obj);
        int i = this.maxProbeDistance;
        while (true) {
            int i2 = this.hashArray[hash];
            if (i2 == 0) {
                return -1;
            }
            if (i2 > 0) {
                int i3 = i2 - 1;
                if (Intrinsics.areEqual(this.keysArray[i3], obj)) {
                    return i3;
                }
            }
            i--;
            if (i < 0) {
                return -1;
            }
            hash = hash == 0 ? this.hashArray.length - 1 : hash - 1;
        }
    }

    public final int findValue(Object obj) {
        int i = this.length;
        while (true) {
            i--;
            if (i < 0) {
                return -1;
            }
            if (this.presenceArray[i] >= 0) {
                Object[] objArr = this.valuesArray;
                Intrinsics.checkNotNull(objArr);
                if (Intrinsics.areEqual(objArr[i], obj)) {
                    return i;
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int findKey = findKey(obj);
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        return objArr[findKey];
    }

    public final int hash(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.hashShift;
    }

    @Override // java.util.Map
    public final int hashCode() {
        KeysItr keysItr = new KeysItr(this, 1);
        int i = 0;
        while (keysItr.hasNext()) {
            int i2 = keysItr.index;
            MapBuilder mapBuilder = keysItr.map;
            if (i2 >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            int i3 = keysItr.index;
            keysItr.index = i3 + 1;
            keysItr.lastIndex = i3;
            Object obj = mapBuilder.keysArray[keysItr.lastIndex];
            int hashCode = obj != null ? obj.hashCode() : 0;
            Object[] objArr = mapBuilder.valuesArray;
            Intrinsics.checkNotNull(objArr);
            Object obj2 = objArr[keysItr.lastIndex];
            int hashCode2 = obj2 != null ? obj2.hashCode() : 0;
            keysItr.initNext$kotlin_stdlib();
            i += hashCode ^ hashCode2;
        }
        return i;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.size == 0;
    }

    public final boolean isReadOnly$kotlin_stdlib() {
        return this.isReadOnly;
    }

    @Override // java.util.Map
    public final Set keySet() {
        MapBuilderKeys mapBuilderKeys = this.keysView;
        if (mapBuilderKeys != null) {
            return mapBuilderKeys;
        }
        MapBuilderKeys mapBuilderKeys2 = new MapBuilderKeys(this);
        this.keysView = mapBuilderKeys2;
        return mapBuilderKeys2;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        checkIsMutable$kotlin_stdlib();
        int addKey$kotlin_stdlib = addKey$kotlin_stdlib(obj);
        Object[] allocateValuesArray = allocateValuesArray();
        if (addKey$kotlin_stdlib >= 0) {
            allocateValuesArray[addKey$kotlin_stdlib] = obj2;
            return null;
        }
        int i = (-addKey$kotlin_stdlib) - 1;
        Object obj3 = allocateValuesArray[i];
        allocateValuesArray[i] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        checkIsMutable$kotlin_stdlib();
        Set<Map.Entry> entrySet = map.entrySet();
        if (entrySet.isEmpty()) {
            return;
        }
        ensureExtraCapacity$1(entrySet.size());
        for (Map.Entry entry : entrySet) {
            int addKey$kotlin_stdlib = addKey$kotlin_stdlib(entry.getKey());
            Object[] allocateValuesArray = allocateValuesArray();
            if (addKey$kotlin_stdlib >= 0) {
                allocateValuesArray[addKey$kotlin_stdlib] = entry.getValue();
            } else {
                int i = (-addKey$kotlin_stdlib) - 1;
                if (!Intrinsics.areEqual(entry.getValue(), allocateValuesArray[i])) {
                    allocateValuesArray[i] = entry.getValue();
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x0066, code lost:
    
        r3[r0] = r7;
        r6.presenceArray[r2] = r0;
        r2 = r7;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void rehash(int r7) {
        /*
            r6 = this;
            int r0 = r6.modCount
            int r0 = r0 + 1
            r6.modCount = r0
            int r0 = r6.length
            int r1 = r6.size
            r2 = 0
            if (r0 <= r1) goto L3a
            java.lang.Object[] r0 = r6.valuesArray
            r1 = r2
            r3 = r1
        L11:
            int r4 = r6.length
            if (r1 >= r4) goto L2c
            int[] r4 = r6.presenceArray
            r4 = r4[r1]
            if (r4 < 0) goto L29
            java.lang.Object[] r4 = r6.keysArray
            r5 = r4[r1]
            r4[r3] = r5
            if (r0 == 0) goto L27
            r4 = r0[r1]
            r0[r3] = r4
        L27:
            int r3 = r3 + 1
        L29:
            int r1 = r1 + 1
            goto L11
        L2c:
            java.lang.Object[] r1 = r6.keysArray
            kotlin.collections.builders.ListBuilderKt.resetRange(r1, r3, r4)
            if (r0 == 0) goto L38
            int r1 = r6.length
            kotlin.collections.builders.ListBuilderKt.resetRange(r0, r3, r1)
        L38:
            r6.length = r3
        L3a:
            int[] r0 = r6.hashArray
            int r1 = r0.length
            if (r7 == r1) goto L4c
            int[] r0 = new int[r7]
            r6.hashArray = r0
            int r7 = java.lang.Integer.numberOfLeadingZeros(r7)
            int r7 = r7 + 1
            r6.hashShift = r7
            goto L50
        L4c:
            int r7 = r0.length
            java.util.Arrays.fill(r0, r2, r7, r2)
        L50:
            int r7 = r6.length
            if (r2 >= r7) goto L84
            int r7 = r2 + 1
            java.lang.Object[] r0 = r6.keysArray
            r0 = r0[r2]
            int r0 = r6.hash(r0)
            int r1 = r6.maxProbeDistance
        L60:
            int[] r3 = r6.hashArray
            r4 = r3[r0]
            if (r4 != 0) goto L6e
            r3[r0] = r7
            int[] r1 = r6.presenceArray
            r1[r2] = r0
            r2 = r7
            goto L50
        L6e:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L7c
            int r4 = r0 + (-1)
            if (r0 != 0) goto L7a
            int r0 = r3.length
            int r0 = r0 + (-1)
            goto L60
        L7a:
            r0 = r4
            goto L60
        L7c:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r6.<init>(r7)
            throw r6
        L84:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.rehash(int):void");
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(obj);
        if (findKey < 0) {
            findKey = -1;
        } else {
            removeKeyAt(findKey);
        }
        if (findKey < 0) {
            return null;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        Object obj2 = objArr[findKey];
        objArr[findKey] = null;
        return obj2;
    }

    public final boolean removeEntry$kotlin_stdlib(Map.Entry entry) {
        checkIsMutable$kotlin_stdlib();
        int findKey = findKey(entry.getKey());
        if (findKey < 0) {
            return false;
        }
        Object[] objArr = this.valuesArray;
        Intrinsics.checkNotNull(objArr);
        if (!Intrinsics.areEqual(objArr[findKey], entry.getValue())) {
            return false;
        }
        removeKeyAt(findKey);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x005d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[LOOP:0: B:5:0x0019->B:22:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void removeKeyAt(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.keysArray
            r1 = 0
            r0[r12] = r1
            int[] r0 = r11.presenceArray
            r0 = r0[r12]
            int r1 = r11.maxProbeDistance
            int r1 = r1 * 2
            int[] r2 = r11.hashArray
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L15
            r1 = r2
        L15:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L19:
            int r5 = r0 + (-1)
            if (r0 != 0) goto L23
            int[] r0 = r11.hashArray
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L24
        L23:
            r0 = r5
        L24:
            int r4 = r4 + 1
            int r5 = r11.maxProbeDistance
            r6 = -1
            if (r4 <= r5) goto L30
            int[] r0 = r11.hashArray
            r0[r1] = r2
            goto L61
        L30:
            int[] r5 = r11.hashArray
            r7 = r5[r0]
            if (r7 != 0) goto L39
            r5[r1] = r2
            goto L61
        L39:
            if (r7 >= 0) goto L40
            r5[r1] = r6
        L3d:
            r1 = r0
            r4 = r2
            goto L5a
        L40:
            java.lang.Object[] r5 = r11.keysArray
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.hash(r5)
            int r5 = r5 - r0
            int[] r9 = r11.hashArray
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L5a
            r9[r1] = r7
            int[] r4 = r11.presenceArray
            r4[r8] = r1
            goto L3d
        L5a:
            int r3 = r3 + r6
            if (r3 >= 0) goto L19
            int[] r0 = r11.hashArray
            r0[r1] = r6
        L61:
            int[] r0 = r11.presenceArray
            r0[r12] = r6
            int r12 = r11.size
            int r12 = r12 + r6
            r11.size = r12
            int r12 = r11.modCount
            int r12 = r12 + 1
            r11.modCount = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.builders.MapBuilder.removeKeyAt(int):void");
    }

    @Override // java.util.Map
    public final int size() {
        return this.size;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder((this.size * 3) + 2);
        sb.append("{");
        KeysItr keysItr = new KeysItr(this, 1);
        int i = 0;
        while (keysItr.hasNext()) {
            if (i > 0) {
                sb.append(", ");
            }
            int i2 = keysItr.index;
            MapBuilder mapBuilder = keysItr.map;
            if (i2 >= mapBuilder.length) {
                throw new NoSuchElementException();
            }
            int i3 = keysItr.index;
            keysItr.index = i3 + 1;
            keysItr.lastIndex = i3;
            Object obj = mapBuilder.keysArray[keysItr.lastIndex];
            if (obj == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj);
            }
            sb.append('=');
            Object[] objArr = mapBuilder.valuesArray;
            Intrinsics.checkNotNull(objArr);
            Object obj2 = objArr[keysItr.lastIndex];
            if (obj2 == mapBuilder) {
                sb.append("(this Map)");
            } else {
                sb.append(obj2);
            }
            keysItr.initNext$kotlin_stdlib();
            i++;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        MapBuilderValues mapBuilderValues = this.valuesView;
        if (mapBuilderValues != null) {
            return mapBuilderValues;
        }
        MapBuilderValues mapBuilderValues2 = new MapBuilderValues(this);
        this.valuesView = mapBuilderValues2;
        return mapBuilderValues2;
    }

    public MapBuilder(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i];
        int[] iArr = new int[i];
        int highestOneBit = Integer.highestOneBit((i < 1 ? 1 : i) * 3);
        this.keysArray = objArr;
        this.valuesArray = null;
        this.presenceArray = iArr;
        this.hashArray = new int[highestOneBit];
        this.maxProbeDistance = 2;
        this.length = 0;
        this.hashShift = Integer.numberOfLeadingZeros(highestOneBit) + 1;
    }
}
