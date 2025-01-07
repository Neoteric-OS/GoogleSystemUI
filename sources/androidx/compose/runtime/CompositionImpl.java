package androidx.compose.runtime;

import android.os.Trace;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.MutableScatterSet.MutableSetWrapper;
import androidx.collection.ScatterSet;
import androidx.compose.runtime.DerivedSnapshotState;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.ComposableLambdaImpl;
import androidx.compose.runtime.internal.RememberEventDispatcher;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Pair;
import kotlin.collections.EmptySet;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner {
    public final MutableScatterSet.MutableSetWrapper abandonSet;
    public final AbstractApplier applier;
    public final ChangeList changes;
    public ComposableLambdaImpl composable;
    public final ComposerImpl composer;
    public final MutableScatterSet conditionallyInvalidatedScopes;
    public final MutableScatterMap derivedStates;
    public boolean disposed;
    public final MutableScatterSet invalidatedScopes;
    public CompositionImpl invalidationDelegate;
    public int invalidationDelegateGroup;
    public MutableScatterMap invalidations;
    public final ChangeList lateChanges;
    public final MutableScatterMap observations;
    public final MutableScatterMap observationsProcessed;
    public final CompositionObserverHolder observerHolder;
    public final CompositionContext parent;
    public boolean pendingInvalidScopes;
    public final SlotTable slotTable;
    public final AtomicReference pendingModifications = new AtomicReference(null);
    public final Object lock = new Object();

    public CompositionImpl(CompositionContext compositionContext, AbstractApplier abstractApplier) {
        this.parent = compositionContext;
        this.applier = abstractApplier;
        MutableScatterSet.MutableSetWrapper mutableSetWrapper = new MutableScatterSet().new MutableSetWrapper();
        this.abandonSet = mutableSetWrapper;
        SlotTable slotTable = new SlotTable();
        if (compositionContext.getCollectingCallByInformation$runtime_release()) {
            slotTable.calledByMap = new MutableIntObjectMap();
        }
        if (compositionContext.getCollectingSourceInformation$runtime_release()) {
            slotTable.collectSourceInformation();
        }
        this.slotTable = slotTable;
        this.observations = ScopeMap.m269constructorimpl$default();
        this.invalidatedScopes = new MutableScatterSet();
        this.conditionallyInvalidatedScopes = new MutableScatterSet();
        this.derivedStates = ScopeMap.m269constructorimpl$default();
        ChangeList changeList = new ChangeList();
        this.changes = changeList;
        ChangeList changeList2 = new ChangeList();
        this.lateChanges = changeList2;
        this.observationsProcessed = ScopeMap.m269constructorimpl$default();
        this.invalidations = ScopeMap.m269constructorimpl$default();
        this.observerHolder = new CompositionObserverHolder();
        ComposerImpl composerImpl = new ComposerImpl(abstractApplier, compositionContext, slotTable, mutableSetWrapper, changeList, changeList2, this);
        compositionContext.registerComposer$runtime_release(composerImpl);
        this.composer = composerImpl;
        boolean z = compositionContext instanceof Recomposer;
        ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$CompositionKt.f9lambda1;
    }

    public final void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.operations.clear();
        this.lateChanges.operations.clear();
        MutableScatterSet.MutableSetWrapper mutableSetWrapper = this.abandonSet;
        if (mutableSetWrapper.this$0$1.isEmpty()) {
            return;
        }
        new RememberEventDispatcher(mutableSetWrapper).dispatchAbandons();
    }

    public final void addPendingInvalidationsLocked(Object obj, boolean z) {
        Object obj2 = this.observations.get(obj);
        if (obj2 == null) {
            return;
        }
        boolean z2 = obj2 instanceof MutableScatterSet;
        InvalidationResult invalidationResult = InvalidationResult.IGNORED;
        MutableScatterSet mutableScatterSet = this.invalidatedScopes;
        MutableScatterSet mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        MutableScatterMap mutableScatterMap = this.observationsProcessed;
        if (!z2) {
            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj2;
            if (ScopeMap.m270removeimpl(mutableScatterMap, obj, recomposeScopeImpl) || recomposeScopeImpl.invalidateForResult(obj) == invalidationResult) {
                return;
            }
            if (recomposeScopeImpl.trackedDependencies == null || z) {
                mutableScatterSet.add(recomposeScopeImpl);
                return;
            } else {
                mutableScatterSet2.add(recomposeScopeImpl);
                return;
            }
        }
        MutableScatterSet mutableScatterSet3 = (MutableScatterSet) obj2;
        Object[] objArr = mutableScatterSet3.elements;
        long[] jArr = mutableScatterSet3.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                        if (!ScopeMap.m270removeimpl(mutableScatterMap, obj, recomposeScopeImpl2) && recomposeScopeImpl2.invalidateForResult(obj) != invalidationResult) {
                            if (recomposeScopeImpl2.trackedDependencies == null || z) {
                                mutableScatterSet.add(recomposeScopeImpl2);
                            } else {
                                mutableScatterSet2.add(recomposeScopeImpl2);
                            }
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void applyChanges() {
        synchronized (this.lock) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.this$0$1.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01d6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList r32) {
        /*
            Method dump skipped, instructions count: 474
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList):void");
    }

    public final void applyLateChanges() {
        synchronized (this.lock) {
            try {
                if (this.lateChanges.operations.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.this$0$1.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public final void changesApplied() {
        synchronized (this.lock) {
            try {
                this.composer.providerUpdates = null;
                if (!this.abandonSet.this$0$1.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
            } catch (Throwable th) {
                try {
                    try {
                        if (!this.abandonSet.this$0$1.isEmpty()) {
                            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                        }
                        throw th;
                    } catch (Exception e) {
                        abandonChanges();
                        throw e;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
    }

    public final void cleanUpDerivedStateObservations() {
        long[] jArr;
        int i;
        long[] jArr2;
        int i2;
        int i3;
        int i4;
        boolean z;
        long[] jArr3;
        Object[] objArr;
        long[] jArr4;
        MutableScatterMap mutableScatterMap = this.derivedStates;
        long[] jArr5 = mutableScatterMap.metadata;
        int length = jArr5.length - 2;
        char c = 7;
        long j = -9187201950435737472L;
        int i5 = 8;
        if (length >= 0) {
            int i6 = 0;
            while (true) {
                long j2 = jArr5[i6];
                if ((((~j2) << c) & j2 & j) != j) {
                    int i7 = 8 - ((~(i6 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j2 & 255) < 128) {
                            int i9 = (i6 << 3) + i8;
                            Object obj = mutableScatterMap.keys[i9];
                            Object obj2 = mutableScatterMap.values[i9];
                            boolean z2 = obj2 instanceof MutableScatterSet;
                            MutableScatterMap mutableScatterMap2 = this.observations;
                            if (z2) {
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                Object[] objArr2 = mutableScatterSet.elements;
                                long[] jArr6 = mutableScatterSet.metadata;
                                int length2 = jArr6.length - 2;
                                jArr2 = jArr5;
                                i2 = length;
                                if (length2 >= 0) {
                                    int i10 = 0;
                                    while (true) {
                                        long j3 = jArr6[i10];
                                        i3 = i6;
                                        Object[] objArr3 = objArr2;
                                        if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                            int i12 = 0;
                                            while (i12 < i11) {
                                                int i13 = i12;
                                                Object[] objArr4 = objArr3;
                                                if ((j3 & 255) < 128) {
                                                    int i14 = (i10 << 3) + i13;
                                                    jArr4 = jArr6;
                                                    if (!mutableScatterMap2.containsKey((DerivedState) objArr4[i14])) {
                                                        mutableScatterSet.removeElementAt(i14);
                                                    }
                                                } else {
                                                    jArr4 = jArr6;
                                                }
                                                j3 >>= 8;
                                                i12 = i13 + 1;
                                                objArr3 = objArr4;
                                                jArr6 = jArr4;
                                            }
                                            jArr3 = jArr6;
                                            objArr = objArr3;
                                            if (i11 != 8) {
                                                break;
                                            }
                                        } else {
                                            jArr3 = jArr6;
                                            objArr = objArr3;
                                        }
                                        if (i10 == length2) {
                                            break;
                                        }
                                        i10++;
                                        objArr2 = objArr;
                                        i6 = i3;
                                        jArr6 = jArr3;
                                    }
                                } else {
                                    i3 = i6;
                                }
                                z = mutableScatterSet.isEmpty();
                            } else {
                                jArr2 = jArr5;
                                i2 = length;
                                i3 = i6;
                                z = !mutableScatterMap2.containsKey((DerivedState) obj2);
                            }
                            if (z) {
                                mutableScatterMap.removeValueAt(i9);
                            }
                            i4 = 8;
                        } else {
                            jArr2 = jArr5;
                            i2 = length;
                            i3 = i6;
                            i4 = i5;
                        }
                        j2 >>= i4;
                        i8++;
                        i5 = i4;
                        jArr5 = jArr2;
                        length = i2;
                        i6 = i3;
                    }
                    jArr = jArr5;
                    int i15 = length;
                    int i16 = i6;
                    if (i7 != i5) {
                        break;
                    }
                    length = i15;
                    i = i16;
                } else {
                    jArr = jArr5;
                    i = i6;
                }
                if (i == length) {
                    break;
                }
                i6 = i + 1;
                jArr5 = jArr;
                c = 7;
                j = -9187201950435737472L;
                i5 = 8;
            }
        }
        MutableScatterSet mutableScatterSet2 = this.conditionallyInvalidatedScopes;
        if (!mutableScatterSet2.isNotEmpty()) {
            return;
        }
        Object[] objArr5 = mutableScatterSet2.elements;
        long[] jArr7 = mutableScatterSet2.metadata;
        int length3 = jArr7.length - 2;
        if (length3 < 0) {
            return;
        }
        int i17 = 0;
        while (true) {
            long j4 = jArr7[i17];
            if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                int i18 = 8 - ((~(i17 - length3)) >>> 31);
                for (int i19 = 0; i19 < i18; i19++) {
                    if ((j4 & 255) < 128) {
                        int i20 = (i17 << 3) + i19;
                        if (!(((RecomposeScopeImpl) objArr5[i20]).trackedDependencies != null)) {
                            mutableScatterSet2.removeElementAt(i20);
                        }
                    }
                    j4 >>= 8;
                }
                if (i18 != 8) {
                    return;
                }
            }
            if (i17 == length3) {
                return;
            } else {
                i17++;
            }
        }
    }

    public final void composeContent(ComposableLambdaImpl composableLambdaImpl) {
        try {
            synchronized (this.lock) {
                drainPendingModificationsForCompositionLocked();
                MutableScatterMap mutableScatterMap = this.invalidations;
                this.invalidations = ScopeMap.m269constructorimpl$default();
                try {
                    observer();
                    ComposerImpl composerImpl = this.composer;
                    if (!composerImpl.changes.operations.isEmpty()) {
                        ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
                    }
                    composerImpl.m254doComposeaFTiNEg(mutableScatterMap, composableLambdaImpl);
                } catch (Exception e) {
                    this.invalidations = mutableScatterMap;
                    throw e;
                }
            }
        } finally {
        }
    }

    public final void composeInitial(ComposableLambdaImpl composableLambdaImpl) {
        if (this.disposed) {
            PreconditionsKt.throwIllegalStateException("The composition is disposed");
        }
        this.parent.composeInitial$runtime_release(this, composableLambdaImpl);
    }

    public final void deactivate() {
        synchronized (this.lock) {
            try {
                boolean z = this.slotTable.groupsSize > 0;
                try {
                    if (!z) {
                        if (!this.abandonSet.this$0$1.isEmpty()) {
                        }
                        this.observations.clear();
                        this.derivedStates.clear();
                        this.invalidations.clear();
                        this.changes.operations.clear();
                        this.lateChanges.operations.clear();
                        ComposerImpl composerImpl = this.composer;
                        composerImpl.invalidateStack.clear();
                        composerImpl.invalidations.clear();
                        composerImpl.changes.operations.clear();
                        composerImpl.providerUpdates = null;
                    }
                    RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                    if (z) {
                        SlotWriter openWriter = this.slotTable.openWriter();
                        try {
                            ComposerKt.deactivateCurrentGroup(openWriter, rememberEventDispatcher);
                            openWriter.close(true);
                            this.applier.onEndChanges();
                            rememberEventDispatcher.dispatchRememberObservers();
                        } catch (Throwable th) {
                            openWriter.close(false);
                            throw th;
                        }
                    }
                    rememberEventDispatcher.dispatchAbandons();
                    Trace.endSection();
                    this.observations.clear();
                    this.derivedStates.clear();
                    this.invalidations.clear();
                    this.changes.operations.clear();
                    this.lateChanges.operations.clear();
                    ComposerImpl composerImpl2 = this.composer;
                    composerImpl2.invalidateStack.clear();
                    composerImpl2.invalidations.clear();
                    composerImpl2.changes.operations.clear();
                    composerImpl2.providerUpdates = null;
                } catch (Throwable th2) {
                    Trace.endSection();
                    throw th2;
                }
                Trace.beginSection("Compose:deactivate");
            } catch (Throwable th3) {
                throw th3;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final void dispose() {
        synchronized (this.lock) {
            try {
                if (this.composer.isComposing) {
                    PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
                }
                if (!this.disposed) {
                    this.disposed = true;
                    ComposableLambdaImpl composableLambdaImpl = ComposableSingletons$CompositionKt.f10lambda2;
                    ChangeList changeList = this.composer.deferredChanges;
                    if (changeList != null) {
                        applyChangesInLocked(changeList);
                    }
                    boolean z = this.slotTable.groupsSize > 0;
                    if (z || !this.abandonSet.this$0$1.isEmpty()) {
                        RememberEventDispatcher rememberEventDispatcher = new RememberEventDispatcher(this.abandonSet);
                        if (z) {
                            SlotWriter openWriter = this.slotTable.openWriter();
                            try {
                                ComposerKt.removeCurrentGroup(openWriter, rememberEventDispatcher);
                                openWriter.close(true);
                                this.applier.clear();
                                this.applier.onEndChanges();
                                rememberEventDispatcher.dispatchRememberObservers();
                            } catch (Throwable th) {
                                openWriter.close(false);
                                throw th;
                            }
                        }
                        rememberEventDispatcher.dispatchAbandons();
                    }
                    ComposerImpl composerImpl = this.composer;
                    composerImpl.getClass();
                    Trace.beginSection("Compose:Composer.dispose");
                    try {
                        composerImpl.parentContext.unregisterComposer$runtime_release(composerImpl);
                        composerImpl.invalidateStack.clear();
                        composerImpl.invalidations.clear();
                        composerImpl.changes.operations.clear();
                        composerImpl.providerUpdates = null;
                        composerImpl.applier.clear();
                        Trace.endSection();
                    } catch (Throwable th2) {
                        Trace.endSection();
                        throw th2;
                    }
                }
            } catch (Throwable th3) {
                throw th3;
            }
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    public final void drainPendingModificationsForCompositionLocked() {
        AtomicReference atomicReference = this.pendingModifications;
        Object obj = CompositionKt.PendingApplyNoModifications;
        Object andSet = atomicReference.getAndSet(obj);
        if (andSet != null) {
            if (andSet.equals(obj)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (andSet instanceof Set) {
                addPendingInvalidationsLocked((Set) andSet, true);
                return;
            }
            if (!(andSet instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, true);
            }
        }
    }

    public final void drainPendingModificationsLocked() {
        Object andSet = this.pendingModifications.getAndSet(null);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications)) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet, false);
            return;
        }
        if (andSet instanceof Object[]) {
            for (Set set : (Set[]) andSet) {
                addPendingInvalidationsLocked(set, false);
            }
            return;
        }
        if (andSet == null) {
            ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
            throw new KotlinNothingValueException();
        }
        ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
        throw new KotlinNothingValueException();
    }

    public final void drainPendingModificationsOutOfBandLocked() {
        Object andSet = this.pendingModifications.getAndSet(EmptySet.INSTANCE);
        if (Intrinsics.areEqual(andSet, CompositionKt.PendingApplyNoModifications) || andSet == null) {
            return;
        }
        if (andSet instanceof Set) {
            addPendingInvalidationsLocked((Set) andSet, false);
            return;
        }
        if (!(andSet instanceof Object[])) {
            ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
            throw new KotlinNothingValueException();
        }
        for (Set set : (Set[]) andSet) {
            addPendingInvalidationsLocked(set, false);
        }
    }

    public final void insertMovableContent(List list) {
        MutableScatterSet.MutableSetWrapper mutableSetWrapper = this.abandonSet;
        int size = list.size();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= size) {
                z = true;
                break;
            } else if (!((MovableContentStateReference) ((Pair) list.get(i)).getFirst()).composition.equals(this)) {
                break;
            } else {
                i++;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            ComposerImpl composerImpl = this.composer;
            composerImpl.getClass();
            try {
                composerImpl.insertMovableContentGuarded(list);
                composerImpl.cleanUpCompose();
            } catch (Throwable th) {
                composerImpl.abortRoot();
                throw th;
            }
        } finally {
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final InvalidationResult invalidate(RecomposeScopeImpl recomposeScopeImpl, Object obj) {
        CompositionImpl compositionImpl;
        int i = recomposeScopeImpl.flags;
        if ((i & 2) != 0) {
            recomposeScopeImpl.flags = i | 4;
        }
        Anchor anchor = recomposeScopeImpl.anchor;
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (this.slotTable.ownsAnchor(anchor)) {
            return recomposeScopeImpl.block != null ? invalidateChecked(recomposeScopeImpl, anchor, obj) : InvalidationResult.IGNORED;
        }
        synchronized (this.lock) {
            compositionImpl = this.invalidationDelegate;
        }
        if (compositionImpl != null) {
            ComposerImpl composerImpl = compositionImpl.composer;
            if (composerImpl.isComposing && composerImpl.tryImminentInvalidation$runtime_release(recomposeScopeImpl, obj)) {
                return InvalidationResult.IMMINENT;
            }
        }
        return InvalidationResult.IGNORED;
    }

    public final void invalidateAll() {
        RecomposeScopeOwner recomposeScopeOwner;
        synchronized (this.lock) {
            try {
                for (Object obj : this.slotTable.slots) {
                    RecomposeScopeImpl recomposeScopeImpl = obj instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) obj : null;
                    if (recomposeScopeImpl != null && (recomposeScopeOwner = recomposeScopeImpl.owner) != null) {
                        recomposeScopeOwner.invalidate(recomposeScopeImpl, null);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final InvalidationResult invalidateChecked(RecomposeScopeImpl recomposeScopeImpl, Anchor anchor, Object obj) {
        CompositionImpl compositionImpl;
        int i;
        synchronized (this.lock) {
            try {
                CompositionImpl compositionImpl2 = this.invalidationDelegate;
                if (compositionImpl2 != null) {
                    SlotTable slotTable = this.slotTable;
                    int i2 = this.invalidationDelegateGroup;
                    if (slotTable.writer) {
                        ComposerKt.composeImmediateRuntimeError("Writer is active");
                    }
                    if (i2 < 0 || i2 >= slotTable.groupsSize) {
                        ComposerKt.composeImmediateRuntimeError("Invalid group index");
                    }
                    if (slotTable.ownsAnchor(anchor)) {
                        int i3 = slotTable.groups[(i2 * 5) + 3] + i2;
                        int i4 = anchor.location;
                        compositionImpl = (i2 <= i4 && i4 < i3) ? compositionImpl2 : null;
                    }
                    compositionImpl2 = null;
                }
                if (compositionImpl == null) {
                    ComposerImpl composerImpl = this.composer;
                    if (composerImpl.isComposing && composerImpl.tryImminentInvalidation$runtime_release(recomposeScopeImpl, obj)) {
                        return InvalidationResult.IMMINENT;
                    }
                    observer();
                    if (obj == null) {
                        this.invalidations.set(recomposeScopeImpl, ScopeInvalidated.INSTANCE);
                    } else if (obj instanceof DerivedState) {
                        Object obj2 = this.invalidations.get(recomposeScopeImpl);
                        if (obj2 != null) {
                            if (obj2 instanceof MutableScatterSet) {
                                MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                Object[] objArr = mutableScatterSet.elements;
                                long[] jArr = mutableScatterSet.metadata;
                                int length = jArr.length - 2;
                                if (length >= 0) {
                                    int i5 = 0;
                                    loop0: while (true) {
                                        long j = jArr[i5];
                                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i6 = 8;
                                            int i7 = 8 - ((~(i5 - length)) >>> 31);
                                            int i8 = 0;
                                            while (i8 < i7) {
                                                if ((j & 255) >= 128) {
                                                    i = i6;
                                                } else {
                                                    if (objArr[(i5 << 3) + i8] == ScopeInvalidated.INSTANCE) {
                                                        break loop0;
                                                    }
                                                    i = 8;
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
                            } else if (obj2 == ScopeInvalidated.INSTANCE) {
                            }
                        }
                        ScopeMap.m268addimpl(this.invalidations, recomposeScopeImpl, obj);
                    } else {
                        this.invalidations.set(recomposeScopeImpl, ScopeInvalidated.INSTANCE);
                    }
                }
                if (compositionImpl != null) {
                    return compositionImpl.invalidateChecked(recomposeScopeImpl, anchor, obj);
                }
                this.parent.invalidate$runtime_release(this);
                return this.composer.isComposing ? InvalidationResult.DEFERRED : InvalidationResult.SCHEDULED;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void invalidateScopeOfLocked(Object obj) {
        Object obj2 = this.observations.get(obj);
        if (obj2 == null) {
            return;
        }
        boolean z = obj2 instanceof MutableScatterSet;
        MutableScatterMap mutableScatterMap = this.observationsProcessed;
        InvalidationResult invalidationResult = InvalidationResult.IMMINENT;
        if (!z) {
            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) obj2;
            if (recomposeScopeImpl.invalidateForResult(obj) == invalidationResult) {
                ScopeMap.m268addimpl(mutableScatterMap, obj, recomposeScopeImpl);
                return;
            }
            return;
        }
        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
        Object[] objArr = mutableScatterSet.elements;
        long[] jArr = mutableScatterSet.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) objArr[(i << 3) + i3];
                        if (recomposeScopeImpl2.invalidateForResult(obj) == invalidationResult) {
                            ScopeMap.m268addimpl(mutableScatterMap, obj, recomposeScopeImpl2);
                        }
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final boolean isDisposed() {
        return this.disposed;
    }

    public final void observer() {
        this.observerHolder.getClass();
        this.parent.getClass();
        Intrinsics.areEqual((Object) null, (Object) null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0052, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean observesAnyOf(java.util.Set r18) {
        /*
            r17 = this;
            r0 = r17
            r1 = r18
            boolean r2 = r1 instanceof androidx.compose.runtime.collection.ScatterSetWrapper
            androidx.collection.MutableScatterMap r3 = r0.derivedStates
            androidx.collection.MutableScatterMap r0 = r0.observations
            r4 = 0
            r5 = 1
            if (r2 == 0) goto L5e
            androidx.compose.runtime.collection.ScatterSetWrapper r1 = (androidx.compose.runtime.collection.ScatterSetWrapper) r1
            androidx.collection.ScatterSet r1 = r1.set
            java.lang.Object[] r2 = r1.elements
            long[] r1 = r1.metadata
            int r6 = r1.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L7b
            r7 = r4
        L1c:
            r8 = r1[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L59
            int r10 = r7 - r6
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r4
        L36:
            if (r12 >= r10) goto L57
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L53
            int r13 = r7 << 3
            int r13 = r13 + r12
            r13 = r2[r13]
            boolean r14 = r0.containsKey(r13)
            if (r14 != 0) goto L52
            boolean r13 = r3.containsKey(r13)
            if (r13 == 0) goto L53
        L52:
            return r5
        L53:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L36
        L57:
            if (r10 != r11) goto L7b
        L59:
            if (r7 == r6) goto L7b
            int r7 = r7 + 1
            goto L1c
        L5e:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L64:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L7b
            java.lang.Object r2 = r1.next()
            boolean r6 = r0.containsKey(r2)
            if (r6 != 0) goto L7a
            boolean r2 = r3.containsKey(r2)
            if (r2 == 0) goto L64
        L7a:
            return r5
        L7b:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.observesAnyOf(java.util.Set):boolean");
    }

    public final boolean recompose() {
        boolean m255recomposeaFTiNEg$runtime_release;
        synchronized (this.lock) {
            try {
                drainPendingModificationsForCompositionLocked();
                try {
                    MutableScatterMap mutableScatterMap = this.invalidations;
                    this.invalidations = ScopeMap.m269constructorimpl$default();
                    try {
                        observer();
                        m255recomposeaFTiNEg$runtime_release = this.composer.m255recomposeaFTiNEg$runtime_release(mutableScatterMap);
                        if (!m255recomposeaFTiNEg$runtime_release) {
                            drainPendingModificationsLocked();
                        }
                    } catch (Exception e) {
                        this.invalidations = mutableScatterMap;
                        throw e;
                    }
                } finally {
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return m255recomposeaFTiNEg$runtime_release;
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final void recomposeScopeReleased() {
        this.pendingInvalidScopes = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.util.Set[]] */
    public final void recordModificationsOf(ScatterSetWrapper scatterSetWrapper) {
        Object obj;
        ScatterSetWrapper scatterSetWrapper2;
        do {
            obj = this.pendingModifications.get();
            if (obj == null ? true : obj.equals(CompositionKt.PendingApplyNoModifications)) {
                scatterSetWrapper2 = scatterSetWrapper;
            } else if (obj instanceof Set) {
                scatterSetWrapper2 = new Set[]{obj, scatterSetWrapper};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Set[] setArr = (Set[]) obj;
                int length = setArr.length;
                ?? copyOf = Arrays.copyOf(setArr, length + 1);
                copyOf[length] = scatterSetWrapper;
                scatterSetWrapper2 = copyOf;
            }
        } while (!this.pendingModifications.compareAndSet(obj, scatterSetWrapper2));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
            }
        }
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public final void recordReadOf(Object obj) {
        RecomposeScopeImpl currentRecomposeScope$runtime_release;
        int i;
        int i2;
        ComposerImpl composerImpl = this.composer;
        if (composerImpl.childrenComposing <= 0 && (currentRecomposeScope$runtime_release = composerImpl.getCurrentRecomposeScope$runtime_release()) != null) {
            int i3 = currentRecomposeScope$runtime_release.flags | 1;
            currentRecomposeScope$runtime_release.flags = i3;
            if ((i3 & 32) == 0) {
                MutableObjectIntMap mutableObjectIntMap = currentRecomposeScope$runtime_release.trackedInstances;
                if (mutableObjectIntMap == null) {
                    mutableObjectIntMap = new MutableObjectIntMap();
                    currentRecomposeScope$runtime_release.trackedInstances = mutableObjectIntMap;
                }
                int i4 = currentRecomposeScope$runtime_release.currentToken;
                int findIndex = mutableObjectIntMap.findIndex(obj);
                if (findIndex < 0) {
                    findIndex = ~findIndex;
                    i2 = -1;
                } else {
                    i2 = mutableObjectIntMap.values[findIndex];
                }
                mutableObjectIntMap.keys[findIndex] = obj;
                mutableObjectIntMap.values[findIndex] = i4;
                if (i2 == currentRecomposeScope$runtime_release.currentToken) {
                    return;
                }
            }
            if (obj instanceof StateObjectImpl) {
                ((StateObjectImpl) obj).m273recordReadInh_f27i8$runtime_release(1);
            }
            ScopeMap.m268addimpl(this.observations, obj, currentRecomposeScope$runtime_release);
            if (obj instanceof DerivedState) {
                DerivedState derivedState = (DerivedState) obj;
                DerivedSnapshotState.ResultRecord currentRecord = ((DerivedSnapshotState) derivedState).getCurrentRecord();
                MutableScatterMap mutableScatterMap = this.derivedStates;
                ScopeMap.m271removeScopeimpl(mutableScatterMap, obj);
                MutableObjectIntMap mutableObjectIntMap2 = currentRecord.dependencies;
                Object[] objArr = mutableObjectIntMap2.keys;
                long[] jArr = mutableObjectIntMap2.metadata;
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
                                    StateObject stateObject = (StateObject) objArr[(i5 << 3) + i8];
                                    if (stateObject instanceof StateObjectImpl) {
                                        ((StateObjectImpl) stateObject).m273recordReadInh_f27i8$runtime_release(1);
                                    }
                                    ScopeMap.m268addimpl(mutableScatterMap, stateObject, obj);
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
                        } else {
                            i5++;
                        }
                    }
                }
                Object obj2 = currentRecord.result;
                MutableScatterMap mutableScatterMap2 = currentRecomposeScope$runtime_release.trackedDependencies;
                if (mutableScatterMap2 == null) {
                    mutableScatterMap2 = new MutableScatterMap();
                    currentRecomposeScope$runtime_release.trackedDependencies = mutableScatterMap2;
                }
                mutableScatterMap2.set(derivedState, obj2);
            }
        }
    }

    public final void recordWriteOf(Object obj) {
        synchronized (this.lock) {
            try {
                invalidateScopeOfLocked(obj);
                Object obj2 = this.derivedStates.get(obj);
                if (obj2 != null) {
                    if (obj2 instanceof MutableScatterSet) {
                        MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                        Object[] objArr = mutableScatterSet.elements;
                        long[] jArr = mutableScatterSet.metadata;
                        int length = jArr.length - 2;
                        if (length >= 0) {
                            int i = 0;
                            while (true) {
                                long j = jArr[i];
                                if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                    int i2 = 8 - ((~(i - length)) >>> 31);
                                    for (int i3 = 0; i3 < i2; i3++) {
                                        if ((255 & j) < 128) {
                                            invalidateScopeOfLocked((DerivedState) objArr[(i << 3) + i3]);
                                        }
                                        j >>= 8;
                                    }
                                    if (i2 != 8) {
                                        break;
                                    }
                                }
                                if (i == length) {
                                    break;
                                } else {
                                    i++;
                                }
                            }
                        }
                    } else {
                        invalidateScopeOfLocked((DerivedState) obj2);
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // androidx.compose.runtime.Composition
    public final void setContent(Function2 function2) {
        composeInitial((ComposableLambdaImpl) function2);
    }

    public final void addPendingInvalidationsLocked(Set set, boolean z) {
        MutableScatterMap mutableScatterMap;
        int i;
        long[] jArr;
        long[] jArr2;
        int i2;
        int i3;
        boolean contains;
        Object[] objArr;
        long[] jArr3;
        Object[] objArr2;
        long[] jArr4;
        long[] jArr5;
        int i4;
        long[] jArr6;
        int i5;
        int i6;
        int i7;
        boolean z2;
        Object[] objArr3;
        int i8;
        Object[] objArr4;
        Object[] objArr5;
        MutableScatterMap mutableScatterMap2;
        Object[] objArr6;
        MutableScatterMap mutableScatterMap3;
        int i9;
        int i10;
        int i11;
        boolean z3 = set instanceof ScatterSetWrapper;
        MutableScatterMap mutableScatterMap4 = this.derivedStates;
        char c = 7;
        long j = -9187201950435737472L;
        int i12 = 8;
        if (z3) {
            ScatterSet scatterSet = ((ScatterSetWrapper) set).set;
            Object[] objArr7 = scatterSet.elements;
            long[] jArr7 = scatterSet.metadata;
            int length = jArr7.length - 2;
            if (length >= 0) {
                int i13 = 0;
                while (true) {
                    long j2 = jArr7[i13];
                    if ((((~j2) << c) & j2 & j) != j) {
                        int i14 = 8 - ((~(i13 - length)) >>> 31);
                        int i15 = 0;
                        while (i15 < i14) {
                            if ((j2 & 255) < 128) {
                                Object obj = objArr7[(i13 << 3) + i15];
                                if (obj instanceof RecomposeScopeImpl) {
                                    ((RecomposeScopeImpl) obj).invalidateForResult(null);
                                } else {
                                    addPendingInvalidationsLocked(obj, z);
                                    Object obj2 = mutableScatterMap4.get(obj);
                                    if (obj2 != null) {
                                        if (obj2 instanceof MutableScatterSet) {
                                            MutableScatterSet mutableScatterSet = (MutableScatterSet) obj2;
                                            Object[] objArr8 = mutableScatterSet.elements;
                                            long[] jArr8 = mutableScatterSet.metadata;
                                            int length2 = jArr8.length - 2;
                                            if (length2 >= 0) {
                                                objArr6 = objArr7;
                                                mutableScatterMap3 = mutableScatterMap4;
                                                int i16 = 0;
                                                while (true) {
                                                    long j3 = jArr8[i16];
                                                    i9 = i14;
                                                    i10 = i15;
                                                    if ((((~j3) << c) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                        int i17 = 8 - ((~(i16 - length2)) >>> 31);
                                                        for (int i18 = 0; i18 < i17; i18++) {
                                                            if ((j3 & 255) < 128) {
                                                                addPendingInvalidationsLocked((DerivedState) objArr8[(i16 << 3) + i18], z);
                                                            }
                                                            j3 >>= 8;
                                                        }
                                                        if (i17 != 8) {
                                                            break;
                                                        }
                                                    }
                                                    if (i16 == length2) {
                                                        break;
                                                    }
                                                    i16++;
                                                    i14 = i9;
                                                    i15 = i10;
                                                    c = 7;
                                                }
                                            }
                                        } else {
                                            objArr6 = objArr7;
                                            mutableScatterMap3 = mutableScatterMap4;
                                            i9 = i14;
                                            i10 = i15;
                                            addPendingInvalidationsLocked((DerivedState) obj2, z);
                                        }
                                        i11 = 8;
                                    }
                                }
                                objArr6 = objArr7;
                                mutableScatterMap3 = mutableScatterMap4;
                                i9 = i14;
                                i10 = i15;
                                i11 = 8;
                            } else {
                                objArr6 = objArr7;
                                mutableScatterMap3 = mutableScatterMap4;
                                i9 = i14;
                                i10 = i15;
                                i11 = i12;
                            }
                            j2 >>= i11;
                            i15 = i10 + 1;
                            i12 = i11;
                            mutableScatterMap4 = mutableScatterMap3;
                            i14 = i9;
                            c = 7;
                            objArr7 = objArr6;
                        }
                        objArr5 = objArr7;
                        mutableScatterMap2 = mutableScatterMap4;
                        if (i14 != i12) {
                            break;
                        }
                    } else {
                        objArr5 = objArr7;
                        mutableScatterMap2 = mutableScatterMap4;
                    }
                    if (i13 == length) {
                        break;
                    }
                    i13++;
                    objArr7 = objArr5;
                    mutableScatterMap4 = mutableScatterMap2;
                    c = 7;
                    j = -9187201950435737472L;
                    i12 = 8;
                }
            }
        } else {
            MutableScatterMap mutableScatterMap5 = mutableScatterMap4;
            for (Object obj3 : set) {
                if (obj3 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) obj3).invalidateForResult(null);
                    mutableScatterMap = mutableScatterMap5;
                } else {
                    addPendingInvalidationsLocked(obj3, z);
                    mutableScatterMap = mutableScatterMap5;
                    Object obj4 = mutableScatterMap.get(obj3);
                    if (obj4 != null) {
                        if (obj4 instanceof MutableScatterSet) {
                            MutableScatterSet mutableScatterSet2 = (MutableScatterSet) obj4;
                            Object[] objArr9 = mutableScatterSet2.elements;
                            long[] jArr9 = mutableScatterSet2.metadata;
                            int length3 = jArr9.length - 2;
                            if (length3 >= 0) {
                                while (true) {
                                    long j4 = jArr9[i];
                                    if ((((~j4) << 7) & j4 & (-9187201950435737472L)) != -9187201950435737472L) {
                                        int i19 = 8 - ((~(i - length3)) >>> 31);
                                        for (int i20 = 0; i20 < i19; i20++) {
                                            if ((j4 & 255) < 128) {
                                                addPendingInvalidationsLocked((DerivedState) objArr9[(i << 3) + i20], z);
                                            }
                                            j4 >>= 8;
                                        }
                                        if (i19 != 8) {
                                            break;
                                        }
                                    }
                                    i = i != length3 ? i + 1 : 0;
                                }
                            }
                        } else {
                            addPendingInvalidationsLocked((DerivedState) obj4, z);
                        }
                    }
                }
                mutableScatterMap5 = mutableScatterMap;
            }
        }
        MutableScatterMap mutableScatterMap6 = this.observations;
        MutableScatterSet mutableScatterSet3 = this.invalidatedScopes;
        if (z) {
            MutableScatterSet mutableScatterSet4 = this.conditionallyInvalidatedScopes;
            if (mutableScatterSet4.isNotEmpty()) {
                long[] jArr10 = mutableScatterMap6.metadata;
                int length4 = jArr10.length - 2;
                if (length4 >= 0) {
                    int i21 = 0;
                    while (true) {
                        long j5 = jArr10[i21];
                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i22 = 8 - ((~(i21 - length4)) >>> 31);
                            int i23 = 0;
                            while (i23 < i22) {
                                if ((j5 & 255) < 128) {
                                    int i24 = (i21 << 3) + i23;
                                    Object obj5 = mutableScatterMap6.keys[i24];
                                    Object obj6 = mutableScatterMap6.values[i24];
                                    if (obj6 instanceof MutableScatterSet) {
                                        MutableScatterSet mutableScatterSet5 = (MutableScatterSet) obj6;
                                        Object[] objArr10 = mutableScatterSet5.elements;
                                        long[] jArr11 = mutableScatterSet5.metadata;
                                        int length5 = jArr11.length - 2;
                                        jArr6 = jArr10;
                                        i5 = length4;
                                        if (length5 >= 0) {
                                            int i25 = 0;
                                            while (true) {
                                                long j6 = jArr11[i25];
                                                i7 = i22;
                                                long[] jArr12 = jArr11;
                                                if ((((~j6) << 7) & j6 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                    int i26 = 8 - ((~(i25 - length5)) >>> 31);
                                                    int i27 = 0;
                                                    while (i27 < i26) {
                                                        if ((j6 & 255) < 128) {
                                                            i8 = i21;
                                                            int i28 = (i25 << 3) + i27;
                                                            objArr4 = objArr10;
                                                            RecomposeScopeImpl recomposeScopeImpl = (RecomposeScopeImpl) objArr10[i28];
                                                            if (mutableScatterSet4.contains(recomposeScopeImpl) || mutableScatterSet3.contains(recomposeScopeImpl)) {
                                                                mutableScatterSet5.removeElementAt(i28);
                                                            }
                                                        } else {
                                                            i8 = i21;
                                                            objArr4 = objArr10;
                                                        }
                                                        j6 >>= 8;
                                                        i27++;
                                                        i21 = i8;
                                                        objArr10 = objArr4;
                                                    }
                                                    i6 = i21;
                                                    objArr3 = objArr10;
                                                    if (i26 != 8) {
                                                        break;
                                                    }
                                                } else {
                                                    i6 = i21;
                                                    objArr3 = objArr10;
                                                }
                                                if (i25 == length5) {
                                                    break;
                                                }
                                                i25++;
                                                i22 = i7;
                                                jArr11 = jArr12;
                                                i21 = i6;
                                                objArr10 = objArr3;
                                            }
                                        } else {
                                            i6 = i21;
                                            i7 = i22;
                                        }
                                        z2 = mutableScatterSet5.isEmpty();
                                    } else {
                                        jArr6 = jArr10;
                                        i5 = length4;
                                        i6 = i21;
                                        i7 = i22;
                                        RecomposeScopeImpl recomposeScopeImpl2 = (RecomposeScopeImpl) obj6;
                                        z2 = mutableScatterSet4.contains(recomposeScopeImpl2) || mutableScatterSet3.contains(recomposeScopeImpl2);
                                    }
                                    if (z2) {
                                        mutableScatterMap6.removeValueAt(i24);
                                    }
                                } else {
                                    jArr6 = jArr10;
                                    i5 = length4;
                                    i6 = i21;
                                    i7 = i22;
                                }
                                j5 >>= 8;
                                i23++;
                                length4 = i5;
                                jArr10 = jArr6;
                                i22 = i7;
                                i21 = i6;
                            }
                            jArr5 = jArr10;
                            int i29 = length4;
                            int i30 = i21;
                            if (i22 != 8) {
                                break;
                            }
                            length4 = i29;
                            i4 = i30;
                        } else {
                            jArr5 = jArr10;
                            i4 = i21;
                        }
                        if (i4 == length4) {
                            break;
                        }
                        i21 = i4 + 1;
                        jArr10 = jArr5;
                    }
                }
                mutableScatterSet4.clear();
                cleanUpDerivedStateObservations();
                return;
            }
        }
        if (mutableScatterSet3.isNotEmpty()) {
            long[] jArr13 = mutableScatterMap6.metadata;
            int length6 = jArr13.length - 2;
            if (length6 >= 0) {
                int i31 = 0;
                while (true) {
                    long j7 = jArr13[i31];
                    if ((((~j7) << 7) & j7 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i32 = 8 - ((~(i31 - length6)) >>> 31);
                        int i33 = 0;
                        while (i33 < i32) {
                            if ((j7 & 255) < 128) {
                                int i34 = (i31 << 3) + i33;
                                Object obj7 = mutableScatterMap6.keys[i34];
                                Object obj8 = mutableScatterMap6.values[i34];
                                if (obj8 instanceof MutableScatterSet) {
                                    MutableScatterSet mutableScatterSet6 = (MutableScatterSet) obj8;
                                    Object[] objArr11 = mutableScatterSet6.elements;
                                    long[] jArr14 = mutableScatterSet6.metadata;
                                    int length7 = jArr14.length - 2;
                                    if (length7 >= 0) {
                                        long[] jArr15 = jArr13;
                                        i2 = length6;
                                        int i35 = 0;
                                        while (true) {
                                            long j8 = jArr14[i35];
                                            i3 = i32;
                                            jArr2 = jArr15;
                                            if ((((~j8) << 7) & j8 & (-9187201950435737472L)) != -9187201950435737472L) {
                                                int i36 = 8 - ((~(i35 - length7)) >>> 31);
                                                int i37 = 0;
                                                while (i37 < i36) {
                                                    if ((j8 & 255) < 128) {
                                                        jArr4 = jArr14;
                                                        int i38 = (i35 << 3) + i37;
                                                        objArr2 = objArr11;
                                                        if (mutableScatterSet3.contains((RecomposeScopeImpl) objArr11[i38])) {
                                                            mutableScatterSet6.removeElementAt(i38);
                                                        }
                                                    } else {
                                                        objArr2 = objArr11;
                                                        jArr4 = jArr14;
                                                    }
                                                    j8 >>= 8;
                                                    i37++;
                                                    jArr14 = jArr4;
                                                    objArr11 = objArr2;
                                                }
                                                objArr = objArr11;
                                                jArr3 = jArr14;
                                                if (i36 != 8) {
                                                    break;
                                                }
                                            } else {
                                                objArr = objArr11;
                                                jArr3 = jArr14;
                                            }
                                            if (i35 == length7) {
                                                break;
                                            }
                                            i35++;
                                            i32 = i3;
                                            jArr15 = jArr2;
                                            jArr14 = jArr3;
                                            objArr11 = objArr;
                                        }
                                    } else {
                                        jArr2 = jArr13;
                                        i2 = length6;
                                        i3 = i32;
                                    }
                                    contains = mutableScatterSet6.isEmpty();
                                } else {
                                    jArr2 = jArr13;
                                    i2 = length6;
                                    i3 = i32;
                                    contains = mutableScatterSet3.contains((RecomposeScopeImpl) obj8);
                                }
                                if (contains) {
                                    mutableScatterMap6.removeValueAt(i34);
                                }
                            } else {
                                jArr2 = jArr13;
                                i2 = length6;
                                i3 = i32;
                            }
                            j7 >>= 8;
                            i33++;
                            length6 = i2;
                            i32 = i3;
                            jArr13 = jArr2;
                        }
                        jArr = jArr13;
                        int i39 = length6;
                        if (i32 != 8) {
                            break;
                        } else {
                            length6 = i39;
                        }
                    } else {
                        jArr = jArr13;
                    }
                    if (i31 == length6) {
                        break;
                    }
                    i31++;
                    jArr13 = jArr;
                }
            }
            cleanUpDerivedStateObservations();
            mutableScatterSet3.clear();
        }
    }
}
