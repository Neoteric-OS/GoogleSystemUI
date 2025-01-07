package com.android.systemui.controls.dagger;

import android.content.pm.PackageManager;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public abstract class ControlsModule {
    public static final boolean providesControlsFeatureEnabled(PackageManager packageManager) {
        return packageManager.hasSystemFeature("android.software.controls");
    }
}
