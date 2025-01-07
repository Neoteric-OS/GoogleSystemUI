package com.android.systemui.plugins;

import android.content.Context;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PluginsModule_ProvidesPrivilegedPluginsFactory implements Provider {
    private final javax.inject.Provider contextProvider;

    public PluginsModule_ProvidesPrivilegedPluginsFactory(javax.inject.Provider provider) {
        this.contextProvider = provider;
    }

    public static PluginsModule_ProvidesPrivilegedPluginsFactory create(javax.inject.Provider provider) {
        return new PluginsModule_ProvidesPrivilegedPluginsFactory(provider);
    }

    public static List providesPrivilegedPlugins(Context context) {
        List providesPrivilegedPlugins = PluginsModule.providesPrivilegedPlugins(context);
        Preconditions.checkNotNullFromProvides(providesPrivilegedPlugins);
        return providesPrivilegedPlugins;
    }

    @Override // javax.inject.Provider
    public List get() {
        return providesPrivilegedPlugins((Context) this.contextProvider.get());
    }
}
