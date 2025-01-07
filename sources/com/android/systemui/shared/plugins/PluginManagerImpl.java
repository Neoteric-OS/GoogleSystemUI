package com.android.systemui.shared.plugins;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemProperties;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;
import android.widget.Toast;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.shared.plugins.PluginActionManager;
import com.android.systemui.shared.system.UncaughtExceptionPreHandlerManager;
import java.lang.Thread;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PluginManagerImpl extends BroadcastReceiver implements PluginManager {
    public final PluginActionManager.Factory mActionManagerFactory;
    public final Context mContext;
    public final boolean mIsDebuggable;
    public boolean mListening;
    public final PluginEnabler mPluginEnabler;
    public final PluginPrefs mPluginPrefs;
    public final ArraySet mPrivilegedPlugins;
    public final ArrayMap mPluginMap = new ArrayMap();
    public final Map mClassLoaders = new ArrayMap();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class ClassLoaderFilter extends ClassLoader {
        public final ClassLoader mBase;
        public final String[] mPackages;

        public ClassLoaderFilter(ClassLoader classLoader, String... strArr) {
            super(ClassLoader.getSystemClassLoader());
            this.mBase = classLoader;
            this.mPackages = strArr;
        }

        @Override // java.lang.ClassLoader
        public final Class loadClass(String str, boolean z) {
            for (String str2 : this.mPackages) {
                if (str.startsWith(str2)) {
                    return this.mBase.loadClass(str);
                }
            }
            return super.loadClass(str, z);
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class CrashWhilePluginActiveException extends RuntimeException {
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PluginExceptionHandler implements Thread.UncaughtExceptionHandler {
        public PluginExceptionHandler() {
        }

        public final boolean checkStack(Throwable th) {
            boolean z;
            if (th == null) {
                return false;
            }
            synchronized (this) {
                try {
                    z = false;
                    for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                        Iterator it = PluginManagerImpl.this.mPluginMap.values().iterator();
                        while (it.hasNext()) {
                            z |= ((PluginActionManager) it.next()).checkAndDisable(stackTraceElement.getClassName());
                        }
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
            return checkStack(th.getCause()) | z;
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public final void uncaughtException(Thread thread, Throwable th) {
            if (SystemProperties.getBoolean("plugin.debugging", false)) {
                return;
            }
            boolean checkStack = checkStack(th);
            if (!checkStack) {
                synchronized (this) {
                    try {
                        Iterator it = PluginManagerImpl.this.mPluginMap.values().iterator();
                        while (it.hasNext()) {
                            checkStack |= ((PluginActionManager) it.next()).disableAll();
                        }
                    } finally {
                    }
                }
            }
            if (checkStack) {
                new CrashWhilePluginActiveException(th);
            }
        }
    }

    public PluginManagerImpl(Context context, PluginActionManager.Factory factory, boolean z, UncaughtExceptionPreHandlerManager uncaughtExceptionPreHandlerManager, PluginEnabler pluginEnabler, PluginPrefs pluginPrefs, List list) {
        ArraySet arraySet = new ArraySet();
        this.mPrivilegedPlugins = arraySet;
        this.mContext = context;
        this.mActionManagerFactory = factory;
        this.mIsDebuggable = z;
        arraySet.addAll(list);
        this.mPluginPrefs = pluginPrefs;
        this.mPluginEnabler = pluginEnabler;
        uncaughtExceptionPreHandlerManager.registerHandler(new PluginExceptionHandler());
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final void addPluginListener(PluginListener pluginListener, Class cls) {
        addPluginListener(pluginListener, cls, false);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final boolean dependsOn(Plugin plugin, Class cls) {
        synchronized (this) {
            for (int i = 0; i < this.mPluginMap.size(); i++) {
                try {
                    if (((PluginActionManager) this.mPluginMap.valueAt(i)).dependsOn(plugin, cls)) {
                        return true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            return false;
        }
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final String[] getPrivilegedPlugins() {
        return (String[]) this.mPrivilegedPlugins.toArray(new String[0]);
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        int disableReason;
        if ("android.intent.action.USER_UNLOCKED".equals(intent.getAction())) {
            synchronized (this) {
                try {
                    for (PluginActionManager pluginActionManager : this.mPluginMap.values()) {
                        pluginActionManager.mBgExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda3(pluginActionManager));
                    }
                } finally {
                }
            }
            return;
        }
        if ("com.android.systemui.action.DISABLE_PLUGIN".equals(intent.getAction())) {
            ComponentName unflattenFromString = ComponentName.unflattenFromString(intent.getData().toString().substring(10));
            Iterator it = this.mPrivilegedPlugins.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                ComponentName unflattenFromString2 = ComponentName.unflattenFromString(str);
                if (unflattenFromString2 != null) {
                    if (unflattenFromString2.equals(unflattenFromString)) {
                        return;
                    }
                } else if (str.equals(unflattenFromString.getPackageName())) {
                    return;
                }
            }
            this.mPluginEnabler.setDisabled(unflattenFromString, 2);
            ((NotificationManager) this.mContext.getSystemService(NotificationManager.class)).cancel(unflattenFromString.getClassName(), 6);
            return;
        }
        final String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
        ComponentName unflattenFromString3 = ComponentName.unflattenFromString(encodedSchemeSpecificPart);
        if (((ArrayMap) this.mClassLoaders).remove(encodedSchemeSpecificPart) != null && Build.IS_ENG) {
            Toast.makeText(this.mContext, "Reloading " + encodedSchemeSpecificPart, 1).show();
        }
        if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction()) && unflattenFromString3 != null && ((disableReason = this.mPluginEnabler.getDisableReason(unflattenFromString3)) == 3 || disableReason == 4 || disableReason == 2)) {
            Log.i("PluginManagerImpl", "Re-enabling previously disabled plugin that has been updated: " + unflattenFromString3.flattenToShortString());
            this.mPluginEnabler.setEnabled(unflattenFromString3);
        }
        synchronized (this) {
            try {
                if (!"android.intent.action.PACKAGE_ADDED".equals(intent.getAction()) && !"android.intent.action.PACKAGE_CHANGED".equals(intent.getAction()) && !"android.intent.action.PACKAGE_REPLACED".equals(intent.getAction())) {
                    for (final PluginActionManager pluginActionManager2 : this.mPluginMap.values()) {
                        final int i = 1;
                        pluginActionManager2.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.shared.plugins.PluginActionManager$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                switch (i) {
                                    case 0:
                                        PluginActionManager.m864$r8$lambda$e2SW2bOJhdGs27PogmcioXOXds(pluginActionManager2, encodedSchemeSpecificPart);
                                        break;
                                    default:
                                        pluginActionManager2.removePkg(encodedSchemeSpecificPart);
                                        break;
                                }
                            }
                        });
                    }
                }
                for (final PluginActionManager pluginActionManager3 : this.mPluginMap.values()) {
                    final int i2 = 0;
                    pluginActionManager3.mBgExecutor.execute(new Runnable() { // from class: com.android.systemui.shared.plugins.PluginActionManager$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            switch (i2) {
                                case 0:
                                    PluginActionManager.m864$r8$lambda$e2SW2bOJhdGs27PogmcioXOXds(pluginActionManager3, encodedSchemeSpecificPart);
                                    break;
                                default:
                                    pluginActionManager3.removePkg(encodedSchemeSpecificPart);
                                    break;
                            }
                        }
                    });
                }
            } finally {
            }
        }
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final void removePluginListener(PluginListener pluginListener) {
        synchronized (this) {
            try {
                if (this.mPluginMap.containsKey(pluginListener)) {
                    ((PluginActionManager) this.mPluginMap.remove(pluginListener)).destroy();
                    if (this.mPluginMap.size() == 0 && this.mListening) {
                        this.mListening = false;
                        this.mContext.unregisterReceiver(this);
                    }
                }
            } finally {
            }
        }
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final void addPluginListener(PluginListener pluginListener, Class cls, boolean z) {
        addPluginListener(PluginManager.Helper.getAction(cls), pluginListener, cls, z);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final void addPluginListener(String str, PluginListener pluginListener, Class cls) {
        addPluginListener(str, pluginListener, cls, false);
    }

    @Override // com.android.systemui.plugins.PluginManager
    public final void addPluginListener(String str, PluginListener pluginListener, Class cls, boolean z) {
        PluginPrefs pluginPrefs = this.mPluginPrefs;
        synchronized (pluginPrefs) {
            if (((ArraySet) pluginPrefs.mPluginActions).add(str)) {
                pluginPrefs.mSharedPrefs.edit().putStringSet("actions", pluginPrefs.mPluginActions).apply();
            }
        }
        PluginActionManager.Factory factory = this.mActionManagerFactory;
        boolean z2 = this.mIsDebuggable;
        PluginActionManager pluginActionManager = new PluginActionManager(factory.mContext, factory.mPackageManager, str, pluginListener, cls, z, factory.mMainExecutor, factory.mBgExecutor, z2, factory.mNotificationManager, factory.mPluginEnabler, factory.mPrivilegedPlugins, factory.mPluginInstanceFactory);
        pluginActionManager.mBgExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda3(pluginActionManager));
        synchronized (this) {
            this.mPluginMap.put(pluginListener, pluginActionManager);
        }
        if (this.mListening) {
            return;
        }
        this.mListening = true;
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this, intentFilter);
        intentFilter.addAction(PluginManager.PLUGIN_CHANGED);
        intentFilter.addAction("com.android.systemui.action.DISABLE_PLUGIN");
        intentFilter.addDataScheme("package");
        this.mContext.registerReceiver(this, intentFilter, "com.android.systemui.permission.PLUGIN", null, 2);
        this.mContext.registerReceiver(this, new IntentFilter("android.intent.action.USER_UNLOCKED"));
    }
}
