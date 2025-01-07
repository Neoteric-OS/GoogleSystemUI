package com.android.systemui.keyguard;

import android.app.IActivityTaskManager;
import com.android.systemui.keyguard.domain.interactor.KeyguardDismissTransitionInteractor;
import com.android.systemui.keyguard.ui.binder.KeyguardSurfaceBehindParamsApplier;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerLockscreenVisibilityManager {
    public final IActivityTaskManager activityTaskManagerService;
    public final Executor executor;
    public final KeyguardStateController keyguardStateController;
    public final KeyguardSurfaceBehindParamsApplier keyguardSurfaceBehindAnimator;

    public WindowManagerLockscreenVisibilityManager(Executor executor, IActivityTaskManager iActivityTaskManager, KeyguardStateController keyguardStateController, KeyguardSurfaceBehindParamsApplier keyguardSurfaceBehindParamsApplier, KeyguardDismissTransitionInteractor keyguardDismissTransitionInteractor) {
    }
}
