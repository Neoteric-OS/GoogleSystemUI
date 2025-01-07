package androidx.room;

import androidx.room.RoomDatabase;
import androidx.room.RoomOpenDelegate;
import androidx.room.concurrent.ExclusiveLock;
import androidx.room.concurrent.FileLock;
import androidx.room.driver.SupportSQLiteConnection;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.SQLiteStatement;
import java.nio.channels.FileChannel;
import kotlin.Result;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class BaseRoomConnectionManager {
    public boolean isConfigured;
    public boolean isInitializing;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DriverWrapper implements SQLiteDriver {
        public final SQLiteDriver actual;
        public final /* synthetic */ RoomConnectionManager this$0;

        public DriverWrapper(RoomConnectionManager roomConnectionManager, SQLiteDriver sQLiteDriver) {
            this.this$0 = roomConnectionManager;
            this.actual = sQLiteDriver;
        }

        @Override // androidx.sqlite.SQLiteDriver
        public final SQLiteConnection open(final String str) {
            FileChannel fileChannel;
            FileChannel fileChannel2;
            final RoomConnectionManager roomConnectionManager = this.this$0;
            ExclusiveLock exclusiveLock = new ExclusiveLock(str, (roomConnectionManager.isConfigured || roomConnectionManager.isInitializing || str.equals(":memory:")) ? false : true);
            Function0 function0 = new Function0() { // from class: androidx.room.BaseRoomConnectionManager$DriverWrapper$open$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    if (roomConnectionManager.isInitializing) {
                        throw new IllegalStateException("Recursive database initialization detected. Did you try to use the database instance during initialization? Maybe in one of the callbacks?");
                    }
                    SQLiteConnection open = this.actual.open(str);
                    BaseRoomConnectionManager baseRoomConnectionManager = roomConnectionManager;
                    if (baseRoomConnectionManager.isConfigured) {
                        RoomConnectionManager roomConnectionManager2 = (RoomConnectionManager) baseRoomConnectionManager;
                        DatabaseConfiguration databaseConfiguration = roomConnectionManager2.configuration;
                        if (databaseConfiguration.journalMode == RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING) {
                            SQLite.execSQL(open, "PRAGMA synchronous = NORMAL");
                        } else {
                            SQLite.execSQL(open, "PRAGMA synchronous = FULL");
                        }
                        SQLiteStatement prepare = open.prepare("PRAGMA busy_timeout");
                        try {
                            prepare.step();
                            long j = prepare.getLong(0);
                            prepare.close();
                            if (j < 3000) {
                                SQLite.execSQL(open, "PRAGMA busy_timeout = 3000");
                            }
                            roomConnectionManager2.openDelegate.onOpen(open);
                        } catch (Throwable th) {
                            prepare.close();
                            throw th;
                        }
                    } else {
                        try {
                            baseRoomConnectionManager.isInitializing = true;
                            BaseRoomConnectionManager.access$configureDatabase(baseRoomConnectionManager, open);
                        } finally {
                            roomConnectionManager.isInitializing = false;
                        }
                    }
                    return open;
                }
            };
            exclusiveLock.threadLock.lock();
            FileLock fileLock = exclusiveLock.fileLock;
            if (fileLock != null) {
                try {
                    fileLock.lock();
                } catch (Throwable th) {
                    exclusiveLock.threadLock.unlock();
                    throw th;
                }
            }
            try {
                Object invoke = function0.invoke();
                if (fileLock != null && (fileChannel2 = fileLock.lockChannel) != null) {
                    try {
                        fileChannel2.close();
                        fileLock.lockChannel = null;
                    } finally {
                    }
                }
                exclusiveLock.threadLock.unlock();
                return (SQLiteConnection) invoke;
            } catch (Throwable th2) {
                if (fileLock != null && (fileChannel = fileLock.lockChannel) != null) {
                    try {
                        fileChannel.close();
                        fileLock.lockChannel = null;
                    } finally {
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX WARN: Finally extract failed */
    public static final void access$configureDatabase(BaseRoomConnectionManager baseRoomConnectionManager, SQLiteConnection sQLiteConnection) {
        Object failure;
        baseRoomConnectionManager.getClass();
        RoomConnectionManager roomConnectionManager = (RoomConnectionManager) baseRoomConnectionManager;
        DatabaseConfiguration databaseConfiguration = roomConnectionManager.configuration;
        RoomDatabase.JournalMode journalMode = RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING;
        if (databaseConfiguration.journalMode == journalMode) {
            SQLite.execSQL(sQLiteConnection, "PRAGMA journal_mode = WAL");
        } else {
            SQLite.execSQL(sQLiteConnection, "PRAGMA journal_mode = TRUNCATE");
        }
        if (databaseConfiguration.journalMode == journalMode) {
            SQLite.execSQL(sQLiteConnection, "PRAGMA synchronous = NORMAL");
        } else {
            SQLite.execSQL(sQLiteConnection, "PRAGMA synchronous = FULL");
        }
        SQLiteStatement prepare = sQLiteConnection.prepare("PRAGMA user_version");
        try {
            prepare.step();
            int i = (int) prepare.getLong(0);
            prepare.close();
            if (i != roomConnectionManager.openDelegate.version) {
                SQLite.execSQL(sQLiteConnection, "BEGIN EXCLUSIVE TRANSACTION");
                try {
                    if (i == 0) {
                        baseRoomConnectionManager.onCreate(sQLiteConnection);
                    } else {
                        baseRoomConnectionManager.onMigrate(sQLiteConnection, i, ((RoomConnectionManager) baseRoomConnectionManager).openDelegate.version);
                    }
                    SQLite.execSQL(sQLiteConnection, "PRAGMA user_version = " + ((RoomConnectionManager) baseRoomConnectionManager).openDelegate.version);
                    failure = Unit.INSTANCE;
                } catch (Throwable th) {
                    failure = new Result.Failure(th);
                }
                if (!(failure instanceof Result.Failure)) {
                    SQLite.execSQL(sQLiteConnection, "END TRANSACTION");
                }
                Throwable m1771exceptionOrNullimpl = Result.m1771exceptionOrNullimpl(failure);
                if (m1771exceptionOrNullimpl != null) {
                    SQLite.execSQL(sQLiteConnection, "ROLLBACK TRANSACTION");
                    throw m1771exceptionOrNullimpl;
                }
            }
            baseRoomConnectionManager.onOpen(sQLiteConnection);
        } catch (Throwable th2) {
            prepare.close();
            throw th2;
        }
    }

    public final void onCreate(SQLiteConnection sQLiteConnection) {
        SQLiteStatement prepare = sQLiteConnection.prepare("SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'");
        try {
            boolean z = false;
            if (prepare.step()) {
                if (prepare.getLong(0) == 0) {
                    z = true;
                }
            }
            prepare.close();
            RoomConnectionManager roomConnectionManager = (RoomConnectionManager) this;
            RoomOpenDelegate roomOpenDelegate = roomConnectionManager.openDelegate;
            roomOpenDelegate.createAllTables(sQLiteConnection);
            if (!z) {
                RoomOpenDelegate.ValidationResult onValidateSchema = roomOpenDelegate.onValidateSchema(sQLiteConnection);
                if (!onValidateSchema.isValid) {
                    throw new IllegalStateException(("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg).toString());
                }
            }
            updateIdentity(sQLiteConnection);
            roomOpenDelegate.onCreate();
            for (RoomDatabase.Callback callback : roomConnectionManager.callbacks) {
                callback.getClass();
                if (sQLiteConnection instanceof SupportSQLiteConnection) {
                    callback.onCreate();
                }
            }
        } catch (Throwable th) {
            prepare.close();
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:127:0x0062 A[EDGE_INSN: B:127:0x0062->B:111:0x0062 BREAK  A[LOOP:4: B:89:0x0024->B:112:?], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x002e  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0064  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMigrate(androidx.sqlite.SQLiteConnection r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.BaseRoomConnectionManager.onMigrate(androidx.sqlite.SQLiteConnection, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onOpen(androidx.sqlite.SQLiteConnection r10) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.room.BaseRoomConnectionManager.onOpen(androidx.sqlite.SQLiteConnection):void");
    }

    public final void updateIdentity(SQLiteConnection sQLiteConnection) {
        SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        SQLite.execSQL(sQLiteConnection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '" + ((RoomConnectionManager) this).openDelegate.identityHash + "')");
    }
}
