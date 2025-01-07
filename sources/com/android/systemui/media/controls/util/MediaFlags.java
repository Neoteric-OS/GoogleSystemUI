package com.android.systemui.media.controls.util;

import com.android.systemui.flags.FeatureFlagsClassic;
import com.android.systemui.flags.Flags;
import com.android.systemui.flags.UnreleasedFlag;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaFlags {
    public final FeatureFlagsClassic featureFlags;

    public MediaFlags(FeatureFlagsClassic featureFlagsClassic) {
        this.featureFlags = featureFlagsClassic;
    }

    public final void isPersistentSsCardEnabled() {
        UnreleasedFlag unreleasedFlag = Flags.NULL_FLAG;
        this.featureFlags.getClass();
    }
}
