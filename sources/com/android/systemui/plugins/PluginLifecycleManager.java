package com.android.systemui.plugins;

import android.content.ComponentName;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PluginLifecycleManager {
    ComponentName getComponentName();

    String getPackage();

    Plugin getPlugin();

    default boolean isLoaded() {
        return getPlugin() != null;
    }

    void loadPlugin();

    void setLogFunc(BiConsumer biConsumer);

    void unloadPlugin();
}
