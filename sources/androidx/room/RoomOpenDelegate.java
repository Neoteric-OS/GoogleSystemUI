package androidx.room;

import androidx.sqlite.SQLiteConnection;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class RoomOpenDelegate {
    public final String identityHash;
    public final String legacyIdentityHash;
    public final int version;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(String str, boolean z) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    public RoomOpenDelegate(String str, String str2, int i) {
        this.version = i;
        this.identityHash = str;
        this.legacyIdentityHash = str2;
    }

    public abstract void createAllTables(SQLiteConnection sQLiteConnection);

    public abstract void dropAllTables(SQLiteConnection sQLiteConnection);

    public abstract void onCreate();

    public abstract void onOpen(SQLiteConnection sQLiteConnection);

    public abstract void onPostMigrate();

    public abstract void onPreMigrate(SQLiteConnection sQLiteConnection);

    public abstract ValidationResult onValidateSchema(SQLiteConnection sQLiteConnection);
}
