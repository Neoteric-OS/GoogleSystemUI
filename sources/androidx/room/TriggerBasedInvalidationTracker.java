package androidx.room;

import androidx.room.InvalidationTracker;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Pair;
import kotlin.collections.MapsKt;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TriggerBasedInvalidationTracker {
    public static final Companion Companion = null;
    public static final String[] TRIGGERS = {"INSERT", "UPDATE", "DELETE"};
    public final RoomDatabase database;
    public final ObservedTableStates observedTableStates;
    public final Map observerMap;
    public final Map shadowTablesMap;
    public final String[] tablesNames;
    public final Map viewTables;
    public final ReentrantLock observerMapLock = new ReentrantLock();
    public final AtomicBoolean pendingRefresh = AtomicFU.atomic(false);
    public Lambda onAllowRefresh = new Function0() { // from class: androidx.room.TriggerBasedInvalidationTracker$onAllowRefresh$1
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ Object invoke() {
            return Boolean.TRUE;
        }
    };
    public final Map tableIdLookup = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    public TriggerBasedInvalidationTracker(RoomDatabase roomDatabase, Map map, Map map2, String[] strArr) {
        this.database = roomDatabase;
        this.shadowTablesMap = map;
        this.viewTables = map2;
        int length = strArr.length;
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            String str = strArr[i];
            Locale locale = Locale.ROOT;
            String lowerCase = str.toLowerCase(locale);
            this.tableIdLookup.put(lowerCase, Integer.valueOf(i));
            String str2 = (String) this.shadowTablesMap.get(strArr[i]);
            String lowerCase2 = str2 != null ? str2.toLowerCase(locale) : null;
            if (lowerCase2 != null) {
                lowerCase = lowerCase2;
            }
            strArr2[i] = lowerCase;
        }
        this.tablesNames = strArr2;
        for (Map.Entry entry : ((HashMap) this.shadowTablesMap).entrySet()) {
            String str3 = (String) entry.getValue();
            Locale locale2 = Locale.ROOT;
            String lowerCase3 = str3.toLowerCase(locale2);
            if (this.tableIdLookup.containsKey(lowerCase3)) {
                String lowerCase4 = ((String) entry.getKey()).toLowerCase(locale2);
                Map map3 = this.tableIdLookup;
                map3.put(lowerCase4, MapsKt.getValue(lowerCase3, map3));
            }
        }
        this.observerMap = new LinkedHashMap();
        this.observedTableStates = new ObservedTableStates(this.tablesNames.length);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$checkInvalidatedTables(androidx.room.TriggerBasedInvalidationTracker r4, androidx.room.PooledConnection r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4.getClass()
            boolean r0 = r6 instanceof androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1
            if (r0 == 0) goto L16
            r0 = r6
            androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$1
            r0.<init>(r4, r6)
        L1b:
            java.lang.Object r4 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r6 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r1 = r0.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L41
            if (r1 == r3) goto L39
            if (r1 != r2) goto L31
            java.lang.Object r5 = r0.L$0
            java.util.Set r5 = (java.util.Set) r5
            kotlin.ResultKt.throwOnFailure(r4)
            goto L6c
        L31:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L39:
            java.lang.Object r5 = r0.L$0
            androidx.room.PooledConnection r5 = (androidx.room.PooledConnection) r5
            kotlin.ResultKt.throwOnFailure(r4)
            goto L53
        L41:
            kotlin.ResultKt.throwOnFailure(r4)
            androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1 r4 = new kotlin.jvm.functions.Function1() { // from class: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1
                static {
                    /*
                        androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1) androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1.INSTANCE androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Object invoke(java.lang.Object r3) {
                    /*
                        r2 = this;
                        androidx.sqlite.SQLiteStatement r3 = (androidx.sqlite.SQLiteStatement) r3
                        kotlin.collections.builders.SetBuilder r2 = new kotlin.collections.builders.SetBuilder
                        r2.<init>()
                    L7:
                        boolean r0 = r3.step()
                        if (r0 == 0) goto L1b
                        r0 = 0
                        long r0 = r3.getLong(r0)
                        int r0 = (int) r0
                        java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                        r2.add(r0)
                        goto L7
                    L1b:
                        kotlin.collections.builders.SetBuilder r2 = r2.build()
                        return r2
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker$checkInvalidatedTables$invalidatedTableIds$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r0.L$0 = r5
            r0.label = r3
            java.lang.String r1 = "SELECT * FROM room_table_modification_log WHERE invalidated = 1"
            java.lang.Object r4 = r5.usePrepared(r1, r4, r0)
            if (r4 != r6) goto L53
            goto L6f
        L53:
            java.util.Set r4 = (java.util.Set) r4
            r1 = r4
            java.util.Collection r1 = (java.util.Collection) r1
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L6e
            r0.L$0 = r4
            r0.label = r2
            java.lang.String r1 = "UPDATE room_table_modification_log SET invalidated = 0 WHERE invalidated = 1"
            java.lang.Object r5 = androidx.room.TransactorKt.execSQL(r5, r1, r0)
            if (r5 != r6) goto L6b
            goto L6f
        L6b:
            r5 = r4
        L6c:
            r6 = r5
            goto L6f
        L6e:
            r6 = r4
        L6f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.access$checkInvalidatedTables(androidx.room.TriggerBasedInvalidationTracker, androidx.room.PooledConnection, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /* JADX WARN: Type inference failed for: r5v3, types: [kotlin.jvm.functions.Function0, kotlin.jvm.internal.Lambda] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$notifyInvalidatedObservers(androidx.room.TriggerBasedInvalidationTracker r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            Method dump skipped, instructions count: 211
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.access$notifyInvalidatedObservers(androidx.room.TriggerBasedInvalidationTracker, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r13v7, types: [androidx.room.PooledConnection] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x00d4 -> B:11:0x00d7). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$startTrackingTable(androidx.room.TriggerBasedInvalidationTracker r12, androidx.room.Transactor r13, int r14, kotlin.coroutines.jvm.internal.ContinuationImpl r15) {
        /*
            Method dump skipped, instructions count: 220
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.access$startTrackingTable(androidx.room.TriggerBasedInvalidationTracker, androidx.room.Transactor, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* JADX WARN: Type inference failed for: r4v6, types: [androidx.room.PooledConnection] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0093 -> B:10:0x0096). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object access$stopTrackingTable(androidx.room.TriggerBasedInvalidationTracker r9, androidx.room.Transactor r10, int r11, kotlin.coroutines.jvm.internal.ContinuationImpl r12) {
        /*
            r9.getClass()
            boolean r0 = r12 instanceof androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1
            if (r0 == 0) goto L16
            r0 = r12
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L16
            int r1 = r1 - r2
            r0.label = r1
            goto L1b
        L16:
            androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$stopTrackingTable$1
            r0.<init>(r9, r12)
        L1b:
            java.lang.Object r12 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L44
            if (r2 != r3) goto L3c
            int r9 = r0.I$1
            int r10 = r0.I$0
            java.lang.Object r11 = r0.L$2
            java.lang.String[] r11 = (java.lang.String[]) r11
            java.lang.Object r2 = r0.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r0.L$0
            androidx.room.PooledConnection r4 = (androidx.room.PooledConnection) r4
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r11
            r11 = r4
            goto L96
        L3c:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L44:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.String[] r9 = r9.tablesNames
            r9 = r9[r11]
            java.lang.String[] r11 = androidx.room.TriggerBasedInvalidationTracker.TRIGGERS
            r12 = 0
            r2 = 3
            r7 = r2
            r2 = r9
            r9 = r7
            r8 = r11
            r11 = r10
            r10 = r12
            r12 = r8
        L56:
            if (r10 >= r9) goto L98
            r4 = r12[r10]
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "room_table_modification_trigger_"
            r5.<init>(r6)
            r5.append(r2)
            r6 = 95
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "DROP TRIGGER IF EXISTS `"
            r5.<init>(r6)
            r5.append(r4)
            r4 = 96
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            r0.L$0 = r11
            r0.L$1 = r2
            r0.L$2 = r12
            r0.I$0 = r10
            r0.I$1 = r9
            r0.label = r3
            java.lang.Object r4 = androidx.room.TransactorKt.execSQL(r11, r4, r0)
            if (r4 != r1) goto L96
            goto L9a
        L96:
            int r10 = r10 + r3
            goto L56
        L98:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L9a:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.access$stopTrackingTable(androidx.room.TriggerBasedInvalidationTracker, androidx.room.Transactor, int, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object addObserver$room_runtime_release(androidx.room.InvalidationTracker.Observer r5, kotlin.coroutines.jvm.internal.ContinuationImpl r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof androidx.room.TriggerBasedInvalidationTracker$addObserver$1
            if (r0 == 0) goto L13
            r0 = r6
            androidx.room.TriggerBasedInvalidationTracker$addObserver$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$addObserver$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$addObserver$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$addObserver$1
            r0.<init>(r4, r6)
        L18:
            java.lang.Object r6 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L31
            if (r2 != r3) goto L29
            boolean r4 = r0.Z$0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L46
        L29:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L31:
            kotlin.ResultKt.throwOnFailure(r6)
            boolean r5 = r4.addObserverOnly$room_runtime_release(r5)
            if (r5 == 0) goto L47
            r0.Z$0 = r5
            r0.label = r3
            java.lang.Object r4 = r4.syncTriggers$room_runtime_release(r0)
            if (r4 != r1) goto L45
            return r1
        L45:
            r4 = r5
        L46:
            r5 = r4
        L47:
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.addObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public final boolean addObserverOnly$room_runtime_release(InvalidationTracker.Observer observer) {
        SetBuilder setBuilder = new SetBuilder();
        for (String str : observer.tables) {
            Set set = (Set) this.viewTables.get(str.toLowerCase(Locale.ROOT));
            if (set != null) {
                setBuilder.addAll(set);
            } else {
                setBuilder.add(str);
            }
        }
        String[] strArr = (String[]) setBuilder.build().toArray(new String[0]);
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            String str2 = strArr[i];
            Integer num = (Integer) this.tableIdLookup.get(str2.toLowerCase(Locale.ROOT));
            if (num == null) {
                throw new IllegalArgumentException("There is no table with name ".concat(str2));
            }
            iArr[i] = num.intValue();
        }
        Pair pair = new Pair(strArr, iArr);
        String[] strArr2 = (String[]) pair.component1();
        int[] iArr2 = (int[]) pair.component2();
        ObserverWrapper observerWrapper = new ObserverWrapper(observer, iArr2, strArr2);
        ReentrantLock reentrantLock = this.observerMapLock;
        reentrantLock.lock();
        try {
            ObserverWrapper observerWrapper2 = this.observerMap.containsKey(observer) ? (ObserverWrapper) MapsKt.getValue(observer, this.observerMap) : (ObserverWrapper) this.observerMap.put(observer, observerWrapper);
            reentrantLock.unlock();
            if (observerWrapper2 != null) {
                return false;
            }
            ObservedTableStates observedTableStates = this.observedTableStates;
            ReentrantLock reentrantLock2 = observedTableStates.lock;
            reentrantLock2.lock();
            try {
                boolean z = false;
                for (int i2 : iArr2) {
                    long[] jArr = observedTableStates.tableObserversCount;
                    long j = jArr[i2];
                    jArr[i2] = 1 + j;
                    if (j == 0) {
                        observedTableStates.needsSync = true;
                        z = true;
                    }
                }
                return z;
            } finally {
                reentrantLock2.unlock();
            }
        } catch (Throwable th) {
            reentrantLock.unlock();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object removeObserver$room_runtime_release(androidx.room.InvalidationTracker.Observer r20, kotlin.coroutines.jvm.internal.ContinuationImpl r21) {
        /*
            r19 = this;
            r0 = r19
            r1 = r21
            boolean r2 = r1 instanceof androidx.room.TriggerBasedInvalidationTracker$removeObserver$1
            if (r2 == 0) goto L17
            r2 = r1
            androidx.room.TriggerBasedInvalidationTracker$removeObserver$1 r2 = (androidx.room.TriggerBasedInvalidationTracker$removeObserver$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            androidx.room.TriggerBasedInvalidationTracker$removeObserver$1 r2 = new androidx.room.TriggerBasedInvalidationTracker$removeObserver$1
            r2.<init>(r0, r1)
        L1c:
            java.lang.Object r1 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L35
            if (r4 != r5) goto L2d
            boolean r0 = r2.Z$0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L8d
        L2d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L35:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.concurrent.locks.ReentrantLock r1 = r0.observerMapLock
            r1.lock()
            java.util.Map r4 = r0.observerMap     // Catch: java.lang.Throwable -> L93
            r6 = r20
            java.lang.Object r4 = r4.remove(r6)     // Catch: java.lang.Throwable -> L93
            androidx.room.ObserverWrapper r4 = (androidx.room.ObserverWrapper) r4     // Catch: java.lang.Throwable -> L93
            r1.unlock()
            r1 = 0
            if (r4 == 0) goto L7f
            int[] r4 = r4.tableIds
            androidx.room.ObservedTableStates r6 = r0.observedTableStates
            java.util.concurrent.locks.ReentrantLock r7 = r6.lock
            r7.lock()
            int r8 = r4.length     // Catch: java.lang.Throwable -> L6f
            r9 = r1
            r10 = r9
        L59:
            if (r9 >= r8) goto L74
            r11 = r4[r9]     // Catch: java.lang.Throwable -> L6f
            long[] r12 = r6.tableObserversCount     // Catch: java.lang.Throwable -> L6f
            r13 = r12[r11]     // Catch: java.lang.Throwable -> L6f
            r15 = 1
            long r17 = r13 - r15
            r12[r11] = r17     // Catch: java.lang.Throwable -> L6f
            int r11 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r11 != 0) goto L71
            r6.needsSync = r5     // Catch: java.lang.Throwable -> L6f
            r10 = r5
            goto L71
        L6f:
            r0 = move-exception
            goto L7b
        L71:
            int r9 = r9 + 1
            goto L59
        L74:
            r7.unlock()
            if (r10 == 0) goto L7f
            r1 = r5
            goto L7f
        L7b:
            r7.unlock()
            throw r0
        L7f:
            if (r1 == 0) goto L8e
            r2.Z$0 = r1
            r2.label = r5
            java.lang.Object r0 = r0.syncTriggers$room_runtime_release(r2)
            if (r0 != r3) goto L8c
            return r3
        L8c:
            r0 = r1
        L8d:
            r1 = r0
        L8e:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        L93:
            r0 = move-exception
            r1.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.removeObserver$room_runtime_release(androidx.room.InvalidationTracker$Observer, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object syncTriggers$room_runtime_release(kotlin.coroutines.jvm.internal.ContinuationImpl r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1
            if (r0 == 0) goto L13
            r0 = r7
            androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1 r0 = (androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1 r0 = new androidx.room.TriggerBasedInvalidationTracker$syncTriggers$1
            r0.<init>(r6, r7)
        L18:
            java.lang.Object r7 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L35
            if (r2 != r3) goto L2d
            java.lang.Object r6 = r0.L$0
            androidx.room.concurrent.CloseBarrier r6 = (androidx.room.concurrent.CloseBarrier) r6
            kotlin.ResultKt.throwOnFailure(r7)     // Catch: java.lang.Throwable -> L2b
            goto L5d
        L2b:
            r7 = move-exception
            goto L69
        L2d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L35:
            kotlin.ResultKt.throwOnFailure(r7)
            androidx.room.RoomDatabase r7 = r6.database
            androidx.room.concurrent.CloseBarrier r2 = r7.closeBarrier
            boolean r4 = r2.block$room_runtime_release()
            if (r4 == 0) goto L6d
            androidx.room.TriggerBasedInvalidationTracker$syncTriggers$2$1 r4 = new androidx.room.TriggerBasedInvalidationTracker$syncTriggers$2$1     // Catch: java.lang.Throwable -> L67
            r5 = 0
            r4.<init>(r6, r5)     // Catch: java.lang.Throwable -> L67
            r0.L$0 = r2     // Catch: java.lang.Throwable -> L67
            r0.label = r3     // Catch: java.lang.Throwable -> L67
            androidx.room.RoomConnectionManager r6 = r7.connectionManager     // Catch: java.lang.Throwable -> L63
            if (r6 != 0) goto L51
            goto L52
        L51:
            r5 = r6
        L52:
            androidx.room.coroutines.ConnectionPool r6 = r5.connectionPool     // Catch: java.lang.Throwable -> L63
            r7 = 0
            java.lang.Object r6 = r6.useConnection(r7, r4, r0)     // Catch: java.lang.Throwable -> L63
            if (r6 != r1) goto L5c
            return r1
        L5c:
            r6 = r2
        L5d:
            r6.unblock$room_runtime_release()
            goto L6d
        L61:
            r7 = r6
            goto L65
        L63:
            r6 = move-exception
            goto L61
        L65:
            r6 = r2
            goto L69
        L67:
            r7 = move-exception
            goto L65
        L69:
            r6.unblock$room_runtime_release()
            throw r7
        L6d:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.TriggerBasedInvalidationTracker.syncTriggers$room_runtime_release(kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }
}
