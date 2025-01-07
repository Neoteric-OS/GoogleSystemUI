package androidx.room.coroutines;

import androidx.room.TransactionScope;
import androidx.room.Transactor;
import androidx.room.concurrent.ThreadLocal_jvmAndroidKt;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.collections.ArrayDeque;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PooledConnectionImpl implements Transactor, RawConnectionAccessor {
    public final ConnectionWithLock delegate;
    public final boolean isReadOnly;
    public final ArrayDeque transactionStack = new ArrayDeque();
    public final AtomicBoolean _isRecycled = AtomicFU.atomic(false);

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class StatementWrapper implements SQLiteStatement {
        public final SQLiteStatement delegate;
        public final long threadId = ThreadLocal_jvmAndroidKt.currentThreadId();

        public StatementWrapper(SQLiteStatement sQLiteStatement) {
            this.delegate = sQLiteStatement;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void bindLong(long j, int i) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                this.delegate.bindLong(j, i);
            } else {
                SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
                throw null;
            }
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void bindNull() {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                this.delegate.bindNull();
            } else {
                SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
                throw null;
            }
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void bindText(String str) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                this.delegate.bindText(str);
            } else {
                SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
                throw null;
            }
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void close() {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                this.delegate.close();
            } else {
                SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
                throw null;
            }
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final int getColumnCount() {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.getColumnCount();
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getColumnName(int i) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.getColumnName(i);
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final long getLong(int i) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.getLong(i);
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final String getText(int i) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.getText(i);
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean isNull(int i) {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.isNull(i);
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final void reset() {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                this.delegate.reset();
            } else {
                SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
                throw null;
            }
        }

        @Override // androidx.sqlite.SQLiteStatement
        public final boolean step() {
            if (PooledConnectionImpl.this._isRecycled.getValue()) {
                SQLite.throwSQLiteException(21, "Statement is recycled");
                throw null;
            }
            if (this.threadId == ThreadLocal_jvmAndroidKt.currentThreadId()) {
                return this.delegate.step();
            }
            SQLite.throwSQLiteException(21, "Attempted to use statement on a different thread");
            throw null;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransactionImpl implements TransactionScope, RawConnectionAccessor {
        public TransactionImpl() {
        }

        @Override // androidx.room.coroutines.RawConnectionAccessor
        public final SQLiteConnection getRawConnection() {
            return PooledConnectionImpl.this.delegate;
        }

        @Override // androidx.room.PooledConnection
        public final Object usePrepared(String str, Function1 function1, ContinuationImpl continuationImpl) {
            return PooledConnectionImpl.this.usePrepared(str, function1, continuationImpl);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class TransactionItem {
        public final int id;

        public TransactionItem(int i) {
            this.id = i;
        }
    }

    public PooledConnectionImpl(ConnectionWithLock connectionWithLock, boolean z) {
        this.delegate = connectionWithLock;
        this.isReadOnly = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0062 A[Catch: all -> 0x0074, TRY_ENTER, TryCatch #0 {all -> 0x0074, blocks: (B:12:0x0056, B:15:0x0062, B:21:0x0096, B:26:0x006e, B:27:0x0076, B:28:0x007c, B:29:0x0082), top: B:11:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0082 A[Catch: all -> 0x0074, TryCatch #0 {all -> 0x0074, blocks: (B:12:0x0056, B:15:0x0062, B:21:0x0096, B:26:0x006e, B:27:0x0076, B:28:0x007c, B:29:0x0082), top: B:11:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0023  */
    /* JADX WARN: Type inference failed for: r6v8, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object beginTransaction(androidx.room.Transactor.SQLiteTransactionType r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6 = this;
            java.lang.String r0 = "SAVEPOINT '"
            boolean r1 = r8 instanceof androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1
            if (r1 == 0) goto L15
            r1 = r8
            androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1 r1 = (androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r2 & r3
            if (r4 == 0) goto L15
            int r2 = r2 - r3
            r1.label = r2
            goto L1a
        L15:
            androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1 r1 = new androidx.room.coroutines.PooledConnectionImpl$beginTransaction$1
            r1.<init>(r6, r8)
        L1a:
            java.lang.Object r8 = r1.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r2 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r3 = r1.label
            r4 = 1
            if (r3 == 0) goto L3f
            if (r3 != r4) goto L37
            java.lang.Object r6 = r1.L$2
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r1.L$1
            androidx.room.Transactor$SQLiteTransactionType r7 = (androidx.room.Transactor.SQLiteTransactionType) r7
            java.lang.Object r1 = r1.L$0
            androidx.room.coroutines.PooledConnectionImpl r1 = (androidx.room.coroutines.PooledConnectionImpl) r1
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r6
            r6 = r1
            goto L55
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            r1.L$0 = r6
            r1.L$1 = r7
            androidx.room.coroutines.ConnectionWithLock r8 = r6.delegate
            r1.L$2 = r8
            r1.label = r4
            kotlinx.coroutines.sync.MutexImpl r3 = r8.lock
            java.lang.Object r1 = r3.lock(r1)
            if (r1 != r2) goto L55
            return r2
        L55:
            r1 = 0
            kotlin.collections.ArrayDeque r2 = r6.transactionStack     // Catch: java.lang.Throwable -> L74
            int r3 = r2.size     // Catch: java.lang.Throwable -> L74
            boolean r5 = r2.isEmpty()     // Catch: java.lang.Throwable -> L74
            androidx.room.coroutines.ConnectionWithLock r6 = r6.delegate
            if (r5 == 0) goto L82
            int r7 = r7.ordinal()     // Catch: java.lang.Throwable -> L74
            if (r7 == 0) goto L7c
            if (r7 == r4) goto L76
            r0 = 2
            if (r7 == r0) goto L6e
            goto L96
        L6e:
            java.lang.String r7 = "BEGIN EXCLUSIVE TRANSACTION"
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L74
            goto L96
        L74:
            r6 = move-exception
            goto La4
        L76:
            java.lang.String r7 = "BEGIN IMMEDIATE TRANSACTION"
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L74
            goto L96
        L7c:
            java.lang.String r7 = "BEGIN DEFERRED TRANSACTION"
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L74
            goto L96
        L82:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L74
            r7.<init>(r0)     // Catch: java.lang.Throwable -> L74
            r7.append(r3)     // Catch: java.lang.Throwable -> L74
            r0 = 39
            r7.append(r0)     // Catch: java.lang.Throwable -> L74
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L74
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L74
        L96:
            androidx.room.coroutines.PooledConnectionImpl$TransactionItem r6 = new androidx.room.coroutines.PooledConnectionImpl$TransactionItem     // Catch: java.lang.Throwable -> L74
            r6.<init>(r3)     // Catch: java.lang.Throwable -> L74
            r2.addLast(r6)     // Catch: java.lang.Throwable -> L74
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L74
            r8.unlock(r1)
            return r6
        La4:
            r8.unlock(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.PooledConnectionImpl.beginTransaction(androidx.room.Transactor$SQLiteTransactionType, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x005e A[Catch: all -> 0x0079, TRY_LEAVE, TryCatch #0 {all -> 0x0079, blocks: (B:12:0x0056, B:14:0x005e, B:17:0x006a, B:19:0x0073, B:20:0x00b0, B:24:0x007b, B:25:0x0090, B:27:0x0096, B:28:0x009c, B:29:0x00b6, B:30:0x00bd), top: B:11:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00b6 A[Catch: all -> 0x0079, TRY_ENTER, TryCatch #0 {all -> 0x0079, blocks: (B:12:0x0056, B:14:0x005e, B:17:0x006a, B:19:0x0073, B:20:0x00b0, B:24:0x007b, B:25:0x0090, B:27:0x0096, B:28:0x009c, B:29:0x00b6, B:30:0x00bd), top: B:11:0x0056 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARN: Type inference failed for: r6v8, types: [kotlinx.coroutines.sync.Mutex] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object endTransaction(boolean r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r6 = this;
            java.lang.String r0 = "ROLLBACK TRANSACTION TO SAVEPOINT '"
            java.lang.String r1 = "RELEASE SAVEPOINT '"
            boolean r2 = r8 instanceof androidx.room.coroutines.PooledConnectionImpl$endTransaction$1
            if (r2 == 0) goto L17
            r2 = r8
            androidx.room.coroutines.PooledConnectionImpl$endTransaction$1 r2 = (androidx.room.coroutines.PooledConnectionImpl$endTransaction$1) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = r3 & r4
            if (r5 == 0) goto L17
            int r3 = r3 - r4
            r2.label = r3
            goto L1c
        L17:
            androidx.room.coroutines.PooledConnectionImpl$endTransaction$1 r2 = new androidx.room.coroutines.PooledConnectionImpl$endTransaction$1
            r2.<init>(r6, r8)
        L1c:
            java.lang.Object r8 = r2.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r3 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r4 = r2.label
            r5 = 1
            if (r4 == 0) goto L3f
            if (r4 != r5) goto L37
            boolean r7 = r2.Z$0
            java.lang.Object r6 = r2.L$1
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r2 = r2.L$0
            androidx.room.coroutines.PooledConnectionImpl r2 = (androidx.room.coroutines.PooledConnectionImpl) r2
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r6
            r6 = r2
            goto L55
        L37:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L3f:
            kotlin.ResultKt.throwOnFailure(r8)
            r2.L$0 = r6
            androidx.room.coroutines.ConnectionWithLock r8 = r6.delegate
            r2.L$1 = r8
            r2.Z$0 = r7
            r2.label = r5
            kotlinx.coroutines.sync.MutexImpl r4 = r8.lock
            java.lang.Object r2 = r4.lock(r2)
            if (r2 != r3) goto L55
            return r3
        L55:
            r2 = 0
            kotlin.collections.ArrayDeque r3 = r6.transactionStack     // Catch: java.lang.Throwable -> L79
            boolean r4 = r3.isEmpty()     // Catch: java.lang.Throwable -> L79
            if (r4 != 0) goto Lb6
            java.lang.Object r4 = r3.removeLast()     // Catch: java.lang.Throwable -> L79
            androidx.room.coroutines.PooledConnectionImpl$TransactionItem r4 = (androidx.room.coroutines.PooledConnectionImpl.TransactionItem) r4     // Catch: java.lang.Throwable -> L79
            androidx.room.coroutines.ConnectionWithLock r6 = r6.delegate
            r5 = 39
            if (r7 == 0) goto L90
            r4.getClass()     // Catch: java.lang.Throwable -> L79
            boolean r7 = r3.isEmpty()     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L7b
            java.lang.String r7 = "END TRANSACTION"
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L79
            goto Lb0
        L79:
            r6 = move-exception
            goto Lbe
        L7b:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r7.<init>(r1)     // Catch: java.lang.Throwable -> L79
            int r0 = r4.id     // Catch: java.lang.Throwable -> L79
            r7.append(r0)     // Catch: java.lang.Throwable -> L79
            r7.append(r5)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L79
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L79
            goto Lb0
        L90:
            boolean r7 = r3.isEmpty()     // Catch: java.lang.Throwable -> L79
            if (r7 == 0) goto L9c
            java.lang.String r7 = "ROLLBACK TRANSACTION"
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L79
            goto Lb0
        L9c:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L79
            r7.<init>(r0)     // Catch: java.lang.Throwable -> L79
            int r0 = r4.id     // Catch: java.lang.Throwable -> L79
            r7.append(r0)     // Catch: java.lang.Throwable -> L79
            r7.append(r5)     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L79
            androidx.sqlite.SQLite.execSQL(r6, r7)     // Catch: java.lang.Throwable -> L79
        Lb0:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L79
            r8.unlock(r2)
            return r6
        Lb6:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L79
            java.lang.String r7 = "Not in a transaction"
            r6.<init>(r7)     // Catch: java.lang.Throwable -> L79
            throw r6     // Catch: java.lang.Throwable -> L79
        Lbe:
            r8.unlock(r2)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.PooledConnectionImpl.endTransaction(boolean, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.room.coroutines.RawConnectionAccessor
    public final SQLiteConnection getRawConnection() {
        return this.delegate;
    }

    @Override // androidx.room.Transactor
    public final Object inTransaction(SuspendLambda suspendLambda) {
        if (this._isRecycled.getValue()) {
            SQLite.throwSQLiteException(21, "Connection is recycled");
            throw null;
        }
        ConnectionElement connectionElement = (ConnectionElement) suspendLambda.getContext().get(ConnectionElement.Key);
        if (connectionElement != null && connectionElement.connectionWrapper == this) {
            return Boolean.valueOf(!this.transactionStack.isEmpty());
        }
        SQLite.throwSQLiteException(21, "Attempted to use connection on a different coroutine");
        throw null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00cc  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a8 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0098 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object transaction$1(androidx.room.Transactor.SQLiteTransactionType r11, kotlin.jvm.functions.Function2 r12, kotlin.coroutines.jvm.internal.ContinuationImpl r13) {
        /*
            Method dump skipped, instructions count: 206
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.PooledConnectionImpl.transaction$1(androidx.room.Transactor$SQLiteTransactionType, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /* JADX WARN: Type inference failed for: r6v9, types: [kotlinx.coroutines.sync.Mutex] */
    @Override // androidx.room.PooledConnection
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object usePrepared(java.lang.String r7, kotlin.jvm.functions.Function1 r8, kotlin.coroutines.jvm.internal.ContinuationImpl r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof androidx.room.coroutines.PooledConnectionImpl$usePrepared$1
            if (r0 == 0) goto L13
            r0 = r9
            androidx.room.coroutines.PooledConnectionImpl$usePrepared$1 r0 = (androidx.room.coroutines.PooledConnectionImpl$usePrepared$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.coroutines.PooledConnectionImpl$usePrepared$1 r0 = new androidx.room.coroutines.PooledConnectionImpl$usePrepared$1
            r0.<init>(r6, r9)
        L18:
            java.lang.Object r9 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L43
            if (r2 != r4) goto L3b
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r0.L$2
            r8 = r7
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r0 = r0.L$0
            androidx.room.coroutines.PooledConnectionImpl r0 = (androidx.room.coroutines.PooledConnectionImpl) r0
            kotlin.ResultKt.throwOnFailure(r9)
            r9 = r6
            r6 = r0
            goto L77
        L3b:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L43:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.atomicfu.AtomicBoolean r9 = r6._isRecycled
            boolean r9 = r9.getValue()
            r2 = 21
            if (r9 != 0) goto La0
            kotlin.coroutines.CoroutineContext r9 = r0.getContext()
            androidx.room.coroutines.ConnectionElement$Key r5 = androidx.room.coroutines.ConnectionElement.Key
            kotlin.coroutines.CoroutineContext$Element r9 = r9.get(r5)
            androidx.room.coroutines.ConnectionElement r9 = (androidx.room.coroutines.ConnectionElement) r9
            if (r9 == 0) goto L9a
            androidx.room.coroutines.PooledConnectionImpl r9 = r9.connectionWrapper
            if (r9 != r6) goto L9a
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r8
            androidx.room.coroutines.ConnectionWithLock r9 = r6.delegate
            r0.L$3 = r9
            r0.label = r4
            kotlinx.coroutines.sync.MutexImpl r2 = r9.lock
            java.lang.Object r0 = r2.lock(r0)
            if (r0 != r1) goto L77
            return r1
        L77:
            androidx.room.coroutines.PooledConnectionImpl$StatementWrapper r0 = new androidx.room.coroutines.PooledConnectionImpl$StatementWrapper     // Catch: java.lang.Throwable -> L8f
            androidx.room.coroutines.ConnectionWithLock r1 = r6.delegate     // Catch: java.lang.Throwable -> L8f
            androidx.sqlite.SQLiteConnection r1 = r1.delegate     // Catch: java.lang.Throwable -> L8f
            androidx.sqlite.SQLiteStatement r7 = r1.prepare(r7)     // Catch: java.lang.Throwable -> L8f
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L8f
            java.lang.Object r6 = r8.invoke(r0)     // Catch: java.lang.Throwable -> L91
            r0.close()     // Catch: java.lang.Throwable -> L8f
            r9.unlock(r3)
            return r6
        L8f:
            r6 = move-exception
            goto L96
        L91:
            r6 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L8f
            throw r6     // Catch: java.lang.Throwable -> L8f
        L96:
            r9.unlock(r3)
            throw r6
        L9a:
            java.lang.String r6 = "Attempted to use connection on a different coroutine"
            androidx.sqlite.SQLite.throwSQLiteException(r2, r6)
            throw r3
        La0:
            java.lang.String r6 = "Connection is recycled"
            androidx.sqlite.SQLite.throwSQLiteException(r2, r6)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.PooledConnectionImpl.usePrepared(java.lang.String, kotlin.jvm.functions.Function1, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.room.Transactor
    public final Object withTransaction(Transactor.SQLiteTransactionType sQLiteTransactionType, Function2 function2, SuspendLambda suspendLambda) {
        if (this._isRecycled.getValue()) {
            SQLite.throwSQLiteException(21, "Connection is recycled");
            throw null;
        }
        ConnectionElement connectionElement = (ConnectionElement) suspendLambda.getContext().get(ConnectionElement.Key);
        if (connectionElement != null && connectionElement.connectionWrapper == this) {
            return transaction$1(sQLiteTransactionType, function2, suspendLambda);
        }
        SQLite.throwSQLiteException(21, "Attempted to use connection on a different coroutine");
        throw null;
    }
}
