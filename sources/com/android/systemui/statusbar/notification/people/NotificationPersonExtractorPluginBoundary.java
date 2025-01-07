package com.android.systemui.statusbar.notification.people;

import com.android.systemui.plugins.NotificationPersonExtractorPlugin;
import com.android.systemui.plugins.PluginManager;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl;
import com.android.systemui.statusbar.policy.ExtensionControllerImpl.ExtensionImpl.PluginItem;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationPersonExtractorPluginBoundary {
    public NotificationPersonExtractorPlugin plugin;

    public NotificationPersonExtractorPluginBoundary(ExtensionControllerImpl extensionControllerImpl) {
        extensionControllerImpl.getClass();
        ExtensionControllerImpl.ExtensionImpl extensionImpl = extensionControllerImpl.new ExtensionImpl();
        extensionImpl.mProducers.add(extensionImpl.new PluginItem(PluginManager.Helper.getAction(NotificationPersonExtractorPlugin.class), NotificationPersonExtractorPlugin.class));
        extensionImpl.mCallbacks.add(new Consumer() { // from class: com.android.systemui.statusbar.notification.people.NotificationPersonExtractorPluginBoundary.1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                NotificationPersonExtractorPluginBoundary.this.plugin = (NotificationPersonExtractorPlugin) obj;
            }
        });
        Collections.sort(extensionImpl.mProducers, Comparator.comparingInt(new ExtensionControllerImpl$ExtensionBuilder$$ExternalSyntheticLambda0()));
        ExtensionControllerImpl.ExtensionImpl.m887$$Nest$mnotifyChanged(extensionImpl);
        this.plugin = (NotificationPersonExtractorPlugin) extensionImpl.mItem;
    }
}
