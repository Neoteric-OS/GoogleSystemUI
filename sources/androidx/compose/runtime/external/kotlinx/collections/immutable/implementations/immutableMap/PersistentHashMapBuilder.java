package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PersistentHashMapBuilder extends AbstractMap implements PersistentMap.Builder, Map, KMutableMap {
    public PersistentHashMap map;
    public int modCount;
    public TrieNode node;
    public Object operationResult;
    public MutabilityOwnership ownership = new MutabilityOwnership();
    public int size;

    public PersistentHashMapBuilder(PersistentHashMap persistentHashMap) {
        this.map = persistentHashMap;
        this.node = persistentHashMap.node;
        persistentHashMap.getClass();
        this.size = persistentHashMap.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.node = TrieNode.EMPTY;
        setSize(0);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean containsKey(Object obj) {
        return this.node.containsKey(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new PersistentHashMapBuilderEntries(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object get(Object obj) {
        return this.node.get(obj != null ? obj.hashCode() : 0, 0, obj);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new PersistentHashMapBuilderKeys(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        this.operationResult = null;
        this.node = this.node.mutablePut(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        return this.operationResult;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void putAll(Map map) {
        PersistentHashMap persistentHashMap = null;
        PersistentHashMap persistentHashMap2 = map instanceof PersistentHashMap ? (PersistentHashMap) map : null;
        if (persistentHashMap2 == null) {
            PersistentHashMapBuilder persistentHashMapBuilder = map instanceof PersistentHashMapBuilder ? (PersistentHashMapBuilder) map : null;
            if (persistentHashMapBuilder != null) {
                persistentHashMap = persistentHashMapBuilder.build();
            }
        } else {
            persistentHashMap = persistentHashMap2;
        }
        if (persistentHashMap == null) {
            super.putAll(map);
            return;
        }
        DeltaCounter deltaCounter = new DeltaCounter();
        deltaCounter.count = 0;
        int i = this.size;
        this.node = this.node.mutablePutAll(persistentHashMap.node, 0, deltaCounter, this);
        int i2 = (persistentHashMap.size + i) - deltaCounter.count;
        if (i != i2) {
            setSize(i2);
        }
    }

    @Override // java.util.Map
    public final boolean remove(Object obj, Object obj2) {
        int i = this.size;
        TrieNode mutableRemove = this.node.mutableRemove(obj != null ? obj.hashCode() : 0, obj, obj2, 0, this);
        if (mutableRemove == null) {
            mutableRemove = TrieNode.EMPTY;
        }
        this.node = mutableRemove;
        return i != this.size;
    }

    public final void setSize(int i) {
        this.size = i;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return new PersistentHashMapBuilderValues(this);
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap.Builder
    public PersistentHashMap build() {
        TrieNode trieNode = this.node;
        PersistentHashMap persistentHashMap = this.map;
        if (trieNode != persistentHashMap.node) {
            this.ownership = new MutabilityOwnership();
            persistentHashMap = new PersistentHashMap(this.node, this.size);
        }
        this.map = persistentHashMap;
        return persistentHashMap;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public Object remove(Object obj) {
        this.operationResult = null;
        TrieNode mutableRemove = this.node.mutableRemove(obj != null ? obj.hashCode() : 0, obj, 0, this);
        if (mutableRemove == null) {
            mutableRemove = TrieNode.EMPTY;
        }
        this.node = mutableRemove;
        return this.operationResult;
    }
}
