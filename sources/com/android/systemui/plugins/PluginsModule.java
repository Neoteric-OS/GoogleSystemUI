package com.android.systemui.plugins;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.android.systemui.dagger.PluginModule;
import com.android.systemui.shared.plugins.PluginActionManager;
import com.android.systemui.shared.plugins.PluginEnabler;
import com.android.systemui.shared.plugins.PluginInstance;
import com.android.systemui.shared.plugins.PluginManagerImpl;
import com.android.systemui.shared.plugins.PluginPrefs;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import com.android.systemui.util.concurrency.ThreadFactory;
import com.android.systemui.util.concurrency.ThreadFactoryImpl;
import com.android.wm.shell.R;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class PluginsModule {
    public static final String PLUGIN_DEBUG = "plugin_debug";
    public static final String PLUGIN_PRIVILEGED = "plugin_privileged";
    public static final String PLUGIN_THREAD = "plugin_thread";

    public static PluginActionManager.Factory providePluginInstanceManagerFactory(Context context, PackageManager packageManager, Executor executor, Executor executor2, NotificationManager notificationManager, PluginEnabler pluginEnabler, List list, PluginInstance.Factory factory) {
        return new PluginActionManager.Factory(context, packageManager, executor, executor2, notificationManager, pluginEnabler, list, factory);
    }

    public static boolean providesPluginDebug() {
        return Build.IS_DEBUGGABLE;
    }

    public static Executor providesPluginExecutor(ThreadFactory threadFactory) {
        return ((ThreadFactoryImpl) threadFactory).buildExecutorOnNewThread("plugin");
    }

    public static PluginInstance.Factory providesPluginInstanceFactory(List list, boolean z) {
        return new PluginInstance.Factory(PluginModule.class.getClassLoader(), new PluginInstance.InstanceFactory(), new PluginInstance.InstanceFactory(), list, z);
    }

    public static PluginManager providesPluginManager(Context context, PluginActionManager.Factory factory, boolean z, UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager, PluginEnabler pluginEnabler, PluginPrefs pluginPrefs, List list) {
        return new PluginManagerImpl(context, factory, z, uncaughtExceptionPreHandlerManager, pluginEnabler, pluginPrefs, list);
    }

    public static PluginPrefs providesPluginPrefs(Context context) {
        return new PluginPrefs(context);
    }

    public static List providesPrivilegedPlugins(Context context) {
        return Arrays.asList(context.getResources().getStringArray(R.array.config_pluginAllowlist));
    }

    public abstract PluginEnabler bindsPluginEnablerImpl(PluginEnablerImpl pluginEnablerImpl);
}
