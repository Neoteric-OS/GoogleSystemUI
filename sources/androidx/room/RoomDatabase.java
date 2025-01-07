package androidx.room;

import android.content.Context;
import android.util.Log;
import androidx.arch.core.executor.ArchTaskExecutor$$ExternalSyntheticLambda0;
import androidx.room.concurrent.CloseBarrier;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.android.systemui.communal.data.db.CommunalDatabase;
import com.android.systemui.communal.data.db.CommunalDatabase$Companion$MIGRATION_1_2$1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import kotlin.NotImplementedError;
import kotlin.collections.EmptyList;
import kotlin.collections.EmptySet;
import kotlin.collections.MapsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.ClassReference;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.internal.ContextScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RoomDatabase {
    public boolean allowMainThreadQueries;
    public RoomConnectionManager connectionManager;
    public ContextScope coroutineScope;
    public Executor internalQueryExecutor;
    public InvalidationTracker internalTracker;
    public TransactionExecutor internalTransactionExecutor;
    public CoroutineContext transactionContext;
    public final CloseBarrier closeBarrier = new CloseBarrier();
    public final ThreadLocal suspendingTransactionId = new ThreadLocal();
    public final Map typeConverters = new LinkedHashMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Builder {
        public boolean allowDestructiveMigrationForAllTables;
        public boolean allowDestructiveMigrationOnDowngrade;
        public final Context context;
        public final String name;
        public ArchTaskExecutor$$ExternalSyntheticLambda0 queryExecutor;
        public ArchTaskExecutor$$ExternalSyntheticLambda0 transactionExecutor;
        public final List callbacks = new ArrayList();
        public final List typeConverters = new ArrayList();
        public final JournalMode journalMode = JournalMode.AUTOMATIC;
        public final long autoCloseTimeout = -1;
        public final MigrationContainer migrationContainer = new MigrationContainer();
        public final Set migrationsNotRequiredFrom = new LinkedHashSet();
        public final Set migrationStartAndEndVersions = new LinkedHashSet();
        public final List autoMigrationSpecs = new ArrayList();
        public boolean requireMigration = true;
        public final ClassReference klass = Reflection.getOrCreateKotlinClass(CommunalDatabase.class);

        public Builder(Context context, String str) {
            this.context = context;
            this.name = str;
        }

        /* JADX WARN: Removed duplicated region for block: B:173:0x0481  */
        /* JADX WARN: Removed duplicated region for block: B:179:0x04a1  */
        /* JADX WARN: Removed duplicated region for block: B:185:0x048a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final androidx.room.RoomDatabase build() {
            /*
                Method dump skipped, instructions count: 1333
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomDatabase.Builder.build():androidx.room.RoomDatabase");
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class JournalMode {
        public static final /* synthetic */ JournalMode[] $VALUES;
        public static final JournalMode AUTOMATIC;
        public static final JournalMode TRUNCATE;
        public static final JournalMode WRITE_AHEAD_LOGGING;

        static {
            JournalMode journalMode = new JournalMode("AUTOMATIC", 0);
            AUTOMATIC = journalMode;
            JournalMode journalMode2 = new JournalMode("TRUNCATE", 1);
            TRUNCATE = journalMode2;
            JournalMode journalMode3 = new JournalMode("WRITE_AHEAD_LOGGING", 2);
            WRITE_AHEAD_LOGGING = journalMode3;
            $VALUES = new JournalMode[]{journalMode, journalMode2, journalMode3};
        }

        public static JournalMode valueOf(String str) {
            return (JournalMode) Enum.valueOf(JournalMode.class, str);
        }

        public static JournalMode[] values() {
            return (JournalMode[]) $VALUES.clone();
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class MigrationContainer {
        public final Map migrations = new LinkedHashMap();

        public final void addMigration(CommunalDatabase$Companion$MIGRATION_1_2$1 communalDatabase$Companion$MIGRATION_1_2$1) {
            Map map = this.migrations;
            Integer valueOf = Integer.valueOf(communalDatabase$Companion$MIGRATION_1_2$1.startVersion);
            Object obj = map.get(valueOf);
            if (obj == null) {
                obj = new TreeMap();
                map.put(valueOf, obj);
            }
            TreeMap treeMap = (TreeMap) obj;
            int i = communalDatabase$Companion$MIGRATION_1_2$1.endVersion;
            if (treeMap.containsKey(Integer.valueOf(i))) {
                Log.w("ROOM", "Overriding migration " + treeMap.get(Integer.valueOf(i)) + " with " + communalDatabase$Companion$MIGRATION_1_2$1);
            }
            treeMap.put(Integer.valueOf(i), communalDatabase$Companion$MIGRATION_1_2$1);
        }
    }

    public abstract InvalidationTracker createInvalidationTracker();

    public RoomOpenDelegate createOpenDelegate() {
        throw new NotImplementedError();
    }

    public List getAutoMigrations() {
        return EmptyList.INSTANCE;
    }

    public final InvalidationTracker getInvalidationTracker() {
        InvalidationTracker invalidationTracker = this.internalTracker;
        if (invalidationTracker == null) {
            return null;
        }
        return invalidationTracker;
    }

    public Set getRequiredAutoMigrationSpecs() {
        return EmptySet.INSTANCE;
    }

    public Map getRequiredTypeConverters() {
        return MapsKt.emptyMap();
    }

    public final boolean inCompatibilityMode$room_runtime_release() {
        RoomConnectionManager roomConnectionManager = this.connectionManager;
        if (roomConnectionManager == null) {
            roomConnectionManager = null;
        }
        return roomConnectionManager.getSupportOpenHelper$room_runtime_release() != null;
    }

    public final void internalInitInvalidationTracker(SQLiteConnection sQLiteConnection) {
        InvalidationTracker invalidationTracker = getInvalidationTracker();
        invalidationTracker.implementation.getClass();
        SQLite.execSQL(sQLiteConnection, "PRAGMA temp_store = MEMORY");
        SQLite.execSQL(sQLiteConnection, "PRAGMA recursive_triggers = 1");
        SQLite.execSQL(sQLiteConnection, "CREATE TEMP TABLE IF NOT EXISTS room_table_modification_log (table_id INTEGER PRIMARY KEY, invalidated INTEGER NOT NULL DEFAULT 0)");
        synchronized (invalidationTracker.trackerLock) {
            if (invalidationTracker.multiInstanceInvalidationClient == null && invalidationTracker.multiInstanceClientInitState != null) {
                invalidationTracker.startMultiInstanceInvalidation();
            }
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Callback {
        public void onCreate() {
        }

        public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
        }
    }
}
