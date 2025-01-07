package com.android.systemui.communal.data.db;

import android.content.ComponentName;
import androidx.room.util.DBUtil;
import androidx.room.util.SQLiteConnectionUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CommunalWidgetDao_Impl$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ int $r8$classId = 0;
    public final /* synthetic */ CommunalWidgetDao_Impl f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ Object f$2;
    public final /* synthetic */ Integer f$3;
    public final /* synthetic */ int f$4;

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda3(CommunalWidgetDao_Impl communalWidgetDao_Impl, int i, ComponentName componentName, Integer num, int i2) {
        this.f$0 = communalWidgetDao_Impl;
        this.f$1 = i;
        this.f$2 = componentName;
        this.f$3 = num;
        this.f$4 = i2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        Integer num;
        int intValue;
        switch (this.$r8$classId) {
            case 0:
                ComponentName componentName = (ComponentName) this.f$2;
                CommunalWidgetDao_Impl communalWidgetDao_Impl = this.f$0;
                communalWidgetDao_Impl.getClass();
                return Long.valueOf(communalWidgetDao_Impl.addWidget(this.f$1, componentName.flattenToString(), this.f$3, this.f$4));
            default:
                CommunalWidgetDao_Impl$$ExternalSyntheticLambda0 communalWidgetDao_Impl$$ExternalSyntheticLambda0 = new CommunalWidgetDao_Impl$$ExternalSyntheticLambda0(2);
                CommunalWidgetDao_Impl communalWidgetDao_Impl2 = this.f$0;
                CommunalDatabase_Impl communalDatabase_Impl = communalWidgetDao_Impl2.__db;
                Map map = (Map) DBUtil.performBlocking(communalDatabase_Impl, true, false, communalWidgetDao_Impl$$ExternalSyntheticLambda0);
                Integer num2 = this.f$3;
                if (num2 != null) {
                    intValue = num2.intValue();
                } else {
                    Iterator it = map.keySet().iterator();
                    if (it.hasNext()) {
                        Integer valueOf = Integer.valueOf(((CommunalItemRank) it.next()).rank + 1);
                        while (it.hasNext()) {
                            Integer valueOf2 = Integer.valueOf(((CommunalItemRank) it.next()).rank + 1);
                            if (valueOf.compareTo(valueOf2) < 0) {
                                valueOf = valueOf2;
                            }
                        }
                        num = valueOf;
                    } else {
                        num = null;
                    }
                    intValue = num != null ? num.intValue() : 0;
                }
                if (num2 != null) {
                    for (Map.Entry entry : map.entrySet()) {
                        CommunalItemRank communalItemRank = (CommunalItemRank) entry.getKey();
                        CommunalWidgetItem communalWidgetItem = (CommunalWidgetItem) entry.getValue();
                        int i = communalItemRank.rank;
                        if (i >= intValue) {
                            DBUtil.performBlocking(communalWidgetDao_Impl2.__db, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda6(communalWidgetItem.itemId, i + 1));
                        }
                    }
                }
                final long longValue = ((Long) DBUtil.performBlocking(communalDatabase_Impl, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda4(intValue, 1))).longValue();
                final String str = (String) this.f$2;
                final int i2 = this.f$4;
                final int i3 = this.f$1;
                Long l = (Long) DBUtil.performBlocking(communalDatabase_Impl, false, true, new Function1() { // from class: com.android.systemui.communal.data.db.CommunalWidgetDao_Impl$$ExternalSyntheticLambda10
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        int i4 = i3;
                        long j = longValue;
                        int i5 = i2;
                        SQLiteConnection sQLiteConnection = (SQLiteConnection) obj2;
                        SQLiteStatement prepare = sQLiteConnection.prepare("INSERT INTO communal_widget_table(widget_id, component_name, item_id, user_serial_number) VALUES(?, ?, ?, ?)");
                        try {
                            prepare.bindLong(i4, 1);
                            String str2 = str;
                            if (str2 == null) {
                                prepare.bindNull();
                            } else {
                                prepare.bindText(str2);
                            }
                            prepare.bindLong(j, 3);
                            prepare.bindLong(i5, 4);
                            prepare.step();
                            Long valueOf3 = Long.valueOf(SQLiteConnectionUtil.getLastInsertedRowId(sQLiteConnection));
                            prepare.close();
                            return valueOf3;
                        } catch (Throwable th) {
                            prepare.close();
                            throw th;
                        }
                    }
                });
                l.getClass();
                return l;
        }
    }

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda3(CommunalWidgetDao_Impl communalWidgetDao_Impl, int i, String str, Integer num, int i2) {
        this.f$0 = communalWidgetDao_Impl;
        this.f$1 = i;
        this.f$2 = str;
        this.f$3 = num;
        this.f$4 = i2;
    }
}
