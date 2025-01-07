package com.android.systemui.communal.data.db;

import android.content.Context;
import androidx.room.RoomDatabase;
import com.android.wm.shell.R;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class CommunalDatabase extends RoomDatabase {
    public static final CommunalDatabase$Companion$MIGRATION_1_2$1 MIGRATION_1_2 = new CommunalDatabase$Companion$MIGRATION_1_2$1(1, 2, 0);
    public static final CommunalDatabase$Companion$MIGRATION_1_2$1 MIGRATION_2_3 = new CommunalDatabase$Companion$MIGRATION_1_2$1(2, 3, 1);
    public static CommunalDatabase instance;

    public abstract CommunalWidgetDao_Impl communalWidgetDao();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public abstract class Companion {
        public static CommunalDatabase getInstance(Context context, DefaultWidgetPopulation defaultWidgetPopulation) {
            if (CommunalDatabase.instance == null) {
                String string = context.getResources().getString(R.string.config_communalDatabase);
                if (string == null || StringsKt__StringsJVMKt.isBlank(string)) {
                    throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
                }
                if (string.equals(":memory:")) {
                    throw new IllegalArgumentException("Cannot build a database with the special name ':memory:'. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder");
                }
                RoomDatabase.Builder builder = new RoomDatabase.Builder(context, string);
                CommunalDatabase$Companion$MIGRATION_1_2$1[] communalDatabase$Companion$MIGRATION_1_2$1Arr = {CommunalDatabase.MIGRATION_1_2, CommunalDatabase.MIGRATION_2_3};
                for (int i = 0; i < 2; i++) {
                    CommunalDatabase$Companion$MIGRATION_1_2$1 communalDatabase$Companion$MIGRATION_1_2$1 = communalDatabase$Companion$MIGRATION_1_2$1Arr[i];
                    builder.migrationStartAndEndVersions.add(Integer.valueOf(communalDatabase$Companion$MIGRATION_1_2$1.startVersion));
                    builder.migrationStartAndEndVersions.add(Integer.valueOf(communalDatabase$Companion$MIGRATION_1_2$1.endVersion));
                }
                CommunalDatabase$Companion$MIGRATION_1_2$1[] communalDatabase$Companion$MIGRATION_1_2$1Arr2 = (CommunalDatabase$Companion$MIGRATION_1_2$1[]) Arrays.copyOf(communalDatabase$Companion$MIGRATION_1_2$1Arr, 2);
                RoomDatabase.MigrationContainer migrationContainer = builder.migrationContainer;
                migrationContainer.getClass();
                for (CommunalDatabase$Companion$MIGRATION_1_2$1 communalDatabase$Companion$MIGRATION_1_2$12 : communalDatabase$Companion$MIGRATION_1_2$1Arr2) {
                    migrationContainer.addMigration(communalDatabase$Companion$MIGRATION_1_2$12);
                }
                builder.requireMigration = false;
                builder.allowDestructiveMigrationOnDowngrade = true;
                builder.allowDestructiveMigrationForAllTables = true;
                if (defaultWidgetPopulation != null) {
                    builder.callbacks.add(defaultWidgetPopulation);
                }
                CommunalDatabase.instance = (CommunalDatabase) builder.build();
            }
            CommunalDatabase communalDatabase = CommunalDatabase.instance;
            Intrinsics.checkNotNull(communalDatabase);
            return communalDatabase;
        }

        public static /* synthetic */ void getMIGRATION_1_2$annotations() {
        }

        public static /* synthetic */ void getMIGRATION_2_3$annotations() {
        }
    }
}
