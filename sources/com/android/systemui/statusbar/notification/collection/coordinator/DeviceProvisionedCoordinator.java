package com.android.systemui.statusbar.notification.collection.coordinator;

import android.service.notification.StatusBarNotification;
import com.android.systemui.statusbar.notification.collection.NotifPipeline;
import com.android.systemui.statusbar.notification.collection.NotificationEntry;
import com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter;
import com.android.systemui.statusbar.policy.DeviceProvisionedController;
import com.android.systemui.statusbar.policy.DeviceProvisionedControllerImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeviceProvisionedCoordinator implements Coordinator {
    public final DeviceProvisionedController mDeviceProvisionedController;
    public final AnonymousClass1 mNotifFilter = new NotifFilter() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator.1
        @Override // com.android.systemui.statusbar.notification.collection.listbuilder.pluggable.NotifFilter
        public final boolean shouldFilterOut(NotificationEntry notificationEntry, long j) {
            DeviceProvisionedCoordinator deviceProvisionedCoordinator = DeviceProvisionedCoordinator.this;
            if (!((DeviceProvisionedControllerImpl) deviceProvisionedCoordinator.mDeviceProvisionedController).deviceProvisioned.get()) {
                StatusBarNotification statusBarNotification = notificationEntry.mSbn;
                deviceProvisionedCoordinator.getClass();
                if (!statusBarNotification.getNotification().extras.getBoolean("android.allowDuringSetup")) {
                    return true;
                }
            }
            return false;
        }
    };
    public final AnonymousClass2 mDeviceProvisionedListener = new DeviceProvisionedController.DeviceProvisionedListener() { // from class: com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator.2
        @Override // com.android.systemui.statusbar.policy.DeviceProvisionedController.DeviceProvisionedListener
        public final void onDeviceProvisionedChanged() {
            invalidateList("onDeviceProvisionedChanged");
        }
    };

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.systemui.statusbar.notification.collection.coordinator.DeviceProvisionedCoordinator$2] */
    public DeviceProvisionedCoordinator(DeviceProvisionedController deviceProvisionedController) {
        this.mDeviceProvisionedController = deviceProvisionedController;
    }

    @Override // com.android.systemui.statusbar.notification.collection.coordinator.Coordinator
    public final void attach(NotifPipeline notifPipeline) {
        ((DeviceProvisionedControllerImpl) this.mDeviceProvisionedController).addCallback(this.mDeviceProvisionedListener);
        notifPipeline.addPreGroupFilter(this.mNotifFilter);
    }
}
