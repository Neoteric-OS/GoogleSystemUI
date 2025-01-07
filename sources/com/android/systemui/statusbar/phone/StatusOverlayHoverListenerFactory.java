package com.android.systemui.statusbar.phone;

import android.content.res.Resources;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusOverlayHoverListenerFactory {
    public final ConfigurationController configurationController;
    public final DarkIconDispatcherImpl darkIconDispatcher;
    public final Resources resources;

    public StatusOverlayHoverListenerFactory(Resources resources, ConfigurationController configurationController, DarkIconDispatcherImpl darkIconDispatcherImpl) {
        this.resources = resources;
        this.configurationController = configurationController;
        this.darkIconDispatcher = darkIconDispatcherImpl;
    }
}
