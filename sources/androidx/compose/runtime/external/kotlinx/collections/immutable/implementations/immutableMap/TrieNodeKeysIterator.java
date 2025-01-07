package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrieNodeKeysIterator extends TrieNodeBaseIterator {
    @Override // java.util.Iterator
    public final Object next() {
        int i = this.index;
        this.index = i + 2;
        return this.buffer[i];
    }
}
