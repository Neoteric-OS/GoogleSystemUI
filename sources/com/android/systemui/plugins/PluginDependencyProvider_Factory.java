package com.android.systemui.plugins;

import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Provider;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PluginDependencyProvider_Factory implements Provider {
    private final javax.inject.Provider managerLazyProvider;

    public PluginDependencyProvider_Factory(javax.inject.Provider provider) {
        this.managerLazyProvider = provider;
    }

    public static PluginDependencyProvider_Factory create(javax.inject.Provider provider) {
        return new PluginDependencyProvider_Factory(provider);
    }

    public static PluginDependencyProvider newInstance(Lazy lazy) {
        return new PluginDependencyProvider(lazy);
    }

    @Override // javax.inject.Provider
    public PluginDependencyProvider get() {
        final javax.inject.Provider provider = this.managerLazyProvider;
        provider.getClass();
        return newInstance(DoubleCheck.lazy(new Provider() { // from class: dagger.internal.Providers$1
            @Override // javax.inject.Provider
            public final Object get() {
                return javax.inject.Provider.this.get();
            }
        }));
    }
}
