package com.android.systemui.statusbar.notification.collection.inflation;

import com.android.systemui.plugins.statusbar.StatusBarStateController;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.notifcollection.DismissedByUserStats;
import com.android.systemui.statusbar.notification.collection.provider.NotificationVisibilityProviderImpl;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0 {
    public final /* synthetic */ OnUserInteractionCallbackImpl f$0;

    public /* synthetic */ OnUserInteractionCallbackImpl$$ExternalSyntheticLambda0(OnUserInteractionCallbackImpl onUserInteractionCallbackImpl) {
        this.f$0 = onUserInteractionCallbackImpl;
    }

    public final DismissedByUserStats createDismissedByUserStats(NotificationEntry notificationEntry) {
        OnUserInteractionCallbackImpl onUserInteractionCallbackImpl = this.f$0;
        onUserInteractionCallbackImpl.getClass();
        int i = 1;
        if (!((BaseHeadsUpManager) onUserInteractionCallbackImpl.mHeadsUpManager).isHeadsUpEntry(notificationEntry.mKey)) {
            StatusBarStateController statusBarStateController = onUserInteractionCallbackImpl.mStatusBarStateController;
            i = statusBarStateController.isDozing() ? 2 : statusBarStateController.getState() == 1 ? 5 : 3;
        }
        return new DismissedByUserStats(i, ((NotificationVisibilityProviderImpl) onUserInteractionCallbackImpl.mVisibilityProvider).obtain(notificationEntry));
    }
}
