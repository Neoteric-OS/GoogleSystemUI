package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class AbstractIterator implements Iterator, KMappedMarker {
    public Object nextValue;
    public State state = State.NotReady;

    public abstract void computeNext();

    public final void done() {
        this.state = State.Done;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        State state = this.state;
        State state2 = State.Failed;
        if (state == state2) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        int ordinal = state.ordinal();
        if (ordinal == 0) {
            return true;
        }
        if (ordinal != 2) {
            this.state = state2;
            computeNext();
            if (this.state == State.Ready) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.state = State.NotReady;
        return this.nextValue;
    }

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final void setNext(Object obj) {
        this.nextValue = obj;
        this.state = State.Ready;
    }
}
