package com.android.systemui.util.settings;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import com.android.systemui.dagger.SystemUIModule$$ExternalSyntheticLambda0;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SecureSettingsImpl implements SecureSettings {
    public final CoroutineDispatcher mBgDispatcher;
    public final ContentResolver mContentResolver;
    public final SystemUIModule$$ExternalSyntheticLambda0 mCurrentUserProvider;

    public SecureSettingsImpl(ContentResolver contentResolver, SystemUIModule$$ExternalSyntheticLambda0 systemUIModule$$ExternalSyntheticLambda0, CoroutineDispatcher coroutineDispatcher) {
        this.mContentResolver = contentResolver;
        this.mCurrentUserProvider = systemUIModule$$ExternalSyntheticLambda0;
        this.mBgDispatcher = coroutineDispatcher;
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    public final CoroutineDispatcher getBackgroundDispatcher() {
        return this.mBgDispatcher;
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    public final ContentResolver getContentResolver() {
        return this.mContentResolver;
    }

    @Override // com.android.systemui.util.settings.UserSettingsProxy
    public final SystemUIModule$$ExternalSyntheticLambda0 getCurrentUserProvider() {
        return this.mCurrentUserProvider;
    }

    @Override // com.android.systemui.util.settings.UserSettingsProxy
    public final String getStringForUser(int i, String str) {
        return Settings.Secure.getStringForUser(this.mContentResolver, str, getRealUserHandle(i));
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    public final Uri getUriFor(String str) {
        return Settings.Secure.getUriFor(str);
    }

    @Override // com.android.systemui.util.settings.UserSettingsProxy
    public final boolean putStringForUser(String str, String str2, int i) {
        return Settings.Secure.putStringForUser(this.mContentResolver, str, str2, getRealUserHandle(i));
    }
}
