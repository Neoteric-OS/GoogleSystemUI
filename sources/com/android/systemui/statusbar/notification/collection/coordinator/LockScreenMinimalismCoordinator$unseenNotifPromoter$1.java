package com.android.systemui.statusbar.notification.collection.coordinator;

import android.os.Build;
import android.util.Log;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LockScreenMinimalismCoordinator$unseenNotifPromoter$1 extends NotifPromoter {
    @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifPromoter
    public final boolean shouldPromoteToTopLevel(NotificationEntry notificationEntry) {
        if (Log.isLoggable("RefactorFlagAssert", 7)) {
            Log.wtf("RefactorFlagAssert", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.", Build.isDebuggable() ? new IllegalStateException("New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.") : null);
            return false;
        }
        if (!Log.isLoggable("RefactorFlag", 5)) {
            return false;
        }
        Log.w("RefactorFlag", "New code path expects com.android.systemui.notification_minimalism_prototype to be enabled.");
        return false;
    }
}
