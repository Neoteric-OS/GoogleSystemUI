package com.android.systemui.communal.data.db;

import androidx.room.util.DBUtil;
import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import com.android.systemui.communal.data.db.CommunalWidgetDao_Impl;
import com.android.systemui.communal.nano.CommunalHubState;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CommunalWidgetDao_Impl$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ int $r8$classId;
    public final /* synthetic */ CommunalWidgetDao_Impl f$0;
    public final /* synthetic */ Object f$1;

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda1(CommunalWidgetDao_Impl communalWidgetDao_Impl, Object obj, int i) {
        this.$r8$classId = i;
        this.f$0 = communalWidgetDao_Impl;
        this.f$1 = obj;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        switch (this.$r8$classId) {
            case 0:
                Map map = (Map) this.f$1;
                CommunalWidgetDao_Impl communalWidgetDao_Impl = this.f$0;
                communalWidgetDao_Impl.getClass();
                for (Map.Entry entry : map.entrySet()) {
                    int intValue = ((Number) entry.getKey()).intValue();
                    int intValue2 = ((Number) entry.getValue()).intValue();
                    CommunalWidgetDao_Impl$$ExternalSyntheticLambda4 communalWidgetDao_Impl$$ExternalSyntheticLambda4 = new CommunalWidgetDao_Impl$$ExternalSyntheticLambda4(intValue, 0);
                    CommunalDatabase_Impl communalDatabase_Impl = communalWidgetDao_Impl.__db;
                    CommunalWidgetItem communalWidgetItem = (CommunalWidgetItem) DBUtil.performBlocking(communalDatabase_Impl, true, false, communalWidgetDao_Impl$$ExternalSyntheticLambda4);
                    if (communalWidgetItem != null) {
                        DBUtil.performBlocking(communalDatabase_Impl, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda6(communalWidgetItem.itemId, intValue2));
                    }
                }
                return Unit.INSTANCE;
            case 1:
                CommunalWidgetDao_Impl$$ExternalSyntheticLambda0 communalWidgetDao_Impl$$ExternalSyntheticLambda0 = new CommunalWidgetDao_Impl$$ExternalSyntheticLambda0(1);
                CommunalWidgetDao_Impl communalWidgetDao_Impl2 = this.f$0;
                CommunalDatabase_Impl communalDatabase_Impl2 = communalWidgetDao_Impl2.__db;
                DBUtil.performBlocking(communalDatabase_Impl2, false, true, communalWidgetDao_Impl$$ExternalSyntheticLambda0);
                DBUtil.performBlocking(communalDatabase_Impl2, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda0(3));
                for (CommunalHubState.CommunalWidgetItem communalWidgetItem2 : ((CommunalHubState) this.f$1).widgets) {
                    communalWidgetDao_Impl2.addWidget(communalWidgetItem2.widgetId, communalWidgetItem2.componentName, Integer.valueOf(communalWidgetItem2.rank), communalWidgetItem2.userSerialNumber);
                }
                return Unit.INSTANCE;
            default:
                SQLiteConnection sQLiteConnection = (SQLiteConnection) obj;
                CommunalWidgetDao_Impl.AnonymousClass1 anonymousClass1 = this.f$0.__deleteAdapterOfCommunalWidgetItem;
                CommunalWidgetItem[] communalWidgetItemArr = (CommunalWidgetItem[]) this.f$1;
                SQLiteStatement prepare = sQLiteConnection.prepare("DELETE FROM `communal_widget_table` WHERE `uid` = ?");
                int i = 0;
                while (i < communalWidgetItemArr.length) {
                    try {
                        int i2 = i + 1;
                        try {
                            CommunalWidgetItem communalWidgetItem3 = communalWidgetItemArr[i];
                            if (communalWidgetItem3 != null) {
                                prepare.bindLong(communalWidgetItem3.uid, 1);
                                prepare.step();
                                prepare.reset();
                                prepare = sQLiteConnection.prepare("SELECT changes()");
                                try {
                                    prepare.step();
                                    prepare.getLong(0);
                                } finally {
                                    prepare.close();
                                }
                            }
                            i = i2;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new NoSuchElementException(e.getMessage());
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                prepare.close();
                return null;
        }
    }
}
