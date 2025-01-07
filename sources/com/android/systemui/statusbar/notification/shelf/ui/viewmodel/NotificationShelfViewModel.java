package com.android.systemui.statusbar.notification.shelf.ui.viewmodel;

import com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModel;
import com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModelImpl;
import com.android.systemui.statusbar.notification.shelf.domain.interactor.NotificationShelfInteractor;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationShelfViewModel implements ActivatableNotificationViewModel {
    public final /* synthetic */ ActivatableNotificationViewModelImpl $$delegate_0;
    public final NotificationShelfInteractor interactor;

    public NotificationShelfViewModel(NotificationShelfInteractor notificationShelfInteractor, ActivatableNotificationViewModelImpl activatableNotificationViewModelImpl) {
        this.interactor = notificationShelfInteractor;
        this.$$delegate_0 = activatableNotificationViewModelImpl;
    }

    @Override // com.android.systemui.statusbar.notification.row.ui.viewmodel.ActivatableNotificationViewModel
    public final Flow isTouchable() {
        return this.$$delegate_0.isTouchable;
    }
}
