package androidx.core.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TreeIterator implements Iterator, KMappedMarker {
    public final Function1 getChildIterator;
    public Iterator iterator;
    public final List stack = new ArrayList();

    public TreeIterator(ViewGroupKt$iterator$1 viewGroupKt$iterator$1, Function1 function1) {
        this.getChildIterator = function1;
        this.iterator = viewGroupKt$iterator$1;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public final Object next() {
        Object next = this.iterator.next();
        Iterator it = (Iterator) this.getChildIterator.invoke(next);
        if (it == null || !it.hasNext()) {
            while (!this.iterator.hasNext() && !this.stack.isEmpty()) {
                this.iterator = (Iterator) CollectionsKt.last(this.stack);
                List list = this.stack;
                if (list.isEmpty()) {
                    throw new NoSuchElementException("List is empty.");
                }
                list.remove(CollectionsKt__CollectionsKt.getLastIndex(list));
            }
        } else {
            this.stack.add(this.iterator);
            this.iterator = it;
        }
        return next;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}
