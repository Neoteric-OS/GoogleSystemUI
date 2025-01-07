package com.android.systemui.dagger;

import com.android.systemui.scene.shared.model.SceneContainerConfig;
import com.android.systemui.scene.shared.model.SceneDataSourceDelegator;
import dagger.internal.Provider;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class SystemUIModule_ProvidesSceneDataSourceDelegatorFactory implements Provider {
    public static SceneDataSourceDelegator providesSceneDataSourceDelegator(CoroutineScope coroutineScope, SceneContainerConfig sceneContainerConfig) {
        return new SceneDataSourceDelegator(coroutineScope, sceneContainerConfig);
    }
}
