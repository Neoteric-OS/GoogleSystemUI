package com.android.systemui.statusbar.notification.interruption;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.interruption.NotificationInterruptStateProviderImpl;
import com.android.systemui.util.time.SystemClock;
import com.android.systemui.util.time.SystemClockImpl;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekOldWhenSuppressor extends VisualInterruptionFilter {
    public final SystemClock systemClock;

    public PeekOldWhenSuppressor(SystemClock systemClock) {
        super(Collections.singleton(VisualInterruptionType.PEEK), "has old `when`", NotificationInterruptStateProviderImpl.NotificationInterruptEvent.HUN_SUPPRESSED_OLD_WHEN, 24);
        this.systemClock = systemClock;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionFilter
    public final boolean shouldSuppress(NotificationEntry notificationEntry) {
        if (notificationEntry.mSbn.getNotification().getWhen() <= 0 || notificationEntry.mSbn.getNotification().fullScreenIntent != null || notificationEntry.mSbn.getNotification().isForegroundService() || notificationEntry.mSbn.getNotification().isUserInitiatedJob()) {
            return false;
        }
        ((SystemClockImpl) this.systemClock).getClass();
        return System.currentTimeMillis() - notificationEntry.mSbn.getNotification().getWhen() >= 86400000;
    }
}
