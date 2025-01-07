package com.android.keyguard.mediator;

import android.os.Handler;
import com.android.systemui.unfold.FoldAodAnimationController;
import com.android.systemui.util.concurrency.PendingTasksContainer;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenOnCoordinator {
    public final FoldAodAnimationController foldAodAnimationController;
    public final Set fullScreenLightRevealAnimations;
    public final PendingTasksContainer pendingTasks;

    public ScreenOnCoordinator(Optional optional, Handler handler) {
        this.foldAodAnimationController = (FoldAodAnimationController) optional.map(ScreenOnCoordinator$foldAodAnimationController$1.INSTANCE).orElse(null);
        this.fullScreenLightRevealAnimations = (Set) optional.map(ScreenOnCoordinator$fullScreenLightRevealAnimations$1.INSTANCE).orElse(null);
        PendingTasksContainer pendingTasksContainer = new PendingTasksContainer();
        pendingTasksContainer.pendingTasksCount = new AtomicInteger(0);
        pendingTasksContainer.completionCallback = new AtomicReference();
        this.pendingTasks = pendingTasksContainer;
    }
}
