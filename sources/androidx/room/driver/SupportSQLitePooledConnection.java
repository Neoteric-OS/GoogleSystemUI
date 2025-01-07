package androidx.room.driver;

import androidx.room.TransactionScope;
import androidx.room.Transactor;
import androidx.room.coroutines.RawConnectionAccessor;
import androidx.sqlite.SQLiteConnection;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SupportSQLitePooledConnection implements Transactor, RawConnectionAccessor {
    public final SupportSQLiteConnection delegate;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SupportSQLiteTransactor implements TransactionScope, RawConnectionAccessor {
        public SupportSQLiteTransactor() {
        }

        @Override // androidx.room.coroutines.RawConnectionAccessor
        public final SQLiteConnection getRawConnection() {
            return SupportSQLitePooledConnection.this.delegate;
        }

        @Override // androidx.room.PooledConnection
        public final Object usePrepared(String str, Function1 function1, ContinuationImpl continuationImpl) {
            return SupportSQLitePooledConnection.this.usePrepared(str, function1, continuationImpl);
        }
    }

    public SupportSQLitePooledConnection(SupportSQLiteConnection supportSQLiteConnection) {
        this.delegate = supportSQLiteConnection;
    }

    @Override // androidx.room.coroutines.RawConnectionAccessor
    public final SQLiteConnection getRawConnection() {
        return this.delegate;
    }

    @Override // androidx.room.Transactor
    public final Object inTransaction(SuspendLambda suspendLambda) {
        return Boolean.valueOf(this.delegate.db.inTransaction());
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object transaction$2(androidx.room.Transactor.SQLiteTransactionType r6, kotlin.jvm.functions.Function2 r7, kotlin.coroutines.jvm.internal.ContinuationImpl r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof androidx.room.driver.SupportSQLitePooledConnection$transaction$1
            if (r0 == 0) goto L13
            r0 = r8
            androidx.room.driver.SupportSQLitePooledConnection$transaction$1 r0 = (androidx.room.driver.SupportSQLitePooledConnection$transaction$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            androidx.room.driver.SupportSQLitePooledConnection$transaction$1 r0 = new androidx.room.driver.SupportSQLitePooledConnection$transaction$1
            r0.<init>(r5, r8)
        L18:
            java.lang.Object r8 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L39
            if (r2 != r3) goto L31
            java.lang.Object r5 = r0.L$1
            androidx.sqlite.db.SupportSQLiteDatabase r5 = (androidx.sqlite.db.SupportSQLiteDatabase) r5
            java.lang.Object r6 = r0.L$0
            androidx.room.driver.SupportSQLitePooledConnection r6 = (androidx.room.driver.SupportSQLitePooledConnection) r6
            kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.lang.Throwable -> L2f
            goto L70
        L2f:
            r7 = move-exception
            goto L83
        L31:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L39:
            kotlin.ResultKt.throwOnFailure(r8)
            androidx.room.driver.SupportSQLiteConnection r8 = r5.delegate
            androidx.sqlite.db.SupportSQLiteDatabase r8 = r8.db
            r8.inTransaction()
            int r6 = r6.ordinal()
            if (r6 == 0) goto L57
            if (r6 == r3) goto L53
            r2 = 2
            if (r6 == r2) goto L4f
            goto L5a
        L4f:
            r8.beginTransaction()
            goto L5a
        L53:
            r8.beginTransactionNonExclusive()
            goto L5a
        L57:
            r8.beginTransactionReadOnly()
        L5a:
            androidx.room.driver.SupportSQLitePooledConnection$SupportSQLiteTransactor r6 = new androidx.room.driver.SupportSQLitePooledConnection$SupportSQLiteTransactor     // Catch: java.lang.Throwable -> L80
            r6.<init>()     // Catch: java.lang.Throwable -> L80
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L80
            r0.L$1 = r8     // Catch: java.lang.Throwable -> L80
            r0.label = r3     // Catch: java.lang.Throwable -> L80
            java.lang.Object r6 = r7.invoke(r6, r0)     // Catch: java.lang.Throwable -> L80
            if (r6 != r1) goto L6c
            return r1
        L6c:
            r4 = r6
            r6 = r5
            r5 = r8
            r8 = r4
        L70:
            r5.setTransactionSuccessful()     // Catch: java.lang.Throwable -> L2f
            r5.endTransaction()
            boolean r5 = r5.inTransaction()
            if (r5 != 0) goto L7f
            r6.getClass()
        L7f:
            return r8
        L80:
            r7 = move-exception
            r6 = r5
            r5 = r8
        L83:
            r5.endTransaction()
            boolean r5 = r5.inTransaction()
            if (r5 != 0) goto L8f
            r6.getClass()
        L8f:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.driver.SupportSQLitePooledConnection.transaction$2(androidx.room.Transactor$SQLiteTransactionType, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    @Override // androidx.room.PooledConnection
    public final Object usePrepared(String str, Function1 function1, ContinuationImpl continuationImpl) {
        SupportSQLiteStatement prepare = this.delegate.prepare(str);
        try {
            return function1.invoke(prepare);
        } finally {
            prepare.close();
        }
    }

    @Override // androidx.room.Transactor
    public final Object withTransaction(Transactor.SQLiteTransactionType sQLiteTransactionType, Function2 function2, SuspendLambda suspendLambda) {
        return transaction$2(sQLiteTransactionType, function2, suspendLambda);
    }
}
