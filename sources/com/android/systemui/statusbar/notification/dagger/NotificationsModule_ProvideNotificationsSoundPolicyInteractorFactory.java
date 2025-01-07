package com.android.systemui.statusbar.notification.dagger;

import com.android.settingslib.notification.data.repository.ZenModeRepositoryImpl;
import com.android.settingslib.notification.domain.interactor.NotificationsSoundPolicyInteractor;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class NotificationsModule_ProvideNotificationsSoundPolicyInteractorFactory implements Provider {
    public static NotificationsSoundPolicyInteractor provideNotificationsSoundPolicyInteractor(ZenModeRepositoryImpl zenModeRepositoryImpl) {
        return new NotificationsSoundPolicyInteractor(zenModeRepositoryImpl);
    }
}
