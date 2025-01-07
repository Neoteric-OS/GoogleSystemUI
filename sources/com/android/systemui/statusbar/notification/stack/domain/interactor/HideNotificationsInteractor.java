package com.android.systemui.statusbar.notification.stack.domain.interactor;

import com.android.systemui.common.ui.domain.interactor.ConfigurationInteractor;
import com.android.systemui.power.domain.interactor.PowerInteractor;
import com.android.systemui.unfold.domain.interactor.UnfoldTransitionInteractor;
import com.android.systemui.util.animation.data.repository.AnimationStatusRepositoryImpl;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class HideNotificationsInteractor {
    public final AnimationStatusRepositoryImpl animationsStatus;
    public final PowerInteractor powerInteractor;
    public final UnfoldTransitionInteractor unfoldTransitionInteractor;

    public HideNotificationsInteractor(UnfoldTransitionInteractor unfoldTransitionInteractor, ConfigurationInteractor configurationInteractor, AnimationStatusRepositoryImpl animationStatusRepositoryImpl, PowerInteractor powerInteractor) {
    }
}
