package com.android.systemui.plugins;

import android.util.ArrayMap;
import com.android.systemui.Dependency;
import com.android.systemui.plugins.PluginDependency;
import dagger.Lazy;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public class PluginDependencyProvider extends PluginDependency.DependencyProvider {
    private final ArrayMap mDependencies = new ArrayMap();
    private final Lazy mManagerLazy;

    public PluginDependencyProvider(Lazy lazy) {
        this.mManagerLazy = lazy;
        PluginDependency.sProvider = this;
    }

    public void allowPluginDependency(Class cls, Object obj) {
        synchronized (this.mDependencies) {
            this.mDependencies.put(cls, obj);
        }
    }

    @Override // com.android.systemui.plugins.PluginDependency.DependencyProvider
    public Object get(Plugin plugin, Class cls) {
        Object obj;
        if (!((PluginManager) this.mManagerLazy.get()).dependsOn(plugin, cls)) {
            throw new IllegalArgumentException(plugin.getClass() + " does not depend on " + cls);
        }
        synchronized (this.mDependencies) {
            try {
                if (!this.mDependencies.containsKey(cls)) {
                    throw new IllegalArgumentException("Unknown dependency " + cls);
                }
                obj = this.mDependencies.get(cls);
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public void allowPluginDependency(Class cls) {
        allowPluginDependency(cls, Dependency.sDependency.getDependencyInner(cls));
    }
}
