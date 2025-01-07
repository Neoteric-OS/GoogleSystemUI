package com.google.android.systemui.power.batteryevent.repository;

import com.android.systemui.settings.UserTracker;
import com.android.systemui.util.settings.GlobalSettings;
import com.android.systemui.util.settings.SecureSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SettingsDataSource {
    public final GlobalSettings globalSettings;
    public boolean lastAirplaneState;
    public int lastChargingLimitSettings;
    public boolean lastDndState;
    public int lastDockDefenderByPass;
    public final SecureSettings secureSettings;
    public final UserTracker userTracker;

    public SettingsDataSource(GlobalSettings globalSettings, SecureSettings secureSettings, UserTracker userTracker) {
        this.globalSettings = globalSettings;
        this.secureSettings = secureSettings;
        this.userTracker = userTracker;
    }
}
