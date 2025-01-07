package com.android.systemui.util.settings;

import android.content.ContentResolver;
import com.android.systemui.dagger.SystemUIModule$$ExternalSyntheticLambda0;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class SecureSettingsImpl_Factory implements Provider {
    public static SecureSettingsImpl newInstance(ContentResolver contentResolver, SystemUIModule$$ExternalSyntheticLambda0 systemUIModule$$ExternalSyntheticLambda0, CoroutineDispatcher coroutineDispatcher) {
        return new SecureSettingsImpl(contentResolver, systemUIModule$$ExternalSyntheticLambda0, coroutineDispatcher);
    }
}
