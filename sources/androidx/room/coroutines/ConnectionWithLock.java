package androidx.room.coroutines;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexImpl;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectionWithLock implements SQLiteConnection, Mutex {
    public final SQLiteConnection delegate;
    public final MutexImpl lock;

    public ConnectionWithLock(SQLiteConnection sQLiteConnection) {
        MutexImpl Mutex$default = MutexKt.Mutex$default();
        this.delegate = sQLiteConnection;
        this.lock = Mutex$default;
    }

    @Override // androidx.sqlite.SQLiteConnection
    public final void close() {
        this.delegate.close();
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public final Object lock(Continuation continuation) {
        return this.lock.lock(continuation);
    }

    @Override // androidx.sqlite.SQLiteConnection
    public final SQLiteStatement prepare(String str) {
        return this.delegate.prepare(str);
    }

    @Override // kotlinx.coroutines.sync.Mutex
    public final void unlock(Object obj) {
        this.lock.unlock(null);
    }
}
