package com.android.systemui.shared.plugins;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.res.Resources;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.util.ArraySet;
import android.util.Log;
import android.view.LayoutInflater;
import com.android.systemui.plugins.Plugin;
import com.android.systemui.plugins.PluginListener;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.shared.plugins.PluginInstance;
import com.android.systemui.shared.plugins.VersionInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PluginActionManager {
    public final String mAction;
    public final boolean mAllowMultiple;
    public final Executor mBgExecutor;
    public final Context mContext;
    public final boolean mIsDebuggable;
    public final PluginListener mListener;
    public final Executor mMainExecutor;
    public final NotificationManager mNotificationManager;
    public final Class mPluginClass;
    public final PluginEnabler mPluginEnabler;
    public final PluginInstance.Factory mPluginInstanceFactory;
    private final ArrayList mPluginInstances;
    public final PackageManager mPm;
    public final ArraySet mPrivilegedPlugins;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Factory {
        public final Executor mBgExecutor;
        public final Context mContext;
        public final Executor mMainExecutor;
        public final NotificationManager mNotificationManager;
        public final PackageManager mPackageManager;
        public final PluginEnabler mPluginEnabler;
        public final PluginInstance.Factory mPluginInstanceFactory;
        public final List mPrivilegedPlugins;

        public Factory(Context context, PackageManager packageManager, Executor executor, Executor executor2, NotificationManager notificationManager, PluginEnabler pluginEnabler, List list, PluginInstance.Factory factory) {
            this.mContext = context;
            this.mPackageManager = packageManager;
            this.mMainExecutor = executor;
            this.mBgExecutor = executor2;
            this.mNotificationManager = notificationManager;
            this.mPluginEnabler = pluginEnabler;
            this.mPrivilegedPlugins = list;
            this.mPluginInstanceFactory = factory;
        }
    }

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class PluginContextWrapper extends ContextWrapper {
        public final ClassLoader mClassLoader;
        public LayoutInflater mInflater;

        public PluginContextWrapper(Context context, ClassLoader classLoader) {
            super(context);
            this.mClassLoader = classLoader;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final ClassLoader getClassLoader() {
            return this.mClassLoader;
        }

        @Override // android.content.ContextWrapper, android.content.Context
        public final Object getSystemService(String str) {
            if (!"layout_inflater".equals(str)) {
                return getBaseContext().getSystemService(str);
            }
            if (this.mInflater == null) {
                this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }
            return this.mInflater;
        }
    }

    /* renamed from: $r8$lambda$5F1fdvH66AX-KG-8BByIAWoLe8U, reason: not valid java name */
    public static void m863$r8$lambda$5F1fdvH66AXKG8BByIAWoLe8U(PluginActionManager pluginActionManager) {
        for (int size = pluginActionManager.mPluginInstances.size() - 1; size >= 0; size--) {
            pluginActionManager.mMainExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda2(pluginActionManager, (PluginInstance) pluginActionManager.mPluginInstances.get(size), 3));
        }
        pluginActionManager.mPluginInstances.clear();
        pluginActionManager.handleQueryPlugins(null);
    }

    /* renamed from: $r8$lambda$e2-SW2bOJhdGs27PogmcioXOXds, reason: not valid java name */
    public static void m864$r8$lambda$e2SW2bOJhdGs27PogmcioXOXds(PluginActionManager pluginActionManager, String str) {
        pluginActionManager.removePkg(str);
        if (pluginActionManager.mAllowMultiple || pluginActionManager.mPluginInstances.size() == 0) {
            pluginActionManager.handleQueryPlugins(str);
        }
    }

    public PluginActionManager(Context context, PackageManager packageManager, String str, PluginListener pluginListener, Class cls, boolean z, Executor executor, Executor executor2, boolean z2, NotificationManager notificationManager, PluginEnabler pluginEnabler, List list, PluginInstance.Factory factory) {
        ArraySet arraySet = new ArraySet();
        this.mPrivilegedPlugins = arraySet;
        this.mPluginInstances = new ArrayList();
        this.mPluginClass = cls;
        this.mMainExecutor = executor;
        this.mBgExecutor = executor2;
        this.mContext = context;
        this.mPm = packageManager;
        this.mAction = str;
        this.mListener = pluginListener;
        this.mAllowMultiple = z;
        this.mNotificationManager = notificationManager;
        this.mPluginEnabler = pluginEnabler;
        this.mPluginInstanceFactory = factory;
        arraySet.addAll(list);
        this.mIsDebuggable = z2;
    }

    public static void onPluginDisconnected(PluginInstance pluginInstance) {
        synchronized (pluginInstance) {
            if (pluginInstance.mHasError) {
                pluginInstance.log("onDestroy - no-op");
                return;
            }
            pluginInstance.log("onDestroy");
            pluginInstance.unloadPlugin();
            pluginInstance.mListener.onPluginDetached(pluginInstance);
        }
    }

    public final boolean checkAndDisable(String str) {
        Iterator it = new ArrayList(this.mPluginInstances).iterator();
        boolean z = false;
        while (it.hasNext()) {
            PluginInstance pluginInstance = (PluginInstance) it.next();
            if (str.startsWith(pluginInstance.mComponentName.getPackageName())) {
                z |= disable(pluginInstance, 3);
            }
        }
        return z;
    }

    public final boolean dependsOn(Plugin plugin, Class cls) {
        Iterator it = new ArrayList(this.mPluginInstances).iterator();
        while (it.hasNext()) {
            PluginInstance pluginInstance = (PluginInstance) it.next();
            if (pluginInstance.mComponentName.getClassName().equals(plugin.getClass().getName())) {
                return pluginInstance.getVersionInfo() != null && pluginInstance.getVersionInfo().mVersions.containsKey(cls);
            }
        }
        return false;
    }

    public final void destroy() {
        Iterator it = new ArrayList(this.mPluginInstances).iterator();
        while (it.hasNext()) {
            this.mMainExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda2(this, (PluginInstance) it.next(), 0));
        }
    }

    public final boolean disable(PluginInstance pluginInstance, int i) {
        ComponentName componentName = pluginInstance.mComponentName;
        if (isPluginPrivileged(componentName)) {
            return false;
        }
        Log.w("PluginActionManager", "Disabling plugin " + componentName.flattenToShortString());
        this.mPluginEnabler.setDisabled(componentName, i);
        return true;
    }

    public final boolean disableAll() {
        ArrayList arrayList = new ArrayList(this.mPluginInstances);
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            z |= disable((PluginInstance) arrayList.get(i), 4);
        }
        return z;
    }

    public final void handleQueryPlugins(String str) {
        String str2 = this.mAction;
        Intent intent = new Intent(str2);
        if (str != null) {
            intent.setPackage(str);
        }
        List<ResolveInfo> queryIntentServices = this.mPm.queryIntentServices(intent, 0);
        if (queryIntentServices.size() > 1 && !this.mAllowMultiple) {
            Log.w("PluginActionManager", "Multiple plugins found for " + str2);
            return;
        }
        Iterator<ResolveInfo> it = queryIntentServices.iterator();
        while (it.hasNext()) {
            ServiceInfo serviceInfo = it.next().serviceInfo;
            ComponentName componentName = new ComponentName(serviceInfo.packageName, serviceInfo.name);
            PluginInstance pluginInstance = null;
            if (!this.mIsDebuggable && !isPluginPrivileged(componentName)) {
                Log.w("PluginActionManager", "Plugin cannot be loaded on production build: " + componentName);
            } else if (this.mPluginEnabler.isEnabled(componentName)) {
                String packageName = componentName.getPackageName();
                try {
                    if (this.mPm.checkPermission("com.android.systemui.permission.PLUGIN", packageName) != 0) {
                        Log.d("PluginActionManager", "Plugin doesn't have permission: " + packageName);
                    } else {
                        try {
                            pluginInstance = this.mPluginInstanceFactory.create(this.mContext, this.mPm.getApplicationInfo(packageName, 0), componentName, this.mPluginClass, this.mListener);
                        } catch (VersionInfo.InvalidVersionException e) {
                            reportInvalidVersion(componentName, componentName.getClassName(), e);
                        }
                    }
                } catch (Throwable th) {
                    Log.w("PluginActionManager", "Couldn't load plugin: " + componentName, th);
                }
            }
            if (pluginInstance != null) {
                this.mPluginInstances.add(pluginInstance);
                this.mMainExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda2(this, pluginInstance, 1));
            }
        }
    }

    public final boolean isPluginPrivileged(ComponentName componentName) {
        Iterator it = this.mPrivilegedPlugins.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            ComponentName unflattenFromString = ComponentName.unflattenFromString(str);
            if (unflattenFromString == null) {
                if (str.equals(componentName.getPackageName())) {
                    return true;
                }
            } else if (unflattenFromString.equals(componentName)) {
                return true;
            }
        }
        return false;
    }

    public final void removePkg(String str) {
        for (int size = this.mPluginInstances.size() - 1; size >= 0; size--) {
            PluginInstance pluginInstance = (PluginInstance) this.mPluginInstances.get(size);
            if (pluginInstance.mComponentName.getPackageName().equals(str)) {
                this.mMainExecutor.execute(new PluginActionManager$$ExternalSyntheticLambda2(this, pluginInstance, 2));
                this.mPluginInstances.remove(size);
            }
        }
    }

    public final void reportInvalidVersion(ComponentName componentName, String str, VersionInfo.InvalidVersionException invalidVersionException) {
        Notification.Builder color = new Notification.Builder(this.mContext, PluginManager.NOTIFICATION_CHANNEL_ID).setStyle(new Notification.BigTextStyle()).setSmallIcon(Resources.getSystem().getIdentifier("stat_sys_warning", "drawable", "android")).setWhen(0L).setShowWhen(false).setVisibility(1).setColor(this.mContext.getColor(Resources.getSystem().getIdentifier("system_notification_accent_color", "color", "android")));
        try {
            str = this.mPm.getServiceInfo(componentName, 0).loadLabel(this.mPm).toString();
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (invalidVersionException.isTooNew()) {
            Notification.Builder contentTitle = color.setContentTitle("Plugin \"" + str + "\" is too new");
            StringBuilder sb = new StringBuilder("Check to see if an OTA is available.\n");
            sb.append(invalidVersionException.getMessage());
            contentTitle.setContentText(sb.toString());
        } else {
            Notification.Builder contentTitle2 = color.setContentTitle("Plugin \"" + str + "\" is too old");
            StringBuilder sb2 = new StringBuilder("Contact plugin developer to get an updated version.\n");
            sb2.append(invalidVersionException.getMessage());
            contentTitle2.setContentText(sb2.toString());
        }
        color.addAction(new Notification.Action.Builder((Icon) null, "Disable plugin", PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.android.systemui.action.DISABLE_PLUGIN").setData(Uri.parse("package://" + componentName.flattenToString())), 67108864)).build());
        this.mNotificationManager.notify(6, color.build());
        Log.w("PluginActionManager", "Error loading plugin; " + invalidVersionException.getMessage());
    }

    public final String toString() {
        return "PluginActionManager@" + hashCode() + " (action=" + this.mAction + ")";
    }
}
