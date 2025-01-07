package kotlin.collections;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;
import androidx.collection.MutableObjectList$$ExternalSyntheticOutline0;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RingBuffer extends AbstractList implements RandomAccess {
    public final Object[] buffer;
    public final int capacity;
    public int size;
    public int startIndex;

    public RingBuffer(int i, Object[] objArr) {
        this.buffer = objArr;
        if (i < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "ring buffer filled size should not be negative but it is ").toString());
        }
        if (i <= objArr.length) {
            this.capacity = objArr.length;
            this.size = i;
        } else {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("ring buffer filled size: ", " cannot be larger than the buffer size: ", i);
            m.append(objArr.length);
            throw new IllegalArgumentException(m.toString().toString());
        }
    }

    @Override // java.util.List
    public final Object get(int i) {
        int size = getSize();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, size, ", size: "));
        }
        return this.buffer[(this.startIndex + i) % this.capacity];
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.size;
    }

    @Override // kotlin.collections.AbstractList, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new AbstractIterator() { // from class: kotlin.collections.RingBuffer$iterator$1
            public int count;
            public int index;

            {
                this.count = RingBuffer.this.size;
                this.index = RingBuffer.this.startIndex;
            }

            @Override // kotlin.collections.AbstractIterator
            public final void computeNext() {
                if (this.count == 0) {
                    this.state = State.Done;
                    return;
                }
                setNext(RingBuffer.this.buffer[this.index]);
                this.index = (this.index + 1) % RingBuffer.this.capacity;
                this.count--;
            }
        };
    }

    public final void removeFirst(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "n shouldn't be negative but it is ").toString());
        }
        if (i > this.size) {
            StringBuilder m = MutableObjectList$$ExternalSyntheticOutline0.m("n shouldn't be greater than the buffer size: n = ", ", size = ", i);
            m.append(this.size);
            throw new IllegalArgumentException(m.toString().toString());
        }
        if (i > 0) {
            int i2 = this.startIndex;
            int i3 = this.capacity;
            int i4 = (i2 + i) % i3;
            if (i2 > i4) {
                Arrays.fill(this.buffer, i2, i3, (Object) null);
                Arrays.fill(this.buffer, 0, i4, (Object) null);
            } else {
                Arrays.fill(this.buffer, i2, i4, (Object) null);
            }
            this.startIndex = i4;
            this.size -= i;
        }
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        return toArray(new Object[getSize()]);
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int i = this.size;
        if (length < i) {
            objArr = Arrays.copyOf(objArr, i);
        }
        int i2 = this.size;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = this.startIndex; i4 < i2 && i5 < this.capacity; i5++) {
            objArr[i4] = this.buffer[i5];
            i4++;
        }
        while (i4 < i2) {
            objArr[i4] = this.buffer[i3];
            i4++;
            i3++;
        }
        if (i2 < objArr.length) {
            objArr[i2] = null;
        }
        return objArr;
    }
}
