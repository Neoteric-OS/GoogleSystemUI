package androidx.compose.runtime.snapshots;

import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.internal.Thread_jvmKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class Snapshot {
    public static final Companion Companion = null;
    public boolean disposed;
    public int id;
    public SnapshotIdSet invalid;
    public int pinningTrackingHandle;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static Snapshot getCurrentThreadSnapshot() {
            return (Snapshot) SnapshotKt.threadSnapshot.get();
        }

        public static boolean isInSnapshot() {
            return SnapshotKt.threadSnapshot.get() != null;
        }

        public static Snapshot makeCurrentNonObservable(Snapshot snapshot) {
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot.threadId == Thread_jvmKt.currentThreadId()) {
                    transparentObserverMutableSnapshot.readObserver = null;
                    return snapshot;
                }
            }
            if (snapshot instanceof TransparentObserverSnapshot) {
                TransparentObserverSnapshot transparentObserverSnapshot = (TransparentObserverSnapshot) snapshot;
                if (transparentObserverSnapshot.threadId == Thread_jvmKt.currentThreadId()) {
                    transparentObserverSnapshot.readObserver = null;
                    return snapshot;
                }
            }
            Snapshot createTransparentSnapshotWithNoParentReadObserver = SnapshotKt.createTransparentSnapshotWithNoParentReadObserver(snapshot, null, false);
            createTransparentSnapshotWithNoParentReadObserver.makeCurrent();
            return createTransparentSnapshotWithNoParentReadObserver;
        }

        public static Object observe(Function0 function0, Function1 function1) {
            Snapshot transparentObserverMutableSnapshot;
            if (function1 == null) {
                return function0.invoke();
            }
            Snapshot snapshot = (Snapshot) SnapshotKt.threadSnapshot.get();
            if (snapshot instanceof TransparentObserverMutableSnapshot) {
                TransparentObserverMutableSnapshot transparentObserverMutableSnapshot2 = (TransparentObserverMutableSnapshot) snapshot;
                if (transparentObserverMutableSnapshot2.threadId == Thread_jvmKt.currentThreadId()) {
                    Function1 function12 = transparentObserverMutableSnapshot2.readObserver;
                    Function1 function13 = transparentObserverMutableSnapshot2.writeObserver;
                    try {
                        ((TransparentObserverMutableSnapshot) snapshot).readObserver = SnapshotKt.mergedReadObserver(function1, function12, true);
                        ((TransparentObserverMutableSnapshot) snapshot).writeObserver = SnapshotKt.access$mergedWriteObserver(null, function13);
                        return function0.invoke();
                    } finally {
                        transparentObserverMutableSnapshot2.readObserver = function12;
                        transparentObserverMutableSnapshot2.writeObserver = function13;
                    }
                }
            }
            if (snapshot == null || (snapshot instanceof MutableSnapshot)) {
                transparentObserverMutableSnapshot = new TransparentObserverMutableSnapshot(snapshot instanceof MutableSnapshot ? (MutableSnapshot) snapshot : null, function1, null, true, false);
            } else {
                if (function1 == null) {
                    return function0.invoke();
                }
                transparentObserverMutableSnapshot = snapshot.takeNestedSnapshot(function1);
            }
            try {
                Snapshot makeCurrent = transparentObserverMutableSnapshot.makeCurrent();
                try {
                    return function0.invoke();
                } finally {
                    Snapshot.restoreCurrent(makeCurrent);
                }
            } finally {
                transparentObserverMutableSnapshot.dispose();
            }
        }

        public static Snapshot$Companion$$ExternalSyntheticLambda0 registerApplyObserver(Function2 function2) {
            SnapshotKt.advanceGlobalSnapshot(SnapshotKt.emptyLambda);
            synchronized (SnapshotKt.lock) {
                SnapshotKt.applyObservers = CollectionsKt.plus(SnapshotKt.applyObservers, function2);
            }
            return new Snapshot$Companion$$ExternalSyntheticLambda0(function2);
        }

        public static void restoreNonObservable(Snapshot snapshot, Snapshot snapshot2, Function1 function1) {
            if (snapshot != snapshot2) {
                snapshot2.getClass();
                Snapshot.restoreCurrent(snapshot);
                snapshot2.dispose();
            } else if (snapshot instanceof TransparentObserverMutableSnapshot) {
                ((TransparentObserverMutableSnapshot) snapshot).readObserver = function1;
            } else if (snapshot instanceof TransparentObserverSnapshot) {
                ((TransparentObserverSnapshot) snapshot).readObserver = function1;
            } else {
                throw new IllegalStateException(("Non-transparent snapshot was reused: " + snapshot).toString());
            }
        }

        public static void sendApplyNotifications() {
            boolean z;
            synchronized (SnapshotKt.lock) {
                MutableScatterSet mutableScatterSet = ((GlobalSnapshot) SnapshotKt.currentGlobalSnapshot.get()).modified;
                z = false;
                if (mutableScatterSet != null) {
                    if (mutableScatterSet.isNotEmpty()) {
                        z = true;
                    }
                }
            }
            if (z) {
                SnapshotKt.advanceGlobalSnapshot(new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotKt$advanceGlobalSnapshot$3
                    @Override // kotlin.jvm.functions.Function1
                    public final /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                });
            }
        }

        public static MutableSnapshot takeMutableSnapshot(Function1 function1, Function1 function12) {
            MutableSnapshot takeNestedMutableSnapshot;
            Snapshot currentSnapshot = SnapshotKt.currentSnapshot();
            MutableSnapshot mutableSnapshot = currentSnapshot instanceof MutableSnapshot ? (MutableSnapshot) currentSnapshot : null;
            if (mutableSnapshot == null || (takeNestedMutableSnapshot = mutableSnapshot.takeNestedMutableSnapshot(function1, function12)) == null) {
                throw new IllegalStateException("Cannot create a mutable snapshot of an read-only snapshot");
            }
            return takeNestedMutableSnapshot;
        }
    }

    public Snapshot(int i, SnapshotIdSet snapshotIdSet) {
        int i2;
        int i3;
        int numberOfTrailingZeros;
        this.invalid = snapshotIdSet;
        this.id = i;
        if (i != 0) {
            SnapshotIdSet invalid$runtime_release = getInvalid$runtime_release();
            Function1 function1 = SnapshotKt.emptyLambda;
            int[] iArr = invalid$runtime_release.belowBound;
            if (iArr != null) {
                i = iArr[0];
            } else {
                long j = invalid$runtime_release.lowerSet;
                if (j != 0) {
                    i3 = invalid$runtime_release.lowerBound;
                    numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
                } else {
                    long j2 = invalid$runtime_release.upperSet;
                    if (j2 != 0) {
                        i3 = invalid$runtime_release.lowerBound + 64;
                        numberOfTrailingZeros = Long.numberOfTrailingZeros(j2);
                    }
                }
                i = i3 + numberOfTrailingZeros;
            }
            synchronized (SnapshotKt.lock) {
                i2 = SnapshotKt.pinningTable.add(i);
            }
        } else {
            i2 = -1;
        }
        this.pinningTrackingHandle = i2;
    }

    public static void restoreCurrent(Snapshot snapshot) {
        SnapshotKt.threadSnapshot.set(snapshot);
    }

    public final void closeAndReleasePinning$runtime_release() {
        synchronized (SnapshotKt.lock) {
            closeLocked$runtime_release();
            releasePinnedSnapshotsForCloseLocked$runtime_release();
        }
    }

    public void closeLocked$runtime_release() {
        SnapshotKt.openSnapshots = SnapshotKt.openSnapshots.clear(getId());
    }

    public abstract void dispose();

    public int getId() {
        return this.id;
    }

    public SnapshotIdSet getInvalid$runtime_release() {
        return this.invalid;
    }

    public abstract Function1 getReadObserver();

    public abstract boolean getReadOnly();

    public int getWriteCount$runtime_release() {
        return 0;
    }

    public abstract Function1 getWriteObserver$runtime_release();

    public final Snapshot makeCurrent() {
        SnapshotThreadLocal snapshotThreadLocal = SnapshotKt.threadSnapshot;
        Snapshot snapshot = (Snapshot) snapshotThreadLocal.get();
        snapshotThreadLocal.set(this);
        return snapshot;
    }

    public abstract void nestedActivated$runtime_release();

    public abstract void nestedDeactivated$runtime_release();

    public abstract void notifyObjectsInitialized$runtime_release();

    public abstract void recordModified$runtime_release(StateObject stateObject);

    public void releasePinnedSnapshotsForCloseLocked$runtime_release() {
        int i = this.pinningTrackingHandle;
        if (i >= 0) {
            SnapshotKt.releasePinningLocked(i);
            this.pinningTrackingHandle = -1;
        }
    }

    public void setId$runtime_release(int i) {
        this.id = i;
    }

    public void setInvalid$runtime_release(SnapshotIdSet snapshotIdSet) {
        this.invalid = snapshotIdSet;
    }

    public void setWriteCount$runtime_release(int i) {
        throw new IllegalStateException("Updating write count is not supported for this snapshot");
    }

    public abstract Snapshot takeNestedSnapshot(Function1 function1);
}
