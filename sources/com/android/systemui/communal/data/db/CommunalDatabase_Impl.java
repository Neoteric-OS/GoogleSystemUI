package com.android.systemui.communal.data.db;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalDatabase_Impl extends CommunalDatabase {
    public volatile CommunalWidgetDao_Impl _communalWidgetDao;

    @Override // com.android.systemui.communal.data.db.CommunalDatabase
    public final CommunalWidgetDao_Impl communalWidgetDao() {
        CommunalWidgetDao_Impl communalWidgetDao_Impl;
        if (this._communalWidgetDao != null) {
            return this._communalWidgetDao;
        }
        synchronized (this) {
            try {
                if (this._communalWidgetDao == null) {
                    this._communalWidgetDao = new CommunalWidgetDao_Impl(this);
                }
                communalWidgetDao_Impl = this._communalWidgetDao;
            } catch (Throwable th) {
                throw th;
            }
        }
        return communalWidgetDao_Impl;
    }

    @Override // androidx.room.RoomDatabase
    public final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "communal_widget_table", "communal_item_rank_table");
    }

    @Override // androidx.room.RoomDatabase
    public final RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: com.android.systemui.communal.data.db.CommunalDatabase_Impl.1
            @Override // androidx.room.RoomOpenDelegate
            public final void createAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `communal_widget_table` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `widget_id` INTEGER NOT NULL, `component_name` TEXT NOT NULL, `item_id` INTEGER NOT NULL, `user_serial_number` INTEGER NOT NULL DEFAULT -1)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `communal_item_rank_table` (`uid` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `rank` INTEGER NOT NULL DEFAULT 0)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                SQLite.execSQL(sQLiteConnection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '02e2da2d36e6955200edd5fb49e63c72')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void dropAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `communal_widget_table`");
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `communal_item_rank_table`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onOpen(SQLiteConnection sQLiteConnection) {
                CommunalDatabase_Impl.this.internalInitInvalidationTracker(sQLiteConnection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onPreMigrate(SQLiteConnection sQLiteConnection) {
                DBUtil.dropFtsSyncTriggers(sQLiteConnection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public final RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection sQLiteConnection) {
                HashMap hashMap = new HashMap(5);
                hashMap.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                hashMap.put("widget_id", new TableInfo.Column("widget_id", "INTEGER", true, 0, null, 1));
                hashMap.put("component_name", new TableInfo.Column("component_name", "TEXT", true, 0, null, 1));
                hashMap.put("item_id", new TableInfo.Column("item_id", "INTEGER", true, 0, null, 1));
                hashMap.put("user_serial_number", new TableInfo.Column("user_serial_number", "INTEGER", true, 0, "-1", 1));
                TableInfo tableInfo = new TableInfo("communal_widget_table", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(sQLiteConnection, "communal_widget_table");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenDelegate.ValidationResult("communal_widget_table(com.android.systemui.communal.data.db.CommunalWidgetItem).\n Expected:\n" + tableInfo + "\n Found:\n" + read, false);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("uid", new TableInfo.Column("uid", "INTEGER", true, 1, null, 1));
                hashMap2.put("rank", new TableInfo.Column("rank", "INTEGER", true, 0, "0", 1));
                TableInfo tableInfo2 = new TableInfo("communal_item_rank_table", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(sQLiteConnection, "communal_item_rank_table");
                if (tableInfo2.equals(read2)) {
                    return new RoomOpenDelegate.ValidationResult(null, true);
                }
                return new RoomOpenDelegate.ValidationResult("communal_item_rank_table(com.android.systemui.communal.data.db.CommunalItemRank).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2, false);
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onCreate() {
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onPostMigrate() {
            }
        };
    }

    @Override // androidx.room.RoomDatabase
    public final List getAutoMigrations() {
        return new ArrayList();
    }

    @Override // androidx.room.RoomDatabase
    public final Set getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public final Map getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(CommunalWidgetDao_Impl.class, Collections.emptyList());
        return hashMap;
    }
}
