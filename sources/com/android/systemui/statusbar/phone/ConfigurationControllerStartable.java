package com.android.systemui.statusbar.phone;

import com.android.systemui.CoreStartable;
import com.android.systemui.statusbar.policy.ConfigurationController;
import java.util.Iterator;
import java.util.Set;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ConfigurationControllerStartable implements CoreStartable {
    public final ConfigurationController configurationController;
    public final Set listeners;

    public ConfigurationControllerStartable(ConfigurationController configurationController, Set set) {
        this.configurationController = configurationController;
        this.listeners = set;
    }

    @Override // com.android.systemui.CoreStartable
    public final void start() {
        Iterator it = this.listeners.iterator();
        while (it.hasNext()) {
            ((ConfigurationControllerImpl) this.configurationController).addCallback((ConfigurationController.ConfigurationListener) it.next());
        }
    }
}
