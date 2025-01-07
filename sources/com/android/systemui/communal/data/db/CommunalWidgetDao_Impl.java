package com.android.systemui.communal.data.db;

import androidx.room.util.DBUtil;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class CommunalWidgetDao_Impl {
    public final CommunalDatabase_Impl __db;
    public final AnonymousClass1 __deleteAdapterOfCommunalWidgetItem = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.systemui.communal.data.db.CommunalWidgetDao_Impl$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    public CommunalWidgetDao_Impl(CommunalDatabase_Impl communalDatabase_Impl) {
        this.__db = communalDatabase_Impl;
    }

    public final long addWidget(int i, String str, Integer num, int i2) {
        return ((Long) DBUtil.performBlocking(this.__db, false, true, new CommunalWidgetDao_Impl$$ExternalSyntheticLambda3(this, i, str, num, i2))).longValue();
    }
}
