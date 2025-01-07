package androidx.collection;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MapWrapper implements Map, KMappedMarker {
    public Entries _entries;
    public Keys _keys;
    public Values _values;
    public final MutableScatterMap parent;

    public MapWrapper(MutableScatterMap mutableScatterMap) {
        this.parent = mutableScatterMap;
    }

    @Override // java.util.Map
    public final void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object compute(Object obj, BiFunction biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object computeIfAbsent(Object obj, Function function) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object computeIfPresent(Object obj, BiFunction biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return this.parent.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return this.parent.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        Entries entries = this._entries;
        if (entries != null) {
            return entries;
        }
        Entries entries2 = new Entries(this.parent);
        this._entries = entries2;
        return entries2;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return this.parent.get(obj);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.parent.isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        Keys keys = this._keys;
        if (keys != null) {
            return keys;
        }
        Keys keys2 = new Keys(this.parent);
        this._keys = keys2;
        return keys2;
    }

    @Override // java.util.Map
    public final Object merge(Object obj, Object obj2, BiFunction biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object putIfAbsent(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final Object replace(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final void replaceAll(BiFunction biFunction) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final int size() {
        return this.parent._size;
    }

    @Override // java.util.Map
    public final Collection values() {
        Values values = this._values;
        if (values != null) {
            return values;
        }
        Values values2 = new Values(this.parent);
        this._values = values2;
        return values2;
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Map
    public final boolean replace(Object obj, Object obj2, Object obj3) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
