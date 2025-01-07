package com.android.systemui.shared.plugins;

import android.app.ActivityThread;
import android.app.LoadedApk;
import android.content.ComponentName;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;
import com.android.systemui.shared.plugins.PluginInstance;
import com.android.systemui.shared.plugins.PluginManagerImpl;
import dalvik.system.PathClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Supplier;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final /* synthetic */ class PluginInstance$Factory$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ PluginInstance.Factory f$0;
    public final /* synthetic */ ApplicationInfo f$1;

    public /* synthetic */ PluginInstance$Factory$$ExternalSyntheticLambda0(PluginInstance.Factory factory, ApplicationInfo applicationInfo) {
        this.f$0 = factory;
        this.f$1 = applicationInfo;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        PluginInstance.Factory factory = this.f$0;
        ApplicationInfo applicationInfo = this.f$1;
        ClassLoader classLoader = factory.mBaseClassLoader;
        if (!factory.mIsDebug) {
            String str = applicationInfo.packageName;
            for (String str2 : factory.mPrivilegedPlugins) {
                ComponentName unflattenFromString = ComponentName.unflattenFromString(str2);
                if (unflattenFromString != null) {
                    if (unflattenFromString.getPackageName().equals(str)) {
                    }
                } else if (str2.equals(str)) {
                }
            }
            Log.w("PluginInstance", "Cannot get class loader for non-privileged plugin. Src:" + applicationInfo.sourceDir + ", pkg: " + applicationInfo.packageName);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        LoadedApk.makePaths((ActivityThread) null, true, applicationInfo, arrayList, arrayList2);
        String str3 = File.pathSeparator;
        return new PathClassLoader(TextUtils.join(str3, arrayList), TextUtils.join(str3, arrayList2), new PluginManagerImpl.ClassLoaderFilter(classLoader, "androidx.constraintlayout.widget", "com.android.systemui.common", "com.android.systemui.log", "com.android.systemui.plugin"));
    }
}
