package com.android.systemui.controls;

import android.content.ComponentName;
import android.graphics.drawable.Icon;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ControlInterface {
    ComponentName getComponent();

    String getControlId();

    Icon getCustomIcon();

    int getDeviceType();

    boolean getFavorite();

    default boolean getRemoved() {
        return false;
    }

    CharSequence getSubtitle();

    CharSequence getTitle();
}
