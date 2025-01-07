package com.android.systemui.util.settings;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.Settings;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class GlobalSettingsImpl implements GlobalSettings {
    public final CoroutineDispatcher mBgDispatcher;
    public final ContentResolver mContentResolver;

    public GlobalSettingsImpl(ContentResolver contentResolver, CoroutineDispatcher coroutineDispatcher) {
        this.mContentResolver = contentResolver;
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

    @Override // com.android.systemui.util.settings.SettingsProxy
    public final String getString(String str) {
        return Settings.Global.getString(this.mContentResolver, str);
    }

    @Override // com.android.systemui.util.settings.SettingsProxy
    public final Uri getUriFor(String str) {
        return Settings.Global.getUriFor(str);
    }

    public final boolean putString(String str, String str2) {
        return Settings.Global.putString(this.mContentResolver, str, str2);
    }
}
