package com.android.systemui.statusbar.notification.collection.coordinator;

import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.row.NotifBindPipeline;
import com.android.systemui.statusbar.policy.BaseHeadsUpManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class HeadsUpCoordinator$bindForAsyncHeadsUp$1 implements NotifBindPipeline.BindCallback {
    public final /* synthetic */ HeadsUpCoordinator $tmp0;

    public HeadsUpCoordinator$bindForAsyncHeadsUp$1(HeadsUpCoordinator headsUpCoordinator) {
        this.$tmp0 = headsUpCoordinator;
    }

    @Override // com.android.systemui.statusbar.notification.row.NotifBindPipeline.BindCallback
    public final void onBindFinished(NotificationEntry notificationEntry) {
        HeadsUpCoordinator headsUpCoordinator = this.$tmp0;
        ((BaseHeadsUpManager) headsUpCoordinator.mHeadsUpManager).showNotification(notificationEntry);
        headsUpCoordinator.mEntriesBindingUntil.remove(notificationEntry.mKey);
    }
}
