package kotlin.collections.builders;

import java.util.Collection;
import java.util.Iterator;
import kotlin.collections.AbstractMutableCollection;
import kotlin.collections.builders.MapBuilder;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MapBuilderValues extends AbstractMutableCollection {
    public final MapBuilder backing;

    public MapBuilderValues(MapBuilder mapBuilder) {
        this.backing = mapBuilder;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean add(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.backing.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.backing.containsValue(obj);
    }

    @Override // kotlin.collections.AbstractMutableCollection
    public final int getSize() {
        return this.backing.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.backing.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        MapBuilder mapBuilder = this.backing;
        mapBuilder.getClass();
        return new MapBuilder.KeysItr(mapBuilder, 2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean remove(Object obj) {
        MapBuilder mapBuilder = this.backing;
        mapBuilder.checkIsMutable$kotlin_stdlib();
        int findValue = mapBuilder.findValue(obj);
        if (findValue < 0) {
            return false;
        }
        mapBuilder.removeKeyAt(findValue);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.removeAll(collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final boolean retainAll(Collection collection) {
        this.backing.checkIsMutable$kotlin_stdlib();
        return super.retainAll(collection);
    }
}
