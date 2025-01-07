package com.android.systemui.util.settings.repository;

import com.android.systemui.user.data.repository.UserRepositoryImpl;
import com.android.systemui.util.settings.SecureSettings;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class UserAwareSecureSettingsRepositoryImpl {
    public final CoroutineDispatcher backgroundDispatcher;
    public final SecureSettings secureSettings;
    public final UserRepositoryImpl userRepository;

    public UserAwareSecureSettingsRepositoryImpl(SecureSettings secureSettings, UserRepositoryImpl userRepositoryImpl, CoroutineDispatcher coroutineDispatcher) {
        this.secureSettings = secureSettings;
        this.userRepository = userRepositoryImpl;
        this.backgroundDispatcher = coroutineDispatcher;
    }
}
