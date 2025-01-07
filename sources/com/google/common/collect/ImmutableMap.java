package com.google.common.collect;

import com.android.settingslib.notification.modes.ZenIcon;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMap;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ImmutableMap implements Map, Serializable {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final long serialVersionUID = 912559;
    public transient ImmutableSet entrySet;
    public transient ImmutableSet keySet;
    public transient ImmutableCollection values;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public Object[] alternatingKeysAndValues;
        public DuplicateKey duplicateKey;
        public int size = 0;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class DuplicateKey {
            public final Object key;
            public final Object value1;
            public final Object value2;

            public DuplicateKey(Object obj, Object obj2, Object obj3) {
                this.key = obj;
                this.value1 = obj2;
                this.value2 = obj3;
            }

            public final IllegalArgumentException exception() {
                StringBuilder sb = new StringBuilder("Multiple entries with same key: ");
                Object obj = this.key;
                sb.append(obj);
                sb.append("=");
                sb.append(this.value1);
                sb.append(" and ");
                sb.append(obj);
                sb.append("=");
                sb.append(this.value2);
                return new IllegalArgumentException(sb.toString());
            }
        }

        public Builder(int i) {
            this.alternatingKeysAndValues = new Object[i * 2];
        }

        public final ImmutableMap buildOrThrow() {
            DuplicateKey duplicateKey = this.duplicateKey;
            if (duplicateKey != null) {
                throw duplicateKey.exception();
            }
            RegularImmutableMap create = RegularImmutableMap.create(this.size, this.alternatingKeysAndValues, this);
            DuplicateKey duplicateKey2 = this.duplicateKey;
            if (duplicateKey2 == null) {
                return create;
            }
            throw duplicateKey2.exception();
        }

        public final void put(Object obj, Object obj2) {
            int i = (this.size + 1) * 2;
            Object[] objArr = this.alternatingKeysAndValues;
            if (i > objArr.length) {
                this.alternatingKeysAndValues = Arrays.copyOf(objArr, ImmutableCollection.Builder.expandedCapacity(objArr.length, i));
            }
            if (obj == null) {
                throw new NullPointerException("null key in entry: null=" + obj2);
            }
            if (obj2 == null) {
                throw new NullPointerException("null value in entry: " + obj + "=null");
            }
            Object[] objArr2 = this.alternatingKeysAndValues;
            int i2 = this.size;
            int i3 = i2 * 2;
            objArr2[i3] = obj;
            objArr2[i3 + 1] = obj2;
            this.size = i2 + 1;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        private final Object keys;
        private final Object values;

        public SerializedForm(ImmutableMap immutableMap) {
            Object[] objArr = new Object[immutableMap.size()];
            Object[] objArr2 = new Object[immutableMap.size()];
            UnmodifiableIterator it = immutableMap.entrySet().iterator();
            int i = 0;
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                objArr[i] = entry.getKey();
                objArr2[i] = entry.getValue();
                i++;
            }
            this.keys = objArr;
            this.values = objArr2;
        }

        public final Object readResolve() {
            Object obj = this.keys;
            if (obj instanceof ImmutableSet) {
                ImmutableSet immutableSet = (ImmutableSet) obj;
                ImmutableCollection immutableCollection = (ImmutableCollection) this.values;
                Builder builder = new Builder(immutableSet.size());
                UnmodifiableIterator it = immutableSet.iterator();
                UnmodifiableIterator it2 = immutableCollection.iterator();
                while (it.hasNext()) {
                    builder.put(it.next(), it2.next());
                }
                return builder.buildOrThrow();
            }
            Object[] objArr = (Object[]) obj;
            Object[] objArr2 = (Object[]) this.values;
            Builder builder2 = new Builder(objArr.length);
            for (int i = 0; i < objArr.length; i++) {
                builder2.put(objArr[i], objArr2[i]);
            }
            return builder2.buildOrThrow();
        }
    }

    static {
        Map.Entry[] entryArr = new Map.Entry[0];
    }

    public static ImmutableMap of(ZenIcon.Key key, ZenIcon.Key key2, ZenIcon.Key key3, ZenIcon.Key key4, ZenIcon.Key key5, ZenIcon.Key key6, ZenIcon.Key key7, ZenIcon.Key key8, ZenIcon.Key key9) {
        return RegularImmutableMap.create(9, new Object[]{-1, key, 0, key2, 1, key3, 2, key4, 3, key5, 4, key6, 5, key7, 6, key8, 7, key9}, null);
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return get(obj) != null;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        ImmutableCollection immutableCollection = this.values;
        if (immutableCollection == null) {
            RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
            RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.alternatingKeysAndValues, 1, regularImmutableMap.size);
            this.values = keysOrValuesAsList;
            immutableCollection = keysOrValuesAsList;
        }
        return immutableCollection.contains(obj);
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Map) {
            return entrySet().equals(((Map) obj).entrySet());
        }
        return false;
    }

    @Override // java.util.Map
    public abstract Object get(Object obj);

    @Override // java.util.Map
    public final Object getOrDefault(Object obj, Object obj2) {
        Object obj3 = get(obj);
        return obj3 != null ? obj3 : obj2;
    }

    @Override // java.util.Map
    public final int hashCode() {
        Iterator it = entrySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            i = ~(~(i + (next != null ? next.hashCode() : 0)));
        }
        return i;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        ImmutableSet immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeySet keySet = new RegularImmutableMap.KeySet(regularImmutableMap, new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.alternatingKeysAndValues, 0, regularImmutableMap.size));
        this.keySet = keySet;
        return keySet;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final String toString() {
        int size = size();
        CollectPreconditions.checkNonnegative(size, "size");
        StringBuilder sb = new StringBuilder((int) Math.min(size * 8, 1073741824L));
        sb.append('{');
        boolean z = true;
        for (Map.Entry entry : entrySet()) {
            if (!z) {
                sb.append(", ");
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            z = false;
        }
        sb.append('}');
        return sb.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        ImmutableCollection immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.KeysOrValuesAsList keysOrValuesAsList = new RegularImmutableMap.KeysOrValuesAsList(regularImmutableMap.alternatingKeysAndValues, 1, regularImmutableMap.size);
        this.values = keysOrValuesAsList;
        return keysOrValuesAsList;
    }

    public Object writeReplace() {
        return new SerializedForm(this);
    }

    @Override // java.util.Map
    public final ImmutableSet entrySet() {
        ImmutableSet immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        RegularImmutableMap regularImmutableMap = (RegularImmutableMap) this;
        RegularImmutableMap.EntrySet entrySet = new RegularImmutableMap.EntrySet(regularImmutableMap, regularImmutableMap.alternatingKeysAndValues, regularImmutableMap.size);
        this.entrySet = entrySet;
        return entrySet;
    }
}
