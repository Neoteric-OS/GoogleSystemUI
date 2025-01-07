package com.android.systemui.shared.plugins;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.ArrayMap;
import android.util.Log;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginFragment;
import com.android.systemui.plugins.PluginLifecycleManager;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginProtector;
import com.android.systemui.plugins.PluginWrapper;
import com.android.systemui.plugins.ProtectedPluginListener;
import com.android.systemui.plugins.annotations.ProvidesInterface;
import com.android.systemui.shared.plugins.PluginActionManager;
import com.android.systemui.shared.plugins.VersionInfo;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PluginInstance implements PluginLifecycleManager, ProtectedPluginListener {
    public final Context mAppContext;
    public final ComponentName mComponentName;
    public final PluginListener mListener;
    public PluginActionManager.PluginContextWrapper mPluginContext;
    public final PluginFactory mPluginFactory;
    public final String mTag;
    public boolean mHasError = false;
    public BiConsumer mLogConsumer = null;
    public Plugin mPlugin = null;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final ClassLoader mBaseClassLoader;
        public final InstanceFactory mInstanceFactory;
        public final boolean mIsDebug;
        public final List mPrivilegedPlugins;
        public final InstanceFactory mVersionChecker;

        public Factory(ClassLoader classLoader, InstanceFactory instanceFactory, InstanceFactory instanceFactory2, List list, boolean z) {
            this.mPrivilegedPlugins = list;
            this.mBaseClassLoader = classLoader;
            this.mInstanceFactory = instanceFactory;
            this.mVersionChecker = instanceFactory2;
            this.mIsDebug = z;
        }

        public final PluginInstance create(Context context, ApplicationInfo applicationInfo, ComponentName componentName, Class cls, PluginListener pluginListener) {
            return new PluginInstance(context, pluginListener, componentName, new PluginFactory(context, this.mInstanceFactory, applicationInfo, componentName, this.mVersionChecker, cls, new PluginInstance$Factory$$ExternalSyntheticLambda0(this, applicationInfo)));
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class InstanceFactory {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PluginFactory {
        public final ApplicationInfo mAppInfo;
        public final PluginInstance$Factory$$ExternalSyntheticLambda0 mClassLoaderFactory;
        public final ComponentName mComponentName;
        public final Context mContext;
        public final InstanceFactory mInstanceFactory;
        public final Class mPluginClass;
        public final InstanceFactory mVersionChecker;

        public PluginFactory(Context context, InstanceFactory instanceFactory, ApplicationInfo applicationInfo, ComponentName componentName, InstanceFactory instanceFactory2, Class cls, PluginInstance$Factory$$ExternalSyntheticLambda0 pluginInstance$Factory$$ExternalSyntheticLambda0) {
            this.mContext = context;
            this.mAppInfo = applicationInfo;
            this.mComponentName = componentName;
            this.mPluginClass = cls;
            this.mClassLoaderFactory = pluginInstance$Factory$$ExternalSyntheticLambda0;
        }

        public final boolean checkVersion(Plugin plugin) {
            if (plugin == null) {
                plugin = createPlugin(null);
            }
            if (plugin instanceof PluginWrapper) {
                plugin = (Plugin) ((PluginWrapper) plugin).getPlugin();
            }
            Class<?> cls = plugin.getClass();
            Class cls2 = this.mPluginClass;
            VersionInfo versionInfo = new VersionInfo();
            if (versionInfo.mDefault == null) {
                versionInfo.mDefault = cls2;
            }
            versionInfo.addClass(cls2, false);
            VersionInfo versionInfo2 = new VersionInfo();
            if (versionInfo2.mDefault == null) {
                versionInfo2.mDefault = cls;
            }
            versionInfo2.addClass(cls, false);
            if (!versionInfo2.mVersions.isEmpty()) {
                ArrayMap arrayMap = new ArrayMap(versionInfo.mVersions);
                versionInfo2.mVersions.forEach(new BiConsumer(versionInfo, arrayMap) { // from class: com.android.systemui.shared.plugins.VersionInfo.1
                    public final /* synthetic */ VersionInfo this$0;
                    public final /* synthetic */ ArrayMap val$versions;

                    public AnonymousClass1(VersionInfo versionInfo3, ArrayMap arrayMap2) {
                        this.val$versions = arrayMap2;
                    }

                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        Class cls3 = (Class) obj;
                        Version version = (Version) obj2;
                        Version version2 = (Version) this.val$versions.remove(cls3);
                        if (version2 == null) {
                            ProvidesInterface providesInterface = (ProvidesInterface) cls3.getDeclaredAnnotation(ProvidesInterface.class);
                            version2 = providesInterface != null ? new Version(providesInterface.version(), false) : null;
                        }
                        if (version2 == null) {
                            throw new InvalidVersionException(cls3.getSimpleName().concat(" does not provide an interface"));
                        }
                        int i = version.mVersion;
                        int i2 = version2.mVersion;
                        if (i2 != i) {
                            int i3 = version.mVersion;
                            throw new InvalidVersionException(cls3, i2 < i3, i2, i3);
                        }
                    }
                });
                arrayMap2.forEach(new VersionInfo.AnonymousClass2());
            } else if (plugin.getVersion() != ((VersionInfo.Version) versionInfo3.mVersions.get(versionInfo3.mDefault)).mVersion) {
                return false;
            }
            return true;
        }

        public final Plugin createPlugin(PluginInstance pluginInstance) {
            try {
                Plugin plugin = (Plugin) Class.forName(this.mComponentName.getClassName(), true, (ClassLoader) this.mClassLoaderFactory.get()).newInstance();
                Objects.toString(plugin);
                return (Plugin) PluginProtector.protectIfAble(plugin, pluginInstance);
            } catch (ReflectiveOperationException e) {
                Log.wtf("PluginInstance", "Failed to load plugin", e);
                return null;
            }
        }

        public final PluginActionManager.PluginContextWrapper createPluginContext() {
            try {
                return new PluginActionManager.PluginContextWrapper(this.mContext.createApplicationContext(this.mAppInfo, 0), (ClassLoader) this.mClassLoaderFactory.get());
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("PluginInstance", "Failed to create plugin context", e);
                return null;
            }
        }
    }

    public PluginInstance(Context context, PluginListener pluginListener, ComponentName componentName, PluginFactory pluginFactory) {
        this.mAppContext = context;
        this.mListener = pluginListener;
        this.mComponentName = componentName;
        this.mPluginFactory = pluginFactory;
        this.mTag = "PluginInstance[" + componentName.getShortClassName() + "]@" + Integer.toHexString(hashCode());
        if (this.mPlugin != null) {
            this.mPluginContext = pluginFactory.createPluginContext();
        }
    }

    public final synchronized boolean checkVersion() {
        if (this.mHasError) {
            return false;
        }
        Plugin plugin = this.mPlugin;
        if (plugin == null) {
            return true;
        }
        if (this.mPluginFactory.checkVersion(plugin)) {
            return true;
        }
        Log.wtf("PluginInstance", "Version check failed for ".concat(this.mPlugin.getClass().getSimpleName()));
        this.mHasError = true;
        unloadPlugin();
        this.mListener.onPluginDetached(this);
        return false;
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final ComponentName getComponentName() {
        return this.mComponentName;
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final String getPackage() {
        return this.mComponentName.getPackageName();
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final Plugin getPlugin() {
        if (this.mHasError) {
            return null;
        }
        return this.mPlugin;
    }

    public Context getPluginContext() {
        return this.mPluginContext;
    }

    public final VersionInfo getVersionInfo() {
        Plugin plugin = this.mPlugin;
        PluginFactory pluginFactory = this.mPluginFactory;
        if (plugin == null) {
            plugin = pluginFactory.createPlugin(null);
        }
        if (plugin instanceof PluginWrapper) {
            plugin = (Plugin) ((PluginWrapper) plugin).getPlugin();
        }
        Class<?> cls = plugin.getClass();
        VersionInfo versionInfo = new VersionInfo();
        if (versionInfo.mDefault == null) {
            versionInfo.mDefault = cls;
        }
        versionInfo.addClass(cls, false);
        if (versionInfo.mVersions.isEmpty()) {
            return null;
        }
        return versionInfo;
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final synchronized void loadPlugin() {
        if (this.mHasError) {
            log("Previous LinkageError detected for plugin class");
            return;
        }
        if (this.mPlugin != null) {
            log("Load request when already loaded");
            return;
        }
        this.mPlugin = this.mPluginFactory.createPlugin(this);
        PluginActionManager.PluginContextWrapper createPluginContext = this.mPluginFactory.createPluginContext();
        this.mPluginContext = createPluginContext;
        if (this.mPlugin != null && createPluginContext != null) {
            if (!checkVersion()) {
                log("loadPlugin: version check failed");
                return;
            }
            log("Loaded plugin; running callbacks");
            Plugin plugin = this.mPlugin;
            if (!(plugin instanceof PluginFragment)) {
                plugin.onCreate(this.mAppContext, this.mPluginContext);
            }
            this.mListener.onPluginLoaded(this.mPlugin, this.mPluginContext, this);
            return;
        }
        Log.e(this.mTag, "Requested load, but failed");
    }

    public final void log(String str) {
        BiConsumer biConsumer = this.mLogConsumer;
        if (biConsumer != null) {
            biConsumer.accept(this.mTag, str);
        }
    }

    @Override // com.android.systemui.plugins.ProtectedPluginListener
    public final synchronized boolean onFail(String str, String str2, LinkageError linkageError) {
        this.mHasError = true;
        unloadPlugin();
        this.mListener.onPluginDetached(this);
        return true;
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final void setLogFunc(BiConsumer biConsumer) {
        this.mLogConsumer = biConsumer;
    }

    public final String toString() {
        return this.mTag;
    }

    @Override // com.android.systemui.plugins.PluginLifecycleManager
    public final synchronized void unloadPlugin() {
        if (this.mPlugin == null) {
            log("Unload request when already unloaded");
            return;
        }
        log("Unloading plugin, running callbacks");
        this.mListener.onPluginUnloaded(this.mPlugin, this);
        Plugin plugin = this.mPlugin;
        if (!(plugin instanceof PluginFragment)) {
            plugin.onDestroy();
        }
        this.mPlugin = null;
        this.mPluginContext = null;
    }
}
