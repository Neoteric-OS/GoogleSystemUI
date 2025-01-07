package com.android.wm.shell.dagger;

import android.content.Context;
import com.android.wm.shell.desktopmode.DesktopModeTaskRepository;
import com.android.wm.shell.desktopmode.persistence.DesktopPersistentRepository;
import com.android.wm.shell.sysui.ShellInit;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class WMShellModule_ProvideDesktopModeTaskRepositoryFactory implements Provider {
    public static DesktopModeTaskRepository provideDesktopModeTaskRepository(Context context, ShellInit shellInit, DesktopPersistentRepository desktopPersistentRepository, CoroutineScope coroutineScope) {
        return new DesktopModeTaskRepository(context, shellInit, desktopPersistentRepository, coroutineScope);
    }
}
