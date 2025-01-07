package com.android.systemui.plugins;

import android.content.Context;
import com.android.systemui.shared.plugins.PluginActionManager;
import com.android.systemui.shared.plugins.PluginEnabler;
import com.android.systemui.shared.plugins.PluginPrefs;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PluginsModule_ProvidesPluginManagerFactory implements Provider {
    private final javax.inject.Provider contextProvider;
    private final javax.inject.Provider debugProvider;
    private final javax.inject.Provider instanceManagerFactoryProvider;
    private final javax.inject.Provider pluginEnablerProvider;
    private final javax.inject.Provider pluginPrefsProvider;
    private final javax.inject.Provider preHandlerManagerProvider;
    private final javax.inject.Provider privilegedPluginsProvider;

    public PluginsModule_ProvidesPluginManagerFactory(javax.inject.Provider provider, javax.inject.Provider provider2, javax.inject.Provider provider3, javax.inject.Provider provider4, javax.inject.Provider provider5, javax.inject.Provider provider6, javax.inject.Provider provider7) {
        this.contextProvider = provider;
        this.instanceManagerFactoryProvider = provider2;
        this.debugProvider = provider3;
        this.preHandlerManagerProvider = provider4;
        this.pluginEnablerProvider = provider5;
        this.pluginPrefsProvider = provider6;
        this.privilegedPluginsProvider = provider7;
    }

    public static PluginsModule_ProvidesPluginManagerFactory create(javax.inject.Provider provider, javax.inject.Provider provider2, javax.inject.Provider provider3, javax.inject.Provider provider4, javax.inject.Provider provider5, javax.inject.Provider provider6, javax.inject.Provider provider7) {
        return new PluginsModule_ProvidesPluginManagerFactory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static PluginManager providesPluginManager(Context context, PluginActionManager.Factory factory, boolean z, UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager, PluginEnabler pluginEnabler, PluginPrefs pluginPrefs, List list) {
        PluginManager providesPluginManager = PluginsModule.providesPluginManager(context, factory, z, uncaughtExceptionPreHandlerManager, pluginEnabler, pluginPrefs, list);
        Preconditions.checkNotNullFromProvides(providesPluginManager);
        return providesPluginManager;
    }

    @Override // javax.inject.Provider
    public PluginManager get() {
        return providesPluginManager((Context) this.contextProvider.get(), (PluginActionManager.Factory) this.instanceManagerFactoryProvider.get(), ((Boolean) this.debugProvider.get()).booleanValue(), (UncaughtExceptionPreHandlerManager) this.preHandlerManagerProvider.get(), (PluginEnabler) this.pluginEnablerProvider.get(), (PluginPrefs) this.pluginPrefsProvider.get(), (List) this.privilegedPluginsProvider.get());
    }
}
