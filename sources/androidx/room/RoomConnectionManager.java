package androidx.room;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import androidx.room.BaseRoomConnectionManager;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenDelegate;
import androidx.room.coroutines.ConnectionPool;
import androidx.room.coroutines.ConnectionPoolImpl;
import androidx.room.driver.SupportSQLiteConnection;
import androidx.room.driver.SupportSQLiteConnectionPool;
import androidx.room.driver.SupportSQLiteDriver;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;
import java.io.File;
import java.util.List;
import kotlin.NotImplementedError;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.EmptyList;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RoomConnectionManager extends BaseRoomConnectionManager {
    public final List callbacks;
    public final DatabaseConfiguration configuration;
    public final ConnectionPool connectionPool;
    public final RoomOpenDelegate openDelegate;
    public SupportSQLiteDatabase supportDatabase;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class NoOpOpenDelegate extends RoomOpenDelegate {
        @Override // androidx.room.RoomOpenDelegate
        public final void createAllTables(SQLiteConnection sQLiteConnection) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final void dropAllTables(SQLiteConnection sQLiteConnection) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final void onCreate() {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final void onOpen(SQLiteConnection sQLiteConnection) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final void onPostMigrate() {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final void onPreMigrate(SQLiteConnection sQLiteConnection) {
            throw new IllegalStateException("NOP delegate should never be called");
        }

        @Override // androidx.room.RoomOpenDelegate
        public final RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection sQLiteConnection) {
            throw new IllegalStateException("NOP delegate should never be called");
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class SupportOpenHelperCallback {
        public final int version;

        public SupportOpenHelperCallback(int i) {
            this.version = i;
        }

        public static void deleteDatabaseFile(String str) {
            if (StringsKt__StringsJVMKt.equals(str, ":memory:", true)) {
                return;
            }
            int length = str.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare(str.charAt(!z ? i : length), 32) <= 0;
                if (z) {
                    if (!z2) {
                        break;
                    } else {
                        length--;
                    }
                } else if (z2) {
                    i++;
                } else {
                    z = true;
                }
            }
            if (str.subSequence(i, length + 1).toString().length() == 0) {
                return;
            }
            Log.w("SupportSQLite", "deleting the database file: ".concat(str));
            try {
                SQLiteDatabase.deleteDatabase(new File(str));
            } catch (Exception e) {
                Log.w("SupportSQLite", "delete failed: ", e);
            }
        }

        public final void onUpgrade(FrameworkSQLiteDatabase frameworkSQLiteDatabase, int i, int i2) {
            RoomConnectionManager.this.onMigrate(new SupportSQLiteConnection(frameworkSQLiteDatabase), i, i2);
        }
    }

    public RoomConnectionManager(DatabaseConfiguration databaseConfiguration, RoomOpenDelegate roomOpenDelegate) {
        int i;
        ConnectionPoolImpl connectionPoolImpl;
        this.configuration = databaseConfiguration;
        this.openDelegate = roomOpenDelegate;
        List list = databaseConfiguration.callbacks;
        this.callbacks = list == null ? EmptyList.INSTANCE : list;
        RoomDatabase.JournalMode journalMode = databaseConfiguration.journalMode;
        String str = databaseConfiguration.name;
        SQLiteDriver sQLiteDriver = databaseConfiguration.sqliteDriver;
        if (sQLiteDriver != null) {
            if (str == null) {
                connectionPoolImpl = new ConnectionPoolImpl(new BaseRoomConnectionManager.DriverWrapper(this, sQLiteDriver));
            } else {
                BaseRoomConnectionManager.DriverWrapper driverWrapper = new BaseRoomConnectionManager.DriverWrapper(this, sQLiteDriver);
                int ordinal = journalMode.ordinal();
                if (ordinal == 1) {
                    i = 1;
                } else {
                    if (ordinal != 2) {
                        throw new IllegalStateException(("Can't get max number of reader for journal mode '" + journalMode + '\'').toString());
                    }
                    i = 4;
                }
                int ordinal2 = journalMode.ordinal();
                if (ordinal2 != 1 && ordinal2 != 2) {
                    throw new IllegalStateException(("Can't get max number of writers for journal mode '" + journalMode + '\'').toString());
                }
                connectionPoolImpl = new ConnectionPoolImpl(driverWrapper, str, i);
            }
            this.connectionPool = connectionPoolImpl;
        } else {
            if (databaseConfiguration.sqliteOpenHelperFactory == null) {
                throw new IllegalArgumentException("SQLiteManager was constructed with both null driver and open helper factory!");
            }
            this.connectionPool = new SupportSQLiteConnectionPool(new SupportSQLiteDriver(new FrameworkSQLiteOpenHelper(databaseConfiguration.context, str, new SupportOpenHelperCallback(roomOpenDelegate.version))));
        }
        boolean z = journalMode == RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING;
        SupportSQLiteOpenHelper supportOpenHelper$room_runtime_release = getSupportOpenHelper$room_runtime_release();
        if (supportOpenHelper$room_runtime_release != null) {
            supportOpenHelper$room_runtime_release.setWriteAheadLoggingEnabled(z);
        }
    }

    public final SupportSQLiteOpenHelper getSupportOpenHelper$room_runtime_release() {
        SupportSQLiteDriver supportSQLiteDriver;
        ConnectionPool connectionPool = this.connectionPool;
        SupportSQLiteConnectionPool supportSQLiteConnectionPool = connectionPool instanceof SupportSQLiteConnectionPool ? (SupportSQLiteConnectionPool) connectionPool : null;
        if (supportSQLiteConnectionPool == null || (supportSQLiteDriver = supportSQLiteConnectionPool.supportDriver) == null) {
            return null;
        }
        return supportSQLiteDriver.openHelper;
    }

    public RoomConnectionManager(DatabaseConfiguration databaseConfiguration, Function1 function1) {
        this.configuration = databaseConfiguration;
        this.openDelegate = new NoOpOpenDelegate("", "", -1);
        List list = databaseConfiguration.callbacks;
        this.callbacks = list == null ? EmptyList.INSTANCE : list;
        final Function1 function12 = new Function1() { // from class: androidx.room.RoomConnectionManager$configWithCompatibilityCallback$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                RoomConnectionManager.this.supportDatabase = (SupportSQLiteDatabase) obj;
                return Unit.INSTANCE;
            }
        };
        CollectionsKt.plus(list == null ? EmptyList.INSTANCE : list, new RoomDatabase.Callback() { // from class: androidx.room.RoomConnectionManager$installOnOpenCallback$newCallbacks$1
            @Override // androidx.room.RoomDatabase.Callback
            public final void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                Function1.this.invoke(supportSQLiteDatabase);
            }
        });
        ((RoomDatabase$createConnectionManager$1) function1).this$0.getClass();
        throw new NotImplementedError();
    }
}
