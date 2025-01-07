package com.android.wm.shell.sysui;

import android.content.res.Configuration;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface ConfigurationChangeListener {
    void onConfigurationChanged(Configuration configuration);

    default void onDensityOrFontScaleChanged$1() {
    }

    default void onThemeChanged() {
    }
}
