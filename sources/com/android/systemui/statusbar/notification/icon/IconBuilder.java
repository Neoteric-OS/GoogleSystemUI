package com.android.systemui.statusbar.notification.icon;

import android.content.Context;
import androidx.appsearch.app.DocumentClassFactoryRegistry$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.StatusBarIconView;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class IconBuilder {
    public final Context context;

    public IconBuilder(Context context) {
        this.context = context;
    }

    public final StatusBarIconView createIconView(NotificationEntry notificationEntry) {
        return new StatusBarIconView(this.context, DocumentClassFactoryRegistry$$ExternalSyntheticOutline0.m$1(notificationEntry.mSbn.getPackageName(), "/0x", Integer.toHexString(notificationEntry.mSbn.getId())), notificationEntry.mSbn, false);
    }
}
