package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.WindowManagerLockscreenVisibilityInteractor;
import kotlinx.coroutines.flow.Flow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WindowManagerLockscreenVisibilityViewModel {
    public final Flow aodVisibility;
    public final Flow lockscreenVisibility;
    public final Flow surfaceBehindAnimating;
    public final Flow surfaceBehindVisibility;

    public WindowManagerLockscreenVisibilityViewModel(WindowManagerLockscreenVisibilityInteractor windowManagerLockscreenVisibilityInteractor) {
        Flow flow = windowManagerLockscreenVisibilityInteractor.surfaceBehindVisibility;
        Flow flow2 = windowManagerLockscreenVisibilityInteractor.usingKeyguardGoingAwayAnimation;
        Flow flow3 = windowManagerLockscreenVisibilityInteractor.lockscreenVisibility;
        Flow flow4 = windowManagerLockscreenVisibilityInteractor.aodVisibility;
    }
}
