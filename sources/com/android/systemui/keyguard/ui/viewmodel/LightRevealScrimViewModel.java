package com.android.systemui.keyguard.ui.viewmodel;

import com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor;
import com.android.systemui.keyguard.domain.interactor.LightRevealScrimInteractor$special$$inlined$filter$1;
import kotlinx.coroutines.flow.SafeFlow;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LightRevealScrimViewModel {
    public final SafeFlow lightRevealEffect;
    public final LightRevealScrimInteractor$special$$inlined$filter$1 revealAmount;

    public LightRevealScrimViewModel(LightRevealScrimInteractor lightRevealScrimInteractor) {
        SafeFlow safeFlow = lightRevealScrimInteractor.lightRevealEffect;
        LightRevealScrimInteractor$special$$inlined$filter$1 lightRevealScrimInteractor$special$$inlined$filter$1 = lightRevealScrimInteractor.revealAmount;
    }
}
