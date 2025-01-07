package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class MutableSnapshot extends Snapshot {
    public static final int[] EmptyIntArray = new int[0];
    public boolean applied;
    public List merged;
    public MutableScatterSet modified;
    public SnapshotIdSet previousIds;
    public int[] previousPinnedSnapshots;
    public final Function1 readObserver;
    public int snapshots;
    public int writeCount;
    public final Function1 writeObserver;

    public MutableSnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Function1 function12) {
        super(i, snapshotIdSet);
        this.readObserver = function1;
        this.writeObserver = function12;
        this.previousIds = SnapshotIdSet.EMPTY;
        this.previousPinnedSnapshots = EmptyIntArray;
        this.snapshots = 1;
    }

    public final void advance$runtime_release() {
        recordPrevious$runtime_release(getId());
        if (this.applied || this.disposed) {
            return;
        }
        int id = getId();
        synchronized (SnapshotKt.lock) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            setId$runtime_release(i);
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
        }
        setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, getId()));
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00bf A[LOOP:1: B:31:0x00bd->B:32:0x00bf, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public androidx.compose.runtime.snapshots.SnapshotApplyResult apply() {
        /*
            Method dump skipped, instructions count: 394
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.MutableSnapshot.apply():androidx.compose.runtime.snapshots.SnapshotApplyResult");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId()).andNot(this.previousIds);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void dispose() {
        if (this.disposed) {
            return;
        }
        this.disposed = true;
        synchronized (SnapshotKt.lock) {
            int i = this.pinningTrackingHandle;
            if (i >= 0) {
                SnapshotKt.releasePinningLocked(i);
                this.pinningTrackingHandle = -1;
            }
        }
        nestedDeactivated$runtime_release();
    }

    public MutableScatterSet getModified$runtime_release() {
        return this.modified;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    /* renamed from: getReadObserver$runtime_release, reason: merged with bridge method [inline-methods] */
    public Function1 getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public boolean getReadOnly() {
        return false;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public int getWriteCount$runtime_release() {
        return this.writeCount;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Function1 getWriteObserver$runtime_release() {
        return this.writeObserver;
    }

    public final SnapshotApplyResult innerApplyLocked$runtime_release(int i, MutableScatterSet mutableScatterSet, Map map, SnapshotIdSet snapshotIdSet) {
        ArrayList arrayList;
        List list;
        ArrayList arrayList2;
        SnapshotIdSet snapshotIdSet2;
        Object[] objArr;
        long[] jArr;
        SnapshotIdSet snapshotIdSet3;
        Object[] objArr2;
        long[] jArr2;
        int i2;
        StateRecord readable;
        StateRecord mergeRecords;
        SnapshotIdSet or = getInvalid$runtime_release().set(getId()).or(this.previousIds);
        Object[] objArr3 = mutableScatterSet.elements;
        long[] jArr3 = mutableScatterSet.metadata;
        int length = jArr3.length - 2;
        if (length >= 0) {
            int i3 = 0;
            arrayList2 = null;
            list = null;
            while (true) {
                long j = jArr3[i3];
                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i4 = 8;
                    int i5 = 8 - ((~(i3 - length)) >>> 31);
                    int i6 = 0;
                    while (i6 < i5) {
                        if ((j & 255) < 128) {
                            StateObject stateObject = (StateObject) objArr3[(i3 << 3) + i6];
                            StateRecord firstStateRecord = stateObject.getFirstStateRecord();
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            StateRecord readable2 = SnapshotKt.readable(firstStateRecord, i, snapshotIdSet);
                            if (readable2 == null || (readable = SnapshotKt.readable(firstStateRecord, getId(), or)) == null) {
                                snapshotIdSet3 = or;
                            } else {
                                snapshotIdSet3 = or;
                                if (readable.snapshotId != 1 && !readable2.equals(readable)) {
                                    StateRecord readable3 = SnapshotKt.readable(firstStateRecord, getId(), getInvalid$runtime_release());
                                    if (readable3 == null) {
                                        SnapshotKt.readError();
                                        throw null;
                                    }
                                    if (map == null || (mergeRecords = (StateRecord) map.get(readable2)) == null) {
                                        mergeRecords = stateObject.mergeRecords(readable, readable2, readable3);
                                    }
                                    if (mergeRecords == null) {
                                        return new SnapshotApplyResult.Failure(this);
                                    }
                                    if (!mergeRecords.equals(readable3)) {
                                        if (mergeRecords.equals(readable2)) {
                                            if (arrayList2 == null) {
                                                arrayList2 = new ArrayList();
                                            }
                                            arrayList2.add(new Pair(stateObject, readable2.create()));
                                            if (list == null) {
                                                list = new ArrayList();
                                            }
                                            list.add(stateObject);
                                        } else {
                                            if (arrayList2 == null) {
                                                arrayList2 = new ArrayList();
                                            }
                                            arrayList2.add(!mergeRecords.equals(readable) ? new Pair(stateObject, mergeRecords) : new Pair(stateObject, readable.create()));
                                        }
                                    }
                                }
                            }
                            i2 = 8;
                        } else {
                            snapshotIdSet3 = or;
                            objArr2 = objArr3;
                            jArr2 = jArr3;
                            i2 = i4;
                        }
                        j >>= i2;
                        i6++;
                        i4 = i2;
                        objArr3 = objArr2;
                        jArr3 = jArr2;
                        or = snapshotIdSet3;
                    }
                    snapshotIdSet2 = or;
                    objArr = objArr3;
                    jArr = jArr3;
                    if (i5 != i4) {
                        break;
                    }
                } else {
                    snapshotIdSet2 = or;
                    objArr = objArr3;
                    jArr = jArr3;
                }
                if (i3 == length) {
                    arrayList = arrayList2;
                    break;
                }
                i3++;
                objArr3 = objArr;
                jArr3 = jArr;
                or = snapshotIdSet2;
            }
        } else {
            arrayList = null;
            list = null;
        }
        arrayList2 = arrayList;
        if (arrayList2 != null) {
            advance$runtime_release();
            int size = arrayList2.size();
            for (int i7 = 0; i7 < size; i7++) {
                Pair pair = (Pair) arrayList2.get(i7);
                StateObject stateObject2 = (StateObject) pair.component1();
                StateRecord stateRecord = (StateRecord) pair.component2();
                stateRecord.snapshotId = getId();
                synchronized (SnapshotKt.lock) {
                    stateRecord.next = stateObject2.getFirstStateRecord();
                    stateObject2.prependStateRecord(stateRecord);
                }
            }
        }
        if (list != null) {
            int size2 = list.size();
            for (int i8 = 0; i8 < size2; i8++) {
                mutableScatterSet.remove((StateObject) list.get(i8));
            }
            List list2 = this.merged;
            if (list2 != null) {
                list = CollectionsKt.plus((Iterable) list, (Collection) list2);
            }
            this.merged = list;
        }
        return SnapshotApplyResult.Success.INSTANCE;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void nestedActivated$runtime_release() {
        this.snapshots++;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void nestedDeactivated$runtime_release() {
        if (this.snapshots <= 0) {
            PreconditionsKt.throwIllegalArgumentException("no pending nested snapshots");
        }
        int i = this.snapshots - 1;
        this.snapshots = i;
        if (i != 0 || this.applied) {
            return;
        }
        MutableScatterSet modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release != null) {
            if (this.applied) {
                PreconditionsKt.throwIllegalStateException("Unsupported operation on a snapshot that has been applied");
            }
            setModified(null);
            int id = getId();
            Object[] objArr = modified$runtime_release.elements;
            long[] jArr = modified$runtime_release.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i2 = 0;
                while (true) {
                    long j = jArr[i2];
                    if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i3 = 8 - ((~(i2 - length)) >>> 31);
                        for (int i4 = 0; i4 < i3; i4++) {
                            if ((255 & j) < 128) {
                                for (StateRecord firstStateRecord = ((StateObject) objArr[(i2 << 3) + i4]).getFirstStateRecord(); firstStateRecord != null; firstStateRecord = firstStateRecord.next) {
                                    int i5 = firstStateRecord.snapshotId;
                                    if (i5 == id || CollectionsKt.contains(this.previousIds, Integer.valueOf(i5))) {
                                        firstStateRecord.snapshotId = 0;
                                    }
                                }
                            }
                            j >>= 8;
                        }
                        if (i3 != 8) {
                            break;
                        }
                    }
                    if (i2 == length) {
                        break;
                    } else {
                        i2++;
                    }
                }
            }
        }
        closeAndReleasePinning$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void notifyObjectsInitialized$runtime_release() {
        if (this.applied || this.disposed) {
            return;
        }
        advance$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void recordModified$runtime_release(StateObject stateObject) {
        MutableScatterSet modified$runtime_release = getModified$runtime_release();
        if (modified$runtime_release == null) {
            int i = ScatterSetKt.$r8$clinit;
            modified$runtime_release = new MutableScatterSet();
            setModified(modified$runtime_release);
        }
        modified$runtime_release.add(stateObject);
    }

    public final void recordPrevious$runtime_release(int i) {
        synchronized (SnapshotKt.lock) {
            this.previousIds = this.previousIds.set(i);
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        int length = this.previousPinnedSnapshots.length;
        for (int i = 0; i < length; i++) {
            SnapshotKt.releasePinningLocked(this.previousPinnedSnapshots[i]);
        }
        int i2 = this.pinningTrackingHandle;
        if (i2 >= 0) {
            SnapshotKt.releasePinningLocked(i2);
            this.pinningTrackingHandle = -1;
        }
    }

    public void setModified(MutableScatterSet mutableScatterSet) {
        this.modified = mutableScatterSet;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public void setWriteCount$runtime_release(int i) {
        this.writeCount = i;
    }

    public MutableSnapshot takeNestedMutableSnapshot(Function1 function1, Function1 function12) {
        NestedMutableSnapshot nestedMutableSnapshot;
        if (this.disposed) {
            PreconditionsKt.throwIllegalArgumentException("Cannot use a disposed snapshot");
        }
        if (this.applied && this.pinningTrackingHandle < 0) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
        }
        recordPrevious$runtime_release(getId());
        Object obj = SnapshotKt.lock;
        synchronized (obj) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            setInvalid$runtime_release(invalid$runtime_release.set(i));
            nestedMutableSnapshot = new NestedMutableSnapshot(i, SnapshotKt.addRange(invalid$runtime_release, getId() + 1, i), SnapshotKt.mergedReadObserver(function1, getReadObserver(), true), SnapshotKt.access$mergedWriteObserver(function12, getWriteObserver$runtime_release()), this);
        }
        if (!this.applied && !this.disposed) {
            int id = getId();
            synchronized (obj) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, getId()));
        }
        return nestedMutableSnapshot;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public Snapshot takeNestedSnapshot(Function1 function1) {
        NestedReadonlySnapshot nestedReadonlySnapshot;
        if (this.disposed) {
            PreconditionsKt.throwIllegalArgumentException("Cannot use a disposed snapshot");
        }
        if (this.applied && this.pinningTrackingHandle < 0) {
            PreconditionsKt.throwIllegalStateException("Unsupported operation on a disposed or applied snapshot");
        }
        int id = getId();
        boolean z = this instanceof GlobalSnapshot;
        recordPrevious$runtime_release(getId());
        Object obj = SnapshotKt.lock;
        synchronized (obj) {
            int i = SnapshotKt.nextSnapshotId;
            SnapshotKt.nextSnapshotId = i + 1;
            SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(i);
            nestedReadonlySnapshot = new NestedReadonlySnapshot(i, SnapshotKt.addRange(getInvalid$runtime_release(), id + 1, i), SnapshotKt.mergedReadObserver(function1, getReadObserver(), true), this);
        }
        if (!this.applied && !this.disposed) {
            int id2 = getId();
            synchronized (obj) {
                int i2 = SnapshotKt.nextSnapshotId;
                SnapshotKt.nextSnapshotId = i2 + 1;
                setId$runtime_release(i2);
                SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.set(getId());
            }
            setInvalid$runtime_release(SnapshotKt.addRange(getInvalid$runtime_release(), id2 + 1, getId()));
        }
        return nestedReadonlySnapshot;
    }
}
