package com.android.systemui.statusbar.notification.interruption;

import android.hardware.display.AmbientDisplayConfiguration;
import com.android.systemui.settings.UserTracker;
import com.android.systemui.settings.UserTrackerImpl;
import java.util.Collections;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class PulseDisabledSuppressor extends VisualInterruptionCondition {
    public final AmbientDisplayConfiguration ambientDisplayConfiguration;
    public final UserTracker userTracker;

    public PulseDisabledSuppressor(AmbientDisplayConfiguration ambientDisplayConfiguration, UserTracker userTracker) {
        super("pulse disabled by user setting", Collections.singleton(VisualInterruptionType.PULSE));
        this.ambientDisplayConfiguration = ambientDisplayConfiguration;
        this.userTracker = userTracker;
    }

    @Override // com.android.systemui.statusbar.notification.interruption.VisualInterruptionCondition
    public final boolean shouldSuppress() {
        return !this.ambientDisplayConfiguration.pulseOnNotificationEnabled(((UserTrackerImpl) this.userTracker).getUserId());
    }
}
