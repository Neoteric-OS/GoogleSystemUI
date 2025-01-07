package com.android.systemui.statusbar.notification.data;

import com.android.systemui.shared.notifications.data.repository.NotificationSettingsRepository;
import com.android.systemui.shared.settings.data.repository.SecureSettingsRepositoryImpl;
import com.android.systemui.shared.settings.data.repository.SystemSettingsRepositoryImpl;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationSettingsRepositoryModule_ProvideNotificationSettingsRepositoryFactory implements Provider {
    public static NotificationSettingsRepository provideNotificationSettingsRepository(CoroutineScope coroutineScope, CoroutineDispatcher coroutineDispatcher, SecureSettingsRepositoryImpl secureSettingsRepositoryImpl, SystemSettingsRepositoryImpl systemSettingsRepositoryImpl) {
        return new NotificationSettingsRepository(coroutineScope, coroutineDispatcher, secureSettingsRepositoryImpl, systemSettingsRepositoryImpl);
    }
}
