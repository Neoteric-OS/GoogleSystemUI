package com.android.systemui.statusbar.events;

import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface BackgroundAnimatableView {
    default View getContentView() {
        return null;
    }

    void setBoundsForAnimation(int i, int i2, int i3, int i4);
}
