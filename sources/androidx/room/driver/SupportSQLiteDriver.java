package androidx.room.driver;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteDriver;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SupportSQLiteDriver implements SQLiteDriver {
    public final SupportSQLiteOpenHelper openHelper;

    public SupportSQLiteDriver(SupportSQLiteOpenHelper supportSQLiteOpenHelper) {
        this.openHelper = supportSQLiteOpenHelper;
    }

    @Override // androidx.sqlite.SQLiteDriver
    public final SQLiteConnection open(String str) {
        return new SupportSQLiteConnection(this.openHelper.getWritableDatabase());
    }
}
