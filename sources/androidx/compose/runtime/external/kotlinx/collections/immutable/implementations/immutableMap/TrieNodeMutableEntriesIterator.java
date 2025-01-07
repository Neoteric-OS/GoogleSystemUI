package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrieNodeMutableEntriesIterator extends TrieNodeBaseIterator {
    public final PersistentHashMapBuilderEntriesIterator parentIterator;

    public TrieNodeMutableEntriesIterator(PersistentHashMapBuilderEntriesIterator persistentHashMapBuilderEntriesIterator) {
        this.parentIterator = persistentHashMapBuilderEntriesIterator;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.index;
        this.index = i + 2;
        PersistentHashMapBuilderEntriesIterator persistentHashMapBuilderEntriesIterator = this.parentIterator;
        Object[] objArr = this.buffer;
        return new MutableMapEntry(persistentHashMapBuilderEntriesIterator, objArr[i], objArr[i + 1]);
    }
}
