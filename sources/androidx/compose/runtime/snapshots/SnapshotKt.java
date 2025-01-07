package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.internal.AtomicInt;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SnapshotKt {
    public static List applyObservers;
    public static final AtomicReference currentGlobalSnapshot;
    public static final SnapshotWeakSet extraStateObjects;
    public static List globalWriteObservers;
    public static int nextSnapshotId;
    public static SnapshotIdSet openSnapshots;
    public static final AtomicInt pendingApplyObserverCount;
    public static final SnapshotDoubleIndexHeap pinningTable;
    public static final Snapshot snapshotInitializer;
    public static final Function1 emptyLambda = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$emptyLambda$1
        @Override // kotlin.jvm.functions.Function1
        public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return Unit.INSTANCE;
        }
    };
    public static final SnapshotThreadLocal threadSnapshot = new SnapshotThreadLocal();
    public static final Object lock = new Object();

    static {
        openSnapshots = SnapshotIdSet.EMPTY;
        nextSnapshotId = 2;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = new SnapshotDoubleIndexHeap();
        snapshotDoubleIndexHeap.values = new int[16];
        snapshotDoubleIndexHeap.index = new int[16];
        int[] iArr = new int[16];
        int i = 0;
        while (i < 16) {
            int i2 = i + 1;
            iArr[i] = i2;
            i = i2;
        }
        snapshotDoubleIndexHeap.handles = iArr;
        pinningTable = snapshotDoubleIndexHeap;
        SnapshotWeakSet snapshotWeakSet = new SnapshotWeakSet();
        snapshotWeakSet.hashes = new int[16];
        snapshotWeakSet.values = new WeakReference[16];
        extraStateObjects = snapshotWeakSet;
        EmptyList emptyList = EmptyList.INSTANCE;
        applyObservers = emptyList;
        globalWriteObservers = emptyList;
        int i3 = nextSnapshotId;
        nextSnapshotId = i3 + 1;
        GlobalSnapshot globalSnapshot = new GlobalSnapshot(i3, SnapshotIdSet.EMPTY);
        openSnapshots = openSnapshots.set(globalSnapshot.id);
        AtomicReference atomicReference = new AtomicReference(globalSnapshot);
        currentGlobalSnapshot = atomicReference;
        snapshotInitializer = (Snapshot) atomicReference.get();
        pendingApplyObserverCount = new AtomicInt(0);
    }

    public static final Function1 access$mergedWriteObserver(final Function1 function1, final Function1 function12) {
        return (function1 == null || function12 == null || function1 == function12) ? function1 == null ? function12 : function1 : new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$mergedWriteObserver$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function1.this.invoke(obj);
                function12.invoke(obj);
                return Unit.INSTANCE;
            }
        };
    }

    public static final Map access$optimisticMerges(MutableSnapshot mutableSnapshot, MutableSnapshot mutableSnapshot2, SnapshotIdSet snapshotIdSet) {
        long[] jArr;
        int i;
        SnapshotIdSet snapshotIdSet2;
        long[] jArr2;
        int i2;
        SnapshotIdSet snapshotIdSet3;
        MutableScatterSet modified$runtime_release = mutableSnapshot2.getModified$runtime_release();
        int id = mutableSnapshot.getId();
        if (modified$runtime_release != null) {
            SnapshotIdSet or = mutableSnapshot2.getInvalid$runtime_release().set(mutableSnapshot2.getId()).or(mutableSnapshot2.previousIds);
            Object[] objArr = modified$runtime_release.elements;
            long[] jArr3 = modified$runtime_release.metadata;
            int length = jArr3.length - 2;
            if (length < 0) {
                return null;
            }
            int i3 = 0;
            HashMap hashMap = null;
            loop0: while (true) {
                long j = jArr3[i3];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8;
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((255 & j) < 128) {
                            StateObject stateObject = (StateObject) objArr[(i3 << 3) + i6];
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            StateRecord readable = readable(firstStateRecord, id, snapshotIdSet);
                            if (readable == null) {
                                jArr2 = jArr3;
                            } else {
                                jArr2 = jArr3;
                                StateRecord readable2 = readable(firstStateRecord, id, or);
                                if (readable2 != null && !readable.equals(readable2)) {
                                    i2 = id;
                                    snapshotIdSet3 = or;
                                    StateRecord readable3 = readable(firstStateRecord, mutableSnapshot2.getId(), mutableSnapshot2.getInvalid$runtime_release());
                                    if (readable3 == null) {
                                        readError();
                                        throw null;
                                    }
                                    StateRecord mergeRecords = stateObject.mergeRecords(readable2, readable, readable3);
                                    if (mergeRecords == null) {
                                        break loop0;
                                    }
                                    if (hashMap == null) {
                                        hashMap = new HashMap();
                                    }
                                    hashMap.put(readable, mergeRecords);
                                    hashMap = hashMap;
                                }
                            }
                            i2 = id;
                            snapshotIdSet3 = or;
                        } else {
                            jArr2 = jArr3;
                            i2 = id;
                            snapshotIdSet3 = or;
                        }
                        j >>= 8;
                        i6++;
                        i4 = 8;
                        jArr3 = jArr2;
                        id = i2;
                        or = snapshotIdSet3;
                    }
                    jArr = jArr3;
                    i = id;
                    snapshotIdSet2 = or;
                    if (i5 != i4) {
                        break;
                    }
                } else {
                    jArr = jArr3;
                    i = id;
                    snapshotIdSet2 = or;
                }
                if (i3 == length) {
                    break;
                }
                i3++;
                jArr3 = jArr;
                id = i;
                or = snapshotIdSet2;
            }
            return hashMap;
        }
        return null;
    }

    public static final void access$validateOpen(Snapshot snapshot) {
        int i;
        if (openSnapshots.get(snapshot.getId())) {
            return;
        }
        StringBuilder sb = new StringBuilder("Snapshot is not open: id=");
        sb.append(snapshot.getId());
        sb.append(", disposed=");
        sb.append(snapshot.disposed);
        sb.append(", applied=");
        MutableSnapshot mutableSnapshot = snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null;
        sb.append(mutableSnapshot != null ? Boolean.valueOf(mutableSnapshot.applied) : "read-only");
        sb.append(", lowestPin=");
        synchronized (lock) {
            SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
            i = snapshotDoubleIndexHeap.size > 0 ? snapshotDoubleIndexHeap.values[0] : -1;
        }
        sb.append(i);
        throw new IllegalStateException(sb.toString().toString());
    }

    public static final SnapshotIdSet addRange(SnapshotIdSet snapshotIdSet, int i, int i2) {
        while (i < i2) {
            snapshotIdSet = snapshotIdSet.set(i);
            i++;
        }
        return snapshotIdSet;
    }

    public static final Object advanceGlobalSnapshot(Function1 function1) {
        Object obj;
        MutableScatterSet mutableScatterSet;
        Object takeNewGlobalSnapshot;
        synchronized (lock) {
            try {
                obj = currentGlobalSnapshot.get();
                mutableScatterSet = ((GlobalSnapshot) obj).modified;
                if (mutableScatterSet != null) {
                    pendingApplyObserverCount.addAndGet(1);
                }
                takeNewGlobalSnapshot = takeNewGlobalSnapshot((Snapshot) obj, function1);
            } catch (Throwable th) {
                throw th;
            }
        }
        if (mutableScatterSet != null) {
            try {
                List list = applyObservers;
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ((Function2) list.get(i)).invoke(new ScatterSetWrapper(mutableScatterSet), obj);
                }
            } finally {
                pendingApplyObserverCount.addAndGet(-1);
            }
        }
        synchronized (lock) {
            checkAndOverwriteUnusedRecordsLocked();
            if (mutableScatterSet != null) {
                Object[] objArr = mutableScatterSet.elements;
                long[] jArr = mutableScatterSet.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i2 = 0;
                    while (true) {
                        long j = jArr[i2];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i3 = 8 - ((~(i2 - length)) >>> 31);
                            for (int i4 = 0; i4 < i3; i4++) {
                                if ((255 & j) < 128) {
                                    processForUnusedRecordsLocked((StateObject) objArr[(i2 << 3) + i4]);
                                }
                                j >>= 8;
                            }
                            if (i3 != 8) {
                                break;
                            }
                        }
                        if (i2 == length) {
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
        return takeNewGlobalSnapshot;
    }

    public static final void checkAndOverwriteUnusedRecordsLocked() {
        SnapshotWeakSet snapshotWeakSet = extraStateObjects;
        int i = snapshotWeakSet.size;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= i) {
                break;
            }
            WeakReference weakReference = snapshotWeakSet.values[i2];
            Object obj = weakReference != null ? weakReference.get() : null;
            if (obj != null && overwriteUnusedRecordsLocked((StateObject) obj)) {
                if (i3 != i2) {
                    snapshotWeakSet.values[i3] = weakReference;
                    int[] iArr = snapshotWeakSet.hashes;
                    iArr[i3] = iArr[i2];
                }
                i3++;
            }
            i2++;
        }
        for (int i4 = i3; i4 < i; i4++) {
            snapshotWeakSet.values[i4] = null;
            snapshotWeakSet.hashes[i4] = 0;
        }
        if (i3 != i) {
            snapshotWeakSet.size = i3;
        }
    }

    public static final Snapshot createTransparentSnapshotWithNoParentReadObserver(Snapshot snapshot, Function1 function1, boolean z) {
        boolean z2 = snapshot instanceof MutableSnapshot;
        if (z2 || snapshot == null) {
            return new TransparentObserverMutableSnapshot(z2 ? (MutableSnapshot) snapshot : null, function1, null, false, z);
        }
        return new TransparentObserverSnapshot(snapshot, function1, z);
    }

    public static final StateRecord current(StateRecord stateRecord) {
        StateRecord readable;
        Snapshot currentSnapshot = currentSnapshot();
        StateRecord readable2 = readable(stateRecord, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 != null) {
            return readable2;
        }
        synchronized (lock) {
            Snapshot currentSnapshot2 = currentSnapshot();
            readable = readable(stateRecord, currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
        }
        if (readable != null) {
            return readable;
        }
        readError();
        throw null;
    }

    public static final Snapshot currentSnapshot() {
        Snapshot snapshot = (Snapshot) threadSnapshot.get();
        return snapshot == null ? (Snapshot) currentGlobalSnapshot.get() : snapshot;
    }

    public static final Function1 mergedReadObserver(final Function1 function1, final Function1 function12, boolean z) {
        if (!z) {
            function12 = null;
        }
        return (function1 == null || function12 == null || function1 == function12) ? function1 == null ? function12 : function1 : new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$mergedReadObserver$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                Function1.this.invoke(obj);
                function12.invoke(obj);
                return Unit.INSTANCE;
            }
        };
    }

    public static final StateRecord newOverwritableRecordLocked(StateRecord stateRecord, StateObject stateObject) {
        StateRecord firstStateRecord = stateObject.getFirstStateRecord();
        int i = nextSnapshotId;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
        if (snapshotDoubleIndexHeap.size > 0) {
            i = snapshotDoubleIndexHeap.values[0];
        }
        int i2 = i - 1;
        SnapshotIdSet snapshotIdSet = SnapshotIdSet.EMPTY;
        StateRecord stateRecord2 = null;
        StateRecord stateRecord3 = null;
        while (true) {
            if (firstStateRecord != null) {
                int i3 = firstStateRecord.snapshotId;
                if (i3 == 0) {
                    break;
                }
                if (i3 != 0 && i3 <= i2 && !snapshotIdSet.get(i3)) {
                    if (stateRecord3 == null) {
                        stateRecord3 = firstStateRecord;
                    } else if (firstStateRecord.snapshotId >= stateRecord3.snapshotId) {
                        stateRecord2 = stateRecord3;
                    }
                }
                firstStateRecord = firstStateRecord.next;
            } else {
                break;
            }
        }
        stateRecord2 = firstStateRecord;
        if (stateRecord2 != null) {
            stateRecord2.snapshotId = Integer.MAX_VALUE;
            return stateRecord2;
        }
        StateRecord create = stateRecord.create();
        create.snapshotId = Integer.MAX_VALUE;
        create.next = stateObject.getFirstStateRecord();
        stateObject.prependStateRecord(create);
        return create;
    }

    public static final void notifyWrite(Snapshot snapshot, StateObject stateObject) {
        snapshot.setWriteCount$runtime_release(snapshot.getWriteCount$runtime_release() + 1);
        Function1 writeObserver$runtime_release = snapshot.getWriteObserver$runtime_release();
        if (writeObserver$runtime_release != null) {
            writeObserver$runtime_release.invoke(stateObject);
        }
    }

    public static final StateRecord overwritableRecord(StateRecord stateRecord, StateObjectImpl stateObjectImpl, Snapshot snapshot, StateRecord stateRecord2) {
        StateRecord newOverwritableRecordLocked;
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(stateObjectImpl);
        }
        int id = snapshot.getId();
        if (stateRecord2.snapshotId == id) {
            return stateRecord2;
        }
        synchronized (lock) {
            newOverwritableRecordLocked = newOverwritableRecordLocked(stateRecord, stateObjectImpl);
        }
        newOverwritableRecordLocked.snapshotId = id;
        if (stateRecord2.snapshotId != 1) {
            snapshot.recordModified$runtime_release(stateObjectImpl);
        }
        return newOverwritableRecordLocked;
    }

    public static final boolean overwriteUnusedRecordsLocked(StateObject stateObject) {
        StateRecord stateRecord;
        int i = nextSnapshotId;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
        if (snapshotDoubleIndexHeap.size > 0) {
            i = snapshotDoubleIndexHeap.values[0];
        }
        StateRecord stateRecord2 = null;
        StateRecord stateRecord3 = null;
        int i2 = 0;
        for (StateRecord firstStateRecord = stateObject.getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.next) {
            int i3 = firstStateRecord.snapshotId;
            if (i3 != 0) {
                if (i3 >= i) {
                    i2++;
                } else if (stateRecord2 == null) {
                    i2++;
                    stateRecord2 = firstStateRecord;
                } else {
                    if (i3 < stateRecord2.snapshotId) {
                        stateRecord = stateRecord2;
                        stateRecord2 = firstStateRecord;
                    } else {
                        stateRecord = firstStateRecord;
                    }
                    if (stateRecord3 == null) {
                        stateRecord3 = stateObject.getFirstStateRecord();
                        StateRecord stateRecord4 = stateRecord3;
                        while (true) {
                            if (stateRecord3 == null) {
                                stateRecord3 = stateRecord4;
                                break;
                            }
                            int i4 = stateRecord3.snapshotId;
                            if (i4 >= i) {
                                break;
                            }
                            if (stateRecord4.snapshotId < i4) {
                                stateRecord4 = stateRecord3;
                            }
                            stateRecord3 = stateRecord3.next;
                        }
                    }
                    stateRecord2.snapshotId = 0;
                    stateRecord2.assign(stateRecord3);
                    stateRecord2 = stateRecord;
                }
            }
        }
        return i2 > 1;
    }

    public static final void processForUnusedRecordsLocked(StateObject stateObject) {
        if (overwriteUnusedRecordsLocked(stateObject)) {
            SnapshotWeakSet snapshotWeakSet = extraStateObjects;
            int i = snapshotWeakSet.size;
            int identityHashCode = System.identityHashCode(stateObject);
            int i2 = -1;
            if (i > 0) {
                int i3 = snapshotWeakSet.size - 1;
                int i4 = 0;
                while (true) {
                    if (i4 > i3) {
                        i2 = -(i4 + 1);
                        break;
                    }
                    int i5 = (i4 + i3) >>> 1;
                    int i6 = snapshotWeakSet.hashes[i5];
                    if (i6 < identityHashCode) {
                        i4 = i5 + 1;
                    } else if (i6 > identityHashCode) {
                        i3 = i5 - 1;
                    } else {
                        WeakReference weakReference = snapshotWeakSet.values[i5];
                        if (stateObject == (weakReference != null ? weakReference.get() : null)) {
                            i2 = i5;
                        } else {
                            int i7 = i5 - 1;
                            while (-1 < i7 && snapshotWeakSet.hashes[i7] == identityHashCode) {
                                WeakReference weakReference2 = snapshotWeakSet.values[i7];
                                if ((weakReference2 != null ? weakReference2.get() : null) == stateObject) {
                                    break;
                                } else {
                                    i7--;
                                }
                            }
                            int i8 = snapshotWeakSet.size;
                            i7 = i5 + 1;
                            while (true) {
                                if (i7 >= i8) {
                                    i7 = -(snapshotWeakSet.size + 1);
                                    break;
                                } else {
                                    if (snapshotWeakSet.hashes[i7] != identityHashCode) {
                                        i7 = -(i7 + 1);
                                        break;
                                    }
                                    WeakReference weakReference3 = snapshotWeakSet.values[i7];
                                    if ((weakReference3 != null ? weakReference3.get() : null) == stateObject) {
                                        break;
                                    } else {
                                        i7++;
                                    }
                                }
                            }
                            i2 = i7;
                        }
                    }
                }
                if (i2 >= 0) {
                    return;
                }
            }
            int i9 = -(i2 + 1);
            WeakReference[] weakReferenceArr = snapshotWeakSet.values;
            int length = weakReferenceArr.length;
            if (i == length) {
                int i10 = length * 2;
                WeakReference[] weakReferenceArr2 = new WeakReference[i10];
                int[] iArr = new int[i10];
                int i11 = i9 + 1;
                ArraysKt.copyInto(i11, i9, i, weakReferenceArr, weakReferenceArr2);
                ArraysKt.copyInto$default(0, i9, 6, snapshotWeakSet.values, weakReferenceArr2);
                ArraysKt.copyInto(i11, i9, i, snapshotWeakSet.hashes, iArr);
                ArraysKt.copyInto$default(0, i9, 6, snapshotWeakSet.hashes, iArr);
                snapshotWeakSet.values = weakReferenceArr2;
                snapshotWeakSet.hashes = iArr;
            } else {
                int i12 = i9 + 1;
                ArraysKt.copyInto(i12, i9, i, weakReferenceArr, weakReferenceArr);
                int[] iArr2 = snapshotWeakSet.hashes;
                ArraysKt.copyInto(i12, i9, i, iArr2, iArr2);
            }
            snapshotWeakSet.values[i9] = new WeakReference(stateObject);
            snapshotWeakSet.hashes[i9] = identityHashCode;
            snapshotWeakSet.size++;
        }
    }

    public static final void readError() {
        throw new IllegalStateException("Reading a state that was created after the snapshot was taken or in a snapshot that has not yet been applied");
    }

    public static final StateRecord readable(StateRecord stateRecord, StateObject stateObject) {
        StateRecord readable;
        Snapshot currentSnapshot = currentSnapshot();
        Function1 readObserver = currentSnapshot.getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(stateObject);
        }
        StateRecord readable2 = readable(stateRecord, currentSnapshot.getId(), currentSnapshot.getInvalid$runtime_release());
        if (readable2 != null) {
            return readable2;
        }
        synchronized (lock) {
            Snapshot currentSnapshot2 = currentSnapshot();
            readable = readable(stateObject.getFirstStateRecord(), currentSnapshot2.getId(), currentSnapshot2.getInvalid$runtime_release());
            if (readable == null) {
                readError();
                throw null;
            }
        }
        return readable;
    }

    public static final void releasePinningLocked(int i) {
        int i2;
        SnapshotDoubleIndexHeap snapshotDoubleIndexHeap = pinningTable;
        int i3 = snapshotDoubleIndexHeap.handles[i];
        snapshotDoubleIndexHeap.swap(i3, snapshotDoubleIndexHeap.size - 1);
        snapshotDoubleIndexHeap.size--;
        int[] iArr = snapshotDoubleIndexHeap.values;
        int i4 = iArr[i3];
        int i5 = i3;
        while (i5 > 0) {
            int i6 = ((i5 + 1) >> 1) - 1;
            if (iArr[i6] <= i4) {
                break;
            }
            snapshotDoubleIndexHeap.swap(i6, i5);
            i5 = i6;
        }
        int[] iArr2 = snapshotDoubleIndexHeap.values;
        int i7 = snapshotDoubleIndexHeap.size >> 1;
        while (i3 < i7) {
            int i8 = (i3 + 1) << 1;
            int i9 = i8 - 1;
            if (i8 < snapshotDoubleIndexHeap.size && (i2 = iArr2[i8]) < iArr2[i9]) {
                if (i2 >= iArr2[i3]) {
                    break;
                }
                snapshotDoubleIndexHeap.swap(i8, i3);
                i3 = i8;
            } else {
                if (iArr2[i9] >= iArr2[i3]) {
                    break;
                }
                snapshotDoubleIndexHeap.swap(i9, i3);
                i3 = i9;
            }
        }
        snapshotDoubleIndexHeap.handles[i] = snapshotDoubleIndexHeap.firstFreeHandle;
        snapshotDoubleIndexHeap.firstFreeHandle = i;
    }

    public static final Object takeNewGlobalSnapshot(Snapshot snapshot, Function1 function1) {
        Object invoke = function1.invoke(openSnapshots.clear(snapshot.getId()));
        synchronized (lock) {
            int i = nextSnapshotId;
            nextSnapshotId = i + 1;
            SnapshotIdSet clear = openSnapshots.clear(snapshot.getId());
            openSnapshots = clear;
            currentGlobalSnapshot.set(new GlobalSnapshot(i, clear));
            snapshot.dispose();
            openSnapshots = openSnapshots.set(i);
        }
        return invoke;
    }

    public static final StateRecord writableRecord(StateRecord stateRecord, StateObject stateObject, Snapshot snapshot) {
        StateRecord readable;
        if (snapshot.getReadOnly()) {
            snapshot.recordModified$runtime_release(stateObject);
        }
        int id = snapshot.getId();
        StateRecord readable2 = readable(stateRecord, id, snapshot.getInvalid$runtime_release());
        if (readable2 == null) {
            readError();
            throw null;
        }
        if (readable2.snapshotId == snapshot.getId()) {
            return readable2;
        }
        synchronized (lock) {
            readable = readable(stateObject.getFirstStateRecord(), id, snapshot.getInvalid$runtime_release());
            if (readable == null) {
                readError();
                throw null;
            }
            if (readable.snapshotId != id) {
                StateRecord newOverwritableRecordLocked = newOverwritableRecordLocked(readable, stateObject);
                newOverwritableRecordLocked.assign(readable);
                newOverwritableRecordLocked.snapshotId = snapshot.getId();
                readable = newOverwritableRecordLocked;
            }
        }
        if (readable2.snapshotId != 1) {
            snapshot.recordModified$runtime_release(stateObject);
        }
        return readable;
    }

    public static final StateRecord current(StateRecord stateRecord, Snapshot snapshot) {
        StateRecord readable = readable(stateRecord, snapshot.getId(), snapshot.getInvalid$runtime_release());
        if (readable != null) {
            return readable;
        }
        readError();
        throw null;
    }

    public static final StateRecord readable(StateRecord stateRecord, int i, SnapshotIdSet snapshotIdSet) {
        StateRecord stateRecord2 = null;
        while (stateRecord != null) {
            int i2 = stateRecord.snapshotId;
            if (i2 != 0 && i2 <= i && !snapshotIdSet.get(i2) && (stateRecord2 == null || stateRecord2.snapshotId < stateRecord.snapshotId)) {
                stateRecord2 = stateRecord;
            }
            stateRecord = stateRecord.next;
        }
        if (stateRecord2 != null) {
            return stateRecord2;
        }
        return null;
    }
}
