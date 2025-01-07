package androidx.compose.runtime;

import android.util.Log;
import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.Recomposer;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.PersistentHashMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableMap.TrieNode;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.Links;
import androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.persistentOrderedSet.PersistentOrderedSet;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.EndOfChain;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.SnapshotThreadLocal;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotApplyResult;
import androidx.compose.runtime.snapshots.SnapshotKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Unit;
import kotlin.collections.EmptyList;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlowImpl;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Recomposer extends CompositionContext {
    public static final Companion Companion = null;
    public final List _knownCompositions;
    public List _knownCompositionsCache;
    public final StateFlowImpl _state;
    public final BroadcastFrameClock broadcastFrameClock;
    public Throwable closeCause;
    public final MutableVector compositionInvalidations;
    public final Map compositionValueStatesAvailable;
    public final List compositionValuesAwaitingInsert;
    public final Map compositionValuesRemoved;
    public final List compositionsAwaitingApply;
    public Set compositionsRemoved;
    public final CoroutineContext effectCoroutineContext;
    public final JobImpl effectJob;
    public RecomposerErrorState errorState;
    public List failedCompositions;
    public boolean frameClockPaused;
    public final RecomposerInfoImpl recomposerInfo;
    public Job runnerJob;
    public MutableScatterSet snapshotInvalidations;
    public final Object stateLock;
    public CancellableContinuationImpl workContinuation;
    public static final StateFlowImpl _runningRecomposers = StateFlowKt.MutableStateFlow(PersistentOrderedSet.EMPTY);
    public static final AtomicReference _hotReloadEnabled = new AtomicReference(Boolean.FALSE);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
        public static final void access$removeRunning(RecomposerInfoImpl recomposerInfoImpl) {
            StateFlowImpl stateFlowImpl;
            PersistentSet persistentSet;
            PersistentOrderedSet persistentOrderedSet;
            Companion companion = Recomposer.Companion;
            do {
                stateFlowImpl = Recomposer._runningRecomposers;
                persistentSet = (PersistentSet) stateFlowImpl.getValue();
                persistentOrderedSet = (PersistentOrderedSet) persistentSet;
                Links links = (Links) persistentOrderedSet.hashMap.get(recomposerInfoImpl);
                if (links != null) {
                    PersistentHashMap persistentHashMap = persistentOrderedSet.hashMap;
                    TrieNode remove = persistentHashMap.node.remove(recomposerInfoImpl != null ? recomposerInfoImpl.hashCode() : 0, 0, recomposerInfoImpl);
                    if (persistentHashMap.node != remove) {
                        persistentHashMap = remove == null ? PersistentHashMap.EMPTY : new PersistentHashMap(remove, persistentHashMap.size - 1);
                    }
                    EndOfChain endOfChain = EndOfChain.INSTANCE;
                    Object obj = links.previous;
                    boolean z = obj != endOfChain;
                    Object obj2 = links.next;
                    if (z) {
                        Object obj3 = persistentHashMap.get(obj);
                        Intrinsics.checkNotNull(obj3);
                        persistentHashMap = persistentHashMap.put(obj, new Links(((Links) obj3).previous, obj2));
                    }
                    if (obj2 != endOfChain) {
                        Object obj4 = persistentHashMap.get(obj2);
                        Intrinsics.checkNotNull(obj4);
                        persistentHashMap = persistentHashMap.put(obj2, new Links(obj, ((Links) obj4).next));
                    }
                    Object obj5 = obj != endOfChain ? persistentOrderedSet.firstElement : obj2;
                    if (obj2 != endOfChain) {
                        obj = persistentOrderedSet.lastElement;
                    }
                    persistentOrderedSet = new PersistentOrderedSet(obj5, obj, persistentHashMap);
                }
                if (persistentSet == persistentOrderedSet) {
                    return;
                }
            } while (!stateFlowImpl.compareAndSet(persistentSet, persistentOrderedSet));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class RecomposerErrorState {
        public final Exception cause;

        public RecomposerErrorState(Exception exc) {
            this.cause = exc;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class RecomposerInfoImpl {
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class State {
        public static final /* synthetic */ State[] $VALUES;
        public static final State Idle;
        public static final State Inactive;
        public static final State InactivePendingWork;
        public static final State PendingWork;
        public static final State ShutDown;
        public static final State ShuttingDown;

        static {
            State state = new State("ShutDown", 0);
            ShutDown = state;
            State state2 = new State("ShuttingDown", 1);
            ShuttingDown = state2;
            State state3 = new State("Inactive", 2);
            Inactive = state3;
            State state4 = new State("InactivePendingWork", 3);
            InactivePendingWork = state4;
            State state5 = new State("Idle", 4);
            Idle = state5;
            State state6 = new State("PendingWork", 5);
            PendingWork = state6;
            $VALUES = new State[]{state, state2, state3, state4, state5, state6};
        }

        public static State valueOf(String str) {
            return (State) Enum.valueOf(State.class, str);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    public Recomposer(CoroutineContext coroutineContext) {
        BroadcastFrameClock broadcastFrameClock = new BroadcastFrameClock(new Recomposer$broadcastFrameClock$1(this));
        this.broadcastFrameClock = broadcastFrameClock;
        this.stateLock = new Object();
        this._knownCompositions = new ArrayList();
        this.snapshotInvalidations = new MutableScatterSet();
        this.compositionInvalidations = new MutableVector(new ControlledComposition[16]);
        this.compositionsAwaitingApply = new ArrayList();
        this.compositionValuesAwaitingInsert = new ArrayList();
        this.compositionValuesRemoved = new LinkedHashMap();
        this.compositionValueStatesAvailable = new LinkedHashMap();
        this._state = StateFlowKt.MutableStateFlow(State.Inactive);
        new SnapshotThreadLocal();
        JobImpl jobImpl = new JobImpl((Job) coroutineContext.get(Job.Key.$$INSTANCE));
        jobImpl.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                final Throwable th = (Throwable) obj;
                CancellationException CancellationException = ExceptionsKt.CancellationException("Recomposer effect job completed", th);
                final Recomposer recomposer = Recomposer.this;
                synchronized (recomposer.stateLock) {
                    try {
                        Job job = recomposer.runnerJob;
                        if (job != null) {
                            StateFlowImpl stateFlowImpl = recomposer._state;
                            Recomposer.State state = Recomposer.State.ShuttingDown;
                            stateFlowImpl.getClass();
                            stateFlowImpl.updateState(null, state);
                            Recomposer.Companion companion = Recomposer.Companion;
                            job.cancel(CancellationException);
                            recomposer.workContinuation = null;
                            job.invokeOnCompletion(new Function1() { // from class: androidx.compose.runtime.Recomposer$effectJob$1$1$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public final Object invoke(Object obj2) {
                                    Throwable th2 = (Throwable) obj2;
                                    Recomposer recomposer2 = Recomposer.this;
                                    Object obj3 = recomposer2.stateLock;
                                    Throwable th3 = th;
                                    synchronized (obj3) {
                                        if (th3 == null) {
                                            th3 = null;
                                        } else if (th2 != null) {
                                            try {
                                                if (th2 instanceof CancellationException) {
                                                    th2 = null;
                                                }
                                                if (th2 != null) {
                                                    kotlin.ExceptionsKt.addSuppressed(th3, th2);
                                                }
                                            } catch (Throwable th4) {
                                                throw th4;
                                            }
                                        }
                                        recomposer2.closeCause = th3;
                                        StateFlowImpl stateFlowImpl2 = recomposer2._state;
                                        Recomposer.State state2 = Recomposer.State.ShutDown;
                                        stateFlowImpl2.getClass();
                                        stateFlowImpl2.updateState(null, state2);
                                    }
                                    return Unit.INSTANCE;
                                }
                            });
                        } else {
                            recomposer.closeCause = CancellationException;
                            StateFlowImpl stateFlowImpl2 = recomposer._state;
                            Recomposer.State state2 = Recomposer.State.ShutDown;
                            stateFlowImpl2.getClass();
                            stateFlowImpl2.updateState(null, state2);
                        }
                    } catch (Throwable th2) {
                        throw th2;
                    }
                }
                return Unit.INSTANCE;
            }
        });
        this.effectJob = jobImpl;
        this.effectCoroutineContext = coroutineContext.plus(broadcastFrameClock).plus(jobImpl);
        this.recomposerInfo = new RecomposerInfoImpl();
    }

    public static final ControlledComposition access$performRecompose(Recomposer recomposer, final ControlledComposition controlledComposition, final MutableScatterSet mutableScatterSet) {
        recomposer.getClass();
        CompositionImpl compositionImpl = (CompositionImpl) controlledComposition;
        if (compositionImpl.composer.isComposing || compositionImpl.disposed) {
            return null;
        }
        Set set = recomposer.compositionsRemoved;
        if (set != null && set.contains(controlledComposition)) {
            return null;
        }
        MutableSnapshot takeMutableSnapshot = Snapshot.Companion.takeMutableSnapshot(new Recomposer$readObserverOf$1(controlledComposition), new Recomposer$writeObserverOf$1(mutableScatterSet, controlledComposition));
        try {
            Snapshot makeCurrent = takeMutableSnapshot.makeCurrent();
            if (mutableScatterSet != null) {
                try {
                    if (mutableScatterSet.isNotEmpty()) {
                        Function0 function0 = new Function0() { // from class: androidx.compose.runtime.Recomposer$performRecompose$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                MutableScatterSet mutableScatterSet2 = MutableScatterSet.this;
                                ControlledComposition controlledComposition2 = controlledComposition;
                                Object[] objArr = mutableScatterSet2.elements;
                                long[] jArr = mutableScatterSet2.metadata;
                                int length = jArr.length - 2;
                                if (length >= 0) {
                                    int i = 0;
                                    while (true) {
                                        long j = jArr[i];
                                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i2 = 8 - ((~(i - length)) >>> 31);
                                            for (int i3 = 0; i3 < i2; i3++) {
                                                if ((255 & j) < 128) {
                                                    ((CompositionImpl) controlledComposition2).recordWriteOf(objArr[(i << 3) + i3]);
                                                }
                                                j >>= 8;
                                            }
                                            if (i2 != 8) {
                                                break;
                                            }
                                        }
                                        if (i == length) {
                                            break;
                                        }
                                        i++;
                                    }
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        ComposerImpl composerImpl = ((CompositionImpl) controlledComposition).composer;
                        if (composerImpl.isComposing) {
                            ComposerKt.composeImmediateRuntimeError("Preparing a composition while composing is not supported");
                        }
                        composerImpl.isComposing = true;
                        try {
                            function0.invoke();
                            composerImpl.isComposing = false;
                        } catch (Throwable th) {
                            composerImpl.isComposing = false;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    Snapshot.restoreCurrent(makeCurrent);
                    throw th2;
                }
            }
            boolean recompose = ((CompositionImpl) controlledComposition).recompose();
            Snapshot.restoreCurrent(makeCurrent);
            if (!recompose) {
                controlledComposition = null;
            }
            return controlledComposition;
        } finally {
            applyAndCheck(takeMutableSnapshot);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0018, code lost:
    
        if (r8.getHasBroadcastFrameClockAwaitersLocked() != false) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x007a, code lost:
    
        if (r8.getHasBroadcastFrameClockAwaitersLocked() != false) goto L40;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean access$recordComposerModifications(androidx.compose.runtime.Recomposer r8) {
        /*
            java.lang.Object r0 = r8.stateLock
            monitor-enter(r0)
            androidx.collection.MutableScatterSet r1 = r8.snapshotInvalidations     // Catch: java.lang.Throwable -> Lb1
            boolean r1 = r1.isEmpty()     // Catch: java.lang.Throwable -> Lb1
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1d
            androidx.compose.runtime.collection.MutableVector r1 = r8.compositionInvalidations     // Catch: java.lang.Throwable -> Lb1
            int r1 = r1.size     // Catch: java.lang.Throwable -> Lb1
            if (r1 == 0) goto L14
            goto L1a
        L14:
            boolean r8 = r8.getHasBroadcastFrameClockAwaitersLocked()     // Catch: java.lang.Throwable -> Lb1
            if (r8 == 0) goto L1b
        L1a:
            r2 = r3
        L1b:
            monitor-exit(r0)
            goto L7e
        L1d:
            androidx.collection.MutableScatterSet r1 = r8.snapshotInvalidations     // Catch: java.lang.Throwable -> Lb1
            androidx.compose.runtime.collection.ScatterSetWrapper r4 = new androidx.compose.runtime.collection.ScatterSetWrapper     // Catch: java.lang.Throwable -> Lb1
            r4.<init>(r1)     // Catch: java.lang.Throwable -> Lb1
            androidx.collection.MutableScatterSet r1 = new androidx.collection.MutableScatterSet     // Catch: java.lang.Throwable -> Lb1
            r1.<init>()     // Catch: java.lang.Throwable -> Lb1
            r8.snapshotInvalidations = r1     // Catch: java.lang.Throwable -> Lb1
            monitor-exit(r0)
            java.lang.Object r0 = r8.stateLock
            monitor-enter(r0)
            java.util.List r1 = r8.getKnownCompositions()     // Catch: java.lang.Throwable -> Lae
            monitor-exit(r0)
            int r0 = r1.size()     // Catch: java.lang.Throwable -> L59
            r5 = r2
        L39:
            if (r5 >= r0) goto L5b
            java.lang.Object r6 = r1.get(r5)     // Catch: java.lang.Throwable -> L59
            androidx.compose.runtime.ControlledComposition r6 = (androidx.compose.runtime.ControlledComposition) r6     // Catch: java.lang.Throwable -> L59
            androidx.compose.runtime.CompositionImpl r6 = (androidx.compose.runtime.CompositionImpl) r6     // Catch: java.lang.Throwable -> L59
            r6.recordModificationsOf(r4)     // Catch: java.lang.Throwable -> L59
            kotlinx.coroutines.flow.StateFlowImpl r6 = r8._state     // Catch: java.lang.Throwable -> L59
            java.lang.Object r6 = r6.getValue()     // Catch: java.lang.Throwable -> L59
            androidx.compose.runtime.Recomposer$State r6 = (androidx.compose.runtime.Recomposer.State) r6     // Catch: java.lang.Throwable -> L59
            androidx.compose.runtime.Recomposer$State r7 = androidx.compose.runtime.Recomposer.State.ShuttingDown     // Catch: java.lang.Throwable -> L59
            int r6 = r6.compareTo(r7)     // Catch: java.lang.Throwable -> L59
            if (r6 <= 0) goto L5b
            int r5 = r5 + 1
            goto L39
        L59:
            r0 = move-exception
            goto L8d
        L5b:
            java.lang.Object r0 = r8.stateLock     // Catch: java.lang.Throwable -> L59
            monitor-enter(r0)     // Catch: java.lang.Throwable -> L59
            androidx.collection.MutableScatterSet r1 = new androidx.collection.MutableScatterSet     // Catch: java.lang.Throwable -> L8a
            r1.<init>()     // Catch: java.lang.Throwable -> L8a
            r8.snapshotInvalidations = r1     // Catch: java.lang.Throwable -> L8a
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L59
            java.lang.Object r0 = r8.stateLock
            monitor-enter(r0)
            kotlinx.coroutines.CancellableContinuation r1 = r8.deriveStateLocked()     // Catch: java.lang.Throwable -> L87
            if (r1 != 0) goto L7f
            androidx.compose.runtime.collection.MutableVector r1 = r8.compositionInvalidations     // Catch: java.lang.Throwable -> L87
            int r1 = r1.size     // Catch: java.lang.Throwable -> L87
            if (r1 == 0) goto L76
            goto L7c
        L76:
            boolean r8 = r8.getHasBroadcastFrameClockAwaitersLocked()     // Catch: java.lang.Throwable -> L87
            if (r8 == 0) goto L7d
        L7c:
            r2 = r3
        L7d:
            monitor-exit(r0)
        L7e:
            return r2
        L7f:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = "called outside of runRecomposeAndApplyChanges"
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L87
            throw r8     // Catch: java.lang.Throwable -> L87
        L87:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        L8a:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L59
            throw r1     // Catch: java.lang.Throwable -> L59
        L8d:
            java.lang.Object r1 = r8.stateLock
            monitor-enter(r1)
            androidx.collection.MutableScatterSet r8 = r8.snapshotInvalidations     // Catch: java.lang.Throwable -> Lab
            int r2 = r8._size     // Catch: java.lang.Throwable -> Lab
            java.util.Iterator r2 = r4.iterator()     // Catch: java.lang.Throwable -> Lab
        L98:
            r3 = r2
            kotlin.sequences.SequenceBuilderIterator r3 = (kotlin.sequences.SequenceBuilderIterator) r3     // Catch: java.lang.Throwable -> Lab
            boolean r4 = r3.hasNext()     // Catch: java.lang.Throwable -> Lab
            if (r4 == 0) goto La9
            java.lang.Object r3 = r3.next()     // Catch: java.lang.Throwable -> Lab
            r8.plusAssign(r3)     // Catch: java.lang.Throwable -> Lab
            goto L98
        La9:
            monitor-exit(r1)
            throw r0
        Lab:
            r8 = move-exception
            monitor-exit(r1)
            throw r8
        Lae:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        Lb1:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.access$recordComposerModifications(androidx.compose.runtime.Recomposer):boolean");
    }

    public static void applyAndCheck(MutableSnapshot mutableSnapshot) {
        try {
            if (mutableSnapshot.apply() instanceof SnapshotApplyResult.Failure) {
                throw new IllegalStateException("Unsupported concurrent change during composition. A state object was modified by composition as well as being modified outside composition.");
            }
        } finally {
            mutableSnapshot.dispose();
        }
    }

    public static final void performInitialMovableContentInserts$fillToInsert(List list, Recomposer recomposer, CompositionImpl compositionImpl) {
        list.clear();
        synchronized (recomposer.stateLock) {
            Iterator it = recomposer.compositionValuesAwaitingInsert.iterator();
            while (it.hasNext()) {
                MovableContentStateReference movableContentStateReference = (MovableContentStateReference) it.next();
                if (movableContentStateReference.composition.equals(compositionImpl)) {
                    list.add(movableContentStateReference);
                    it.remove();
                }
            }
        }
    }

    public final void cancel() {
        synchronized (this.stateLock) {
            if (((State) this._state.getValue()).compareTo(State.Idle) >= 0) {
                StateFlowImpl stateFlowImpl = this._state;
                State state = State.ShuttingDown;
                stateFlowImpl.getClass();
                stateFlowImpl.updateState(null, state);
            }
        }
        this.effectJob.cancel(null);
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void composeInitial$runtime_release(CompositionImpl compositionImpl, ComposableLambdaImpl composableLambdaImpl) {
        boolean z = compositionImpl.composer.isComposing;
        try {
            MutableSnapshot takeMutableSnapshot = Snapshot.Companion.takeMutableSnapshot(new Recomposer$readObserverOf$1(compositionImpl), new Recomposer$writeObserverOf$1(null, compositionImpl));
            try {
                Snapshot makeCurrent = takeMutableSnapshot.makeCurrent();
                try {
                    compositionImpl.composeContent(composableLambdaImpl);
                    if (!z) {
                        SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                    }
                    synchronized (this.stateLock) {
                        if (((State) this._state.getValue()).compareTo(State.ShuttingDown) > 0 && !getKnownCompositions().contains(compositionImpl)) {
                            this._knownCompositions.add(compositionImpl);
                            this._knownCompositionsCache = null;
                        }
                    }
                    try {
                        performInitialMovableContentInserts(compositionImpl);
                        try {
                            compositionImpl.applyChanges();
                            compositionImpl.applyLateChanges();
                            if (z) {
                                return;
                            }
                            SnapshotKt.currentSnapshot().notifyObjectsInitialized$runtime_release();
                        } catch (Exception e) {
                            processCompositionError(e, null);
                        }
                    } catch (Exception e2) {
                        processCompositionError(e2, compositionImpl);
                    }
                } finally {
                    Snapshot.restoreCurrent(makeCurrent);
                }
            } finally {
                applyAndCheck(takeMutableSnapshot);
            }
        } catch (Exception e3) {
            processCompositionError(e3, compositionImpl);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void deletedMovableContent$runtime_release(MovableContentStateReference movableContentStateReference) {
        synchronized (this.stateLock) {
            Map map = this.compositionValuesRemoved;
            MovableContent movableContent = movableContentStateReference.content;
            Object obj = map.get(movableContent);
            if (obj == null) {
                obj = new ArrayList();
                map.put(movableContent, obj);
            }
            ((List) obj).add(movableContentStateReference);
        }
    }

    public final CancellableContinuation deriveStateLocked() {
        StateFlowImpl stateFlowImpl = this._state;
        int compareTo = ((State) stateFlowImpl.getValue()).compareTo(State.ShuttingDown);
        MutableVector mutableVector = this.compositionInvalidations;
        if (compareTo <= 0) {
            this._knownCompositions.clear();
            this._knownCompositionsCache = EmptyList.INSTANCE;
            this.snapshotInvalidations = new MutableScatterSet();
            mutableVector.clear();
            this.compositionsAwaitingApply.clear();
            this.compositionValuesAwaitingInsert.clear();
            this.failedCompositions = null;
            CancellableContinuationImpl cancellableContinuationImpl = this.workContinuation;
            if (cancellableContinuationImpl != null) {
                cancellableContinuationImpl.cancel(null);
            }
            this.workContinuation = null;
            this.errorState = null;
            return null;
        }
        RecomposerErrorState recomposerErrorState = this.errorState;
        State state = State.PendingWork;
        State state2 = State.Inactive;
        if (recomposerErrorState == null) {
            if (this.runnerJob == null) {
                this.snapshotInvalidations = new MutableScatterSet();
                mutableVector.clear();
                if (getHasBroadcastFrameClockAwaitersLocked()) {
                    state2 = State.InactivePendingWork;
                }
            } else {
                state2 = (mutableVector.size == 0 && !this.snapshotInvalidations.isNotEmpty() && this.compositionsAwaitingApply.isEmpty() && this.compositionValuesAwaitingInsert.isEmpty() && !getHasBroadcastFrameClockAwaitersLocked()) ? State.Idle : state;
            }
        }
        stateFlowImpl.getClass();
        stateFlowImpl.updateState(null, state2);
        if (state2 != state) {
            return null;
        }
        CancellableContinuationImpl cancellableContinuationImpl2 = this.workContinuation;
        this.workContinuation = null;
        return cancellableContinuationImpl2;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final boolean getCollectingCallByInformation$runtime_release() {
        return ((Boolean) _hotReloadEnabled.get()).booleanValue();
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final boolean getCollectingParameterInformation$runtime_release() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final boolean getCollectingSourceInformation$runtime_release() {
        return false;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final int getCompoundHashKey$runtime_release() {
        return 1000;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final CoroutineContext getEffectCoroutineContext() {
        return this.effectCoroutineContext;
    }

    public final boolean getHasBroadcastFrameClockAwaitersLocked() {
        return (this.frameClockPaused || this.broadcastFrameClock.hasAwaitersUnlocked.get() == 0) ? false : true;
    }

    public final boolean getHasSchedulingWork() {
        boolean z;
        synchronized (this.stateLock) {
            if (!this.snapshotInvalidations.isNotEmpty() && this.compositionInvalidations.size == 0) {
                z = getHasBroadcastFrameClockAwaitersLocked();
            }
        }
        return z;
    }

    public final List getKnownCompositions() {
        List list = this._knownCompositionsCache;
        if (list == null) {
            List list2 = this._knownCompositions;
            list = list2.isEmpty() ? EmptyList.INSTANCE : new ArrayList(list2);
            this._knownCompositionsCache = list;
        }
        return list;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void insertMovableContent$runtime_release(MovableContentStateReference movableContentStateReference) {
        CancellableContinuation deriveStateLocked;
        synchronized (this.stateLock) {
            this.compositionValuesAwaitingInsert.add(movableContentStateReference);
            deriveStateLocked = deriveStateLocked();
        }
        if (deriveStateLocked != null) {
            ((CancellableContinuationImpl) deriveStateLocked).resumeWith(Unit.INSTANCE);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void invalidate$runtime_release(CompositionImpl compositionImpl) {
        CancellableContinuation cancellableContinuation;
        synchronized (this.stateLock) {
            if (this.compositionInvalidations.contains(compositionImpl)) {
                cancellableContinuation = null;
            } else {
                this.compositionInvalidations.add(compositionImpl);
                cancellableContinuation = deriveStateLocked();
            }
        }
        if (cancellableContinuation != null) {
            ((CancellableContinuationImpl) cancellableContinuation).resumeWith(Unit.INSTANCE);
        }
    }

    public final Object join(Continuation continuation) {
        Object first = FlowKt.first(this._state, new Recomposer$join$2(2, null), continuation);
        return first == CoroutineSingletons.COROUTINE_SUSPENDED ? first : Unit.INSTANCE;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void movableContentStateReleased$runtime_release(MovableContentStateReference movableContentStateReference, MovableContentState movableContentState) {
        synchronized (this.stateLock) {
            this.compositionValueStatesAvailable.put(movableContentStateReference, movableContentState);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference movableContentStateReference) {
        MovableContentState movableContentState;
        synchronized (this.stateLock) {
            movableContentState = (MovableContentState) this.compositionValueStatesAvailable.remove(movableContentStateReference);
        }
        return movableContentState;
    }

    public final void performInitialMovableContentInserts(CompositionImpl compositionImpl) {
        synchronized (this.stateLock) {
            ArrayList arrayList = (ArrayList) this.compositionValuesAwaitingInsert;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (((MovableContentStateReference) arrayList.get(i)).composition.equals(compositionImpl)) {
                    ArrayList arrayList2 = new ArrayList();
                    performInitialMovableContentInserts$fillToInsert(arrayList2, this, compositionImpl);
                    while (!arrayList2.isEmpty()) {
                        performInsertValues(arrayList2, null);
                        performInitialMovableContentInserts$fillToInsert(arrayList2, this, compositionImpl);
                    }
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00e9, code lost:
    
        r3 = r10.size();
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ee, code lost:
    
        if (r4 >= r3) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00fa, code lost:
    
        if (((kotlin.Pair) r10.get(r4)).getSecond() == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00fc, code lost:
    
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ff, code lost:
    
        r3 = new java.util.ArrayList(r10.size());
        r4 = r10.size();
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x010d, code lost:
    
        if (r9 >= r4) goto L97;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x010f, code lost:
    
        r11 = (kotlin.Pair) r10.get(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0119, code lost:
    
        if (r11.getSecond() != null) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x011b, code lost:
    
        r11 = (androidx.compose.runtime.MovableContentStateReference) r11.getFirst();
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0125, code lost:
    
        if (r11 == null) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0127, code lost:
    
        r3.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x012a, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0124, code lost:
    
        r11 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x012d, code lost:
    
        r4 = r18.stateLock;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x012f, code lost:
    
        monitor-enter(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0130, code lost:
    
        kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r3, r18.compositionValuesAwaitingInsert);
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0135, code lost:
    
        monitor-exit(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0136, code lost:
    
        r3 = new java.util.ArrayList(r10.size());
        r4 = r10.size();
        r9 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0144, code lost:
    
        if (r9 >= r4) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0146, code lost:
    
        r11 = r10.get(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x0151, code lost:
    
        if (((kotlin.Pair) r11).getSecond() == null) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0153, code lost:
    
        r3.add(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0156, code lost:
    
        r9 = r9 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0159, code lost:
    
        r10 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List performInsertValues(java.util.List r19, androidx.collection.MutableScatterSet r20) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.Recomposer.performInsertValues(java.util.List, androidx.collection.MutableScatterSet):java.util.List");
    }

    public final void processCompositionError(Exception exc, CompositionImpl compositionImpl) {
        if (!((Boolean) _hotReloadEnabled.get()).booleanValue() || (exc instanceof ComposeRuntimeError)) {
            synchronized (this.stateLock) {
                RecomposerErrorState recomposerErrorState = this.errorState;
                if (recomposerErrorState != null) {
                    throw recomposerErrorState.cause;
                }
                this.errorState = new RecomposerErrorState(exc);
            }
            throw exc;
        }
        synchronized (this.stateLock) {
            Log.e("ComposeInternal", "Error was captured in composition while live edit was enabled.", exc);
            this.compositionsAwaitingApply.clear();
            this.compositionInvalidations.clear();
            this.snapshotInvalidations = new MutableScatterSet();
            this.compositionValuesAwaitingInsert.clear();
            this.compositionValuesRemoved.clear();
            this.compositionValueStatesAvailable.clear();
            this.errorState = new RecomposerErrorState(exc);
            if (compositionImpl != null) {
                recordFailedCompositionLocked(compositionImpl);
            }
            deriveStateLocked();
        }
    }

    public final void recordFailedCompositionLocked(CompositionImpl compositionImpl) {
        List list = this.failedCompositions;
        if (list == null) {
            list = new ArrayList();
            this.failedCompositions = list;
        }
        if (!list.contains(compositionImpl)) {
            list.add(compositionImpl);
        }
        this._knownCompositions.remove(compositionImpl);
        this._knownCompositionsCache = null;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void reportRemovedComposition$runtime_release(CompositionImpl compositionImpl) {
        synchronized (this.stateLock) {
            try {
                Set set = this.compositionsRemoved;
                if (set == null) {
                    set = new LinkedHashSet();
                    this.compositionsRemoved = set;
                }
                set.add(compositionImpl);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final Object runRecomposeAndApplyChanges(Continuation continuation) {
        Object withContext = BuildersKt.withContext(this.broadcastFrameClock, new Recomposer$recompositionRunner$2(this, new Recomposer$runRecomposeAndApplyChanges$2(this, null), MonotonicFrameClockKt.getMonotonicFrameClock(((ContinuationImpl) continuation).getContext()), null), continuation);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        Unit unit = Unit.INSTANCE;
        if (withContext != coroutineSingletons) {
            withContext = unit;
        }
        return withContext == coroutineSingletons ? withContext : unit;
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void unregisterComposition$runtime_release(CompositionImpl compositionImpl) {
        synchronized (this.stateLock) {
            this._knownCompositions.remove(compositionImpl);
            this._knownCompositionsCache = null;
            this.compositionInvalidations.remove(compositionImpl);
            this.compositionsAwaitingApply.remove(compositionImpl);
        }
    }

    @Override // androidx.compose.runtime.CompositionContext
    public final void recordInspectionTable$runtime_release(Set set) {
    }
}
