package com.android.systemui.statusbar.notification.interruption;

import android.os.PowerManager;
import com.android.systemui.plugins.statusbar.StatusBarStateController;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PeekDeviceNotInUseSuppressor extends VisualInterruptionCondition {
    public final PowerManager powerManager;
    public final StatusBarStateController statusBarStateController;

    public PeekDeviceNotInUseSuppressor(PowerManager powerManager, StatusBarStateController statusBarStateController) {
        super("device not in use", Collections.singleton(VisualInterruptionType.PEEK));
        this.powerManager = powerManager;
        this.statusBarStateController = statusBarStateController;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
    public final boolean shouldSuppress() {
        return !this.powerManager.isScreenOn() || this.statusBarStateController.isDreaming();
    }
}
