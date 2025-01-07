package com.android.systemui.statusbar.notification.collection.provider;

import com.android.systemui.statusbar.notification.VisibilityLocationProvider;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController;
import com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayoutController$$ExternalSyntheticLambda11;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class VisibilityLocationProviderDelegator implements VisibilityLocationProvider {
    public NotificationStackScrollLayoutController$$ExternalSyntheticLambda11 delegate;

    @Override // com.android.systemui.statusbar.notification.VisibilityLocationProvider
    public final boolean isInVisibleLocation(NotificationEntry notificationEntry) {
        if (this.delegate != null) {
            return NotificationStackScrollLayoutController.isInVisibleLocation$1(notificationEntry);
        }
        throw new IllegalArgumentException("delegate not initialized");
    }
}
