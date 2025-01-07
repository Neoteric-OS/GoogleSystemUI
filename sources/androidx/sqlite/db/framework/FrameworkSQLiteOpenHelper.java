package androidx.sqlite.db.framework;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import androidx.room.RoomConnectionManager;
import androidx.room.driver.SupportSQLiteConnection;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper;
import androidx.sqlite.util.ProcessLock;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FrameworkSQLiteOpenHelper implements SupportSQLiteOpenHelper {
    public final RoomConnectionManager.SupportOpenHelperCallback callback;
    public final Context context;
    public final Lazy lazyDelegate = LazyKt__LazyJVMKt.lazy(new Function0() { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$lazyDelegate$1
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            String str = FrameworkSQLiteOpenHelper.this.name;
            FrameworkSQLiteOpenHelper frameworkSQLiteOpenHelper = FrameworkSQLiteOpenHelper.this;
            Context context = frameworkSQLiteOpenHelper.context;
            String str2 = frameworkSQLiteOpenHelper.name;
            FrameworkSQLiteOpenHelper.DBRefHolder dBRefHolder = new FrameworkSQLiteOpenHelper.DBRefHolder();
            dBRefHolder.db = null;
            FrameworkSQLiteOpenHelper.OpenHelper openHelper = new FrameworkSQLiteOpenHelper.OpenHelper(context, str2, dBRefHolder, frameworkSQLiteOpenHelper.callback);
            openHelper.setWriteAheadLoggingEnabled(FrameworkSQLiteOpenHelper.this.writeAheadLoggingEnabled);
            return openHelper;
        }
    });
    public final String name;
    public boolean writeAheadLoggingEnabled;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class DBRefHolder {
        public FrameworkSQLiteDatabase db;
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class OpenHelper extends SQLiteOpenHelper {
        public static final /* synthetic */ int $r8$clinit = 0;
        public final RoomConnectionManager.SupportOpenHelperCallback callback;
        public final Context context;
        public final DBRefHolder dbRef;
        public final ProcessLock lock;
        public boolean migrated;
        public boolean opened;

        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        final class CallbackException extends RuntimeException {
            private final CallbackName callbackName;
            private final Throwable cause;

            public CallbackException(CallbackName callbackName, Throwable th) {
                super(th);
                this.callbackName = callbackName;
                this.cause = th;
            }

            public final CallbackName getCallbackName() {
                return this.callbackName;
            }

            @Override // java.lang.Throwable
            public final Throwable getCause() {
                return this.cause;
            }
        }

        /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
        /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
        /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
        public final class CallbackName {
            public static final /* synthetic */ CallbackName[] $VALUES;
            public static final CallbackName ON_CONFIGURE;
            public static final CallbackName ON_CREATE;
            public static final CallbackName ON_DOWNGRADE;
            public static final CallbackName ON_OPEN;
            public static final CallbackName ON_UPGRADE;

            static {
                CallbackName callbackName = new CallbackName("ON_CONFIGURE", 0);
                ON_CONFIGURE = callbackName;
                CallbackName callbackName2 = new CallbackName("ON_CREATE", 1);
                ON_CREATE = callbackName2;
                CallbackName callbackName3 = new CallbackName("ON_UPGRADE", 2);
                ON_UPGRADE = callbackName3;
                CallbackName callbackName4 = new CallbackName("ON_DOWNGRADE", 3);
                ON_DOWNGRADE = callbackName4;
                CallbackName callbackName5 = new CallbackName("ON_OPEN", 4);
                ON_OPEN = callbackName5;
                $VALUES = new CallbackName[]{callbackName, callbackName2, callbackName3, callbackName4, callbackName5};
            }

            public static CallbackName valueOf(String str) {
                return (CallbackName) Enum.valueOf(CallbackName.class, str);
            }

            public static CallbackName[] values() {
                return (CallbackName[]) $VALUES.clone();
            }
        }

        public OpenHelper(Context context, String str, final DBRefHolder dBRefHolder, final RoomConnectionManager.SupportOpenHelperCallback supportOpenHelperCallback) {
            super(context, str, null, supportOpenHelperCallback.version, new DatabaseErrorHandler(supportOpenHelperCallback, dBRefHolder) { // from class: androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper$$ExternalSyntheticLambda0
                public final /* synthetic */ RoomConnectionManager.SupportOpenHelperCallback f$0;
                public final /* synthetic */ FrameworkSQLiteOpenHelper.DBRefHolder f$1;

                {
                    this.f$1 = dBRefHolder;
                }

                @Override // android.database.DatabaseErrorHandler
                public final void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    FrameworkSQLiteOpenHelper.DBRefHolder dBRefHolder2 = this.f$1;
                    int i = FrameworkSQLiteOpenHelper.OpenHelper.$r8$clinit;
                    FrameworkSQLiteDatabase frameworkSQLiteDatabase = dBRefHolder2.db;
                    if (frameworkSQLiteDatabase == null || !Intrinsics.areEqual(frameworkSQLiteDatabase.delegate, sQLiteDatabase)) {
                        frameworkSQLiteDatabase = new FrameworkSQLiteDatabase(sQLiteDatabase);
                        dBRefHolder2.db = frameworkSQLiteDatabase;
                    }
                    Log.e("SupportSQLite", "Corruption reported by sqlite on database: " + frameworkSQLiteDatabase + ".path");
                    if (!frameworkSQLiteDatabase.delegate.isOpen()) {
                        String path = frameworkSQLiteDatabase.delegate.getPath();
                        if (path != null) {
                            RoomConnectionManager.SupportOpenHelperCallback.deleteDatabaseFile(path);
                            return;
                        }
                        return;
                    }
                    List<Pair<String, String>> list = null;
                    try {
                        try {
                            list = frameworkSQLiteDatabase.delegate.getAttachedDbs();
                        } catch (SQLiteException unused) {
                        }
                        try {
                            frameworkSQLiteDatabase.close();
                        } catch (IOException unused2) {
                        }
                        if (list != null) {
                            return;
                        }
                    } finally {
                        if (list != null) {
                            Iterator<T> it = list.iterator();
                            while (it.hasNext()) {
                                RoomConnectionManager.SupportOpenHelperCallback.deleteDatabaseFile((String) ((Pair) it.next()).second);
                            }
                        } else {
                            String path2 = frameworkSQLiteDatabase.delegate.getPath();
                            if (path2 != null) {
                                RoomConnectionManager.SupportOpenHelperCallback.deleteDatabaseFile(path2);
                            }
                        }
                    }
                }
            });
            this.context = context;
            this.dbRef = dBRefHolder;
            this.callback = supportOpenHelperCallback;
            this.lock = new ProcessLock(str == null ? UUID.randomUUID().toString() : str, context.getCacheDir(), false);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper, java.lang.AutoCloseable
        public final void close() {
            try {
                ProcessLock processLock = this.lock;
                processLock.lock(processLock.processLock);
                super.close();
                this.dbRef.db = null;
                this.opened = false;
            } finally {
                this.lock.unlock();
            }
        }

        public final SupportSQLiteDatabase getSupportDatabase(boolean z) {
            try {
                this.lock.lock((this.opened || getDatabaseName() == null) ? false : true);
                this.migrated = false;
                SQLiteDatabase innerGetDatabase = innerGetDatabase(z);
                if (!this.migrated) {
                    FrameworkSQLiteDatabase wrappedDb = getWrappedDb(innerGetDatabase);
                    this.lock.unlock();
                    return wrappedDb;
                }
                close();
                SupportSQLiteDatabase supportDatabase = getSupportDatabase(z);
                this.lock.unlock();
                return supportDatabase;
            } catch (Throwable th) {
                this.lock.unlock();
                throw th;
            }
        }

        public final FrameworkSQLiteDatabase getWrappedDb(SQLiteDatabase sQLiteDatabase) {
            DBRefHolder dBRefHolder = this.dbRef;
            FrameworkSQLiteDatabase frameworkSQLiteDatabase = dBRefHolder.db;
            if (frameworkSQLiteDatabase != null && Intrinsics.areEqual(frameworkSQLiteDatabase.delegate, sQLiteDatabase)) {
                return frameworkSQLiteDatabase;
            }
            FrameworkSQLiteDatabase frameworkSQLiteDatabase2 = new FrameworkSQLiteDatabase(sQLiteDatabase);
            dBRefHolder.db = frameworkSQLiteDatabase2;
            return frameworkSQLiteDatabase2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [android.database.sqlite.SQLiteOpenHelper, androidx.sqlite.db.framework.FrameworkSQLiteOpenHelper$OpenHelper] */
        /* JADX WARN: Type inference failed for: r3v1, types: [android.database.sqlite.SQLiteOpenHelper] */
        /* JADX WARN: Type inference failed for: r3v13, types: [android.database.sqlite.SQLiteDatabase] */
        /* JADX WARN: Type inference failed for: r3v15 */
        /* JADX WARN: Type inference failed for: r3v16 */
        public final SQLiteDatabase innerGetDatabase(boolean z) {
            File parentFile;
            String databaseName = getDatabaseName();
            boolean z2 = this.opened;
            if (databaseName != null && !z2 && (parentFile = this.context.getDatabasePath(databaseName).getParentFile()) != null) {
                parentFile.mkdirs();
                if (!parentFile.isDirectory()) {
                    Log.w("SupportSQLite", "Invalid database parent file, not a directory: " + parentFile);
                }
            }
            try {
                this = z ? getWritableDatabase() : getReadableDatabase();
                return this;
            } catch (Throwable unused) {
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException unused2) {
                }
                try {
                    return z ? this.getWritableDatabase() : this.getReadableDatabase();
                } catch (Throwable th) {
                    th = th;
                    if (th instanceof CallbackException) {
                        CallbackException callbackException = (CallbackException) th;
                        Throwable cause = callbackException.getCause();
                        int ordinal = callbackException.getCallbackName().ordinal();
                        if (ordinal == 0) {
                            throw cause;
                        }
                        if (ordinal == 1) {
                            throw cause;
                        }
                        if (ordinal == 2) {
                            throw cause;
                        }
                        if (ordinal == 3) {
                            throw cause;
                        }
                        if (!(cause instanceof SQLiteException)) {
                            throw cause;
                        }
                        th = cause;
                    }
                    boolean z3 = th instanceof SQLiteException;
                    throw th;
                }
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onConfigure(SQLiteDatabase sQLiteDatabase) {
            if (!this.migrated && this.callback.version != sQLiteDatabase.getVersion()) {
                sQLiteDatabase.setMaxSqlCacheSize(1);
            }
            try {
                RoomConnectionManager.SupportOpenHelperCallback supportOpenHelperCallback = this.callback;
                getWrappedDb(sQLiteDatabase);
                supportOpenHelperCallback.getClass();
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CONFIGURE, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                RoomConnectionManager.SupportOpenHelperCallback supportOpenHelperCallback = this.callback;
                FrameworkSQLiteDatabase wrappedDb = getWrappedDb(sQLiteDatabase);
                supportOpenHelperCallback.getClass();
                RoomConnectionManager.this.onCreate(new SupportSQLiteConnection(wrappedDb));
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_CREATE, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.migrated = true;
            try {
                this.callback.onUpgrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_DOWNGRADE, th);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onOpen(SQLiteDatabase sQLiteDatabase) {
            if (!this.migrated) {
                try {
                    RoomConnectionManager.SupportOpenHelperCallback supportOpenHelperCallback = this.callback;
                    FrameworkSQLiteDatabase wrappedDb = getWrappedDb(sQLiteDatabase);
                    supportOpenHelperCallback.getClass();
                    SupportSQLiteConnection supportSQLiteConnection = new SupportSQLiteConnection(wrappedDb);
                    RoomConnectionManager roomConnectionManager = RoomConnectionManager.this;
                    roomConnectionManager.onOpen(supportSQLiteConnection);
                    roomConnectionManager.supportDatabase = wrappedDb;
                } catch (Throwable th) {
                    throw new CallbackException(CallbackName.ON_OPEN, th);
                }
            }
            this.opened = true;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.migrated = true;
            try {
                this.callback.onUpgrade(getWrappedDb(sQLiteDatabase), i, i2);
            } catch (Throwable th) {
                throw new CallbackException(CallbackName.ON_UPGRADE, th);
            }
        }
    }

    public FrameworkSQLiteOpenHelper(Context context, String str, RoomConnectionManager.SupportOpenHelperCallback supportOpenHelperCallback) {
        this.context = context;
        this.name = str;
        this.callback = supportOpenHelperCallback;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.lazyDelegate.isInitialized()) {
            ((OpenHelper) this.lazyDelegate.getValue()).close();
        }
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public final String getDatabaseName() {
        return this.name;
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public final SupportSQLiteDatabase getWritableDatabase() {
        return ((OpenHelper) this.lazyDelegate.getValue()).getSupportDatabase(true);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper
    public final void setWriteAheadLoggingEnabled(boolean z) {
        if (this.lazyDelegate.isInitialized()) {
            ((OpenHelper) this.lazyDelegate.getValue()).setWriteAheadLoggingEnabled(z);
        }
        this.writeAheadLoggingEnabled = z;
    }
}
