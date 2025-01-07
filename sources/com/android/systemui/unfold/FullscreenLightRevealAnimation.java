package com.android.systemui.unfold;

import com.android.systemui.util.concurrency.PendingTasksContainer$registerTask$1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface FullscreenLightRevealAnimation {
    void init();

    void onScreenTurningOn(PendingTasksContainer$registerTask$1 pendingTasksContainer$registerTask$1);
}
