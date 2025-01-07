package com.android.settingslib.mobile.dataservice;

import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenDelegate;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.SQLite;
import androidx.sqlite.SQLiteConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MobileNetworkDatabase_Impl extends MobileNetworkDatabase {
    @Override // androidx.room.RoomDatabase
    public final InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "subscriptionInfo", "uiccInfo", "MobileNetworkInfo");
    }

    @Override // androidx.room.RoomDatabase
    public final RoomOpenDelegate createOpenDelegate() {
        return new RoomOpenDelegate() { // from class: com.android.settingslib.mobile.dataservice.MobileNetworkDatabase_Impl.1
            @Override // androidx.room.RoomOpenDelegate
            public final void createAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `subscriptionInfo` (`sudId` TEXT NOT NULL, `simSlotIndex` INTEGER NOT NULL, `isEmbedded` INTEGER NOT NULL, `isOpportunistic` INTEGER NOT NULL, `uniqueName` TEXT, `isSubscriptionVisible` INTEGER NOT NULL, `isDefaultSubscriptionSelection` INTEGER NOT NULL, `isValidSubscription` INTEGER NOT NULL, `isActiveSubscription` INTEGER NOT NULL, `isActiveDataSubscriptionId` INTEGER NOT NULL, PRIMARY KEY(`sudId`))");
                SQLite.execSQL(sQLiteConnection, "CREATE INDEX IF NOT EXISTS `index_subscriptionInfo_sudId` ON `subscriptionInfo` (`sudId`)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `uiccInfo` (`sudId` TEXT NOT NULL, `isActive` INTEGER NOT NULL, PRIMARY KEY(`sudId`))");
                SQLite.execSQL(sQLiteConnection, "CREATE INDEX IF NOT EXISTS `index_uiccInfo_sudId` ON `uiccInfo` (`sudId`)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS `MobileNetworkInfo` (`subId` TEXT NOT NULL, `isMobileDataEnabled` INTEGER NOT NULL, `showToggleForPhysicalSim` INTEGER NOT NULL, PRIMARY KEY(`subId`))");
                SQLite.execSQL(sQLiteConnection, "CREATE INDEX IF NOT EXISTS `index_MobileNetworkInfo_subId` ON `MobileNetworkInfo` (`subId`)");
                SQLite.execSQL(sQLiteConnection, "CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
                SQLite.execSQL(sQLiteConnection, "INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c8e4769f99f33fbac0f5a63a6356fd30')");
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void dropAllTables(SQLiteConnection sQLiteConnection) {
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `subscriptionInfo`");
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `uiccInfo`");
                SQLite.execSQL(sQLiteConnection, "DROP TABLE IF EXISTS `MobileNetworkInfo`");
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onOpen(SQLiteConnection sQLiteConnection) {
                MobileNetworkDatabase_Impl.this.internalInitInvalidationTracker(sQLiteConnection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public final void onPreMigrate(SQLiteConnection sQLiteConnection) {
                DBUtil.dropFtsSyncTriggers(sQLiteConnection);
            }

            @Override // androidx.room.RoomOpenDelegate
            public final RoomOpenDelegate.ValidationResult onValidateSchema(SQLiteConnection sQLiteConnection) {
                HashMap hashMap = new HashMap(10);
                hashMap.put("sudId", new TableInfo.Column("sudId", "TEXT", true, 1, null, 1));
                hashMap.put("simSlotIndex", new TableInfo.Column("simSlotIndex", "INTEGER", true, 0, null, 1));
                hashMap.put("isEmbedded", new TableInfo.Column("isEmbedded", "INTEGER", true, 0, null, 1));
                hashMap.put("isOpportunistic", new TableInfo.Column("isOpportunistic", "INTEGER", true, 0, null, 1));
                hashMap.put("uniqueName", new TableInfo.Column("uniqueName", "TEXT", false, 0, null, 1));
                hashMap.put("isSubscriptionVisible", new TableInfo.Column("isSubscriptionVisible", "INTEGER", true, 0, null, 1));
                hashMap.put("isDefaultSubscriptionSelection", new TableInfo.Column("isDefaultSubscriptionSelection", "INTEGER", true, 0, null, 1));
                hashMap.put("isValidSubscription", new TableInfo.Column("isValidSubscription", "INTEGER", true, 0, null, 1));
                hashMap.put("isActiveSubscription", new TableInfo.Column("isActiveSubscription", "INTEGER", true, 0, null, 1));
                hashMap.put("isActiveDataSubscriptionId", new TableInfo.Column("isActiveDataSubscriptionId", "INTEGER", true, 0, null, 1));
                HashSet hashSet = new HashSet(0);
                HashSet hashSet2 = new HashSet(1);
                hashSet2.add(new TableInfo.Index("index_subscriptionInfo_sudId", false, Arrays.asList("sudId"), Arrays.asList("ASC")));
                TableInfo tableInfo = new TableInfo("subscriptionInfo", hashMap, hashSet, hashSet2);
                TableInfo read = TableInfo.read(sQLiteConnection, "subscriptionInfo");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenDelegate.ValidationResult("subscriptionInfo(com.android.settingslib.mobile.dataservice.SubscriptionInfoEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read, false);
                }
                HashMap hashMap2 = new HashMap(2);
                hashMap2.put("sudId", new TableInfo.Column("sudId", "TEXT", true, 1, null, 1));
                hashMap2.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, 1));
                HashSet hashSet3 = new HashSet(0);
                HashSet hashSet4 = new HashSet(1);
                hashSet4.add(new TableInfo.Index("index_uiccInfo_sudId", false, Arrays.asList("sudId"), Arrays.asList("ASC")));
                TableInfo tableInfo2 = new TableInfo("uiccInfo", hashMap2, hashSet3, hashSet4);
                TableInfo read2 = TableInfo.read(sQLiteConnection, "uiccInfo");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenDelegate.ValidationResult("uiccInfo(com.android.settingslib.mobile.dataservice.UiccInfoEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2, false);
                }
                HashMap hashMap3 = new HashMap(3);
                hashMap3.put("subId", new TableInfo.Column("subId", "TEXT", true, 1, null, 1));
                hashMap3.put("isMobileDataEnabled", new TableInfo.Column("isMobileDataEnabled", "INTEGER", true, 0, null, 1));
                hashMap3.put("showToggleForPhysicalSim", new TableInfo.Column("showToggleForPhysicalSim", "INTEGER", true, 0, null, 1));
                HashSet hashSet5 = new HashSet(0);
                HashSet hashSet6 = new HashSet(1);
                hashSet6.add(new TableInfo.Index("index_MobileNetworkInfo_subId", false, Arrays.asList("subId"), Arrays.asList("ASC")));
                TableInfo tableInfo3 = new TableInfo("MobileNetworkInfo", hashMap3, hashSet5, hashSet6);
                TableInfo read3 = TableInfo.read(sQLiteConnection, "MobileNetworkInfo");
                if (tableInfo3.equals(read3)) {
                    return new RoomOpenDelegate.ValidationResult(null, true);
                }
                return new RoomOpenDelegate.ValidationResult("MobileNetworkInfo(com.android.settingslib.mobile.dataservice.MobileNetworkInfoEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3, false);
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
        hashMap.put(SubscriptionInfoDao_Impl.class, Collections.emptyList());
        hashMap.put(UiccInfoDao_Impl.class, Collections.emptyList());
        hashMap.put(MobileNetworkInfoDao_Impl.class, Collections.emptyList());
        return hashMap;
    }
}
