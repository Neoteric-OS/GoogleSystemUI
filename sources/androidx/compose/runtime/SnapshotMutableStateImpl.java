package androidx.compose.runtime;

import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.snapshots.StateRecord;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SnapshotMutableStateImpl extends StateObjectImpl implements SnapshotMutableState {
    public StateStateRecord next;
    public final SnapshotMutationPolicy policy;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class StateStateRecord extends StateRecord {
        public Object value;

        public StateStateRecord(Object obj) {
            this.value = obj;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final void assign(StateRecord stateRecord) {
            this.value = ((StateStateRecord) stateRecord).value;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public final StateRecord create() {
            return new StateStateRecord(this.value);
        }
    }

    public SnapshotMutableStateImpl(Object obj, SnapshotMutationPolicy snapshotMutationPolicy) {
        this.policy = snapshotMutationPolicy;
        StateStateRecord stateStateRecord = new StateStateRecord(obj);
        if (Snapshot.Companion.isInSnapshot()) {
            StateStateRecord stateStateRecord2 = new StateStateRecord(obj);
            stateStateRecord2.snapshotId = 1;
            stateStateRecord.next = stateStateRecord2;
        }
        this.next = stateStateRecord;
    }

    @Override // androidx.compose.runtime.MutableState
    public final Object component1() {
        return getValue();
    }

    @Override // androidx.compose.runtime.MutableState
    public final Function1 component2() {
        return new Function1() { // from class: androidx.compose.runtime.SnapshotMutableStateImpl$component2$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                SnapshotMutableStateImpl.this.setValue(obj);
                return Unit.INSTANCE;
            }
        };
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord getFirstStateRecord() {
        return this.next;
    }

    @Override // androidx.compose.runtime.snapshots.SnapshotMutableState
    public final SnapshotMutationPolicy getPolicy() {
        return this.policy;
    }

    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return ((StateStateRecord) SnapshotKt.readable(this.next, this)).value;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final StateRecord mergeRecords(StateRecord stateRecord, StateRecord stateRecord2, StateRecord stateRecord3) {
        if (this.policy.equivalent(((StateStateRecord) stateRecord2).value, ((StateStateRecord) stateRecord3).value)) {
            return stateRecord2;
        }
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public final void prependStateRecord(StateRecord stateRecord) {
        this.next = (StateStateRecord) stateRecord;
    }

    @Override // androidx.compose.runtime.MutableState
    public final void setValue(Object obj) {
        Snapshot currentSnapshot;
        StateStateRecord stateStateRecord = (StateStateRecord) SnapshotKt.current(this.next);
        if (this.policy.equivalent(stateStateRecord.value, obj)) {
            return;
        }
        StateStateRecord stateStateRecord2 = this.next;
        synchronized (SnapshotKt.lock) {
            currentSnapshot = SnapshotKt.currentSnapshot();
            ((StateStateRecord) SnapshotKt.overwritableRecord(stateStateRecord2, this, currentSnapshot, stateStateRecord)).value = obj;
        }
        SnapshotKt.notifyWrite(currentSnapshot, this);
    }

    public final String toString() {
        return "MutableState(value=" + ((StateStateRecord) SnapshotKt.current(this.next)).value + ")@" + hashCode();
    }
}
