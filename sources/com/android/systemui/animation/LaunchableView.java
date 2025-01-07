package com.android.systemui.animation;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface LaunchableView {
    default Rect getPaddingForLaunchAnimation() {
        return new Rect();
    }

    void setShouldBlockVisibilityChanges(boolean z);

    default void onActivityLaunchAnimationEnd() {
    }
}
