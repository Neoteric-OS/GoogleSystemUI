package kotlinx.coroutines.internal;

import kotlinx.atomicfu.AtomicArray;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockFreeTaskQueueCore {
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    public final AtomicRef _next = AtomicFU.atomic((Object) null);
    public final AtomicLong _state = AtomicFU.atomic(0L);
    public final AtomicArray array;
    public final int capacity;
    public final int mask;
    public final boolean singleConsumer;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Placeholder {
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeTaskQueueCore(int i, boolean z) {
        this.capacity = i;
        this.singleConsumer = z;
        int i2 = i - 1;
        this.mask = i2;
        this.array = new AtomicArray(i);
        if (i2 > 1073741823) {
            throw new IllegalStateException("Check failed.");
        }
        if ((i & i2) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int addLast(Object obj) {
        AtomicLong atomicLong = this._state;
        while (true) {
            long j = atomicLong.value;
            if ((3458764513820540928L & j) != 0) {
                return (2305843009213693952L & j) != 0 ? 2 : 1;
            }
            int i = (int) (1073741823 & j);
            int i2 = (int) ((1152921503533105152L & j) >> 30);
            int i3 = this.mask;
            if (((i2 + 2) & i3) == (i & i3)) {
                return 1;
            }
            if (!this.singleConsumer) {
                if (this.array.array[i2 & i3].value != null) {
                    int i4 = this.capacity;
                    if (i4 < 1024 || ((i2 - i) & 1073741823) > (i4 >> 1)) {
                        break;
                    }
                }
            }
            AtomicLong atomicLong2 = this._state;
            long j2 = ((-1152921503533105153L) & j) | (((i2 + 1) & 1073741823) << 30);
            atomicLong2.getClass();
            if (AtomicLong.FU.compareAndSet(atomicLong2, j, j2)) {
                this.array.array[i2 & i3].value = obj;
                while ((this._state.value & 1152921504606846976L) != 0) {
                    this = this.next();
                    Object obj2 = this.array.array[this.mask & i2].value;
                    if ((obj2 instanceof Placeholder) && ((Placeholder) obj2).index == i2) {
                        this.array.array[this.mask & i2].value = obj;
                    } else {
                        this = null;
                    }
                    if (this == null) {
                        return 0;
                    }
                }
                return 0;
            }
        }
        return 1;
    }

    public final boolean close() {
        long j;
        AtomicLong atomicLong = this._state;
        do {
            j = atomicLong.value;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!AtomicLong.FU.compareAndSet(atomicLong, j, j | 2305843009213693952L));
        return true;
    }

    public final LockFreeTaskQueueCore next() {
        long j;
        AtomicLong atomicLong = this._state;
        while (true) {
            j = atomicLong.value;
            if ((j & 1152921504606846976L) != 0) {
                break;
            }
            long j2 = j | 1152921504606846976L;
            if (AtomicLong.FU.compareAndSet(atomicLong, j, j2)) {
                j = j2;
                break;
            }
        }
        AtomicRef atomicRef = this._next;
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.value;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            AtomicRef atomicRef2 = this._next;
            LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
            int i = (int) (1073741823 & j);
            int i2 = (int) ((1152921503533105152L & j) >> 30);
            while (true) {
                int i3 = this.mask;
                int i4 = i & i3;
                if (i4 != (i3 & i2)) {
                    Object obj = this.array.array[i4].value;
                    if (obj == null) {
                        obj = new Placeholder(i);
                    }
                    AtomicArray atomicArray = lockFreeTaskQueueCore2.array;
                    atomicArray.array[lockFreeTaskQueueCore2.mask & i].value = obj;
                    i++;
                }
            }
            lockFreeTaskQueueCore2._state.value = (-1152921504606846977L) & j;
            atomicRef2.compareAndSet(null, lockFreeTaskQueueCore2);
        }
    }

    public final Object removeFirstOrNull() {
        AtomicLong atomicLong = this._state;
        while (true) {
            long j = atomicLong.value;
            if ((j & 1152921504606846976L) != 0) {
                return REMOVE_FROZEN;
            }
            int i = (int) (j & 1073741823);
            int i2 = this.mask;
            int i3 = ((int) ((1152921503533105152L & j) >> 30)) & i2;
            int i4 = i2 & i;
            if (i3 == i4) {
                return null;
            }
            Object obj = this.array.array[i4].value;
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else {
                if (obj instanceof Placeholder) {
                    return null;
                }
                AtomicLong atomicLong2 = this._state;
                long j2 = (i + 1) & 1073741823;
                atomicLong2.getClass();
                if (AtomicLong.FU.compareAndSet(atomicLong2, j, (j & (-1073741824)) | j2)) {
                    this.array.array[this.mask & i].value = null;
                    return obj;
                }
                if (this.singleConsumer) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
                    do {
                        AtomicLong atomicLong3 = lockFreeTaskQueueCore._state;
                        while (true) {
                            long j3 = atomicLong3.value;
                            int i5 = (int) (j3 & 1073741823);
                            if ((j3 & 1152921504606846976L) != 0) {
                                lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                                break;
                            }
                            AtomicLong atomicLong4 = lockFreeTaskQueueCore._state;
                            atomicLong4.getClass();
                            if (AtomicLong.FU.compareAndSet(atomicLong4, j3, (j3 & (-1073741824)) | j2)) {
                                lockFreeTaskQueueCore.array.array[lockFreeTaskQueueCore.mask & i5].value = null;
                                lockFreeTaskQueueCore = null;
                                break;
                            }
                        }
                    } while (lockFreeTaskQueueCore != null);
                    return obj;
                }
            }
        }
    }
}
