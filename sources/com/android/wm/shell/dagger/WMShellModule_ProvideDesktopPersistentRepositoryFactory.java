package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.desktopmode.persistence.DesktopPersistentRepository;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideDesktopPersistentRepositoryFactory implements Provider {
    public static DesktopPersistentRepository provideDesktopPersistentRepository(Context context, CoroutineScope coroutineScope) {
        return new DesktopPersistentRepository(context, coroutineScope);
    }
}
