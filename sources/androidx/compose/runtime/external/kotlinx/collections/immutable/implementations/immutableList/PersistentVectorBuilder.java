package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.collections.AbstractMutableList;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.ArrayIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PersistentVectorBuilder extends AbstractMutableList implements List, Collection, KMutableCollection {
    public MutabilityOwnership ownership = new MutabilityOwnership();
    public Object[] root;
    public int rootShift;
    public int size;
    public Object[] tail;
    public PersistentList vector;
    public Object[] vectorRoot;
    public Object[] vectorTail;

    public PersistentVectorBuilder(AbstractPersistentList abstractPersistentList, Object[] objArr, Object[] objArr2, int i) {
        this.vector = abstractPersistentList;
        this.vectorRoot = objArr;
        this.vectorTail = objArr2;
        this.rootShift = i;
        this.root = objArr;
        this.tail = objArr2;
        this.size = abstractPersistentList.size();
    }

    public static void copyToBuffer(Object[] objArr, int i, Iterator it) {
        while (i < 32 && it.hasNext()) {
            objArr[i] = it.next();
            i++;
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        ListImplementation.checkPositionIndex$runtime_release(i, getSize());
        if (i == getSize()) {
            add(obj);
            return;
        }
        ((AbstractList) this).modCount++;
        int rootSize$1 = rootSize$1();
        if (i >= rootSize$1) {
            insertIntoTail(i - rootSize$1, obj, this.root);
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        insertIntoTail(0, objectRef.value, insertIntoRoot$1(objArr, this.rootShift, i, obj, objectRef));
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        Object[] mutableBuffer;
        ListImplementation.checkPositionIndex$runtime_release(i, this.size);
        if (i == this.size) {
            return addAll(collection);
        }
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int i2 = (i >> 5) << 5;
        int size = ((collection.size() + (this.size - i2)) - 1) / 32;
        if (size == 0) {
            int i3 = i & 31;
            int size2 = ((collection.size() + i) - 1) & 31;
            Object[] objArr = this.tail;
            Object[] makeMutable = makeMutable(objArr);
            System.arraycopy(objArr, i3, makeMutable, size2 + 1, tailSize() - i3);
            copyToBuffer(makeMutable, i3, collection.iterator());
            this.tail = makeMutable;
            this.size = collection.size() + this.size;
            return true;
        }
        Object[][] objArr2 = new Object[size][];
        int tailSize = tailSize();
        int size3 = collection.size() + this.size;
        if (size3 > 32) {
            size3 -= (size3 - 1) & (-32);
        }
        if (i >= rootSize$1()) {
            mutableBuffer = mutableBuffer();
            splitToBuffers(collection, i, this.tail, tailSize, objArr2, size, mutableBuffer);
        } else if (size3 > tailSize) {
            int i4 = size3 - tailSize;
            mutableBuffer = makeMutableShiftingRight(i4, this.tail);
            insertIntoRoot(collection, i, i4, objArr2, size, mutableBuffer);
        } else {
            Object[] objArr3 = this.tail;
            mutableBuffer = mutableBuffer();
            int i5 = tailSize - size3;
            System.arraycopy(objArr3, i5, mutableBuffer, 0, tailSize - i5);
            int i6 = 32 - i5;
            Object[] makeMutableShiftingRight = makeMutableShiftingRight(i6, this.tail);
            int i7 = size - 1;
            objArr2[i7] = makeMutableShiftingRight;
            insertIntoRoot(collection, i, i6, objArr2, i7, makeMutableShiftingRight);
        }
        this.root = pushBuffersIncreasingHeightIfNeeded(this.root, i2, objArr2);
        this.tail = mutableBuffer;
        this.size = collection.size() + this.size;
        return true;
    }

    public final PersistentList build() {
        PersistentList persistentVector;
        Object[] objArr = this.root;
        if (objArr == this.vectorRoot && this.tail == this.vectorTail) {
            persistentVector = this.vector;
        } else {
            this.ownership = new MutabilityOwnership();
            this.vectorRoot = objArr;
            Object[] objArr2 = this.tail;
            this.vectorTail = objArr2;
            if (objArr == null) {
                persistentVector = objArr2.length == 0 ? SmallPersistentVector.EMPTY : new SmallPersistentVector(Arrays.copyOf(this.tail, getSize()));
            } else {
                Intrinsics.checkNotNull(objArr);
                persistentVector = new PersistentVector(objArr, this.tail, getSize(), this.rootShift);
            }
        }
        this.vector = persistentVector;
        return persistentVector;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        Object[] objArr;
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        if (rootSize$1() <= i) {
            objArr = this.tail;
        } else {
            Object[] objArr2 = this.root;
            Intrinsics.checkNotNull(objArr2);
            for (int i2 = this.rootShift; i2 > 0; i2 -= 5) {
                objArr2 = objArr2[UtilsKt.indexSegment(i, i2)];
            }
            objArr = objArr2;
        }
        return objArr[i & 31];
    }

    public final int getModCount$runtime_release() {
        return ((AbstractList) this).modCount;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        return this.size;
    }

    public final void insertIntoRoot(Collection collection, int i, int i2, Object[][] objArr, int i3, Object[] objArr2) {
        if (this.root == null) {
            throw new IllegalStateException("root is null");
        }
        int i4 = i >> 5;
        AbstractListIterator leafBufferIterator = leafBufferIterator(rootSize$1() >> 5);
        int i5 = i3;
        Object[] objArr3 = objArr2;
        while (leafBufferIterator.previousIndex() != i4) {
            Object[] objArr4 = (Object[]) leafBufferIterator.previous();
            ArraysKt.copyInto(0, 32 - i2, 32, objArr4, objArr3);
            objArr3 = makeMutableShiftingRight(i2, objArr4);
            i5--;
            objArr[i5] = objArr3;
        }
        Object[] objArr5 = (Object[]) leafBufferIterator.previous();
        int rootSize$1 = i3 - (((rootSize$1() >> 5) - 1) - i4);
        if (rootSize$1 < i3) {
            objArr2 = objArr[rootSize$1];
            Intrinsics.checkNotNull(objArr2);
        }
        splitToBuffers(collection, i, objArr5, 32, objArr, rootSize$1, objArr2);
    }

    public final Object[] insertIntoRoot$1(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        Object obj2;
        int indexSegment = UtilsKt.indexSegment(i2, i);
        if (i == 0) {
            objectRef.value = objArr[31];
            Object[] makeMutable = makeMutable(objArr);
            System.arraycopy(objArr, indexSegment, makeMutable, indexSegment + 1, 31 - indexSegment);
            makeMutable[indexSegment] = obj;
            return makeMutable;
        }
        Object[] makeMutable2 = makeMutable(objArr);
        int i3 = i - 5;
        makeMutable2[indexSegment] = insertIntoRoot$1((Object[]) makeMutable2[indexSegment], i3, i2, obj, objectRef);
        while (true) {
            indexSegment++;
            if (indexSegment >= 32 || (obj2 = makeMutable2[indexSegment]) == null) {
                break;
            }
            makeMutable2[indexSegment] = insertIntoRoot$1((Object[]) obj2, i3, 0, objectRef.value, objectRef);
        }
        return makeMutable2;
    }

    public final void insertIntoTail(int i, Object obj, Object[] objArr) {
        int tailSize = tailSize();
        Object[] makeMutable = makeMutable(this.tail);
        if (tailSize >= 32) {
            Object[] objArr2 = this.tail;
            Object obj2 = objArr2[31];
            ArraysKt.copyInto(i + 1, i, 31, objArr2, makeMutable);
            makeMutable[i] = obj;
            pushFilledTail(objArr, makeMutable, mutableBufferWith(obj2));
            return;
        }
        ArraysKt.copyInto(i + 1, i, tailSize, this.tail, makeMutable);
        makeMutable[i] = obj;
        this.root = objArr;
        this.tail = makeMutable;
        this.size++;
    }

    public final boolean isMutable(Object[] objArr) {
        return objArr.length == 33 && objArr[32] == this.ownership;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    public final AbstractListIterator leafBufferIterator(int i) {
        Object[] objArr = this.root;
        if (objArr == null) {
            throw new IllegalStateException("Invalid root");
        }
        int rootSize$1 = rootSize$1() >> 5;
        ListImplementation.checkPositionIndex$runtime_release(i, rootSize$1);
        int i2 = this.rootShift;
        return i2 == 0 ? new SingleElementListIterator(i, objArr) : new TrieIterator(objArr, i, rootSize$1, i2 / 5);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        ListImplementation.checkPositionIndex$runtime_release(i, this.size);
        return new PersistentVectorMutableIterator(this, i);
    }

    public final Object[] makeMutable(Object[] objArr) {
        if (objArr == null) {
            return mutableBuffer();
        }
        if (isMutable(objArr)) {
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        int length = objArr.length;
        if (length > 32) {
            length = 32;
        }
        ArraysKt.copyInto$default(0, length, 6, objArr, mutableBuffer);
        return mutableBuffer;
    }

    public final Object[] makeMutableShiftingRight(int i, Object[] objArr) {
        if (isMutable(objArr)) {
            System.arraycopy(objArr, 0, objArr, i, 32 - i);
            return objArr;
        }
        Object[] mutableBuffer = mutableBuffer();
        System.arraycopy(objArr, 0, mutableBuffer, i, 32 - i);
        return mutableBuffer;
    }

    public final Object[] mutableBuffer() {
        Object[] objArr = new Object[33];
        objArr[32] = this.ownership;
        return objArr;
    }

    public final Object[] mutableBufferWith(Object obj) {
        Object[] objArr = new Object[33];
        objArr[0] = obj;
        objArr[32] = this.ownership;
        return objArr;
    }

    public final Object[] nullifyAfter(Object[] objArr, int i, int i2) {
        if (!(i2 >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("shift should be positive");
        }
        if (i2 == 0) {
            return objArr;
        }
        int indexSegment = UtilsKt.indexSegment(i, i2);
        Object nullifyAfter = nullifyAfter((Object[]) objArr[indexSegment], i, i2 - 5);
        if (indexSegment < 31) {
            int i3 = indexSegment + 1;
            if (objArr[i3] != null) {
                if (isMutable(objArr)) {
                    Arrays.fill(objArr, i3, 32, (Object) null);
                }
                Object[] mutableBuffer = mutableBuffer();
                System.arraycopy(objArr, 0, mutableBuffer, 0, i3);
                objArr = mutableBuffer;
            }
        }
        if (nullifyAfter == objArr[indexSegment]) {
            return objArr;
        }
        Object[] makeMutable = makeMutable(objArr);
        makeMutable[indexSegment] = nullifyAfter;
        return makeMutable;
    }

    public final Object[] pullLastBuffer$1(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        Object[] pullLastBuffer$1;
        int indexSegment = UtilsKt.indexSegment(i2 - 1, i);
        if (i == 5) {
            objectRef.value = objArr[indexSegment];
            pullLastBuffer$1 = null;
        } else {
            pullLastBuffer$1 = pullLastBuffer$1((Object[]) objArr[indexSegment], i - 5, i2, objectRef);
        }
        if (pullLastBuffer$1 == null && indexSegment == 0) {
            return null;
        }
        Object[] makeMutable = makeMutable(objArr);
        makeMutable[indexSegment] = pullLastBuffer$1;
        return makeMutable;
    }

    public final void pullLastBufferFromRoot(Object[] objArr, int i, int i2) {
        if (i2 == 0) {
            this.root = null;
            if (objArr == null) {
                objArr = new Object[0];
            }
            this.tail = objArr;
            this.size = i;
            this.rootShift = i2;
            return;
        }
        ObjectRef objectRef = new ObjectRef(null);
        Intrinsics.checkNotNull(objArr);
        Object[] pullLastBuffer$1 = pullLastBuffer$1(objArr, i2, i, objectRef);
        Intrinsics.checkNotNull(pullLastBuffer$1);
        this.tail = (Object[]) objectRef.value;
        this.size = i;
        if (pullLastBuffer$1[1] == null) {
            this.root = (Object[]) pullLastBuffer$1[0];
            this.rootShift = i2 - 5;
        } else {
            this.root = pullLastBuffer$1;
            this.rootShift = i2;
        }
    }

    public final Object[] pushBuffers(Object[] objArr, int i, int i2, Iterator it) {
        if (!it.hasNext()) {
            PreconditionsKt.throwIllegalArgumentException("invalid buffersIterator");
        }
        if (!(i2 >= 0)) {
            PreconditionsKt.throwIllegalArgumentException("negative shift");
        }
        if (i2 == 0) {
            return (Object[]) it.next();
        }
        Object[] makeMutable = makeMutable(objArr);
        int indexSegment = UtilsKt.indexSegment(i, i2);
        int i3 = i2 - 5;
        makeMutable[indexSegment] = pushBuffers((Object[]) makeMutable[indexSegment], i, i3, it);
        while (true) {
            indexSegment++;
            if (indexSegment >= 32 || !it.hasNext()) {
                break;
            }
            makeMutable[indexSegment] = pushBuffers((Object[]) makeMutable[indexSegment], 0, i3, it);
        }
        return makeMutable;
    }

    public final Object[] pushBuffersIncreasingHeightIfNeeded(Object[] objArr, int i, Object[][] objArr2) {
        ArrayIterator arrayIterator = new ArrayIterator(objArr2);
        int i2 = i >> 5;
        int i3 = this.rootShift;
        Object[] pushBuffers = i2 < (1 << i3) ? pushBuffers(objArr, i, i3, arrayIterator) : makeMutable(objArr);
        while (arrayIterator.hasNext()) {
            this.rootShift += 5;
            pushBuffers = mutableBufferWith(pushBuffers);
            int i4 = this.rootShift;
            pushBuffers(pushBuffers, 1 << i4, i4, arrayIterator);
        }
        return pushBuffers;
    }

    public final void pushFilledTail(Object[] objArr, Object[] objArr2, Object[] objArr3) {
        int i = this.size;
        int i2 = i >> 5;
        int i3 = this.rootShift;
        if (i2 > (1 << i3)) {
            this.root = pushTail(this.rootShift + 5, mutableBufferWith(objArr), objArr2);
            this.tail = objArr3;
            this.rootShift += 5;
            this.size++;
            return;
        }
        if (objArr == null) {
            this.root = objArr2;
            this.tail = objArr3;
            this.size = i + 1;
        } else {
            this.root = pushTail(i3, objArr, objArr2);
            this.tail = objArr3;
            this.size++;
        }
    }

    public final Object[] pushTail(int i, Object[] objArr, Object[] objArr2) {
        int indexSegment = UtilsKt.indexSegment(getSize() - 1, i);
        Object[] makeMutable = makeMutable(objArr);
        if (i == 5) {
            makeMutable[indexSegment] = objArr2;
        } else {
            makeMutable[indexSegment] = pushTail(i - 5, (Object[]) makeMutable[indexSegment], objArr2);
        }
        return makeMutable;
    }

    public final int recyclableRemoveAll(Function1 function1, Object[] objArr, int i, int i2, ObjectRef objectRef, List list, List list2) {
        if (isMutable(objArr)) {
            list.add(objArr);
        }
        Object[] objArr2 = (Object[]) objectRef.value;
        Object[] objArr3 = objArr2;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (!((Boolean) function1.invoke(obj)).booleanValue()) {
                if (i2 == 32) {
                    objArr3 = !list.isEmpty() ? (Object[]) list.remove(((ArrayList) list).size() - 1) : mutableBuffer();
                    i2 = 0;
                }
                objArr3[i2] = obj;
                i2++;
            }
        }
        objectRef.value = objArr3;
        if (objArr2 != objArr3) {
            list2.add(objArr2);
        }
        return i2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(final Collection collection) {
        return removeAllWithPredicate(new Function1() { // from class: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder$removeAll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(collection.contains(obj));
            }
        });
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0075, code lost:
    
        if (r0 != r10) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x002d, code lost:
    
        if (r0 != r10) goto L44;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean removeAllWithPredicate(kotlin.jvm.functions.Function1 r19) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder.removeAllWithPredicate(kotlin.jvm.functions.Function1):boolean");
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        ((AbstractList) this).modCount++;
        int rootSize$1 = rootSize$1();
        if (i >= rootSize$1) {
            return removeFromTailAt(this.root, rootSize$1, this.rootShift, i - rootSize$1);
        }
        ObjectRef objectRef = new ObjectRef(this.tail[0]);
        Object[] objArr = this.root;
        Intrinsics.checkNotNull(objArr);
        removeFromTailAt(removeFromRootAt$1(objArr, this.rootShift, i, objectRef), rootSize$1, this.rootShift, 0);
        return objectRef.value;
    }

    public final Object[] removeFromRootAt$1(Object[] objArr, int i, int i2, ObjectRef objectRef) {
        int indexSegment = UtilsKt.indexSegment(i2, i);
        if (i == 0) {
            Object obj = objArr[indexSegment];
            Object[] makeMutable = makeMutable(objArr);
            int i3 = indexSegment + 1;
            System.arraycopy(objArr, i3, makeMutable, indexSegment, 32 - i3);
            makeMutable[31] = objectRef.value;
            objectRef.value = obj;
            return makeMutable;
        }
        int indexSegment2 = objArr[31] == null ? UtilsKt.indexSegment(rootSize$1() - 1, i) : 31;
        Object[] makeMutable2 = makeMutable(objArr);
        int i4 = i - 5;
        int i5 = indexSegment + 1;
        if (i5 <= indexSegment2) {
            while (true) {
                makeMutable2[indexSegment2] = removeFromRootAt$1((Object[]) makeMutable2[indexSegment2], i4, 0, objectRef);
                if (indexSegment2 == i5) {
                    break;
                }
                indexSegment2--;
            }
        }
        makeMutable2[indexSegment] = removeFromRootAt$1((Object[]) makeMutable2[indexSegment], i4, i2, objectRef);
        return makeMutable2;
    }

    public final Object removeFromTailAt(Object[] objArr, int i, int i2, int i3) {
        int size = getSize() - i;
        if (size == 1) {
            Object obj = this.tail[0];
            pullLastBufferFromRoot(objArr, i, i2);
            return obj;
        }
        Object[] objArr2 = this.tail;
        Object obj2 = objArr2[i3];
        Object[] makeMutable = makeMutable(objArr2);
        int i4 = i3 + 1;
        System.arraycopy(objArr2, i4, makeMutable, i3, size - i4);
        makeMutable[size - 1] = null;
        this.root = objArr;
        this.tail = makeMutable;
        this.size = (i + size) - 1;
        this.rootShift = i2;
        return obj2;
    }

    public final int rootSize$1() {
        int i = this.size;
        if (i <= 32) {
            return 0;
        }
        return (i - 1) & (-32);
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        ListImplementation.checkElementIndex$runtime_release(i, getSize());
        if (rootSize$1() > i) {
            ObjectRef objectRef = new ObjectRef(null);
            Object[] objArr = this.root;
            Intrinsics.checkNotNull(objArr);
            this.root = setInRoot(objArr, this.rootShift, i, obj, objectRef);
            return objectRef.value;
        }
        Object[] makeMutable = makeMutable(this.tail);
        if (makeMutable != this.tail) {
            ((AbstractList) this).modCount++;
        }
        int i2 = i & 31;
        Object obj2 = makeMutable[i2];
        makeMutable[i2] = obj;
        this.tail = makeMutable;
        return obj2;
    }

    public final Object[] setInRoot(Object[] objArr, int i, int i2, Object obj, ObjectRef objectRef) {
        int indexSegment = UtilsKt.indexSegment(i2, i);
        Object[] makeMutable = makeMutable(objArr);
        if (i != 0) {
            makeMutable[indexSegment] = setInRoot((Object[]) makeMutable[indexSegment], i - 5, i2, obj, objectRef);
            return makeMutable;
        }
        if (makeMutable != objArr) {
            ((AbstractList) this).modCount++;
        }
        objectRef.value = makeMutable[indexSegment];
        makeMutable[indexSegment] = obj;
        return makeMutable;
    }

    public final void splitToBuffers(Collection collection, int i, Object[] objArr, int i2, Object[][] objArr2, int i3, Object[] objArr3) {
        Object[] mutableBuffer;
        if (i3 < 1) {
            PreconditionsKt.throwIllegalArgumentException("requires at least one nullBuffer");
        }
        Object[] makeMutable = makeMutable(objArr);
        objArr2[0] = makeMutable;
        int i4 = i & 31;
        int size = ((collection.size() + i) - 1) & 31;
        int i5 = (i2 - i4) + size;
        if (i5 < 32) {
            ArraysKt.copyInto(size + 1, i4, i2, makeMutable, objArr3);
        } else {
            int i6 = i5 - 31;
            if (i3 == 1) {
                mutableBuffer = makeMutable;
            } else {
                mutableBuffer = mutableBuffer();
                i3--;
                objArr2[i3] = mutableBuffer;
            }
            int i7 = i2 - i6;
            ArraysKt.copyInto(0, i7, i2, makeMutable, objArr3);
            ArraysKt.copyInto(size + 1, i4, i7, makeMutable, mutableBuffer);
            objArr3 = mutableBuffer;
        }
        Iterator it = collection.iterator();
        copyToBuffer(makeMutable, i4, it);
        for (int i8 = 1; i8 < i3; i8++) {
            Object[] mutableBuffer2 = mutableBuffer();
            copyToBuffer(mutableBuffer2, 0, it);
            objArr2[i8] = mutableBuffer2;
        }
        copyToBuffer(objArr3, 0, it);
    }

    public final int tailSize() {
        int i = this.size;
        return i <= 32 ? i : i - ((i - 1) & (-32));
    }

    public final int removeAll(Function1 function1, Object[] objArr, int i, ObjectRef objectRef) {
        Object[] objArr2 = objArr;
        int i2 = i;
        boolean z = false;
        for (int i3 = 0; i3 < i; i3++) {
            Object obj = objArr[i3];
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                if (!z) {
                    objArr2 = makeMutable(objArr);
                    z = true;
                    i2 = i3;
                }
            } else if (z) {
                objArr2[i2] = obj;
                i2++;
            }
        }
        objectRef.value = objArr2;
        return i2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        if (tailSize < 32) {
            Object[] makeMutable = makeMutable(this.tail);
            makeMutable[tailSize] = obj;
            this.tail = makeMutable;
            this.size = getSize() + 1;
        } else {
            pushFilledTail(this.root, this.tail, mutableBufferWith(obj));
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount++;
        int tailSize = tailSize();
        Iterator it = collection.iterator();
        if (32 - tailSize >= collection.size()) {
            Object[] makeMutable = makeMutable(this.tail);
            copyToBuffer(makeMutable, tailSize, it);
            this.tail = makeMutable;
            this.size = collection.size() + this.size;
        } else {
            int size = ((collection.size() + tailSize) - 1) / 32;
            Object[][] objArr = new Object[size][];
            Object[] makeMutable2 = makeMutable(this.tail);
            copyToBuffer(makeMutable2, tailSize, it);
            objArr[0] = makeMutable2;
            for (int i = 1; i < size; i++) {
                Object[] mutableBuffer = mutableBuffer();
                copyToBuffer(mutableBuffer, 0, it);
                objArr[i] = mutableBuffer;
            }
            this.root = pushBuffersIncreasingHeightIfNeeded(this.root, rootSize$1(), objArr);
            Object[] mutableBuffer2 = mutableBuffer();
            copyToBuffer(mutableBuffer2, 0, it);
            this.tail = mutableBuffer2;
            this.size = collection.size() + this.size;
        }
        return true;
    }
}
