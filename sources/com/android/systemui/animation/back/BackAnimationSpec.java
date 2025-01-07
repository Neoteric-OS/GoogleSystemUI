package com.android.systemui.animation.back;

import android.window.BackEvent;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface BackAnimationSpec {
    void getBackTransformation(BackEvent backEvent, float f, BackTransformation backTransformation);
}
