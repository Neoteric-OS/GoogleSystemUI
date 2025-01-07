package com.android.systemui.shared.plugins;

import android.content.ComponentName;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface PluginEnabler {
    int getDisableReason(ComponentName componentName);

    boolean isEnabled(ComponentName componentName);

    void setDisabled(ComponentName componentName, int i);

    void setEnabled(ComponentName componentName);
}
