package androidx.room.util;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SQLiteConnectionUtil {
    public static final long getLastInsertedRowId(SQLiteConnection sQLiteConnection) {
        SQLiteStatement prepare = sQLiteConnection.prepare("SELECT changes()");
        try {
            prepare.step();
            if (((int) prepare.getLong(0)) == 0) {
                return -1L;
            }
            prepare = sQLiteConnection.prepare("SELECT last_insert_rowid()");
            try {
                prepare.step();
                return prepare.getLong(0);
            } finally {
            }
        } finally {
        }
    }
}
