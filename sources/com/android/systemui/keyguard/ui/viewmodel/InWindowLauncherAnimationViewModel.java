package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.InWindowLauncherUnlockAnimationInteractor;
import kotlinx.coroutines.flow.ReadonlyStateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class InWindowLauncherAnimationViewModel {
    public final ReadonlyStateFlow shouldPrepareForInWindowAnimation;
    public final ReadonlyStateFlow shouldStartInWindowAnimation;

    public InWindowLauncherAnimationViewModel(InWindowLauncherUnlockAnimationInteractor inWindowLauncherUnlockAnimationInteractor) {
        this.shouldPrepareForInWindowAnimation = inWindowLauncherUnlockAnimationInteractor.transitioningToGoneWithInWindowAnimation;
        this.shouldStartInWindowAnimation = inWindowLauncherUnlockAnimationInteractor.shouldStartInWindowAnimation;
    }
}
