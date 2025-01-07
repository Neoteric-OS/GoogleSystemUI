package com.android.systemui.dagger;

import com.android.systemui.dump.DumpManager;
import com.android.systemui.model.SceneContainerPlugin;
import com.android.systemui.model.SysUiState;
import com.android.systemui.settings.DisplayTracker;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SystemUIModule_ProvideSysUiStateFactory implements Provider {
    public static SysUiState provideSysUiState(DisplayTracker displayTracker, DumpManager dumpManager, SceneContainerPlugin sceneContainerPlugin) {
        SysUiState sysUiState = new SysUiState(displayTracker, sceneContainerPlugin);
        dumpManager.registerDumpable(sysUiState);
        return sysUiState;
    }
}
