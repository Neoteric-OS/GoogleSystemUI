package com.android.systemui.settings;

import android.content.ContentResolver;
import com.android.systemui.shared.settings.data.repository.SecureSettingsRepositoryImpl;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SecureSettingsRepositoryModule_ProvideSecureSettingsRepositoryFactory implements Provider {
    public static SecureSettingsRepositoryImpl provideSecureSettingsRepository(ContentResolver contentResolver, CoroutineDispatcher coroutineDispatcher) {
        return new SecureSettingsRepositoryImpl(contentResolver, coroutineDispatcher);
    }
}
