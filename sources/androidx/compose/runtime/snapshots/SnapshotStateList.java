package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.AbstractPersistentList;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.PersistentVectorBuilder;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.SmallPersistentVector;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotStateList implements StateObject, List, RandomAccess, KMutableList {
    public StateListStateRecord firstStateRecord;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateListStateRecord extends StateRecord {
        public PersistentList list;
        public int modification;
        public int structuralChange;

        public StateListStateRecord(PersistentList persistentList) {
            this.list = persistentList;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            synchronized (SnapshotStateListKt.sync) {
                this.list = ((StateListStateRecord) stateRecord).list;
                this.modification = ((StateListStateRecord) stateRecord).modification;
                this.structuralChange = ((StateListStateRecord) stateRecord).structuralChange;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateListStateRecord(this.list);
        }
    }

    public SnapshotStateList() {
        SmallPersistentVector smallPersistentVector = SmallPersistentVector.EMPTY;
        StateListStateRecord stateListStateRecord = new StateListStateRecord(smallPersistentVector);
        if (Snapshot.Companion.isInSnapshot()) {
            StateListStateRecord stateListStateRecord2 = new StateListStateRecord(smallPersistentVector);
            stateListStateRecord2.snapshotId = 1;
            stateListStateRecord.next = stateListStateRecord2;
        }
        this.firstStateRecord = stateListStateRecord;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList add = persistentList.add(obj);
            z = false;
            if (add.equals(persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj2) {
                    int i2 = stateListStateRecord3.modification;
                    if (i2 == i) {
                        stateListStateRecord3.list = add;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i2 + 1;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final boolean addAll(final int i, final Collection collection) {
        return mutateBoolean(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$addAll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((List) obj).addAll(i, collection));
            }
        });
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        Snapshot currentSnapshot;
        StateListStateRecord stateListStateRecord = this.firstStateRecord;
        synchronized (SnapshotKt.lock) {
            currentSnapshot = SnapshotKt.currentSnapshot();
            StateListStateRecord stateListStateRecord2 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord, this, currentSnapshot);
            synchronized (SnapshotStateListKt.sync) {
                stateListStateRecord2.list = SmallPersistentVector.EMPTY;
                stateListStateRecord2.modification++;
                stateListStateRecord2.structuralChange++;
            }
        }
        SnapshotKt.notifyWrite(currentSnapshot, this);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return getReadable$runtime_release().list.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return getReadable$runtime_release().list.containsAll(collection);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return getReadable$runtime_release().list.get(i);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final StateListStateRecord getReadable$runtime_release() {
        return (StateListStateRecord) SnapshotKt.readable(this.firstStateRecord, this);
    }

    public final int getStructure$runtime_release() {
        return ((StateListStateRecord) SnapshotKt.current(this.firstStateRecord)).structuralChange;
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return getReadable$runtime_release().list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return getReadable$runtime_release().list.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return getReadable$runtime_release().list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return new StateListIterator(this, 0);
    }

    public final boolean mutateBoolean(Function1 function1) {
        int i;
        PersistentList persistentList;
        Object invoke;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentVectorBuilder builder = persistentList.builder();
            invoke = function1.invoke(builder);
            PersistentList build = builder.build();
            if (Intrinsics.areEqual(build, persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj) {
                    int i2 = stateListStateRecord3.modification;
                    if (i2 == i) {
                        stateListStateRecord3.list = build;
                        stateListStateRecord3.modification = i2 + 1;
                        z = true;
                        stateListStateRecord3.structuralChange++;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return ((Boolean) invoke).booleanValue();
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        stateRecord.next = this.firstStateRecord;
        this.firstStateRecord = (StateListStateRecord) stateRecord;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        Object obj = get(i);
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i2 = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList removeAt = persistentList.removeAt(i);
            if (Intrinsics.areEqual(removeAt, persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj2) {
                    int i3 = stateListStateRecord3.modification;
                    if (i3 == i2) {
                        stateListStateRecord3.list = removeAt;
                        z = true;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i3 + 1;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return obj;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList removeAll = ((AbstractPersistentList) persistentList).removeAll(collection);
            z = false;
            if (Intrinsics.areEqual(removeAll, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj) {
                    int i2 = stateListStateRecord3.modification;
                    if (i2 == i) {
                        stateListStateRecord3.list = removeAll;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i2 + 1;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(final Collection collection) {
        return mutateBoolean(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateList$retainAll$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(((List) obj).retainAll(collection));
            }
        });
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        Object obj2 = get(i);
        do {
            Object obj3 = SnapshotStateListKt.sync;
            synchronized (obj3) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i2 = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList persistentList2 = persistentList.set(i, obj);
            if (persistentList2.equals(persistentList)) {
                break;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj3) {
                    int i3 = stateListStateRecord3.modification;
                    if (i3 == i2) {
                        stateListStateRecord3.list = persistentList2;
                        stateListStateRecord3.modification = i3 + 1;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return obj2;
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return getReadable$runtime_release().list.size();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        if (!(i >= 0 && i <= i2 && i2 <= size())) {
            PreconditionsKt.throwIllegalArgumentException("fromIndex or toIndex are out of bounds");
        }
        return new SubList(this, i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    public final String toString() {
        return "SnapshotStateList(value=" + ((StateListStateRecord) SnapshotKt.current(this.firstStateRecord)).list + ")@" + hashCode();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj = SnapshotStateListKt.sync;
            synchronized (obj) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList addAll = persistentList.addAll(collection);
            z = false;
            if (Intrinsics.areEqual(addAll, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj) {
                    int i2 = stateListStateRecord3.modification;
                    if (i2 == i) {
                        stateListStateRecord3.list = addAll;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i2 + 1;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return new StateListIterator(this, i);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return CollectionToArray.toArray(this, objArr);
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        int i2;
        PersistentList persistentList;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i2 = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            PersistentList add = persistentList.add(i, obj);
            if (add.equals(persistentList)) {
                return;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj2) {
                    int i3 = stateListStateRecord3.modification;
                    if (i3 == i2) {
                        stateListStateRecord3.list = add;
                        z = true;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i3 + 1;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        int i;
        PersistentList persistentList;
        boolean z;
        Snapshot currentSnapshot;
        do {
            Object obj2 = SnapshotStateListKt.sync;
            synchronized (obj2) {
                StateListStateRecord stateListStateRecord = (StateListStateRecord) SnapshotKt.current(this.firstStateRecord);
                i = stateListStateRecord.modification;
                persistentList = stateListStateRecord.list;
            }
            Intrinsics.checkNotNull(persistentList);
            AbstractPersistentList abstractPersistentList = (AbstractPersistentList) persistentList;
            int indexOf = abstractPersistentList.indexOf(obj);
            PersistentList persistentList2 = abstractPersistentList;
            if (indexOf != -1) {
                persistentList2 = abstractPersistentList.removeAt(indexOf);
            }
            z = false;
            if (Intrinsics.areEqual(persistentList2, persistentList)) {
                return false;
            }
            StateListStateRecord stateListStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateListStateRecord stateListStateRecord3 = (StateListStateRecord) SnapshotKt.writableRecord(stateListStateRecord2, this, currentSnapshot);
                synchronized (obj2) {
                    int i2 = stateListStateRecord3.modification;
                    if (i2 == i) {
                        stateListStateRecord3.list = persistentList2;
                        stateListStateRecord3.structuralChange++;
                        stateListStateRecord3.modification = i2 + 1;
                        z = true;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return true;
    }
}
