package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DerivedSnapshotState extends StateObjectImpl implements DerivedState {
    public final Function0 calculation;
    public ResultRecord first = new ResultRecord();
    public final SnapshotMutationPolicy policy;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ResultRecord extends StateRecord {
        public static final Object Unset = new Object();
        public MutableObjectIntMap dependencies = ObjectIntMapKt.EmptyObjectIntMap;
        public Object result = Unset;
        public int resultHash;
        public int validSnapshotId;
        public int validSnapshotWriteCount;

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            ResultRecord resultRecord = (ResultRecord) stateRecord;
            this.dependencies = resultRecord.dependencies;
            this.result = resultRecord.result;
            this.resultHash = resultRecord.resultHash;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new ResultRecord();
        }

        public final boolean isValid(DerivedState derivedState, Snapshot snapshot) {
            boolean z;
            boolean z2;
            Object obj = SnapshotKt.lock;
            synchronized (obj) {
                z = true;
                if (this.validSnapshotId == snapshot.getId()) {
                    if (this.validSnapshotWriteCount == snapshot.getWriteCount$runtime_release()) {
                        z2 = false;
                    }
                }
                z2 = true;
            }
            if (this.result == Unset || (z2 && this.resultHash != readableHash(derivedState, snapshot))) {
                z = false;
            }
            if (z && z2) {
                synchronized (obj) {
                    this.validSnapshotId = snapshot.getId();
                    this.validSnapshotWriteCount = snapshot.getWriteCount$runtime_release();
                }
            }
            return z;
        }

        public final int readableHash(DerivedState derivedState, Snapshot snapshot) {
            MutableObjectIntMap mutableObjectIntMap;
            int i;
            int i2;
            StateRecord current;
            synchronized (SnapshotKt.lock) {
                mutableObjectIntMap = this.dependencies;
            }
            int i3 = 0;
            int i4 = 1;
            char c = 7;
            if (!(mutableObjectIntMap._size != 0)) {
                return 7;
            }
            MutableVector derivedStateObservers = SnapshotStateKt.derivedStateObservers();
            int i5 = derivedStateObservers.size;
            if (i5 > 0) {
                Object[] objArr = derivedStateObservers.content;
                int i6 = 0;
                do {
                    ((DerivedStateObserver) objArr[i6]).start();
                    i6++;
                } while (i6 < i5);
            }
            try {
                Object[] objArr2 = mutableObjectIntMap.keys;
                int[] iArr = mutableObjectIntMap.values;
                long[] jArr = mutableObjectIntMap.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i7 = 0;
                    int i8 = 7;
                    while (true) {
                        long j = jArr[i7];
                        if ((((~j) << c) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i9 = 8;
                            int i10 = 8 - ((~(i7 - length)) >>> 31);
                            int i11 = 0;
                            while (i11 < i10) {
                                if ((j & 255) < 128) {
                                    int i12 = (i7 << 3) + i11;
                                    StateObject stateObject = (StateObject) objArr2[i12];
                                    if (iArr[i12] == i4) {
                                        if (stateObject instanceof DerivedSnapshotState) {
                                            DerivedSnapshotState derivedSnapshotState = (DerivedSnapshotState) stateObject;
                                            current = derivedSnapshotState.currentRecord((ResultRecord) SnapshotKt.current(derivedSnapshotState.first, snapshot), snapshot, false, derivedSnapshotState.calculation);
                                        } else {
                                            current = SnapshotKt.current(stateObject.getFirstStateRecord(), snapshot);
                                        }
                                        i8 = (((i8 * 31) + System.identityHashCode(current)) * 31) + current.snapshotId;
                                    }
                                    i2 = 8;
                                } else {
                                    i2 = i9;
                                }
                                j >>= i2;
                                i11++;
                                i9 = i2;
                                i4 = 1;
                            }
                            if (i10 != i9) {
                                break;
                            }
                        }
                        if (i7 == length) {
                            break;
                        }
                        i7++;
                        i4 = 1;
                        c = 7;
                    }
                    i = i8;
                } else {
                    i = 7;
                }
                int i13 = derivedStateObservers.size;
                if (i13 <= 0) {
                    return i;
                }
                Object[] objArr3 = derivedStateObservers.content;
                do {
                    ((DerivedStateObserver) objArr3[i3]).done();
                    i3++;
                } while (i3 < i13);
                return i;
            } catch (Throwable th) {
                int i14 = derivedStateObservers.size;
                if (i14 > 0) {
                    Object[] objArr4 = derivedStateObservers.content;
                    do {
                        ((DerivedStateObserver) objArr4[i3]).done();
                        i3++;
                    } while (i3 < i14);
                }
                throw th;
            }
        }
    }

    public DerivedSnapshotState(SnapshotMutationPolicy snapshotMutationPolicy, Function0 function0) {
        this.calculation = function0;
        this.policy = snapshotMutationPolicy;
    }

    public final ResultRecord currentRecord(ResultRecord resultRecord, Snapshot snapshot, boolean z, Function0 function0) {
        MutableVector derivedStateObservers;
        SnapshotMutationPolicy snapshotMutationPolicy;
        int i;
        ResultRecord resultRecord2 = resultRecord;
        if (resultRecord2.isValid(this, snapshot)) {
            if (z) {
                derivedStateObservers = SnapshotStateKt.derivedStateObservers();
                int i2 = derivedStateObservers.size;
                if (i2 > 0) {
                    Object[] objArr = derivedStateObservers.content;
                    int i3 = 0;
                    do {
                        ((DerivedStateObserver) objArr[i3]).start();
                        i3++;
                    } while (i3 < i2);
                }
                try {
                    MutableObjectIntMap mutableObjectIntMap = resultRecord2.dependencies;
                    SnapshotThreadLocal snapshotThreadLocal = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
                    IntRef intRef = (IntRef) snapshotThreadLocal.get();
                    if (intRef == null) {
                        intRef = new IntRef();
                        snapshotThreadLocal.set(intRef);
                    }
                    int i4 = intRef.element;
                    Object[] objArr2 = mutableObjectIntMap.keys;
                    int[] iArr = mutableObjectIntMap.values;
                    long[] jArr = mutableObjectIntMap.metadata;
                    int length = jArr.length - 2;
                    if (length >= 0) {
                        int i5 = 0;
                        while (true) {
                            long j = jArr[i5];
                            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i6 = 8;
                                int i7 = 8 - ((~(i5 - length)) >>> 31);
                                int i8 = 0;
                                while (i8 < i7) {
                                    if ((j & 255) < 128) {
                                        int i9 = (i5 << 3) + i8;
                                        StateObject stateObject = (StateObject) objArr2[i9];
                                        intRef.element = i4 + iArr[i9];
                                        Function1 readObserver = snapshot.getReadObserver();
                                        if (readObserver != null) {
                                            readObserver.invoke(stateObject);
                                        }
                                        i = 8;
                                    } else {
                                        i = i6;
                                    }
                                    j >>= i;
                                    i8++;
                                    i6 = i;
                                }
                                if (i7 != i6) {
                                    break;
                                }
                            }
                            if (i5 == length) {
                                break;
                            }
                            i5++;
                        }
                    }
                    intRef.element = i4;
                    int i10 = derivedStateObservers.size;
                    if (i10 > 0) {
                        Object[] objArr3 = derivedStateObservers.content;
                        int i11 = 0;
                        do {
                            ((DerivedStateObserver) objArr3[i11]).done();
                            i11++;
                        } while (i11 < i10);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return resultRecord2;
        }
        final MutableObjectIntMap mutableObjectIntMap2 = new MutableObjectIntMap();
        SnapshotThreadLocal snapshotThreadLocal2 = SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel;
        final IntRef intRef2 = (IntRef) snapshotThreadLocal2.get();
        if (intRef2 == null) {
            intRef2 = new IntRef();
            snapshotThreadLocal2.set(intRef2);
        }
        final int i12 = intRef2.element;
        derivedStateObservers = SnapshotStateKt.derivedStateObservers();
        int i13 = derivedStateObservers.size;
        if (i13 > 0) {
            Object[] objArr4 = derivedStateObservers.content;
            int i14 = 0;
            do {
                ((DerivedStateObserver) objArr4[i14]).start();
                i14++;
            } while (i14 < i13);
        }
        try {
            intRef2.element = i12 + 1;
            Object observe = Snapshot.Companion.observe(function0, new Function1() { // from class: androidx.compose.runtime.DerivedSnapshotState$currentRecord$result$1$1$result$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    if (obj == DerivedSnapshotState.this) {
                        throw new IllegalStateException("A derived state calculation cannot read itself");
                    }
                    if (obj instanceof StateObject) {
                        int i15 = intRef2.element;
                        MutableObjectIntMap mutableObjectIntMap3 = mutableObjectIntMap2;
                        int i16 = i15 - i12;
                        int findKeyIndex = mutableObjectIntMap3.findKeyIndex(obj);
                        mutableObjectIntMap3.set(Math.min(i16, findKeyIndex >= 0 ? mutableObjectIntMap3.values[findKeyIndex] : Integer.MAX_VALUE), obj);
                    }
                    return Unit.INSTANCE;
                }
            });
            intRef2.element = i12;
            int i15 = derivedStateObservers.size;
            if (i15 > 0) {
                Object[] objArr5 = derivedStateObservers.content;
                int i16 = 0;
                do {
                    ((DerivedStateObserver) objArr5[i16]).done();
                    i16++;
                } while (i16 < i15);
            }
            Object obj = SnapshotKt.lock;
            synchronized (obj) {
                try {
                    Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
                    Object obj2 = resultRecord2.result;
                    if (obj2 == ResultRecord.Unset || (snapshotMutationPolicy = this.policy) == null || !snapshotMutationPolicy.equivalent(observe, obj2)) {
                        ResultRecord resultRecord3 = this.first;
                        synchronized (obj) {
                            StateRecord newOverwritableRecordLocked = SnapshotKt.newOverwritableRecordLocked(resultRecord3, this);
                            newOverwritableRecordLocked.assign(resultRecord3);
                            newOverwritableRecordLocked.snapshotId = currentSnapshot.getId();
                            resultRecord2 = (ResultRecord) newOverwritableRecordLocked;
                            resultRecord2.dependencies = mutableObjectIntMap2;
                            resultRecord2.resultHash = resultRecord2.readableHash(this, currentSnapshot);
                            resultRecord2.result = observe;
                        }
                        return resultRecord2;
                    }
                    resultRecord2.dependencies = mutableObjectIntMap2;
                    resultRecord2.resultHash = resultRecord2.readableHash(this, currentSnapshot);
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            IntRef intRef3 = (IntRef) SnapshotStateKt__DerivedStateKt.calculationBlockNestedLevel.get();
            if (intRef3 != null && intRef3.element == 0) {
                SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                synchronized (obj) {
                    Snapshot currentSnapshot2 = SnapshotKt.currentSnapshot();
                    resultRecord2.validSnapshotId = currentSnapshot2.getId();
                    resultRecord2.validSnapshotWriteCount = currentSnapshot2.getWriteCount$runtime_release();
                }
            }
            return resultRecord2;
        } finally {
            int i17 = derivedStateObservers.size;
            if (i17 > 0) {
                Object[] objArr6 = derivedStateObservers.content;
                int i18 = 0;
                do {
                    ((DerivedStateObserver) objArr6[i18]).done();
                    i18++;
                } while (i18 < i17);
            }
        }
    }

    @Override // androidx.compose.runtime.DerivedState
    public final ResultRecord getCurrentRecord() {
        Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, currentSnapshot), currentSnapshot, false, this.calculation);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.first;
    }

    @Override // androidx.compose.runtime.DerivedState
    public final SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        Function1 readObserver = SnapshotKt.currentSnapshot().getReadObserver();
        if (readObserver != null) {
            readObserver.invoke(this);
        }
        Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
        return currentRecord((ResultRecord) SnapshotKt.current(this.first, currentSnapshot), currentSnapshot, true, this.calculation).result;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.first = (ResultRecord) stateRecord;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("DerivedState(value=");
        ResultRecord resultRecord = (ResultRecord) SnapshotKt.current(this.first);
        sb.append(resultRecord.isValid(this, SnapshotKt.currentSnapshot()) ? String.valueOf(resultRecord.result) : "<Not calculated>");
        sb.append(")@");
        sb.append(hashCode());
        return sb.toString();
    }
}
