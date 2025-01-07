package androidx.sqlite.db;

import android.database.Cursor;
import androidx.room.driver.SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1;
import androidx.sqlite.db.framework.FrameworkSQLiteStatement;
import java.io.Closeable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface SupportSQLiteDatabase extends Closeable {
    void beginTransaction();

    void beginTransactionNonExclusive();

    void beginTransactionReadOnly();

    FrameworkSQLiteStatement compileStatement(String str);

    void endTransaction();

    void execSQL(String str);

    boolean inTransaction();

    boolean isOpen();

    Cursor query(SupportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1 supportSQLiteStatement$SupportAndroidSQLiteStatement$ensureCursor$1);

    void setTransactionSuccessful();
}
