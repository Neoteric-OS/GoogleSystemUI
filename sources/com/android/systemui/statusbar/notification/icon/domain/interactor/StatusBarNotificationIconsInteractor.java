package com.android.systemui.statusbar.notification.icon.domain.interactor;

import com.android.systemui.statusbar.data.repository.NotificationListenerSettingsRepository;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarNotificationIconsInteractor {
    public final Flow statusBarNotifs;

    public StatusBarNotificationIconsInteractor(CoroutineContext coroutineContext, NotificationIconsInteractor notificationIconsInteractor, NotificationListenerSettingsRepository notificationListenerSettingsRepository) {
        this.statusBarNotifs = FlowKt.flowOn(FlowKt.transformLatest(notificationListenerSettingsRepository.showSilentStatusIcons, new StatusBarNotificationIconsInteractor$special$$inlined$flatMapLatest$1(null, notificationIconsInteractor)), coroutineContext);
    }
}
