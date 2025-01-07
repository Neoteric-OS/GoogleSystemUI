package com.android.wm.shell.bubbles;

import android.app.Notification;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class BubbleEntry {
    public boolean mIsDismissable;
    public NotificationListenerService.Ranking mRanking;
    public StatusBarNotification mSbn;
    public boolean mShouldSuppressNotificationDot;
    public boolean mShouldSuppressNotificationList;
    public boolean mShouldSuppressPeek;

    public final Notification.BubbleMetadata getBubbleMetadata() {
        return this.mSbn.getNotification().getBubbleMetadata();
    }

    public final boolean isBubble() {
        return (this.mSbn.getNotification().flags & 4096) != 0;
    }

    public final void setFlagBubble(boolean z) {
        isBubble();
        if (!z) {
            this.mSbn.getNotification().flags &= -4097;
        } else if (getBubbleMetadata() != null && this.mRanking.canBubble()) {
            this.mSbn.getNotification().flags |= 4096;
        }
        isBubble();
    }
}
