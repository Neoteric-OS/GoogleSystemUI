package com.android.systemui.communal.data.db;

import androidx.room.util.SQLiteConnectionUtil;
import androidx.room.util.SQLiteStatementUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CommunalWidgetDao_Impl$$ExternalSyntheticLambda4 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ int f$0;

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda4(int i, int i2) {
        this.$r8$classId = i2;
        this.f$0 = i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SQLiteStatement prepare;
        int i = this.$r8$classId;
        int i2 = this.f$0;
        SQLiteConnection sQLiteConnection = (SQLiteConnection) obj;
        switch (i) {
            case 0:
                prepare = sQLiteConnection.prepare("SELECT * FROM communal_widget_table WHERE widget_id = ?");
                try {
                    prepare.bindLong(i2, 1);
                    int columnIndexOrThrow = SQLiteStatementUtil.getColumnIndexOrThrow(prepare, "uid");
                    int columnIndexOrThrow2 = SQLiteStatementUtil.getColumnIndexOrThrow(prepare, "widget_id");
                    int columnIndexOrThrow3 = SQLiteStatementUtil.getColumnIndexOrThrow(prepare, "component_name");
                    int columnIndexOrThrow4 = SQLiteStatementUtil.getColumnIndexOrThrow(prepare, "item_id");
                    int columnIndexOrThrow5 = SQLiteStatementUtil.getColumnIndexOrThrow(prepare, "user_serial_number");
                    if (prepare.step()) {
                        r5 = new CommunalWidgetItem(prepare.getLong(columnIndexOrThrow), (int) prepare.getLong(columnIndexOrThrow2), prepare.isNull(columnIndexOrThrow3) ? null : prepare.getText(columnIndexOrThrow3), prepare.getLong(columnIndexOrThrow4), (int) prepare.getLong(columnIndexOrThrow5));
                    }
                    return r5;
                } finally {
                }
            default:
                prepare = sQLiteConnection.prepare("INSERT INTO communal_item_rank_table(rank) VALUES(?)");
                try {
                    prepare.bindLong(i2, 1);
                    prepare.step();
                    return Long.valueOf(SQLiteConnectionUtil.getLastInsertedRowId(sQLiteConnection));
                } finally {
                }
        }
    }
}
