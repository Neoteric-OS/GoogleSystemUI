package com.android.systemui.statusbar.notification.domain.interactor;

import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository;
import com.android.systemui.shared.notifications.domain.interactor.NotificationSettingsInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationSettingsInteractorModule_ProvideNotificationSettingsInteractorFactory implements Provider {
    public static NotificationSettingsInteractor provideNotificationSettingsInteractor(NotificationSettingsRepository notificationSettingsRepository) {
        return new NotificationSettingsInteractor(notificationSettingsRepository);
    }
}
