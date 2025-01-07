package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PersistentHashMapBuilderBaseIterator extends PersistentHashMapBaseIterator implements Iterator {
    public final PersistentHashMapBuilder builder;
    public int expectedModCount;
    public Object lastIteratedKey;
    public boolean nextWasInvoked;

    public PersistentHashMapBuilderBaseIterator(PersistentHashMapBuilder persistentHashMapBuilder, TrieNodeBaseIterator[] trieNodeBaseIteratorArr) {
        super(persistentHashMapBuilder.node, trieNodeBaseIteratorArr);
        this.builder = persistentHashMapBuilder;
        this.expectedModCount = persistentHashMapBuilder.modCount;
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final Object next() {
        if (this.builder.modCount != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        if (!this.hasNext) {
            throw new NoSuchElementException();
        }
        TrieNodeBaseIterator trieNodeBaseIterator = this.path[this.pathLastIndex];
        this.lastIteratedKey = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
        this.nextWasInvoked = true;
        return super.next();
    }

    @Override // androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMapBaseIterator, java.util.Iterator
    public final void remove() {
        if (!this.nextWasInvoked) {
            throw new IllegalStateException();
        }
        boolean z = this.hasNext;
        if (!z) {
            TypeIntrinsics.asMutableMap(this.builder).remove(this.lastIteratedKey);
        } else {
            if (!z) {
                throw new NoSuchElementException();
            }
            TrieNodeBaseIterator trieNodeBaseIterator = this.path[this.pathLastIndex];
            Object obj = trieNodeBaseIterator.buffer[trieNodeBaseIterator.index];
            TypeIntrinsics.asMutableMap(this.builder).remove(this.lastIteratedKey);
            resetPath(obj != null ? obj.hashCode() : 0, this.builder.node, obj, 0);
        }
        this.lastIteratedKey = null;
        this.nextWasInvoked = false;
        this.expectedModCount = this.builder.modCount;
    }

    public final void resetPath(int i, TrieNode trieNode, Object obj, int i2) {
        int i3 = i2 * 5;
        if (i3 <= 30) {
            int indexSegment = 1 << TrieNodeKt.indexSegment(i, i3);
            if (trieNode.hasEntryAt$runtime_release(indexSegment)) {
                this.path[i2].reset(trieNode.buffer, Integer.bitCount(trieNode.dataMap) * 2, trieNode.entryKeyIndex$runtime_release(indexSegment));
                this.pathLastIndex = i2;
                return;
            } else {
                int nodeIndex$runtime_release = trieNode.nodeIndex$runtime_release(indexSegment);
                TrieNode nodeAtIndex$runtime_release = trieNode.nodeAtIndex$runtime_release(nodeIndex$runtime_release);
                this.path[i2].reset(trieNode.buffer, Integer.bitCount(trieNode.dataMap) * 2, nodeIndex$runtime_release);
                resetPath(i, nodeAtIndex$runtime_release, obj, i2 + 1);
                return;
            }
        }
        TrieNodeBaseIterator trieNodeBaseIterator = this.path[i2];
        Object[] objArr = trieNode.buffer;
        trieNodeBaseIterator.reset(objArr, objArr.length, 0);
        while (true) {
            TrieNodeBaseIterator trieNodeBaseIterator2 = this.path[i2];
            if (Intrinsics.areEqual(trieNodeBaseIterator2.buffer[trieNodeBaseIterator2.index], obj)) {
                this.pathLastIndex = i2;
                return;
            } else {
                this.path[i2].index += 2;
            }
        }
    }
}
