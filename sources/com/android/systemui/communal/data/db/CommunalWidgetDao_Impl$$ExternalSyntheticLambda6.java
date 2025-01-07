package com.android.systemui.communal.data.db;

import androidx.sqlite.SQLiteConnection;
import androidx.sqlite.SQLiteStatement;
import kotlin.jvm.functions.Function1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class CommunalWidgetDao_Impl$$ExternalSyntheticLambda6 implements Function1 {
    public final /* synthetic */ int f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ CommunalWidgetDao_Impl$$ExternalSyntheticLambda6(long j, int i) {
        this.f$0 = i;
        this.f$1 = j;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        int i = this.f$0;
        long j = this.f$1;
        SQLiteStatement prepare = ((SQLiteConnection) obj).prepare("UPDATE communal_item_rank_table SET rank = ? WHERE uid = ?");
        try {
            prepare.bindLong(i, 1);
            prepare.bindLong(j, 2);
            prepare.step();
            prepare.close();
            return null;
        } catch (Throwable th) {
            prepare.close();
            throw th;
        }
    }
}
