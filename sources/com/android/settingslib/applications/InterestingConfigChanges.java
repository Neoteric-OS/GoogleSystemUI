package com.android.settingslib.applications;

import android.content.res.Configuration;
import android.content.res.Resources;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InterestingConfigChanges {
    public final int mFlags;
    public final Configuration mLastConfiguration = new Configuration();

    public InterestingConfigChanges(int i) {
        this.mFlags = i;
    }

    public final boolean applyNewConfig(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        Configuration configuration2 = this.mLastConfiguration;
        return (this.mFlags & configuration2.updateFrom(Configuration.generateDelta(configuration2, configuration))) != 0;
    }
}
