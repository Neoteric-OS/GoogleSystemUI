package androidx.room.util;

import android.os.Looper;
import androidx.room.RoomConnectionManager;
import androidx.room.RoomDatabase;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.ListIterator;
import kotlin.collections.builders.ListBuilder;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.BuildersKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class DBUtil {
    public static final void dropFtsSyncTriggers(SQLiteConnection sQLiteConnection) {
        ListBuilder listBuilder = new ListBuilder();
        SQLiteStatement prepare = sQLiteConnection.prepare("SELECT name FROM sqlite_master WHERE type = 'trigger'");
        while (prepare.step()) {
            try {
                listBuilder.add(prepare.getText(0));
            } catch (Throwable th) {
                prepare.close();
                throw th;
            }
        }
        prepare.close();
        ListIterator listIterator = listBuilder.build().listIterator(0);
        while (true) {
            ListBuilder.Itr itr = (ListBuilder.Itr) listIterator;
            if (!itr.hasNext()) {
                return;
            }
            String str = (String) itr.next();
            if (str.startsWith("room_fts_content_sync_")) {
                SQLite.execSQL(sQLiteConnection, "DROP TRIGGER IF EXISTS ".concat(str));
            }
        }
    }

    public static final Object performBlocking(RoomDatabase roomDatabase, boolean z, boolean z2, Function1 function1) {
        if (!roomDatabase.allowMainThreadQueries && Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException("Cannot access database on the main thread since it may potentially lock the UI for a long period of time.");
        }
        RoomConnectionManager roomConnectionManager = roomDatabase.connectionManager;
        if (roomConnectionManager == null) {
            roomConnectionManager = null;
        }
        SupportSQLiteOpenHelper supportOpenHelper$room_runtime_release = roomConnectionManager.getSupportOpenHelper$room_runtime_release();
        if (supportOpenHelper$room_runtime_release == null) {
            throw new IllegalStateException("Cannot return a SupportSQLiteOpenHelper since no SupportSQLiteOpenHelper.Factory was configured with Room.");
        }
        if (!supportOpenHelper$room_runtime_release.getWritableDatabase().inTransaction() && roomDatabase.suspendingTransactionId.get() != null) {
            throw new IllegalStateException("Cannot access database on a different coroutine context inherited from a suspending transaction.");
        }
        return BuildersKt.runBlocking(EmptyCoroutineContext.INSTANCE, new DBUtil__DBUtil_androidKt$performBlocking$1(roomDatabase, null, function1, z, z2));
    }
}
