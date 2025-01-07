package androidx.compose.runtime.snapshots;

import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedReadonlySnapshot extends Snapshot {
    public final Snapshot parent;
    public final Function1 readObserver;

    public NestedReadonlySnapshot(int i, SnapshotIdSet snapshotIdSet, Function1 function1, Snapshot snapshot) {
        super(i, snapshotIdSet);
        this.readObserver = function1;
        this.parent = snapshot;
        snapshot.nestedActivated$runtime_release();
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void dispose() {
        if (this.disposed) {
            return;
        }
        int i = this.id;
        Snapshot snapshot = this.parent;
        if (i != snapshot.getId()) {
            closeAndReleasePinning$runtime_release();
        }
        snapshot.nestedDeactivated$runtime_release();
        this.disposed = true;
        synchronized (SnapshotKt.lock) {
            int i2 = this.pinningTrackingHandle;
            if (i2 >= 0) {
                SnapshotKt.releasePinningLocked(i2);
                this.pinningTrackingHandle = -1;
            }
        }
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getReadObserver() {
        return this.readObserver;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final boolean getReadOnly() {
        return true;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Function1 getWriteObserver$runtime_release() {
        return null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void nestedActivated$runtime_release() {
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void nestedDeactivated$runtime_release() {
        SnapshotStateMapKt.unsupported();
        throw null;
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void recordModified$runtime_release(StateObject stateObject) {
        Function1 function1 = SnapshotKt.emptyLambda;
        throw new IllegalStateException("Cannot modify a state object in a read-only snapshot");
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final Snapshot takeNestedSnapshot(Function1 function1) {
        return new NestedReadonlySnapshot(this.id, this.invalid, SnapshotKt.mergedReadObserver(function1, this.readObserver, true), this.parent);
    }

    @Override // androidx.compose.runtime.snapshots.Snapshot
    public final void notifyObjectsInitialized$runtime_release() {
    }
}
