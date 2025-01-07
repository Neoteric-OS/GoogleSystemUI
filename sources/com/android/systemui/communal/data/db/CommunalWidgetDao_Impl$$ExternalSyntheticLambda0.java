package com.android.systemui.communal.data.db;

import androidx.room.AmbiguousColumnResolver;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CommunalWidgetDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ int $r8$classId;

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda0(int i) {
        this.$r8$classId = i;
    }

    /* JADX WARN: Finally extract failed */
    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        SQLiteStatement prepare;
        switch (this.$r8$classId) {
            case 0:
                prepare = ((SQLiteConnection) obj).prepare("SELECT * FROM communal_widget_table JOIN communal_item_rank_table ON communal_item_rank_table.uid = communal_widget_table.item_id ORDER BY communal_item_rank_table.rank ASC");
                try {
                    int columnCount = prepare.getColumnCount();
                    ArrayList arrayList = new ArrayList(columnCount);
                    for (int i = 0; i < columnCount; i++) {
                        arrayList.add(prepare.getColumnName(i));
                    }
                    int[][] resolve = AmbiguousColumnResolver.resolve(arrayList, new String[][]{new String[]{"uid", "rank"}, new String[]{"uid", "widget_id", "component_name", "item_id", "user_serial_number"}});
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    while (prepare.step()) {
                        CommunalItemRank communalItemRank = new CommunalItemRank(prepare.getLong(resolve[0][0]), (int) prepare.getLong(resolve[0][1]));
                        if (prepare.isNull(resolve[1][0]) && prepare.isNull(resolve[1][1]) && prepare.isNull(resolve[1][2]) && prepare.isNull(resolve[1][3]) && prepare.isNull(resolve[1][4])) {
                            linkedHashMap.put(communalItemRank, null);
                        } else {
                            CommunalWidgetItem communalWidgetItem = new CommunalWidgetItem(prepare.getLong(resolve[1][0]), (int) prepare.getLong(resolve[1][1]), prepare.isNull(resolve[1][2]) ? null : prepare.getText(resolve[1][2]), prepare.getLong(resolve[1][3]), (int) prepare.getLong(resolve[1][4]));
                            if (!linkedHashMap.containsKey(communalItemRank)) {
                                linkedHashMap.put(communalItemRank, communalWidgetItem);
                            }
                        }
                    }
                    return linkedHashMap;
                } catch (Throwable th) {
                    throw th;
                }
            case 1:
                prepare = ((SQLiteConnection) obj).prepare("DELETE FROM communal_widget_table");
                try {
                    prepare.step();
                    prepare.close();
                    return null;
                } finally {
                }
            case 2:
                prepare = ((SQLiteConnection) obj).prepare("SELECT * FROM communal_widget_table JOIN communal_item_rank_table ON communal_item_rank_table.uid = communal_widget_table.item_id ORDER BY communal_item_rank_table.rank ASC");
                try {
                    int columnCount2 = prepare.getColumnCount();
                    ArrayList arrayList2 = new ArrayList(columnCount2);
                    for (int i2 = 0; i2 < columnCount2; i2++) {
                        arrayList2.add(prepare.getColumnName(i2));
                    }
                    int[][] resolve2 = AmbiguousColumnResolver.resolve(arrayList2, new String[][]{new String[]{"uid", "rank"}, new String[]{"uid", "widget_id", "component_name", "item_id", "user_serial_number"}});
                    LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                    while (prepare.step()) {
                        CommunalItemRank communalItemRank2 = new CommunalItemRank(prepare.getLong(resolve2[0][0]), (int) prepare.getLong(resolve2[0][1]));
                        if (prepare.isNull(resolve2[1][0]) && prepare.isNull(resolve2[1][1]) && prepare.isNull(resolve2[1][2]) && prepare.isNull(resolve2[1][3]) && prepare.isNull(resolve2[1][4])) {
                            linkedHashMap2.put(communalItemRank2, null);
                        } else {
                            CommunalWidgetItem communalWidgetItem2 = new CommunalWidgetItem(prepare.getLong(resolve2[1][0]), (int) prepare.getLong(resolve2[1][1]), prepare.isNull(resolve2[1][2]) ? null : prepare.getText(resolve2[1][2]), prepare.getLong(resolve2[1][3]), (int) prepare.getLong(resolve2[1][4]));
                            if (!linkedHashMap2.containsKey(communalItemRank2)) {
                                linkedHashMap2.put(communalItemRank2, communalWidgetItem2);
                            }
                        }
                    }
                    return linkedHashMap2;
                } catch (Throwable th2) {
                    throw th2;
                }
            default:
                prepare = ((SQLiteConnection) obj).prepare("DELETE FROM communal_item_rank_table");
                try {
                    prepare.step();
                    prepare.close();
                    return null;
                } finally {
                }
        }
    }
}
