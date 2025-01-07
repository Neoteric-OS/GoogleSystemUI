package kotlin.collections;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation$$ExternalSyntheticOutline0;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ArrayDeque extends AbstractMutableList {
    public static final Object[] emptyElementData = new Object[0];
    public Object[] elementData = emptyElementData;
    public int head;
    public int size;

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2 = this.size;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        if (i == i2) {
            addLast(obj);
            return;
        }
        if (i == 0) {
            addFirst(obj);
            return;
        }
        ensureCapacity$1(i2 + 1);
        int positiveMod = positiveMod(this.head + i);
        int i3 = this.size;
        if (i < ((i3 + 1) >> 1)) {
            int length = positiveMod == 0 ? this.elementData.length - 1 : positiveMod - 1;
            int i4 = this.head;
            int length2 = i4 == 0 ? this.elementData.length - 1 : i4 - 1;
            if (length >= i4) {
                Object[] objArr = this.elementData;
                objArr[length2] = objArr[i4];
                ArraysKt.copyInto(i4, i4 + 1, length + 1, objArr, objArr);
            } else {
                Object[] objArr2 = this.elementData;
                ArraysKt.copyInto(i4 - 1, i4, objArr2.length, objArr2, objArr2);
                Object[] objArr3 = this.elementData;
                objArr3[objArr3.length - 1] = objArr3[0];
                ArraysKt.copyInto(0, 1, length + 1, objArr3, objArr3);
            }
            this.elementData[length] = obj;
            this.head = length2;
        } else {
            int positiveMod2 = positiveMod(i3 + this.head);
            if (positiveMod < positiveMod2) {
                Object[] objArr4 = this.elementData;
                ArraysKt.copyInto(positiveMod + 1, positiveMod, positiveMod2, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.elementData;
                ArraysKt.copyInto(1, 0, positiveMod2, objArr5, objArr5);
                Object[] objArr6 = this.elementData;
                objArr6[0] = objArr6[objArr6.length - 1];
                ArraysKt.copyInto(positiveMod + 1, positiveMod, objArr6.length - 1, objArr6, objArr6);
            }
            this.elementData[positiveMod] = obj;
        }
        this.size++;
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        int i2 = this.size;
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        if (collection.isEmpty()) {
            return false;
        }
        int i3 = this.size;
        if (i == i3) {
            return addAll(collection);
        }
        ensureCapacity$1(collection.size() + i3);
        int positiveMod = positiveMod(this.size + this.head);
        int positiveMod2 = positiveMod(this.head + i);
        int size = collection.size();
        if (i < ((this.size + 1) >> 1)) {
            int i4 = this.head;
            int i5 = i4 - size;
            if (positiveMod2 < i4) {
                Object[] objArr = this.elementData;
                ArraysKt.copyInto(i5, i4, objArr.length, objArr, objArr);
                if (size >= positiveMod2) {
                    Object[] objArr2 = this.elementData;
                    ArraysKt.copyInto(objArr2.length - size, 0, positiveMod2, objArr2, objArr2);
                } else {
                    Object[] objArr3 = this.elementData;
                    ArraysKt.copyInto(objArr3.length - size, 0, size, objArr3, objArr3);
                    Object[] objArr4 = this.elementData;
                    ArraysKt.copyInto(0, size, positiveMod2, objArr4, objArr4);
                }
            } else if (i5 >= 0) {
                Object[] objArr5 = this.elementData;
                ArraysKt.copyInto(i5, i4, positiveMod2, objArr5, objArr5);
            } else {
                Object[] objArr6 = this.elementData;
                i5 += objArr6.length;
                int i6 = positiveMod2 - i4;
                int length = objArr6.length - i5;
                if (length >= i6) {
                    ArraysKt.copyInto(i5, i4, positiveMod2, objArr6, objArr6);
                } else {
                    ArraysKt.copyInto(i5, i4, i4 + length, objArr6, objArr6);
                    Object[] objArr7 = this.elementData;
                    ArraysKt.copyInto(0, this.head + length, positiveMod2, objArr7, objArr7);
                }
            }
            this.head = i5;
            int i7 = positiveMod2 - size;
            if (i7 < 0) {
                i7 += this.elementData.length;
            }
            copyCollectionElements(i7, collection);
        } else {
            int i8 = positiveMod2 + size;
            if (positiveMod2 < positiveMod) {
                int i9 = size + positiveMod;
                Object[] objArr8 = this.elementData;
                if (i9 <= objArr8.length) {
                    ArraysKt.copyInto(i8, positiveMod2, positiveMod, objArr8, objArr8);
                } else if (i8 >= objArr8.length) {
                    ArraysKt.copyInto(i8 - objArr8.length, positiveMod2, positiveMod, objArr8, objArr8);
                } else {
                    int length2 = positiveMod - (i9 - objArr8.length);
                    ArraysKt.copyInto(0, length2, positiveMod, objArr8, objArr8);
                    Object[] objArr9 = this.elementData;
                    ArraysKt.copyInto(i8, positiveMod2, length2, objArr9, objArr9);
                }
            } else {
                Object[] objArr10 = this.elementData;
                ArraysKt.copyInto(size, 0, positiveMod, objArr10, objArr10);
                Object[] objArr11 = this.elementData;
                if (i8 >= objArr11.length) {
                    ArraysKt.copyInto(i8 - objArr11.length, positiveMod2, objArr11.length, objArr11, objArr11);
                } else {
                    ArraysKt.copyInto(0, objArr11.length - size, objArr11.length, objArr11, objArr11);
                    Object[] objArr12 = this.elementData;
                    ArraysKt.copyInto(i8, positiveMod2, objArr12.length - size, objArr12, objArr12);
                }
            }
            copyCollectionElements(positiveMod2, collection);
        }
        return true;
    }

    public final void addFirst(Object obj) {
        ensureCapacity$1(this.size + 1);
        int i = this.head;
        if (i == 0) {
            i = this.elementData.length;
        }
        int i2 = i - 1;
        this.head = i2;
        this.elementData[i2] = obj;
        this.size++;
    }

    public final void addLast(Object obj) {
        ensureCapacity$1(getSize() + 1);
        this.elementData[positiveMod(getSize() + this.head)] = obj;
        this.size = getSize() + 1;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        int positiveMod = positiveMod(getSize() + this.head);
        int i = this.head;
        if (i < positiveMod) {
            Arrays.fill(this.elementData, i, positiveMod, (Object) null);
        } else if (!isEmpty()) {
            Object[] objArr = this.elementData;
            Arrays.fill(objArr, this.head, objArr.length, (Object) null);
            Arrays.fill(this.elementData, 0, positiveMod, (Object) null);
        }
        this.head = 0;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    public final void copyCollectionElements(int i, Collection collection) {
        Iterator it = collection.iterator();
        int length = this.elementData.length;
        while (i < length && it.hasNext()) {
            this.elementData[i] = it.next();
            i++;
        }
        int i2 = this.head;
        for (int i3 = 0; i3 < i2 && it.hasNext(); i3++) {
            this.elementData[i3] = it.next();
        }
        this.size = collection.size() + this.size;
    }

    public final void ensureCapacity$1(int i) {
        if (i < 0) {
            throw new IllegalStateException("Deque is too big.");
        }
        Object[] objArr = this.elementData;
        if (i <= objArr.length) {
            return;
        }
        if (objArr == emptyElementData) {
            if (i < 10) {
                i = 10;
            }
            this.elementData = new Object[i];
            return;
        }
        int length = objArr.length;
        int i2 = length + (length >> 1);
        if (i2 - i < 0) {
            i2 = i;
        }
        if (i2 - 2147483639 > 0) {
            i2 = i > 2147483639 ? Integer.MAX_VALUE : 2147483639;
        }
        Object[] objArr2 = new Object[i2];
        ArraysKt.copyInto(0, this.head, objArr.length, objArr, objArr2);
        Object[] objArr3 = this.elementData;
        int length2 = objArr3.length;
        int i3 = this.head;
        ArraysKt.copyInto(length2 - i3, 0, i3, objArr3, objArr2);
        this.head = 0;
        this.elementData = objArr2;
    }

    public final Object first() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.elementData[this.head];
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        int size = getSize();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, size, ", size: "));
        }
        return this.elementData[positiveMod(this.head + i)];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    public final int incremented(int i) {
        if (i == this.elementData.length - 1) {
            return 0;
        }
        return i + 1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        int i;
        int positiveMod = positiveMod(getSize() + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            while (i2 < positiveMod) {
                if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                    i = this.head;
                } else {
                    i2++;
                }
            }
            return -1;
        }
        if (i2 < positiveMod) {
            return -1;
        }
        int length = this.elementData.length;
        while (true) {
            if (i2 >= length) {
                for (int i3 = 0; i3 < positiveMod; i3++) {
                    if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                        i2 = i3 + this.elementData.length;
                        i = this.head;
                    }
                }
                return -1;
            }
            if (Intrinsics.areEqual(obj, this.elementData[i2])) {
                i = this.head;
                break;
            }
            i2++;
        }
        return i2 - i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return getSize() == 0;
    }

    public final Object last() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        return this.elementData[positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head)];
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        int length;
        int i;
        int positiveMod = positiveMod(getSize() + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            length = positiveMod - 1;
            if (i2 <= length) {
                while (!Intrinsics.areEqual(obj, this.elementData[length])) {
                    if (length != i2) {
                        length--;
                    }
                }
                i = this.head;
                return length - i;
            }
            return -1;
        }
        if (i2 > positiveMod) {
            int i3 = positiveMod - 1;
            while (true) {
                if (-1 >= i3) {
                    length = this.elementData.length - 1;
                    int i4 = this.head;
                    if (i4 <= length) {
                        while (!Intrinsics.areEqual(obj, this.elementData[length])) {
                            if (length != i4) {
                                length--;
                            }
                        }
                        i = this.head;
                    }
                } else {
                    if (Intrinsics.areEqual(obj, this.elementData[i3])) {
                        length = i3 + this.elementData.length;
                        i = this.head;
                        break;
                    }
                    i3--;
                }
            }
        }
        return -1;
    }

    public final int positiveMod(int i) {
        Object[] objArr = this.elementData;
        return i >= objArr.length ? i - objArr.length : i;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return false;
        }
        removeAt(indexOf);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        int positiveMod;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.size + this.head);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        z = true;
                    } else {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    }
                    i++;
                }
                Arrays.fill(this.elementData, positiveMod, positiveMod2, (Object) null);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        z2 = true;
                    } else {
                        this.elementData[i2] = obj2;
                        i2++;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        z2 = true;
                    } else {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    }
                }
                z = z2;
            }
            if (z) {
                int i4 = positiveMod - this.head;
                if (i4 < 0) {
                    i4 += this.elementData.length;
                }
                this.size = i4;
            }
        }
        return z;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        int i2 = this.size;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, i2, ", size: "));
        }
        if (i == CollectionsKt__CollectionsKt.getLastIndex(this)) {
            return removeLast();
        }
        if (i == 0) {
            return removeFirst();
        }
        int positiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        Object obj = objArr[positiveMod];
        if (i < (this.size >> 1)) {
            int i3 = this.head;
            if (positiveMod >= i3) {
                ArraysKt.copyInto(i3 + 1, i3, positiveMod, objArr, objArr);
            } else {
                ArraysKt.copyInto(1, 0, positiveMod, objArr, objArr);
                Object[] objArr2 = this.elementData;
                objArr2[0] = objArr2[objArr2.length - 1];
                int i4 = this.head;
                ArraysKt.copyInto(i4 + 1, i4, objArr2.length - 1, objArr2, objArr2);
            }
            Object[] objArr3 = this.elementData;
            int i5 = this.head;
            objArr3[i5] = null;
            this.head = incremented(i5);
        } else {
            int positiveMod2 = positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head);
            if (positiveMod <= positiveMod2) {
                Object[] objArr4 = this.elementData;
                ArraysKt.copyInto(positiveMod, positiveMod + 1, positiveMod2 + 1, objArr4, objArr4);
            } else {
                Object[] objArr5 = this.elementData;
                ArraysKt.copyInto(positiveMod, positiveMod + 1, objArr5.length, objArr5, objArr5);
                Object[] objArr6 = this.elementData;
                objArr6[objArr6.length - 1] = objArr6[0];
                ArraysKt.copyInto(0, 1, positiveMod2 + 1, objArr6, objArr6);
            }
            this.elementData[positiveMod2] = null;
        }
        this.size--;
        return obj;
    }

    public final Object removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        Object[] objArr = this.elementData;
        int i = this.head;
        Object obj = objArr[i];
        objArr[i] = null;
        this.head = incremented(i);
        this.size = getSize() - 1;
        return obj;
    }

    public final Object removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("ArrayDeque is empty.");
        }
        int positiveMod = positiveMod(CollectionsKt__CollectionsKt.getLastIndex(this) + this.head);
        Object[] objArr = this.elementData;
        Object obj = objArr[positiveMod];
        objArr[positiveMod] = null;
        this.size = getSize() - 1;
        return obj;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        int positiveMod;
        boolean z = false;
        z = false;
        z = false;
        if (!isEmpty() && this.elementData.length != 0) {
            int positiveMod2 = positiveMod(this.size + this.head);
            int i = this.head;
            if (i < positiveMod2) {
                positiveMod = i;
                while (i < positiveMod2) {
                    Object obj = this.elementData[i];
                    if (collection.contains(obj)) {
                        this.elementData[positiveMod] = obj;
                        positiveMod++;
                    } else {
                        z = true;
                    }
                    i++;
                }
                Arrays.fill(this.elementData, positiveMod, positiveMod2, (Object) null);
            } else {
                int length = this.elementData.length;
                boolean z2 = false;
                int i2 = i;
                while (i < length) {
                    Object[] objArr = this.elementData;
                    Object obj2 = objArr[i];
                    objArr[i] = null;
                    if (collection.contains(obj2)) {
                        this.elementData[i2] = obj2;
                        i2++;
                    } else {
                        z2 = true;
                    }
                    i++;
                }
                positiveMod = positiveMod(i2);
                for (int i3 = 0; i3 < positiveMod2; i3++) {
                    Object[] objArr2 = this.elementData;
                    Object obj3 = objArr2[i3];
                    objArr2[i3] = null;
                    if (collection.contains(obj3)) {
                        this.elementData[positiveMod] = obj3;
                        positiveMod = incremented(positiveMod);
                    } else {
                        z2 = true;
                    }
                }
                z = z2;
            }
            if (z) {
                int i4 = positiveMod - this.head;
                if (i4 < 0) {
                    i4 += this.elementData.length;
                }
                this.size = i4;
            }
        }
        return z;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        int size = getSize();
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException(ListImplementation$$ExternalSyntheticOutline0.m("index: ", i, size, ", size: "));
        }
        int positiveMod = positiveMod(this.head + i);
        Object[] objArr = this.elementData;
        Object obj2 = objArr[positiveMod];
        objArr[positiveMod] = obj;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        return toArray(new Object[getSize()]);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        int length = objArr.length;
        int i = this.size;
        if (length < i) {
            objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), i);
        }
        int positiveMod = positiveMod(this.size + this.head);
        int i2 = this.head;
        if (i2 < positiveMod) {
            ArraysKt.copyInto$default(i2, positiveMod, 2, this.elementData, objArr);
        } else if (!isEmpty()) {
            Object[] objArr2 = this.elementData;
            ArraysKt.copyInto(0, this.head, objArr2.length, objArr2, objArr);
            Object[] objArr3 = this.elementData;
            ArraysKt.copyInto(objArr3.length - this.head, 0, positiveMod, objArr3, objArr);
        }
        int i3 = this.size;
        if (i3 < objArr.length) {
            objArr[i3] = null;
        }
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        addLast(obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ensureCapacity$1(collection.size() + getSize());
        copyCollectionElements(positiveMod(getSize() + this.head), collection);
        return true;
    }
}
