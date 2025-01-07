package androidx.compose.runtime.snapshots;

import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.snapshots.Snapshot;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableMap;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotStateMap implements StateObject, Map, KMutableMap {
    public final Set entries;
    public StateMapStateRecord firstStateRecord;
    public final Set keys;
    public final Collection values;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StateMapStateRecord extends StateRecord {
        public PersistentMap map;
        public int modification;

        public StateMapStateRecord(PersistentMap persistentMap) {
            this.map = persistentMap;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) stateRecord;
            synchronized (SnapshotStateMapKt.sync) {
                this.map = stateMapStateRecord.map;
                this.modification = stateMapStateRecord.modification;
            }
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateMapStateRecord(this.map);
        }
    }

    public SnapshotStateMap() {
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        StateMapStateRecord stateMapStateRecord = new StateMapStateRecord(persistentHashMap);
        if (Snapshot.Companion.isInSnapshot()) {
            StateMapStateRecord stateMapStateRecord2 = new StateMapStateRecord(persistentHashMap);
            stateMapStateRecord2.snapshotId = 1;
            stateMapStateRecord.next = stateMapStateRecord2;
        }
        this.firstStateRecord = stateMapStateRecord;
        this.entries = new SnapshotMapEntrySet(this);
        this.keys = new SnapshotMapKeySet(this);
        this.values = new SnapshotMapValueSet(this);
    }

    @Override // java.util.Map
    public final void clear() {
        Snapshot currentSnapshot;
        StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current(this.firstStateRecord);
        PersistentHashMap persistentHashMap = PersistentHashMap.EMPTY;
        if (persistentHashMap != stateMapStateRecord.map) {
            StateMapStateRecord stateMapStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, currentSnapshot);
                synchronized (SnapshotStateMapKt.sync) {
                    stateMapStateRecord3.map = persistentHashMap;
                    stateMapStateRecord3.modification++;
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        }
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return getReadable$runtime_release().map.containsKey(obj);
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        return getReadable$runtime_release().map.containsValue(obj);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        return this.entries;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        return getReadable$runtime_release().map.get(obj);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.firstStateRecord;
    }

    public final StateMapStateRecord getReadable$runtime_release() {
        return (StateMapStateRecord) SnapshotKt.readable(this.firstStateRecord, this);
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return getReadable$runtime_release().map.isEmpty();
    }

    @Override // java.util.Map
    public final Set keySet() {
        return this.keys;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.firstStateRecord = (StateMapStateRecord) stateRecord;
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        PersistentMap persistentMap;
        int i;
        Object put;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj3 = SnapshotStateMapKt.sync;
            synchronized (obj3) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current(this.firstStateRecord);
                persistentMap = stateMapStateRecord.map;
                i = stateMapStateRecord.modification;
            }
            Intrinsics.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            put = builder.put(obj, obj2);
            PersistentMap build = builder.build();
            if (Intrinsics.areEqual(build, persistentMap)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, currentSnapshot);
                synchronized (obj3) {
                    int i2 = stateMapStateRecord3.modification;
                    if (i2 == i) {
                        stateMapStateRecord3.map = build;
                        stateMapStateRecord3.modification = i2 + 1;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return put;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        PersistentMap persistentMap;
        int i;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj = SnapshotStateMapKt.sync;
            synchronized (obj) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current(this.firstStateRecord);
                persistentMap = stateMapStateRecord.map;
                i = stateMapStateRecord.modification;
            }
            Intrinsics.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            builder.putAll(map);
            PersistentMap build = builder.build();
            if (Intrinsics.areEqual(build, persistentMap)) {
                return;
            }
            StateMapStateRecord stateMapStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, currentSnapshot);
                synchronized (obj) {
                    int i2 = stateMapStateRecord3.modification;
                    if (i2 == i) {
                        stateMapStateRecord3.map = build;
                        stateMapStateRecord3.modification = i2 + 1;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        PersistentMap persistentMap;
        int i;
        Object remove;
        Snapshot currentSnapshot;
        boolean z;
        do {
            Object obj2 = SnapshotStateMapKt.sync;
            synchronized (obj2) {
                StateMapStateRecord stateMapStateRecord = (StateMapStateRecord) SnapshotKt.current(this.firstStateRecord);
                persistentMap = stateMapStateRecord.map;
                i = stateMapStateRecord.modification;
            }
            Intrinsics.checkNotNull(persistentMap);
            PersistentMap.Builder builder = persistentMap.builder();
            remove = builder.remove(obj);
            PersistentMap build = builder.build();
            if (Intrinsics.areEqual(build, persistentMap)) {
                break;
            }
            StateMapStateRecord stateMapStateRecord2 = this.firstStateRecord;
            synchronized (SnapshotKt.lock) {
                currentSnapshot = SnapshotKt.currentSnapshot();
                StateMapStateRecord stateMapStateRecord3 = (StateMapStateRecord) SnapshotKt.writableRecord(stateMapStateRecord2, this, currentSnapshot);
                synchronized (obj2) {
                    int i2 = stateMapStateRecord3.modification;
                    if (i2 == i) {
                        stateMapStateRecord3.map = build;
                        stateMapStateRecord3.modification = i2 + 1;
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
            SnapshotKt.notifyWrite(currentSnapshot, this);
        } while (!z);
        return remove;
    }

    @Override // java.util.Map
    public final int size() {
        return getReadable$runtime_release().map.size();
    }

    public final String toString() {
        return "SnapshotStateMap(value=" + ((StateMapStateRecord) SnapshotKt.current(this.firstStateRecord)).map + ")@" + hashCode();
    }

    @Override // java.util.Map
    public final Collection values() {
        return this.values;
    }
}
