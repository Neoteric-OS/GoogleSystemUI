package com.android.systemui.ambient.touch;

import android.graphics.Rect;
import android.graphics.Region;
import com.android.systemui.ambient.touch.TouchMonitor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface TouchHandler {
    default Boolean isEnabled() {
        return Boolean.TRUE;
    }

    void onSessionStart(TouchMonitor.TouchSessionImpl touchSessionImpl);

    default void onDestroy() {
    }

    default void getTouchInitiationRegion(Rect rect, Region region, Rect rect2) {
    }
}
