package com.android.systemui.statusbar.notification.icon.domain.interactor;

import com.android.systemui.statusbar.notification.data.repository.NotificationsKeyguardViewStateRepository;
import com.android.systemui.statusbar.notification.domain.interactor.ActiveNotificationsInteractor;
import com.android.systemui.statusbar.notification.domain.interactor.HeadsUpNotificationIconInteractor;
import java.util.Optional;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationIconsInteractor {
    public final ActiveNotificationsInteractor activeNotificationsInteractor;
    public final Optional bubbles;
    public final HeadsUpNotificationIconInteractor headsUpNotificationIconInteractor;
    public final NotificationsKeyguardViewStateRepository keyguardViewStateRepository;

    public NotificationIconsInteractor(ActiveNotificationsInteractor activeNotificationsInteractor, Optional optional, HeadsUpNotificationIconInteractor headsUpNotificationIconInteractor, NotificationsKeyguardViewStateRepository notificationsKeyguardViewStateRepository) {
        this.activeNotificationsInteractor = activeNotificationsInteractor;
        this.bubbles = optional;
        this.headsUpNotificationIconInteractor = headsUpNotificationIconInteractor;
        this.keyguardViewStateRepository = notificationsKeyguardViewStateRepository;
    }

    public static FlowKt__ZipKt$combine$$inlined$combineUnsafe$FlowKt__ZipKt$1 filteredNotifSet$default(NotificationIconsInteractor notificationIconsInteractor, boolean z, boolean z2, int i) {
        boolean z3 = (i & 1) == 0;
        boolean z4 = (i & 2) != 0;
        boolean z5 = (i & 4) != 0 ? true : z;
        boolean z6 = (i & 8) != 0;
        boolean z7 = (i & 16) != 0;
        boolean z8 = (i & 32) != 0 ? true : z2;
        return FlowKt.combine(notificationIconsInteractor.activeNotificationsInteractor.topLevelRepresentativeNotifications, notificationIconsInteractor.headsUpNotificationIconInteractor.isolatedNotification, notificationIconsInteractor.keyguardViewStateRepository.areNotificationsFullyHidden, new NotificationIconsInteractor$filteredNotifSet$1(notificationIconsInteractor, z3, z4, z5, z6, z7, z8, null));
    }
}
