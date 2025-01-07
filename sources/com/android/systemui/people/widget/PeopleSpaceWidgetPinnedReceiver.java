package com.android.systemui.people.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PeopleSpaceWidgetPinnedReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int $r8$clinit = 0;
    public final PeopleSpaceWidgetManager mPeopleSpaceWidgetManager;

    public PeopleSpaceWidgetPinnedReceiver(PeopleSpaceWidgetManager peopleSpaceWidgetManager) {
        this.mPeopleSpaceWidgetManager = peopleSpaceWidgetManager;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int intExtra;
        if (context == null || intent == null || (intExtra = intent.getIntExtra("appWidgetId", -1)) == -1) {
            return;
        }
        PeopleTileKey peopleTileKey = new PeopleTileKey(intent.getStringExtra("android.intent.extra.shortcut.ID"), intent.getStringExtra("android.intent.extra.PACKAGE_NAME"), intent.getIntExtra("android.intent.extra.USER_ID", -1));
        if (PeopleTileKey.isValid(peopleTileKey)) {
            this.mPeopleSpaceWidgetManager.addNewWidget(intExtra, peopleTileKey);
        }
    }
}
