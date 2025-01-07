package com.android.systemui.statusbar;

import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface StatusIconDisplayable extends DarkIconDispatcher.DarkReceiver {
    String getSlot();

    int getVisibleState();

    default boolean isIconBlocked() {
        return false;
    }

    boolean isIconVisible();

    void setDecorColor(int i);

    void setStaticDrawableColor(int i);

    default void setStaticDrawableColor(int i, int i2) {
        setStaticDrawableColor(i);
    }

    default void setVisibleState(int i) {
        setVisibleState(2, false);
    }

    void setVisibleState(int i, boolean z);
}
