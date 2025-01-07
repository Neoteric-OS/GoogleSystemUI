package androidx.compose.runtime.snapshots;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DerivedSnapshotState;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.DerivedStateObserver;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.internal.Thread_jvmKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SnapshotStateObserver {
    public Snapshot$Companion$$ExternalSyntheticLambda0 applyUnsubscribe;
    public ObservedScopeMap currentMap;
    public final Lambda onChangedExecutor;
    public boolean sendingNotifications;
    public final AtomicReference pendingChanges = new AtomicReference(null);
    public final Function2 applyObserver = new Function2() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$applyObserver$1
        {
            super(2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v5, types: [kotlin.jvm.functions.Function1, kotlin.jvm.internal.Lambda] */
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            Object obj3;
            Collection plus;
            Collection collection = (Set) obj;
            SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
            do {
                obj3 = snapshotStateObserver.pendingChanges.get();
                if (obj3 == null) {
                    plus = collection;
                } else if (obj3 instanceof Set) {
                    plus = CollectionsKt__CollectionsKt.listOf(obj3, collection);
                } else {
                    if (!(obj3 instanceof List)) {
                        ComposerKt.composeRuntimeError("Unexpected notification");
                        throw new KotlinNothingValueException();
                    }
                    plus = CollectionsKt.plus((Iterable) Collections.singletonList(collection), (Collection) obj3);
                }
            } while (!snapshotStateObserver.pendingChanges.compareAndSet(obj3, plus));
            if (SnapshotStateObserver.access$drainChanges(SnapshotStateObserver.this)) {
                final SnapshotStateObserver snapshotStateObserver2 = SnapshotStateObserver.this;
                snapshotStateObserver2.getClass();
                snapshotStateObserver2.onChangedExecutor.invoke(new Function0() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$sendNotifications$1
                    {
                        super(0);
                    }

                    /* JADX WARN: Finally extract failed */
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        Object[] objArr;
                        Object[] objArr2;
                        Object[] objArr3;
                        do {
                            SnapshotStateObserver snapshotStateObserver3 = SnapshotStateObserver.this;
                            synchronized (snapshotStateObserver3.observedScopeMapsLock) {
                                if (!snapshotStateObserver3.sendingNotifications) {
                                    snapshotStateObserver3.sendingNotifications = true;
                                    try {
                                        MutableVector mutableVector = snapshotStateObserver3.observedScopeMaps;
                                        int i = mutableVector.size;
                                        if (i > 0) {
                                            Object[] objArr4 = mutableVector.content;
                                            int i2 = 0;
                                            while (true) {
                                                SnapshotStateObserver.ObservedScopeMap observedScopeMap = (SnapshotStateObserver.ObservedScopeMap) objArr4[i2];
                                                MutableScatterSet mutableScatterSet = observedScopeMap.invalidated;
                                                Object[] objArr5 = mutableScatterSet.elements;
                                                long[] jArr = mutableScatterSet.metadata;
                                                int length = jArr.length - 2;
                                                if (length >= 0) {
                                                    int i3 = 0;
                                                    while (true) {
                                                        long j = jArr[i3];
                                                        objArr = objArr4;
                                                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                                            int i4 = 8;
                                                            int i5 = 8 - ((~(i3 - length)) >>> 31);
                                                            int i6 = 0;
                                                            while (i6 < i5) {
                                                                if ((j & 255) < 128) {
                                                                    objArr3 = objArr5;
                                                                    observedScopeMap.onChanged.invoke(objArr5[(i3 << 3) + i6]);
                                                                    i4 = 8;
                                                                } else {
                                                                    objArr3 = objArr5;
                                                                }
                                                                j >>= i4;
                                                                i6++;
                                                                objArr5 = objArr3;
                                                            }
                                                            objArr2 = objArr5;
                                                            if (i5 != i4) {
                                                                break;
                                                            }
                                                        } else {
                                                            objArr2 = objArr5;
                                                        }
                                                        if (i3 == length) {
                                                            break;
                                                        }
                                                        i3++;
                                                        objArr4 = objArr;
                                                        objArr5 = objArr2;
                                                    }
                                                } else {
                                                    objArr = objArr4;
                                                }
                                                mutableScatterSet.clear();
                                                i2++;
                                                if (i2 >= i) {
                                                    break;
                                                }
                                                objArr4 = objArr;
                                            }
                                        }
                                        snapshotStateObserver3.sendingNotifications = false;
                                    } catch (Throwable th) {
                                        snapshotStateObserver3.sendingNotifications = false;
                                        throw th;
                                    }
                                }
                            }
                        } while (SnapshotStateObserver.access$drainChanges(SnapshotStateObserver.this));
                        return Unit.INSTANCE;
                    }
                });
            }
            return Unit.INSTANCE;
        }
    };
    public final Function1 readObserver = new Function1() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$readObserver$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            SnapshotStateObserver snapshotStateObserver = SnapshotStateObserver.this;
            snapshotStateObserver.getClass();
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = snapshotStateObserver.currentMap;
                Intrinsics.checkNotNull(observedScopeMap);
                Object obj2 = observedScopeMap.currentScope;
                Intrinsics.checkNotNull(obj2);
                int i = observedScopeMap.currentToken;
                MutableObjectIntMap mutableObjectIntMap = observedScopeMap.currentScopeReads;
                if (mutableObjectIntMap == null) {
                    mutableObjectIntMap = new MutableObjectIntMap();
                    observedScopeMap.currentScopeReads = mutableObjectIntMap;
                    observedScopeMap.scopeToValues.set(obj2, mutableObjectIntMap);
                }
                observedScopeMap.recordRead(obj, i, obj2, mutableObjectIntMap);
            }
            return Unit.INSTANCE;
        }
    };
    public final MutableVector observedScopeMaps = new MutableVector(new ObservedScopeMap[16]);
    public final Object observedScopeMapsLock = new Object();
    public long currentMapThreadId = -1;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    final class ObservedScopeMap {
        public Object currentScope;
        public MutableObjectIntMap currentScopeReads;
        public int deriveStateScopeCount;
        public final Function1 onChanged;
        public int currentToken = -1;
        public final MutableScatterMap valueToScopes = ScopeMap.m269constructorimpl$default();
        public final MutableScatterMap scopeToValues = new MutableScatterMap();
        public final MutableScatterSet invalidated = new MutableScatterSet();
        public final MutableVector statesToReread = new MutableVector(new DerivedState[16]);
        public final SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1 derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public final void done() {
                SnapshotStateObserver.ObservedScopeMap observedScopeMap = SnapshotStateObserver.ObservedScopeMap.this;
                observedScopeMap.deriveStateScopeCount--;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public final void start() {
                SnapshotStateObserver.ObservedScopeMap.this.deriveStateScopeCount++;
            }
        };
        public final MutableScatterMap dependencyToDerivedStates = ScopeMap.m269constructorimpl$default();
        public final HashMap recordedDerivedStateValues = new HashMap();

        /* JADX WARN: Type inference failed for: r2v6, types: [androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1] */
        public ObservedScopeMap(Function1 function1) {
            this.onChanged = function1;
        }

        public final void observe(Object obj, Function1 function1, Function0 function0) {
            long[] jArr;
            long[] jArr2;
            int i;
            Object obj2 = this.currentScope;
            MutableObjectIntMap mutableObjectIntMap = this.currentScopeReads;
            int i2 = this.currentToken;
            this.currentScope = obj;
            this.currentScopeReads = (MutableObjectIntMap) this.scopeToValues.get(obj);
            if (this.currentToken == -1) {
                this.currentToken = SnapshotKt.currentSnapshot().getId();
            }
            SnapshotStateObserver$ObservedScopeMap$derivedStateObserver$1 snapshotStateObserver$ObservedScopeMap$derivedStateObserver$1 = this.derivedStateObserver;
            MutableVector derivedStateObservers = SnapshotStateKt.derivedStateObservers();
            try {
                derivedStateObservers.add(snapshotStateObserver$ObservedScopeMap$derivedStateObserver$1);
                Snapshot.Companion.observe(function0, function1);
                derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                Object obj3 = this.currentScope;
                Intrinsics.checkNotNull(obj3);
                int i3 = this.currentToken;
                MutableObjectIntMap mutableObjectIntMap2 = this.currentScopeReads;
                if (mutableObjectIntMap2 != null) {
                    long[] jArr3 = mutableObjectIntMap2.metadata;
                    int length = jArr3.length - 2;
                    if (length >= 0) {
                        int i4 = 0;
                        while (true) {
                            long j = jArr3[i4];
                            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                                int i5 = 8;
                                int i6 = 8 - ((~(i4 - length)) >>> 31);
                                int i7 = 0;
                                while (i7 < i6) {
                                    if ((j & 255) < 128) {
                                        int i8 = (i4 << 3) + i7;
                                        Object obj4 = mutableObjectIntMap2.keys[i8];
                                        jArr2 = jArr3;
                                        boolean z = mutableObjectIntMap2.values[i8] != i3;
                                        if (z) {
                                            removeObservation(obj3, obj4);
                                        }
                                        if (z) {
                                            mutableObjectIntMap2.removeValueAt(i8);
                                        }
                                        i = 8;
                                    } else {
                                        jArr2 = jArr3;
                                        i = i5;
                                    }
                                    j >>= i;
                                    i7++;
                                    i5 = i;
                                    jArr3 = jArr2;
                                }
                                jArr = jArr3;
                                if (i6 != i5) {
                                    break;
                                }
                            } else {
                                jArr = jArr3;
                            }
                            if (i4 == length) {
                                break;
                            }
                            i4++;
                            jArr3 = jArr;
                        }
                    }
                }
                this.currentScope = obj2;
                this.currentScopeReads = mutableObjectIntMap;
                this.currentToken = i2;
            } catch (Throwable th) {
                derivedStateObservers.removeAt(derivedStateObservers.size - 1);
                throw th;
            }
        }

        /*  JADX ERROR: Type inference failed
            jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
            */
        public final boolean recordInvalidation(java.util.Set r42) {
            /*
                Method dump skipped, instructions count: 1802
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap.recordInvalidation(java.util.Set):boolean");
        }

        public final void recordRead(Object obj, int i, Object obj2, MutableObjectIntMap mutableObjectIntMap) {
            int i2;
            if (this.deriveStateScopeCount > 0) {
                return;
            }
            int findIndex = mutableObjectIntMap.findIndex(obj);
            if (findIndex < 0) {
                findIndex = ~findIndex;
                i2 = -1;
            } else {
                i2 = mutableObjectIntMap.values[findIndex];
            }
            mutableObjectIntMap.keys[findIndex] = obj;
            mutableObjectIntMap.values[findIndex] = i;
            if ((obj instanceof DerivedState) && i2 != i) {
                DerivedSnapshotState.ResultRecord currentRecord = ((DerivedState) obj).getCurrentRecord();
                this.recordedDerivedStateValues.put(obj, currentRecord.result);
                MutableObjectIntMap mutableObjectIntMap2 = currentRecord.dependencies;
                MutableScatterMap mutableScatterMap = this.dependencyToDerivedStates;
                ScopeMap.m271removeScopeimpl(mutableScatterMap, obj);
                Object[] objArr = mutableObjectIntMap2.keys;
                long[] jArr = mutableObjectIntMap2.metadata;
                int length = jArr.length - 2;
                if (length >= 0) {
                    int i3 = 0;
                    while (true) {
                        long j = jArr[i3];
                        if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                            int i4 = 8 - ((~(i3 - length)) >>> 31);
                            for (int i5 = 0; i5 < i4; i5++) {
                                if ((j & 255) < 128) {
                                    StateObject stateObject = (StateObject) objArr[(i3 << 3) + i5];
                                    if (stateObject instanceof StateObjectImpl) {
                                        ((StateObjectImpl) stateObject).m273recordReadInh_f27i8$runtime_release(2);
                                    }
                                    ScopeMap.m268addimpl(mutableScatterMap, stateObject, obj);
                                }
                                j >>= 8;
                            }
                            if (i4 != 8) {
                                break;
                            }
                        }
                        if (i3 == length) {
                            break;
                        } else {
                            i3++;
                        }
                    }
                }
            }
            if (i2 == -1) {
                if (obj instanceof StateObjectImpl) {
                    ((StateObjectImpl) obj).m273recordReadInh_f27i8$runtime_release(2);
                }
                ScopeMap.m268addimpl(this.valueToScopes, obj, obj2);
            }
        }

        public final void removeObservation(Object obj, Object obj2) {
            MutableScatterMap mutableScatterMap = this.valueToScopes;
            ScopeMap.m270removeimpl(mutableScatterMap, obj2, obj);
            if (!(obj2 instanceof DerivedState) || mutableScatterMap.containsKey(obj2)) {
                return;
            }
            ScopeMap.m271removeScopeimpl(this.dependencyToDerivedStates, obj2);
            this.recordedDerivedStateValues.remove(obj2);
        }

        public final void removeScopeIf(Function1 function1) {
            long[] jArr;
            int i;
            long[] jArr2;
            int i2;
            long j;
            int i3;
            long j2;
            int i4;
            MutableScatterMap mutableScatterMap = this.scopeToValues;
            long[] jArr3 = mutableScatterMap.metadata;
            int length = jArr3.length - 2;
            if (length < 0) {
                return;
            }
            int i5 = 0;
            while (true) {
                long j3 = jArr3[i5];
                long j4 = -9187201950435737472L;
                if ((((~j3) << 7) & j3 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i6 = 8;
                    int i7 = 8 - ((~(i5 - length)) >>> 31);
                    int i8 = 0;
                    while (i8 < i7) {
                        if ((j3 & 255) < 128) {
                            int i9 = (i5 << 3) + i8;
                            Object obj = mutableScatterMap.keys[i9];
                            MutableObjectIntMap mutableObjectIntMap = (MutableObjectIntMap) mutableScatterMap.values[i9];
                            Boolean bool = (Boolean) function1.invoke(obj);
                            if (bool.booleanValue()) {
                                Object[] objArr = mutableObjectIntMap.keys;
                                int[] iArr = mutableObjectIntMap.values;
                                long[] jArr4 = mutableObjectIntMap.metadata;
                                int length2 = jArr4.length - 2;
                                jArr2 = jArr3;
                                if (length2 >= 0) {
                                    i3 = i7;
                                    int i10 = 0;
                                    while (true) {
                                        long j5 = jArr4[i10];
                                        i2 = i5;
                                        j = j3;
                                        j2 = -9187201950435737472L;
                                        if ((((~j5) << 7) & j5 & (-9187201950435737472L)) != -9187201950435737472L) {
                                            int i11 = 8 - ((~(i10 - length2)) >>> 31);
                                            for (int i12 = 0; i12 < i11; i12++) {
                                                if ((j5 & 255) < 128) {
                                                    int i13 = (i10 << 3) + i12;
                                                    Object obj2 = objArr[i13];
                                                    int i14 = iArr[i13];
                                                    removeObservation(obj, obj2);
                                                }
                                                j5 >>= 8;
                                            }
                                            if (i11 != 8) {
                                                break;
                                            }
                                        }
                                        if (i10 == length2) {
                                            break;
                                        }
                                        i10++;
                                        i5 = i2;
                                        j3 = j;
                                    }
                                } else {
                                    i2 = i5;
                                    j = j3;
                                    i3 = i7;
                                    j2 = -9187201950435737472L;
                                }
                            } else {
                                jArr2 = jArr3;
                                i2 = i5;
                                j = j3;
                                i3 = i7;
                                j2 = j4;
                            }
                            if (bool.booleanValue()) {
                                mutableScatterMap.removeValueAt(i9);
                            }
                            i4 = 8;
                        } else {
                            jArr2 = jArr3;
                            i2 = i5;
                            j = j3;
                            i3 = i7;
                            j2 = j4;
                            i4 = i6;
                        }
                        j3 = j >> i4;
                        i8++;
                        i6 = i4;
                        j4 = j2;
                        jArr3 = jArr2;
                        i7 = i3;
                        i5 = i2;
                    }
                    jArr = jArr3;
                    int i15 = i5;
                    if (i7 != i6) {
                        return;
                    } else {
                        i = i15;
                    }
                } else {
                    jArr = jArr3;
                    i = i5;
                }
                if (i == length) {
                    return;
                }
                i5 = i + 1;
                jArr3 = jArr;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SnapshotStateObserver(Function1 function1) {
        this.onChangedExecutor = (Lambda) function1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean access$drainChanges(SnapshotStateObserver snapshotStateObserver) {
        boolean z;
        Set set;
        synchronized (snapshotStateObserver.observedScopeMapsLock) {
            z = snapshotStateObserver.sendingNotifications;
        }
        if (z) {
            return false;
        }
        boolean z2 = false;
        while (true) {
            Object obj = snapshotStateObserver.pendingChanges.get();
            Set set2 = null;
            List list = null;
            List list2 = null;
            if (obj != null) {
                if (obj instanceof Set) {
                    set = (Set) obj;
                } else {
                    if (!(obj instanceof List)) {
                        ComposerKt.composeRuntimeError("Unexpected notification");
                        throw new KotlinNothingValueException();
                    }
                    List list3 = (List) obj;
                    Set set3 = (Set) list3.get(0);
                    if (list3.size() == 2) {
                        list2 = list3.get(1);
                    } else if (list3.size() > 2) {
                        list2 = list3.subList(1, list3.size());
                    }
                    set = set3;
                    list = list2;
                }
                if (snapshotStateObserver.pendingChanges.compareAndSet(obj, list)) {
                    set2 = set;
                } else {
                    continue;
                }
            }
            if (set2 == null) {
                return z2;
            }
            synchronized (snapshotStateObserver.observedScopeMapsLock) {
                MutableVector mutableVector = snapshotStateObserver.observedScopeMaps;
                int i = mutableVector.size;
                if (i > 0) {
                    Object[] objArr = mutableVector.content;
                    int i2 = 0;
                    do {
                        z2 = ((ObservedScopeMap) objArr[i2]).recordInvalidation(set2) || z2;
                        i2++;
                    } while (i2 < i);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008c A[Catch: all -> 0x009a, TryCatch #0 {all -> 0x009a, blocks: (B:4:0x0007, B:6:0x000f, B:9:0x0081, B:13:0x008c, B:15:0x009c, B:17:0x0091, B:21:0x0021, B:24:0x002d, B:26:0x0041, B:28:0x004d, B:30:0x0057, B:32:0x006a, B:39:0x007b, B:44:0x00a0), top: B:3:0x0007 }] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void clear(java.lang.Object r22) {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            java.lang.Object r2 = r0.observedScopeMapsLock
            monitor-enter(r2)
            androidx.compose.runtime.collection.MutableVector r0 = r0.observedScopeMaps     // Catch: java.lang.Throwable -> L9a
            int r3 = r0.size     // Catch: java.lang.Throwable -> L9a
            r5 = 0
            r6 = 0
        Ld:
            if (r5 >= r3) goto La0
            java.lang.Object[] r7 = r0.content     // Catch: java.lang.Throwable -> L9a
            r7 = r7[r5]     // Catch: java.lang.Throwable -> L9a
            androidx.compose.runtime.snapshots.SnapshotStateObserver$ObservedScopeMap r7 = (androidx.compose.runtime.snapshots.SnapshotStateObserver.ObservedScopeMap) r7     // Catch: java.lang.Throwable -> L9a
            androidx.collection.MutableScatterMap r8 = r7.scopeToValues     // Catch: java.lang.Throwable -> L9a
            java.lang.Object r8 = r8.remove(r1)     // Catch: java.lang.Throwable -> L9a
            androidx.collection.MutableObjectIntMap r8 = (androidx.collection.MutableObjectIntMap) r8     // Catch: java.lang.Throwable -> L9a
            if (r8 != 0) goto L21
        L1f:
            r15 = r5
            goto L81
        L21:
            java.lang.Object[] r9 = r8.keys     // Catch: java.lang.Throwable -> L9a
            int[] r10 = r8.values     // Catch: java.lang.Throwable -> L9a
            long[] r8 = r8.metadata     // Catch: java.lang.Throwable -> L9a
            int r11 = r8.length     // Catch: java.lang.Throwable -> L9a
            int r11 = r11 + (-2)
            if (r11 < 0) goto L1f
            r12 = 0
        L2d:
            r13 = r8[r12]     // Catch: java.lang.Throwable -> L9a
            r15 = r5
            long r4 = ~r13     // Catch: java.lang.Throwable -> L9a
            r16 = 7
            long r4 = r4 << r16
            long r4 = r4 & r13
            r16 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r4 = r4 & r16
            int r4 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r4 == 0) goto L77
            int r4 = r12 - r11
            int r4 = ~r4     // Catch: java.lang.Throwable -> L9a
            int r4 = r4 >>> 31
            r5 = 8
            int r4 = 8 - r4
            r5 = 0
        L4b:
            if (r5 >= r4) goto L70
            r17 = 255(0xff, double:1.26E-321)
            long r17 = r13 & r17
            r19 = 128(0x80, double:6.3E-322)
            int r17 = (r17 > r19 ? 1 : (r17 == r19 ? 0 : -1))
            if (r17 >= 0) goto L67
            int r17 = r12 << 3
            int r17 = r17 + r5
            r18 = r8
            r8 = r9[r17]     // Catch: java.lang.Throwable -> L9a
            r17 = r10[r17]     // Catch: java.lang.Throwable -> L9a
            r7.removeObservation(r1, r8)     // Catch: java.lang.Throwable -> L9a
        L64:
            r8 = 8
            goto L6a
        L67:
            r18 = r8
            goto L64
        L6a:
            long r13 = r13 >> r8
            int r5 = r5 + 1
            r8 = r18
            goto L4b
        L70:
            r18 = r8
            r8 = 8
            if (r4 != r8) goto L81
            goto L79
        L77:
            r18 = r8
        L79:
            if (r12 == r11) goto L81
            int r12 = r12 + 1
            r5 = r15
            r8 = r18
            goto L2d
        L81:
            androidx.collection.MutableScatterMap r4 = r7.scopeToValues     // Catch: java.lang.Throwable -> L9a
            int r4 = r4._size     // Catch: java.lang.Throwable -> L9a
            if (r4 == 0) goto L89
            r4 = 1
            goto L8a
        L89:
            r4 = 0
        L8a:
            if (r4 != 0) goto L8f
            int r6 = r6 + 1
            goto L9c
        L8f:
            if (r6 <= 0) goto L9c
            java.lang.Object[] r4 = r0.content     // Catch: java.lang.Throwable -> L9a
            int r5 = r15 - r6
            r7 = r4[r15]     // Catch: java.lang.Throwable -> L9a
            r4[r5] = r7     // Catch: java.lang.Throwable -> L9a
            goto L9c
        L9a:
            r0 = move-exception
            goto Lac
        L9c:
            int r5 = r15 + 1
            goto Ld
        La0:
            java.lang.Object[] r1 = r0.content     // Catch: java.lang.Throwable -> L9a
            int r4 = r3 - r6
            r5 = 0
            java.util.Arrays.fill(r1, r4, r3, r5)     // Catch: java.lang.Throwable -> L9a
            r0.size = r4     // Catch: java.lang.Throwable -> L9a
            monitor-exit(r2)
            return
        Lac:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.snapshots.SnapshotStateObserver.clear(java.lang.Object):void");
    }

    public final void clearIf(Function1 function1) {
        synchronized (this.observedScopeMapsLock) {
            try {
                MutableVector mutableVector = this.observedScopeMaps;
                int i = mutableVector.size;
                int i2 = 0;
                for (int i3 = 0; i3 < i; i3++) {
                    ObservedScopeMap observedScopeMap = (ObservedScopeMap) mutableVector.content[i3];
                    observedScopeMap.removeScopeIf(function1);
                    if (!(observedScopeMap.scopeToValues._size != 0)) {
                        i2++;
                    } else if (i2 > 0) {
                        Object[] objArr = mutableVector.content;
                        objArr[i3 - i2] = objArr[i3];
                    }
                }
                int i4 = i - i2;
                Arrays.fill(mutableVector.content, i4, i, (Object) null);
                mutableVector.size = i4;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void observeReads(Object obj, Function1 function1, Function0 function0) {
        Object obj2;
        ObservedScopeMap observedScopeMap;
        synchronized (this.observedScopeMapsLock) {
            MutableVector mutableVector = this.observedScopeMaps;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    obj2 = objArr[i2];
                    if (((ObservedScopeMap) obj2).onChanged == function1) {
                        break;
                    } else {
                        i2++;
                    }
                } while (i2 < i);
            }
            obj2 = null;
            observedScopeMap = (ObservedScopeMap) obj2;
            if (observedScopeMap == null) {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity(1, function1);
                observedScopeMap = new ObservedScopeMap(function1);
                mutableVector.add(observedScopeMap);
            }
        }
        ObservedScopeMap observedScopeMap2 = this.currentMap;
        long j = this.currentMapThreadId;
        if (j != -1 && j != Thread_jvmKt.currentThreadId()) {
            PreconditionsKt.throwIllegalArgumentException("Detected multithreaded access to SnapshotStateObserver: previousThreadId=" + j + "), currentThread={id=" + Thread_jvmKt.currentThreadId() + ", name=" + Thread.currentThread().getName() + "}. Note that observation on multiple threads in layout/draw is not supported. Make sure your measure/layout/draw for each Owner (AndroidComposeView) is executed on the same thread.");
        }
        try {
            this.currentMap = observedScopeMap;
            this.currentMapThreadId = Thread_jvmKt.currentThreadId();
            observedScopeMap.observe(obj, this.readObserver, function0);
        } finally {
            this.currentMap = observedScopeMap2;
            this.currentMapThreadId = j;
        }
    }

    public final void clear() {
        synchronized (this.observedScopeMapsLock) {
            MutableVector mutableVector = this.observedScopeMaps;
            int i = mutableVector.size;
            if (i > 0) {
                Object[] objArr = mutableVector.content;
                int i2 = 0;
                do {
                    ObservedScopeMap observedScopeMap = (ObservedScopeMap) objArr[i2];
                    observedScopeMap.valueToScopes.clear();
                    observedScopeMap.scopeToValues.clear();
                    observedScopeMap.dependencyToDerivedStates.clear();
                    observedScopeMap.recordedDerivedStateValues.clear();
                    i2++;
                } while (i2 < i);
            }
        }
    }
}
