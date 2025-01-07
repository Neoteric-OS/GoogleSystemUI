package com.android.systemui.qs.dagger;

import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.FeatureFlagsClassicRelease;
import com.android.systemui.flags.Flags;
import com.android.systemui.util.settings.GlobalSettings;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface QSFlagsModule {
    static boolean isPMLiteEnabled(FeatureFlags featureFlags, GlobalSettings globalSettings) {
        return ((FeatureFlagsClassicRelease) featureFlags).isEnabled(Flags.POWER_MENU_LITE) && globalSettings.getInt(1, "sysui_pm_lite") != 0;
    }
}
