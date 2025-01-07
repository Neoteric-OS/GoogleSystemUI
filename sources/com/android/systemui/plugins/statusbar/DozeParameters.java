package com.android.systemui.plugins.statusbar;

import com.android.systemui.plugins.annotations.ProvidesInterface;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
@ProvidesInterface(version = 1)
/* loaded from: classes.dex */
public interface DozeParameters {
    public static final int VERSION = 1;

    boolean shouldControlScreenOff();
}
