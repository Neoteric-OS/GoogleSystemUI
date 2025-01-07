package com.android.systemui.plugins;

import android.text.TextUtils;
import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface PluginManager {
    public static final String NOTIFICATION_CHANNEL_ID = "ALR";
    public static final String PLUGIN_CHANGED = "com.android.systemui.action.PLUGIN_CHANGED";

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public class Helper {
        public static String getAction(Class cls) {
            ProvidesInterface providesInterface = (ProvidesInterface) cls.getDeclaredAnnotation(ProvidesInterface.class);
            if (providesInterface == null) {
                throw new RuntimeException(cls + " doesn't provide an interface");
            }
            if (!TextUtils.isEmpty(providesInterface.action())) {
                return providesInterface.action();
            }
            throw new RuntimeException(cls + " doesn't provide an action");
        }
    }

    void addPluginListener(PluginListener pluginListener, Class cls);

    void addPluginListener(PluginListener pluginListener, Class cls, boolean z);

    void addPluginListener(String str, PluginListener pluginListener, Class cls);

    void addPluginListener(String str, PluginListener pluginListener, Class cls, boolean z);

    boolean dependsOn(Plugin plugin, Class cls);

    String[] getPrivilegedPlugins();

    void removePluginListener(PluginListener pluginListener);
}
