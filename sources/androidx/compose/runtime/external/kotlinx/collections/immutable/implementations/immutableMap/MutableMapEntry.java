package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class MutableMapEntry extends MapEntry implements Map.Entry, KMutableMap.Entry {
    public final PersistentHashMapBuilderEntriesIterator parentIterator;
    public Object value;

    public MutableMapEntry(PersistentHashMapBuilderEntriesIterator persistentHashMapBuilderEntriesIterator, Object obj, Object obj2) {
        super(obj, obj2);
        this.parentIterator = persistentHashMapBuilderEntriesIterator;
        this.value = obj2;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.MapEntry, java.util.Map.Entry
    public final Object getValue() {
        return this.value;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.MapEntry, java.util.Map.Entry
    public final Object setValue(Object obj) {
        Object obj2 = this.value;
        this.value = obj;
        PersistentHashMapBuilderEntriesIterator persistentHashMapBuilderEntriesIterator = this.parentIterator;
        Object obj3 = this.key;
        PersistentHashMapBuilderBaseIterator persistentHashMapBuilderBaseIterator = persistentHashMapBuilderEntriesIterator.base;
        if (persistentHashMapBuilderBaseIterator.builder.containsKey(obj3)) {
            boolean z = persistentHashMapBuilderBaseIterator.hasNext;
            if (!z) {
                persistentHashMapBuilderBaseIterator.builder.put(obj3, obj);
            } else {
                if (!z) {
                    throw new NoSuchElementException();
                }
                TrieNodeBaseIterator trieNodeBaseIterator = persistentHashMapBuilderBaseIterator.path[persistentHashMapBuilderBaseIterator.pathLastIndex];
                Object obj4 = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
                persistentHashMapBuilderBaseIterator.builder.put(obj3, obj);
                persistentHashMapBuilderBaseIterator.resetPath(obj4 != null ? obj4.hashCode() : 0, persistentHashMapBuilderBaseIterator.builder.node, obj4, 0);
            }
            persistentHashMapBuilderBaseIterator.expectedModCount = persistentHashMapBuilderBaseIterator.builder.modCount;
        }
        return obj2;
    }
}
