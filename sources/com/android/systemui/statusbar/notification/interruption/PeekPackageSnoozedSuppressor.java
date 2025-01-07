package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;
import com.android.systemui.statusbar.policy.HeadsUpManager;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekPackageSnoozedSuppressor extends VisualInterruptionFilter {
    public final HeadsUpManager headsUpManager;

    public PeekPackageSnoozedSuppressor(HeadsUpManager headsUpManager) {
        super("package snoozed", Collections.singleton(VisualInterruptionType.PEEK));
        this.headsUpManager = headsUpManager;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
    public final boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (notificationEntry.mSbn.getNotification().fullScreenIntent != null) {
            return false;
        }
        return ((BaseHeadsUpManager) this.headsUpManager).isSnoozed(notificationEntry.mSbn.getPackageName());
    }
}
