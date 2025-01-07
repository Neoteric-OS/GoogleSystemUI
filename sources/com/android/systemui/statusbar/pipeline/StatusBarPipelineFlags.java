package com.android.systemui.statusbar.pipeline;

import android.R;
import android.content.Context;
import com.android.systemui.flags.FeatureFlags;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarPipelineFlags {
    public final FeatureFlags featureFlags;
    public final String mobileSlot;
    public final String wifiSlot;

    public StatusBarPipelineFlags(Context context, FeatureFlags featureFlags) {
        this.featureFlags = featureFlags;
        this.mobileSlot = context.getString(R.string.suspended_widget_accessibility);
        this.wifiSlot = context.getString(R.string.time_picker_decrement_minute_button);
    }
}
