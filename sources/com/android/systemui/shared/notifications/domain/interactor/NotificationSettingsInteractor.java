package com.android.systemui.shared.notifications.domain.interactor;

import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationSettingsInteractor {
    public final ReadonlyStateFlow isCooldownEnabled;
    public final Flow isNotificationHistoryEnabled;

    public NotificationSettingsInteractor(NotificationSettingsRepository notificationSettingsRepository) {
        this.isNotificationHistoryEnabled = notificationSettingsRepository.isNotificationHistoryEnabled;
        ReadonlyStateFlow readonlyStateFlow = notificationSettingsRepository.isCooldownEnabled;
    }
}
