package com.android.systemui.statusbar.policy;

import android.content.Context;
import android.content.res.Configuration;
import com.android.systemui.statusbar.CommandQueue;
import com.android.systemui.statusbar.phone.ConfigurationControllerImpl;
import com.android.systemui.statusbar.policy.ConfigurationController;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class RemoteInputQuickSettingsDisabler implements ConfigurationController.ConfigurationListener {
    public final CommandQueue commandQueue;
    public final Context context;
    public boolean isLandscape;
    public boolean remoteInputActive;
    public boolean shouldUseSplitNotificationShade;
    public final SplitShadeStateControllerImpl splitShadeStateController;

    public RemoteInputQuickSettingsDisabler(Context context, CommandQueue commandQueue, SplitShadeStateControllerImpl splitShadeStateControllerImpl, ConfigurationController configurationController) {
        this.context = context;
        this.commandQueue = commandQueue;
        this.splitShadeStateController = splitShadeStateControllerImpl;
        this.isLandscape = context.getResources().getConfiguration().orientation == 2;
        this.shouldUseSplitNotificationShade = splitShadeStateControllerImpl.shouldUseSplitNotificationShade(context.getResources());
        ((ConfigurationControllerImpl) configurationController).addCallback(this);
    }

    public final int adjustDisableFlags(int i) {
        return (this.remoteInputActive && this.isLandscape && !this.shouldUseSplitNotificationShade) ? i | 1 : i;
    }

    @Override // com.android.systemui.statusbar.policy.ConfigurationController.ConfigurationListener
    public final void onConfigChanged(Configuration configuration) {
        boolean z = false;
        boolean z2 = configuration.orientation == 2;
        if (z2 != this.isLandscape) {
            this.isLandscape = z2;
            z = true;
        }
        boolean shouldUseSplitNotificationShade = this.splitShadeStateController.shouldUseSplitNotificationShade(this.context.getResources());
        if (shouldUseSplitNotificationShade != this.shouldUseSplitNotificationShade) {
            this.shouldUseSplitNotificationShade = shouldUseSplitNotificationShade;
            z = true;
        }
        if (z) {
            this.commandQueue.recomputeDisableFlags(this.context.getDisplayId(), true);
        }
    }
}
