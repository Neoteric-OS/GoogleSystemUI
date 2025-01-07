package com.android.systemui.plugins;

import android.content.Context;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PluginListener {
    default boolean onPluginAttached(PluginLifecycleManager pluginLifecycleManager) {
        return true;
    }

    default void onPluginLoaded(Plugin plugin, Context context, PluginLifecycleManager pluginLifecycleManager) {
        onPluginConnected(plugin, context);
    }

    default void onPluginUnloaded(Plugin plugin, PluginLifecycleManager pluginLifecycleManager) {
        onPluginDisconnected(plugin);
    }

    default void onPluginDetached(PluginLifecycleManager pluginLifecycleManager) {
    }

    @Deprecated
    default void onPluginDisconnected(Plugin plugin) {
    }

    @Deprecated
    default void onPluginConnected(Plugin plugin, Context context) {
    }
}
