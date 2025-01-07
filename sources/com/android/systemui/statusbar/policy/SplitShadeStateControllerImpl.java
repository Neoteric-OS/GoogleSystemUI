package com.android.systemui.statusbar.policy;

import android.content.res.Resources;
import com.android.systemui.flags.FeatureFlags;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SplitShadeStateControllerImpl {
    public final FeatureFlags featureFlags;

    public SplitShadeStateControllerImpl(FeatureFlags featureFlags) {
        this.featureFlags = featureFlags;
    }

    public final boolean shouldUseSplitNotificationShade(Resources resources) {
        if (resources.getBoolean(R.bool.config_use_split_notification_shade)) {
            return true;
        }
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.featureFlags.getClass();
        return false;
    }
}
