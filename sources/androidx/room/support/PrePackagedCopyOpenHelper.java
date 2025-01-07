package androidx.room.support;

import androidx.room.DatabaseConfiguration;
import androidx.room.DelegatingOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PrePackagedCopyOpenHelper implements SupportSQLiteOpenHelper, DelegatingOpenHelper {
    public DatabaseConfiguration databaseConfiguration;
}
