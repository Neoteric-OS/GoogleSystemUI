package androidx.room.driver;

import androidx.room.coroutines.ConnectionPool;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SupportSQLiteConnectionPool implements ConnectionPool {
    public final Lazy supportConnection$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: androidx.room.driver.SupportSQLiteConnectionPool$supportConnection$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            SupportSQLiteConnectionPool.this.supportDriver.openHelper.getClass();
            return new SupportSQLitePooledConnection(new SupportSQLiteConnection(SupportSQLiteConnectionPool.this.supportDriver.openHelper.getWritableDatabase()));
        }
    });
    public final SupportSQLiteDriver supportDriver;

    public SupportSQLiteConnectionPool(SupportSQLiteDriver supportSQLiteDriver) {
        this.supportDriver = supportSQLiteDriver;
    }

    @Override // androidx.room.coroutines.ConnectionPool
    public final void close() {
        this.supportDriver.openHelper.close();
    }

    @Override // androidx.room.coroutines.ConnectionPool
    public final Object useConnection(boolean z, Function2 function2, ContinuationImpl continuationImpl) {
        return function2.invoke((SupportSQLitePooledConnection) this.supportConnection$delegate.getValue(), continuationImpl);
    }
}
