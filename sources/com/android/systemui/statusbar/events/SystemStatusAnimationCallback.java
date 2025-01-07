package com.android.systemui.statusbar.events;

import androidx.core.animation.Animator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface SystemStatusAnimationCallback {
    default Animator onSystemEventAnimationBegin() {
        return null;
    }

    default Animator onSystemEventAnimationFinish(boolean z) {
        return null;
    }

    default void onHidePersistentDot() {
    }

    default void onSystemStatusAnimationTransitionToPersistentDot(String str) {
    }
}
