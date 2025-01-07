package com.android.systemui.statusbar.notification.stack.ui.viewbinder;

import com.android.systemui.statusbar.notification.stack.ui.viewmodel.SharedNotificationContainerViewModel;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SharedNotificationContainerBinder$bind$3 implements Runnable {
    public final /* synthetic */ SharedNotificationContainerViewModel $viewModel;

    public SharedNotificationContainerBinder$bind$3(SharedNotificationContainerViewModel sharedNotificationContainerViewModel) {
        this.$viewModel = sharedNotificationContainerViewModel;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.$viewModel.notificationStackChanged();
    }
}
