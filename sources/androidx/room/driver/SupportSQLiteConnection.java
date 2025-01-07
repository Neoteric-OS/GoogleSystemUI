package androidx.room.driver;

import androidx.room.driver.SupportSQLiteStatement;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.Locale;
import kotlin.text.StringsKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SupportSQLiteConnection implements SQLiteConnection {
    public final SupportSQLiteDatabase db;

    public SupportSQLiteConnection(SupportSQLiteDatabase supportSQLiteDatabase) {
        this.db = supportSQLiteDatabase;
    }

    @Override // androidx.sqlite.SQLiteConnection
    public final void close() {
        this.db.close();
    }

    @Override // androidx.sqlite.SQLiteConnection
    public final SupportSQLiteStatement prepare(String str) {
        String upperCase;
        int hashCode;
        String obj = StringsKt.trim(str).toString();
        int length = obj.length();
        SupportSQLiteDatabase supportSQLiteDatabase = this.db;
        if (length < 3 || ((hashCode = (upperCase = obj.substring(0, 3).toUpperCase(Locale.ROOT)).hashCode()) == 79487 ? !upperCase.equals("PRA") : hashCode == 81978 ? !upperCase.equals("SEL") : !(hashCode == 85954 && upperCase.equals("WIT")))) {
            return new SupportSQLiteStatement.SupportOtherAndroidSQLiteStatement(supportSQLiteDatabase, str);
        }
        SupportSQLiteStatement.SupportAndroidSQLiteStatement supportAndroidSQLiteStatement = new SupportSQLiteStatement.SupportAndroidSQLiteStatement(supportSQLiteDatabase, str);
        supportAndroidSQLiteStatement.bindingTypes = new int[0];
        supportAndroidSQLiteStatement.longBindings = new long[0];
        supportAndroidSQLiteStatement.doubleBindings = new double[0];
        supportAndroidSQLiteStatement.stringBindings = new String[0];
        supportAndroidSQLiteStatement.blobBindings = new byte[0][];
        return supportAndroidSQLiteStatement;
    }
}
