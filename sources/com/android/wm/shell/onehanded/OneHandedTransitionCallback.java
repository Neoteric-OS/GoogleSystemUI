package com.android.wm.shell.onehanded;

import android.graphics.Rect;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface OneHandedTransitionCallback {
    void onStartFinished(Rect rect);

    void onStopFinished(Rect rect);

    default void onStartTransition() {
    }
}
