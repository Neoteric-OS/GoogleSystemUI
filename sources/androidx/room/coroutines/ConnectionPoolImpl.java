package androidx.room.coroutines;

import androidx.room.BaseRoomConnectionManager;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function0;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectionPoolImpl implements ConnectionPool {
    public final Pool readers;
    public final long timeout;
    public final Pool writers;
    public final ThreadLocal threadLocal = new ThreadLocal();
    public final AtomicBoolean _isClosed = AtomicFU.atomic(false);

    public ConnectionPoolImpl(final BaseRoomConnectionManager.DriverWrapper driverWrapper) {
        int i = Duration.$r8$clinit;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        Pool pool = new Pool(1, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl.1
            final /* synthetic */ String $fileName = ":memory:";

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return driverWrapper.open(this.$fileName);
            }
        });
        this.readers = pool;
        this.writers = pool;
    }

    @Override // androidx.room.coroutines.ConnectionPool
    public final void close() {
        AtomicBoolean atomicBoolean = this._isClosed;
        atomicBoolean.getClass();
        if (AtomicBoolean.FU.compareAndSet(atomicBoolean, 0, 1)) {
            Pool pool = this.readers;
            pool.channel.close(null);
            for (ConnectionWithLock connectionWithLock : pool.connections) {
                if (connectionWithLock != null) {
                    connectionWithLock.close();
                }
            }
            Pool pool2 = this.writers;
            pool2.channel.close(null);
            for (ConnectionWithLock connectionWithLock2 : pool2.connections) {
                if (connectionWithLock2 != null) {
                    connectionWithLock2.close();
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x018a A[Catch: all -> 0x01a1, TRY_LEAVE, TryCatch #0 {all -> 0x01a1, blocks: (B:16:0x0184, B:18:0x018a, B:24:0x019b, B:21:0x019e), top: B:15:0x0184 }] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0137 A[Catch: all -> 0x0148, TryCatch #5 {all -> 0x0148, blocks: (B:63:0x0122, B:65:0x0137, B:69:0x0144, B:70:0x014e, B:74:0x0158, B:78:0x01a2, B:79:0x01a9, B:80:0x01aa, B:81:0x01ab, B:82:0x01b1), top: B:62:0x0122 }] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01ab A[Catch: all -> 0x0148, TryCatch #5 {all -> 0x0148, blocks: (B:63:0x0122, B:65:0x0137, B:69:0x0144, B:70:0x014e, B:74:0x0158, B:78:0x01a2, B:79:0x01a9, B:80:0x01aa, B:81:0x01ab, B:82:0x01b1), top: B:62:0x0122 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0078  */
    @Override // androidx.room.coroutines.ConnectionPool
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object useConnection(boolean r17, kotlin.jvm.functions.Function2 r18, kotlin.coroutines.jvm.internal.ContinuationImpl r19) {
        /*
            Method dump skipped, instructions count: 483
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.coroutines.ConnectionPoolImpl.useConnection(boolean, kotlin.jvm.functions.Function2, kotlin.coroutines.jvm.internal.ContinuationImpl):java.lang.Object");
    }

    public ConnectionPoolImpl(final BaseRoomConnectionManager.DriverWrapper driverWrapper, final String str, int i) {
        int i2 = Duration.$r8$clinit;
        this.timeout = DurationKt.toDuration(30, DurationUnit.SECONDS);
        if (i > 0) {
            this.readers = new Pool(i, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    SQLiteConnection open = driverWrapper.open(str);
                    SQLite.execSQL(open, "PRAGMA query_only = 1");
                    return open;
                }
            });
            this.writers = new Pool(1, new Function0() { // from class: androidx.room.coroutines.ConnectionPoolImpl.5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return driverWrapper.open(str);
                }
            });
            return;
        }
        throw new IllegalArgumentException("Maximum number of readers must be greater than 0");
    }
}
