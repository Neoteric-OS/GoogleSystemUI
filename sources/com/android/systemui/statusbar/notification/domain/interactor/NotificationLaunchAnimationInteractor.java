package com.android.systemui.statusbar.notification.domain.interactor;

import android.util.Log;
import com.android.systemui.animation.ActivityTransitionAnimator;
import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.data.repository.NotificationLaunchAnimationRepository;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotificationLaunchAnimationInteractor {
    public final NotificationLaunchAnimationRepository repository;

    public NotificationLaunchAnimationInteractor(NotificationLaunchAnimationRepository notificationLaunchAnimationRepository) {
        this.repository = notificationLaunchAnimationRepository;
    }

    public final void setIsLaunchAnimationRunning(boolean z) {
        if (ActivityTransitionAnimator.DEBUG_TRANSITION_ANIMATION) {
            Log.d("NotificationLaunchAnimationInteractor", "setIsLaunchAnimationRunning(running=" + z + ")");
        }
        AuthContainerView$$ExternalSyntheticOutline0.m(z, this.repository.isLaunchAnimationRunning, null);
    }
}
