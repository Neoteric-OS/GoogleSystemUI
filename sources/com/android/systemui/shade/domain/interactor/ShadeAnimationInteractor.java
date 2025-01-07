package com.android.systemui.shade.domain.interactor;

import com.android.systemui.biometrics.AuthContainerView$$ExternalSyntheticOutline0;
import com.android.systemui.shade.data.repository.ShadeAnimationRepository;
import kotlinx.coroutines.flow.ReadonlyStateFlow;
import kotlinx.coroutines.flow.StateFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class ShadeAnimationInteractor {
    public final ReadonlyStateFlow isLaunchingActivity;
    public final ShadeAnimationRepository shadeAnimationRepository;

    public ShadeAnimationInteractor(ShadeAnimationRepository shadeAnimationRepository) {
        this.shadeAnimationRepository = shadeAnimationRepository;
        this.isLaunchingActivity = new ReadonlyStateFlow(shadeAnimationRepository.isLaunchingActivity);
    }

    public abstract StateFlow isAnyCloseAnimationRunning();

    public final void setIsLaunchingActivity(boolean z) {
        AuthContainerView$$ExternalSyntheticOutline0.m(z, this.shadeAnimationRepository.isLaunchingActivity, null);
    }
}
