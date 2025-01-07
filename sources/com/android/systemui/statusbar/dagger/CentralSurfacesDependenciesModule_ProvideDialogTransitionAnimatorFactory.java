package com.android.systemui.statusbar.dagger;

import android.service.dreams.IDreamManager;
import com.android.internal.jank.InteractionJankMonitor;
import com.android.systemui.animation.DialogTransitionAnimator;
import com.android.systemui.statusbar.policy.KeyguardStateController;
import dagger.Lazy;
import dagger.internal.Provider;
import java.util.concurrent.Executor;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class CentralSurfacesDependenciesModule_ProvideDialogTransitionAnimatorFactory implements Provider {
    public static DialogTransitionAnimator provideDialogTransitionAnimator(Executor executor, IDreamManager iDreamManager, KeyguardStateController keyguardStateController, Lazy lazy, InteractionJankMonitor interactionJankMonitor, CentralSurfacesDependenciesModule$2 centralSurfacesDependenciesModule$2) {
        return new DialogTransitionAnimator(executor, new CentralSurfacesDependenciesModule$1(iDreamManager, keyguardStateController, lazy), interactionJankMonitor, centralSurfacesDependenciesModule$2);
    }
}
